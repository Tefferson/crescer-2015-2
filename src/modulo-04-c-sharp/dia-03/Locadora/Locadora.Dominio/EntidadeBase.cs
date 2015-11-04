using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Locadora.Dominio
{
    public abstract class EntidadeBase
    {
        public int Id { get; protected set; }
    }
}
