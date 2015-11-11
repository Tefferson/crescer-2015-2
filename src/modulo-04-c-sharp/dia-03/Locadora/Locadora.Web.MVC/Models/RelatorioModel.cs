using System.Collections.Generic;

namespace Locadora.Web.MVC.Models
{
    public class RelatorioModel
    {
        public IList<JogoModel> Jogos { get; set; }
        public int QuantidadeTotalDeJogos { get; set; }

        public RelatorioModel()
        {
            Jogos = new List<JogoModel>();
        }
    }
}