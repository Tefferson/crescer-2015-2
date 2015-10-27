using System;

namespace ConsoleApp
{
    class Program
    {
        const int ADICIONAR = 1;
        const int REMOVER_POR_NOME = 2;
        const int REMOVER_POR_NUMERO = 3;
        const int LISTAR = 4;
        const int LISTAR_POR_NOME = 5; 
        const int SAIR = 0;
        static string LerLinha(string mensagem)
        {
            Console.WriteLine(mensagem);
            return Console.ReadLine();
        }

        static int LerNumero(string mensagem)
        {
            int numeroLido;
            int.TryParse(LerLinha(mensagem), out numeroLido);
            return numeroLido;
        }

        static void Main(string[] args)
        {
            var agenda = new Agenda();
            var loop = true;
            var menu = "1-Adicionar\n2-Remover por nome\n3-Remover por número\n4-Listar\n5-Listar por nome\n0-Sair\n";
            var informarNome = "Por favor informe o nome:";
            var informarNumero = "Por favor informe o número:";
            var continuar = "Por favor, pressione enter para continuar...";
            var cabecalho = "=====AGENDA=====";
            var mensagemExplicativa = "\nPor favor, informe uma opção:";
            while (loop)
            {
                Console.Clear();
                Console.WriteLine(cabecalho);
                {
                    switch (LerNumero(menu+mensagemExplicativa))
                    {
                        case ADICIONAR:
                            agenda.AdicionarContato(new Contato(LerLinha(informarNome), LerNumero(informarNumero)));
                            break;
                        case LISTAR:
                            LerLinha(agenda.ListarContatos() + '\n' + continuar);
                            break;
                        case REMOVER_POR_NOME:
                            agenda.RemoverContatosPorNome(LerLinha(informarNome));
                            break;
                        case REMOVER_POR_NUMERO:
                            agenda.RemoverContatosPorNumero(LerNumero(informarNumero));
                            break;
                        case LISTAR_POR_NOME:
                            LerLinha(agenda.ListarContatosOrdenadosPorNome() + '\n' + continuar);
                            break;
                        case SAIR:
                            loop = false;
                            break;
                    }
                }
            }
        }
    }
}
