/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.CampoFacadeLocal;
import entidad.Campo;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author cris
 */
@Stateless
public class CampoRN implements CampoRNLocal {

    @EJB
    private CampoFacadeLocal campoFacadeLocal;

    @Override
    public void create(Campo cam) throws Exception {
        this.campoFacadeLocal.create(cam);
    }

    @Override
    public void remove(Campo cam) throws Exception {
       this.campoFacadeLocal.remove(cam);
    }

    @Override
    public void edit(Campo cam) throws Exception {
         this.campoFacadeLocal.edit(cam);
    }

    @Override
    public List<Campo> findAll() throws Exception {
return (this.campoFacadeLocal.findAll());
    }

    @Override
    public Campo findByDni(Integer dni) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void activate(Campo campo, Boolean bEstado) throws Exception {
      campoFacadeLocal.activate(campo, bEstado);
    }

    @Override
    public List<Campo> findAllActivo() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
