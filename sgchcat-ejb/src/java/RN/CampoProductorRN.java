/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RN;

import DAO.CampoProductorFacadeLocal;
import entidad.CampoProductor;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author cris
 */
@Stateless
public class CampoProductorRN implements CampoProductorRNLocal {
     @EJB
    private CampoProductorFacadeLocal campoProductorFacadeLocal;
    @Override
    public void create(CampoProductor cam) throws Exception {
    this.campoProductorFacadeLocal.create(cam);
    }

    @Override
    public void remove(CampoProductor cam) throws Exception {
     this.campoProductorFacadeLocal.remove(cam);
    }

    @Override
    public void edit(CampoProductor cam) throws Exception {
     this.campoProductorFacadeLocal.edit(cam);
    }

    @Override
    public List<CampoProductor> findAll() throws Exception {
    return(this.campoProductorFacadeLocal.findAll());
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
