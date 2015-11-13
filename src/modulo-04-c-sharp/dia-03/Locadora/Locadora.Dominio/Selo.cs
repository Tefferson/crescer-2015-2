namespace Locadora.Dominio
{
    public class Selo : EntidadeBase
    {
        public string Nome { get; set; }
        public decimal Preco { get; set; }
        public int PrazoDevolucao { get; set; }
        
        public Selo() { }

        public override string ToString()
        {
            return Nome;
        }

        public override bool Equals(object obj)
        {
            if (obj != null && obj.GetType() == typeof(Selo))
            {
                Selo jogoComp = (Selo)obj;

                return this.Id == jogoComp.Id
                    && this.Nome == jogoComp.Nome
                    && this.Preco == jogoComp.Preco
                    && this.PrazoDevolucao == jogoComp.PrazoDevolucao;
            }

            return false;
        }

        public override int GetHashCode()
        {
            return base.GetHashCode();
        }
    }
}
