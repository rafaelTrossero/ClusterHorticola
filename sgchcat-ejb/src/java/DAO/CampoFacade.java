/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entidad.Campo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author cris
 */
@Stateless
public class CampoFacade extends AbstractFacade<Campo> implements CampoFacadeLocal {
    @PersistenceContext(unitName = "ClusterHortDB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CampoFacade() {
        super(Campo.class);
    }
    
}
