/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RN;

import entidad.Campo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cris
 */
@Local
public interface CampoRNLocal {
    void create(Campo cam) throws Exception;

    void remove(Campo cam) throws Exception;

    void edit(Campo cam) throws Exception;

    List<Campo> findAll() throws Exception;

    public Campo findByDni(Integer dni) throws Exception;

    public void activate(Campo campo, Boolean bEstado) throws Exception;
    
    List<Campo> findAllActivo() throws Exception;
    
}
