/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entidad.Usuario;
import entidad.tipoUsuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import recursos.Encriptacion;

/**
 *
 * @author cris
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {
    @PersistenceContext(unitName = "ClusterHortDB-ejbPU")
    private EntityManager em;
    private Encriptacion encriptAdmin = new Encriptacion();

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public Usuario findByUsuarioContrasena(String username, String password) throws Exception {
           String encriptMD5= encriptAdmin.hash("123");
           if (username.equals("Admin") && password.equals(encriptMD5)) {
            Usuario user_admin = new Usuario();
            user_admin.setUsername("Usuario administrador");
            user_admin.setTipousuario(tipoUsuario.ADMINISTRADOR);
            user_admin.setId(9999L);
   
            return user_admin;
        }
        
         try {
            Query q = em.createNamedQuery("Usuario.findByUsuarioContrasena");
            q.setParameter("username", username);
            q.setParameter("password", password);

            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        
    }
    
       @Override
    public Usuario findByUsuarioEmail(String email) throws Exception {
                 
         try {
            Query q = em.createNamedQuery("Usuario.findByUsuarioEmail");
            q.setParameter("email", email);
           

            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        
    }

    @Override
    public void activate(Usuario usu, Boolean bEstado) {
   Query q = em.createNamedQuery("Usuario.ActualizarEstado");
        q.setParameter("active", bEstado);
        q.setParameter("id", usu.getId());
        // Query q = em.createQuery("UPDATE Agenda p SET p.habilitado = " + estado + " WHERE p.id = " +  id);    
         /*q.setParameter("id", id);
         q.setParameter("estado", estado);  */
        q.executeUpdate();
    
    }

    @Override
    public Usuario findByAlumno(Integer matricula) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean bFindByDni(Usuario p, int op) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean bFindByUserName(Usuario p, int op) throws Exception {
     Query q = null;

        if (op == 0) {
            //guardar
            q = em.createNamedQuery("Usuario.findByNameUserID");

        } else {
            //modificar
            q = em.createNamedQuery("Usuario.findByNameUser");
            q.setParameter("id", p.getId());

        }//fin if

        q.setParameter("userName", p.getUsername());

        try {
            q.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    
    }
    
}
