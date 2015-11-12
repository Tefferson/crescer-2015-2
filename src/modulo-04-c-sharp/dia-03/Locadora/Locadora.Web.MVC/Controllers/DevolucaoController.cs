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

        public ActionResult Detalhes(string nomeJogo)
        {
            locacaoRepositorio = FabricaDeModulos.CriarLocacaoRepositorio();
            clienteRepositorio = FabricaDeModulos.CriarClienteRepositorio();
            jogoRepositorio = FabricaDeModulos.CriarJogoRepositorio();

            var servicoLocacao = new ServicoLocacao(jogoRepositorio, clienteRepositorio, locacaoRepositorio);

            var locacao = locacaoRepositorio.BuscarPendentesPorNomeDoJogo(nomeJogo).FirstOrDefault();

            if (locacao != null)
            {
                DevolucaoModel model = new DevolucaoModel()
                {
                    DataLocacao = locacao.DataLocacao,
                    ImagemJogo = locacao.Jogo.Imagem,
                    NomeJogo = locacao.Jogo.Nome,
                   // ValorFinal = servicoLocacao.CalcularValorFinal(locacao)
                };
            }

            return View("Devolucao");
        }
    }
}