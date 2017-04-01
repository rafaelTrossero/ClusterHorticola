/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.ProductorFacadeLocal;
import entidad.Productor;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author RafaTrossero
 */
@Stateless
public class ProductorRN implements ProductorRNLocal {
    
    @EJB
    private ProductorFacadeLocal productorFacadeLocal;
    

    @Override
    public void create(Productor pro) throws Exception {
        this.productorFacadeLocal.create(pro);
    }

    @Override
    public void remove(Productor pro) throws Exception {
    this.productorFacadeLocal.remove(pro);
    }

    @Override
    public void edit(Productor pro) throws Exception {
    this.productorFacadeLocal.edit(pro);
    }

    @Override
    public List<Productor> findAll() throws Exception {
    return (this.productorFacadeLocal.findAll());
    }

    @Override
    public Productor findByDni(Integer dni) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void activate(Productor productor, Boolean bEstado) throws Exception {
   productorFacadeLocal.activate(productor, bEstado);
    }

    @Override
    public List<Productor> findAllActivo() throws Exception {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
