/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import RN.CampoRNLocal;
import entidad.Campo;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author cris
 */
@ManagedBean
@SessionScoped
public class ListaCampoBean {
private List<Campo> lstCampo;
    
    private List<SelectItem> lstSICampo;
     private int iActionBtnSelect;
     
     @EJB
    private CampoRNLocal campoRNLocal;


    /**
     * Creates a new instance of ListaCampoBean
     */
    public ListaCampoBean() {
         lstCampo = new ArrayList<>();
    }

    public List<Campo> getLstCampo() {
        return lstCampo;
    }

    public void setLstCampo(List<Campo> lstCampo) {
        this.lstCampo = lstCampo;
    }

    public CampoRNLocal getCampoRNLocal() {
        return campoRNLocal;
    }

    public void setCampoRNLocal(CampoRNLocal campoRNLocal) {
        this.campoRNLocal = campoRNLocal;
    }
    

    public List<SelectItem> getLstSICampo() {
        return lstSICampo;
    }

    public void setLstSICampo(List<SelectItem> lstSICampo) {
        this.lstSICampo = lstSICampo;
    }

    public int getiActionBtnSelect() {
        return iActionBtnSelect;
    }

    public void setiActionBtnSelect(int iActionBtnSelect) {
        this.iActionBtnSelect = iActionBtnSelect;
    }
    
     public void cargar_campo() {
        try {
            this.setLstCampo(this.campoRNLocal.findAll());
        } catch (Exception ex) {
            System.out.println("Error al cargar campo " + ex.toString());
        }
    }

    public void cargar_SI_campo() {
        this.setLstSICampo(new ArrayList<SelectItem>());

        for (Campo a : this.getLstCampo()) {
            SelectItem si = new SelectItem(a, a.getNombre());
            this.getLstSICampo().add(si);
        }
    }
    
    
}
