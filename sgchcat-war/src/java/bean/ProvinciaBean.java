/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.ProvinciaRNLocal;
import entidad.Provincia;
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
 * @author Juan Pablo
 */
@ManagedBean
@RequestScoped
public class ProvinciaBean {

    private Provincia provincia;
    @ManagedProperty("#{listaProvinciaBean}")
    private ListaProvinciaBean listaProvinciaBean;
    @ManagedProperty("#{usuarioLogerBean}")
    private UsuarioLogerBean usuarioLogerBean;
    @EJB
    private ProvinciaRNLocal provinciaRNLocal;
    private CommandButton cbAction;
    private static Boolean bCamposRequeridos = false;
    private Boolean bCamposEditables;
    private String cadenaProvincia;

    /**
     * Creates a new instance of ProvinciaBean
     */
    public ProvinciaBean() {
        provincia = new Provincia();
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public UsuarioLogerBean getUsuarioLogerBean() {
        return usuarioLogerBean;
    }

    public void setUsuarioLogerBean(UsuarioLogerBean usuarioLogerBean) {
        this.usuarioLogerBean = usuarioLogerBean;
    }

    public ListaProvinciaBean getListaProvinciaBean() {
        return listaProvinciaBean;
    }

    public void setListaProvinciaBean(ListaProvinciaBean listaProvinciaBean) {
        this.listaProvinciaBean = listaProvinciaBean;
    }

    public CommandButton getCbAction() {
        return cbAction;
    }

    public void setCbAction(CommandButton cbAction) {
        this.cbAction = cbAction;
    }

    public String getCadenaProvincia() {
        return cadenaProvincia;
    }

    public void setCadenaProvincia(String cadenaProvincia) {
        this.cadenaProvincia = cadenaProvincia;
    }

    public Boolean getbCamposEditables() {
        return bCamposEditables;
    }

    public void setbCamposEditables(Boolean bCamposEditables) {
        this.bCamposEditables = bCamposEditables;
    }

    public Boolean getbCamposRequeridos() {
        return bCamposRequeridos;
    }

    public void setbCamposRequeridos(Boolean bCamposRequeridos) {
        this.bCamposRequeridos = bCamposRequeridos;
    }

    public void actionBtn() {
        switch (this.getListaProvinciaBean().getiActionBtnSelect()) {
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
                this.delete(Boolean.TRUE);
                break;
            case 3:
                //recupera el campo borrado
                this.delete(Boolean.FALSE);
                break;
            case 4:
                //deshabilita el campo
                this.habilitar(Boolean.FALSE);
                break;
            case 5:
                //habilita el campo
                this.habilitar(Boolean.TRUE);
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

        if (btnSelect.getId().equals("cbCreate")) {
            this.getListaProvinciaBean().setiActionBtnSelect(0);
            this.getCbAction().setValue("Guardar");
            //campos requeridos
            this.setbCamposRequeridos(true);

        } else if (btnSelect.getId().equals("cbEdit")) {
            this.getListaProvinciaBean().setiActionBtnSelect(1);
            this.getCbAction().setValue("Modificar");
            //campos requeridos
            this.setbCamposRequeridos(true);
        } else if (btnSelect.getId().equals("cbDelete")) {
            this.getListaProvinciaBean().setiActionBtnSelect(2);
            this.setbCamposEditables(true);
            this.getCbAction().setValue("Eliminar");


        } else if (btnSelect.getId().equals("cbRecuperarBorrado")) {
            this.getListaProvinciaBean().setiActionBtnSelect(3);
            this.setbCamposEditables(true);
            this.getCbAction().setValue("Recuperar");

        } else if (btnSelect.getId().equals("cbDeshabilitado")) {
            this.getListaProvinciaBean().setiActionBtnSelect(4);
            this.setbCamposEditables(true);
            this.getCbAction().setValue("Deshabilitar");

        } else if (btnSelect.getId().equals("cbHabilitado")) {
            this.getListaProvinciaBean().setiActionBtnSelect(5);
            this.setbCamposEditables(true);
            this.getCbAction().setValue("Habilitar");

        }//fin else
    }//fin setBtnSelect

    public void create() {
        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        try {
            this.getProvincia().setBorrado(false);
            provinciaRNLocal.create(provincia, usuarioLogerBean.getUsuario(), usuarioLogerBean.getUrl());

            sMensaje = "El dato fue guardado";
            severity = FacesMessage.SEVERITY_INFO;

            //agregar a la lista
            this.getListaProvinciaBean().getLstProvincia().add(provincia);
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
            //get la fila seleccionada           
            provinciaRNLocal.edit(this.getProvincia(), usuarioLogerBean.getUsuario(), usuarioLogerBean.getUrl());

            sMensaje = "El dato fue modificado ";
            severity = FacesMessage.SEVERITY_INFO;

            //elimino y agrego el organismo modificado a la lista
            int iPos = this.getListaProvinciaBean().getLstProvincia().indexOf(this.getProvincia());

            this.getListaProvinciaBean().getLstProvincia().remove(iPos);
            this.getListaProvinciaBean().getLstProvincia().add(iPos, this.getProvincia());

            this.getCbAction().setValue("Editar");
            this.getCbAction().setDisabled(true);

            //limíar campos
            this.limpiar();
            this.setbCamposRequeridos(false);

        } catch (Exception ex) {
            severity = FacesMessage.SEVERITY_ERROR;
            sMensaje = "Error al modificar: " + ex.getMessage();

        } finally {
            fm = new FacesMessage(severity, sMensaje, null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }//fin edit

    public void delete(Boolean bEstado) {
        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        try {

            if (bEstado) {
                sMensaje = "El dato fue eliminado";
            } else {
                sMensaje = "El dato fue recuperado";
            }

            System.out.println("Entro al delete metodo");
            provinciaRNLocal.remove(this.getProvincia(), bEstado, usuarioLogerBean.getUsuario(), usuarioLogerBean.getUrl());

            //elimino el organismo de la lista

            int iPos = this.getListaProvinciaBean().getLstProvincia().indexOf(this.getProvincia());
            this.setProvincia(this.getListaProvinciaBean().getLstProvincia().get(iPos));
            this.getProvincia().setBorrado(bEstado);
            this.getListaProvinciaBean().getLstProvincia().remove(this.getProvincia());
            this.getListaProvinciaBean().getLstProvincia().add(iPos, this.getProvincia());


            severity = FacesMessage.SEVERITY_INFO;

            this.getCbAction().setDisabled(true);

            //limíar campos
            this.limpiar();
            this.setbCamposRequeridos(false);


        } catch (Exception ex) {
            severity = FacesMessage.SEVERITY_ERROR;
            sMensaje = "Error al eliminar: " + ex.getMessage();

        } finally {
            fm = new FacesMessage(severity, sMensaje, null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }//fin remove

    public void habilitar(Boolean bEstado) {
        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        try {

            provinciaRNLocal.habilitar(this.getProvincia(), bEstado, usuarioLogerBean.getUsuario(), usuarioLogerBean.getUrl());

            //elimino el organismo de la lista

            int iPos = this.getListaProvinciaBean().getLstProvincia().indexOf(this.getProvincia());

            this.setProvincia(this.getListaProvinciaBean().getLstProvincia().get(iPos));
            //this.getProvincia().setHabilitado(bEstado);
            this.getListaProvinciaBean().getLstProvincia().remove(iPos);
            this.getListaProvinciaBean().getLstProvincia().add(iPos, this.getProvincia());
            //this.getListaServicioBean().getLstServicio().remove(this.getServicio());

            if (!bEstado) {
                sMensaje = "El dato fue deshabilitado";
            } else {
                sMensaje = "El dato fue habilitado ";
            }
            severity = FacesMessage.SEVERITY_INFO;

            this.getCbAction().setDisabled(true);

            //limpíar campos
            this.limpiar();
            this.setbCamposRequeridos(false);

        } catch (Exception ex) {
            severity = FacesMessage.SEVERITY_ERROR;
            sMensaje = "Error al recuperar: " + ex.getMessage();

        } finally {
            fm = new FacesMessage(severity, sMensaje, null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }//fin recuperar

    public void cerrarDialog() {

        this.limpiar();
        this.setbCamposRequeridos(false);
        //cerrar el dialog
        RequestContext context = RequestContext.getCurrentInstance();
        // context.update("pDialog");
        context.execute("PF('dlgProvincia').hide()");

    }//fin cerrarDialog

    public void limpiar() {
        this.setProvincia(new Provincia());
    }//fin limpiar

    public void cargarProvinciaLike() {
        try {

            //tipoOp = 1: dni 2: Apell y Nomb 
            this.getListaProvinciaBean().setLstProvincia(
                    this.provinciaRNLocal.findLike(this.getCadenaProvincia(), Boolean.TRUE));

        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error: " + ex.getMessage(), null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }

    }//fin cargarEstablecimientoLike  
}
