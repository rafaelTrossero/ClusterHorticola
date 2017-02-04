/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.EspecieRNLocal;
import entidad.Especie;
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
public class ListaEspecieBean {

    @ManagedProperty(value = "#{usuarioLogerBean}")
    private UsuarioLogerBean usuarioLogerBean;
    private List<Especie> lstEspecie;
    
    private List<SelectItem> lstSIEspecie;
    private int iActionBtnSelect;
    @EJB
    private EspecieRNLocal especieRNLocal;
    
    public ListaEspecieBean() {
        lstEspecie = new ArrayList<>();
    }

    public UsuarioLogerBean getUsuarioLogerBean() {
        return usuarioLogerBean;
    }

    public void setUsuarioLogerBean(UsuarioLogerBean usuarioLogerBean) {
        this.usuarioLogerBean = usuarioLogerBean;
    }

    public List<Especie> getLstEspecie() {
        return lstEspecie;
    }

    public void setLstEspecie(List<Especie> lstEspecie) {
        this.lstEspecie = lstEspecie;
    }

    public List<SelectItem> getLstSIEspecie() {
        return lstSIEspecie;
    }

    public void setLstSIEspecie(List<SelectItem> lstSIEspecie) {
        this.lstSIEspecie = lstSIEspecie;
    }

    public int getiActionBtnSelect() {
        return iActionBtnSelect;
    }

    public void setiActionBtnSelect(int iActionBtnSelect) {
        this.iActionBtnSelect = iActionBtnSelect;
    }

    public EspecieRNLocal getEspecieRNLocal() {
        return especieRNLocal;
    }

    public void setEspecieRNLocal(EspecieRNLocal especieRNLocal) {
        this.especieRNLocal = especieRNLocal;
    }
    
     public void cargarPais() {
        try {
            this.setLstEspecie(especieRNLocal.findAll());
        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al cargar las especies: " + ex,
                    null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }//fin 
    
     public void cargarSIPais() {

        this.setLstSIEspecie(new ArrayList<SelectItem>());

        for (Especie p : this.getLstEspecie()) {
            
                SelectItem si = new SelectItem(p, p.getEspecie());
                this.getLstSIEspecie().add(si);
           
        }//fin for
        System.out.println("Termino cargar Pais: " + this.getLstSIEspecie());
    }//fin 
}
