using Locadora.Dominio.Repositorio;
using Locadora.Dominio.Servicos;
using Locadora.Repositorio.EF;
using Locadora.Web.MVC.Models;
using System.Web.Mvc;
using System.Web.Security;

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
            if (ModelState.IsValid)
            {
                IUsuarioRepositorio repositorio = new UsuarioRepositorio();
                ServicoAutenticacao autenticador = new ServicoAutenticacao(repositorio);

                var usuarioAutenticado = autenticador.BuscarPorAutenticacao(usuario, senha);
                var autenticacaoEncontrada = usuarioAutenticado != null;

                if (autenticacaoEncontrada)
                {
                    var usuarioLogadoModel = new UsuarioLogadoModel(usuarioAutenticado);

                    FormsAuthentication.SetAuthCookie(usuario, true);
                    Session["USUARIO_LOGADO"] = usuarioLogadoModel;
                }
            }

            return RedirectToAction("Index", "Home");
        }

    }
}