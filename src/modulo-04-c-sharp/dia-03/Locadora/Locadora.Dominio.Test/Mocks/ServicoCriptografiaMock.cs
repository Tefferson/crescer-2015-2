using Locadora.Dominio.Servicos;

namespace Locadora.Dominio.Test.Mocks
{
    class ServicoCriptografiaMock : IServicoCriptografia
    {
        public const string SALT = "$CWI";

        public string CriptografarSenha(string senha)
        {
            return senha + SALT;
        }
    }
}