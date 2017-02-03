/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import entidad.Productor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author RafaTrossero
 */
@Local
public interface ProductorRNLocal {
    
    void create(Productor pro) throws Exception;

    void remove(Productor pro) throws Exception;

    void edit(Productor pro) throws Exception;

    List<Productor> findAll() throws Exception;

    public Productor findByDni(Integer dni) throws Exception;

    public void activate(Productor productor, Boolean bEstado) throws Exception;
    
    List<Productor> findAllActivo() throws Exception;
}
