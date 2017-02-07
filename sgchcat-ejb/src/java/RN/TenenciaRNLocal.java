/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RN;

import entidad.Campo;
import entidad.Tenencia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cris
 */
@Local
public interface TenenciaRNLocal {
    void create(Tenencia tenencia) throws Exception;

    void remove(Tenencia tenencia) throws Exception;

    void edit(Tenencia tenencia) throws Exception;

    List<Tenencia> findAll() throws Exception;
}
