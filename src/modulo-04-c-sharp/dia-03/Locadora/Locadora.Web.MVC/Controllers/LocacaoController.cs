using System;
using System.Web.Mvc;
using Locadora.Web.MVC.Models;
using Locadora.Dominio.Repositorio;
using WebApplication1.Filters;
using Locadora.Web.MVC.Helpers;
using System.Linq;

namespace Locadora.Web.MVC.Controllers
{
    [Autorizador]
    public class LocacaoController : BaseController
    {
        private IJogoRepositorio jogoRepositorio = null;
        // GET: Locacao
        public ActionResult Locacao(int id)
        {
            jogoRepositorio = FabricaDeModulos.CriarJogoRepositorio();

            var jogo = jogoRepositorio.BuscarPorId(id);

            var model = new LocacaoModel()
            {
                Id = jogo.Id,
                NomeJogo = jogo.Nome,
                Imagem = jogo.Imagem,
                Valor = jogo.Selo.Preco,
                DataPrevista = DateTime.Now.AddDays(jogo.Selo.PrazoDevolucao)
            };

            return View(model);
        }

        public JsonResult Autocomplete(string term)
        {
            var repositorio = FabricaDeModulos.CriarClienteRepositorio();
            return Json(repositorio.BuscarPorNome(term).Select(c => new { label = c.Nome, value = c.Id }), JsonRequestBehavior.AllowGet);
        }
    }
}