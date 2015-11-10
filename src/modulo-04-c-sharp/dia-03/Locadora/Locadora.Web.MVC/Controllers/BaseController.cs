using Locadora.Dominio.Repositorio;
using System.Web.Mvc;

namespace Locadora.Web.MVC.Controllers
{
    public abstract class BaseController : Controller
    {
        public IJogoRepositorio CriarJogoRepositorio()
        {
            return new Locadora.Repositorio.EF.JogoRepositorio();
        }
    }
}