/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cris
 */
@ManagedBean
@SessionScoped
public class dowloadFile {

    /**
     * Creates a new instance of dowloadFile
     * @throws java.io.IOException
     */
    public void downloadFile() throws IOException{
    //private static final long serialVersionUID = 626953318628565053L;
 System.out.println("si si si si si si si si si");
    File file = new File("C:\\docs\\instructions.txt");
      InputStream fis = new FileInputStream(file);
      byte[] buf = new byte[1024];
      int offset = 0;
      int numRead = 0;
      while ((offset < buf.length) && ((numRead = fis.read(buf, offset, buf.length -offset)) >= 0)) 
      {
        offset += numRead;
      }
      fis.close();
      HttpServletResponse response =
         (HttpServletResponse) FacesContext.getCurrentInstance()
        .getExternalContext().getResponse();

     response.setContentType("application/octet-stream");
     response.setHeader("Content-Disposition", "attachment;filename=instructions.txt");
     response.getOutputStream().write(buf);
     response.getOutputStream().flush();
     response.getOutputStream().close();
     FacesContext.getCurrentInstance().responseComplete();
     System.out.println("si si si si si si si si si");
}

}
