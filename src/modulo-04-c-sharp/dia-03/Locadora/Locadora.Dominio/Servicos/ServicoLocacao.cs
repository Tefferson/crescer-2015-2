using Locadora.Dominio.Repositorio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Locadora.Dominio.Servicos
{
    class ServicoLocacao
    {
        private IJogoRepositorio jogoRepositorio;
        private IClienteRepositorio clienteRepositorio;

        public ServicoLocacao(IJogoRepositorio jogoRepositorio, IClienteRepositorio clienteRepositorio)
        {
            this.jogoRepositorio = jogoRepositorio;
            this.clienteRepositorio = clienteRepositorio;
        }

        public bool LocarJogoParaCliente(int idCliente, int idJogo)
        {
            Cliente cliente = clienteRepositorio.BuscarPorId(idCliente);
            Jogo jogo = jogoRepositorio.BuscarPorId(idJogo);

            bool jogoPodeSerLocado = jogo.ClienteLocacao == null;

            if (jogoPodeSerLocado)
            {
                if (ClientePossuiMenosDeTresJogosLocados(idCliente))
                {
                    jogo.LocarPara(cliente);
                    jogoRepositorio.Atualizar(jogo);
                    return true;
                }
            }

            return false;
        }

        private bool ClientePossuiMenosDeTresJogosLocados(int idCliente)
        {
            int jogosLocados = jogoRepositorio.BuscarTodos()
                                              .Count(j => j.ClienteLocacao.Id == idCliente);
            return jogosLocados < 3;
        }
    }
}
