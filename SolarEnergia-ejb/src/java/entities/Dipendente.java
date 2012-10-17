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
@Table(name = "dipendente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dipendente.findAll", query = "SELECT d FROM Dipendente d"),
    @NamedQuery(name = "Dipendente.findByID", query = "SELECT d FROM Dipendente d WHERE d.iddipendente = :iddipendente"),
    @NamedQuery(name = "Dipendente.findByCodFiscale", query = "SELECT d FROM Dipendente d WHERE d.codfiscale = :codfiscale"),
    @NamedQuery(name = "Dipendente.findByNome", query = "SELECT d FROM Dipendente d WHERE d.nome = :nome"),
    @NamedQuery(name = "Dipendente.findByCognome", query = "SELECT d FROM Dipendente d WHERE d.cognome = :cognome"),
    @NamedQuery(name = "Dipendente.findByCognomeAndNome", query = "SELECT d FROM Dipendente d WHERE d.cognome = :cognome AND d.nome = :nome"),
    @NamedQuery(name = "Dipendente.findByMansione", query = "SELECT d FROM Dipendente d WHERE d.mansione = :mansione"),
    @NamedQuery(name = "Dipendente.findBySalario", query = "SELECT d FROM Dipendente d WHERE d.salario = :salario"),
    @NamedQuery(name = "Dipendente.findByIndirizzo", query = "SELECT d FROM Dipendente d WHERE d.indirizzo = :indirizzo")})
public class Dipendente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer iddipendente;
    @Size(max = 50)
    @Column(name = "codfiscale")
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
    
    @OneToMany(mappedBy = "contabile", fetch = FetchType.LAZY)
    private List<Fatturafornitore> fatturafornitoreList;
    
    @OneToMany(mappedBy = "ingegnere", fetch = FetchType.LAZY)
    private List<Progetto> progettoList;
    
    @OneToOne(cascade= CascadeType.ALL, mappedBy = "dipendente", fetch = FetchType.LAZY)
    private Login login;
    
    @JoinColumn(name = "salario", referencedColumnName = "idsalario")
    @ManyToOne(cascade= CascadeType.ALL,fetch = FetchType.LAZY)
    private Salario salario;
    
    @JoinColumn(name = "mansione", referencedColumnName = "idmansione")
    @ManyToOne(fetch = FetchType.LAZY)
    private Mansione mansione;
    
    @OneToMany(mappedBy = "addvendite", fetch = FetchType.LAZY)
    private List<Contratto> contrattoList;
    
    @OneToMany(mappedBy = "contabile", fetch = FetchType.LAZY)
    private List<Fatturacliente> fatturaclienteList;

    public Dipendente() {
    }

    public Dipendente(Integer iddipendente) {
        this.iddipendente = iddipendente;
    }

    public Integer getIddipendente() {
        return iddipendente;
    }

    public void setIddipendente(Integer iddipendente) {
        this.iddipendente = iddipendente;
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
    public List<Fatturafornitore> getFatturafornitoreList() {
        return fatturafornitoreList;
    }

    public void setFatturafornitoreList(List<Fatturafornitore> fatturafornitoreList) {
        this.fatturafornitoreList = fatturafornitoreList;
    }

    @XmlTransient
    public List<Progetto> getProgettoList() {
        return progettoList;
    }

    public void setProgettoList(List<Progetto> progettoList) {
        this.progettoList = progettoList;
    }

    @XmlTransient
    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Salario getSalario() {
        return salario;
    }

    public void setSalario(Salario salario) {
        this.salario = salario;
    }

    public Mansione getMansione() {
        return mansione;
    }

    public void setMansione(Mansione mansione) {
        this.mansione = mansione;
    }

    @XmlTransient
    public List<Contratto> getContrattoList() {
        return contrattoList;
    }

    public void setContrattoList(List<Contratto> contrattoList) {
        this.contrattoList = contrattoList;
    }

    @XmlTransient
    public List<Fatturacliente> getFatturaclienteList() {
        return fatturaclienteList;
    }

    public void setFatturaclienteList(List<Fatturacliente> fatturaclienteList) {
        this.fatturaclienteList = fatturaclienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddipendente != null ? iddipendente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dipendente)) {
            return false;
        }
        Dipendente other = (Dipendente) object;
        if ((this.iddipendente == null && other.iddipendente != null) || (this.iddipendente != null && !this.iddipendente.equals(other.iddipendente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome+" "+cognome+"\n";
    }      

}
