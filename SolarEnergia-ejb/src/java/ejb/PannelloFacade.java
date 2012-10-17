/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Pannello;
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
public class PannelloFacade extends AbstractFacade<Pannello> implements PannelloFacadeLocal {
    @PersistenceContext(unitName = "SolarEnergia-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PannelloFacade() {
        super(Pannello.class);
    }
    
    @Override
    public int count() {
        return super.count();
    }

    @Override
    public List<Pannello> findAll() {
        Query q = em.createNamedQuery("Pannello.findAll");
        return q.getResultList();
    }

    @Override
    public Pannello findByID(Integer id) {
        
        Query q = em.createNamedQuery("Pannello.findByID");
        q.setParameter("idpannello", id);
        try
        {
            return (Pannello) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }

    @Override
    public Pannello findBySerie(String serie) {
        
        Query q = em.createNamedQuery("Pannello.findBySerie");
        q.setParameter("nserie", serie);
        try
        {
            return (Pannello) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }

    @Override
    public List<Pannello> findByProgetto(Progetto p) {
        
        Query q = em.createNamedQuery("Pannello.findByProgetto");
        q.setParameter("progetto", p);
        return q.getResultList();
    }

    @Override
    public List<Pannello> findByTipo(String tipo) {
        
        Query q = em.createNamedQuery("Pannello.findByTipo");
        q.setParameter("tipo", tipo);
        return q.getResultList();
    }

    @Override
    public List<Pannello> findByMarca(String marca) {
        
        Query q = em.createNamedQuery("Pannello.findByMarca");
        q.setParameter("marca", marca);
        return q.getResultList();
    }

    @Override
    public List<Pannello> findByNSerie(String nserie) {
        Query q = em.createNamedQuery("Pannello.findByNSerie");
        q.setParameter("nserie", nserie);
        return q.getResultList();
    }

    @Override
    public List<Pannello> findByMarcaAndNSerie(String marca, String nserie) {
        Query q = em.createNamedQuery("Pannello.findByMarcaAndNSerie");
        q.setParameter("marca", marca);
        q.setParameter("nserie", nserie);
        return q.getResultList();
    }

    @Override
    public List<Pannello> findByMarcaAndTipo(String marca, String tipo) {
        Query q = em.createNamedQuery("Pannello.findByMarcaAndTipo");
        q.setParameter("marca", marca);
        q.setParameter("tipo", tipo);
        return q.getResultList();
    }

    @Override
    public List<Pannello> findByTipoAndNSerie(String tipo, String nserie) {
        Query q = em.createNamedQuery("Pannello.findByTipoAndNSerie");
        q.setParameter("tipo", tipo);
        q.setParameter("nserie", nserie);
        return q.getResultList();
    }

    @Override
    public List<Pannello> findByPrezzo(double min, double max) {
        Query q = em.createNamedQuery("Pannello.findByPrezzo");
        q.setParameter("min", min);
        q.setParameter("max", max);
        return q.getResultList();
    }

    @Override
    public List<Pannello> findByQuantita(int qta) {
        Query q = em.createNamedQuery("Pannello.findByQuantita");
        q.setParameter("quantita", qta);
        return q.getResultList();
    }
    
}
