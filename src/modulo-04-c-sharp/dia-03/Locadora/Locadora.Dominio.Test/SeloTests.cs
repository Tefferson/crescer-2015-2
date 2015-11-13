using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Locadora.Dominio.Test
{
    [TestClass]
    public class SeloTests
    {
        [TestMethod]
        public void SeloPossuiPrazoDeUmDiaENomePlatina()
        {
            Selo selo = new Selo()
            {
                Nome = "Platina",
                PrazoDevolucao = 1
            };

            Assert.AreEqual("Platina", selo.Nome);
            Assert.AreEqual(1, selo.PrazoDevolucao);
        }

        [TestMethod]
        public void SeloEhCriadoComIdZero()
        {
            Selo selo = new Selo();
            Assert.AreEqual(0, selo.Id);
        }

        [TestMethod]
        public void ToStringTest()
        {
            Selo selo = new Selo()
            {
                Nome = "Platina"
            };

            Assert.AreEqual("Platina", selo.ToString());
        }

        [TestMethod]
        public void EqualsTest()
        {
            Selo seloA = new Selo()
            {
                Nome = "Platina",
                Preco = 213.3456m,
                PrazoDevolucao = 365
            };
            Selo seloB = new Selo()
            {
                Nome = "Platina",
                Preco = 213.3456m,
                PrazoDevolucao = 365
            };

            Assert.AreEqual(seloA, seloB);
        }
    }
}