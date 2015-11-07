using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Locadora.Web.MVC.Models
{
    public class DetalhesJogoModel
    {
        public string Nome { get; set; }
        public decimal Preco { get; set; }
        public string Categoria { get; set; }
        public string Selo { get; set; }
        public string Descricao { get; set; }
        public string Imagem { get; set; }
        public string Video { get; set; }
    }
}