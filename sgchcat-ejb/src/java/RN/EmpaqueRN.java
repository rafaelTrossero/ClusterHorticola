/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.EmpaqueFacadeLocal;
import entidad.Empaque;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author cris
 */
@Stateless
public class EmpaqueRN implements EmpaqueRNLocal {

    @EJB
    private EmpaqueFacadeLocal empaqueFacadeLocal;

    @Override
    public void create(Empaque e) throws Exception {
        this.empaqueFacadeLocal.create(e);
    }

    @Override
    public void edit(Empaque e) throws Exception {
        this.empaqueFacadeLocal.edit(e);
    }

    @Override
    public void remove(Empaque e) throws Exception {
        this.empaqueFacadeLocal.remove(e);
    }

    @Override
    public void habilitar(Empaque e) throws Exception {
   
    }

    @Override
    public List<Empaque> findAll() throws Exception {
        return (this.empaqueFacadeLocal.findAll());
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
