using System.Security.Principal;
using System.Threading;
using System.Web;
using System.Web.Mvc;
using WebApplication1.Models;

namespace WebApplication1.Filters
{
    public class Autorizador : AuthorizeAttribute
    {
        public override void OnAuthorization(AuthorizationContext filterContext)
        {
            UsuarioLogado usuarioLogado = filterContext.HttpContext.Session["USUARIO_LOGADO"] as UsuarioLogado;
            bool sessaoPossuiUsuarioLogado = usuarioLogado != null;

            if (sessaoPossuiUsuarioLogado)
            {
                var identidade = new GenericIdentity(usuarioLogado.Usuario);
                string[] roles = usuarioLogado.Permissoes;

                var principal = new GenericPrincipal(identidade, roles);

                Thread.CurrentPrincipal = principal;
                HttpContext.Current.User = principal;
            }

            base.OnAuthorization(filterContext);
        }
    }
}