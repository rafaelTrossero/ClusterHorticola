/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.IngresoMercadoRNLocal;
import entidad.IngresoMercado;
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

/**
 *
 * @author RafaTrossero
 */
@ManagedBean
@RequestScoped
public class IngresoMercadoBean {

     private IngresoMercado ingresoMercado;
    @ManagedProperty("#{listaIngresoMercadoBean}")
    private ListaIngresoMercadoBean listaIngresoMercadoBean;
    @ManagedProperty("#{usuarioLogerBean}")
    private UsuarioLogerBean usuarioLogerBean;
    @EJB
    private IngresoMercadoRNLocal ingresoMercadoRNLocal;
    private CommandButton cbAction;
    
    private Boolean bCamposRequeridos;
    private Boolean bCamposEditables;
    private String cadenaVariedad;
    public IngresoMercadoBean() {
        this.ingresoMercado = new IngresoMercado();
    }

    public IngresoMercado getIngresoMercado() {
        return ingresoMercado;
    }

    public void setIngresoMercado(IngresoMercado ingresoMercado) {
        this.ingresoMercado = ingresoMercado;
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
    
    public void actionBtn() {

        switch (this.getListaIngresoMercadoBean().getiActionBtnSelect()) {
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

        } else if (btnSelect.getId().equals("cbDeshabilitado")) {
            this.getListaIngresoMercadoBean().setiActionBtnSelect(4);
            this.setbCamposEditables(true);
            this.getCbAction().setValue("Deshabilitar");

        } else if (btnSelect.getId().equals("cbHabilitado")) {
            this.getListaIngresoMercadoBean().setiActionBtnSelect(5);
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
            String mes="";
            System.out.println("el mes es" + ingresoMercado.getFecha().getMonth());
             System.out.println("LA PROCEDENCIA ES" + ingresoMercado.getProcedencia());
            /*if(ingresoMercado.getFecha().getMonth() == 0){
             mes ="ENERO";
            }
            if(ingresoMercado.getFecha().getMonth() == 1){
             mes ="FEBRERO";
            }
            if(ingresoMercado.getFecha().getMonth() == 2){
             mes ="MARZO";
            }
            if(ingresoMercado.getFecha().getMonth() == 3){
             mes ="ABRIL";
            }
            if(ingresoMercado.getFecha().getMonth() == 4){
             mes ="MAYO";
            }
            if(ingresoMercado.getFecha().getMonth() == 5){
             mes ="JUNIO";
            }
            if(ingresoMercado.getFecha().getMonth() == 6){
             mes ="JULIO";
            }
            if(ingresoMercado.getFecha().getMonth() == 7){
             mes ="AGOSTO";
            }
            if(ingresoMercado.getFecha().getMonth() == 8){
             mes ="SEPTIEMBRE";
            }
            if(ingresoMercado.getFecha().getMonth() == 9){
             mes ="OCTUBRE";
            }
            if(ingresoMercado.getFecha().getMonth() == 10){
             mes ="NOVIEMBRE";
            }
            if(ingresoMercado.getFecha().getMonth() == 11){
             mes ="DICIEMBRE";
            }
            ingresoMercado.setMes(mes);
            System.out.println("el mes es" + mes);
           */
            ingresoMercadoRNLocal.create(ingresoMercado);
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
    
      public void cerrarDialog() {

        this.limpiar();
        this.setbCamposRequeridos(false);
        //cerrar el dialog
        RequestContext context = RequestContext.getCurrentInstance();
        // context.update("pDialog");
        context.execute("PF('dlgIngresoMercado').hide()");  
    }//fin cerrarDialog

   public void cargarProvinciasSelect(SelectEvent event){
       
    System.out.println(" entra a cargarProvinciasSelect: ");
      Provincia provincia = ((Provincia) event.getObject());
    System.out.println(" provincia: " + provincia);  
    this.ingresoMercado.setProcedencia(provincia);
      System.out.println("LA PROCEDENCIA ANTES DEL CREATE ES::--" + ingresoMercado.getProcedencia());
    
    }
      
      
    public void limpiar() {
        this.setIngresoMercado(new IngresoMercado());
    }//fin limpiar
    
    
}
