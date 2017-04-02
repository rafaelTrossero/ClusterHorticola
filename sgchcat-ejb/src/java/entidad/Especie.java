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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author RafaTrossero
 */
@Entity
@NamedQueries({
           
          
           @NamedQuery(name = "Especie.ActualizarEstado", query="UPDATE Especie u SET u.active =:active WHERE u.id =:id"),
           

})
public class Especie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "especie")
    private List<Variedad> lstVariedad;
     @OneToMany(mappedBy = "especie")
    private List<PreciosHortaliza> lstPreciosHortaliza;
     @OneToMany(mappedBy = "especie")
    private List<IngresoMercado> lstIngresoMercado;
     private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Variedad> getLstVariedad() {
        return lstVariedad;
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

    public void setLstVariedad(List<Variedad> lstVariedad) {
        this.lstVariedad = lstVariedad;
    }

    public List<PreciosHortaliza> getLstPreciosHortaliza() {
        return lstPreciosHortaliza;
    }

    public void setLstPreciosHortaliza(List<PreciosHortaliza> lstPreciosHortaliza) {
        this.lstPreciosHortaliza = lstPreciosHortaliza;
    }

    public List<IngresoMercado> getLstIngresoMercado() {
        return lstIngresoMercado;
    }

    public void setLstIngresoMercado(List<IngresoMercado> lstIngresoMercado) {
        this.lstIngresoMercado = lstIngresoMercado;
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
        if (!(object instanceof Especie)) {
            return false;
        }
        Especie other = (Especie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.Especie[ id=" + id + " ]";
    }
    
}
