/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.DepartamentoRNLocal;
import entidad.Departamento;
import entidad.Provincia;
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
 * @author Juan Pablo
 */
@ManagedBean
@SessionScoped
public class ListaDepartamentoBean {

    @ManagedProperty(value = "#{usuarioLogerBean}")
    private UsuarioLogerBean usuarioLogerBean;
    private List<Departamento> lstDepartamento;
    private List<Departamento> lstDepartamentoBorrado;
    private List<SelectItem> lstSIDepartamento;
    private List<SelectItem> lstSIDomicilioDepartamento;
    private int iActionBtnSelect;
    /*private Provincia provincia;
     private Provincia provinciaDomicilio;*/
    @EJB
    private DepartamentoRNLocal departamentoRNLocal;

    public ListaDepartamentoBean() {
        lstDepartamento = new ArrayList<Departamento>();
    }

    public List<Departamento> getLstDepartamento() {
        return lstDepartamento;
    }

    public void setLstDepartamento(List<Departamento> lstDepartamento) {
        this.lstDepartamento = lstDepartamento;
    }

    public List<Departamento> getLstDepartamentoBorrado() {
        return lstDepartamentoBorrado;
    }

    public void setLstDepartamentoBorrado(List<Departamento> lstDepartamentoBorrado) {
        this.lstDepartamentoBorrado = lstDepartamentoBorrado;
    }

    public void setiActionBtnSelect(int iActionBtnSelect) {
        this.iActionBtnSelect = iActionBtnSelect;
    }

    public int getiActionBtnSelect() {
        return iActionBtnSelect;
    }

    public List<SelectItem> getLstSIDepartamento() {
        return lstSIDepartamento;
    }

    public void setLstSIDepartamento(List<SelectItem> lstSIDepartamento) {
        this.lstSIDepartamento = lstSIDepartamento;
    }

    public List<SelectItem> getLstSIDomicilioDepartamento() {
        return lstSIDomicilioDepartamento;
    }

    public void setLstSIDomicilioDepartamento(List<SelectItem> lstSIDomicilioDepartamento) {
        this.lstSIDomicilioDepartamento = lstSIDomicilioDepartamento;
    }

    public UsuarioLogerBean getUsuarioLogerBean() {
        return usuarioLogerBean;
    }

    public void setUsuarioLogerBean(UsuarioLogerBean usuarioLogerBean) {
        this.usuarioLogerBean = usuarioLogerBean;
    }

    public void cargarDepartamentos() {
        try {
            this.setLstDepartamento(departamentoRNLocal.findAll());
            this.setLstDepartamentoBorrado(departamentoRNLocal.findByBorrado(Boolean.FALSE));
        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al cargar los departamentos: " + ex,
                    null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }// fin cargarDepartamentos

    public void cargarSIDepartamentos() {
        this.setLstSIDepartamento(new ArrayList<SelectItem>());
        for (Departamento d : this.getLstDepartamento()) {
            if (!d.getBorrado()) {
                SelectItem si = new SelectItem(d, d.getDescripcion());
                this.getLstSIDepartamento().add(si);
            }
        }//fin for

    }//fin cargarSIDepartamentos

    public void cargarSIDomicilioDepartamentos() {
        this.setLstSIDomicilioDepartamento(new ArrayList<SelectItem>());
        for (Departamento d : this.getLstDepartamento()) {
            if (!d.getBorrado()) {
                SelectItem si = new SelectItem(d, d.getDescripcion());
                this.getLstSIDomicilioDepartamento().add(si);
            }
        }//fin for

    }//fin cargarSIDepartamentos

    public void recuperarDepartamentos(ValueChangeEvent event) {

        try {

            System.out.println("Provincia: " + event.getNewValue());
            if (event.getNewValue() != null) {
                if (!"0".equals(event.getNewValue().toString())) {
                    Provincia provincia = (Provincia) event.getNewValue();
                    this.setLstDepartamento(this.departamentoRNLocal.findByProvincia(provincia.getId()));
                    this.setLstDepartamentoBorrado(this.departamentoRNLocal.findByProvinciaBorrado(provincia.getId(), Boolean.FALSE));
                    this.cargarSIDepartamentos();
                } else {
                    this.setLstDepartamento(new ArrayList<Departamento>());
                    this.setLstDepartamentoBorrado(new ArrayList<Departamento>());
                    this.setLstSIDepartamento(new ArrayList<SelectItem>());
                }
            }//fin if

        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al cargar los departamentos: " + ex,
                    null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }

    }//fin recuperarDepartamentos

    public void recuperarDomicilioDepartamentos(ValueChangeEvent event) {
        try {
            if (event.getNewValue() != null) {
                if (!"0".equals(event.getNewValue().toString())) {
                    Provincia provincia = (Provincia) event.getNewValue();
                    this.setLstDepartamento(this.departamentoRNLocal.findByProvincia(provincia.getId()));
                    this.setLstDepartamentoBorrado(this.departamentoRNLocal.findByProvinciaBorrado(provincia.getId(), Boolean.FALSE));
                    this.cargarSIDomicilioDepartamentos();
                } else {
                    this.setLstDepartamento(new ArrayList<Departamento>());
                    this.setLstDepartamentoBorrado(new ArrayList<Departamento>());
                    this.setLstSIDomicilioDepartamento(new ArrayList<SelectItem>());
                }
            }

        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al cargar los departamentos: " + ex,
                    null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }//fin recuperarDepartamentos
}// FIN CLASE
