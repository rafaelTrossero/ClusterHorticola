/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.PaisRNLocal;
import entidad.Pais;
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
 * @author Belen
 */
@ManagedBean
@RequestScoped
public class PaisBean {

    private Pais pais;
    @ManagedProperty("#{listaPaisBean}")
    private ListaPaisBean listaPaisBean;
    @ManagedProperty("#{usuarioLogerBean}")
    private UsuarioLogerBean usuarioLogerBean;
    @EJB
    private PaisRNLocal paisRNLocal;
    private CommandButton cbAction;
    private static Boolean bCamposRequeridos = false;
    private Boolean bCamposEditables;
    private String cadenaPais;

    /**
     * Creates a new instance of ProvinciaBean
     */
    public PaisBean() {
        pais = new Pais();
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public UsuarioLogerBean getUsuarioLogerBean() {
        return usuarioLogerBean;
    }

    public void setUsuarioLogerBean(UsuarioLogerBean usuarioLogerBean) {
        this.usuarioLogerBean = usuarioLogerBean;
    }

    public ListaPaisBean getListaPaisBean() {
        return listaPaisBean;
    }

    public void setListaPaisBean(ListaPaisBean listaPaisBean) {
        this.listaPaisBean = listaPaisBean;
    }

    public PaisRNLocal getPaisRNLocal() {
        return paisRNLocal;
    }

    public void setPaisRNLocal(PaisRNLocal paisRNLocal) {
        this.paisRNLocal = paisRNLocal;
    }

    public CommandButton getCbAction() {
        return cbAction;
    }

    public void setCbAction(CommandButton cbAction) {
        this.cbAction = cbAction;
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

    public String getCadenaPais() {
        return cadenaPais;
    }

    public void setCadenaPais(String cadenaPais) {
        this.cadenaPais = cadenaPais;
    }

    /**
     *
     */
    public void actionBtn() {

        switch (this.getListaPaisBean().getiActionBtnSelect()) {
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

        if (btnSelect.getId().equals("cbCreate") || btnSelect.getId().equals("cbNuevoPais")) {
            this.getListaPaisBean().setiActionBtnSelect(0);
            this.getCbAction().setValue("Guardar");
            //campos requeridos
            this.setbCamposRequeridos(true);

        } else if (btnSelect.getId().equals("cbDelete")) {
            this.getListaPaisBean().setiActionBtnSelect(2);
            this.getCbAction().setValue("Eliminar");
            this.setbCamposEditables(true);

        } else if (btnSelect.getId().equals("cbEdit")) {
            this.getListaPaisBean().setiActionBtnSelect(1);
            this.getCbAction().setValue("Modificar");
            //campos requeridos
            this.setbCamposRequeridos(true);
        } else if (btnSelect.getId().equals("cbRecuperarBorrado")) {
            this.getListaPaisBean().setiActionBtnSelect(3);
            this.setbCamposEditables(true);
            this.getCbAction().setValue("Recuperar");

        } else if (btnSelect.getId().equals("cbDeshabilitado")) {
            this.getListaPaisBean().setiActionBtnSelect(4);
            this.setbCamposEditables(true);
            this.getCbAction().setValue("Deshabilitar");

        } else if (btnSelect.getId().equals("cbHabilitado")) {
            this.getListaPaisBean().setiActionBtnSelect(5);
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
            this.getPais().setBorrado(false);

            paisRNLocal.create(pais, usuarioLogerBean.getUsuario(), usuarioLogerBean.getUrl());
            sMensaje = "El dato fue guardado";
            severity = FacesMessage.SEVERITY_INFO;

            //agregar a la lista
            this.getListaPaisBean().getLstPais().add(pais);

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
            paisRNLocal.edit(this.getPais(), usuarioLogerBean.getUsuario(), usuarioLogerBean.getUrl());

            sMensaje = "El dato fue modificado ";
            severity = FacesMessage.SEVERITY_INFO;

            //elimino y agrego el organismo modificado a la lista
            int iPos = this.getListaPaisBean().getLstPais().indexOf(this.getPais());

            this.getListaPaisBean().getLstPais().remove(iPos);
            this.getListaPaisBean().getLstPais().add(iPos, this.getPais());

            this.getCbAction().setValue("Editar");
            this.getCbAction().setDisabled(true);

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
            paisRNLocal.remove(this.getPais(), bEstado, usuarioLogerBean.getUsuario(), usuarioLogerBean.getUrl());

            //elimino el organismo de la lista

            int iPos = this.getListaPaisBean().getLstPais().indexOf(this.getPais());

            this.setPais(this.getListaPaisBean().getLstPais().get(iPos));
            this.getPais().setBorrado(bEstado);
            this.getListaPaisBean().getLstPais().remove(this.getPais());

            this.getListaPaisBean().getLstPais().add(iPos, this.getPais());


            severity = FacesMessage.SEVERITY_INFO;

            //this.getCbAction().setValue("Eliminar");
            System.out.println("Antes de desactivar el boton");
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
    }//fin delete

    public void habilitar(Boolean bEstado) {
        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        try {

            paisRNLocal.habilitar(this.getPais(), bEstado, usuarioLogerBean.getUsuario(), usuarioLogerBean.getUrl());

            //elimino el organismo de la lista

            int iPos = this.getListaPaisBean().getLstPais().indexOf(this.getPais());

            this.setPais(this.getListaPaisBean().getLstPais().get(iPos));
            //this.getPais().setHabilitado(bEstado);
            this.getListaPaisBean().getLstPais().remove(iPos);
            this.getListaPaisBean().getLstPais().add(iPos, this.getPais());
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
    }//fin habilitar

    public void cerrarDialog() {

        this.limpiar();
        this.setbCamposRequeridos(false);
        //cerrar el dialog
        RequestContext context = RequestContext.getCurrentInstance();
        // context.update("pDialog");
        context.execute("PF('dlgPais').hide()");  
    }//fin cerrarDialog

    public void limpiar() {
        this.setPais(new Pais());
    }//fin limpiar

    public void cargarPaisLike() {
        try {

            //tipoOp = 1: dni 2: Apell y Nomb 
            this.getListaPaisBean().setLstPais(
                    this.paisRNLocal.findLike(this.getCadenaPais(), Boolean.TRUE));

        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error: " + ex.getMessage(), null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }

    }
    //fin cargarEstablecimientoLike  
}
