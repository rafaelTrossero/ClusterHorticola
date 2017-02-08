/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import entidad.IngresoMercado;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author RafaTrossero
 */
@Local
public interface IngresoMercadoRNLocal {
    void create(IngresoMercado i) throws Exception;

    void remove(IngresoMercado i) throws Exception;

    void edit(IngresoMercado i) throws Exception;

    List<IngresoMercado> findAll() throws Exception;

   

    public void activate(IngresoMercado i , Boolean bEstado) throws Exception;
    
    List<IngresoMercado> findAllActivo() throws Exception;
}
