/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Login;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Balordo
 */
@Local
public interface LoginFacadeLocal {

    void create(Login login);
    void edit(Login login);
    void remove(Login login);
    Login findByID(Integer id);
    Login findByUsername(String user);
    Login findByUsernameAndPassword(String user, String pass);
    List<Login> findAll();
    int count();    
}
