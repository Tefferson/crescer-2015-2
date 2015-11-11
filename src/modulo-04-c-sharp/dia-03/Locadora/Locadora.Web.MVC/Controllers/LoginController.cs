using Locadora.Dominio.Repositorio;
using Locadora.Dominio.Servicos;
using Locadora.Repositorio.EF;
using Locadora.Web.MVC.Helpers;
using Locadora.Web.MVC.Models;
using Locadora.Web.MVC.Seguranca;
using System.Web.Mvc;
using System.Web.Security;

namespace WebApplication1.Controllers
{
    public class LoginController : Controller
    {
        // GET: Login
        public ActionResult Index()
        {
            if(ControleDeSessao.UsuarioLogado != null)
            {
                return RedirectToAction("Index", "Home");
            }

            return View();
        }

        public ActionResult Login(LoginModel loginModel)
        {
            if (ModelState.IsValid)
            {
                IUsuarioRepositorio repositorio = new UsuarioRepositorio();
                ServicoAutenticacao autenticador = FabricaDeModulos.CriarServicoAutenticacao();

                var usuarioAutenticado = autenticador.BuscarPorAutenticacao(loginModel.Usuario, loginModel.Senha);
                var autenticacaoEncontrada = usuarioAutenticado != null;

                if (autenticacaoEncontrada)
                {
                    ControleDeSessao.CriarSessaoDeUsuario(usuarioAutenticado);
                    return RedirectToAction("Index", "Home");
                }
            }

            ModelState.AddModelError("INVALID_LOGIN", "Usuário ou senha inválidos.");
            return View("Index", loginModel);
        }

        public void Sair()
        {
            ControleDeSessao.Encerrar();
        }

    }
}