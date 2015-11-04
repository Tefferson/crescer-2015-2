using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace System.Data
{
    internal static class ExtensoesIDbCommand
    {
        public static void AddParam(this IDbCommand comando, string nome, object value)
        {
            IDbDataParameter parametro = comando.CreateParameter();
            parametro.ParameterName = nome;
            parametro.Value = value ?? DBNull.Value;

            comando.Parameters.Add(parametro);
        }
    }
}
