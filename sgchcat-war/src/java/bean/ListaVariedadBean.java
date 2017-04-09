/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.VariedadRNLocal;
import entidad.Especie;
import entidad.Variedad;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author RafaTrossero
 */
@ManagedBean
@SessionScoped
public class ListaVariedadBean {

    @ManagedProperty(value = "#{usuarioLogerBean}")
    private UsuarioLogerBean usuarioLogerBean;
    private List<Variedad> lstVariedad;

    private List<SelectItem> lstSIVariedad;
    private List<Variedad> lstVariedadActive;

    private List<SelectItem> lstSIVariedadActive;
    private int iActionBtnSelect;
    @EJB
    private VariedadRNLocal variedadRNLocal;

    public ListaVariedadBean() {
        lstVariedad = new ArrayList<>();
    }

    public UsuarioLogerBean getUsuarioLogerBean() {
        return usuarioLogerBean;
    }

    public void setUsuarioLogerBean(UsuarioLogerBean usuarioLogerBean) {
        this.usuarioLogerBean = usuarioLogerBean;
    }

    public List<Variedad> getLstVariedad() {
        return lstVariedad;
    }

    public void setLstVariedad(List<Variedad> lstVariedad) {
        this.lstVariedad = lstVariedad;
    }

    public List<SelectItem> getLstSIVariedad() {
        return lstSIVariedad;
    }

    public void setLstSIVariedad(List<SelectItem> lstSIVariedad) {
        this.lstSIVariedad = lstSIVariedad;
    }

    public int getiActionBtnSelect() {
        return iActionBtnSelect;
    }

    public List<Variedad> getLstVariedadActive() {
        return lstVariedadActive;
    }

    public void setLstVariedadActive(List<Variedad> lstVariedadActive) {
        this.lstVariedadActive = lstVariedadActive;
    }

    public List<SelectItem> getLstSIVariedadActive() {
        return lstSIVariedadActive;
    }

    public void setLstSIVariedadActive(List<SelectItem> lstSIVariedadActive) {
        this.lstSIVariedadActive = lstSIVariedadActive;
    }

    public void setiActionBtnSelect(int iActionBtnSelect) {
        this.iActionBtnSelect = iActionBtnSelect;
    }

    public VariedadRNLocal getVariedadRNLocal() {
        return variedadRNLocal;
    }

    public void setVariedadRNLocal(VariedadRNLocal variedadRNLocal) {
        this.variedadRNLocal = variedadRNLocal;
    }

    public void cargarVariedad() {
        try {
            this.setLstVariedad(variedadRNLocal.findAll());
        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al cargar las especies: " + ex,
                    null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }//fin 

    public void cargarVariedadActive() {
        try {
            this.setLstVariedadActive(variedadRNLocal.findAllActivo());
        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al cargar las especies: " + ex,
                    null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
        this.cargarSIVariedadActive();
    }//fin 

    public void cargarSIVariedad() {

        this.setLstSIVariedad(new ArrayList<SelectItem>());

        for (Variedad p : this.getLstVariedad()) {

            SelectItem si = new SelectItem(p, p.getDescripcion());
            this.getLstSIVariedad().add(si);

        }//fin for
        System.out.println("Termino cargar Variedad: " + this.getLstSIVariedad());
    }//fin 

    public void cargarSIVariedadActive() {

        this.setLstSIVariedadActive(new ArrayList<SelectItem>());

        for (Variedad p : this.getLstVariedadActive()) {

            SelectItem si = new SelectItem(p, p.getDescripcion());
            this.getLstSIVariedadActive().add(si);

        }//fin for
        System.out.println("Termino cargar Variedad: " + this.getLstSIVariedad());
    }//fin 

    public void recuperarVariedad(ValueChangeEvent event) {

        try {

            if (event.getNewValue() != null) {
                if (!"0".equals(event.getNewValue().toString())) {
                    Especie especie = (Especie) event.getNewValue();

                    System.out.println("especie; " + especie);
                    this.setLstVariedad(this.variedadRNLocal.findByEspecie(especie.getId()));
                    System.out.println("entroooooooooooooooooooooooooooooooooooo lst variedad tieneOO . " + lstVariedad);
                    //this.setLstProvinciaBorrado(this.provinciaRNLocal.findByPaisBorrado(pais.getId(), Boolean.FALSE));
                    this.cargarSIVariedad();

                } else {
                    this.setLstVariedad(new ArrayList<Variedad>());
                    this.setLstSIVariedad(new ArrayList<SelectItem>());

                }

            }//fin if
        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al cargar las variedades: " + ex,
                    null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }

        //this.cargarProvincias();
    }//fin recuperarProvincias

}
