/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.ProductorFacadeLocal;
import entidad.Campo;
import entidad.Productor;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import recursos.cadenas;

/**
 *
 * @author RafaTrossero
 */
@Stateless
public class ProductorRN implements ProductorRNLocal {
    
    @EJB
    private ProductorFacadeLocal productorFacadeLocal;
    

    @Override
    public void create(Productor pro) throws Exception {
         this.convertir_strings(pro);
        this.validar(pro, 0);
        this.productorFacadeLocal.create(pro);
    }

    @Override
    public void remove(Productor pro) throws Exception {
    this.productorFacadeLocal.remove(pro);
    }

    @Override
    public void edit(Productor pro) throws Exception {
        this.convertir_strings(pro);
        this.validar(pro, 1);
    this.productorFacadeLocal.edit(pro);
    }

    @Override
    public List<Productor> findAll() throws Exception {
    return (this.productorFacadeLocal.findAll());
    }

    @Override
    public Productor findByDni(Integer dni) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void activate(Productor productor, Boolean bEstado) throws Exception {
   productorFacadeLocal.activate(productor, bEstado);
    }

    @Override
    public List<Productor> findAllActivo() throws Exception {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  private void convertir_strings(Productor p) {
        p.setNombre(cadenas.convertir(p.getNombre()));
    }
      private void validar(Productor p, int op) throws Exception {
        //verifica si el código es menor o igual a cero
        

        //verifica si es una línea en blanco
        if (p.getNombre().trim().length() == 0) {
            throw new Exception("El nombre del Productor no puede estar vacio");
        }
        if (p.getCuil().trim().length() == 0) {
            throw new Exception("El cuil del Productor no puede estar vacio");
        }
        
        if (!cadenas.es_numero(p.getDni())){ 
            throw new Exception("El DNI del Productor no puede estar vacio");
        }
        if (!cadenas.es_letras(p.getNombre())) {
            throw new Exception("El nombre del Productor debe contener solo caracteres alfabeticos");
        }
        if(!p.getCelular().equals("")){
         if (!cadenas.es_numero(p.getCelular())){ 
            throw new Exception("El Numero de celular del Productor no puede contener caracteres alfabeticos");
        }
        }if(!p.getCelular().equals("")){
          if (!cadenas.es_numero(p.getTelefono())){ 
            throw new Exception("El Numero de Telefono del Productor no puede contener caracteres alfabeticos");
        }
        }
//Valida si contine letras
      
         if (p.getDomicilio().getLocalidad() == null) {
            throw new Exception("Debe Ingresar la Localidad");
        }
         if (productorFacadeLocal.bFindByDni(p, op)) {
         throw new Exception("Ya existe un productor con ese DNI");
         }
         if (productorFacadeLocal.bFindByCuil(p, op)) {
         throw new Exception("Ya existe un productor con ese CUIT");
         }

    }//fin validar
}
