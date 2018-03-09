/**
 * 
 */
package cr.go.ice.interrupciones.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.vvs.jsf.AbstractFacesController;
import com.vvs.utilidades.Fechas;
import com.vvs.utilidades.Utilidades;

import cr.go.ice.interrupciones.servlets.ServletReporte;
import cr.go.ice.interrupciones.utils.UtilidadesFaces;
import cr.go.ice.interrupciones.web.Recurso;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ReporteInterrupcionTiempoPorVehiculoController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ReporteInterrupcionTiempoPorVehiculoController.java</code>.</p>
 * <p>Fecha creación: 26/07/2010</p>
 * <p>Ultima actualización: 26/07/2010</p>
 * @author Vista Verde Tecnologia (Evelyn)
 * @version 1.5
 */

public class ReporteInterrupcionTiempoPorVehiculoController extends AbstractFacesController{

	private Integer vehiculo;
	private Date fechaInicio;
    private Date fechaFinal;
    private Integer formato;
    private  static final String JasperPath = "/jasperReports/";
    /**
     * Constructor
     *
     */
    public ReporteInterrupcionTiempoPorVehiculoController(){
    	this.reiniciarCampos();
    }
    
    /**
     * Reinicia los valores de la página
     *
     */
    private void reiniciarCampos(){
        this.fechaInicio = new Date();
        this.fechaFinal = new Date();
        this.vehiculo = null;        
        this.formato = UtilidadesFaces.FORMATO_XLS;
    }
    
    /**
     * Precarga la oficina del usuario de la aplicación, si el mismo es un usuario CLOR
     * @param context
     */
    public void load(FacesContext context){
        Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
        if(limpiar != null)
            reiniciarCampos();
        
    }
    
    public String getInit(FacesContext context){
        Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
        if(limpiar != null)
            reiniciarCampos();
        return "success";
    }
    /**
     * Lista de tipos de formatos para el reporte
     * @return lista de formatos
     */
    public List getListaFormatos(){
        
        List formatos = new ArrayList();       
        
        formatos.add(0,new SelectItem(UtilidadesFaces.FORMATO_PDF,"Pdf"));
        formatos.add(new SelectItem(UtilidadesFaces.FORMATO_XLS,"Excel"));
        return formatos;
    }
    
    /**
     * Realiza las validaciones de los parámetros de entrada dados por el usuario y genera el reporte
     * @return error o success de acuerdo a la correctitud de los parametros de entrada o no, de haberse encontrado
     * datos en la base de datos y de la correcta generación del reporte
     */ 
    public String aceptar(){
        if(validarParametros()){
            if(validarParametros()){                
                return generarReporte();
            }else{
                return "error";
            }
        }else{
            return "error";
        }
    } 
    
    private boolean validarParametros(){
        boolean correcto = true;
       
        if (this.vehiculo == null || this.vehiculo.equals(new Integer(0))) {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de vehículo debe ser mayor a cero."));
            correcto = false;
        }
        
        if(this.fechaInicio.compareTo(new Date()) > 0){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha inicial es mayor a la fecha actual, por favor, corregirla."));            
            correcto=false;
        }  
        if(this.fechaFinal.compareTo(new Date()) > 0){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha final es mayor a la fecha actual, por favor, corregirla."));            
            correcto=false;
        }          
        long horasInicio = Fechas.millisToMinutes(this.fechaInicio.getTime());
        long horasFinal = Fechas.millisToMinutes(this.fechaFinal.getTime());
        long diferencia = (horasFinal - horasInicio);
        if(diferencia < 0){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha final debe ser mayor o igual que la fecha inicial."));            
            correcto=false;
        }
        return correcto;
    }
    
    
    @SuppressWarnings("unchecked")
    private String generarReporte(){  
        //pasarParametrosReporte();   
        String nombreArchivo = "";
        String nombre = "";
        String ServletJasperPath = "/jasperReports/";
        HashMap parametrosReporte = new HashMap();
        try{                    
//            FacesContext context = FacesContext.getCurrentInstance();                       
//            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletReporte");
//            dispatcher.forward(request, response);                                  
//            response.getOutputStream().flush();
//
//            if(!FacesContext.getCurrentInstance().getResponseComplete() ) {
//                FacesContext.getCurrentInstance().responseComplete();
//            } 
            SimpleDateFormat sf = new SimpleDateFormat();
            sf.applyPattern("dd/MM/yyyy");      
            parametrosReporte.put("pFechaInicio",sf.format(this.fechaInicio));
            parametrosReporte.put("pFechaFin",sf.format(this.fechaFinal));
            parametrosReporte.put("pVehiculo",this.vehiculo);
            
            parametrosReporte.put("SUBREPORT_DIR",ServletJasperPath);
            nombreArchivo = "SigeItrRepInterrupcionTiemposVehiculos";
  
            nombre = "TiemposVehiculos";
            Date fechaActual = new Date();                              
            String fechaActualSTR = sf.format(fechaActual);
          
            nombreArchivo += ".jasper";
          
         
              nombre += fechaActualSTR + ".xls";
          
          if (this.runReport(JasperPath + nombreArchivo, nombre,parametrosReporte,this.getConnection(),this.formato,UtilidadesFaces.getCurrentUserId())){
              this.addInfo(null, "reporteEjecutado");
              return  "success";      
              
          }else{
              this.addError(null, "reporteError");
          }
            return "success";
        } catch(Exception se){
            se.printStackTrace();
            return "error";
        } 
    }
    private Connection getConnection(){
        DataSource data = (DataSource)Utilidades.getContextVariable("jdbc/interrupciones2DS");
        Connection conn = null;
        try {
            conn = data.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            this.addError(null, Recurso.getEtiqueta("error") + e.getMessage());
        }
        return conn;
    }
    private void pasarParametrosReporte(){        
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteTiempoVehiculo));
        ctx.getExternalContext().getSessionMap().put("vehiculo",this.vehiculo);
        ctx.getExternalContext().getSessionMap().put("fechaInicio",this.fechaInicio);
        ctx.getExternalContext().getSessionMap().put("fechaFinal",this.fechaFinal);
        
        ctx.getExternalContext().getSessionMap().put("formato",this.formato);        
                
    }
    
    /**
     * Cancela el proceso de la generación de reportes
     * @return success
     */    
    public String cancelar(){
        this.reiniciarCampos();
        return "success";
    }

    
    /*****************************************************************************************************
     ***************************************************************************************************** 
     *****************************************************************************************************/
    
    
	/**
	 * @return the fechaFinal
	 */
	public Date getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the formato
	 */
	public Integer getFormato() {
		return formato;
	}

	/**
	 * @param formato the formato to set
	 */
	public void setFormato(Integer formato) {
		this.formato = formato;
	}

	/**
	 * @return the vehiculo
	 */
	public Integer getVehiculo() {
		return vehiculo;
	}

	/**
	 * @param vehiculo the vehiculo to set
	 */
	public void setVehiculo(Integer vehiculo) {
		this.vehiculo = vehiculo;
	}

    /**
     * @see com.vvs.jsf.AbstractFacesController#getPropertyFieldName(java.lang.String)
     */
    @Override
    protected String getPropertyFieldName(String property) {
        return null;
    }

    /**
     * @see com.vvs.jsf.AbstractFacesController#resetController()
     */
    @Override
    protected void resetController() {
    } 
    
}
