using Locadora.Dominio;
using System.Linq;

namespace Locadora.Web.MVC.Models
{
    public class UsuarioLogadoModel
    {
        public string Usuario { get; private set; }

        public string[] Permissoes { get; private set; }

        public UsuarioLogadoModel(Usuario usuario)
        {
            this.Usuario = usuario.Email;

            var permissoes = usuario.Permissoes;
            bool usuarioPossuiPermissoes = permissoes != null;

            if (usuarioPossuiPermissoes)
            {
                this.Permissoes = permissoes.Select(p => p.Nome).ToArray<string>();
            }
            else
            {
                Permissoes = new string[] { };
            }
        }
    }
}