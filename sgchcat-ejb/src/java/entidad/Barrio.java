/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author bcasas
 */
@Entity
@PrimaryKeyJoinColumn(name = "barrio_id", referencedColumnName = "id")
@NamedQueries({
    @NamedQuery(name = "Barrio.findAll", query = "SELECT b FROM Barrio b ORDER BY b.descripcion"),
    @NamedQuery(name = "Barrio.UpdateBorrado", query = "UPDATE Barrio b SET b.borrado = :estado WHERE b.id = :id"),
    //@NamedQuery(name="Barrio.UpdateHabilitado", query="UPDATE Barrio b SET b.habilitado = :estado WHERE b.id = :id"),
    @NamedQuery(name = "Barrio.findByNombreBarrio", query = "SELECT b FROM Barrio b WHERE b.descripcion = :nombre ORDER BY b.descripcion"),
    @NamedQuery(name = "Barrio.findByNombreBarrioID", query = "SELECT b FROM Barrio b WHERE b.descripcion = :nombre  AND b.id <>:id ORDER BY b.descripcion"),
    @NamedQuery(name = "Barrio.findByLocalidad", query = "SELECT b FROM Barrio b WHERE b.localidad.id = :idLocalidad ORDER BY b.descripcion"),
    @NamedQuery(name = "Barrio.findByLocalidadBorrado", query = "SELECT b FROM Barrio b WHERE b.localidad.id = :idLocalidad AND b.borrado= :estado ORDER BY b.descripcion"),
    @NamedQuery(name = "Barrio.findByBorrado", query = "SELECT b FROM Barrio b WHERE b.borrado = :estado ORDER BY b.descripcion")
})
public class Barrio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Descripcion", length = 100, nullable = false)
    private String descripcion;
    @Column(name = "Borrado")
    private Boolean borrado;
    @ManyToOne
    @JoinColumn(name = "localidad_id", referencedColumnName = "id", nullable = false)
    private Localidad localidad;

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

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
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
        if (!(object instanceof Barrio)) {
            return false;
            
        }
        Barrio other = (Barrio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.descripcion;
    }
}//class Barrio
