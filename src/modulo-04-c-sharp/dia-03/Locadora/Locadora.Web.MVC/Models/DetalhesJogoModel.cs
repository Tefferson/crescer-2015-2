using Locadora.Dominio;

namespace Locadora.Web.MVC.Models
{
    public class DetalhesJogoModel
    {
        public string Nome { get; set; }
        public decimal Preco { get; set; }
        public string Categoria { get; set; }
        public Selo Selo { get; set; }
        public string Descricao { get; set; }
        public string Imagem { get; set; }
        public string Video { get; set; }
    }
}