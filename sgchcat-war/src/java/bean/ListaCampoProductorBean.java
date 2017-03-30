/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import RN.CampoProductorRNLocal;
import entidad.CampoProductor;
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
public class ListaCampoProductorBean {
private List<CampoProductor> lstCampoProductor;
    
    private List<SelectItem> lstSICampo;
     private int iActionBtnSelect;
     
     @EJB
    private CampoProductorRNLocal campoProductorRNLocal;
    /**
     * Creates a new instance of ListaCampoProductorBean
     */
    public ListaCampoProductorBean() {
        lstCampoProductor = new ArrayList<>();
    }

    public List<CampoProductor> getLstCampoProductor() {
        return lstCampoProductor;
    }

    public void setLstCampoProductor(List<CampoProductor> lstCampoProductor) {
        this.lstCampoProductor = lstCampoProductor;
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

    public CampoProductorRNLocal getCampoProductorRNLocal() {
        return campoProductorRNLocal;
    }

    public void setCampoProductorRNLocal(CampoProductorRNLocal campoProductorRNLocal) {
        this.campoProductorRNLocal = campoProductorRNLocal;
    }

   
    
    
     public void cargar_campo_productor() {
        try {
            this.setLstCampoProductor(this.campoProductorRNLocal.findAll());
        } catch (Exception ex) {
            System.out.println("Error al cargar campo productor " + ex.toString());
        }
    }
}
