using Locadora.Dominio;

namespace Locadora.Web.MVC.Models
{
    public class JogoModel
    {
        public int Id { get; set; }
        public string Nome { get; set; }
        public string Categoria { get; set; }
        public string Imagem { get; set; }
        public Selo Selo;
    }
}