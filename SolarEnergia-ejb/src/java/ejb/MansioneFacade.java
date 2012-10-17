/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Mansione;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
;

/**
 *
 * @author Balordo
 */
@Stateless
public class MansioneFacade extends AbstractFacade<Mansione> implements MansioneFacadeLocal {
    @PersistenceContext(unitName = "SolarEnergia-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MansioneFacade() {
        super(Mansione.class);
    }
    
    @Override
    public int count() {
        return super.count();
    }

    @Override
    public List<Mansione> findAll() {
        Query q = em.createNamedQuery("Mansione.findAll");
        return q.getResultList();
    }

    @Override
    public Mansione findByID(Integer id) {
        
        Query q = em.createNamedQuery("Mansione.findByID");
        q.setParameter("idmansione", id);
        try
        {
            return (Mansione) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }

    @Override
    public Mansione findByMansione(String man) {
        
        Query q = em.createNamedQuery("Mansione.findByMansione");
        q.setParameter("mansione", man);
        try
        {
            return (Mansione) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }
    
}
