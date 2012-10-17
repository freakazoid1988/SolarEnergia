/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Balordo
 */
@Entity
@Table(name = "fatturafornitore")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fatturafornitore.findAll", query = "SELECT f FROM Fatturafornitore f"),
    @NamedQuery(name = "Fatturafornitore.findByIdfattura", query = "SELECT f FROM Fatturafornitore f WHERE f.idfattura = :idfattura"),
    @NamedQuery(name = "Fatturafornitore.findByCodice", query = "SELECT f FROM Fatturafornitore f WHERE f.codice = :codice"),
    @NamedQuery(name = "Fatturafornitore.findByContabile", query = "SELECT f FROM Fatturafornitore f WHERE f.contabile = :contabile"),
    @NamedQuery(name = "Fatturafornitore.findByOrdine", query = "SELECT f FROM Fatturafornitore f WHERE f.ordine = :ordine"),
    @NamedQuery(name = "Fatturafornitore.findByValore", query = "SELECT f FROM Fatturafornitore f WHERE f.valore >= :min AND f.valore <= :max"),
    @NamedQuery(name = "Fatturafornitore.findByDataRilascio", query = "SELECT f FROM Fatturafornitore f WHERE f.datarilascio >= :dateMin AND f.datarilascio <= :dateMax")})
public class Fatturafornitore implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfattura")
    private Integer idfattura;
    @Basic(optional = false)
    @Column(name = "codice")
    private String codice;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valore")
    private Double valore;
    @Column(name = "datarilascio")
    @Temporal(TemporalType.DATE)
    private Date datarilascio;
    
    @JoinColumn(name = "ordine", referencedColumnName = "idordine")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ordinepannelli ordine;
    
    @JoinColumn(name = "contabile", referencedColumnName = "iddipendente")
    @ManyToOne(fetch = FetchType.LAZY)
    private Dipendente contabile;
    

    public Fatturafornitore() {
    }

    public Fatturafornitore(Integer idfattura) {
        this.idfattura = idfattura;
    }

    public Fatturafornitore(Integer idfattura, String codice) {
        this.idfattura = idfattura;
        this.codice = codice;
    }

    public Integer getIdfattura() {
        return idfattura;
    }

    public void setIdfattura(Integer idfattura) {
        this.idfattura = idfattura;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public Double getValore() {
        return valore;
    }

    public void setValore(Double valore) {
        this.valore = valore;
    }

    public Date getDatarilascio() {
        return datarilascio;
    }

    public void setDatarilascio(Date datarilascio) {
        this.datarilascio = datarilascio;
    }

    public Ordinepannelli getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordinepannelli ordine) {
        this.ordine = ordine;
    }

    public Dipendente getContabile() {
        return contabile;
    }

    public void setContabile(Dipendente contabile) {
        this.contabile = contabile;
    }
  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += 3 * (idfattura != null ? idfattura.hashCode() : hash++);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fatturafornitore)) {
            return false;
        }
        Fatturafornitore other = (Fatturafornitore) object;
        if ((this.idfattura == null && other.idfattura != null) || (this.idfattura != null && !this.idfattura.equals(other.idfattura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Fatturafornitore[ idfattura=" + idfattura + " ]";
    }  
}
