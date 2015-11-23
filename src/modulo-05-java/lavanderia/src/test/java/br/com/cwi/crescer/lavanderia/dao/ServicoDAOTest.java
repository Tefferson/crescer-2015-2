package br.com.cwi.crescer.lavanderia.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cwi.crescer.dao.ServicoDAO;
import br.com.cwi.crescer.domain.Servico;

public class ServicoDAOTest extends AbstractInfrastructureTest {

    @Autowired
    private ServicoDAO servicoDAO;

    @Test
    public void deveBuscarServicoPorId() throws Exception {
        Servico servico = servicoDAO.findById(1L);
        Assert.assertNotNull(servico);
    }
}