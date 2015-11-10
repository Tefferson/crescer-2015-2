using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Locadora.Dominio
{
    public class Cliente : EntidadeBase
    {
        public string Nome { get; set; }

        public Cliente()
        {

        }

        public Cliente(int id)
        {
            this.Id = id;
        }

        public override string ToString()
        {
            var builder = new StringBuilder();
            builder.AppendLine("Id: " + this.Id);
            builder.AppendLine("Nome: " + this.Nome);

            return builder.ToString();
        }

        public override bool Equals(object obj)
        {
            if (obj != null && obj.GetType() == typeof(Cliente))
            {
                Cliente clienteComp = (Cliente)obj;

                return this.Id == clienteComp.Id
                    && this.Nome == clienteComp.Nome;
            }

            return false;
        }

        public override int GetHashCode()
        {
            return base.GetHashCode();
        }
    }
}
