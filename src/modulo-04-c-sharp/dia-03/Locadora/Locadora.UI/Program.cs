using Locadora.Dominio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;

namespace Locadora.UI
{
    class Program
    {
        static void Main(string[] args)
        {
            string caminho = Environment.CurrentDirectory + @"..\..\..\..\arquivos\game_store.xml";
            BaseDeDados dados = new BaseDeDados(caminho);

            //Jogo jogo = dados.PesquisarJogoPorNome("Sunset Riders Alterado");
            //Jogo jogoNovo = new Jogo("joguinho",23.99,"muita ação");
            //dados.CadastrarJogo(jogoNovo);
            //jogo.Nome += " De Novo";
            //dados.EditarJogo(jogo);
            dados.ExportarRelatorioEmTxt();
            Console.ReadKey();
        }
    }
}
