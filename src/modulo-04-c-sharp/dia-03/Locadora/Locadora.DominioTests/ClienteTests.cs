using Microsoft.VisualStudio.TestTools.UnitTesting;
using Locadora.Dominio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;

namespace Locadora.Dominio.Tests
{
    [TestClass()]
    public class ClienteTests
    {
        [TestMethod()]
        public void ClienteTest()
        {
            Cliente cliente = new Cliente("cliteste");
            Assert.AreEqual("cliteste", cliente.Nome);
            Assert.AreEqual(0, cliente.Id);
        }

        [TestMethod()]
        public void ToStringTest()
        {
            Cliente cliente = new Cliente("cliteste");
            cliente.Id = 116;
            Assert.AreEqual("116-cliteste", cliente.ToString());
        }

        [TestMethod()]
        public void ToXElementTest()
        {
            int id = 68;
            string nome = "cliteste";
            XElement esperado =
                      new XElement("cliente",
                          new XAttribute("id", id),
                          new XElement("nome", nome));
            XElement atual = new Cliente(esperado).ToXElement();
            Assert.AreEqual(esperado.Value, atual.Value);
        }
    }
}