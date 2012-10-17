/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Balordo
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByIdcliente", query = "SELECT c FROM Cliente c WHERE c.idcliente = :idcliente"),
    @NamedQuery(name = "Cliente.findByID", query = "SELECT c FROM Cliente c WHERE c.idcliente = :idcliente"),
    @NamedQuery(name = "Cliente.findByCodFiscale", query = "SELECT c FROM Cliente c WHERE c.codfiscale = :codfiscale"),
    @NamedQuery(name = "Cliente.findByNome", query = "SELECT c FROM Cliente c WHERE c.nome = :nome"),
    @NamedQuery(name = "Cliente.findByCognome", query = "SELECT c FROM Cliente c WHERE c.cognome = :cognome"),
    @NamedQuery(name = "Cliente.findByIndirizzo", query = "SELECT c FROM Cliente c WHERE c.indirizzo = :indirizzo")})
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idcliente;
    @Size(max = 50)
    @Column(name = "codfiscale", unique=true)
    private String codfiscale;
    @Size(max = 50)
    @Column(name = "nome")
    private String nome;
    @Size(max = 50)
    @Column(name = "cognome")
    private String cognome;
    @Size(max = 255)
    @Column(name = "indirizzo")
    private String indirizzo;
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Progetto> progettoList;
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Contratto> contrattoList;
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Rilievi> rilieviList;

    public Cliente() {
    }

    public Cliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getCodfiscale() {
        return codfiscale;
    }

    public void setCodfiscale(String codfiscale) {
        this.codfiscale = codfiscale;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    @XmlTransient
    public List<Progetto> getProgettoList() {
        return progettoList;
    }

    public void setProgettoList(List<Progetto> progettoList) {
        this.progettoList = progettoList;
    }

    @XmlTransient
    public List<Contratto> getContrattoList() {
        return contrattoList;
    }

    public void setContrattoList(List<Contratto> contrattoList) {
        this.contrattoList = contrattoList;
    }

    public List<Rilievi> getRilieviList() {
        return rilieviList;
    }

    public void setRilieviList(List<Rilievi> rilieviList) {
        this.rilieviList = rilieviList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome+" "+cognome+"\n";
    }


}
