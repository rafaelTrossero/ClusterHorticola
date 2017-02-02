/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.LocalidadRNLocal;
import entidad.Departamento;
import entidad.Localidad;
import entidad.Pais;
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
import org.primefaces.event.SelectEvent;

/**
 *
 * @author bcasas
 */
@ManagedBean
@RequestScoped
public class LocalidadBean {

    private Localidad localidad;
    @ManagedProperty("#{listaLocalidadBean}")
    private ListaLocalidadBean listaLocalidadBean;
    @ManagedProperty("#{usuarioLogerBean}")
    private UsuarioLogerBean usuarioLogerBean;
    private Boolean bCamposEditables;
    private static Boolean bCamposRequeridos = false;
    @EJB
    private LocalidadRNLocal localidadRNLocal;
    private CommandButton cbAction;
    //para desactivar los campos
    private Boolean bProvincia;
    private Boolean bDepartamento;
    private Boolean bLocalidad;
    private Boolean bBarrio;
    private String cadenaLocalidad;

    public LocalidadBean() {
        localidad = new Localidad();
        localidad.setDepartamento(new Departamento());
        localidad.getDepartamento().setProvincia(new Provincia());
        localidad.getDepartamento().getProvincia().setPais(new Pais());
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public ListaLocalidadBean getListaLocalidadBean() {
        return listaLocalidadBean;
    }

    public void setListaLocalidadBean(ListaLocalidadBean listaLocalidadBean) {
        this.listaLocalidadBean = listaLocalidadBean;
    }

    public LocalidadRNLocal getLocaliadRNLocal() {
        return localidadRNLocal;
    }

    public void setLocaliadRNLocal(LocalidadRNLocal localiadRNLocal) {
        this.localidadRNLocal = localiadRNLocal;
    }

    public String getCadenaLocalidad() {
        return cadenaLocalidad;
    }

    public void setCadenaLocalidad(String cadenaLocalidad) {
        this.cadenaLocalidad = cadenaLocalidad;
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

    public Boolean getbProvincia() {
        return bProvincia;
    }

    public void setbProvincia(Boolean bProvincia) {
        this.bProvincia = bProvincia;
    }

    public Boolean getbDepartamento() {
        return bDepartamento;
    }

    public void setbDepartamento(Boolean bDepartamento) {
        this.bDepartamento = bDepartamento;
    }

    public Boolean getbLocalidad() {
        return bLocalidad;
    }

    public void setbLocalidad(Boolean bLocalidad) {
        this.bLocalidad = bLocalidad;
    }

    public Boolean getbBarrio() {
        return bBarrio;
    }

    public void setbBarrio(Boolean bBarrio) {
        this.bBarrio = bBarrio;
    }

    public UsuarioLogerBean getUsuarioLogerBean() {
        return usuarioLogerBean;
    }

    public void setUsuarioLogerBean(UsuarioLogerBean usuarioLogerBean) {
        this.usuarioLogerBean = usuarioLogerBean;
    }

    public void actionBtn() {
        switch (this.getListaLocalidadBean().getiActionBtnSelect()) {
            case 0:
                this.create();
                // this.limpiar();
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
        }// fin switch
    }// fin actionBtn

    public void setBtnSelect(ActionEvent e) {
        CommandButton btnSelect = (CommandButton) e.getSource();
        System.out.println("boton select: " + btnSelect.getId());
        //0 crea
        //1: edit
        //2 delete

        //activa el boton
        this.getCbAction().setDisabled(false);

        if (btnSelect.getId().equals("cbCreate") || btnSelect.getId().equals("cbNuevaLoc")) {
            this.getListaLocalidadBean().setiActionBtnSelect(0);
            this.getCbAction().setValue("Guardar");
            //campos requeridos
            this.setbCamposRequeridos(true);
        } else if (btnSelect.getId().equals("cbEdit")) {
            this.getListaLocalidadBean().setiActionBtnSelect(1);
            this.getCbAction().setValue("Modificar");
            //campos requeridos
            this.setbCamposRequeridos(true);
        } else if (btnSelect.getId().equals("cbDelete")) {
            this.getListaLocalidadBean().setiActionBtnSelect(2);
            this.getCbAction().setValue("Eliminar");
            this.setbCamposEditables(true);
        } else if (btnSelect.getId().equals("cbRecuperarBorrado")) {
            this.getListaLocalidadBean().setiActionBtnSelect(3);
            this.setbCamposEditables(true);
            this.getCbAction().setValue("Recuperar");

        } else if (btnSelect.getId().equals("cbDeshabilitado")) {
            this.getListaLocalidadBean().setiActionBtnSelect(4);
            this.setbCamposEditables(true);
            this.getCbAction().setValue("Deshabilitar");

        } else if (btnSelect.getId().equals("cbHabilitado")) {
            this.getListaLocalidadBean().setiActionBtnSelect(5);
            this.setbCamposEditables(true);
            this.getCbAction().setValue("Habilitar");

        }
    }// fin setBtnSelect

    public void create() {
        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        try {

            this.getLocalidad().setBorrado(false);
            localidadRNLocal.create(localidad, usuarioLogerBean.getUsuario(), usuarioLogerBean.getUrl());

            sMensaje = "El dato fue guardado";
            severity = FacesMessage.SEVERITY_INFO;

            //agregar a la lista
            this.getListaLocalidadBean().getLstLocalidad().add(localidad);


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
    }// fin create

    public void edit() {
        System.out.println("Entro al edit");
        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        try {
            //get la fila seleccionada           
            localidadRNLocal.edit(this.getLocalidad(), usuarioLogerBean.getUsuario(), usuarioLogerBean.getUrl());

            sMensaje = "El dato fue modificado ";
            severity = FacesMessage.SEVERITY_INFO;

            //elimino y agrego el organismo modificado a la lista
            int iPos = this.getListaLocalidadBean().getLstLocalidad().indexOf(this.getLocalidad());

            this.getListaLocalidadBean().getLstLocalidad().remove(iPos);
            this.getListaLocalidadBean().getLstLocalidad().add(iPos, this.getLocalidad());

            this.getCbAction().setValue("Editar");
            this.getCbAction().setDisabled(true);

            //limíar campos
            //this.limpiar();
            this.setbCamposRequeridos(false);
        } catch (Exception ex) {
            severity = FacesMessage.SEVERITY_ERROR;
            sMensaje = "Error al modificar: " + ex.getMessage();

        } finally {
            fm = new FacesMessage(severity, sMensaje, null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }// fin edit

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
            localidadRNLocal.remove(this.getLocalidad(), bEstado, usuarioLogerBean.getUsuario(), usuarioLogerBean.getUrl());

            //elimino el organismo de la lista

            int iPos = this.getListaLocalidadBean().getLstLocalidad().indexOf(this.getLocalidad());
            this.setLocalidad(this.getListaLocalidadBean().getLstLocalidad().get(iPos));
            this.getLocalidad().setBorrado(bEstado);
            this.getListaLocalidadBean().getLstLocalidad().remove(this.getLocalidad());
            this.getListaLocalidadBean().getLstLocalidad().add(iPos, this.getLocalidad());


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
    }// fin delete

    public void habilitar(Boolean bEstado) {
        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        try {

            localidadRNLocal.habilitar(this.getLocalidad(), bEstado, usuarioLogerBean.getUsuario(), usuarioLogerBean.getUrl());

            //elimino el organismo de la lista

            int iPos = this.getListaLocalidadBean().getLstLocalidad().indexOf(this.getLocalidad());

            this.setLocalidad(this.getListaLocalidadBean().getLstLocalidad().get(iPos));
            //this.getLocalidad().setHabilitado(bEstado);
            this.getListaLocalidadBean().getLstLocalidad().remove(iPos);
            this.getListaLocalidadBean().getLstLocalidad().add(iPos, this.getLocalidad());
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
        context.execute("PF('dlgLocalidad').hide()");

    }//fin cerrarDialog

    public void cargarDepartamento() {

        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        try {

//System.out.println("Profesional : " + this.getProfesional().getDocumentoIdentidad().getTipoDocumento() + " : " + this.getProfesional().getDocumentoIdentidad().getNumero());
            //Persona per = profesionalRNLocal.findPersonaByTipoNumeroDocumento(this.getProfesional());
            Localidad loc = (Localidad) localidadRNLocal.findAll();
            System.out.println("Localidad: " + loc.getClass().getName());
            System.out.println("Departamento: " + ((Localidad) loc).getDepartamento());

            if (loc != null) {

                if (loc.getClass().getSimpleName().equals("Departamento")) {
                    System.out.println("Entro al if");
                    this.setLocalidad((Localidad) loc);
                } else {
                    System.out.println("Entro al else");
                    this.getLocalidad().setId(loc.getId());
                    this.getLocalidad().setDescripcion(loc.getDescripcion());
                    this.getLocalidad().setDepartamento(loc.getDepartamento());

                }//fin 

                //actualizo el dialog
                RequestContext context = RequestContext.getCurrentInstance();
                context.update(":pDialog");

                this.getListaLocalidadBean().setiActionBtnSelect(1);
                this.getCbAction().setValue("Modificar");
            } else {
                this.getListaLocalidadBean().setiActionBtnSelect(0);
            }

            /*if(prof != null){
             this.setProfesional(prof);
             RequestContext context = RequestContext.getCurrentInstance(); 
             context.update("pDialog");
             }//fin if*/
        } //fin cargarProfesional
        catch (Exception ex) {
            severity = FacesMessage.SEVERITY_ERROR;
            sMensaje = "Error: " + ex.getMessage();
            fm = new FacesMessage(severity, sMensaje, null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);

        }
    }//fin cargarProfesional

    public void abrirDialogDomicilio() {
        this.setbCamposRequeridos(false);

        //desactivar todos los combos
        this.setbProvincia(Boolean.TRUE);
        this.setbDepartamento(Boolean.TRUE);
        this.setbLocalidad(Boolean.TRUE);
        this.setbBarrio(Boolean.TRUE);

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlgDomicilio').show();");

    }

    public void cargarDepartamentoSelect(SelectEvent event) {

        System.out.println(" entra a cargarDepartamentoSelect:: ");
        Departamento departamento = ((Departamento) event.getObject());
        System.out.println("departamento: " + departamento);
        this.getLocalidad().setDepartamento(departamento);

        //System.out.println("cargarProvinciasSelect: " + this.getAuxBean().getLstDepartamentoSelect());


    }

    public void cerrarDialogDomicilio() {
        this.setbCamposRequeridos(true);

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlgDomicilio').hide();");

    }

    public void activarProvincia() {
        this.setbDepartamento(Boolean.TRUE);
        this.setbLocalidad(Boolean.TRUE);
        this.setbBarrio(Boolean.TRUE);
    }//fin activarProvincia

    public void activarDepartamento() {
        this.setbDepartamento(Boolean.FALSE);
        this.setbLocalidad(Boolean.TRUE);
        this.setbBarrio(Boolean.TRUE);
    }//fin activarDepartamento

    public void activarLocalidad() {
        this.setbDepartamento(Boolean.TRUE);
        this.setbLocalidad(Boolean.FALSE);
        this.setbBarrio(Boolean.TRUE);
    }//fin activarDepartamento

    public void activarBarrio() {
        this.setbDepartamento(Boolean.TRUE);
        this.setbLocalidad(Boolean.TRUE);
        this.setbBarrio(Boolean.FALSE);
    }//fin activarDepartamento

    public void limpiar() {
        this.setLocalidad(new Localidad());
    }// fin limpiar

    public void cargarLocalidadLike() {
        try {

            //tipoOp = 1: dni 2: Apell y Nomb 
            this.getListaLocalidadBean().setLstLocalidad(
                    this.localidadRNLocal.findLike(this.getCadenaLocalidad(), Boolean.TRUE));

        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error: " + ex.getMessage(), null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }

    }
    //fin cargarLocalidadLike  
}
