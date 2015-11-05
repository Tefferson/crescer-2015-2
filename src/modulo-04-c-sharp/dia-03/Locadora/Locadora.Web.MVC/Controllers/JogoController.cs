using Locadora.Dominio.Repositorio;
using Locadora.Web.MVC.Models;
using System.Web.Mvc;

namespace Locadora.Web.MVC.Controllers
{
    public class JogoController : BaseController
    {
        private IJogoRepositorio repositorio = null;

        // GET: Jogo
        public ActionResult Detalhes(int id)
        {
            repositorio = CriarJogoRepositorio();
            var jogo = repositorio.BuscarPorId(id);

            DetalhesJogoModel model = new DetalhesJogoModel()
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