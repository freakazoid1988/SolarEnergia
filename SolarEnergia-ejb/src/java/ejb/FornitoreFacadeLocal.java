/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Fornitore;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Balordo
 */
@Local
public interface FornitoreFacadeLocal {

    void create(Fornitore fornitore);
    void edit(Fornitore fornitore);
    void remove(Fornitore fornitore);
    Fornitore findById(Integer id);
    Fornitore findByPartitaIVA(String partitaIVA);
    Fornitore findByDitta(String ditta);
    List<Fornitore> findByCognome(String cognome);
    List<Fornitore> findByCognomeAndNome(String cognome, String nome);
    List<Fornitore> findByCitta(String chiaveRicerca);
    List<Fornitore> findByProvincia(String chiaveRicerca);
    List<Fornitore> findAll();
    int count();
}
