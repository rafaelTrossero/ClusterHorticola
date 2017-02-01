/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 *
 * @author jmoreno
 */
@Named(value = "estadisticasBean")
@SessionScoped
public class EstadisticasBean implements Serializable {

    /**
     * Creates a new instance of EstadisticasBean
     */
    public EstadisticasBean() {
    }
    
    public void imprimir_estadistica(String report_name, HashMap param, Collection<?> lista) throws Exception {
       
        try
        {
             System.out.println("entroooooooooooooooooooooooooooo");
            FacesContext context = FacesContext.getCurrentInstance();
            String reportPath = context.getExternalContext().getRealPath("/reports/" + report_name + ".jasper");
            
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(lista);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, param, beanCollectionDataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, context.getExternalContext().getRealPath("/reports/" + report_name + ".pdf"));


            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, httpServletResponse.getOutputStream());
            exporter.exportReport();
            context.responseComplete();
        } catch (Exception ex) {
            System.out.println(ex + "CAUSA: " + ex.getCause());

        }
    }
}
