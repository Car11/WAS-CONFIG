package cr.go.ice.interrupciones.web.controller;

import java.util.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.ParametroBO;
import cr.go.ice.interrupciones.domain.Parametro;
import cr.go.ice.interrupciones.domain.procedure.Pevemq_IndCalidadNac;
import cr.go.ice.interrupciones.domain.procedure.Pevemq_IndCalidadReg;
import cr.go.ice.interrupciones.utils.UtilidadesFaces;

public class IndicesCalidadController extends AbstractFacesController  {
	private Integer mes;
	private Integer ano;
	private  static final String JasperPath = "/jasperReports/";
	private Integer formato;
	private Integer tipoIndice;
	private String error;
	Pevemq_IndCalidadNac procedimientoNacional; 
	Pevemq_IndCalidadReg procedimientoRegional; 
	
	private ParametroBO parametroBO;
	
	public IndicesCalidadController() 
    {
		reiniciarCampos();
	}

	private void reiniciarCampos(){
        Calendar calendar = Calendar.getInstance();
        this.procedimientoNacional = new Pevemq_IndCalidadNac();
        this.procedimientoRegional = new Pevemq_IndCalidadReg();
        this.tipoIndice = Integer.valueOf(0);
        this.ano = new Integer(calendar.get(Calendar.YEAR));
        this.mes = new Integer(calendar.get(Calendar.MONTH) + 1);
    }
	
	@SuppressWarnings("rawtypes")
	public List getListaMesSI()
    {
        return UtilidadesFaces.getListaMes();
    }
	
	public String getInit(){
		FacesContext context = FacesContext.getCurrentInstance();
		 Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
	        FacesContext ctx = FacesContext.getCurrentInstance();
	        if(limpiar != null)
	            reiniciarCampos();
		return "success";
	}
	
    @SuppressWarnings("unused")
	public void load(FacesContext context)
    {
        Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
        FacesContext ctx = FacesContext.getCurrentInstance();
        if(limpiar != null)
            reiniciarCampos();
    }
    private boolean validarParametros()
    {
        boolean correcto = true;
        
        if(this.ano == null || this.ano.intValue() <= 0)
        {
           
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar el año."));
            correcto = false;
        }  
        if(this.mes == null || this.mes <= 0)
        {
            
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar un Mes."));
            correcto = false;          
        }
        return correcto;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List getIndices() {
    	List tipos = new ArrayList();    
    	tipos.add(new SelectItem(0, "Índices a Nivel Regional"));
    	tipos.add(new SelectItem(1, "Índices a Nivel Nacional"));          
    	return tipos;      
    }
    @SuppressWarnings("rawtypes")
	private boolean validarOpcionesParametros()
    {
        boolean correcto = true;
    
        Calendar calendar = Calendar.getInstance();
        
        int anoActual = calendar.get(Calendar.YEAR);
        int mesActual = calendar.get(Calendar.MONTH) + 1;
        int diaActual = calendar.get(Calendar.DAY_OF_MONTH);
        if(this.ano.intValue() > anoActual)
        {
            
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El año no puede ser mayor al actual."));
            correcto = false;           
        }          
        if(this.ano.equals(anoActual) && this.mes > mesActual)
        {
        	
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El mes no puede ser mayor al actual."));
            correcto = false;
        }
        if(diaActual >5 && this.mes < mesActual)
        {
	        List parametros = parametroBO.getParametros();
			Parametro parametro = null;
			if(parametros != null && parametros.size() > 0)
				parametro = (Parametro)parametros.get(0);
			if((parametro == null) || (parametro.getParametroID().getGeneracionIndices().equals("S") == false) && correcto){			        			  
				
	        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Los índices no pueden ser generados despúes del día 5 de cada mes."));
				correcto = false;
			}
        }
        return correcto;
    }
    
    public String aceptar()
    {
    	if(validarParametros())
    	{
    		if(validarOpcionesParametros())
    		{
    			return ejecutarProcedimiento();
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
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public String ejecutarProcedimiento()
    {
    
    	boolean resultado = false;
		HashMap parametrosProcedimiento = new HashMap();
	    try
	    {
	    	
            parametrosProcedimiento.put("mes",this.mes);
            parametrosProcedimiento.put("ano",this.ano);
            if(this.tipoIndice.equals(Integer.valueOf(0)))
            {
            	resultado = this.procedimientoRegional.IndicesCalidadRegional(parametrosProcedimiento);
            }else if (this.tipoIndice.equals(Integer.valueOf(1)))
            {
            	resultado = this.procedimientoNacional.IndicesCalidadNacional(parametrosProcedimiento);
            }
           
            if(resultado)
            {
            
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "La ejecución del proceso fue existosa."));
            }else
            {
            	
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error!", "La ejecución del proceso no fue exitosa."));
            }
                       
            
	        return "success";
	    } 
	    catch(Exception se)
	    {
	    	
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error!", "La ejecución del proceso no fue exitosa."));
	        se.printStackTrace();
	        return "error";
	    } 
    }

	public String cancelar()
    {
        return "success";
    }
    
    public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getFormato() {
		return formato;
	}

	public void setFormato(Integer formato) {
		this.formato = formato;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public static String getJasperpath() {
		return JasperPath;
	}

	public Integer getTipoIndice() {
		return tipoIndice;
	}

	public void setTipoIndice(Integer tipoIndice) {
		this.tipoIndice = tipoIndice;
	}

	public Pevemq_IndCalidadNac getProcedimientoNacional() {
		return procedimientoNacional;
	}

	public void setProcedimientoNacional(Pevemq_IndCalidadNac procedimientoNacional) {
		this.procedimientoNacional = procedimientoNacional;
	}

	public Pevemq_IndCalidadReg getProcedimientoRegional() {
		return procedimientoRegional;
	}

	public void setProcedimientoRegional(Pevemq_IndCalidadReg procedimientoRegional) {
		this.procedimientoRegional = procedimientoRegional;
	}

	public ParametroBO getParametroBO() {
		return parametroBO;
	}

	public void setParametroBO(ParametroBO parametroBO) {
		this.parametroBO = parametroBO;
	}

	protected String getPropertyFieldName(String property) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void resetController() {
		// TODO Auto-generated method stub
		
	}
	

}
