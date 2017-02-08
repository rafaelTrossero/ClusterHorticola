/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidad.Barrio;
import entidad.Departamento;
import entidad.Especie;
import entidad.Localidad;
import entidad.Pais;
import entidad.Productor;
import entidad.Provincia;
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

    public ListaTenenciaBean getListaTenenciaBean() {
        return listaTenenciaBean;
    }

    public void setListaTenenciaBean(ListaTenenciaBean listaTenenciaBean) {
        this.listaTenenciaBean = listaTenenciaBean;
    }

    public ListaProductorBean getListaProductorBean() {
        return listaProductorBean;
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

        this.getListaPaisBean().cargarPais();
        this.getListaPaisBean().cargarSIPais();
        this.getListaProvinciaBean().cargarProvincias();
        this.getListaProvinciaBean().cargarSIProvincias();
        this.getListaLocalidadBean().cargarLocalidades();
        this.getListaLocalidadBean().cargarSILocalidades();

        return "productor.xhtml?faces-redirect=true";
    }

    public String entrarFormEspecie() {

        this.getListaEspecieBean().setLstEspecie(new ArrayList<Especie>());
        this.getListaEspecieBean().setLstSIEspecie(null);
        this.getListaEspecieBean().cargarEspecie();
        this.getListaEspecieBean().cargarSIEspecie();

        return "especie.xhtml?faces-redirect=true";
    }

    public String entrarFormVariedad() {

        this.getListaVariedadBean().setLstVariedad(new ArrayList<Variedad>());
        this.getListaVariedadBean().setLstSIVariedad(null);
        this.getListaVariedadBean().cargarVariedad();
        this.getListaVariedadBean().cargarSIVariedad();

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

        return "preciosHortaliza.xhtml?faces-redirect=true";
    }
    
      public String entrarFormIngresoMercado() {
        this.getListaEspecieBean().setLstEspecie(new ArrayList<Especie>());
        this.getListaEspecieBean().setLstSIEspecie(null);
        this.getListaEspecieBean().cargarEspecie();
        this.getListaEspecieBean().cargarSIEspecie();
        this.getListaProductorBean().setLstProductor(new ArrayList<Productor>());
        this.getListaProductorBean().setLstSIProductor(null);
        
         this.getListaProductorBean().cargar_productor();
        this.getListaProductorBean().cargar_SI_productor();
    

        return "ingresoMercado.xhtml?faces-redirect=true";
    }

    public String entrarFormCampo() {

        this.getListaPaisBean().cargarPais();
        this.getListaPaisBean().cargarSIPais();
        this.getListaCampoBean().cargar_campo();
        this.getListaPaisBean().cargarSIPais();
        this.getListaTenenciaBean().cargar_tenencia();
        this.getListaTenenciaBean().cargar_SI_tenencia();
        this.getListaProductorBean().cargar_productor();
        this.getListaProductorBean().cargar_SI_productor();
        this.getListaTenenciaBean().cargar_SI_tenencia();
        this.getListaProvinciaBean().cargarProvincias();
        this.getListaProvinciaBean().cargarSIProvincias();
        this.getListaLocalidadBean().cargarLocalidades();
        this.getListaLocalidadBean().cargarSILocalidades();

        return "campo.xhtml?faces-redirect=true";
    }
}
