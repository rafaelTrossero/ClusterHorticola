/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidad;

/**
 *
 * @author gio
 */
public enum tipoUsuario {
    
   
    ADMINISTRADOR("aDMINISTRADOR"),
    DOCENTE("DOCENTE"),
    ALUMNO("ALUMNO"),
    PROFESIONAL("PROFESIONAL");
    
    private String name;

    private tipoUsuario(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}


