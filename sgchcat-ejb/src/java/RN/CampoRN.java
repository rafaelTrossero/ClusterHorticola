/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.CampoFacadeLocal;
import entidad.Campo;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import recursos.cadenas;

/**
 *
 * @author cris
 */
@Stateless
public class CampoRN implements CampoRNLocal {

    @EJB
    private CampoFacadeLocal campoFacadeLocal;

    @Override
    public void create(Campo cam) throws Exception {
        this.convertir_strings(cam);
        this.validar(cam, 0);
        this.campoFacadeLocal.create(cam);
    }

    @Override
    public void remove(Campo cam) throws Exception {
       this.campoFacadeLocal.remove(cam);
    }

    @Override
    public void edit(Campo cam) throws Exception {
        this.convertir_strings(cam);
        this.validar(cam, 1);
         this.campoFacadeLocal.edit(cam);
    }

    @Override
    public List<Campo> findAll() throws Exception {
return (this.campoFacadeLocal.findAll());
    }

    @Override
    public Campo findByDni(Integer dni) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void activate(Campo campo, Boolean bEstado) throws Exception {
      campoFacadeLocal.activate(campo, bEstado);
    }

    @Override
    public List<Campo> findAllActivo() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     private void convertir_strings(Campo p) {
        p.setNombre(cadenas.convertir(p.getNombre()));
    }
      private void validar(Campo p, int op) throws Exception {
        //verifica si el código es menor o igual a cero
        

        //verifica si es una línea en blanco
        if (p.getNombre().trim().length() == 0) {
            throw new Exception("El nombre del Campo no puede estar vacio");
        }
        if (!cadenas.es_letras(p.getNombre())) {
            throw new Exception("El nombre del Campo debe contener solo caracteres alfabeticos");
        }
        
//Valida si contine letras
       if (!cadenas.es_numero(p.getSuperficie_total())){ 
            throw new Exception("Superficie Total debe contener solo caracteres numéricos");
        }
        if (p.getDomicilio() == null) {
            throw new Exception("Debe Ingresar el domicilio");
        }
         if (p.getProductor() == null) {
            throw new Exception("Debe Ingresar Seleccionar el productor");
        }
         if (p.getTenencia() == null) {
            throw new Exception("Debe Ingresar Seleccionar la tenencia");
        }


    }//fin validar
     
    
}
