using System.Collections.Generic;

namespace Locadora.Dominio.Repositorio
{
    public interface ILocacaoRepositorio
    {
        int Criar(Locacao locacao);
        int Atualizar(Locacao locacao);
        Locacao BuscarPorId(int idLocacao);
        IList<Locacao> BuscarPendentes();
        IList<Locacao> BuscarPorNomeDoJogo(string term);
        IList<Locacao> BuscarPendentesPorNomeDoJogo(string nomeJogo);
    }
}
