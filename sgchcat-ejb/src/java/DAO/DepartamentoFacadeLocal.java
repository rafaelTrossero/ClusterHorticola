/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entidad.Departamento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cris
 */
@Local
public interface DepartamentoFacadeLocal {

    void create(Departamento departamento);

  void edit(Departamento departamento);

  void remove(Departamento departamento);

  Departamento find(Object id);

  List<Departamento> findAll();

  List<Departamento> findRange(int[] range);
  
   public void updateBorrado(Boolean borrado, Long id) throws Exception;
   public void updateHabilitado(Boolean habilitado, Long id) throws Exception;
   public Departamento findDepartamentoId(Departamento d, int op) throws Exception;
   public Departamento findCapturaDepartamentoId(Departamento d, int op) throws Exception;

  int count();
  public Boolean bFindByCodigoDepartamento(Departamento d, int op) throws Exception;
  public Boolean bFindByNombreDepartamento(Departamento d, int op) throws Exception;
  
  public List<Departamento> findByProvincia(Long idProvincia) throws Exception;
  public List<Departamento> findByProvinciaBorrado(Long idProvincia, Boolean estado) throws Exception;
  public List<Departamento> findByBorrado(Boolean estado) throws Exception;
  
  public List<Departamento> findLike(String cadena, Boolean conBorrados) throws Exception;
  public Departamento findByIdNombreDepartamento(Long id, String nombre) throws Exception;
    
}
