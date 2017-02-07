/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.TenenciaRNLocal;
import entidad.Tenencia;
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
public class ListaTenenciaBean {

    private List<Tenencia> lstTenencia;
    private List<SelectItem> lstSITenencia;
    private int iActionBtnSelect;
    @EJB
    private TenenciaRNLocal tenenciaRNLocal;

    /**
     * Creates a new instance of ListaTenenciaBean
     */
    public ListaTenenciaBean() {
        lstTenencia = new ArrayList<Tenencia>();
    }

    public List<Tenencia> getLstTenencia() {
        return lstTenencia;
    }

    public void setLstTenencia(List<Tenencia> lstTenencia) {
        this.lstTenencia = lstTenencia;
    }

    public List<SelectItem> getLstSITenencia() {
        return lstSITenencia;
    }

    public void setLstSITenencia(List<SelectItem> lstSITenencia) {
        this.lstSITenencia = lstSITenencia;
    }

    public int getiActionBtnSelect() {
        return iActionBtnSelect;
    }

    public void setiActionBtnSelect(int iActionBtnSelect) {
        this.iActionBtnSelect = iActionBtnSelect;
    }

    public TenenciaRNLocal getTenenciaRNLocal() {
        return tenenciaRNLocal;
    }

    public void setTenenciaRNLocal(TenenciaRNLocal tenenciaRNLocal) {
        this.tenenciaRNLocal = tenenciaRNLocal;
    }

    public void cargar_tenencia() {
        try {
            this.setLstTenencia(this.tenenciaRNLocal.findAll());
        } catch (Exception ex) {
            System.out.println("Error al cargar alumnos " + ex.toString());
        }
    }

    public void cargar_SI_tenencia() {
        this.setLstSITenencia(new ArrayList<SelectItem>());

        for (Tenencia a : this.getLstTenencia()) {
            SelectItem si = new SelectItem(a, a.getDescripcion());
            this.getLstSITenencia().add(si);
        }
    }

    public void limpiar() {

        lstTenencia = new ArrayList<Tenencia>();

    }

}
