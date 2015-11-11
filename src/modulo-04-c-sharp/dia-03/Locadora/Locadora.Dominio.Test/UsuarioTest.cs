using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Locadora.Dominio.Test
{
    [TestClass]
    public class UsuarioTeste
    {
        [TestMethod]
        public void UsuarioEhCriadoSemPermissoes()
        {
            var usuario = new Usuario();
            Assert.AreEqual(0, usuario.Permissoes.Count);
        }

        [TestMethod]
        public void UsuarioComUmaPermissaoAdmin()
        {
            var usuario = new Usuario();
            usuario.AdicionarPermissao(new Permissao("Admin"));

            Assert.IsTrue(usuario.TemPermissao("Admin"));
        }

        [TestMethod]
        public void UsuarioNaoTemPermissaoAdmin()
        {
            var usuario = new Usuario();
            usuario.AdicionarPermissao(new Permissao("User"));

            Assert.IsFalse(usuario.TemPermissao("Admin"));
        }

        [TestMethod]
        public void UsuarioNaoAdicionaPermissaoQueJaTenha()
        {
            var usuario = new Usuario();
            usuario.AdicionarPermissao(new Permissao("Admin"));
            usuario.AdicionarPermissao(new Permissao("Admin"));

            Assert.AreEqual(1, usuario.Permissoes.Count);
        }
    }
}