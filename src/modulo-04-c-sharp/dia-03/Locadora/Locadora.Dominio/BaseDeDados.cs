﻿using System;
using System.Collections.Generic;
using System.IO;
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
            XElement xejogo = GetElements("jogos").FirstOrDefault(jogo => jogo.Element("nome").Value == nome);
            return xejogo == null ? null : new Jogo(xejogo);
        }

        public void CadastrarJogo(Jogo jogo)
        {
            AddXElement("jogos", jogo);
        }

        public void CadastrarCliente(Cliente cliente)
        {
            AddXElement("clientes", cliente);
        }

        public int GetNextJogoId()
        {
            return GetNextId("jogos"); ;
        }

        public int GetNextClienteId()
        {
            return GetNextId("clientes"); ;
        }

        public void EditarJogo(Jogo jogoEditado)
        {
            XDocument doc = XDocument.Load(CaminhoArquivo);
            string strId = jogoEditado.Id.ToString();
            XElement xejogo = doc.Element("root").Elements("jogos")
                .Elements().FirstOrDefault(jogo => strId == jogo.Attribute("id").Value);
            xejogo.Element("nome").Value = jogoEditado.Nome;
            xejogo.Element("preco").Value = jogoEditado.Preco.ToString();
            xejogo.Element("categoria").Value = jogoEditado.Categoria;
            doc.Save(CaminhoArquivo);
        }

        public void ExportarRelatorioEmTxt()
        {
            IEnumerable<XElement> jogos = GetElements("jogos");
            string novaLinha = Environment.NewLine;
            string cabecalho = "                             LOCADORA NUNES GAMES                               ";
            string titulo = "                              Relatório de jogos                                " + novaLinha;
            string linhaIguais = "================================================================================";
            string linhaTracos = "--------------------------------------------------------------------------------";
            string colunas = "ID       Categoria        Nome                          Preço         Disponivel";
            string estatisticas = "Quantidade total de jogos: {1}{0}Quantidade de jogos disponíveis: {2}{0}Valor médio por jogo: R$ {3}{0}Jogo mais caro: {4}{0}Jogo mais barato: {5}";
            string dataEHora = String.Format("{0:dd/MM/yyyy}                                                              {0:HH:mm:ss}", DateTime.Now);
            string relatorio = cabecalho
                + novaLinha + dataEHora
                + novaLinha + titulo
                + novaLinha + linhaIguais
                + novaLinha + colunas
                + novaLinha + GetListaDeJogosComoTexto()
                + novaLinha + linhaTracos
                + novaLinha + estatisticas
                + novaLinha + linhaIguais;
            colunas = String.Format(colunas, novaLinha, jogos.Count());
            Func<XElement, double> doubleLambda = jogo => Convert.ToDouble(jogo.Element("preco").Value.Replace(".", ","));
            string maiorPreco = jogos.Max(doubleLambda).ToString();
            string menorPreco = jogos.Min(doubleLambda).ToString();
            string maisCaro = jogos.First(jogo => jogo.Element("preco").Value == maiorPreco).Element("nome").Value;
            string maisBarato = jogos.First(jogo => jogo.Element("preco").Value == menorPreco).Element("nome").Value;
            relatorio = String.Format(
                relatorio, novaLinha, jogos.Count(),
                jogos.Count(jogo => jogo.Element("disponivel").Value == "true"),
                jogos.Average(doubleLambda).ToString("0.00"),
                maisCaro, maisBarato);
            string relPath = Environment.CurrentDirectory + @"..\..\..\..\arquivos\Relatorio_Game_Store.txt";
            File.WriteAllText(relPath, relatorio);
        }

        private string GetListaDeJogosComoTexto()
        {
            string jogosComoTexto = "";
            foreach (XElement xelem in GetElements("jogos"))
            {
                jogosComoTexto += new Jogo(xelem) + Environment.NewLine;
            }
            return jogosComoTexto;
        }

        private int GetNextId(string node)
        {
            return GetElements(node)
                .Max(xejogo => Convert.ToInt32(xejogo.Attribute("id").Value)) + 1;
        }

        private void AddXElement(string node, LocadoraElement elem)
        {
            elem.Id = GetNextId(node);
            XElement xelem = elem.ToXElement();
            XDocument doc = XDocument.Load(CaminhoArquivo);
            doc.Element("root").Element(node).Add(xelem);
            doc.Save(CaminhoArquivo);
        }

        private IEnumerable<XElement> GetElements(string node)
        {
            return XElement.Load(CaminhoArquivo).Element(node).Elements();
        }
    }
}
