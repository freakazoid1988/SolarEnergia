/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Cliente;
import entities.Rilievi;
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
public class RilieviFacade extends AbstractFacade<Rilievi> implements RilieviFacadeLocal {
    @PersistenceContext(unitName = "SolarEnergia-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RilieviFacade() {
        super(Rilievi.class);
    }

    @Override
    public Rilievi findByID(Integer id) {
        Query q = em.createNamedQuery("Rilievi.findByID");
        q.setParameter("idlogin", id);
        try
        {
            return (Rilievi) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }

    @Override
    public List<Rilievi> findByCliente(Cliente c) {
        Query q = em.createNamedQuery("Rilievi.finByCliente");
        q.setParameter("cliente", c);
        return q.getResultList();
    }

    @Override
    public List<Rilievi> findAll() {
        Query q = em.createNamedQuery("Rilievi.findAll");
        return q.getResultList();
    }
    
}
