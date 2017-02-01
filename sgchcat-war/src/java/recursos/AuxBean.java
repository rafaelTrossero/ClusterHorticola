/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import entidad.Barrio;
import entidad.Localidad;
import entidad.Provincia;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author AFerSor
 */
@ManagedBean
@SessionScoped
public class AuxBean {
    
    private List<Barrio> lstBarrioSelect;
    private List<Localidad> lstLocalidadSelect;
    private List<Provincia> lstProvinciaSelect;
    
    public List<Localidad> getLstLocalidadSelect() {
        return lstLocalidadSelect;
    }
    
    public void setLstLocalidadSelect(List<Localidad> lstLocalidadSelect) {
        this.lstLocalidadSelect = lstLocalidadSelect;
    }
    
    public List<Barrio> getLstBarrioSelect() {
        return lstBarrioSelect;
    }
    
    public void setLstBarrioSelect(List<Barrio> lstBarrioSelect) {
        this.lstBarrioSelect = lstBarrioSelect;
    }
    
    public List<Provincia> getLstProvinciaSelect() {
        return lstProvinciaSelect;
    }
    
    public void setLstProvinciaSelect(List<Provincia> lstProvinciaSelect) {
        this.lstProvinciaSelect = lstProvinciaSelect;
    }
    
    public AuxBean() {
        
    }
}//fin AuxBean

