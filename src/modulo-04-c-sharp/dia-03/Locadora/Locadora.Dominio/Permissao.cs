using System.Collections.Generic;

namespace Locadora.Dominio
{
    public class Permissao : EntidadeBase
    {
        public const string ADMINISTRADOR = "ADMINISTRADOR";
        public const string OPERADOR = "OPERADOR";
        public string Nome{ get; set; }
        public ICollection<Usuario> Usuarios { get; set; }
        public Permissao() { }

        public Permissao(string nome)
        {
            Nome = nome;
        }

        public override bool Equals(object obj)
        {
            if(obj != null && obj.GetType() == typeof(Permissao))
            {
                Permissao comp = (Permissao)obj;
                return Nome == comp.Nome
                    && Usuarios == comp.Usuarios;
            }
            return false;
        }

        public override int GetHashCode()
        {
            return base.GetHashCode();
        }
    }
}
