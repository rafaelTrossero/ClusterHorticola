/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

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
public interface CampoProductorFacadeLocal {

    void create(CampoProductor campoProductor);

    void edit(CampoProductor campoProductor);

    void remove(CampoProductor campoProductor);

    CampoProductor find(Object id);

    List<CampoProductor> findAll();

    List<CampoProductor> findRange(int[] range);

    int count();
     public CampoProductor buscarCampoProductor(Campo campo, Productor productor) throws Exception;
    
}
