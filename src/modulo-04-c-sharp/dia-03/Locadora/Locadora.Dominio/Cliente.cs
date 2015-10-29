using System;
using System.Xml.Linq;

namespace Locadora.Dominio
{
    public class Cliente : LocadoraElement
    {
        public string Nome { get; set; }

        public Cliente(string nome)
        {
            Nome = nome;
        }

        public Cliente(XElement xecliente)
        {
            Id = Convert.ToInt32(xecliente.Attribute("id").Value);
            Nome = xecliente.Element("nome").Value;
        }

        public override string ToString()
        {
            return Id + "-" + Nome;
        }

        public override XElement ToXElement()
        {
            XElement xecliente =
                      new XElement("cliente",
                          new XAttribute("id", Id),
                          new XElement("nome", Nome));
            return xecliente;
        }

        public override bool Equals(object obj)
        {
            Cliente comparando = (Cliente)obj;
            return Id == comparando.Id && Nome == comparando.Nome;
        }

        public override int GetHashCode()
        {
            return base.GetHashCode();
        }
    }
}