/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import RN.EmpaqueRNLocal;
import RN.IngresoMercadoRNLocal;
import entidad.Empaque;
import entidad.IngresoMercado;
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
 * @author cris
 */
@ManagedBean
@RequestScoped
public class EmpaqueBean {
private Empaque empaque;
    @ManagedProperty("#{listaEmpaqueBean}")
    private ListaEmpaqueBean listaEmpaqueBean;
    @ManagedProperty("#{usuarioLogerBean}")
    private UsuarioLogerBean usuarioLogerBean;
    @EJB
    private EmpaqueRNLocal empaqueRNLocal;
    private CommandButton cbAction;
    
    private Boolean bCamposRequeridos;
    private Boolean bCamposEditables;
    /**
     * Creates a new instance of EmpaqueBean
     */
    public EmpaqueBean() {
        this.empaque = new Empaque();
    }

    public Empaque getEmpaque() {
        return empaque;
    }

    public void setEmpaque(Empaque empaque) {
        this.empaque = empaque;
    }

    public ListaEmpaqueBean getListaEmpaqueBean() {
        return listaEmpaqueBean;
    }

    public void setListaEmpaqueBean(ListaEmpaqueBean listaEmpaqueBean) {
        this.listaEmpaqueBean = listaEmpaqueBean;
    }

    public UsuarioLogerBean getUsuarioLogerBean() {
        return usuarioLogerBean;
    }

    public void setUsuarioLogerBean(UsuarioLogerBean usuarioLogerBean) {
        this.usuarioLogerBean = usuarioLogerBean;
    }

    public EmpaqueRNLocal getEmpaqueRNLocal() {
        return empaqueRNLocal;
    }

    public void setEmpaqueRNLocal(EmpaqueRNLocal empaqueRNLocal) {
        this.empaqueRNLocal = empaqueRNLocal;
    }

    public CommandButton getCbAction() {
        return cbAction;
    }

    public void setCbAction(CommandButton cbAction) {
        this.cbAction = cbAction;
    }

    public Boolean bCamposRequeridos() {
        return bCamposRequeridos;
    }
 public Boolean getbCamposEditables() {
        return bCamposEditables;
    }
    public void setbCamposRequeridos(Boolean bCamposRequeridos) {
        this.bCamposRequeridos = bCamposRequeridos;
    }

    public Boolean isbCamposEditables() {
        return bCamposEditables;
    }

    public void setbCamposEditables(Boolean bCamposEditables) {
        this.bCamposEditables = bCamposEditables;
    }
    
     public void actionBtn() {

        switch (this.getListaEmpaqueBean().getiActionBtnSelect()) {
            case 0:
                create();
                //limíar campos
                //this.limpiar();
                break;
            case 1:
             //   this.edit();
                break;
            case 2:
                System.out.println("Entro al delete");
            //    this.delete(Boolean.TRUE);
                break;
            case 3:
                //recupera el campo borrado
             //   this.delete(Boolean.FALSE);
                break;
            case 4:
                //deshabilita el campo
             //   this.habilitar(Boolean.FALSE);
                break;
            case 5:
                //habilita el campo
             //   this.habilitar(Boolean.TRUE);
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

        if (btnSelect.getId().equals("cbCreate") || btnSelect.getId().equals("cbNuevoEmpaque")) {
            this.getListaEmpaqueBean().setiActionBtnSelect(0);
            this.getCbAction().setValue("Guardar");
            //campos requeridos
            this.setbCamposRequeridos(true);

        } else if (btnSelect.getId().equals("cbDelete")) {
            this.getListaEmpaqueBean().setiActionBtnSelect(2);
            this.getCbAction().setValue("Eliminar");
            this.setbCamposEditables(true);

        } else if (btnSelect.getId().equals("cbEdit")) {
            this.getListaEmpaqueBean().setiActionBtnSelect(1);
            this.getCbAction().setValue("Modificar");
            //campos requeridos
            this.setbCamposRequeridos(true);
        } else if (btnSelect.getId().equals("cbRecuperarBorrado")) {
            this.getListaEmpaqueBean().setiActionBtnSelect(3);
            this.setbCamposEditables(true);
            this.getCbAction().setValue("Recuperar");

        } else if (btnSelect.getId().equals("cbDeshabilitado")) {
            this.getListaEmpaqueBean().setiActionBtnSelect(4);
            this.setbCamposEditables(true);
            this.getCbAction().setValue("Deshabilitar");

        } else if (btnSelect.getId().equals("cbHabilitado")) {
            this.getListaEmpaqueBean().setiActionBtnSelect(5);
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
            empaque.setUsuario(usuarioLogerBean.getUsuario());
            empaqueRNLocal.create(empaque);
            sMensaje = "El dato fue guardado";
            severity = FacesMessage.SEVERITY_INFO;

            //agregar a la lista
            this.getListaEmpaqueBean().getLstEmpaque().add(empaque);
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
        context.execute("PF('dlgEmpaque').hide()");  
    }//fin cerrarDialog

    public void limpiar() {
        this.setEmpaque(new Empaque());
    }//fin limpiar
    
}
