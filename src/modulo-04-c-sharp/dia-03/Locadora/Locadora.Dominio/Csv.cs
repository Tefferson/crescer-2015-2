using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;

namespace Locadora.Dominio
{
    public class Csv
    {

        private List<string> ToList(StreamReader reader)
        {
            string row = "";
            List<string> lista = new List<string>();
            bool notEnded = false;
            string commaString = "";

            while (row != null)
            {
                string[] split;
                split = row.Split(',');
                foreach (string value in split)
                {
                    bool startsWithQuote = value.StartsWith("\"");
                    bool endsWithQuote = value.EndsWith("\"");
                    if (notEnded)
                    {
                        if (endsWithQuote)
                        {
                            lista.Add(commaString += "," + value.Substring(0, value.Length - 1));
                            commaString = "";
                            notEnded = false;
                        }
                        else
                        {
                            commaString += "," + value;
                        }
                        continue;
                    }
                    if (startsWithQuote && endsWithQuote)
                    {
                        if ((value.EndsWith("\"\"") && !value.EndsWith("\"\"\"")) && value != "\"\"")
                        {
                            notEnded = true;
                            commaString = value;
                            continue;
                        }
                        lista.Add(value.Substring(1, value.Length - 2));
                        continue;
                    }
                    if (startsWithQuote && !endsWithQuote)
                    {
                        notEnded = true;
                        commaString += value.Substring(1);
                        continue;
                    }
                    lista.Add(value);
                }
                row = reader.ReadLine();
            }
            return lista;
        }

        public IList<Jogo> GetJogosFromCsv(StreamReader reader)
        {
            List<string> lista = ToList(reader);
            IList<Jogo> listaDeJogos = new List<Jogo>();
            for (int i = 1; i < lista.Count; i += 3)
            {
                listaDeJogos.Add(ParseToJogo(lista.GetRange(i, 3).ToArray()));
            }
            return listaDeJogos;
        }

        private Jogo ParseToJogo(string[] values)
        {
            return new Jogo(values[0], Convert.ToDouble(values[1]), values[2]);
        }
    }
}
