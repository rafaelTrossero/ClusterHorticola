/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package recursos;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 * Clase usada para generar reportes
 *
 * @author AFerSor
 */
@ManagedBean(name = "generarReportesBean")
@SessionScoped
public class GenerarReportesBean {

    /**
     * Creates a new instance of GenerarReportesBean
     */
    public GenerarReportesBean() {
    }

    public void generar(HashMap parametros, String nombreReporte) throws Exception {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext
                .getExternalContext().getResponse();
//funcionando
        InputStream reportStream = facesContext.getExternalContext()
                .getResourceAsStream("/Reportes/" + nombreReporte);



        ServletOutputStream servletOutputStream = response.getOutputStream();
        facesContext.responseComplete();

        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "filename=reporte.pdf");

        JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parametros);


        servletOutputStream.flush();
        servletOutputStream.close();

        /* //funcionando
         FacesContext context = FacesContext.getCurrentInstance();
         String reportPath=  context.getExternalContext().getRealPath("/reportes/" + nombreReporte);  
       
         //  HashMap param = new HashMap();

         JasperPrint jasperPrint=JasperFillManager.fillReport(reportPath, parametros);   
         JasperExportManager.exportReportToPdfFile(jasperPrint, "report.pdf"); 
       
         HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();  
        
         httpServletResponse.setContentType("application/force-download");
         httpServletResponse.addHeader("Content-disposition", "attachment; filename=reporte.pdf");  
         ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();  
         JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);  
         //servletOutputStream.close();
         context.responseComplete();

         System.out.println("Termino");*/

    }//fin imprimirReporte
}//FIN CLASE
