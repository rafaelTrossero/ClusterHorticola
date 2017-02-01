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
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author Juan Pablo
 */
@Entity
@PrimaryKeyJoinColumn(name = "departamento_id", referencedColumnName = "id")
@NamedQueries({
    @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d ORDER BY d.descripcion"),
    @NamedQuery(name = "Departamento.findByCodigoDepartamento", query = "SELECT d FROM Departamento d WHERE d.codigo = :codigo AND d.provincia = :provincia ORDER BY d.descripcion"),
    @NamedQuery(name = "Departamento.findByCodigoDepartamentoID", query = "SELECT d FROM Departamento d WHERE d.provincia = :provincia  AND d.codigo = :codigo AND d.id <>:id ORDER BY d.descripcion"),
    @NamedQuery(name = "Departamento.findByNombreDepartamento", query = "SELECT d FROM Departamento d WHERE d.descripcion = :nombre ORDER BY d.descripcion"),
    @NamedQuery(name = "Departamento.findByNombreDepartamentoID", query = "SELECT d FROM Departamento d WHERE d.descripcion = :nombre  AND d.id <>:id ORDER BY d.descripcion"),
    @NamedQuery(name = "Departamento.UpdateBorrado", query = "UPDATE Departamento d SET d.borrado = :estado WHERE d.id = :id"),
//   @NamedQuery(name = "Agenda.findByAgenda", query = "SELECT a FROM Agenda a WHERE a.especialidad = :especialidad "
//    + "AND a.profesional = :profesional "
//    + "AND a.habilitado = 1"
//    + "AND a.tipoPrestacion = :tipoPrestacion"),
    @NamedQuery(name = "Departamento.finByCodigoIndec", query = "SELECT d FROM Provincia d WHERE d.id = :id ORDER BY d.descripcion"),
    @NamedQuery(name = "Departamento.findByProvincia", query = "SELECT d FROM Departamento d WHERE d.provincia.id = :idProvincia ORDER BY d.descripcion"),
    @NamedQuery(name = "Departamento.findByProvinciaBorrado", query = "SELECT d FROM Departamento d WHERE d.provincia.id = :idProvincia AND d.borrado= :estado ORDER BY d.descripcion"),
    @NamedQuery(name = "Departamento.findByBorrado", query = "SELECT d FROM Departamento d WHERE d.borrado = :estado ORDER BY d.descripcion"),
    @NamedQuery(name = "Departamento.ExisteEnProvincia", query = "SELECT d FROM Departamento d WHERE d.descripcion = :nombre AND d.provincia.id = :idProvincia"),
    @NamedQuery(name = "Departamento.findByIDNombreDepartamento", query = "SELECT d FROM Departamento d WHERE d.descripcion = :nombre  AND d.id = :id ORDER BY d.descripcion")
})
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "codigo", nullable = false)
    private Long codigo;
    @Column(name = "indec", length = 5)
    private String indec;
    @Column(name = "descripcion", length = 100, nullable = false)
    private String descripcion;
    @OneToMany(mappedBy = "departamento")
    private List<Localidad> listaLocalidad;
    @ManyToOne
    @JoinColumn(name = "provincia_id", referencedColumnName = "id", nullable = false)
    private Provincia provincia;
    @Column(name = "borrado")
    private Boolean borrado;
    //private Boolean habilitado;

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

    public String getIndec() {
        return indec;
    }

    public void setIndec(String indec) {
        this.indec = indec;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Localidad> getListaLocalidad() {
        return listaLocalidad;
    }

    public void setListaLocalidad(List<Localidad> listaLocalidad) {
        this.listaLocalidad = listaLocalidad;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

//  public Boolean getHabilitado() {
//    return habilitado;
//  }
//
//  public void setHabilitado(Boolean habilitado) {
//    this.habilitado = habilitado;
//  }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getDescripcion();
    }
}
