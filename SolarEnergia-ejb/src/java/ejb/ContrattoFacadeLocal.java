/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Cliente;
import entities.Contratto;
import entities.Dipendente;
import entities.Progetto;
import java.util.List;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Balordo
 */
@Local
public interface ContrattoFacadeLocal {

    void create(Contratto contratto);
    void edit(Contratto contratto);
    void remove(Contratto contratto);
    Contratto findByID(Integer id);
    Contratto findByCodice(String cod);
    Contratto findByProgetto(Progetto p);
    List<Contratto> findByCliente(Cliente cliente);
    List<Contratto> findByAddetto(Dipendente addVendite);
    List<Contratto> findByDataFirma(Date data);
    List<Contratto> findByDataFirma(Date dataMin, Date dataMax);
    List<Contratto> findByValore(double min, double min0);
    List<Contratto> findAll();
    int count();
}
