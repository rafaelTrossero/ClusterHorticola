/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidad.Variedad;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author RafaTrossero
 */
@Stateless
public class VariedadFacade extends AbstractFacade<Variedad> implements VariedadFacadeLocal {

    @PersistenceContext(unitName = "ClusterHortDB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VariedadFacade() {
        super(Variedad.class);
    }

    @Override
    public List<Variedad> findByEspecie(Long idEspecie) throws Exception {
        Query q = em.createNamedQuery("Variedad.findByEspecie");
        q.setParameter("idEspecie", idEspecie);
        q.setParameter("active", true);
        return q.getResultList();
    }

    @Override
    public void activate(Variedad variedad, Boolean bEstado) {
        Query q = em.createNamedQuery("Especie.ActualizarEstado");
        q.setParameter("active", bEstado);
        q.setParameter("id", variedad.getId());
        // Query q = em.createQuery("UPDATE Agenda p SET p.habilitado = " + estado + " WHERE p.id = " +  id);    
         /*q.setParameter("id", id);
         q.setParameter("estado", estado);  */
        q.executeUpdate();
    }

    @Override
    public List<Variedad> findAllActivo() {
    try {
            Query q = em.createNamedQuery("Variedad.SelectAlltrue");

            q.setParameter("active", true);

            return (List<Variedad>) q.getResultList();

        } catch (Exception e) {
            return null;
        }
    
    }

    @Override
    public Boolean bFindByNombreVariedad(Variedad p, int op) throws Exception {
       Query q = null;


        if (op == 0) {
            //guardar
            q = em.createNamedQuery("Variedad.findByNombreVariedad");

        } else {
            //modificar
            q = em.createNamedQuery("Variedad.findByNombreVariedadID");
            q.setParameter("id", p.getId());


        }//fin if

        
        q.setParameter("nombre", p.getDescripcion());
        q.setParameter("especie", p.getEspecie());


        try {
            q.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

}
