package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.vvs.jsf.AbstractFacesController;


import cr.go.ice.interrupciones.BO.EmpleadoBO;
import cr.go.ice.interrupciones.BO.InterrupcionBO;
import cr.go.ice.interrupciones.BO.OficinaBO;
import cr.go.ice.interrupciones.BO.ParametroBO;
import cr.go.ice.interrupciones.BO.ReporteBO;
import cr.go.ice.interrupciones.BO.UsuarioOficinaBO;
import cr.go.ice.interrupciones.domain.Empleado;
import cr.go.ice.interrupciones.domain.Interrupcion;
import cr.go.ice.interrupciones.domain.InterrupcionID;
import cr.go.ice.interrupciones.domain.Oficina;
import cr.go.ice.interrupciones.domain.Parametro;
import cr.go.ice.interrupciones.domain.Reporte;
import cr.go.ice.interrupciones.domain.ReporteID;
import cr.go.ice.interrupciones.domain.UsuarioOficina;
import cr.go.ice.interrupciones.utils.Usuario;
import cr.go.ice.interrupciones.utils.UtilidadesI;
import cr.go.ice.cia.dominio.UsuarioCia;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ActInterrupcionController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ActInterrupcionController.java</code>.</p>
 * <p>Fecha creación: 21/04/2007</p>
 * <p>Ultima actualización: 21/04/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ActInterrupcionController extends AbstractFacesController {
	
	private Integer ano;
	private Integer oficina;
	private Long consecutivo;
	private Long cedula;
	private Boolean mas5;
	private Boolean menos5;
	
	private InterrupcionBO interrupcionBO;
	private ReporteBO reporteBO;
	private ParametroBO parametroBO;
	private EmpleadoBO empleadoBO;
	private UsuarioOficinaBO usuarioOficinaBO;
	private OficinaBO oficinaBO;
	private Boolean comboOficina;


	/**
	 * Constructor por defecto
	 */
	public ActInterrupcionController(){
        inicializarAtributos();
	}
    
    private void inicializarAtributos(){
        this.mas5 = Boolean.TRUE;
        this.menos5 = Boolean.FALSE;
        this.oficina=0;
    }
    
    public String getInit() {
    	
    	FacesContext context = FacesContext.getCurrentInstance();
    	 String nombreUsuarioSession = (String) context.getApplication().createValueBinding("#{autorizacionController.nombreUsuario}").getValue(this.getFacesContext());
         String[] valores = nombreUsuarioSession.split("-");
         this.cedula = new Long(valores[0].trim());
    	
	        return "success";
	        
    }

	/**
	 * 
	 * Retorna una lista de los años en que han ocurrido interrupciones
	 * @return Lista de años
	 */
	public List getListaAnos(){
        List anos;
        
        if(this.mas5.booleanValue())
            anos = this.interrupcionBO.getAnosDeInterrupciones();
        else
            anos = this.reporteBO.getAnosDeReportes();
		List anosSI = new ArrayList();
		if(anos != null){
			for(int i = 0; i < anos.size(); i++){
			    Integer ano = (Integer)anos.get(i);
				SelectItem itm = new SelectItem(ano,ano.toString());
				anosSI.add(itm);				
			}
		}
        
//      Comentado la restricción cuando se implementó el CIA
//      boolean userAdmin = Usuario.isUserAdmin();
//      this.comboOficina = (userAdmin) ? new Boolean(false) : new Boolean(true);
       this.comboOficina = Boolean.FALSE;//cambia catologo de oficinas
        
      
        Empleado emp = this.empleadoBO.buscar(cedula);
        
        if(emp != null)
        {
        	List<UsuarioOficina> listaPivote = this.usuarioOficinaBO.buscarCedula(this.cedula);
        	if(!listaPivote.isEmpty())
        	{
        		this.oficina = listaPivote.get(0).getId().getOficina().getCodigoOficina();
        	}else
        	{
        		this.oficina = new Integer(0);
        	}
            
        }else
        {
            this.oficina = new Integer(0);
        }
		
		return anosSI;		
	}
	
	/**
	 * Metodo modificador de parametroBO.
	 * @param parametroBO El parametroBO a modificar.
	 */
	public void setParametroBO(ParametroBO parametroBO) {
		this.parametroBO = parametroBO;
	}
    
    /**
     * Comment for listenerCheckMayorCincoMin
     * @param v
     * @return "listener"
     */
    public String listenerCheckMayorCincoMin(){
        return "listener";  
    }  
    
    /**
     * Comment for listenerCheckMenorCincoMin
     * @param v
     * @return "listener"
     */
    public String listenerCheckMenorCincoMin(){  
        return "listener";  
    }      
    
	/**
	 * Metodo accesor de ano.
	 * @return Retorna el ano.
	 */
	public Integer getAno() {
		return ano;
	}
	/**
	 * Metodo modificador de ano.
	 * @param ano El ano a modificar.
	 */
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	/**
	 * Metodo accesor de consecutivo.
	 * @return Retorna el consecutivo.
	 */
	public Long getConsecutivo() {
		return consecutivo;
	}
	/**
	 * Metodo modificador de consecutivo.
	 * @param consecutivo El consecutivo a modificar.
	 */
	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}
	/**
	 * Metodo accesor de oficina.
	 * @return Retorna el oficina.
	 */
	public Integer getOficina() {
		return oficina;
	}
	/**
	 * Metodo modificador de oficina.
	 * @param oficina El oficina a modificar.
	 */
	public void setOficina(Integer oficina) {
		this.oficina = oficina;
	}

	/**
	 * Metodo accesor de mas5.
	 * @return Retorna el mas5.
	 */
	public Boolean getMas5() {
		return mas5;
	}
	/**
	 * Metodo modificador de mas5.
	 * @param mas5 El mas5 a modificar.
	 */
	public void setMas5(Boolean mas5) {
		this.mas5 = mas5;
	}
	/**
	 * Metodo accesor de menos5.
	 * @return Retorna el menos5.
	 */
	public Boolean getMenos5() {
		return menos5;
	}
	/**
	 * Metodo modificador de menos5.
	 * @param menos5 El menos5 a modificar.
	 */
	public void setMenos5(Boolean menos5) {
		this.menos5 = menos5;
	}
	/**
	 * Metodo accesor de interrupcionBO.
	 * @return Retorna el interrupcionBO.
	 */
	public InterrupcionBO getInterrupcionBO() {
		return interrupcionBO;
	}
	/**
	 * Metodo modificador de interrupcionBO.
	 * @param interrupcionBO El interrupcionBO a modificar.
	 */
	public void setInterrupcionBO(InterrupcionBO interrupcionBO) {
		this.interrupcionBO = interrupcionBO;
	}
	/**
	 * Metodo accesor de reporteBO.
	 * @return Retorna el reporteBO.
	 */
	public ReporteBO getReporteBO() {
		return reporteBO;
	}
	/**
	 * Metodo modificador de reporteBO.
	 * @param reporteBO El reporteBO a modificar.
	 */
	public void setReporteBO(ReporteBO reporteBO) {
		this.reporteBO = reporteBO;
	}
	/**
	 * Metodo accesor de empleadoBO.
	 * @return Retorna el empleadoBO.
	 */
	public EmpleadoBO getEmpleadoBO() {
		return empleadoBO;
	}
	/**
	 * Metodo modificador de empleadoBO.
	 * @param empleadoBO El empleadoBO a modificar.
	 */
	public void setEmpleadoBO(EmpleadoBO empleadoBO) {
		this.empleadoBO = empleadoBO;
	}
	
	/**
	 * De acuerdo a los parametros introducidos por el usuario, realiza validaciones y determina la existencia 
	 * de una interrupción para la correspondiente modificación
	 * @return <code>String</code> success o error de acuerdo al resultado de la validación
	 */
	public String modificar(){
		FacesContext ctx = FacesContext.getCurrentInstance();
		
		if(this.oficina == null || this.oficina.intValue() <= 0){
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La oficina es requerida."));

			return "error";
		}	
		if((this.mas5 == null || this.mas5.booleanValue() == false) && (this.menos5 == null || this.menos5.booleanValue() == false)){
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar el tipo de interrupción."));

			return "error";
		}		
		if(this.consecutivo == null || this.consecutivo.longValue() < 0){
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El consecutivo es requerido."));

			return "error";
		}	

		
		if(this.mas5.booleanValue()){
			InterrupcionID interrupcionID = new InterrupcionID();
			interrupcionID.setAa(this.ano);
			interrupcionID.setCodigoOficina(this.oficina);
			interrupcionID.setNumeroInterrupcion(this.consecutivo);
			if(this.interrupcionBO.interrupcionExiste(interrupcionID) == false){
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La interrupción no existe en la base de datos."));

				return "error";
			}else{				
				Interrupcion interrupcion = this.interrupcionBO.getInterrupcion(interrupcionID);
				Date fehaInicio = UtilidadesI.getFechaCompleta(interrupcion.getFechaInicio(), interrupcion.getHoraInicio().doubleValue());
				if(this.aplicarValidaciones(fehaInicio) == false)
					return "error";
			}
			
		}
		if(this.menos5.booleanValue()){
			ReporteID reporteID = new ReporteID();
			reporteID.setAa(this.ano);
			reporteID.setCodigoOficina(this.oficina);
			reporteID.setNumeroReporte(this.consecutivo);
			if(this.reporteBO.reporteExiste(reporteID) == false){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La interrupción no existe en la base de datos."));

				return "error";
			}else{
				Reporte reporte = this.reporteBO.getReporte(reporteID);
				Date fehaInicio = UtilidadesI.getFechaCompleta(reporte.getFechaInicio(), reporte.getHoraInicio().doubleValue());
				if(this.aplicarValidaciones(fehaInicio) == false)
					return "error";
			}
		}
		
		System.out.println("Valores: " + this.ano + " - " + this.oficina + " - " + this.consecutivo);

        ctx.getExternalContext().getSessionMap().put("modificar", Boolean.TRUE);   
		ctx.getExternalContext().getSessionMap().put("ano",this.ano);		 
	    ctx.getExternalContext().getSessionMap().put("oficina",this.oficina);
	    ctx.getExternalContext().getSessionMap().put("consecutivo",this.consecutivo);
	    if(this.mas5.booleanValue())
	    	return "mas5min";
	    else
	    	return "menos5min";
	}

	
	/*
	 * Aplica las validaciones determinando si la interrupcion se encuentra en un periodo valido para su correspondiente modificacion
	 * @return true o false
	 */
	private boolean aplicarValidaciones(Date fechaInicioInterrupcion){
		boolean correcto = true;		
		
		GregorianCalendar gc = new GregorianCalendar();		
        gc.setTime(fechaInicioInterrupcion);
        double diferencia = UtilidadesI.getTiempoTranscurridoDespuesDia5MesSiguiente(gc);
        
		List parametros = parametroBO.getParametros();
		Parametro parametro = null;
		if(parametros != null && parametros.size() > 0)
			parametro = (Parametro)parametros.get(0);
		if((diferencia >= 0 && parametro == null) || (diferencia >= 0 && parametro.getParametroID().getGeneracionIndices().equals("S") == false)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No puede modificar una interrupcion después del día 5 del mes siguiente en el cual ocurrió la interrupción."));
			return false;
		} 
		
		GregorianCalendar fechaInicioInter = new GregorianCalendar();		
		fechaInicioInter.setTime(fechaInicioInterrupcion);
		
		if(diferencia >= 0 && (parametro != null && parametro.getParametroID().getGeneracionIndices().equals("S") == true) && UtilidadesI.esMesAnterior(fechaInicioInter) == false){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No puede modificar una interrupción congelada."));

			return false;
		}
		
		
		return correcto;
	}

	/**
	 * 
	 * Cancela el proceso de modificación
	 * @return success para cancelar el proceso
	 */
	public String cancelar(){
		return "success";
	}



	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getOficinasSelectItems()
	{

       
        
    	List selectItems = new ArrayList();
    	List<UsuarioOficina> listaPivote = this.usuarioOficinaBO.buscarCedula(this.cedula);
    	List oficinas = this.oficinaBO.getOficinas();
    	
    	selectItems.add(new SelectItem(new Integer(0), "Seleccione una oficina"));
    	
    	for(int i=0; i<oficinas.size();i++)
    	{
    		for(int o=0; o<listaPivote.size(); o++)
    		{
        		Oficina oficina = (Oficina) oficinas.get(i);
        		if(oficina.getCodigoOficina() == listaPivote.get(o).getId().getOficina().getCodigoOficina())
        		{
        			selectItems.add(new SelectItem(oficina.getCodigoOficina(), oficina.getCodigoOficina() +" - "+ oficina.getNombreOficina()));
        		}
    		}
    	}
    	return selectItems;
    }
	
	
    /**
     * Metodo accesor de comboOficina.
     * @return Retorna el comboOficina.
     */
    public Boolean getComboOficina() {
        return this.comboOficina;
    }
    /**
     * Metodo modificador de comboOficina.
     * @param comboOficina El comboOficina a modificar.
     */
    public void setComboOficina(Boolean comboOficina) {
        this.comboOficina = comboOficina;
    }

	public UsuarioOficinaBO getUsuarioOficinaBO() {
		return usuarioOficinaBO;
	}

	public void setUsuarioOficinaBO(UsuarioOficinaBO usuarioOficinaBO) {
		this.usuarioOficinaBO = usuarioOficinaBO;
	}

	public OficinaBO getOficinaBO() {
		return oficinaBO;
	}

	public void setOficinaBO(OficinaBO oficinaBO) {
		this.oficinaBO = oficinaBO;
	}

	public Long getCedula() {
		return cedula;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
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
