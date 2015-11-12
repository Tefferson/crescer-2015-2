using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Locadora.Dominio.Repositorio
{
    public interface ILocacaoRepositorio
    {
        int Criar(Locacao locacao);
        int Atualizar(Locacao locacao);
        IList<Locacao> BuscarPendentes();
        IList<Locacao> BuscarPendentesPorNomeDoJogo(string nomeJogo);
    }
}
