using System;
using System.Collections.Generic;
using System.Linq;

namespace ConsoleApp
{
    public class Agenda
    {
        private List<Contato> contatos = new List<Contato>();

        public int QuantidadeContatos { get { return contatos.Count; } }

        public Agenda()
        {

        }

        public void AdicionarContato(Contato contato)
        {
            contatos.Add(contato);
        }

        public void RemoverContatosPorNome(string nomeContato)
        {
            for (var i = contatos.Count-1; i >= 0; i--)
            {
                if (contatos[i].Nome == nomeContato)
                {
                    contatos.RemoveAt(i);
                }
            }
        }

        public string ListarContatos()
        {
            var lista = "";
            foreach (var contato in contatos)
            {
                lista += (contato.Nome + "-" + contato.Numero + "\n");
            }
            return lista;
        }

        public string ListarContatosOrdenadosPorNome()
        {            
            contatos.Sort(delegate (Contato contatoA, Contato y)
            {
                if (contatoA.Nome == null && y.Nome == null) return 0;
                else if (contatoA.Nome == null) return -1;
                else if (y.Nome == null) return 1;
                else return contatoA.Nome.CompareTo(y.Nome);
            });
            return ListarContatos();
        }

        public void RemoverContatosPorNumero(int numeroContato)
        {
            for (var i = contatos.Count - 1; i >= 0; i--)
            {
                if (contatos[i].Numero == numeroContato)
                {
                    contatos.RemoveAt(i);
                }
            }
        }
    }
}
