﻿using Microsoft.VisualStudio.TestTools.UnitTesting;
using Locadora.Dominio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;

namespace Locadora.Dominio.Tests
{
    [TestClass]
    public class BaseDeDadosTests
    {
        string caminho = Environment.CurrentDirectory + @"..\..\..\..\arquivos\game_store.xml";

        [TestMethod]
        public void PesquisarJogoPorNomeTest()
        {
            BaseDeDados dados = new BaseDeDados(caminho);
            IList<Jogo> atual = dados.PesquisarJogoPorNome("Contra III");
            Jogo esperado = new Jogo("Contra III", 31, "AVENTURA");
            esperado.Id = 17;
            Assert.AreEqual(esperado, atual[0]);
        }

        [TestMethod]
        public void CadastrarJogoTest()
        {
            BaseDeDados dados = new BaseDeDados(caminho);
            string nextId = dados.GetNextJogoId().ToString();
            Jogo esperado = new Jogo("Jogo de testar" + nextId, 23.99, "AVENTURA");
            dados.Cadastrar(esperado);
            Assert.AreEqual(esperado, dados.PesquisarJogoPorNome("Jogo de testar" + nextId)[0]);
        }

        [TestMethod]
        public void CadastrarClienteTest()
        {
            BaseDeDados dados = new BaseDeDados(caminho);
            string nextId = dados.GetNextClienteId().ToString();
            Cliente esperado = new Cliente("TestCli" + nextId);
            dados.Cadastrar(esperado);
            Cliente atual = new Cliente(XElement.Load(caminho)
                .Element("clientes").Elements()
                .First(cliente => cliente.Attribute("id").Value == nextId));
            Assert.AreEqual(esperado, atual);
        }

        [TestMethod]
        public void EditarJogoTest()
        {
            BaseDeDados dados = new BaseDeDados(caminho);
            Jogo esperado = dados.PesquisarJogoPorNome("Killer Instinct")[0];
            esperado.Preco = 19;
            dados.EditarJogo(esperado);
            Assert.AreEqual(esperado, dados.PesquisarJogoPorNome("Killer Instinct")[0]);
        }

        [TestMethod]
        public void PesquisarClientePorNomeTest()
        {
            BaseDeDados dados = new BaseDeDados(caminho);
            Cliente cliente = dados.PesquisarClientePorNome("usER")[1];
            Assert.AreEqual("User", cliente.Nome);
        }

        [TestMethod]
        public void JogoIsDisponivelTest()
        {
            BaseDeDados dados = new BaseDeDados(caminho);
            dados.SetJogoDisponivel(6, false);
            Assert.IsTrue(!dados.JogoIsDisponivel(6));
        }

        [TestMethod]
        public void SetJogoDisponivelTest()
        {
            BaseDeDados dados = new BaseDeDados(caminho);
            bool disponivel = dados.JogoIsDisponivel(16);
            dados.SetJogoDisponivel(16, !disponivel);
            Assert.AreEqual(!disponivel, dados.JogoIsDisponivel(16));
        }
    }
}