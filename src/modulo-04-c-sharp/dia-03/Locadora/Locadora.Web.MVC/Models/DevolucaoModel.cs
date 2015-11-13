using System;

namespace Locadora.Web.MVC.Models
{
    public class DevolucaoModel
    {
        public int IdLocacao { get; set; }
        public string NomeJogo { get; set; }
        public DateTime DataLocacao { get; set; }
        public decimal ValorFinal { get; set; }
        public string ImagemJogo { get; set; }
    }
}