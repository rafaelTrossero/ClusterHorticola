/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "domicilio")
public class Domicilio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @ManyToOne
    @JoinColumn(name = "localidad_id", referencedColumnName = "id")
    private Localidad localidad;
    @OneToMany(mappedBy = "domicilio")
    private List<Productor> lstProductor;
    @OneToMany(mappedBy = "domicilio")
    private List<ProductorDomicilio> lstProductorDomicilio;
    @Column(name = "calle")
    private String calle;
    @Column(name = "numero")
    private String numero;
    @Column(name = "referencia")
    private String referencia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

   

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

  

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public List<Productor> getLstProductor() {
        return lstProductor;
    }

    public void setLstProductor(List<Productor> lstProductor) {
        this.lstProductor = lstProductor;
    }

    public List<ProductorDomicilio> getLstProductorDomicilio() {
        return lstProductorDomicilio;
    }

    public void setLstProductorDomicilio(List<ProductorDomicilio> lstProductorDomicilio) {
        this.lstProductorDomicilio = lstProductorDomicilio;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Domicilio)) {
            return false;
        }
        Domicilio other = (Domicilio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.Domicilio[ id=" + id + " ]";
    }
}
