using Locadora.Dominio.Repositorio;
using Locadora.Dominio.Servicos;
using Locadora.Repositorio.EF;
using System.Collections.Generic;
using System.Web.Mvc;
using System.Web.Security;
using WebApplication1.Models;

namespace WebApplication1.Controllers
{
    public class LoginController : Controller
    {
        // GET: Login
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult Login(string usuario, string senha)
        {
            IUsuarioRepositorio repositorio = new UsuarioRepositorio();
            ServicoAutenticacao autenticador = new ServicoAutenticacao(repositorio);

            var usuarioAutenticado = autenticador.BuscarPorAutenticacao(usuario, senha);
            var ehUsuarioValido = usuarioAutenticado != null;

            if (ehUsuarioValido)
            {
                var usuarioLogadoModel = new UsuarioLogado(usuarioAutenticado);

                FormsAuthentication.SetAuthCookie(usuario, true);
                Session["USUARIO_LOGADO"] = usuarioLogadoModel;
            }

            return RedirectToAction("Index", "Home");
        }

    }
}