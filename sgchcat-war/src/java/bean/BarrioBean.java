package bean;

import RN.barrioRNLocal;
import entidad.Barrio;
import entidad.Departamento;
import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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

@ManagedBean
@RequestScoped
public class BarrioBean {

  private Barrio barrio;
  @ManagedProperty("#{listaBarrioBean}")
  private ListaBarrioBean listaBarrioBean;
  @ManagedProperty("#{auxBean}")
  private AuxBean auxBean;
  @ManagedProperty("#{usuarioLogerBean}")
    private UsuarioLogerBean usuarioLogerBean;
  
  private Boolean bCamposEditables;
  private static Boolean bCamposRequeridos = false;
  @EJB
  private barrioRNLocal barrioRNLocal;
  private CommandButton cbAction;
  private Localidad[] arrayLocalidadSelect;
  //para desactivar los campos
  private Boolean bProvincia;
  private Boolean bDepartamento;
  private Boolean bLocalidad;
  private Boolean bBarrio;
  private String cadenaBarrio;

  public BarrioBean() {
    barrio = new Barrio();
    barrio.setLocalidad(new Localidad());
    barrio.getLocalidad().setDepartamento(new Departamento());
    barrio.getLocalidad().getDepartamento().setProvincia(new Provincia());
    barrio.getLocalidad().getDepartamento().getProvincia().setPais(new Pais());
  }

  public Barrio getBarrio() {
    return barrio;
  }

  public void setBarrio(Barrio barrio) {
    this.barrio = barrio;
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
    BarrioBean.bCamposRequeridos = bCamposRequeridos;
  }

  public ListaBarrioBean getListaBarrioBean() {
    return listaBarrioBean;
  }

  public void setListaBarrioBean(ListaBarrioBean listaBarrioBean) {
    this.listaBarrioBean = listaBarrioBean;
  }

  public AuxBean getAuxBean() {
    return auxBean;
  }

  public void setAuxBean(AuxBean auxBean) {
    this.auxBean = auxBean;
  }

  public Localidad[] getArrayLocalidadSelect() {
    return arrayLocalidadSelect;
  }

  public void setArrayLocalidadSelect(Localidad[] arrayLocalidadSelect) {
    this.arrayLocalidadSelect = arrayLocalidadSelect;
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

  public String getCadenaBarrio() {
    return cadenaBarrio;
  }

  public void setCadenaBarrio(String cadenaBarrio) {
    this.cadenaBarrio = cadenaBarrio;
  }
    
  public void actionBtn() {
    switch (this.getListaBarrioBean().getiActionBtnSelect()) {
      case 0:
        create();

        break;
      case 1:
        this.edit();
        break;
      case 2:
        //borra el campo
        this.delete(Boolean.TRUE);
        break;
      case 3:
        //recupera el campo borrado
        this.delete(Boolean.FALSE);

        break;

    }//fin switch
  }

  public void setBtnSelect(ActionEvent e) {
    CommandButton btnSelect = (CommandButton) e.getSource();

//System.out.println("boton select: " + btnSelect.getId());

    //activo el boton
    this.getCbAction().setDisabled(false);

    if (btnSelect.getId().equals("cbCreate") || btnSelect.getId().equals("cbNuevoBarrio")) {
      this.getListaBarrioBean().setiActionBtnSelect(0);
      this.getCbAction().setValue("Guardar");

      System.out.println("paso boton guardar ");

      //campos requeridos
      this.setbCamposRequeridos(true);

    }
    else if (btnSelect.getId().equals("cbDelete")) {
      this.getListaBarrioBean().setiActionBtnSelect(2);

      this.setbCamposEditables(true);
      this.getCbAction().setValue("Eliminar");

    }
    else if (btnSelect.getId().equals("cbEdit")) {
      this.getListaBarrioBean().setiActionBtnSelect(1);

      this.getCbAction().setValue("Modificar");

      //campos requeridos
      this.setbCamposRequeridos(true);
    }
    else if (btnSelect.getId().equals("cbRecuperarBorrado")) {
      this.getListaBarrioBean().setiActionBtnSelect(3);
      this.setbCamposEditables(true);
      this.getCbAction().setValue("Recuperar");

    }
    else if (btnSelect.getId().equals("cbDeshabilitado")) {
      this.getListaBarrioBean().setiActionBtnSelect(4);
      this.setbCamposEditables(true);
      this.getCbAction().setValue("Deshabilitar");

    }
    else if (btnSelect.getId().equals("cbHabilitado")) {
      this.getListaBarrioBean().setiActionBtnSelect(5);
      this.setbCamposEditables(true);
      this.getCbAction().setValue("Habilitar");

    }


  }//fin

  public void create() {

    System.out.println("Entro al create");
    String sMensaje = "";
    FacesMessage fm;
    FacesMessage.Severity severity = null;
    try {

      System.out.println("Barrio: " + barrio);

      this.getBarrio().setBorrado(false);
      barrioRNLocal.create(barrio,usuarioLogerBean.getUsuario(), usuarioLogerBean.getUrl());
      System.out.println("paso");
      System.out.println("CARGA LOCALIDAD???" + this.getBarrio().getLocalidad());
      sMensaje = "El dato fue guardado";
      severity = FacesMessage.SEVERITY_INFO;

      //agregar a la lista

      this.getListaBarrioBean().getLstBarrio().add(barrio);
      //limpiar campos
      this.limpiar();


    }
    catch (Exception ex) {

      severity = FacesMessage.SEVERITY_ERROR;
      sMensaje = "Error al crear: " + ex.getMessage();

    }
    finally {
      fm = new FacesMessage(severity, sMensaje, null);
      FacesContext fc = FacesContext.getCurrentInstance();
      fc.addMessage(null, fm);
    }

  }//fin crear

  private void limpiar() {
    this.setBarrio(new Barrio());

  }

  private void edit() {
    System.out.println("Entro al edit");
    String sMensaje = "";
    FacesMessage fm;
    FacesMessage.Severity severity = null;
    try {
      //get la fila seleccionada           
      barrioRNLocal.edit(this.getBarrio(),usuarioLogerBean.getUsuario(), usuarioLogerBean.getUrl());

      sMensaje = "El dato fue modificado ";
      severity = FacesMessage.SEVERITY_INFO;

      //elimino y agrego el organismo modificado a la lista
      int iPos = this.getListaBarrioBean().getLstBarrio().indexOf(this.getBarrio());


      this.getListaBarrioBean().getLstBarrio().remove(iPos);
      this.getListaBarrioBean().getLstBarrio().add(iPos, this.getBarrio());


      this.getCbAction().setValue("Editar");
      this.getCbAction().setDisabled(true);
      //limíar campos
      //this.limpiar();
      this.setbCamposRequeridos(false);
    }
    catch (Exception ex) {
      severity = FacesMessage.SEVERITY_ERROR;
      sMensaje = "Error al modificar: " + ex.getMessage();

    }
    finally {
      fm = new FacesMessage(severity, sMensaje, null);
      FacesContext fc = FacesContext.getCurrentInstance();
      fc.addMessage(null, fm);
    }
  }//fin edit

  private void delete(Boolean bEstado) {
    System.out.println("Entro al delete");
    String sMensaje = "";
    FacesMessage fm;
    FacesMessage.Severity severity = null;
    try {

      if (bEstado) {
        sMensaje = "El dato fue eliminado";
      }
      else {
        sMensaje = "El dato fue recuperado";
      }
      barrioRNLocal.remove(this.getBarrio(), bEstado,usuarioLogerBean.getUsuario(), usuarioLogerBean.getUrl());

      //elimino el organismo de la lista

      int iPos = this.getListaBarrioBean().getLstBarrio().indexOf(this.getBarrio());

      this.setBarrio(this.getListaBarrioBean().getLstBarrio().get(iPos));
      this.getBarrio().setBorrado(bEstado);
      this.getListaBarrioBean().getLstBarrio().remove(iPos);
      this.getListaBarrioBean().getLstBarrio().add(iPos, this.getBarrio());




      severity = FacesMessage.SEVERITY_INFO;

      this.getCbAction().setDisabled(true);

      //limíar campos
      this.limpiar();
      this.setbCamposRequeridos(false);

    }
    catch (Exception ex) {
      severity = FacesMessage.SEVERITY_ERROR;
      sMensaje = "Error al eliminar: " + ex.getMessage();

    }
    finally {
      fm = new FacesMessage(severity, sMensaje, null);
      FacesContext fc = FacesContext.getCurrentInstance();
      fc.addMessage(null, fm);
    }
  } // fin remove

  public void cargarBarriossss() {

    String sMensaje = "";
    FacesMessage fm;
    FacesMessage.Severity severity = null;
    try {

//System.out.println("Profesional : " + this.getProfesional().getDocumentoIdentidad().getTipoDocumento() + " : " + this.getProfesional().getDocumentoIdentidad().getNumero());
      //Persona per = profesionalRNLocal.findPersonaByTipoNumeroDocumento(this.getProfesional());
      Barrio bar = (Barrio) barrioRNLocal.findAll();
      System.out.println("Barrio: " + bar.getClass().getName());
      System.out.println("Localidad: " + ((Barrio) bar).getLocalidad());
      if (bar != null) {

        if (bar.getClass().getSimpleName().equals("Barrio")) {
          System.out.println("Entro al if");
          this.setBarrio((Barrio) bar);
        }
        else {
          System.out.println("Entro al else");
          this.getBarrio().setId(bar.getId());
          //this.getBarrio().setDocumentoIdentidad(per.getDocumentoIdentidad());
          this.getBarrio().setDescripcion(bar.getDescripcion());
          this.getBarrio().setLocalidad(bar.getLocalidad());
          //this.getProfesional().setApellido(per.getApellido());
        }//fin 

        //actualizo el dialog
        RequestContext context = RequestContext.getCurrentInstance();
        context.update(":pDialog");

        this.getListaBarrioBean().setiActionBtnSelect(1);
        this.getCbAction().setValue("Modificar");
      }
      else {
        this.getListaBarrioBean().setiActionBtnSelect(0);
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

  public void cargarLocalidad() {

    String sMensaje = "";
    FacesMessage fm;
    FacesMessage.Severity severity = null;
    try {

//System.out.println("Profesional : " + this.getProfesional().getDocumentoIdentidad().getTipoDocumento() + " : " + this.getProfesional().getDocumentoIdentidad().getNumero());
      //Persona per = profesionalRNLocal.findPersonaByTipoNumeroDocumento(this.getProfesional());
      Barrio bar = (Barrio) barrioRNLocal.findAll();
      System.out.println("Barrio: " + bar.getClass().getName());
      System.out.println("Localidad: " + ((Barrio) bar).getLocalidad());


      if (bar != null) {
        if (bar.getClass().getSimpleName().equals("Loacalidad")) {
          System.out.println("Entro al if");
          this.setBarrio(bar);
        }
        else {
          System.out.println("Entro al else");
          this.getBarrio().setId(bar.getId());
          this.getBarrio().setDescripcion(bar.getDescripcion());
          this.getBarrio().setLocalidad(bar.getLocalidad());

        }//fin 

        //actualizo el dialog
        RequestContext context = RequestContext.getCurrentInstance();
        context.update(":pDialog");

        this.getListaBarrioBean().setiActionBtnSelect(1);
        this.getCbAction().setValue("Modificar");
      }
      else {
        this.getListaBarrioBean().setiActionBtnSelect(0);
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

  public void cargarLocalidadSelect() {

    System.out.println("Entro al cargarLocalidadSelect");
    System.out.println("Entro al cargarLocalidadSelect" + this.barrio.getLocalidad());
    cargarLocalidad();
    //  this.getAuxBean().setLstEspecialidadesSelect(new ArrayList <Especialidad> (Arrays.asList(this.getArrayEspecialidadSelect())));

    //  System.out.println("cargarEspecialidadesSelect: " + this.getAuxBean().getLstEspecialidadesSelect());


  }//fin cargarProfesionalesSelect

  public void cargarLocalidadesSelect(SelectEvent event) {
    System.out.println(" entra a cargarLocalidadSelect: ");
    Localidad localidad = ((Localidad) event.getObject());
    System.out.println("localidad:  " + localidad);
    this.getBarrio().setLocalidad(localidad);

    //System.out.println("cargarProvinciasSelect: " + this.getAuxBean().getLstDepartamentoSelect());
  }

  public void cerrarDialog() {

    System.out.println("Entro al cerrarDialog");

    this.limpiar();
    this.setbCamposRequeridos(false);
    //cerrar el dialog
    RequestContext context = RequestContext.getCurrentInstance();
    //context.update("pDialog");
    context.execute("PF('dlgBarrio').hide()");
  }//fin cerrarDialog

  public void abrirDialogDomicilio() {
    this.setbCamposRequeridos(false);

    //desactivar todos los combos
    this.setbProvincia(Boolean.TRUE);
    this.setbDepartamento(Boolean.TRUE);
    this.setbLocalidad(Boolean.TRUE);
    //  this.setbBarrio(Boolean.TRUE);

    RequestContext context = RequestContext.getCurrentInstance();
    context.execute("PF('dlgBarrio').show();");

  }

  public void cerrarDialogDomicilio() {
    this.setbCamposRequeridos(true);

    RequestContext context = RequestContext.getCurrentInstance();
    context.execute("PF('dlgBarrio').hide();");

  }

  public void activarProvincia() {
    this.setbDepartamento(Boolean.TRUE);
    this.setbLocalidad(Boolean.TRUE);
    // this.setbBarrio(Boolean.TRUE);
  }//fin activarProvincia

  public void activarDepartamento() {
    this.setbDepartamento(Boolean.FALSE);
    this.setbLocalidad(Boolean.TRUE);
    //this.setbBarrio(Boolean.TRUE);
  }//fin activarDepartamento

  public void activarLocalidad() {
    this.setbDepartamento(Boolean.TRUE);
    this.setbLocalidad(Boolean.FALSE);
    // this.setbBarrio(Boolean.TRUE);
  }//fin activarDepartamento

  public void activarBarrio() {
    this.setbDepartamento(Boolean.TRUE);
    this.setbLocalidad(Boolean.TRUE);
    //this.setbBarrio(Boolean.FALSE);
  }//fin activarDepartamento
  
    public void cargarBarrioLike() {
        try {

            //tipoOp = 1: dni 2: Apell y Nomb 
            this.getListaBarrioBean().setLstBarrio(
                    this.barrioRNLocal.findLike(this.getCadenaBarrio(),Boolean.TRUE));

        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error: " + ex.getMessage(), null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }

    }
    //fin cargarEstablecimientoLike  
  
}// FIN CLASE  BarrioBean 
