/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Dipendente;
import entities.Fatturafornitore;
import entities.Ordinepannelli;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Balordo
 */
@Local
public interface FatturafornitoreFacadeLocal {

    void create(Fatturafornitore fatturafornitore);

    void edit(Fatturafornitore fatturafornitore);

    void remove(Fatturafornitore fatturafornitore);

    Fatturafornitore findByID(Integer id);

    Fatturafornitore findByCodice(String cod);

    List<Fatturafornitore> findByContabile(Dipendente addetto);

    List<Fatturafornitore> findByOrdine(Ordinepannelli ord);

    List<Fatturafornitore> findByValore(double min, double max);

    List<Fatturafornitore> findByDataRilascio(Date dateMin, Date dateMax);

    List<Fatturafornitore> findAll();

    int count();
}
