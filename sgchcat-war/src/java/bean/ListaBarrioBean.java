/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.barrioRNLocal;
import entidad.Barrio;
import entidad.Localidad;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author AFerSor
 */
@ManagedBean
@SessionScoped
public class ListaBarrioBean {

    private List<Barrio> lstBarrio;
    private List<Barrio> lstBarrioBorrado;
    private List<SelectItem> lstSIBarrio;
    private List<SelectItem> lstSIDomicilioBarrio;
    private int iActionBtnSelect;
    private Localidad localidad;
    @EJB
    private barrioRNLocal barrioRNLocal;

    public ListaBarrioBean() {
        lstBarrio = new ArrayList<Barrio>();


    }

    @PostConstruct
    public void init() {
        //cargarBarrios();
        //this.cargarSIBarrios();       
    }//fin init

    public List<Barrio> getLstBarrio() {
        return lstBarrio;
    }

    public void setLstBarrio(List<Barrio> lstBarrio) {
        this.lstBarrio = lstBarrio;
    }

    public List<Barrio> getLstBarrioBorrado() {
        return lstBarrioBorrado;
    }

    public void setLstBarrioBorrado(List<Barrio> lstBarrioBorrado) {
        this.lstBarrioBorrado = lstBarrioBorrado;
    }

    public int getiActionBtnSelect() {
        return iActionBtnSelect;
    }

    public void setiActionBtnSelect(int iActionBtnSelect) {
        this.iActionBtnSelect = iActionBtnSelect;
    }

    public List<SelectItem> getLstSIBarrio() {
        return lstSIBarrio;
    }

    public void setLstSIBarrio(List<SelectItem> lstSIBarrio) {
        this.lstSIBarrio = lstSIBarrio;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
        /*if(localidad != null){
         this.recuperarDomicilioBarrios();
         }*/
    }

    public List<SelectItem> getLstSIDomicilioBarrio() {
        return lstSIDomicilioBarrio;
    }

    public void setLstSIDomicilioBarrio(List<SelectItem> lstSIDomicilioBarrio) {
        this.lstSIDomicilioBarrio = lstSIDomicilioBarrio;
    }

    public void cargarBarrios() {
        try {
            this.setLstBarrio(barrioRNLocal.findAll());
            this.setLstBarrioBorrado(barrioRNLocal.findByBorrado(Boolean.FALSE));

        }
        catch (Exception ex) {

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                               "Error al cargar los barrios: " + ex,
                                               null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);

            // Logger.getLogger(ListaBarrioBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//fin cargarBarrios

    public void cargarSIBarrios() {

        this.setLstSIBarrio(new ArrayList<SelectItem>());
        for (Barrio b : this.getLstBarrio()) {
            if (!b.getBorrado()) {
                SelectItem si = new SelectItem(b, b.getDescripcion());
                this.getLstSIBarrio().add(si);
            }
        }//fin for
    }//fin cargarSILocalidades

    public void cargarSIDomicilioBarrios() {

        this.setLstSIDomicilioBarrio(new ArrayList<SelectItem>());
        for (Barrio b : this.getLstBarrio()) {
            SelectItem si = new SelectItem(b, b.getDescripcion());
            this.getLstSIDomicilioBarrio().add(si);
        }//fin for
    }//fin cargarSILocalidades

    public void recuperarBarrios(ValueChangeEvent event) {
        try {
            if (event.getNewValue() != null) {
                if (!"0".equals(event.getNewValue().toString())) {
                    localidad = (Localidad) event.getNewValue();
                    this.setLstBarrio(this.barrioRNLocal.findByLocalidad(localidad.getId()));
                    this.setLstBarrioBorrado(this.barrioRNLocal.findByLocalidadBorrado(localidad.getId(), Boolean.FALSE));
                    this.cargarSIBarrios();
                }
                else {
                    this.setLstBarrio(new ArrayList<Barrio>());
                    this.setLstBarrioBorrado(new ArrayList<Barrio>());
                    this.setLstSIBarrio(new ArrayList<SelectItem>());
                }
            }
        }
        catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                               "Error al cargar los barrios: " + ex,
                                               null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }


    }//fin cargarLocalidades

    public void recuperarDomiciliosBarrios(ValueChangeEvent event) {

        try {
            if (event.getNewValue() != null) {
                if (!"0".equals(event.getNewValue().toString())) {
                    localidad = (Localidad) event.getNewValue();
                    this.setLstBarrio(this.barrioRNLocal.findByLocalidad(localidad.getId()));
                    this.setLstBarrioBorrado(this.barrioRNLocal.findByLocalidadBorrado(localidad.getId(), Boolean.FALSE));
                    this.cargarSIDomicilioBarrios();
                }
                else {
                    this.setLstBarrio(new ArrayList<Barrio>());
                    this.setLstBarrioBorrado(new ArrayList<Barrio>());
                    this.setLstSIDomicilioBarrio(new ArrayList<SelectItem>());
                }
            }//fin if
        }
        catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                               "Error al cargar los barrios: " + ex,
                                               null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }

    }//fin cargarLocalidades
    /*public void recuperarDomicilioBarrios(){
          
     this.setLstBarrio(localidad.getBarrio());
     this.cargarSIDomicilioBarrios();

       
     }//fin cargarLocalidades
    
     public void recuperarBarrios(){
     this.setLstBarrio(localidad.getBarrio());
     this.cargarSIBarrios();
     }//fin recuperarLocalidades*/
}
