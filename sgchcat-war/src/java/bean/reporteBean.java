/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import static org.apache.poi.hssf.usermodel.HeaderFooter.date;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author cris
 */
@ManagedBean
@RequestScoped
public class reporteBean {

    private LineChartModel areaModel;
    private Date fecha_inicio;
    private Date fecha_fin;
    private final String escudo1 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Imagenes/cluster.jpg");
   

//private final String escudo2 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Imagenes/escudo.jpg");
    /**
     * Creates a new instance of reporteBean
     */
    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public LineChartModel getAreaModel() {
        return areaModel;
    }

    public void setAreaModel(LineChartModel areaModel) {
        this.areaModel = areaModel;
    }

    public reporteBean() {

    }

    public void setearReporte() throws SQLException {

        Connection conect;
        conect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ClusterHortDB", "postgres", "123456");
        System.out.println("funcionando" + conect);
        String path;
        //List<Object[]> l = turnoRNLocal.findHistorialTurnosServicios(1, this.getFechaInicio(), this.getFechaFin());
        System.out.println("funcionando");

        try {
            path = "reportes\\empaque.jasper";
            FacesContext context = FacesContext.getCurrentInstance();
            System.out.println("entrooooooooooooooooooooooooooooooo1" + path);
            String reportPath = context.getExternalContext().getRealPath(path);
            System.out.println("entrooooooooooooooooooooooooooooooo2" + reportPath);

            HashMap parametro = new HashMap();
            SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
            String a = (sdf.format(fecha_inicio));
            String fecha1 = "20" + a;
            System.out.println("la fechas es " + fecha1);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yy/MM/dd");
            String b = (sdf1.format(fecha_fin));
            String fecha2 = "20" + b;
            System.out.println("la fechas es " + fecha2);
           SimpleDateFormat nuevafecha = new SimpleDateFormat("yyyy/MM/dd");
           Date fechadateini= nuevafecha.parse(fecha1);
           Date fechadatefin= nuevafecha.parse(fecha2);
           System.out.println("la fechas es.............. " + fechadateini);
           System.out.println("la fechas es.............. " + fechadatefin);
           
            System.out.println("fecha1" + fecha_inicio);
            System.out.println("fecha2" + fecha_fin);
            String inicio=new SimpleDateFormat("yyyy-MM-dd").format(fecha_inicio);
            String fin=new SimpleDateFormat("yyyy-MM-dd").format(fecha_fin);
            System.out.println("la fechas es.............. " + inicio);
           System.out.println("la fechas es.............. " + fin);
            parametro.put("parameter1", fecha_inicio);
            parametro.put("parameter2", fecha_fin);
            parametro.put("escudo1", escudo1);

            System.out.println("entroooooooooooo000oooooooooooooooo4" + parametro);

            System.out.println("entroooooooooooooooooooooooooooo5" + parametro);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parametro, conect);
            JasperExportManager.exportReportToPdfFile(jasperPrint, context.getExternalContext().getRealPath("reportes\\empaque.pdf"));
            System.out.println("entroooooooooooooooooooooooooooo6" + parametro);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, httpServletResponse.getOutputStream());
            exporter.exportReport();
            context.responseComplete();
            //conect.close();

        } catch (Exception ex) {
            System.out.println(ex + "CAUSAaaaaaaaaaaaaaaaaaaaaaa: " + ex.getCause().getMessage());
        }

    }

    public void setearReportePrueba() throws SQLException {

        Connection conect;
        conect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ClusterHortDB", "postgres", "123456");
        System.out.println("funcionando" + conect);
        String path;
        //List<Object[]> l = turnoRNLocal.findHistorialTurnosServicios(1, this.getFechaInicio(), this.getFechaFin());
        System.out.println("funcionando");

        try {
            path = "reportes\\nuevoReporte.jasper";
            FacesContext context = FacesContext.getCurrentInstance();
            System.out.println("entrooooooooooooooooooooooooooooooo1" + path);
            String reportPath = context.getExternalContext().getRealPath(path);
            System.out.println("entrooooooooooooooooooooooooooooooo2" + reportPath);

            HashMap parametro = new HashMap();
            System.out.println("entrooooooooooooooooooooooooooooooo3" + reportPath);
            parametro.put("parameter1", "hola");

            System.out.println("entroooooooooooo000oooooooooooooooo4" + parametro);

            System.out.println("entroooooooooooooooooooooooooooo5" + parametro);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parametro, conect);
            JasperExportManager.exportReportToPdfFile(jasperPrint, context.getExternalContext().getRealPath("reportes\\nuevoReporte.pdf"));
            System.out.println("entroooooooooooooooooooooooooooo6" + parametro);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, httpServletResponse.getOutputStream());
            exporter.exportReport();
            context.responseComplete();
            //conect.close();

        } catch (Exception ex) {
            System.out.println(ex + "CAUSAaaaaaaaaaaaaaaaaaaaaaa: " + ex.getCause().getMessage());
        }

    }

    public void generar() throws SQLException {

        Connection conect;
        conect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ClusterHortDB", "postgres", "123456");
        String path;
        System.out.println("funcionando");

        try {

            HashMap parametros = new HashMap();
            path = "reportes\\nuevoReporte.jasper";
//funcionando

            FacesContext context = FacesContext.getCurrentInstance();
            String reportPath = context.getExternalContext().getRealPath(path);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parametros, conect); //new JREmptyDataSource() si le pongo eso en vez de conect me devuelve null
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "filename=reporte.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            servletOutputStream.flush();
            servletOutputStream.close();
            FacesContext.getCurrentInstance().responseComplete();

        } catch (Exception ex) {
            System.out.println(ex + "CAUSA: " + ex.getCause());

        }

    }//fin generar

}
