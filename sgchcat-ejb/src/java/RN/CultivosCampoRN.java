/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.CultivosCampoFacadeLocal;
import entidad.CultivosCampo;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author RafaTrossero
 */
@Stateless
public class CultivosCampoRN implements CultivosCampoRNLocal {

    @EJB
    private CultivosCampoFacadeLocal cultivosCampoFacadeLocal;

    @Override
    public void create(CultivosCampo e) throws Exception {
        this.cultivosCampoFacadeLocal.create(e);
    }

    @Override
    public void edit(CultivosCampo e) throws Exception {
        this.cultivosCampoFacadeLocal.edit(e);
    }

    @Override
    public void remove(CultivosCampo e) throws Exception {
        this.cultivosCampoFacadeLocal.remove(e);
    }

    @Override
    public void habilitar(CultivosCampo e) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CultivosCampo> findAll() throws Exception {
        return (this.cultivosCampoFacadeLocal.findAll());
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
