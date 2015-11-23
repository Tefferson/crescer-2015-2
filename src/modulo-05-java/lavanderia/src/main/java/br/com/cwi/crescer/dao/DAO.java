package br.com.cwi.crescer.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class DAO {

	@PersistenceContext
	protected EntityManager em;
}