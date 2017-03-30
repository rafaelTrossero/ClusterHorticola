/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import entidad.CampoProductor;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author cris
 */
@ManagedBean
@SessionScoped
public class ListaCampoProductoAuxrBean {
 private List <CampoProductor> lstcampoProductor;
    /**
     * Creates a new instance of ListaCampoProductoAuxrBean
     */
    public ListaCampoProductoAuxrBean() {
    }

    public List<CampoProductor> getLstcampoProductor() {
        return lstcampoProductor;
    }

    public void setLstcampoProductor(List<CampoProductor> lstcampoProductor) {
        this.lstcampoProductor = lstcampoProductor;
    }
    
    
}
