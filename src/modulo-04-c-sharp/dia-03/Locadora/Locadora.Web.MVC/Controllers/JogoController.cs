using Locadora.Dominio;
using Locadora.Dominio.Repositorio;
using Locadora.Web.MVC.Models;
using System.Collections.Generic;
using System.Web.Mvc;
using WebApplication1.Filters;

namespace Locadora.Web.MVC.Controllers
{
    [Autorizador]
    public class JogoController : BaseController
    {
        private IJogoRepositorio repositorio = null;

        [HttpGet]
        [Autorizador(Roles = "DETALHES_DO_JOGO")]
        public ActionResult Detalhes(int id)
        {
            repositorio = CriarJogoRepositorio();
            var jogo = repositorio.BuscarPorId(id);

            DetalhesJogoModel model = new DetalhesJogoModel()
            {
                Nome = jogo.Nome,
                Preco = jogo.Preco,
                Categoria = jogo.Categoria.ToString(),
                Selo = jogo.Selo.ToString(),
                Descricao = jogo.Descricao,
                Imagem = jogo.Imagem,
                Video = jogo.Video
            };

            return View(model);
        }

        [HttpPost]
        public ActionResult Salvar(ManterJogoModel model)
        {
            bool podeSalvarNoBanco = ModelState.IsValid;

            if (podeSalvarNoBanco)
            {
                bool deveAlterar = model.Id > 0;
                repositorio = CriarJogoRepositorio();
                Jogo jogo = new Jogo(model.Id)
                {
                    Nome = model.Nome,
                    Categoria = model.Categoria,
                    Descricao = model.Descricao,
                    Imagem = model.Imagem,
                    Video = model.Video,
                    Preco = model.Preco,
                    Selo = model.Selo
                };

                if (deveAlterar)
                {
                    repositorio.Atualizar(jogo);
                    TempData["Mensagem"] = "Jogo editado com sucesso!";
                }
                else
                {
                    repositorio.Criar(jogo);
                    TempData["Mensagem"] = "Jogo salvo com sucesso!";
                }

                return RedirectToAction("JogosDisponiveis", "Relatorio");
            }
            else
            {
                ColocarListaCategoriaEListaSeloNaViewBag();
                return View("Manter", model);
            }
        }

        [HttpGet]
        public ActionResult Manter(int id = -1)
        {
            ColocarListaCategoriaEListaSeloNaViewBag();
            bool estaEditando = id > 0;

            if (estaEditando)
            {
                repositorio = CriarJogoRepositorio();
                Jogo jogo = repositorio.BuscarPorId(id);

                ManterJogoModel model = new ManterJogoModel()
                {
                    Nome = jogo.Nome,
                    Preco = jogo.Preco,
                    Categoria = jogo.Categoria,
                    Selo = jogo.Selo,
                    Descricao = jogo.Descricao,
                    Imagem = jogo.Imagem,
                    Video = jogo.Video
                };

                return View(model);
            }
            else
            {
                return View(new ManterJogoModel());
            }
        }

        private void ColocarListaCategoriaEListaSeloNaViewBag()
        {
            ViewBag.ListaCategoria = new SelectList(new List<Categoria>() { Categoria.AVENTURA, Categoria.CORRIDA, Categoria.ESPORTE, Categoria.LUTA, Categoria.RPG });
            ViewBag.ListaSelo = new SelectList(new List<Selo>() { Selo.OURO, Selo.PRATA, Selo.BRONZE });
        }
    }
}