namespace Locadora.Repositorio.EF.Migrations
{
    using System;
    using System.Data.Entity.Migrations;

    public partial class initial : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Selo",
                c => new
                {
                    Id = c.Int(nullable: false, identity: false),
                    Nome = c.String(nullable: false, maxLength: 250),
                })
                .PrimaryKey(t => t.Id);

            CriarSelos();


            CreateTable(
                "dbo.Categoria",
                c => new
                {
                    Id = c.Int(nullable: false, identity: false),
                    Nome = c.String(nullable: false, maxLength: 250),
                })
                .PrimaryKey(t => t.Id);

            CriarCategorias();

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
                    Preco = c.Decimal(nullable: false, precision: 18, scale: 2),
                    IdCategoria = c.Int(nullable: false),
                    Descricao = c.String(nullable: false, maxLength: 250),
                    Imagem = c.String(nullable: false, maxLength: 250),
                    Video = c.String(nullable: false, maxLength: 250),
                    IdSelo = c.Int(nullable: false),
                    IdClienteLocacao = c.Int(),
                })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Cliente", t => t.IdClienteLocacao)
                .ForeignKey("dbo.Selo", t => t.IdSelo)
                .ForeignKey("dbo.Categoria", t => t.IdCategoria)
                .Index(t => t.IdSelo)
                .Index(t => t.IdClienteLocacao)
                .Index(t => t.IdCategoria);

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
            DropForeignKey("dbo.Jogo", "IdClienteLocacao", "dbo.Cliente");
            DropIndex("dbo.Usuario_Permissao", new[] { "IdPermissao" });
            DropIndex("dbo.Usuario_Permissao", new[] { "IdUsuario" });
            DropIndex("dbo.Jogo", new[] { "IdClienteLocacao" });
            DropTable("dbo.Usuario_Permissao");
            DropTable("dbo.Usuario");
            DropTable("dbo.Permissao");
            DropTable("dbo.Jogo");
            DropTable("dbo.Cliente");
        }

        private void CriarSelos()
        {
            Sql("INSERT INTO Selo (Id, Nome) VALUES (1, 'Bronze')");
            Sql("INSERT INTO Selo (Id, Nome) VALUES (2, 'Prata')");
            Sql("INSERT INTO Selo (Id, Nome) VALUES (3, 'Ouro')");
        }

        private void CriarCategorias()
        {
            Sql("INSERT INTO Categoria (Id, Nome) VALUES (1, 'RPG')");
            Sql("INSERT INTO Categoria (Id, Nome) VALUES (2, 'Corrida')");
            Sql("INSERT INTO Categoria (Id, Nome) VALUES (3, 'Aventura')");
            Sql("INSERT INTO Categoria (Id, Nome) VALUES (4, 'Luta')");
            Sql("INSERT INTO Categoria (Id, Nome) VALUES (5, 'Esporte')");
        }
    }
}
