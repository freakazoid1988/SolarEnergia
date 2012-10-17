/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Cliente;
import entities.Rilievi;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Balordo
 */
@Local
public interface RilieviFacadeLocal {

    void create(Rilievi rilievi);

    void edit(Rilievi rilievi);

    void remove(Rilievi rilievi);

    Rilievi findByID(Integer id);

    List<Rilievi> findByCliente(Cliente c);
    
    List<Rilievi> findAll();

    int count();
    
}
