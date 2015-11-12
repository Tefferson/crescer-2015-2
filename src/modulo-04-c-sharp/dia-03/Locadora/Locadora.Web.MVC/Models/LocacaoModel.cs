using System;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace Locadora.Web.MVC.Models
{
    public class LocacaoModel
    {
        [Required]
        public int Id { get; set; }
        public string NomeJogo { get; set; }
        public DateTime DataPrevista { get; set; }
        public decimal Valor { get; set; }
        public string Imagem { get; set; }
        [Required]
        [DisplayName("Cliente")]
        public string NomeCliente { get; set; }
    }
}