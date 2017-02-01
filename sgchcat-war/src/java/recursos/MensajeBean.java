/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author AFerSor
 */
@ManagedBean
@SessionScoped
public class MensajeBean {

    private String mensaje;
    
    private String pantalla;
    private String actualizar;
    
    public MensajeBean() {
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getPantalla() {
        return pantalla;
    }

    public void setPantalla(String pantalla) {
        this.pantalla = pantalla;
    }

    public String getActualizar() {
        
        System.out.println("Entro al getActualizar: " + actualizar);
        return actualizar;
    }

    public void setActualizar(String actualizar) {
        this.actualizar = actualizar;
    }
    
        
    public void cerrarPantalla(){
        RequestContext context = RequestContext.getCurrentInstance();  
        
        /*if(actualizar != null && !actualizar.isEmpty()){
            System.out.println("entro actualizar");
            
            context.update(this.getActualizar());
        }*/
        
        context.execute("dlgMensaje.hide()");
        
        if(pantalla != null && !pantalla.isEmpty()){
            context.execute(this.getPantalla());
        }
        
        this.setPantalla(null);
        this.setActualizar(null);
        
        
    }//fin cerrarPantalla
    
}
