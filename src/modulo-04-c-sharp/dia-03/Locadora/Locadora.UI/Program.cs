using Locadora.Dominio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Locadora.UI
{
    class Program
    {
        static void Main(string[] args)
        {
            BaseDeDados dados = new BaseDeDados(@"..\arquivos\game_store.xml");

            Jogo jogo = dados.PesquisarJogoPorNome("Sunset Riders");
            Jogo jogoNovo = new Jogo("joguinho",23.99,"muita ação");
            dados.CadastrarJogo(jogoNovo);
            jogo.Nome += " Alterado";
            dados.EditarJogo(jogo);
            Console.ReadKey();
        }
    }
}
