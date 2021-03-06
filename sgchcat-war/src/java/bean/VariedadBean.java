/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.VariedadRNLocal;
import entidad.Especie;

import entidad.Variedad;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;

/**
 *
 * @author RafaTrossero
 */
@ManagedBean
@RequestScoped
public class VariedadBean {

    private Variedad variedad;
    @ManagedProperty("#{listaVariedadBean}")
    private ListaVariedadBean listaVariedadBean;
    @ManagedProperty("#{usuarioLogerBean}")
    private UsuarioLogerBean usuarioLogerBean;
    @EJB
    private VariedadRNLocal VariedadRNLocal;
    private CommandButton cbAction;
    
    private Boolean bCamposRequeridos;
    private Boolean bCamposEditables;
    private String cadenaVariedad;
    
    public VariedadBean() {
        this.variedad = new Variedad();
    }

    public Variedad getVariedad() {
        return variedad;
    }

    public void setVariedad(Variedad variedad) {
        this.variedad = variedad;
    }

    public ListaVariedadBean getListaVariedadBean() {
        return listaVariedadBean;
    }

    public void setListaVariedadBean(ListaVariedadBean listaVariedadBean) {
        this.listaVariedadBean = listaVariedadBean;
    }

    public UsuarioLogerBean getUsuarioLogerBean() {
        return usuarioLogerBean;
    }

    public void setUsuarioLogerBean(UsuarioLogerBean usuarioLogerBean) {
        this.usuarioLogerBean = usuarioLogerBean;
    }

    public VariedadRNLocal getVariedadRNLocal() {
        return VariedadRNLocal;
    }

    public void setVariedadRNLocal(VariedadRNLocal VariedadRNLocal) {
        this.VariedadRNLocal = VariedadRNLocal;
    }

    public CommandButton getCbAction() {
        return cbAction;
    }

    public void setCbAction(CommandButton cbAction) {
        this.cbAction = cbAction;
    }

    public Boolean getbCamposRequeridos() {
        return bCamposRequeridos;
    }

    public void setbCamposRequeridos(Boolean bCamposRequeridos) {
        this.bCamposRequeridos = bCamposRequeridos;
    }

    public Boolean getbCamposEditables() {
        return bCamposEditables;
    }

    public void setbCamposEditables(Boolean bCamposEditables) {
        this.bCamposEditables = bCamposEditables;
    }

    public String getCadenaVariedad() {
        return cadenaVariedad;
    }

    public void setCadenaVariedad(String cadenaVariedad) {
        this.cadenaVariedad = cadenaVariedad;
    }
    
     public void actionBtn() {

        switch (this.getListaVariedadBean().getiActionBtnSelect()) {
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

        if (btnSelect.getId().equals("cbCreate") || btnSelect.getId().equals("cbNuevoVariedad")) {
            this.getListaVariedadBean().setiActionBtnSelect(0);
            this.getCbAction().setValue("Guardar");
            //campos requeridos
            this.setbCamposRequeridos(true);

        }  else if (btnSelect.getId().equals("cbEdit")) {
            this.getListaVariedadBean().setiActionBtnSelect(1);
            this.getCbAction().setValue("Modificar");
            //campos requeridos
            this.setbCamposRequeridos(true);
        } else if (btnSelect.getId().equals("cbDeshabilitado")) {
            this.getListaVariedadBean().setiActionBtnSelect(2);
            this.setbCamposEditables(true);
            this.getCbAction().setValue("Deshabilitar");

        } else if (btnSelect.getId().equals("cbHabilitado")) {
            this.getListaVariedadBean().setiActionBtnSelect(3);
            this.setbCamposEditables(true);
            this.getCbAction().setValue("Habilitar");
        }

        //fin else
    }//fin setBtnSelect
 
     public void create() {
        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        try {
            //this.getEspecie().setBorrado(false);
            variedad.setActive(Boolean.TRUE);
            VariedadRNLocal.create(variedad);
            sMensaje = "El dato fue guardado";
            severity = FacesMessage.SEVERITY_INFO;

            //agregar a la lista
            this.getListaVariedadBean().getLstVariedad().add(variedad);

            //limpiar campos
          this.limpiar();

        } catch (Exception ex) {
            severity = FacesMessage.SEVERITY_ERROR;
            sMensaje = "Error al crear: " + ex.getMessage();

        } finally {
            fm = new FacesMessage(severity, sMensaje, null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }// fin crear
    
      public void cerrarDialog() {

        this.limpiar();
        this.setbCamposRequeridos(false);
        //cerrar el dialog
        RequestContext context = RequestContext.getCurrentInstance();
        // context.update("pDialog");
        context.execute("PF('dlgVariedad').hide()");  
    }//fin cerrarDialog

    public void limpiar() {
        this.setVariedad(new Variedad());
    }//fin limpiar
    public void edit() {
        System.out.println("Entro al edit");
        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        try {
            variedad.setActive(Boolean.TRUE);
            
            VariedadRNLocal.edit(variedad);
            
            
          
            //usersRNLocal.edit(this.getUsers());

            sMensaje = "Información actualizada con éxito";
            severity = FacesMessage.SEVERITY_INFO;

            //elimino y agrego el organismo modificado a la lista
            //int iPos = this.getListaAlumnoBean().getLstUsers().indexOf(this.getUsers());
            int iPos = this.getListaVariedadBean().getLstVariedad().indexOf(this.getVariedad());
         
            this.getListaVariedadBean().getLstVariedad().remove(iPos);
            this.getListaVariedadBean().getLstVariedad().add(iPos, this.getVariedad());

            //this.getCbAction().setValue("Update");
            this.getCbAction().setDisabled(true);

            //this.setbCamposRequeridos(false);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgVariedad').hide()");
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
             VariedadRNLocal.activate(this.getVariedad(), bEstado);
            

            //elimino el organismo de la lista
            //int iPos = this.getListaAlumnoBean().getLstAlumno()).indexOf(this.getAlumno());
            int iPos = this.getListaVariedadBean().getLstVariedad().indexOf(this.getVariedad());

            this.setVariedad(this.getListaVariedadBean().getLstVariedad().get(iPos));
            this.getVariedad().setActive(bEstado);
            
            this.getListaVariedadBean().getLstVariedad().remove(iPos);
            this.getListaVariedadBean().getLstVariedad().add(iPos, this.getVariedad());

            if (!bEstado) {
                sMensaje = "Variedad desactivado correctamente";
            } else {
                sMensaje = "Variedad reactivado correctaamente";
            }
            severity = FacesMessage.SEVERITY_INFO;

            this.getCbAction().setDisabled(true);

            //limíar campos
            this.limpiar();
            //this.setbCamposRequeridos(false);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgVariedad').hide()");

        } catch (Exception ex) {
            severity = FacesMessage.SEVERITY_ERROR;
            sMensaje = "An error ocurred during activation: " + ex.getMessage();

        } finally {
            fm = new FacesMessage(severity, sMensaje, null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }
     
}
