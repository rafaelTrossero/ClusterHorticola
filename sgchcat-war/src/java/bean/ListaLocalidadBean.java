/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.LocalidadRNLocal;
import entidad.Departamento;
import entidad.Localidad;
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
import org.primefaces.context.RequestContext;

/**
 *
 * @author bcasas
 */
@ManagedBean
@SessionScoped
public class ListaLocalidadBean {

    @ManagedProperty(value = "#{usuarioLogerBean}")
    private UsuarioLogerBean usuarioLogerBean;
    private List<Localidad> lstLocalidad;
    private List<Localidad> lstLocalidades;
    private List<Localidad> lstLocalidadBorrado;
    private int iActionBtnSelect;
    private Departamento departamento;
    private Departamento departamentoDomicilio;
    private List<SelectItem> lstSILocalidad;
    private List<SelectItem> lstSIDomicilioLocalidad;
    private Long departamentoSelect;
    @EJB
    private LocalidadRNLocal localidadRNLocal;

    public ListaLocalidadBean() {
        lstLocalidad = new ArrayList<Localidad>();
    }

    public List<Localidad> getLstLocalidad() {
        return lstLocalidad;
    }

    public void setLstLocalidad(List<Localidad> lstLocalidad) {
        this.lstLocalidad = lstLocalidad;
    }

    public List<Localidad> getLstLocalidadBorrado() {
        return lstLocalidadBorrado;
    }

    public void setLstLocalidadBorrado(List<Localidad> lstLocalidadBorrado) {
        this.lstLocalidadBorrado = lstLocalidadBorrado;
    }

    public int getiActionBtnSelect() {
        return iActionBtnSelect;
    }

    public void setiActionBtnSelect(int iActionBtnSelect) {
        this.iActionBtnSelect = iActionBtnSelect;
    }

    public LocalidadRNLocal getLocalidadRNLocal() {
        return localidadRNLocal;
    }

    public void setLocalidadRNLocal(LocalidadRNLocal localidadRNLocal) {
        this.localidadRNLocal = localidadRNLocal;
    }

    public List<Localidad> getLstLocalidades() {

        return lstLocalidades;
    }

    public void setLstLocalidades(List<Localidad> lstLocalidades) {
        this.lstLocalidades = lstLocalidades;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {

        this.departamento = departamento;
        if (departamento != null) {
            this.recuperarLocaliadesCombo();
        }
    }

    public Departamento getDepartamentoDomicilio() {
        return departamentoDomicilio;
    }

    public void setDepartamentoDomicilio(Departamento departamentoDomicilio) {
        this.departamentoDomicilio = departamentoDomicilio;
        if (departamentoDomicilio != null) {
            this.recuperarDomicilioLocalidades();
        }
    }

    public Long getDepartamentoSelect() {
        return departamentoSelect;
    }

    public void setDepartamentoSelect(Long departamentoSelect) {
        this.departamentoSelect = departamentoSelect;
    }

    public List<SelectItem> getLstSILocalidad() {
        return lstSILocalidad;
    }

    public void setLstSILocalidad(List<SelectItem> lstSILocalidad) {
        this.lstSILocalidad = lstSILocalidad;
    }

    public List<SelectItem> getLstSIDomicilioLocalidad() {
        return lstSIDomicilioLocalidad;
    }

    public void setLstSIDomicilioLocalidad(List<SelectItem> lstSIDomicilioLocalidad) {
        this.lstSIDomicilioLocalidad = lstSIDomicilioLocalidad;
    }

    public UsuarioLogerBean getUsuarioLogerBean() {
        return usuarioLogerBean;
    }

    public void setUsuarioLogerBean(UsuarioLogerBean usuarioLogerBean) {
        this.usuarioLogerBean = usuarioLogerBean;
    }

    public void cargarLocalidades() {
        try {

            this.setLstLocalidad(localidadRNLocal.findAll());
            this.setLstLocalidadBorrado(localidadRNLocal.findByBorrado(Boolean.FALSE));
        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al cargar las localidades: " + ex,
                    null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }// fin cargarLocaliades

    public void cargarSILocalidades() {

        this.setLstSILocalidad(new ArrayList<SelectItem>());
        for (Localidad l : this.getLstLocalidad()) {
            if (!l.getBorrado()) {
                SelectItem si = new SelectItem(l, l.getDescripcion());
                this.getLstSILocalidad().add(si);
            }
        }//fin for
    }//fin cargarSILocalidades

    public void cargarSIDomicilioLocalidades() {

        this.setLstSIDomicilioLocalidad(new ArrayList<SelectItem>());
        for (Localidad l : this.getLstLocalidad()) {
            if (!l.getBorrado()) {
                SelectItem si = new SelectItem(l, l.getDescripcion());
                this.getLstSIDomicilioLocalidad().add(si);
            }
        }//fin for
    }//fin cargarSILocalidades

    public void recuperarLocalidades(ValueChangeEvent event) {
        try {

            if (event.getNewValue() != null) {
                if (!"0".equals(event.getNewValue().toString())) {
                    Departamento dpto = (Departamento) event.getNewValue();

                    this.setLstLocalidad(this.localidadRNLocal.findByDepartamento(dpto.getId()));
                    this.setLstLocalidadBorrado(this.localidadRNLocal.findByDepartamentoBorrado(dpto.getId(), Boolean.FALSE));
                    this.cargarSILocalidades();
                } else {
                    this.setLstLocalidad(new ArrayList<Localidad>());
                    this.setLstLocalidadBorrado(new ArrayList<Localidad>());
                    this.setLstSILocalidad(new ArrayList<SelectItem>());
                }

            }//fin if
        } //fin cargarLocalidades
        catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al cargar las localidades: " + ex,
                    null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }

    }//fin cargarLocalidades

    public void recuperarDomicilioLocalidades(ValueChangeEvent event) {

        try {
            if (event.getNewValue() != null) {
                if (!"0".equals(event.getNewValue().toString())) {
                    Departamento dpto = (Departamento) event.getNewValue();
                    this.setLstLocalidad(this.localidadRNLocal.findByDepartamento(dpto.getId()));
                    this.setLstLocalidadBorrado(this.localidadRNLocal.findByDepartamentoBorrado(dpto.getId(), Boolean.FALSE));
                    this.cargarSIDomicilioLocalidades();
                } else {
                    this.setLstLocalidad(new ArrayList<Localidad>());
                    this.setLstLocalidadBorrado(new ArrayList<Localidad>());
                    this.setLstSIDomicilioLocalidad(new ArrayList<SelectItem>());
                }
            }
        } //fin cargarLocalidades
        catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al cargar las localidades: " + ex,
                    null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }


    }//fin cargarLocalidades

    public void recuperarLocalidades() {
        this.lstLocalidades = new ArrayList<Localidad>();

        if (!departamento.getListaLocalidad().isEmpty()) {
            departamento.getListaLocalidad().get(0);
        }


        lstLocalidades = departamento.getListaLocalidad();


        RequestContext.getCurrentInstance().update("somLocalidad");

        System.out.println(departamento.getListaLocalidad());
    }

    public void recuperarLocaliadesCombo() {
        this.setLstLocalidad(departamento.getListaLocalidad());
        this.cargarSILocalidades();
    }//fin recuperarLocaliadesCombo

    public void recuperarDomicilioLocalidades() {

        this.setLstLocalidad(departamentoDomicilio.getListaLocalidad());
        this.cargarSIDomicilioLocalidades();

    }//fin recuperar domicilio-localidades

    public void cargarLocalidadesSinAsociarAPS() {
        // modif consulta findALL
        try {
            System.out.println("Entro a cargar sin asociar");
            this.setLstLocalidad(localidadRNLocal.findLocalidadesSinAsociarAps());
            System.out.println("localidades sin asociar:" + this.getLstLocalidad().size());
        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al cargar las localidades: " + ex,
                    null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }//fin cargarLocalidades
}//FIN CLASE LISTALOCALIDADBEAN

