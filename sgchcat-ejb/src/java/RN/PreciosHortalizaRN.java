/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.PreciosHortalizaFacadeLocal;
import entidad.PreciosHortaliza;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author RafaTrossero
 */
@Stateless
public class PreciosHortalizaRN implements PreciosHortalizaRNLocal {

    @EJB
    private PreciosHortalizaFacadeLocal preciosHortalizaFacadeLocal;
    
    @Override
    public void create(PreciosHortaliza p) throws Exception {
   this.preciosHortalizaFacadeLocal.create(p);
    }

    @Override
    public void remove(PreciosHortaliza p) throws Exception {
   this.preciosHortalizaFacadeLocal.remove(p);
    }

    @Override
    public void edit(PreciosHortaliza p) throws Exception {
    this.preciosHortalizaFacadeLocal.edit(p);
    }

    @Override
    public List<PreciosHortaliza> findAll() throws Exception {
   return(this.preciosHortalizaFacadeLocal.findAll());
    }

    @Override
    public void activate(PreciosHortaliza p, Boolean bEstado) throws Exception {
       preciosHortalizaFacadeLocal.activate(p, bEstado);

    }

    @Override
    public List<PreciosHortaliza> findAllActivo() throws Exception {
     return(this.preciosHortalizaFacadeLocal.findAllActivo());
    
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
