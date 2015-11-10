using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Locadora.Dominio.Test
{
    [TestClass]
    public class ClienteTests
    {
        [TestMethod]
        public void ClienteTemId0ENomeNulo()
        {
            Cliente cliente = new Cliente();
            Assert.AreEqual(0, cliente.Id);
            Assert.AreEqual(null, cliente.Nome);
        }

        [TestMethod]
        public void ClienteTemId2ENome()
        {
            Cliente cliente = new Cliente(2);
            cliente.Nome = "Nome";

            Assert.AreEqual(2, cliente.Id);
            Assert.AreEqual("Nome", cliente.Nome);
        }

        [TestMethod]
        public void ClienteAIGualClienteB()
        {
            Cliente clienteA = new Cliente();
            Cliente clienteB = new Cliente();
            Assert.AreEqual(clienteA, clienteB);
        }
    }
}