package cr.go.ice.interrupciones.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.jibx.runtime.JiBXException;
import org.primefaces.component.datatable.DataTable;

import com.vvs.jsf.AbstractFacesController;
import com.vvs.utilidades.Fechas;

import cr.go.ice.interrupciones.BO.AnimalBO;
import cr.go.ice.interrupciones.BO.CausaBO;
import cr.go.ice.interrupciones.BO.CircuitoBO;
import cr.go.ice.interrupciones.BO.ConsecutivoClorBO;
import cr.go.ice.interrupciones.BO.CuadrillaBO;
import cr.go.ice.interrupciones.BO.EmpleadoBO;
import cr.go.ice.interrupciones.BO.MaterialBO;
import cr.go.ice.interrupciones.BO.OficinaBO;
import cr.go.ice.interrupciones.BO.ParametroBO;
import cr.go.ice.interrupciones.BO.ProteccionBO;
import cr.go.ice.interrupciones.BO.ProvolmaBO;
import cr.go.ice.interrupciones.BO.PuebloBO;
import cr.go.ice.interrupciones.BO.RegionBO;
import cr.go.ice.interrupciones.BO.ReporteBO;
import cr.go.ice.interrupciones.BO.SeccionBO;
import cr.go.ice.interrupciones.BO.SubEstacionBO;
import cr.go.ice.interrupciones.BO.SubRegionBO;
import cr.go.ice.interrupciones.BO.UsuarioOficinaBO;
import cr.go.ice.interrupciones.BO.VehiculoBO;

import cr.go.ice.interrupciones.domain.Animal;
import cr.go.ice.interrupciones.domain.Causa;
import cr.go.ice.interrupciones.domain.ConsecutivoClor;
import cr.go.ice.interrupciones.domain.ConsecutivoClorID;
import cr.go.ice.interrupciones.domain.Cuadrilla;
import cr.go.ice.interrupciones.domain.CuadrillaID;
import cr.go.ice.interrupciones.domain.Dano;
import cr.go.ice.interrupciones.domain.Empleado;
import cr.go.ice.interrupciones.domain.Material;
import cr.go.ice.interrupciones.domain.Oficina;
import cr.go.ice.interrupciones.domain.Parametro;
import cr.go.ice.interrupciones.domain.Proteccion;
import cr.go.ice.interrupciones.domain.Provolma;
import cr.go.ice.interrupciones.domain.ProvolmaID;
import cr.go.ice.interrupciones.domain.Region;
import cr.go.ice.interrupciones.domain.Reporte;
import cr.go.ice.interrupciones.domain.ReporteID;
import cr.go.ice.interrupciones.domain.Seccion;
import cr.go.ice.interrupciones.domain.SeccionID;
import cr.go.ice.interrupciones.domain.SubRegion;
import cr.go.ice.interrupciones.domain.SubRegionID;
import cr.go.ice.interrupciones.domain.TipoVoltaje;
import cr.go.ice.interrupciones.domain.TipoVoltajeID;
import cr.go.ice.interrupciones.domain.UsuarioOficina;
import cr.go.ice.interrupciones.domain.Vehiculo;
import cr.go.ice.interrupciones.domain.VehiculoID;
import cr.go.ice.interrupciones.servicios.ConsultaPueblos;
import cr.go.ice.interrupciones.servicios.RespuestaConsultaPueblos;
import cr.go.ice.interrupciones.servicios.ServiciosAcemas;
import cr.go.ice.interrupciones.utils.UtilidadesI;
import cr.go.ice.obras.BO.AgenciaBO;
import cr.go.ice.obras.domain.Agencia;
import cr.go.ice.sace.sacecon.domain.Pueblo;

/**
 * 
 * <p>Clase cr.go.ice.interrupciones.web.controller.MenorCincoMinController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>MenorCincoMinController.java</code>Establece la relación entre capa de presentación y la lógica del negocio de las interrupciones menores a cinco minutos.</p>
 * <p>Fecha creación: 19/04/2007</p>
 * <p>Ultima actualización: 19/04/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class MenorCincoMinController extends AbstractFacesController {

    private MaterialBO materialBO;
    private ProteccionBO proteccionBO;
    private CircuitoBO circuitoBO;
    private UsuarioOficinaBO usuarioOficinaBO;


    private SubEstacionBO subEstacionBO;

    private SeccionBO seccionBO;

    private ReporteBO reporteBO;

    private CuadrillaBO cuadrillaBO;

    private ConsecutivoClorBO consecutivoClorBO;

    private EmpleadoBO empleadoBO;

    private AgenciaBO agenciaBO;

    private AnimalBO animalBO;

    private PuebloBO puebloBO;

    private VehiculoBO vehiculoBO;

    private CausaBO causaBO;

    private ProvolmaBO provolmaBO;

    private String operador;
    private String nuevoFoco;
    private String oficina;

    private String consecutivoInterrupcion;

    private Integer codigoAgencia;

    private String cliente;

    private String telefonoCliente;

    private Long medidor;

    private String lugarInterrupcion;

    private Date fechaInicioInterrupcion;

    private Date fechaFinalInterrupcion;

    private Integer subestacion;

    private Integer circuito;

    private Long seccion;

    private TipoVoltaje tipoVoltaje;

    private Pueblo pueblo;

    private Vehiculo vehiculo;

    private Empleado empleado;

    private Long clientesAfectados;

    private Boolean bitacora;

    private Boolean trifasica;

    private Boolean indicadorSubestacion;

    private Boolean faseR;

    private Boolean faseS;

    private Boolean faseT;

    private Boolean scada;

    private Boolean manual;
    
    private Boolean regresaDePag;

    private String nombrePueblo;

    private String observaciones;

    private Integer codigoProteccion;

    private Integer codigoMaterial;

    private Integer codigoDano;

    private Integer codigoCausa;

    private Integer codigoCausa2;

    private Integer codigoAnimal;

    private Integer cantidadCable;

    private Integer tipoCable;

    private Empleado empleadoCuadrilla;

    private Boolean empleadoResponsable;

    private Boolean empleadoMiembro;

    private String nombreEmpleado;

    private Long cedula;

    private boolean primeraVezConsecutivo;

    private List empleadosCuadrilla;

    private DataTable dataTableEmpleados;

    private DataTable dataTablePueblos;

    private DataTable dataTableCuadrilla;

    private Integer operacion;

    private CuadrillaID cuadrillaID;

    private Integer codigoCalleFalla;

    private Integer posteFalla;

    private Integer secuenciaFalla;

    private Integer codigoCalleEquipo;

    private Integer posteEquipo;

    private Integer secuenciaEquipo;

    private Pueblo puebloEquipo;

    private Integer codigoOficina;

    private ParametroBO parametroBO;

    private VehiculoID vehiculoID;

    private boolean indAgencia;
    private Boolean indTension;
    
    private String accionProvolma;
    private String accionGuardar;
    private String nombreAnimal;
    
	private String nombreRegion;
	private RegionBO regionBO;
	private Region region; 
	
	private String nombreSubregion;
	private SubRegionBO subRegionBO;
	private SubRegion subRegion;	
	private String titulo;
	private String telefono;
	private String operadorScada;
	private Boolean irCuadrillaVehiculo;
	private VehiculoID vehiculoIDViejo;
	private OficinaBO oficinaBO;
	private Boolean comboOficina;
	private Reporte reporte;
	private String tituloFinal;	
    private ConsecutivoClor consecutivoClor;
    private String estructuraSelected;
    private ServiciosAcemas serviciosAcemas;
    /**
     * 
     * Constructor por defecto. Inicializa los atributos necesarios para la
     * interrupción menor a cinco minutos
     */
    public MenorCincoMinController()
    {
    	this.regresaDePag = false;
        this.operadorScada = null;
        this.primeraVezConsecutivo = true;
        this.operacion = new Integer(0);
        cuadrillaID = null;
        this.empleadosCuadrilla = new ArrayList();
        this.manual = new Boolean(true);
        this.cedula = new Long(0);
        this.nombreEmpleado = "";
        this.empleadoCuadrilla = new Empleado();
        this.empleadoCuadrilla.setCedula(new Long(0));
        this.empleadoCuadrilla.setNombreEmpleado("");
        this.vehiculo = new Vehiculo();
        this.vehiculo.setVehiculoID(new VehiculoID());
        GregorianCalendar fecha = new GregorianCalendar();
        this.fechaInicioInterrupcion = fecha.getTime();
        this.fechaFinalInterrupcion = this.fechaInicioInterrupcion;
        this.nombrePueblo = "";
        this.pueblo = new Pueblo();
        this.pueblo.setNombre("");
        this.pueblo.setNumero(new Integer(0));
        this.observaciones = " ";
        this.codigoAgencia = new Integer(1);
        indAgencia = false;
        accionProvolma = "";
        accionGuardar = "";
        this.estructuraSelected = "";
        this.nuevoFoco="form1:voltaje"; 
        this.subestacion = new Integer(0);
        this.circuito = new Integer(0);
        this.seccion = new Long(0);
        
        this.codigoProteccion = new Integer(0);
        this.codigoMaterial = new Integer(0);
        this.codigoCausa = new Integer(0);
        this.codigoCausa2 = new Integer(0);
        this.serviciosAcemas = new ServiciosAcemas();
        this.indTension = false;
    }
    
    
    public String getInit(){
    	FacesContext context = FacesContext.getCurrentInstance();
    	  Object ingresar = context.getExternalContext().getRequestParameterMap().get("ingresar");
          if(ingresar != null){
              this.reporte = null;
              this.titulo = "Ingreso de Interrupción menor a 5 minutos ";
              
              String nombreUsuarioSession = (String) context.getApplication().createValueBinding("#{autorizacionController.nombreUsuario}").getValue(this.getFacesContext());
	            String[] valores = nombreUsuarioSession.split("-");
	            this.cedula = new Long(valores[0].trim());
              
              
              this.empleado = this.empleadoBO.buscar(this.cedula);
              
              this.consecutivoInterrupcion = null;
              this.operador = this.empleado.getNombreCompleto();
              List<UsuarioOficina> listaPivote = this.usuarioOficinaBO.buscarCedula(this.cedula);
          	if(!listaPivote.isEmpty())
          	{
          		this.oficina = listaPivote.get(0).getId().getOficina().getNombreOficina();
          		this.codigoOficina = listaPivote.get(0).getId().getOficina().getCodigoOficina();
          	}else
          	{
          		this.oficina = "";
          		this.codigoOficina = new Integer(0);
          	}
//            Comentado la restricción cuando se implementó el CIA            
//            boolean userAdmin = Usuario.isUserAdmin();
//            this.comboOficina = (userAdmin) ? new Boolean(false) : new Boolean(true);
            this.comboOficina = new Boolean(false);//          Comentado la restricción cuando se implementó el CIA   

              
              cuadrillaID = null;
              primeraVezConsecutivo = false;
              this.empleadosCuadrilla = new ArrayList();
              this.limpiarDatos();        
              this.region = this.regionBO.buscarPorOficina(this.codigoOficina);
              if(region != null){
                  this.nombreRegion = region.getNombreRegion();
                  this.subRegion = this.subRegionBO.buscarPorOficina(this.codigoOficina); 
                  if(subRegion != null)
                      this.nombreSubregion = this.subRegion.getNombreSubRegion();
                  else
                      this.nombreSubregion = "";
              }
              else
                  this.nombreRegion = "";             
          }
		return "success";
    
    }
    

    /**
     * Método que obtiene la lista de las todas las agencias
     * @return Lista Agencias
     */
    public List getListaAgencias() {
        List agencias = null;
        List agenciasSI = new Vector();
        
        agencias = this.agenciaBO.getAgencias();

        agenciasSI.add(new SelectItem(new Integer(0), "Seleccione una agencia"));

        if (agencias != null && agencias.size() > 0) {
            for (int i = 0; i < agencias.size(); i++) {
                Agencia agencia = (Agencia) agencias.get(i);
                agenciasSI.add(new SelectItem(agencia.getCodigoAgencia(), agencia.getCodigoAgencia() + " - " + agencia.getNombreAgencia()));
            }
        }

        if ((agencias == null || agencias.size() == 0) && indAgencia == true) {
            indAgencia = false;
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Para la selección seleccionada no existen agencias."));
        }

        return agenciasSI;
    }

    
    /**
     * Accion de regreso de la pagina de ingreso de detalle de vehiculo y cuadrilla
     * @return error o success
     */
	public String regresarDePag3(){
	    this.accionProvolma = "";		    
        if((this.empleadosCuadrilla == null || this.empleadosCuadrilla.size() == 0)){
            String mensaje = "Debe de completar el detalle de cuadrilla";
            this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";             
            return "error";
        }
        if((this.vehiculo.getVehiculoID().getNumeroVehiculo() == null || this.vehiculo.getVehiculoID().getNumeroVehiculo().longValue() <= 0)){          
            String mensaje = "Debe de completar el detalle de vehículo utilizado";
            this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";             
            return "error";
        }
        if(this.codigoCausa!=null && this.codigoCausa.equals(Integer.valueOf(411)))
        {
        	if(this.vehiculo.getFechaLocalizacion()==null || (this.vehiculo.getKilometrosLocalizacion()==null || this.vehiculo.getKilometrosLocalizacion().equals(Double.valueOf(0))) )
        	{
        		String mensaje = "Debe de completar el valor de Fecha de la Locazación y Kilómetros de Localización ";
                this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";             
                return "error";
        	}
        	
        }
	    if (this.validarDatosCuadrilla() == false)
	        return "error";	    
	    
	    
	    return "success";
	}    
    
    /**
     * Acción de ir a ingresar detalle de vehiculo y cuadrilla
     * @return
     */
	public String irPagina3(){	    
	    this.accionProvolma = "";
		

		if(this.irCuadrillaVehiculo.booleanValue() == false){
			
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No puede agregar el detalle ya que no se indicó que existe."));
			return "error";
		}		
					
		if(vehiculo.getVehiculoID().getNumeroVehiculo() == null || vehiculo.getVehiculoID().getNumeroVehiculo().longValue() == 0){
			this.vehiculo.setHoraLlegada(this.fechaFinalInterrupcion);
			this.vehiculo.setHoraInicio(this.fechaInicioInterrupcion);
			this.vehiculo.setTiempoAviso(this.fechaInicioInterrupcion);
            this.vehiculo.setHoraFin(this.fechaFinalInterrupcion);
            this.vehiculo.setFechaLocalizacion(this.fechaFinalInterrupcion);
		}				
		this.vehiculo.setHoraRestablece(this.fechaFinalInterrupcion);
			
		return "success";
	}    
    
    private boolean isModificar(){
        ReporteID reporteID = new ReporteID();
        if(this.consecutivoInterrupcion != null){
            String valoresLlave[] = this.getConsecutivoInterrupcion().split("-");
            reporteID.setAa(new Integer(valoresLlave[0].trim()));
            reporteID.setCodigoOficina(new Integer(valoresLlave[1].trim()));
            reporteID.setNumeroReporte(new Long(valoresLlave[2].trim()));
            
            
            if(this.reporte == null){
                this.reporte = new Reporte();
                this.reporte.setReporteID(reporteID);
            }
            else{
                this.reporte = this.reporteBO.getReporte(reporteID);
            }       
            if((this.operacion == null || this.operacion.intValue() != 1) && (this.reporte == null || this.reporteBO.reporteExiste(this.reporte.getReporteID()) == false))
                return false;
            else
                return true;
        }else{
            return false;
        }
    }
    
    public String getModificar() {
    	FacesContext context = FacesContext.getCurrentInstance();
    	Object modificar = context.getExternalContext().getSessionMap().remove("modificar");
        if (modificar != null) {
        	this.titulo = "Edición de Interrupción menor a 5 minutos ";

            Integer ano = (Integer) context.getExternalContext().getSessionMap().remove("ano");
            Integer oficina = (Integer) context.getExternalContext().getSessionMap().remove("oficina");
            Long consecutivo = (Long) context.getExternalContext().getSessionMap().remove("consecutivo");
            primeraVezConsecutivo = false;
            this.consecutivoInterrupcion = ano + " - " + oficina + " - " + consecutivo;
            this.codigoOficina = oficina;
            this.limpiarDatos();
            this.cargarDatos();

            this.oficina = this.oficinaBO.buscar(this.codigoOficina).getNombreOficina();

            SeccionID seccionID = new SeccionID(this.subestacion, this.circuito, this.seccion);
            Seccion seccion = this.seccionBO.buscar(seccionID);

            this.region = this.regionBO.buscar(seccion.getRegion());
            if (region != null) {
                this.nombreRegion = region.getNombreRegion();
                SubRegionID subID = new SubRegionID();
                Region region = new Region();
                region.setRegion(seccion.getRegion());
                subID.setRegion(region);
                subID.setSubRegion(seccion.getSubRegion());
                this.subRegion = this.subRegionBO.buscar(subID);
                if (subRegion != null) {
                    this.nombreSubregion = this.subRegion.getNombreSubRegion();
                } else { 
                    this.nombreSubregion = "";
                }
            } else {
                this.nombreRegion = "";
            }
        }
        return "success";
    }
    
    /**
     * Cancela el mantenimiento de cuadrilla y vehiculo
     * @return success
     */
    public String cancelarCuadrillaVehiculo(){
        
        this.accionProvolma = "";
        if(isModificar() == false){
            
            this.empleadoMiembro = new Boolean(false);
            this.empleadoResponsable = new Boolean(false);
            this.cuadrillaID = new CuadrillaID();
            this.empleadosCuadrilla = new ArrayList();   
            this.irCuadrillaVehiculo = new Boolean(false);
            this.vehiculo = new Vehiculo();
            this.vehiculo.setVehiculoID(new VehiculoID());
            this.vehiculoIDViejo = null;
            
            
        }else{
            this.cuadrillaID = new CuadrillaID();
            this.cuadrillaID.setAa(this.reporte.getReporteID().getAa());
            this.cuadrillaID.setCodigoOficina(this.reporte.getReporteID().getCodigoOficina());
            this.cuadrillaID.setNumeroInterrupcion(this.reporte.getReporteID().getNumeroReporte());
            this.cuadrillaID.setReporteInterrupcion(new Integer(0));        
            
            this.empleadosCuadrilla = this.cuadrillaBO.getCuadrillas(cuadrillaID);
            if(this.empleadosCuadrilla == null || this.empleadosCuadrilla.size() == 0){
                this.empleadosCuadrilla = new ArrayList(); 
                this.irCuadrillaVehiculo = new Boolean(false);
            }
            else
                this.irCuadrillaVehiculo = new Boolean(true);
            
            this.vehiculoID = null;
            VehiculoID vehiculoID = new VehiculoID();
            Vehiculo vehiculo = new Vehiculo();
            vehiculoID.setAa(this.reporte.getReporteID().getAa());
            vehiculoID.setCodigoOficina(this.reporte.getReporteID().getCodigoOficina());
            vehiculoID.setNumeroInterrupcion(this.reporte.getReporteID().getNumeroReporte());
            vehiculoID.setTipo(new Integer(1));
            vehiculo.setVehiculoID(vehiculoID);
            this.vehiculo = this.vehiculoBO.buscar(vehiculo);
            if(this.vehiculo == null){
                this.vehiculo = new Vehiculo();
                this.vehiculo.setVehiculoID(new VehiculoID());
                this.vehiculoIDViejo = null;
            }
            else{
                this.vehiculoID = new VehiculoID();
                this.vehiculoID.setAa(this.vehiculo.getVehiculoID().getAa());
                this.vehiculoID.setCodigoOficina(this.vehiculo.getVehiculoID().getCodigoOficina());     
                this.vehiculoID.setNumeroInterrupcion(this.vehiculo.getVehiculoID().getNumeroInterrupcion());
                this.vehiculoID.setTipo(new Integer(1));
                this.vehiculoID.setNumeroVehiculo(this.vehiculo.getVehiculoID().getNumeroVehiculo());
                this.vehiculoIDViejo = this.vehiculoID;
            }
        }
        return "success";
    }

    /**
     * Método que válida los datos ingresados en la primera página, para que el usuario continue hacia la siguiente página
     * @return Boolean "true" si los datos requeridos se encuentran validados correctamente, sino retorna "false"
     */
    public boolean validarDatosInterrupcion() {
        //FacesMessage msg = new FacesMessage();
        double horasInicio = 0;
        double horasFinal = 0;
        double diferencia = 0;
        boolean resultado = true;

        Date dummy = new Date();
        if (this.codigoOficina == null || this.codigoOficina.intValue() <= 0) {
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La oficina es requerida."));
            return false;
        }
        if (this.fechaInicioInterrupcion == null || this.fechaInicioInterrupcion.equals(dummy)) {
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de inicio de interrupción es requerida."));

            return false;
        }
        if (this.fechaFinalInterrupcion == null || this.fechaFinalInterrupcion.equals(dummy)) {
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de fin de interrupción es requerida."));

            return false;
        }

        GregorianCalendar fecha = new GregorianCalendar();
        horasInicio = Fechas.millisToMinutes(fecha.getTime().getTime());
        horasFinal = Fechas.millisToMinutes(this.fechaInicioInterrupcion.getTime());
        diferencia = horasFinal - horasInicio;
        if (diferencia > 0) {
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de inicio de interrupción debe ser mayor o igual a la del sistema."));

            return false;
        }
        horasInicio = Fechas.millisToMinutes(fecha.getTime().getTime());
        horasFinal = Fechas.millisToMinutes(this.fechaFinalInterrupcion.getTime());
        diferencia = horasFinal - horasInicio;
        if (diferencia > 0) {
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha fin de interrupción debe ser menor o igual a la del sistema."));

            return false;
        }

        horasInicio = Fechas.millisToMinutes(this.fechaInicioInterrupcion.getTime());
        horasFinal = Fechas.millisToMinutes(this.fechaFinalInterrupcion.getTime());
        diferencia = horasFinal - horasInicio;
        if (diferencia < 0) {
         
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de fin de interrupción debe ser mayor a la fecha de inicio de interrupción."));

            return false;
        }
        
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(this.fechaInicioInterrupcion.getTime());
        diferencia = UtilidadesI.getTiempoTranscurridoDespuesDia5MesSiguiente(gc);
        
        List parametros = parametroBO.getParametros();
        Parametro parametro = null;
        if(parametros != null && parametros.size() > 0)
            parametro = (Parametro)parametros.get(0);
        if((diferencia >= 0 && parametro == null) || (diferencia >= 0 && parametro.getParametroID().getGeneracionIndices().equals("S") == false)){
    		String mensaje = "No puede agregar o modificar una interrupción después del día 5 del mes siguiente en el cual ocurrió la interrupción.";
            this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";	            
            return false;
        } 
        
		GregorianCalendar fechaInicioInter = new GregorianCalendar();		
		fechaInicioInter.setTime(this.fechaInicioInterrupcion);
		
		if(diferencia >= 0 && (parametro != null && parametro.getParametroID().getGeneracionIndices().equals("S") == true) && UtilidadesI.esMesAnterior(fechaInicioInter) == false){
			String mensaje = "No puede agregar una interrupción con fecha de inicio menor al mes anterior.";
			this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";
			return false;
		}       
                
        
        GregorianCalendar fechaActual = new GregorianCalendar();
        int mesActual = fechaActual.get(GregorianCalendar.MONTH);
        int mesFechaInicioInteruup = gc.get(GregorianCalendar.MONTH);
        int anoFechaInicioInteruup = gc.get(GregorianCalendar.YEAR);
        int anoActual = fechaActual.get(GregorianCalendar.YEAR);        
        
        ConsecutivoClor consecutivoAnoAnterior = new ConsecutivoClor();
        ConsecutivoClorID consecutivoID = new ConsecutivoClorID();
        consecutivoID.setCodigoOficina(this.codigoOficina); 
        Integer anoAnterior = new Integer(anoActual - 1);
        consecutivoID.setAno(anoAnterior);
        consecutivoAnoAnterior.setConsecutivoClorID(consecutivoID);  
        consecutivoAnoAnterior = this.consecutivoClorBO.buscar(consecutivoAnoAnterior);
        
        if ((UtilidadesI.esMesAnterior(gc) == true && mesActual == 0 && mesFechaInicioInteruup == 11) && (consecutivoAnoAnterior.getAbrirAno().equals(ConsecutivoClor.ABRIR_ANO_NO))) {
            String mensaje = "Para ingresar esta interrupción de diciembre del año pasado debe contactar al administrador del sistema.";
            this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";             
            return false;
        }   
            
        if (anoFechaInicioInteruup + 1 == anoActual && consecutivoAnoAnterior.getAbrirAno().equals(ConsecutivoClor.ABRIR_ANO_NO)) {
            String mensaje = "Para ingresar esta interrupción del año pasado debe contactar al administrador del sistema.";
            this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";             
            return false;
        }
        
        if (anoFechaInicioInteruup + 1 < anoActual) {
            String mensaje = "Únicamente se pueden ingresar interrupciones del año pasado.";
            this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";             
            return false;
        }          
        
        Integer anoConsecutivo = this.consecutivoClorBO.getAnoConsecutivo(this.codigoOficina);
        if(anoConsecutivo.intValue() + 1 == anoFechaInicioInteruup){
            String mensaje = "Sólo se pueden incluir interrupciones del año pasado";
            this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";             
            return false;
        }    
        
        
        if (this.lugarInterrupcion == null || this.lugarInterrupcion.trim().length() == 0) {
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "EL lugar de interrupción es requerido."));

            return false;
        }

        if (this.pueblo.getNumero() == null || this.pueblo.getNumero().intValue() <= 0) {
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El pueblo es requerido."));

            return false;
        }
        
        /*if (this.puebloBO.puebloExiste(this.pueblo) == false) {
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            msg.setDetail("El pueblo no existe");
            FacesContext.getCurrentInstance().addMessage("form1:puebloFalla", msg);
            return false;
        } //se sustituye por el servicio web, desarrollado por José Coto
          // El objetivo de este servicio es validad el pueblo directamente en @ce+
         */
        
    	if(this.pueblo.getNumero() == null || this.pueblo.getNumero().intValue() <= 0){
			
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El pueblo es requerido."));

			return false;
		}	
    	
    	System.out.println("Validar pueblo: " + this.pueblo.getNumero());
        
    	if(!validarPueblo(this.pueblo)){
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Pueblo No existe."));
	        System.out.println("---> Pueblo no existe");
			return false;			
		}
                
        if (this.codigoCalleFalla == null || this.codigoCalleFalla.intValue() <= 0) {
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La calle es requerida."));

            return false;
        }
        if (this.posteFalla == null || this.posteFalla.intValue() < 0) {
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El poste es requerido."));

            return false;
        }
        if (this.secuenciaFalla == null || this.secuenciaFalla.intValue() < 0) {
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La secuencua es requerida."));

            return false;
        }

       /*if (this.puebloEquipo.getCodigoPueblo() == null || this.puebloEquipo.getCodigoPueblo().intValue() <= 0) {
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            msg.setDetail("El pueblo del equipo es requerido");
            FacesContext.getCurrentInstance().addMessage("form1:puebloEquipo", msg);
            return false;
        }*/
        if(this.puebloEquipo.getNumero() == null || this.puebloEquipo.getNumero().intValue() <= 0){
	
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El pueblo del equipo es requerido."));

			return false;
		}
        
        if (!validarPueblo(this.puebloEquipo)) {
        
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El pueblo no existe."));

            return false;
        }
        
        
       /* if (this.puebloBO.puebloExiste(this.puebloEquipo) == false) {
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            msg.setDetail("El pueblo no existe");
            FacesContext.getCurrentInstance().addMessage("form1:puebloEquipo", msg);
            return false;
        }   Se cambia por !validar pueblo por medio del servicio Web     */
        
        if (this.codigoCalleEquipo == null || this.codigoCalleEquipo.intValue() <= 0) {
      
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La calle es requerida."));

            return false;
        }
        if (this.posteEquipo == null || this.posteEquipo.intValue() < 0) {
    
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El poste es requerido."));
            return false;
        }
        if (this.secuenciaEquipo == null || this.secuenciaEquipo.intValue() < 0) {
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La secuencia es requerida."));

            return false;
        }

        if(this.subestacion == null || this.subestacion.intValue() == 0){
          
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La subestación es requerida."));

            return false;
        }else{        
            if(this.subEstacionBO.buscar(this.subestacion) == null){
             
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La subestación digitada no existe."));

                return false;
            }
        }
        
        if(this.circuito == null || this.circuito.intValue() == 0){
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El circuito es requerido."));

            return false;
        }else{
            if(this.circuitoBO.buscar(this.subestacion, this.circuito) == null){
               
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El circuito digitado no existe."));

                return false;
            }
        }
        
        if(this.seccion == null || this.seccion.longValue() <= 0){
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La sección es requerida."));
            return false;
        }else{
        
            SeccionID seccionID = new SeccionID(this.subestacion, this.circuito, this.seccion);
            Seccion seccion = this.seccionBO.buscar(seccionID);
            
            if(seccion == null){
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La sección digitada no existe."));

                return false;
            }
            
            if(seccion.getCodigoOficina().equals(this.codigoOficina) == false){
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La sección no pertenece a la oficina seleecionada."));

                return false;
            }     
            
            if(seccion.getCongelado().equals(Seccion.ESTADO_CONGELADO)){
         
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La sección se encuentra congelada."));

                return false;
            }
            
            if(this.clientesAfectados == null || this.clientesAfectados.longValue() <= 0){
       
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La cantidad de clientes afectados es requerida."));

                return false;
            }                                
            
        }                  

        return resultado;
    }
   
    /**
	 * Método para validar el pueblo de una interrupción
	 * @param p
	 * @return
	 */
    @SuppressWarnings("unchecked")
    private boolean validarPueblo(Pueblo p) {
		boolean correcto = false;
		try {
			List<Pueblo> listaPueblo = new ArrayList<Pueblo>();
			// Llena el objeto de tipo consulta con los valores que necesita
			// para dicha consulta
			ConsultaPueblos consulta = new ConsultaPueblos();
			consulta.getDatos().setTipoConsulta(Integer.valueOf(0));// 0 para
																	// buscar
																	// por
																	// código de
																	// pueblo
			consulta.getDatos().setPueblo(p.getNumero());
			// Se crea un objeto de tipo respuesta que reciba los datos que
			// devuelve la consulta
			System.out.println(this.serviciosAcemas);
			RespuestaConsultaPueblos respuesta;

			respuesta = this.serviciosAcemas.consultaPueblo(consulta);
			// Se carga la lista de pueblos con la respuesta del broker
			if (!respuesta.getEncabezado().getCodigoCompletacion().equals(Integer.valueOf(2))
					&& !respuesta.getEncabezado().getCodigoRazon().equals(Integer.valueOf(2))) {
				listaPueblo = respuesta.getDatos().getPueblos();
				p = listaPueblo.get(0);
				correcto = true;
			}
			
		} catch (JiBXException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return correcto;

	}
	
	/**
	 * Método para obtener el pueblo de una interrupción
	 * @param p
	 * @return
	 */
	private Pueblo obtenerPueblo(Pueblo p) {

		try {
			List<Pueblo> listaPueblo = new ArrayList<Pueblo>();
			// Llena el objeto de tipo consulta con los valores que necesita
			// para dicha consulta
			ConsultaPueblos consulta = new ConsultaPueblos();
			consulta.getDatos().setTipoConsulta(Integer.valueOf(0));// 0 para
			// buscar
			// por
			// código de
			// pueblo
			consulta.getDatos().setPueblo(p.getNumero());
			// Se crea un objeto de tipo respuesta que reciba los datos que
			// devuelve la consulta
			System.out.println(this.serviciosAcemas);
			RespuestaConsultaPueblos respuesta;

			respuesta = this.serviciosAcemas.consultaPueblo(consulta);
			// Se carga la lista de pueblos con la respuesta del broker
			if((!respuesta.getEncabezado().getCodigoCompletacion().equals(Integer.valueOf(2))) && (!respuesta.getEncabezado().getCodigoRazon().equals(Integer.valueOf(2)))){
				listaPueblo = respuesta.getDatos().getPueblos();
				p = listaPueblo.get(0);
			}

		} catch (JiBXException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return p;

	}

    
    /**
     * Método que determina si es satisfactorio o no, ir hacia la página 2
     * @return String "success" ó "error" de acuerdo a si existen o no errores
     * en los datos ingresados
     */
    public String irPagina2() {
        this.accionProvolma = "";
        this.estructuraSelected = "";
        if (validarDatosInterrupcion()) {
            this.tituloFinal = this.titulo ;//+ this.getConsecutivoInterrupcion();
            if(this.operadorScada == null)
                this.operadorScada = this.operador;
            return "success";
        } else {
        	System.out.println("Error");
            return "error";
        }
    }


    /**
     * Método que determina si es satisfactorio o no, ir hacia la página 3
     * @return String "success" ó "error" de acuerdo a si existen o no errores
     * en los datos ingresados
     */
    public String validarDatosPagina2() {
        //FacesMessage msg = new FacesMessage();
        accionProvolma = "";
        double horasInicio;
        double horasFinal;
        double diferencia;
        
        
        if(this.codigoProteccion == null || this.codigoProteccion.intValue() <= 0){
         
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de protección es requerido."));

            return "error";
        }else{
            if(this.proteccionBO.buscar(this.codigoProteccion) == null){

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de protección no existe."));

                return "error";
            }
        }
        if(this.codigoMaterial == null || this.codigoMaterial.intValue() <= 0){

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de material es requerido."));

            return "error";
        }else{
            if(this.materialBO.buscar(this.codigoMaterial) == null){

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de material no existe."));

                return "error";
            }
        }
        
        if(this.codigoCausa == null || this.codigoCausa.intValue() <= 0){

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de causa es requerido."));

            return "error";
        }else{
            if(this.causaBO.buscar(this.codigoCausa) == null){

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de causa no existe."));

                return "error";
            }
        }    
        
        if(this.codigoCausa2 != null && this.codigoCausa2.intValue() <= 0){

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de causa opcional no posee un valor válido."));

            return "error";
        }else{
            if(this.codigoCausa2 != null && this.causaBO.buscar(this.codigoCausa2) == null){

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de causa opcional no existe."));

                return "error";
            }
        }          

        Provolma provolma = new Provolma();
        ProvolmaID provolmaID = new ProvolmaID();
        provolmaID.setCodigoVoltaje(this.tipoVoltaje==null?Integer.valueOf(0) : this.tipoVoltaje.getTipoVoltajeID().getCodigoVoltaje());
        Dano dano = new Dano();
        dano.setCodigoDano(this.codigoDano);
        provolmaID.setDano(dano);
        Material material = new Material();
        material.setCodigoMaterial(this.codigoMaterial);
        provolmaID.setMaterial(material);
        Proteccion proteccion = new Proteccion();
        proteccion.setCodigoProteccion(this.codigoProteccion);
        provolmaID.setProteccion(proteccion);
        provolma.setProvolmaID(provolmaID);
        if (provolmaBO.existe(provolma) == false) {
            this.accionProvolma = "<SCRIPT language='JavaScript1.2'>" +
    		"window.alert('ERROR! Combinación de códigos no existe en PROVOLMA');" +
    		"</SCRIPT>";	        
	        return "error";
        }

        if (this.tipoVoltaje == null || this.tipoVoltaje.getTipoVoltajeID().getCodigoVoltaje().intValue() == 0) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El voltaje es requerido."));

            return "error";
        }

        if (this.codigoMaterial.intValue() == 201 || this.codigoMaterial.intValue() == 230 || this.codigoMaterial.intValue() == 234) {
 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El Material seleccionado no es un material permitido para reporte."));

            return "error";
        }

        if (this.codigoMaterial.intValue() == 223) {
            if ((this.cantidadCable == null || this.cantidadCable.intValue() < 0) || (this.tipoCable == null || this.tipoCable.intValue() < 0)) {
    			String mensaje = "Seleccionó el código 223.  Ingrese el detalle";
    	        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";                
                return "error";
            }
        }
        if (this.tipoVoltaje.getTipoVoltajeID().getCodigoVoltaje().intValue() == 10) {
            if (this.codigoCausa.intValue() < 421 || this.codigoCausa.intValue() > 424) {
				String mensaje = "Para el código de voltaje 10 sólo se permiten las causas de la 421 a la 424";
		        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";                
                return "error";
            }
        }

        if (this.tipoVoltaje.getTipoVoltajeID().getCodigoVoltaje().intValue() == 10 && this.codigoCausa2 != null && this.codigoCausa2.intValue() > 0) {
            if (this.codigoCausa2.intValue() < 421 || this.codigoCausa2.intValue() > 424) {
				String mensaje = "Para el código de voltaje 10 sólo se permiten las causas de la 421 a la 424";
		        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";                
                return "error";
            }
        }

        horasInicio = Fechas.millisToMinutes(this.fechaInicioInterrupcion.getTime());
        horasFinal = Fechas.millisToMinutes(this.fechaFinalInterrupcion.getTime());
        diferencia = horasFinal - horasInicio;
        if (this.codigoCausa.intValue() != 411 && diferencia > 5) {
    		String mensaje = "Información: revise las fechas de la interrupción, únicamente la causa 411 permite incluir interrupciones mayores a 5 minutos";
            this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";	
            return "error";
        }

        if (this.codigoCausa.intValue() == 417) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La Causa Catástrofe (417) no es una causa permitida para el reporte."));

            return "error";
        }
        if (this.codigoCausa.intValue() == 402) {
            if (this.codigoAnimal == null || this.codigoAnimal.intValue() <= 0) {
				String mensaje = "Seleccionó el código 402.  Ingrese el detalle";
		        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";                
                return "error";
            }
        }

		if((this.irCuadrillaVehiculo.booleanValue()) && ((this.empleadosCuadrilla == null || this.empleadosCuadrilla.size() == 0) && (this.vehiculo.getVehiculoID().getNumeroVehiculo() == null || this.vehiculo.getVehiculoID().getNumeroVehiculo().longValue() <= 0))){

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Se indicó que existe el detalle de cuadrilla y vehículo. Debe ingresar tal detalle."));

		return "error";
		}                

        return "success";
    }
    
    /**
     * Método utilizado para que la segunda pagina realice la acción de regresar a la primera
     * @return <code>String</code> successEdicion o successIngreso 
     */
    public String regresarDePaginaDos(){
        this.accionProvolma = "";
        this.nuevoFoco="form1:voltaje"; 
        this.regresaDePag = true;
        if(this.isModificar())
            return "successEdicion";
        else
            return "successIngreso";
    }

    /**
     * Método que retorna las posibles causas que originaron una interrupción
     * @return Lista de Causas
     */
    public List getCausasSelectItems() {
        List selectItems = new ArrayList();
        List causas = this.causaBO.getCausas();
        selectItems.add(new SelectItem(new Integer(0), "-- Causa Opcional --"));
        if (causas != null) {
            for (int i = 0; i < causas.size(); i++) {
                Causa causa = (Causa) causas.get(i);
                selectItems.add(new SelectItem(causa.getCodigoCausa(), causa.getCodigoCausa() + " - " + causa.getNombreCausa()));
            }
        }
        return selectItems;
    }

    /**
     * Método que valida los datos insertados por el ussuario, correspondientes
     * al vehiculo
     * @return Boolean "true" cuando los datos son correctos, sino retorna "false"
     */
    public boolean validarDatosCuadrilla() {
       // FacesMessage msg = new FacesMessage();
        double horasInicio = 0;
        double horasFinal = 0;
        double horasInicioVehiculo = 0;
        double horasAvisoVehiculo = 0;
        double horasFinalVehiculo = 0;  
        double horasLocalizacion = 0;
		double horaInicioInterrup =0;
		double horaFinInterrup =0;
        double horaFin = 0;
        boolean resultado = true;
        if (this.vehiculo.getVehiculoID().getNumeroVehiculo() == null || this.vehiculo.getVehiculoID().getNumeroVehiculo().longValue() <= 0) {
            return true;
        } else {
            Date dummy = new Date();
            if (this.vehiculo.getTiempoAviso() == null || this.vehiculo.getTiempoAviso().equals(dummy)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de aviso es requerida."));

                return false;
            }
            if (this.vehiculo.getHoraInicio() == null || this.vehiculo.getHoraInicio().equals(dummy)) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de salida es requerida."));

                return false;
            }
            if (this.vehiculo.getHoraLlegada() == null || this.vehiculo.getHoraLlegada().equals(dummy)) {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de llegada es requerida."));

                return false;
            }
            
            if(this.vehiculo.getHoraFin() == null || this.vehiculo.getHoraFin().equals(dummy)){
 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de fin es requerida."));

                return false;
            }
            if(this.vehiculo.getFechaLocalizacion() == null || this.vehiculo.getFechaLocalizacion().equals(dummy)){
      
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha y hora de la Localización es requerida."));

                return false;
            }
            

            horasInicio = Fechas.millisToMinutes(this.fechaInicioInterrupcion.getTime());
            horasFinal = Fechas.millisToMinutes(this.fechaFinalInterrupcion.getTime());
            horasInicioVehiculo = Fechas.millisToMinutes(this.vehiculo.getHoraInicio().getTime());
            horasFinalVehiculo = Fechas.millisToMinutes(this.vehiculo.getHoraLlegada().getTime());
            horasAvisoVehiculo = Fechas.millisToMinutes(this.vehiculo.getTiempoAviso().getTime());            
            horaFin = Fechas.millisToMinutes(this.vehiculo.getHoraFin().getTime());
            horasLocalizacion = Fechas.millisToMinutes(this.vehiculo.getFechaLocalizacion().getTime());
            horaInicioInterrup = Fechas.millisToMinutes(this.fechaInicioInterrupcion.getTime());
            horaFinInterrup = Fechas.millisToMinutes(this.fechaFinalInterrupcion.getTime());
 
            if(horasLocalizacion > horaFinInterrup)
            {
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha y hora de la Localización no puede ser mayor a la hora fin de la interrupción."));

                return false;
            }
            
            if(horasLocalizacion < horaInicioInterrup)
            {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha y hora de la Localización no puede ser menor a la hora de la interrupción."));

                return false;
            }
            
            if(horasLocalizacion < horasFinalVehiculo)
            {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha y hora de la Localización tiene que ser mayor o igual a la hora de llegada del vehículo."));

                return false;
            }
            
            if(horasLocalizacion < horasAvisoVehiculo)
            {
   
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha y hora de la Localización no puede ser menor a la hora de aviso a la cuadrilla."));

                return false;
            }
            
            if(!(horasAvisoVehiculo >= horasInicio)){
    
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de aviso a la cuadrilla debe ser mayor o igual a la fecha de inicio de la interrupción."));

                return false;
            }
            
            if(!(horasInicioVehiculo >= horasAvisoVehiculo)){

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de salida del vehículo debe ser mayor o igual a la fecha de aviso a la cuadrilla."));

                return false;
            }
            
            if(!(horasFinalVehiculo >= horasInicioVehiculo)){

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de llegada del vehículo debe ser mayor o igual a la fecha de salida del vehículo."));

                return false;
            }    
            
            if(!(horasFinal <= horaFin)){

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de cierre de la cuadrilla debe ser mayor o igual a la fecha y hora de fin de la interrupción."));

                return false;
            }
            
            GregorianCalendar fechaActual = new GregorianCalendar();
            double horasActual = Fechas.millisToMinutes(fechaActual.getTime().getTime());
            if(!(horaFin <= horasActual)){

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha y hora de cierre de la cuadrilla debe de ser menor o igual a la fecha y hora actual."));

                return false;
            }         

            if (this.vehiculo.getKilometrosInicio() == null || this.vehiculo.getKilometrosInicio().doubleValue() <= 0) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El kilometraje inicial es requerido."));

                return false;
            }
            if (this.vehiculo.getKilometrosLlegada() == null || this.vehiculo.getKilometrosLlegada().doubleValue() <= 0) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El kilometraje de llegada es requerido."));

                return false;
            }
            if (this.vehiculo.getKilometrosFinal() == null || this.vehiculo.getKilometrosFinal().doubleValue() <= 0) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El kilometraje fianl es requerido."));

                return false;
            }
            if (this.vehiculo.getKilometrosInicio().doubleValue() > this.vehiculo.getKilometrosLlegada().doubleValue()) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El kilometraje inicial debe ser menor o igual que el de llegada."));

                return false;
            }

            if (this.vehiculo.getKilometrosLlegada().doubleValue() > this.vehiculo.getKilometrosFinal().doubleValue()) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El kilometraje llegada debe ser menor o igual que el final."));

                return false;
            }
            if(this.vehiculo.getKilometrosLocalizacion().doubleValue() > this.vehiculo.getKilometrosFinal().doubleValue()){

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El kilometraje de la Localización debe ser menor o igual que el final."));

				return false;
			}
			if(this.vehiculo.getKilometrosLocalizacion().doubleValue() < this.vehiculo.getKilometrosLlegada().doubleValue()){

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El kilometraje de la Localización debe ser mayor o igual que el final."));

				return false;
			}

        }

        return resultado;
    }

    /**
     * Método que almacena una interrupción menor a cinco minutos en la base de
     * datos
     * @return String "success" cuando se almacena la interrupción menor a cinco
     * minutos en la base de datos
     */
    public String guardarReporte() {
        
        this.accionProvolma = "";
   
	    String resultadoPag2 = this.validarDatosPagina2();
	    if(resultadoPag2.equals("error")){
	        return "error";
	    }

        if (this.observaciones == null || this.observaciones.trim().length() == 0) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Las observaciones son requeridas."));

            return "error";
        }

        ReporteID reporteID = new ReporteID();
        double horasInicio = 0;
        double horasFinal = 0;
        double tiefue = 0;
        double horasAbonado = 0;

        if(isModificar() == false){
            this.primeraVezConsecutivo = true;
            this.getConsecutivoInterrupcion();
        }
        String valoresLlave[] = this.consecutivoInterrupcion.split("-");

        horasInicio = Fechas.millisToMinutes(this.fechaInicioInterrupcion.getTime());
        horasFinal = Fechas.millisToMinutes(this.fechaFinalInterrupcion.getTime());
        tiefue = horasFinal - horasInicio;
        double tiefueHoras = tiefue / 60.0;
        tiefueHoras = UtilidadesI.roundNum(tiefueHoras);
        horasAbonado = tiefueHoras * this.clientesAfectados.longValue();

        reporteID.setAa(new Integer(valoresLlave[0].trim()));
        reporteID.setCodigoOficina(new Integer(valoresLlave[1].trim()));
        reporteID.setNumeroReporte(new Long(valoresLlave[2].trim()));
        if(this.reporte == null){
            this.reporte = new Reporte();
	        reporte.setReporteID(reporteID);
        }
        else{
            this.reporte = this.reporteBO.getReporte(reporteID);
            if(this.reporte == null){
                this.reporte = new Reporte();
                this.reporte.setReporteID(reporteID);
            }
        }
        
        SeccionID seccionID = new SeccionID(this.subestacion, this.circuito, this.seccion);
        Seccion seccion = this.seccionBO.buscar(seccionID);

        /** ********************************************************************************************* */
        reporte.setAbonadoAfectado(this.clientesAfectados);

        if (this.bitacora.booleanValue())
            reporte.setBitacora(new Integer(1));
        else
            reporte.setBitacora(new Integer(0));

        reporte.setCausa1(this.codigoCausa);
        if (this.codigoCausa2 != null && this.codigoCausa2.intValue() > 0)
            reporte.setCausa2(this.codigoCausa2);
        reporte.setCircuito(this.circuito);
        if(this.codigoAgencia.intValue() > 0)
            reporte.setCodigoAgencia(this.codigoAgencia);
        if (this.codigoCausa.intValue() == 402)
            reporte.setCodigoAnimal(this.codigoAnimal);
        else
            reporte.setCodigoAnimal(null);
        reporte.setCodigoCalle(this.codigoCalleFalla);
        reporte.setCodigoDano(this.codigoDano);
        reporte.setCodigoCalleEquipo(this.codigoCalleEquipo);
        reporte.setCodigoMaterial(this.codigoMaterial);
        if (this.codigoMaterial.intValue() == 223) {
            reporte.setAcometida(this.cantidadCable);
            reporte.setTipoAcometida(this.tipoCable);
        }

        reporte.setCodigoProteccion(this.codigoProteccion);
        if (this.pueblo.getNumero() != null && this.pueblo.getNumero().intValue() > 0) {
            reporte.setCodigoPuebloEquipo(this.puebloEquipo.getNumero());
            reporte.setCodigoPueblo(this.pueblo.getNumero());
        }
        reporte.setCodigoVoltaje(this.tipoVoltaje.getTipoVoltajeID().getCodigoVoltaje());
        reporte.setComentario(this.observaciones.trim().toUpperCase());
        if (this.faseR != null && this.faseR.booleanValue())
            reporte.setFaseR("R");
        else
            reporte.setFaseR("");
        if (this.faseS != null && this.faseS.booleanValue())
            reporte.setFaseS("S");
        else
            reporte.setFaseS("");
        if (this.faseT != null && this.faseT.booleanValue())
            reporte.setFaseT("T");
        else
            reporte.setFaseT("");

        reporte.setFechaInicio(this.fechaInicioInterrupcion);
        reporte.setFechaFin(this.fechaFinalInterrupcion);

        SimpleDateFormat formater = new SimpleDateFormat("HH mm");
        String hora = formater.format(this.fechaInicioInterrupcion);
        double resultado = Double.parseDouble(hora.substring(0, 2));
        double min = Double.parseDouble(hora.substring(3, 5));
        min = min / 100.0;
        resultado = resultado + min;
        reporte.setHoraInicio(new Double(resultado));
        reporte.setHoraAviso(new Double(resultado));

        formater = new SimpleDateFormat("HH mm");
        hora = formater.format(this.fechaFinalInterrupcion);
        resultado = Double.parseDouble(hora.substring(0, 2));
        min = Double.parseDouble(hora.substring(3, 5));
        min = min / 100.0;
        resultado = resultado + min;
        reporte.setHoraFin(new Double(resultado));
        
        horasAbonado = UtilidadesI.roundNum(horasAbonado);
        reporte.setHorasAbonado(new Double(horasAbonado));

        if (this.indicadorSubestacion.booleanValue())
            reporte.setIndSubEstacion(new Integer(1));
        else
            reporte.setIndSubEstacion(new Integer(0));

        reporte.setLugar(this.lugarInterrupcion.trim().toUpperCase());
        reporte.setMedidor(this.medidor);
        reporte.setNombreCliente(this.cliente.trim().toUpperCase());
        if (this.scada.booleanValue()){
            reporte.setOperadoPor(new Integer(0));
            reporte.setOperadorScada(this.operadorScada.trim().toUpperCase());
        }
        else{
            reporte.setOperadoPor(new Integer(1));
            reporte.setOperadorScada(null);
        }

        reporte.setOperador(this.cedula);
        reporte.setPoste(this.posteFalla);
        reporte.setPosteEquipo(this.posteEquipo);
        reporte.setRegion(seccion.getRegion());
        reporte.setSeccion(this.seccion);
        reporte.setSecuencia(this.secuenciaFalla);
        reporte.setSecuenciaEquipo(this.secuenciaEquipo);
        reporte.setSubEstacion(this.subestacion);
        reporte.setSubRegion(seccion.getSubRegion());
        if(this.indTension)
        {
        	this.reporte.setIndTension(Integer.valueOf(2));
        }else
        {
        	this.reporte.setIndTension(Integer.valueOf(1));
        }
        if(this.telefono != null)
            reporte.setTelefonoCliente(this.telefono.toString());

        reporte.setTiefue(new Double(tiefueHoras));

        int codigoVoltaje = this.tipoVoltaje.getTipoVoltajeID().getCodigoVoltaje().intValue();
        if (codigoVoltaje == 10)
            reporte.setTipoVoltaje(new Integer(1));
        else {
            if (codigoVoltaje == 20 || codigoVoltaje == 30 || codigoVoltaje == 40) {
                reporte.setTipoVoltaje(new Integer(2));
            } else {
                reporte.setTipoVoltaje(new Integer(3));
            }
        }

        if (this.trifasica.booleanValue())
            reporte.setTrifasica(new Integer(1));
        else
            reporte.setTrifasica(new Integer(0));

        /** ********************************************************************************************* */
        

        asignarId(reporteID);

        if (this.vehiculo.getVehiculoID().getNumeroVehiculo() != null && this.vehiculo.getVehiculoID().getNumeroVehiculo().longValue() > 0) {
            this.vehiculo.getVehiculoID().setAa(reporte.getReporteID().getAa());
            this.vehiculo.getVehiculoID().setCodigoOficina(reporte.getReporteID().getCodigoOficina());
            this.vehiculo.getVehiculoID().setNumeroInterrupcion(reporte.getReporteID().getNumeroReporte());
            this.vehiculo.getVehiculoID().setTipo(new Integer(1));
			double total = (this.vehiculo.getKilometrosLlegada().doubleValue() - this.vehiculo.getKilometrosInicio().doubleValue());
			total = total * 2;
            this.vehiculo.setKilometrosTotales(new Double(total));

        }
        if ((this.operacion == null || this.operacion.intValue() != 1) && this.reporteBO.reporteExiste(reporte.getReporteID()) == false) {
            this.reporteBO.agregar(reporte);//agregando interrupcion
            if (this.empleadosCuadrilla != null && this.empleadosCuadrilla.size() > 0  && this.irCuadrillaVehiculo.booleanValue())
                this.cuadrillaBO.agregar(this.empleadosCuadrilla);
            if (this.vehiculo.getVehiculoID().getNumeroVehiculo() != null && this.vehiculo.getVehiculoID().getNumeroVehiculo().longValue() > 0  && this.irCuadrillaVehiculo.booleanValue())
                this.vehiculoBO.agregar(this.vehiculo);

        } else {
            this.reporteBO.modificar(this.reporte);//modificando interrupcion
            if(this.irCuadrillaVehiculo.booleanValue()){
	            if ((this.empleadosCuadrilla != null && this.empleadosCuadrilla.size() > 0) || this.cuadrillaID != null)
	                this.cuadrillaBO.modificar(this.empleadosCuadrilla, this.cuadrillaID);
            }
			else{
			    if(this.cuadrillaID != null)
			        this.cuadrillaBO.modificar(new ArrayList(),this.cuadrillaID);
			}
			if(this.irCuadrillaVehiculo.booleanValue()){
	            if (this.vehiculo.getVehiculoID().getNumeroVehiculo() != null && this.vehiculo.getVehiculoID().getNumeroVehiculo().longValue() > 0){
				    Vehiculo vehiculoViejo = new Vehiculo();
				    vehiculoViejo.setVehiculoID(this.vehiculoIDViejo);
				    vehiculoViejo.setHoraFin(new Date());
				    vehiculoViejo.setHoraInicio(new Date());
				    vehiculoViejo.setHoraLlegada(new Date());
				    vehiculoViejo.setHoraRestablece(new Date());
				    vehiculoViejo.setKilometrosFinal(new Double(0));
				    vehiculoViejo.setKilometrosInicio(new Double(0));
				    vehiculoViejo.setKilometrosLlegada(new Double(0));
				    vehiculoViejo.setKilometrosTotales(new Double(0));
				    vehiculoViejo.setNumeroVehiculo(new Long(0));
				    vehiculoViejo.setTiempoAviso(new Date());				    
				    this.vehiculoBO.eliminar(vehiculoViejo);
					this.vehiculoBO.agregar(this.vehiculo);
	            }
			}
            else {
                if(this.vehiculoID != null){
	                this.vehiculo.setVehiculoID(this.vehiculoID);
	                this.vehiculoBO.eliminar(this.vehiculo);
                }
            }
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.getExternalContext().getSessionMap().put("operacion", new Integer(0));

            this.primeraVezConsecutivo = false;
        }
        String mensaje = "Interrupción menor a 5 minutos almacenada en la base de datos con los siguientes datos: Año: " + reporteID.getAa() + " - " + "Oficina: " + reporteID.getCodigoOficina() + " - " + "Consecutivo: " + reporteID.getNumeroReporte();		                
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Interrupción menor a 5 minutos almacenada en la base de datos con los siguientes datos: Año: " + reporteID.getAa() + " - " + "Oficina: " + reporteID.getCodigoOficina() + " - " + "Consecutivo: " + reporteID.getNumeroReporte()));

        this.accionGuardar = "<SCRIPT language='JavaScript1.2'>" +
		"window.alert('" + mensaje + "');</SCRIPT>";
        FacesContext ctx = FacesContext.getCurrentInstance();	    
	    ctx.getExternalContext().getSessionMap().put("consecutivoInterrupcion",this.consecutivoInterrupcion);
	    this.regresaDePag = false;
        return "success";
    }    
    
    private void asignarId(ReporteID reporteID){
        for(int i = 0; i < this.empleadosCuadrilla.size(); i++){
            ((Cuadrilla)this.empleadosCuadrilla.get(i)).getCuadrillaID().setAa(reporteID.getAa());
            ((Cuadrilla)this.empleadosCuadrilla.get(i)).getCuadrillaID().setCodigoOficina(reporteID.getCodigoOficina());
            ((Cuadrilla)this.empleadosCuadrilla.get(i)).getCuadrillaID().setNumeroInterrupcion(reporteID.getNumeroReporte());
        }
    }
     

    /**
     * Retorna una lista de empleados de acuerdo al nombre del empleado
     * ingresado
     * @return Lista de empleados correspondientes
     */
    public List getListaEmpleados() {
        List empleados = new ArrayList();
        List resultado = null;
        SelectItem item = new SelectItem();
        Empleado emp = new Empleado();
        emp.setCedula(new Long(0));
        emp.setNombreEmpleado(" ");
        item.setValue(emp);
        item.setLabel("Seleccione un Empleado");
        empleados.add(item);
        if(this.codigoOficina != null){
            resultado = this.usuarioOficinaBO.buscarOficinaEmpleado(this.codigoOficina);
        }else{
        	
            Empleado empleado = this.empleadoBO.buscar(this.cedula);  
            List<UsuarioOficina> listaPivote = this.usuarioOficinaBO.buscarCedula(this.cedula);
        	if(!listaPivote.isEmpty())
        	{
        		this.codigoOficina = listaPivote.get(0).getId().getOficina().getCodigoOficina();
        	}else
        	{
        		this.codigoOficina = new Integer(0);
        	}
            resultado = this.usuarioOficinaBO.buscarOficinaEmpleado(this.codigoOficina);
            resultado = this.usuarioOficinaBO.buscarOficinaEmpleado(this.codigoOficina);
        }
        if (resultado != null) {
            for (int i = 0; i < resultado.size(); i++) {
            	UsuarioOficina temporal = (UsuarioOficina) resultado.get(i);
                emp = temporal.getId().getEmpleado();
                item = new SelectItem();
                item.setValue(emp);
                item.setLabel(emp.getNombreCompleto());
                empleados.add(item);
            }
        }
        return empleados;

    }   

    /**
     * Método que válida si los datos de un empleado son correctos para
     * insertarlo a la lista de empleados de la cuadrilla que atendieron la
     * interrupción
     * @return "success" si los datos eran correctos y se agrego el empleado a
     * la lista, de lo contrario retorna "false"
     */
    public String agregarEmpleadoCuadrilla() {
        this.accionProvolma = "";
      
        if (this.empleadoCuadrilla.getCedula().longValue() > 0) {
            if ((this.empleadoResponsable == null || this.empleadoResponsable.booleanValue() == false) && (this.empleadoMiembro == null || this.empleadoMiembro.booleanValue() == false)) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Selecinoe un tipo de empleado."));

                return "error";
            }
            if (empleadoResponsableExiste() == true && this.empleadoResponsable.booleanValue() == true) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El empleado responsable ya fue ingresado."));

                return "error";
            }  
            if (existeEmpleado(this.empleadoCuadrilla.getCedula()) == true) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El empleado ya fue ingresado."));

                return "error";
            }                
            Cuadrilla miCuadrilla = new Cuadrilla();
            CuadrillaID miCuadrillaID = new CuadrillaID();

            miCuadrillaID.setCedula(this.empleadoCuadrilla.getCedula());
            miCuadrillaID.setReporteInterrupcion(new Integer(0));
            miCuadrillaID.setAa(null);
            miCuadrillaID.setCodigoOficina(null);
            miCuadrillaID.setNumeroInterrupcion(null);
            miCuadrilla.setCuadrillaID(miCuadrillaID);
            miCuadrilla.setNombreEmpleado(this.empleadoCuadrilla.getNombreCompleto());
            if (this.empleadoMiembro.booleanValue())
                miCuadrilla.setIndicador("M");
            else {
                if (this.empleadoResponsable.booleanValue())
                    miCuadrilla.setIndicador("R");
            }
            this.empleadosCuadrilla.add(miCuadrilla);
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccione un empleado para agregar."));

        }
        return "success";

    }
    
    /**
     * Determina si un empleado ya fue ingresado a la cuadrilla
     * @param cedula cedula del empleado
     * @return true o false
     */
    private boolean existeEmpleado(Long cedula){
        for(int i = 0; i < this.empleadosCuadrilla.size(); i++){
            Cuadrilla cuadrilla = (Cuadrilla)this.empleadosCuadrilla.get(i);
            if(cuadrilla.getCuadrillaID().getCedula().longValue() == cedula.longValue())
                return true;
        }
        return false;
    }
    
    /**
     * Elimina un empleado de la cuadrilla
     * @return success
     */
	public String eliminarEmpleadoCuadrilla(){
        this.accionProvolma = "";   
	    int index = this.dataTableCuadrilla.getRowIndex();
	    this.empleadosCuadrilla.remove(index);
		return "success";
	}    
    
    private boolean empleadoResponsableExiste(){
        for(int i = 0; i < this.empleadosCuadrilla.size(); i++){
            Cuadrilla miCuadrilla = (Cuadrilla)this.empleadosCuadrilla.get(i);
            if(miCuadrilla.getIndicador().equals("R"))
                return true;
        }
        return false;
    }


    /**
     * Metodo que carga los datos de una interrupción deseada por el usuario.
     * Esto con el fin de modificarla.
     */
    public void cargarDatos() {
    	this.estructuraSelected = "";
        ReporteID reporteID = new ReporteID();

        String valoresLlave[] = this.consecutivoInterrupcion.split("-");

        reporteID.setAa(new Integer(valoresLlave[0].trim()));
        reporteID.setCodigoOficina(new Integer(valoresLlave[1].trim()));
        reporteID.setNumeroReporte(new Long(valoresLlave[2].trim()));
        this.reporte = reporteBO.getReporte(reporteID);

        this.clientesAfectados = reporte.getAbonadoAfectado();
        this.cantidadCable = reporte.getAcometida();
        if (reporte.getBitacora().intValue() == 1)
            this.bitacora = new Boolean(true);
        else
            this.bitacora = new Boolean(false);
        this.codigoCausa = reporte.getCausa1();
        this.codigoCausa2 = reporte.getCausa2();
        this.circuito = reporte.getCircuito();
        this.codigoAgencia = reporte.getCodigoAgencia();
        this.codigoAnimal = reporte.getCodigoAnimal();
        if(this.codigoAnimal != null){
			Animal animal = this.animalBO.buscar(this.codigoAnimal);
			this.nombreAnimal = animal.getNombreAnimal();
        }
        this.codigoCalleFalla = reporte.getCodigoCalle();
        this.codigoCalleEquipo = reporte.getCodigoCalleEquipo();
        this.codigoDano = reporte.getCodigoDano();
        this.codigoMaterial = reporte.getCodigoMaterial();
        this.codigoProteccion = reporte.getCodigoProteccion();
        Pueblo p = new Pueblo();
        if(reporte.getCodigoPueblo() != null && reporte.getCodigoPueblo().intValue() > 0){
            p.setNumero(reporte.getCodigoPueblo());
            this.pueblo = this.obtenerPueblo(p);     
            this.nombrePueblo = this.pueblo.getNombre();
        }
        else{
            this.pueblo = new Pueblo();
            this.pueblo.setNumero(null);
            this.nombrePueblo = null;
        }

        if(reporte.getCodigoPuebloEquipo() != null && reporte.getCodigoPuebloEquipo().intValue() > 0){
            p.setNumero(reporte.getCodigoPuebloEquipo());
            this.puebloEquipo = this.obtenerPueblo(p);
        }
        else{
            this.puebloEquipo = new Pueblo();
            this.puebloEquipo.setNumero(null);
                           
        }   
        
        this.observaciones = reporte.getComentario();
        
        
		this.region = this.regionBO.buscar(reporte.getRegion());
	    SubRegionID subID = new SubRegionID();
	    Region region = new Region();
	    region.setRegion(reporte.getRegion());
	    subID.setRegion(region);
	    subID.setSubRegion(reporte.getSubRegion());		
		this.subRegion = this.subRegionBO.buscar(subID);
		this.nombreRegion = this.region.getNombreRegion();
		this.nombreSubregion = this.subRegion.getNombreSubRegion();                 

        if (reporte.getFaseR() == null || reporte.getFaseR().length() == 0)
            this.faseR = null;
        else
            this.faseR = new Boolean(true);
        if (reporte.getFaseS() == null || reporte.getFaseS().length() == 0)
            this.faseS = null;
        else
            this.faseS = new Boolean(true);
        if (reporte.getFaseT() == null || reporte.getFaseT().length() == 0)
            this.faseT = null;
        else
            this.faseT = new Boolean(true);

        this.fechaFinalInterrupcion = reporte.getFechaFin();
        this.fechaInicioInterrupcion = reporte.getFechaInicio();
        reporte.getHorasAbonado();
        if (reporte.getIndSubEstacion().intValue() == 1)
            this.indicadorSubestacion = new Boolean(true);
        else
            this.indicadorSubestacion = new Boolean(false);
        this.lugarInterrupcion = reporte.getLugar();
        this.medidor = reporte.getMedidor();
        this.cliente = reporte.getNombreCliente();
        if (reporte.getOperadoPor() != null && reporte.getOperadoPor().intValue() == 0) {
            this.scada = new Boolean(true);
            this.operadorScada = reporte.getOperadorScada();
            this.manual = new Boolean(false);
        } else {
            this.scada = new Boolean(false);
            this.operadorScada = reporte.getOperadorScada();
            this.manual = new Boolean(true);
        }
        this.cedula = reporte.getOperador();
        
        this.empleado = this.empleadoBO.buscar(this.cedula);
        this.operador = this.empleado.getNombreCompleto();
        
        Oficina oficinaInterup = this.oficinaBO.buscar(this.codigoOficina);
        this.oficina = oficinaInterup.getNombreOficina();        
        
        this.posteFalla = reporte.getPoste();
        this.posteEquipo = reporte.getPosteEquipo();
        SeccionID seccionID = new SeccionID();
        seccionID.setCircuito(reporte.getCircuito());
        seccionID.setSeccion(reporte.getSeccion());
        seccionID.setSubEstacion(reporte.getSubEstacion());
        this.seccion = reporte.getSeccion();
        this.secuenciaFalla = reporte.getSecuencia();
        this.secuenciaEquipo = reporte.getSecuenciaEquipo();
        this.subestacion = reporte.getSubEstacion();
        if(reporte.getTelefonoCliente() != null)
            this.telefono = reporte.getTelefonoCliente();
;
        this.tipoCable = reporte.getTipoAcometida();
        if (reporte.getTrifasica().intValue() == 1)
            this.trifasica = new Boolean(true);
        else
            this.trifasica = new Boolean(false);

        TipoVoltajeID tipoVoltajeID = new TipoVoltajeID();
        tipoVoltajeID.setCodigoVoltaje(reporte.getCodigoVoltaje());
        tipoVoltajeID.setTipoVoltaje(reporte.getTipoVoltaje());
        this.tipoVoltaje = new TipoVoltaje();
        this.tipoVoltaje.setTipoVoltajeID(tipoVoltajeID);


        this.cuadrillaID = new CuadrillaID();
        this.cuadrillaID.setAa(reporte.getReporteID().getAa());
        this.cuadrillaID.setCodigoOficina(reporte.getReporteID().getCodigoOficina());
        this.cuadrillaID.setNumeroInterrupcion(reporte.getReporteID().getNumeroReporte());
        this.cuadrillaID.setReporteInterrupcion(new Integer(0));

        this.empleadosCuadrilla = this.cuadrillaBO.getCuadrillas(cuadrillaID);
        if (this.empleadosCuadrilla == null){
            this.empleadosCuadrilla = new ArrayList();
            this.irCuadrillaVehiculo = new Boolean(false);
        }else
		    this.irCuadrillaVehiculo = new Boolean(true);
        
        this.vehiculoID = null;
        VehiculoID vehiculoID = new VehiculoID();
        Vehiculo vehiculo = new Vehiculo();
        vehiculoID.setAa(reporte.getReporteID().getAa());
        vehiculoID.setCodigoOficina(reporte.getReporteID().getCodigoOficina());
        vehiculoID.setNumeroInterrupcion(reporte.getReporteID().getNumeroReporte());
        vehiculoID.setTipo(new Integer(1));
        vehiculo.setVehiculoID(vehiculoID);
        this.vehiculo = this.vehiculoBO.buscar(vehiculo);
        if (this.vehiculo == null) {
            this.vehiculo = new Vehiculo();
            this.vehiculo.setVehiculoID(new VehiculoID());
            this.vehiculoIDViejo = null;
        } else {
            this.vehiculoID = new VehiculoID();
            this.vehiculoID.setAa(this.vehiculo.getVehiculoID().getAa());
            this.vehiculoID.setCodigoOficina(this.vehiculo.getVehiculoID().getCodigoOficina());
            this.vehiculoID.setNumeroInterrupcion(this.vehiculo.getVehiculoID().getNumeroInterrupcion());
            this.vehiculoID.setTipo(new Integer(1));
            this.vehiculoID.setNumeroVehiculo(this.vehiculo.getVehiculoID().getNumeroVehiculo());
            this.vehiculoIDViejo = this.vehiculoID;
        }

        /** ************************************************************* */

        double horasInicio = reporte.getHoraInicio().doubleValue();
        double horasFinal = reporte.getHoraFin().doubleValue();
        Double hora = null;
        Double minutos = null;
        GregorianCalendar gc = null;

        gc = new GregorianCalendar();
        gc.setTime(reporte.getFechaInicio());
        hora = new Double(horasInicio);
        gc.set(GregorianCalendar.HOUR_OF_DAY, hora.intValue());
        if (horasInicio != 0) {
            double min = horasInicio - hora.intValue();
            minutos = new Double(min * 100);
        } else
            minutos = new Double(0);
		long minute = Math.round(minutos.doubleValue());
		minutos = new Double(minute);
        gc.set(GregorianCalendar.MINUTE, minutos.intValue());
        this.fechaInicioInterrupcion = gc.getTime();

        gc = new GregorianCalendar();
        gc.setTime(reporte.getFechaFin());
        hora = new Double(horasFinal);
        gc.set(GregorianCalendar.HOUR_OF_DAY, hora.intValue());
        if (horasFinal != 0) {
            double min = horasFinal - hora.intValue();
            minutos = new Double(min * 100);
        } else
            minutos = new Double(0);
		minute = Math.round(minutos.doubleValue());
		minutos = new Double(minute);
        gc.set(GregorianCalendar.MINUTE, minutos.intValue());
        this.fechaFinalInterrupcion = gc.getTime();

    }

    /**
     * Limpia o reinicia las variables utilizadas para almacenar y desplegar los
     * datos correspondientes a la interrupción
     */
    private void limpiarDatos() {
        this.cliente = "";
        this.medidor = null;
        this.telefonoCliente = "";
        this.telefono = null;
        GregorianCalendar fecha = new GregorianCalendar();
        this.fechaInicioInterrupcion = fecha.getTime();
        this.fechaFinalInterrupcion = this.fechaInicioInterrupcion;
        this.clientesAfectados = null;

        scada = new Boolean(false);
        manual = new Boolean(true);
        this.operadorScada = null;

        this.empleadoCuadrilla = new Empleado();
        this.empleadoCuadrilla.setCedula(new Long(0));
        this.empleadoCuadrilla.setNombreEmpleado("");
        this.vehiculo = new Vehiculo();
        this.vehiculo.setVehiculoID(new VehiculoID());

        this.nombrePueblo = "";
        this.pueblo = new Pueblo();
        this.pueblo.setNombre("");
        this.puebloEquipo = new Pueblo();
        this.puebloEquipo.setNombre("");
        this.observaciones = "";
        this.codigoAnimal = null;
        this.bitacora = new Boolean(false);
        this.trifasica = new Boolean(false);
        this.indicadorSubestacion = new Boolean(false);
        this.faseR = new Boolean(false);
        this.faseS = new Boolean(false);
        this.faseT = new Boolean(false);

        this.cantidadCable = new Integer(0);
        this.tipoCable = new Integer(0);

        this.empleadoMiembro = new Boolean(false);
        this.empleadoResponsable = new Boolean(false);

        this.posteFalla = null;
        this.codigoCalleFalla = null;
        this.secuenciaFalla = null;

        this.codigoCalleEquipo = null;
        this.posteEquipo = null;
        this.secuenciaEquipo = null;

        this.lugarInterrupcion = null;
        this.codigoAgencia = new Integer(0);
        this.tipoVoltaje = new TipoVoltaje();
        TipoVoltajeID tipoVoltajeID = new TipoVoltajeID();
        this.tipoVoltaje.setTipoVoltajeID(tipoVoltajeID);

        this.codigoCausa = null;
        this.codigoCausa2 = null;
        this.codigoDano = null;
        this.codigoMaterial = null;
        this.codigoProteccion = null;

        this.subestacion = null;
        this.circuito = null;
        this.seccion = null;
        this.indTension = false;

        indAgencia = false;
        accionProvolma = "";
        accionGuardar = "";
        
        this.nombreAnimal = "";   
        this.irCuadrillaVehiculo = null;

    }

    /**
     * Metodo accesor de agenciaBO.
     * 
     * @return Retorna el agenciaBO.
     */
    public AgenciaBO getAgenciaBO() {
        return agenciaBO;
    }

    /**
     * Metodo modificador de agenciaBO.
     * 
     * @param agenciaBO
     *            El agenciaBO a modificar.
     */
    public void setAgenciaBO(AgenciaBO agenciaBO) {
        this.agenciaBO = agenciaBO;
    }

    /**
     * Metodo accesor de animalBO.
     * 
     * @return Retorna el animalBO.
     */
    public AnimalBO getAnimalBO() {
        return animalBO;
    }

    /**
     * Metodo modificador de animalBO.
     * 
     * @param animalBO
     *            El animalBO a modificar.
     */
    public void setAnimalBO(AnimalBO animalBO) {
        this.animalBO = animalBO;
    }

    /**
     * Metodo accesor de bitacora.
     * 
     * @return Retorna el bitacora.
     */
    public Boolean getBitacora() {
        return bitacora;
    }

    /**
     * Metodo modificador de bitacora.
     * 
     * @param bitacora
     *            El bitacora a modificar.
     */
    public void setBitacora(Boolean bitacora) {
        this.bitacora = bitacora;
    }

    /**
     * Metodo accesor de cantidadCable.
     * 
     * @return Retorna el cantidadCable.
     */
    public Integer getCantidadCable() {
        return cantidadCable;
    }

    /**
     * Metodo modificador de cantidadCable.
     * 
     * @param cantidadCable
     *            El cantidadCable a modificar.
     */
    public void setCantidadCable(Integer cantidadCable) {
        this.cantidadCable = cantidadCable;
    }

    /**
     * Metodo accesor de causaBO.
     * 
     * @return Retorna el causaBO.
     */
    public CausaBO getCausaBO() {
        return causaBO;
    }

    /**
     * Metodo modificador de causaBO.
     * 
     * @param causaBO
     *            El causaBO a modificar.
     */
    public void setCausaBO(CausaBO causaBO) {
        this.causaBO = causaBO;
    }


    /**
     * Metodo accesor de circuitoBO.
     * 
     * @return Retorna el circuitoBO.
     */
    public CircuitoBO getCircuitoBO() {
        return circuitoBO;
    }

    /**
     * Metodo modificador de circuitoBO.
     * 
     * @param circuitoBO
     *            El circuitoBO a modificar.
     */
    public void setCircuitoBO(CircuitoBO circuitoBO) {
        this.circuitoBO = circuitoBO;
    }

    /**
     * Metodo accesor de cliente.
     * 
     * @return Retorna el cliente.
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * Metodo modificador de cliente.
     * 
     * @param cliente
     *            El cliente a modificar.
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    /**
     * Metodo accesor de clientesAfectados.
     * 
     * @return Retorna el clientesAfectados.
     */
    public Long getClientesAfectados() {
        return clientesAfectados;
    }

    /**
     * Metodo modificador de clientesAfectados.
     * 
     * @param clientesAfectados
     *            El clientesAfectados a modificar.
     */
    public void setClientesAfectados(Long clientesAfectados) {
        this.clientesAfectados = clientesAfectados;
    }

    /**
     * Metodo accesor de codigoAgencia.
     * 
     * @return Retorna el codigoAgencia.
     */
    public Integer getCodigoAgencia() {
        return codigoAgencia;
    }

    /**
     * Metodo modificador de codigoAgencia.
     * 
     * @param codigoAgencia
     *            El codigoAgencia a modificar.
     */
    public void setCodigoAgencia(Integer codigoAgencia) {
        this.codigoAgencia = codigoAgencia;
    }

    /**
     * Metodo accesor de codigoAnimal.
     * 
     * @return Retorna el codigoAnimal.
     */
    public Integer getCodigoAnimal() {
        return codigoAnimal;
    }

    /**
     * Metodo modificador de codigoAnimal.
     * 
     * @param codigoAnimal
     *            El codigoAnimal a modificar.
     */
    public void setCodigoAnimal(Integer codigoAnimal) {
        this.codigoAnimal = codigoAnimal;
    }

    /**
     * Metodo accesor de codigoCausa.
     * 
     * @return Retorna el codigoCausa.
     */
    public Integer getCodigoCausa() {
        return codigoCausa;
    }

    /**
     * Metodo modificador de codigoCausa.
     * 
     * @param codigoCausa
     *            El codigoCausa a modificar.
     */
    public void setCodigoCausa(Integer codigoCausa) {
        this.codigoCausa = codigoCausa;
    }

    /**
     * Metodo accesor de codigoCausa2.
     * 
     * @return Retorna el codigoCausa2.
     */
    public Integer getCodigoCausa2() {
        return codigoCausa2;
    }

    /**
     * Metodo modificador de codigoCausa2.
     * 
     * @param codigoCausa2
     *            El codigoCausa2 a modificar.
     */
    public void setCodigoCausa2(Integer codigoCausa2) {
        this.codigoCausa2 = codigoCausa2;
    }

    /**
     * Metodo accesor de codigoDano.
     * 
     * @return Retorna el codigoDano.
     */
    public Integer getCodigoDano() {
        return codigoDano;
    }

    /**
     * Metodo modificador de codigoDano.
     * 
     * @param codigoDano
     *            El codigoDano a modificar.
     */
    public void setCodigoDano(Integer codigoDano) {
        this.codigoDano = codigoDano;
    }

    /**
     * Metodo accesor de codigoMaterial.
     * 
     * @return Retorna el codigoMaterial.
     */
    public Integer getCodigoMaterial() {
        return codigoMaterial;
    }

    /**
     * Metodo modificador de codigoMaterial.
     * 
     * @param codigoMaterial
     *            El codigoMaterial a modificar.
     */
    public void setCodigoMaterial(Integer codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
    }

    /**
     * Metodo accesor de codigoProteccion.
     * 
     * @return Retorna el codigoProteccion.
     */
    public Integer getCodigoProteccion() {
        return codigoProteccion;
    }

    /**
     * Metodo modificador de codigoProteccion.
     * 
     * @param codigoProteccion
     *            El codigoProteccion a modificar.
     */
    public void setCodigoProteccion(Integer codigoProteccion) {
        this.codigoProteccion = codigoProteccion;
    }

    /**
     * Metodo accesor de consecutivoClorBO.
     * 
     * @return Retorna el consecutivoClorBO.
     */
    public ConsecutivoClorBO getConsecutivoClorBO() {
        return consecutivoClorBO;
    }

    /**
     * Metodo modificador de consecutivoClorBO.
     * 
     * @param consecutivoClorBO
     *            El consecutivoClorBO a modificar.
     */
    public void setConsecutivoClorBO(ConsecutivoClorBO consecutivoClorBO) {
        this.consecutivoClorBO = consecutivoClorBO;
    }

    /**
     * Metodo accesor de consecutivoInterrupcion.
     * @return Retorna el consecutivoInterrupcion.
     */
    public String getConsecutivoInterrupcion() {
        if (primeraVezConsecutivo) {
            this.consecutivoClor = this.consecutivoClorBO.getConsecutivoClorReporte(this.codigoOficina);
            this.consecutivoInterrupcion = this.consecutivoClor.getConsecutivoClorID().getAno() + " - " + this.codigoOficina + " - " + consecutivoClor.getConsecutivoRep();
            primeraVezConsecutivo = false;
        }
        return consecutivoInterrupcion;
    }

    /**
     * Metodo modificador de consecutivoInterrupcion.
     * @param consecutivoInterrupcion
     * El consecutivoInterrupcion a modificar.
     */
    public void setConsecutivoInterrupcion(String consecutivoInterrupcion) {
        this.consecutivoInterrupcion = consecutivoInterrupcion;
    }

    /**
     * Metodo accesor de dataTableCuadrilla.
     * 
     * @return Retorna el dataTableCuadrilla.
     */
    public DataTable getDataTableCuadrilla() {
        return dataTableCuadrilla;
    }

    /**
     * Metodo modificador de dataTableCuadrilla.
     * 
     * @param dataTableCuadrilla
     *            El dataTableCuadrilla a modificar.
     */
    public void setDataTableCuadrilla(DataTable dataTableCuadrilla) {
        this.dataTableCuadrilla = dataTableCuadrilla;
    }

    /**
     * Metodo accesor de dataTableEmpleados.
     * 
     * @return Retorna el dataTableEmpleados.
     */
    public DataTable getDataTableEmpleados() {
        return dataTableEmpleados;
    }

    /**
     * Metodo modificador de dataTableEmpleados.
     * 
     * @param dataTableEmpleados
     *            El dataTableEmpleados a modificar.
     */
    public void setDataTableEmpleados(DataTable dataTableEmpleados) {
        this.dataTableEmpleados = dataTableEmpleados;
    }

    /**
     * Metodo accesor de dataTablePueblos.
     * 
     * @return Retorna el dataTablePueblos.
     */
    public DataTable getDataTablePueblos() {
        return dataTablePueblos;
    }

    /**
     * Metodo modificador de dataTablePueblos.
     * 
     * @param dataTablePueblos
     *            El dataTablePueblos a modificar.
     */
    public void setDataTablePueblos(DataTable dataTablePueblos) {
        this.dataTablePueblos = dataTablePueblos;
    }

    /**
     * Metodo accesor de empleado.
     * 
     * @return Retorna el empleado.
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * Metodo modificador de empleado.
     * 
     * @param empleado
     *            El empleado a modificar.
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    /**
     * Metodo accesor de empleadoBO.
     * 
     * @return Retorna el empleadoBO.
     */
    public EmpleadoBO getEmpleadoBO() {
        return empleadoBO;
    }

    /**
     * Metodo modificador de empleadoBO.
     * 
     * @param empleadoBO
     *            El empleadoBO a modificar.
     */
    public void setEmpleadoBO(EmpleadoBO empleadoBO) {
        this.empleadoBO = empleadoBO;
    }

    /**
     * Metodo accesor de empleadoCuadrilla.
     * 
     * @return Retorna el empleadoCuadrilla.
     */
    public Empleado getEmpleadoCuadrilla() {
        return empleadoCuadrilla;
    }

    /**
     * Metodo modificador de empleadoCuadrilla.
     * 
     * @param empleadoCuadrilla
     *            El empleadoCuadrilla a modificar.
     */
    public void setEmpleadoCuadrilla(Empleado empleadoCuadrilla) {
        this.empleadoCuadrilla = empleadoCuadrilla;
    }

    /**
     * Metodo accesor de empleadoMiembro.
     * 
     * @return Retorna el empleadoMiembro.
     */
    public Boolean getEmpleadoMiembro() {
        return empleadoMiembro;
    }

    /**
     * Metodo modificador de empleadoMiembro.
     * 
     * @param empleadoMiembro
     *            El empleadoMiembro a modificar.
     */
    public void setEmpleadoMiembro(Boolean empleadoMiembro) {
        this.empleadoMiembro = empleadoMiembro;
    }

    /**
     * Metodo accesor de empleadoResponsable.
     * 
     * @return Retorna el empleadoResponsable.
     */
    public Boolean getEmpleadoResponsable() {
        return empleadoResponsable;
    }

    /**
     * Metodo modificador de empleadoResponsable.
     * 
     * @param empleadoResponsable
     *            El empleadoResponsable a modificar.
     */
    public void setEmpleadoResponsable(Boolean empleadoResponsable) {
        this.empleadoResponsable = empleadoResponsable;
    }

    /**
     * Metodo accesor de empleadosCuadrilla.
     * 
     * @return Retorna el empleadosCuadrilla.
     */
    public List getEmpleadosCuadrilla() {
        return empleadosCuadrilla;
    }

    /**
     * Metodo modificador de empleadosCuadrilla.
     * 
     * @param empleadosCuadrilla
     *            El empleadosCuadrilla a modificar.
     */
    public void setEmpleadosCuadrilla(List empleadosCuadrilla) {
        this.empleadosCuadrilla = empleadosCuadrilla;
    }

    /**
     * Metodo accesor de faseR.
     * 
     * @return Retorna el faseR.
     */
    public Boolean getFaseR() {
        return faseR;
    }

    /**
     * Metodo modificador de faseR.
     * @param faseR
     *            El faseR a modificar.
     */
    public void setFaseR(Boolean faseR) {
        this.faseR = faseR;
    }

    /**
     * Metodo accesor de faseS.
     * 
     * @return Retorna el faseS.
     */
    public Boolean getFaseS() {
        return faseS;
    }

    /**
     * Metodo modificador de faseS.
     * 
     * @param faseS
     *            El faseS a modificar.
     */
    public void setFaseS(Boolean faseS) {
        this.faseS = faseS;
    }

    /**
     * Metodo accesor de faseT.
     * 
     * @return Retorna el faseT.
     */
    public Boolean getFaseT() {
        return faseT;
    }

    /**
     * Metodo modificador de faseT.
     * 
     * @param faseT
     *            El faseT a modificar.
     */
    public void setFaseT(Boolean faseT) {
        this.faseT = faseT;
    }

    /**
     * Metodo accesor de fechaFinalInterrupcion.
     * 
     * @return Retorna el fechaFinalInterrupcion.
     */
    public Date getFechaFinalInterrupcion() {
        return fechaFinalInterrupcion;
    }

    /**
     * Metodo modificador de fechaFinalInterrupcion.
     * 
     * @param fechaFinalInterrupcion
     *            El fechaFinalInterrupcion a modificar.
     */
    public void setFechaFinalInterrupcion(Date fechaFinalInterrupcion) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaFinalInterrupcion);
        if(calendar.get(Calendar.YEAR) != 1970){
            this.fechaFinalInterrupcion = fechaFinalInterrupcion;
        }else{
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(this.fechaFinalInterrupcion);
            calendar2.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
            calendar2.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
            this.fechaFinalInterrupcion = calendar2.getTime();
        }
    }

    /**
     * Metodo accesor de fechaInicioInterrupcion.
     * 
     * @return Retorna el fechaInicioInterrupcion.
     */
    public Date getFechaInicioInterrupcion() {
        return fechaInicioInterrupcion;
    }

    /**
     * Metodo modificador de fechaInicioInterrupcion.
     * 
     * @param fechaInicioInterrupcion
     *            El fechaInicioInterrupcion a modificar.
     */
    public void setFechaInicioInterrupcion(Date fechaInicioInterrupcion) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicioInterrupcion);
        if(calendar.get(Calendar.YEAR) != 1970){
            this.fechaInicioInterrupcion = fechaInicioInterrupcion;
        }else{
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(this.fechaInicioInterrupcion);
            calendar2.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
            calendar2.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
            this.fechaInicioInterrupcion = calendar2.getTime();
        }
    }

    /**
     * Metodo accesor de indicadorSubestacion.
     * 
     * @return Retorna el indicadorSubestacion.
     */
    public Boolean getIndicadorSubestacion() {
        return indicadorSubestacion;
    }

    /**
     * Metodo modificador de indicadorSubestacion.
     * 
     * @param indicadorSubestacion
     *            El indicadorSubestacion a modificar.
     */
    public void setIndicadorSubestacion(Boolean indicadorSubestacion) {
        this.indicadorSubestacion = indicadorSubestacion;
    }

    /**
     * Metodo accesor de lugarInterrupcion.
     * 
     * @return Retorna el lugarInterrupcion.
     */
    public String getLugarInterrupcion() {
        return lugarInterrupcion;
    }

    /**
     * Metodo modificador de lugarInterrupcion.
     * 
     * @param lugarInterrupcion
     *            El lugarInterrupcion a modificar.
     */
    public void setLugarInterrupcion(String lugarInterrupcion) {
        this.lugarInterrupcion = lugarInterrupcion;
    }

    /**
     * Metodo accesor de manual.
     * 
     * @return Retorna el manual.
     */
    public Boolean getManual() {
        return manual;
    }

    /**
     * Metodo modificador de manual.
     * 
     * @param manual
     *            El manual a modificar.
     */
    public void setManual(Boolean manual) {
        this.manual = manual;
    }

    /**
     * Metodo accesor de medidor.
     * 
     * @return Retorna el medidor.
     */
    public Long getMedidor() {
        return medidor;
    }

    /**
     * Metodo modificador de medidor.
     * 
     * @param medidor
     *            El medidor a modificar.
     */
    public void setMedidor(Long medidor) {
        this.medidor = medidor;
    }

    /**
     * Metodo accesor de nombreEmpleado.
     * 
     * @return Retorna el nombreEmpleado.
     */
    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    /**
     * Metodo modificador de nombreEmpleado.
     * 
     * @param nombreEmpleado
     *            El nombreEmpleado a modificar.
     */
    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    /**
     * Metodo accesor de nombrePueblo.
     * 
     * @return Retorna el nombrePueblo.
     */
    public String getNombrePueblo() {
        return nombrePueblo;
    }

    /**
     * Metodo modificador de nombrePueblo.
     * 
     * @param nombrePueblo
     *            El nombrePueblo a modificar.
     */
    public void setNombrePueblo(String nombrePueblo) {
        this.nombrePueblo = nombrePueblo;
    }

    /**
     * Metodo accesor de observaciones.
     * 
     * @return Retorna el observaciones.
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Metodo modificador de observaciones.
     * 
     * @param observaciones
     *            El observaciones a modificar.
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Metodo accesor de oficina.
     * 
     * @return Retorna el oficina.
     */
    public String getOficina() {
        return oficina;
    }

    /**
     * Metodo modificador de oficina.
     * 
     * @param oficina
     *            El oficina a modificar.
     */
    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public void ingresar(FacesContext context){
        Object ingresar = context.getExternalContext().getRequestParameterMap().get("init");
        if(ingresar != null){
            this.reporte = null;
            this.titulo = "Ingreso de Interrupción menor a 5 minutos ";
            
           
            
            this.empleado = this.empleadoBO.buscar(this.cedula);
            
            this.consecutivoInterrupcion = null;
            this.operador = this.empleado.getNombreCompleto();
            List<UsuarioOficina> listaPivote = this.usuarioOficinaBO.buscarCedula(this.cedula);
        	if(!listaPivote.isEmpty())
        	{
        		this.oficina = listaPivote.get(0).getId().getOficina().getNombreOficina();
        		this.codigoOficina = listaPivote.get(0).getId().getOficina().getCodigoOficina();
        	}else
        	{
        		this.oficina = "";
        		this.codigoOficina = new Integer(0);
        	}
//          Comentado la restricción cuando se implementó el CIA            
//          boolean userAdmin = Usuario.isUserAdmin();
//          this.comboOficina = (userAdmin) ? new Boolean(false) : new Boolean(true);
          this.comboOficina = new Boolean(false);//          Comentado la restricción cuando se implementó el CIA   

            
            cuadrillaID = null;
            primeraVezConsecutivo = false;
            this.empleadosCuadrilla = new ArrayList();
            this.limpiarDatos();        
            this.region = this.regionBO.buscarPorOficina(this.codigoOficina);
            if(region != null){
                this.nombreRegion = region.getNombreRegion();
                this.subRegion = this.subRegionBO.buscarPorOficina(this.codigoOficina); 
                if(subRegion != null)
                    this.nombreSubregion = this.subRegion.getNombreSubRegion();
                else
                    this.nombreSubregion = "";
            }
            else
                this.nombreRegion = "";             
        }
    }
    
    
    public void ingresarAdmin(FacesContext context){
        Object ingresar = context.getExternalContext().getRequestParameterMap().get("ingresar");
        if(ingresar != null){
            this.reporte = null;
            this.titulo = "Ingreso de Interrupción menor a 5 minutos ";
            
           this.empleado = this.empleadoBO.buscar(this.cedula);
            
            this.consecutivoInterrupcion = null;
            this.operador = this.empleado.getNombreCompleto();
            List<UsuarioOficina> listaPivote = this.usuarioOficinaBO.buscarCedula(this.cedula);
        	if(!listaPivote.isEmpty())
        	{
        		this.oficina = listaPivote.get(0).getId().getOficina().getNombreOficina();
        		this.codigoOficina = listaPivote.get(0).getId().getOficina().getCodigoOficina();
        	}else
        	{
        		this.oficina = "";
        		this.codigoOficina = new Integer(0);
        	}
//          Comentado la restricción cuando se implementó el CIA            
//            boolean userAdmin = Usuario.isUserAdmin();
//            this.comboOficina = (userAdmin) ? new Boolean(false) : new Boolean(true);
            this.comboOficina = new Boolean(false);//          Comentado la restricción cuando se implementó el CIA   
            cuadrillaID = null;
            primeraVezConsecutivo = false;
            this.empleadosCuadrilla = new ArrayList();
            this.limpiarDatos();        
            this.region = this.regionBO.buscarPorOficina(this.codigoOficina);
            if(region != null){
                this.nombreRegion = region.getNombreRegion();
                this.subRegion = this.subRegionBO.buscarPorOficina(this.codigoOficina); 
                if(subRegion != null)
                    this.nombreSubregion = this.subRegion.getNombreSubRegion();
                else
                    this.nombreSubregion = "";
            }
            else
                this.nombreRegion = "";             
        }
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List getOficinasSelectItems(){
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
	 * Determina el nombre del operador
	 * @return nombre del operador
	 */
    public String getOperador() {
        return operador;
    }

    /**
     * Metodo modificador de operador.
     * 
     * @param operador
     *            El operador a modificar.
     */
    public void setOperador(String operador) {
        this.operador = operador;
    }

    /**
     * Metodo accesor de provolmaBO.
     * 
     * @return Retorna el provolmaBO.
     */
    public ProvolmaBO getProvolmaBO() {
        return provolmaBO;
    }

    /**
     * Metodo modificador de provolmaBO.
     * 
     * @param provolmaBO
     *            El provolmaBO a modificar.
     */
    public void setProvolmaBO(ProvolmaBO provolmaBO) {
        this.provolmaBO = provolmaBO;
    }

    /**
     * Metodo accesor de pueblo.
     * 
     * @return Retorna el pueblo.
     */
    public Pueblo getPueblo() {
        return pueblo;
    }

    /**
     * Metodo modificador de pueblo.
     * 
     * @param pueblo
     *            El pueblo a modificar.
     */
    public void setPueblo(Pueblo pueblo) {
        this.pueblo = pueblo;
    }

    /**
     * Metodo accesor de puebloBO.
     * 
     * @return Retorna el puebloBO.
     */
    public PuebloBO getPuebloBO() {
        return puebloBO;
    }

    /**
     * Metodo modificador de puebloBO.
     * 
     * @param puebloBO
     *            El puebloBO a modificar.
     */
    public void setPuebloBO(PuebloBO puebloBO) {
        this.puebloBO = puebloBO;
    }

    /**
     * Metodo accesor de scada.
     * 
     * @return Retorna el scada.
     */
    public Boolean getScada() {
        return scada;
    }

    /**
     * Metodo modificador de scada.
     * 
     * @param scada
     *            El scada a modificar.
     */
    public void setScada(Boolean scada) {
        this.scada = scada;
    }


    /**
     * Metodo accesor de circuito.
     * @return Retorna el circuito.
     */
    public Integer getCircuito() {
        return this.circuito;
    }

    /**
     * Metodo modificador de circuito.
     * @param circuito El circuito a modificar.
     */
    public void setCircuito(Integer circuito) {
        this.circuito = circuito;
    }

    /**
     * Metodo accesor de seccion.
     * @return Retorna el seccion.
     */
    public Long getSeccion() {
        return this.seccion;
    }

    /**
     * Metodo modificador de seccion.
     * @param seccion El seccion a modificar.
     */
    public void setSeccion(Long seccion) {
        this.seccion = seccion;
    }

    /**
     * Metodo accesor de subestacion.
     * @return Retorna el subestacion.
     */
    public Integer getSubestacion() {
        return this.subestacion;
    }

    /**
     * Metodo modificador de subestacion.
     * @param subestacion El subestacion a modificar.
     */
    public void setSubestacion(Integer subestacion) {
        this.subestacion = subestacion;
    }

    /**
     * Metodo accesor de seccionBO.
     * 
     * @return Retorna el seccionBO.
     */
    public SeccionBO getSeccionBO() {
        return seccionBO;
    }

    /**
     * Metodo modificador de seccionBO.
     * 
     * @param seccionBO
     *            El seccionBO a modificar.
     */
    public void setSeccionBO(SeccionBO seccionBO) {
        this.seccionBO = seccionBO;
    }


    /**
     * Metodo accesor de subEstacionBO.
     * 
     * @return Retorna el subEstacionBO.
     */
    public SubEstacionBO getSubEstacionBO() {
        return subEstacionBO;
    }

    /**
     * Metodo modificador de subEstacionBO.
     * 
     * @param subEstacionBO
     *            El subEstacionBO a modificar.
     */
    public void setSubEstacionBO(SubEstacionBO subEstacionBO) {
        this.subEstacionBO = subEstacionBO;
    }

    /**
     * Metodo accesor de telefonoCliente.
     * 
     * @return Retorna el telefonoCliente.
     */
    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    /**
     * Metodo modificador de telefonoCliente.
     * 
     * @param telefonoCliente
     *            El telefonoCliente a modificar.
     */
    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    /**
     * Metodo accesor de tipoCable.
     * 
     * @return Retorna el tipoCable.
     */
    public Integer getTipoCable() {
        return tipoCable;
    }

    /**
     * Metodo modificador de tipoCable.
     * 
     * @param tipoCable
     *            El tipoCable a modificar.
     */
    public void setTipoCable(Integer tipoCable) {
        this.tipoCable = tipoCable;
    }

    /**
     * Metodo accesor de tipoVoltaje.
     * 
     * @return Retorna el tipoVoltaje.
     */
    public TipoVoltaje getTipoVoltaje() {
        return tipoVoltaje;
    }

    /**
     * Metodo modificador de tipoVoltaje.
     * 
     * @param tipoVoltaje
     *            El tipoVoltaje a modificar.
     */
    public void setTipoVoltaje(TipoVoltaje tipoVoltaje) {
        this.tipoVoltaje = tipoVoltaje;
    }

    /**
     * Metodo accesor de trifasica.
     * 
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
     * Metodo accesor de vehiculo.
     * @return Retorna el vehiculo.
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * Metodo modificador de vehiculo.
     * @param vehiculo
     *            El vehiculo a modificar.
     */
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    /**
     * Metodo accesor de vehiculoBO.
     * 
     * @return Retorna el vehiculoBO.
     */
    public VehiculoBO getVehiculoBO() {
        return vehiculoBO;
    }

    /**
     * Metodo modificador de vehiculoBO.
     * 
     * @param vehiculoBO
     *            El vehiculoBO a modificar.
     */
    public void setVehiculoBO(VehiculoBO vehiculoBO) {
        this.vehiculoBO = vehiculoBO;
    }

    /**
     * Metodo accesor de cuadrillaBO.
     * 
     * @return Retorna el cuadrillaBO.
     */
    public CuadrillaBO getCuadrillaBO() {
        return cuadrillaBO;
    }

    /**
     * Metodo modificador de cuadrillaBO.
     * 
     * @param cuadrillaBO
     *            El cuadrillaBO a modificar.
     */
    public void setCuadrillaBO(CuadrillaBO cuadrillaBO) {
        this.cuadrillaBO = cuadrillaBO;
    }

    /**
     * Metodo accesor de reporteBO.
     * 
     * @return Retorna el reporteBO.
     */
    public ReporteBO getReporteBO() {
        return reporteBO;
    }

    /**
     * Metodo modificador de reporteBO.
     * 
     * @param reporteBO
     *            El reporteBO a modificar.
     */
    public void setReporteBO(ReporteBO reporteBO) {
        this.reporteBO = reporteBO;
    }

    /**
     * Metodo accesor de codigoCalleEquipo.
     * 
     * @return Retorna el codigoCalleEquipo.
     */
    public Integer getCodigoCalleEquipo() {
        return codigoCalleEquipo;
    }

    /**
     * Metodo modificador de codigoCalleEquipo.
     * 
     * @param codigoCalleEquipo
     *            El codigoCalleEquipo a modificar.
     */
    public void setCodigoCalleEquipo(Integer codigoCalleEquipo) {
        this.codigoCalleEquipo = codigoCalleEquipo;
    }

    /**
     * Metodo accesor de codigoCalleFalla.
     * 
     * @return Retorna el codigoCalleFalla.
     */
    public Integer getCodigoCalleFalla() {
        return codigoCalleFalla;
    }

    /**
     * Metodo modificador de codigoCalleFalla.
     * 
     * @param codigoCalleFalla
     *            El codigoCalleFalla a modificar.
     */
    public void setCodigoCalleFalla(Integer codigoCalleFalla) {
        this.codigoCalleFalla = codigoCalleFalla;
    }

    /**
     * Metodo accesor de posteEquipo.
     * 
     * @return Retorna el posteEquipo.
     */
    public Integer getPosteEquipo() {
        return posteEquipo;
    }

    /**
     * Metodo modificador de posteEquipo.
     * 
     * @param posteEquipo
     *            El posteEquipo a modificar.
     */
    public void setPosteEquipo(Integer posteEquipo) {
        this.posteEquipo = posteEquipo;
    }

    /**
     * Metodo accesor de posteFalla.
     * 
     * @return Retorna el posteFalla.
     */
    public Integer getPosteFalla() {
        return posteFalla;
    }

    /**
     * Metodo modificador de posteFalla.
     * 
     * @param posteFalla
     *            El posteFalla a modificar.
     */
    public void setPosteFalla(Integer posteFalla) {
        this.posteFalla = posteFalla;
    }

    /**
     * Metodo accesor de puebloEquipo.
     * 
     * @return Retorna el puebloEquipo.
     */
    public Pueblo getPuebloEquipo() {
        return puebloEquipo;
    }

    /**
     * Metodo modificador de puebloEquipo.
     * 
     * @param puebloEquipo
     *            El puebloEquipo a modificar.
     */
    public void setPuebloEquipo(Pueblo puebloEquipo) {
        this.puebloEquipo = puebloEquipo;
    }

    /**
     * Metodo accesor de secuenciaEquipo.
     * 
     * @return Retorna el secuenciaEquipo.
     */
    public Integer getSecuenciaEquipo() {
        return secuenciaEquipo;
    }

    /**
     * Metodo modificador de secuenciaEquipo.
     * 
     * @param secuenciaEquipo
     *            El secuenciaEquipo a modificar.
     */
    public void setSecuenciaEquipo(Integer secuenciaEquipo) {
        this.secuenciaEquipo = secuenciaEquipo;
    }

    /**
     * Metodo accesor de secuenciaFalla.
     * 
     * @return Retorna el secuenciaFalla.
     */
    public Integer getSecuenciaFalla() {
        return secuenciaFalla;
    }

    /**
     * Metodo modificador de secuenciaFalla.
     * 
     * @param secuenciaFalla
     *            El secuenciaFalla a modificar.
     */
    public void setSecuenciaFalla(Integer secuenciaFalla) {
        this.secuenciaFalla = secuenciaFalla;
    }

    /**
     * Metodo accesor de parametroBO.
     * @return Retorna el parametroBO.
     */
    public ParametroBO getParametroBO() {
        return parametroBO;
    }

    /**
     * Metodo modificador de parametroBO.
     * 
     * @param parametroBO
     *            El parametroBO a modificar.
     */
    public void setParametroBO(ParametroBO parametroBO) {
        this.parametroBO = parametroBO;
    }

    /**
     * Cancela el proceso de interrupcion
     * @return success
     */
    public String cancelar() {
        this.accionGuardar = "";
        this.estructuraSelected = "";
        this.accionProvolma = "";
        return "success";
    }
    /**
	 * Listener del campo pueblo de la localizacion de la falla.
	 */
	public String listenerPuebloFalla() {
		puebloEquipo.setNumero(pueblo.getNumero());
		return "listener";
	}
	/**
	 * Listener del campo codigoCalle de la localizacion de la falla.
	 */
	public void listenerCodigoCalleFalla() {
		codigoCalleEquipo = codigoCalleFalla;
	}
	/**
	 * Listener del campo poste de la localizacion de la falla.
	 */
	public void listenerPosteFalla() {
		posteEquipo = posteFalla;
	}
	/**
	 * Listener del campo secuencia de la localizacion de la falla.
	 */
	public void listenerSecuenciaFalla() {
		secuenciaEquipo = secuenciaFalla;
	}
	/**
	 * Listener del campo material o equipo.
	 */
	public void listenerMaterialEquipo() {
		if (codigoMaterial != null) {
			Material temp = materialBO.buscar(codigoMaterial);
			if (temp != null && temp.getEstado().equals(Material.ESTADO_INACTIVO)) {
				codigoMaterial = null;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error!", "El Material o equipo esta desactivado."));
			}
		}
	}
	/**
	 * Listener del campo causa principal.
	 */
	public void listenerCausaPrincipal() {
		codigoCausa = validarEstadoCausa(codigoCausa);
	}
	/**
	 * Listener del campo causa asociada.
	 */
	public void listenerCausaAsociada() {
		codigoCausa2 = validarEstadoCausa(codigoCausa2);
	}
	/**
	 * Valida el estado de la causa.
	 * @param codCausa
	 */
	private Integer validarEstadoCausa(Integer codCausa) {
		if (codCausa != null) {
			Causa temp = causaBO.buscar(codCausa);
			if (temp != null && temp.getEstado().equals(Causa.ESTADO_INACTIVO)) {
				codCausa = null;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error!", "La causa esta desactivada."));
			}
		}
		return codCausa;
	}
    public String listenerAgencias(){
    	return "success";
    }

    /**
     * Metodo para listenerOficina
     * @param v
     * @return
     */
    public String listenerOficina(){
        
    	this.subestacion = null;
	 	this.circuito= null;
	 	this.clientesAfectados=null;
	 	this.circuito= null;
	 	
	 	
	 	this.region = this.regionBO.buscarPorOficina(this.codigoOficina);
	 	 
		if(region != null){
		    this.nombreRegion = region.getNombreRegion();
		    this.subRegion = this.subRegionBO.buscarPorOficina(this.codigoOficina);
		    if(subRegion != null){
		        this.nombreSubregion = this.subRegion.getNombreSubRegion();
		    }else{
		        this.nombreSubregion = "";
		    }
		}else{
		    this.nombreRegion = "";
		}
		return "listener";
       
    
    }

    /**
     * Metodo accesor de codigoOficina.
     * 
     * @return Retorna el codigoOficina.
     */
    public Integer getCodigoOficina() {
        return codigoOficina;
    }

    /**
     * Metodo modificador de codigoOficina.
     * 
     * @param codigoOficina
     *            El codigoOficina a modificar.
     */
    public void setCodigoOficina(Integer codigoOficina) {
        this.codigoOficina = codigoOficina;
    }
    
    /**
     * Metodo accesor de accionProvolma.
     * @return Retorna el accionProvolma.
     */
    public String getAccionProvolma() {
        return accionProvolma;
    }
    /**
     * Metodo modificador de accionProvolma.
     * @param accionProvolma El accionProvolma a modificar.
     */
    public void setAccionProvolma(String accionProvolma) {
        this.accionProvolma = accionProvolma;
    }
    
    
    /**
     * Metodo accesor de accionGuardar.
     * @return Retorna el accionGuardar.
     */
    public String getAccionGuardar() {
        return accionGuardar;
    }
    /**
     * Metodo modificador de accionGuardar.
     * @param accionGuardar El accionGuardar a modificar.
     */
    public void setAccionGuardar(String accionGuardar) {
        this.accionGuardar = accionGuardar;
    }
    
    /**
     * Metodo accesor de nombreAnimal.
     * @return Retorna el nombreAnimal.
     */
    public String getNombreAnimal() {
        return nombreAnimal;
    }
    /**
     * Metodo modificador de nombreAnimal.
     * @param nombreAnimal El nombreAnimal a modificar.
     */    
    public void setNombreAnimal(String nombreAnimal) {
        this.nombreAnimal = nombreAnimal;
    }
    /**
     * Metodo accesor de nombreRegion.
     * @return Retorna el nombreRegion.
     */
    public String getNombreRegion() {
        return this.nombreRegion;
    }
    /**
     * Metodo modificador de nombreRegion.
     * @param nombreRegion El nombreRegion a modificar.
     */
    public void setNombreRegion(String nombreRegion) {
        this.nombreRegion = nombreRegion;
    }
    /**
     * Metodo accesor de region.
     * @return Retorna el region.
     */
    public Region getRegion() {
        return this.region;
    }
    /**
     * Metodo modificador de region.
     * @param region El region a modificar.
     */
    public void setRegion(Region region) {
        this.region = region;
    }
    /**
     * Metodo accesor de regionBO.
     * @return Retorna el regionBO.
     */
    public RegionBO getRegionBO() {
        return this.regionBO;
    }
    /**
     * Metodo modificador de regionBO.
     * @param regionBO El regionBO a modificar.
     */
    public void setRegionBO(RegionBO regionBO) {
        this.regionBO = regionBO;
    }
    /**
     * Metodo accesor de nombreSubregion.
     * @return Retorna el nombreSubregion.
     */
    public String getNombreSubregion() {
        return nombreSubregion;
    }
    /**
     * Metodo modificador de nombreSubregion.
     * @param nombreSubregion El nombreSubregion a modificar.
     */
    public void setNombreSubregion(String nombreSubregion) {
        this.nombreSubregion = nombreSubregion;
    }
    /**
     * Metodo accesor de subRegion.
     * @return Retorna el subRegion.
     */
    public SubRegion getSubRegion() {
        return subRegion;
    }
    /**
     * Metodo modificador de subRegion.
     * @param subRegion El subRegion a modificar.
     */
    public void setSubRegion(SubRegion subRegion) {
        this.subRegion = subRegion;
    }
    /**
     * Metodo accesor de subRegionBO.
     * @return Retorna el subRegionBO.
     */
    public SubRegionBO getSubRegionBO() {
        return subRegionBO;
    }
    /**
     * Metodo modificador de subRegionBO.
     * @param subRegionBO El subRegionBO a modificar.
     */
    public void setSubRegionBO(SubRegionBO subRegionBO) {
        this.subRegionBO = subRegionBO;
    }
    /**
     * Metodo accesor de titulo.
     * @return Retorna el titulo.
     */
    public String getTitulo() {
        return this.titulo;
    }
    /**
     * Metodo modificador de titulo.
     * @param titulo El titulo a modificar.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    
    public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	/**
     * Metodo accesor de operadorScada.
     * @return Retorna el operadorScada.
     */
    public String getOperadorScada() {
        return this.operadorScada;
    }
    /**
     * Metodo modificador de operadorScada.
     * @param operadorScada El operadorScada a modificar.
     */
    public void setOperadorScada(String operadorScada) {
        this.operadorScada = operadorScada;
    }
    /**
     * Metodo accesor de irCuadrillaVehiculo.
     * @return Retorna el irCuadrillaVehiculo.
     */
    public Boolean getIrCuadrillaVehiculo() {
        return this.irCuadrillaVehiculo;
    }
    /**
     * Metodo modificador de irCuadrillaVehiculo.
     * @param irCuadrillaVehiculo El irCuadrillaVehiculo a modificar.
     */
    public void setIrCuadrillaVehiculo(Boolean irCuadrillaVehiculo) {
        this.irCuadrillaVehiculo = irCuadrillaVehiculo;
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
    /**
     * Metodo accesor de tituloFinal.
     * @return Retorna el tituloFinal.
     */
    public String getTituloFinal() {
        return this.tituloFinal;
    }
    /**
     * Metodo modificador de tituloFinal.
     * @param tituloFinal El tituloFinal a modificar.
     */
    public void setTituloFinal(String tituloFinal) {
        this.tituloFinal = tituloFinal;
    }

	/**
	 * @return the estructuraSelected
	 */
	public String getEstructuraSelected() {
		return estructuraSelected;
	}

	/**
	 * @param estructuraSelected the estructuraSelected to set
	 */
	public void setEstructuraSelected(String estructuraSelected) {
		this.estructuraSelected = estructuraSelected;
	}

    /**
     * Metodo modificador de materialBO.
     * @param materialBO El materialBO a modificar.
     */
    public void setMaterialBO(MaterialBO materialBO) {
        this.materialBO = materialBO;
    }

    /**
     * Metodo modificador de proteccionBO.
     * @param proteccionBO El proteccionBO a modificar.
     */
    public void setProteccionBO(ProteccionBO proteccionBO) {
        this.proteccionBO = proteccionBO;
    }

	public Boolean getIndTension()
	{
		Boolean resultado = false;
		try
		{
			if(this.reporte!=null)
			{
				if(this.reporte.getIndTension() == null || this.reporte.getIndTension().equals(Integer.valueOf(1)))
				{
					//return false;
					resultado = false;
				}else
				{
					//return true;
					resultado = true;
				}
			}else
			{
				if(this.regresaDePag)
				{
					return this.indTension;
				}else
				{
					//return false;
					resultado = false;
				}
			}
			if(this.isModificar())
			{
				if(this.reporte!=null)
				{
					if(this.reporte.getIndTension() == null || this.reporte.getIndTension().equals(Integer.valueOf(1)))
					{
						//return false;
						resultado = false;
					}else
					{
						//return true;
						resultado = true;
					}
				}else
				{
					if(this.regresaDePag)
					{
						return this.indTension;
					}else
					{
						//return false;
						resultado = false;
					}
				}
			}
			this.indTension = resultado;
			return this.indTension;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public void setIndTension(Boolean indTension) {
		this.indTension = indTension;
	}

	public Boolean getRegresaDePag() {
		return regresaDePag;
	}

	public void setRegresaDePag(Boolean regresaDePag) {
		this.regresaDePag = regresaDePag;
	}

	public UsuarioOficinaBO getUsuarioOficinaBO() {
		return usuarioOficinaBO;
	}

	public void setUsuarioOficinaBO(UsuarioOficinaBO usuarioOficinaBO) {
		this.usuarioOficinaBO = usuarioOficinaBO;
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


	public String getNuevoFoco() {
		return nuevoFoco;
	}


	public void setNuevoFoco(String nuevoFoco) {
		this.nuevoFoco = nuevoFoco;
	}
}
