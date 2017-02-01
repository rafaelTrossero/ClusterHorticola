/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.UsuarioRNLocal;
import entidad.Usuario;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author AFerSor
 */
@ManagedBean(name = "usuarioLogerBean")
@SessionScoped
public class UsuarioLogerBean implements Serializable {

    private Usuario usuario;
    private String url;
    
     private String username;
    private String contrasenia;
  private Boolean administracion;
  private Boolean trabajosFinales;
  private Boolean seguimientoTrabFinales;
  private Boolean operacionesProyecto;
  private Boolean misProyectos;
  private Boolean miProyecto;
  private Boolean cambiarClave;
  
    @EJB
    public UsuarioRNLocal usuarioRNLocal;

    private String message;

    private Boolean renderedMenu;
    //mostrar menu

    private Boolean bUsuario;

    private Boolean bAuditoria;

    public Boolean isOperacionesProyecto() {
        return operacionesProyecto;
    }

    public void setOperacionesProyecto(Boolean operacionesProyecto) {
        this.operacionesProyecto = operacionesProyecto;
    }

    public Boolean isMisProyectos() {
        return misProyectos;
    }

    public Boolean isMiProyecto() {
        return miProyecto;
    }

    public void setMiProyecto(Boolean miProyecto) {
        this.miProyecto = miProyecto;
   
    }

   
     public Boolean isCambiarClave() {
        return cambiarClave;
    }

    public void setCambiarClave(Boolean cambiarClave) {
        this.cambiarClave = cambiarClave;
    }
    
    
    
    
   /* private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage facesMessage;*/

    public UsuarioLogerBean() {
        // faceContext=FacesContext.getCurrentInstance();
       // httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest();
    }

    public Boolean isSeguimientoTrabFinales() {
        return seguimientoTrabFinales;
    }

    public void setSeguimientoTrabFinales(Boolean seguimientoTrabFinales) {
        this.seguimientoTrabFinales = seguimientoTrabFinales;
    }

    public Boolean isAdministracion() {
        return administracion;
    }

    public void setAdministracion(Boolean administracion) {
        this.administracion = administracion;
    }

    public Boolean isTrabajosFinales() {
        return trabajosFinales;
    }

    public void setTrabajosFinales(Boolean trabajosFinales) {
        this.trabajosFinales = trabajosFinales;
    }



    public Boolean isbUsuario() {
        return bUsuario;
    }

    public void setbUsuario(Boolean bUsuario) {
        this.bUsuario = bUsuario;
    }

    public Boolean isbAuditoria() {
        return bAuditoria;
    }

    public void setbAuditoria(Boolean bAuditoria) {
        this.bAuditoria = bAuditoria;
    }

    public Boolean getRenderedMenu() {
        return renderedMenu;
    }

    public void setRenderedMenu(Boolean renderedMenu) {
        this.renderedMenu = renderedMenu;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

   /* public Usuario getSelected() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }*/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
/*
    public String validateLogin() throws Exception {

        System.out.println("AAAAAAAAAAAAAAA" + username);

        System.out.println("AAAAAAAAAAAAAAA" + contrasenia);
        Usuario login = this.usuarioRNLocal.findByUsuarioContrasena(username, contrasenia);
        System.out.println("AAAAAAAAAAAAAAA" + login);
        
        if(login != null){
            httpServletRequest.getSession().setAttribute("sessionUsuario", username);
            facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Correcto", null);
            faceContext.addMessage(null, facesMessage);
            return "acceso";
        }else{
             facesMessage=new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrecto", null);
            faceContext.addMessage(null, facesMessage);
            return "login";
        }
        
    }*/

    public String getMessage() {
        return message;
    }

}//fin usuarioLoggerBean
