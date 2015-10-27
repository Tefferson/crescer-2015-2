using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication1
{
    class Program
    {
        static void Main(string[] args)
        {
            BaseDeDados dados = new BaseDeDados();

            dados.BuscarPorCargo(new Cargo("Desenvolvedor", 190)).ToList().ForEach(f => Console.WriteLine(f));

            Console.Read();
        }


    }
}
