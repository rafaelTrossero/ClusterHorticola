/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RN;

import entidad.Campo;
import entidad.CampoProductor;
import entidad.Productor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cris
 */
@Local
public interface CampoProductorRNLocal {
    void create(CampoProductor cam) throws Exception;

    void remove(CampoProductor cam) throws Exception;

    void edit(CampoProductor cam) throws Exception;

    List<CampoProductor> findAll() throws Exception;
    
    public CampoProductor buscarCampoProductor(Campo campo, Productor productor) throws Exception;
}
