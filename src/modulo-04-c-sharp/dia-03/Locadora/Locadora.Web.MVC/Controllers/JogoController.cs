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
        public ActionResult Detalhes(int id)
        {
            repositorio = CriarJogoRepositorio();
            var jogo = repositorio.BuscarPorId(id);

            DetalhesJogoModel model = new DetalhesJogoModel()
            {
                Nome = jogo.Nome,
                Categoria = jogo.Categoria.ToString(),
                Selo = jogo.Selo,
                Descricao = jogo.Descricao,
                Imagem = jogo.Imagem,
                Video = jogo.Video
            };

            return View(model);
        }

        [HttpPost]
        [Autorizador(Roles = Permissao.ADMIN)]
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
        [Autorizador(Roles = Permissao.ADMIN)]
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
            ViewBag.ListaSelo = new SelectList(new List<Selo>() { new Selo("OURO", 15, 1) { }, new Selo("PRATA", 10, 2), new Selo("BRONZE", 5, 3) });
        }
    }
}