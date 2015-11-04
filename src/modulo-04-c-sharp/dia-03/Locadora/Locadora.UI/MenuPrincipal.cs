using Locadora.UI.CadastroDeJogo;
using System;
using System.Collections.Generic;

namespace Locadora.UI
{
    class MenuPrincipal : TelaBase
    {
        private const int MENU_CADASTRAR_NOVO_JOGO = 1;
        private const int MENU_PESQUISAR_JOGO = 2;
        private const int MENU_IMPRIMIR_RELATORIO = 3;

        private Dictionary<int, string> menus;

        public MenuPrincipal()
        {
            menus = new Dictionary<int, string>();
            menus.Add(MENU_CADASTRAR_NOVO_JOGO, "Cadastrar Novo Jogo");
            menus.Add(MENU_PESQUISAR_JOGO, "Pesquisar Jogo");
            menus.Add(MENU_IMPRIMIR_RELATORIO, "Imprimir Relatório");
        }

        public override string Titulo
        {
            get
            {
                return "Menu Principal";
            }
        }

        protected override void Iniciar()
        {
            try
            {
                MostrarMenu();
                int menuEscolhido = EscolherMenu();
                AbrirMenu(menuEscolhido);
            }
            catch
            {
                ImprimirErro("Ocorreu um erro não tratado. Por favor, contate o administrador.");
            }
        }

        private int EscolherMenu()
        {
            int menuEscolhido = 0;

            while (true)
            {
                string escolhaUsuario = LerDados("Escolha");
                if (int.TryParse(escolhaUsuario, out menuEscolhido))
                {
                    if (menus.ContainsKey(menuEscolhido))
                    {
                        return menuEscolhido;
                    }
                }

                ImprimirErro("Escolha inválida...");
                Exibir();
            }
        }

        private void MostrarMenu()
        {
            foreach (var m in menus)
            {
                Console.WriteLine(String.Format("{0} - {1}", m.Key, m.Value));
            }
        }

        private void AbrirMenu(int menuEscolhido)
        {
            switch (menuEscolhido)
            {
                case MENU_CADASTRAR_NOVO_JOGO:
                    new MenuCadastrarNovoJogo().Exibir();
                    break;
                case MENU_PESQUISAR_JOGO:
                    new MenuPesquisarJogo().Exibir();
                    break;
                case MENU_IMPRIMIR_RELATORIO:
                    new MenuRelatorio().Exibir();
                    break;
                default:
                    break;
            }
        }

    }
}
