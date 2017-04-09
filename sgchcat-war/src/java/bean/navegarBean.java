/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidad.Barrio;
import entidad.Campo;
import entidad.Departamento;
import entidad.Empaque;
import entidad.Especie;
import entidad.IngresoMercado;
import entidad.Localidad;
import entidad.Pais;
import entidad.Productor;
import entidad.Provincia;
import entidad.Usuario;
import entidad.Variedad;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author jmoreno
 */
@ManagedBean
@RequestScoped
public class navegarBean {

    @ManagedProperty("#{listaPaisBean}")
    private ListaPaisBean listaPaisBean;
    @ManagedProperty("#{listaProvinciaBean}")
    private ListaProvinciaBean listaProvinciaBean;
    @ManagedProperty("#{listaDepartamentoBean}")
    private ListaDepartamentoBean listaDepartamentoBean;
    @ManagedProperty("#{listaLocalidadBean}")
    private ListaLocalidadBean listaLocalidadBean;
    @ManagedProperty("#{listaBarrioBean}")
    private ListaBarrioBean listaBarrioBean;
    @ManagedProperty("#{listaTenenciaBean}")
    private ListaTenenciaBean listaTenenciaBean;
    @ManagedProperty("#{listaProductorBean}")
    private ListaProductorBean listaProductorBean;
    @ManagedProperty("#{listaCampoBean}")
    private ListaCampoBean listaCampoBean;
    @ManagedProperty("#{listaEspecieBean}")
    private ListaEspecieBean listaEspecieBean;
    @ManagedProperty("#{listaVariedadBean}")
    private ListaVariedadBean listaVariedadBean;
    @ManagedProperty("#{listaEmpaqueBean}")
    private ListaEmpaqueBean listaEmpaqueBean;
    @ManagedProperty("#{listaUsuarioBean}")
    private ListaUsuarioBean listaUsuarioBean;
    @ManagedProperty("#{listIdentificationTypeBean1}")
    private ListIdentificationTypeBean1 listIdentificationTypeBean1;
    @ManagedProperty("#{listaCampoProductorBean}")
     private ListaCampoProductorBean listaCampoProductorBean;
    @ManagedProperty("#{listaIngresoMercadoBean}")
     private ListaIngresoMercadoBean listaIngresoMercadoBean;

    

    /**
     * Creates a new instance of navegarBean
     */
    public navegarBean() {
    }

    public ListaPaisBean getListaPaisBean() {
        return listaPaisBean;
    }

    public void setListaPaisBean(ListaPaisBean listaPaisBean) {
        this.listaPaisBean = listaPaisBean;
    }

    public ListaProvinciaBean getListaProvinciaBean() {
        return listaProvinciaBean;
    }

    public void setListaProvinciaBean(ListaProvinciaBean listaProvinciaBean) {
        this.listaProvinciaBean = listaProvinciaBean;
    }

    public ListaDepartamentoBean getListaDepartamentoBean() {
        return listaDepartamentoBean;
    }

    public ListaEmpaqueBean getListaEmpaqueBean() {
        return listaEmpaqueBean;
    }

    public void setListaEmpaqueBean(ListaEmpaqueBean listaEmpaqueBean) {
        this.listaEmpaqueBean = listaEmpaqueBean;
    }

    public ListaTenenciaBean getListaTenenciaBean() {
        return listaTenenciaBean;
    }

    public void setListaTenenciaBean(ListaTenenciaBean listaTenenciaBean) {
        this.listaTenenciaBean = listaTenenciaBean;
    }

    public ListaProductorBean getListaProductorBean() {
        return listaProductorBean;
    }

    public ListIdentificationTypeBean1 getListIdentificationTypeBean1() {
        return listIdentificationTypeBean1;
    }

    public void setListIdentificationTypeBean1(ListIdentificationTypeBean1 listIdentificationTypeBean1) {
        this.listIdentificationTypeBean1 = listIdentificationTypeBean1;
    }

 
    public void setListaProductorBean(ListaProductorBean listaProductorBean) {
        this.listaProductorBean = listaProductorBean;
    }

    public ListaCampoBean getListaCampoBean() {
        return listaCampoBean;
    }

    public void setListaCampoBean(ListaCampoBean listaCampoBean) {
        this.listaCampoBean = listaCampoBean;
    }

    public void setListaDepartamentoBean(ListaDepartamentoBean listaDepartamentoBean) {
        this.listaDepartamentoBean = listaDepartamentoBean;
    }

    public ListaLocalidadBean getListaLocalidadBean() {
        return listaLocalidadBean;
    }

    public void setListaLocalidadBean(ListaLocalidadBean listaLocalidadBean) {
        this.listaLocalidadBean = listaLocalidadBean;
    }

    public ListaBarrioBean getListaBarrioBean() {
        return listaBarrioBean;
    }

    public void setListaBarrioBean(ListaBarrioBean listaBarrioBean) {
        this.listaBarrioBean = listaBarrioBean;
    }

    public ListaEspecieBean getListaEspecieBean() {
        return listaEspecieBean;
    }

    public void setListaEspecieBean(ListaEspecieBean listaEspecieBean) {
        this.listaEspecieBean = listaEspecieBean;
    }

    public ListaVariedadBean getListaVariedadBean() {
        return listaVariedadBean;
    }

    public void setListaVariedadBean(ListaVariedadBean listaVariedadBean) {
        this.listaVariedadBean = listaVariedadBean;
    }

    public ListaUsuarioBean getListaUsuarioBean() {
        return listaUsuarioBean;
    }

    public void setListaUsuarioBean(ListaUsuarioBean listaUsuarioBean) {
        this.listaUsuarioBean = listaUsuarioBean;
    }

    public ListaCampoProductorBean getListaCampoProductorBean() {
        return listaCampoProductorBean;
    }

    public void setListaCampoProductorBean(ListaCampoProductorBean listaCampoProductorBean) {
        this.listaCampoProductorBean = listaCampoProductorBean;
    }

    public ListaIngresoMercadoBean getListaIngresoMercadoBean() {
        return listaIngresoMercadoBean;
    }

    public void setListaIngresoMercadoBean(ListaIngresoMercadoBean listaIngresoMercadoBean) {
        this.listaIngresoMercadoBean = listaIngresoMercadoBean;
    }
    

    public String entrarFormPais() {
        this.getListaPaisBean().setLstPais(new ArrayList<Pais>());
        this.getListaPaisBean().setLstSIPais(null);

        return "pais.xhtml?faces-redirect=true";
    }

    public String entrarFormProvincia() {
        this.getListaPaisBean().setLstPais(new ArrayList<Pais>());
        this.getListaPaisBean().setLstSIPais(null);
        this.getListaPaisBean().cargarPais();
        this.getListaPaisBean().cargarSIPais();
        this.getListaProvinciaBean().setLstProvincia(new ArrayList<Provincia>());
        this.getListaProvinciaBean().setLstSIProvincia(null);

        return "provincia.xhtml?faces-redirect=true";
    }

    public String entrarFormDepartamento() {
        this.getListaPaisBean().setLstPais(new ArrayList<Pais>());
        this.getListaPaisBean().setLstSIPais(null);
        this.getListaPaisBean().cargarPais();
        this.getListaPaisBean().cargarSIPais();
        this.getListaProvinciaBean().setLstProvincia(new ArrayList<Provincia>());
        this.getListaProvinciaBean().setLstSIProvincia(null);
        this.getListaProvinciaBean().cargarProvincias();
        this.getListaProvinciaBean().cargarSIProvincias();
        this.getListaDepartamentoBean().setLstDepartamento(new ArrayList<Departamento>());
        this.getListaDepartamentoBean().setLstSIDepartamento(null);

        return "departamento.xhtml?faces-redirect=true";
    }

    public String entrarFormLocalidad() {
        this.getListaPaisBean().setLstPais(new ArrayList<Pais>());
        this.getListaPaisBean().setLstSIPais(null);
        this.getListaPaisBean().cargarPais();
        this.getListaPaisBean().cargarSIPais();
        this.getListaProvinciaBean().setLstProvincia(new ArrayList<Provincia>());
        this.getListaProvinciaBean().setLstSIProvincia(null);
        this.getListaProvinciaBean().cargarProvincias();
        this.getListaProvinciaBean().cargarSIProvincias();
        this.getListaDepartamentoBean().setLstDepartamento(new ArrayList<Departamento>());
        this.getListaDepartamentoBean().setLstSIDepartamento(null);
        this.getListaDepartamentoBean().cargarDepartamentos();
        this.getListaDepartamentoBean().cargarSIDepartamentos();
        this.getListaLocalidadBean().setLstLocalidad(new ArrayList<Localidad>());
        this.getListaLocalidadBean().setLstSILocalidad(null);

        return "localidad.xhtml?faces-redirect=true";
    }

    public String entrarFormBarrio() {
        this.getListaPaisBean().setLstPais(new ArrayList<Pais>());
        this.getListaPaisBean().setLstSIPais(null);
        this.getListaPaisBean().cargarPais();
        this.getListaPaisBean().cargarSIPais();
        this.getListaProvinciaBean().setLstProvincia(new ArrayList<Provincia>());
        this.getListaProvinciaBean().setLstSIProvincia(null);
        this.getListaProvinciaBean().cargarProvincias();
        this.getListaProvinciaBean().cargarSIProvincias();
        this.getListaDepartamentoBean().setLstDepartamento(new ArrayList<Departamento>());
        this.getListaDepartamentoBean().setLstSIDepartamento(null);
        this.getListaDepartamentoBean().cargarDepartamentos();
        this.getListaDepartamentoBean().cargarSIDepartamentos();
        this.getListaLocalidadBean().setLstLocalidad(new ArrayList<Localidad>());
        this.getListaLocalidadBean().setLstSILocalidad(null);
        this.getListaLocalidadBean().cargarLocalidades();
        this.getListaLocalidadBean().cargarSILocalidades();
        this.getListaBarrioBean().setLstBarrio(new ArrayList<Barrio>());
        this.getListaBarrioBean().setLstSIBarrio(null);

        return "barrio.xhtml?faces-redirect=true";
    }

    public String entrarFormProductor() {
         this.getListaProductorBean().setLstProductor(new ArrayList<Productor>());
        this.getListaProductorBean().setLstSIProductor(null);
        this.getListaProductorBean().cargar_productor();
        this.getListaProductorBean().cargar_SI_productor();

        this.getListaPaisBean().cargarPais();
        this.getListaPaisBean().cargarSIPais();
        this.getListaProvinciaBean().cargarProvincias();
        this.getListaProvinciaBean().cargarSIProvincias();
        this.getListaLocalidadBean().cargarLocalidades();
        this.getListaLocalidadBean().cargarSILocalidades();

        return "productor.xhtml?faces-redirect=true";
    }
    public String entrarFormUsuario() {
       this.getListaUsuarioBean().setLstUsuario(new ArrayList<Usuario>());
        this.getListaUsuarioBean().cargar_usuario();
         this.getListIdentificationTypeBean1().setLstSIIdentificationType(null);
        this.getListIdentificationTypeBean1().cargarIdentificationTypes();
        

        return "Usuario.xhtml?faces-redirect=true";
    }

    public String entrarFormEspecie() {

        this.getListaEspecieBean().setLstEspecie(new ArrayList<Especie>());
        this.getListaEspecieBean().setLstSIEspecie(null);
        this.getListaEspecieBean().cargarEspecie();
        this.getListaEspecieBean().cargarSIEspecie();
        this.getListaEspecieBean().setLstEspecieActive(new ArrayList<Especie>());
        this.getListaEspecieBean().setLstSIEspecieActive(null);
        this.getListaEspecieBean().cargarEspecieActive();
        this.getListaEspecieBean().cargarSIEspecieActive();

        return "especie.xhtml?faces-redirect=true";
    }

    public String entrarFormVariedad() {

        this.getListaVariedadBean().setLstVariedad(new ArrayList<Variedad>());
        this.getListaVariedadBean().setLstSIVariedad(null);
        this.getListaVariedadBean().cargarVariedad();
        this.getListaVariedadBean().cargarSIVariedad();
        this.getListaEspecieBean().setLstEspecie(new ArrayList<Especie>());
        this.getListaEspecieBean().setLstSIEspecie(null);
        this.getListaEspecieBean().cargarEspecie();
        this.getListaEspecieBean().cargarSIEspecie();
        
        this.getListaVariedadBean().setLstVariedadActive(new ArrayList<Variedad>());
        this.getListaVariedadBean().setLstSIVariedadActive(null);
        this.getListaVariedadBean().cargarVariedadActive();
        this.getListaVariedadBean().cargarSIVariedadActive();
        this.getListaEspecieBean().setLstEspecieActive(new ArrayList<Especie>());
        this.getListaEspecieBean().setLstSIEspecieActive(null);
        this.getListaEspecieBean().cargarEspecieActive();
        this.getListaEspecieBean().cargarSIEspecieActive();

        return "variedad.xhtml?faces-redirect=true";
    }

    public String entrarFormPrecios() {
        this.getListaEspecieBean().setLstEspecie(new ArrayList<Especie>());
        this.getListaEspecieBean().setLstSIEspecie(null);
        this.getListaEspecieBean().cargarEspecie();
        this.getListaEspecieBean().cargarSIEspecie();
        this.getListaVariedadBean().setLstVariedad(new ArrayList<Variedad>());
        this.getListaVariedadBean().setLstSIVariedad(null);
        this.getListaVariedadBean().cargarVariedad();
        this.getListaVariedadBean().cargarSIVariedad();
        
         this.getListaEspecieBean().setLstEspecieActive(new ArrayList<Especie>());
        this.getListaEspecieBean().setLstSIEspecieActive(null);
        this.getListaEspecieBean().cargarEspecieActive();
        this.getListaEspecieBean().cargarSIEspecieActive();
        this.getListaVariedadBean().setLstVariedadActive(new ArrayList<Variedad>());
        this.getListaVariedadBean().setLstSIVariedadActive(null);
        this.getListaVariedadBean().cargarVariedadActive();
        this.getListaVariedadBean().cargarSIVariedadActive();
        return "preciosHortaliza.xhtml?faces-redirect=true";
    }
    
      
      public String entrarFormIngresoMercado() {
          this.getListaProvinciaBean().setLstProvincia(new ArrayList<Provincia>());
        this.getListaProvinciaBean().setLstSIProvincia(null);
        this.getListaProvinciaBean().cargarProvincias();
        this.getListaProvinciaBean().cargarSIProvincias();
        this.getListaEspecieBean().setLstEspecie(new ArrayList<Especie>());
        this.getListaEspecieBean().setLstSIEspecie(null);
        this.getListaEspecieBean().cargarEspecie();
        this.getListaEspecieBean().cargarSIEspecie();
        this.getListaProductorBean().setLstProductor(new ArrayList<Productor>());
        this.getListaProductorBean().setLstSIProductor(null);
        this.getListaVariedadBean().setLstVariedad(new ArrayList<Variedad>());
        this.getListaVariedadBean().setLstSIVariedad(null);
        this.getListaVariedadBean().cargarVariedad();
        this.getListaVariedadBean().cargarSIVariedad();
        
        this.getListaEspecieBean().setLstEspecieActive(new ArrayList<Especie>());
        this.getListaEspecieBean().setLstSIEspecieActive(null);
        this.getListaEspecieBean().cargarEspecieActive();
        this.getListaEspecieBean().cargarSIEspecieActive();
        this.getListaProductorBean().setLstProductorActivo(new ArrayList<Productor>());
        this.getListaProductorBean().setLstSIProductorActivo(null);
        this.getListaVariedadBean().setLstVariedadActive(new ArrayList<Variedad>());
        this.getListaVariedadBean().setLstSIVariedadActive(null);
        this.getListaVariedadBean().cargarVariedadActive();
        this.getListaVariedadBean().cargarSIVariedadActive();
        
         this.getListaProductorBean().cargar_productor();
        this.getListaProductorBean().cargar_SI_productor();
          this.getListaProductorBean().cargar_productorActivo();
        this.getListaProductorBean().cargar_SI_productorActivo();
        this.getListaIngresoMercadoBean().setLstIngresoMercado(new ArrayList<IngresoMercado>());
        this.getListaIngresoMercadoBean().cargarIngresoMercado();
        

        return "ingresoMercado.xhtml?faces-redirect=true";
    }

       public String entrarFormEmpaque() {
        this.getListaEspecieBean().setLstEspecie(new ArrayList<Especie>());
        this.getListaEspecieBean().setLstSIEspecie(null);
        this.getListaEspecieBean().cargarEspecie();
        this.getListaEspecieBean().cargarSIEspecie();
        this.getListaProductorBean().setLstProductor(new ArrayList<Productor>());
        this.getListaProductorBean().setLstSIProductor(null);
        this.getListaVariedadBean().setLstVariedad(new ArrayList<Variedad>());
        this.getListaVariedadBean().setLstSIVariedad(null);
        
        this.getListaEmpaqueBean().setLstEmpaque(new ArrayList<Empaque>());
        this.getListaEmpaqueBean().cargarIngresoEmpaque();
        
        this.getListaVariedadBean().cargarVariedad();
        this.getListaVariedadBean().cargarSIVariedad();
        
         this.getListaProductorBean().cargar_productor();
        this.getListaProductorBean().cargar_SI_productor();
        
        
         this.getListaEspecieBean().setLstEspecieActive(new ArrayList<Especie>());
        this.getListaEspecieBean().setLstSIEspecieActive(null);
        this.getListaEspecieBean().cargarEspecieActive();
        this.getListaEspecieBean().cargarSIEspecieActive();
        this.getListaProductorBean().setLstProductorActivo(new ArrayList<Productor>());
        this.getListaProductorBean().setLstSIProductorActivo(null);
        this.getListaVariedadBean().setLstVariedadActive(new ArrayList<Variedad>());
        this.getListaVariedadBean().setLstSIVariedadActive(null);
        
        this.getListaEmpaqueBean().setLstEmpaque(new ArrayList<Empaque>());
        this.getListaEmpaqueBean().cargarIngresoEmpaque();
        
        this.getListaVariedadBean().cargarVariedadActive();
        this.getListaVariedadBean().cargarSIVariedadActive();
        
         this.getListaProductorBean().cargar_productorActivo();
        this.getListaProductorBean().cargar_SI_productorActivo();

        return "empaque.xhtml?faces-redirect=true";
    }
 public String entrarFormReporteEmpaque() {

        
        return "reporteEmpaque.xhtml?faces-redirect=true";
    }
    public String entrarFormCampo() {

        this.getListaPaisBean().cargarPais();
        this.getListaPaisBean().cargarSIPais();
        this.getListaCampoBean().cargar_campo();
        this.getListaCampoBean().cargar_SI_campo();
        this.getListaTenenciaBean().cargar_tenencia();
        this.getListaTenenciaBean().cargar_SI_tenencia();
        this.getListaProductorBean().cargar_productorActivo();
        this.getListaProductorBean().cargar_SI_productorActivo();
        this.getListaProductorBean().cargar_productor();
        this.getListaProductorBean().cargar_SI_productor();
        
        this.getListaProductorBean().cargar_productorActivo();
        this.getListaProductorBean().cargar_SI_productorActivo();
        this.getListaProductorBean().cargar_productor();
        this.getListaProductorBean().cargar_SI_productor();
     
     
        this.getListaProvinciaBean().cargarProvincias();
        this.getListaProvinciaBean().cargarSIProvincias();
        this.getListaLocalidadBean().cargarLocalidades();
        this.getListaLocalidadBean().cargarSILocalidades();
        this.getListaCampoProductorBean().cargar_campo_productor();
           this.getListaVariedadBean().setLstVariedad(new ArrayList<Variedad>());
        this.getListaVariedadBean().setLstSIVariedad(null);
        this.getListaVariedadBean().cargarVariedad();
        this.getListaVariedadBean().cargarSIVariedad();
        this.getListaEspecieBean().setLstEspecie(new ArrayList<Especie>());
        this.getListaEspecieBean().setLstSIEspecie(null);
        this.getListaEspecieBean().cargarEspecie();
        this.getListaEspecieBean().cargarSIEspecie();
        
   
        this.getListaCampoProductorBean().cargar_campo_productor();
           this.getListaVariedadBean().setLstVariedadActive(new ArrayList<Variedad>());
        this.getListaVariedadBean().setLstSIVariedadActive(null);
        this.getListaVariedadBean().cargarVariedadActive();
        this.getListaVariedadBean().cargarSIVariedadActive();
        this.getListaEspecieBean().setLstEspecieActive(new ArrayList<Especie>());
        this.getListaEspecieBean().setLstSIEspecieActive(null);
        this.getListaEspecieBean().cargarEspecieActive();
        this.getListaEspecieBean().cargarSIEspecieActive();
        return "campo.xhtml?faces-redirect=true";
    }
    
     public String entrarFormCultivosCampo() {

         this.getListaCampoBean().setLstCampo(new ArrayList<Campo>());
         this.getListaCampoBean().cargar_campo();
        this.getListaCampoProductorBean().cargar_campo_productor();
           this.getListaVariedadBean().setLstVariedad(new ArrayList<Variedad>());
           this.getListaVariedadBean().cargarVariedad();
        this.getListaVariedadBean().setLstSIVariedad(null);
        
        this.getListaVariedadBean().cargarSIVariedad();
        this.getListaEspecieBean().setLstEspecie(new ArrayList<Especie>());
        this.getListaEspecieBean().cargarEspecie();
        this.getListaEspecieBean().setLstSIEspecie(null);
       
        this.getListaEspecieBean().cargarSIEspecie();
        
          
           this.getListaVariedadBean().setLstVariedadActive(new ArrayList<Variedad>());
           this.getListaVariedadBean().cargarVariedadActive();
        this.getListaVariedadBean().setLstSIVariedadActive(null);
        
        this.getListaVariedadBean().cargarSIVariedadActive();
        this.getListaEspecieBean().setLstEspecieActive(new ArrayList<Especie>());
        this.getListaEspecieBean().cargarEspecieActive();
        this.getListaEspecieBean().setLstSIEspecieActive(null);
       
        this.getListaEspecieBean().cargarSIEspecieActive();
        return "cultivosCampo.xhtml?faces-redirect=true";
    }
}
