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
        internal void start()
        {
            while (UpdateTela()) ;
        }

        const string CONTINUAR = "Por favor, pressione ENTER se quiser continuar...";
        const string INFORMAR_NOME = "Por favor, informe um nome:";
        const string INFORMAR_PRECO = "Por favor, informe um preço:";
        const string INFORMAR_OPCAO = "Por favor, informe uma opção:";
        const string INFORMAR_CATEGORIA = "Por favor, informe uma categoria:";
        const string INFORMAR_ID_CLIENTE = "Por favor, informe o cliente:";
        const string INFORMAR_ID_JOGO = "Por favor, informe o jogo:";
        const string INFORMAR_ID_LISTA = "Por favor, informe um id da lista:";
        const string INFORMAR_OPCAO_VALIDA = "Por favor, informe uma opção válida:";
        const string DIGITAR_ENTER_PARA_VOLTAR = "Pressione ENTER novamente para voltar... ";
        const string PESQUISA_CONCLUIDA = "Pesquisa concluída. Por favor, pressione ENTER para ir ao menu...";
        const string PESQUISA_EDITAR_FALHA = "Desculpe, o jogo desejado não existe.";
        const string RELATORIO_GERADO = "Relatório gerado. Por favor, pressione ENTER para ir ao menu...";
        const string JOGO_DEVOLVIDO = "Jogo devolvido, pressione ENTER para ir ao menu...";
        const string JOGO_ESTAVA_DISPONIVEL = "Jogo já estava disponível. Por favor, pressione ENTER para ir ao menu...";
        const string LOCAR_SUCESSO = "Jogo locado com sucesso!!!";
        const string LOCAR_FALHA = "Desculpe, não foi possível locar o jogo...";
        const string CLIENTE_CADASTRADO_FALHA = "Desculpe, não foi possível cadastrar o cliente...";
        const string CLIENTE_CADASTRADO = "Cliente cadastrado com sucesso!!!";
        const string EDITAR_JOGO_FALHA = "Desculpe, não foi possível editar o jogo...";
        const string JOGO_EDITADO = "Jogo editado com sucesso!!!";
        const string VOLTANDO_PARA_MENU = "Voltando para o menu, por favor pressione ENTER...";
        const string CADASTRO_JOGO_SUCESSO = "Jogo cadstrado com sucesso!!!";
        const string CADASTRO_JOGO_FALHA = "Desculpe, não foi possível cadastrar o jogo...";
        const string TCHAU = "Tchau!!!";
        const string ERRO_ES = "Desculpe, ocorreu um erro, o programa será fechado...";
        const int PESQUISAR_JOGO_POR_NOME = 1;
        const int LOCAR = 2;
        const int DEVOLVER = 3;
        const int CADASTRAR_CLIENTE = 4;
        const int CADASTRAR_JOGO = 5;
        const int EDITAR_JOGO = 6;
        const int GERAR_RELATORIO = 7;
        const int SAIR_DO_SISTEMA = 0;

        string caminho = Environment.CurrentDirectory + @"..\..\..\..\arquivos\game_store.xml";
        Telas current = Telas.Menu;
        Teclado teclado = new Teclado();
        BaseDeDados dados;
        Relatorio relatorio;
        private string[] OPCOES_DO_MENU = {
                "1-Pesquisar jogo por nome", "2-Alugar jogo", "3-Devolver jogo",
                "4-Cadastrar cliente", "5-Cadastrar jogo", "6-Editar jogo",
                "7-Gerar relatório TXT", "0-Sair", Environment.NewLine};

        public Tela()
        {
            dados = new BaseDeDados(caminho);
            relatorio = new Relatorio();
        }

        public bool UpdateTela()
        {
            EscreverCabecalho();
            try
            {
                switch (current)
                {
                    case Telas.Menu:
                        return Menu();
                    case Telas.LocacaoDeJogo:
                        return LocarJogo();
                    case Telas.DevolucaoDeJogo:
                        return DevolverJogo();
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
            catch (Exception)
            {
                return !VoltarParaMenuComMensagem(ERRO_ES);
            }
        }

        private bool DevolverJogo()
        {
            int idJogo;

            if (teclado.LerInt(INFORMAR_ID_JOGO, INFORMAR_ID_JOGO, out idJogo)
                && dados.ValidarIdJogo(idJogo))
            {
                if (!dados.JogoIsDisponivel(idJogo))
                {
                    dados.SetJogoDisponivel(idJogo, true);
                    return VoltarParaMenuComMensagem(JOGO_DEVOLVIDO);
                }
            }
            return VoltarParaMenuComMensagem(JOGO_ESTAVA_DISPONIVEL);
        }

        private bool LocarJogo()
        {
            int idJogo;
            int idCliente;
            if (teclado.LerInt(INFORMAR_ID_JOGO, INFORMAR_ID_JOGO, out idJogo)
                && dados.ValidarIdJogo(idJogo) && dados.JogoIsDisponivel(idJogo))
            {
                EscreverMensagens();
                if (teclado.LerInt(INFORMAR_ID_CLIENTE, INFORMAR_ID_CLIENTE, out idCliente)
                && dados.ValidarIdCliente(idCliente))
                {
                    dados.Cadastrar(new Locacao(idCliente, idJogo));
                    dados.SetJogoDisponivel(idJogo, false);
                    return VoltarParaMenuComMensagem(LOCAR_SUCESSO);
                }
            }
            return VoltarParaMenuComMensagem(LOCAR_FALHA);
        }

        private bool CadastrarCliente()
        {
            string nome;
            if (teclado.LerNome(INFORMAR_NOME, INFORMAR_NOME, out nome))
            {
                dados.Cadastrar(new Cliente(nome));
                return VoltarParaMenuComMensagem(CLIENTE_CADASTRADO);
            }
            return VoltarParaMenuComMensagem(CLIENTE_CADASTRADO_FALHA);
        }

        private bool GerarRelatorio()
        {
            relatorio.ExportarRelatorioEmTxt(dados);
            return VoltarParaMenuComMensagem(RELATORIO_GERADO);
        }

        private bool PesquisarJogoPorNome()
        {
            string nome;
            teclado.LerNome(INFORMAR_NOME, DIGITAR_ENTER_PARA_VOLTAR, out nome);

            EscreverCabecalho();
            if (!String.IsNullOrEmpty(nome))
            {
                PesquisarJogoPorNome(nome);
            }

            return VoltarParaMenuComMensagem(PESQUISA_CONCLUIDA);
        }

        private IList<Jogo> PesquisarJogoPorNome(string nome)
        {
            Console.BackgroundColor = ConsoleColor.White;
            Console.ForegroundColor = ConsoleColor.Black;
            EscreverMensagens(true, Relatorio.COLUNAS + Environment.NewLine);
            Console.BackgroundColor = ConsoleColor.Black;
            Console.ForegroundColor = ConsoleColor.White;
            string listaDeJogos = "";
            IList<Jogo> pesquisado = dados.PesquisarJogoPorNome(nome);
            foreach (Jogo jogo in pesquisado)
            {
                listaDeJogos += relatorio.ToStringFormatado(jogo) + Environment.NewLine;
            }
            EscreverMensagens(true, listaDeJogos);
            return pesquisado;
        }

        private bool Sair()
        {
            EscreverMensagens(true, TCHAU);
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
                return VoltarParaMenuComMensagem(VOLTANDO_PARA_MENU);
            }
            EscreverCabecalho();
            if (!SelecionarJogoParaEditar(PesquisarJogoPorNome(nome), out editar))
            {
                return VoltarParaMenuComMensagem(EDITAR_JOGO_FALHA);
            }

            if (editar == null)
            {
                return VoltarParaMenuComMensagem(DIGITAR_ENTER_PARA_VOLTAR);
            }

            if (teclado.LerNome(INFORMAR_NOME, DIGITAR_ENTER_PARA_VOLTAR, out nome))
            {
                EscreverMensagens();
                if (teclado.LerPreco(INFORMAR_PRECO, DIGITAR_ENTER_PARA_VOLTAR, out preco))
                {
                    EscreverMensagens();
                    if (teclado.LerCategoria(GetCategorias() + INFORMAR_CATEGORIA, DIGITAR_ENTER_PARA_VOLTAR, out categoria))
                    {
                        EscreverMensagens();
                        editar.Nome = nome;
                        editar.Preco = preco;
                        editar.Categoria = categoria.ToString().ToUpper();
                        dados.EditarJogo(editar);
                        return VoltarParaMenuComMensagem(JOGO_EDITADO);
                    }
                }
            }
            return VoltarParaMenuComMensagem(EDITAR_JOGO_FALHA);
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
            if (teclado.LerNome(INFORMAR_NOME, DIGITAR_ENTER_PARA_VOLTAR, out nome))
            {
                EscreverMensagens();
                if (teclado.LerPreco(INFORMAR_PRECO, DIGITAR_ENTER_PARA_VOLTAR, out preco))
                {
                    EscreverMensagens();
                    if (teclado.LerCategoria(GetCategorias() + INFORMAR_CATEGORIA, DIGITAR_ENTER_PARA_VOLTAR, out categoria))
                    {
                        EscreverMensagens();
                        dados.Cadastrar(new Jogo(nome, preco, categoria.ToString().ToUpper()));
                        return VoltarParaMenuComMensagem(CADASTRO_JOGO_SUCESSO);
                    }
                }
            }
            return VoltarParaMenuComMensagem(CADASTRO_JOGO_FALHA);
        }

        private bool Menu()
        {
            EscreverMensagens(true, OPCOES_DO_MENU);
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
                case LOCAR:
                    current = Telas.LocacaoDeJogo;
                    break;
                case DEVOLVER:
                    current = Telas.DevolucaoDeJogo;
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

        private string FormatTo80(string line)
        {
            int len = 40 + (line.Length / 2);
            return String.Format("{0," + len + "}", line);
        }

        private void EscreverCabecalho()
        {
            Console.Clear();
            Console.ForegroundColor = ConsoleColor.Green;
            Console.WriteLine(Relatorio.IGUAIS);
            Console.WriteLine(FormatTo80(current.ToString()));
            Console.WriteLine(Relatorio.IGUAIS);
            Console.ForegroundColor = ConsoleColor.White;
        }

        private bool VoltarParaMenuComMensagem(string mensagem)
        {
            if (!String.IsNullOrEmpty(mensagem))
            {
                Console.Write(FormatTo80(mensagem));
                Console.ReadKey();
            }
            current = Telas.Menu;
            return true;
        }
    }
}
