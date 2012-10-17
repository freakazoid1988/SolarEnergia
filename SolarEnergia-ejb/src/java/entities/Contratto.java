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
@Table(name = "contratto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contratto.findAll", query = "SELECT c FROM Contratto c"),
    @NamedQuery(name = "Contratto.findByIdcontratto", query = "SELECT c FROM Contratto c WHERE c.idcontratto = :idcontratto"),
    @NamedQuery(name = "Contratto.findByID", query = "SELECT c FROM Contratto c WHERE c.idcontratto = :idcontratto"),
    @NamedQuery(name = "Contratto.findByCodice", query = "SELECT c FROM Contratto c WHERE c.codice = :codice"),
    @NamedQuery(name = "Contratto.findByValore", query = "SELECT c FROM Contratto c WHERE c.valore >= :min AND c.valore <= :max"),
    @NamedQuery(name = "Contratto.findByAddetto", query = "SELECT c FROM Contratto c WHERE c.addvendite = :addvendite"),
    @NamedQuery(name = "Contratto.findByDatafirma", query = "SELECT c FROM Contratto c WHERE c.datafirma >= :dataMin AND c.datafirma <= :dataMax")})
public class Contratto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idcontratto;
    @Size(max = 50)
    @Column(name = "codice")
    private String codice;
    @Column(name = "valore")
    private Float valore;
    @Column(name = "datafirma")
    @Temporal(TemporalType.DATE)
    private Date datafirma;
    @Size(max = 255)
    @Column(name = "stato")
    private String stato;
    
    @Transient
    private boolean selected;
        
    @JoinColumn(name = "progetto", referencedColumnName = "idprogetto")
    @OneToOne(fetch = FetchType.LAZY)
    private Progetto progetto;
    
    @JoinColumn(name = "addvendite", referencedColumnName = "iddipendente")
    @ManyToOne(fetch = FetchType.LAZY)
    private Dipendente addvendite;
    
    @JoinColumn(name = "cliente", referencedColumnName = "idcliente")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;
    
    @OneToMany(mappedBy = "contratto", fetch = FetchType.LAZY)
    private List<Fatturacliente> fatturaclienteList;

    public Contratto() {
    }

    public Contratto(Integer idcontratto) {
        this.idcontratto = idcontratto;
    }

    public Integer getIdcontratto() {
        return idcontratto;
    }

    public void setIdcontratto(Integer idcontratto) {
        this.idcontratto = idcontratto;
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

    public Progetto getProgetto() {
        return progetto;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public void setProgetto(Progetto progetto) {
        this.progetto = progetto;
    }

    public Dipendente getAddvendite() {
        return addvendite;
    }

    public void setAddvendite(Dipendente addvendite) {
        this.addvendite = addvendite;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @XmlTransient
    public List<Fatturacliente> getFatturaclienteList() {
        return fatturaclienteList;
    }

    public void setFatturaclienteList(List<Fatturacliente> fatturaclienteList) {
        this.fatturaclienteList = fatturaclienteList;
    }

    public Date getDatafirma() {
        return datafirma;
    }

    public void setDatafirma(Date datafirma) {
        this.datafirma = datafirma;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontratto != null ? idcontratto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contratto)) {
            return false;
        }
        Contratto other = (Contratto) object;
        if ((this.idcontratto == null && other.idcontratto != null) || (this.idcontratto != null && !this.idcontratto.equals(other.idcontratto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Contratto[ idcontratto=" + idcontratto + " ]";
    }
}
