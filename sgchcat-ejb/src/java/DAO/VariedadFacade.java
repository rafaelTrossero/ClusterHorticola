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
        return q.getResultList();
    }
    
}
