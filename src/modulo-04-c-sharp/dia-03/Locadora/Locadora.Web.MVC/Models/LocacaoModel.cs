using System;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace Locadora.Web.MVC.Models
{
    public class LocacaoModel
    {
        [Required]
        public int IdJogo { get; set; }
        public string NomeJogo { get; set; }
        public DateTime DataPrevista { get; set; }
        public decimal Valor { get; set; }
        public string ImagemJogo { get; set; }
        public string NomeSelo  { get; set; }
        [Required]
        [DisplayName("Cliente")]
        public string NomeCliente { get; set; }
    }
}