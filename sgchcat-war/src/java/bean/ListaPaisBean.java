/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.PaisRNLocal;
import entidad.Pais;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author AFerSor
 */
@ManagedBean
@SessionScoped
public class ListaPaisBean {

    @ManagedProperty(value = "#{usuarioLogerBean}")
    private UsuarioLogerBean usuarioLogerBean;
    private List<Pais> lstPais;
    private List<SelectItem> lstSINacionalidad;
    private List<SelectItem> lstSIPais;
    private int iActionBtnSelect;
    @EJB
    private PaisRNLocal paisRNLocal;

    public ListaPaisBean() {
        lstPais = new ArrayList<Pais>();
    }

    @PostConstruct
    public void init() {
        //cargarPais();
    }

    public List<Pais> getLstPais() {
        return lstPais;
    }

    public void setLstPais(List<Pais> lstPais) {
        this.lstPais = lstPais;
    }

    public List<SelectItem> getLstSINacionalidad() {
        return lstSINacionalidad;
    }

    public void setLstSINacionalidad(List<SelectItem> lstSINacionalidad) {
        this.lstSINacionalidad = lstSINacionalidad;
    }

    public List<SelectItem> getLstSIPais() {

        return lstSIPais;
    }

    public void setLstSIPais(List<SelectItem> lstSIPais) {
        this.lstSIPais = lstSIPais;
    }

    public int getiActionBtnSelect() {
        return iActionBtnSelect;
    }

    public void setiActionBtnSelect(int iActionBtnSelect) {
        this.iActionBtnSelect = iActionBtnSelect;
    }

    public PaisRNLocal getPaisRNLocal() {
        return paisRNLocal;
    }

    public void setPaisRNLocal(PaisRNLocal paisRNLocal) {
        this.paisRNLocal = paisRNLocal;
    }

    public UsuarioLogerBean getUsuarioLogerBean() {
        return usuarioLogerBean;
    }

    public void setUsuarioLogerBean(UsuarioLogerBean usuarioLogerBean) {
        this.usuarioLogerBean = usuarioLogerBean;
    }

    public void cargarPais() {
        try {
            this.setLstPais(paisRNLocal.findAll());
        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al cargar los paises: " + ex,
                    null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }//fin cargarNacionalidad

    public void cargarSINacionalidad() {


        this.setLstSINacionalidad(new ArrayList<SelectItem>());
        for (Pais p : this.getLstPais()) {
            SelectItem si = new SelectItem(p, p.getNacionalidad());
            this.getLstSINacionalidad().add(si);
        }//fin for

    }//fin cargarSINacionalidad

    public void cargarSIPais() {

        this.setLstSIPais(new ArrayList<SelectItem>());

        for (Pais p : this.getLstPais()) {
            if (!p.getBorrado()) {
                SelectItem si = new SelectItem(p, p.getDescripcion());
                this.getLstSIPais().add(si);
            }
        }//fin for
        System.out.println("Termino cargar Pais: " + this.getLstSIPais());
    }//fin cargarSINacionalidad
}//FIN CLASE
