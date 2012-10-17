/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Salario;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Balordo
 */
@Local
public interface SalarioFacadeLocal {

    void create(Salario salario);
    void edit(Salario salario);
    void remove(Salario salario);
    Salario findByID(Integer id);
    List<Salario> findByValore(float min, float max);
    List<Salario> findByDataInizio(Date data);
    List<Salario> findAll();
    int count();    
}
