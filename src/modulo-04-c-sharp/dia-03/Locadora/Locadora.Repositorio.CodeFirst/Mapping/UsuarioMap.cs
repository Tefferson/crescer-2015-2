using Locadora.Dominio;
using System.Data.Entity.ModelConfiguration;

namespace Locadora.Repositorio.EF.Mapping
{
    class UsuarioMap : EntityTypeConfiguration<Usuario>
    {
        public UsuarioMap()
        {
            ToTable("Usuario");
            HasKey(u => u.Id);

            Property(p => p.Email).IsRequired().HasMaxLength(250);
            Property(p => p.NomeCompleto).IsRequired().HasMaxLength(250);
            Property(p => p.Senha).IsRequired().HasMaxLength(250);

            HasMany(u => u.Permissoes).WithMany(p => p.Usuarios).Map(
                m =>
                {
                    m.ToTable("Usuario_Permissao");
                    m.MapLeftKey("IdUsuario");
                    m.MapRightKey("IdPermissao");
                }
            );
        }
    }
}
