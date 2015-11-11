using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Locadora.Infraestrutura.ServicosTest
{
    [TestClass]
    public class ServicoCriptografiaTeste
    {
        [TestMethod]
        public void CriptografaSenhaComSucesso()
        {
            var servicoCriptografia = new Locadora.Infraestrutura.Servicos.ServicoCriptografia();
            string senhaPadrao = "123";

            string senhaCriptografada = servicoCriptografia.CriptografarSenha(senhaPadrao);
            Assert.AreEqual("501C3E9279882FFB776A1D1E506D0410", senhaCriptografada);
        }
    }
}