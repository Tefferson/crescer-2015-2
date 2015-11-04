using System.Web.Mvc;

namespace Locadora.Controllers
{
    public class RelatorioController : Controller
    {
        // GET: Relatorio
        public ActionResult Jogos()
        {
            var model = new Locadora.Models.RelatorioModel();
            return View(model);
        }
    }
}