/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author USUARIO
 */
@ManagedBean(name="fechaActualBean")
@ApplicationScoped
public class FechaActualBean implements Serializable{

    private String fechaActual;
    private Date dFechaActual;
    /** Creates a new instance of FechaActualBean */
    public FechaActualBean() {
    }

    public Date getdFechaActual() {
        return new Date();
    }

    public void setdFechaActual(Date dFechaActual) {
        this.dFechaActual = dFechaActual;
    }
    

    public String getFechaActual() {
        Calendar diaActual = Calendar.getInstance();
        int iDiaSemana = diaActual.get(diaActual.DAY_OF_WEEK)-2;
        int iDia = diaActual.get(diaActual.DATE);
        int iMes = diaActual.get(diaActual.MONTH);
        int iAno = diaActual.get(diaActual.YEAR);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String sDia="";
        switch(iDiaSemana){
             case -1:
                sDia = "Domingo";
                break;
            case 0:
                sDia = "Lunes";
                break;
             case 1:
                sDia = "Martes";
                break;
             case 2:
                sDia = "Miércoles";
                break;
             case 3:
                sDia = "Jueves";
                break;
             case 4:
                sDia = "Viernes";
                break;
             case 5:
                sDia = "Sábado";
                break;
        }//finswitch
        
        String sMes = "";
        switch(iMes){
            case 0:
                sMes="Enero";
                break;
            case 1:
                sMes="Febrero";
                break;
            case 2:
                sMes="Marzo";
                break;
            case 3:
                sMes="Abril";
                break;
            case 4:
                sMes="Mayo";
                break;
            case 5:
                sMes="Junio";
                break;
            case 6:
                sMes="Julio";
                break;
            case 7:
                sMes="Agosto";
                break;
            case 8:
                sMes="Septiembre";
                break;
            case 9:
                sMes="Octubre";
                break;
            case 10:
                sMes="Noviembre";
                break;
            case 11:
                sMes="Diciembre";
                break;
                
        }//findel switch
        System.out.println("fechaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        return sDia + ", " + iDia + " de " + sMes + " de " + iAno;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }



}
