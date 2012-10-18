package beans;

import ejb.ClienteFacadeLocal;
import ejb.ContrattoFacadeLocal;
import ejb.FatturaclienteFacadeLocal;
import entities.Cliente;
import entities.Contratto;
import entities.Dipendente;
import entities.Fatturacliente;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;


/**
 *
 * @author freakazoid
 */

public class ContrattoBean implements Serializable
{
    private Contratto contratto;
    private boolean nuovo;
    private Fatturacliente fattura;

    @EJB
    ContrattoFacadeLocal contrattoBean;

    @EJB
    ClienteFacadeLocal clienteBean;
    
    @EJB
    FatturaclienteFacadeLocal fatturaCliente;

    
    public ContrattoBean()
    {
        contratto = new Contratto();
        contratto.setCliente(new Cliente());
        fattura = new Fatturacliente();
    }

    public Contratto getContratto()
    {
        return contratto;
    }

    public void setContratto(Contratto contratto)
    {
        this.contratto = contratto;
    }

    public String nuovoContratto(Dipendente addetto)
    {
        contratto.setAddvendite(addetto);
        return "aggiungi";
    }
    
    public boolean isNuovo()
    {
        return nuovo;
    }
    
    public void setNuovo(boolean nuovo)
    {
        this.nuovo=nuovo;
    }

    public String aggiungiContratto()
    {
        
        contratto.setCliente(clienteBean.findByID(contratto.getCliente().getIdcliente()));
        clienteBean.edit(contratto.getCliente());
        contrattoBean.create(contratto);
        contratto = contrattoBean.findByCodice(contratto.getCodice());
        fattura.setCodice(contratto.getCodice());
        fattura.setContabile(contratto.getAddvendite());
        fattura.setContratto(contratto);
        fattura.setDataRilascio(new Date());
        fattura.setValore(contratto.getValore());
        fatturaCliente.create(fattura);
        return "visualizza";
    }

    public List<Contratto> contratti(Dipendente addetto)
    {
        return contrattoBean.findByAddetto(addetto);
    }

    public String clear()
    {
        contratto = new Contratto();
        contratto.setCliente(new Cliente());
        return "back";
    }

    public String clear2()
    {
        contratto = new Contratto();
        contratto.setCliente(new Cliente());
        return "indietro";
    }

    public String dettagli(Contratto c)
    {
        contratto = c;
        return "visualizzaContratto";
    }


    public String vistaSemplice()
    {
        contratto = contrattoBean.findByID(contratto.getIdcontratto());
        return "vistaContratto";
    }
}
