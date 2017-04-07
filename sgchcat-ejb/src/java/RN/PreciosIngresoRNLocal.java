/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import entidad.PreciosIngreso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USUARIO
 */
@Local
public interface PreciosIngresoRNLocal {
     public void create(PreciosIngreso e) throws Exception;

    public void edit(PreciosIngreso e) throws Exception;

    public void remove (PreciosIngreso e) throws Exception;

    public void habilitar(PreciosIngreso e) throws Exception;

    public List<PreciosIngreso> findAll() throws Exception;
    public void activate(PreciosIngreso precioIngreso, Boolean bEstado) throws Exception;
}
