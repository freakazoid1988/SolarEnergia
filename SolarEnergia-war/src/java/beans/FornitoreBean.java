/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejb.FornitoreFacadeLocal;
import entities.Fornitore;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Balordo
 */
public class FornitoreBean implements Serializable
{
    Fornitore fornitore;
    boolean nuovo,visione,visible,dettagli,modifica,rimozione,rem,errore,disabled,continua,disable;
    String criterio,chiaveRicerca;
    List<Fornitore> fornitori,temporanei, fornitoreTemp;

    @EJB
    FornitoreFacadeLocal fornitoreLocal;  
    
    public FornitoreBean() 
    {        
        criterio="nome";
        disabled=true;
        temporanei=new LinkedList<Fornitore>();
        fornitoreTemp=new LinkedList<Fornitore>();
        continua = false;
        disable = true;
    }

    public String getChiaveRicerca() {
        return chiaveRicerca;
    }

    public void setChiaveRicerca(String chiaveRicerca) {
        this.chiaveRicerca = chiaveRicerca;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public boolean isDettagli() {
        return dettagli;
    }

    public void setDettagli(boolean dettagli) {
        this.dettagli = dettagli;
    }

    public boolean isErrore() {
        return errore;
    }

    public void setErrore(boolean errore) {
        this.errore = errore;
    }

    public Fornitore getFornitore() {
        return fornitore;
    }

    public void setFornitore(Fornitore fornitore) {
        this.fornitore = fornitore;
    }

    public List<Fornitore> getFornitori() {
        return fornitori;
    }

    public void setFornitori(List<Fornitore> fornitori) {
        this.fornitori = fornitori;
    }

    public boolean isModifica() {
        return modifica;
    }

    public void setModifica(boolean modifica) {
        this.modifica = modifica;
    }

    public boolean isNuovo() {
        return nuovo;
    }

    public void setNuovo(boolean nuovo) {
        this.nuovo = nuovo;
    }

    public boolean isRem() {
        return rem;
    }

    public void setRem(boolean rem) {
        this.rem = rem;
    }

    public boolean isRimozione() {
        return rimozione;
    }

    public void setRimozione(boolean rimozione) {
        this.rimozione = rimozione;
    }

    public List<Fornitore> getTemporanei() {
        return temporanei;
    }

    public void setTemporanei(List<Fornitore> temporanei) {
        this.temporanei = temporanei;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisione() {
        return visione;
    }

    public void setVisione(boolean visione) {
        this.visione = visione;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    
    public void abilitaPulsante(ValueChangeEvent event)
    {
        disabled=false;
    }

    public List<Fornitore> getFornitoreTemp() {
        return fornitoreTemp;
    }

    public void setFornitoreTemp(List<Fornitore> fornitoreTemp) {
        this.fornitoreTemp = fornitoreTemp;
    }

    public boolean isContinua() {
        return continua;
    }

    public void setContinua(boolean continua) {
        this.continua = continua;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
    
    public String nuovo()
    {
        fornitore=new Fornitore();
        reset();
        nuovo=true;
        visione=false;
        disabled=true;
        errore=false;
        rem=false;
        visible=false;
        dettagli=false;
        modifica=false;
        rimozione=false;
        return "loop";
    }
    
    private void reset()
    {
      continua = false;
      disable = true;
      chiaveRicerca=null;
      criterio="nome";
    }
    
    public String indietro()
    {
        if(nuovo)
        {
            nuovo=false;
            return "loop";
        }
        
        if(visione)
        {
            if(!visible)
            {
                if(dettagli)
                {
                  dettagli=false;
                  visible=true;
                  return "loop";
                }else
                    if(modifica)
                    {
                      visible=true;
                      modifica=false;
                      disabled=true;
                      errore=false;
                      return "loop";
                    }else
                        if(rimozione)
                        {
                            rimozione=false;
                            rem=false;
                            return "loop";
                        }
                reset();
                visione=false;
                return "loop";
            }else
            {
                visible=false;
                visione=true;
                return "loop";               
            }
        }
        reset();
        return "mag";
    }
    
    public String conferma()
    {
        nuovo=false;
        visione=false;
        fornitoreLocal.create(fornitore);
        return "loop";
    }
    
    public String visione()
    {
        nuovo=false;
        modifica=false;
        disabled=true;
        errore=false;
        rimozione=false;
        dettagli=false;
        visible=false;
        visione=true;
        fornitori=new LinkedList<Fornitore>();
        reset();
        return "loop";
    }
    
    public String tutti()
    {
        modifica=false;
        disabled=true;
        rimozione=false;
        dettagli=false;
        errore=false;
        rem=false;
        visible=true;
        if(!fornitori.isEmpty())
        fornitori.clear(); 
        fornitoreTemp.clear();
        fornitoreTemp = fornitoreLocal.findAll();
        if (fornitoreTemp.size() > 10) {
            disable = false;
            fornitori = fornitoreTemp.subList(0, 10);
        }else{
            fornitori = fornitoreTemp;
        }
        return "loop";
    }
    
    public String continues() {
        fornitori.clear();
        if (fornitoreTemp.size() > 10) {
            disable = false;
            continua = true;
            fornitori = fornitoreTemp.subList(0, 10);

        } else {
            continua = false;
            disable = true;
            fornitori = fornitoreTemp;
        }

        return "loop";
    }
    
    public String cerca()
    {
        fornitori.clear();
        StringTokenizer st=new StringTokenizer(chiaveRicerca," -,;:");

        if(!st.hasMoreTokens())
        {
           visible=false;
           modifica=false;
           rimozione=false;
           disabled=true;
           dettagli=false;
           rem=false;
           fornitori.clear();
           return "loop";
        }

        if(criterio.equals("nome"))
        {
            temporanei=fornitoreLocal.findAll();
            String tokenTemp=new String();

            while(true)
            {
                if(!st.hasMoreTokens())
                    break;

                tokenTemp=st.nextToken();

                for(Fornitore d:temporanei)
                    if((d.getNome().toLowerCase().contains(tokenTemp.toLowerCase())||d.getCognome().toLowerCase().contains(tokenTemp.toLowerCase()))&&!fornitori.contains(d))
                        fornitori.add(d);
            }
        }else
            if(criterio.equals("ditta"))
            {
                if(st.countTokens()==1)
                {
                    temporanei=fornitoreLocal.findAll();
                    String tokenTemp=new String();

                    while(true)
                    {
                        if(!st.hasMoreTokens())
                            break;

                        tokenTemp=st.nextToken();

                        for(Fornitore d:temporanei)
                            if(d.getDitta().toLowerCase().contains(tokenTemp.toLowerCase())&&!fornitori.contains(d))
                                fornitori.add(d);
                    }
                }else
                    fornitori.add(fornitoreLocal.findByDitta(chiaveRicerca));                
            }
            else
                if(criterio.equals("iva"))                
                    fornitori.add(fornitoreLocal.findByPartitaIVA(chiaveRicerca));
                else
                    if(criterio.equals("citta"))
                    {
                        if(st.countTokens()==1)
                        {
                            temporanei=fornitoreLocal.findAll();
                            String tokenTemp=new String();

                            while(true)
                            {
                                if(!st.hasMoreTokens())
                                    break;

                                tokenTemp=st.nextToken();

                                for(Fornitore d:temporanei)
                                    if(d.getCitta().toLowerCase().contains(tokenTemp.toLowerCase())&&!fornitori.contains(d))
                                        fornitori.add(d);
                            }
                        }else
                            fornitori=fornitoreLocal.findByCitta(chiaveRicerca);
                    }else
                        if(criterio.equals("provincia"))
                        {
                            if(st.countTokens()==1)
                            {
                                temporanei=fornitoreLocal.findAll();
                                String tokenTemp=new String();

                                while(true)
                                {
                                    if(!st.hasMoreTokens())
                                        break;

                                    tokenTemp=st.nextToken();

                                    for(Fornitore d:temporanei)
                                        if(d.getProvincia().toLowerCase().contains(tokenTemp.toLowerCase())&&!fornitori.contains(d))
                                            fornitori.add(d);
                                }
                            }else
                                fornitori=fornitoreLocal.findByProvincia(chiaveRicerca);
                        }                            
        visible=true;
        modifica=false;
        rimozione=false;
        dettagli=false;
        disabled=true;
        rem=false;
        return "loop";
    }
    
    /*public String dettagli(Integer id)
    {
        fornitore=fornitoreLocal.findById(id);
        visible=false;
        dettagli=true;
        return "loop";
    }*/

    public String modifica(Integer id)
    {
        fornitore=fornitoreLocal.findById(id);
        visible=false;
        modifica=true;
         return "loop";
    }

    public String confermaModifiche()
    {
        try
        {
            fornitoreLocal.edit(fornitore);
            modifica=false;
            disabled=true;
            visible=true;
            errore=false;
            fornitori=fornitoreLocal.findAll();
            return "loop";
        }
        catch(EJBException e)
        {
            errore=true;
            disabled=true;
            return "loop";
        }
    }

    public String rimuovi(Integer id)
    {
        fornitore=fornitoreLocal.findById(id);
        visible=false;
        rimozione=true;
        return "loop";
    }

    public String preRimozione()
    {
        rem=true;
        return "loop";
    }

    public String confermaRimozione()
    {
        fornitoreLocal.remove(fornitore);
        rem=false;
        rimozione=false;
        return "loop";
    }
    
    public String annullaRimozione(){
        rem=false;
        rimozione=false;
        return "loop";
    }
}
