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
    public class JogoTests
    {
        [TestMethod()]
        public void JogoTest()
        {
            Jogo jogo = new Jogo("Jogo", 43.5, "AVENTURA");
            Assert.AreEqual("Jogo", jogo.Nome);
            Assert.AreEqual(43.50, jogo.Preco);
            Assert.AreEqual("AVENTURA", jogo.Categoria);
        }

        [TestMethod()]
        public void ToXElementTest()
        {
            int id = 68;
            string nome = "jogo";
            double preco = 23;
            string categoria = "AVENTURA";
            bool disponivel = true;
            XElement esperado =
                      new XElement("jogo",
                          new XAttribute("id", id),
                          new XElement("nome", nome),
                          new XElement("preco", preco),
                          new XElement("categoria", categoria),
                          new XElement("disponivel", disponivel)
                          );
            XElement atual = new Jogo(esperado).ToXElement();
            Assert.AreEqual(esperado.Value, atual.Value);
        }
    }
}