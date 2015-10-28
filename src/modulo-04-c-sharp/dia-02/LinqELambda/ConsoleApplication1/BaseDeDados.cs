using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

namespace ConsoleApplication1
{
    public class BaseDeDados
    {
        public List<Funcionario> Funcionarios
        {
            get; private set;
        }

        public BaseDeDados()
        {
            CriarBase();
        }

        private void CriarBase()
        {
            Funcionarios = new List<Funcionario>();

            Cargo desenvolvedor = new Cargo("Desenvolvedor", 190);
            Cargo analista = new Cargo("Analista", 250);
            Cargo gerente = new Cargo("Gerente", 550.5);

            Funcionario lucasLeal = new Funcionario(1, "Lucas Leal", new DateTime(1995, 01, 24));
            lucasLeal.Cargo = desenvolvedor;
            lucasLeal.TurnoTrabalho = TurnoTrabalho.Manha;
            Funcionarios.Add(lucasLeal);

            Funcionario jeanPinzon = new Funcionario(2, "Jean Pinzon", new DateTime(1991, 04, 25));
            jeanPinzon.Cargo = desenvolvedor;
            jeanPinzon.TurnoTrabalho = TurnoTrabalho.Tarde;
            Funcionarios.Add(jeanPinzon);

            Funcionario rafaelBenetti = new Funcionario(3, "Rafael Benetti", new DateTime(1991, 08, 15));
            rafaelBenetti.Cargo = desenvolvedor;
            rafaelBenetti.TurnoTrabalho = TurnoTrabalho.Noite;
            Funcionarios.Add(rafaelBenetti);

            Funcionario mauricioBorges = new Funcionario(4, "Maurício Borges", new DateTime(1996, 11, 30));
            mauricioBorges.Cargo = desenvolvedor;
            mauricioBorges.TurnoTrabalho = TurnoTrabalho.Manha;
            Funcionarios.Add(mauricioBorges);

            Funcionario leandroAndreolli = new Funcionario(5, "Leandro Andreolli", new DateTime(1990, 03, 07));
            leandroAndreolli.Cargo = desenvolvedor;
            leandroAndreolli.TurnoTrabalho = TurnoTrabalho.Manha;
            Funcionarios.Add(leandroAndreolli);

            Funcionario felipeNervo = new Funcionario(6, "Felipe Nervo", new DateTime(1994, 01, 12));
            felipeNervo.Cargo = desenvolvedor;
            felipeNervo.TurnoTrabalho = TurnoTrabalho.Tarde;
            Funcionarios.Add(felipeNervo);

            Funcionario lucasKauer = new Funcionario(7, "Lucas Kauer", new DateTime(1997, 05, 10));
            lucasKauer.Cargo = desenvolvedor;
            lucasKauer.TurnoTrabalho = TurnoTrabalho.Noite;
            Funcionarios.Add(lucasKauer);

            Funcionario eduardoArnold = new Funcionario(8, "Eduardo Arnold", new DateTime(1989, 09, 16));
            eduardoArnold.Cargo = desenvolvedor;
            eduardoArnold.TurnoTrabalho = TurnoTrabalho.Tarde;
            Funcionarios.Add(eduardoArnold);

            Funcionario gabrielAlboy = new Funcionario(9, "Gabriel Alboy", new DateTime(1990, 02, 25));
            gabrielAlboy.Cargo = analista;
            gabrielAlboy.TurnoTrabalho = TurnoTrabalho.Manha;
            Funcionarios.Add(gabrielAlboy);

            Funcionario carlosHenrique = new Funcionario(10, "Carlos Henrique", new DateTime(1965, 12, 02));
            carlosHenrique.Cargo = analista;
            carlosHenrique.TurnoTrabalho = TurnoTrabalho.Tarde;
            Funcionarios.Add(carlosHenrique);

            Funcionario margareteRicardo = new Funcionario(11, "Margarete Ricardo", new DateTime(1980, 10, 10));
            margareteRicardo.Cargo = gerente;
            margareteRicardo.TurnoTrabalho = TurnoTrabalho.Manha;
            Funcionarios.Add(margareteRicardo);
        }

        //A
        public IList<Funcionario> OrdenadosPorCategoria()
        {
            return Funcionarios.OrderBy(funcionario => funcionario.Cargo.Titulo).ToList();
        }

        //B
        public IList<Funcionario> BuscarPorNome(string nome)
        {
            return Funcionarios.Where(funcionario => funcionario.Nome.Contains(nome))
                .OrderBy(funcionario => funcionario.Nome)
                .ToList();
        }

        //C
        public IList<dynamic> BuscaRapida()
        {
            IEnumerable<dynamic> query = from funcionario in Funcionarios
                                         select new
                                         {
                                             Nome = funcionario.Nome,
                                             TituloCargo = funcionario.Cargo.Titulo
                                         };
            return query.ToList();
        }

        //D
        public IList<Funcionario> BuscarPorTurno(params TurnoTrabalho[] turnos)
        {
            if (turnos.Count() > 0)
            {
                var turnosUnicos = turnos.GroupBy(turno => turno);
                var query = from turno in turnosUnicos
                            join funcionario in Funcionarios
                            on turno.Key equals funcionario.TurnoTrabalho
                            select funcionario;
                return query.ToList();
            }
            return new List<Funcionario>();
        }

        //E
        public IList<dynamic> QtdFuncionariosPorTurno()
        {
            IEnumerable<dynamic> qtdFuncionariosPorTurno = Funcionarios
                .GroupBy(funcionario => funcionario.TurnoTrabalho)
                .Select(grupo => new
                {
                    TurnoTrabalho = grupo.Key,
                    Count = grupo.Sum(x => 1)
                }).ToList();
            return qtdFuncionariosPorTurno.ToList();
        }

        //F
        public IList<Funcionario> BuscarPorCargo(Cargo cargo)
        {
            return Funcionarios.Where(funcionario => funcionario.Cargo.Equals(cargo)).ToList();
        }

        //G
        public IList<Funcionario> FiltrarPorIdadeAproximada(int idade)
        {
            DateTime dataReferencia = DateTime.Now.AddYears(-idade);
            DateTime menos5Anos = dataReferencia.AddYears(-5);
            DateTime mais5Anos = dataReferencia.AddYears(5);
            return Funcionarios.Where(funcionario => funcionario.DataNascimento > menos5Anos
            && funcionario.DataNascimento < mais5Anos).ToList();
        }

        //H
        public double SalarioMedio(TurnoTrabalho? turno)
        {
            IList<Funcionario> filtradosPorTurno;
            if (turno.HasValue)
            {
                filtradosPorTurno = BuscarPorTurno(turno.Value);
            }
            else
            {
                TurnoTrabalho[] turnos = { TurnoTrabalho.Manha, TurnoTrabalho.Tarde, TurnoTrabalho.Noite };
                filtradosPorTurno = BuscarPorTurno(turnos);
            }
            int count = filtradosPorTurno.Count;
            return filtradosPorTurno.Sum(funcionario => funcionario.Cargo.Salario) / count;
        }

        //I
        public IList<Funcionario> AniversariantesDoMes()
        {
            int mesAtual = DateTime.Now.Month;
            return Funcionarios.Where(funcionario => funcionario.DataNascimento.Month == mesAtual).ToList();
        }

        //J
        public dynamic FuncionarioMaisComplexo()
        {
            string padrao = "[b-df-hj-np-tv-zB-DF-HJ-NP-TV-Z]";
            int maiorReincidenciaDeConasoantes = Funcionarios.Max(funcionario => Regex.Matches(funcionario.Nome, @padrao).Count);

            Funcionario funcionarioComplexo = Funcionarios.First(funcionario => Regex
            .Matches(funcionario.Nome, @padrao)
            .Count == maiorReincidenciaDeConasoantes);

            double salario = funcionarioComplexo.Cargo.Salario;
            return new
            {
                Nome = funcionarioComplexo.Nome,
                SalarioRS = "R$" + String.Format("{0:0.00}", salario),
                SalarioUS = "U$" + String.Format("{0:0.00}", salario)
            };
        }
    }
}