using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp
{
    class Program
    {
        static void Main(string[] args)
        {
            var agenda = new Agenda();
            var loop = true;
            var estado = 0;
            var menu = "1-Adicionar\n2-Remover por nome\n3-Remover por número\n4-Listar\n5-Listar por nome\n0-Sair";
            var informarNome = "Por favor informe o nome:";
            var informarNumero = "Por favor informe o número:";
            var continuar = "Por favor, pressione enter para continuar...";
            var digitado = "";
            var nome = "";
            var numero = 0;
            while (loop)
            {
                Console.Clear();
                if (estado == 0)
                {
                    Console.WriteLine(menu);
                    digitado = Console.ReadLine();
                    var digitadoToInt = Convert.ToInt32(digitado);
                    if (digitadoToInt == 1)
                    {
                        estado = 1;
                    }
                    else if (digitadoToInt == 4)
                    {
                        estado = 3;
                    }
                    else if (digitadoToInt == 2)
                    {
                        estado = 4;
                    }
                    else if (digitadoToInt == 3)
                    {
                        estado = 5;
                    }
                    else if (digitadoToInt == 5)
                    {
                        estado = 6;
                    }
                    else if (digitadoToInt == 0)
                    {
                        estado = 7;
                    }
                }
                else if (estado == 1)
                {
                    Console.WriteLine(informarNome);
                    digitado = Console.ReadLine();
                    if (digitado != "")
                    {
                        nome = digitado;
                        estado = 2;
                    }
                    else
                    {
                        estado = 0;
                    }
                }
                else if (estado == 2)
                {
                    Console.WriteLine(informarNumero);
                    digitado = Console.ReadLine();
                    numero = Convert.ToInt32(digitado);
                    agenda.AdicionarContato(new Contato(nome, numero));
                    estado = 0;
                }
                else if (estado == 3)
                {
                    Console.WriteLine(agenda.ListarContatos());
                    Console.WriteLine(continuar);
                    Console.ReadKey();
                    estado = 0;
                }
                else if (estado == 4)
                {
                    Console.WriteLine(informarNome);
                    digitado = Console.ReadLine();
                    if (digitado != "")
                    {
                        agenda.RemoverContatosPorNome(digitado);
                    }
                    estado = 0;
                }
                else if (estado == 5)
                {
                    Console.WriteLine(informarNumero);
                    digitado = Console.ReadLine();
                    var digitadoToInt = Convert.ToInt32(digitado);
                    agenda.RemoverContatosPorNumero(digitadoToInt);
                    estado = 0;
                }
                else if (estado == 6)
                {
                    Console.WriteLine(agenda.ListarContatosOrdenadosPorNome());
                    Console.WriteLine(continuar);
                    Console.ReadKey();
                    estado = 0;
                }
                else if (estado == 7)
                {
                    loop = false;
                }

            }
        }
    }
}
