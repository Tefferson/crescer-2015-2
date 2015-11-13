using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Locadora.Dominio.Test
{
    [TestClass]
    public class JogoTest
    {
        [TestMethod]
        public void JogoTemNomeTesteEEstaIndisponivel()
        {
            Jogo jogo = new Jogo() {
                Nome = "Teste",
                Disponivel = false
            };

            Assert.AreEqual("Teste", jogo.Nome);
            Assert.IsFalse(jogo.Disponivel);
        }

        [TestMethod]
        public void JogoADeveSerIgualJogoB()
        {
            Jogo jogoA = new Jogo(id: 1, clienteLocacao: null);
            Jogo jogoB = new Jogo(id: 1, clienteLocacao: null);

            Assert.AreEqual(jogoA, jogoB);
        }
    }
}
