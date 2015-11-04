using Locadora.Dominio;

namespace Locadora.Models
{
    public class RelatorioModel
    {
        private Relatorio relatorio;

        public string Relatorio { get { return relatorio.GerarRelatorio(); } }

        public RelatorioModel()
        {
            relatorio = new Relatorio();
        }
    }
}