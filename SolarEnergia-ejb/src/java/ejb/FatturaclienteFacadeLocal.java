/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Contratto;
import entities.Dipendente;
import entities.Fatturacliente;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Balordo
 */
@Local
public interface FatturaclienteFacadeLocal {

    void create(Fatturacliente fatturacliente);
    void edit(Fatturacliente fatturacliente);
    void remove(Fatturacliente fatturacliente);
    Fatturacliente findByID(Integer id);
    Fatturacliente findByCodice(String token);
    List<Fatturacliente> findByContabile(Dipendente addetto);
    List<Fatturacliente> findByContratto(Contratto cont);
    List<Fatturacliente> findByValore(double min, double max);
    List<Fatturacliente> findByDataRilascio(Date dateMin, Date dateMax);
    List<Fatturacliente> findAll();
    int count();
}
