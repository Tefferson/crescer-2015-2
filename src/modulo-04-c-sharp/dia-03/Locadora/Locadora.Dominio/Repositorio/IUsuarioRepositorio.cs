namespace Locadora.Dominio.Repositorio
{
    public interface IUsuarioRepositorio
    {
        Usuario BuscarPorEmail(string email);
    }
}
