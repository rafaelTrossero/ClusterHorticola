/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidad.PreciosHortaliza;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author RafaTrossero
 */
@Local
public interface PreciosHortalizaFacadeLocal {

    void create(PreciosHortaliza preciosHortaliza);

    void edit(PreciosHortaliza preciosHortaliza);

    void remove(PreciosHortaliza preciosHortaliza);

    PreciosHortaliza find(Object id);

    List<PreciosHortaliza> findAll();

    List<PreciosHortaliza> findRange(int[] range);

    int count();
     public void activate(PreciosHortaliza preciosHortaliza, Boolean bEstado);
    
}
