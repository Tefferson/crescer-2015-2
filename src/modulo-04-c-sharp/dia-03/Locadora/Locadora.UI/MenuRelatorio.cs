using Locadora.Dominio;
using System;
using System.IO;

namespace Locadora.UI
{
    class MenuRelatorio : TelaBase
    {
        public override string Titulo
        {
            get
            {
                return "Relatorio";
            }
        }

        protected override void Iniciar()
        {
            while (true)
            {
                string caminhoPastaRelatorio = LerDados("Informe a pasta onde gerar o relatório (digite 0 para sair)");

                if (caminhoPastaRelatorio != "0")
                {
                    try
                    {
                        GerarRelatorio(caminhoPastaRelatorio);
                        ImprimirMensagem("Relatorio criado com sucesso.");
                        break;
                    }
                    catch (Exception)
                    {
                        ImprimirErro("Ocorreu um erro ao gerar o arquivo. Por favor, escolha outro diretório.");
                    }
                }
                else
                {
                    break;
                }
            }
        }

        private void GerarRelatorio(string caminhoPastaRelatorio)
        {
            if (!Directory.Exists(caminhoPastaRelatorio))
            {
                Directory.CreateDirectory(caminhoPastaRelatorio);
            }

            new Relatorio().Gerar(caminhoPastaRelatorio, JogoRepositorio);
        }
        
    }
}
