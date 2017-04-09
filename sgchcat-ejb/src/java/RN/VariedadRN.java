/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.VariedadFacadeLocal;
import entidad.Variedad;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

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
    this.variedadFacadeLocal.create(v);
    }

    @Override
    public void edit(Variedad v) throws Exception {
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

    
}
