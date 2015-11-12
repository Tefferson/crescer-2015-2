using System;
using System.Globalization;
using System.Text;

namespace Locadora.Dominio
{
    public class Jogo : EntidadeBase
    {
        public string Nome { get; set; }

        public Categoria Categoria { get; set; }

        public string Descricao { get; set; }

        public string Imagem { get; set; }

        public string Video { get; set; }

        public int IdSelo { get; set; }

        public Selo Selo { get; set; }

        public bool Disponivel { get; set; }

        public Jogo()
        {

        }

        public Jogo(int id, Cliente clienteLocacao = null)
        {
            this.Id = id;
        }

        public override string ToString()
        {
            var builder = new StringBuilder();
            builder.AppendLine("Id: " + this.Id);
            builder.AppendLine("Nome: " + this.Nome);
            builder.AppendLine("Categoria: " + this.Categoria);
            builder.AppendLine("Descrição: " + this.Descricao);
            builder.AppendLine("Selo: " + this.Selo.Nome);
            builder.AppendLine("Image: " + this.Imagem);
            builder.AppendLine("Video: " + this.Video);
            builder.AppendLine("Situação: " + (this.Disponivel ? "Disponível" : "Indisponível"));

            return builder.ToString();
        }

        public override int GetHashCode()
        {
            return base.GetHashCode();
        }

        public override bool Equals(object obj)
        {
            if (obj != null && obj.GetType() == typeof(Jogo))
            {
                Jogo jogoComp = (Jogo)obj;

                return this.Id == jogoComp.Id
                    && this.Nome == jogoComp.Nome
                    && this.Categoria == jogoComp.Categoria
                    && this.Descricao == jogoComp.Descricao
                    && this.Selo == jogoComp.Selo
                    && this.Imagem == jogoComp.Imagem
                    && this.Video == jogoComp.Video
                    && this.Disponivel == jogoComp.Disponivel;
            }

            return false;
        }
    }
}
