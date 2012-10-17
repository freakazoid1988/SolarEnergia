/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejb.*;
import entities.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import javax.ejb.EJB;

/**
 *
 * @author Balordo
 */
public class StoricoTransazioniBean implements Serializable {

    private boolean visibile, contratto, errorecontratto, erroreTransazione, selectAll, erroreOrdine, continua, disable, disableTotal1, disableTotal2, disableTotal3, disabled;
    private List<Ordinepannelli> ordini, ord;
    private List<Contratto> contratti, cont;
    private List<Dipendente> dipendenti;
    private List<Fatturafornitore> fatFornitore;
    private List<Fatturacliente> fatCliente;
    private String chiaveRicerca, criterio, scelta, crit;
    private Double totSalari, totFatFornitori, totFatClienti, totale;
    @EJB
    OrdinepannelliFacadeLocal ordineLocal;
    @EJB
    DipendenteFacadeLocal dipendenteLocal;
    @EJB
    MansioneFacadeLocal manLocal;
    @EJB
    ContrattoFacadeLocal contrattoLocal;
    @EJB
    FatturafornitoreFacadeLocal fatForLocal;
    @EJB
    FatturaclienteFacadeLocal fatCliLocal;

    public StoricoTransazioniBean() {
        ordini = new LinkedList<Ordinepannelli>();
        ord = new LinkedList<Ordinepannelli>();
        contratti = new LinkedList<Contratto>();
        cont = new LinkedList<Contratto>();
        dipendenti = new LinkedList<Dipendente>();
        fatCliente = new LinkedList<Fatturacliente>();
        fatFornitore = new LinkedList<Fatturafornitore>();
        scelta = "contratto";
        criterio = "mese";
        crit = "no";
        contratto = true;
        continua = false;
        disable = true;
        disableTotal1 = true;
        disableTotal2 = true;
        disableTotal3 = true;
        disabled = true;
        totSalari = 0.0;
        totFatFornitori = 0.0;
        totFatClienti = 0.0;
        totale = 0.0;
    }

    public String getChiaveRicerca() {
        return chiaveRicerca;
    }

    public void setChiaveRicerca(String chiaveRicerca) {
        this.chiaveRicerca = chiaveRicerca;
    }

    public List<Contratto> getContratti() {
        return contratti;
    }

    public void setContratti(List<Contratto> contratti) {
        this.contratti = contratti;
    }

    public void setContratto(boolean contratto) {
        this.contratto = contratto;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public boolean isErroreOrdine() {
        return erroreOrdine;
    }

    public void setErroreOrdine(boolean erroreOrdine) {
        this.erroreOrdine = erroreOrdine;
    }

    public boolean isErroreTransazione() {
        return erroreTransazione;
    }

    public void setErroreTransazione(boolean erroreTransazione) {
        this.erroreTransazione = erroreTransazione;
    }

    public boolean isErrorecontratto() {
        return errorecontratto;
    }

    public void setErrorecontratto(boolean errorecontratto) {
        this.errorecontratto = errorecontratto;
    }

    public List<Ordinepannelli> getOrdini() {
        return ordini;
    }

    public void setOrdini(List<Ordinepannelli> ordini) {
        this.ordini = ordini;
    }

    public String getScelta() {
        return scelta;
    }

    public void setScelta(String scelta) {
        this.scelta = scelta;
    }

    public boolean isSelectAll() {
        return selectAll;
    }

    public void setSelectAll(boolean selectAll) {
        this.selectAll = selectAll;
    }

    public boolean isVisibile() {
        return visibile;
    }

    public void setVisibile(boolean visibile) {
        this.visibile = visibile;
    }

    public List<Contratto> getCont() {
        return cont;
    }

    public void setCont(List<Contratto> cont) {
        this.cont = cont;
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

    public String getCrit() {
        return crit;
    }

    public void setCrit(String crit) {
        this.crit = crit;
    }

    public List<Dipendente> getDipendenti() {
        return dipendenti;
    }

    public void setDipendenti(List<Dipendente> dipendenti) {
        this.dipendenti = dipendenti;
    }

    public List<Ordinepannelli> getOrd() {
        return ord;
    }

    public void setOrd(List<Ordinepannelli> ord) {
        this.ord = ord;
    }

    public Double getTotSalari() {
        return totSalari;
    }

    public void setTotSalari(Double totSalari) {
        this.totSalari = totSalari;
    }

    public List<Fatturacliente> getFatCliente() {
        return fatCliente;
    }

    public void setFatCliente(List<Fatturacliente> fatCliente) {
        this.fatCliente = fatCliente;
    }

    public List<Fatturafornitore> getFatFornitore() {
        return fatFornitore;
    }

    public void setFatFornitore(List<Fatturafornitore> fatFornitore) {
        this.fatFornitore = fatFornitore;
    }

    public Double getTotFatClienti() {
        return totFatClienti;
    }

    public void setTotFatClienti(Double totFatClienti) {
        this.totFatClienti = totFatClienti;
    }

    public Double getTotFatFornitori() {
        return totFatFornitori;
    }

    public void setTotFatFornitori(Double totFatFornitori) {
        this.totFatFornitori = totFatFornitori;
    }

    public Double getTotale() {
        return totale;
    }

    public void setTotale(Double totale) {
        this.totale = totale;
    }

    public boolean isDisableTotal1() {
        return disableTotal1;
    }

    public void setDisableTotal1(boolean disableTotal1) {
        this.disableTotal1 = disableTotal1;
    }

    public boolean isDisableTotal2() {
        return disableTotal2;
    }

    public void setDisableTotal2(boolean disableTotal2) {
        this.disableTotal2 = disableTotal2;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isContratto() {
        if (contratto) {
            return true;
        }
        return false;
    }

    public String tutti() {
        chiaveRicerca = null;
        cont.clear();
        ord.clear();
        if (scelta.equals("contratto")) {
            cont = contrattoLocal.findAll();
            if (cont.size() > 10) {
                contratti = cont.subList(0, 10);
                disable = false;
            } else {
                contratti = cont;
            }
            contratto = true;
        } else if (scelta.equals("ordine")) {
            ord = ordineLocal.findByStato("Ricevuto");
            ord.addAll(ordineLocal.findByStato("Cancellato"));
            if (ord.size() > 10) {
                ordini = ord.subList(0, 10);
                disable = false;
            } else {
                ordini = ord;
            }
            contratto = false;
        }
        visibile = true;
        errorecontratto = false;
        erroreOrdine = false;
        erroreTransazione = false;
        return "loop";
    }

    public String continues() {
        if (scelta.equals("contratto")) {
            contratti.clear();
            if (cont.size() > 10) {
                disable = false;
                continua = true;
                contratti = cont.subList(0, 10);

            } else {
                continua = false;
                disable = true;
                contratti = cont;
            }
        } else if (scelta.equals("ordine")) {
            ordini.clear();
            if (ord.size() > 10) {
                disable = false;
                continua = true;
                ordini = ord.subList(0, 10);

            } else {
                continua = false;
                disable = true;
                ordini = ord;
            }
        }

        return "loop";
    }

    public String cerca() {
        ordini.clear();
        ord.clear();
        contratti.clear();
        StringTokenizer st = new StringTokenizer(chiaveRicerca, " -,;:/");

        if (!st.hasMoreTokens()) {
            ordini.clear();
            contratti.clear();
            visibile = false;
            erroreTransazione = false;
            return "loop";
        }

        if (criterio.equals("mese") && scelta.equals("ordine")) {
            Date dataMin, dataMax;
            String token1 = new String();
            String token2 = new String();

            //ricevuto anno e mese
            if (st.countTokens() == 2) {
                token1 = st.nextToken();
                token2 = st.nextToken();
                try {
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String dataMi = token2 + "-" + token1 + "-01";
                    dataMin = formatter.parse(dataMi);
                    String dataMa = token2 + "-" + token1 + "-31";
                    dataMax = formatter.parse(dataMa);
                    ordini = ordineLocal.findByRicevutoAndData("Ricevuto", dataMin, dataMax);
                    ord = ordineLocal.findByRicevutoAndData("Cancellato", dataMin, dataMax);
                    for (Ordinepannelli o : ord) {
                        ordini.add(o);
                    }
                    contratto = false;
                } catch (ParseException ex) {
                }
            }
        } else if (criterio.equals("anno") && scelta.equals("ordine")) {
            Date dataMin, dataMax;
            String token1 = new String();

            //ricevuto solo l'anno
            if (st.countTokens() == 1) {
                token1 = st.nextToken();
                int anno = Integer.parseInt(token1);
                dataMin = new Date(anno - 1900, 0, 1);
                dataMax = new Date(anno - 1900, 11, 31);
                ordini = ordineLocal.findByRicevutoAndData("Ricevuto", dataMin, dataMax);
                ord = ordineLocal.findByRicevutoAndData("Cancellato", dataMin, dataMax);
                for (Ordinepannelli o : ord) {
                    ordini.add(o);
                }
                contratto = false;
            }
        } else if (criterio.equals("anno") && scelta.equals("contratto")) {
            Date dataMin, dataMax;
            String token1 = new String();

            //ricevuto solo l'anno
            if (st.countTokens() == 1) {
                token1 = st.nextToken();
                int anno = Integer.parseInt(token1);
                dataMin = new Date(anno - 1900, 0, 1);
                dataMax = new Date(anno - 1900, 11, 31);
                contratti = contrattoLocal.findByDataFirma(dataMin, dataMax);
                contratto = true;
            }
        } else if (criterio.equals("mese") && scelta.equals("contratto")) {
            Date dataMin, dataMax;
            String token1 = new String();
            String token2 = new String();

            //ricevuto anno e mese
            if (st.countTokens() == 2) {
                token1 = st.nextToken();
                token2 = st.nextToken();
                try {
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String dataMi = token2 + "-" + token1 + "-01";
                    dataMin = formatter.parse(dataMi);
                    String dataMa = token2 + "-" + token1 + "-31";
                    dataMax = formatter.parse(dataMa);
                    contratti = contrattoLocal.findByDataFirma(dataMin, dataMax);
                    contratto = true;

                } catch (ParseException ex) {
                }
            }
        } else if (criterio.equals("prezzo") && scelta.equals("contratto")) {
            try {
                double min = Double.parseDouble(st.nextToken());

                if (!st.hasMoreTokens()) {
                    contratti = contrattoLocal.findByValore(min, min);
                } else {
                    double max = Double.parseDouble(st.nextToken());
                    contratti = contrattoLocal.findByValore(min, max);
                }
                contratto = true;
            } catch (NumberFormatException e) {
                contratti.clear();
                e.toString();
            }
        } else if (criterio.equals("prezzo") && scelta.equals("ordine")) {
            try {
                double min = Double.parseDouble(st.nextToken());

                if (!st.hasMoreTokens()) {
                    ordini = ordineLocal.findByTotaleAndStato(min, min, "Ricevuto");
                    ord = ordineLocal.findByTotaleAndStato(min, min, "Cancellato");
                    for (Ordinepannelli o : ord) {
                        ordini.add(o);
                    }
                } else {
                    double max = Double.parseDouble(st.nextToken());
                    ordini = ordineLocal.findByTotaleAndStato(min, max, "Ricevuto");
                    ord = ordineLocal.findByTotaleAndStato(min, max, "Cancellato");
                    for (Ordinepannelli o : ord) {
                        ordini.add(o);
                    }
                }
                contratto = false;
            } catch (NumberFormatException e) {
                ordini.clear();
                e.toString();
            }
        }
        visibile = true;
        errorecontratto = false;
        erroreOrdine = false;
        erroreTransazione = false;
        return "loop";
    }

    public String indietro() {
        if (erroreTransazione) {
            erroreTransazione = false;
            return "loop";
        }
        if (visibile) {
            visibile = false;
            errorecontratto = false;
            erroreOrdine = false;
            return "loop";
        }
        contratto = true;
        errorecontratto = false;
        erroreOrdine = false;
        erroreTransazione = false;
        chiaveRicerca = null;
        continua = false;
        disable = true;
        scelta = "contratto";
        criterio = "mese";
        crit = "no";
        ordini.clear();
        ord.clear();
        cont.clear();
        contratti.clear();
        dipendenti.clear();
        return "cont";
    }

    public String calcolaTotSalari() {
        totSalari = 0.0;
        dipendenti = dipendenteLocal.findAll();
        if (crit.equals("si")) {
            for (Dipendente d : dipendenti) {
                totSalari += d.getSalario().getValore();
            }
        } else if (crit.equals("no")) {
            Mansione man = manLocal.findByMansione("Amministratore");
            Dipendente amm = dipendenteLocal.findByMansione(man).get(0);
            dipendenti.remove(amm);
            for (Dipendente d : dipendenti) {
                totSalari += d.getSalario().getValore();
            }
        }
        disableTotal1 = false;
        return "loop";
    }

    public String calcolaTotFatFornitori() {
        totFatFornitori = 0.0;
        fatFornitore = fatForLocal.findAll();
        for (Fatturafornitore ff : fatFornitore) {
            totFatFornitori += ff.getValore();
        }
        disableTotal2 = false;
        return "loop";
    }

    public String calcolaTotFatClienti() {
        totFatClienti = 0.0;
        fatCliente = fatCliLocal.findAll();
        for (Fatturacliente fc : fatCliente) {
            totFatClienti += fc.getValore();
        }
        disableTotal3 = false;
        return "loop";
    }

    public String calcolaTotale() {
        totale = totFatClienti - (totSalari + totFatFornitori);
        return "loop";
    }
    
    public String preCalcolo(){
        if(!disableTotal1 && !disableTotal2 && !disableTotal3)
            disabled = false;
        return "loop";
    }
    
    public String ind(){
        totale = 0.0;
        totSalari = 0.0;
        totFatFornitori = 0.0;
        totFatClienti = 0.0;
        disableTotal1 = true;
        disableTotal2 = true;
        disableTotal3 = true;
        disabled = true;
        return "cont";
    }
}
