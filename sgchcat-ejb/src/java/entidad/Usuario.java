/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gio
 */
@Entity
//@DiscriminatorValue("3")
//@PrimaryKeyJoinColumn(name = "persona_id", referencedColumnName = "id")
@NamedQueries({
   // @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u ORDER BY u.apellido, u.nombre"),
   // @NamedQuery(name = "Usuario.findByNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.username = :username OR u.numeroDocumento = :numero"),
   // @NamedQuery(name = "Usuario.findByNombreUsuarioID", query = "SELECT u FROM Usuario u WHERE (u.username = :username OR u.numeroDocumento = :numero) AND u.id <>:id"),
   // @NamedQuery(name = "Usuario.UpdateBorrado", query = "UPDATE Usuario u SET u.borrado = :borrado WHERE u.id = :id"),
  //  @NamedQuery(name = "Usuario.UpdateHabilitado", query = "UPDATE Usuario u SET u.habilitado = :habilitado WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByUsuarioContrasena", query = "SELECT u FROM Usuario u WHERE u.username =:username AND u.password =:password "),
    @NamedQuery(name = "Usuario.findByUsuarioEmail", query = "SELECT u FROM Usuario u WHERE u.email =:email"),
   // @NamedQuery(name = "Usuario.bFindByDocumentoOrUsuario", query = "SELECT u FROM Usuario u WHERE u.username =:username "
   // + "OR u.numeroDocumento = :numero "),
   // @NamedQuery(name = "Usuario.UpdateClave", query = "UPDATE Usuario u SET u.password = :clave, u.vencimiento_clave = :fecha WHERE u.id = :id"
    })
public class Usuario implements Serializable {

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "username", length = 100, nullable = false)
    private String username;
    @Column(name = "password", length = 64, nullable = false)
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "vencimiento_clave",nullable = true)
    private Date vencimiento_clave;
    @Enumerated(EnumType.STRING)
    //@Column(nullable = false)
    private tipoUsuario tipousuario;
    //@Column(nullable = false)
    @Column(name = "email", length = 100,nullable = true)
    private String email;
    @OneToMany(mappedBy = "usuario")
    private List<Empaque> lstEmpaque;
     
    @OneToMany(mappedBy = "usuario")
    private List<Auditoria> lstAuditoria;
    

    public Long getId() {
        return id;
    }

    public tipoUsuario getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(tipoUsuario tipousuario) {
        this.tipousuario = tipousuario;
    }

    public List<Empaque> getLstEmpaque() {
        return lstEmpaque;
    }

    public void setLstEmpaque(List<Empaque> lstEmpaque) {
        this.lstEmpaque = lstEmpaque;
    }
    

    public void setId(Long id) {
        this.id = id;
    }

  

    /**
     *
     * @return
     */
    
    
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

   

    public List<Auditoria> getLstAuditoria() {
        return lstAuditoria;
    }

    public void setLstAuditoria(List<Auditoria> lstAuditoria) {
        this.lstAuditoria = lstAuditoria;
    }

    public Date getVencimiento_clave() {
        return vencimiento_clave;
    }

    public void setVencimiento_clave(Date vencimiento_clave) {
        this.vencimiento_clave = vencimiento_clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*@Override
     public int hashCode() {
     int hash = 0;
     hash += (id != null ? id.hashCode() : 0);
     return hash;
     }

     @Override
     public boolean equals(Object object) {
     // TODO: Warning - this method won't work in the case the id fields are not set
     if (!(object instanceof Usuario)) {
     return false;
     }
     Usuario other = (Usuario) object;
     if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
     return false;
     }
     return true;
     }*/
    @Override
    public String toString() {
        return "entidad.Usuario[ id=" + id + " ]";
    }
   
}
