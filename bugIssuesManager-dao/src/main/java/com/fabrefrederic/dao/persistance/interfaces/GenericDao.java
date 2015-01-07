/**
 *
 */
package com.fabrefrederic.dao.persistance.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * Generic interface DAO
 *
 * @author frederic.fabre
 * @param <T> Entity
 * @param <ID> Entity id
 */
public interface GenericDao<T> extends Serializable {

	/**
	 * Gets the class type to manage
	 *
	 * @return
	 */
	Class<T> getEntityBeanType();

	/**
	 * Sets the class type to manage
	 *
	 * @param classToSet
	 */
	void setClazz(final Class< T > classToSet);

	/**
	 * Search for an entity by its id
	 *
	 * @param id
	 * @return
	 */
	T findById(final Long id);

	/**
	 * Search for all the entities
	 *
	 * @return
	 */
	List< T > findAll();

	/**
	 * Save an entity
	 *
	 * @param entity
	 */
	void save(final T entity);

	/**
	 * Update an entity
	 *
	 * @param entity
	 */
	void update(final T entity);

	/**
	 * Deletes an entity
	 *
	 * @param entity
	 */
	void delete(final T entity);

	/**
	 * Deletes an entity by its id
	 *
	 * @param entityId
	 */
	void deleteById(final Long entityId);

	/**
	 * Flush
	 */
	void flush();
}