﻿using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Xml.Linq;

namespace Locadora.Dominio
{
    public class BaseDeDados
    {
        public const string CABECALHO = "                             LOCADORA NUNES GAMES                               ";
        public const string TITULO = "                              Relatório de jogos                                ";
        public const string IGUAIS = "================================================================================";
        public const string TRACOS = "--------------------------------------------------------------------------------";
        public const string COLUNAS = "ID       Categoria        Nome                          Preço         Disponivel";

        string CaminhoArquivo { get; set; }

        public BaseDeDados(string caminhoArquivo)
        {
            CaminhoArquivo = caminhoArquivo;
        }

        public IList<Jogo> PesquisarJogoPorNome(string nome)
        {
            nome = nome.ToUpper();
            List<Jogo> listaDeJogos = new List<Jogo>();
            IEnumerable<XElement> xejogos = GetElements("jogos").Where(jogo => jogo.Element("nome").Value.ToUpper().Contains(nome));
            foreach (XElement xelem in xejogos)
            {
                listaDeJogos.Add(new Jogo(xelem));
            }
            return listaDeJogos;
        }

        public void Cadastrar(LocadoraElement elem)
        {
            string node = GetNodeName(elem);
            if (node.Length > 0)
            {
                AddXElement(node, elem);
            }
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
            XElement doc = XElement.Load(CaminhoArquivo);
            string strId = jogoEditado.Id.ToString();
            XElement xejogo = doc.Element("jogos")
                .Elements().FirstOrDefault(jogo => strId == jogo.Attribute("id").Value);
            xejogo.Element("nome").Value = jogoEditado.Nome;
            xejogo.Element("preco").Value = jogoEditado.Preco.ToString();
            xejogo.Element("categoria").Value = jogoEditado.Categoria;
            xejogo.Element("disponivel").Value = jogoEditado.Disponivel.ToString();
            doc.Save(CaminhoArquivo);
        }

        public void ExportarRelatorioEmTxt()
        {
            IEnumerable<XElement> jogos = GetElements("jogos");
            string novaLinha = Environment.NewLine;
            string estatisticas = "Quantidade total de jogos: {1}{0}Quantidade de jogos disponíveis: {2}{0}Valor médio por jogo: R$ {3}{0}Jogo mais caro: {4}{0}Jogo mais barato: {5}";
            string dataEHora = String.Format("{0:dd/MM/yyyy}                                                              {0:HH:mm:ss}", DateTime.Now);
            string relatorio = CABECALHO
                + novaLinha + dataEHora
                + novaLinha + TITULO + novaLinha
                + novaLinha + IGUAIS
                + novaLinha + COLUNAS
                + novaLinha + GetListaDeJogosComoTexto()
                + novaLinha + TRACOS
                + novaLinha + estatisticas
                + novaLinha + IGUAIS;
            Func<XElement, double> doubleLambda = jogo => Convert.ToDouble(jogo.Element("preco").Value.Replace(".", ","));
            string maiorPreco = jogos.Max(doubleLambda).ToString();
            string menorPreco = jogos.Min(doubleLambda).ToString();
            string maisCaro = jogos.First(jogo => jogo.Element("preco").Value == maiorPreco).Element("nome").Value;
            string maisBarato = jogos.First(jogo => jogo.Element("preco").Value == menorPreco).Element("nome").Value;
            relatorio = String.Format(
                relatorio, novaLinha, jogos.Count(),
                jogos.Count(jogo => jogo.Element("disponivel").Value.ToUpper() == "TRUE"),
                jogos.Average(doubleLambda).ToString("0.00"),
                maisCaro, maisBarato);
            string relPath = Environment.CurrentDirectory + @"..\..\..\..\arquivos\Relatorio_Game_Store.txt";
            File.WriteAllText(relPath, relatorio);
        }

        public void SetJogoDisponivel(int idJogo, bool disponivel)
        {
            string idStr = idJogo.ToString();
            XElement xejogo = GetElements("jogos").First(jogo => jogo.Attribute("id").Value == idStr);
            xejogo.Element("disponivel").Value = disponivel.ToString();
            EditarJogo(new Jogo(xejogo));
        }

        public bool JogoIsDisponivel(int idJogo)
        {
            string idStr = idJogo.ToString();
            return GetElements("jogos").Any(jogo => jogo.Attribute("id").Value == idStr
            && jogo.Element("disponivel").Value.ToUpper() == "TRUE");
        }

        private string GetListaDeJogosComoTexto()
        {
            string jogosComoTexto = "";
            foreach (XElement xelem in GetElements("jogos"))
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

        private int GetNextId(string node)
        {
            return GetElements(node)
                .Max(xejogo => Convert.ToInt32(xejogo.Attribute("id").Value)) + 1;
        }

        private void AddXElement(string node, LocadoraElement elem)
        {
            elem.Id = GetNextId(node);
            XElement xelem = elem.ToXElement();
            XElement doc = XElement.Load(CaminhoArquivo);
            doc.Element(node).Add(xelem);
            doc.Save(CaminhoArquivo);
        }

        private IEnumerable<XElement> GetElements(string node)
        {
            return XElement.Load(CaminhoArquivo).Element(node).Elements();
        }

        public bool ValidarIdCliente(int id)
        {
            return ValidarId("clientes", id);
        }

        public bool ValidarIdJogo(int id)
        {
            return ValidarId("jogos", id);
        }

        private bool ValidarId(string node, int id)
        {
            string idStr = id.ToString();
            return XElement.Load(CaminhoArquivo).Element(node)
                .Elements().Any(elem => elem.Attribute("id").Value == idStr);
        }

        private string GetNodeName(LocadoraElement elem)
        {
            if (elem is Jogo)
            {
                return "jogos";
            }
            else if (elem is Cliente)
            {
                return "clientes";
            }
            else if (elem is Locacao)
            {
                return "locacoes";
            }
            return "";
        }
    }
}
