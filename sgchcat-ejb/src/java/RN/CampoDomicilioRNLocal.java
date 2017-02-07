/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RN;

import entidad.CampoDomicilio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cris
 */
@Local
public interface CampoDomicilioRNLocal {
   void create(CampoDomicilio cam) throws Exception;

    void remove(CampoDomicilio cam) throws Exception;

    void edit(CampoDomicilio cam) throws Exception;

    List<CampoDomicilio> findAll() throws Exception; 
}
