/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Fornitore;
import entities.Ordinepannelli;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Balordo
 */
@Stateless
public class OrdinepannelliFacade extends AbstractFacade<Ordinepannelli> implements OrdinepannelliFacadeLocal {
    @PersistenceContext(unitName = "SolarEnergia-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdinepannelliFacade() {
        super(Ordinepannelli.class);
    }

    @Override
    public int count() {
        return super.count();
    }

    @Override
    public List<Ordinepannelli> findAll() {
        Query q = em.createNamedQuery("Ordinepannelli.findAll");
        return q.getResultList();
    }
    
    @Override
    public Ordinepannelli findByID(Integer id) {
        
        Query q = em.createNamedQuery("Ordinepannelli.findByID");
        q.setParameter("idordine", id);
        try
        {
            return (Ordinepannelli) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }

    @Override
    public List<Ordinepannelli> findByFornitore(Fornitore f) {
        
        Query q = em.createNamedQuery("Ordinepannelli.findByFornitore");
        q.setParameter("fornitore", f);
        return q.getResultList();
    }

    @Override
    public List<Ordinepannelli> findByDataOrdine(Date dataMin, Date dataMax) {
        Query q = em.createNamedQuery("Ordinepannelli.findByDataOrdine");
        q.setParameter("dataMin", dataMin);
        q.setParameter("dataMax", dataMax);
        return q.getResultList();
    }

    @Override
    public List<Ordinepannelli> findByStato(String ricevuto) {
        Query q= em.createNamedQuery("Ordinepannelli.findByStato");
        q.setParameter("stato",ricevuto);
        return q.getResultList();
    }

    @Override
    public List<Ordinepannelli> findByRicevutoAndData(String ricevuto, Date dataMin, Date dataMax) {
        Query q=em.createNamedQuery("Ordinepannelli.findByRicevutoAndData");
        q.setParameter("stato",ricevuto);
        q.setParameter("dataMin",dataMin);
        q.setParameter("dataMax",dataMax);
        return q.getResultList();
    }

    @Override
    public List<Ordinepannelli> findByTotaleAndStato(double min, double max, String ricevuto) {
        Query q= em.createNamedQuery("Ordinepannelli.findByTotaleAndStato");
        q.setParameter("min",min);
        q.setParameter("max",max);
        q.setParameter("stato",ricevuto);
        return q.getResultList();
    }
    
}
