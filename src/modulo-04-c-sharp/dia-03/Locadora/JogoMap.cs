﻿using Locadora.Dominio;
using System.Data.Entity;
using System.Data.Entity.ModelConfiguration;

namespace Locadora.Repositorio.EF
{
    public class JogoMap : EntityTypeConfiguration<Jogo>
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
            Property(p => p.Selo).IsRequired().HasColumnName("IdSelo");
            Property(p => p.Categoria).IsRequired().HasColumnName("IdCategoria");

            HasOptional(o => o.ClienteLocacao).WithOptionalDependent().Map(m => m.MapKey("IdClienteLocacao"));
        }
    }
}
