/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.EspecieFacadeLocal;
import entidad.Especie;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author RafaTrossero
 */
@Stateless
public class EspecieRN implements EspecieRNLocal {

    @EJB
    private EspecieFacadeLocal especieFacadeLocal;

    @Override
    public void create(Especie e) throws Exception {
        this.especieFacadeLocal.create(e);
    }

    @Override
    public void edit(Especie e) throws Exception {
        this.especieFacadeLocal.edit(e);
    }

    @Override
    public void remove(Especie e) throws Exception {
        this.especieFacadeLocal.remove(e);
    }

    @Override
    public void habilitar(Especie e) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Especie> findAll() throws Exception {
        return (this.especieFacadeLocal.findAll());
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
