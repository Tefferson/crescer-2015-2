using Locadora.Dominio.Repositorio;
using System;
using System.Threading;

namespace Locadora.UI
{
    abstract class TelaBase
    {
        private const int TEMPO_MENSAGEM = 3000;

        public abstract string Titulo { get; }

        private IJogoRepositorio jogoRepositorio = null;
        protected IJogoRepositorio JogoRepositorio
        {
            get
            {
                if(jogoRepositorio == null)
                {
                    jogoRepositorio = new Locadora.Repositorio.ADO.JogoRepositorio();
                }

                return jogoRepositorio;
            }
        }

        protected void ImprimirMensagem(string mensagem)
        {
            Console.WriteLine();
            Console.WriteLine("INFO :: " + mensagem);
            Thread.Sleep(TEMPO_MENSAGEM);
        }

        protected void ImprimirErro(string mensagem)
        {
            Console.WriteLine();
            Console.WriteLine("ERRO :: " + mensagem);

            Thread.Sleep(TEMPO_MENSAGEM);
        }

        protected string LerDados(string info)
        {
            Console.Write(info + ": ");
            return Console.ReadLine();
        }

        public void Exibir()
        {
            Console.Clear();
            ImprimirTitulo();
            Iniciar();
        }

        protected abstract void Iniciar();


        private void ImprimirTitulo()
        {
            Console.WriteLine(String.Format("===== {0} =====", Titulo));
        }

    }
}
