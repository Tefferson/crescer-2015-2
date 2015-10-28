using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TrabalhoComArquivos
{
    class Program
    {
        static void Main(string[] args)
        {
            string caminhoArquivo = @"C:\Users\tefferson.guterres\Desktop\arquivos\meu-arquivo.txt";

            var writer = new StreamWriter(caminhoArquivo, true);
            try
            {
                var writer2 = new StreamWriter(caminhoArquivo, true);
                writer2.WriteLine("didi must die1!!");
            }
            catch
            {
            }
            finally
            {

            }


            Console.WriteLine(String.Format("{0:dd-MM-yyyy}", DateTime.Now));

            writer.WriteLine("didi must die!!");
            writer.Close();
            
            Console.Read();
        }
    }
}
