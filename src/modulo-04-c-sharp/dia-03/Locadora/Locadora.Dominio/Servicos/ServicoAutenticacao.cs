using Locadora.Dominio.Repositorio;

namespace Locadora.Dominio.Servicos
{
    public class ServicoAutenticacao
    {
        private IUsuarioRepositorio repositorio;
        private IServicoCriptografia servicoCriptografia;

        public ServicoAutenticacao(IUsuarioRepositorio repositorio, IServicoCriptografia servicoCriptografia)
        {
            this.repositorio = repositorio;
            this.servicoCriptografia = servicoCriptografia;
        }

        public Usuario BuscarPorAutenticacao(string email, string senha)
        {
            var usuario = repositorio.BuscarPorEmail(email);

            if (usuario != null)
            {
                string senhaCriptografada = servicoCriptografia.CriptografarSenha(senha);
                if (usuario.Senha == senhaCriptografada)
                {
                    return usuario;
                }
            }

            return null;
        }
    }
}
