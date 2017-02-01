/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entidad.Localidad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cris
 */
@Local
public interface LocalidadFacadeLocal {

   void create(Localidad localidad);

    void edit(Localidad localidad);

    void remove(Localidad localidad);

    Localidad find(Object id);

    List<Localidad> findAll();

    List<Localidad> findRange(int[] range);

    int count();

    public List<Localidad> findLocalidadesSinAsociarAps();

    public void updateBorrado(Boolean borrado, Long id) throws Exception;

    public void updateHabilitado(Boolean habilitado, Long id) throws Exception;

    public Boolean bFindByCodigoLocalidad(Localidad l, int op) throws Exception;

    public Boolean bFindByNombreLocalidad(Localidad l, int op) throws Exception;

    public Boolean bFindByCodigoPostal(Localidad l, int op) throws Exception;

    public List<Localidad> findByDepartamento(Long idDepartamento) throws Exception;

    public List<Localidad> findByDepartamentoBorrado(Long idDepartamento, Boolean estado) throws Exception;

    public List<Localidad> findByBorrado(Boolean estado) throws Exception;

    public List<Localidad> findLike(String cadena, Boolean conBorrados) throws Exception;

    public Localidad findByIdNombreLocalidad(Long id, String nombre) throws Exception;
    
}
