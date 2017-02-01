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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author AFerSor
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Pais.UpdateBorrado", query = "UPDATE Pais p SET p.borrado = :estado WHERE p.id = :id"),
    @NamedQuery(name = "Pais.findByCodigoPais", query = "SELECT p FROM Pais p WHERE p.codigo = :codigo ORDER BY p.descripcion"),
    @NamedQuery(name = "Pais.findByCodigoPaisID", query = "SELECT p FROM Pais p WHERE p.codigo = :codigo  AND p.id <>:id ORDER BY p.descripcion"),
    @NamedQuery(name = "Pais.findByNombrePais", query = "SELECT p FROM Pais p WHERE p.descripcion = :nombre ORDER BY p.descripcion"),
    @NamedQuery(name = "Pais.findByNombrePaisID", query = "SELECT p FROM Pais p WHERE p.descripcion = :nombre  AND p.id <>:id ORDER BY p.descripcion"),
    @NamedQuery(name = "Pais.findByIDNombrePais", query = "SELECT p FROM Pais p WHERE p.descripcion = :nombre  AND p.id = :id ORDER BY p.descripcion")
})
public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "codigo")
    private Long codigo;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @OneToMany(mappedBy = "pais")
    private List<Provincia> lstProvincia;
    @Column(name = "borrado")
    private Boolean borrado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public List<Provincia> getLstProvincia() {
        return lstProvincia;
    }

    public void setLstProvincia(List<Provincia> lstProvincia) {
        this.lstProvincia = lstProvincia;
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
        if (!(object instanceof Pais)) {
            return false;
        }
        Pais other = (Pais) object;
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
