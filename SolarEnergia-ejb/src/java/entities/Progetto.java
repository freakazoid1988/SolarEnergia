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
@Table(name = "progetto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Progetto.findAll", query = "SELECT p FROM Progetto p"),
    @NamedQuery(name = "Progetto.findByIdprogetto", query = "SELECT p FROM Progetto p WHERE p.idprogetto = :idprogetto"),
    @NamedQuery(name = "Progetto.findByID", query = "SELECT p FROM Progetto p WHERE p.idprogetto = :idprogetto"),
    @NamedQuery(name = "Progetto.findByCodice", query = "SELECT p FROM Progetto p WHERE p.codice = :codice"),
    @NamedQuery(name = "Progetto.findByIngegnere", query = "SELECT p FROM Progetto p WHERE p.ingegnere = :ingegnere")})
public class Progetto implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idprogetto;
    @Size(max = 50)
    @Column(name = "codice")
    private String codice;
    
    @Column(name = "quantitapannelli")
    private Integer quantitapannelli;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valore")
    private Float valore;
    @Size(max = 50)
    @Column(name = "tipopannello")
    private String tipopannello;
    @Size(max = 50)
    @Column(name = "marcapannello")
    private String marcapannello;
    
    @JoinColumn(name = "ingegnere", referencedColumnName = "iddipendente")
    @ManyToOne(fetch = FetchType.LAZY)
    private Dipendente ingegnere;
    
    @JoinColumn(name = "cliente", referencedColumnName = "idcliente")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;
    
    @JoinTable(joinColumns = {
        @JoinColumn}, inverseJoinColumns = {
            @JoinColumn})
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<Pannello> pannelli;
    
    @OneToOne(mappedBy = "progetto", fetch = FetchType.LAZY)
    private Contratto contratto;

    public Progetto() {
    }

    public Progetto(Integer idprogetto) {
        this.idprogetto = idprogetto;
    }

    public Integer getIdprogetto() {
        return idprogetto;
    }

    public void setIdprogetto(Integer idprogetto) {
        this.idprogetto = idprogetto;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public Dipendente getIngegnere() {
        return ingegnere;
    }

    public void setIngegnere(Dipendente ingegnere) {
        this.ingegnere = ingegnere;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @XmlTransient
    public List<Pannello> getPannelli() {
        return pannelli;
    }

    public void setPannelli(List<Pannello> pannelli) {
        this.pannelli = pannelli;
    }

    @XmlTransient
    public Contratto getContratto() {
        return contratto;
    }

    public void setContratto(Contratto contratto) {
        this.contratto = contratto;
    }
    
        public Integer getQuantitapannelli() {
        return quantitapannelli;
    }

    public void setQuantitapannelli(Integer quantitapannelli) {
        this.quantitapannelli = quantitapannelli;
    }

    public Float getValore() {
        return valore;
    }

    public void setValore(Float valore) {
        this.valore = valore;
    }

    public String getTipopannello() {
        return tipopannello;
    }

    public void setTipopannello(String tipopannello) {
        this.tipopannello = tipopannello;
    }

    public String getMarcapannello() {
        return marcapannello;
    }

    public void setMarcapannello(String marcapannello) {
        this.marcapannello = marcapannello;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprogetto != null ? idprogetto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Progetto)) {
            return false;
        }
        Progetto other = (Progetto) object;
        if ((this.idprogetto == null && other.idprogetto != null) || (this.idprogetto != null && !this.idprogetto.equals(other.idprogetto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Progetto[ idprogetto=" + idprogetto + " ]";
    }


}
