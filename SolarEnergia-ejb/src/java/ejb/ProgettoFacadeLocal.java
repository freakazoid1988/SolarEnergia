/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Cliente;
import entities.Dipendente;
import entities.Progetto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Balordo
 */
@Local
public interface ProgettoFacadeLocal {

    void create(Progetto progetto);
    void edit(Progetto progetto);
    void remove(Progetto progetto);
    Progetto findByID(Integer id);
    Progetto findByCodice(String cod); //modificare codice da integer a string???
    List<Progetto> findByCliente(Cliente c);
    List<Progetto> findByIngegnere(Dipendente d);
    List<Progetto> findAll();
    int count();    
}
