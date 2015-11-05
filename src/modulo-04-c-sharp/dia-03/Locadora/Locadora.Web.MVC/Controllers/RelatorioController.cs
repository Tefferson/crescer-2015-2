using Locadora.Dominio.Repositorio;
using Locadora.Web.MVC.Models;
using System.Linq;
using System.Web.Mvc;

namespace Locadora.Web.MVC.Controllers
{
    public class RelatorioController : Controller
    {
        private IJogoRepositorio repositorio = null;

        public ActionResult JogosDisponiveis()
        {
            repositorio = new Locadora.Repositorio.ADO.JogoRepositorio();
            var model = new RelatorioModel();

            foreach (var jogo in repositorio.BuscarTodos())
            {
                var jogoModel = new JogoModel()
                {
                    Id = jogo.Id,
                    Nome = jogo.Nome,
                    Preco = jogo.Preco,
                    Categoria = jogo.Categoria.ToString(),
                    Selo = jogo.Selo.ToString()
                };

                model.Jogos.Add(jogoModel);
            }

            model.QuantidadeTotalDeJogos = model.Jogos.Count;
            model.JogoMaisBarato = model.Jogos.OrderBy(jogo => jogo.Preco).First().Nome;
            model.JogoMaisCaro = model.Jogos.OrderByDescending(jogo => jogo.Preco).First().Nome;
            model.PrecoMedio = model.Jogos.Average(jogo => jogo.Preco);

            return View(model);
        }
    }
}