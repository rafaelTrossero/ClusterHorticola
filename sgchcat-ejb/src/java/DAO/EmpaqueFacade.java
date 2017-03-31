/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidad.Empaque;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author cris
 */
@Stateless
public class EmpaqueFacade extends AbstractFacade<Empaque> implements EmpaqueFacadeLocal {

    @PersistenceContext(unitName = "ClusterHortDB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpaqueFacade() {
        super(Empaque.class);
    }

    @Override
    public void activate(Empaque emp, Boolean bEstado) {
        Query q = em.createNamedQuery("Empaque.ActualizarEstado");
        q.setParameter("active", bEstado);
        q.setParameter("id", emp.getId());
        // Query q = em.createQuery("UPDATE Agenda p SET p.habilitado = " + estado + " WHERE p.id = " +  id);    
         /*q.setParameter("id", id);
         q.setParameter("estado", estado);  */
        q.executeUpdate();

    }

}
