/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import RN.CultivosCampoRNLocal;
import entidad.CultivosCampo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author RafaTrossero
 */
@ManagedBean
@SessionScoped
public class ListaCultivosCampoBean implements Serializable {

    @EJB
    private CultivosCampoRNLocal cultivosCampoRNLocal;
    
    private CultivosCampo cultivosCampoObj;
     private CultivosCampo cultCam;
    
    private List<CultivosCampo> listaCultivosCampo;
    
    int i = 1;
    
    public ListaCultivosCampoBean() {
        cultivosCampoObj = new CultivosCampo();
         cultivosCampoObj.setNumero(i);
        this.listaCultivosCampo = new ArrayList<CultivosCampo>();
        this.cultCam = new CultivosCampo();
        
    }
     @PostConstruct
    void init() {
        System.out.println("PostConstructor ListCultivosBeanBean");

      
        
       
    }

    public CultivosCampoRNLocal getCultivosCampoRNLocal() {
        return cultivosCampoRNLocal;
    }

    public void setCultivosCampoRNLocal(CultivosCampoRNLocal cultivosCampoRNLocal) {
        this.cultivosCampoRNLocal = cultivosCampoRNLocal;
    }

    public CultivosCampo getCultivosCampoObj() {
        return cultivosCampoObj;
    }

    public void setCultivosCampoObj(CultivosCampo cultivosCampoObj) {
        System.out.println("asdassssssssssss" +cultivosCampoObj);
         listaCultivosCampo.add(cultivosCampoObj);
        
        System.out.println("asdassssssssssss" +cultivosCampoObj);
        System.out.println("priiiiimeerooooooooooooooooo" +i);
      
    }

    public List<CultivosCampo> getListaCultivosCampo() {
        return listaCultivosCampo;
    }

    public void setListaCultivosCampo(List<CultivosCampo> listaCultivosCampo) {
        this.listaCultivosCampo = listaCultivosCampo;
    }

    public CultivosCampo getCultCam() {
        return cultCam;
    }

    public void setCultCam(CultivosCampo cultCam) {
        this.cultCam = cultCam;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
    
    
     public String reinit() {
        
        System.out.println("antes de sumar" +(listaCultivosCampo.size()+1));
        i=i+1;
        cultivosCampoObj = new CultivosCampo();
        cultivosCampoObj.setNumero((listaCultivosCampo.size()+1));
         System.out.println("despues de sumar" +i);
        return null;
    }
     
      public void deleteAction(CultivosCampo cultivosCampo) {
        
        System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii:____" +(cultivosCampo.getNumero()));
        
        
        Iterator<CultivosCampo> it = listaCultivosCampo.iterator();
            System.out.println("el archivo es " + listaCultivosCampo);

            while (it.hasNext()) {
                cultCam = it.next();
               
               if(cultCam.getNumero()>cultivosCampo.getNumero()){
                   cultCam.setNumero(cultCam.getNumero()-1);
               }

            }
            
       
        listaCultivosCampo.remove(cultivosCampo.getNumero()-1);
        System.out.println("tamañooooooooouuooooooooo" +listaCultivosCampo.size());
        reinit();
    }
}
