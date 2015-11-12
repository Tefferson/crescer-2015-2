using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Locadora.Dominio
{
    public class Locacao : EntidadeBase
    {
        public Jogo Jogo { get; set; }
        public Cliente Cliente { get; set; }
        public Situacao Situacao { get; set; }

        public DateTime DataLocacao { get; set; }
        public DateTime DataPrevistaDevolucao { get; set; }
        public DateTime? DataDevolucao { get; set; }
    }
}
