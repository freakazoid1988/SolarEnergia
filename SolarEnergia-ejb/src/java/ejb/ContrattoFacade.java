/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Cliente;
import entities.Contratto;
import entities.Dipendente;
import entities.Progetto;
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
public class ContrattoFacade extends AbstractFacade<Contratto> implements ContrattoFacadeLocal {
    @PersistenceContext(unitName = "SolarEnergia-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContrattoFacade() {
        super(Contratto.class);
    }

    @Override
    public int count() {
        return super.count();
    }

    @Override
    public List<Contratto> findAll() {
        Query q = em.createNamedQuery("Contratto.findAll");
        return q.getResultList();
    }
    
    @Override
    public Contratto findByID(Integer id) {
        
        Query q = em.createNamedQuery("Contratto.findByID");
        q.setParameter("idcontratto", id);
        try
        {
            return (Contratto) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }

    @Override
    public Contratto findByCodice(String cod) {
        
        Query q = em.createNamedQuery("Contratto.findByCodice");
        q.setParameter("codice", cod);
        try
        {
            return (Contratto) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }

    @Override
    public Contratto findByProgetto(Progetto p) {
        
        Query q = em.createNamedQuery("Contratto.findByProgetto");
        q.setParameter("progetto", p);
        try
        {
            return (Contratto) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            e.toString();
            return null;
        }
    }

    @Override
    public List<Contratto> findByCliente(Cliente cliente) {
        
        Query q = em.createNamedQuery("Contratto.findByCliente");
        q.setParameter("cliente", cliente);
        return q.getResultList();
    }

    @Override
    public List<Contratto> findByAddetto(Dipendente addVendite) {
        
        Query q = em.createNamedQuery("Contratto.findByAddetto");
        q.setParameter("addvendite", addVendite);
        return q.getResultList();
    }

    @Override
    public List<Contratto> findByDataFirma(Date data) {
        
        Query q = em.createNamedQuery("Contratto.findByDataFirma");
        q.setParameter("datafirma", data);
        return q.getResultList();
    }

    @Override
    public List<Contratto> findByDataFirma(Date dataMin, Date dataMax) {
        Query q= em.createNamedQuery("Contratto.findByDataFirma");
        q.setParameter("dataMin",dataMin);
        q.setParameter("dataMax",dataMax);
        return q.getResultList();
    }

    @Override
    public List<Contratto> findByValore(double min, double max) {
        Query q= em.createNamedQuery("Contratto.findByValore");
        q.setParameter("min",min);
        q.setParameter("max",max);
        return q.getResultList();
    }
    
}
