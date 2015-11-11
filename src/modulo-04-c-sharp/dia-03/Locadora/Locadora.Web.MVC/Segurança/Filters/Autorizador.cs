using Locadora.Web.MVC.Models;
using Locadora.Web.MVC.Seguranca;
using System.Security.Principal;
using System.Threading;
using System.Web;
using System.Web.Mvc;
using System.Web.Routing;

namespace WebApplication1.Filters
{
    public class Autorizador : AuthorizeAttribute
    {
        public override void OnAuthorization(AuthorizationContext filterContext)
        {
            UsuarioLogadoModel usuario = ControleDeSessao.UsuarioLogado;

            if (usuario != null && AuthorizeCore(filterContext.HttpContext))
            {
                GenericIdentity myIdentity = new GenericIdentity(usuario.Usuario);
                GenericPrincipal principal = new GenericPrincipal(myIdentity, usuario.Permissoes);

                Thread.CurrentPrincipal =
                    HttpContext.Current.User = principal;

                base.OnAuthorization(filterContext);
            }
            else
            {
                RedirecionarParaLogin(filterContext);
            }
        }

        private void RedirecionarParaLogin(AuthorizationContext filterContext)
        {
            filterContext.Result = new RedirectToRouteResult(
                                                new RouteValueDictionary
                                                {
                                                    { "action", "Index" },
                                                    { "controller", "Login" }
                                                });
        }
    }
}