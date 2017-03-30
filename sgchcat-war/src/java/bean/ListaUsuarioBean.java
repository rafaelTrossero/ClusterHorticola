/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import RN.UsuarioRNLocal;
import entidad.Usuario;
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
public class ListaUsuarioBean {
private List<Usuario> lstUsuario   ;
    
    private List<SelectItem> lstSIUsuario;
     private int iActionBtnSelect;
@EJB
    private UsuarioRNLocal usuarioRNLocal;
    /**
     * Creates a new instance of ListaUsuarioBean
     */
    public ListaUsuarioBean() {
        lstUsuario = new ArrayList<>();
        
    }

    public List<Usuario> getLstUsuario() {
        return lstUsuario;
    }

    public void setLstUsuario(List<Usuario> lstUsuario) {
        this.lstUsuario = lstUsuario;
    }

    public List<SelectItem> getLstSIUsuario() {
        return lstSIUsuario;
    }

    public void setLstSIUsuario(List<SelectItem> lstSIUsuario) {
        this.lstSIUsuario = lstSIUsuario;
    }

    public int getiActionBtnSelect() {
        return iActionBtnSelect;
    }

    public void setiActionBtnSelect(int iActionBtnSelect) {
        this.iActionBtnSelect = iActionBtnSelect;
    }

    public UsuarioRNLocal getUsuarioRNLocal() {
        return usuarioRNLocal;
    }

    public void setUsuarioRNLocal(UsuarioRNLocal usuarioRNLocal) {
        this.usuarioRNLocal = usuarioRNLocal;
    }
    
     public void cargar_usuario() {
        try {
            this.setLstUsuario(this.usuarioRNLocal.findAll());
        } catch (Exception ex) {
            System.out.println("Error al cargar usuarios " + ex.toString());
        }
    }
    
}
