using System;
using System.Collections.Generic;
using System.Xml.Linq;

namespace Locadora.Dominio
{
    public class ImportaJogo
    {
        public ImportaJogo()
        {

        }

        public void importarJogos(IList<Jogo> jogos, BaseDeDados dados)
        {
            foreach (Jogo jogo in jogos)
            {
                dados.AddXElement("jogos", jogo);
            }
        }
    }
}
