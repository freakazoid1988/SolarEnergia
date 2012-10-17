/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejb.ClienteFacadeLocal;
import ejb.ContrattoFacadeLocal;
import ejb.LoginFacadeLocal;
import entities.Cliente;
import entities.Contratto;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author freakazoid
 */
public class ClienteBean implements Serializable
{
    


    private Cliente cliente;
    private boolean esistenti=true, aggiunta;
    
    @EJB
    LoginFacadeLocal logBean;

    @EJB
    ClienteFacadeLocal clienteBean;
    
    @EJB
    ContrattoFacadeLocal contrattoBean;

    /*@EJB
    StatoSessionLocal statoBean;

    @EJB
    ContrattoSessionLocal contrattoBean;*/

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    public boolean isAggiunta()
    {
        return aggiunta;
    }

    public void setAggiunta(boolean aggiunta)
    {
        this.aggiunta = aggiunta;
    }

    public boolean isEsistenti()
    {
        return esistenti;
    }

    public void setEsistenti(boolean esistenti)
    {
        this.esistenti = esistenti;
    }

    public String dettagli(Integer id)
    {
        cliente = clienteBean.findByID(id);
        return "visualizza";
    }

    public String aggiungi()
    {        
        aggiunta = true;
        cliente = new Cliente();
        cliente.setNome("");
        //cliente.setStato(new Stato());
        return "aggiungiC";
    }

    /*public List<Stato> stati()
    {
        return statoBean.findAll();
    }*/

    public String seleziona(boolean esistenti)
    {
        this.esistenti = esistenti;
        return "loop";
    }


    public List<Contratto> contratti()
    {
        return contrattoBean.findByCliente(cliente);
    }

    public String applica()
    {

        if(aggiunta)
            clienteBean.create(cliente);

        else
            clienteBean.edit(cliente);

        aggiunta = false;
        esistenti = true;
    
        return "visualizza";
    }

    public String rimuovi()
    {        
        clienteBean.remove(this.cliente);
        return "back";
    }

    public String clear()
    {
        aggiunta = false;
        esistenti = true;
        return "back";
    }
}

  
