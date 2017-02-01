/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author AFerSor
 */
@FacesConverter(value="ConversorBoolean")
public class ConversorBoolean implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return null;
    }//fin getAsObject

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Boolean bValue = (Boolean) value;
        
        if(bValue){
            return "SI";
        }else{
            return "NO";
        }
    }//fin getAsString
    
}//fin conversor
