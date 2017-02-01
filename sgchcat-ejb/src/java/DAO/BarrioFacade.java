/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entidad.Barrio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author cris
 */
@Stateless
public class BarrioFacade extends AbstractFacade<Barrio> implements BarrioFacadeLocal {
    @PersistenceContext(unitName = "ClusterHortDB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BarrioFacade() {
        super(Barrio.class);
    }
    @Override
    public void updateBorrado(Boolean borrado, Long id) throws Exception {
        Query q = em.createNamedQuery("Barrio.UpdateBorrado");
        q.setParameter("estado", borrado);
        q.setParameter("id", id);
        q.executeUpdate();
    }

    @Override
    public void updateHabilitado(Boolean habilitado, Long id) throws Exception {

        Query q = em.createNamedQuery("Barrio.UpdateHabilitado");
        q.setParameter("estado", habilitado);
        q.setParameter("id", id);

        q.executeUpdate();
    }

    @Override
    public List<Barrio> findByLocalidad(Long idLocalidad) throws Exception {
        Query q = em.createNamedQuery("Barrio.findByLocalidad");
        q.setParameter("idLocalidad", idLocalidad);
        return q.getResultList();
    }

    @Override
    public List<Barrio> findByLocalidadBorrado(Long idLocalidad, Boolean estado) throws Exception {
        Query q = em.createNamedQuery("Barrio.findByLocalidadBorrado");
        q.setParameter("idLocalidad", idLocalidad);
        q.setParameter("estado", estado);
        return q.getResultList();
    }

    @Override
    public List<Barrio> findByBorrado(Boolean estado) throws Exception {
        Query q = em.createNamedQuery("Barrio.findByBorrado");
        q.setParameter("estado", estado);
        return q.getResultList();
    }

    @Override
    public List<Barrio> findLike(String cadena, Boolean conBorrados) throws Exception {
        String sQuery;
        if (conBorrados) {
            sQuery = "SELECT b FROM Barrio b WHERE TRIM(LOWER(b.descripcion)) LIKE '%" + cadena.trim().toLowerCase() + "%'";
        } else {
            sQuery = "SELECT b FROM Barrio b WHERE b.borrado = false AND "
                    + "b.habilitado = true AND "
                    + "TRIM(LOWER(b.descripcion)) LIKE '%" + cadena.trim().toLowerCase() + "%'";
        }

        sQuery += " ORDER BY b.descripcion";
        Query q = em.createQuery(sQuery);

        return q.getResultList();
    }
    
}
