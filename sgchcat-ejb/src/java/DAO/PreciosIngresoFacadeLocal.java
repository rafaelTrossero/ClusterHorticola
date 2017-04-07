/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidad.PreciosIngreso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USUARIO
 */
@Local
public interface PreciosIngresoFacadeLocal {

    void create(PreciosIngreso preciosIngreso);

    void edit(PreciosIngreso preciosIngreso);

    void remove(PreciosIngreso preciosIngreso);

    PreciosIngreso find(Object id);

    List<PreciosIngreso> findAll();

    List<PreciosIngreso> findRange(int[] range);

    int count();
    
}
