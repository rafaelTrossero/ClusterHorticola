/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.IngresoMercadoFacadeLocal;
import entidad.IngresoMercado;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import recursos.cadenas;

/**
 *
 * @author RafaTrossero
 */
@Stateless
public class IngresoMercadoRN implements IngresoMercadoRNLocal {

    @EJB
    private IngresoMercadoFacadeLocal ingresoMercadoFacadeLocal;
    
    @Override
    public void create(IngresoMercado i) throws Exception {
        this.validar(i, 0);
    this.ingresoMercadoFacadeLocal.create(i);
    }

    @Override
    public void remove(IngresoMercado i) throws Exception {
    this.ingresoMercadoFacadeLocal.remove(i);
    }

    @Override
    public void edit(IngresoMercado i) throws Exception {
    this.ingresoMercadoFacadeLocal.edit(i);
    }

    @Override
    public List<IngresoMercado> findAll() throws Exception {
    return(this.ingresoMercadoFacadeLocal.findAll());
    }

  

    @Override
    public void activate(IngresoMercado i, Boolean bEstado) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IngresoMercado> findAllActivo() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  private void validar(IngresoMercado i, int op) throws Exception {
     
        System.out.println("ingreso es--->"+ i.getIngreso());
        System.out.println("procedencia es--->"+ i.getProcedencia());

        //verifica si es una línea en blanco
      if(i.getIngreso().equals("Extra Provincial")){
        if (i.getProcedencia() == null) {
            throw new Exception("Debe seleccionar una provincia de procedencia");
        }
      }/*
        if (!cadenas.es_numero(i.getKg().toString())){ 
            throw new Exception("Cantidad de Kg debe contener solo caracteres numéricos");
        }*/


    }//fin validar
}
