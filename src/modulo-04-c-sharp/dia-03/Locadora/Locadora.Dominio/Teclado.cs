using System;

namespace Locadora.Dominio
{
    public class Teclado
    {
        public Teclado() { }

        public Int32? LerInt()
        {
            string digitado = Console.ReadLine();
            int num;
            if (digitado != "" && Int32.TryParse(digitado, out num))
            {
                return num;
            }
            return null;
        }

        public Int32? LerInt(int min, int max)
        {
            string digitado = Console.ReadLine();
            int num;
            if (digitado != "" && Int32.TryParse(digitado, out num))
            {
                if (num >= min && num <= max)
                {
                    return num;
                }
            }
            return null;
        }

        public Double? LerDouble()
        {
            string digitado = Console.ReadLine();
            double num;
            if (digitado != "" && Double.TryParse(digitado.Replace(".", ","), out num))
            {
                return num;
            }
            return null;
        }

        public string LerString()
        {
            string digitado = Console.ReadLine();
            return String.IsNullOrEmpty(digitado)? null : digitado;
        }

        public string LerLinha()
        {
            return Console.ReadLine();
        }

        public bool LerNome(string mensagemSolicitacao, string mensagemAviso, out string nome)
        {
            nome = null;
            while (nome == null)
            {
                Console.Write(mensagemSolicitacao);
                nome = LerString();
                if (nome == null)
                {
                    Console.Write(mensagemAviso);
                    nome = LerString();
                    if (nome == null)
                    {
                        nome = "";
                        return false;
                    }
                }
            }
            return true;
        }

        public bool LerPreco(string mensagemSolicitacao, string mensagemAviso, out double preco)
        {
            double? precoLido = null;
            while (precoLido == null)
            {
                Console.Write(mensagemSolicitacao);
                precoLido = LerDouble();
                if (precoLido == null)
                {
                    Console.Write(mensagemAviso);
                    precoLido = LerDouble();
                    if (precoLido == null)
                    {
                        preco = 0; ;
                        return false;
                    }
                }
            }
            preco = (double)precoLido;
            return true;
        }

        public bool LerCategoria(string mensagemSolicitacao, string mensagemAviso, out Categoria categoria)
        {
            int? intLido = null;
            while (intLido == null || !Enum.IsDefined(typeof(Categoria), intLido))
            {
                Console.Write(mensagemSolicitacao);
                intLido = LerInt();
                if (intLido == null || !Enum.IsDefined(typeof(Categoria), intLido))
                {
                    Console.Write(mensagemAviso);
                    intLido = LerInt();
                    if (intLido == null)
                    {
                        categoria = 0; ;
                        return false;
                    }
                }
            }
            categoria = (Categoria)intLido;
            return true;
        }

        public bool LerInt(string mensagemSolicitacao, string mensagemAviso, out int id)
        {
            int? intLido = null;
            while (intLido == null)
            {
                Console.Write(mensagemSolicitacao);
                intLido = LerInt();
                if (intLido == null)
                {
                    Console.Write(mensagemAviso);
                    intLido = LerInt();
                    if (intLido == null)
                    {
                        id = 0; ;
                        return false;
                    }
                }
            }
            id = (int)intLido;
            return true;
        }
    }
}
