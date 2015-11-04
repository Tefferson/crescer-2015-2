using Locadora.Dominio.Repositorio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Locadora.Dominio;

namespace Locadora.Repositorio.ADO
{
    public class ClienteRepositorio : IClienteRepositorio
    {
        public int Atualizar(Cliente entidade)
        {
            throw new NotImplementedException();
        }

        public Cliente BuscarPorId(int id)
        {
            throw new NotImplementedException();
        }

        public IList<Cliente> BuscarPorNome(string nome)
        {
            throw new NotImplementedException();
        }

        public IList<Cliente> BuscarTodos()
        {
            throw new NotImplementedException();
        }

        public int Criar(Cliente entidade)
        {
            throw new NotImplementedException();
        }

        public int Excluir(int id)
        {
            throw new NotImplementedException();
        }
        
    }
}
