using System;
using System.Collections.Generic;
using Locadora.Dominio.Repositorio;
using System.Linq;

namespace Locadora.Dominio.Test.Mocks
{
    class JogoRepositorioMock : IJogoRepositorio
    {
        public int Atualizar(Jogo jogo)
        {
            Jogo jogoParaAtualizar = Db().FirstOrDefault(j => j.Id == jogo.Id);

            bool atualizouJogo = jogoParaAtualizar != null;

            return atualizouJogo ? 1 : 0;
        }

        public IList<Jogo> BuscarDisponiveis()
        {
            return Db().Where(j => j.Disponivel).ToList();
        }

        public IList<Jogo> BuscarIndisponiveis()
        {
            return Db().Where(j => !j.Disponivel).ToList();
        }

        public Jogo BuscarPorId(int id)
        {
            return Db().FirstOrDefault(j => j.Id == id);
        }

        public IList<Jogo> BuscarPorNome(string nome)
        {
            return Db().Where(j => j.Nome.Contains(nome)).ToList();
        }

        public IList<Jogo> BuscarTodos()
        {
            return Db();
        }

        public int Criar(Jogo jogo)
        {
            return Db().Any(j => j.Id == jogo.Id) ? 1 : 0;
        }

        public int Excluir(int id)
        {
            return Db().Any(j => j.Id == id) ? 1 : 0;
        }

        private IList<Jogo> Db()
        {
            IList<Jogo> jogos = new List<Jogo>();

            Jogo jogo1 = new Jogo(1)
            {
                Video = "v",
                Imagem = "i",
                IdSelo = 0,
                Categoria = Categoria.AVENTURA,
                Descricao = "Ótimo jogo!",
                Nome = "Jogo do ano",
                Selo = new Selo()
                {
                    Nome = "Platina",
                    PrazoDevolucao = 2,
                    Preco = 25m
                },
                Disponivel = false
            };

            Jogo jogo2 = new Jogo(2)
            {
                Video = "v",
                Imagem = "i",
                IdSelo = 0,
                Categoria = Categoria.CORRIDA,
                Descricao = "Ótimo jogo!!!",
                Nome = "Jogo do ano!!!",
                Selo = new Selo()
                {
                    Nome = "Platina V",
                    PrazoDevolucao = 1,
                    Preco = 20m
                },
                Disponivel = true
            };

            jogos.Add(jogo1);
            jogos.Add(jogo2);

            return jogos;
        }
    }
}
