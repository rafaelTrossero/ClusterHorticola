/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import RN.EmpaqueRNLocal;
import entidad.Empaque;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author cris
 */
@ManagedBean
@SessionScoped
public class ListaEmpaqueBean {
@ManagedProperty(value = "#{usuarioLogerBean}")
    private UsuarioLogerBean usuarioLogerBean;
    private List<Empaque> lstEmpaque;
    
    private List<SelectItem> lstSIEmpaque;
    private int iActionBtnSelect;
    @EJB
    private EmpaqueRNLocal empaqueRNLocal;
    /**
     * Creates a new instance of ListaEmpaqueBean
     */
    public ListaEmpaqueBean() {
        this.lstEmpaque = new ArrayList<>();
    }

    public UsuarioLogerBean getUsuarioLogerBean() {
        return usuarioLogerBean;
    }

    public void setUsuarioLogerBean(UsuarioLogerBean usuarioLogerBean) {
        this.usuarioLogerBean = usuarioLogerBean;
    }

    public List<Empaque> getLstEmpaque() {
        return lstEmpaque;
    }

    public void setLstEmpaque(List<Empaque> lstEmpaque) {
        this.lstEmpaque = lstEmpaque;
    }

    public List<SelectItem> getLstSIEmpaque() {
        return lstSIEmpaque;
    }

    public void setLstSIEmpaque(List<SelectItem> lstSIEmpaque) {
        this.lstSIEmpaque = lstSIEmpaque;
    }

    public int getiActionBtnSelect() {
        return iActionBtnSelect;
    }

    public void setiActionBtnSelect(int iActionBtnSelect) {
        this.iActionBtnSelect = iActionBtnSelect;
    }

    public EmpaqueRNLocal getEmpaqueRNLocal() {
        return empaqueRNLocal;
    }

    public void setEmpaqueRNLocal(EmpaqueRNLocal empaqueRNLocal) {
        this.empaqueRNLocal = empaqueRNLocal;
    }
    
    public void cargarIngresoEmpaque() {
        try {
            this.setLstEmpaque(empaqueRNLocal.findAll());
        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al cargar los empaques: " + ex,
                    null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }//fin
    
   
    
    
}
