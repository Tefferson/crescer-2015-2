using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Locadora.Web.MVC.Models
{
    public class JogoModel
    {
        public string Nome { get; set; }
        public decimal Preco { get; set; }
        public string Categoria { get; set; }
    }
}