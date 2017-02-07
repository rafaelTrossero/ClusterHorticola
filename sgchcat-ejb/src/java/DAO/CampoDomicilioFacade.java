/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entidad.CampoDomicilio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author cris
 */
@Stateless
public class CampoDomicilioFacade extends AbstractFacade<CampoDomicilio> implements CampoDomicilioFacadeLocal {
    @PersistenceContext(unitName = "ClusterHortDB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CampoDomicilioFacade() {
        super(CampoDomicilio.class);
    }
    
}
