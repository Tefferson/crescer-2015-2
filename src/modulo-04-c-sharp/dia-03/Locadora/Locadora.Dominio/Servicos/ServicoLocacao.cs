using Locadora.Dominio.Repositorio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Locadora.Dominio.Servicos
{
    public class ServicoLocacao
    {
        private IJogoRepositorio jogoRepositorio;
        private IClienteRepositorio clienteRepositorio;
        private ILocacaoRepositorio locacaoRepositorio;

        public ServicoLocacao(IJogoRepositorio jogoRepositorio, IClienteRepositorio clienteRepositorio, ILocacaoRepositorio locacaoRepositorio)
        {
            this.jogoRepositorio = jogoRepositorio;
            this.clienteRepositorio = clienteRepositorio;
            this.locacaoRepositorio = locacaoRepositorio;
        }

        public bool LocarJogoParaCliente(int idJogo, string nomeCliente)
        {
            Cliente cliente = clienteRepositorio.BuscarPorNome(nomeCliente).FirstOrDefault(c => c.Nome == nomeCliente);
            Jogo jogo = jogoRepositorio.BuscarPorId(idJogo);

            bool jogoEClienteSaoValidos = jogo != null && cliente != null;

            if (jogoEClienteSaoValidos)
            {
                if (jogo.Disponivel && ClientePossuiMenosDeTresJogosLocados(cliente))
                {
                    jogo.Disponivel = false;
                    jogoRepositorio.Atualizar(jogo);

                    DateTime dataPrevistaParaDevolucao = DateTime.Now.AddDays(jogo.Selo.PrazoDevolucao);

                    Locacao locacao = new Locacao()
                    {
                        IdJogo = jogo.Id,
                        IdCliente = cliente.Id,
                        Situacao = Situacao.Pendente,
                        DataLocacao = DateTime.Now,
                        DataPrevistaDevolucao = dataPrevistaParaDevolucao
                    };

                    locacaoRepositorio.Criar(locacao);

                    return true;
                }
            }

            return false;
        }

      /*  public decimal CalcularValorFinal(Locacao locacao)
        {
            decimal precoBase = locacao.Jogo.Selo.Preco;
            
            bool atrasado = locacao
            if()
                
        }*/

        private bool ClientePossuiMenosDeTresJogosLocados(Cliente cliente)
        {
            int idCliente = cliente.Id;
            int jogosLocados = locacaoRepositorio
                                    .BuscarPendentes()
                                    .Count(l => l.Cliente.Id == idCliente);
            return jogosLocados < 3;
        }

        public bool DevolverJogo(int idLocacao)
        {
            Locacao locacao = locacaoRepositorio.BuscarPorId(idLocacao);
            locacao.Jogo.Disponivel = true;
            locacao.Situacao = Situacao.Devolvido;
            return locacaoRepositorio.Atualizar(locacao) > 0;
        }
    }
}
