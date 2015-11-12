using Locadora.Dominio;
using System.Data.Entity.ModelConfiguration;

namespace Locadora.Repositorio.EF.Mapping
{
    class LocacaoMap : EntityTypeConfiguration<Locacao>
    {
        public LocacaoMap()
        {
            ToTable("Locacao");
            HasKey(l => l.Id);

            Property(p => p.DataDevolucao).IsOptional();
            Property(p => p.DataLocacao).IsRequired();
            Property(p => p.DataPrevistaDevolucao).IsRequired();

            HasRequired(o => o.Cliente).WithOptional().Map(m => m.MapKey("IdCliente"));
            HasRequired(o => o.Jogo).WithOptional().Map(m => m.MapKey("IdJogo"));
        }

    }
}
