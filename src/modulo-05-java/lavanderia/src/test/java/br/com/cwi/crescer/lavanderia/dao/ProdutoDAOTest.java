package br.com.cwi.crescer.lavanderia.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cwi.crescer.dao.ProdutoDAO;
import br.com.cwi.crescer.domain.Material;
import br.com.cwi.crescer.domain.Produto;
import br.com.cwi.crescer.domain.Servico;

public class ProdutoDAOTest extends AbstractInfrastructureTest {

    @Autowired
    private ProdutoDAO produtoDAO;

    @Test
    public void deveBuscarProdutoPorId() throws Exception {
        Produto produto = produtoDAO.findById(1L);
        Assert.assertNotNull(produto);
    }
    
    @Test
    public void deveBuscarMaterial() throws Exception {
        Material material = produtoDAO.findById(1L).getMaterial();
        Assert.assertNotNull(material);
    }
    
    @Test
    public void deveBuscarServico() throws Exception {
    	Servico servico = produtoDAO.findById(1L).getServico();
        Assert.assertNotNull(servico);
    }
}