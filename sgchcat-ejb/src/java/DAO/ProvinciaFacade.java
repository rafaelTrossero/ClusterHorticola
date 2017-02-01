/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entidad.Provincia;
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
public class ProvinciaFacade extends AbstractFacade<Provincia> implements ProvinciaFacadeLocal {
    @PersistenceContext(unitName = "ClusterHortDB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProvinciaFacade() {
        super(Provincia.class);
    }
    @Override
    public void updateBorrado(Boolean borrado, Long id) throws Exception {
        Query q = em.createNamedQuery("Provincia.UpdateBorrado");
        q.setParameter("estado", borrado);
        q.setParameter("id", id);
        q.executeUpdate();
    }

    @Override
    public void updateHabilitado(Boolean habilitado, Long id) throws Exception {

        Query q = em.createNamedQuery("Provincia.UpdateHabilitado");
        q.setParameter("estado", habilitado);
        q.setParameter("id", id);

        q.executeUpdate();
    }

    @Override
    public Boolean bFindByCodigoProvincia(Provincia p, int op) throws Exception {
        Query q = null;


        if (op == 0) {
            //guardar
            q = em.createNamedQuery("Provincia.findByCodigoProvincia");

        } else {
            //modificar
            q = em.createNamedQuery("Provincia.findByCodigoProvinciaID");
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
    public Boolean bFindByNombreProvincia(Provincia p, int op) throws Exception {
        Query q = null;


        if (op == 0) {
            //guardar
            q = em.createNamedQuery("Provincia.findByNombreProvincia");

        } else {
            //modificar
            q = em.createNamedQuery("Provincia.findByNombreProvinciaID");
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
    public List<Provincia> findByPais(Long idPais) throws Exception {
        Query q = em.createNamedQuery("Provincia.findByPais");
        q.setParameter("idPais", idPais);
        return q.getResultList();
    }//fin findByPais

    @Override
    public List<Provincia> findByBorrado(Boolean borrado) throws Exception {
        Query q = em.createNamedQuery("Provincia.findByBorrado");
        q.setParameter("estado", borrado);
        return q.getResultList();
    }

    @Override
    public List<Provincia> findByPaisBorrado(Long idPais, Boolean borrado) throws Exception {
        Query q = em.createNamedQuery("Provincia.findByPaisBorrado");
        q.setParameter("idPais", idPais);
        q.setParameter("estado", borrado);
        return q.getResultList();
    }

    @Override
    public List<Provincia> findLike(String cadena, Boolean conBorrados) throws Exception {
        String sQuery;
        if (conBorrados) {
            sQuery = "SELECT p FROM Provincia p WHERE TRIM(LOWER(p.descripcion)) LIKE '%" + cadena.trim().toLowerCase() + "%'";
        } else {
            sQuery = "SELECT p FROM Provincia p WHERE p.borrado = false AND "
                    + "p.habilitado = true AND "
                    + "TRIM(LOWER(p.descripcion)) LIKE '%" + cadena.trim().toLowerCase() + "%'";
        }
        sQuery += " ORDER BY p.descripcion";
        Query q = em.createQuery(sQuery);

        return q.getResultList();
    }

    @Override
    public Provincia findByIdNombreProvincia(Long id, String nombre) throws Exception {
        Query q = null;

        q = em.createNamedQuery("Provincia.findByIDNombreProvincia");
        q.setParameter("id", id);
        q.setParameter("nombre", nombre);

        return ((Provincia) q.getSingleResult());
    }
    
}
