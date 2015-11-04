using Locadora.Dominio.Repositorio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Locadora.Dominio;
using System.Xml.Linq;

namespace Locadora.Repositorio.XML
{
    public class JogoRepositorio : RepositorioBase, IJogoRepositorio
    {
        protected override string NomeArquivoXml
        {
            get
            {
                return "db_jogos.xml";
            }
        }

        public int Atualizar(Jogo entidade)
        {
            string idBusca = entidade.Id.ToString();
            XElement db = CarregarBaseXml();
            XElement jogoSalvo = db.Elements("jogo")
                                   .First(j => j.Attribute("id").Value == idBusca);

            jogoSalvo.SetElementValue("nome", entidade.Nome);
            jogoSalvo.SetElementValue("preco", entidade.Preco);
            jogoSalvo.SetElementValue("categoria", entidade.Categoria.ToString());
            jogoSalvo.SetElementValue("id_cliente_locacao", entidade.IdClienteLocacao.HasValue ? entidade.IdClienteLocacao.Value.ToString() : "");

            SalvarDbXml(db);
            return 1;
        }

        public Jogo BuscarPorId(int id)
        {
            IEnumerable<XElement> dbJogos = CarregarBaseXml().Elements("jogo");
            string idPesquisa = id.ToString();

            XElement jogoEncontrado = dbJogos.FirstOrDefault(j => j.Attribute("id").Value == idPesquisa);

            return ConverterXmlEmJogo(jogoEncontrado);
        }

        public IList<Jogo> BuscarPorNome(string nome)
        {
            IEnumerable<XElement> dbJogos = CarregarBaseXml().Elements("jogo");

            dbJogos = dbJogos.Where(jogo => jogo.Element("nome").Value.IndexOf(nome, StringComparison.InvariantCultureIgnoreCase) >= 0);

            return ConverterListaXmlEmEntidades(dbJogos).ToList();
        }

        public IList<Jogo> BuscarTodos()
        {
            IEnumerable<XElement> dbJogos = CarregarBaseXml().Elements("jogo");
            return ConverterListaXmlEmEntidades(dbJogos).ToList();
        }

        public int Criar(Jogo entidade)
        {
            int novoId = BuscarUltimoIdInserido() + 1;

            XElement jogoXml = ConverterJogoEmXml(entidade);
            XElement db = CarregarBaseXml();
            db.Add(jogoXml);

            SalvarDbXml(db);
            return 1;
        }

        public int Excluir(int id)
        {
            XElement dbJogos = CarregarBaseXml();
            string idComparar = id.ToString();
            int registrosEncontrados = 0;

            XElement jogoASerExcluido = dbJogos.Elements("jogo").FirstOrDefault(jogo => jogo.Attribute("id").Value == idComparar);

            if(jogoASerExcluido != null)
            {
                registrosEncontrados = 1;
                jogoASerExcluido.Remove();
                SalvarDbXml(dbJogos);
            }

            return registrosEncontrados;
        }

        private IEnumerable<Jogo> ConverterListaXmlEmEntidades(IEnumerable<XElement> dbJogos)
        {
            foreach (XElement j in dbJogos)
            {
                yield return ConverterXmlEmJogo(j);
            }
        }
        
        private XElement ConverterJogoEmXml(Jogo jogo)
        {
            XElement jogoXml = new XElement("jogo");
            jogoXml.SetAttributeValue("id", jogo.Id);
            jogoXml.SetElementValue("nome", jogo.Nome);
            jogoXml.SetElementValue("preco", jogo.Preco);
            jogoXml.SetElementValue("categoria", jogo.Categoria.ToString());
            jogoXml.SetElementValue("id_cliente_locacao", jogo.IdClienteLocacao.HasValue ? jogo.IdClienteLocacao.Value.ToString() : "");

            return jogoXml;
        }

        private Jogo ConverterXmlEmJogo(XElement jogoXml)
        {
            if(jogoXml == null)
            {
                return null;
            }

            var jogo = new Jogo(
                    id: Convert.ToInt32(jogoXml.Attribute("id").Value),
                    idClienteLocacao: jogoXml.Element("id_cliente_locacao").Value.ToNullable<int>()
                    );

            jogo.Nome = jogoXml.Element("nome").Value;
            jogo.Preco = Convert.ToDecimal(jogoXml.Element("preco").Value);
            jogo.Categoria = ConverterXmlCategoriaEmEnum(jogoXml.Element("categoria"));
            
            return jogo;
        }

        private Categoria ConverterXmlCategoriaEmEnum(XElement categoriaXml)
        {
            string valorXml = categoriaXml.Value;
            return (Categoria)Enum.Parse(typeof(Categoria), valorXml);
        }
    }
}
