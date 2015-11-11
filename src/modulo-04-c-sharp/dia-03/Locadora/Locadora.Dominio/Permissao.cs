using System.Collections.Generic;

namespace Locadora.Dominio
{
    public class Permissao : EntidadeBase
    {
        public string Nome{ get; set; }
        public ICollection<Usuario> Usuarios { get; set; }
        public Permissao() { }
    }
}
