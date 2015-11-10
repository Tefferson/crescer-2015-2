namespace Locadora.Repositorio.CodeFirst.Migrations
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
                        Preco = c.Decimal(nullable: false, precision: 18, scale: 2),
                        Categoria = c.Int(nullable: false),
                        Descricao = c.String(nullable: false, maxLength: 250),
                        Imagem = c.String(nullable: false, maxLength: 250),
                        Video = c.String(nullable: false, maxLength: 250),
                        Selo = c.Int(nullable: false),
                        IdClienteLocacao = c.Int(),
                        IdSelo = c.Int(),
                        IdCategoria = c.Int(),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Cliente", t => t.IdClienteLocacao)
                .ForeignKey("dbo.Selo", t => t.IdSelo)
                .ForeignKey("dbo.Categoria", t => t.IdCategoria)
                .Index(t => t.IdClienteLocacao);

            CreateTable(
                "dbo.Selo",
                c => new
                {
                    Id = c.Int(nullable: false, identity: false),
                    Nome = c.String(nullable: false, maxLength: 250),
                })
                .PrimaryKey(t => t.Id);

            CreateTable(
                "dbo.Categoria",
                c => new
                {
                    Id = c.Int(nullable: false, identity: false),
                    Nome = c.String(nullable: false, maxLength: 250),
                })
                .PrimaryKey(t => t.Id);

        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Jogo", "IdClienteLocacao", "dbo.Cliente");
            DropIndex("dbo.Jogo", new[] { "IdClienteLocacao" });
            DropTable("dbo.Jogo");
            DropTable("dbo.Selo");
            DropTable("dbo.Categoria");
            DropTable("dbo.Cliente");
        }
    }
}
