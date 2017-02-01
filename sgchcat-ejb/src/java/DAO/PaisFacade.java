/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entidad.Pais;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author cris
 */
@Stateless
public class PaisFacade extends AbstractFacade<Pais> implements PaisFacadeLocal {
    @PersistenceContext(unitName = "ClusterHortDB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaisFacade() {
        super(Pais.class);
    }
    
     @Override
    public void updateBorrado(Boolean borrado, Long id) throws Exception {
        Query q = em.createNamedQuery("Pais.UpdateBorrado");
        q.setParameter("estado", borrado);
        q.setParameter("id", id);
        q.executeUpdate();
    }

    @Override
    public void updateHabilitado(Boolean habilitado, Long id) throws Exception {

        Query q = em.createNamedQuery("Pais.UpdateHabilitado");
        q.setParameter("estado", habilitado);
        q.setParameter("id", id);

        q.executeUpdate();
    }

    @Override
    public Boolean bFindByCodigoPais(Pais p, int op) throws Exception {
        Query q = null;

        if (op == 0) {
            //guardar
            q = em.createNamedQuery("Pais.findByCodigoPais");

        } else {
            //modificar
            q = em.createNamedQuery("Pais.findByCodigoPaisID");
            q.setParameter("id", p.getId());

        }//fin if

        q.setParameter("codigo", p.getCodigo());

        try {
            q.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    @Override
    public Boolean bFindByNombrePais(Pais p, int op) throws Exception {
        Query q = null;

        if (op == 0) {
            //guardar
            q = em.createNamedQuery("Pais.findByNombrePais");

        } else {
            //modificar
            q = em.createNamedQuery("Pais.findByNombrePaisID");
            q.setParameter("id", p.getId());

        }//fin if

        q.setParameter("nombre", p.getDescripcion());

        try {
            q.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }

    }

    @Override
    public List<Pais> findLike(String cadena, Boolean conBorrados) throws Exception {

        String sQuery;
        if (conBorrados) {
            sQuery = "SELECT p FROM Pais p WHERE TRIM(LOWER(p.descripcion)) LIKE '%" + cadena.trim().toLowerCase() + "%'";
        } else {
            sQuery = "SELECT p FROM Pais p WHERE p.borrado = false AND "
                    + "p.habilitado = true AND "
                    + "TRIM(LOWER(p.descripcion)) LIKE '%" + cadena.trim().toLowerCase() + "%'";
        }
        sQuery += " ORDER BY p.descripcion";
        Query q = em.createQuery(sQuery);

        return q.getResultList();
    }

    @Override
    public Pais FindByIdNombrePais(Long id, String nombre) throws Exception {
        Query q = null;

        q = em.createNamedQuery("Pais.findByIDNombrePais");
        q.setParameter("id", id);
        q.setParameter("nombre", nombre);

        return ((Pais) q.getSingleResult());
    }
}
