using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;

namespace Locadora.Dominio
{
    public abstract class LocadoraElement
    {
        public int Id { get; set; }
        public abstract XElement ToXElement();
    }
}
