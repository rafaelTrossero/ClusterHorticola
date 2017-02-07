/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entidad.Tenencia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cris
 */
@Local
public interface TenenciaFacadeLocal {

    void create(Tenencia tenencia);

    void edit(Tenencia tenencia);

    void remove(Tenencia tenencia);

    Tenencia find(Object id);

    List<Tenencia> findAll();

    List<Tenencia> findRange(int[] range);

    int count();
    
}
