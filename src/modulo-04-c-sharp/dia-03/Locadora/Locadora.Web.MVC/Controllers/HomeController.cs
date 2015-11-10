using Locadora.Web.MVC.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using WebApplication1.Filters;

namespace Locadora.Web.MVC.Controllers
{
    [Autorizador]
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            return View();
        }
    }
}