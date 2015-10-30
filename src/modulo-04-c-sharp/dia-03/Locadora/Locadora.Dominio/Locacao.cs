using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;

namespace Locadora.Dominio
{
    public class Locacao : LocadoraElement
    {
        public int IdCliente { get; set; }
        public int IdJogo { get; set; }

        public Locacao(int idCliente, int idJogo)
        {
            IdCliente = idCliente;
            IdJogo = idJogo;
        }

        public Locacao(XElement xelocacao)
        {
            Id = Convert.ToInt32(xelocacao.Attribute("id").Value);
            IdCliente = Convert.ToInt32(xelocacao.Element("id-cliente").Value);
            IdJogo = Convert.ToInt32(xelocacao.Element("id-jogo").Value);
        }

        public override string ToString()
        {
            return Id + "-" + IdCliente + "-" + IdJogo;
        }

        public override XElement ToXElement()
        {
            XElement xelocacao =
                      new XElement("locacao",
                          new XAttribute("id", Id),
                          new XElement("id-cliente", IdCliente),
                          new XElement("id-jogo", IdJogo));
            return xelocacao;
        }

        public override bool Equals(object obj)
        {
            Locacao comparando = (Locacao)obj;
            return Id == comparando.Id && IdCliente == comparando.IdCliente && IdJogo == comparando.IdJogo;
        }

        public override int GetHashCode()
        {
            return base.GetHashCode();
        }
    }
}
