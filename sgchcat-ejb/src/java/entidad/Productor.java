/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author RafaTrossero
 */
@Entity
public class Productor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer dni;
    @Column(nullable = false)
    private String cuil;
   
    @Column(nullable = false)
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    private Domicilio domicilio;
    @OneToMany(mappedBy = "productor")
    private List<ProductorDomicilio> lstProductorDomicilio;
    @OneToMany(mappedBy = "productor")
    private List<Empaque> lstEmpaque;
     @OneToMany(mappedBy = "productor")
    private List<CampoProductor> lstCampoProductor;
   
    @Column(nullable = false)
    private String telefono;
    @Column(nullable = false)
    private String celular;

    public Long getId() {
        return id;
    }

    public List<Empaque> getLstEmpaque() {
        return lstEmpaque;
    }

    public List<CampoProductor> getLstCampoProductor() {
        return lstCampoProductor;
    }

    public void setLstCampoProductor(List<CampoProductor> lstCampoProductor) {
        this.lstCampoProductor = lstCampoProductor;
    }
    

    public void setLstEmpaque(List<Empaque> lstEmpaque) {
        this.lstEmpaque = lstEmpaque;
    }
    

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
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
        if (!(object instanceof Productor)) {
            return false;
        }
        Productor other = (Productor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.Productor[ id=" + id + " ]";
    }

}
