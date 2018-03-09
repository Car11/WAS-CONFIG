
package cr.go.ice.interrupciones.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import com.vvs.jsf.AbstractFacesController;
import com.vvs.utilidades.Fechas;

import cr.go.ice.interrupciones.BO.NoPropiaSeccionamientoBO;
import cr.go.ice.interrupciones.BO.OficinaBO;
import cr.go.ice.interrupciones.BO.SeccionBO;
import cr.go.ice.interrupciones.domain.*;
import cr.go.ice.interrupciones.utils.UtilidadesI;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.SeccionamientoController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SeccionamientoController.java</code> Establese la relacion entre capa de presentación y la lógica del negocio de los seccionamientos pertenecientes a interrupciones mayores a cinco minutos.</p>
 * <p>Fecha creación: 15/04/2007</p>
 * <p>Ultima actualización: 15/04/2007</p>
 * @author Vista Verde Soft (David)
 * @version 1.1
 */
public class SeccionamientoController extends AbstractFacesController {
	/**
	 * <code>dataTableSeccionamientos</code> Tabla componente de JSF asociada a la lista de seccionamientos
	 */	
	private DataTable dataTableSeccionamientos;
	/**
	 * <code>seccion</code> seccion
	 */	
	private Seccion seccion;
	/**
	 * <code>oficina</code> oficina
	 */	
	private Oficina oficina;
	/**
	 * <code>subEstacion</code> 
	 */	
	private SubEstacion subEstacion;
	/**
	 * <code>circuito</code> circuito
	 */	
	private Circuito circuito;
	/**
	 * <code>seccionBO</code> seccionBO
	 */	
	private SeccionBO seccionBO;
	/**
	 * <code>tipoVoltaje</code> tipoVoltaje
	 */	
	private TipoVoltaje tipoVoltaje;
	/**
	 * <code>noPropiaSeccionamientoBO</code> noPropiaSeccionamientoBO
	 */		
	private NoPropiaSeccionamientoBO noPropiaSeccionamientoBO;
	/**
	 * <code>seccionReal</code> seccionReal
	 */
	private Seccion seccionReal;
	/**
	 * <code>fechaInicioInterrupcion</code> fechaInicioInterrupcion
	 */
	private Date fechaInicioInterrupcion;
	/**
	 * <code>fechaFinalInterrupcion</code> fechaFinalInterrupcion
	 */
	private Date fechaFinalInterrupcion;
	/**
	 * <code>bitacora</code> bitacora
	 */
	private Boolean bitacora;
	/**
	 * <code>trifasica</code> trifasica
	 */
	private Boolean trifasica;
	/**
	 * <code>subestacion</code> subestacion
	 */
	private Boolean subestacion;
	/**
	 * <code>lugarInterrupcion</code> lugarInterrupcion
	 */
	private String lugarInterrupcion;
	/**
	 * <code>observaciones</code> observaciones
	 */
	private String observaciones;
	/**
	 * <code>seccionamientos</code> seccionamientos
	 */	
	private List seccionamientos;
	/**
	 * <code>clientesAfectados</code> clientesAfectados
	 */
	private Long clientesAfectados;
	/**
	 * <code>clientes</code> clientes
	 */
	private Long clientes;	
	/**
	 * <code>saleCircuito</code> saleCircuito
	 */
	private Integer saleCircuito;
	/**
	 * <code>operacion</code> operacion
	 */
	private Integer operacion;
	/**
	 * <code>primeraVez</code> primeraVez
	 */
	private boolean primeraVez;
	/**
	 * <code>codigoVoltaje</code>codigoVoltaje
	 */
	private Integer codigoVoltaje;
	/**
	 * <code>consecutivoInterrupcion</code> consecutivoInterrupcion
	 */
	private String consecutivoInterrupcion;
	/**
	 * <code>oficinaBO</code> oficina BO
	 */	
	private OficinaBO oficinaBO;
	/**
	 * <code>codigoOficina</code> codigo Oficina
	 */		
	private Integer codigoOficina;
	/**
	 * <code>accion</code> accion popup
	 */	
	private String accion;
	
	/**
	 * Constructor por defecto.  Inicializa los atributos necesarios para los seccionamientos de las interrupciones mayores a cinco minutos.
	 */
	public SeccionamientoController(){	
        FacesContext ctx = FacesContext.getCurrentInstance();
        Map sessionMap = ctx.getExternalContext().getSessionMap();        
        this.operacion = (Integer) sessionMap.get("opera");
        this.codigoOficina = (Integer) sessionMap.get("oficina");        
        this.consecutivoInterrupcion = (String) sessionMap.get("consecutivoInterrupcion");
        this.subEstacion = (SubEstacion) sessionMap.get("subestacion");
        this.circuito = (Circuito) sessionMap.get("circuito");
        this.fechaInicioInterrupcion = (Date)sessionMap.get("fechaInicioInterrupcion");
        this.fechaFinalInterrupcion = (Date)sessionMap.get("fechaFinalInterrupcion");
        this.clientesAfectados = (Long)sessionMap.get("clientesAfectados");
        this.lugarInterrupcion = (String)sessionMap.get("lugarInterrupcion");
        this.observaciones = (String)sessionMap.get("observaciones");
        this.saleCircuito = (Integer)sessionMap.get("saleCircuito");
        this.seccionReal = (Seccion)sessionMap.get("seccion");
        this.codigoVoltaje = (Integer)sessionMap.get("codigoVoltaje");  
		     
        this.clientes = this.clientesAfectados;                               
        this.bitacora = Boolean.valueOf(false);
        this.trifasica = Boolean.valueOf(false);
        this.subestacion = Boolean.valueOf(false);
        this.seccion = new Seccion();       
        this.primeraVez = true;    
	}
	
	
	
	public String getInit(){
		FacesContext ctx = FacesContext.getCurrentInstance();
        Map sessionMap = ctx.getExternalContext().getSessionMap();
		Boolean isModificar = (Boolean)sessionMap.get("isModificar");
        if(!isModificar)
        {
        	this.resetController();
        }
        return "success";
	}
	/**
	 * @return Devuelve seccion.
	 */
	public Seccion getSeccion() {
		return seccion;
	}
	/**
	 * @param seccion El seccion a establecer.
	 */
	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;		
	}
	/**
	 * Metodo accesor de circuito.
	 * @return Retorna el circuito.
	 */
	public Circuito getCircuito() {
		return circuito;
	}
	/**
	 * Metodo modificador de circuito.
	 * @param circuito El circuito a modificar.
	 */
	public void setCircuito(Circuito circuito) {
		this.circuito = circuito;
	}
	/**
	 * Metodo accesor de consecutivoInterrupcion.
	 * @return Retorna el consecutivoInterrupcion.
	 */
	public String getConsecutivoInterrupcion() {
	    this.accion = "";
		FacesContext ctx = FacesContext.getCurrentInstance();
        Map sessionMap = ctx.getExternalContext().getSessionMap();
        boolean ind = false;
        		
        this.operacion = (Integer) sessionMap.get("opera");        
        if(this.operacion.intValue() == 1 || this.operacion.intValue() == 0 || this.operacion.intValue() == 2){  
        	ctx.getExternalContext().getSessionMap().put("opera",new Integer(3));
        	
        	this.codigoOficina = (Integer) sessionMap.get("oficina");
            this.oficina = this.oficinaBO.buscar(this.codigoOficina);
            this.consecutivoInterrupcion = (String) sessionMap.get("consecutivoInterrupcion");
            this.subEstacion = (SubEstacion) sessionMap.get("subestacion");
            this.circuito = (Circuito) sessionMap.get("circuito");
            this.fechaInicioInterrupcion = (Date)sessionMap.get("fechaInicioInterrupcion");
            this.fechaFinalInterrupcion = (Date)sessionMap.get("fechaFinalInterrupcion");
            this.clientesAfectados = (Long)sessionMap.get("clientesAfectados");
            this.lugarInterrupcion = (String)sessionMap.get("lugarInterrupcion");
            this.observaciones = (String)sessionMap.get("observaciones");            
            Integer saleAux = (Integer)sessionMap.get("saleCircuito");
            if(this.saleCircuito.intValue() != saleAux.intValue() && saleAux.intValue() == 2)
            	ind = true;
            this.saleCircuito = saleAux;
            this.seccionReal = (Seccion)sessionMap.get("seccion"); 
            this.codigoVoltaje = (Integer)sessionMap.get("codigoVoltaje");                  
            this.clientes = this.clientesAfectados;    
        	this.primeraVez = true;                	
            try{            	
            		this.seccionamientos = (ArrayList) sessionMap.get("seccionamientos");
            		if(this.seccionamientos != null && this.seccionamientos.size() > 0){
            			primeraVez = false;
        			    if(existenCambiosSeccionamientosEnCircuito()){
            				this.cargarSecciones();
            				String mensaje = "Las secciones fueron cargadas nuevamente ya que el circuito o sección fue modificado";
            		        this.accion = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";            		        
        			    }            		
            			else{ 
                			if(ind == true){
                			    long cantidadSecciones = this.seccionBO.getSeccionesEscalar(this.subEstacion.getCodigoSubEstacion(),this.circuito.getCircuitoID().getCircuito());
                			    cantidadSecciones = cantidadSecciones - 1;
                			    if(cantidadSecciones > this.seccionamientos.size()){
    	            				this.cargarSecciones();
    	            				String mensaje = "Las secciones fueron cargadas nuevamente ya que se indicó que el circuito sale totalmente";
    	            		        this.accion = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";
                			    }
                			}
                			else{
    	            			if(existenCambiosSeccionamientosEnFechas()){
    	            				String mensaje = "A algunas de las secciones se les modificó sus rangos de fechas debido a que las fechas de la interrupción madre fueron modificadas";
    	            		        this.accion = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";    	            			
    	            			}    	            			    
    	            		}            			    
            			}               			
            		}
            }
            catch(Exception e){
            	this.seccionamientos = new ArrayList();
            }      
        }
        this.oficina = this.oficinaBO.buscar(this.codigoOficina);
		return consecutivoInterrupcion;
	}
	
	

	
	/**
	 * Determina si se produjeron cambios en el tramo de red de la interrupción madre
	 * @return <code>true</code> o <code>false</code> si se produjeron cambios o no
	 */
	private boolean existenCambiosSeccionamientosEnCircuito(){
	    NoPropiaSeccionamiento seccionamiento = null;
	    if(this.seccionamientos != null && this.seccionamientos.size() > 0){
	        for(int i = 0; i < this.seccionamientos.size(); i++){
		        seccionamiento = (NoPropiaSeccionamiento)this.seccionamientos.get(i);
	            if(seccionamiento.getSubestacion().intValue() != this.subEstacion.getCodigoSubEstacion().intValue() || 
	               seccionamiento.getCircuito().intValue() != this.circuito.getCircuitoID().getCircuito().intValue() ||
	               seccionamiento.getNoPropiaSeccionamientoID().getSeccion().longValue() == this.seccionReal.getSeccionID().getSeccion().longValue())
	                return true;
	        }
	    }
	    return false;
	}	
	
	/**
	 * Determina si se produjeron cambios en las fechas de la interrupción madre
	 * @return <code>true</code> o <code>false</code> si se produjeron cambios o no
	 */	
	private boolean existenCambiosSeccionamientosEnFechas(){	    
	    NoPropiaSeccionamiento seccionamiento = null;
	    List resultadoList = new ArrayList();
	    boolean cambios = false;
	    if(this.seccionamientos != null && this.seccionamientos.size() > 0){
	        for(int i = 0; i < this.seccionamientos.size(); i++){
	            seccionamiento = (NoPropiaSeccionamiento)this.seccionamientos.get(i);
				double horasInicio = Fechas.millisToMinutes(this.fechaFinalInterrupcion.getTime());
				double horasFinal = Fechas.millisToMinutes(seccionamiento.getFechaFin().getTime());
				double diferencia = horasFinal - horasInicio;					            
	            if(seccionamiento.getFechaInicio().equals(this.fechaInicioInterrupcion) != true || diferencia > 0){	    			
	    			seccionamiento.setFechaInicio(this.fechaInicioInterrupcion);
	    			seccionamiento.setFechaFin(this.fechaFinalInterrupcion);
	    			horasInicio = Fechas.millisToMinutes(seccionamiento.getFechaInicio().getTime());
	    			horasFinal = Fechas.millisToMinutes(seccionamiento.getFechaFin().getTime());
	    			diferencia = (horasFinal - horasInicio) / 60.0;
                    diferencia = UtilidadesI.roundNum(diferencia);
	    			seccionamiento.setTiefue(new Double(diferencia));
	    			double horasAbonado = diferencia * seccionamiento.getAbonadosAfectados().longValue();
                    horasAbonado = UtilidadesI.roundNum(horasAbonado);
	    			seccionamiento.setHorasAbonado(new Double(horasAbonado));
                    
                    SimpleDateFormat formater = new SimpleDateFormat("HH.mm");
                    String hora = formater.format(seccionamiento.getFechaInicio());
                    double resultado = Double.parseDouble(hora.substring(0,2));
                    double min = Double.parseDouble(hora.substring(3,5));
                    min = min / 100.0;
                    resultado = resultado + min;                            
                    seccionamiento.setHoraInicio(new Double(resultado));  
                    
	    			cambios = true;
	            }
                resultadoList.add(seccionamiento);
	           
	        }
	    }
	    this.seccionamientos = new ArrayList(resultadoList);
	    return cambios;
	}		
	/**
	 * Metodo modificador de consecutivoInterrupcion.
	 * @param consecutivoInterrupcion El consecutivoInterrupcion a modificar.
	 */
	public void setConsecutivoInterrupcion(String consecutivoInterrupcion) {
		this.consecutivoInterrupcion = consecutivoInterrupcion;
	}
	/**
	 * Metodo accesor de oficina.
	 * @return Retorna el oficina.
	 */
	public Oficina getOficina() {
		return oficina;
	}
	/**
	 * Metodo modificador de oficina.
	 * @param oficina El oficina a modificar.
	 */
	public void setOficina(Oficina oficina) {
		this.oficina = oficina;
	}
	/**
	 * Metodo accesor de subEstacion.
	 * @return Retorna el subEstacion.
	 */
	public SubEstacion getSubEstacion() {
		return subEstacion;
	}
	/**
	 * Metodo modificador de subEstacion.
	 * @param subEstacion El subEstacion a modificar.
	 */
	public void setSubEstacion(SubEstacion subEstacion) {
		this.subEstacion = subEstacion;
	}
	/**
	 * Metodo accesor de seccionBO.
	 * @return Retorna el seccionBO.
	 */
	public SeccionBO getSeccionBO() {
		return seccionBO;
	}
	/**
	 * Metodo modificador de seccionBO.
	 * @param seccionBO El seccionBO a modificar.
	 */
	public void setSeccionBO(SeccionBO seccionBO) {
		this.seccionBO = seccionBO;
	}
	/**
	 * Metodo accesor de bitacora.
	 * @return Retorna el bitacora.
	 */
	public Boolean getBitacora() {
		return bitacora;
	}
	/**
	 * Metodo modificador de bitacora.
	 * @param bitacora El bitacora a modificar.
	 */
	public void setBitacora(Boolean bitacora) {
		this.bitacora = bitacora;
	}
	/**
	 * Metodo accesor de clientesAfectados.
	 * @return Retorna el clientesAfectados.
	 */
	public Long getClientesAfectados() {
		return clientesAfectados;
	}
	/**
	 * Metodo modificador de clientesAfectados.
	 * @param clientesAfectados El clientesAfectados a modificar.
	 */
	public void setClientesAfectados(Long clientesAfectados) {
		this.clientesAfectados = clientesAfectados;
	}
	/**
	 * Metodo accesor de fechaFinalInterrupcion.
	 * @return Retorna el fechaFinalInterrupcion.
	 */
	public Date getFechaFinalInterrupcion() {
		return fechaFinalInterrupcion;
	}
	/**
	 * Metodo modificador de fechaFinalInterrupcion.
	 * @param fechaFinalInterrupcion El fechaFinalInterrupcion a modificar.
	 */
	public void setFechaFinalInterrupcion(Date fechaFinalInterrupcion) {
		this.fechaFinalInterrupcion = fechaFinalInterrupcion;
	}
	/**
	 * Metodo accesor de fechaInicioInterrupcion.
	 * @return Retorna el fechaInicioInterrupcion.
	 */
	public Date getFechaInicioInterrupcion() {
		return fechaInicioInterrupcion;
	}
	/**
	 * Metodo modificador de fechaInicioInterrupcion.
	 * @param fechaInicioInterrupcion El fechaInicioInterrupcion a modificar.
	 */
	public void setFechaInicioInterrupcion(Date fechaInicioInterrupcion) {
		this.fechaInicioInterrupcion = fechaInicioInterrupcion;
	}
	/**
	 * Metodo accesor de lugarInterrupcion.
	 * @return Retorna el lugarInterrupcion.
	 */
	public String getLugarInterrupcion() {
		return lugarInterrupcion;
	}
	/**
	 * Metodo modificador de lugarInterrupcion.
	 * @param lugarInterrupcion El lugarInterrupcion a modificar.
	 */
	public void setLugarInterrupcion(String lugarInterrupcion) {
		this.lugarInterrupcion = lugarInterrupcion;
	}
	/**
	 * Metodo accesor de observaciones.
	 * @return Retorna el observaciones.
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * Metodo modificador de observaciones.
	 * @param observaciones El observaciones a modificar.
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	/**
	 * Metodo accesor de tipoVoltaje.
	 * @return Retorna el tipoVoltaje.
	 */
	public TipoVoltaje getTipoVoltaje() {
		return tipoVoltaje;
	}
	/**
	 * Metodo modificador de tipoVoltaje.
	 * @param tipoVoltaje El tipoVoltaje a modificar.
	 */
	public void setTipoVoltaje(TipoVoltaje tipoVoltaje) {
		this.tipoVoltaje = tipoVoltaje;
	}
	/**
	 * Metodo accesor de trifasica.
	 * @return Retorna el trifasica.
	 */
	public Boolean getTrifasica() {
		return trifasica;
	}
	/**
	 * Metodo modificador de trifasica.
	 * @param trifasica El trifasica a modificar.
	 */
	public void setTrifasica(Boolean trifasica) {
		this.trifasica = trifasica;
	}
	/**
	 * Metodo accesor de dataTableSeccionamientos.
	 * @return Retorna el dataTableSeccionamientos.
	 */
	public DataTable getDataTableSeccionamientos() {
		return dataTableSeccionamientos;
	}
	/**
	 * Metodo modificador de dataTableSeccionamientos.
	 * @param dataTableSeccionamientos El dataTableSeccionamientos a modificar.
	 */
	public void setDataTableSeccionamientos(
			DataTable dataTableSeccionamientos) {
		this.dataTableSeccionamientos = dataTableSeccionamientos;
	}
	/**
	 * Metodo accesor de seccionamientos.
	 * @return Retorna el seccionamientos.
	 */
	public List getSeccionamientos() {
		return seccionamientos;
	}
	/**
	 * Metodo modificador de seccionamientos.
	 * @param seccionamientos El seccionamientos a modificar.
	 */
	public void setSeccionamientos(List seccionamientos) {
		this.seccionamientos = seccionamientos;
	}
	/**
	 * Metodo accesor de noPropiaSeccionamientoBO.
	 * @return Retorna el noPropiaSeccionamientoBO.
	 */
	public NoPropiaSeccionamientoBO getNoPropiaSeccionamientoBO() {
		return noPropiaSeccionamientoBO;
	}
	/**
	 * Metodo modificador de noPropiaSeccionamientoBO.
	 * @param noPropiaSeccionamientoBO El noPropiaSeccionamientoBO a modificar.
	 */
	public void setNoPropiaSeccionamientoBO(
			NoPropiaSeccionamientoBO noPropiaSeccionamientoBO) {
		this.noPropiaSeccionamientoBO = noPropiaSeccionamientoBO;
	}
	
	/**
	 * Metodo accesor de subestacion.
	 * @return Retorna el subestacion.
	 */
	public Boolean getSubestacion() {
		return subestacion;
	}
	/**
	 * Metodo modificador de subestacion.
	 * @param subestacion El subestacion a modificar.
	 */
	public void setSubestacion(Boolean subestacion) {
		this.subestacion = subestacion;
	}	
		
	/**
	 * Retorna una lista de seccionamientos,ya sea obtenidos de la base de datos u obtenidos de memoria.
	 * @return Lista de los seccionamientos
	 */
	public List getListaSeccionamientos(){       
		
		if(primeraVez){
			NoPropiaSeccionamientoID noPropiaSeccionamientoID = new NoPropiaSeccionamientoID();
            FacesContext ctx = FacesContext.getCurrentInstance();
            Map sessionMap = ctx.getExternalContext().getSessionMap();
            this.seccionamientos = new ArrayList<>();
            Boolean isModificar = (Boolean)sessionMap.get("isModificar");
            if(isModificar.booleanValue()){
    			String valoresLlave[] = this.consecutivoInterrupcion.split("-");				
    			noPropiaSeccionamientoID.setAa(new Integer(valoresLlave[0].trim()));
    			noPropiaSeccionamientoID.setCodigoOficina(new Integer(valoresLlave[1].trim()));
    			noPropiaSeccionamientoID.setNumeroInterrupcion(new Long(valoresLlave[2].trim()));
                this.seccionamientos = this.noPropiaSeccionamientoBO.getNoPropiaSeccionamientos(noPropiaSeccionamientoID);
                if(this.seccionamientos.size() == 0){
                    this.seccionamientos = new ArrayList();
                    this.cargarSecciones();
                }           
                else{
                    darFormatoFecha();
                }
            }else{
                this.cargarSecciones();
            }
			
			this.primeraVez = false; 
		}
		
		return this.seccionamientos;
	}	
	
	/**
	 * Método que da formato a la fecha, especificamente a la hora para presentarla en la interfaz de usuario.
	 * Esto por cuanto la fecha no se puede almacenar en la base de datos con su correspondiente hora.
	 *
	 */
	private void darFormatoFecha(){
		double horas = 0;		
		Double minutos;
		Double hora;
		GregorianCalendar gc;
		List resultado = new ArrayList();		
		for(int i = 0; i < this.seccionamientos.size(); i++){
			NoPropiaSeccionamiento seccionamiento = (NoPropiaSeccionamiento)this.seccionamientos.get(i);
			
			horas = seccionamiento.getHoraInicio().doubleValue();			
			gc = new GregorianCalendar();
			gc.setTime(seccionamiento.getFechaInicio());
			hora = new Double(horas);
			gc.set(GregorianCalendar.HOUR_OF_DAY,hora.intValue());
			if(horas != 0){
				double min = horas - hora.intValue();
				minutos = new Double(min * 100);
			}
			else
				minutos = new Double(0);
			long minute = Math.round(minutos.doubleValue());
			minutos = new Double(minute);
			gc.set(GregorianCalendar.MINUTE,minutos.intValue());
				
			seccionamiento.setFechaInicio(gc.getTime());
			
			horas = seccionamiento.getHoraFin().doubleValue();			
			gc = new GregorianCalendar();
			gc.setTime(seccionamiento.getFechaFin());
			hora = new Double(horas);
			gc.set(GregorianCalendar.HOUR_OF_DAY,hora.intValue());
			if(horas != 0){
				double min = horas - hora.intValue();
				minutos = new Double(min * 100);
			}
			else
				minutos = new Double(0);
			minute = Math.round(minutos.doubleValue());
			minutos = new Double(minute);
			gc.set(GregorianCalendar.MINUTE,minutos.intValue());
			seccionamiento.setFechaFin(gc.getTime());
			
			resultado.add(seccionamiento);
		}
		this.seccionamientos = new ArrayList(resultado);
	}			
	
	/**
	 * Permite la acción de regresar del mantenimiento de seccionamientos
	 * @return String "success"
	 */
	public String regresarInterup(){
		String resultado = this.actualizarDatosTabla();
        FacesContext ctx = FacesContext.getCurrentInstance();
       
        ctx.getExternalContext().getSessionMap().put("seccionamientos",this.seccionamientos);
        this.accion = "";
        int seccionamientos = this.seccionamientos.size();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Se ingresaron " + seccionamientos + " seccionamientos."));
        return resultado;
	}		
	
	
	/**
	 * Método para cargar la secciones asociadas al circuito de la interrupción madre cuando el usuario 
	 * selecciona que el circutio sale totalmente.
	 */	
	private void cargarSecciones(){
		double horasInicio = 0;
		double horasFinal = 0;
		double diferencia = 0;
		int i = -1;
		int filas = 0;
		this.seccionamientos = new ArrayList();
		List secciones = this.seccionBO.getSecciones(this.subEstacion.getCodigoSubEstacion(),this.circuito.getCircuitoID().getCircuito());
		if(secciones != null){
			for(i = 0; i < secciones.size(); i++){
					Seccion miSeccion = (Seccion) secciones.get(i);
					if(miSeccion.equals(this.seccionReal) == false){
						filas = filas + 1 ;
						/********************************************************************************/
						NoPropiaSeccionamiento seccionamiento = new NoPropiaSeccionamiento();
						NoPropiaSeccionamientoID seccionamientoID = new NoPropiaSeccionamientoID();		
						seccionamientoID.setAa(null);
						seccionamientoID.setCodigoOficina(null);
						seccionamientoID.setNumeroInterrupcion(null);
						seccionamientoID.setSeccion(miSeccion.getSeccionID().getSeccion());		
						seccionamiento.setNoPropiaSeccionamientoID(seccionamientoID);												
						seccionamiento.setRegion(miSeccion.getRegion());
						seccionamiento.setSubregion(miSeccion.getSubRegion());								
						seccionamiento.setSubestacion(this.subEstacion.getCodigoSubEstacion());
						seccionamiento.setCircuito(this.circuito.getCircuitoID().getCircuito());
						seccionamiento.setFechaInicio(this.fechaInicioInterrupcion);
						seccionamiento.setFechaFin(this.fechaFinalInterrupcion);
						seccionamiento.setAbonadosAfectados(miSeccion.getAbonadoSeccion());							
						seccionamiento.setNombreSeccion(miSeccion.getSeccionID().getSeccion() + " - " + miSeccion.getNombreSeccion());							
						seccionamiento.setCodigoVoltaje(this.codigoVoltaje);
						int codigoVoltaje = this.codigoVoltaje.intValue();
						if(codigoVoltaje == 10)
							seccionamiento.setTipoVoltaje(new Integer(1));
						else{
							if(codigoVoltaje == 20 || codigoVoltaje == 30 || codigoVoltaje == 40){
								seccionamiento.setTipoVoltaje(new Integer(2));
							}
							else{
								seccionamiento.setTipoVoltaje(new Integer(3));
							}
						}																				
						horasInicio = Fechas.millisToMinutes(this.fechaInicioInterrupcion.getTime());
						horasFinal = Fechas.millisToMinutes(this.fechaFinalInterrupcion.getTime());
						diferencia = (horasFinal - horasInicio) / 60.0;
                        diferencia = UtilidadesI.roundNum(diferencia);
						seccionamiento.setTiefue(new Double(diferencia));
						double horasAbonado = diferencia * miSeccion.getAbonadoSeccion().longValue();
                        horasAbonado = UtilidadesI.roundNum(horasAbonado);
						seccionamiento.setHorasAbonado(new Double(horasAbonado));		
						
						if(this.trifasica.booleanValue())
							seccionamiento.setTrifasica(new Integer(1));
						else
							seccionamiento.setTrifasica(new Integer(0));
						
						if(this.bitacora.booleanValue())
							seccionamiento.setBitacora(new Integer(1));
						else
							seccionamiento.setBitacora(new Integer(0));
						
						seccionamiento.setLugar(this.lugarInterrupcion.trim().toUpperCase());																		
						
						SimpleDateFormat formater = new SimpleDateFormat("HH.mm");
						String hora = formater.format(this.fechaInicioInterrupcion);
						double resultado = Double.parseDouble(hora.substring(0,2));
						double min = Double.parseDouble(hora.substring(3,5));
						min = min / 100.0;
						resultado = resultado + min;							
						seccionamiento.setHoraInicio(new Double(resultado));
						
						formater = new SimpleDateFormat("HH.mm");
						hora = formater.format(this.fechaFinalInterrupcion);
						resultado = Double.parseDouble(hora.substring(0,2));
						min = Double.parseDouble(hora.substring(3,5));
						min = min / 100.0;
						resultado = resultado + min;						
						seccionamiento.setHoraFin(new Double(resultado));
						
						this.seccionamientos.add(seccionamiento);		
						/********************************************************************************/	
					}
			}
		}
				
					
	}	
	
	/**
	 * Actualiza los datos de la tabla de acuerdo a los datos de entrada dados por el usuario
	 * @return error o success de acuerdo a la correctitud de los datos
	 */
	public String actualizarDatosTabla(){
	    this.accion = "";
		
		int filas = this.dataTableSeccionamientos.getRowCount();
		List lista = new ArrayList();
		Date dummy = new Date();
		
		for(int i = 0; i < filas; i++){
			int fila = i + 1;
			this.dataTableSeccionamientos.setRowIndex(i);
			NoPropiaSeccionamiento seccionamiento = (NoPropiaSeccionamiento)this.dataTableSeccionamientos.getRowData();
            if(seccionamiento.isSeleccion()){
    			if(seccionamiento.getFechaFin() == null || seccionamiento.getFechaFin().equals(dummy)){
    				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error en la fila " + fila + " de la sección  " + seccionamiento.getNombreSeccion() + ".  La fecha de fin es requerida."));
    		        return "error";
    			}	
    			
    			double horasInicio = Fechas.millisToMinutes(this.fechaFinalInterrupcion.getTime());
    			double horasFinal = Fechas.millisToMinutes(seccionamiento.getFechaFin().getTime());
    			double diferencia = horasFinal - horasInicio;
    			if(diferencia > 0){
    				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error en la fila " + fila + " de la sección  " + seccionamiento.getNombreSeccion() + " .  La fecha de fin debe ser menor o igual a la de interrupción madre."));
    		       return "error";
    			}	
    			
    			horasInicio = Fechas.millisToMinutes(this.fechaInicioInterrupcion.getTime());
    			horasFinal = Fechas.millisToMinutes(seccionamiento.getFechaFin().getTime());
    			diferencia = horasFinal - horasInicio;
    			if(diferencia < 0){
    				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error en la fila " + fila + " de la sección  " + seccionamiento.getNombreSeccion() + " .  La fecha de inicio debe ser menor o igual a la de fin."));
    		        
    				return "error";
    			}	
    			
    			if(seccionamiento.getAbonadosAfectados() == null || seccionamiento.getAbonadosAfectados().longValue() <= 0){
    				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error en la fila " + fila + " de la sección  " + seccionamiento.getNombreSeccion() + " .  La cantidad de clientes afectados es requerida."));
    		        
    				return "error";
    			}		
    			
    			SeccionID seccionID = new SeccionID();
    			seccionID.setCircuito(seccionamiento.getCircuito());
    			seccionID.setSubEstacion(seccionamiento.getSubestacion());
    			seccionID.setSeccion(seccionamiento.getNoPropiaSeccionamientoID().getSeccion());
    			this.clientes = this.seccionBO.buscar(seccionID).getAbonadoSeccion();
    			long clientesAfectados = seccionamiento.getAbonadosAfectados().longValue();
    			if(clientesAfectados > this.clientes.longValue()){
    				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error en la fila " + fila + " de la sección  " + seccionamiento.getNombreSeccion() + " .  La cantidad de clientes afectados debe ser menor de " + this.clientes));
    		       
    				return "error";
    			}
            }
		}
		
		for(int i = 0; i < filas; i++){
			this.dataTableSeccionamientos.setRowIndex(i);
			NoPropiaSeccionamiento seccionamiento = (NoPropiaSeccionamiento)this.dataTableSeccionamientos.getRowData();
			if(seccionamiento.isSeleccion()){
    			int codigoVoltaje = seccionamiento.getCodigoVoltaje().intValue();
    			if(codigoVoltaje == 10)
    				seccionamiento.setTipoVoltaje(new Integer(1));
    			else{
    				if(codigoVoltaje == 20 || codigoVoltaje == 30 || codigoVoltaje == 40){
    					seccionamiento.setTipoVoltaje(new Integer(2));
    				}
    				else{
    					seccionamiento.setTipoVoltaje(new Integer(3));
    				}
    			}
    			
    			SimpleDateFormat formater = new SimpleDateFormat("HH.mm");
    			String hora = formater.format(seccionamiento.getFechaFin());
    			double resultado = Double.parseDouble(hora.substring(0,2));
    			double min = Double.parseDouble(hora.substring(3,5));
    			min = min / 100.0;
    			resultado = resultado + min;							
    			seccionamiento.setHoraFin(new Double(resultado));
    							
    			double horasInicio = Fechas.millisToMinutes(seccionamiento.getFechaInicio().getTime());
    			double horasFinal = Fechas.millisToMinutes(seccionamiento.getFechaFin().getTime());
    			double diferencia = (horasFinal - horasInicio) / 60.0;
                
                diferencia = UtilidadesI.roundNum(diferencia);
    			seccionamiento.setTiefue(new Double(diferencia));
    			double horasAbonado = diferencia * seccionamiento.getAbonadosAfectados().longValue();

                horasAbonado = UtilidadesI.roundNum(horasAbonado);
    			seccionamiento.setHorasAbonado(new Double(horasAbonado));			
    			
    			lista.add(seccionamiento);
            }
		}
		this.seccionamientos = null;
		this.seccionamientos = new ArrayList(lista);
		
		
		int totalSeccionamientos = this.seccionamientos.size();
		String mensaje = "Se ingresaron " + totalSeccionamientos + " seccionamientos";
        this.accion = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";			
		return "success";
	}
	
	/**
	 * Cancela el proceso y regresa a la página principal
	 * @return success
	 */
	public String cancelar(){        
        this.accion = "";
        FacesContext ctx = FacesContext.getCurrentInstance();
        Map sessionMap = ctx.getExternalContext().getSessionMap();
        Boolean isModificar = (Boolean)sessionMap.get("isModificar");
        if(isModificar.booleanValue() == false){
            this.seccionamientos = new ArrayList();
            FacesContext context = FacesContext.getCurrentInstance();
            context.getApplication().createValueBinding("#{mayorCincoMinController.saleParcial}").setValue(context, Boolean.valueOf(false));
            context.getApplication().createValueBinding("#{mayorCincoMinController.saleTotal}").setValue(context, Boolean.valueOf(false));
        }else{
            String valoresLlave[] = this.consecutivoInterrupcion.split("-");
            NoPropiaSeccionamientoID noPropiaSeccionamientoID = new NoPropiaSeccionamientoID();             
            noPropiaSeccionamientoID.setAa(new Integer(valoresLlave[0].trim()));
            noPropiaSeccionamientoID.setCodigoOficina(new Integer(valoresLlave[1].trim()));
            noPropiaSeccionamientoID.setNumeroInterrupcion(new Long(valoresLlave[2].trim()));
            this.seccionamientos = this.noPropiaSeccionamientoBO.getNoPropiaSeccionamientos(noPropiaSeccionamientoID);
            reasignarNombreSeccionamientos();
            darFormatoFechaSeccionamientos();
            ctx.getExternalContext().getSessionMap().put("seccionamientos",this.seccionamientos);            
        }        
        
	    return "success";
	}
    
    /**
     * Método para cargar la secciones asociadas al circuito de la interrupción madre cuando el usuario 
     * selecciona que el circutio sale totalmente.
     */ 
    private void reasignarNombreSeccionamientos(){
        ArrayList resultado = new ArrayList();
        if(seccionamientos != null){
            for(int i = 0; i < seccionamientos.size(); i++){
                        NoPropiaSeccionamiento seccionamiento = (NoPropiaSeccionamiento)seccionamientos.get(i);                     
                        NoPropiaSeccionamientoID  seccionamientoID = seccionamiento.getNoPropiaSeccionamientoID();
                        seccionamiento.setNombreSeccion(seccionamientoID.getSeccion() + " - " + seccionamiento.getNombreSeccion());
                        resultado.add(seccionamiento);
                
            }
        }
        this.seccionamientos = new ArrayList(resultado);
                            
    }   
    
    private void darFormatoFechaSeccionamientos(){
        
        double horas = 0;       
        Double minutos;
        Double hora;
        GregorianCalendar gc;       
        ArrayList resultado = new ArrayList();
        if(seccionamientos != null){
            for(int i = 0; i < seccionamientos.size(); i++){
                        
                        NoPropiaSeccionamiento ig = (NoPropiaSeccionamiento)seccionamientos.get(i);                     
                                            
                        horas = ig.getHoraInicio().doubleValue();           
                        gc = new GregorianCalendar();
                        gc.setTime(ig.getFechaInicio());
                        hora = new Double(horas);
                        gc.set(GregorianCalendar.HOUR_OF_DAY,hora.intValue());
                        if(horas != 0){
                            double min = horas - hora.intValue();
                            minutos = new Double(min * 100);
                        }
                        else
                            minutos = new Double(0);
                        long minute = Math.round(minutos.doubleValue());
                        minutos = new Double(minute);
                        gc.set(GregorianCalendar.MINUTE,minutos.intValue());
                            
                        ig.setFechaInicio(gc.getTime());
                        
                        horas = ig.getHoraFin().doubleValue();          
                        gc = new GregorianCalendar();
                        gc.setTime(ig.getFechaFin());
                        hora = new Double(horas);
                        gc.set(GregorianCalendar.HOUR_OF_DAY,hora.intValue());
                        if(horas != 0){
                            double min = horas - hora.intValue();
                            minutos = new Double(min * 100);
                        }
                        else
                            minutos = new Double(0);
                        minute = Math.round(minutos.doubleValue());
                        minutos = new Double(minute);
                        gc.set(GregorianCalendar.MINUTE,minutos.intValue());
                        ig.setFechaFin(gc.getTime());
                        
                        resultado.add(ig);
                        
                
            }
        }
        this.seccionamientos = new ArrayList(resultado);        
      

        }    
    
    /**
     * Marca como seleccionadas toda la lista de secciones que se muestran al usuario
     * @return success
     */
    public String marcarSecciones(){
        for(int i = 0; i < seccionamientos.size(); i++){
            ((NoPropiaSeccionamiento)seccionamientos.get(i)).setSeleccion(true);     
        }
        return "success";
    }
    
    /**
     * Desmarca como seleccionadas toda la lista de secciones que se muestran al usuario
     * @return success
     */
    public String desmarcarSecciones(){
    	List listaSeccionamientosTemporal = new ArrayList();
    	this.dataTableSeccionamientos = new DataTable();
        for(int i = 0; i < seccionamientos.size(); i++){
        	NoPropiaSeccionamiento temporal = ((NoPropiaSeccionamiento)seccionamientos.get(i));
        	temporal.setSeleccion(false);
            listaSeccionamientosTemporal.add(temporal);
        }
        this.seccionamientos = null;
        this.seccionamientos = listaSeccionamientosTemporal;
        return "success";
    }    
    

    /**
     * Metodo accesor de oficinaBO.
     * @return Retorna el oficinaBO.
     */
    public OficinaBO getOficinaBO() {
        return oficinaBO;
    }
    /**
     * Metodo modificador de oficinaBO.
     * @param oficinaBO El oficinaBO a modificar.
     */
    public void setOficinaBO(OficinaBO oficinaBO) {
        this.oficinaBO = oficinaBO;
    }

    /**
     * Metodo accesor de accion.
     * @return Retorna el accion.
     */
    public String getAccion() {
        return accion;
    }
    /**
     * Metodo modificador de accion.
     * @param accion El accion a modificar.
     */
    public void setAccion(String accion) {
        this.accion = accion;
    }

	@Override
	protected String getPropertyFieldName(String property) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void resetController() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Map sessionMap = ctx.getExternalContext().getSessionMap();        
        this.operacion = (Integer) sessionMap.get("opera");
        this.codigoOficina = (Integer) sessionMap.get("oficina");        
        this.consecutivoInterrupcion = (String) sessionMap.get("consecutivoInterrupcion");
        this.subEstacion = (SubEstacion) sessionMap.get("subestacion");
        this.circuito = (Circuito) sessionMap.get("circuito");
        this.fechaInicioInterrupcion = (Date)sessionMap.get("fechaInicioInterrupcion");
        this.fechaFinalInterrupcion = (Date)sessionMap.get("fechaFinalInterrupcion");
        this.clientesAfectados = (Long)sessionMap.get("clientesAfectados");
        this.lugarInterrupcion = (String)sessionMap.get("lugarInterrupcion");
        this.observaciones = (String)sessionMap.get("observaciones");
        this.saleCircuito = (Integer)sessionMap.get("saleCircuito");
        this.seccionReal = (Seccion)sessionMap.get("seccion");
        this.codigoVoltaje = (Integer)sessionMap.get("codigoVoltaje");  
		     
        this.clientes = this.clientesAfectados;                               
        this.bitacora = Boolean.valueOf(false);
        this.trifasica = Boolean.valueOf(false);
        this.subestacion = Boolean.valueOf(false);
        this.seccion = new Seccion(); 
	}
	/**
	 * Retorna el valor del atributo saleCircuito.
	 * @return El valor del atributo saleCircuito.
	 */
	public Integer getSaleCircuito() {
		return saleCircuito;
	}
	/**
	 * Establece el valor del atributo saleCircuito.
	 * @param saleCircuito Valor del atributo saleCircuito a establecer.
	 */
	public void setSaleCircuito(Integer saleCircuito) {
		this.saleCircuito = saleCircuito;
	}
}
