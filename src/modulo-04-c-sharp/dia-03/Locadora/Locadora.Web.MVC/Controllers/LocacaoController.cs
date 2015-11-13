using System;
using System.Web.Mvc;
using Locadora.Web.MVC.Models;
using Locadora.Dominio.Repositorio;
using WebApplication1.Filters;
using Locadora.Web.MVC.Helpers;
using System.Linq;
using Locadora.Dominio.Servicos;
using Locadora.Dominio;

namespace Locadora.Web.MVC.Controllers
{
    [Autorizador]
    public class LocacaoController : BaseController
    {
        private IJogoRepositorio jogoRepositorio = null;
        private IClienteRepositorio clienteRepositorio = null;
        private ILocacaoRepositorio locacaoRepositorio = null;
        private ServicoLocacao servicoLocacao = null;
        // GET: Locacao

        [HttpGet]
        [Autorizador(Roles = Permissao.OPERADOR)]
        public ActionResult Locacao(int id)
        {
            jogoRepositorio = FabricaDeModulos.CriarJogoRepositorio();

            var jogo = jogoRepositorio.BuscarPorId(id);
            bool jogoNaoEncontrado = jogo == null;

            if (jogoNaoEncontrado)
            {
                RedirectToAction("JogosDisponiveis", "Relatorio");
            }

            var model = new LocacaoModel()
            {
                IdJogo = jogo.Id,
                NomeJogo = jogo.Nome,
                ImagemJogo = jogo.Imagem,
                NomeSelo = jogo.Selo.Nome,
                Valor = jogo.Selo.Preco,
                DataPrevista = DateTime.Now.AddDays(jogo.Selo.PrazoDevolucao)
            };

            return View(model);
        }

        [HttpPost]
        [Autorizador(Roles = Permissao.OPERADOR)]
        public ActionResult Salvar(LocacaoModel model)
        {
            jogoRepositorio = FabricaDeModulos.CriarJogoRepositorio();
            clienteRepositorio = FabricaDeModulos.CriarClienteRepositorio();
            locacaoRepositorio = FabricaDeModulos.CriarLocacaoRepositorio();

            servicoLocacao = new ServicoLocacao(jogoRepositorio, clienteRepositorio, locacaoRepositorio);

            bool locacaoEfetuadaComSucesso = servicoLocacao.LocarJogoParaCliente(model.IdJogo, model.NomeCliente);

            @TempData["Mensagem"] = locacaoEfetuadaComSucesso ? "Locação efetuada" : "Erro na locação";
            @TempData["TipoMensagem"] = locacaoEfetuadaComSucesso ? "sucesso" : "falha";

            return RedirectToAction("JogosDisponiveis", "Relatorio");
        }

        public JsonResult Autocomplete(string term)
        {
            var repositorio = FabricaDeModulos.CriarClienteRepositorio();
            return Json(repositorio.BuscarPorNome(term).Select(c => new { label = c.Nome, value = c.Id }), JsonRequestBehavior.AllowGet);
        }
    }
}