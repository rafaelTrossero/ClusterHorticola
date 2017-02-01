/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import entidad.Usuario;
import javax.ejb.Local;

/**
 *
 * @author RafaTrossero
 */
@Local
public interface UsuarioRNLocal {
    
    public Usuario findByUsuarioContrasena (String username, String password) throws Exception;
    
    public Usuario findByUsuarioEmail(String email) throws Exception;
    
      void edit(Usuario p) throws Exception;
}
