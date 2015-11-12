using Locadora.Dominio;
using Locadora.Dominio.Repositorio;
using Locadora.Web.MVC.Helpers;
using Locadora.Web.MVC.Models;
using System.Collections.Generic;
using System.Web.Mvc;
using WebApplication1.Filters;

namespace Locadora.Web.MVC.Controllers
{
    [Autorizador]
    public class JogoController : BaseController
    {
        private IJogoRepositorio jogoRepositorio = null;
        private ISeloRepositorio seloRepositorio = null;

        [HttpGet]
        public ActionResult Detalhes(int id)
        {
            jogoRepositorio = FabricaDeModulos.CriarJogoRepositorio();
            var jogo = jogoRepositorio.BuscarPorId(id);

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
                jogoRepositorio = FabricaDeModulos.CriarJogoRepositorio();
                seloRepositorio = FabricaDeModulos.CriarSeloRepositorio();

                Jogo jogo = new Jogo(model.Id)
                {
                    Nome = model.Nome,
                    Categoria = model.Categoria,
                    Descricao = model.Descricao,
                    Imagem = model.Imagem,
                    Video = model.Video,
                    Selo = seloRepositorio.BuscarPorNome(model.Selo),
                    Disponivel = true
                };

                if (deveAlterar)
                {
                    jogoRepositorio.Atualizar(jogo);
                    TempData["Mensagem"] = "Jogo editado com sucesso!";
                }
                else
                {
                    jogoRepositorio.Criar(jogo);
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
            seloRepositorio = FabricaDeModulos.CriarSeloRepositorio();
            ColocarListaCategoriaEListaSeloNaViewBag();
            bool estaEditando = id > 0;

            if (estaEditando)
            {
                jogoRepositorio = FabricaDeModulos.CriarJogoRepositorio();
                Jogo jogo = jogoRepositorio.BuscarPorId(id);

                ManterJogoModel model = new ManterJogoModel()
                {
                    Nome = jogo.Nome,
                    Categoria = jogo.Categoria,
                    Selo = jogo.Selo.Nome,
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
            ViewBag.ListaSelo = new SelectList(seloRepositorio.BuscarTodosNomes());
        }
    }
}