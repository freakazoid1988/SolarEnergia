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
@Table(name = "fornitore")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fornitore.findAll", query = "SELECT f FROM Fornitore f"),
    @NamedQuery(name = "Fornitore.findById", query = "SELECT f FROM Fornitore f WHERE f.idfornitore = :idfornitore"),
    @NamedQuery(name = "Fornitore.findByPartitaIVA", query = "SELECT f FROM Fornitore f WHERE f.partitaIVA = :partitaIVA"),
    @NamedQuery(name = "Fornitore.findByNome", query = "SELECT f FROM Fornitore f WHERE f.nome = :nome"),
    @NamedQuery(name = "Fornitore.findByCognome", query = "SELECT f FROM Fornitore f WHERE f.cognome = :cognome"),
    @NamedQuery(name = "Fornitore.findByTelefono", query = "SELECT f FROM Fornitore f WHERE f.telefono = :telefono"),
    @NamedQuery(name = "Fornitore.findByMail", query = "SELECT f FROM Fornitore f WHERE f.mail = :mail"),
    @NamedQuery(name = "Fornitore.findByCitta", query = "SELECT f FROM Fornitore f WHERE f.citta = :citta"),
    @NamedQuery(name = "Fornitore.findByProvincia", query = "SELECT f FROM Fornitore f WHERE f.provincia = :provincia"),
    @NamedQuery(name = "Fornitore.findByIndirizzo", query = "SELECT f FROM Fornitore f WHERE f.indirizzo = :indirizzo"),
    @NamedQuery(name="Fornitore.findByCognomeAndNome",query="SELECT f FROM Fornitore f WHERE f.nome= :nome AND f.cognome= :cognome"),
    @NamedQuery(name = "Fornitore.findByDitta", query = "SELECT f FROM Fornitore f WHERE f.ditta = :ditta")})
public class Fornitore implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idfornitore;
    @Size(max = 50)
    @Column(name = "partitaIVA")
    private String partitaIVA;
    @Size(max = 50)
    @Column(name = "nome")
    private String nome;
    @Size(max = 50)
    @Column(name = "cognome")
    private String cognome;
    @Size(max = 50)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 255)
    @Column(name = "mail")
    private String mail;
    @Size(max = 255)
    @Column(name = "citta")
    private String citta;
    @Size(max = 255)
    @Column(name = "provincia")
    private String provincia;
    @Size(max = 255)
    @Column(name = "indirizzo")
    private String indirizzo;
    @Size(max = 255)
    @Column(name = "ditta")
    private String ditta;
    
    @OneToMany(mappedBy="fornitore",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Ordinepannelli>ordini;
    
    @Transient
    private boolean selected;

    public Fornitore() {
        selected=false;
    }

    public Fornitore(Integer idfornitore) {
        this.idfornitore = idfornitore;
        selected=false;
    }
    
    public void aggiungiOrdine(Ordinepannelli ordine)
    {
        ordini.add(ordine);
    }

    public void rimuoviOrdine(Ordinepannelli ordine)
    {
        ordini.remove(ordine);
    }

    public Ordinepannelli getOrdine(Ordinepannelli ordine)
    {
       for(Ordinepannelli o:ordini)
           if(o.equals(ordine))
               return o;
       return null;
    }
    
    public List<Ordinepannelli> getOrdini()
    {
        return ordini;
    }
    
    public void setOrdini(List<Ordinepannelli> ordini)
    {
        this.ordini = ordini;
    } 
    
    public String visualizzaOrdini()
    {
        if(ordini.isEmpty())
        {
            String s=new String();
            for(Ordinepannelli o:ordini)
                s+=o.toString()+"\n";
            return s;
        }
        return "Nessun ordine disponibile";
    }
    
    public Integer getIdfornitore() {
        return idfornitore;
    }

    public void setIdfornitore(Integer idfornitore) {
        this.idfornitore = idfornitore;
    }

    public String getPartitaIVA() {
        return partitaIVA;
    }

    public void setPartitaIVA(String partitaIVA) {
        this.partitaIVA = partitaIVA;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getDitta() {
        return ditta;
    }

    public void setDitta(String ditta) {
        this.ditta = ditta;
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
        hash += (idfornitore != null ? idfornitore.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornitore)) {
            return false;
        }
        Fornitore other = (Fornitore) object;
        if ((this.idfornitore == null && other.idfornitore != null) || (this.idfornitore != null && !this.idfornitore.equals(other.idfornitore))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ditta+"\n"+nome+" "+cognome+"\n"+mail;
    }   
}
