/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;


import DAO.DepartamentoFacadeLocal;
import entidad.Auditoria;
import entidad.Departamento;
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
public class DepartamentoRN implements DepartamentoRNLocal {

    @EJB
    private DepartamentoFacadeLocal departamentoFacadeLocal;
    

    @Override
    public void create(Departamento d, Usuario usuario, String pantalla) throws Exception {
        /*if (!grupoFacadeLocal.bFindByPermiso(usuario.getGrupo().getId(), pantalla, TipoOperacion.crear)) {
            throw new Exception("No tiene permisos para realizar esta operación");
        }//fin if*/
        this.convertir_strings(d);
        
        this.validar(d, 0);
        departamentoFacadeLocal.create(d);
        //historial
        /*Auditoria audit = new Auditoria();
        audit.setFecha(new Date());
        audit.setTabla_op("Departamento");
        audit.setIdRegistro(String.valueOf(d.getId()));
        audit.setTipoOperacion(TipoOperacion.crear);
        audit.setUsuario(usuario);
        auditoriaFacadeLocal.create(audit);*/
    }

    @Override
    public void edit(Departamento d, Usuario usuario, String pantalla) throws Exception {
        /*if (!grupoFacadeLocal.bFindByPermiso(usuario.getGrupo().getId(), pantalla, TipoOperacion.modificar)) {
            throw new Exception("No tiene permisos para realizar esta operación");
        }//fin if*/
        this.convertir_strings(d);
        this.validar(d, 1);
        departamentoFacadeLocal.edit(d);
        //historial
        /*Auditoria audit = new Auditoria();
        audit.setFecha(new Date());
        audit.setTabla_op("Departamento");
        audit.setIdRegistro(String.valueOf(d.getId()));
        audit.setTipoOperacion(TipoOperacion.modificar);
        audit.setUsuario(usuario);
        auditoriaFacadeLocal.create(audit);*/
    }

    @Override
    public void remove(Departamento d, Boolean bEstado, Usuario usuario, String pantalla) throws Exception {
        /*if (!grupoFacadeLocal.bFindByPermiso(usuario.getGrupo().getId(), pantalla, bEstado ? TipoOperacion.eliminar : TipoOperacion.recuperar)) {
            throw new Exception("No tiene permisos para realizar esta operación");
        }//fin if*/

        departamentoFacadeLocal.updateBorrado(bEstado, d.getId());

        //historial
        /*Auditoria audit = new Auditoria();
        audit.setFecha(new Date());
        audit.setTabla_op("Provincia");
        audit.setIdRegistro(String.valueOf(d.getId()));
        audit.setUsuario(usuario);
        if (bEstado) {
            audit.setTipoOperacion(TipoOperacion.eliminar);
        } else {
            audit.setTipoOperacion(TipoOperacion.recuperar);
        }


        auditoriaFacadeLocal.create(audit);*/
    }

    @Override
    public void habilitar(Departamento d, Boolean bEstado, Usuario usuario, String pantalla) throws Exception {

        /*if (!grupoFacadeLocal.bFindByPermiso(usuario.getGrupo().getId(), pantalla, bEstado ? TipoOperacion.habilitar : TipoOperacion.deshabilitar)) {
            throw new Exception("No tiene permisos para realizar esta operación");
        }//fin if */
        //p.setBorrado(true);
        departamentoFacadeLocal.updateHabilitado(bEstado, d.getId());


        //historial
        /*Auditoria audit = new Auditoria();
        //  audit.setFecha(new Date());
        audit.setTabla_op("Departamento");
        audit.setIdRegistro(String.valueOf(d.getId()));

        if (!bEstado) {
            audit.setTipoOperacion(TipoOperacion.deshabilitar);
        } else {
            audit.setTipoOperacion(TipoOperacion.habilitar);
        }


        auditoriaFacadeLocal.create(audit);*/

    }//fin recuperar

    @Override
    public List<Departamento> findAll() throws Exception {
        return departamentoFacadeLocal.findAll();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private void validar(Departamento d, int op) throws Exception {
        //verifica si el código es menor o igual a cero
        if (d.getCodigo() <= 0) {
            throw new Exception("El código debe ser mayor o igual a cero");
        }

       
        //Valida si contine letras
        if(!cadenas.es_letras(d.getDescripcion())){
            throw new Exception("El nombre del Departamento deben ser caracteres alfabéticos");
        }

        //verificando duplicados  
        if (departamentoFacadeLocal.bFindByCodigoDepartamento(d, op)) {
            throw new Exception("Ya existe una Departamento con ese codigo");
        }//fin if*/
        if (departamentoFacadeLocal.bFindByNombreDepartamento(d, op)) {
            throw new Exception("Ya existe un Departamento con ese nombre");
        }//fin if
    }// fin validar

    private void convertir_strings(Departamento d) {
        d.setDescripcion(cadenas.convertir(d.getDescripcion()));
        
    }

    @Override
    public List<Departamento> findByProvincia(Long idProvincia) throws Exception {
        return this.departamentoFacadeLocal.findByProvincia(idProvincia);
    }

  @Override
  public List<Departamento> findByProvinciaBorrado(Long idProvincia, Boolean estado) throws Exception {
    return this.departamentoFacadeLocal.findByProvinciaBorrado(idProvincia, estado);
  }

  @Override
  public List<Departamento> findByBorrado(Boolean estado) throws Exception {
    return this.departamentoFacadeLocal.findByBorrado(estado);
  }

  @Override
  public List<Departamento> findLike(String cadena, Boolean conBorrado) throws Exception {
    return this.departamentoFacadeLocal.findLike(cadena, conBorrado);
  }
}// FIN CLASE
