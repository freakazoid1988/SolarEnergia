/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejb.*;
import entities.Contratto;
import entities.Mansione;
import entities.Progetto;
import entities.Salario;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import javax.ejb.EJB;

/**
 *
 * @author Balordo
 */
public class RicercaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    DipendenteFacadeLocal dipBean;
    @EJB
    ContrattoFacadeLocal contBean;
    @EJB
    SalarioFacadeLocal salBean;
    @EJB
    ProgettoFacadeLocal progBean;
    @EJB
    MansioneFacadeLocal manBean;
    @EJB
    ClienteFacadeLocal clienteBean;
    private String parolaChiave, criterio;
    private boolean visible, continua, disable;
    private List risultati, temp;

    public RicercaBean() {
        risultati = new LinkedList();
        temp = new LinkedList();
        criterio = "nome";
        continua = false;
        disable = true;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public String getParolaChiave() {
        return parolaChiave;
    }

    public void setParolaChiave(String parolaChiave) {
        this.parolaChiave = parolaChiave;
    }

    public List getRisultati() {
        return risultati;
    }

    public void setRisultati(List risultati) {
        this.risultati = risultati;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public boolean isContinua() {
        return continua;
    }

    public void setContinua(boolean continua) {
        this.continua = continua;
    }

    public List getTemp() {
        return temp;
    }

    public void setTemp(List temp) {
        this.temp = temp;
    }

    public String search(boolean cliente) {
        visible = true;
        StringTokenizer st = new StringTokenizer(parolaChiave, " -,;:");

        if (!st.hasMoreTokens()) {
            risultati.clear();
            return "loop";
        }

        String token = st.nextToken();

        if (criterio.equals("nome")) {
            if (!st.hasMoreTokens()) {
                risultati = (cliente) ? clienteBean.findByCognome(token) : dipBean.findByCognome(token);
            } else {
                String token2 = st.nextToken();
                risultati = dipBean.findByCognomeAndNome(token, token2);

                if (risultati.isEmpty()) {
                    risultati = dipBean.findByCognomeAndNome(token2, token);
                }
            }
        } else if (criterio.equals("contratto")) {
            Contratto c = contBean.findByCodice(token);
            risultati.clear();

            if (c != null) {
                risultati.add(c.getAddvendite());
            }
        } else if (criterio.equals("progetto")) {
            Progetto p = progBean.findByCodice(token);
            risultati.clear();

            if (p != null) {
                risultati.add(p.getIngegnere());
            }
        } else if (criterio.equals("mansione")) {
            String mansione = token;

            while (st.hasMoreTokens()) {
                mansione += " " + st.nextToken();
            }

            Mansione man = manBean.findByMansione(mansione);
            risultati.clear();

            if (man != null) {
                risultati = dipBean.findByMansione(man);
            }
        } else {
            try {
                int min = Integer.parseInt(token);
                risultati.clear();

                if (!st.hasMoreTokens()) {

                    for (Salario s : salBean.findByValore(min, min)) {
                        risultati.add(dipBean.findBySalario(s));
                    }
                } else {
                    int max = Integer.parseInt(st.nextToken());
                    for (Salario s : salBean.findByValore(min, max)) {
                        risultati.add(dipBean.findBySalario(s));
                    }
                }
            } catch (NumberFormatException e) {
                risultati.clear();
                e.toString();
            }
        }
        return "loop";
    }

    public String searchAll(boolean cliente) {
        temp.clear();
        if (cliente) {
            temp = clienteBean.findAll();
            if (temp.size() > 10) {
                disable = false;
                risultati = temp.subList(0, 10);
            } else {
                risultati = temp;
            }
        } else {
            temp = dipBean.findAll();
            if (temp.size() > 10) {
                disable = false;
                risultati = temp.subList(0, 10);
            } else {
                risultati = temp;
            }
        }
        visible = true;
        return "loop";
    }
    
    public String continues() {
        risultati.clear();
        if (temp.size() > 10) {
            disable = false;
            continua = true;
            risultati = temp.subList(0, 10);

        } else {
            continua = false;
            disable = true;
            risultati = temp;
        }

        return "loop";
    }

    public String clear(String s) {
        parolaChiave = null;
        criterio = "nome";
        visible = false;
        continua = false;
        disable = true;
        risultati.clear();
        return s;
    }
}
