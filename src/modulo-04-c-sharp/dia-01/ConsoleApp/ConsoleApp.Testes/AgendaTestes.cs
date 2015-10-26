using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace ConsoleApp.Testes
{
    [TestClass]
    public class AgendaTestes
    {
        [TestMethod]
        public void AgendaTemUmContato()
        {
            var agenda = new Agenda();
            var contato = new Contato("Tefferson", 987654);

            agenda.AdicionarContato(contato);

            Assert.AreEqual(agenda.QuantidadeContatos, 1);
        }

        [TestMethod]
        public void AgendaNaoEstaOrdenadaPorNome()
        {
            var agenda = new Agenda();
            var contato1 = new Contato("Tefferson", 987654);
            var contato2 = new Contato("ATefferson", 987654);
            var contato3 = new Contato("ETefferson", 987654);
            var esperado = "Tefferson-987654\nATefferson-987654\nETefferson-987654\n";

            agenda.AdicionarContato(contato1);
            agenda.AdicionarContato(contato2);
            agenda.AdicionarContato(contato3);

            Assert.AreEqual(esperado, agenda.ListarContatos());
        }

        [TestMethod]
        public void AgendaEstaOrdenadaPorNome()
        {
            var agenda = new Agenda();
            var contato1 = new Contato("Tefferson", 987654);
            var contato2 = new Contato("ATefferson", 987654);
            var contato3 = new Contato("ETefferson", 987654);
            var esperado = "ATefferson-987654\nETefferson-987654\nTefferson-987654\n";

            agenda.AdicionarContato(contato1);
            agenda.AdicionarContato(contato2);
            agenda.AdicionarContato(contato3);

            Assert.AreEqual(esperado, agenda.ListarContatosOrdenadosPorNome());
        }

        [TestMethod]
        public void AgendaRemoveTodosOsContatosComNomeTeste()
        {
            var agenda = new Agenda();
            var contato1 = new Contato("Teste", 987654);
            var contato2 = new Contato("Tefferson", 987654);
            var contato3 = new Contato("Teste", 987654);
            var esperado = "Tefferson-987654\n";

            agenda.AdicionarContato(contato1);
            agenda.AdicionarContato(contato2);
            agenda.AdicionarContato(contato3);
            agenda.RemoverContatosPorNome("Teste");

            Assert.AreEqual(esperado, agenda.ListarContatos());
        }

        [TestMethod]
        public void AgendaRemoveTodosOsContatosComNumeroNove()
        {
            var agenda = new Agenda();
            var contato1 = new Contato("Teste", 9);
            var contato2 = new Contato("Tefferson", 987654);
            var contato3 = new Contato("Teste", 9);
            var esperado = "Tefferson-987654\n";

            agenda.AdicionarContato(contato1);
            agenda.AdicionarContato(contato2);
            agenda.AdicionarContato(contato3);
            agenda.RemoverContatosPorNumero(9);

            Assert.AreEqual(esperado, agenda.ListarContatos());
        }
    }
}
