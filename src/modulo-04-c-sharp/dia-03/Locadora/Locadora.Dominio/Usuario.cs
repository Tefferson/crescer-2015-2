using System.Collections.Generic;
using System.Linq;

namespace Locadora.Dominio
{
    public class Usuario : EntidadeBase
    {
        public string Email { get; set; }
        public string NomeCompleto { get; set; }
        public string Senha { get; set; }
        public ICollection<Permissao> Permissoes { get; set; }

        public Usuario(string email, string nomeCompleto, string senha)
        {
            Email = email;
            NomeCompleto = nomeCompleto;
            Senha = senha;
        }

        public Usuario() { }

        public bool TemPermissao(string nomePermissao)
        {
            return this.Permissoes != null
                   && this.Permissoes.Any(p => p.Nome.Equals(nomePermissao));
        }

        public void AdicionarPermissao(Permissao permissao)
        {
            if (this.Permissoes == null)
            {
                this.Permissoes = new List<Permissao>();
            }

            if (!this.Permissoes.Contains(permissao))
            {
                this.Permissoes.Add(permissao);
            }
        }

        public override bool Equals(object obj)
        {
            if (obj != null && obj.GetType() == typeof(Usuario))
            {
                Usuario comp = (Usuario)obj;
                return Email == comp.Email
                    && NomeCompleto == comp.NomeCompleto
                    && Senha == comp.Senha
                    && Permissoes == comp.Permissoes;
            }
            return false;
        }

        public override int GetHashCode()
        {
            return base.GetHashCode();
        }
    }
}
