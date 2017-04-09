/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidad.Especie;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author RafaTrossero
 */
@Stateless
public class EspecieFacade extends AbstractFacade<Especie> implements EspecieFacadeLocal {
    @PersistenceContext(unitName = "ClusterHortDB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EspecieFacade() {
        super(Especie.class);
    }

    @Override
    public void activate(Especie especie, Boolean bEstado) {
    Query q = em.createNamedQuery("Especie.ActualizarEstado");
        q.setParameter("active", bEstado);
        q.setParameter("id", especie.getId());
        // Query q = em.createQuery("UPDATE Agenda p SET p.habilitado = " + estado + " WHERE p.id = " +  id);    
         /*q.setParameter("id", id);
         q.setParameter("estado", estado);  */
        q.executeUpdate();
    }

    @Override
    public List<Especie> findAllActivo() {
    try {
            Query q = em.createNamedQuery("Especie.SelectAlltrue");

            q.setParameter("active", true);

            return (List<Especie>) q.getResultList();

        } catch (Exception e) {
            return null;
        }
    }
    
    }
    
