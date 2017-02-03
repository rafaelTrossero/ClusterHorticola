/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.ProductorRNLocal;
import entidad.Productor;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;

/**
 *
 * @author RafaTrossero
 */
@ManagedBean
@RequestScoped
public class productorBean {

    private Productor productor;
    private Boolean bCamposEditables;
    private CommandButton cbAction;
    @ManagedProperty("#{listaProductorBean}")
    private ListaProductorBean listaProductorBean;
    @EJB
    private ProductorRNLocal productorRNLocal;

    public productorBean() {
        this.productor = new Productor();
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

    public void actionBtn() {

        switch (this.getListaProductorBean().getiActionBtnSelect()) {
            case 0:
                create();
                //limíar campos
                //this.limpiar();
                break;
            case 1:
                // this.edit();
                break;
            case 2:
                //deshabilita el campo
                // this.activate(Boolean.FALSE);
                break;
            case 3:
                //habilita el campo
                //  this.activate(Boolean.TRUE);
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

            System.out.println("Productor: " + productor);

            productorRNLocal.create(productor);

            sMensaje = "El dato fue guardado";
            severity = FacesMessage.SEVERITY_INFO;

            //agregar a la lista
            this.getListaProductorBean().getLstProductor().add(productor);

      //limpiar campos
            //  this.limpiar();
        } catch (Exception ex) {

            severity = FacesMessage.SEVERITY_ERROR;
            sMensaje = "Error al crear: " + ex.getMessage();

        } finally {
            fm = new FacesMessage(severity, sMensaje, null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }

    }//fin crear

}
