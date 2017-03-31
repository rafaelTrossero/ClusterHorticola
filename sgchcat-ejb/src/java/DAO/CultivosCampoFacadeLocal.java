/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidad.CultivosCampo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author RafaTrossero
 */
@Local
public interface CultivosCampoFacadeLocal {

    void create(CultivosCampo cultivosCampo);

    void edit(CultivosCampo cultivosCampo);

    void remove(CultivosCampo cultivosCampo);

    CultivosCampo find(Object id);

    List<CultivosCampo> findAll();

    List<CultivosCampo> findRange(int[] range);

    int count();
    
}
