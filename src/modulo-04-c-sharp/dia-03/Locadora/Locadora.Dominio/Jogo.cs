using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;

namespace Locadora.Dominio
{
    public class Jogo
    {
        public int Id { get; set; }
        public string Nome { get; set; }
        public double Preco { get; set; }
        public string Categoria { get; set; }

        public Jogo(string nome, double preco, string categoria)
        {
            Nome = nome;
            Preco = preco;
            Categoria = categoria;
        }

        public Jogo(XElement xejogo)
        {
            Id = Convert.ToInt32(xejogo.Attribute("id").Value);
            Nome = xejogo.Element("nome").Value;
            Preco = Convert.ToDouble(xejogo.Element("preco").Value.Replace(".", ","));
            Categoria = xejogo.Element("categoria").Value;
        }

        public override string ToString()
        {
            return Id + "-" + Nome + "-" + String.Format("{0:0.00}", Preco) + "-" + Categoria;
        }

        public XElement ToXElement()
        {
            XElement xejogo =
                      new XElement("jogo",
                          new XAttribute("id", Id),
                          new XElement("nome", Nome),
                          new XElement("preco", Preco),
                          new XElement("categoria", Categoria));
            return xejogo;
        }
    }
}
