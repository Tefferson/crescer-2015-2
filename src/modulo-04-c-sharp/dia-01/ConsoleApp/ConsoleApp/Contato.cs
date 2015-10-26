using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp
{
    public class Contato
    {
        private string nome;
        private int numero;

        public string Nome { get { return nome; } set { nome = value; } }
        public int Numero { get { return numero; } set { numero = value; } }

        public Contato(string nome, int numero)
        {
            this.nome = nome;
            this.numero = numero;
        }
    }
}
