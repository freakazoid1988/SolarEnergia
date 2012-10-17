/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Contratto;
import entities.Dipendente;
import entities.Fatturacliente;
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
public class FatturaclienteFacade extends AbstractFacade<Fatturacliente> implements FatturaclienteFacadeLocal {
    @PersistenceContext(unitName = "SolarEnergia-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FatturaclienteFacade() {
        super(Fatturacliente.class);
    }
    
    @Override
    public int count() {
        return super.count();
    }

    @Override
    public List<Fatturacliente> findAll() {
        Query q = em.createNamedQuery("Fatturacliente.findAll");
        return q.getResultList();
    }
    
    @Override
    public Fatturacliente findByID(Integer id) {
        
        Query q = em.createNamedQuery("Fatturacliente.findByID");
        q.setParameter("idfattura", id);
        try
        {
            return (Fatturacliente) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }

    @Override
    public List<Fatturacliente> findByContabile(Dipendente addetto) {
        
        Query q = em.createNamedQuery("Fatturacliente.findByContabile");
        q.setParameter("contabile", addetto);
        return q.getResultList();
    }

    @Override
    public List<Fatturacliente> findByContratto(Contratto cont) {
        
        Query q = em.createNamedQuery("Fatturacliente.findByContratto");
        q.setParameter("contratto", cont);
        return q.getResultList();
    }

    @Override
    public Fatturacliente findByCodice(String cod) {
        Query q = em.createNamedQuery("Fatturacliente.findByCodice");
        q.setParameter("codice", cod);
        try
        {
            return (Fatturacliente) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }

    @Override
    public List<Fatturacliente> findByValore(double min, double max) {
        Query q = em.createNamedQuery("Fatturacliente.findByValore");
        q.setParameter("min", min);
        q.setParameter("max", max);
        return q.getResultList();
    }

    @Override
    public List<Fatturacliente> findByDataRilascio(Date dateMin, Date dateMax) {
        Query q = em.createNamedQuery("Fatturacliente.findByDataRilascio");
        q.setParameter("dateMin", dateMin);
        q.setParameter("dateMax", dateMax);
        return q.getResultList();
    }
    
}
