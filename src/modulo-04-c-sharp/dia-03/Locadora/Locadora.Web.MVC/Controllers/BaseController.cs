using Locadora.Dominio.Repositorio;
using System.Web.Mvc;
using WebApplication1.Filters;

namespace Locadora.Web.MVC.Controllers
{
    [Autorizador]
    public abstract class BaseController : Controller
    {
    }
}