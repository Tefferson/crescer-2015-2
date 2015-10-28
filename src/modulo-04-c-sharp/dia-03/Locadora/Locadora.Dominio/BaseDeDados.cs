using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Linq;

namespace Locadora.Dominio
{
    public class BaseDeDados
    {
        string CaminhoArquivo { get; set; }

        public BaseDeDados(string caminhoArquivo)
        {
            CaminhoArquivo = caminhoArquivo;
        }

        public Jogo PesquisarJogoPorNome(string nome)
        {
            XElement xelem = XElement.Load(CaminhoArquivo);
            XElement xejogo = xelem.Elements().FirstOrDefault(jogo => jogo.Element("nome").Value == nome);
            return xejogo == null ? null : new Jogo(xejogo);
        }

        public void CadastrarJogo(Jogo jogo)
        {
            int novoId = XElement
                .Load(CaminhoArquivo)
                .Elements()
                .Max(xejogo => Convert.ToInt32(xejogo.Attribute("id").Value)) + 1;
            jogo.Id = novoId;
            XDocument doc = XDocument.Load(CaminhoArquivo);
            doc.Element("jogos").Add(jogo.ToXElement());
            doc.Save(CaminhoArquivo);
        }

        public void EditarJogo(Jogo jogoEditado)
        {
            XDocument doc = XDocument.Load(CaminhoArquivo);
            string strId = jogoEditado.Id.ToString();
            XElement xejogo = doc.Elements("jogos").Elements().FirstOrDefault(jogo => strId==jogo.Attribute("id").Value);
            xejogo.Element("nome").Value = jogoEditado.Nome;
            xejogo.Element("preco").Value = jogoEditado.Preco.ToString();
            xejogo.Element("categoria").Value = jogoEditado.Categoria;
            doc.Save(CaminhoArquivo);
        }

        public void ExportarRelatorioEmTxt()
        {
            //string novaLinha = Environment.NewLine;
            //string cabecalho = "                             LOCADORA NUNES GAMES                               ";
            //string titulo = "                              Relatório de jogos                                "+novaLinha;
            //string linhaIguais = "================================================================================";
            //string linhaTracos = "--------------------------------------------------------------------------------";
            //string colunas = "ID       Categoria        Nome                          Preço         Disponivel";
            //string estatisticas = "Quantidade total de jogos: {1}{0}Quantidade de jogos disponíveis: {2}{0}Valor médio por jogo: R$ {3}{0}Jogo mais caro: {4}{0}Jogo mais barato: {5}";
            //string relatorio = cabecalho + novaLinha + titulo + linhaIguais + colunas + novaLinha + linhaTracos + novaLinha + estatisticas + novaLinha + linhaIguais;
            //Console.WriteLine(String.Format(relatorio,novaLinha,1,2,3,4,5));
        }
    }
}
