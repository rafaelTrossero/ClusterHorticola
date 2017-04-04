/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.CultivosCampoRNLocal;
import entidad.Campo;
import entidad.CultivosCampo;
import java.util.Iterator;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author RafaTrossero
 */
@ManagedBean
@RequestScoped
public class CultivosCampoBean {

    /**
     * Creates a new instance of CultivosCampoBean
     */
    @ManagedProperty("#{listaCultivosCampoBean}")
    private ListaCultivosCampoBean listaCultivosCampoBean;
    private Campo campo;
    private CultivosCampo cultivosCampo;
    private CultivosCampo cultivosCampoIT;
     @EJB
    private CultivosCampoRNLocal cultivosCampoRNLocal;
    public CultivosCampoBean() {
        this.campo = new Campo();
              this.cultivosCampo = new CultivosCampo();
        this.cultivosCampoIT = new CultivosCampo();
    }

    public Campo getCampo() {
        return campo;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    public CultivosCampo getCultivosCampo() {
        return cultivosCampo;
    }

    public void setCultivosCampo(CultivosCampo cultivosCampo) {
        this.cultivosCampo = cultivosCampo;
    }

    public CultivosCampo getCultivosCampoIT() {
        return cultivosCampoIT;
    }

    public void setCultivosCampoIT(CultivosCampo cultivosCampoIT) {
        this.cultivosCampoIT = cultivosCampoIT;
    }

    public ListaCultivosCampoBean getListaCultivosCampoBean() {
        return listaCultivosCampoBean;
    }

    public void setListaCultivosCampoBean(ListaCultivosCampoBean listaCultivosCampoBean) {
        this.listaCultivosCampoBean = listaCultivosCampoBean;
    }

    public CultivosCampoRNLocal getCultivosCampoRNLocal() {
        return cultivosCampoRNLocal;
    }

    public void setCultivosCampoRNLocal(CultivosCampoRNLocal cultivosCampoRNLocal) {
        this.cultivosCampoRNLocal = cultivosCampoRNLocal;
    }
    
    public void create() {

        System.out.println("Entro al create");
        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        try {
    
            System.out.println("ENtro a setear Cultivos");
        
        cultivosCampo.setCampo(campo);
     

        /*
         Se recorre la lista que tiene los cultivos insertados y se hace un create en la tabla
         por cada cultivo de la lista
         */
        Iterator<CultivosCampo> it = listaCultivosCampoBean.getListaCultivosCampo().iterator();

        while (it.hasNext()) {
            cultivosCampoIT = it.next();

            this.cultivosCampoIT.setCampo(campo);
            this.setCultivosCampo(cultivosCampoIT);
            try {
                this.cultivosCampoRNLocal.create(cultivosCampoIT);

            } catch (Exception ex) {
               
            }
        }
         

            sMensaje = "El dato fue guardado";
            severity = FacesMessage.SEVERITY_INFO;

            limpiar();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgCultivosCampo').hide();");
        } catch (Exception ex) {

            severity = FacesMessage.SEVERITY_ERROR;
            sMensaje = "Error al crear: " + ex.getMessage();

        } finally {
            fm = new FacesMessage(severity, sMensaje, null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }

    }//fin
     public void limpiar(){
         this.campo = new Campo();
              this.cultivosCampo = new CultivosCampo();
        this.cultivosCampoIT = new CultivosCampo();
        this.listaCultivosCampoBean.limpiar();
     }
    
    
}
