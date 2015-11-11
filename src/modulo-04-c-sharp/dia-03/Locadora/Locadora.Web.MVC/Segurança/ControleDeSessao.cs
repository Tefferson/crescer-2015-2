using Locadora.Dominio;
using Locadora.Web.MVC.Models;
using System.Web;
using System.Web.Security;

namespace Locadora.Web.MVC.Seguranca
{
    public class ControleDeSessao
    {
        private const string USUARIO_LOGADO = "USUARIO_LOGADO";

        public static UsuarioLogadoModel UsuarioLogado
        {
            get
            {
                return HttpContext.Current.Session[USUARIO_LOGADO] as UsuarioLogadoModel;
            }
        }

        public static void CriarSessaoDeUsuario(Usuario usuarioAutenticado)
        {
            var usuarioLogado = new UsuarioLogadoModel(usuarioAutenticado);
            FormsAuthentication.SetAuthCookie(usuarioLogado.Usuario, true);
            HttpContext.Current.Session[USUARIO_LOGADO] = usuarioLogado;
        }

        public static void Encerrar()
        {
            FormsAuthentication.SignOut();
            HttpContext.Current.Session.Abandon();
        }
    }
}