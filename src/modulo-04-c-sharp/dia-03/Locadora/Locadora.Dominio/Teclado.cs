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

        public Int32? LerInt(params string[] mensagens)
        {
            writeMensagens(mensagens);
            string digitado = Console.ReadLine();
            int num;
            if (digitado != "" && Int32.TryParse(digitado, out num))
            {
                return num;
            }
            return null;
        }

        public Double? LerDouble(params string[] mensagens)
        {
            writeMensagens(mensagens);
            string digitado = Console.ReadLine();
            double num;
            if (digitado != "" && Double.TryParse(digitado, out num))
            {
                return num;
            }
            return null;
        }

        public string LerString(params string[] mensagens)
        {
            writeMensagens(mensagens);
            string digitado = Console.ReadLine();
            return digitado != "" ? digitado : null;
        }

        public string LerLinha(params string[] mensagens)
        {
            writeMensagens(mensagens);
            return Console.ReadLine();
        }

        private void writeMensagens(string[] mensagens)
        {
            foreach (string mensagem in mensagens)
            {
                Console.WriteLine(mensagem);
            }
        }
    }
}
