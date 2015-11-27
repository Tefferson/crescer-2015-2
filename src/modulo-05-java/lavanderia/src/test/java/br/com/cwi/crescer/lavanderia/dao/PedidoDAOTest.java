package br.com.cwi.crescer.lavanderia.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cwi.crescer.lavanderia.dao.PedidoDAO;
import br.com.cwi.crescer.lavanderia.domain.Cliente;
import br.com.cwi.crescer.lavanderia.domain.Item;
import br.com.cwi.crescer.lavanderia.domain.Pedido;
import br.com.cwi.crescer.lavanderia.domain.Pedido.SituacaoPedido;

public class PedidoDAOTest extends AbstractInfrastructureTest {

    @Autowired
    private PedidoDAO pedidoDAO;

    @Test
    public void deveBuscarPedidoPorIdEAtivo() throws Exception {
        Pedido pedido = pedidoDAO.findById(1L);
        Assert.assertNotNull(pedido);
        Assert.assertNotNull(pedido.getCliente());
    }

    @Test
    public void deveBuscarPedidosPendentes() throws Exception {
        List<Pedido> pedidos = pedidoDAO.findBySituacao(SituacaoPedido.PENDENTE);
        Assert.assertNotNull(pedidos);
        Assert.assertFalse(pedidos.isEmpty());

        for (Pedido pedido : pedidos) {
            Assert.assertEquals(SituacaoPedido.PENDENTE, pedido.getSituacao());
        }
    }
    
    @Test
    public void deveBuscarItens() throws Exception {
        List<Item> itens = pedidoDAO.findById(1L).getItens();
        Assert.assertNotNull(itens);
    }
    
    @Test
    public void deveBuscarCliente() throws Exception {
        Cliente cliente = pedidoDAO.findById(1L).getCliente();
        Assert.assertNotNull(cliente);
    }
}