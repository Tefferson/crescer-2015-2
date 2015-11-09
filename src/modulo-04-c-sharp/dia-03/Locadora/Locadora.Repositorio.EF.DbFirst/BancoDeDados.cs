using Locadora.Dominio;
using System.Data.Entity;
using System.Data.Entity.ModelConfiguration;

namespace Locadora.Repositorio.EF.DbFirst
{
    public class BancoDeDados : DbContext
    {
        public BancoDeDados() : base("LOCADORA")
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
            Property(p => p.Selo).IsRequired();
            Property(p => p.Categoria).IsRequired();

            HasOptional(o => o.ClienteLocacao).WithOptionalDependent().Map(m => m.MapKey("IdClienteLocacao"));
            //  HasRequired(o => o.Selo).WithRequiredDependent().Map(m => m.MapKey("IdClienteLocacao"));
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
}
