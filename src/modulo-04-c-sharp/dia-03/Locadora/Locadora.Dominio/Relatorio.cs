using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;

namespace Locadora.Dominio
{
    public class Relatorio
    {
        public const string CABECALHO = "                             LOCADORA NUNES GAMES                               ";
        public const string TITULO = "                              Relatório de jogos                                ";
        public const string IGUAIS = "================================================================================";
        public const string TRACOS = "--------------------------------------------------------------------------------";
        public const string COLUNAS = "ID       Categoria        Nome                          Preço         Disponivel";
        public const string MUST_FORMAT_WITH_DATETIME = "{0:dd/MM/yyyy}                                                              {0:HH:mm:ss}";
        string caminhoRelatorio = Environment.CurrentDirectory + @"..\..\..\..\arquivos\Relatorio_Game_Store.txt";

        public void ExportarRelatorioEmTxt(BaseDeDados dados)
        {
            IEnumerable<XElement> jogos = dados.GetElements("jogos"); ;
            string estatisticas = "Quantidade total de jogos: {1}{0}Quantidade de jogos disponíveis: {2}{0}Valor médio por jogo: R$ {3}{0}Jogo mais caro: {4}{0}Jogo mais barato: {5}";
            string dataEHora = String.Format(MUST_FORMAT_WITH_DATETIME, DateTime.Now);
            string relatorio = String.Format("{1}{0}{2}{0}{3}{0}{0}{4}{0}{5}{0}{6}{0}{7}{0}{8}{0}{9}",
                Environment.NewLine, CABECALHO, dataEHora, TITULO, IGUAIS, COLUNAS, GetListaDeJogosComoTexto(dados),
                 TRACOS, estatisticas, IGUAIS);
            Func<XElement, double> doubleLambda = jogo => Convert.ToDouble(jogo.Element("preco").Value.Replace(".", ","));
            string maiorPreco = jogos.Max(doubleLambda).ToString();
            string menorPreco = jogos.Min(doubleLambda).ToString();
            string maisCaro = jogos.First(jogo => jogo.Element("preco").Value == maiorPreco).Element("nome").Value;
            string maisBarato = jogos.First(jogo => jogo.Element("preco").Value == menorPreco).Element("nome").Value;
            relatorio = String.Format(relatorio, Environment.NewLine, jogos.Count(),
                jogos.Count(jogo => jogo.Element("disponivel").Value.ToUpper() == "TRUE"),
                jogos.Average(doubleLambda).ToString("0.00"), maisCaro, maisBarato);
            File.WriteAllText(caminhoRelatorio, relatorio);
        }

        private string GetListaDeJogosComoTexto(BaseDeDados dados)
        {
            string jogosComoTexto = "";
            foreach (XElement xelem in dados.GetElements("jogos"))
            {
                jogosComoTexto += ToStringFormatado(new Jogo(xelem)) + Environment.NewLine;
            }
            return jogosComoTexto;
        }

        public string ToStringFormatado(Jogo jogo)
        {
            return String.Format("{0,-9}{1,-17}{2,-30}{3,-14}{4,10}",
                 jogo.Id, jogo.Categoria, Truncate(jogo.Nome, 30), "R$ " + jogo.Preco.ToString("0.00"), jogo.Disponivel ? "SIM" : "NÃO");
        }

        private string Truncate(string nome, int maxSize)
        {
            if (maxSize < 4)
            {
                return "";
            }
            if (nome.Length < maxSize)
            {
                return nome;
            }

            return nome.Substring(0, maxSize - 3) + "...";
        }
    }
}
