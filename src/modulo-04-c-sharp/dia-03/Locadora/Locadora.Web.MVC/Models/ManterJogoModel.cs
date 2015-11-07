using Locadora.Dominio;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace Locadora.Web.MVC.Models
{
    public class ManterJogoModel
    {
        public int Id { get; set; }
        [Required]
        public string Nome { get; set; }
        [Required]
        [DisplayName("Preço")]
        [RegularExpression("[0-9]+(,[0-9]{2})?")]
        public decimal Preco { get; set; }
        [Required]
        public Categoria Categoria { get; set; }
        [Required]
        public Selo Selo { get; set; }
        [Required]
        [DisplayName("Descrição")]
        public string Descricao { get; set; }
        [Required]
        public string Imagem { get; set; }
        [Required]
        [DisplayName("Vídeo")]
        public string Video { get; set; }
    }
}