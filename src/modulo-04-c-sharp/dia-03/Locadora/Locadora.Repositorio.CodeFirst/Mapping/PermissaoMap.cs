using Locadora.Dominio;
using System.Data.Entity.ModelConfiguration;

namespace Locadora.Repositorio.EF.Mapping
{
    class PermissaoMap : EntityTypeConfiguration<Permissao>
    {
        public PermissaoMap()
        {
            ToTable("Permissao");
            HasKey(u => u.Id);

            Property(p => p.Nome).IsRequired().HasMaxLength(250);
        }
    }
}
