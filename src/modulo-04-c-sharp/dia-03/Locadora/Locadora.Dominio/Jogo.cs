using System;
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
            Nome = xejogo.Element("nome").Value.ToString().Replace("\n", " ");
            Preco = Convert.ToDouble(xejogo.Element("preco").Value.Replace(".", ","));
            Categoria = xejogo.Element("categoria").Value;
            Disponivel = Convert.ToBoolean(xejogo.Element("disponivel").Value);
        }

        public override string ToString()
        {
            return String.Format("{6,-12}{0}{5}{7,-12}R$ {1}{5}{8,-12}{2}{5}{9,-12}{3}{5}{10,-12}{4}",
                Nome, Preco.ToString("0.00"), Id, Categoria, Disponivel ? "SIM" : "NÃO",
                Environment.NewLine, "Nome:", "Preço:", "ID:", "Categoria:", "Disponível:");
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
