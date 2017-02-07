/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entidad.CampoProductor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author cris
 */
@Stateless
public class CampoProductorFacade extends AbstractFacade<CampoProductor> implements CampoProductorFacadeLocal {
    @PersistenceContext(unitName = "ClusterHortDB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CampoProductorFacade() {
        super(CampoProductor.class);
    }
    
}
