/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.EmpaqueFacadeLocal;
import entidad.Empaque;
import entidad.Productor;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import recursos.cadenas;

/**
 *
 * @author cris
 */
@Stateless
public class EmpaqueRN implements EmpaqueRNLocal {

    @EJB
    private EmpaqueFacadeLocal empaqueFacadeLocal;

    @Override
    public void create(Empaque e) throws Exception {
             this.convertir_strings(e);
        this.validar(e, 0);
        this.empaqueFacadeLocal.create(e);
    }

    @Override
    public void edit(Empaque e) throws Exception {
        this.convertir_strings(e);
        this.validar(e, 1);
        this.empaqueFacadeLocal.edit(e);
    }

    @Override
    public void remove(Empaque e) throws Exception {
        this.empaqueFacadeLocal.remove(e);
    }

    @Override
    public void habilitar(Empaque e) throws Exception {
   
    }

    @Override
    public List<Empaque> findAll() throws Exception {
        return (this.empaqueFacadeLocal.findAll());
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void activate(Empaque emp, Boolean bEstado) throws Exception {
    this.empaqueFacadeLocal.activate(emp, bEstado);
    }
    
    private void convertir_strings(Empaque p) {
        p.setAsociacion(cadenas.convertir(p.getAsociacion()));
    }
      private void validar(Empaque p, int op) throws Exception {
        //verifica si el código es menor o igual a cero
        

        //verifica si es una línea en blanco
        
        
//Valida si contine letras
      
         if (p.getProductor() == null) {
            throw new Exception("Debe Seleccionar Productor");
        }
         if (p.getEspecie() == null) {
            throw new Exception("Debe Seleccionar Especie");
        }
          if (p.getVariedad() == null) {
            throw new Exception("Debe Seleccionar Variedad");
        }
          if (p.getUnidad().equals("")) {
            throw new Exception("Debe seleccionar Unidad");
        }
          if (p.getFecha() == null) {
            throw new Exception("Debe Seleccionar Fecha");
        }

    }//fin validar
}
