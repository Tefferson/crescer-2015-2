using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Locadora.Infraestrutura.Servicos.Test
{
    [TestClass]
    public class ServicoCriptografiaTeste
    {
        [TestMethod]
        public void CriptografaSenhaComSucesso()
        {
            var servicoCriptografia = new ServicoCriptografia();
            string senhaPadrao = "123";

            string senhaCriptografada = servicoCriptografia.CriptografarSenha(senhaPadrao);
            Assert.AreEqual("501C3E9279882FFB776A1D1E506D0410", senhaCriptografada);
        }
    }
}