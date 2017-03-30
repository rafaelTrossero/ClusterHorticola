/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.UsuarioFacadeLocal;
import entidad.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author RafaTrossero
 */
@Stateless
public class UsuarioRN implements UsuarioRNLocal {

    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    @Override
    public Usuario findByUsuarioContrasena(String username, String password) throws Exception {
        return usuarioFacadeLocal.findByUsuarioContrasena(username, password);
        
    }
 @Override
    public Usuario findByUsuarioEmail(String email) throws Exception {
        return usuarioFacadeLocal.findByUsuarioEmail(email);
        
    }
    
      @Override
    public void edit(Usuario p) throws Exception {
        
        this.usuarioFacadeLocal.edit(p);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void create(Usuario usu) throws Exception {
   this.usuarioFacadeLocal.create(usu);
    }

    @Override
    public void remove(Usuario usu) throws Exception {
   this.usuarioFacadeLocal.remove(usu);
    }

    @Override
    public List<Usuario> findAll() throws Exception {
    return (this.usuarioFacadeLocal.findAll());
    
    }
    
}
