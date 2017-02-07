/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entidad.CampoDomicilio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cris
 */
@Local
public interface CampoDomicilioFacadeLocal {

    void create(CampoDomicilio campoDomicilio);

    void edit(CampoDomicilio campoDomicilio);

    void remove(CampoDomicilio campoDomicilio);

    CampoDomicilio find(Object id);

    List<CampoDomicilio> findAll();

    List<CampoDomicilio> findRange(int[] range);

    int count();
    
}
