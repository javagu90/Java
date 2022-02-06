package com.alidasoftware.pos.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaQuery;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.util.JpaUtil;

public class GenericDao<T> {

	private EntityManager em;

	private Class<T> entityClass;

	public void beginTransaction() {
		em = JpaUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
	}

	public void commit() {
		em.getTransaction().commit();
	}

	public void rollback() {
		em.getTransaction().rollback();
	}

	public void closeTransaction() {
		if (em != null && em.isOpen()) {
			em.close();
		}
	}

	public void commitAndCloseTransaction() {
		commit();
		closeTransaction();
	}

	public void flush() {
		em.flush();
	}

	public void joinTransaction() {
		em = JpaUtil.getEntityManagerFactory().createEntityManager();
		em.joinTransaction();
	}
	
	public GenericDao() {
		
	}

	public GenericDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public T save(T entity) throws AlidaPosException {
		em.persist(entity);
		return entity;
	}

	protected void delete(Object id, Class<T> classe) throws AlidaPosException {
		T entityToBeRemoved = em.getReference(classe, id);
		em.remove(entityToBeRemoved);
	}

	public T update(T entity) throws AlidaPosException {
		return em.merge(entity);
	}

	public T find(int entityID) {
		return em.find(entityClass, entityID);
	}

	public T findReferenceOnly(int entityID) {
		return em.getReference(entityClass, entityID);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getListResult(String namedQuery, Map<String, Object> parameters) throws AlidaPosException {
		List<T> result = null;
		try {
			Query query = em.createNamedQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			result = (List<T>) query.getResultList();
		} catch (NoResultException e) {
			System.out.println("tiro no result exception");
			return null;
			//throw new AlidaPosException("No existen registros." + entityClass.toString());
		} catch (Exception e) {
			System.out.println("tiro el error :  " + e.getMessage());
			throw new AlidaPosException("Error al ejecutar Consulta." + entityClass.toString() + "\n" + e.getMessage());
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getListResultQuery(String queryString, Map<String, Object> parameters) throws AlidaPosException {
		List<T> result = null;
		try {
			//Query query = em.createNamedQuery(namedQuery);
			Query query = em.createQuery(queryString);
			
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			result = (List<T>) query.getResultList();
		} catch (NoResultException e) {
			System.out.println("tiro no result exception");
			return null;
			//throw new AlidaPosException("No existen registros." + entityClass.toString());
		} catch (Exception e) {
			System.out.println("tiro el error :  " + e.getMessage());
			throw new AlidaPosException("Error al ejecutar Consulta." + entityClass.toString() + "\n" + e.getMessage());
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findAll() throws AlidaPosException {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		Query query = em.createQuery(cq);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	protected T findOneResult(String namedQuery, Map<String, Object> parameters) throws AlidaPosException {
		T result = null;
		try {
			Query query = em.createNamedQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			result = (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
			//throw new AlidaPosException("No existen registros." + entityClass.toString());
		} catch (Exception e) {
			throw new AlidaPosException("Error al ejecutar Consulta." + entityClass.toString() + "\n" + e.getMessage());
		}
		//System.out.println("findOneResult : " + result.getClass().toString());
		return result;
	}
	
	@SuppressWarnings("unchecked")
	protected T findOneResultQuery(String queryString, Map<String, Object> parameters) throws AlidaPosException {
		T result = null;
		try {
			//Query query = em.createNamedQuery(namedQuery);
			Query query = em.createQuery(queryString);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			result = (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
			//throw new AlidaPosException("No existen registros." + entityClass.toString());
		} catch (Exception e) {
			throw new AlidaPosException("Error al ejecutar Consulta." + entityClass.toString() + "\n" + e.getMessage());
		}
		//System.out.println("findOneResult : " + result.getClass().toString());
		return result;
	}
	
	private void populateQueryParameters(Query query, Map<String, Object> parameters) {
		for (Entry<String, Object> entry : parameters.entrySet()) {
			if (entry.getValue() instanceof Date) {
				query.setParameter(entry.getKey(), (Date) entry.getValue(), TemporalType.DATE);
			} else {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
	}
	
	public Throwable getLastThrowable(Exception e) {
		Throwable t = null;
		for (t = e.getCause(); t.getCause() != null; t = t.getCause());
		return t;
	}
}
