using System;

namespace Locadora.Dominio
{
    public class Locacao : EntidadeBase
    {
        //TODO: unificar locação e serviço locação, são a mesma
        public int IdJogo { get; set; }
        public Jogo Jogo { get; set; }
        public int IdCliente { get; set; }
        public Cliente Cliente { get; set; }
        public Situacao Situacao { get; set; }

        public DateTime DataLocacao { get; set; }
        public DateTime DataPrevistaDevolucao { get; set; }
        public DateTime? DataDevolucao { get; set; }
    }
}
