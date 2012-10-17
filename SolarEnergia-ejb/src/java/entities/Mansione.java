/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Balordo
 */
@Entity
@Table(name = "mansione")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mansione.findAll", query = "SELECT m FROM Mansione m"),
    @NamedQuery(name = "Mansione.findByIdmansione", query = "SELECT m FROM Mansione m WHERE m.idmansione = :idmansione"),
    @NamedQuery(name = "Mansione.findByMansione", query = "SELECT m FROM Mansione m WHERE m.mansione = :mansione")})
public class Mansione implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idmansione;
    @Size(max = 50)
    @Column(name = "mansione")
    private String mansione;
    @OneToMany(mappedBy = "mansione", fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    private List<Dipendente> dipendenteList;

    public Mansione() {
    }

    public Mansione(Integer idmansione) {
        this.idmansione = idmansione;
    }
    
    public Mansione(String mansione) {
        this.mansione = mansione;
    }

    public Integer getIdmansione() {
        return idmansione;
    }

    public void setIdmansione(Integer idmansione) {
        this.idmansione = idmansione;
    }
    
    public String getMansione() {
        return mansione;
    }

    public void setMansione(String mansione) {
        this.mansione = mansione;
    }

    @XmlTransient
    public List<Dipendente> getDipendenteList() {
        return dipendenteList;
    }

    public void setDipendenteList(List<Dipendente> dipendenteList) {
        this.dipendenteList = dipendenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmansione != null ? idmansione.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mansione)) {
            return false;
        }
        Mansione other = (Mansione) object;
        if ((this.idmansione == null && other.idmansione != null) || (this.idmansione != null && !this.idmansione.equals(other.idmansione))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Mansione[ idmansione=" + idmansione + " ]";
    }
}
