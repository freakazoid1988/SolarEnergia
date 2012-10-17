/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Balordo
 */
@Entity
@Table(name = "salario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salario.findAll", query = "SELECT s FROM Salario s"),
    @NamedQuery(name = "Salario.findByIdsalario", query = "SELECT s FROM Salario s WHERE s.idsalario = :idsalario"),
    @NamedQuery(name = "Salario.findByValore", query = "SELECT s FROM Salario s WHERE s.valore >= :min AND s.valore <= :max"),
    @NamedQuery(name = "Salario.findByDatainizio", query = "SELECT s FROM Salario s WHERE s.datainizio = :datainizio")})
public class Salario implements Serializable {

    
    private static final long serialVersionUID = 1L;
    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idsalario;
    @Column(name = "datainizio")
    @Temporal(TemporalType.DATE)
    private Date datainizio;
    @Column(name = "valore")
    private double valore;
    
    
    @OneToMany(mappedBy = "salario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Dipendente> dipendenteList;

    public Salario() {
    }

    public Salario(Integer idsalario) {
        this.idsalario = idsalario;
    }

    public Salario(double salario) {
        this.valore = salario;
    }

    public Integer getIdsalario() {
        return idsalario;
    }

    public void setIdsalario(Integer idsalario) {
        this.idsalario = idsalario;
    }

    @XmlTransient
    public List<Dipendente> getDipendenteList() {
        return dipendenteList;
    }

    public void setDipendenteList(List<Dipendente> dipendenteList) {
        this.dipendenteList = dipendenteList;
    }
    
    
    @Override
    public String toString() {
        return "entities.Salario[ idsalario=" + idsalario + " ]";
    }
    public Date getDatainizio() {
        return datainizio;
    }

    public void setDatainizio(Date datainizio) {
        this.datainizio = datainizio;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsalario != null ? idsalario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salario)) {
            return false;
        }
        Salario other = (Salario) object;
        if ((this.idsalario == null && other.idsalario != null) || (this.idsalario != null && !this.idsalario.equals(other.idsalario))) {
            return false;
        }
        return true;
    }


    public double getValore() {
        return valore;
    }

    public void setValore(double valore) {
        this.valore = valore;
    }
}
