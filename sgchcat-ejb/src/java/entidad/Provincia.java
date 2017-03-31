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
 *
 * @author Juan Pablo
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Provincia.UpdateBorrado", query = "UPDATE Provincia p SET p.borrado = :estado WHERE p.id = :id"),
    //@NamedQuery(name="Provincia.UpdateHabilitado", query="UPDATE Provincia p SET p.habilitado = :estado WHERE p.id = :id"),
    @NamedQuery(name = "Provincia.findByCodigoProvincia", query = "SELECT p FROM Provincia p WHERE p.codigo = :codigo ORDER BY p.descripcion"),
    @NamedQuery(name = "Provincia.findByCodigoProvinciaID", query = "SELECT p FROM Provincia p WHERE p.codigo = :codigo  AND p.id <>:id ORDER BY p.descripcion"),
    @NamedQuery(name = "Provincia.findByNombreProvincia", query = "SELECT p FROM Provincia p WHERE p.descripcion = :nombre ORDER BY p.descripcion"),
    @NamedQuery(name = "Provincia.findByNombreProvinciaID", query = "SELECT p FROM Provincia p WHERE p.descripcion = :nombre  AND p.id <>:id ORDER BY p.descripcion"),
    @NamedQuery(name = "Provincia.findByPais", query = "SELECT p FROM Provincia p WHERE p.pais.id= :idPais ORDER BY p.descripcion"),
    @NamedQuery(name = "Provincia.findByPaisBorrado", query = "SELECT p FROM Provincia p WHERE p.pais.id= :idPais AND p.borrado= :estado ORDER BY p.descripcion"),
    @NamedQuery(name = "Provincia.findByBorrado", query = "SELECT p FROM Provincia p WHERE p.borrado= :estado ORDER BY p.descripcion"),
    @NamedQuery(name = "Provincia.findByIDNombreProvincia", query = "SELECT p FROM Provincia p WHERE p.descripcion = :nombre  AND p.id = :id ORDER BY p.descripcion")
})
public class Provincia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "codigo")
    private Long codigo;
    @Column(name = "indec", length = 2)
    private String indec;
    @Column(name = "descripcion", length = 100, nullable = false)
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "pais_id", referencedColumnName = "id")
    private Pais pais;
    @Column(name = "borrado")
    private Boolean borrado;
    @OneToMany(mappedBy = "provincia")
    private List<Departamento> lstDepartamento;
    @OneToMany(mappedBy = "procedencia")
    private List<IngresoMercado> lstIngresoMercado;

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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public List<Departamento> getLstDepartamento() {
        return lstDepartamento;
    }

    public void setLstDepartamento(List<Departamento> lstDepartamento) {
        this.lstDepartamento = lstDepartamento;
    }

    public List<IngresoMercado> getLstIngresoMercado() {
        return lstIngresoMercado;
    }

    public void setLstIngresoMercado(List<IngresoMercado> lstIngresoMercado) {
        this.lstIngresoMercado = lstIngresoMercado;
    }

    @Override
    public int hashCode() {
        System.out.println("hashCode: ");
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provincia)) {
            return false;
        }
        Provincia other = (Provincia) object;
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
