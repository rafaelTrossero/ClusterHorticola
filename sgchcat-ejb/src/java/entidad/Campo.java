/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author cris
 */
@Entity
@NamedQueries({
           
          
           @NamedQuery(name = "Campo.ActualizarEstado", query="UPDATE Campo u SET u.active =:active WHERE u.id =:id"),
           

})
public class Campo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    private Boolean active;
    @ManyToOne
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    private Domicilio domicilio;
    @OneToMany(mappedBy = "campo")
    private List<CampoDomicilio> lstCampoDomicilio;
    @Column(nullable = false)
    private Double superficie_total;
    @OneToOne
    @JoinColumn(name = "tenencia_id", referencedColumnName = "id", nullable = false)
    private Tenencia tenencia;
    @OneToMany(mappedBy = "campo")
    private List<CampoProductor> lstCampoProductor;
      @ManyToOne
    @JoinColumn(name = "productor_id", referencedColumnName = "id")
    private Productor productor;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isActive() {
        return active;
    }
     public Boolean getActive() {
        return active;
    }

    public Productor getProductor() {
        return productor;
    }

    public void setProductor(Productor productor) {
        this.productor = productor;
    }

 

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<CampoProductor> getLstCampoProductor() {
        return lstCampoProductor;
    }

    public void setLstCampoProductor(List<CampoProductor> lstCampoProductor) {
        this.lstCampoProductor = lstCampoProductor;
    }
    

    public Tenencia getTenencia() {
        return tenencia;
    }

    public void setTenencia(Tenencia tenencia) {
        this.tenencia = tenencia;
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

    public List<CampoDomicilio> getLstCampoDomicilio() {
        return lstCampoDomicilio;
    }

    public void setLstCampoDomicilio(List<CampoDomicilio> lstCampoDomicilio) {
        this.lstCampoDomicilio = lstCampoDomicilio;
    }

    public Double getSuperficie_total() {
        return superficie_total;
    }

    public void setSuperficie_total(Double superficie_total) {
        this.superficie_total = superficie_total;
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
        if (!(object instanceof Campo)) {
            return false;
        }
        Campo other = (Campo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.Campo[ id=" + id + " ]";
    }
    
}
