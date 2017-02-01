/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import entidad.Departamento;
import entidad.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan Pablo
 */
@Local
public interface DepartamentoRNLocal {
    public void create(Departamento d, Usuario usuario, String pantalla) throws Exception;
    public void edit(Departamento d, Usuario usuario, String pantalla) throws Exception;
    public void remove(Departamento d, Boolean bEstado, Usuario usuario, String pantalla) throws Exception;
    public void habilitar(Departamento d, Boolean bEstado, Usuario usuario, String pantalla) throws Exception;
    public List<Departamento> findAll() throws Exception;
    
    public List<Departamento> findByProvincia(Long idProvincia) throws Exception;
    public List<Departamento> findByProvinciaBorrado(Long idProvincia, Boolean estado) throws Exception;
    public List<Departamento> findByBorrado(Boolean estado) throws Exception;
    public List<Departamento> findLike(String cadena, Boolean conBorrado) throws Exception;
}
