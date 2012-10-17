/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejb.ContrattoFacadeLocal;
import ejb.DipendenteFacadeLocal;
import ejb.FatturaclienteFacadeLocal;
import ejb.FatturafornitoreFacadeLocal;
import entities.Contratto;
import entities.Dipendente;
import entities.Fatturacliente;
import entities.Fatturafornitore;
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
public class FatturaBean implements Serializable {

    private String criterio, parolaChiave;
    private List<Fatturafornitore> fattureFor, fornitoreTemp;
    private List<Fatturacliente> fattureCli, clienteTemp;
    private List<Dipendente> addetti;
    private boolean visione, continua, disable;
    @EJB
    FatturafornitoreFacadeLocal fatForLocal;
    @EJB
    FatturaclienteFacadeLocal fatCliLocal;
    @EJB
    ContrattoFacadeLocal contLocal;
    @EJB
    DipendenteFacadeLocal dipLocal;

    public FatturaBean() {
        fattureFor = new LinkedList<Fatturafornitore>();
        fattureCli = new LinkedList<Fatturacliente>();
        fornitoreTemp = new LinkedList<Fatturafornitore>();
        clienteTemp = new LinkedList<Fatturacliente>();
        addetti = new LinkedList<Dipendente>();
        continua = false;
        disable = true;
    }

    public String getParolaChiave() {
        return parolaChiave;
    }

    public void setParolaChiave(String parolaChiave) {
        this.parolaChiave = parolaChiave;
    }

    public List<Fatturafornitore> getFattureFor() {
        return fattureFor;
    }

    public void setFattureFor(List<Fatturafornitore> fattureFor) {
        this.fattureFor = fattureFor;
    }

    public boolean isVisione() {
        return visione;
    }

    public void setVisione(boolean visione) {
        this.visione = visione;
    }

    public List<Dipendente> getAddetti() {
        return addetti;
    }

    public void setAddetti(List<Dipendente> addetti) {
        this.addetti = addetti;
    }

    public List<Fatturacliente> getClienteTemp() {
        return clienteTemp;
    }

    public void setClienteTemp(List<Fatturacliente> clienteTemp) {
        this.clienteTemp = clienteTemp;
    }

    public boolean isContinua() {
        return continua;
    }

    public void setContinua(boolean continua) {
        this.continua = continua;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public List<Fatturafornitore> getFornitoreTemp() {
        return fornitoreTemp;
    }

    public void setFornitoreTemp(List<Fatturafornitore> fornitoreTemp) {
        this.fornitoreTemp = fornitoreTemp;
    }

    public List<Fatturacliente> getFattureCli() {
        return fattureCli;
    }

    public void setFattureCli(List<Fatturacliente> fattureCli) {
        this.fattureCli = fattureCli;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public String tutteFatFornitori() {
        fornitoreTemp.clear();
        fornitoreTemp = fatForLocal.findAll();
        if (fornitoreTemp.size() > 10) {
            disable = false;
            fattureFor = fornitoreTemp.subList(0, 10);
        } else {
            fattureFor = fornitoreTemp;
        }
        visione = true;
        return "loop";
    }

    public String continuesF() {
        fattureFor.clear();
        if (fornitoreTemp.size() > 10) {
            disable = false;
            continua = true;
            fattureFor = fornitoreTemp.subList(0, 10);

        } else {
            continua = false;
            disable = true;
            fattureFor = fornitoreTemp;
        }
        return "loop";
    }

    public String tutteFatCliente() {
        clienteTemp.clear();
        clienteTemp = fatCliLocal.findAll();
        if (clienteTemp.size() > 10) {
            disable = false;
            fattureCli = clienteTemp.subList(0, 10);
        } else {
            fattureCli = clienteTemp;
        }
        visione = true;
        return "loop";
    }
    
    public String continuesC() {
        fattureCli.clear();
        if (clienteTemp.size() > 10) {
            disable = false;
            continua = true;
            fattureCli = clienteTemp.subList(0, 10);

        } else {
            continua = false;
            disable = true;
            fattureCli = clienteTemp;
        }
        return "loop";
    }

    public String search(boolean cliente) {
        clear();
        visione = true;
        StringTokenizer st = new StringTokenizer(parolaChiave, " ,;:");

        if (!st.hasMoreTokens()) {
            visione = false;
            return "loop";
        }

        String token = st.nextToken();

        if (criterio.equals("codice")) {
            if (cliente) {
                fattureCli.add(fatCliLocal.findByCodice(token));
            } else {
                fattureFor.add(fatForLocal.findByCodice(token));
            }
        } else if (criterio.equals("contratto")) {
            Contratto c = contLocal.findByCodice(token);

            if (c != null) {
                fattureCli.addAll(fatCliLocal.findByContratto(c));
            }
        } else if (criterio.equals("addetto")) {
            if (cliente) {
                if (!st.hasMoreTokens()) {
                    addetti = dipLocal.findByCognome(token);
                    for (Dipendente d : addetti) {
                        fattureCli.addAll(fatCliLocal.findByContabile(d));
                    }
                } else {
                    String token2 = st.nextToken();
                    addetti = dipLocal.findByCognomeAndNome(token, token2);
                    for (Dipendente d : addetti) {
                        fattureCli.addAll(fatCliLocal.findByContabile(d));

                    }
                }
            } else {
                if (!st.hasMoreTokens()) {
                    addetti = dipLocal.findByCognome(token);
                    for (Dipendente d : addetti) {
                        fattureFor.addAll(fatForLocal.findByContabile(d));
                    }
                } else {
                    String token2 = st.nextToken();
                    addetti = dipLocal.findByCognomeAndNome(token, token2);
                    for (Dipendente d : addetti) {
                        fattureFor.addAll(fatForLocal.findByContabile(d));
                    }
                }

            }
        } else if (criterio.equals("valore")) {
            try {
                double min = Double.parseDouble(token);
                if (!st.hasMoreTokens()) {
                    if (cliente) {
                        fattureCli = fatCliLocal.findByValore(min, min);
                    } else {
                        fattureFor = fatForLocal.findByValore(min, min);
                    }
                } else {
                    String token2 = st.nextToken();
                    double max = Double.parseDouble(token2);
                    if (cliente) {
                        fattureCli = fatCliLocal.findByValore(min, max);
                    } else {
                        fattureFor = fatForLocal.findByValore(min, max);
                    }
                }
            } catch (NumberFormatException e) {
                clear();
                e.toString();
            }
        } else {
            try {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date dateMin, dateMax;
                String s = token + "-01" + "-01";
                dateMin = (Date) formatter.parse(s);
                String s1 = token + "-12" + "-31";
                dateMax = (Date) formatter.parse(s1);

                if (!st.hasMoreTokens()) {
                    if (cliente) {
                        fattureCli = fatCliLocal.findByDataRilascio(dateMin, dateMax);
                    } else {
                        fattureFor = fatForLocal.findByDataRilascio(dateMin, dateMax);
                    }
                } else {
                    String token2 = st.nextToken();
                    Date dateMin1, dateMax1;
                    String ss = token + "-" + token2 + "-01";
                    dateMin1 = (Date) formatter.parse(ss);
                    String ss1 = token + "-" + token2 + "-31";
                    dateMax1 = (Date) formatter.parse(ss1);

                    if (cliente) {
                        fattureCli = fatCliLocal.findByDataRilascio(dateMin1, dateMax1);
                    } else {
                        fattureFor = fatForLocal.findByDataRilascio(dateMin1, dateMax1);
                    }
                }
            } catch (ParseException pe) {
                clear();
                pe.toString();
            }
        }

        return "loop";
    }

    private void clear() {
        fattureCli.clear();
        fattureFor.clear();
        clienteTemp.clear();
        fornitoreTemp.clear();
        addetti.clear();
    }

    public String ind(String s) {
        parolaChiave = null;
        criterio = "nome";
        visione = false;
        continua = false;
        disable = true;
        clear();
        return s;
    }
}
