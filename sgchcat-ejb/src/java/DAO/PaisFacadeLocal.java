/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entidad.Pais;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cris
 */
@Local
public interface PaisFacadeLocal {

    void create(Pais pais);

    void edit(Pais pais);

    void remove(Pais pais);

    Pais find(Object id);

    List<Pais> findAll();

    List<Pais> findRange(int[] range);

    int count();

    public void updateBorrado(Boolean borrado, Long id) throws Exception;

    public void updateHabilitado(Boolean habilitado, Long id) throws Exception;

    public Boolean bFindByCodigoPais(Pais p, int op) throws Exception;

    public Boolean bFindByNombrePais(Pais p, int op) throws Exception;

    public List<Pais> findLike(String cadena, Boolean conBorrados) throws Exception;

    public Pais FindByIdNombrePais(Long id, String nombre) throws Exception;
    
}
