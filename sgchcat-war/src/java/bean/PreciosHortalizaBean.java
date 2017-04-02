/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.PreciosHortalizaRNLocal;
import entidad.PreciosHortaliza;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;

/**
 *
 * @author RafaTrossero
 */
@ManagedBean
@RequestScoped
public class PreciosHortalizaBean {

    private PreciosHortaliza preciosHortaliza;
    @ManagedProperty("#{listaPreciosHortalizaBean}")
    private ListaPreciosHortalizaBean listaPreciosHortalizaBean;
    @ManagedProperty("#{usuarioLogerBean}")
    private UsuarioLogerBean usuarioLogerBean;
    @EJB
    private PreciosHortalizaRNLocal preciosHortalizaRNLocal;
    private CommandButton cbAction;
    
    private Boolean bCamposRequeridos;
    private Boolean bCamposEditables;
    private String cadenaVariedad;
    public PreciosHortalizaBean() {
        
        this.preciosHortaliza = new PreciosHortaliza();
    }

    public PreciosHortaliza getPreciosHortaliza() {
        return preciosHortaliza;
    }

    public void setPreciosHortaliza(PreciosHortaliza preciosHortaliza) {
        this.preciosHortaliza = preciosHortaliza;
    }

    public ListaPreciosHortalizaBean getListaPreciosHortalizaBean() {
        return listaPreciosHortalizaBean;
    }

    public void setListaPreciosHortalizaBean(ListaPreciosHortalizaBean listaPreciosHortalizaBean) {
        this.listaPreciosHortalizaBean = listaPreciosHortalizaBean;
    }

    public UsuarioLogerBean getUsuarioLogerBean() {
        return usuarioLogerBean;
    }

    public void setUsuarioLogerBean(UsuarioLogerBean usuarioLogerBean) {
        this.usuarioLogerBean = usuarioLogerBean;
    }

    public PreciosHortalizaRNLocal getPreciosHortalizaRNLocal() {
        return preciosHortalizaRNLocal;
    }

    public void setPreciosHortalizaRNLocal(PreciosHortalizaRNLocal preciosHortalizaRNLocal) {
        this.preciosHortalizaRNLocal = preciosHortalizaRNLocal;
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

        switch (this.getListaPreciosHortalizaBean().getiActionBtnSelect()) {
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

        if (btnSelect.getId().equals("cbCreate") || btnSelect.getId().equals("cbNuevoPreciosHortaliza")) {
            this.getListaPreciosHortalizaBean().setiActionBtnSelect(0);
            this.getCbAction().setValue("Guardar");
            //campos requeridos
            this.setbCamposRequeridos(true);

        } /*else if (btnSelect.getId().equals("cbDelete")) {
            this.getListaPreciosHortalizaBean().setiActionBtnSelect(2);
            this.getCbAction().setValue("Eliminar");
            this.setbCamposEditables(true);

        }*/ else if (btnSelect.getId().equals("cbEdit")) {
            this.getListaPreciosHortalizaBean().setiActionBtnSelect(1);
            this.getCbAction().setValue("Modificar");
            //campos requeridos
            this.setbCamposRequeridos(true);
        }/* else if (btnSelect.getId().equals("cbRecuperarBorrado")) {
            this.getListaPreciosHortalizaBean().setiActionBtnSelect(3);
            this.setbCamposEditables(true);
            this.getCbAction().setValue("Recuperar");

        } */else if (btnSelect.getId().equals("cbDeshabilitado")) {
            this.getListaPreciosHortalizaBean().setiActionBtnSelect(2);
            this.setbCamposEditables(true);
            this.getCbAction().setValue("Deshabilitar");

        } else if (btnSelect.getId().equals("cbHabilitado")) {
            this.getListaPreciosHortalizaBean().setiActionBtnSelect(3);
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
             preciosHortaliza.setActive(Boolean.TRUE);
            preciosHortalizaRNLocal.create(preciosHortaliza);
            sMensaje = "El dato fue guardado";
            severity = FacesMessage.SEVERITY_INFO;

            //agregar a la lista
            this.getListaPreciosHortalizaBean().getLstPreciosHortaliza().add(preciosHortaliza);

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
        context.execute("PF('dlgPreciosHortaliza').hide()");  
    }//fin cerrarDialog

    public void limpiar() {
        this.setPreciosHortaliza(new PreciosHortaliza());
    }//fin limpiar
    
     public void edit() {
        System.out.println("Entro al edit");
        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        try {
            preciosHortaliza.setActive(Boolean.TRUE);
           
            preciosHortalizaRNLocal.edit(preciosHortaliza);
            
            
          
            //usersRNLocal.edit(this.getUsers());

            sMensaje = "Información actualizada con éxito";
            severity = FacesMessage.SEVERITY_INFO;

            //elimino y agrego el organismo modificado a la lista
            //int iPos = this.getListaAlumnoBean().getLstUsers().indexOf(this.getUsers());
            int iPos = this.getListaPreciosHortalizaBean().getLstPreciosHortaliza().indexOf(this.getPreciosHortaliza());
         
            this.getListaPreciosHortalizaBean().getLstPreciosHortaliza().remove(iPos);
            this.getListaPreciosHortalizaBean().getLstPreciosHortaliza().add(iPos, this.getPreciosHortaliza());

            //this.getCbAction().setValue("Update");
            this.getCbAction().setDisabled(true);

            //this.setbCamposRequeridos(false);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgPreciosHortaliza').hide()");
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
             preciosHortalizaRNLocal.activate(this.getPreciosHortaliza(), bEstado);
            

            //elimino el organismo de la lista
            //int iPos = this.getListaAlumnoBean().getLstAlumno()).indexOf(this.getAlumno());
            int iPos = this.getListaPreciosHortalizaBean().getLstPreciosHortaliza().indexOf(this.getPreciosHortaliza());

            this.setPreciosHortaliza(this.getListaPreciosHortalizaBean().getLstPreciosHortaliza().get(iPos));
            this.getPreciosHortaliza().setActive(bEstado);
            
            this.getListaPreciosHortalizaBean().getLstPreciosHortaliza().remove(iPos);
            this.getListaPreciosHortalizaBean().getLstPreciosHortaliza().add(iPos, this.getPreciosHortaliza());

            if (!bEstado) {
                sMensaje = "Precio de Hortaliza desactivado correctamente";
            } else {
                sMensaje = "Precio de Hortaliza reactivado correctaamente";
            }
            severity = FacesMessage.SEVERITY_INFO;

            this.getCbAction().setDisabled(true);

            //limíar campos
            this.limpiar();
            //this.setbCamposRequeridos(false);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgPreciosHortaliza').hide()");

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
