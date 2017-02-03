/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import entidad.Domicilio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author RafaTrossero
 */
@Local
public interface DomicilioRNLocal {
    
    public void create(Domicilio d) throws Exception;
    public void edit(Domicilio d) throws Exception;
    public void remove(Domicilio d) throws Exception;
    public void habilitar(Domicilio d) throws Exception;
    public List<Domicilio> findAll() throws Exception;
}
