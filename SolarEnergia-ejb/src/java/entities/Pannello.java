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

/**
 *
 * @author Balordo
 */
@Entity
@Table(name = "pannello")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pannello.findAll", query = "SELECT p FROM Pannello p"),
    @NamedQuery(name = "Pannello.findByIdpannello", query = "SELECT p FROM Pannello p WHERE p.idpannello = :idpannello"),
    @NamedQuery(name = "Pannello.findByID", query = "SELECT p FROM Pannello p WHERE p.idpannello = :idpannello"),
    @NamedQuery(name = "Pannello.findByTipo", query = "SELECT p FROM Pannello p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "Pannello.findByNserie", query = "SELECT p FROM Pannello p WHERE p.nserie = :nserie"),
    @NamedQuery(name = "Pannello.findByMarca", query = "SELECT p FROM Pannello p WHERE p.marca = :marca"),
    @NamedQuery(name = "Pannello.findByMarcaAndNSerie", query = "SELECT p FROM Pannello p WHERE p.marca = :marca AND p.nserie = :nserie"),
    @NamedQuery(name = "Pannello.findByMarcaAndTipo", query = "SELECT p FROM Pannello p WHERE p.marca = :marca AND p.tipo = :tipo"),
    @NamedQuery(name = "Pannello.findByTipoAndNSerie", query = "SELECT p FROM Pannello p WHERE p.tipo = :tipo AND p.nserie = :nserie"),
    @NamedQuery(name = "Pannello.findByPrezzo", query = "SELECT p FROM Pannello p WHERE p.prezzo >= :min AND p.prezzo <= :max"),
    @NamedQuery(name = "Pannello.findByDescrizione", query = "SELECT p FROM Pannello p WHERE p.descrizione = :descrizione"),
    @NamedQuery(name = "Pannello.findByQuantita", query = "SELECT p FROM Pannello p WHERE p.quantita <= :quantita"),})
public class Pannello implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpannello")
    private Integer idpannello;
    @Size(max = 50)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 50)
    @Column(name = "nserie")
    private String nserie;
    @Size(max = 50)
    @Column(name = "marca")
    private String marca;
    @Size(max = 255)
    @Column(name = "descrizione")
    private String descrizione;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prezzo")
    private Double prezzo;
    @Column(name="quantita")
    private int quantita;
    
    @Transient
    private boolean selected;
    
    @Transient
    private int quantOrdine;
    
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Progetto> progettoList;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Ordinepannelli> ordineList;

    public Pannello() {
    }

    public Pannello(Integer idpannello) {
        this.idpannello = idpannello;
    }

    public Integer getIdpannello() {
        return idpannello;
    }

    public void setIdpannello(Integer idpannello) {
        this.idpannello = idpannello;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNserie() {
        return nserie;
    }

    public void setNserie(String nserie) {
        this.nserie = nserie;
    }


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public List<Ordinepannelli> getOrdineList() {
        return ordineList;
    }

    public void setOrdineList(List<Ordinepannelli> ordineList) {
        this.ordineList = ordineList;
    }

    public List<Progetto> getProgettoList() {
        return progettoList;
    }

    public void setProgettoList(List<Progetto> progettoList) {
        this.progettoList = progettoList;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getQuantOrdine() {
        return quantOrdine;
    }

    public void setQuantOrdine(int quantOrdine) {
        this.quantOrdine = quantOrdine;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpannello != null ? idpannello.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pannello)) {
            return false;
        }
        Pannello other = (Pannello) object;
        if ((this.idpannello == null && other.idpannello != null) || (this.idpannello != null && !this.idpannello.equals(other.idpannello))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pannello[ idpannello=" + idpannello + " ]";
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
    
    /*public int getQuantita() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void setQuantita(int i) {
        throw new UnsupportedOperationException("Not yet implemented");
    }*/

    
}
