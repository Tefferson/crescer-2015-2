using Locadora.Dominio.Servicos;
using Locadora.Dominio.Test.Mocks;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Locadora.Dominio.Test
{
    [TestClass]
    public class ServicoLocacaoTests
    {
        [TestMethod]
        public void LocarJogoParaClienteTest()
        {
            var servicoLocacao = CriarServicoLocacao();

            Assert.IsTrue(servicoLocacao.LocarJogoParaCliente(2, "Cliente fiel"));
        }

        [TestMethod]
        public void CalcularValorFinalTest()
        {
            var locacao = new LocacaoRepositorioMock().BuscarPorId(1);

            decimal valorFinal = CriarServicoLocacao().CalcularValorFinal(locacao);

            Assert.AreEqual(25m, valorFinal);
        }

        [TestMethod]
        public void DevolverJogoTest()
        {
            var servicoLocacao = CriarServicoLocacao();
            Assert.IsFalse(servicoLocacao.DevolverJogo(1));
        }

        private ServicoLocacao CriarServicoLocacao()
        {
            return new ServicoLocacao(new JogoRepositorioMock(), new ClienteRepositorioMock(), new LocacaoRepositorioMock());
        }
    }
}