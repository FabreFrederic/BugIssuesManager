/**
 *
 */
package com.fabrefrederic.service.implementationHibernate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabrefrederic.dao.persistance.interfaces.ModeleDao;
import com.fabrefrederic.metier.implementationTest.Marque;
import com.fabrefrederic.metier.implementationTest.Modele;
import com.fabrefrederic.service.interfaces.IModeleService;

/**
 * @author frederic.fabre
 * 
 */
@Service
@Component
public class ModeleService implements IModeleService, ApplicationEventPublisherAware {
    @Autowired
    private ModeleDao modeleDao;

    /** Event publisher */
    private ApplicationEventPublisher applicationEventPublisher;

    /*
     * (non-Javadoc)
     *
     * @see com.hibernateMaven01.service.interfaces.IModeleService#
     * recupererListeModelesParMarque(com.hibernateMaven01.metier.Marque)
     *
     * Retourne la liste des modeles de la marque choisie
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Modele> findListeModelesByMarque(final Marque marque) {
        final List<Modele> liste = new ArrayList<Modele>();
        // Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
        // Modele.class);
        // criteria.add(Restrictions.isNotEmpty("marque"));
        // criteria.add(Restrictions.eq("marque", marque));
        // liste = criteria.list();
        return liste;
    }

    /**
     * Sauvegarde ou met � jour un mod�le
     * 
     * @param object : objet � sauvegarder ou � mettre � jour
     */
    @Override
    @Transactional
    public void save(final Modele modele) {
        // Transaction transaction =
        // ((GenericHibernateDao<Modele,
        // Serializable>)this.modeleDao).getHibernateTemplate().getSessionFactory().getCurrentSession().getTransaction();
        // transaction.begin();
        // this.modeleDao.saveOrUpdate(modele);
        // // Creation de l'evenement
        // ModeleSaveOrUpdateEvent modeleSaveOrUpdateEvent = new ModeleSaveOrUpdateEvent(this, modele);
        // // Publication de l'evenement
        // this.applicationEventPublisher.publishEvent(modeleSaveOrUpdateEvent);
        // transaction.commit();
        modeleDao.save(modele);

    }

    /*
     * (non-Javadoc)
     * @see org.springframework.context.ApplicationEventPublisherAware#setApplicationEventPublisher(org.springframework.context.ApplicationEventPublisher)
     */
    /**
     * L'interface ApplicationEventPublisherAware d�finit un accesseur pour avoir acces a l'emetteur d'evenements
     */
    @Override
    public void setApplicationEventPublisher(final ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * @param modeleDao the modeleDao to set
     */
    public void setModeleDao(final ModeleDao modeleDao) {
        this.modeleDao = modeleDao;
    }

}
