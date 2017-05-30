/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.IngresoMercadoRNLocal;
import RN.PreciosIngresoRNLocal;
import entidad.IngresoMercado;
import entidad.Pais;
import entidad.PreciosIngreso;
import entidad.Provincia;
import entidad.Variedad;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import recursos.AuxBean;

/**
 *
 * @author RafaTrossero
 */
@ManagedBean
@RequestScoped
public class IngresoMercadoBean {
@ManagedProperty("#{auxBean}")
  private AuxBean auxBean;
private Provincia pro;
     private IngresoMercado ingresoMercado;
    @ManagedProperty("#{listaIngresoMercadoBean}")
    private ListaIngresoMercadoBean listaIngresoMercadoBean;
    @ManagedProperty("#{usuarioLogerBean}")
    private UsuarioLogerBean usuarioLogerBean;
    @EJB
    private IngresoMercadoRNLocal ingresoMercadoRNLocal;
    @EJB
    private PreciosIngresoRNLocal preciosIngresoRNLocal;
    private CommandButton cbAction;
    
    private Boolean bCamposRequeridos;
    private Boolean bCamposEditables;
    private String cadenaVariedad;
    private PreciosIngreso preciosIngreso;
    public IngresoMercadoBean() {
        this.ingresoMercado = new IngresoMercado();
        this.preciosIngreso = new PreciosIngreso();
        
   
    }

    public IngresoMercado getIngresoMercado() {
        return ingresoMercado;
    }

    public Provincia getPro() {
        return pro;
    }

    public void setPro(Provincia pro) {
        this.pro = pro;
    }

    public void setIngresoMercado(IngresoMercado ingresoMercado) {
        this.ingresoMercado = ingresoMercado;
       
    }

    public AuxBean getAuxBean() {
        return auxBean;
    }

    public void setAuxBean(AuxBean auxBean) {
        this.auxBean = auxBean;
    }
    

    public ListaIngresoMercadoBean getListaIngresoMercadoBean() {
        return listaIngresoMercadoBean;
    }

    public void setListaIngresoMercadoBean(ListaIngresoMercadoBean listaIngresoMercadoBean) {
        this.listaIngresoMercadoBean = listaIngresoMercadoBean;
    }

    public UsuarioLogerBean getUsuarioLogerBean() {
        return usuarioLogerBean;
    }

    public void setUsuarioLogerBean(UsuarioLogerBean usuarioLogerBean) {
        this.usuarioLogerBean = usuarioLogerBean;
    }

    public IngresoMercadoRNLocal getIngresoMercadoRNLocal() {
        return ingresoMercadoRNLocal;
    }

    public void setIngresoMercadoRNLocal(IngresoMercadoRNLocal ingresoMercadoRNLocal) {
        this.ingresoMercadoRNLocal = ingresoMercadoRNLocal;
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

    public PreciosIngreso getPreciosIngreso() {
        return preciosIngreso;
    }

    public void setPreciosIngreso(PreciosIngreso preciosIngreso) {
        this.preciosIngreso = preciosIngreso;
    }
    
    public void actionBtn() {

        switch (this.getListaIngresoMercadoBean().getiActionBtnSelect()) {
            case 0:
                create();
                //limíar campos
                //this.limpiar();
                break;
            case 1:
               this.edit();
                break;
            case 2:
                System.out.println("Entro al delete");
            this.activate(Boolean.TRUE);
                break;
            case 3:
                //recupera el campo borrado
             this.activate(Boolean.FALSE);
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

        if (btnSelect.getId().equals("cbCreate") || btnSelect.getId().equals("cbNuevoIngresoMercado")) {
            this.getListaIngresoMercadoBean().setiActionBtnSelect(0);
            this.getCbAction().setValue("Guardar");
            //campos requeridos
            this.setbCamposRequeridos(true);

        } else if (btnSelect.getId().equals("cbDelete")) {
            this.getListaIngresoMercadoBean().setiActionBtnSelect(2);
            this.getCbAction().setValue("Eliminar");
            this.setbCamposEditables(true);

        } else if (btnSelect.getId().equals("cbEdit")) {
            this.getListaIngresoMercadoBean().setiActionBtnSelect(1);
            this.getCbAction().setValue("Modificar");
            //campos requeridos
            this.setbCamposRequeridos(true);
        } else if (btnSelect.getId().equals("cbRecuperarBorrado")) {
            this.getListaIngresoMercadoBean().setiActionBtnSelect(3);
            this.setbCamposEditables(true);
            this.getCbAction().setValue("Recuperar");

        }

        //fin else
    }//fin setBtnSelect
 
     public void create() {
        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        try {
            
            ingresoMercado.setActive(Boolean.TRUE);
            ingresoMercadoRNLocal.create(ingresoMercado);
            preciosIngreso.setIngresoMercado(ingresoMercado);
            preciosIngresoRNLocal.create(preciosIngreso);
            sMensaje = "El dato fue guardado";
            severity = FacesMessage.SEVERITY_INFO;

            //agregar a la lista
            this.getListaIngresoMercadoBean().getLstIngresoMercado().add(ingresoMercado);

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
     
     public void edit() {
        System.out.println("Entro al edit");
        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        try {
            ingresoMercado.setActive(Boolean.TRUE);
            ingresoMercadoRNLocal.edit(ingresoMercado);
           
            preciosIngreso.setIngresoMercado(ingresoMercado);
            preciosIngresoRNLocal.edit(preciosIngreso);
            //usersRNLocal.edit(this.getUsers());

            sMensaje = "Información actualizada con éxito";
            severity = FacesMessage.SEVERITY_INFO;

            //elimino y agrego el organismo modificado a la lista
            //int iPos = this.getListaAlumnoBean().getLstUsers().indexOf(this.getUsers());
            int iPos = this.getListaIngresoMercadoBean().getLstIngresoMercado().indexOf(this.getIngresoMercado());
         
            this.getListaIngresoMercadoBean().getLstIngresoMercado().remove(iPos);
            this.getListaIngresoMercadoBean().getLstIngresoMercado().add(iPos, this.getIngresoMercado());

            //this.getCbAction().setValue("Update");
            this.getCbAction().setDisabled(true);

            //this.setbCamposRequeridos(false);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgIngresoMercado').hide()");
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
             ingresoMercadoRNLocal.activate(this.getIngresoMercado(), bEstado);
            

            //elimino el organismo de la lista
            //int iPos = this.getListaAlumnoBean().getLstAlumno()).indexOf(this.getAlumno());
            int iPos = this.getListaIngresoMercadoBean().getLstIngresoMercado().indexOf(this.getIngresoMercado());

            this.setIngresoMercado(this.getListaIngresoMercadoBean().getLstIngresoMercado().get(iPos));
            this.getIngresoMercado().setActive(bEstado);
            
            this.getListaIngresoMercadoBean().getLstIngresoMercado().remove(iPos);
            this.getListaIngresoMercadoBean().getLstIngresoMercado().add(iPos, this.getIngresoMercado());

            if (!bEstado) {
                sMensaje = "Ingreso desactivado correctamente";
            } else {
                sMensaje = "Ingreso reactivado correctaamente";
            }
            severity = FacesMessage.SEVERITY_INFO;

            this.getCbAction().setDisabled(true);

            //limíar campos
            this.limpiar();
            //this.setbCamposRequeridos(false);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgIngresoMercado').hide()");

        } catch (Exception ex) {
            severity = FacesMessage.SEVERITY_ERROR;
            sMensaje = "An error ocurred during activation: " + ex.getMessage();

        } finally {
            fm = new FacesMessage(severity, sMensaje, null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }
      public void cerrarDialog() {

        this.limpiar();
        this.setbCamposRequeridos(false);
        //cerrar el dialog
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("pDialog");
        context.execute("PF('dlgIngresoMercado').hide()");  
    }//fin cerrarDialog
      

  
      
    public void limpiar() {
        this.setIngresoMercado(new IngresoMercado());
    }//fin limpiar
    
    
}
