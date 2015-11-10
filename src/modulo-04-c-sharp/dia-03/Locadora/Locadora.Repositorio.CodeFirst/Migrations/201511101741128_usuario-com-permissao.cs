namespace Locadora.Repositorio.CodeFirst.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class usuariocompermissao : DbMigration
    {
        public override void Up()
        {
            RenameTable(name: "dbo.Permissaos", newName: "Permissao");
            RenameTable(name: "dbo.Usuarios", newName: "Usuario");
            RenameTable(name: "dbo.UsuarioPermissaos", newName: "Usuario_Permissao");
            RenameColumn(table: "dbo.Usuario_Permissao", name: "Usuario_Id", newName: "IdUsuario");
            RenameColumn(table: "dbo.Usuario_Permissao", name: "Permissao_Id", newName: "IdPermissao");
            RenameIndex(table: "dbo.Usuario_Permissao", name: "IX_Usuario_Id", newName: "IX_IdUsuario");
            RenameIndex(table: "dbo.Usuario_Permissao", name: "IX_Permissao_Id", newName: "IX_IdPermissao");
            AlterColumn("dbo.Permissao", "Nome", c => c.String(nullable: false, maxLength: 250));
            AlterColumn("dbo.Usuario", "Email", c => c.String(nullable: false, maxLength: 250));
            AlterColumn("dbo.Usuario", "NomeCompleto", c => c.String(nullable: false, maxLength: 250));
            AlterColumn("dbo.Usuario", "Senha", c => c.String(nullable: false, maxLength: 250));
        }
        
        public override void Down()
        {
            AlterColumn("dbo.Usuario", "Senha", c => c.String());
            AlterColumn("dbo.Usuario", "NomeCompleto", c => c.String());
            AlterColumn("dbo.Usuario", "Email", c => c.String());
            AlterColumn("dbo.Permissao", "Nome", c => c.String());
            RenameIndex(table: "dbo.Usuario_Permissao", name: "IX_IdPermissao", newName: "IX_Permissao_Id");
            RenameIndex(table: "dbo.Usuario_Permissao", name: "IX_IdUsuario", newName: "IX_Usuario_Id");
            RenameColumn(table: "dbo.Usuario_Permissao", name: "IdPermissao", newName: "Permissao_Id");
            RenameColumn(table: "dbo.Usuario_Permissao", name: "IdUsuario", newName: "Usuario_Id");
            RenameTable(name: "dbo.Usuario_Permissao", newName: "UsuarioPermissaos");
            RenameTable(name: "dbo.Usuario", newName: "Usuarios");
            RenameTable(name: "dbo.Permissao", newName: "Permissaos");
        }
    }
}
