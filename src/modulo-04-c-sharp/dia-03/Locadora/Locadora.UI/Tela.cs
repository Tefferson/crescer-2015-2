using Locadora.Dominio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Locadora.UI
{
    class Tela
    {
        const string CONTINUAR = "Por favor, pressione ENTER se quiser continuar...";
        const string INFORMAR_NOME = "Por favor, informe um nome:";
        const string INFORMAR_PRECO = "Por favor, informe um preço:";
        const string INFORMAR_OPCAO = "Por favor, informe uma opção:";
        const string INFORMAR_OPCAO_VALIDA = "Por favor, informe uma opção válida:";
        const string DIGITAR_ENTER_PARA_VOLTAR = "Pressione ENTER novamente para voltar";
        const string PESQUISA_FALHA = "Desculpe, sua consulta não teve sucesso.";
        const int PESQUISAR_JOGO_POR_NOME = 1;
        const int CADASTRAR_JOGO = 2;

        string caminho = Environment.CurrentDirectory + @"..\..\..\..\arquivos\game_store.xml";
        Telas current = Telas.Menu;
        Teclado teclado = new Teclado();
        BaseDeDados dados;
        public Tela()
        {
            dados = new BaseDeDados(caminho);
        }

        public bool UpdateTela()
        {
            Console.Clear();
            switch (current)
            {
                case Telas.Menu:
                    return Menu();
                case Telas.CadastroJogo:
                    return CadastrarJogo();
                case Telas.EditarJogo:
                    return EditarJogo();
                case Telas.PesquisarJogoNome:
                    return PesquisarJogoPorNome();
                case Telas.Sair:
                    return Sair();
            }
            return false;
        }

        private bool PesquisarJogoPorNome()
        {
            string nome;
            bool loop = true;
            while (loop)
            {
                nome = teclado.LerLinha(INFORMAR_NOME);
                if (nome == "")
                {
                    nome = teclado.LerString(DIGITAR_ENTER_PARA_VOLTAR);
                    if (nome == null)
                    {
                        current = Telas.Menu;
                        loop = false;
                    }
                    else
                    {
                        Console.Clear();
                        Jogo pesquisado = dados.PesquisarJogoPorNome(nome);
                        Console.WriteLine(pesquisado != null ? pesquisado.ToString() : PESQUISA_FALHA);
                        Console.WriteLine();
                    }
                }
                else
                {
                    Console.Clear();
                    Jogo pesquisado = dados.PesquisarJogoPorNome(nome);
                    Console.WriteLine(pesquisado != null ? pesquisado.ToString() : PESQUISA_FALHA);
                    Console.WriteLine();
                }

            }

            return true;
        }

        private bool Sair()
        {
            throw new NotImplementedException();
        }

        private bool EditarJogo()
        {
            throw new NotImplementedException();
        }

        private bool CadastrarJogo()
        {
            //implementar dps
            //string nome = teclado.LerString();
            //double? preco = teclado.LerDouble();
            //Categoria.
            return true;
        }

        private bool Menu()
        {
            //nl = new line
            string nl = Environment.NewLine;
            string[] opcoes = {
                "1-Pesquisar jogo por nome",
                "2-Cadastrar jogo",
                "3",
                "4",
                "5"
            };
            string ops = (String.Join(nl, opcoes) + nl);
            int? op = teclado.LerInt(ops, INFORMAR_OPCAO);
            while (op == null)
            {
                op = teclado.LerInt(INFORMAR_OPCAO);
            }

            switch (op)
            {
                case PESQUISAR_JOGO_POR_NOME:
                    current = Telas.PesquisarJogoNome;
                    break;
                case CADASTRAR_JOGO:
                    current = Telas.CadastroJogo;
                    break;
            }
            return true;
        }
    }
}
