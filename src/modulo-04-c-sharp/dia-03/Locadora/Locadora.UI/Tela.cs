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
        const string INFORMAR_NUMERO_LISTA = "Por favor, informe um número da lista:";
        const string INFORMAR_OPCAO_VALIDA = "Por favor, informe uma opção válida:";
        const string DIGITAR_ENTER_PARA_VOLTAR = "Pressione ENTER novamente para voltar";
        const string PESQUISA_CONCLUIDA = "Pesquisa concluída. Por favor, pressione ENTER para ir ao menu...";
        const string PESQUISA_FALHA = "Desculpe, sua consulta não teve sucesso.";
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
            Console.Clear();
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
            EscreverMensagens(INFORMAR_NOME);
            string nome = teclado.LerString();
            while (nome == null)
            {
                EscreverMensagens(INFORMAR_NOME);
                nome = teclado.LerString();
            }

            dados.Cadastrar(new Cliente(nome));
            return true;
        }

        private bool GerarRelatorio()
        {
            dados.ExportarRelatorioEmTxt();
            EscreverMensagens(RELATORIO_GERADO);
            teclado.LerLinha();
            current = Telas.Menu;
            return true;
        }

        private bool PesquisarJogoPorNome()
        {
            string nome;

            LerNome(INFORMAR_NOME, DIGITAR_ENTER_PARA_VOLTAR, out nome);

            Console.Clear();
            PesquisarJogoPorNome(nome);
            teclado.LerLinha();
            current = Telas.Menu;
            return true;
        }

        private IList<Jogo> PesquisarJogoPorNome(string nome)
        {
            string listaDeJogos = "";
            IList<Jogo> pesquisado = dados.PesquisarJogoPorNome(nome);
            foreach (Jogo jogo in pesquisado)
            {
                listaDeJogos += jogo + Environment.NewLine + Environment.NewLine;
            }
            EscreverMensagens(pesquisado.Count > 0 ? listaDeJogos : PESQUISA_FALHA, PESQUISA_CONCLUIDA);
            return pesquisado;
        }

        private bool Sair()
        {
            EscreverMensagens("Tchau!!!");
            teclado.LerLinha();
            return false;
        }

        private bool EditarJogo()
        {
            string nome;
            double preco;
            Categoria categoria;
            Jogo editar = null;

            if (!LerNome(INFORMAR_NOME, DIGITAR_ENTER_PARA_VOLTAR, out nome))
            {
                current = Telas.Menu;
                return true;
            }
            Console.Clear();
            if (!SelecionarJogoParaEditar(PesquisarJogoPorNome(nome), out editar))
            {
                current = Telas.Menu;
                return true;
            }

            EscreverMensagens(editar != null ? editar.ToString() : PESQUISA_EDITAR_FALHA, "");
            if (editar == null)
            {
                EscreverMensagens(DIGITAR_ENTER_PARA_VOLTAR);
                teclado.LerLinha();
                current = Telas.Menu;
                return true;
            }

            if (!LerNome(INFORMAR_NOME, DIGITAR_ENTER_PARA_VOLTAR, out nome))
            {
                current = Telas.Menu;
                return true;
            }
            EscreverMensagens();

            if (!LerPreco(INFORMAR_PRECO, DIGITAR_ENTER_PARA_VOLTAR, out preco))
            {
                current = Telas.Menu;
                return true;
            }
            EscreverMensagens();

            if (!LerCategoria(GetCategorias() + INFORMAR_CATEGORIA, DIGITAR_ENTER_PARA_VOLTAR, out categoria))
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
            EscreverMensagens(INFORMAR_NUMERO_LISTA);
            int? selected = teclado.LerInt(1, jogos.Count);
            if (selected == null)
            {
                return false;
            }
            jogo = jogos[(int)selected - 1];
            return true;
        }

        private bool CadastrarJogo()
        {
            string nome;
            double preco;
            Categoria categoria;
            if (!LerNome(INFORMAR_NOME, DIGITAR_ENTER_PARA_VOLTAR, out nome))
            {
                current = Telas.Menu;
                return true;
            }
            EscreverMensagens();

            if (!LerPreco(INFORMAR_PRECO, DIGITAR_ENTER_PARA_VOLTAR, out preco))
            {
                current = Telas.Menu;
                return true;
            }
            EscreverMensagens();

            if (!LerCategoria(GetCategorias() + INFORMAR_CATEGORIA, DIGITAR_ENTER_PARA_VOLTAR, out categoria))
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
            //nl = new line
            string nl = Environment.NewLine;
            string[] opcoes = {
                "1-Pesquisar jogo por nome",
                "2-Cadastrar cliente",
                "3-Cadastrar jogo",
                "4-Editar jogo",
                "5-Gerar relatório TXT",
                "6-Sair"
            };
            string ops = (String.Join(nl, opcoes) + nl);
            EscreverMensagens(ops, INFORMAR_OPCAO);
            int? op = teclado.LerInt();
            while (op == null)
            {
                EscreverMensagens(INFORMAR_OPCAO);
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

        private void EscreverMensagens(params string[] mensagens)
        {
            if (mensagens.Length == 0)
            {
                Console.WriteLine();
            }
            foreach (string mensagem in mensagens)
            {
                Console.WriteLine(mensagem);
            }
        }

        private bool LerNome(string mensagemSolicitacao, string mensagemAviso, out string nome)
        {
            nome = null;
            while (nome == null)
            {
                EscreverMensagens(mensagemSolicitacao);
                nome = teclado.LerString();
                if (nome == null)
                {
                    EscreverMensagens(mensagemAviso);
                    nome = teclado.LerString();
                    if (nome == null)
                    {
                        nome = "";
                        return false;
                    }
                }
            }
            return true;
        }

        private bool LerPreco(string mensagemSolicitacao, string mensagemAviso, out double preco)
        {
            double? precoLido = null;
            while (precoLido == null)
            {
                EscreverMensagens(mensagemSolicitacao);
                precoLido = teclado.LerDouble();
                if (precoLido == null)
                {
                    EscreverMensagens(mensagemAviso);
                    precoLido = teclado.LerDouble();
                    if (precoLido == null)
                    {
                        preco = 0; ;
                        return false;
                    }
                }
            }
            preco = (double)precoLido;
            return true;
        }

        private bool LerCategoria(string mensagemSolicitacao, string mensagemAviso, out Categoria categoria)
        {
            int? intLido = null;
            while (intLido == null || !Enum.IsDefined(typeof(Categoria), intLido))
            {
                EscreverMensagens(mensagemSolicitacao);
                intLido = teclado.LerInt();
                if (intLido == null || !Enum.IsDefined(typeof(Categoria), intLido))
                {
                    EscreverMensagens(mensagemAviso);
                    intLido = teclado.LerInt();
                    if (intLido == null)
                    {
                        categoria = 0; ;
                        return false;
                    }
                }
            }
            categoria = (Categoria)intLido;
            return true;
        }
    }
}
