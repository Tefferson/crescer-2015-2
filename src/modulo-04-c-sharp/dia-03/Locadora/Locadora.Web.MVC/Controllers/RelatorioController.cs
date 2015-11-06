using Locadora.Dominio;
using Locadora.Dominio.Repositorio;
using Locadora.Web.MVC.Models;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Web.Mvc;

namespace Locadora.Web.MVC.Controllers
{
    public class RelatorioController : BaseController
    {
        private IJogoRepositorio repositorio = null;

        public ActionResult JogosDisponiveis(string nome)
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

            foreach (var jogo in listaDeJogos)
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