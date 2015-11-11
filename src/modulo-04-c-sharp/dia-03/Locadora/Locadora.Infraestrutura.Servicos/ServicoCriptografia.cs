using Locadora.Dominio.Servicos;
using System.Text;

namespace Locadora.Infraestrutura.Servicos
{
    public class ServicoCriptografia : IServicoCriptografia
    {
        public string CriptografarSenha(string senha)
        {
            senha += "%$CWI_CRESCER_2015-2&%";
            return GerarMd5(senha);
        }

        private string GerarMd5(string texto)
        {
            System.Security.Cryptography.MD5 md5 = System.Security.Cryptography.MD5.Create();

            byte[] inputBytes = Encoding.ASCII.GetBytes(texto);
            byte[] hash = md5.ComputeHash(inputBytes);
            var stringBuilder = new StringBuilder();

            for (int i = 0; i < hash.Length; i++)
            {
                stringBuilder.Append(hash[i].ToString("X2"));
            }

            return stringBuilder.ToString();
        }
    }
}