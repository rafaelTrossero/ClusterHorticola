/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.CampoDomicilioRNLocal;
import RN.CampoProductorRNLocal;
import RN.CampoRNLocal;
import RN.DomicilioRNLocal;
import entidad.Campo;
import entidad.CampoDomicilio;
import entidad.CampoProductor;
import entidad.Domicilio;
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
public class CampoBean {

    private Campo campo;
    private CampoProductor campoProductor;

    private Domicilio domicilio;
    private CampoDomicilio campodomicilio;
    private Boolean bCamposEditables;
    private CommandButton cbAction;
    private int iActionBtnSelect;
  
    
    @ManagedProperty("#{listaCampoBean}")
    private ListaCampoBean listaCampoBean;
    
    @ManagedProperty("#{listaCultivosCampoBean}")
    private ListaCultivosCampoBean listaCultivosCampoBean;
    
    @ManagedProperty("#{listaCampoProductorBean}")
    private ListaCampoProductorBean listaCampoProductorBean;
    @ManagedProperty("#{listaCampoProductoAuxrBean}")
    private ListaCampoProductoAuxrBean listaCampoProductorAuxBean;
    @EJB
    private CampoRNLocal campoRNLocal;
    @EJB
    private CampoDomicilioRNLocal campoDomicilioRNLocal;
    @EJB
    private DomicilioRNLocal domicilioRNLocal;
    @EJB
    private CampoProductorRNLocal campoProductorRNLocal;
  
    /**
     * Creates a new instance of CampoBean
     */
    public CampoBean() {
        this.campo = new Campo();
        this.domicilio = new Domicilio();
        this.campodomicilio = new CampoDomicilio();
        this.campoProductor = new CampoProductor();
     
    }

    public Campo getCampo() {
        return campo;
    }

    public CampoProductor getCampoProductor() {
        return campoProductor;
    }

    public void setCampoProductor(CampoProductor campoProductor) {
        this.campoProductor = campoProductor;
    }

    public int getiActionBtnSelect() {
        return iActionBtnSelect;
    }

    public ListaCampoProductoAuxrBean getListaCampoProductorAuxBean() {
        return listaCampoProductorAuxBean;
    }

    public void setListaCampoProductorAuxBean(ListaCampoProductoAuxrBean listaCampoProductorAuxBean) {
        this.listaCampoProductorAuxBean = listaCampoProductorAuxBean;
    }

    public ListaCampoProductorBean getListaCampoProductorBean() {
        return listaCampoProductorBean;
    }

    public void setListaCampoProductorBean(ListaCampoProductorBean listaCampoProductorBean) {
        this.listaCampoProductorBean = listaCampoProductorBean;
    }

    public void setiActionBtnSelect(int iActionBtnSelect) {
        this.iActionBtnSelect = iActionBtnSelect;
    }

    public CampoProductorRNLocal getCampoProductorRNLocal() {
        return campoProductorRNLocal;
    }

    public void setCampoProductorRNLocal(CampoProductorRNLocal campoProductorRNLocal) {
        this.campoProductorRNLocal = campoProductorRNLocal;
    }

    public CampoDomicilioRNLocal getCampoDomicilioRNLocal() {
        return campoDomicilioRNLocal;
    }

    public void setCampoDomicilioRNLocal(CampoDomicilioRNLocal campoDomicilioRNLocal) {
        this.campoDomicilioRNLocal = campoDomicilioRNLocal;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public Boolean getbCamposEditables() {
        return bCamposEditables;
    }

    public void setbCamposEditables(Boolean bCamposEditables) {
        this.bCamposEditables = bCamposEditables;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Boolean isbCamposEditables() {
        return bCamposEditables;
    }

    public CommandButton getCbAction() {
        return cbAction;
    }

    public void setCbAction(CommandButton cbAction) {
        this.cbAction = cbAction;
    }

    public ListaCampoBean getListaCampoBean() {
        return listaCampoBean;
    }

    public void setListaCampoBean(ListaCampoBean listaCampoBean) {
        this.listaCampoBean = listaCampoBean;
    }

    public CampoDomicilio getCampodomicilio() {
        return campodomicilio;
    }

    public void setCampodomicilio(CampoDomicilio campodomicilio) {
        this.campodomicilio = campodomicilio;
    }

    public CampoRNLocal getCampoRNLocal() {
        return campoRNLocal;
    }

    public void setCampoRNLocal(CampoRNLocal campoRNLocal) {
        this.campoRNLocal = campoRNLocal;
    }

    public DomicilioRNLocal getDomicilioRNLocal() {
        return domicilioRNLocal;
    }

    public void setDomicilioRNLocal(DomicilioRNLocal domicilioRNLocal) {
        this.domicilioRNLocal = domicilioRNLocal;
    }

   

    public ListaCultivosCampoBean getListaCultivosCampoBean() {
        return listaCultivosCampoBean;
    }

    public void setListaCultivosCampoBean(ListaCultivosCampoBean listaCultivosCampoBean) {
        this.listaCultivosCampoBean = listaCultivosCampoBean;
    }

   

    public void actionBtn() {
        System.out.println("..............." + this.getListaCampoBean().getiActionBtnSelect());

        switch (this.getListaCampoBean().getiActionBtnSelect()) {
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
            this.getListaCampoBean().setiActionBtnSelect(0);
            this.getCbAction().setValue("Guardar");
            System.out.println("numero........: " + this.getListaCampoBean().getiActionBtnSelect());
            //campos requeridos
            // this.setbCamposRequeridos(true);

        } else if (btnSelect.getId().equals("cbEditar")) {
            this.getCbAction().setValue("Modificar");
            this.getListaCampoBean().setiActionBtnSelect(1);
            System.out.println("numero........: " + this.getListaCampoBean().getiActionBtnSelect());
           
            //campos requeridos
            //this.setbCamposRequeridos(true);
        } else if (btnSelect.getId().equals("cbDeshabilitado")) {
            this.getListaCampoBean().setiActionBtnSelect(2);

            this.setbCamposEditables(true);
            this.getCbAction().setValue("Desactivar");

        } else if (btnSelect.getId().equals("cbHabilitado")) {
            this.getListaCampoBean().setiActionBtnSelect(3);

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
            this.getCampo().setLstCampoProductor(this.getListaCampoProductorAuxBean().getLstcampoProductor());
            this.getCampo().setLstCampoProductor(this.getListaCampoProductorBean().getLstCampoProductor());

            campo.setActive(Boolean.TRUE);
            domicilioRNLocal.create(domicilio);
            System.out.println("Domicilio: " + domicilio);
            campo.setDomicilio(domicilio);
            campoRNLocal.create(campo);
            campodomicilio.setCampo(campo);
            campoProductor.setProductor(campo.getProductor());
            campoProductor.setCampo(campo);
            campodomicilio.setDomicilio(domicilio);
            campoDomicilioRNLocal.create(campodomicilio);
            campoProductorRNLocal.create(campoProductor);
          
            System.out.println("Campo: " + campo);

            sMensaje = "El dato fue guardado";
            severity = FacesMessage.SEVERITY_INFO;

            //agregar a la lista
            this.getListaCampoBean().getLstCampo().add(campo);

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

    }//fin
    
     public void edit() {
        System.out.println("Entro al edit");
        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        try {
            
            
            campo.setActive(Boolean.TRUE);
            domicilioRNLocal.edit(domicilio);
            
            campo.setDomicilio(domicilio);
            System.out.println("Domicilio: " + domicilio);
            campoRNLocal.edit(campo);
           
            campodomicilio.setCampo(campo);
            campoProductor.setProductor(campo.getProductor());
            campoProductor.setCampo(campo);
            campodomicilio.setDomicilio(domicilio);
            campoDomicilioRNLocal.edit(campodomicilio);
            campoProductorRNLocal.edit(campoProductor);
            
            
          
            //usersRNLocal.edit(this.getUsers());

            sMensaje = "Información actualizada con éxito";
            severity = FacesMessage.SEVERITY_INFO;

            //elimino y agrego el organismo modificado a la lista
            //int iPos = this.getListaAlumnoBean().getLstUsers().indexOf(this.getUsers());
            int iPos = this.getListaCampoBean().getLstCampo().indexOf(this.getCampo());
         
            this.getListaCampoBean().getLstCampo().remove(iPos);
            this.getListaCampoBean().getLstCampo().add(iPos, this.getCampo());

            //this.getCbAction().setValue("Update");
            this.getCbAction().setDisabled(true);

            //this.setbCamposRequeridos(false);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgCampo').hide()");
        } catch (Exception ex) {
            severity = FacesMessage.SEVERITY_ERROR;
            sMensaje = "Error al actualizar: " + ex.getMessage();

        } finally {
            fm = new FacesMessage(severity, sMensaje, "");
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }

    
}
