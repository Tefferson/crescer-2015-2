using System;
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
                var usuariosEPermissoes = db.Usuario
                    .Where(u => u.Email == email)
                    .SelectMany(
                    u => u.Permissoes.Select(p =>
                       new
                       {
                           Usuario = u,
                           Permissao = p
                       }
                        )
                    ).GroupBy(u => u.Usuario.Email)
                    .FirstOrDefault();

                if (usuariosEPermissoes != null)
                {
                    var permissoes = usuariosEPermissoes.Select(m => m.Permissao).ToArray();

                    var user = usuariosEPermissoes.FirstOrDefault().Usuario;
                    return new Usuario(user.NomeCompleto, user.Email, user.Senha)
                    {
                        Permissoes = permissoes
                    };
                }
                return null;
            }
        }
    }
}