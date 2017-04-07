/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.PreciosIngresoFacadeLocal;
import entidad.PreciosIngreso;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author USUARIO
 */
@Stateless
public class PreciosIngresoRN implements PreciosIngresoRNLocal {

    @EJB
    private PreciosIngresoFacadeLocal preciosIngresoFacadeLocal;
    
    @Override
    public void create(PreciosIngreso e) throws Exception {
        this.preciosIngresoFacadeLocal.create(e);
    }

    @Override
    public void edit(PreciosIngreso e) throws Exception {
        this.preciosIngresoFacadeLocal.edit(e);
    }

    @Override
    public void remove(PreciosIngreso e) throws Exception {
        this.preciosIngresoFacadeLocal.remove(e);
    }

    @Override
    public void habilitar(PreciosIngreso e) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PreciosIngreso> findAll() throws Exception {
        return(this.preciosIngresoFacadeLocal.findAll());
    }

    @Override
    public void activate(PreciosIngreso precioIngreso, Boolean bEstado) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
