/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.DomicilioRNLocal;
import RN.ProductorRNLocal;
import entidad.Domicilio;
import entidad.Productor;
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
public class productorBean {

    private Productor productor;
    private Domicilio domicilio;
    private Boolean bCamposEditables;
    private CommandButton cbAction;
    @ManagedProperty("#{listaProductorBean}")
    private ListaProductorBean listaProductorBean;
    @EJB
    private ProductorRNLocal productorRNLocal;
     @EJB
    private DomicilioRNLocal domicilioRNLocal;

    public productorBean() {
        this.productor = new Productor();
        this.domicilio = new Domicilio();
    }

    public Productor getProductor() {
        return productor;
    }

    public void setProductor(Productor productor) {
        this.productor = productor;
    }

    public ProductorRNLocal getProductorRNLocal() {
        return productorRNLocal;
    }

    public void setProductorRNLocal(ProductorRNLocal productorRNLocal) {
        this.productorRNLocal = productorRNLocal;
    }

    public ListaProductorBean getListaProductorBean() {
        return listaProductorBean;
    }

    public void setListaProductorBean(ListaProductorBean listaProductorBean) {
        this.listaProductorBean = listaProductorBean;
    }

    public Boolean getbCamposEditables() {
        return bCamposEditables;
    }

    public void setbCamposEditables(Boolean bCamposEditables) {
        this.bCamposEditables = bCamposEditables;
    }

    public void isbCamposEditables(Boolean bCamposEditables) {
        this.bCamposEditables = bCamposEditables;
    }

    public CommandButton getCbAction() {
        return cbAction;
    }

    public void setCbAction(CommandButton cbAction) {
        this.cbAction = cbAction;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public void actionBtn() {

        switch (this.getListaProductorBean().getiActionBtnSelect()) {
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
            this.getListaProductorBean().setiActionBtnSelect(0);
            this.getCbAction().setValue("Guardar");
            //campos requeridos
            // this.setbCamposRequeridos(true);

        } else if (btnSelect.getId().equals("cbEditar")) {
            this.getCbAction().setValue("Modificar");
            this.getListaProductorBean().setiActionBtnSelect(1);

            //campos requeridos
            //this.setbCamposRequeridos(true);
        } else if (btnSelect.getId().equals("cbDeshabilitado")) {
            this.getListaProductorBean().setiActionBtnSelect(2);

            this.setbCamposEditables(true);
            this.getCbAction().setValue("Desactivar");

        } else if (btnSelect.getId().equals("cbHabilitado")) {
            this.getListaProductorBean().setiActionBtnSelect(3);

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
             productor.setActive(Boolean.TRUE);
             domicilioRNLocal.create(domicilio);
             System.out.println("Domicilio: " + domicilio);
             productor.setDomicilio(domicilio);
            productorRNLocal.create(productor);
            System.out.println("Productor: " + productor);
            
           

            sMensaje = "El dato fue guardado";
            severity = FacesMessage.SEVERITY_INFO;

            //agregar a la lista
            this.getListaProductorBean().getLstProductor().add(productor);

      //limpiar campos
            this.limpiar();
             this.getCbAction().setDisabled(true);

            //this.setbCamposRequeridos(false);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgProductor').hide()");
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
            
            
            productor.setActive(Boolean.TRUE);
            domicilioRNLocal.edit(domicilio);
            
            productor.setDomicilio(domicilio);
            System.out.println("Domicilio: " + domicilio);
            productorRNLocal.edit(productor);
            
            
          
            //usersRNLocal.edit(this.getUsers());

            sMensaje = "Información actualizada con éxito";
            severity = FacesMessage.SEVERITY_INFO;

            //elimino y agrego el organismo modificado a la lista
            //int iPos = this.getListaAlumnoBean().getLstUsers().indexOf(this.getUsers());
            int iPos = this.getListaProductorBean().getLstProductor().indexOf(this.getProductor());
         
            this.getListaProductorBean().getLstProductor().remove(iPos);
            this.getListaProductorBean().getLstProductor().add(iPos, this.getProductor());

            //this.getCbAction().setValue("Update");
            this.getCbAction().setDisabled(true);

            //this.setbCamposRequeridos(false);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgProductor').hide()");
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
             productorRNLocal.activate(this.getProductor(), bEstado);
            

            //elimino el organismo de la lista
            //int iPos = this.getListaAlumnoBean().getLstAlumno()).indexOf(this.getAlumno());
            int iPos = this.getListaProductorBean().getLstProductor().indexOf(this.getProductor());

            this.setProductor(this.getListaProductorBean().getLstProductor().get(iPos));
            this.getProductor().setActive(bEstado);
            
            this.getListaProductorBean().getLstProductor().remove(iPos);
            this.getListaProductorBean().getLstProductor().add(iPos, this.getProductor());

            if (!bEstado) {
                sMensaje = "Empaque desactivado correctamente";
            } else {
                sMensaje = "Empaque reactivado correctaamente";
            }
            severity = FacesMessage.SEVERITY_INFO;

            this.getCbAction().setDisabled(true);

            //limíar campos
            this.limpiar();
            //this.setbCamposRequeridos(false);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgProductor').hide()");

        } catch (Exception ex) {
            severity = FacesMessage.SEVERITY_ERROR;
            sMensaje = "An error ocurred during activation: " + ex.getMessage();

        } finally {
            fm = new FacesMessage(severity, sMensaje, null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }
    public void limpiar() {
        this.setProductor(new Productor());
        this.setDomicilio(new Domicilio());
    }//fin limpiar

}
