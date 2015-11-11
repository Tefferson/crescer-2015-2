using Locadora.Dominio.Repositorio;
using System.Collections.Generic;
using System.Linq;

namespace Locadora.Dominio.Test.Mocks
{
    class UsuarioRepositorioMock : IUsuarioRepositorio
    {
        public Usuario BuscarPorEmail(string email)
        {
            return Db().FirstOrDefault(u => u.Email.Equals(email));
        }

        private IList<Usuario> Db()
        {
            var usuarios = new List<Usuario>();

            var usuario1 = new Usuario()
            {
                Email = "tefferson.guterres@cwi.com.br",
                Senha = "123" + ServicoCriptografiaMock.SALT
            };

            usuario1.Permissoes.Add(new Permissao("ADMIN"));

            usuarios.Add(usuario1);
            return usuarios;
        }
    }
}