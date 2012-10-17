/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejb.ClienteFacadeLocal;
import ejb.PannelloFacadeLocal;
import ejb.ProgettoFacadeLocal;
import ejb.RilieviFacadeLocal;
import entities.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Balordo
 */
public class ProgettoBean implements Serializable
{
    private Progetto progetto;
    private Dipendente ingegnere;
    private Pannello pannello;
    private List<Rilievi> rilievi;
    private List<Pannello> preventivi, temporanei, t;
    private List<Progetto> progetti;
    private Rilievi rilievo;

    public Rilievi getRilievo() {
        return rilievo;
    }

    public void setRilievo(Rilievi rilievo) {
        this.rilievo = rilievo;
    }

    public List<Progetto> getProgetti() {
        return progetti;
    }

    public void setProgetti(List<Progetto> progetti) {
        this.progetti = progetti;
    }
    private boolean modifica, nuovo, aggiuntaPannello, ultimo;
    private float valore, preventivo;

    public void setPreventivo(float preventivo) {
        this.preventivo = preventivo;
    }

    public float getPreventivo() {
        return preventivo;
    }

    @EJB
    ClienteFacadeLocal clienteBean;

    @EJB
    ProgettoFacadeLocal progettoBean;

    @EJB
    PannelloFacadeLocal pannelloBean;
    
    @EJB
    RilieviFacadeLocal rilieviBean;

    public ProgettoBean()
    {
        progetto = new Progetto();
        progetto.setCliente(new Cliente());
        progetto.setPannelli(new LinkedList());
        pannello = new Pannello();
        valore = 0;
        preventivi = new LinkedList<Pannello>();
    }

    public float getValore()
    {
        return valore;
    }

    public void setValore(float valore)
    {
        this.valore = valore;
    }

    public boolean isUltimo()
    {
        return ultimo;
    }

    public List<Pannello> getT() {
        return t;
    }

    public void setT(List<Pannello> t) {
        this.t = t;
    }

    public void setUltimo(boolean ultimo)
    {
        this.ultimo = ultimo;
    }

    public boolean isModifica()
    {
        return modifica;
    }

    public void setModifica(boolean modifica)
    {
        this.modifica = modifica;
    }

    public boolean isNuovo()
    {
        return nuovo;
    }

    public void setNuovo(boolean nuovo)
    {
        this.nuovo = nuovo;
    }

    public boolean isAggiuntaPannello()
    {
        return aggiuntaPannello;
    }

    public void setAggiuntaPannello(boolean aggiuntaPannello)
    {
        this.aggiuntaPannello = aggiuntaPannello;
    }

    public Dipendente getIngegnere()
    {
        return ingegnere;
    }

    public void setIngegnere(Dipendente ingegnere)
    {
        this.ingegnere = ingegnere;
    }

    public Progetto getProgetto()
    {
        return progetto;
    }

    public void setProgetto(Progetto progetto)
    {
        this.progetto = progetto;
    }

    public Pannello getPannello()
    {
        return pannello;
    }

    public void setPannello(Pannello pannello)
    {
        this.pannello = pannello;
    }

    public List<Rilievi> getRilievi() {
        return rilievi;
    }

    public void setRilievi(LinkedList<Rilievi> rilievi) {
        this.rilievi = rilievi;
    }

    public String visionaRilievi(){
        
        rilievi = new LinkedList<Rilievi>();
        rilievi = rilieviBean.findAll();        
        return "loop";
    }

    public String calcolaPreventivo(){
        
        preventivo = calcolaValore();
        return "loop";
    }
    
    private float calcolaValore()
    {
        String tipo = progetto.getTipopannello();
        String marca = progetto.getMarcapannello();
        Pannello p = pannelloBean.findByMarcaAndTipo(marca, tipo).get(0);
        //Pannello p = preventivi.get(0);
        return (float)(p.getPrezzo() * progetto.getQuantitapannelli());
    }

    public String aggiungiProgetto()
    {
        temporanei = new LinkedList<Pannello>();
        progetto.setCliente(clienteBean.findByID(progetto.getCliente().getIdcliente()));
        temporanei = pannelloBean.findByMarcaAndTipo(progetto.getMarcapannello(), progetto.getTipopannello());
        clienteBean.edit(progetto.getCliente());
        progetto.setPannelli(temporanei);
        progetto.setValore(calcolaValore());
        progettoBean.create(progetto);
        progetto = progettoBean.findByCodice(progetto.getCodice());                  
        return "back";
    }
    
    
    public String rimuoviProgetto()
    {
        progettoBean.remove(progetto);
        clear();
        return "back";
    }

    public void valueChange(ValueChangeEvent e)
    {
        pannello = pannelloBean.findByID((Integer)e.getNewValue());
        modifica = false;        
    }

    public List<Cliente> listaClienti()
    {
        return clienteBean.findAll();
    }
    
    public List<Pannello> listaPannelli()
    {
        return pannelloBean.findAll();
    }

    public List<Pannello> pannelli()
    {
        List <Pannello> result = progetto.getPannelli();
        return result;
    }
    
    public List<Pannello> listaPannelliNoRipMarca()
    {
        temporanei = pannelloBean.findAll();
        t = new LinkedList<Pannello>();
        
        for(Pannello p : temporanei) {
            if(t.isEmpty()) {
                t.add(p);
            }
            else if(!presenteM(p, t)) {
                t.add(p);
            }
            
        }
        
        return t;
    }
    
    public List<Pannello> listaPannelliNoRipTipo()
    {
        temporanei = pannelloBean.findAll();
        t = new LinkedList<Pannello>();
        
        for(Pannello p : temporanei) {
            if(t.isEmpty()) {
                t.add(p);
            }
            else if(!presenteT(p, t)) {
                t.add(p);
            }
            
        }
        
        return t;
    }
    
    private boolean presenteM(Pannello p, List<Pannello> t) {
        
            for(Pannello x : t)
            {
                if(x.getMarca().equalsIgnoreCase(p.getMarca())) {
                    return true;
                }
            }
        return false;
    }
    
    private boolean presenteT(Pannello p, List<Pannello> t) {
        
            for(Pannello x : t)
            {
                if(x.getTipo().equalsIgnoreCase(p.getTipo())) {
                    return true;
                }
            }
        return false;
    }

    public String progetti(Dipendente ingegnere)
    {
        progetti = new LinkedList<Progetto>();
        progetti = progettoBean.findByIngegnere(ingegnere);
        return "loop";
    }
    
    public String nuovoProgetto(Dipendente ingegnere)
    {
        List<Pannello> pan = pannelli();
        pannello = (pan.isEmpty())?pannello:pannelli().get(0);
        progetto.setIngegnere(ingegnere);
        setNuovo(true); //da controllare
        return "aggiungi";
    }


    public String dettagli(Progetto p)
    {
        progetto = p;        
        pannello = pannelloBean.findByID(progetto.getPannelli().get(0).getIdpannello());
        return "visualizzaProgetto";
    }

    public String modifica()
    {
        valore = 0;
        modifica = true;
        ultimo = false;
        return "loop";
    }


    public String indietro(boolean modificaProgetto, boolean nuovoProgetto)
    {
        modifica = false;
        aggiuntaPannello = false;
        nuovo = false;
        ultimo = false;
        rilievi = new LinkedList<Rilievi>();
        t= new LinkedList<Pannello>();
        List<Pannello> pan = (modificaProgetto)?progetto.getPannelli():pannelli();
        pannello = (!nuovoProgetto)?pannello:(pan.isEmpty())?new Pannello():pan.get(0);
        return "loop";
    }

    public String clear()
    {
        progetto = new Progetto();
        progetto.setCliente(new Cliente());
        progetto.setPannelli(new LinkedList());
        pannello = new Pannello();
        modifica = false;
        nuovo = false;
        rilievi = new LinkedList<Rilievi>();
        t= new LinkedList<Pannello>();
        valore = 0;
        aggiuntaPannello = false;
        return "back";
    }

    public String vistaSemplice()
    {
        progetto = progettoBean.findByID(progetto.getIdprogetto());
        pannello = pannelloBean.findByID(progetto.getPannelli().get(0).getIdpannello());
        return "vistaProgetto";
    }       
}