/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.NoResultException;

/**
 *
 * @author Balordo
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> implements ClienteFacadeLocal {
    @PersistenceContext(unitName = "SolarEnergia-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    @Override
    public int count() {
        return super.count();
    }

    @Override
    public List<Cliente> findAll() {
        Query q = em.createNamedQuery("Cliente.findAll");
        return q.getResultList();
    }

    @Override
    public Cliente findByID(Integer id) {
        
        Query q = em.createNamedQuery("Cliente.findByID");
        q.setParameter("idcliente", id);
        try
        {
            return (Cliente) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
        
    }

    @Override
    public Cliente findByCodFiscale(String CF) {
        
        Query q = em.createNamedQuery("Cliente.findByCodFiscale");
        q.setParameter("codfiscale", CF);
        try
        {
            return (Cliente) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }

    @Override
    public List<Cliente> findByCognome(String cognome) {
        
        Query q = em.createNamedQuery("Cliente.findByCognome");
        q.setParameter("cognome", cognome);
        return q.getResultList();
    }

    @Override
    public List<Cliente> findByCognomeAndNome(String cognome, String nome) {
        
        Query q = em.createNamedQuery("Cliente.findByCognomeAndNome");
        q.setParameter("cognome", cognome);
        q.setParameter("nome", nome);
        return q.getResultList();
    }
    
}
