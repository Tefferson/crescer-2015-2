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

        internal void start()
        {
            while(UpdateTela()) ;
        }

        const string INFORMAR_PRECO = "Por favor, informe um preço:";
        const string INFORMAR_OPCAO = "Por favor, informe uma opção:";
        const string INFORMAR_CATEGORIA = "Por favor, informe uma categoria:";
        const string INFORMAR_OPCAO_VALIDA = "Por favor, informe uma opção válida:";
        const string DIGITAR_ENTER_PARA_VOLTAR = "Pressione ENTER novamente para voltar";
        const string PESQUISA_FALHA = "Desculpe, sua consulta não teve sucesso.";
        const string PESQUISA_EDITAR_FALHA = "Desculpe, o jogo desejado não existe.";
        const string RELATORIO_GERADO = "Relatório gerado. Por favor, pressione ENTER para ir ao menu...";
        const int PESQUISAR_JOGO_POR_NOME = 1;
        const int CADASTRAR_JOGO = 2;
        const int EDITAR_JOGO = 3;
        const int GERAR_RELATORIO = 4;
        const int SAIR_DO_SISTEMA = 5;

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
                case Telas.ExportarTxt:
                    return GerarRelatorio();
                case Telas.Sair:
                    return Sair();
                default:
                    return false;
            }
        }

        private bool GerarRelatorio()
        {
            dados.ExportarRelatorioEmTxt();
            teclado.LerLinha(RELATORIO_GERADO);
            current = Telas.Menu;
            return true;
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
            teclado.LerLinha("Tchau!!!");
            return false;
        }

        private bool EditarJogo()
        {
            string nome;
            Jogo editar = null;
            while (true)
            {
                nome = teclado.LerLinha(INFORMAR_NOME);
                if (nome == "")
                {
                    nome = teclado.LerString(DIGITAR_ENTER_PARA_VOLTAR);
                    if (nome == null)
                    {
                        current = Telas.Menu;
                        return true;
                    }
                    else
                    {
                        Console.Clear();
                        editar = dados.PesquisarJogoPorNome(nome);
                        Console.WriteLine(editar != null ? editar.ToString() : PESQUISA_EDITAR_FALHA);
                        Console.WriteLine();
                        break;
                    }
                }
                else
                {
                    Console.Clear();
                    editar = dados.PesquisarJogoPorNome(nome);
                    Console.WriteLine(editar != null ? editar.ToString() : PESQUISA_EDITAR_FALHA);
                    Console.WriteLine();
                    break;
                }

            }

            nome = teclado.LerString(INFORMAR_NOME);
            while (nome == null)
            {
                nome = teclado.LerString(DIGITAR_ENTER_PARA_VOLTAR);
                if (nome == null)
                {
                    current = Telas.Menu;
                    return true;
                }
            }
            Console.WriteLine();

            double? preco = teclado.LerDouble(INFORMAR_PRECO);
            while (preco == null)
            {
                preco = teclado.LerDouble(DIGITAR_ENTER_PARA_VOLTAR);
                if (preco == null)
                {
                    current = Telas.Menu;
                    return true;
                }
            }
            Console.WriteLine();

            Console.WriteLine(GetCategorias());
            Console.WriteLine();
            int? categoria = teclado.LerInt(INFORMAR_CATEGORIA);
            while (categoria == null || !Enum.IsDefined(typeof(Categoria), categoria))
            {
                categoria = teclado.LerInt(DIGITAR_ENTER_PARA_VOLTAR + " ou informe a categoria:");
                if (categoria == null)
                {
                    current = Telas.Menu;
                    return true;
                }
            }

            editar.Nome = nome;
            editar.Preco = (double)preco;
            editar.Categoria = ((Categoria)categoria).ToString().ToUpper();

            dados.EditarJogo(editar);
            return true;
        }

        private bool CadastrarJogo()
        {
            string nome = teclado.LerString(INFORMAR_NOME);
            while (nome == null)
            {
                nome = teclado.LerString(DIGITAR_ENTER_PARA_VOLTAR);
                if (nome == null)
                {
                    current = Telas.Menu;
                    return true;
                }
            }
            Console.WriteLine();

            double? preco = teclado.LerDouble(INFORMAR_PRECO);
            while (preco == null)
            {
                preco = teclado.LerDouble(DIGITAR_ENTER_PARA_VOLTAR);
                if (preco == null)
                {
                    current = Telas.Menu;
                    return true;
                }
            }
            Console.WriteLine();

            Console.WriteLine(GetCategorias());
            Console.WriteLine();
            int? categoria = teclado.LerInt(INFORMAR_CATEGORIA);
            while (categoria == null || !Enum.IsDefined(typeof(Categoria), categoria))
            {
                categoria = teclado.LerInt(DIGITAR_ENTER_PARA_VOLTAR + " ou informe a categoria:");
                if (categoria == null)
                {
                    current = Telas.Menu;
                    return true;
                }
            }

            dados.Cadastrar(new Jogo(nome, (double)preco, ((Categoria)categoria).ToString().ToUpper()));
            return true;
        }

        private bool Menu()
        {
            //nl = new line
            string nl = Environment.NewLine;
            string[] opcoes = {
                "1-Pesquisar jogo por nome",
                "2-Cadastrar jogo",
                "3-Editar jogo",
                "4-Gerar relatório TXT",
                "5-Sair"
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
                case EDITAR_JOGO:
                    current = Telas.EditarJogo;
                    break;
                case GERAR_RELATORIO:
                    current = Telas.ExportarTxt;
                    break;
                case SAIR_DO_SISTEMA:
                    current = Telas.Sair;
                    break;
            }
            return true;
        }

        private string GetCategorias()
        {
            string cats = "";
            foreach (string categoria in Enum.GetNames(typeof(Categoria)))
            {
                cats += String.Format("{1:D}-{0}{2}", categoria.ToUpper(),
                                             Enum.Parse(typeof(Categoria), categoria),
                                             Environment.NewLine);
            }
            return cats;
        }
    }
}
