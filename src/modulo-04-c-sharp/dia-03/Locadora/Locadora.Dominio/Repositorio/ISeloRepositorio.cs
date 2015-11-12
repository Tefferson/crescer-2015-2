using System.Collections.Generic;

namespace Locadora.Dominio.Repositorio
{
    public interface ISeloRepositorio
    {
        Selo BuscarPorNome(string nome);
        IList<string> BuscarTodosNomes();
    }
}
