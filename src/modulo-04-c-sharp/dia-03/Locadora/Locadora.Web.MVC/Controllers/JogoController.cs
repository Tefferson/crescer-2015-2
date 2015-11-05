using Locadora.Dominio.Repositorio;
using Locadora.Web.MVC.Models;
using System.Web.Mvc;

namespace Locadora.Web.MVC.Controllers
{
    public class JogoController : Controller
    {
        private IJogoRepositorio repositorio = new Locadora.Repositorio.ADO.JogoRepositorio();
        // GET: Jogo
        public ActionResult Detalhes(int id)
        {
            var jogo = repositorio.BuscarPorId(id);

            JogoModel model = new JogoModel()
            {
                Id = jogo.Id,
                Nome = jogo.Nome,
                Preco = jogo.Preco,
                Categoria = jogo.Categoria.ToString(),
                Selo = jogo.Selo.ToString(),
                Descricao = jogo.Descricao
            };

            return View(model);
        }
    }
}