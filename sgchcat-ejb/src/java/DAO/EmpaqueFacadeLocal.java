/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entidad.Empaque;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cris
 */
@Local
public interface EmpaqueFacadeLocal {

    void create(Empaque empaque);

    void edit(Empaque empaque);

    void remove(Empaque empaque);

    Empaque find(Object id);

    List<Empaque> findAll();

    List<Empaque> findRange(int[] range);

    int count();
    
}
