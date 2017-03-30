/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidad.Campo;
import entidad.CampoProductor;
import entidad.Productor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    @Override
    public CampoProductor buscarCampoProductor(Campo campo, Productor productor) {
        try {
            Query q = em.createNamedQuery("CampoProductor.findByCampoProductor");

            q.setParameter("campo", campo);
            q.setParameter("productor", productor);

            return (CampoProductor) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

}
