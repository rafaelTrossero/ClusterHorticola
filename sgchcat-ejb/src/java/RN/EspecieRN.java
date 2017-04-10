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
import recursos.cadenas;

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
        this.convertir_strings(e);
        this.validar(e, 0);
        this.especieFacadeLocal.create(e);
    }

    @Override
    public void edit(Especie e) throws Exception {
        this.convertir_strings(e);
        this.validar(e, 1);
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
    @Override
    public void activate(Especie especie, Boolean bEstado) throws Exception {
        especieFacadeLocal.activate(especie, bEstado);
    }

    @Override
    public List<Especie> findAllActivo() throws Exception {
        return (this.especieFacadeLocal.findAllActivo());
    }

    private void validar(Especie i, int op) throws Exception {

        if (especieFacadeLocal.bFindByNombreEspecie(i, op)) {
            throw new Exception("La especie "+i.getDescripcion() +" ya existe");
        }//fin if

    }

    private void convertir_strings(Especie p) {
        p.setDescripcion(cadenas.convertir(p.getDescripcion()));
    }
}
