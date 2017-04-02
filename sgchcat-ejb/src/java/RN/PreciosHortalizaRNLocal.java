/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import entidad.PreciosHortaliza;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author RafaTrossero
 */
@Local
public interface PreciosHortalizaRNLocal {
    
     void create(PreciosHortaliza p) throws Exception;

    void remove(PreciosHortaliza p) throws Exception;

    void edit(PreciosHortaliza p) throws Exception;

    List<PreciosHortaliza> findAll() throws Exception;


    public void activate(PreciosHortaliza p, Boolean bEstado) throws Exception;
    
    List<PreciosHortaliza> findAllActivo() throws Exception;
   
}
