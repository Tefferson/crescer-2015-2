using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication1
{
    public class Cargo
    {
        public string Titulo { get; private set; }
        public double Salario { get; private set; }

        public Cargo(string titulo, double salario)
        {
            this.Titulo = titulo;
            this.Salario = salario;
        }

        public override bool Equals(object obj)
        {
            Cargo cargo = (Cargo)obj;
            return Titulo == cargo.Titulo && Salario == cargo.Salario;
        }

        public override string ToString()
        {
            return Titulo + "-" + Salario;
        }
        public override int GetHashCode()
        {
            return base.GetHashCode();
        }
    }
}