using Locadora.Dominio;
using System.Data.Entity.ModelConfiguration;

namespace Locadora.Repositorio.EF.Mapping
{
    class SeloMap : EntityTypeConfiguration<Selo>
    {
        public SeloMap()
        {
            ToTable("Selo");
            HasKey(s => s.Id);

            Property(p => p.Nome).IsRequired().HasMaxLength(250);
            Property(p => p.PrazoDevolucao).IsRequired();
            Property(p => p.Preco).IsRequired();
        }

    }
}
