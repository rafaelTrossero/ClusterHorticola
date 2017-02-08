/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidad.Variedad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author RafaTrossero
 */
@Local
public interface VariedadFacadeLocal {

    void create(Variedad variedad);

    void edit(Variedad variedad);

    void remove(Variedad variedad);

    Variedad find(Object id);

    List<Variedad> findAll();

    List<Variedad> findRange(int[] range);

    int count();
    
     public List <Variedad> findByEspecie (Long idEspecie) throws Exception;
    
}
