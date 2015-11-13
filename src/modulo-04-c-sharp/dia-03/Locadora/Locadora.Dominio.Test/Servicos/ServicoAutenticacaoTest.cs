using Locadora.Dominio.Servicos;
using Locadora.Dominio.Test.Mocks;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Locadora.Dominio.Test
{
    [TestClass]
    public class ServicoAutenticacaoTeste
    {
        [TestMethod]
        public void UsuarioLogadoComSucesso()
        {
            ServicoAutenticacao servicoAutenticacao = CriarServicoAutenticacao();
            Usuario usuarioAutenticado = servicoAutenticacao.BuscarPorAutenticacao("tefferson.guterres@cwi.com.br", "123");

            Assert.IsNotNull(usuarioAutenticado);
        }

        [TestMethod]
        public void UsuarioNaoPodeLogar()
        {
            ServicoAutenticacao servicoAutenticacao = CriarServicoAutenticacao();
            Usuario usuarioNaoAutenticado = servicoAutenticacao.BuscarPorAutenticacao("tefferson.guterres", "1234");

            Assert.IsNull(usuarioNaoAutenticado);
        }

        private ServicoAutenticacao CriarServicoAutenticacao()
        {
            return new ServicoAutenticacao(new UsuarioRepositorioMock(), new ServicoCriptografiaMock());
        }
    }
}