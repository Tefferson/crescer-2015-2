using Locadora.Dominio;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace Locadora.Web.MVC.Models
{
    public class ManterJogoModel
    {
        public int Id { get; set; }
        [Required(ErrorMessage = "É obrigatório informar um nome válido")]
        public string Nome { get; set; }
        [Required(ErrorMessage = "É obrigatório selecionar uma categoria válida")]
        public Categoria Categoria { get; set; }
        [Required(ErrorMessage = "É obrigatório selecionar um selo válido")]
        public string Selo { get; set; }
        [Required(ErrorMessage = "É obrigatório inserir uma descrição")]
        [DisplayName("Descrição")]
        public string Descricao { get; set; }
        [Required(ErrorMessage = "É obrigatório informar uma imagem")]
        public string Imagem { get; set; }
        [Required(ErrorMessage = "É obrigatório informar um vídeo")]
        [DisplayName("Vídeo")]
        public string Video { get; set; }
    }
}