package cr.go.ice.interrupciones.web.controller;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import com.vvs.jsf.AbstractFacesController;
import com.vvs.utilidades.Utilidades;
import cr.go.ice.interrupciones.utils.UtilidadesFaces;
import cr.go.ice.interrupciones.web.Recurso;

public class ReporteIndiceContinuidadDistribucionController extends AbstractFacesController 
{
	private Integer mesInicial;
	private Integer anoInicial;
	private Integer mesFinal;
	private Integer anoFinal;
	private  static final String JasperPath = "/jasperReports/";
	private Integer formato;
	private String error;
	
    public ReporteIndiceContinuidadDistribucionController() 
    {
		reiniciarCampos();
	}

	private void reiniciarCampos(){
        Calendar calendar = Calendar.getInstance();
        this.anoInicial = new Integer(calendar.get(Calendar.YEAR));
        this.mesInicial = new Integer(calendar.get(Calendar.MONTH) + 1);
        this.anoFinal = new Integer(calendar.get(Calendar.YEAR));
        this.mesFinal = new Integer(calendar.get(Calendar.MONTH) + 1);
        this.error ="";
    }
	
    public void load(FacesContext context)
    {
        Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
        if(limpiar != null)
            reiniciarCampos();
    }
    
    @SuppressWarnings("rawtypes")
	public List getListaMesSI()
    {
        return UtilidadesFaces.getListaMes();
    }
    
    private boolean validarParametros()
    {
        boolean correcto = true;
        
        if(this.anoInicial == null || this.anoInicial.intValue() <= 0)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar el a�o."));
            correcto = false;
        } 
        if(this.mesInicial == null || this.mesInicial <= 0)
        {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar el mes."));
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El a�o inicial no puede ser mayor al actual."));
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
        return correcto;
    }
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String generarReporte()
	{  
	    this.error = "";
		String nombre = "";
		String nombreArchivo = "";
		HashMap parametrosReporte = new HashMap();
	    try
	    {
	    	parametrosReporte.put("aa",this.anoInicial);
	    	parametrosReporte.put("mes",this.mesInicial);
	    	
	    	
	    	nombre = "SigeItrRepIndicesContinuidadDistri" + getActualDate() + ".xls";
	    	nombreArchivo = "SigeItrRepIndicesContinuidadDistribucion.jasper";
	    	this.formato = UtilidadesFaces.FORMATO_XLS;
	    	if (this.runReport(JasperPath + nombreArchivo, nombre,parametrosReporte,this.getConnection(),this.formato,UtilidadesFaces.getCurrentUserId()))
	    	{
	    		this.addInfo(null, "reporteEjecutado");
	    		return  "success";      
	    	}else
	    	{
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
}

