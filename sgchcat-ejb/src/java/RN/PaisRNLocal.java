/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import entidad.Pais;
import entidad.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author AFerSor
 */
@Local
public interface PaisRNLocal {

    public void create(Pais p, Usuario usuario, String pantalla) throws Exception;

    public void edit(Pais p, Usuario usuario, String pantalla) throws Exception;

    public void remove(Pais p, Boolean bEstado, Usuario usuario, String pantalla) throws Exception;

    public void habilitar(Pais p, Boolean bEstado, Usuario usuario, String pantalla) throws Exception;

    public List<Pais> findAll() throws Exception;

    public List<Pais> findLike(String cadena, Boolean conBorrados) throws Exception;
}//FIN CLASE
