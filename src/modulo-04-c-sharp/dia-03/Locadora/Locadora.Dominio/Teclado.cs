using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Locadora.Dominio
{
    public class Teclado
    {
        public Teclado() { }

        public Int32? LerInt()
        {
            string digitado = Console.ReadLine();
            int num;
            if (digitado != "" && Int32.TryParse(digitado, out num))
            {
                return num;
            }
            return null;
        }

        public Int32? LerInt(int min, int max)
        {
            string digitado = Console.ReadLine();
            int num;
            if (digitado != "" && Int32.TryParse(digitado, out num))
            {
                if (num >= min && num <= max)
                {
                    return num;
                }
            }
            return null;
        }

        public Double? LerDouble()
        {
            string digitado = Console.ReadLine();
            double num;
            if (digitado != "" && Double.TryParse(digitado.Replace(".", ","), out num))
            {
                return num;
            }
            return null;
        }

        public string LerString()
        {
            string digitado = Console.ReadLine();
            return digitado != "" ? digitado : null;
        }

        public string LerLinha()
        {
            return Console.ReadLine();
        }
    }
}
