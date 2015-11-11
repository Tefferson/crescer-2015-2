using Locadora.Dominio;
using Locadora.Repositorio.EF.Mapping;
using System.Data.Entity;

namespace Locadora.Repositorio.EF
{
    public class BancoDeDados : DbContext
    {
        public BancoDeDados() : base("LOCADORA_EF")
        {

        }

        public DbSet<Jogo> Jogo { get; set; }
        public DbSet<Cliente> Cliente { get; set; }
        public DbSet<Usuario> Usuario { get; set; }
        public DbSet<Permissao> Permissao { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Configurations.Add(new JogoMap());
            modelBuilder.Configurations.Add(new ClienteMap());
            modelBuilder.Configurations.Add(new UsuarioMap());
            modelBuilder.Configurations.Add(new PermissaoMap());
            base.OnModelCreating(modelBuilder);
        }
    }
}
