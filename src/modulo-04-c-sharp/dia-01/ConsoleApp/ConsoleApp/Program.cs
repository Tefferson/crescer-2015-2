using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp
{
    class Program
    {
        enum Estados { MOSTRANDO_MENU, CRIANDO_CONTATO, LISTANDO_CONTATOS, REMOVENDO_CONTATOS_POR_NOME, REMOVENDO_CONTATOS_POR_NUMERO, LISTANDO_CONTATOS_POR_NOME, SAINDO }
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
            const int ADICIONAR = 1;
            const int REMOVER_POR_NOME = 2;
            const int REMOVER_POR_NUMERO = 3;
            const int LISTAR = 4;
            const int LISTAR_POR_NOME = 5;
            const int SAIR = 0;
            var agenda = new Agenda();
            var loop = true;
            var estado = Estados.MOSTRANDO_MENU;
            var menu = "1-Adicionar\n2-Remover por nome\n3-Remover por número\n4-Listar\n5-Listar por nome\n0-Sair";
            var informarNome = "Por favor informe o nome:";
            var informarNumero = "Por favor informe o número:";
            var continuar = "Por favor, pressione enter para continuar...";
            var cabecalho = "=====AGENDA=====";
            while (loop)
            {
                Console.Clear();
                Console.WriteLine(cabecalho);
                if (estado == Estados.MOSTRANDO_MENU)
                {
                    switch (LerNumero(menu))
                    {
                        case ADICIONAR:
                            estado = Estados.CRIANDO_CONTATO;
                            break;
                        case LISTAR:
                            estado = Estados.LISTANDO_CONTATOS;
                            break;
                        case REMOVER_POR_NOME:
                            estado = Estados.REMOVENDO_CONTATOS_POR_NOME;
                            break;
                        case REMOVER_POR_NUMERO: estado = Estados.REMOVENDO_CONTATOS_POR_NUMERO;
                            break;
                        case LISTAR_POR_NOME: estado = Estados.LISTANDO_CONTATOS_POR_NOME;
                            break;
                        case SAIR: estado = Estados.SAINDO;
                            break;
                    }
                }
                else if (estado == Estados.CRIANDO_CONTATO)
                {
                    agenda.AdicionarContato(new Contato(LerLinha(informarNome), LerNumero(informarNumero)));
                    estado = Estados.MOSTRANDO_MENU;
                }
                else if (estado == Estados.LISTANDO_CONTATOS)
                {
                    LerLinha(agenda.ListarContatos() + '\n' + continuar);
                    estado = Estados.MOSTRANDO_MENU;
                }
                else if (estado == Estados.REMOVENDO_CONTATOS_POR_NOME)
                {
                    agenda.RemoverContatosPorNome(LerLinha(informarNome));
                    estado = Estados.MOSTRANDO_MENU;
                }
                else if (estado == Estados.REMOVENDO_CONTATOS_POR_NUMERO)
                {
                    agenda.RemoverContatosPorNumero(LerNumero(informarNumero));
                    estado = Estados.MOSTRANDO_MENU;
                }
                else if (estado == Estados.LISTANDO_CONTATOS_POR_NOME)
                {
                    LerLinha(agenda.ListarContatosOrdenadosPorNome() + '\n' + continuar);
                    estado = Estados.MOSTRANDO_MENU;
                }
                else if (estado == Estados.SAINDO)
                {
                    loop = false;
                }

            }
        }
    }
}
