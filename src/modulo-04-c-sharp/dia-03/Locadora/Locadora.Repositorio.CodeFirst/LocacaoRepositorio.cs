using Locadora.Dominio;
using Locadora.Dominio.Repositorio;
using System.Linq;
using System;
using System.Collections.Generic;

namespace Locadora.Repositorio.EF
{
    public class LocacaoRepositorio : ILocacaoRepositorio
    {
        public int Atualizar(Locacao locacao)
        {
            using (var db = new BancoDeDados())
            {
                db.Entry(locacao).State = System.Data.Entity.EntityState.Modified;
                return db.SaveChanges();
            }
        }

        public IList<Locacao> BuscarPendentes()
        {
            using (var db = new BancoDeDados())
            {
                return db.Locacao.Where(l => l.Situacao == Situacao.Pendente).ToList();
            }
        }

        public IList<Locacao> BuscarPendentesPorNomeDoJogo(string nomeJogo)
        {
            using (var db = new BancoDeDados())
            {
                return db.Locacao.Where(l => l.Jogo.Nome == nomeJogo).ToList();
            }
        }

        public int Criar(Locacao locacao)
        {
            using (var db = new BancoDeDados())
            {
                db.Entry(locacao).State = System.Data.Entity.EntityState.Added;
                return db.SaveChanges();
            }
        }
    }
}