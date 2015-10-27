using System;
using System.Text;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace ConsoleApp.Testes
{
    [TestClass]
    public class ContatoTestes
    {
        [TestMethod]
        public void ToStringToContatoRetornaNomeTracoNumero()
        {
            Contato contato = new Contato("Teste",999);
            var esperado = "Teste-999";
            Assert.AreEqual(esperado, contato.ToString());
        }
    }
}
