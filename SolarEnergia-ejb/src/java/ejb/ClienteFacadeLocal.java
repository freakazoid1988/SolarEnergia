/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Cliente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Balordo
 */
@Local
public interface ClienteFacadeLocal {

    void create(Cliente cliente);
    void edit(Cliente cliente);
    void remove(Cliente cliente);
    Cliente findByID(Integer id);
    Cliente findByCodFiscale(String CF);
    List<Cliente> findByCognome(String cognome);
    List<Cliente> findByCognomeAndNome(String cognome, String nome);
    List<Cliente> findAll();
    int count();    
}
