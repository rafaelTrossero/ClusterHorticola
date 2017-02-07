/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RN;

import DAO.TenenciaFacadeLocal;
import entidad.Tenencia;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author cris
 */
@Stateless
public class TenenciaRN implements TenenciaRNLocal {

     @EJB
    private TenenciaFacadeLocal tenenciaFacadeLocal;
    @Override
    public void create(Tenencia tenencia) throws Exception {
     this.tenenciaFacadeLocal.create(tenencia);
    }

    @Override
    public void remove(Tenencia tenencia) throws Exception {
      this.tenenciaFacadeLocal.remove(tenencia);
    }

    @Override
    public void edit(Tenencia tenencia) throws Exception {
    this.tenenciaFacadeLocal.edit(tenencia);
    }

    @Override
    public List<Tenencia> findAll() throws Exception {
  return(this.tenenciaFacadeLocal.findAll());
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
