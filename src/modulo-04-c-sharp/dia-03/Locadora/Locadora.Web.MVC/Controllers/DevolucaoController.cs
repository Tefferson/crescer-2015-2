using Locadora.Dominio;
using Locadora.Dominio.Repositorio;
using Locadora.Dominio.Servicos;
using Locadora.Web.MVC.Helpers;
using Locadora.Web.MVC.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using WebApplication1.Filters;

namespace Locadora.Web.MVC.Controllers
{
    [Autorizador]
    public class DevolucaoController : Controller
    {
        private IJogoRepositorio jogoRepositorio = null;
        private IClienteRepositorio clienteRepositorio = null;
        private ILocacaoRepositorio locacaoRepositorio = null;
        private ServicoLocacao servicoLocacao = null;

        // GET: Devolucao
        public ActionResult Devolucao()
        {
            return View();
        }

        public ActionResult Devolver(int idLocacao)
        {
            locacaoRepositorio = FabricaDeModulos.CriarLocacaoRepositorio();
            clienteRepositorio = FabricaDeModulos.CriarClienteRepositorio();
            jogoRepositorio = FabricaDeModulos.CriarJogoRepositorio();

            servicoLocacao = new ServicoLocacao(jogoRepositorio, clienteRepositorio, locacaoRepositorio);

            bool jogoFoiDevolvido = servicoLocacao.DevolverJogo(idLocacao);

            TempData["Mensagem"] = jogoFoiDevolvido ? "Jogo devolvido com sucesso" : "Não foi possível devolver o jogo";
            TempData["TipoMensagem"] = jogoFoiDevolvido ? "sucesso" : "falha";
            return RedirectToAction("Devolucao");
        }

        public ActionResult Detalhes(string nomeJogo)
        {
            locacaoRepositorio = FabricaDeModulos.CriarLocacaoRepositorio();
            clienteRepositorio = FabricaDeModulos.CriarClienteRepositorio();
            jogoRepositorio = FabricaDeModulos.CriarJogoRepositorio();

            servicoLocacao = new ServicoLocacao(jogoRepositorio, clienteRepositorio, locacaoRepositorio);

            var locacao = locacaoRepositorio.BuscarPendentesPorNomeDoJogo(nomeJogo).FirstOrDefault();
            bool jogoEstaLocado = locacao != null && !locacao.Jogo.Disponivel;

            if (jogoEstaLocado)
            {
                DevolucaoModel model = new DevolucaoModel()
                {
                    IdLocacao = locacao.Id,
                    DataLocacao = locacao.DataLocacao,
                    ImagemJogo = locacao.Jogo.Imagem,
                    NomeJogo = locacao.Jogo.Nome,
                    ValorFinal = servicoLocacao.CalcularValorFinal(locacao)
                };
                return View(model);
            }

            TempData["Mensagem"] = "Jogo informado não encontrado";
            TempData["TipoMensagem"] = "Falha";
            return View("Devolucao");
        }

        public JsonResult Autocomplete(string term)
        {
            var repositorio = FabricaDeModulos.CriarLocacaoRepositorio();
            return Json(repositorio.BuscarPorNomeDoJogo(term)
                            .Where(l => l.Situacao == Situacao.Pendente)
                            .Select(j => new { label = j.Jogo.Nome, value = j.Jogo.Id, icon = j.Jogo.Imagem }), 
                            JsonRequestBehavior.AllowGet);
        }
    }
}