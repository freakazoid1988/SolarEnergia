/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejb.DipendenteFacadeLocal;
import ejb.LoginFacadeLocal;
import ejb.MansioneFacadeLocal;
import entities.Dipendente;
import entities.Login;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author Balordo
 */
public class LogBean implements Serializable
{
    private static final long serialVersionUID = 1L;

    String user,pass;
    Dipendente logDip;
    boolean errore;

    @EJB
    LoginFacadeLocal loginBean;

    @EJB
    MansioneFacadeLocal manBean;

    @EJB
    DipendenteFacadeLocal dipBean;

    public boolean isErrore()
    {
        return errore;
    }

    public void setErrore(boolean errore)
    {
        this.errore = errore;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public Dipendente getLogDip()
    {
        return logDip;
    }

    public void setLogDip(Dipendente logDip)
    {
        this.logDip = logDip;
    }

    public String verifica()
    {
        Login l = loginBean.findByUsernameAndPassword(user, pass);

        if(l == null)
        {
            errore = true;
            return "loop";
        }
        
        logDip = l.getDipendente();
        errore = false;
        String mansione = logDip.getMansione().getMansione();

        if(mansione.equalsIgnoreCase("amministratore"))
            return "admin";

        if(mansione.equalsIgnoreCase("ingegnere"))
            return "ingegnere";

        return "addVendite";
    }

    public String logout()
    {        
        user = null;
        pass = null;
        errore = false;
        logDip = null;
        return "logout";
    }
}
