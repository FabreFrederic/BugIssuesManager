package com.fabrefrederic.dao.persistance;

import org.springframework.stereotype.Component;

import com.fabrefrederic.dao.persistance.interfaces.ModeleDao;
import com.fabrefrederic.metier.implementationTest.Modele;

/**
 * Modele JPA DAO
 *
 * @author Frederic Fabre
 *
 */
@Component("modeleDao")
public class ModeleJpaDao extends GenericJpaDao<Modele> implements ModeleDao {
	/** UID */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 *
	 * @param persistentClass
	 */
	public ModeleJpaDao(Class<Modele> persistentClass) {
		super(persistentClass);
	}

	/**
	 * Default constructor
	 */
	public ModeleJpaDao() {

	}
}
