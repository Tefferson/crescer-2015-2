using Locadora.Dominio;
using Locadora.Dominio.Repositorio;
using System.Linq;

namespace Locadora.Repositorio.EF
{
    public class UsuarioRepositorio : IUsuarioRepositorio
    {
        public Usuario BuscarPorEmail(string email)
        {
            using (var db = new BancoDeDados())
            {
                return db.Usuario.Include("Permissoes").FirstOrDefault(u => u.Email == email);
            }
        }
    }
}