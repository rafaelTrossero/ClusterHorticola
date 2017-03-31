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
public class IngresoMercado implements Serializable {
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
   
   @ManyToOne
    @JoinColumn(name = "procedencia", referencedColumnName = "id")
    private Provincia procedencia;
   private Boolean active;
 
   
   @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
   
    private Float kg;
    private Float precio;
    private String Ingreso;
    //private String mes;
    
         

    public Long getId() {
        return id;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public Float getKg() {
        return kg;
    }

    public void setKg(Float kg) {
        this.kg = kg;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getIngreso() {
        return Ingreso;
    }

    public void setIngreso(String Ingreso) {
        this.Ingreso = Ingreso;
    }


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Provincia getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(Provincia procedencia) {
        this.procedencia = procedencia;
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
        if (!(object instanceof IngresoMercado)) {
            return false;
        }
        IngresoMercado other = (IngresoMercado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.IngresoMercado[ id=" + id + " ]";
    }
    
}
