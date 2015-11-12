using Locadora.Dominio.Repositorio;
using System.Collections.Generic;
using System.Linq;
using Locadora.Dominio;
using System;

namespace Locadora.Repositorio.EF
{
    public class SeloRepositorio : ISeloRepositorio
    {
        public IList<string> BuscarTodosNomes()
        {
            using (var db = new BancoDeDados())
            {
                return db.Selo.Select(s => s.Nome).ToList();
            }
        }

        public Selo BuscarPorNome(string nome)
        {
            using (var db = new BancoDeDados())
            {
                var ss = db.Selo.FirstOrDefault(selo => selo.Nome == nome);
                return ss;
            }
        }
    }
}
