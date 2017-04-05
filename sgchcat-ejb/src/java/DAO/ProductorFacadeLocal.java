/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidad.Productor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author RafaTrossero
 */
@Local
public interface ProductorFacadeLocal {

    void create(Productor productor);

    void edit(Productor productor);

    void remove(Productor productor);

    Productor find(Object id);

    List<Productor> findAll();

    List<Productor> findRange(int[] range);
     public Boolean bFindByDni(Productor p, int op) throws Exception;
     public Boolean bFindByCuil(Productor p, int op) throws Exception;
    int count();
    public void activate(Productor productor, Boolean bEstado);
    
}
