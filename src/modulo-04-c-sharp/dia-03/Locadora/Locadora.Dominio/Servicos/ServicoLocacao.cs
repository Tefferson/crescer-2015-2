using Locadora.Dominio.Repositorio;
using System;
using System.Linq;

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

        public decimal CalcularValorFinal(Locacao locacao)
        {
            decimal precoBase = locacao.Jogo.Selo.Preco;
            int prazo = locacao.Jogo.Selo.PrazoDevolucao;

            TimeSpan diff = DateTime.Now - locacao.DataLocacao;
            int diasDesdeALocacao = diff.Days;

            bool deveAplicarMulta = diasDesdeALocacao > prazo;

            if (deveAplicarMulta)
            {
                decimal valorDaMultaPorDiaAtrasado = 5m;
                int diasDeAtraso = diasDesdeALocacao - prazo;

                precoBase += diasDeAtraso * valorDaMultaPorDiaAtrasado;
            }

            return precoBase;
        }

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

            if (locacao.Situacao == Situacao.Pendente)
            {
                locacao.Jogo.Disponivel = true;
                locacao.Situacao = Situacao.Devolvido;
                locacao.DataDevolucao = DateTime.Now;
                return locacaoRepositorio.Atualizar(locacao) > 0;
            }

            return false;
        }
    }
}
