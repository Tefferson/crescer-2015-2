using System;
using System.Collections.Generic;
using System.Linq;
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

        public IEnumerable<XElement> GetElements(string node)
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
