package br.com.cwi.crescer.lavanderia.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class DAO {

	@PersistenceContext
	protected EntityManager em;
}