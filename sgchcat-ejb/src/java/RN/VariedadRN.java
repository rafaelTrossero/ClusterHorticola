/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.VariedadFacadeLocal;
import entidad.IngresoMercado;
import entidad.Variedad;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import recursos.cadenas;

/**
 *
 * @author RafaTrossero
 */
@Stateless
public class VariedadRN implements VariedadRNLocal {

    @EJB
    private VariedadFacadeLocal variedadFacadeLocal;
    
    @Override
    public void create(Variedad v) throws Exception {
        this.convertir_strings(v);
        this.validar(v, 0);
    this.variedadFacadeLocal.create(v);
    }

    @Override
    public void edit(Variedad v) throws Exception {
        this.convertir_strings(v);
        this.validar(v, 1);
    this.variedadFacadeLocal.edit(v);
    }

    @Override
    public void remove(Variedad v) throws Exception {
    this.variedadFacadeLocal.remove(v);
    }

    @Override
    public void habilitar(Variedad v) throws Exception {
    this.habilitar(v);
    }

    @Override
    public List<Variedad> findAll() throws Exception {
    return(this.variedadFacadeLocal.findAll()); 
    }

    @Override
    public List<Variedad> findByEspecie(Long idEspecie) throws Exception {
    return (this.variedadFacadeLocal.findByEspecie(idEspecie));
    }

    @Override
    public void activate(Variedad variedad, Boolean bEstado) throws Exception {
      variedadFacadeLocal.activate(variedad, bEstado);
    }

    @Override
    public List<Variedad> findAllActivo() throws Exception {
      return(this.variedadFacadeLocal.findAllActivo());
    }

     private void validar(Variedad i, int op) throws Exception {
     
        
        //verifica si es una línea en blanco
      if (i.getEspecie()== null) {
            throw new Exception("Debe seleccionar especie");
        }
        if (i.getDescripcion().isEmpty()) {
            throw new Exception("Debe ingresar una variedad");
        }
        
              if (variedadFacadeLocal.bFindByNombreVariedad(i, op)) {
            throw new Exception("La variedad "+i.getDescripcion() +" para la especie "+i.getEspecie().getDescripcion() +" ya existe");
        }//fin if
      /*
        if (!cadenas.es_numero(i.getKg().toString())){ 
            throw new Exception("Cantidad de Kg debe contener solo caracteres numéricos");
        }*/


    }//fin validar
    
      private void convertir_strings(Variedad p) {
        p.setDescripcion(cadenas.convertir(p.getDescripcion()));
    }
}
