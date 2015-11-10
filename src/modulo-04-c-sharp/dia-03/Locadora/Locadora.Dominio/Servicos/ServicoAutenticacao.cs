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
            return repositorio.BuscarPorEmail(email);//TODO: validar senha
        }
    }
}
