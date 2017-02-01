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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Clase destinada al manejo de Localidades
 *
 * @author LaTIC's Team
 * @version 1.0
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Localidad.UpdateBorrado", query = "UPDATE Localidad l SET l.borrado = :estado WHERE l.id = :id"),
    @NamedQuery(name = "Localidad.findByCodigoLocalidad", query = "SELECT l FROM Localidad l WHERE l.codigo = :codigo AND l.departamento = :departamento ORDER BY l.descripcion"),
    @NamedQuery(name = "Localidad.findByCodigoLocalidadID", query = "SELECT l FROM Localidad l WHERE l.codigo = :codigo  AND l.departamento = :departamento AND l.id <>:id ORDER BY l.descripcion"),
    @NamedQuery(name = "Localidad.findByNombreLocalidad", query = "SELECT l FROM Localidad l WHERE l.descripcion = :nombre ORDER BY l.descripcion"),
    @NamedQuery(name = "Localidad.findByNombreLocalidadID", query = "SELECT l FROM Localidad l WHERE l.descripcion = :nombre  AND l.id <>:id ORDER BY l.descripcion"),
    @NamedQuery(name = "Localidad.findByCodigoPostal", query = "SELECT l FROM Localidad l WHERE l.codigoPostal = :codigoPostal ORDER BY l.descripcion"),
    @NamedQuery(name = "Localidad.findByDepartamento", query = "SELECT l FROM Localidad l WHERE l.departamento.id = :idDepartamento ORDER BY l.descripcion"),
    @NamedQuery(name = "Localidad.findByDepartamentoBorrado", query = "SELECT l FROM Localidad l WHERE l.departamento.id = :idDepartamento AND l.borrado= :estado ORDER BY l.descripcion"),
    @NamedQuery(name = "Localidad.findByBorrado", query = "SELECT l FROM Localidad l WHERE l.borrado = :estado ORDER BY l.descripcion"),
    @NamedQuery(name = "Localidad.findByIDNombreLocalidad", query = "SELECT l FROM Localidad l WHERE l.descripcion = :nombre  AND l.id = :id ORDER BY l.descripcion")
})
public class Localidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "departamento_id", referencedColumnName = "id")
    private Departamento departamento;
    @OneToMany(mappedBy = "localidad")
    private List<Barrio> barrio;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "codigo", nullable = false)
    private Long codigo;
    @Column(name = "indec", length = 8)
    private String indec;
    @Column(name = "codigoPostal")
    private String codigoPostal;
    @Column(name = "borrado")
    private Boolean borrado;

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

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getIndec() {
        return indec;
    }

    public void setIndec(String indec) {
        this.indec = indec;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Barrio> getBarrio() {
        return barrio;
    }

    public void setBarrio(List<Barrio> barrio) {
        this.barrio = barrio;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
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
        if (!(object instanceof Localidad)) {
            return false;
        }
        Localidad other = (Localidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.descripcion;
    }
}
