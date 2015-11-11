using System.Collections.Generic;

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

        private Usuario() { }
    }
}
