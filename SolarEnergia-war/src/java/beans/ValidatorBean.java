/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejb.*;
import entities.Mansione;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Balordo
 */
public class ValidatorBean implements Serializable {

    @EJB
    DipendenteFacadeLocal dipBean;
    @EJB
    ClienteFacadeLocal clienteBean;
    @EJB
    MansioneFacadeLocal manBean;
    @EJB
    LoginFacadeLocal loginBean;
    @EJB
    ContrattoFacadeLocal contrattoBean;
    @EJB
    ProgettoFacadeLocal progettoBean;
    @EJB
    FornitoreFacadeLocal fornitoreLocal;

    public void validaNome(FacesContext context, UIComponent component, Object value) {
        String nome = (String) value;
        FacesMessage messaggio = new FacesMessage();
        messaggio.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (nome.equals("")) {
            messaggio.setSummary("Attenzione! il campo 'nome' è obbligatorio!");
            context.addMessage("Form:Nome", messaggio);
            throw new ValidatorException(messaggio);
        }

        if (nome.length() > 50) {
            messaggio.setSummary("Superata la lunghezza massima per il campo 'nome'");
            context.addMessage("Form:Nome", messaggio);
            throw new ValidatorException(messaggio);
        }

        for (int i = 0; i < nome.length(); i++) {
            if (!Character.isLetter(nome.charAt(i)) && !Character.isWhitespace(nome.charAt(i))) {
                messaggio.setSummary("Sono presenti caratteri scorretti nel campo 'nome'");
                context.addMessage("Form:Nome", messaggio);
                throw new ValidatorException(messaggio);
            }
        }
    }

    public void validaCognome(FacesContext context, UIComponent component, Object value) {
        String cognome = (String) value;
        FacesMessage messaggio = new FacesMessage();
        messaggio.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (cognome.equals("")) {
            messaggio.setSummary("Attenzione! il campo 'cognome' è obbligatorio!");
            context.addMessage("Form:Cognome", messaggio);
            throw new ValidatorException(messaggio);
        }

        if (cognome.length() > 50) {
            messaggio.setSummary("Superata la lunghezza massima per il campo 'cognome'");
            context.addMessage("Form:Cognome", messaggio);
            throw new ValidatorException(messaggio);
        }

        for (int i = 0; i < cognome.length(); i++) {
            if (!Character.isLetter(cognome.charAt(i)) && !Character.isWhitespace(cognome.charAt(i))) {
                messaggio.setSummary("Sono presenti caratteri scorretti nel cognome");
                context.addMessage("Form:Cognome", messaggio);
                throw new ValidatorException(messaggio);
            }
        }
    }

    public void validaPassword(FacesContext context, UIComponent component, Object value) {
        String pass = (String) value;
        FacesMessage messaggio = new FacesMessage();
        messaggio.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (pass.equals("")) {
            messaggio.setSummary("Attenzione! il campo 'password' è obbligatorio!");
            context.addMessage("Form:Nome", messaggio);
            throw new ValidatorException(messaggio);
        }

        if (pass.length() < 6 || pass.length() > 16) {
            messaggio.setSummary("Attenzione! Inserire una password lunga da 6 a 16 caratteri!");
            context.addMessage("Form:Nome", messaggio);
            throw new ValidatorException(messaggio);
        }

        if (!alfaNum(pass)) {
            messaggio.setSummary("Attenzione! Inserire una password alfanumerica, sono ammessi solo i caratteri [a-z],[0-9]");
            context.addMessage("Form:Nome", messaggio);
            throw new ValidatorException(messaggio);
        }

        if (pass.contains("password")) {
            messaggio.setSummary("Attenzione! La password non può contenere la parola 'password'!");
            context.addMessage("Form:Nome", messaggio);
            throw new ValidatorException(messaggio);
        }
    }

    public void validaCodiceFiscale(FacesContext context, UIComponent component, Object value) {
        String codFiscale = (String) value;
        int cifre = 0;
        FacesMessage messaggio = new FacesMessage();
        messaggio.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (codFiscale.equals("")) {
            messaggio.setSummary("Attenzione! il campo 'codice fiscale' è obbligatorio!");
            context.addMessage("Form:CodFiscale", messaggio);
            throw new ValidatorException(messaggio);
        }

        if (codFiscale.length() != 16) {
            messaggio.setSummary("è stato inserito un codice fiscale non valido");
            context.addMessage("Form:CodFiscale", messaggio);
            throw new ValidatorException(messaggio);
        }

        for (int i = 0; i < codFiscale.length(); i++) {
            if (Character.isWhitespace(codFiscale.charAt(i))) {
                messaggio.setSummary("Inserire il codice fiscale senza spazi");
                context.addMessage("Form:CodFiscale", messaggio);
                throw new ValidatorException(messaggio);
            }

            if (!Character.isLetter(codFiscale.charAt(i))) {
                cifre++;
            }
        }

        if (cifre != 7) {
            messaggio.setSummary("è stato inserito un codice fiscale non valido");
            context.addMessage("Form:CodFiscale", messaggio);
            throw new ValidatorException(messaggio);
        }

        if (dipBean.findByCodFiscale(codFiscale) != null) {
            messaggio.setSummary("Il codice fiscale inserito è già presente!");
            context.addMessage("Form:CodFiscale", messaggio);
            throw new ValidatorException(messaggio);
        }
        if (clienteBean.findByCodFiscale(codFiscale) != null) {
            messaggio.setSummary("Il codice fiscale inserito è già presente!");
            context.addMessage("Form:CodFiscale", messaggio);
            throw new ValidatorException(messaggio);
        }
    }

    public void validaMansione(FacesContext context, UIComponent component, Object value) {
        String nomeMansione = (String) value;
        FacesMessage messaggio = new FacesMessage();
        messaggio.setSeverity(FacesMessage.SEVERITY_ERROR);
        Mansione mansione = manBean.findByMansione(nomeMansione);

        if (nomeMansione.equals("")) {
            messaggio.setSummary("Attenzione! il campo 'mansione' è obbligatorio!");
            context.addMessage("Form:Mansione", messaggio);
            throw new ValidatorException(messaggio);
        }

        if (nomeMansione.equalsIgnoreCase("amministratore") && !dipBean.findByMansione(mansione).isEmpty()) {
            messaggio.setSummary("Esiste già un amministratore");
            context.addMessage("Form:mansione", messaggio);
            throw new ValidatorException(messaggio);
        }

        if (manBean.findByMansione(nomeMansione) != null) {
            messaggio.setSummary("La mansione inserita è già presente!");
            context.addMessage("Form:mansione", messaggio);
            throw new ValidatorException(messaggio);
        }
    }

    public void validaListaMansioni(FacesContext context, UIComponent component, Object value) {
        String nomeMansione = (String) value;
        FacesMessage messaggio = new FacesMessage();
        messaggio.setSeverity(FacesMessage.SEVERITY_ERROR);
        Mansione mansione = manBean.findByMansione(nomeMansione);

        if (nomeMansione.equalsIgnoreCase("amministratore") && !dipBean.findByMansione(mansione).isEmpty()) {
            messaggio.setSummary("Esiste già un amministratore");
            context.addMessage("Form:mansione", messaggio);
            throw new ValidatorException(messaggio);
        }
    }

    public void validaSalario(FacesContext context, UIComponent component, Object value) {
        double salario = (Double) value;
        FacesMessage messaggio = new FacesMessage();
        messaggio.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (salario < 0) {
            messaggio.setSummary("Il salario non può essere negativo");
            context.addMessage("Form:salario", messaggio);
            throw new ValidatorException(messaggio);
        }
    }

    public void validaUsername(FacesContext context, UIComponent component, Object value) {
        String username = (String) value;
        FacesMessage messaggio = new FacesMessage();
        messaggio.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (loginBean.findByUsername(username) != null) {
            messaggio.setSummary("L'username inserito è già presente!");
            context.addMessage("Form:mansione", messaggio);
            throw new ValidatorException(messaggio);
        }
    }

    public void validaTelefono(FacesContext context, UIComponent component, Object value) {
        String telefono = (String) value;
        FacesMessage messaggio = new FacesMessage();
        messaggio.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (telefono.equals("")) {
            messaggio.setSummary("Attenzione! il campo 'telefono' è obbligatorio!");
            context.addMessage("Form:Telefono", messaggio);
            throw new ValidatorException(messaggio);
        }

        if (telefono.length() != 10) {
            messaggio.setSummary("Il numero di telefono inserito non è corretto");
            context.addMessage("Form:Telefono", messaggio);
            throw new ValidatorException(messaggio);
        }

        for (int i = 0; i < telefono.length(); i++) {
            if (Character.isLetter(telefono.charAt(i)) || Character.isWhitespace(telefono.charAt(i))) {
                messaggio.setSummary("Sono presenti caratteri scorretti nel numero di telefono");
                context.addMessage("Form:Telefono", messaggio);
                throw new ValidatorException(messaggio);
            }
        }
    }

    public void validaCodiceContratto(FacesContext context, UIComponent component, Object value) {
        String codice = (String) value;
        FacesMessage messaggio = new FacesMessage();
        messaggio.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (codice.equals("")) {
            messaggio.setSummary("Attenzione! il campo 'codice' è obbligatorio!");
            context.addMessage("Form:codice", messaggio);
            throw new ValidatorException(messaggio);
        }

        if (contrattoBean.findByCodice(codice) != null) {
            messaggio.setSummary("Il codice inserito è già presente!");
            context.addMessage("Form:codice", messaggio);
            throw new ValidatorException(messaggio);
        }
    }

    public void validaCodiceProgetto(FacesContext context, UIComponent component, Object value) {
        String codice = (String) value;
        FacesMessage messaggio = new FacesMessage();
        messaggio.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (codice.equals("")) {
            messaggio.setSummary("Attenzione! il campo 'codice' è obbligatorio!");
            context.addMessage("Form:codice", messaggio);
            throw new ValidatorException(messaggio);
        }

        if (progettoBean.findByCodice(codice) != null) {
            messaggio.setSummary("Il codice inserito è già presente!");
            context.addMessage("Form:codice", messaggio);
            throw new ValidatorException(messaggio);
        }
    }

    public void validaData(FacesContext context, UIComponent component, Object value) throws ParseException {

        DateFormat formatter;
        Date date = (Date) value;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(date);
        FacesMessage messaggio = new FacesMessage();
        messaggio.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (date == null) {
            messaggio.setSummary("Attenzione! il campo 'data' è obbligatorio!");
            context.addMessage("Form:datainizio", messaggio);
            throw new ValidatorException(messaggio);
        }

        if (!s.matches("\\d{4}-[01]\\d-[0-3]\\d")) {
            messaggio.setSummary("Attenzione! Inserire una data valida!");
            context.addMessage("Form:datainizio", messaggio);
            throw new ValidatorException(messaggio);
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        try {
            format.parse(s);
        } catch (ParseException e) {
            messaggio.setSummary("Attenzione! Inserire una data valida!");
            context.addMessage("Form:datainizio", messaggio);
            throw new ValidatorException(messaggio);
        }


    }

    public void validaValore(FacesContext context, UIComponent component, Object value) {
        float valore = (Float) value;
        FacesMessage messaggio = new FacesMessage();
        messaggio.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (valore <= 0) {
            messaggio.setSummary("Il valore del contratto non può essere negativo o uguale a zero");
            context.addMessage("Form:valore", messaggio);
            throw new ValidatorException(messaggio);
        }
    }

    public void validaEMail(FacesContext context, UIComponent component, Object value) {
        String s = (String) value;
        FacesMessage messaggio = new FacesMessage();
        messaggio.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (!valuta(s)) {
            messaggio.setSummary("Attenzione! L'indirizzo e-mail fornito non è valido!");
            context.addMessage("Form:valore", messaggio);
            throw new ValidatorException(messaggio);
        }
    }

    private boolean alfaNum(String pass) {

        int n = 0;
        int s = 0;
        char prova;
        for (int i = 0; i < pass.length(); i++) {
            prova = pass.charAt(i);
            if (Character.isLetter(prova)) {
                s++;
            } else if (Character.isDigit(prova)) {
                n++;
            } else {
                return false;
            }
        }

        if (s != 0 && n != 0) {
            return true;
        }

        return false;
    }

    public void validaCitta(FacesContext context, UIComponent component, Object value) {
        String citta = (String) value;
        FacesMessage messaggio = new FacesMessage();
        messaggio.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (citta.equals("")) {
            messaggio.setSummary("Attenzione il campo 'città' è obbligatorio!");
            context.addMessage("Form:Citta", messaggio);
            throw new ValidatorException(messaggio);

        }

        for (int i = 0; i < citta.length(); i++) {
            if (!Character.isLetter(citta.charAt(i)) && !Character.isSpaceChar(citta.charAt(i))) {
                messaggio.setSummary("Sono presenti caratteri scorretti nel campo 'città'!");
                context.addMessage("Form:Citta", messaggio);
                throw new ValidatorException(messaggio);
            }
        }

        if (citta.length() > 50) {
            messaggio.setSummary("Attenzione il campo 'città' ha superato la lunghezza max!");
            context.addMessage("Form:Citta", messaggio);
            throw new ValidatorException(messaggio);
        }
    }

    public void validaProvincia(FacesContext context, UIComponent component, Object value) {
        String provincia = (String) value;
        FacesMessage messaggio = new FacesMessage();
        messaggio.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (provincia.equals("")) {
            messaggio.setSummary("Attenzione il campo 'provincia' è obbligatorio!");
            context.addMessage("Form:Provincia", messaggio);
            throw new ValidatorException(messaggio);

        }

        for (int i = 0; i < provincia.length(); i++) {
            if (!Character.isLetter(provincia.charAt(i)) && !Character.isSpaceChar(provincia.charAt(i))) {
                messaggio.setSummary("Sono presenti caratteri scorretti nel campo 'provincia'!");
                context.addMessage("Form:Provincia", messaggio);
                throw new ValidatorException(messaggio);
            }
        }

        if (provincia.length() > 50) {
            messaggio.setSummary("Attenzione il campo 'provincia' ha superato la lunghezza massima!");
            context.addMessage("Form:Provincia", messaggio);
            throw new ValidatorException(messaggio);
        }
    }

    public void validaPartitaIva(FacesContext context, UIComponent component, Object value) {
        String partitaIva = (String) value;
        FacesMessage messaggio = new FacesMessage();
        messaggio.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (partitaIva.equals("")) {
            messaggio.setSummary("Attenzione il campo 'partita iva' è obbligatorio!");
            context.addMessage("Form:PartitaIva", messaggio);
            throw new ValidatorException(messaggio);

        }

        for (int i = 0; i < partitaIva.length(); i++) {
            if (!Character.isDigit(partitaIva.charAt(i))) {
                messaggio.setSummary("Attenzione! La partita IVA inserita deve composta da soli numeri!");
                context.addMessage("Form:PartitaIva", messaggio);
                throw new ValidatorException(messaggio);
            }
        }

        if (partitaIva.length() != 11) {
            messaggio.setSummary("Attenzione! La partita IVA inserita contiene più di 11 cifre!");
            context.addMessage("Form:PartitaIva", messaggio);
            throw new ValidatorException(messaggio);
        }

        if (fornitoreLocal.findByPartitaIVA(partitaIva) != null) {
            messaggio.setSummary("Partita IVA già esistente!");
            context.addMessage("Form:PartitaIva", messaggio);
            throw new ValidatorException(messaggio);
        }
    }

    public void validaDitta(FacesContext context, UIComponent component, Object value) {
        String ditta = (String) value;
        FacesMessage messaggio = new FacesMessage();
        messaggio.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (ditta.equals("")) {
            messaggio.setSummary("Attenzione il campo 'ditta' è obbligatorio!");
            context.addMessage("Form:Ditta", messaggio);
            throw new ValidatorException(messaggio);
        }

        if (ditta.length() > 50) {
            messaggio.setSummary("Superata la lunghezza massima per il campo 'ditta'!");
            context.addMessage("Form:Ditta", messaggio);
            throw new ValidatorException(messaggio);
        }

        if (fornitoreLocal.findByDitta(ditta) != null) {
            messaggio.setSummary("Esiste già la seguente ditta!");
            context.addMessage("Form:Ditta", messaggio);
            throw new ValidatorException(messaggio);
        }
    }

    private boolean valuta(String s) {
        int c = 0;
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '@') {
                c++;
            } else if (s.charAt(i) == '.') {
                p++;
            }
            if (s.charAt(1) == '@' || s.charAt(s.length() - 1) == '@') {
                return false;
            }
        }
        if (c != 1 || p == 0) {
            return false;
        }

        return true;
    }

//    public void validaStato(FacesContext context, UIComponent component, Object value) {
//        String stato = (String) value;
//        FacesMessage messaggio = new FacesMessage();
//        messaggio.setSeverity(FacesMessage.SEVERITY_ERROR);
//
//        if (!(stato.equalsIgnoreCase("inoltrato") || stato.equalsIgnoreCase("ricevuto") || stato.equalsIgnoreCase("cancellato"))) {
//            messaggio.setSummary("Attenzione! Gli unici stati validi per un ordine sono 'Inoltrato' 'Ricevuto' 'Cancellato'!");
//            context.addMessage("Form:Stato", messaggio);
//            throw new ValidatorException(messaggio);
//        }
//    }

    public void validaQuantita(FacesContext context, UIComponent component, Object value) {
        int quantita = (Integer) value;
        FacesMessage messaggio = new FacesMessage();
        messaggio.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (quantita < 0) {
            messaggio.setSummary("Attenzione il campo 'quantità' deve contenere un valore positivo!");
            context.addMessage("Form:Quantita", messaggio);
            throw new ValidatorException(messaggio);
        }

        if (quantita > 999999) {
            messaggio.setSummary("Attenzione superato il limite per il campo 'quantità'");
            context.addMessage("Form:Quantita", messaggio);
            throw new ValidatorException(messaggio);
        }
    }

    public void validaQtaOrdine(FacesContext context, UIComponent component, Object value) {
        int quantita = (Integer) value;
        FacesMessage messaggio = new FacesMessage();
        messaggio.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (quantita <= 0) {
            messaggio.setSummary("Attenzione il campo 'quantità' deve contenere un valore superiore a zero!");
            context.addMessage("Form:Quantita", messaggio);
            throw new ValidatorException(messaggio);
        }

        if (quantita > 999999) {
            messaggio.setSummary("Attenzione superato il limite per il campo 'quantità'");
            context.addMessage("Form:Quantita", messaggio);
            throw new ValidatorException(messaggio);
        }
    }
}
