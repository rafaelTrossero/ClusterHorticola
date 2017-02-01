/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entidad.Barrio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cris
 */
@Local
public interface BarrioFacadeLocal {

    void create(Barrio barrio);

    void edit(Barrio barrio);

    void remove(Barrio barrio);

    Barrio find(Object id);

    List<Barrio> findAll();

    List<Barrio> findRange(int[] range);

    int count();

    public void updateBorrado(Boolean borrado, Long id) throws Exception;

    public void updateHabilitado(Boolean habilitado, Long id) throws Exception;

    public List<Barrio> findByLocalidad(Long idLocalidad) throws Exception;

    public List<Barrio> findByLocalidadBorrado(Long idLocalidad, Boolean estado) throws Exception;

    public List<Barrio> findByBorrado(Boolean estado) throws Exception;

    public List<Barrio> findLike(String cadena, Boolean conBorrados) throws Exception;
    
}
