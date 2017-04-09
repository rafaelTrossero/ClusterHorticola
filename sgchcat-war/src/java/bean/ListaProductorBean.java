/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;


import RN.ProductorRNLocal;
import entidad.Productor;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
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
     private List<Productor> lstProductorActivo;
    
    private List<SelectItem> lstSIProductorActivo;
    
    
     private int iActionBtnSelect;
@EJB
    private ProductorRNLocal productorRNLocal;
    
    public ListaProductorBean() {
         lstProductor = new ArrayList<>();
    }

    public List<Productor> getLstProductor() {
        return lstProductor;
    }

    public ProductorRNLocal getProductorRNLocal() {
        return productorRNLocal;
    }

    public void setProductorRNLocal(ProductorRNLocal productorRNLocal) {
        this.productorRNLocal = productorRNLocal;
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

    public List<Productor> getLstProductorActivo() {
        return lstProductorActivo;
    }

    public List<SelectItem> getLstSIProductorActivo() {
        return lstSIProductorActivo;
    }

    public void setLstSIProductorActivo(List<SelectItem> lstSIProductorActivo) {
        this.lstSIProductorActivo = lstSIProductorActivo;
    }
    

    public void setLstProductorActivo(List<Productor> lstProductorActivo) {
        this.lstProductorActivo = lstProductorActivo;
    }
    
    public void cargar_productor() {
        try {
            this.setLstProductor(this.productorRNLocal.findAll());
        } catch (Exception ex) {
            System.out.println("Error al cargar Productores " + ex.toString());
        }
        this.cargar_SI_productor();
    }
    public void cargar_productorActivo() {
        try {
            this.setLstProductorActivo(this.productorRNLocal.findAllActivo());
        } catch (Exception ex) {
            System.out.println("Error al cargar productores activos " + ex.toString());
        }
        this.cargar_SI_productorActivo();
    }

    public void cargar_SI_productor() {
        this.setLstSIProductor(new ArrayList<SelectItem>());

        for (Productor a : this.getLstProductor()) {
            SelectItem si = new SelectItem(a, a.getNombre());
            this.getLstSIProductor().add(si);
        }
        
    }
    public void cargar_SI_productorActivo() {
        this.setLstSIProductorActivo(new ArrayList<SelectItem>());

        for (Productor a : this.getLstProductorActivo()) {
            SelectItem si = new SelectItem(a, a.getNombre());
            this.getLstSIProductorActivo().add(si);
        }
    }
        
}
