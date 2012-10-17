/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Dipendente;
import entities.Mansione;
import entities.Salario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Balordo
 */
@Local
public interface DipendenteFacadeLocal {

    void create(Dipendente dipendente);
    void edit(Dipendente dipendente);
    void remove(Dipendente dipendente);
    Dipendente findByID(Integer id);
    Dipendente findByCodFiscale(String CF);
    Dipendente findBySalario(Salario s);
    List<Dipendente> findByCognome(String cognome);
    List<Dipendente> findByCognomeAndNome(String cognome, String nome);
    List<Dipendente> findByMansione(Mansione mansione);
    List<Dipendente> findAll();
    int count();    
}
