/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Fornitore;
import entities.Ordinepannelli;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Balordo
 */
@Local
public interface OrdinepannelliFacadeLocal {

    void create(Ordinepannelli ordinepannelli);
    void edit(Ordinepannelli ordinepannelli);
    void remove(Ordinepannelli ordinepannelli);
    Ordinepannelli findByID(Integer id);
    List<Ordinepannelli> findByFornitore(Fornitore f);
    List<Ordinepannelli> findByDataOrdine(Date dataMin, Date dataMax);
    List<Ordinepannelli> findByStato(String ricevuto);
    List<Ordinepannelli> findByRicevutoAndData(String evaso, Date dataMin, Date dataMax);
    List<Ordinepannelli> findByTotaleAndStato(double min, double min0, String evaso);
    List<Ordinepannelli> findAll();
    int count();
}
