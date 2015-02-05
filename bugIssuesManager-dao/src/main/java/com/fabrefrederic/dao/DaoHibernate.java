package com.fabrefrederic.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fabrefrederic.dao.interfaces.GenericDao;

/**
 * Generic JPA DAO
 *
 * @param <T>
 */
@Repository
public abstract class DaoHibernate<T extends Serializable> implements GenericDao<T> {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(DaoHibernate.class);
	/** Class to persist */
	protected Class< T > entityBeanType;

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Default constructor
	 */
	public DaoHibernate() {}

	/**
	 * Constructor
	 */
	public DaoHibernate(Class<T> persistentClass) {
		super();
		this.entityBeanType = persistentClass;
	}

	@Override
	public void setClazz(final Class< T > classToSet){
		this.entityBeanType = classToSet;
	}

	@Override
	public Class<T> getEntityBeanType() {
		return entityBeanType;
	}

	@Override
	@Transactional
	public T findById(final Long id) {
		return this.entityManager.find(this.entityBeanType, id);
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List< T > findAll() {
		return this.entityManager.createQuery("from " + this.entityBeanType.getName()).getResultList();
	}

	@Override
	@Transactional
	public void save(final T entity) {
		try {
			this.entityManager.persist(entity);
		} catch (final Exception e) {
			LOGGER.error(e);
		}
	}

	@Override
	public void update(final T entity) {
		this.entityManager.merge(entity);
	}

	@Override
	public void delete(final T entity) {
		this.entityManager.remove(entity);
	}

	@Override
	public void deleteById(final Long entityId) {
		final T entity = this.findById(entityId);
		this.delete(entity);
	}

	@Override
	public void flush() {
		this.entityManager.flush();
	}

	/**
	 * @return the entityManager
	 */
	 public EntityManager getEntityManager() {
		 return entityManager;
	 }

	 /**
	  * @param entityManager the entityManager to set
	  */
	 public void setEntityManager(EntityManager entityManager) {
		 this.entityManager = entityManager;
	 }

}