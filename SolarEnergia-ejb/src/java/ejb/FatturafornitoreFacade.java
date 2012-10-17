/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Dipendente;
import entities.Fatturafornitore;
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
public class FatturafornitoreFacade extends AbstractFacade<Fatturafornitore> implements FatturafornitoreFacadeLocal {
    @PersistenceContext(unitName = "SolarEnergia-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FatturafornitoreFacade() {
        super(Fatturafornitore.class);
    }

    @Override
    public int count() {
        return super.count();
    }

    @Override
    public List<Fatturafornitore> findAll() {
        Query q = em.createNamedQuery("Fatturafornitore.findAll");
        return q.getResultList();
    }
    
    @Override
    public Fatturafornitore findByID(Integer id) {
        
        Query q = em.createNamedQuery("Fatturafornitore.findByID");
        q.setParameter("idfattura", id);
        try
        {
            return (Fatturafornitore) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }

    @Override
    public List<Fatturafornitore> findByContabile(Dipendente addetto) {
        
        Query q = em.createNamedQuery("Fatturafornitore.findByContabile");
        q.setParameter("contabile", addetto);
        return q.getResultList();
    }

    @Override
    public List<Fatturafornitore> findByOrdine(Ordinepannelli ord) {
        
        Query q = em.createNamedQuery("Fatturafornitore.findByOrdine");
        q.setParameter("ordine", ord);
        return q.getResultList();
    }

    @Override
    public Fatturafornitore findByCodice(String cod) {
        Query q = em.createNamedQuery("Fatturafornitore.findByCodice");
        q.setParameter("codice", cod);
        try
        {
            return (Fatturafornitore) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }

    @Override
    public List<Fatturafornitore> findByValore(double min, double max) {
        Query q = em.createNamedQuery("Fatturafornitore.findByValore");
        q.setParameter("min", min);
        q.setParameter("max", max);
        return q.getResultList();
    }

    @Override
    public List<Fatturafornitore> findByDataRilascio(Date dateMin, Date dateMax) {
        Query q = em.createNamedQuery("Fatturafornitore.findByDataRilascio");
        q.setParameter("dateMin", dateMin);
        q.setParameter("dateMax", dateMax);
        return q.getResultList();
    }
    
}
