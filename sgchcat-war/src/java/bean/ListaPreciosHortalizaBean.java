/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.PreciosHortalizaRNLocal;
import entidad.PreciosHortaliza;
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
public class ListaPreciosHortalizaBean {

      @ManagedProperty(value = "#{usuarioLogerBean}")
    private UsuarioLogerBean usuarioLogerBean;
    private List<PreciosHortaliza> lstPreciosHortaliza;
    
    private List<SelectItem> lstSIPreciosHortaliza;
        private List<PreciosHortaliza> lstPreciosHortalizaActive;
    
    private List<SelectItem> lstSIPreciosHortalizaActive;
    private int iActionBtnSelect;
    @EJB
    private PreciosHortalizaRNLocal preciosHortalizaRNLocal;
    public ListaPreciosHortalizaBean() {
        lstPreciosHortaliza = new ArrayList<>();
    }

    public UsuarioLogerBean getUsuarioLogerBean() {
        return usuarioLogerBean;
    }

    public void setUsuarioLogerBean(UsuarioLogerBean usuarioLogerBean) {
        this.usuarioLogerBean = usuarioLogerBean;
    }

    public List<PreciosHortaliza> getLstPreciosHortaliza() {
        return lstPreciosHortaliza;
    }

    public List<PreciosHortaliza> getLstPreciosHortalizaActive() {
        return lstPreciosHortalizaActive;
    }

    public void setLstPreciosHortalizaActive(List<PreciosHortaliza> lstPreciosHortalizaActive) {
        this.lstPreciosHortalizaActive = lstPreciosHortalizaActive;
    }

    public List<SelectItem> getLstSIPreciosHortalizaActive() {
        return lstSIPreciosHortalizaActive;
    }

    public void setLstSIPreciosHortalizaActive(List<SelectItem> lstSIPreciosHortalizaActive) {
        this.lstSIPreciosHortalizaActive = lstSIPreciosHortalizaActive;
    }

    public void setLstPreciosHortaliza(List<PreciosHortaliza> lstPreciosHortaliza) {
        this.lstPreciosHortaliza = lstPreciosHortaliza;
    }

    public List<SelectItem> getLstSIPreciosHortaliza() {
        return lstSIPreciosHortaliza;
    }

    public void setLstSIPreciosHortaliza(List<SelectItem> lstSIPreciosHortaliza) {
        this.lstSIPreciosHortaliza = lstSIPreciosHortaliza;
    }

    public int getiActionBtnSelect() {
        return iActionBtnSelect;
    }

    public void setiActionBtnSelect(int iActionBtnSelect) {
        this.iActionBtnSelect = iActionBtnSelect;
    }

    public PreciosHortalizaRNLocal getPreciosHortalizaRNLocal() {
        return preciosHortalizaRNLocal;
    }

    public void setPreciosHortalizaRNLocal(PreciosHortalizaRNLocal preciosHortalizaRNLocal) {
        this.preciosHortalizaRNLocal = preciosHortalizaRNLocal;
    }
    
    
    
     public void cargarPreciosHortaliza() {
        try {
            this.setLstPreciosHortaliza(preciosHortalizaRNLocal.findAll());
        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al cargar los PreciosHortaliza: " + ex,
                    null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }//fin 
     
       public void cargarPreciosHortalizaActive() {
        try {
            this.setLstPreciosHortalizaActive(preciosHortalizaRNLocal.findAllActivo());
        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al cargar los PreciosHortaliza: " + ex,
                    null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
        this.cargarSIPreciosHortalizaActive();
    }//fin
    
     public void cargarSIPreciosHortaliza() {

        this.setLstSIPreciosHortaliza(new ArrayList<SelectItem>());

        for (PreciosHortaliza p : this.getLstPreciosHortaliza()) {
            
                SelectItem si = new SelectItem(p, p.getEspecie().getDescripcion()+ p.getVariedad().getDescripcion() + p.getPrecioMin() + p.getPrecioMax());
                this.getLstSIPreciosHortaliza().add(si);
           
        }//fin for
        System.out.println("Termino cargar PreciosHortaliza: " + this.getLstSIPreciosHortaliza());
    }//fin 
       public void cargarSIPreciosHortalizaActive() {

        this.setLstSIPreciosHortalizaActive(new ArrayList<SelectItem>());

        for (PreciosHortaliza p : this.getLstPreciosHortalizaActive()) {
            
                SelectItem si = new SelectItem(p, p.getEspecie().getDescripcion()+ p.getVariedad().getDescripcion() + p.getPrecioMin() + p.getPrecioMax());
                this.getLstSIPreciosHortalizaActive().add(si);
           
        }//fin for
        System.out.println("Termino cargar PreciosHortaliza: " + this.getLstSIPreciosHortalizaActive());
    }//fin 
}
