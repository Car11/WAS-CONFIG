package cr.go.ice.interrupciones.servicios;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import com.vvs.reporte.*;
import com.vvs.jsf.AbstractFacesController;




/**
 * <p>Clase cr.go.ice.interrupciones.servicios.GenerarReporteAdjunto.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Genera un archivo temporal para adjuntar por correo en los reportes necesarios</code>.</p>
 * <p>Fecha creación: 28/07/2016</p>
 * <p>Ultima actualización: 28/07/2016</p>
 * @author Rossmon (rhidalgo)
 * @version 1.1
 */

public class GenerarReporteAdjunto extends AbstractFacesController{
	/**
     * Metodo que genera un archivo temporal que se envia por correo
     * @param correosParaEnviar Lista de destinatarios
     */
    @SuppressWarnings("unchecked")
	public boolean generarArchivo(String jasperReport, String fileName, HashMap parameters, JRBeanCollectionDataSource jrDS, Connection con,Integer tipo, Long cedulaUser)
    {
    	boolean reportGenetared=false;
        Integer FORMATO_PDF = Integer.valueOf(1);
        String path = this.getContextRealPath("/jasperReports"+File.separatorChar);
        
        try
        {
            HttpServletResponse resp =(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
            resp.reset();
            resp.setHeader("pragma", "no-cache");
            resp.setHeader("Cache-control","no-cache, no-store, must-revalidate") ;
            
            InputStream in =obtenerJasper(jasperReport);
            if(in==null){
                return reportGenetared;
            }
            if(jrDS!=null)
            {
            	resp.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");
            	JasperRunManager.runReportToPdfStream(in, resp.getOutputStream(), parameters, jrDS);
            	reportGenetared=true;
            }else if (con != null){
                if(tipo != null){
                    if(FORMATO_PDF.equals(tipo)){
                        resp.setContentType("application/pdf");
                        resp.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");
                        JasperRunManager.runReportToPdfStream(in, resp.getOutputStream(), parameters, con);
                        reportGenetared=true;
                    }else{
                        resp.setContentType("application/vnd.ms-excel");
                        fileName = fileName.replaceFirst("pdf", "xls");
                        resp.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");
                        this.generarAdjunto(in, parameters, con, resp.getOutputStream());
                        InputStream in2 = this.obtenerJasper(jasperReport);
                        this.generarArchivoXlS(in2, parameters, con, resp.getOutputStream());
                        reportGenetared=true; 
                    }
                }else{
                    resp.setContentType("application/pdf");
                    resp.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");
                    JasperRunManager.runReportToPdfStream(in, resp.getOutputStream(), parameters, con);
                    reportGenetared=true;
                }  
            }else{
              JasperRunManager.runReportToPdfStream(in, resp.getOutputStream(), parameters);
              reportGenetared=true;
            }
            
            resp.getOutputStream().flush();
            resp.getOutputStream().close();

            responseComplete();
            
        }catch(IOException e) {
            e.printStackTrace();
            reportGenetared=false;
        }catch (JRException e) {
            e.printStackTrace();
            reportGenetared=false;
        } catch (Exception e) {
            e.printStackTrace();
            reportGenetared=false;
        }finally{
            try {
            	if(con            != null)
            	{
            		con.close();           
            	}
            }
            catch (SQLException e)
            {
            	e.printStackTrace();
            }
        }              

        return reportGenetared;
    }
    public void generarArchivoXlS(InputStream reportStream, HashMap parametros,Connection conexionBD, OutputStream outputStream) throws JRException{
    	try
    	{
            parametros.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
            JasperPrint jPrint = JasperFillManager.fillReport(reportStream,parametros,conexionBD);
            JRXlsExporter exportadorXLS = new JRXlsExporter();
            exportadorXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT,jPrint);
            exportadorXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,outputStream);
            exportadorXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
            exportadorXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            exportadorXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            exportadorXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE); 
            exportadorXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
            exportadorXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
            exportadorXLS.exportReport();
    	}catch(JRException ex)
    	{
    		ex.printStackTrace();
    	}
    }
    
    public void generarAdjunto(InputStream reportStream, HashMap parametros,Connection conexionBD, OutputStream outputStream)
    {
    	try
    	{
	    		
	    		ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
	        	parametros.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
		    	
		        JasperPrint jPrint2 = JasperFillManager.fillReport(reportStream,parametros,conexionBD);
		        JRXlsExporter exportadorXLS2 = new JRXlsExporter();
		        exportadorXLS2.setParameter(JRXlsExporterParameter.JASPER_PRINT,jPrint2);
		        exportadorXLS2.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,xlsReport);
		        exportadorXLS2.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
		        exportadorXLS2.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		        exportadorXLS2.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		        exportadorXLS2.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE); 
		        exportadorXLS2.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
		        exportadorXLS2.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		        exportadorXLS2.exportReport();
		        
		        HttpServletResponse response =(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		        
		        InputStream subreportStream = new ByteArrayInputStream(xlsReport.toByteArray());
		        File file = new File(this.getContextRealPath("/file/SigeItrRepInterrupcionPorCircuito.xls"+File.separatorChar));
		        OutputStream out = new FileOutputStream(file);
		        int read = 0;
		        byte[] bytes = new byte[1024];
		        while ((read = subreportStream.read(bytes)) != -1)
		        {
		        	out.write(bytes, 0, read);
		        }
		        out.flush();
		        out.close();
		        subreportStream.close();
    	}catch(JRException ex)
    	{
    		ex.printStackTrace();
    	}catch(IOException ex)
    	{
    		ex.printStackTrace();
    	}
    }
	@Override
	protected String getPropertyFieldName(String property) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected void resetController() {
		// TODO Auto-generated method stub
		
	}

}
