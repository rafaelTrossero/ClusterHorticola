/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;


import DAO.PaisFacadeLocal;
import entidad.Auditoria;
import entidad.Pais;
import entidad.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import recursos.cadenas;

/**
 *
 * @author AFerSor
 */
@Stateless
public class PaisRN implements PaisRNLocal {

    @EJB
    private PaisFacadeLocal paisDAOFacadeLocal;
   

    @Override
    public void create(Pais p, Usuario usuario, String pantalla) throws Exception {
        /*if (!grupoFacadeLocal.bFindByPermiso(usuario.getGrupo().getId(), pantalla, TipoOperacion.crear)) {
         throw new Exception("No tiene permisos para realizar esta operación");
         }//fin if*/

        this.convertir_strings(p);
        this.validar(p, 0);
        paisDAOFacadeLocal.create(p);
        //historial
    /*Auditoria audit = new Auditoria();
         audit.setFecha(new Date());
         audit.setTabla_op("Pais");
         audit.setIdRegistro(String.valueOf(p.getId()));
         audit.setTipoOperacion(TipoOperacion.crear);
         audit.setUsuario(usuario);
    
         auditoriaFacadeLocal.create(audit);*/
    }

    @Override
    public void edit(Pais p, Usuario usuario, String pantalla) throws Exception {
        /*if (!grupoFacadeLocal.bFindByPermiso(usuario.getGrupo().getId(), pantalla, TipoOperacion.modificar)) {
         throw new Exception("No tiene permisos para realizar esta operación");
         }//fin if*/

        this.convertir_strings(p);
        this.validar(p, 1);
        paisDAOFacadeLocal.edit(p);
        //historial
    /*Auditoria audit = new Auditoria();
         audit.setFecha(new Date());
         audit.setTabla_op("Pais");
         audit.setIdRegistro(String.valueOf(p.getId()));
         audit.setTipoOperacion(TipoOperacion.modificar);
         audit.setUsuario(usuario);
    
         auditoriaFacadeLocal.create(audit);*/
    }

    @Override
    public void remove(Pais p, Boolean bEstado, Usuario usuario, String pantalla) throws Exception {
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

        paisDAOFacadeLocal.updateBorrado(bEstado, p.getId());
        //historial
    /*Auditoria audit = new Auditoria();
         audit.setFecha(new Date());
         audit.setTabla_op("Pais");
         audit.setIdRegistro(String.valueOf(p.getId()));
         audit.setUsuario(usuario);
         if (bEstado) {
         audit.setTipoOperacion(TipoOperacion.eliminar);
         }
         else {
         audit.setTipoOperacion(TipoOperacion.recuperar);
         }

         auditoriaFacadeLocal.create(audit);*/
    } // fin remove

    @Override
    public void habilitar(Pais p, Boolean bEstado, Usuario usuario, String pantalla) throws Exception {
        /*if (bEstado) {
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
        paisDAOFacadeLocal.updateHabilitado(bEstado, p.getId());

        //historial
    /*Auditoria audit = new Auditoria();
         audit.setFecha(new Date());
         audit.setTabla_op("Pais");
         audit.setIdRegistro(String.valueOf(p.getId()));
         audit.setUsuario(usuario);

         if (!bEstado) {
         audit.setTipoOperacion(TipoOperacion.deshabilitar);
         }
         else {
         audit.setTipoOperacion(TipoOperacion.habilitar);
         }


         auditoriaFacadeLocal.create(audit);*/
    }//fin recuperar

    @Override
    public List<Pais> findAll() throws Exception {
        return paisDAOFacadeLocal.findAll();
    }//fin findALL

    private void validar(Pais p, int op) throws Exception {
        //verifica si el código es menor o igual a cero
        /*if (p.getCodigo() <= 0) {
         throw new Exception("El código debe ser mayor o igual a cero");
         }*/

        //verifica si es una línea en blanco
        if (p.getDescripcion().trim().length() == 0) {
            throw new Exception("El nombre del país no puede estar vacio");
        }
        //Valida si contine letras
        if (!cadenas.es_letras(p.getDescripcion())) {
            throw new Exception("El nombre del País deben ser caracteres alfabéticos");
        }
        if (!cadenas.es_letras(p.getNacionalidad())) {
            throw new Exception("El nombre de la Nacionalidad deben ser caracteres alfabéticos");
        }
        //verificando duplicados  
        if (paisDAOFacadeLocal.bFindByCodigoPais(p, op)) {
            throw new Exception("Ya existe un país con ese código");
        }//fin if*/
        if (paisDAOFacadeLocal.bFindByNombrePais(p, op)) {
            throw new Exception("Ya existe un país con ese nombre");
        }//fin if

    }//fin validar

    private void convertir_strings(Pais p) {
        p.setDescripcion(cadenas.convertir(p.getDescripcion()));
        p.setNacionalidad(cadenas.convertir(p.getNacionalidad()));
    }

    @Override
    public List<Pais> findLike(String cadena, Boolean conBorrados) throws Exception {
        return paisDAOFacadeLocal.findLike(cadena, conBorrados);
    }
}//FIN CLASE
