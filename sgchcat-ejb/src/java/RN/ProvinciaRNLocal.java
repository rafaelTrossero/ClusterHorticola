/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import entidad.Provincia;
import entidad.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan Pablo
 */
@Local
public interface ProvinciaRNLocal {
    public void create(Provincia p, Usuario usuario, String pantalla) throws Exception;
    public void edit(Provincia p, Usuario usuario, String pantalla) throws Exception;
    public void remove(Provincia p, Boolean bEstado, Usuario usuario, String pantalla) throws Exception;
    public void habilitar(Provincia p, Boolean bEstado, Usuario usuario, String pantalla) throws Exception;
    public List<Provincia> findAll() throws Exception;
    public List<Provincia> findByPais(Long idPais) throws Exception;
    public List<Provincia> findByBorrado(Boolean estado) throws Exception;
    public List<Provincia> findByPaisBorrado(Long idPais, Boolean estado) throws Exception;
    public List<Provincia> findLike(String cadena, Boolean conBorrado) throws Exception;
}
