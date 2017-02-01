/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.ProvinciaRNLocal;
import entidad.Pais;
import entidad.Provincia;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
 * @author Juan Pablo
 */
@ManagedBean
@SessionScoped
public class ListaProvinciaBean {

    @ManagedProperty(value = "#{usuarioLogerBean}")
    private UsuarioLogerBean usuarioLogerBean;
    private List<Provincia> lstProvincia;
    private List<Provincia> lstProvinciaBorrado;
    private List<SelectItem> lstSIProvincia;
    private List<SelectItem> lstSIDomicilioProvincia;
    private int iActionBtnSelect;
    /* private Pais pais;
     private Pais paisDomicilio;*/
    @EJB
    private ProvinciaRNLocal provinciaRNLocal;

    public ListaProvinciaBean() {
        lstProvincia = new ArrayList<Provincia>();

    }

    @PostConstruct
    public void init() {
        cargarProvincias();
        cargarSIProvincias();
        /* if(pais != null){
         System.out.println("Entro al recuperarProvincias");
         this.recuperarProvincias();
         }*/
    }//fin init

    public List<Provincia> getLstProvincia() {
        return lstProvincia;
    }

    public void setLstProvincia(List<Provincia> lstProvincia) {
        this.lstProvincia = lstProvincia;
    }

    public List<Provincia> getLstProvinciaBorrado() {
        return lstProvinciaBorrado;
    }

    public void setLstProvinciaBorrado(List<Provincia> lstProvinciaBorrado) {
        this.lstProvinciaBorrado = lstProvinciaBorrado;
    }

    public int getiActionBtnSelect() {
        return iActionBtnSelect;
    }

    public void setiActionBtnSelect(int iActionBtnSelect) {
        this.iActionBtnSelect = iActionBtnSelect;
    }

    public List<SelectItem> getLstSIProvincia() {
        return lstSIProvincia;
    }

    public void setLstSIProvincia(List<SelectItem> lstSIProvincia) {

        this.lstSIProvincia = lstSIProvincia;
    }

    public List<SelectItem> getLstSIDomicilioProvincia() {
        return lstSIDomicilioProvincia;
    }

    public void setLstSIDomicilioProvincia(List<SelectItem> lstSIDomicilioProvincia) {
        this.lstSIDomicilioProvincia = lstSIDomicilioProvincia;
    }

    public UsuarioLogerBean getUsuarioLogerBean() {
        return usuarioLogerBean;
    }

    public void setUsuarioLogerBean(UsuarioLogerBean usuarioLogerBean) {
        this.usuarioLogerBean = usuarioLogerBean;
    }

    public void cargarProvincias() {
        try {
            System.out.println("Entro al cargarProvincia");
            this.setLstProvincia(provinciaRNLocal.findAll());
            this.setLstProvinciaBorrado(provinciaRNLocal.findByBorrado(Boolean.FALSE));
            System.out.println("listado provincia: " + this.getLstProvincia());
        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al cargar las provincias: " + ex,
                    null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }//fin cargarObrasSociales

    public void cargarSIProvincias() {

        this.setLstSIProvincia(new ArrayList<SelectItem>());
        for (Provincia p : this.getLstProvincia()) {
            if (!p.getBorrado()) {
                SelectItem si = new SelectItem(p, p.getDescripcion());
                this.getLstSIProvincia().add(si);
            }
        }//fin for

    }//fin cargarSIProvincias

    public void cargarSIDomicilioProvincias() {

        this.setLstSIDomicilioProvincia(new ArrayList<SelectItem>());
        for (Provincia p : this.getLstProvincia()) {
            if (!p.getBorrado()) {
                SelectItem si = new SelectItem(p, p.getDescripcion());
                this.getLstSIDomicilioProvincia().add(si);
            }
        }//fin for

    }//fin cargarSIProvincias

    public void recuperarProvincias(ValueChangeEvent event) {

        try {

            if (event.getNewValue() != null) {
                if (!"0".equals(event.getNewValue().toString())) {
                    Pais pais = (Pais) event.getNewValue();

                    System.out.println("pais; " + pais);
                    this.setLstProvincia(this.provinciaRNLocal.findByPais(pais.getId()));
                    this.setLstProvinciaBorrado(this.provinciaRNLocal.findByPaisBorrado(pais.getId(), Boolean.FALSE));
                    this.cargarSIProvincias();
                } else {
                    this.setLstProvincia(new ArrayList<Provincia>());
                    this.setLstProvinciaBorrado(new ArrayList<Provincia>());
                    this.setLstSIProvincia(new ArrayList<SelectItem>());
                }

            }//fin if
        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al cargar las provincias: " + ex,
                    null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }

        //this.cargarProvincias();
    }//fin recuperarProvincias

    public void recuperarDomicilioProvincias(ValueChangeEvent event) {

        try {
            if (event.getNewValue() != null) {
                if (!"0".equals(event.getNewValue().toString())) {
                    Pais pais = (Pais) event.getNewValue();
                    this.setLstProvincia(this.provinciaRNLocal.findByPais(pais.getId()));
                    this.setLstProvinciaBorrado(this.provinciaRNLocal.findByPaisBorrado(pais.getId(), Boolean.FALSE));
                    this.cargarSIDomicilioProvincias();
                } else {
                    this.setLstProvincia(new ArrayList<Provincia>());
                    this.setLstProvinciaBorrado(new ArrayList<Provincia>());
                    this.setLstSIDomicilioProvincia(new ArrayList<SelectItem>());
                }
            }
        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al cargar las provincias: " + ex,
                    null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }


    }//fin recuperarProvincias
}//FIN CLASE
