using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Locadora.Dominio.Test
{
    [TestClass]
    public class UsuarioTeste
    {
        [TestMethod]
        public void UsuarioEhCriadoSemPermissoes()
        {
            Usuario usuario = new Usuario();
            Assert.IsNull(usuario.Permissoes);
        }

        [TestMethod]
        public void UsuarioComUmaPermissaoAdmin()
        {
            Usuario usuario = new Usuario();
            usuario.AdicionarPermissao(new Permissao("Admin"));

            Assert.IsTrue(usuario.TemPermissao("Admin"));
        }

        [TestMethod]
        public void UsuarioNaoTemPermissaoAdmin()
        {
            Usuario usuario = new Usuario();
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