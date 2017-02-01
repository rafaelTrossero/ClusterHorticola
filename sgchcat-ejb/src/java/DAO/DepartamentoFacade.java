/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entidad.Departamento;
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
public class DepartamentoFacade extends AbstractFacade<Departamento> implements DepartamentoFacadeLocal {
    @PersistenceContext(unitName = "ClusterHortDB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartamentoFacade() {
        super(Departamento.class);
    }
    @Override
    public void updateBorrado(Boolean borrado, Long id) {
        Query q = em.createNamedQuery("Departamento.UpdateBorrado");
        q.setParameter("estado", borrado);
        q.setParameter("id", id);
        // Query q = em.createQuery("UPDATE Profesional p SET p.habilitado = " + estado + " WHERE p.id = " +  id);    
         /*q.setParameter("id", id);
         q.setParameter("estado", estado);  */
        q.executeUpdate();
    }//fin 

    @Override
    public void updateHabilitado(Boolean habilitado, Long id) throws Exception {

        System.out.println("Entro al habilitado: " + habilitado);
        Query q = em.createNamedQuery("Departamento.UpdateHabilitado");
        q.setParameter("estado", habilitado);
        q.setParameter("id", id);
        // Query q = em.createQuery("UPDATE Profesional p SET p.habilitado = " + estado + " WHERE p.id = " +  id);    
         /*q.setParameter("id", id);
         q.setParameter("estado", estado);  */
        q.executeUpdate();
    }//fin updateHabilitado

    @Override
    public Departamento findDepartamentoId(Departamento d, int op) throws Exception {
        Query q = null;

        System.out.println("idDepartamento: " + d.getId());//.getDocumentoIdentidad().getTipoDocumento().getDescripcion());
        if (op == 0) {
            //guardar
            q = em.createNamedQuery("Departamento.findAll");//Profesional.findByTipoNumeroDocumento");
        } else {
            //modificar
            q = em.createNamedQuery("Departamento.findCapturaDepartamentoId");
            q.setParameter("id", d.getId());
        }//fin if


        q.setParameter("id", d.getListaLocalidad());//.getDocumentoIdentidad().getTipoDocumento().getDescripcion());
        //  q.setParameter("numero", p.getDocumentoIdentidad().getNumero());  

        System.out.println("paso por aqui");
        try {
            q.getSingleResult();
            return this.find(d);
        } catch (NoResultException e) {
            System.out.println("paso por aqui");
            return this.find(d);

        }//fin carch

    }

    @Override
    public Departamento findCapturaDepartamentoId(Departamento d, int op) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean bFindByCodigoDepartamento(Departamento d, int op) throws Exception {
        Query q = null;


        if (op == 0) {
            //guardar
            q = em.createNamedQuery("Departamento.findByCodigoDepartamento");

        } else {
            //modificar
            q = em.createNamedQuery("Departamento.findByCodigoDepartamentoID");
            q.setParameter("id", d.getId());

        }//fin if


        q.setParameter("provincia", d.getProvincia());
        q.setParameter("codigo", d.getCodigo());


        try {
            q.getSingleResult();
            System.out.println(q.getSingleResult());
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    @Override
    public Boolean bFindByNombreDepartamento(Departamento d, int op) throws Exception {
        Query q = null;


        if (op == 0) {
            //guardar
            q = em.createNamedQuery("Departamento.ExisteEnProvincia");
            q.setParameter("idProvincia", d.getProvincia().getId());

        } else {
            //modificar
            q = em.createNamedQuery("Departamento.findByNombreDepartamentoID");
            q.setParameter("id", d.getId());


        }//fin if


        q.setParameter("nombre", d.getDescripcion());



        try {
            q.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    @Override
    public List<Departamento> findByProvincia(Long idProvincia) throws Exception {
        Query q = em.createNamedQuery("Departamento.findByProvincia");
        q.setParameter("idProvincia", idProvincia);
        return q.getResultList();
    }//fin findByProvincia

//    @Override
//    public String ObtenerIndec(Departamento dep) throws Exception {
//        
//        String indecProvincia = null;
//       Query q = em.createNamedQuery("Departamento.finByCodigoIndec");
//       Long id=dep.getProvincia().getId();
//       q.setParameter("id", id);
//      indecProvincia =((Provincia)q.getSingleResult()).getIndec()  ;
//         System.out.println("pasa el casteo "+ indecProvincia);
//       return indecProvincia.concat(dep.getIndec());
//    }
    @Override
    public List<Departamento> findByProvinciaBorrado(Long idProvincia, Boolean estado) throws Exception {
        Query q = em.createNamedQuery("Departamento.findByProvinciaBorrado");
        q.setParameter("idProvincia", idProvincia);
        q.setParameter("estado", estado);
        return q.getResultList();
    }

    @Override
    public List<Departamento> findByBorrado(Boolean estado) throws Exception {
        Query q = em.createNamedQuery("Departamento.findByBorrado");
        q.setParameter("estado", estado);
        return q.getResultList();
    }

    @Override
    public List<Departamento> findLike(String cadena, Boolean conBorrados) throws Exception {
        String sQuery;
        if (conBorrados) {
            sQuery = "SELECT d FROM Departamento d WHERE TRIM(LOWER(d.descripcion)) LIKE '%" + cadena.trim().toLowerCase() + "%'";
        } else {
            sQuery = "SELECT d FROM Departamento d WHERE d.borrado = false AND "
                    + "d.habilitado = true AND "
                    + "TRIM(LOWER(d.descripcion)) LIKE '%" + cadena.trim().toLowerCase() + "%'";
        }

        sQuery += " ORDER BY d.descripcion";
        Query q = em.createQuery(sQuery);

        return q.getResultList();
    }

    @Override
    public Departamento findByIdNombreDepartamento(Long id, String nombre) throws Exception {
        Query q = null;

        q = em.createNamedQuery("Departamento.findByIDNombreDepartamento");
        q.setParameter("id", id);
        q.setParameter("nombre", nombre);

        return ((Departamento) q.getSingleResult());
    }
    
}
