using Locadora.Dominio.Repositorio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Locadora.Dominio.Test.Mocks
{
    class LocacaoRepositorioMock : ILocacaoRepositorio
    {
        public int Atualizar(Locacao locacao)
        {
            return Db().Any(l => l.Id == locacao.Id) ? 1 : 0;
        }

        public IList<Locacao> BuscarPendentes()
        {
            return Db().Where(l => l.Situacao == Situacao.Pendente).ToList();
        }

        public IList<Locacao> BuscarPendentesPorNomeDoJogo(string nomeJogo)
        {
            return Db().Where(l => l.Situacao == Situacao.Pendente && l.Jogo.Nome.Contains(nomeJogo)).ToList();
        }

        public Locacao BuscarPorId(int idLocacao)
        {
            return Db().FirstOrDefault(l => l.Id == idLocacao);
        }

        public IList<Locacao> BuscarPorNomeDoJogo(string term)
        {
            return Db().Where(l => l.Jogo.Nome.Contains(term)).ToList();
        }

        public int Criar(Locacao locacao)
        {
            return Db().Any(l => l.Id == locacao.Id) ? 0 : 1;
        }

        private IList<Locacao> Db()
        {
            var locacoes = new List<Locacao>();

            Locacao locacao1 = new Locacao(1)
            {
                IdCliente = 1,
                IdJogo = 1,
                Situacao = Situacao.Pendente,
                DataLocacao = new DateTime(2015, 11, 11),
                DataPrevistaDevolucao = new DateTime(2015, 11, 13),
                Jogo = new JogoRepositorioMock().BuscarPorId(1),
                Cliente = new ClienteRepositorioMock().BuscarPorId(1),
            };

            locacoes.Add(locacao1);

            return locacoes;
        }
    }
}
