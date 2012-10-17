/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Dipendente;
import entities.Mansione;
import entities.Salario;
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
public class DipendenteFacade extends AbstractFacade<Dipendente> implements DipendenteFacadeLocal {
    @PersistenceContext(unitName = "SolarEnergia-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DipendenteFacade() {
        super(Dipendente.class);
    }

    @Override
    public int count() {
        return super.count();
    }

    @Override
    public List<Dipendente> findAll() {
        Query q = em.createNamedQuery("Dipendente.findAll");
        return q.getResultList();
    }
    
    @Override
    public Dipendente findByID(Integer id) {
        
        Query q = em.createNamedQuery("Dipendente.findByID");
        q.setParameter("iddipendente", id);
        try
        {
            return (Dipendente) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }

    @Override
    public Dipendente findByCodFiscale(String CF) {
        
        Query q = em.createNamedQuery("Dipendente.findByCodFiscale");
        q.setParameter("codfiscale", CF);
        try
        {
            return (Dipendente) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }

    @Override
    public List<Dipendente> findByCognome(String cognome) {
        
        Query q = em.createNamedQuery("Dipendente.findByCognome");
        q.setParameter("cognome", cognome);
        return q.getResultList();
    }

    @Override
    public List<Dipendente> findByCognomeAndNome(String cognome, String nome) {
        
        Query q = em.createNamedQuery("Dipendente.findByCognomeAndNome");
        q.setParameter("cognome", cognome);
        q.setParameter("nome", nome);
        return q.getResultList();
    }

    @Override
    public List<Dipendente> findByMansione(Mansione mansione) {
        
        Query q = em.createNamedQuery("Dipendente.findByMansione");
        q.setParameter("mansione", mansione);
        return q.getResultList();
    }

    @Override
    public Dipendente findBySalario(Salario s) {
        
        Query q = em.createNamedQuery("Dipendente.findBySalario");
        q.setParameter("salario", s);
        try
        {
            return (Dipendente) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }
    
}
