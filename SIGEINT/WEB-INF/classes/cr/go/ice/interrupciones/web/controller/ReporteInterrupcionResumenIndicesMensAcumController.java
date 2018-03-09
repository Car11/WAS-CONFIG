package cr.go.ice.interrupciones.web.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.InputStream;
import java.io.OutputStream;
import com.vvs.jsf.AbstractFacesController;
import com.vvs.utilidades.Utilidades;

import cr.go.ice.interrupciones.BO.CircuitoBO;
import cr.go.ice.interrupciones.BO.IndiceCalidadNacionalBO;
import cr.go.ice.interrupciones.BO.IndiceCalidadRegionBO;
import cr.go.ice.interrupciones.BO.RegionBO;
import cr.go.ice.interrupciones.BO.SubEstacionBO;
import cr.go.ice.interrupciones.BO.SubRegionBO;
import cr.go.ice.interrupciones.domain.*;
import cr.go.ice.interrupciones.servlets.ServletReporte;
import cr.go.ice.interrupciones.utils.UtilidadesFaces;
import cr.go.ice.interrupciones.utils.UtilidadesI;
import cr.go.ice.interrupciones.web.Recurso;

public class ReporteInterrupcionResumenIndicesMensAcumController extends AbstractFacesController 
{
	//private String URL_reportes;
//	private JasperPrint jasperPrint;
	private Integer mesInicial;
	private Integer anoInicial;
	private Integer mesFinal;
	private Integer anoFinal;
	private  static final String JasperPath = "/jasperReports/";
	private Integer formato;
	private String error;
	
	private IndiceCalidadNacionalBO indiceCalidadNacionalBO;
	private IndiceCalidadRegionBO indiceCalidadRegionBO;
	
    public ReporteInterrupcionResumenIndicesMensAcumController() 
    {
		reiniciarCampos();
	}

	private void reiniciarCampos(){
        Calendar calendar = Calendar.getInstance();
        this.anoInicial = new Integer(calendar.get(Calendar.YEAR));
        this.mesInicial = new Integer(calendar.get(Calendar.MONTH) + 1);
        this.anoFinal = new Integer(calendar.get(Calendar.YEAR));
        this.mesFinal = new Integer(calendar.get(Calendar.MONTH) + 1);
        this.error = "";
    }
	
    public List getListaMesSI()
    {
        return UtilidadesFaces.getListaMes();
    }
	
    public void load(FacesContext context)
    {
        Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
        FacesContext ctx = FacesContext.getCurrentInstance();
        if(limpiar != null)
            reiniciarCampos();
       
     //  ServletContext servletContext = (ServletContext) ctx.getExternalContext().getContext();
       // URL_reportes = servletContext.getResourceAsStream("/jasperReports/");
        //  URL_reportes = "D:\\InterrupcionesWeb\\InterrupcionesWeb\\src\\cr\\go\\ice\\interrupciones\\reportes\\";
       //   URL_reportes=  "D:\\InterrupcionesWeb\\InterrupcionesWeb\\WebContent\\jasperReports\\";
     //  System.out.println(" ---- ---- --- R U T A   R E P O R T E   I N D I C A D O R E S --- --- -- -"+URL_reportes);
    }
    
    public String getInit(FacesContext context)
    {
        Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
        FacesContext ctx = FacesContext.getCurrentInstance();
        if(limpiar != null)
            reiniciarCampos();
		return "success";
       
     
    }
    
    private boolean validarParametros()
    {
        boolean correcto = true;
       
        if(this.anoInicial == null || this.anoInicial.intValue() <= 0)
        {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar un año de inicio."));
            correcto = false;
        }  
        if(this.anoFinal == null || this.anoFinal <= 0)
        {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar un año final."));            
            correcto = false;        
        }
        if(this.mesInicial == null || this.mesInicial <= 0)
        {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar un mes inicial"));            
            correcto = false;          
        }
        if(this.mesFinal == null || this.mesFinal <= 0)
        {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar un mes final."));            
            correcto = false;        
        }
        return correcto;
    }
    
    private boolean validarOpcionesParametros()
    {
        boolean correcto = true;
        this.error = "";
       Calendar calendar = Calendar.getInstance();
        
        int anoActual = calendar.get(Calendar.YEAR);       
        if(this.anoInicial.intValue() > anoActual)
        {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El año inicial no puede ser mayor al actual."));            
            correcto = false;           
        }          
        if(this.mesInicial.intValue() > this.mesFinal)
        {
        	if(this.anoInicial.intValue() >= anoActual)
        	{
        		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El mes no puede ser mayor al actual."));            
	            correcto = false;           
        	}
        }
        if(correcto)
        {
	        List<IndiceCalidadNacional> listaNacional = new ArrayList<IndiceCalidadNacional>();
	        List<IndiceCalidadRegion> listaRegion = new ArrayList<IndiceCalidadRegion>();
	        listaNacional = this.indiceCalidadNacionalBO.buscar(this.anoInicial, this.mesInicial);
	        listaRegion = this.indiceCalidadRegionBO.buscar(this.anoInicial, this.mesInicial);
	        if(listaNacional.isEmpty() || listaRegion.isEmpty())
	        {
	        	this.error ="Para el Año y Mes ingresados no se encuentran datos" + "\n"; 
	        	correcto = false;
	        }
        }
        return correcto;
    }
    
	private String generarReporte()
	{  
	    this.error = "";
	    boolean utilizarDatosHistoricos = false;
		String servletJasperPath = "/jasperReports/";
		String nombre = "";
		String nombreArchivo = "";
		HashMap parametrosReporte = new HashMap();
	   
		//pasarParametrosReporte();
		System.out.println("VALORES: mesInicial "+this.mesInicial+" anoInicial "+this.anoInicial);
	    try
	    {                    
            //HASHMAP PARA LOS PARAMETROS DEL REPORTE
	    	 	parametrosReporte.put("ANO",this.anoInicial);
	            parametrosReporte.put("MES",this.mesInicial);
	            //parametrosReporte.put("AAinicial",this.anoInicial);
	            //parametrosReporte.put("AAfinal",this.anoFinal);
	            //parametrosReporte.put("SUBREPORT_DIR", servletJasperPath); 
	            /*  parametrosReporte.put("SUBREPORT_DIR", URL_reportes);  */
	            
		        nombre = "SigeItrRepIndicesCalidadRegion" + getActualDate() + ".xls";
		        nombreArchivo = "SigeItrRepIndicesCalidadRegion.jasper";
	        this.formato = UtilidadesFaces.FORMATO_XLS;
	        if (this.runReport(JasperPath + nombreArchivo, nombre,parametrosReporte,this.getConnection(),this.formato,UtilidadesFaces.getCurrentUserId())){
	            this.addInfo(null, "reporteEjecutado");
	            return  "success";      
	        }else{
	            this.addError(null, "reporteError");
	        }
	        return "success";
	    } 
	    catch(Exception se)
	    {
	        se.printStackTrace();
	        return "error";
	    } 
	}
	/*      compile_fill_showXLS(nombreArchivo, parametrosReporte); 
	 public void guardar_XLS(String nomb) throws IOException 
	   { 
	    try {
	        JRXlsExporter exporter = new JRXlsExporter();
	        ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();   
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);   
	        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);

	        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, URL_reportes+nomb+".xls"); //"/jasperreportes/"
	        exporter.exportReport();

	        HttpServletResponse response =(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();   
	       
	           byte bytes[] = xlsReport.toByteArray(); 
	           response.setContentType("application/vnd.ms-excel");
	           response.setHeader("Content-Disposition", "attachment; filename="+nomb+".xls"); 
	           response.setContentLength(bytes.length);
	           xlsReport.close();
	           OutputStream ouputStream = response.getOutputStream();
	           ouputStream.write(bytes, 0, bytes.length);
	           ouputStream.flush();
	           ouputStream.close();
	    }
	    catch(JRException ex)
	    {  
	    	System.out.println("reportesError mostrando xls "+ex.getMessage());
	    }
	  }  */
		/*public JasperReport compilar_reporte(String nombre) throws FileNotFoundException 
	    { //compilar reporte
	      InputStream inputStream = null;
	      JasperReport jasperReport=null;
	        try{
	             inputStream = new FileInputStream(URL_reportes+nombre+".jrxml");
	           JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
	           jasperReport = JasperCompileManager.compileReport(jasperDesign);
	          // JasperCompileManager.compileReportToFile(url_reportes+"/"+nombre+".jrxml", url_reportes+"/"+nombre+".jasper");
	           JasperCompileManager.compileReportToFile(URL_reportes+nombre+".jrxml", URL_reportes+nombre);
	           System.out.println(":::REPORTE GUARDADO JASPER::: "+nombre);
	        }
	        catch(JRException ex)
	        {
	        	System.out.println(":::ERROR::"+nombre+"::"+ex.getMessage()); 
	        	return null; 
	        }
	      return jasperReport;
	    }*/
 	/*	public void llenar_reporte(String nombre,Map param) 
	    { //pasarle los parametros y la conexion
	       try 
	       { 
	    	   Connection conec = this.getConnection();
	            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(URL_reportes+nombre+"");//URL_reportes+nombre+"");
	           jasperPrint = JasperFillManager.fillReport(reporte, param, conec); //param
	       } 
	       catch (JRException ex) 
	       { 
	    	   System.out.println(":::ERROR LLENDANDO REPORTE::: "+nombre+" ::: "+ex.getMessage()); 
	       }
	    }
	    
	    public void compile_fill_showXLS(String nombre,Map param)
	    { //formato de excell para descargar desde el servidor
	       // compilar_reporte(nombre); 
	        llenar_reporte(nombre, param);
	        try {
				guardar_XLS(nombre);
			} catch (IOException e) {
				System.out.println(":::ERROR::compile_fill_showXLS "+e.getMessage()); 
				e.printStackTrace();
			}
	    }}*/
	public String getActualDate()
	{
		Date fechaActual = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		dateFormat.applyPattern("dd-MM-yyyy");
		String fecha_actual = dateFormat.format(fechaActual);
		return fecha_actual;
	}
    public String aceptar()
    {
        if(validarParametros())
        {
            if(validarOpcionesParametros())
            {
            	this.error = "";
            	return generarReporte();
            }
            else
            {
                return "error";
            }
        }
        else
        {
            return "error";
        }
    }
    private Connection getConnection()
    {
        DataSource data = (DataSource)Utilidades.getContextVariable("jdbc/interrupciones2DS");
        Connection conn = null;
        try 
        {
            conn = data.getConnection();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            this.addError(null, Recurso.getEtiqueta("error") + e.getMessage());
        }
        return conn;
    }
    public String cancelar()
    {
        return "success";
    }   	
	public Integer getMesInicial() {
		return mesInicial;
	}

	public void setMesInicial(Integer mes) {
		this.mesInicial = mes;
	}
	public Integer getAnoInicial() {
		return anoInicial;
	}
	public void setAnoInicial(Integer ano) {
		this.anoInicial = ano;
	}
	public Integer getMesFinal() {
		return mesFinal;
	}
	public void setMesFinal(Integer mesFinal) {
		this.mesFinal = mesFinal;
	}
	public Integer getAnoFinal() {
		return anoFinal;
	}
	public void setAnoFinal(Integer anoFinal) {
		this.anoFinal = anoFinal;
	}
	public Integer getFormato() {
		return formato;
	}
	public void setFormato(Integer formato) {
		this.formato = formato;
	}
    public String getError() {
        return this.error;
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

	public IndiceCalidadNacionalBO getIndiceCalidadNacionalBO() {
		return indiceCalidadNacionalBO;
	}

	public void setIndiceCalidadNacionalBO(IndiceCalidadNacionalBO indiceCalidadNacionalBO) {
		this.indiceCalidadNacionalBO = indiceCalidadNacionalBO;
	}

	public IndiceCalidadRegionBO getIndiceCalidadRegionBO() {
		return indiceCalidadRegionBO;
	}

	public void setIndiceCalidadRegionBO(IndiceCalidadRegionBO indiceCalidadRegionBO) {
		this.indiceCalidadRegionBO = indiceCalidadRegionBO;
	}
	

}
