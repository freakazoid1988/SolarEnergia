/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Salario;
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
public class SalarioFacade extends AbstractFacade<Salario> implements SalarioFacadeLocal {
    @PersistenceContext(unitName = "SolarEnergia-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SalarioFacade() {
        super(Salario.class);
    }
    
    @Override
    public int count() {
        return super.count();
    }

    @Override
    public List<Salario> findAll() {
        Query q = em.createNamedQuery("Salario.findAll");
        return q.getResultList();
    }

    @Override
    public Salario findByID(Integer id) {
        
        Query q = em.createNamedQuery("Salario.findByID");
        q.setParameter("idsalario", id);
        try
        {
            return (Salario) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }

    @Override
    public List<Salario> findByDataInizio(Date data) {
        
        Query q = em.createNamedQuery("Salario.findByDataInizio");
        q.setParameter("datainizio", data);
        return q.getResultList();
    }

    @Override
    public List<Salario> findByValore(float min, float max)
    {
        Query q = em.createNamedQuery("Salario.findByValore");
        q.setParameter("min", min);
        q.setParameter("max", max);
        return q.getResultList();
    }   
}
