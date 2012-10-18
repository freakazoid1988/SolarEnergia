/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Balordo
 */
@Entity
@Table(name = "rilievi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rilievi.findAll", query = "SELECT r FROM Rilievi r"),
    @NamedQuery(name = "Rilievi.findByIdrilievi", query = "SELECT r FROM Rilievi r WHERE r.idrilievi = :idrilievi"),
    @NamedQuery(name = "Rilievi.findByCliente", query = "SELECT r FROM Rilievi r WHERE r.cliente = :cliete"),
    @NamedQuery(name = "Rilievi.findByPotenzaimpianto", query = "SELECT r FROM Rilievi r WHERE r.potenzaimpianto = :potenzaimpianto"),
    @NamedQuery(name = "Rilievi.findByLocazione", query = "SELECT r FROM Rilievi r WHERE r.locazione = :locazione"),
    @NamedQuery(name = "Rilievi.findByTipopannello", query = "SELECT r FROM Rilievi r WHERE r.tipopannello = :tipopannello"),
    @NamedQuery(name = "Rilievi.findByQualitapannello", query = "SELECT r FROM Rilievi r WHERE r.qualitapannello = :qualitapannello")})
public class Rilievi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrilievi")
    private Integer idrilievi;
    @Size(max = 255)
    @Column(name = "potenzaimpianto")
    private String potenzaimpianto;
    @Size(max = 255)
    @Column(name = "locazione")
    private String locazione;
    @Size(max = 255)
    @Column(name = "tipopannello")
    private String tipopannello;
    @Size(max = 255)
    @Column(name = "qualitapannello")
    private String qualitapannello;
    
    @JoinColumn(name = "cliente", referencedColumnName = "idcliente")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @Transient
    private boolean selected;
    
    public Rilievi() {
    }

    public Rilievi(Integer idrilievi) {
        this.idrilievi = idrilievi;
    }

    public Integer getIdrilievi() {
        return idrilievi;
    }

    public void setIdrilievi(Integer idrilievi) {
        this.idrilievi = idrilievi;
    }
    
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public String getPotenzaimpianto() {
        return potenzaimpianto;
    }

    public void setPotenzaimpianto(String potenzaimpianto) {
        this.potenzaimpianto = potenzaimpianto;
    }

    public String getLocazione() {
        return locazione;
    }

    public void setLocazione(String locazione) {
        this.locazione = locazione;
    }

    public String getTipopannello() {
        return tipopannello;
    }

    public void setTipopannello(String tipopannello) {
        this.tipopannello = tipopannello;
    }

    public String getQualitapannello() {
        return qualitapannello;
    }

    public void setQualitapannello(String qualitapannello) {
        this.qualitapannello = qualitapannello;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrilievi != null ? idrilievi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rilievi)) {
            return false;
        }
        Rilievi other = (Rilievi) object;
        if ((this.idrilievi == null && other.idrilievi != null) || (this.idrilievi != null && !this.idrilievi.equals(other.idrilievi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Rilievi[ idrilievi=" + idrilievi + " ]";
    }
    
}
