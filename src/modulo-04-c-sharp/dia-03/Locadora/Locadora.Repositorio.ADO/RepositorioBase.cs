using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Locadora.Repositorio.ADO
{
    public abstract class RepositorioBase
    {
        internal IDbConnection CriarConexao()
        {
            return new SqlConnection(ConfigurationManager.ConnectionStrings["LOCADORA"].ConnectionString);
        }
    }
}
