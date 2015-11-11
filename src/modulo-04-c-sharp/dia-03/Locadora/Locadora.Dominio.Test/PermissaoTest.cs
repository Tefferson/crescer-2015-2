using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Locadora.Dominio.Test
{
    [TestClass]
    public class PermissaoTest
    {
        [TestMethod]
        public void PermissaoAdminEhCriada()
        {
            var permissao = new Permissao("Admin");
            Assert.AreEqual("Admin", permissao.Nome);
        }

        [TestMethod]
        public void PermissaoEhIgualAOutra()
        {
            var permissaoA = new Permissao("Admin");
            var permissaoB = new Permissao("Admin");

            Assert.AreEqual(permissaoA, permissaoB);
        }
    }
}