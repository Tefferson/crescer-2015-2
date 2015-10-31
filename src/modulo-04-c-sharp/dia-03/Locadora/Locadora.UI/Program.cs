using Locadora.Dominio;
using System;
using System.IO;
using System.Windows.Forms;

namespace Locadora.UI
{
    class Program
    {
        [STAThread]
        static void Main(string[] args)
        {
            new Tela().start();
        }
    }
}
