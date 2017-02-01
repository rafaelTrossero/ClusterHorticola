/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import entidad.Localidad;
import entidad.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bcasas
 */
@Local
public interface LocalidadRNLocal {

    public void create(Localidad l, Usuario usuario, String pantalla) throws Exception;

    public void edit(Localidad l, Usuario usuario, String pantalla) throws Exception;

    public void remove(Localidad l, Boolean bEstado, Usuario usuario, String pantalla) throws Exception;

    public void habilitar(Localidad l, Boolean bEstado, Usuario usuario, String pantalla) throws Exception;

    public List<Localidad> findAll() throws Exception;

    public List<Localidad> findLocalidadesSinAsociarAps();

    public List<Localidad> findByDepartamento(Long idDepartamento) throws Exception;

    public List<Localidad> findByDepartamentoBorrado(Long idDepartamento, Boolean borrado) throws Exception;

    public List<Localidad> findByBorrado(Boolean borrado) throws Exception;

    public List<Localidad> findLike(String cadena, Boolean conBorrado) throws Exception;
}
