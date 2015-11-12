using System;
using Locadora.Dominio.Repositorio;
using Locadora.Dominio.Servicos;
using Locadora.Infraestrutura.Servicos;
using Locadora.Repositorio.EF;

namespace Locadora.Web.MVC.Helpers
{
    public class FabricaDeModulos
    {
        public static IJogoRepositorio CriarJogoRepositorio()
        {
            return new JogoRepositorio();
        }

        public static IUsuarioRepositorio CriarUsuarioRepositorio()
        {
            return new UsuarioRepositorio();
        }

        public static IClienteRepositorio CriarClienteRepositorio()
        {
            return new ClienteRepositorio();
        }

        public static ISeloRepositorio CriarSeloRepositorio()
        {
            return new SeloRepositorio();
        }

        public static IServicoCriptografia CriarServicoCriptografia()
        {
            return new ServicoCriptografia();
        }

        public static ServicoAutenticacao CriarServicoAutenticacao()
        {
            return new ServicoAutenticacao(CriarUsuarioRepositorio(), CriarServicoCriptografia());
        }
    }
}