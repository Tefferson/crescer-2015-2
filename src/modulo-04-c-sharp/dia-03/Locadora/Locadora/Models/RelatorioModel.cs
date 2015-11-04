using Locadora.Dominio;
using System.Collections.Generic;

namespace Locadora.Models
{
    public class RelatorioModel
    {
        private BaseDeDados dados;
        public IList<Jogo> ListaDeJogos { get; }

        public RelatorioModel()
        {
            dados = new BaseDeDados();
            ListaDeJogos = dados.PesquisarJogoPorNome("");
        }
    }
}