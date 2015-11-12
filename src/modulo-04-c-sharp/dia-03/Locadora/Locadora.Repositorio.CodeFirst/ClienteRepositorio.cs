using Locadora.Dominio.Repositorio;
using System.Collections.Generic;
using System.Linq;
using Locadora.Dominio;

namespace Locadora.Repositorio.EF
{
    public class ClienteRepositorio : IClienteRepositorio
    {
        public Cliente BuscarPorId(int idCliente)
        {
            using (var db = new BancoDeDados())
            {
                return db.Cliente.Find(idCliente);
            }
        }

        public IList<Cliente> BuscarPorNome(string nome)
        {
            using (var db = new BancoDeDados())
            {
                return db.Cliente.Where(cliente => cliente.Nome.Contains(nome)).ToList();
            }
        }
    }
}
