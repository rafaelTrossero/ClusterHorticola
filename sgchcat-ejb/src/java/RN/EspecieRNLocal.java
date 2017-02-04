/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import entidad.Especie;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author RafaTrossero
 */
@Local
public interface EspecieRNLocal {
    
      public void create(Especie e) throws Exception;

    public void edit(Especie e) throws Exception;

    public void remove (Especie e) throws Exception;

    public void habilitar(Especie e) throws Exception;

    public List<Especie> findAll() throws Exception;
}
