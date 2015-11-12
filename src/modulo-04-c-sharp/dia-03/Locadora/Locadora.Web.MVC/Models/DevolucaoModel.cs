using System;

namespace Locadora.Web.MVC.Models
{
    public class DevolucaoModel
    {
        public string NomeJogo { get; set; }
        public DateTime DataLocacao { get; set; }
        public decimal ValorFinal { get; set; }
        public string ImagemJogo { get; set; }
    }
}