/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;


import entidad.Productor;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author RafaTrossero
 */
@ManagedBean
@SessionScoped
public class ListaProductorBean {

     private List<Productor> lstProductor;
    
    private List<SelectItem> lstSIProductor;
     private int iActionBtnSelect;

    
    public ListaProductorBean() {
         lstProductor = new ArrayList<>();
    }

    public List<Productor> getLstProductor() {
        return lstProductor;
    }

    public void setLstProductor(List<Productor> lstProductor) {
        this.lstProductor = lstProductor;
    }

    public List<SelectItem> getLstSIProductor() {
        return lstSIProductor;
    }

    public void setLstSIProductor(List<SelectItem> lstSIProductor) {
        this.lstSIProductor = lstSIProductor;
    }
     public int getiActionBtnSelect() {
        return iActionBtnSelect;
    }

    public void setiActionBtnSelect(int iActionBtnSelect) {
        this.iActionBtnSelect = iActionBtnSelect;
    }
}
