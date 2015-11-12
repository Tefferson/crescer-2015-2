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
            Property(p => p.IdCliente);
            Property(p => p.IdJogo);

            HasRequired(o => o.Cliente).WithMany().HasForeignKey(o => o.IdCliente);
            HasRequired(o => o.Jogo).WithMany().HasForeignKey(o => o.IdJogo);
        }

    }
}
