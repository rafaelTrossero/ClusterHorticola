/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Juan Pablo
 */
public class cadenas {
  public static String convertir( String cadena ){
    char reemplazos_a[] = {'Á','Ä','Â','À'};
    char reemplazos_e[] = {'É','Ë','Ê','È'};
    char reemplazos_i[] = {'Í','Ï','Î','Ì'};
    char reemplazos_o[] = {'Ó','Ö','Ô','Ò'};
    char reemplazos_u[] = {'Ú','Ü','Û','Ù'};
    char reemplazos_c[] = {'Ç'};
    int index;
    
    //elimina espacios en blanco al inicio y al final
    cadena = cadena.trim();
    
    //convierte todos los caracteres a mayúsculas
    cadena = cadena.toUpperCase();
    
     // reemplaza caracteres invalidos de "A"
    for(index = 0; index < reemplazos_a.length; index++) {
      cadena = cadena.replace(reemplazos_a[index], 'A');
    }
    for(index = 0; index < reemplazos_e.length; index++) {
      cadena = cadena.replace(reemplazos_e[index], 'E');
    }
    for(index = 0; index < reemplazos_i.length; index++) {
      cadena = cadena.replace(reemplazos_i[index], 'I');
    }
    for(index = 0; index < reemplazos_o.length; index++) {
      cadena = cadena.replace(reemplazos_o[index], 'O');
    }
    for(index = 0; index < reemplazos_u.length; index++) {
      cadena = cadena.replace(reemplazos_u[index], 'U');
    }
    for(index = 0; index < reemplazos_c.length; index++) {
      cadena = cadena.replace(reemplazos_c[index], 'C');
    }
    
    return cadena;
  }
  
  public static boolean es_numero(String cadena) {
    try {
      Long.parseLong(cadena);
    }
    catch (Exception e) {
      return false;
    }
    return true;
  }
  
  
  public static boolean es_letras(String cadena) throws Exception{
      
        Pattern patron = Pattern.compile("[A-Za-z]");
        Matcher encaja = patron.matcher(cadena);
        try{
           if(!encaja.find()){
          throw new Exception("El dato ingresado solo puede tener letras");
        } 
        }      
        catch(Exception e){
            return false;
        }   
     
      return true;

  }
   public static boolean es_email(String cadena) throws Exception{
       Pattern pat = null;
        Matcher mat = null;        
        pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        mat = pat.matcher(cadena);
        if (mat.find()) {
            System.out.println("[" + mat.group() + "]");
            return true;
        }else{
            return false;
        }      

  }
 
}
