using Locadora.Dominio;
using System.Data.Entity;
using System.Data.Entity.ModelConfiguration;

namespace Locadora.Repositorio.EF
{
    public class BancoDeDados : DbContext
    {
        public BancoDeDados() : base("LOCADORA_EF")
        {

        }

        public DbSet<Jogo> Jogo { get; set; }
        public DbSet<Cliente> Cliente { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Configurations.Add(new JogoMap());
            modelBuilder.Configurations.Add(new ClienteMap());
            base.OnModelCreating(modelBuilder);
        }
    }

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
            Property(p => p.Preco).IsRequired();
            Property(p => p.Selo).IsRequired().HasColumnName("IdSelo") ;
            Property(p => p.Categoria).IsRequired().HasColumnName("IdCategoria");

            HasOptional(o => o.ClienteLocacao).WithOptionalDependent().Map(m => m.MapKey("IdClienteLocacao"));
        }

    }

    class ClienteMap : EntityTypeConfiguration<Cliente>
    {
        public ClienteMap()
        {
            ToTable("Cliente");
            HasKey(c => c.Id);

            Property(p => p.Nome).IsRequired().HasMaxLength(250);
        }

    }

    class CategoriaMap : EntityTypeConfiguration<Cliente>
    {
        public CategoriaMap()
        {
            ToTable("Categoria");
            HasKey(c => c.Id);

            Property(p => p.Nome).IsRequired().HasMaxLength(250);
        }

    }

    class SeloMap : EntityTypeConfiguration<Cliente>
    {
        public SeloMap()
        {
            ToTable("Selo");
            HasKey(c => c.Id);

            Property(p => p.Nome).IsRequired().HasMaxLength(250);
        }

    }
}
