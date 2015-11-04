using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Locadora.Dominio.Repositorio
{
    public interface IJogoRepositorio
    {
        IList<Jogo> BuscarPorNome(string nome);
        IList<Jogo> BuscarTodos();
        int Criar(Jogo jogo);
        int Atualizar(Jogo jogo);
        int Excluir(int id);
        Jogo BuscarPorId(int id);
    }
}
