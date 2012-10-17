/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Mansione;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Balordo
 */
@Local
public interface MansioneFacadeLocal {

    void create(Mansione mansione);
    void edit(Mansione mansione);
    void remove(Mansione mansione);
    Mansione findByID(Integer id);
    Mansione findByMansione(String man);
    List<Mansione> findAll();
    int count();    
}
