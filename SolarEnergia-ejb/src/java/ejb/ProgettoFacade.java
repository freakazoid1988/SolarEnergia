/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Cliente;
import entities.Dipendente;
import entities.Progetto;
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
public class ProgettoFacade extends AbstractFacade<Progetto> implements ProgettoFacadeLocal {
    @PersistenceContext(unitName = "SolarEnergia-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProgettoFacade() {
        super(Progetto.class);
    }
    
    @Override
    public int count() {
        return super.count();
    }

    @Override
    public List<Progetto> findAll() {
        Query q = em.createNamedQuery("Progetto.findAll");
        return q.getResultList();
    }

    @Override
    public Progetto findByID(Integer id) {
        
        Query q = em.createNamedQuery("Progetto.findByID");
        q.setParameter("idprogetto", id);
        try
        {
            return (Progetto) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }

    @Override
    public Progetto findByCodice(String cod) {
        
        Query q = em.createNamedQuery("Progetto.findByCodice");
        q.setParameter("codice", cod);
        try
        {
            return (Progetto) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }

    @Override
    public List<Progetto> findByCliente(Cliente c) {
        
        Query q = em.createNamedQuery("Progetto.findByCliente");
        q.setParameter("cliente", c);
        return q.getResultList();
    }

    @Override
    public List<Progetto> findByIngegnere(Dipendente d) {
        
        Query q = em.createNamedQuery("Progetto.findByIngegnere");
        q.setParameter("ingegnere", d);
        return q.getResultList();
    }
    
}
