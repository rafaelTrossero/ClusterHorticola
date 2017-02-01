/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.DepartamentoRNLocal;
import entidad.Departamento;
import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import recursos.AuxBean;

/**
 *
 * @author Juan Pablo
 */
@ManagedBean
@RequestScoped
public class DepartamentoBean {

  private Departamento departamento;
  
  @ManagedProperty("#{listaDepartamentoBean}")
  private ListaDepartamentoBean listaDepartamentoBean;
   
  @ManagedProperty("#{auxBean}")
  private AuxBean auxBean;
  
  @ManagedProperty("#{usuarioLogerBean}")
    private UsuarioLogerBean usuarioLogerBean;
  
  private  List<Localidad> listaLocalidadBean;
  private Boolean bCamposEditables;
  private static Boolean bCamposRequeridos = false;
 
  @EJB
  private DepartamentoRNLocal departamentoRNLocal;
  private CommandButton cbAction;
  private Provincia[] arrayProvinciaSelect;
  private DataTable dataTableProvincia;
  
    //para desactivar los campos
    private Boolean bProvincia;
    private Boolean bDepartamento;
    private Boolean bLocalidad;
    
    private String cadenaDepartamento;
    
  public DepartamentoBean() {
    departamento = new Departamento(); 
    departamento.setProvincia(new Provincia());
    departamento.getProvincia().setPais(new Pais());  
      }

  public Departamento getDepartamento() {
    return departamento;
  }

  public void setDepartamento(Departamento departamento) {
    this.departamento = departamento;
//    if(this.getListaDepartamentoBean().getiActionBtnSelect() == 1){
//        this.getAuxBean().setLstProvinciaSelect(this.);
//    }
  }

  public ListaDepartamentoBean getListaDepartamentoBean() {
    return listaDepartamentoBean;
  }

  public void setListaDepartamentoBean(ListaDepartamentoBean listaDepartamentoBean) {
    this.listaDepartamentoBean = listaDepartamentoBean;
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

    public  Boolean getbCamposRequeridos() {
        return bCamposRequeridos;
    }

    public  void setbCamposRequeridos(Boolean bCamposRequeridos) {
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

    public Provincia[] getArrayProvinciaSelect() {
        return arrayProvinciaSelect;
    }

    public void setArrayProvinciaSelect(Provincia[] arrayProvinciaSelect) {
        this.arrayProvinciaSelect = arrayProvinciaSelect;
    }

    public DataTable getDataTableProvincia() {
        return dataTableProvincia;
    }

    public void setDataTableProvincia(DataTable dataTableProvincia) {
        this.dataTableProvincia = dataTableProvincia;
    }

    public AuxBean getAuxBean() {
        return auxBean;
    }

    public void setAuxBean(AuxBean auxBean) {
        this.auxBean = auxBean;
    }

    public UsuarioLogerBean getUsuarioLogerBean() {
        return usuarioLogerBean;
    }

    public void setUsuarioLogerBean(UsuarioLogerBean usuarioLogerBean) {
        this.usuarioLogerBean = usuarioLogerBean;
    }

  public String getCadenaDepartamento() {
    return cadenaDepartamento;
  }

  public void setCadenaDepartamento(String cadenaDepartamento) {
    this.cadenaDepartamento = cadenaDepartamento;
  }

  public void actionBtn(){
    switch(this.getListaDepartamentoBean().getiActionBtnSelect()){
      case 0:
        this.create();
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
    }// fin switch
  }// fin actionBtn

  public void setBtnSelect(ActionEvent e) {
    CommandButton btnSelect = (CommandButton)e.getSource();
    System.out.println("boton select: " + btnSelect.getId());
    //0 crea
    //1: edit
    //2 delete
    
    //activa el boton
    this.getCbAction().setDisabled(false);
    
    if(btnSelect.getId().equals("cbCreate")) {
      this.getListaDepartamentoBean().setiActionBtnSelect(0);
      this.getCbAction().setValue("Guardar");
      // campos requeridos
      this.setbCamposRequeridos(true); 
      
      
    }else if(btnSelect.getId().equals("cbEdit")) {
      this.getListaDepartamentoBean().setiActionBtnSelect(1);
      
         this.getCbAction().setValue("Modificar");
         // campos requeridos
         this.setbCamposRequeridos(true); 
      
    }else if(btnSelect.getId().equals("cbDelete")) {
      this.getListaDepartamentoBean().setiActionBtnSelect(2);
      this.getCbAction().setValue("Eliminar");
      
      this.setbCamposEditables(true);
      } else if (btnSelect.getId().equals("cbRecuperarBorrado")) {
            this.getListaDepartamentoBean().setiActionBtnSelect(3);
            this.setbCamposEditables(true);
            this.getCbAction().setValue("Recuperar");

        } else if (btnSelect.getId().equals("cbDeshabilitado")) {
            this.getListaDepartamentoBean().setiActionBtnSelect(4);
            this.setbCamposEditables(true);
            this.getCbAction().setValue("Deshabilitar");

        } else if (btnSelect.getId().equals("cbHabilitado")) {
            this.getListaDepartamentoBean().setiActionBtnSelect(5);
            this.setbCamposEditables(true);
            this.getCbAction().setValue("Habilitar");
        }

    
  }// fin setBtnSelect
  
  public void create() {
    String sMensaje = "";
    FacesMessage fm;
    FacesMessage.Severity severity = null;
    try {
        
        System.out.println("Entro al create");
        this.getDepartamento().setBorrado(false);
     //   this.getDepartamento().setHabilitado(true);
        
        departamentoRNLocal.create(departamento, usuarioLogerBean.getUsuario(), usuarioLogerBean.getUrl());
       System.out.println(" entra a crear" + this.getDepartamento());
        sMensaje = "El dato fue guardado";
        severity = FacesMessage.SEVERITY_INFO;

           //agregar a la lista
      this.getListaDepartamentoBean().getLstDepartamento().add(departamento);

      //limpiar campos
      this.limpiar();
      
          } catch (Exception ex) {
      severity = FacesMessage.SEVERITY_ERROR;
      sMensaje = "Error al crear: " + ex.getMessage();

    }finally {
      fm = new  FacesMessage(severity,sMensaje,null);
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
      departamentoRNLocal.edit(this.getDepartamento(), usuarioLogerBean.getUsuario(), usuarioLogerBean.getUrl());

      sMensaje = "El dato fue modificado ";
      severity = FacesMessage.SEVERITY_INFO;

      //elimino y agrego el organismo modificado a la lista
      int iPos = this.getListaDepartamentoBean().getLstDepartamento().indexOf(this.getDepartamento());

      this.getListaDepartamentoBean().getLstDepartamento().remove(iPos);
      this.getListaDepartamentoBean().getLstDepartamento().add(iPos, this.getDepartamento());

      this.getCbAction().setValue("Editar");
      this.getCbAction().setDisabled(true);
    }catch (Exception ex) {
      severity = FacesMessage.SEVERITY_ERROR;
      sMensaje = "Error al modificar: " + ex.getMessage();
      
    }finally {
      fm = new  FacesMessage(severity,sMensaje,null);
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
      }
      else {
        sMensaje = "El dato fue recuperado";
      }
      System.out.println("Entro al delete metodo");
      departamentoRNLocal.remove(this.getDepartamento(), bEstado, usuarioLogerBean.getUsuario(), usuarioLogerBean.getUrl());
      
      int iPos= this.getListaDepartamentoBean().getLstDepartamento().indexOf(this.getDepartamento());
      this.setDepartamento(this.getListaDepartamentoBean().getLstDepartamento().get(iPos));
      this.getDepartamento().setBorrado(bEstado);
      this.getListaDepartamentoBean().getLstDepartamento().remove(this.getDepartamento());
      this.getListaDepartamentoBean().getLstDepartamento().add(iPos, this.getDepartamento());

      
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
            
    }finally {
      fm = new  FacesMessage(severity,sMensaje,null);
      FacesContext fc = FacesContext.getCurrentInstance();
      fc.addMessage(null, fm);
    }
  }// fin delete

   public void habilitar(Boolean bEstado) {
        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        try {

            departamentoRNLocal.habilitar(this.getDepartamento(), bEstado, usuarioLogerBean.getUsuario(), usuarioLogerBean.getUrl());

            //elimino el organismo de la lista

            int iPos = this.getListaDepartamentoBean().getLstDepartamento().indexOf(this.getDepartamento());

            this.setDepartamento(this.getListaDepartamentoBean().getLstDepartamento().get(iPos));
        //    this.getDepartamento().setHabilitado(bEstado);
            this.getListaDepartamentoBean().getLstDepartamento().remove(iPos);
            this.getListaDepartamentoBean().getLstDepartamento().add(iPos, this.getDepartamento());
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
 public void cerrarDialog(){
        
        this.limpiar();
        this.setbCamposRequeridos(false);
        //cerrar el dialog
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("pDialog");
        context.execute("PF('dlgDepartamento').hide()");

    }//fin cerrarDialog
 
      
public void limpiar() {
    this.setDepartamento(new Departamento());
  }// fin limpiar

//public void cargarLocalidadesSelect(){
//        
//       List<Localidad> lstLoc = new ArrayList<Localidad>(Arrays.asList(this.getArrayLocalidadSelect()));
//        if (this.getAuxBean().getLstLocalidadSelect() == null) {
//            this.getAuxBean().setLstLocalidadSelect(new ArrayList<Localidad>(Arrays.asList(this.getArrayLocalidadSelect()
//                    )));
//        } else {
//            for (Localidad l : lstLoc) {
//                if (!this.getAuxBean().getLstLocalidadSelect().contains(l)) {
//                    this.getAuxBean().getLstLocalidadSelect().add(l);
//                }
//
//            }
//        }
//
//
//        System.out.println("cargarLocalidadesSelect: " + this.getAuxBean().getLstLocalidadSelect());
//
//
//    }
// public void quitarLocalidad() {
//        Localidad localidad=(Localidad) this.getDataTableLocalidad().getRowData();
//        this.getAuxBean().getLstLocalidadSelect().remove(localidad);
//        localidad.setDepartamento(null);
//                try {
//          
//          departamentoRNLocal.edit(departamento);
//           
//        } catch (Exception ex) {
//            Logger.getLogger(DepartamentoBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }

 public void cargarProvinciasSelect(SelectEvent event){
       
    System.out.println(" entra a cargarProvinciasSelect: ");
      Provincia provincia = ((Provincia) event.getObject());
    System.out.println(" provincia: " + provincia);  
    this.getDepartamento().setProvincia(provincia);
      
    //List<Provincia> lstProv = new ArrayList<Provincia>(Arrays.asList(this.getArrayProvinciaSelect()));

 
//    if(this.getAuxBean().getLstProvinciaSelect()== null){
//        this.getAuxBean().setLstProvinciaSelect(new ArrayList<Provincia>(Arrays.asList(this.getArrayProvinciaSelect())));
//    } else{
//        for(Provincia p : lstProv){
//            if(!this.getAuxBean().getLstProvinciaSelect().contains(p)){
//                this.getAuxBean().getLstProvinciaSelect().add(p);
//            }
//        }
//    }
      
        


        System.out.println("cargarProvinciasSelect: " + this.getAuxBean().getLstProvinciaSelect());


    }
 public void quitarProvincia() {
     Provincia provincia =(Provincia) this.getDataTableProvincia().getRowData();
     this.getAuxBean().getLstProvinciaSelect().remove(provincia);
     provincia.setPais(null);
                try {
          
          departamentoRNLocal.edit(departamento, usuarioLogerBean.getUsuario(), usuarioLogerBean.getUrl());
           
        } catch (Exception ex) {
            Logger.getLogger(DepartamentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

public void abrirDialogDomicilio(){
        this.setbCamposRequeridos(false);
        
        //desactivar todos los combos
        this.setbProvincia(Boolean.TRUE);
        this.setbDepartamento(Boolean.TRUE);
        this.setbLocalidad(Boolean.TRUE);
        //this.setbBarrio(Boolean.TRUE);
        
        RequestContext context = RequestContext.getCurrentInstance(); 
        context.execute("PF('dlgDomicilio').show();");

    }
    
    public void cerrarDialogDomicilio(){
        this.setbCamposRequeridos(true);
        
        RequestContext context = RequestContext.getCurrentInstance(); 
        context.execute("PF('dlgDomicilio').hide();");

    }


     public void activarProvincia(){
        this.setbDepartamento(Boolean.TRUE);
        this.setbLocalidad(Boolean.TRUE);
       // this.setbBarrio(Boolean.TRUE);
    }//fin activarProvincia
    
    public void activarDepartamento(){
        this.setbDepartamento(Boolean.FALSE);
        this.setbLocalidad(Boolean.TRUE);
        //this.setbBarrio(Boolean.TRUE);
    }//fin activarDepartamento
    
    public void activarLocalidad(){
        this.setbDepartamento(Boolean.TRUE);
        this.setbLocalidad(Boolean.FALSE);
       // this.setbBarrio(Boolean.TRUE);
    }//fin activarDepartamento
    
    public void activarBarrio(){
        this.setbDepartamento(Boolean.TRUE);
        this.setbLocalidad(Boolean.TRUE);
        //this.setbBarrio(Boolean.FALSE);
    }//fin activarDepartamento
    
    public void cargarDepartamentoLike() {
        try {

            //tipoOp = 1: dni 2: Apell y Nomb 
            this.getListaDepartamentoBean().setLstDepartamento(
                    this.departamentoRNLocal.findLike(this.getCadenaDepartamento(),Boolean.TRUE));

        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error: " + ex.getMessage(), null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }

    }//fin cargarDepartamentoLike  

}// FIN CLASE
