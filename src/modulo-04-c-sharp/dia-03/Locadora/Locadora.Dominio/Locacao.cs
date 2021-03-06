﻿using System;

namespace Locadora.Dominio
{
    public class Locacao : EntidadeBase
    {
        public int IdJogo { get; set; }
        public Jogo Jogo { get; set; }
        public int IdCliente { get; set; }
        public Cliente Cliente { get; set; }
        public Situacao Situacao { get; set; }

        public DateTime DataLocacao { get; set; }
        public DateTime DataPrevistaDevolucao { get; set; }
        public DateTime? DataDevolucao { get; set; }

        public Locacao(int idLocacao) {
            Id = idLocacao;
        }

        public Locacao() { }
    }
}
