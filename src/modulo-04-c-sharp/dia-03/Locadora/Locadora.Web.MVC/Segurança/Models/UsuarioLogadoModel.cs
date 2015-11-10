using Locadora.Dominio;
using System.Linq;

namespace WebApplication1.Models
{
    public class UsuarioLogado
    {
        public string Usuario { get; private set; }

        public string[] Permissoes { get; private set; }

        public UsuarioLogado(Usuario usuario)
        {
            this.Usuario = usuario.Email;
            this.Permissoes = usuario.Permissoes.Select(p=>p.Nome).ToArray<string>();
        }
    }
}