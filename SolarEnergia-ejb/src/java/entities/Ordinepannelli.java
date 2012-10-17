/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "ordinepannelli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordinepannelli.findAll", query = "SELECT o FROM Ordinepannelli o"),
    @NamedQuery(name = "Ordinepannelli.findByIdordine", query = "SELECT o FROM Ordinepannelli o WHERE o.idordine = :idordine"),
    @NamedQuery(name = "Ordinepannelli.findByQuantita", query = "SELECT o FROM Ordinepannelli o WHERE o.quantita = :quantita"),
    @NamedQuery(name=  "Ordinepannelli.findByRicevutoAndData",query="SELECT o FROM Ordinepannelli o WHERE o.stato= :stato AND o.dataordine>=:dataMin AND o.dataordine<= :dataMax"),
    @NamedQuery(name=  "Ordinepannelli.findByTotaleAndStato",query="SELECT o FROM Ordinepannelli o WHERE o.totale >= :min AND o.totale <=:max AND o.stato<= :stato"),
    @NamedQuery(name = "Ordinepannelli.findByTotale", query = "SELECT o FROM Ordinepannelli o WHERE o.totale = :totale"),
    @NamedQuery(name = "Ordinepannelli.findByStato", query = "SELECT o FROM Ordinepannelli o WHERE o.stato = :stato"),
    @NamedQuery(name = "Ordinepannelli.findByFornitore", query = "SELECT o FROM Ordinepannelli o WHERE o.fornitore = :fornitore"),
    @NamedQuery(name = "Ordinepannelli.findByDataordine", query = "SELECT o FROM Ordinepannelli o WHERE o.dataordine >= :dataMin AND o.dataordine <= :dataMax")})
public class Ordinepannelli implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idordine;
    @Column(name = "quantita")
    private Integer quantita;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totale")
    private Double totale;
    @Size(max = 255)
    @Column(name = "stato")
    private String stato;
    @Column(name = "dataordine")
    @Temporal(TemporalType.DATE)
    private Date dataordine;
    
    @Transient
    private boolean selected;
    
    @OneToMany(mappedBy = "ordine", fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
    private List<Fatturafornitore> fatturafornitoreList;
        
    @JoinColumn(name = "fornitore", referencedColumnName = "idfornitore")
    @ManyToOne(fetch = FetchType.LAZY)
    private Fornitore fornitore;
    
    @JoinTable(joinColumns = {
        @JoinColumn}, inverseJoinColumns = {
        @JoinColumn})
    @ManyToMany(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
    private List<Pannello> pannelli;

    public Ordinepannelli() {
    }

    public Ordinepannelli(Integer idordine) {
        this.idordine = idordine;
    }

    public Integer getIdordine() {
        return idordine;
    }

    public void setIdordine(Integer idordine) {
        this.idordine = idordine;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public Double getTotale() {
        return totale;
    }

    public void setTotale(Double totale) {
        this.totale = totale;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Fornitore getFornitore() {
        return fornitore;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setFornitore(Fornitore fornitore) {
        this.fornitore = fornitore;
    }

    public List<Pannello> getPannelli() {
        return pannelli;
    }

    public void setPannelli(List<Pannello> pannelli) {
        this.pannelli = pannelli;
    }
    
        public Date getDataordine() {
        return dataordine;
    }

    public void setDataordine(Date dataordine) {
        this.dataordine = dataordine;
    }

    @XmlTransient
    public List<Fatturafornitore> getFatturafornitoreList() {
        return fatturafornitoreList;
    }

    public void setFatturafornitoreList(List<Fatturafornitore> fatturafornitoreList) {
        this.fatturafornitoreList = fatturafornitoreList;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idordine != null ? idordine.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordinepannelli)) {
            return false;
        }
        Ordinepannelli other = (Ordinepannelli) object;
        if ((this.idordine == null && other.idordine != null) || (this.idordine != null && !this.idordine.equals(other.idordine))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ordinepannelli[ idordine=" + idordine + " ]";
    }
}
