using System;
using System.Collections.Generic;
using System.Linq;

namespace ConsoleApplication1
{
    class Program
    {
        static void imprimirFuncionarios(IEnumerable<dynamic> lista)
        {
            lista.ToList().ForEach(f => Console.WriteLine(f));
        }

        static void Main(string[] args)
        {
            BaseDeDados dados = new BaseDeDados();

            //IList<Funcionario> lista = dados.AniversariantesDoMes();
            //imprimirFuncionarios(lista);
            dynamic funcionarioMaisComplexo = dados.FuncionarioMaisComplexo();
            Console.WriteLine(funcionarioMaisComplexo);

            Console.Read();
        }

    }
}
