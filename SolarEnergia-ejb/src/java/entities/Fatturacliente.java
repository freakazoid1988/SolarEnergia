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
@Table(name = "fatturacliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fatturacliente.findAll", query = "SELECT f FROM Fatturacliente f"),
    @NamedQuery(name = "Fatturacliente.findByIdfattura", query = "SELECT f FROM Fatturacliente f WHERE f.idfattura = :idfattura"),
    @NamedQuery(name = "Fatturacliente.findByCodice", query = "SELECT f FROM Fatturacliente f WHERE f.codice = :codice"),
    @NamedQuery(name = "Fatturacliente.findByContabile", query = "SELECT f FROM Fatturacliente f WHERE f.contabile = :contabile"),
    @NamedQuery(name = "Fatturacliente.findByContratto", query = "SELECT f FROM Fatturacliente f WHERE f.contratto = :contratto"),
    @NamedQuery(name = "Fatturacliente.findByDataRilascio", query = "SELECT f FROM Fatturacliente f WHERE f.datarilascio >= :dateMin AND f.datarilascio <= :dateMax"),
    @NamedQuery(name = "Fatturacliente.findByValore", query = "SELECT f FROM Fatturacliente f WHERE f.valore >= :min AND f.valore <= :max")})
public class Fatturacliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idfattura;
    @Basic(optional = false)
    @Column(name = "codice")
    private String codice;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valore")
    private Float valore;
    @Column(name = "datarilascio")
    @Temporal(TemporalType.DATE)
    private Date datarilascio;
    
    
    @JoinColumn(name = "contratto", referencedColumnName = "idcontratto")
    @ManyToOne(fetch = FetchType.LAZY)
    private Contratto contratto;
    
    @JoinColumn(name = "contabile", referencedColumnName = "iddipendente")
    @ManyToOne(fetch = FetchType.LAZY)
    private Dipendente contabile;
    

    public Fatturacliente() {
    }

    public Fatturacliente(Integer idfattura) {
        this.idfattura = idfattura;
    }

    public Fatturacliente(Integer idfattura, String codice) {
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

    public Float getValore() {
        return valore;
    }

    public void setValore(Float valore) {
        this.valore = valore;
    }

    public Date getDataRilascio() {
        return datarilascio;
    }

    public void setDataRilascio(Date datarilascio) {
        this.datarilascio = datarilascio;
    }

    public Dipendente getContabile() {
        return contabile;
    }

    public void setContabile(Dipendente contabile) {
        this.contabile = contabile;
    }

    public Contratto getContratto() {
        return contratto;
    }

    public void setContratto(Contratto contratto) {
        this.contratto = contratto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfattura != null ? idfattura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fatturacliente)) {
            return false;
        }
        Fatturacliente other = (Fatturacliente) object;
        if ((this.idfattura == null && other.idfattura != null) || (this.idfattura != null && !this.idfattura.equals(other.idfattura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Fatturacliente[ idfattura=" + idfattura + " ]";
    }
}
