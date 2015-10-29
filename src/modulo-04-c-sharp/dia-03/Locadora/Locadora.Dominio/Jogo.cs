using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;

namespace Locadora.Dominio
{
    public class Jogo : LocadoraElement
    {
        public string Nome { get; set; }
        public double Preco { get; set; }
        public string Categoria { get; set; }
        public bool Disponivel { get; set; }

        public Jogo(string nome, double preco, string categoria)
        {
            Nome = nome;
            Preco = preco;
            Categoria = categoria;
            Disponivel = true;
        }

        public Jogo(XElement xejogo)
        {
            Id = Convert.ToInt32(xejogo.Attribute("id").Value);
            Nome = xejogo.Element("nome").Value;
            Preco = Convert.ToDouble(xejogo.Element("preco").Value.Replace(".", ","));
            Categoria = xejogo.Element("categoria").Value;
            Disponivel = Convert.ToBoolean(xejogo.Element("disponivel").Value);
        }

        public override string ToString()
        {
            return String.Format("{0,-9}{1,-17}{2,-30}{3,-14}{4,10}",
                 Id, Categoria, Truncate(Nome, 30), "R$ " + Preco.ToString("0.00"), Disponivel ? "SIM" : "NÃO");
        }

        private string Truncate(string nome, int maxSize)
        {
            if (maxSize < 4)
            {
                return "";
            }
            if (Nome.Length < maxSize)
            {
                return nome;
            }

            return nome.Substring(0, maxSize - 3) + "...";
        }

        public override XElement ToXElement()
        {
            XElement xejogo =
                      new XElement("jogo",
                          new XAttribute("id", Id),
                          new XElement("nome", Nome),
                          new XElement("preco", Preco),
                          new XElement("categoria", Categoria),
                          new XElement("disponivel", Disponivel));
            return xejogo;
        }

        public override bool Equals(object obj)
        {
            Jogo comparando = (Jogo)obj;
            return Id == comparando.Id && Nome == comparando.Nome
                && Preco == comparando.Preco && Categoria == comparando.Categoria
                && Disponivel == comparando.Disponivel;
        }

        public override int GetHashCode()
        {
            return base.GetHashCode();
        }
    }
}
