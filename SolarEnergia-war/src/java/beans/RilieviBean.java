/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejb.ClienteFacadeLocal;
import ejb.PannelloFacadeLocal;
import ejb.RilieviFacadeLocal;
import entities.Cliente;
import entities.Dipendente;
import entities.Rilievi;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author freakazoid
 */
public class RilieviBean implements Serializable
{
    private Rilievi rilievi;
    private Dipendente addetto;
    private boolean aggiunta, visione;
    private List<Rilievi> listaRilievi, temporanei;
    
    @EJB
    ClienteFacadeLocal clienteBean;
    
    @EJB
    PannelloFacadeLocal pannelloBean;
    
    @EJB
    RilieviFacadeLocal rilieviBean;
    
    public RilieviBean()
    {
        rilievi = new Rilievi();
        rilievi.setCliente(new Cliente());
        visione = false;
        aggiunta = false;
        temporanei = new LinkedList<Rilievi>();
    }
    
    public List<Rilievi> getTemporanei() {
        return temporanei;
    }

    public void setTemporanei(List<Rilievi> temporanei) {
        this.temporanei = temporanei;
    }
    
    public Dipendente getAddetto()
    {
        return addetto;
    }
    
    public void setAddetto(Dipendente addetto)
    {
        this.addetto = addetto;
    }
    
    public Rilievi getRilievi()
    {
        return rilievi;
    }
    
    public void setRilievi(Rilievi rilievi)
    {
        this.rilievi = rilievi;
    }

    public boolean isAggiunta() {
        return aggiunta;
    }

    public void setAggiunta(boolean aggiunta) {
        this.aggiunta = aggiunta;
    }

    public boolean isVisione() {
        return visione;
    }

    public void setVisione(boolean visione) {
        this.visione = visione;
    }

    public List<Rilievi> getListaRilievi() {
        return listaRilievi;
    }

    public void setListaRilievi(List<Rilievi> listaRilievi) {
        this.listaRilievi = listaRilievi;
    }
    
    
    
    public String aggiungiRilievi()
    {
        aggiunta = true;
        return "vaiRilievi";
    }
    
    public String registraRilievi()
    {
        rilievi.setCliente(clienteBean.findByID(rilievi.getCliente().getIdcliente()));
        clienteBean.edit(rilievi.getCliente());
        rilieviBean.create(rilievi);
        clear();
        return "registra";
    }
    
    public String rimuoviRilievi()
    {     
        if(!temporanei.isEmpty())
        {
            for (Rilievi r: temporanei) 
            {
                if(r.isSelected()) 
                {
                    rilieviBean.remove(r);
                }
            }
        }
        clear();
        return "back";
    }
    
    public String convalidaRimozione()
    {
        visione = true;
        aggiunta = false;
        if(!listaRilievi.isEmpty())
        {
            for(Rilievi r:listaRilievi) 
            {
                if(r.isSelected())
                {
                    temporanei.add(r);
                }
            }
        }
        return "convalida";
    }
            
    
        public String clear()
        {
            rilievi = new Rilievi();
            rilievi.setCliente(new Cliente());
            listaRilievi = new LinkedList<Rilievi>();
            temporanei = new LinkedList<Rilievi>();
            aggiunta = false;
            visione = false;
            return "back";
        }
        
    public List<Cliente> listaClienti()
    {
        return clienteBean.findAll();
    }
    
    public String visualizzaRilievi()
    {
        visione = true;
        aggiunta = false;
        listaRilievi = rilieviBean.findAll();
        return "loop";
    }
    
    
}
