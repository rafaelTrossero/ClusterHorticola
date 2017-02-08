/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import entidad.Especie;
import entidad.Variedad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author RafaTrossero
 */
@Local
public interface VariedadRNLocal {
    
    
      public void create(Variedad v) throws Exception;

    public void edit(Variedad v) throws Exception;

    public void remove (Variedad v) throws Exception;

    public void habilitar(Variedad v) throws Exception;

    public List<Variedad> findAll() throws Exception;
    
    public List <Variedad> findByEspecie (Long idEspecie) throws Exception;
    

    
}
