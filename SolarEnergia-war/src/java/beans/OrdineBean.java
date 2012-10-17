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
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Balordo
 */
public class OrdineBean implements Serializable {

    private boolean fase1, fase2, fase3, visione, info, merci, riepilogo, rimozione, aggiornamento, errore, disabled, continua, disable, isRicevuto, controlla;
    private List<Pannello> fornitura, temporanei, aggQta, terminati;
    private List<Fornitore> fornitori, tempFor, fornitoreTemp;
    private List<Ordinepannelli> ordini, tempOrd, ordineTemp;
    private Fornitore fornitore;
    private Fatturafornitore fattura;
    private Ordinepannelli ordine;
    private Dipendente amministratore;
    private Mansione man;
    private Pannello pannelloTemp;
    private String criterioFornitore, criterioPannello, chiaveRicerca, criterioOrdine;
    private double totale;
    @EJB
    FornitoreFacadeLocal forLocal;
    @EJB
    PannelloFacadeLocal panLocal;
    @EJB
    OrdinepannelliFacadeLocal ordLocal;
    @EJB
    FatturafornitoreFacadeLocal fatLocal;
    @EJB
    DipendenteFacadeLocal dipLocal;
    @EJB
    MansioneFacadeLocal manLocal;

    public OrdineBean() {
        fornitura = new LinkedList<Pannello>();
        terminati = new LinkedList<Pannello>();
        fornitori = new LinkedList<Fornitore>();
        temporanei = new LinkedList<Pannello>();
        aggQta = new LinkedList<Pannello>();
        tempFor = new LinkedList<Fornitore>();
        fornitoreTemp = new LinkedList<Fornitore>();
        tempOrd = new LinkedList<Ordinepannelli>();
        ordineTemp = new LinkedList<Ordinepannelli>();
        ordini = new LinkedList<Ordinepannelli>();
        ordine = new Ordinepannelli();
        fattura = new Fatturafornitore();
        amministratore = new Dipendente();
        man = new Mansione();
        fase1 = true;
        disabled = true;
        continua = false;
        disable = true;
        isRicevuto = false;
        criterioFornitore = "nome";
        criterioPannello = "marcaNSerie";
        criterioOrdine = "ditta";
    }

    public boolean isAggiornamento() {
        return aggiornamento;
    }

    public void setAggiornamento(boolean aggiornamento) {
        this.aggiornamento = aggiornamento;
    }

    public String getChiaveRicerca() {
        return chiaveRicerca;
    }

    public void setChiaveRicerca(String chiaveRicerca) {
        this.chiaveRicerca = chiaveRicerca;
    }

    public String getCriterioFornitore() {
        return criterioFornitore;
    }

    public void setCriterioFornitore(String criterioFornitore) {
        this.criterioFornitore = criterioFornitore;
    }

    public String getCriterioOrdine() {
        return criterioOrdine;
    }

    public void setCriterioOrdine(String criterioOrdine) {
        this.criterioOrdine = criterioOrdine;
    }

    public String getCriterioPannello() {
        return criterioPannello;
    }

    public void setCriterioPannello(String criterioPannello) {
        this.criterioPannello = criterioPannello;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isErrore() {
        return errore;
    }

    public void setErrore(boolean errore) {
        this.errore = errore;
    }

    public boolean isFase1() {
        return fase1;
    }

    public void setFase1(boolean fase1) {
        this.fase1 = fase1;
    }

    public boolean isFase2() {
        return fase2;
    }

    public void setFase2(boolean fase2) {
        this.fase2 = fase2;
    }

    public boolean isFase3() {
        return fase3;
    }

    public void setFase3(boolean fase3) {
        this.fase3 = fase3;
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

    public List<Pannello> getFornitura() {
        return fornitura;
    }

    public void setFornitura(List<Pannello> fornitura) {
        this.fornitura = fornitura;
    }

    public boolean isInfo() {
        return info;
    }

    public void setInfo(boolean info) {
        this.info = info;
    }

    public boolean isMerci() {
        return merci;
    }

    public void setMerci(boolean merci) {
        this.merci = merci;
    }

    public Ordinepannelli getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordinepannelli ordine) {
        this.ordine = ordine;
    }

    public List<Ordinepannelli> getOrdini() {
        return ordini;
    }

    public void setOrdini(List<Ordinepannelli> ordini) {
        this.ordini = ordini;
    }

    public Pannello getPannelloTemp() {
        return pannelloTemp;
    }

    public void setPannelloTemp(Pannello pannelloTemp) {
        this.pannelloTemp = pannelloTemp;
    }

    public boolean isRiepilogo() {
        return riepilogo;
    }

    public void setRiepilogo(boolean riepilogo) {
        this.riepilogo = riepilogo;
    }

    public boolean isRimozione() {
        return rimozione;
    }

    public void setRimozione(boolean rimozione) {
        this.rimozione = rimozione;
    }

    public List<Fornitore> getTempFor() {
        return tempFor;
    }

    public void setTempFor(List<Fornitore> tempFor) {
        this.tempFor = tempFor;
    }

    public List<Ordinepannelli> getTempOrd() {
        return tempOrd;
    }

    public void setTempOrd(List<Ordinepannelli> tempOrd) {
        this.tempOrd = tempOrd;
    }

    public List<Pannello> getTemporanei() {
        return temporanei;
    }

    public void setTemporanei(List<Pannello> temporanei) {
        this.temporanei = temporanei;
    }

    public boolean isVisione() {
        return visione;
    }

    public void setVisione(boolean visione) {
        this.visione = visione;
    }

    public List<Pannello> getAggQta() {
        return aggQta;
    }

    public void setAggQta(List<Pannello> aggQta) {
        this.aggQta = aggQta;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public Fatturafornitore getFattura() {
        return fattura;
    }

    public void setFattura(Fatturafornitore fattura) {
        this.fattura = fattura;
    }

    public Dipendente getAmministratore() {
        return amministratore;
    }

    public void setAmministratore(Dipendente amministratore) {
        this.amministratore = amministratore;
    }

    public Mansione getMan() {
        return man;
    }

    public void setMan(Mansione man) {
        this.man = man;
    }

    public List<Pannello> getTerminati() {
        return terminati;
    }

    public void setTerminati(List<Pannello> terminati) {
        this.terminati = terminati;
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

    public List<Fornitore> getFornitoreTemp() {
        return fornitoreTemp;
    }

    public void setFornitoreTemp(List<Fornitore> fornitoreTemp) {
        this.fornitoreTemp = fornitoreTemp;
    }

    public List<Ordinepannelli> getOrdineTemp() {
        return ordineTemp;
    }

    public void setOrdineTemp(List<Ordinepannelli> ordineTemp) {
        this.ordineTemp = ordineTemp;
    }

    public boolean isIsRicevuto() {
        return isRicevuto;
    }

    public void setIsRicevuto(boolean isRicevuto) {
        this.isRicevuto = isRicevuto;
    }

    public String visionaFornitori() {
        fornitoreTemp.clear();
        fornitoreTemp = forLocal.findAll();
        if(fornitoreTemp.size()>10){
            fornitori = fornitoreTemp.subList(0, 10);
            disable = false;
         }
        else{
            fornitori = fornitoreTemp;
        }
        visione = true;
        return "loop";
    }
    
    public String continuesF() {
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
    
    public String rifornimenti() {
        ordineTemp.clear();
        ordineTemp = ordLocal.findAll();
        if(ordineTemp.size()>10){
            ordini = ordineTemp.subList(0, 10);
            disable = false;
         }
        else{
            ordini = ordineTemp;
        }
        visione = true;
        aggiornamento = false;
        rimozione = false;
        disabled = true;
        return "loop";
    }
    
    public String continuesO() {
        ordini.clear();
        if (ordineTemp.size() > 10) {
            disable = false;
            continua = true;
            ordini = ordineTemp.subList(0, 10);

        } else {
            continua = false;
            disable = true;
            ordini = ordineTemp;
        }

        return "loop";
    }
    
    public String magazzino() {
        terminati.clear();
        terminati = panLocal.findAll();
        if(terminati.size()>10){
            fornitura = terminati.subList(0, 10);
            disable = false;
         }
        else{
            fornitura = terminati;
        }
        visione = true;
        return "loop";
    }
    
    public String continuesP() {
        fornitura.clear();
        if (terminati.size() > 10) {
            disable = false;
            continua = true;
            fornitura = terminati.subList(0, 10);

        } else {
            continua = false;
            disable = true;
            fornitura = terminati;
        }

        return "loop";
    }

    public String cercaOrdini() {
        ordini.clear();
        StringTokenizer st = new StringTokenizer(chiaveRicerca, " -,;:");

        if (!st.hasMoreTokens()) {
            ordini.clear();
            visione = false;
            disabled = true;
            aggiornamento = false;
            rimozione = false;
            return "loop";
        }

        if (criterioOrdine.equals("ditta")) {
            tempOrd = ordLocal.findAll();
            String tokenTemp = new String();

            while (true) {
                if (!st.hasMoreTokens()) {
                    break;
                }

                tokenTemp = st.nextToken();

                for (Ordinepannelli d : tempOrd) {
                    if (d.getFornitore().getDitta().toLowerCase().contains(tokenTemp.toLowerCase()) && !ordini.contains(d)) {
                        ordini.add(d);
                    }
                }
            }
        } else if (criterioOrdine.equals("periodo")) {
            Date dataMin, dataMax;
            String token1 = new String();
            String token2 = new String();

            //ricevuto anno e mese
            if (st.countTokens() == 2) {
                token1 = st.nextToken();
                token2 = st.nextToken();
                try {
                    DateFormat formatter = new SimpleDateFormat("dd MMMMM yyyy");
                    String dataMi = "01 " + token1 + " " + token2;
                    dataMin = formatter.parse(dataMi);
                    String dataMa = "31 " + token1 + " " + token2;
                    dataMax = formatter.parse(dataMa);
                    ordini = ordLocal.findByDataOrdine(dataMin, dataMax);

                } catch (ParseException ex) {
                }
            }
        }
        visione = true;
        disabled = true;
        aggiornamento = false;
        rimozione = false;
        return "loop";
    }

    public String cercaPannello() {
        fornitura.clear();
        StringTokenizer st = new StringTokenizer(chiaveRicerca, " -,;:");

        if (!st.hasMoreTokens()) {
            fornitura.clear();
            return "loop";
        }

        if (criterioPannello.equals("marcaNSerie")) {
            if (st.countTokens() == 1) {
                temporanei = panLocal.findAll();
                String tokenTemp = new String();

                while (true) {
                    if (!st.hasMoreTokens()) {
                        break;
                    }

                    tokenTemp = st.nextToken();

                    for (Pannello p : temporanei) {
                        if ((p.getMarca().toLowerCase().contains(tokenTemp.toLowerCase()) || p.getNserie().toLowerCase().contains(tokenTemp.toLowerCase())) && !fornitura.contains(p)) {
                            fornitura.add(p);
                        }
                    }
                }
            } else {
                if (st.countTokens() > 1) {
                    // query di verifica per input di marca multi token
                    fornitura = panLocal.findByMarca(chiaveRicerca);

                    if (fornitura.isEmpty()) {
                        StringBuilder sb = new StringBuilder();
                        boolean modello = false, possibileMarca = false;
                        String tokenTemp = new String();

                        tokenTemp = st.nextToken();//consumato primo token
                        fornitura = panLocal.findByMarca(tokenTemp);

                        if (!fornitura.isEmpty()) {
                            possibileMarca = true;
                        } else {
                            fornitura = panLocal.findByNSerie(tokenTemp);

                            //controllo allo stato q0
                            if (!fornitura.isEmpty()) {
                                modello = true;
                            }
                        }

                        //stato q8
                        if (modello) {
                            String token1 = tokenTemp; // token modello ricavato
                            tokenTemp = st.nextToken();//consumato primo token marca
                            sb.append(tokenTemp);

                            //controllo uscita stati
                            fornitura = panLocal.findByMarca(tokenTemp);

                            //stato q2 --> no risultato parziale preliminare
                            if (fornitura.isEmpty()) {
                                // stato q3
                                while (st.hasMoreTokens()) {
                                    sb.append(" ");
                                    tokenTemp = st.nextToken();
                                    sb.append(tokenTemp);

                                    if (!st.hasMoreTokens()) {
                                        break;
                                    }
                                }
                                String token2 = sb.toString(); //token marca ricavato

                                //stato q7
                                fornitura = panLocal.findByMarcaAndNSerie(token2, token1);
                            } else {
                                //caso di risultato parziale preliminare --> stato q4
                                while (true) {
                                    if (!st.hasMoreTokens()) {
                                        break;
                                    }

                                    sb.append(" ");
                                    tokenTemp = st.nextToken();
                                    sb.append(tokenTemp);
                                }
                                String token2 = sb.toString();

                                //stato q11
                                fornitura = panLocal.findByMarcaAndNSerie(token2, token1);
                            }
                        } else {
                            if (possibileMarca) {
                                if (!fornitura.isEmpty())// stato q4 --> risultato parziale
                                {
                                    String tokenEliminato = new String();  //modello
                                    sb.append(tokenTemp);

                                    while (true) {
                                        if (fornitura.isEmpty()) {
                                            tokenEliminato = sb.substring(sb.length() - tokenTemp.length(), sb.length());
                                            sb.delete(sb.length() - tokenTemp.length() - 1, sb.length());
                                            break;
                                        }
                                        sb.append(" ");
                                        tokenTemp = st.nextToken();
                                        sb.append(tokenTemp);
                                        fornitura = panLocal.findByTipo(sb.toString());
                                    }
                                    String token2 = sb.toString();// marca
                                    fornitura = panLocal.findByMarcaAndNSerie(token2, tokenEliminato);
                                }
                            } else {
                                //stato q3 ---> possibile input errato
                                sb.append(tokenTemp);
                                boolean inputErrato = false;

                                while (true) {
                                    if (!st.hasMoreTokens()) {
                                        inputErrato = true;
                                        break;
                                    }

                                    if (!fornitura.isEmpty()) {
                                        break;
                                    }

                                    sb.append(" ");
                                    tokenTemp = st.nextToken();
                                    sb.append(tokenTemp);

                                    fornitura = panLocal.findByMarca(sb.toString());
                                }

                                if (!inputErrato) {
                                    String token1 = sb.toString();// marca
                                    String token2 = st.nextToken(); //modello

                                    fornitura = panLocal.findByMarcaAndNSerie(token1, token2);
                                }
                            }
                        }
                    }
                }
            }
        } else if (criterioPannello.equals("marcaTip")) {
            if (st.countTokens() == 1) {
                temporanei = panLocal.findAll();
                String tokenTemp = new String();

                while (true) {
                    if (!st.hasMoreTokens()) {
                        break;
                    }

                    tokenTemp = st.nextToken();

                    for (Pannello p : temporanei) {
                        if ((p.getMarca().toLowerCase().contains(tokenTemp.toLowerCase()) || p.getTipo().toLowerCase().contains(tokenTemp.toLowerCase())) && !fornitura.contains(p)) {
                            fornitura.add(p);
                        }
                    }
                }
            } else {
                if (st.countTokens() > 1) {
                    //verifica marca multi token
                    fornitura = panLocal.findByMarca(chiaveRicerca);

                    if (fornitura.isEmpty()) //verifica tipologia multi token
                    {
                        fornitura = panLocal.findByTipo(chiaveRicerca);
                    }

                    if (fornitura.isEmpty()) {
                        StringBuilder sb = new StringBuilder();
                        String tokenTemp = st.nextToken(); //consumo primo token
                        boolean marca = false, tipologia = false;

                        //stato q0---> verifica caso
                        fornitura = panLocal.findByMarca(tokenTemp);
                        if (!fornitura.isEmpty()) {
                            marca = true;
                        } else {
                            fornitura = panLocal.findByTipo(tokenTemp);

                            //controllo allo stato q0
                            if (!fornitura.isEmpty()) {
                                tipologia = true;
                            }
                        }

                        // stato q1
                        if (marca) {
                            String token1 = tokenTemp; // marca

                            tokenTemp = st.nextToken();//consumato primo token tipologia
                            sb.append(tokenTemp);

                            //stato q2
                            while (true) {
                                if (!st.hasMoreTokens()) {
                                    break;
                                }

                                sb.append(" ");
                                tokenTemp = st.nextToken();
                                sb.append(tokenTemp);

                            }
                            String token2 = sb.toString(); //tipologia
                            fornitura = panLocal.findByMarcaAndTipo(token1, token2);
                        } else //stato q3
                        if (tipologia) {
                            String tokenEliminato = new String();
                            sb.append(tokenTemp);

                            while (true) {
                                if (fornitura.isEmpty()) {
                                    tokenEliminato = sb.substring(sb.length() - tokenTemp.length(), sb.length());
                                    sb.delete(sb.length() - tokenTemp.length() - 1, sb.length());
                                    break;
                                }

                                sb.append(" ");
                                tokenTemp = st.nextToken();
                                sb.append(tokenTemp);

                                fornitura = panLocal.findByTipo(sb.toString());
                            }
                            String token1 = sb.toString(); //tipologia
                            sb.delete(0, sb.length());
                            sb.append(tokenEliminato);
                            while (true) {
                                if (!st.hasMoreTokens()) {
                                    break;
                                }

                                sb.append(" ");
                                tokenTemp = st.nextToken();
                                sb.append(tokenTemp);
                            }
                            String token2 = sb.toString(); //marca

                            fornitura = panLocal.findByMarcaAndTipo(token2, token1);
                        } else {
                            //stato q3 ---> possibile input errato
                            sb.append(tokenTemp);
                            boolean inputErrato = false, eMarca = false, eTipologia = false;

                            while (true) {
                                if (!st.hasMoreTokens()) {
                                    inputErrato = true;
                                    break;
                                }

                                if (!fornitura.isEmpty()) {
                                    break;
                                }

                                sb.append(" ");
                                tokenTemp = st.nextToken();
                                sb.append(tokenTemp);

                                fornitura = panLocal.findByMarca(sb.toString());
                                if (!fornitura.isEmpty()) {
                                    eMarca = true;
                                } else {
                                    fornitura = panLocal.findByTipo(sb.toString());

                                    if (!fornitura.isEmpty()) {
                                        eTipologia = true;
                                    }
                                }
                            }

                            if (!inputErrato) {
                                if (eMarca) {
                                    String token1 = sb.toString(); //marca
                                    sb.delete(0, sb.length());

                                    while (true) {
                                        if (!st.hasMoreTokens()) {
                                            break;
                                        }
                                        tokenTemp = st.nextToken();
                                        sb.append(tokenTemp);

                                        if (st.hasMoreTokens()) {
                                            sb.append(" ");
                                        }
                                    }
                                    String token2 = sb.toString(); //tipologia
                                    fornitura = panLocal.findByMarcaAndTipo(token1, token2);

                                } else if (eTipologia) {
                                    String token1 = sb.toString(); //tipologia
                                    sb.delete(0, sb.length());

                                    while (true) {
                                        if (!st.hasMoreTokens()) {
                                            break;
                                        }
                                        tokenTemp = st.nextToken();
                                        sb.append(tokenTemp);

                                        if (st.hasMoreTokens()) {
                                            sb.append(" ");
                                        }
                                    }
                                    String token2 = sb.toString(); //marca
                                    fornitura = panLocal.findByMarcaAndTipo(token2, token1);
                                }
                            }
                        }
                    }
                }
            }
        } else if (criterioPannello.equals("tipoMod")) {
            if (st.countTokens() == 1) {
                temporanei = panLocal.findAll();
                String tokenTemp = new String();

                while (true) {
                    if (!st.hasMoreTokens()) {
                        break;
                    }

                    tokenTemp = st.nextToken();

                    for (Pannello p : temporanei) {
                        if ((p.getNserie().toLowerCase().contains(tokenTemp.toLowerCase()) || p.getTipo().toLowerCase().contains(tokenTemp.toLowerCase())) && !fornitura.contains(p)) {
                            fornitura.add(p);
                        }
                    }
                }
            } else {
                if (st.countTokens() > 1) {
                    // query di verifica per input di tipologia multi token
                    fornitura = panLocal.findByTipo(chiaveRicerca);

                    if (fornitura.isEmpty()) {
                        StringBuilder sb = new StringBuilder();
                        boolean modello = false, possibileTipologia = false;
                        String tokenTemp = new String();

                        tokenTemp = st.nextToken();//consumato primo token
                        fornitura = panLocal.findByTipo(tokenTemp);

                        if (!fornitura.isEmpty()) {
                            possibileTipologia = true;
                        } else {
                            fornitura = panLocal.findByNSerie(tokenTemp);

                            //controllo allo stato q0
                            if (!fornitura.isEmpty()) {
                                modello = true;
                            }
                        }

                        //stato q8
                        if (modello) {
                            String token1 = tokenTemp; // token modello ricavato
                            tokenTemp = st.nextToken();//consumato primo token tipologia
                            sb.append(tokenTemp);

                            //controllo uscita stati
                            fornitura = panLocal.findByTipo(tokenTemp);

                            //stato q2 --> no risultato parziale preliminare
                            if (fornitura.isEmpty()) {
                                // stato q3
                                while (st.hasMoreTokens()) {
                                    sb.append(" ");
                                    tokenTemp = st.nextToken();
                                    sb.append(tokenTemp);

                                    if (!st.hasMoreTokens()) {
                                        break;
                                    }
                                }
                                String token2 = sb.toString(); //token tipologia ricavato

                                //stato q7
                                fornitura = panLocal.findByTipoAndNSerie(token2, token1);
                            } else {
                                //caso di risultato parziale preliminare --> stato q4
                                while (true) {
                                    if (!st.hasMoreTokens()) {
                                        break;
                                    }

                                    sb.append(" ");
                                    tokenTemp = st.nextToken();
                                    sb.append(tokenTemp);
                                }
                                String token2 = sb.toString();

                                //stato q11
                                fornitura = panLocal.findByTipoAndNSerie(token2, token1);
                            }
                        } else {
                            if (possibileTipologia) {
                                if (!fornitura.isEmpty())// stato q4 --> risultato parziale
                                {
                                    String tokenEliminato = new String();  //modello
                                    sb.append(tokenTemp);

                                    while (true) {
                                        if (fornitura.isEmpty()) {
                                            tokenEliminato = sb.substring(sb.length() - tokenTemp.length(), sb.length());
                                            sb.delete(sb.length() - tokenTemp.length() - 1, sb.length());
                                            break;
                                        }

                                        sb.append(" ");
                                        tokenTemp = st.nextToken();
                                        sb.append(tokenTemp);

                                        fornitura = panLocal.findByTipo(sb.toString());
                                    }
                                    String token2 = sb.toString();// tipologia

                                    fornitura = panLocal.findByTipoAndNSerie(token2, tokenEliminato);
                                }
                            } else {
                                //stato q3 ---> possibile input errato
                                sb.append(tokenTemp);
                                boolean inputErrato = false;

                                while (true) {
                                    if (!st.hasMoreTokens()) {
                                        inputErrato = true;
                                        break;
                                    }

                                    if (!fornitura.isEmpty()) {
                                        break;
                                    }

                                    sb.append(" ");
                                    tokenTemp = st.nextToken();
                                    sb.append(tokenTemp);

                                    fornitura = panLocal.findByTipo(sb.toString());
                                }

                                if (!inputErrato) {
                                    String token1 = sb.toString();// tipologia
                                    String token2 = st.nextToken(); //modello

                                    fornitura = panLocal.findByTipoAndNSerie(token1, token2);
                                }
                            }
                        }
                    }
                }
            }
        } else if (criterioPannello.equals("prezzo")) {
            try {
                double min = Double.parseDouble(st.nextToken());

                if (!st.hasMoreTokens()) {
                    fornitura = panLocal.findByPrezzo(min, min);
                } else {
                    double max = Double.parseDouble(st.nextToken());
                    fornitura = panLocal.findByPrezzo(min, max);
                }
            } catch (NumberFormatException e) {
                fornitura.clear();
                e.toString();
            }
        }
        visione = true;
        return "loop";
    }

    public String cercaFornitore() {
        fornitori.clear();
        StringTokenizer st = new StringTokenizer(chiaveRicerca, " -,;:");

        if (!st.hasMoreTokens()) {
            fornitori.clear();
            return "loop";
        }

        if (criterioFornitore.equals("nome")) {
            tempFor = forLocal.findAll();
            String tokenTemp = new String();

            while (true) {
                if (!st.hasMoreTokens()) {
                    break;
                }

                tokenTemp = st.nextToken();

                for (Fornitore d : tempFor) {
                    if ((d.getNome().toLowerCase().contains(tokenTemp.toLowerCase()) || d.getCognome().toLowerCase().contains(tokenTemp.toLowerCase())) && !fornitori.contains(d)) {
                        fornitori.add(d);
                    }
                }
            }
        } else if (criterioFornitore.equals("ditta")) {
            if (st.countTokens() == 1) {
                tempFor = forLocal.findAll();
                String tokenTemp = new String();

                while (true) {
                    if (!st.hasMoreTokens()) {
                        break;
                    }

                    tokenTemp = st.nextToken();

                    for (Fornitore d : tempFor) {
                        if (d.getDitta().toLowerCase().contains(tokenTemp.toLowerCase()) && !fornitori.contains(d)) {
                            fornitori.add(d);
                        }
                    }
                }
            } else {
                fornitori.add(forLocal.findByDitta(chiaveRicerca));
            }
        } else if (criterioFornitore.equals("iva")) {
            fornitori.add(forLocal.findByPartitaIVA(chiaveRicerca));
        } else if (criterioFornitore.equals("citta")) {
            if (st.countTokens() == 1) {
                tempFor = forLocal.findAll();
                String tokenTemp = new String();

                while (true) {
                    if (!st.hasMoreTokens()) {
                        break;
                    }

                    tokenTemp = st.nextToken();

                    for (Fornitore d : tempFor) {
                        if (d.getCitta().toLowerCase().contains(tokenTemp.toLowerCase()) && !fornitori.contains(d)) {
                            fornitori.add(d);
                        }
                    }
                }
            } else {
                fornitori = forLocal.findByCitta(chiaveRicerca);
            }
        } else if (criterioFornitore.equals("provincia")) {
            if (st.countTokens() == 1) {
                tempFor = forLocal.findAll();
                String tokenTemp = new String();

                while (true) {
                    if (!st.hasMoreTokens()) {
                        break;
                    }

                    tokenTemp = st.nextToken();

                    for (Fornitore d : tempFor) {
                        if (d.getProvincia().toLowerCase().contains(tokenTemp.toLowerCase()) && !fornitori.contains(d)) {
                            fornitori.add(d);
                        }
                    }
                }
            } else {
                fornitori = forLocal.findByProvincia(chiaveRicerca);
            }
        }
        visione = true;
        return "loop";

    }

    public String continua() {
        if (fase1) {
            criterioFornitore = "nome";
            chiaveRicerca = null;
            fornitori.clear();
            visione = false;
            fase1 = false;
            fase2 = true;
        } else if (fase2) {
            addSelected();
            criterioPannello = "marcaNSerie";
            chiaveRicerca = null;
            fornitura.clear();
            visione = false;
            fase2 = false;
            fase3 = true;
            riepilogo = true;
        }
        return "loop";
    }

    private void addSelected() {
        if (!fornitura.isEmpty()) {
            for (Pannello p : fornitura) {
                if (p.isSelected()) {
                    if (!temporanei.contains(p)) {
                        temporanei.add(p);
                    }
                }
            }
        }
    }

    public String seleziona(Integer id) {
        fornitore = forLocal.findById(id);
        fornitori.clear();
        criterioFornitore = "nome";
        chiaveRicerca = null;
        visione = false;
        fase1 = false;
        fase2 = true;
        return "loop";
    }

    public String indietro() {
        if (fase1) {
            if (visione) {
                visione = false;
                return "loop";
            } else {
                criterioFornitore = "nome";
                chiaveRicerca = null;
                fornitori.clear();
                fornitura.clear();
                temporanei.clear();
                fornitore = null;
                return "mag";
            }
        } else if (fase2) {
            addSelected();
            if (visione) {
                visione = false;
                return "loop";
            } else {
                chiaveRicerca = null;
                criterioPannello = "marcaNSerie";
                fornitura.clear();
                fase2 = fase1;
                fase1 = true;
                return "loop";
            }
        } else if (fase3) {
            if (riepilogo) {
                riepilogo = false;
                fase3 = false;
                errore = false;
                fase2 = true;
                return "loop";

            } else if (info) {
                info = false;
                riepilogo = true;
                return "loop";
            } else if (merci) {
                merci = false;
                riepilogo = true;
                return "loop";
            }
        }
        return "loop";
    }

    public int quan() {
        int q = 0;

        if (!temporanei.isEmpty()) {
            for (Pannello p : temporanei) {
                q += p.getQuantOrdine();
            }
        }

        return q;
    }

    public double tot() {
        double t = 0;

        if (!temporanei.isEmpty()) {
            for (Pannello p : temporanei) {
                t = t + (p.getQuantOrdine() * p.getPrezzo());
            }
        }
        return t;
    }

    public String quasiTerminati() {
        fornitura = panLocal.findByQuantita(50);
        visione = true;
        return "loop";
    }

    public String info() {
        riepilogo = false;
        info = true;
        return "loop";
    }

    public String fornitura() {
        riepilogo = false;
        merci = true;
        return "loop";
    }

    public String rimuovi(Integer id) {
        pannelloTemp = getPannello(temporanei, id);
        temporanei.remove(pannelloTemp);
        return "loop";
    }

    private Pannello getPannello(List<Pannello> lista, Integer id) {
        for (Pannello p : lista) {
            if (p.getIdpannello() == id) {
                return p;
            }
        }
        return null;
    }

    public String eliminaOrdine(Integer id) {
        ordine = getOrdine(ordini, id);
        rimozione = true;
        visione = false;
        return "loop";
    }

    public String aggiornaStato(Integer id) {
        ordine = getOrdine(ordini, id);
        visione = false;
        aggiornamento = true;
        return "loop";
    }
    
    private Ordinepannelli getOrdine(List<Ordinepannelli> lista, Integer id) {
        for (Ordinepannelli o : lista) {
            if (o.getIdordine() == id) {
                return o;
            }
        }
        return null;
    }

    public String annullaAggiornamento() {
        aggiornamento = false;
        disabled = true;
        visione = true;
        ordini = ordLocal.findAll();
        return "loop";
    }

    public String confermaAggiornamento() {
        aggiornamento = false;
        disabled = true;
        visione = true;
        ordLocal.edit(ordine);
        if (ordine.getStato().equalsIgnoreCase("ricevuto")) {
            for (Pannello p : aggQta) {
                for (Pannello p1 : panLocal.findAll()) {
                    if (p.getMarca().equalsIgnoreCase(p1.getMarca()) && p.getTipo().equalsIgnoreCase(p1.getTipo())) {
                        p1.setQuantita(p1.getQuantita() + p.getQuantOrdine());
                        panLocal.edit(p1);
                    }
                }
            }
            Date oggi = new Date();
            man = manLocal.findByMansione("Amministratore");
            amministratore = dipLocal.findByMansione(man).get(0);
            String codice = generaCodice(ordine.getTotale().intValue(), oggi.hashCode());
            fattura.setCodice(codice);
            fattura.setValore(totale);
            fattura.setContabile(amministratore);
            fattura.setDatarilascio(oggi);
            fattura.setOrdine(ordine);
            fattura.setValore(ordine.getTotale());
            fatLocal.create(fattura);
            amministratore = null;
            fattura = new Fatturafornitore();
        }
        aggQta.clear();
        return "loop";
    }

    public String annullaEliminazione() {
        visione = true;
        rimozione = false;
        return "loop";
    }

    public String confermaEliminazione() {
        ordini.remove(ordine);

        if (ordini.isEmpty()) {
            rimozione = false;
            ordLocal.remove(ordine);
            return "loop";
        }
        visione = true;
        rimozione = false;
        ordLocal.remove(ordine);
        return "loop";
    }

    public String annulla() {
        fornitore = null;
        temporanei.clear();
        fase3 = false;
        errore = false;
        fase1 = true;
        return "loop";
    }

    public String ind() {
        if (aggiornamento) {
            aggiornamento = false;
            disabled = true;
            visione = true;
            return "loop";
        }
        if (rimozione) {
            rimozione = false;
            visione = true;
            return "loop";
        }
        if (visione) {
            visione = false;
            return "loop";
        }
        chiaveRicerca = null;
        criterioOrdine = "ditta";
        ordini.clear();
        continua = false;
        disable = true;
        return "mag";
    }

    public String inoltra() {
        if (temporanei.isEmpty() || fornitore == null) {
            errore = true;
            return "loop";
        }

        ordine.setPannelli(temporanei);
        for (Pannello a : temporanei) {
            aggQta.add(a);
        }
        //ordine.setFornitura(creaStringa(temporanei));
        ordine.setStato("Inoltrato");
        ordine.setQuantita(quan());
        ordine.setTotale(tot());
        totale = ordine.getTotale();
        ordine.setFornitore(fornitore);
        ordLocal.create(ordine);
        fornitore = null;
        temporanei.clear();
        ordine = new Ordinepannelli();
        fase3 = false;
        fase1 = true;
        return "mag";
    }

    public void abilitaPulsante(ValueChangeEvent event) {
        disabled = false;
    }

    private String generaCodice(int fattura, int data) {

        int x = fattura + data;
        if (x < 0) {
            x *= (-1);
        }
        String s = Integer.toString(x);
        if (fatLocal.findByCodice(s) == null) {
            return s;
        } else {
            while (fatLocal.findByCodice(s) != null) {
                x++;
                s = Integer.toString(x);
            }
        }
        s = Integer.toString(x);
        return s;
    }
}
