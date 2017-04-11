/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entidad.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cris
 */
@Local
public interface UsuarioFacadeLocal {

   void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    
     public Usuario findByUsuarioContrasena (String username, String password) throws Exception;
     
     public Usuario findByUsuarioEmail (String email) throws Exception;
     public void activate(Usuario usu, Boolean bEstado);
     
      public Usuario findByAlumno(Integer matricula) throws Exception;
    
     public Boolean bFindByDni(Usuario p, int op) throws Exception;
      public Boolean bFindByUserName(Usuario p, int op) throws Exception;
    
}
