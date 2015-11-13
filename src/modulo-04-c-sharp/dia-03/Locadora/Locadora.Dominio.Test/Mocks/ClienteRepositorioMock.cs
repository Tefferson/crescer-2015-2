using Locadora.Dominio.Repositorio;
using System.Collections.Generic;
using System.Linq;

namespace Locadora.Dominio.Test.Mocks
{
    class ClienteRepositorioMock : IClienteRepositorio
    {
        public Cliente BuscarPorId(int idCliente)
        {
            return Db().FirstOrDefault(c => c.Id == idCliente);
        }

        public IList<Cliente> BuscarPorNome(string nome)
        {
            return Db().Where(c => c.Nome.Contains(nome)).ToList();
        }

        private IList<Cliente> Db()
        {
            var clientes = new List<Cliente>();

            var cliente1 = new Cliente(1)
            {
                Nome = "Cliente bom"
            };

            var cliente2 = new Cliente(2)
            {
                Nome = "Cliente fiel"
            };

            clientes.Add(cliente1);
            clientes.Add(cliente2);
            return clientes;
        }
    }
}
