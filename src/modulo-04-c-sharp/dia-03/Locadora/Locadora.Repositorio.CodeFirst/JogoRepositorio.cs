using Locadora.Dominio.Repositorio;
using System.Collections.Generic;
using System.Linq;
using Locadora.Dominio;

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

        public Jogo BuscarPorId(int id)
        {
            using (var db = new BancoDeDados())
            {
                return db.Jogo.Find(id);
            }
        }

        public IList<Jogo> BuscarPorNome(string nome)
        {
            using (var db = new BancoDeDados())
            {
                return db.Jogo.Where(jogo => jogo.Nome.Contains(nome)).ToList();
            }
        }

        public IList<Jogo> BuscarTodos()
        {
            using (var db = new BancoDeDados())
            {
                return db.Jogo.ToList();
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
