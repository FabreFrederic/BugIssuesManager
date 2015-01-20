package com.fabrefrederic.service.interfaces;

import java.util.List;

import com.fabrefrederic.metier.implementationTest.Marque;
import com.fabrefrederic.metier.implementationTest.Modele;

/**
 * Modele Service
 * 
 * @author frederic.fabre
 * 
 */
public interface IModeleService {

    public List<Modele> findListeModelesByMarque(Marque marque);

    public void save(Modele modele);
}
