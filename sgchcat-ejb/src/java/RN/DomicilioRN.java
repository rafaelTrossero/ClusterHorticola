/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.DomicilioFacadeLocal;
import entidad.Domicilio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author RafaTrossero
 */
@Stateless
public class DomicilioRN implements DomicilioRNLocal {

    @EJB
    private DomicilioFacadeLocal domicilioFacadeLocal;
    
    @Override
    public void create(Domicilio d) throws Exception {
   this.domicilioFacadeLocal.create(d);
   }

    @Override
    public void edit(Domicilio d) throws Exception {
    this.domicilioFacadeLocal.edit(d);
    }

    @Override
    public void remove(Domicilio d) throws Exception {
    this.domicilioFacadeLocal.remove(d);
    }

    @Override
    public void habilitar(Domicilio d) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Domicilio> findAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
