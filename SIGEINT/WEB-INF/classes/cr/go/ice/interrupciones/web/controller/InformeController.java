package cr.go.ice.interrupciones.web.controller;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.sql.DataSource;
import com.vvs.jsf.AbstractFacesController;
import com.vvs.utilidades.Fechas;
import com.vvs.utilidades.Utilidades;
import cr.go.ice.interrupciones.BO.CircuitoBO;
import cr.go.ice.interrupciones.BO.CorreoBO;
import cr.go.ice.interrupciones.BO.EmpleadoBO;
import cr.go.ice.interrupciones.BO.EnvioEmailBO;
import cr.go.ice.interrupciones.BO.InterrupcionBO;
import cr.go.ice.interrupciones.BO.OficinaBO;
import cr.go.ice.interrupciones.BO.UsuarioOficinaBO;
import cr.go.ice.interrupciones.domain.Causa;
import cr.go.ice.interrupciones.domain.Interrupcion;
import cr.go.ice.interrupciones.domain.Circuito;
import cr.go.ice.interrupciones.domain.CircuitoID;
import cr.go.ice.interrupciones.domain.Correo;
import cr.go.ice.interrupciones.domain.Empleado;
import cr.go.ice.interrupciones.domain.Oficina;
import cr.go.ice.interrupciones.domain.SubEstacion;
import cr.go.ice.interrupciones.domain.UsuarioOficina;
import cr.go.ice.interrupciones.utils.UtilidadesFaces;
import cr.go.ice.interrupciones.web.Recurso;
import cr.go.ice.interrupciones.servicios.GenerarReporteAdjunto;
import cr.go.ice.obras.BO.AgenciaBO;
import cr.go.ice.obras.domain.Agencia;
import cr.go.ice.sige.confmailer.bo.MailerBo;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.InformeController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>InformeController.java</code>.</p>
 * <p>Fecha creación: 06/08/2007</p>
 * <p>Ultima actualización: 06/08/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class InformeController extends AbstractFacesController{

    
    private CircuitoBO circuitoBO;   
    private OficinaBO oficinaBO;
    private AgenciaBO agenciaBO;
    private EmpleadoBO empleadoBO;
    private CorreoBO correoBO;
    private EnvioEmailBO envioEmailBO;
    private UsuarioOficinaBO usuarioOficinaBO;

    
    private MailerBo mailerBo;
    
    private Integer codigoOficina;
    private Integer tipoInterrupcion;
    private Integer tipoBitacora;
    private Date fechaInicio;
    private Date fechaFinal;
    private Boolean email;
        
    private Circuito circuito;
    private Integer codigoAgencia;
    
    private InterrupcionBO interrupcionBO;
    
    private boolean comboOficina;
    private boolean comboAgencia;
    private Long cedula;
    private GenerarReporteAdjunto generarReporteAdjunto;
    
    String error;
     
    private  static final String jasperPath = "/jasperReports/";
    private Integer formato;
    public static String servletImagepath;
   
    public static String servletJasperPath; 
    /**
     * Constructor por defecto
     */    
    public InformeController(){
        this.fechaInicio = new Date();
        this.fechaFinal = new Date();        
        this.tipoBitacora = new Integer(1);
        this.email = Boolean.valueOf(false);
        this.codigoOficina = 0;
        this.codigoAgencia = 0;
        
	    this.circuito = new Circuito();
	    CircuitoID cirID = new CircuitoID();
	    cirID.setCircuito(new Integer(0));
	    SubEstacion sub = new SubEstacion();
	    sub.setCodigoSubEstacion(new Integer(0));
	    cirID.setSubEstacion(sub);
	    this.circuito.setCircuitoID(cirID);	
	    this.circuito.setNombreCircuito("x");
	    
	    this.comboOficina = false;
	    this.comboAgencia = false;
	    this.formato = UtilidadesFaces.FORMATO_PDF;
	    this.generarReporteAdjunto = new GenerarReporteAdjunto();
    }
    
    public String getInit(){
//      Comentado la restricción cuando se implementó el CIA   
//		boolean userClor = Usuario.isUserClor();
        boolean userClor = true;
        FacesContext context = FacesContext.getCurrentInstance();
		
		Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
		
		if(userClor && limpiar != null){
			 String nombreUsuarioSession = (String) context.getApplication().createValueBinding("#{autorizacionController.nombreUsuario}").getValue(this.getFacesContext());
	            String[] valores = nombreUsuarioSession.split("-");
	            cedula = new Long(valores[0].trim());
            Empleado emp = this.empleadoBO.buscar(this.cedula);
            List<UsuarioOficina> listaPivote = this.usuarioOficinaBO.buscarCedula(this.cedula);
        	if(!listaPivote.isEmpty())
        	{
        		this.codigoOficina = listaPivote.get(0).getId().getOficina().getCodigoOficina();
        		comboOficina = true;
        	}else
        	{
        		this.codigoOficina = new Integer(0);
        		comboOficina = false;
        	}
            
		}	
    	return "success";
    }
    
    /**
     * Retorna una lista de select item de los tipos de interrupción
     * @return Lista de tipos de interrupción
     */    
    public List getListaTipoInterrupcion(){
        List tramo = new ArrayList();        
        tramo.add(new SelectItem(new Integer(0),"0 - Todos"));
        tramo.add(new SelectItem(Interrupcion.INTERRUPCION_SUMINISTRO,"1 - Interrupción de suministro"));
        tramo.add(new SelectItem(Interrupcion.INTERRUPCION_PRIMARIA,"2 - Interrupción primaria"));
        tramo.add(new SelectItem(Interrupcion.INTERRUPCION_SECUNDARIA,"3 - Interrupción secundaria"));
        tramo.add(new SelectItem(Interrupcion.INTERRUPCION_CATASTROFE,"4 - Interrupción por catástrofe"));
        tramo.add(new SelectItem(Interrupcion.INTERRUPCION_DISTRIBUCION,"5 - Interrupción por distribución"));        
        return tramo;       
    } 
    
    /**
     * Retorna una lista de select item de las oficinas existentes
     * @return Lista de oficinas
     */    
	public List getListaOficinas(){		
		Vector voficinas = new Vector();
		List loficinas = oficinaBO.getOficinas();
		
		voficinas.add(new SelectItem(new Integer(0), "Seleccione una oficina"));
		
		for (int i = 0; i < loficinas.size(); i++) {
			Oficina xoficina = (Oficina) loficinas.get(i);
			voficinas.add(new SelectItem(xoficina.getCodigoOficina(), xoficina.getCodigoOficina() + " - " + xoficina.getNombreOficina()));
		}		
		
		return voficinas;		
	}
	
	/**
	 * Precarga la oficina del usuario de la aplicación, si el mismo es un usuario CLOR
	 * @param context
	 */
//	public void load(FacesContext context){
////      Comentado la restricción cuando se implementó el CIA   
////		boolean userClor = Usuario.isUserClor();
//        boolean userClor = true;
//
//		
//		Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
//		
//		if(userClor && limpiar != null){
//			UsuarioCia persona = Usuario.getUsuarioObj();
//			Long cedula = new Long(persona.getCedula());
//            Empleado emp = this.empleadoBO.buscar(cedula);
//            List<UsuarioOficina> listaPivote = this.usuarioOficinaBO.buscarCedula(cedula);
//        	if(!listaPivote.isEmpty())
//        	{
//        		this.codigoOficina = listaPivote.get(0).getId().getOficina().getCodigoOficina();
//        	}else
//        	{
//        		this.codigoOficina = new Integer(0);
//        	}
//            
//		}	
//	}
	
	/**
	 * Retorna una lista de select item de los circuitos filtrados de acuerdo a la oficina seleccionada
	 * @return Lista de circuitos
	 */	
	public List getListaCircuitos(){	    
	    List circuitosSI = new ArrayList();
	    Circuito cir = new Circuito();
	    CircuitoID cirID = new CircuitoID();
	    cirID.setCircuito(new Integer(0));
	    SubEstacion sub = new SubEstacion();
	    sub.setCodigoSubEstacion(new Integer(0));
	    cirID.setSubEstacion(sub);
	    cir.setCircuitoID(cirID);	    
	    cir.setNombreCircuito("x");        
	    circuitosSI.add(new SelectItem(cir,"Todos"));	   
	    
	    
	    List circuitos = this.circuitoBO.getCircuitosPorOficina(this.codigoOficina);
	    if(circuitos != null){
	        for(int i = 0; i < circuitos.size(); i++){
	            Circuito circuito = (Circuito)circuitos.get(i);
	            circuitosSI.add(new SelectItem(circuito, circuito.getCircuitoID().getSubEstacion().getCodigoSubEstacion() + " - " + circuito.getCircuitoID().getCircuito() + " - " + circuito.getNombreCircuito()));
	        }
	    }
	    return circuitosSI;
	}
	
	/**
	 * Listener para el combo de oficinas.
	 */	
	public String listenerOficina() {
		codigoAgencia = 0;
	    return "success";
	}
	/**
	 * Listener para el combo de agencias.
	 */
	public String listenerAgencia() {
		codigoOficina = 0;
	    return "success";
	}
	/**
	 * Retorna una lista de select item de las agencias existentes
	 * @return Lista de agencias
	 */	
	public List getListaAgencias(){
	    List agencias = this.agenciaBO.getAgencias();
	    List agenciasSI = new ArrayList();
	    agenciasSI.add(new SelectItem(new Integer(0),"Seleccione una agencia"));
	    if(agencias != null){
	        for(int i = 0; i < agencias.size(); i++){
	            Agencia agencia = (Agencia)agencias.get(i);
	            agenciasSI.add(new SelectItem(agencia.getCodigoAgencia(),agencia.getCodigoAgencia() + " - " + agencia.getNombreAgencia()));
	        }
	    }	    
	    return agenciasSI;	    
	}
	
	/**
	 * Retorna una lista de select item de los tipos de bitácora
	 * @return Lista de tipos de bitácora
	 */	
    public List getListaTipoBitacora(){
        List tramo = new ArrayList();
        tramo.add(new SelectItem(new Integer(1),"Bitácora"));
        tramo.add(new SelectItem(new Integer(0),"No bitácora"));
        tramo.add(new SelectItem(new Integer(2),"Ambos"));
        return tramo;       
    } 	 
	
    /**
     * Realiza las validaciones de los parámetros de entrada dados por el usuario y genera el reporte de interrupciones
     * por circuito
     * @return error o success de acuerdo a la correctitud de los parametros de entrada o no, de haberse encontrado
     * datos en la base de datos y de la correcta generación del reporte
     */	
    @SuppressWarnings("unchecked")
    public String generarReporteInterrupcionesPorCircuito(){
        FacesMessage msg = new FacesMessage();  
        String nombreArchivo = "";
        String nombre = "";
        boolean reporte = false;
        HashMap parametrosReporte = new HashMap();
		if(this.codigoOficina == null || this.codigoOficina.intValue() <= 0){
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La oficina es requerida."));
			return "error";
		}		
		if(this.fechaInicio == null){
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de inicio es requerida."));
			return "error";
		}		
		if(this.fechaFinal == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de fin es requerida."));
	        return "error";
		}	
		long horasInicio = Fechas.millisToMinutes(this.fechaInicio.getTime());
		long horasFinal = Fechas.millisToMinutes(this.fechaFinal.getTime());
		long diferencia = horasFinal - horasInicio;
		if(diferencia < 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de fin debe ser mayor o igual a la fecha de inicio."));
			return "error";
		}		
		
		String voltajes = "";
		Boolean causa = Boolean.valueOf(false);
        
        String todosVoltajes = "10, 20, 30, 40, 50, 60";
        
		switch(this.tipoInterrupcion.intValue()){
		    case 0:voltajes = todosVoltajes;break;
		    case 1:voltajes = "10";break;
		    case 2:voltajes = "20, 30, 40";break;
		    case 3:voltajes = "50, 60";break;
		    case 4:causa = Boolean.valueOf(true);break;		    
		    case 5:voltajes = "20, 30, 40, 50, 60";break;		    
		}
        Long interrupciones = this.interrupcionBO.getInterrupcionesPorCircuito(this.codigoOficina, this.fechaInicio, this.fechaFinal, voltajes, causa, this.tipoBitacora, this.circuito.getCircuitoID().getCircuito(),this.circuito.getCircuitoID().getSubEstacion().getCodigoSubEstacion());

		if(interrupciones.longValue() == 0){
		    String mensaje = "No hay información que cumpla con los parámetros definidos";
	        this.error = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";

			return "error";
		}
		else{
		    try{	
		        
		        
		        parametrosReporte.put("TITULO",this.getTitulo());
		        parametrosReporte.put("OFICINA",this.getOficina());
                
                parametrosReporte.put("codigoOficina",this.codigoOficina);
                parametrosReporte.put("tipoBitacora",this.tipoBitacora);
                                
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd/MM/yyyy");
                
                parametrosReporte.put("fechaInicio",sf.format(this.fechaInicio));
                parametrosReporte.put("fechaFinal",sf.format(this.fechaFinal));
                if(causa.booleanValue()){
                    parametrosReporte.put("codigoCausa",Causa.CODIGO_CATASTROFE);
                    parametrosReporte.put("codigosVoltajeSTR",todosVoltajes);
                }
                else{
                    parametrosReporte.put("codigoCausa",new Integer(0));
                    parametrosReporte.put("codigosVoltajeSTR",voltajes);
                }
                parametrosReporte.put("codigoCircuito",this.circuito.getCircuitoID().getCircuito());
                parametrosReporte.put("codigoSubestacion",this.circuito.getCircuitoID().getSubEstacion().getCodigoSubEstacion());
                
                nombre = "SigeItrRepInterrupcionPorCircuito.xls";
                                
                
                parametrosReporte.put("DIR_IMAGENES",servletImagepath);
                parametrosReporte.put("SUBREPORT_DIR",servletJasperPath);                                              
                
                nombreArchivo = "SigeItrRepInterrupcionPorCircuito.jasper";
                this.formato = UtilidadesFaces.FORMATO_XLS;
                if (this.generarReporteAdjunto.generarArchivo(jasperPath + nombreArchivo, nombre,parametrosReporte,null,this.getConnection(),this.formato,UtilidadesFaces.getCurrentUserId()))
                {
                    this.addInfo(null, "reporteEjecutado");
                    reporte = true;
                    
                }else{
                	reporte = false;
                    this.addError(null, "reporteError");
                }
                
                if(reporte)
                {
                	if(this.email.booleanValue()){
	                    List<Correo> correosParaEnviar = this.correoBO.getCorreosParaEnviar(this.codigoOficina);
	                    if(correosParaEnviar.size() == 0){
	                         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No existen direcciones de correos, por favor verificar la oficina."));
	                    }else{              
	                        try{
	                            enviarCorreo(correosParaEnviar);
	                           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "El correo ha sido enviado exitosamente a su destinatario."));
	                        }catch(Exception e){
	                            e.printStackTrace();
	                           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El correo no pudo ser enviado a su destinatario."));
	                        }
	                    }
                	}
                }
			    return "success";
		    } catch(Exception se){
		        se.printStackTrace();
		        return "error";
		    } 
		}        
    }
    
    /**
     * Realiza las validaciones de los parámetros de entrada dados por el usuario y genera el reporte de interrupciones
     * por período
     * @return error o success de acuerdo a la correctitud de los parametros de entrada o no, de haberse encontrado
     * datos en la base de datos y de la correcta generación del reporte
     */       
    @SuppressWarnings("unchecked")
    public String generarReporteInterrupcionesPorPeriodo(){
        FacesMessage msg = new FacesMessage(); 
        String nombreArchivo = "";
        String nombre = "";
        HashMap parametrosReporte = new HashMap();
        
		if(this.fechaInicio == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de inicio es requerida."));
			return "error";
		}		
		if(this.fechaFinal == null){
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de fin es requerida."));
	        return "error";
		}	
		long horasInicio = Fechas.millisToMinutes(this.fechaInicio.getTime());
		long horasFinal = Fechas.millisToMinutes(this.fechaFinal.getTime());
		long diferencia = horasFinal - horasInicio;
		if(diferencia < 0){
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de fin debe ser mayor o igual a la fecha de inicio."));
			return "error";
		}
		if((this.codigoOficina == null || this.codigoOficina.intValue() == 0) && this.comboOficina){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una oficina."));
			return "error";
		}	
		if((this.codigoAgencia == null || this.codigoAgencia.intValue() == 0) && this.comboAgencia){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar un agencia."));
			return "error";
		}	
		
		if(this.codigoOficina != null && this.codigoOficina.intValue() > 0 && this.codigoAgencia != null && this.codigoAgencia.intValue() > 0){
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Únicamente puede seleccionar una oficina o una agencia, pero no ambos."));
			return "error";
		}		
		Long interrupciones = null;
		
		if(this.codigoOficina == null){
			 interrupciones = this.interrupcionBO.getInterrupcionesPorPeriodo(null, this.codigoAgencia, this.fechaInicio, this.fechaFinal);
		}else{
			interrupciones = this.interrupcionBO.getInterrupcionesPorPeriodo(this.codigoOficina, null, this.fechaInicio, this.fechaFinal);
		}
		if(interrupciones.longValue() == 0){
		    String mensaje = "No hay información que cumpla con los parámetros definidos";
	        this.error = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";
			return "error";
		}
		else{
		    try{	
		        SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");
		        servletJasperPath = "/jasperReports/";
		        nombre = "SigeItrRepInterrupcionPorPeriodo.xls";
		        parametrosReporte.put("DIR_IMAGENES",servletImagepath);
                parametrosReporte.put("SUBREPORT_DIR",servletJasperPath);  
                
                nombreArchivo = "SigeItrRepInterrupcionPorPeriodo.jasper";
                
                parametrosReporte.put("fechaInicio",spf.format(this.fechaInicio));
                parametrosReporte.put("fechaFinal",spf.format(this.fechaFinal));
                parametrosReporte.put("codigoOficina",this.codigoOficina);
                parametrosReporte.put("codigoAgencia",this.codigoAgencia);
                
                this.formato = UtilidadesFaces.FORMATO_XLS;
                if (this.runReport(jasperPath + nombreArchivo, nombre,parametrosReporte,this.getConnection(),this.formato,UtilidadesFaces.getCurrentUserId())){
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
    /**
     * Crea el título para el reporte de las interrupciones por circuito
     * @return String titulo del reporte
     */    
    private String getTitulo(){
        String titulo = "INFORME DE INTERRUPCIONES ";

        switch(this.tipoInterrupcion.intValue()){
            case 1:titulo += "POR SUMINISTRO ";break;
            case 2:titulo += "POR PRIMARIO ";break;
            case 3:titulo += "POR SECUNDARIO ";break;
            case 4:titulo += "POR CATASTROFE ";break;
            case 5:titulo += "POR DISTRIBUCION ";break;
            case 0:titulo += "PARA TODOS LOS TIPOS ";break;
        }
              
        switch(this.tipoBitacora.intValue()){
            case 1:titulo += "INCLUIDAS EN BITACORA ";break;
            case 0:titulo += "NO INCLUIDAS EN BITACORA ";break;
            case 2:titulo += "REGISTRADAS O NO EN BITACORA ";break;            
        }                
        
        titulo += "A NIVEL DE CIRCUITO";                
        return titulo;
    }
    
    /**
     * Metodo que envía un correo a determinados destinatarios
     * @param correosParaEnviar Lista de destinatarios
     */
    private void enviarCorreo(List<Correo> correosParaEnviar){
        try{
        	this.envioEmailBO.enviarCorreoInterrupcionesCircuito(correosParaEnviar, this.getContextRealPath("/file/SigeItrRepInterrupcionPorCircuito.xls"+File.separatorChar));
        }catch(Exception e)
        {
        	e.printStackTrace();
        }        
    }
     
    /**
     * Retorna el nombre de la oficina seleccionada para las interrupciones por circuito
     * @return oficina
     */
    private String getOficina(){
        SimpleDateFormat sf = new SimpleDateFormat();
        sf.applyPattern("dd/MM/yyyy");
        Oficina oficina = this.oficinaBO.buscar(this.codigoOficina);
        String oficinaSTR = "OFICINA: " + oficina.getCodigoOficina() + " - " + oficina.getNombreOficina();
        oficinaSTR += " DEL " +  sf.format(this.fechaInicio) + " AL " + sf.format(this.fechaFinal);
        return oficinaSTR;
    }
    /**
     * Cancela el proceso de la generación de reportes
     * @return success
     */    
    public String cancelar(){
        return "success";
    }
    /**
     * Metodo accesor de circuitoBO.
     * @return Retorna el circuitoBO.
     */
    public CircuitoBO getCircuitoBO() {
        return this.circuitoBO;
    }
    /**
     * Metodo modificador de circuitoBO.
     * @param circuitoBO El circuitoBO a modificar.
     */
    public void setCircuitoBO(CircuitoBO circuitoBO) {
        this.circuitoBO = circuitoBO;
    }
    /**
     * Metodo accesor de codigoOficina.
     * @return Retorna el codigoOficina.
     */
    public Integer getCodigoOficina() {
        return this.codigoOficina;
    }
    /**
     * Metodo modificador de codigoOficina.
     * @param codigoOficina El codigoOficina a modificar.
     */
    public void setCodigoOficina(Integer codigoOficina) {
        this.codigoOficina = codigoOficina;
    }
    /**
     * Metodo accesor de email.
     * @return Retorna el email.
     */
    public Boolean getEmail() {
        return this.email;
    }
    /**
     * Metodo modificador de email.
     * @param email El email a modificar.
     */
    public void setEmail(Boolean email) {
        this.email = email;
    }
    /**
     * Metodo accesor de fechaFinal.
     * @return Retorna el fechaFinal.
     */
    public Date getFechaFinal() {
        return this.fechaFinal;
    }
    /**
     * Metodo modificador de fechaFinal.
     * @param fechaFinal El fechaFinal a modificar.
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    /**
     * Metodo accesor de fechaInicio.
     * @return Retorna el fechaInicio.
     */
    public Date getFechaInicio() {
        return this.fechaInicio;
    }
    /**
     * Metodo modificador de fechaInicio.
     * @param fechaInicio El fechaInicio a modificar.
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    /**
     * Metodo accesor de oficinaBO.
     * @return Retorna el oficinaBO.
     */
    public OficinaBO getOficinaBO() {
        return this.oficinaBO;
    }
    /**
     * Metodo modificador de oficinaBO.
     * @param oficinaBO El oficinaBO a modificar.
     */
    public void setOficinaBO(OficinaBO oficinaBO) {
        this.oficinaBO = oficinaBO;
    }
    /**
     * Metodo accesor de tipoBitacora.
     * @return Retorna el tipoBitacora.
     */
    public Integer getTipoBitacora() {
        return this.tipoBitacora;
    }
    /**
     * Metodo modificador de tipoBitacora.
     * @param tipoBitacora El tipoBitacora a modificar.
     */
    public void setTipoBitacora(Integer tipoBitacora) {
        this.tipoBitacora = tipoBitacora;
    }
    /**
     * Metodo accesor de tipoInterrupcion.
     * @return Retorna el tipoInterrupcion.
     */
    public Integer getTipoInterrupcion() {
        return this.tipoInterrupcion;
    }
    /**
     * Metodo modificador de tipoInterrupcion.
     * @param tipoInterrupcion El tipoInterrupcion a modificar.
     */
    public void setTipoInterrupcion(Integer tipoInterrupcion) {
        this.tipoInterrupcion = tipoInterrupcion;
    }
    /**
     * Metodo accesor de circuito.
     * @return Retorna el circuito.
     */
    public Circuito getCircuito() {
        return this.circuito;
    }
    /**
     * Metodo modificador de circuito.
     * @param circuito El circuito a modificar.
     */
    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }
    /**
     * Metodo accesor de agenciaBO.
     * @return Retorna el agenciaBO.
     */
    public AgenciaBO getAgenciaBO() {
        return this.agenciaBO;
    }
    /**
     * Metodo modificador de agenciaBO.
     * @param agenciaBO El agenciaBO a modificar.
     */
    public void setAgenciaBO(AgenciaBO agenciaBO) {
        this.agenciaBO = agenciaBO;
    }
    /**
     * Metodo accesor de codigoAgencia.
     * @return Retorna el codigoAgencia.
     */
    public Integer getCodigoAgencia() {
        return this.codigoAgencia;
    }
    /**
     * Metodo modificador de codigoAgencia.
     * @param codigoAgencia El codigoAgencia a modificar.
     */
    public void setCodigoAgencia(Integer codigoAgencia) {
        this.codigoAgencia = codigoAgencia;
    }
    /**
     * Metodo accesor de interrupcionBO.
     * @return Retorna el interrupcionBO.
     */
    public InterrupcionBO getInterrupcionBO() {
        return this.interrupcionBO;
    }
    /**
     * Metodo modificador de interrupcionBO.
     * @param interrupcionBO El interrupcionBO a modificar.
     */
    public void setInterrupcionBO(InterrupcionBO interrupcionBO) {
        this.interrupcionBO = interrupcionBO;
    }
    /**
     * Metodo accesor de empleadoBO.
     * @return Retorna el empleadoBO.
     */
    public EmpleadoBO getEmpleadoBO() {
        return this.empleadoBO;
    }
    /**
     * Metodo modificador de empleadoBO.
     * @param empleadoBO El empleadoBO a modificar.
     */
    public void setEmpleadoBO(EmpleadoBO empleadoBO) {
        this.empleadoBO = empleadoBO;
    }
    /**
     * Metodo accesor de comboAgencia.
     * @return Retorna el comboAgencia.
     */
    public boolean isComboAgencia() {
        return this.comboAgencia;
    }
    /**
     * Metodo modificador de comboAgencia.
     * @param comboAgencia El comboAgencia a modificar.
     */
    public void setComboAgencia(boolean comboAgencia) {
        this.comboAgencia = comboAgencia;
    }
    /**
     * Metodo accesor de comboOficina.
     * @return Retorna el comboOficina.
     */
    public boolean isComboOficina() {
        return this.comboOficina;
    }
    /**
     * Metodo modificador de comboOficina.
     * @param comboOficina El comboOficina a modificar.
     */
    public void setComboOficina(boolean comboOficina) {
        this.comboOficina = comboOficina;
    }
    /**
     * Metodo accesor de error.
     * @return Retorna el error.
     */
    public String getError() {
        return this.error;
    }
    /**
     * Metodo modificador de error.
     * @param error El error a modificar.
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Metodo modificador de correoBO.
     * @param correoBO El correoBO a modificar.
     */
    public void setCorreoBO(CorreoBO correoBO) {
        this.correoBO = correoBO;
    }

    /**
     * Metodo modificador de mailerBo.
     * @param mailerBo El mailerBo a modificar.
     */
    public void setMailerBo(MailerBo mailerBo) {
        this.mailerBo = mailerBo;
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

	public EnvioEmailBO getEnvioEmailBO() {
		return envioEmailBO;
	}

	public void setEnvioEmailBO(EnvioEmailBO envioEmailBO) {
		this.envioEmailBO = envioEmailBO;
	}

	public UsuarioOficinaBO getUsuarioOficinaBO() {
		return usuarioOficinaBO;
	}

	public void setUsuarioOficinaBO(UsuarioOficinaBO usuarioOficinaBO) {
		this.usuarioOficinaBO = usuarioOficinaBO;
	}

	public Long getCedula() {
		return cedula;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}
}
