using Locadora.Dominio;
using Locadora.Dominio.Repositorio;
using Locadora.Web.MVC.Models;
using System.Collections.Generic;
using System.Linq;
using System.Web.Mvc;
using WebApplication1.Filters;

namespace Locadora.Web.MVC.Controllers
{
    [Autorizador]
    public class RelatorioController : BaseController
    {
        private IJogoRepositorio repositorio = null;

        public ActionResult JogosDisponiveis(string nome, string ordem = "Título(A-Z)")
        {
            repositorio = CriarJogoRepositorio();
            var model = new RelatorioModel();
            bool buscarPorNome = !string.IsNullOrEmpty(nome);

            IList<Jogo> listaDeJogos;

            if (buscarPorNome)
            {
                listaDeJogos = repositorio.BuscarPorNome(nome);
            }
            else
            {
                listaDeJogos = repositorio.BuscarTodos();
            }

            if (listaDeJogos.Count > 0)
            {
                ViewBag.Ordem = ordem;
                IOrderedEnumerable<Jogo> listaOrdenada = null;
                if (ordem == "Título(A-Z)")
                {
                    listaOrdenada = listaDeJogos.OrderBy(j => j.Nome);
                }
                else if (ordem == "Título(Z-A)")
                {
                    listaOrdenada = listaDeJogos.OrderByDescending(j => j.Nome);
                }
                else if (ordem == "Preço(menor-maior)")
                {
                    listaOrdenada = listaDeJogos.OrderBy(j => j.Preco);
                }
                else if (ordem == "Preço(maior-menor)")
                {
                    listaOrdenada = listaDeJogos.OrderByDescending(j => j.Preco);
                }

                foreach (var jogo in listaOrdenada)
                {
                    var jogoModel = new JogoModel()
                    {
                        Id = jogo.Id,
                        Nome = jogo.Nome,
                        Preco = jogo.Preco,
                        Categoria = jogo.Categoria.ToString(),
                        Imagem = jogo.Imagem
                    };

                    model.Jogos.Add(jogoModel);
                }
                model.QuantidadeTotalDeJogos = model.Jogos.Count;
                model.JogoMaisBarato = model.Jogos.OrderBy(jogo => jogo.Preco).First().Nome;
                model.JogoMaisCaro = model.Jogos.OrderByDescending(jogo => jogo.Preco).First().Nome;
                model.PrecoMedio = model.Jogos.Average(jogo => jogo.Preco);
            }
            return View(model);
        }

        public ActionResult Autocomplete(string term)
        {
            var repositorio = CriarJogoRepositorio();
            return Json(repositorio.BuscarPorNome(term).Select(j => new {label=j.Nome, value=j.Id, icon=j.Imagem }), JsonRequestBehavior.AllowGet);
        }
    }
}