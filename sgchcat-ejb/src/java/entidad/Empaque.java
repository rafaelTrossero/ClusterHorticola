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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 *
 * @author cris
 */
@Entity
@NamedQueries({
           
          
           @NamedQuery(name = "Empaque.ActualizarEstado", query="UPDATE Empaque u SET u.active =:active WHERE u.id =:id"),
           

})
public class Empaque implements Serializable {

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
    @JoinColumn(name = "productor_id", referencedColumnName = "id")
    private Productor productor;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    private String renspaN;
    private String asociacion;
    private Float cantidad;
    private String unidad;
    private float total_kilos;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
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

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public Productor getProductor() {
        return productor;
    }

    public void setProductor(Productor productor) {
        this.productor = productor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRenspaN() {
        return renspaN;
    }

    public void setRenspaN(String renspaN) {
        this.renspaN = renspaN;
    }

    public String getAsociacion() {
        return asociacion;
    }

    public void setAsociacion(String asociacion) {
        this.asociacion = asociacion;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public float getTotal_kilos() {
        return total_kilos;
    }

    public void setTotal_kilos(float total_kilos) {
        this.total_kilos = total_kilos;
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
        if (!(object instanceof Empaque)) {
            return false;
        }
        Empaque other = (Empaque) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.Empaque[ id=" + id + " ]";
    }

}
