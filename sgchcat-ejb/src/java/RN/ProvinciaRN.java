/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;


import DAO.ProvinciaFacadeLocal;
import entidad.Auditoria;
import entidad.Provincia;

import entidad.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import recursos.cadenas;

/**
 *
 * @author Juan Pablo
 */
@Stateless
public class ProvinciaRN implements ProvinciaRNLocal {

    @EJB
    private ProvinciaFacadeLocal provinciaFacadeLocal;
  
    @Override
    public void create(Provincia p, Usuario usuario, String pantalla) throws Exception {
        /*if (!grupoFacadeLocal.bFindByPermiso(usuario.getGrupo().getId(), pantalla, TipoOperacion.crear)) {
         throw new Exception("No tiene permisos para realizar esta operación");
         }//fin if*/

        this.convertir_strings(p);
        this.validar(p, 0);
        provinciaFacadeLocal.create(p);
        //historial
    /*Auditoria audit = new Auditoria();
         audit.setFecha(new Date());
         audit.setTabla_op("Provincia");
         audit.setIdRegistro(String.valueOf(p.getId()));
         audit.setTipoOperacion(TipoOperacion.crear);
         audit.setUsuario(usuario);
    
         auditoriaFacadeLocal.create(audit);*/
    }

    @Override
    public void edit(Provincia p, Usuario usuario, String pantalla) throws Exception {
        /*if (!grupoFacadeLocal.bFindByPermiso(usuario.getGrupo().getId(), pantalla, TipoOperacion.modificar)) {
         throw new Exception("No tiene permisos para realizar esta operación");
         }//fin if*/

        this.convertir_strings(p);
        this.validar(p, 1);
        provinciaFacadeLocal.edit(p);
        //historial
    /*Auditoria audit = new Auditoria();
         audit.setFecha(new Date());
         audit.setTabla_op("Provincia");
         audit.setIdRegistro(String.valueOf(p.getId()));
         audit.setTipoOperacion(TipoOperacion.modificar);
         audit.setUsuario(usuario);
    
         auditoriaFacadeLocal.create(audit);*/
    }

    @Override
    public void remove(Provincia p, Boolean bEstado, Usuario usuario, String pantalla) throws Exception {
        /*if (bEstado) {
         if (!grupoFacadeLocal.bFindByPermiso(usuario.getGrupo().getId(), pantalla, TipoOperacion.eliminar)) {
         throw new Exception("No tiene permisos para realizar esta operación");
         }//fin if
         }
         else {
         if (!grupoFacadeLocal.bFindByPermiso(usuario.getGrupo().getId(), pantalla, TipoOperacion.recuperar)) {
         throw new Exception("No tiene permisos para realizar esta operación");
         }//fin if
         }*/

        System.out.println(" estado:" + bEstado + "provincia:" + p.getId());
        provinciaFacadeLocal.updateBorrado(bEstado, p.getId());
        //historial
    /*Auditoria audit = new Auditoria();
         audit.setFecha(new Date());
         audit.setTabla_op("Provincia");
         audit.setIdRegistro(String.valueOf(p.getId()));
         audit.setUsuario(usuario);
    
         if (bEstado) {
         audit.setTipoOperacion(TipoOperacion.eliminar);
         }
         else {
         audit.setTipoOperacion(TipoOperacion.recuperar);
         }
    
         auditoriaFacadeLocal.create(audit);*/
    } //fin remove

    @Override
    public void habilitar(Provincia p, Boolean bEstado, Usuario usuario, String pantalla) throws Exception {
        /*if (!bEstado) {
         if (!grupoFacadeLocal.bFindByPermiso(usuario.getGrupo().getId(), pantalla, TipoOperacion.deshabilitar)) {
         throw new Exception("No tiene permisos para realizar esta operación");
         }//fin if
         }
         else {
         if (!grupoFacadeLocal.bFindByPermiso(usuario.getGrupo().getId(), pantalla, TipoOperacion.habilitar)) {
         throw new Exception("No tiene permisos para realizar esta operación");
         }//fin if
         }*/

        //p.setBorrado(true);
        provinciaFacadeLocal.updateHabilitado(bEstado, p.getId());

        //historial
    /*Auditoria audit = new Auditoria();
         audit.setFecha(new Date());
         audit.setTabla_op("Provincia");
         audit.setIdRegistro(String.valueOf(p.getId()));
    
         if (!bEstado) {
         audit.setTipoOperacion(TipoOperacion.deshabilitar);
         }
         else {
         audit.setTipoOperacion(TipoOperacion.habilitar);
         }
         audit.setUsuario(usuario);
    
    
         auditoriaFacadeLocal.create(audit);*/

    }//fin recuperar

    @Override
    public List<Provincia> findAll() throws Exception {
        return provinciaFacadeLocal.findAll();
    }

    private void validar(Provincia p, int op) throws Exception {
        //Valida si contine letras
        if (!cadenas.es_letras(p.getDescripcion())) {
            throw new Exception("El nombre de la Provincia deben ser caracteres alfabéticos");
        }
        if (provinciaFacadeLocal.bFindByNombreProvincia(p, op)) {
            throw new Exception("Ya existe un provincia con ese nombre");
        }//fin if

    }//fin validar

    private void convertir_strings(Provincia p) {
        p.setDescripcion(cadenas.convertir(p.getDescripcion()));
    }

    @Override
    public List<Provincia> findByPais(Long idPais) throws Exception {
        return provinciaFacadeLocal.findByPais(idPais);
    }

    @Override
    public List<Provincia> findByBorrado(Boolean estado) throws Exception {
        return provinciaFacadeLocal.findByBorrado(estado);
    }

    @Override
    public List<Provincia> findByPaisBorrado(Long idPais, Boolean estado) throws Exception {
        return provinciaFacadeLocal.findByPaisBorrado(idPais, estado);
    }

    @Override
    public List<Provincia> findLike(String cadena, Boolean conBorrado) throws Exception {
        return this.provinciaFacadeLocal.findLike(cadena, conBorrado);
    }
}//FIN CLASE

