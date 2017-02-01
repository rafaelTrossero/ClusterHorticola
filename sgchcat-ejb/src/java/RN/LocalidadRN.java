/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;


import DAO.LocalidadFacadeLocal;
import entidad.Auditoria;
import entidad.Localidad;
import entidad.Usuario;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import recursos.cadenas;

/**
 *
 * @author bcasas
 */
@Stateless
public class LocalidadRN implements LocalidadRNLocal {

    @EJB
    private LocalidadFacadeLocal localidadFacadeLocal;
    

    @Override
    public void create(Localidad localidad, Usuario usuario, String pantalla) throws Exception {
        /*if(!grupoFacadeLocal.bFindByPermiso(usuario.getGrupo().getId(),pantalla, TipoOperacion.crear)){
         throw new Exception("No tiene permisos para realizar esta operación");
         }//fin if*/

        this.convertir_strings(localidad);
        this.validar(localidad, 0);
        localidadFacadeLocal.create(localidad);
        //historial
        /*Auditoria audit = new Auditoria();
         audit.setTabla_op("Localidad");
         audit.setIdRegistro(String.valueOf(localidad.getId()));
         audit.setTipoOperacion(TipoOperacion.crear);
         audit.setFecha(new Date());
         audit.setUsuario(usuario);
         auditoriaFacadeLocal.create(audit);*/
    }

    @Override
    public void edit(Localidad localidad, Usuario usuario, String pantalla) throws Exception {

        /*if (!grupoFacadeLocal.bFindByPermiso(usuario.getGrupo().getId(), pantalla, TipoOperacion.modificar)) {
         throw new Exception("No tiene permisos para realizar esta operación");
         }//fin if*/
        this.convertir_strings(localidad);
        this.validar(localidad, 1);
        localidadFacadeLocal.edit(localidad);

        //historial
        /*Auditoria audit = new Auditoria();
         audit.setFecha(new Date());
         audit.setTabla_op("Localidad");
         audit.setIdRegistro(String.valueOf(localidad.getId()));
         audit.setTipoOperacion(TipoOperacion.modificar);
         audit.setUsuario(usuario);
         auditoriaFacadeLocal.create(audit);*/
    }

    @Override
    public void remove(Localidad localidad, Boolean bEstado, Usuario usuario, String pantalla) throws Exception {

        /*if (!grupoFacadeLocal.bFindByPermiso(usuario.getGrupo().getId(), pantalla, bEstado ? TipoOperacion.eliminar : TipoOperacion.recuperar)) {
         throw new Exception("No tiene permisos para realizar esta operación");
         }//fin if*/
        // this.validar(localidad);
        localidadFacadeLocal.updateBorrado(bEstado, localidad.getId());
        //historial
        /*Auditoria audit = new Auditoria();
         audit.setTabla_op("Localidad");
         audit.setIdRegistro(String.valueOf(localidad.getId()));
         audit.setFecha(new Date());
         audit.setUsuario(usuario);
         if (bEstado) {
         audit.setTipoOperacion(TipoOperacion.eliminar);
         } else {
         audit.setTipoOperacion(TipoOperacion.recuperar);
         }


         auditoriaFacadeLocal.create(audit);*/
    }

    @Override
    public void habilitar(Localidad localidad, Boolean bEstado, Usuario usuario, String pantalla) throws Exception {

        /*if (!grupoFacadeLocal.bFindByPermiso(usuario.getGrupo().getId(), pantalla, bEstado ? TipoOperacion.habilitar : TipoOperacion.deshabilitar)) {
         throw new Exception("No tiene permisos para realizar esta operación");
         }//fin if*/
        //p.setBorrado(true);
        localidadFacadeLocal.updateHabilitado(bEstado, localidad.getId());

        //historial
        /*Auditoria audit = new Auditoria();
         audit.setFecha(new Date());
         audit.setUsuario(usuario);
         audit.setTabla_op("Localidad");
         audit.setIdRegistro(String.valueOf(localidad.getId()));

         if (!bEstado) {
         audit.setTipoOperacion(TipoOperacion.deshabilitar);
         } else {
         audit.setTipoOperacion(TipoOperacion.habilitar);
         }


         auditoriaFacadeLocal.create(audit);*/
    }//fin recuperar

    @Override
    public List<Localidad> findAll() throws Exception {
        return this.localidadFacadeLocal.findAll();

    }

    private void validar(Localidad localidad, int op) throws Exception {
        //verifica si el código es menor o igual a cero
        if (localidad.getCodigo() <= 0) {
            throw new Exception("El código debe ser mayor o igual a cero");
        }

       

        //Valida si contine letras
        if (!cadenas.es_letras(localidad.getDescripcion())) {
            throw new Exception("El nombre de la Localidad deben ser caracteres alfabéticos");
        }

        //verificando duplicados  
        if (localidadFacadeLocal.bFindByCodigoLocalidad(localidad, op)) {
            throw new Exception("Ya existe una Localidad con ese codigo");
        }//fin if*/
        if (localidadFacadeLocal.bFindByNombreLocalidad(localidad, op)) {
            throw new Exception("Ya existe un Localidad con ese nombre");
        }//fin if
//                 if (!localidadFacadeLocal.bFindByCodigoPostal(localidad, op)) {
//                    throw new Exception("NO CAMPO NULO");
//                }//fin if

        //}//fin switch
    }

    @Override
    public List<Localidad> findLocalidadesSinAsociarAps() {
        return this.localidadFacadeLocal.findLocalidadesSinAsociarAps();
    }

    private void convertir_strings(Localidad localidad) {
        localidad.setDescripcion(cadenas.convertir(localidad.getDescripcion()));
        localidad.setCodigoPostal(cadenas.convertir(localidad.getCodigoPostal()));
        
    }

    @Override
    public List<Localidad> findByDepartamento(Long idDepartamento) throws Exception {
        return this.localidadFacadeLocal.findByDepartamento(idDepartamento);
    }

    @Override
    public List<Localidad> findByDepartamentoBorrado(Long idDepartamento, Boolean borrado) throws Exception {
        return this.localidadFacadeLocal.findByDepartamentoBorrado(idDepartamento, borrado);
    }

    @Override
    public List<Localidad> findByBorrado(Boolean borrado) throws Exception {
        return this.localidadFacadeLocal.findByBorrado(borrado);
    }

    @Override
    public List<Localidad> findLike(String cadena, Boolean conBorrado) throws Exception {
        return this.localidadFacadeLocal.findLike(cadena, conBorrado);
    }
}
