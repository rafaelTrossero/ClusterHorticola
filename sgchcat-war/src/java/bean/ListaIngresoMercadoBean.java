/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.IngresoMercadoRNLocal;
import RN.VariedadRNLocal;
import entidad.IngresoMercado;
import entidad.Variedad;
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
 * @author RafaTrossero
 */
@ManagedBean
@SessionScoped
public class ListaIngresoMercadoBean {

     @ManagedProperty(value = "#{usuarioLogerBean}")
    private UsuarioLogerBean usuarioLogerBean;
    private List<IngresoMercado> lstIngresoMercado;
    
    private List<SelectItem> lstSIIngresoMercado;
    private int iActionBtnSelect;
    @EJB
    private IngresoMercadoRNLocal ingresoMercadoRNLocal;
    
    public ListaIngresoMercadoBean() {
        this.lstIngresoMercado = new ArrayList<>();
    }

    public UsuarioLogerBean getUsuarioLogerBean() {
        return usuarioLogerBean;
    }

    public void setUsuarioLogerBean(UsuarioLogerBean usuarioLogerBean) {
        this.usuarioLogerBean = usuarioLogerBean;
    }

    public List<IngresoMercado> getLstIngresoMercado() {
        return lstIngresoMercado;
    }

    public void setLstIngresoMercado(List<IngresoMercado> lstIngresoMercado) {
        this.lstIngresoMercado = lstIngresoMercado;
    }

    public List<SelectItem> getLstSIIngresoMercado() {
        return lstSIIngresoMercado;
    }

    public void setLstSIIngresoMercado(List<SelectItem> lstSIIngresoMercado) {
        this.lstSIIngresoMercado = lstSIIngresoMercado;
    }

    public int getiActionBtnSelect() {
        return iActionBtnSelect;
    }

    public void setiActionBtnSelect(int iActionBtnSelect) {
        this.iActionBtnSelect = iActionBtnSelect;
    }

    public IngresoMercadoRNLocal getIngresoMercadoRNLocal() {
        return ingresoMercadoRNLocal;
    }

    public void setIngresoMercadoRNLocal(IngresoMercadoRNLocal ingresoMercadoRNLocal) {
        this.ingresoMercadoRNLocal = ingresoMercadoRNLocal;
    }
    
    public void cargarIngresoMercado() {
        try {
            this.setLstIngresoMercado(ingresoMercadoRNLocal.findAll());
        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al cargar las IngresoMercado: " + ex,
                    null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }//fin 
    /*
     public void cargarSIIngresoMercado() {

        this.setLstSIIngresoMercado(new ArrayList<SelectItem>());

        for (IngresoMercado p : this.getLstIngresoMercado()) {
            
                SelectItem si = new SelectItem(p, p.g);
                this.getLstSIVariedad().add(si);
           
        }//fin for
        System.out.println("Termino cargar Variedad: " + this.getLstSIVariedad());
    }//fin 
    */
}
