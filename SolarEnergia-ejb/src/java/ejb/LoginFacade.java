/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Login;
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
public class LoginFacade extends AbstractFacade<Login> implements LoginFacadeLocal {
    @PersistenceContext(unitName = "SolarEnergia-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LoginFacade() {
        super(Login.class);
    }
    
    @Override
    public int count() {
        return super.count();
    }

    @Override
    public List<Login> findAll() {
        Query q = em.createNamedQuery("Login.findAll");
        return q.getResultList();
    }

    @Override
    public Login findByID(Integer id) {
        
        Query q = em.createNamedQuery("Login.findByID");
        q.setParameter("idlogin", id);
        try
        {
            return (Login) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }

    @Override
    public Login findByUsername(String user) {
        
        Query q = em.createNamedQuery("Login.findByUsername");
        q.setParameter("username", user);
        try
        {
            return (Login) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }

    @Override
    public Login findByUsernameAndPassword(String user, String pass) {
        
        Query q = em.createNamedQuery("Login.findByUsernameAndPassword");
        q.setParameter("username", user);
        q.setParameter("password", pass);
        try
        {
            Login result = (Login)q.getSingleResult();
            return result;
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }
    
}
