/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import entidad.tipoUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author cris
 */
@ManagedBean
@SessionScoped
public class ListIdentificationTypeBean1 {

    private List<SelectItem> lstSIIdentificationType;
    /**
     * Creates a new instance of ListIdentificationTypeBean1
     */
    public ListIdentificationTypeBean1() {
    }

    public List<SelectItem> getLstSIIdentificationType() {
        return lstSIIdentificationType;
    }

    public void setLstSIIdentificationType(List<SelectItem> lstSIIdentificationType) {
        this.lstSIIdentificationType = lstSIIdentificationType;
    }
    
    public void cargarIdentificationTypes(){
  lstSIIdentificationType= new ArrayList<> ();
    for( tipoUsuario it: tipoUsuario.values()){
        System.out.println("los tipos de usuarios son:" + it.getName());
        lstSIIdentificationType.add(new SelectItem(it, it.getName()));
    }
}
    
    
}
