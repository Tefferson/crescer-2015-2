using Locadora.Dominio;
using System.Collections.Generic;

namespace Locadora.Models
{
    public class RelatorioModel
    {
        private BaseDeDados dados;
        public IList<Jogo> ListaDeJogos { get; }
        public string JogoMaisCaro { get; }
        public string JogoMaisBarato { get; }
        public string PrecoMedio { get; }

        public RelatorioModel()
        {
            dados = new BaseDeDados();
            ListaDeJogos = dados.PesquisarJogoPorNome();
            JogoMaisBarato = dados.GetNomeJogoMaisBarato();
            JogoMaisCaro = dados.GetNomeJogoMaisCaro();
            PrecoMedio = dados.GetValorMedio();
        }
    }
}