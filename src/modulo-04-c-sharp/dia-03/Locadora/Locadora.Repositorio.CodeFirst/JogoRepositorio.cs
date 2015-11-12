using Locadora.Dominio.Repositorio;
using System.Collections.Generic;
using System.Linq;
using Locadora.Dominio;
using System;

namespace Locadora.Repositorio.EF
{
    public class JogoRepositorio : IJogoRepositorio
    {
        public int Atualizar(Jogo jogo)
        {
            using (var db = new BancoDeDados())
            {
                db.Entry(jogo).State = System.Data.Entity.EntityState.Modified;
                return db.SaveChanges();
            }
        }

        public IList<Jogo> BuscarDisponiveis()
        {
            using (var db = new BancoDeDados())
            {
                return db.Jogo.Include("Selo").Where(j => j.Disponivel).ToList();
            }
        }

        public IList<Jogo> BuscarIndisponiveis()
        {
            using (var db = new BancoDeDados())
            {
                return db.Jogo.Include("Selo").Where(j => !j.Disponivel).ToList();
            }
        }

        public Jogo BuscarPorId(int id)
        {
            using (var db = new BancoDeDados())
            {
                return db.Jogo.Include("Selo").FirstOrDefault(j => j.Id == id);
            }
        }

        public IList<Jogo> BuscarPorNome(string nome)
        {
            using (var db = new BancoDeDados())
            {
                return db.Jogo.Include("Selo").Where(jogo => jogo.Nome.Contains(nome)).ToList();
            }
        }

        public IList<Jogo> BuscarTodos()
        {
            using (var db = new BancoDeDados())
            {
                return db.Jogo.Include("Selo").ToList();
            }
        }

        public int Criar(Jogo jogo)
        {
            using (var db = new BancoDeDados())
            {
                db.Entry(jogo).State = System.Data.Entity.EntityState.Added;
                return db.SaveChanges();
            }
        }

        public int Excluir(int id)
        {
            using (var db = new BancoDeDados())
            {
                db.Entry(new Jogo(id)).State = System.Data.Entity.EntityState.Deleted;
                return db.SaveChanges();
            }
        }
    }
}
