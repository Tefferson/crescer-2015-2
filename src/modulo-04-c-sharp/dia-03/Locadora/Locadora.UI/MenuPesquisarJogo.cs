using Locadora.Dominio;
using Locadora.Dominio.Repositorio;
using Locadora.UI.CadastroDeJogo;
using System;
using System.Collections.Generic;

namespace Locadora.UI
{
    class MenuPesquisarJogo : TelaBase
    {
        IList<Jogo> jogosEncontrados = null;

        public override string Titulo
        {
            get
            {
                return "Pesquisar Jogo";
            }
        }

        protected override void Iniciar()
        {
            string nome = LerNomeParaPesquisa();
            
            jogosEncontrados = this.JogoRepositorio.BuscarPorNome(nome);

            Console.WriteLine("ID   JOGO");
            Console.WriteLine("---- -------------------------------");

            foreach (Jogo j in jogosEncontrados)
            {
                Console.WriteLine(String.Format("{0} - {1}", j.Id, j.Nome));
            }

            EscolherOpcaoParaPesquisa();
        }

        private void EscolherOpcaoParaPesquisa()
        {
            int opcaoEscolhida = -1;

            while (true)
            {
                Console.WriteLine();
                Console.WriteLine("Escolha uma opção:");
                Console.WriteLine("1 - Editar");
                Console.WriteLine("2 - Excluir");
                Console.WriteLine("0 - Voltar");
                
                if (int.TryParse(Console.ReadLine(), out opcaoEscolhida))
                {
                    if(opcaoEscolhida == 0)
                    {
                        break;
                    }
                    else if(opcaoEscolhida == 1)
                    {
                        EditarJogo();
                        break;
                    }
                    else if(opcaoEscolhida == 2)
                    {
                        ExcluirJogo();
                        break;
                    }
                }

                ImprimirErro("Opção inválida...");
            }
        }

        private void EditarJogo()
        {
            while (true)
            {
                int idJogo = 0;

                if (int.TryParse(LerDados("Editar jogo de ID"), out idJogo))
                {
                    if (idJogo > 0)
                    {
                        Jogo jogoParaEditar = JogoRepositorio.BuscarPorId(idJogo);

                        if(jogoParaEditar != null)
                        {
                            new MenuEditarJogo(jogoParaEditar).Exibir();
                            return;
                        }
                    }
                }

                ImprimirErro("Jogo não encontrado...");
            }
        }

        private void ExcluirJogo()
        {
            while (true)
            {
                int idJogo = 0;

                if (int.TryParse(LerDados("Excluir jogo de ID"), out idJogo))
                {
                    if (idJogo > 0)
                    {
                        if (JogoRepositorio.BuscarPorId(idJogo) != null)
                        {
                            JogoRepositorio.Excluir(idJogo);
                            ImprimirMensagem("Jogo excluído com sucesso.");
                            return;
                        }
                    }
                }

                ImprimirErro("Jogo não encontrado..."); 
            }
        }

        private string LerNomeParaPesquisa()
        {
            while (true)
            {
                string nome = LerDados("Nome");

                if (String.IsNullOrWhiteSpace(nome))
                {
                    ImprimirErro("Digite um nome válido...");
                }
                else
                {
                    return nome;
                } 
            }
        }
    }
}
