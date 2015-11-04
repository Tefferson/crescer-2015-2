using System;
using Locadora.Dominio;
using Locadora.Dominio.Repositorio;

namespace Locadora.UI.CadastroDeJogo
{
    class MenuEditarJogo : MenuDeJogo
    {
        private Jogo jogoEmEdicao;

        public override string Titulo
        {
            get
            {
                return "Editar jogo " + jogoEmEdicao.Nome;
            }
        }

        public MenuEditarJogo(Jogo jogoParaEditar)
        {
            this.jogoEmEdicao = jogoParaEditar;
        }

        protected override void Iniciar()
        {
            Console.WriteLine(jogoEmEdicao);
            Console.WriteLine();

            AtualizarDadosDoJogo(jogoEmEdicao);
            SalvarJogo(jogoEmEdicao);
        }

        protected override void SalvarJogo(Jogo jogo)
        {
            IJogoRepositorio jogoRepositorio = JogoRepositorio;
            jogoRepositorio.Atualizar(jogo);

            ImprimirMensagem("Jogo salvo com sucesso.");
        }
    }
}
