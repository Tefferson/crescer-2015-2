using Locadora.Dominio;
using System.Data.Entity.ModelConfiguration;

namespace Locadora.Repositorio.EF.Mapping
{
    class ClienteMap : EntityTypeConfiguration<Cliente>
    {
        public ClienteMap()
        {
            ToTable("Cliente");
            HasKey(c => c.Id);

            Property(p => p.Nome).IsRequired().HasMaxLength(250);
        }

    }
}
