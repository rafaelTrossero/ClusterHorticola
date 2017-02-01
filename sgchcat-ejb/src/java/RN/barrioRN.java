/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RN;

import DAO.AuditoriaFacadeLocal;
import DAO.BarrioFacadeLocal;
import entidad.Barrio;
import entidad.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import recursos.cadenas;

/**
 *
 * @author cris
 */
@Stateless
public class barrioRN implements barrioRNLocal {

    
    @EJB
    private BarrioFacadeLocal barrioFacadeLocal;
   

    @Override
    public void create(Barrio b, Usuario usuario, String pantalla) throws Exception {
        /*if (!grupoFacadeLocal.bFindByPermiso(usuario.getGrupo().getId(), pantalla, TipoOperacion.crear)) {
            throw new Exception("No tiene permisos para realizar esta operación");
        }//fin if*/
        this.convertir_strings(b);
        this.validar(b, 0);
        barrioFacadeLocal.create(b);

        System.out.println("paso paso");
        //historial
        /*Auditoria audit = new Auditoria();
        audit.setTabla_op("Barrio");
        audit.setFecha(new Date());
        audit.setIdRegistro(String.valueOf(b.getId()));
        audit.setTipoOperacion(TipoOperacion.crear);
        audit.setUsuario(usuario);
        auditoriaFacadeLocal.create(audit);*/
    }

    @Override
    public void edit(Barrio b, Usuario usuario, String pantalla) throws Exception {
        /*if (!grupoFacadeLocal.bFindByPermiso(usuario.getGrupo().getId(), pantalla, TipoOperacion.modificar)) {
            throw new Exception("No tiene permisos para realizar esta operación");
        }//fin if*/
        this.convertir_strings(b);
        this.validar(b, 1);
        barrioFacadeLocal.edit(b);
        //historial
        /*Auditoria audit = new Auditoria();
        audit.setFecha(new Date());
        audit.setTabla_op("Barrio");
        audit.setIdRegistro(String.valueOf(b.getId()));
        audit.setTipoOperacion(TipoOperacion.modificar);
        audit.setUsuario(usuario);
        auditoriaFacadeLocal.create(audit);*/
    }

    @Override
    public void remove(Barrio b, Boolean bEstado, Usuario usuario, String pantalla) throws Exception {
        /*if (!grupoFacadeLocal.bFindByPermiso(usuario.getGrupo().getId(), pantalla, bEstado ? TipoOperacion.eliminar : TipoOperacion.recuperar)) {
            throw new Exception("No tiene permisos para realizar esta operación");
        }//fin if*/
        //        this.validar(b,2); 
        System.out.println("remove: " + bEstado);
        barrioFacadeLocal.updateBorrado(bEstado, b.getId());

        //historial
        /*Auditoria audit = new Auditoria();
        audit.setTabla_op("Barrio");
        audit.setIdRegistro(String.valueOf(b.getId()));
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
    public void habilitar(Barrio b, Boolean bEstado, Usuario usuario, String pantalla) throws Exception {
        //  b.setBorrado(true);
        /*if (!grupoFacadeLocal.bFindByPermiso(usuario.getGrupo().getId(), pantalla, bEstado ? TipoOperacion.habilitar : TipoOperacion.deshabilitar)) {
            throw new Exception("No tiene permisos para realizar esta operación");
        }//fin if*/

        barrioFacadeLocal.updateHabilitado(bEstado, b.getId());

        //historial
        /*Auditoria audit = new Auditoria();
        audit.setFecha(new Date());
        audit.setTabla_op("Barrio");
        audit.setIdRegistro(String.valueOf(b.getId()));
        audit.setUsuario(usuario);
        if (!bEstado) {
            audit.setTipoOperacion(TipoOperacion.deshabilitar);
        } else {
            audit.setTipoOperacion(TipoOperacion.habilitar);
        }


        auditoriaFacadeLocal.create(audit);*/
    }

    @Override
    public List<Barrio> findAll() throws Exception {
        System.out.println("pasa list barrio");
        return barrioFacadeLocal.findAll();

    }

    private void validar(Barrio b, int op) throws Exception {
        if(!cadenas.es_letras(b.getDescripcion())){
        throw new Exception("El nombre del Barrio deben ser caracteres alfabéticos");
    }

    } // fin validar

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private void convertir_strings(Barrio b) {
        b.setDescripcion(cadenas.convertir(b.getDescripcion()));
    }

    @Override
    public List<Barrio> findByLocalidad(Long idLocalidad) throws Exception {
        return this.barrioFacadeLocal.findByLocalidad(idLocalidad);
    }

  @Override
  public List<Barrio> findByLocalidadBorrado(Long idLocalidad, Boolean estado) throws Exception {
    return this.barrioFacadeLocal.findByLocalidadBorrado(idLocalidad, estado);
  }

  @Override
  public List<Barrio> findByBorrado(Boolean estado) throws Exception {
    return this.barrioFacadeLocal.findByBorrado(estado);
  }

  @Override
  public List<Barrio> findLike(String cadena, Boolean conBorrado) throws Exception {
    return this.barrioFacadeLocal.findLike(cadena, conBorrado);
  }
}
