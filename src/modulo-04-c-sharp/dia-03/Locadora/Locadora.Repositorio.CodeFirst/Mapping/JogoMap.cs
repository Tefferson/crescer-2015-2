using Locadora.Dominio;
using System.Data.Entity.ModelConfiguration;

namespace Locadora.Repositorio.EF.Mapping
{
    class JogoMap : EntityTypeConfiguration<Jogo>
    {
        public JogoMap()
        {
            ToTable("Jogo");
            HasKey(j => j.Id);

            Property(p => p.Nome).IsRequired().HasMaxLength(250);
            Property(p => p.Descricao).IsRequired().HasMaxLength(250);
            Property(p => p.Imagem).IsRequired().HasMaxLength(250);
            Property(p => p.Video).IsRequired().HasMaxLength(250);
            Property(p => p.Categoria).IsRequired().HasColumnName("IdCategoria");

            HasOptional(o => o.ClienteLocacao).WithOptionalDependent().Map(m => m.MapKey("IdClienteLocacao"));
            HasRequired(o => o.Selo).WithRequiredDependent().Map(m => m.MapKey("IdSelo"));
        }

    }
}
