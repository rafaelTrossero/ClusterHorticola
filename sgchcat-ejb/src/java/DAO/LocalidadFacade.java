/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entidad.Localidad;
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
public class LocalidadFacade extends AbstractFacade<Localidad> implements LocalidadFacadeLocal {
    @PersistenceContext(unitName = "ClusterHortDB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LocalidadFacade() {
        super(Localidad.class);
    }
    @Override
    public void updateBorrado(Boolean borrado, Long id) throws Exception {
        Query q = em.createNamedQuery("Localidad.UpdateBorrado");
        q.setParameter("estado", borrado);
        q.setParameter("id", id);
        q.executeUpdate();
    }

    @Override
    public void updateHabilitado(Boolean habilitado, Long id) throws Exception {

        Query q = em.createNamedQuery("Localidad.UpdateHabilitado");
        q.setParameter("estado", habilitado);
        q.setParameter("id", id);

        q.executeUpdate();
    }

    @Override
    public List<Localidad> findLocalidadesSinAsociarAps() {
        Query q = em.createNamedQuery("Localidad.findLocalidadesSinAsociarAps");
        return q.getResultList();
    }

    @Override
    public Boolean bFindByCodigoLocalidad(Localidad l, int op) throws Exception {
        Query q = null;

        if (op == 0) {
            //guardar
            q = em.createNamedQuery("Localidad.findByCodigoLocalidad");

        } else {
            //modificar
            q = em.createNamedQuery("Localidad.findByCodigoLocalidadID");
            q.setParameter("id", l.getId());

        }//fin if

        q.setParameter("codigo", l.getCodigo());
        q.setParameter("departamento", l.getDepartamento());

        try {
            q.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    @Override
    public Boolean bFindByNombreLocalidad(Localidad l, int op) throws Exception {
        Query q = null;

        if (op == 0) {
            //guardar
            q = em.createNamedQuery("Localidad.findByNombreLocalidad");

        } else {
            //modificar
            q = em.createNamedQuery("Localidad.findByNombreLocalidadID");
            q.setParameter("id", l.getId());

        }//fin if

        q.setParameter("nombre", l.getDescripcion());

        try {
            q.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    @Override
    public Boolean bFindByCodigoPostal(Localidad l, int op) throws Exception {
        Query q = null;

        if (op == 0) {
            //guardar
            q = em.createNamedQuery("Localidad.findByCodigoPostal");

        }
//        else{
//            //modificar
//            q = em.createNamedQuery("Localidad.findByCodigoPostalID");
//            q.setParameter("id", l.getId());
//            
//            
//        }//fin if

        q.setParameter("codigoPostal", l.getCodigoPostal());

        try {
            q.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    @Override
    public List<Localidad> findByDepartamento(Long idDepartamento) throws Exception {
        Query q = em.createNamedQuery("Localidad.findByDepartamento");
        q.setParameter("idDepartamento", idDepartamento);
        return q.getResultList();
    }

//    @Override
//    public String ObtenerIndec(Localidad loc) throws Exception {
//        
//        String indecDepartamento = null;
//        Query q= em.createNamedQuery("Localidad.finByCodigoIndec");
//        Long id= loc.getDepartamento().getId();
//        q.setParameter("id", id);
//        indecDepartamento = ((Departamento)q.getSingleResult()).getIndec();
//        System.out.println("pasa el casteo: " + indecDepartamento );
//        return indecDepartamento.concat(loc.getIndec());
//    }
    @Override
    public List<Localidad> findByDepartamentoBorrado(Long idDepartamento, Boolean estado) throws Exception {
        Query q = em.createNamedQuery("Localidad.findByDepartamentoBorrado");
        q.setParameter("idDepartamento", idDepartamento);
        q.setParameter("estado", estado);
        return q.getResultList();
    }

    @Override
    public List<Localidad> findByBorrado(Boolean estado) throws Exception {
        Query q = em.createNamedQuery("Localidad.findByBorrado");
        q.setParameter("estado", estado);
        return q.getResultList();
    }

    @Override
    public List<Localidad> findLike(String cadena, Boolean conBorrados) throws Exception {
        String sQuery;
        if (conBorrados) {
            sQuery = "SELECT l FROM Localidad l WHERE TRIM(LOWER(l.descripcion)) LIKE '%" + cadena.trim().toLowerCase() + "%'";
        } else {
            sQuery = "SELECT l FROM Localidad l WHERE l.borrado = false AND "
                    + "l.habilitado = true AND "
                    + "TRIM(LOWER(l.descripcion)) LIKE '%" + cadena.trim().toLowerCase() + "%'";
        }
        sQuery += " ORDER BY l.descripcion";

        Query q = em.createQuery(sQuery);

        return q.getResultList();
    }

    @Override
    public Localidad findByIdNombreLocalidad(Long id, String nombre) throws Exception {
        Query q = null;

        q = em.createNamedQuery("Localidad.findByIDNombreLocalidad");
        q.setParameter("id", id);
        q.setParameter("nombre", nombre);

        return ((Localidad) q.getSingleResult());
    }
    
}
