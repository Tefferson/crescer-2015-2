using Locadora.Web.MVC.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Locadora.Web.MVC.Controllers
{
    public class RelatorioController : Controller
    {
        public ActionResult JogosDisponiveis()
        {
            var model = new List<JogoModel>()
            {
                new JogoModel() { Id = 1, Nome = "Teste", Preco = 9.9m, Categoria="RPG" },
                new JogoModel() { Id = 1, Nome = "dasd", Preco = 5m, Categoria="Aventura" },
                new JogoModel() { Id = 1, Nome = "dasda", Preco = 10m, Categoria="RPG" }
            };

            return View(model);
        }
    }
}