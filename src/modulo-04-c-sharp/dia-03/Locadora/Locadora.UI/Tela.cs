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
            while (UpdateTela()) ;
        }

        const string INFORMAR_PRECO = "Por favor, informe um preço:";
        const string INFORMAR_OPCAO = "Por favor, informe uma opção:";
        const string INFORMAR_CATEGORIA = "Por favor, informe uma categoria:";
        const string INFORMAR_ID_LISTA = "Por favor, informe um id da lista:";
        const string INFORMAR_OPCAO_VALIDA = "Por favor, informe uma opção válida:";
        const string DIGITAR_ENTER_PARA_VOLTAR = "Pressione ENTER novamente para voltar... ";
        const string PESQUISA_CONCLUIDA = "Pesquisa concluída. Por favor, pressione ENTER para ir ao menu...";
        const string PESQUISA_EDITAR_FALHA = "Desculpe, o jogo desejado não existe.";
        const string RELATORIO_GERADO = "Relatório gerado. Por favor, pressione ENTER para ir ao menu...";
        const int PESQUISAR_JOGO_POR_NOME = 1;
        const int CADASTRAR_CLIENTE = 2;
        const int CADASTRAR_JOGO = 3;
        const int EDITAR_JOGO = 4;
        const int GERAR_RELATORIO = 5;
        const int SAIR_DO_SISTEMA = 6;

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
            EscreverCabecalho();
            switch (current)
            {
                case Telas.Menu:
                    return Menu();
                case Telas.CadastroCliente:
                    return CadastrarCliente();
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

        private bool CadastrarCliente()
        {
            EscreverMensagens(false, INFORMAR_NOME);
            string nome = teclado.LerString();
            while (nome == null)
            {
                EscreverMensagens(false, INFORMAR_NOME);
                nome = teclado.LerString();
            }

            dados.Cadastrar(new Cliente(nome));
            return true;
        }

        private bool GerarRelatorio()
        {
            dados.ExportarRelatorioEmTxt();
            EscreverMensagens(true, RELATORIO_GERADO);
            teclado.LerLinha();
            current = Telas.Menu;
            return true;
        }

        private bool PesquisarJogoPorNome()
        {
            string nome;
            teclado.LerNome(INFORMAR_NOME, DIGITAR_ENTER_PARA_VOLTAR, out nome);

            EscreverCabecalho();
            if (!String.IsNullOrEmpty(nome))
            {
                PesquisarJogoPorNome(nome);
                EscreverMensagens(true, PESQUISA_CONCLUIDA);
                teclado.LerLinha();
            }
            current = Telas.Menu;
            return true;
        }

        private IList<Jogo> PesquisarJogoPorNome(string nome)
        {
            Console.BackgroundColor = ConsoleColor.White;
            Console.ForegroundColor = ConsoleColor.Black;
            EscreverMensagens(true, BaseDeDados.COLUNAS + Environment.NewLine);
            Console.BackgroundColor = ConsoleColor.Black;
            Console.ForegroundColor = ConsoleColor.White;
            string listaDeJogos = "";
            IList<Jogo> pesquisado = dados.PesquisarJogoPorNome(nome);
            foreach (Jogo jogo in pesquisado)
            {
                listaDeJogos += dados.ToStringFormatado(jogo) + Environment.NewLine;
            }
            EscreverMensagens(true, listaDeJogos);
            return pesquisado;
        }

        private bool Sair()
        {
            EscreverMensagens(true, "Tchau!!!");
            teclado.LerLinha();
            return false;
        }

        private bool EditarJogo()
        {
            string nome;
            double preco;
            Categoria categoria;
            Jogo editar = null;

            if (!teclado.LerNome(INFORMAR_NOME, DIGITAR_ENTER_PARA_VOLTAR, out nome))
            {
                current = Telas.Menu;
                return true;
            }
            EscreverCabecalho();
            if (!SelecionarJogoParaEditar(PesquisarJogoPorNome(nome), out editar))
            {
                current = Telas.Menu;
                return true;
            }

            if (editar == null)
            {
                EscreverMensagens(false, DIGITAR_ENTER_PARA_VOLTAR);
                teclado.LerLinha();
                current = Telas.Menu;
                return true;
            }

            if (!teclado.LerNome(INFORMAR_NOME, DIGITAR_ENTER_PARA_VOLTAR, out nome))
            {
                current = Telas.Menu;
                return true;
            }
            EscreverMensagens();

            if (!teclado.LerPreco(INFORMAR_PRECO, DIGITAR_ENTER_PARA_VOLTAR, out preco))
            {
                current = Telas.Menu;
                return true;
            }
            EscreverMensagens();

            if (!teclado.LerCategoria(GetCategorias() + INFORMAR_CATEGORIA, DIGITAR_ENTER_PARA_VOLTAR, out categoria))
            {
                current = Telas.Menu;
                return true;
            }
            EscreverMensagens();

            editar.Nome = nome;
            editar.Preco = preco;
            editar.Categoria = categoria.ToString().ToUpper();
            current = Telas.Menu;
            dados.EditarJogo(editar);
            return true;
        }

        private bool SelecionarJogoParaEditar(IList<Jogo> jogos, out Jogo jogo)
        {
            jogo = null;
            if (jogos.Count == 1)
            {
                jogo = jogos[0];
                return true;
            }
            else if (jogos.Count == 0)
            {
                return false;
            }
            EscreverMensagens(false, INFORMAR_ID_LISTA);
            int? selected = teclado.LerInt();
            if (selected == null)
            {
                return false;
            }

            jogo = jogos.FirstOrDefault(elem => elem.Id == selected);
            return true;
        }

        private bool CadastrarJogo()
        {
            string nome;
            double preco;
            Categoria categoria;
            if (!teclado.LerNome(INFORMAR_NOME, DIGITAR_ENTER_PARA_VOLTAR, out nome))
            {
                current = Telas.Menu;
                return true;
            }
            EscreverMensagens();

            if (!teclado.LerPreco(INFORMAR_PRECO, DIGITAR_ENTER_PARA_VOLTAR, out preco))
            {
                current = Telas.Menu;
                return true;
            }
            EscreverMensagens();

            if (!teclado.LerCategoria(GetCategorias() + INFORMAR_CATEGORIA, DIGITAR_ENTER_PARA_VOLTAR, out categoria))
            {
                current = Telas.Menu;
                return true;
            }

            dados.Cadastrar(new Jogo(nome, preco, categoria.ToString().ToUpper()));
            current = Telas.Menu;
            return true;
        }

        private bool Menu()
        {
            EscreverMensagens(true,
                "1-Pesquisar jogo por nome", "2-Cadastrar cliente",
                "3-Cadastrar jogo", "4-Editar jogo",
                "5-Gerar relatório TXT", "6-Sair", Environment.NewLine);
            EscreverMensagens(false, INFORMAR_OPCAO);
            int? op = teclado.LerInt();
            while (op == null)
            {
                EscreverMensagens(false, INFORMAR_OPCAO);
                op = teclado.LerInt();
            }

            switch (op)
            {
                case PESQUISAR_JOGO_POR_NOME:
                    current = Telas.PesquisarJogoNome;
                    break;
                case CADASTRAR_CLIENTE:
                    current = Telas.CadastroCliente;
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
                                             Environment.NewLine, Environment.NewLine);
            }
            return cats;
        }

        private void EscreverMensagens()
        {
            EscreverMensagens(true);
        }

        public void EscreverMensagens(bool useNewLine, params string[] mensagens)
        {
            string linhas = "";
            string delimitador = useNewLine ? Environment.NewLine : "";
            foreach (string mensagem in mensagens)
            {
                linhas += FormatTo80(mensagem) + delimitador;
            }
            Console.Write(linhas);
        }

        private void EscreverCabecalho()
        {
            Console.Clear();
            Console.ForegroundColor = ConsoleColor.Green;
            Console.WriteLine(BaseDeDados.IGUAIS);
            Console.WriteLine(FormatTo80(current.ToString()));
            Console.WriteLine(BaseDeDados.IGUAIS);
            Console.ForegroundColor = ConsoleColor.White;
        }

        private string FormatTo80(string line)
        {
            int len = 40 + (line.Length / 2);
            return String.Format("{0," + len + "}", line);
        }
    }
}
