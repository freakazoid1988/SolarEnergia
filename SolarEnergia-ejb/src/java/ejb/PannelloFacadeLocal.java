/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Pannello;
import entities.Progetto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Balordo
 */
@Local
public interface PannelloFacadeLocal {

    void create(Pannello pannello);
    void edit(Pannello pannello);
    void remove(Pannello pannello);
    Pannello findByID(Integer id);
    Pannello findBySerie(String serie);
    List<Pannello> findByProgetto(Progetto p);
    List<Pannello> findByTipo(String tipo);
    List<Pannello> findByMarca(String marca);
    List<Pannello> findByNSerie(String nserie);
    List<Pannello> findByMarcaAndNSerie(String marca, String nserie);
    List<Pannello> findByMarcaAndTipo(String marca, String tipo);
    List<Pannello> findByTipoAndNSerie(String tipo, String nserie);
    List<Pannello> findByPrezzo(double min, double max);
    List<Pannello> findByQuantita(int qta);
    List<Pannello> findAll();
    int count();
}
