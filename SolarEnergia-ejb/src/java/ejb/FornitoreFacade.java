/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Fornitore;
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
public class FornitoreFacade extends AbstractFacade<Fornitore> implements FornitoreFacadeLocal {
    @PersistenceContext(unitName = "SolarEnergia-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FornitoreFacade() {
        super(Fornitore.class);
    }

    @Override
    public int count() {
        return super.count();
    }

    @Override
    public List<Fornitore> findAll() {
        Query q = em.createNamedQuery("Fornitore.findAll");
        return q.getResultList();
    }
    
    @Override
    public Fornitore findById(Integer id) {
        
        Query q = em.createNamedQuery("Fornitore.findById");
        q.setParameter("idfornitore", id);
        try
        {
            return (Fornitore) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }

    @Override
    public Fornitore findByPartitaIVA(String partitaIVA) {
        
        Query q = em.createNamedQuery("Fornitore.findByPartitaIVA");
        q.setParameter("partitaIVA", partitaIVA);
        try
        {
            return (Fornitore) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }

    @Override
    public List<Fornitore> findByCognome(String cognome) {
        
        Query q = em.createNamedQuery("Fornitore.findByCognome");
        q.setParameter("cognome", cognome);
        return q.getResultList();
    }

    @Override
    public List<Fornitore> findByCognomeAndNome(String cognome, String nome) {
        
        Query q = em.createNamedQuery("Fornitore.findByCognomeAndNome");
        q.setParameter("cognome", cognome);
        q.setParameter("nome", nome);
        return q.getResultList();
    }

    @Override
    public Fornitore findByDitta(String ditta) {
        Query q=em.createNamedQuery("Fornitore.findByDitta");
        q.setParameter("ditta",ditta);

        try
        {
            return (Fornitore)q.getSingleResult();
        }
        catch(NoResultException e){
            e.toString();
            return null;
        }
    }

    @Override
    public List<Fornitore> findByCitta(String citta)
    {
        Query q=em.createNamedQuery("Fornitore.findByCitta");
        q.setParameter("citta",citta);
        return q.getResultList();       
    }

    @Override
    public List<Fornitore> findByProvincia(String provincia)
    {
        Query q=em.createNamedQuery("Fornitore.findByProvincia");
        q.setParameter("provincia",provincia);
        return q.getResultList();
    }
    
}
