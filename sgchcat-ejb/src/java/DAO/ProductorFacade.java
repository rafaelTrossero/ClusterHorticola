/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidad.Productor;
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
public class ProductorFacade extends AbstractFacade<Productor> implements ProductorFacadeLocal {

    @PersistenceContext(unitName = "ClusterHortDB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductorFacade() {
        super(Productor.class);
    }

    @Override
    public void activate(Productor productor, Boolean bEstado) {
        Query q = em.createNamedQuery("Productor.ActualizarEstado");
        q.setParameter("active", bEstado);
        q.setParameter("id", productor.getId());
        // Query q = em.createQuery("UPDATE Agenda p SET p.habilitado = " + estado + " WHERE p.id = " +  id);    
         /*q.setParameter("id", id);
         q.setParameter("estado", estado);  */
        q.executeUpdate();

    }

    @Override
    public Boolean bFindByDni(Productor p, int op) throws Exception {
        Query q = null;

        if (op == 0) {
            //guardar
            q = em.createNamedQuery("Productor.findByDni");

        } else {
            //modificar
            q = em.createNamedQuery("Productor.findByDniID");
            q.setParameter("id", p.getId());

        }//fin if

        q.setParameter("dni", p.getDni());

        try {
            q.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }

    }

    @Override
    public Boolean bFindByCuil(Productor p, int op) throws Exception {
        Query q = null;

        if (op == 0) {
            //guardar
            q = em.createNamedQuery("Productor.findByCuil");

        } else {
            //modificar
            q = em.createNamedQuery("Productor.findByCuilID");
            q.setParameter("id", p.getId());

        }//fin if

        q.setParameter("cuil", p.getCuil());

        try {
            q.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    @Override
    public List<Productor> findAllActivo() {
      try {
            Query q = em.createNamedQuery("Productor.SelectAlltrue");

            q.setParameter("active", true);

            return (List<Productor>) q.getResultList();

        } catch (Exception e) {
            return null;
        }
    
    }

}
