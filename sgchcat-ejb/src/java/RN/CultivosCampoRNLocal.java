/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import entidad.CultivosCampo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author RafaTrossero
 */
@Local
public interface CultivosCampoRNLocal {
    
    public void create(CultivosCampo e) throws Exception;

    public void edit(CultivosCampo e) throws Exception;

    public void remove (CultivosCampo e) throws Exception;

    public void habilitar(CultivosCampo e) throws Exception;

    public List<CultivosCampo> findAll() throws Exception; 
}
