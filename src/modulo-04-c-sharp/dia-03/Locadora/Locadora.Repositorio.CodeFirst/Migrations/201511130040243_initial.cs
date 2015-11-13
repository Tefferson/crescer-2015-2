namespace Locadora.Repositorio.EF.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class initial : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Cliente",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Nome = c.String(nullable: false, maxLength: 250),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.Jogo",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Nome = c.String(nullable: false, maxLength: 250),
                        IdCategoria = c.Int(nullable: false),
                        Descricao = c.String(nullable: false, maxLength: 250),
                        Imagem = c.String(nullable: false, maxLength: 250),
                        Video = c.String(nullable: false, maxLength: 250),
                        IdSelo = c.Int(nullable: false),
                        Disponivel = c.Boolean(nullable: false),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Selo", t => t.IdSelo, cascadeDelete: true)
                .Index(t => t.IdSelo);
            
            CreateTable(
                "dbo.Selo",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Nome = c.String(nullable: false, maxLength: 250),
                        Preco = c.Decimal(nullable: false, precision: 18, scale: 2),
                        PrazoDevolucao = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.Locacao",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        IdJogo = c.Int(nullable: false),
                        IdCliente = c.Int(nullable: false),
                        Situacao = c.Int(nullable: false),
                        DataLocacao = c.DateTime(nullable: false),
                        DataPrevistaDevolucao = c.DateTime(nullable: false),
                        DataDevolucao = c.DateTime(),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Cliente", t => t.IdCliente, cascadeDelete: true)
                .ForeignKey("dbo.Jogo", t => t.IdJogo, cascadeDelete: true)
                .Index(t => t.IdJogo)
                .Index(t => t.IdCliente);
            
            CreateTable(
                "dbo.Permissao",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Nome = c.String(nullable: false, maxLength: 250),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.Usuario",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Email = c.String(nullable: false, maxLength: 250),
                        NomeCompleto = c.String(nullable: false, maxLength: 250),
                        Senha = c.String(nullable: false, maxLength: 250),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.Usuario_Permissao",
                c => new
                    {
                        IdUsuario = c.Int(nullable: false),
                        IdPermissao = c.Int(nullable: false),
                    })
                .PrimaryKey(t => new { t.IdUsuario, t.IdPermissao })
                .ForeignKey("dbo.Usuario", t => t.IdUsuario, cascadeDelete: true)
                .ForeignKey("dbo.Permissao", t => t.IdPermissao, cascadeDelete: true)
                .Index(t => t.IdUsuario)
                .Index(t => t.IdPermissao);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Usuario_Permissao", "IdPermissao", "dbo.Permissao");
            DropForeignKey("dbo.Usuario_Permissao", "IdUsuario", "dbo.Usuario");
            DropForeignKey("dbo.Locacao", "IdJogo", "dbo.Jogo");
            DropForeignKey("dbo.Locacao", "IdCliente", "dbo.Cliente");
            DropForeignKey("dbo.Jogo", "IdSelo", "dbo.Selo");
            DropIndex("dbo.Usuario_Permissao", new[] { "IdPermissao" });
            DropIndex("dbo.Usuario_Permissao", new[] { "IdUsuario" });
            DropIndex("dbo.Locacao", new[] { "IdCliente" });
            DropIndex("dbo.Locacao", new[] { "IdJogo" });
            DropIndex("dbo.Jogo", new[] { "IdSelo" });
            DropTable("dbo.Usuario_Permissao");
            DropTable("dbo.Usuario");
            DropTable("dbo.Permissao");
            DropTable("dbo.Locacao");
            DropTable("dbo.Selo");
            DropTable("dbo.Jogo");
            DropTable("dbo.Cliente");
        }
    }
}
