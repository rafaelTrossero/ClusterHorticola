/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author RafaTrossero
 */

@ManagedBean(name="controlSesionBean")
@RequestScoped
public class ControlSesionBean implements Serializable {

     private int iTimeSession;
    
    public ControlSesionBean() {
    }

    public int getiTimeSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        System.out.println("duracion de la session: " + session.getMaxInactiveInterval());
        return session.getMaxInactiveInterval();
    }

    public void setiTimeSession(int iTimeSession) {
        this.iTimeSession = iTimeSession;
    }
        
    public String cerrarSession(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(session != null){
            session.invalidate();
            return "login.xhtml?faces-redirect=true";
        }//fin if
        
        return "";
    }//fin cerrarSession
    
    public String createSession(){
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        /*session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(session != null){
            session.invalidate();
        }//fin */
        
         return "login.xhtml?faces-redirect=true";
         
    }//fin abrirSession
    
    
}
