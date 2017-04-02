/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author RafaTrossero
 */
@Entity
public class PreciosHortaliza implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "especie_id", referencedColumnName = "id")
    private Especie especie;
    
    @ManyToOne
    @JoinColumn(name = "variedad_id", referencedColumnName = "id")
    private Variedad variedad;
    
    private Float precioMin;
    private Float precioMax;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private Boolean active;
    private Boolean baja;

    public Long getId() {
        return id;
    }

   public Boolean isActive() {
        return active;
    }
  public void setActive(Boolean active) {
        this.active = active;
    }
    public Boolean getActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Variedad getVariedad() {
        return variedad;
    }

    public void setVariedad(Variedad variedad) {
        this.variedad = variedad;
    }

    public Float getPrecioMin() {
        return precioMin;
    }

    public void setPrecioMin(Float precioMin) {
        this.precioMin = precioMin;
    }

    public Float getPrecioMax() {
        return precioMax;
    }

    public void setPrecioMax(Float precioMax) {
        this.precioMax = precioMax;
    }

    public Boolean getBaja() {
        return baja;
    }

    public void setBaja(Boolean baja) {
        this.baja = baja;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        if (!(object instanceof PreciosHortaliza)) {
            return false;
        }
        PreciosHortaliza other = (PreciosHortaliza) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.PreciosHortaliza[ id=" + id + " ]";
    }
    
}
