/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidad.Departamento;
import entidad.Pais;
import entidad.Provincia;
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

    public void setListaDepartamentoBean(ListaDepartamentoBean listaDepartamentoBean) {
        this.listaDepartamentoBean = listaDepartamentoBean;
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

}
