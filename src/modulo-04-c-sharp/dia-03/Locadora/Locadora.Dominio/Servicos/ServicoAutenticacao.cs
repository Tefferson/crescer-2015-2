using Locadora.Dominio.Repositorio;

namespace Locadora.Dominio.Servicos
{
    public class ServicoAutenticacao
    {
        private IUsuarioRepositorio repositorio;

        public ServicoAutenticacao(IUsuarioRepositorio repositorio)
        {
            this.repositorio = repositorio;
        }

        public Usuario BuscarPorAutenticacao(string email, string senha)
        {
            var usuario = repositorio.BuscarPorEmail(email);

            if(usuario != null)
            {
                if(usuario.Senha == senha)
                {
                    return usuario;
                }
            }

            return null;//TODO: validar senha
        }
    }
}
