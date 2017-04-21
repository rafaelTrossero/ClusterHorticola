/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidad.IngresoMercado;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author RafaTrossero
 */
@Local
public interface IngresoMercadoFacadeLocal {

    void create(IngresoMercado ingresoMercado);

    void edit(IngresoMercado ingresoMercado);

    void remove(IngresoMercado ingresoMercado);

    IngresoMercado find(Object id);

    List<IngresoMercado> findAll();

    List<IngresoMercado> findRange(int[] range);

    int count();
     public void activate(IngresoMercado emp, Boolean bEstado);
}
