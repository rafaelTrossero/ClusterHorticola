/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.ProductorRNLocal;
import RN.UsuarioRNLocal;
import entidad.Usuario;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;
import recursos.Encriptacion;

/**
 *
 * @author RafaTrossero
 */
@ManagedBean
@RequestScoped
public class UsuarioBean {

      private Usuario usuario;
    private Usuario usuarioSelected;
    private Usuario usu;
    private Encriptacion encript = new Encriptacion();
    
    @ManagedProperty("#{usuarioLogerBean}")
    private UsuarioLogerBean usuarioLogerBean;
    @EJB
    private UsuarioRNLocal usuarioRNLocal;
    private Boolean bCamposEditables;
    private CommandButton cbAction;
    @ManagedProperty("#{listaUsuarioBean}")
    private ListaUsuarioBean listaUsuarioBean;
    
    public UsuarioBean() {
        this.usuario = new Usuario();
        this.usu = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioSelected() {
        return usuarioSelected;
    }

    public void setUsuarioSelected(Usuario usuarioSelected) {
        this.usuarioSelected = usuarioSelected;
    }

    public UsuarioLogerBean getUsuarioLogerBean() {
        return usuarioLogerBean;
    }

    public void setUsuarioLogerBean(UsuarioLogerBean usuarioLogerBean) {
        this.usuarioLogerBean = usuarioLogerBean;
    }

    public UsuarioRNLocal getUsuarioRNLocal() {
        return usuarioRNLocal;
    }

    public void setUsuarioRNLocal(UsuarioRNLocal usuarioRNLocal) {
        this.usuarioRNLocal = usuarioRNLocal;
    }
    

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public Boolean getbCamposEditables() {
        return bCamposEditables;
    }

    public void setbCamposEditables(Boolean bCamposEditables) {
        this.bCamposEditables = bCamposEditables;
    }

    public CommandButton getCbAction() {
        return cbAction;
    }

    public void setCbAction(CommandButton cbAction) {
        this.cbAction = cbAction;
    }

    public ListaUsuarioBean getListaUsuarioBean() {
        return listaUsuarioBean;
    }

    public void setListaUsuarioBean(ListaUsuarioBean listaUsuarioBean) {
        this.listaUsuarioBean = listaUsuarioBean;
    }
    
     public void actionBtn() {

        switch (this.getListaUsuarioBean().getiActionBtnSelect()) {
            case 0:
                create();
                //limíar campos
                //this.limpiar();
                break;
            case 1:
                this.edit();
                break;
            case 2:
                //deshabilita el campo
                 this.activate(Boolean.FALSE);
                break;
            case 3:
                //habilita el campo
                this.activate(Boolean.TRUE);
                break;

        }//fin switch
    }//fin actionBtn
     public void setBtnSelect(ActionEvent e) {
        CommandButton btnSelect = (CommandButton) e.getSource();
        System.out.println("boton select: " + btnSelect.getId());
    //0 crea
        //1: edit
        //2 delete

        //activo el boton
        this.getCbAction().setDisabled(false);

        if (btnSelect.getId().equals("cbNuevo")) {
            this.getListaUsuarioBean().setiActionBtnSelect(0);
            this.getCbAction().setValue("Guardar");
            //campos requeridos
            // this.setbCamposRequeridos(true);

        } else if (btnSelect.getId().equals("cbEditar")) {
            this.getCbAction().setValue("Modificar");
            this.getListaUsuarioBean().setiActionBtnSelect(1);

            //campos requeridos
            //this.setbCamposRequeridos(true);
        } else if (btnSelect.getId().equals("cbDeshabilitado")) {
            this.getListaUsuarioBean().setiActionBtnSelect(2);

            this.setbCamposEditables(true);
            this.getCbAction().setValue("Desactivar");

        } else if (btnSelect.getId().equals("cbHabilitado")) {
            this.getListaUsuarioBean().setiActionBtnSelect(3);

            this.setbCamposEditables(true);
            this.getCbAction().setValue("Reactivar");

        }

        //fin else
    }//fin setBtnSelect
     
      public void create() {

        System.out.println("Entro al create");
        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        try {
        String encriptMD5Alu= encript.hash(usu.getPassword());
            usu.setPassword(encriptMD5Alu);
            usu.setActive(Boolean.TRUE);
            
             usuarioRNLocal.create(usu);
             
            System.out.println("Usuario: " + usu.getActive());
            
           

            sMensaje = "El usuario " + usu.getUsername()+ " fue guardado";
            severity = FacesMessage.SEVERITY_INFO;

            //agregar a la lista
            this.getListaUsuarioBean().getLstUsuario().add(this.getUsu());

      //limpiar campos
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgUsuario').hide()");
             this.limpiar();
        } catch (Exception ex) {

            severity = FacesMessage.SEVERITY_ERROR;
            sMensaje = "Error al crear: " + ex.getMessage();

        } finally {
            fm = new FacesMessage(severity, sMensaje, null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }

    }//fin crear
      
       public void edit() {
        System.out.println("Entro al edit");
        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        try {
            
            
            usu.setActive(Boolean.TRUE);
          String encriptMD5Alu= encript.hash(usu.getPassword());
            usu.setPassword(encriptMD5Alu);
            
            
          
            usuarioRNLocal.edit(usu);
            
            
          
            //usersRNLocal.edit(this.getUsers());

            sMensaje = "Información actualizada con éxito";
            severity = FacesMessage.SEVERITY_INFO;

            //elimino y agrego el organismo modificado a la lista
            //int iPos = this.getListaAlumnoBean().getLstUsers().indexOf(this.getUsers());
            int iPos = this.getListaUsuarioBean().getLstUsuario().indexOf(this.getUsu());
         
            this.getListaUsuarioBean().getLstUsuario().remove(iPos);
            this.getListaUsuarioBean().getLstUsuario().add(iPos, this.getUsu());

            //this.getCbAction().setValue("Update");
            this.getCbAction().setDisabled(true);

            //this.setbCamposRequeridos(false);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgUsuario').hide()");
        } catch (Exception ex) {
            severity = FacesMessage.SEVERITY_ERROR;
            sMensaje = "Error al actualizar: " + ex.getMessage();

        } finally {
            fm = new FacesMessage(severity, sMensaje, "");
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }
       
       public void activate(Boolean bEstado) {
        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;

        try {
             usuarioRNLocal.activate(this.getUsu(), bEstado);
            

            //elimino el organismo de la lista
            //int iPos = this.getListaAlumnoBean().getLstAlumno()).indexOf(this.getAlumno());
            int iPos = this.getListaUsuarioBean().getLstUsuario().indexOf(this.getUsu());

            this.setUsu(this.getListaUsuarioBean().getLstUsuario().get(iPos));
            this.getUsu().setActive(bEstado);
            
            this.getListaUsuarioBean().getLstUsuario().remove(iPos);
            this.getListaUsuarioBean().getLstUsuario().add(iPos, this.getUsu());

            if (!bEstado) {
                sMensaje = "Usuario desactivado correctamente";
            } else {
                sMensaje = "Usuario reactivado correctaamente";
            }
            severity = FacesMessage.SEVERITY_INFO;

            this.getCbAction().setDisabled(true);

            //limíar campos
            this.limpiar();
            //this.setbCamposRequeridos(false);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgUsuario').hide()");

        } catch (Exception ex) {
            severity = FacesMessage.SEVERITY_ERROR;
            sMensaje = "An error ocurred during activation: " + ex.getMessage();

        } finally {
            fm = new FacesMessage(severity, sMensaje, null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }
      
      public void limpiar(){
          this.usuario= new Usuario();
      this.usu = new Usuario();
      }
    
}
