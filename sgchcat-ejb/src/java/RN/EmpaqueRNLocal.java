/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RN;

import entidad.Empaque;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cris
 */
@Local
public interface EmpaqueRNLocal {
    public void create(Empaque e) throws Exception;

    public void edit(Empaque e) throws Exception;

    public void remove (Empaque e) throws Exception;

    public void habilitar(Empaque e) throws Exception;

    public List<Empaque> findAll() throws Exception; 
}
