/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RN;

import DAO.CampoDomicilioFacadeLocal;
import entidad.CampoDomicilio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author cris
 */
@Stateless
public class CampoDomicilioRN implements CampoDomicilioRNLocal {
    @EJB
    private CampoDomicilioFacadeLocal campoDomicilioFacadeLocal;

    @Override
    public void create(CampoDomicilio cam) throws Exception {
     this.campoDomicilioFacadeLocal.create(cam);
    }

    @Override
    public void remove(CampoDomicilio cam) throws Exception {
 this.campoDomicilioFacadeLocal.remove(cam);
    }

    @Override
    public void edit(CampoDomicilio cam) throws Exception {
 this.campoDomicilioFacadeLocal.edit(cam);
    }

    @Override
    public List<CampoDomicilio> findAll() throws Exception {

    return (this.campoDomicilioFacadeLocal.findAll());
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
