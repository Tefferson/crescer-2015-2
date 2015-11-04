using System.Collections.Generic;

namespace Locadora.Web.MVC.Models
{
    public class RelatorioModel
    {
        public IList<JogoModel> Jogos { get; set; }
        public int QuantidadeTotalDeJogos { get; set; }
        public string JogoMaisCaro { get; set; }
        public string JogoMaisBarato { get; set; }
        public decimal PrecoMedio { get; set; }

        public RelatorioModel()
        {
            Jogos = new List<JogoModel>();
        }
    }
}