﻿using Microsoft.VisualStudio.TestTools.UnitTesting;
using ConsoleApplication1;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication1.Tests
{
    [TestClass]
    public class BaseDeDadosTests
    {
        [TestMethod]
        public void BaseDeDadosTest()
        {
            BaseDeDados dados = new BaseDeDados();
            Assert.AreEqual(11, dados.Funcionarios.Count);
        }

        [TestMethod]
        public void OrdenadosPorCategoriaTest()
        {
            BaseDeDados dados = new BaseDeDados();
            IList<Funcionario> funcionariosPorCategoria = dados.OrdenadosPorCategoria();
            Assert.AreEqual("Analista", funcionariosPorCategoria[0].Cargo.Titulo);
            Assert.AreEqual("Gerente", funcionariosPorCategoria[dados.Funcionarios.Count - 1].Cargo.Titulo);
        }

        [TestMethod]
        public void BuscarPorNomeTest()
        {
            BaseDeDados dados = new BaseDeDados();
            Funcionario funcionario = dados.BuscarPorNome("Margarete Ricardo")[0];
            Assert.AreEqual("Margarete Ricardo", funcionario.Nome);
        }

        [TestMethod]
        public void BuscaRapidaTest()
        {
            BaseDeDados dados = new BaseDeDados();
            var funcionarios = dados.BuscaRapida();
            Assert.AreEqual(11, funcionarios.Count);
        }

        [TestMethod]
        public void BuscarPorTurnoSemParametroTest()
        {
            BaseDeDados dados = new BaseDeDados();
            IList<Funcionario> funcionarios = dados.BuscarPorTurno();
            Assert.AreEqual(0, funcionarios.Count);
        }

        [TestMethod]
        public void BuscarNoTurnoDaNoiteTest()
        {
            BaseDeDados dados = new BaseDeDados();
            IList<Funcionario> funcionarios = dados.BuscarPorTurno(TurnoTrabalho.Noite);
            Assert.AreEqual(2, funcionarios.Count);
        }

        [TestMethod]
        public void QtdFuncionariosPorTurnoTest()
        {
            BaseDeDados dados = new BaseDeDados();
            string esperadoManha = "{ TurnoTrabalho = Manha, Count = 5 }";
            string esperadoTarde = "{ TurnoTrabalho = Tarde, Count = 4 }";
            string esperadoNoite = "{ TurnoTrabalho = Noite, Count = 2 }";
            IList<dynamic> atual = dados.QtdFuncionariosPorTurno();
            Assert.AreEqual(esperadoManha, atual[0].ToString());
            Assert.AreEqual(esperadoTarde, atual[1].ToString());
            Assert.AreEqual(esperadoNoite, atual[2].ToString());
        }

        [TestMethod]
        public void BuscarPorCargoTest()
        {
            BaseDeDados dados = new BaseDeDados();
            IList<Funcionario> funcionarios = dados.BuscarPorCargo(new Cargo("Gerente", 550.5));
            Assert.AreEqual("Margarete Ricardo", funcionarios[0].Nome);
            Assert.AreEqual(1, funcionarios.Count);
        }

        [TestMethod]
        public void FiltrarPorIdadeAproximadaTest()
        {
            BaseDeDados dados = new BaseDeDados();
            Funcionario margarete = dados.BuscarPorNome("Margarete Ricardo")[0];
            Funcionario atual = dados.FiltrarPorIdadeAproximada(40)[0];
            Assert.AreEqual(margarete, atual);
        }

        [TestMethod]
        public void SalarioMedioDeTodosOsFuncionariosTest()
        {
            BaseDeDados dados = new BaseDeDados();
            double esperado = 233.68;
            double atual = dados.SalarioMedio(null);
            Assert.AreEqual(esperado, atual, 0.2);
        }

        [TestMethod]
        public void SalarioMedioDoTurnoDaNoiteTest()
        {
            BaseDeDados dados = new BaseDeDados();
            double esperado = 190.0;
            double atual = dados.SalarioMedio(TurnoTrabalho.Noite);
            Assert.AreEqual(esperado, atual);
        }

        [TestMethod]
        public void AniversariantesDoMesTest()
        {
            BaseDeDados dados = new BaseDeDados();
            Funcionario funcionario = dados.AniversariantesDoMes()[0];
            Assert.AreEqual("Margarete Ricardo", funcionario.Nome);
        }

        [TestMethod]
        public void FuncionarioMaisComplexoTest()
        {
            BaseDeDados dados = new BaseDeDados();
            string esperado = new
            {
                Nome = "Leandro Andreolli",
                SalarioRS = "R$190,00",
                SalarioUS = "U$190,00"
            }.ToString();

            string atual = dados.FuncionarioMaisComplexo().ToString();
            Assert.AreEqual(esperado, atual);
        }
    }
}