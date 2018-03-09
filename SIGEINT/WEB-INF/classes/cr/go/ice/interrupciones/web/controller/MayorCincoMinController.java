package cr.go.ice.interrupciones.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.jibx.runtime.JiBXException;
import org.primefaces.component.datatable.DataTable;

import com.vvs.jsf.AbstractFacesController;
import com.vvs.utilidades.Fechas;

import cr.go.ice.cia.dominio.UsuarioCia;
import cr.go.ice.interrupciones.BO.AnimalBO;
import cr.go.ice.interrupciones.BO.CausaBO;
import cr.go.ice.interrupciones.BO.CircuitoBO;
import cr.go.ice.interrupciones.BO.ConsecutivoClorBO;
import cr.go.ice.interrupciones.BO.CuadrillaBO;
import cr.go.ice.interrupciones.BO.EmpleadoBO;
import cr.go.ice.interrupciones.BO.EquipoEspecialBO;
import cr.go.ice.interrupciones.BO.FusibleBO;
import cr.go.ice.interrupciones.BO.InterrupcionBO;
import cr.go.ice.interrupciones.BO.InterrupcionGemelaBO;
import cr.go.ice.interrupciones.BO.MaterialBO;
import cr.go.ice.interrupciones.BO.NoPropiaSeccionamientoBO;
import cr.go.ice.interrupciones.BO.OficinaBO;
import cr.go.ice.interrupciones.BO.ParametroBO;
import cr.go.ice.interrupciones.BO.PosteInstaladoRetiradoBO;
import cr.go.ice.interrupciones.BO.ProteccionBO;
import cr.go.ice.interrupciones.BO.ProvolmaBO;
import cr.go.ice.interrupciones.BO.PuebloBO;
import cr.go.ice.interrupciones.BO.RegionBO;
import cr.go.ice.interrupciones.BO.SeccionBO;
import cr.go.ice.interrupciones.BO.SubEstacionBO;
import cr.go.ice.interrupciones.BO.SubRegionBO;
import cr.go.ice.interrupciones.BO.TrafoBO;
import cr.go.ice.interrupciones.BO.UsuarioOficinaBO;
import cr.go.ice.interrupciones.BO.VehiculoBO;
import cr.go.ice.interrupciones.domain.Animal;
import cr.go.ice.interrupciones.domain.Causa;
import cr.go.ice.interrupciones.domain.Circuito;
import cr.go.ice.interrupciones.domain.ConsecutivoClor;
import cr.go.ice.interrupciones.domain.ConsecutivoClorID;
import cr.go.ice.interrupciones.domain.Cuadrilla;
import cr.go.ice.interrupciones.domain.CuadrillaID;
import cr.go.ice.interrupciones.domain.Dano;
import cr.go.ice.interrupciones.domain.Empleado;
import cr.go.ice.interrupciones.domain.EquipoEspecial;
import cr.go.ice.interrupciones.domain.EquipoEspecialID;
import cr.go.ice.interrupciones.domain.Fusible;
import cr.go.ice.interrupciones.domain.FusibleID;
import cr.go.ice.interrupciones.domain.Interrupcion;
import cr.go.ice.interrupciones.domain.InterrupcionGemela;
import cr.go.ice.interrupciones.domain.InterrupcionGemelaID;
import cr.go.ice.interrupciones.domain.InterrupcionID;
import cr.go.ice.interrupciones.domain.Material;
import cr.go.ice.interrupciones.domain.NoPropiaSeccionamiento;
import cr.go.ice.interrupciones.domain.NoPropiaSeccionamientoID;
import cr.go.ice.interrupciones.domain.Oficina;
import cr.go.ice.interrupciones.domain.Parametro;
import cr.go.ice.interrupciones.domain.PosteInstaladoRetirado;
import cr.go.ice.interrupciones.domain.PosteInstaladoRetiradoID;
import cr.go.ice.interrupciones.domain.Proteccion;
import cr.go.ice.interrupciones.domain.Provolma;
import cr.go.ice.interrupciones.domain.ProvolmaID;
import cr.go.ice.interrupciones.domain.Region;
import cr.go.ice.interrupciones.domain.Seccion;
import cr.go.ice.interrupciones.domain.SeccionID;
import cr.go.ice.interrupciones.domain.SubEstacion;
import cr.go.ice.interrupciones.domain.SubRegion;
import cr.go.ice.interrupciones.domain.SubRegionID;
import cr.go.ice.interrupciones.domain.TipoVoltaje;
import cr.go.ice.interrupciones.domain.TipoVoltajeID;
import cr.go.ice.interrupciones.domain.Trafo;
import cr.go.ice.interrupciones.domain.TrafoID;
import cr.go.ice.interrupciones.domain.UsuarioOficina;
import cr.go.ice.interrupciones.domain.Vehiculo;
import cr.go.ice.interrupciones.domain.VehiculoID;
import cr.go.ice.interrupciones.servicios.ConsultaPueblos;
import cr.go.ice.interrupciones.servicios.RespuestaConsultaPueblos;
import cr.go.ice.interrupciones.servicios.ServiciosAcemas;
import cr.go.ice.interrupciones.utils.Usuario;
import cr.go.ice.interrupciones.utils.UtilidadesI;
import cr.go.ice.obras.BO.AgenciaBO;
import cr.go.ice.obras.domain.Agencia;
import cr.go.ice.sace.sacecon.domain.Pueblo;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.MayorCincoMinController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>MayorCincoMinController.java</code> Establese la relacion entre capa de presentación y la lógica del negocio de las interrupciones mayores a cinco minutos.</p>
 * <p>Fecha creación: 01/04/2007</p>
 * <p>Ultima actualización: 01/04/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class MayorCincoMinController extends AbstractFacesController{
		
    private PosteInstaladoRetiradoBO posteInstaladoRetiradoBO;
    private EquipoEspecialBO equipoEspecialBO;    
    private VehiculoBO vehiculoBO;
    private MaterialBO materialBO;
    private ProteccionBO proteccionBO;
	private ParametroBO parametroBO;
    private OficinaBO oficinaBO;
    private AnimalBO animalBO;
    private PuebloBO puebloBO;
    private CausaBO causaBO;
    private ProvolmaBO provolmaBO;
    private FusibleBO fusibleBO;    
    private InterrupcionGemelaBO interrupcionGemelaBO;    
    private ConsecutivoClorBO consecutivoClorBO;
    private CircuitoBO circuitoBO;  
    private InterrupcionBO interrupcionBO;
    private SubEstacionBO subEstacionBO;
    private TrafoBO trafoBO;
    private SeccionBO seccionBO;
    private EmpleadoBO empleadoBO;  
    private AgenciaBO agenciaBO;
    private CuadrillaBO cuadrillaBO;
    private NoPropiaSeccionamientoBO noPropiaSeccionamientoBO;
    private RegionBO regionBO;
    private SubRegionBO subRegionBO;  
    private UsuarioOficinaBO usuarioOficinaBO;
    
	private Integer numeroMovimientoGemela;
	private Date fechaInicioGemela;
	private Date fechaFinalGemela;
	private Long abonadosAfectados;
	private DataTable dataTableGemelas;
	private List interrupcionesGemelas; 
	private Long abonadosAfectadosGemela;
	private InterrupcionID interrupcionID;
	private Boolean faseR;
	private Boolean faseS;
	private Boolean faseT;
	private Boolean indTension;
	
	private Integer codigoProteccion;
	private Integer codigoIntervenido;
	private Integer codigoDano;
	private Integer codigoMaterial;
	private Integer codigoCausa;
	
	private DataTable dataTablePostes;
	private Integer tipoPoste;
	private Integer longitudPoste;
	private Boolean posteInstalado;
	private Boolean posteRetirado;
	private List postes;
	private Integer numeroMovimientoPoste;
	
	private Integer cantidadCable;
	private Integer tipoCable;
	
	private DataTable dataTableEquipos;
	private String serieEquipo;
	private Long numeroEquipo;
	private Boolean equipoInstalado;
	private Boolean equipoRetirado;
	private List equipos;


	private Integer codigoAnimal;
	private Integer numeroMovimientoFusible;
	private Integer numeroMovimientoTrafo;	
	private List trafos;
	private List fusibles;
	private List empleadosCuadrilla;
	private Integer codigoPueblo;
	private Boolean trifasica;
	private Boolean bitacora;
	private Boolean indicadorSubestacion;
	private Boolean regresaDePag;
	private Integer codigoCausa2;
	private boolean primeraVezConsecutivo;
	private List abonadosGemelas;
	private long acumuladorGemelas;	
	private String estructuraSelected;
	private String consecutivoInterrupcion;

		
	private ConsecutivoClor consecutivoClor;
	private DataTable dataTableEmpleados;
	private DataTable dataTableCuadrilla;
	private DataTable dataTableFusibles;
	private Fusible fusible;
	private Integer saleCircuito;
	private DataTable dataTableTrafos;	

	private Empleado empleadoCuadrilla;
	private TipoVoltaje tipoVoltaje;	
	private Interrupcion interrupcion;
	private Trafo trafo;
	private Empleado empleado;
	private Cuadrilla cuadrilla;
	private Vehiculo vehiculo;
	private SubRegion subRegion;
	private Pueblo pueblo;
	private Pueblo puebloEquipo;	


	private Integer subEstacion;
	private Integer codigoAgencia;
	private String operador;
	private Long cedula;
	private String oficina;		
	private String cliente;
	private String telefonoCliente;
	private Long medidor;
	private String lugarInterrupcion;
	private Date fechaInicioInterrupcion;
	private Date fechaFinalInterrupcion;
	private Double duracionInterrupcion;	
	private Date fechaRecepcionAviso;
	private Long clientesAfectados;
	private String observaciones;
	private Boolean scada;
	private Boolean manual;		
	private Long numeroTrafo;
	private String serieTrafo;
	private Boolean trafoRetirado;
	private Boolean trafoInstalado;	
	private Boolean empleadoResponsable;
	private Boolean empleadoMiembro;	
	private Integer operacion;
	private CuadrillaID cuadrillaID;
	private FusibleID fusibleID;
	private TrafoID trafoID;
	private PosteInstaladoRetiradoID posteInstaladoRetiradoID;
	private EquipoEspecialID equipoEspecialID;
	private InterrupcionGemelaID interrupcionGemelaID;    
	private Integer codigoOficina;
	private Integer codigoCalle;
	private Integer poste;
	private Integer secuencia;
	private Integer codigoCalleEquipo;
	private Integer posteEquipo;
	private Integer secuenciaEquipo;
	private Boolean saleTotal;
	private Boolean saleParcial;
	private List seccionamientos;
	private List listaFusibles;
	private Boolean gemelas;	
	private VehiculoID vehiculoID;
	private boolean indAgencia;
	private String accionProvolma;	
	private String nuevoFoco;
	private String accionGuardar;
	private String nombreAnimal;
	private Double horasAbonado;
	private Integer codigoPoste;
	private Integer codigoEquipo;
	private boolean botonFusible;
	private boolean botonTransformador;
	private Long saldoGemelas;
	private String nombreRegion;
	private Region region;
	private String nombreSubregion;
	
	private String titulo;
	private String tituloFinal;
	private String telefono;
	
	private Boolean irCuadrillaVehiculo;
	private String operadorScada;
	private VehiculoID vehiculoIDViejo;
	private Boolean comboOficina;    
    private Integer subestacion;
    private Integer circuito;
    private Long seccion;    
    private boolean detalleFusibleTransformador;
    
    private ServiciosAcemas serviciosAcemas;
    List<UsuarioOficina> listaPivote;
	
	/**
	 * Constructor
	 */
	public MayorCincoMinController(){
		this.resetController();
        		
	}
	
	
	 @SuppressWarnings("deprecation")
	    public String getInit() {
		 FacesContext context = FacesContext.getCurrentInstance();
		 Object ingresar = context.getExternalContext().getRequestParameterMap().get("init");
	        if(ingresar != null){
	            this.interrupcion = null;
	            this.titulo = "Ingreso de Interrupción mayor a 5 minutos ";
	            
	            
	            String nombreUsuarioSession = (String) context.getApplication().createValueBinding("#{autorizacionController.nombreUsuario}").getValue(this.getFacesContext());
	            String[] valores = nombreUsuarioSession.split("-");
	            this.cedula = new Long(valores[0].trim());
	            
	            
	            this.empleado = this.empleadoBO.buscar(this.cedula);            
	            
	            this.consecutivoInterrupcion = null;
	            this.operador = this.empleado.getNombreCompleto();
	            listaPivote = this.usuarioOficinaBO.buscarCedula(this.cedula);
	           
	        	if(!listaPivote.isEmpty())
	        	{
	        		this.oficina = listaPivote.get(0).getId().getOficina().getNombreOficina();
	        		this.codigoOficina = listaPivote.get(0).getId().getOficina().getCodigoOficina();
	        	}else
	        	{
	        		this.oficina = "";
	        		this.codigoOficina = new Integer(0);
	        	}
	           
	            
	            this.comboOficina = Boolean.valueOf(false);
	            
	            cuadrillaID = null;
	            fusibleID = null;
	            trafoID = null;
	            posteInstaladoRetiradoID = null;
	            equipoEspecialID = null;
	            interrupcionGemelaID = null;                                    
	            primeraVezConsecutivo = false;
	                
	            
	            GregorianCalendar fecha = new GregorianCalendar();	
	            
	    		this.fechaInicioInterrupcion = fecha.getTime();
	    		this.fechaFinalInterrupcion = fechaInicioInterrupcion;
	    		this.fechaRecepcionAviso = fechaInicioInterrupcion;
	            this.postes = new ArrayList();
	            this.equipos = new ArrayList();
	            this.empleadosCuadrilla = new ArrayList();
	            this.trafos = new ArrayList();
	            this.fusibles = new ArrayList();
	            this.seccionamientos = new ArrayList();
	            this.interrupcionesGemelas = new ArrayList();
	            this.abonadosGemelas = new ArrayList();            
	            this.acumuladorGemelas = 0;         
	            
	            this.numeroMovimientoFusible = new Integer(1);
	            this.numeroMovimientoPoste = new Integer(1);
	            this.numeroMovimientoTrafo = new Integer(1);
	            this.numeroMovimientoGemela = new Integer(1);
	            this.codigoMaterial = null;
	            this.indTension = Boolean.FALSE;
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
	 
	 
	protected void resetController(){
		
		this.regresaDePag = false;
	    this.operadorScada = null;
		cuadrillaID = null;
		fusibleID = null; 
		trafoID = null;
		posteInstaladoRetiradoID = null;
		equipoEspecialID = null;
		interrupcionGemelaID = null;
		this.operacion = new Integer(0);
		scada = Boolean.valueOf(false);
		manual = Boolean.valueOf(true);
		subEstacion = new Integer(0);	
		this.trafo = new Trafo();
		trafoInstalado = Boolean.valueOf(false);
		trafoRetirado = Boolean.valueOf(false);
		this.numeroTrafo = new Long(0);
		this.serieTrafo = "";
		this.cedula = new Long(0);
		this.empleadoCuadrilla = new Empleado();
		this.empleadoCuadrilla.setCedula(new Long(0));
		this.empleadoCuadrilla.setNombreEmpleado(" ");
		this.cuadrilla = new Cuadrilla();
		this.vehiculo = new Vehiculo();
		this.vehiculo.setVehiculoID(new VehiculoID());
		this.fusible = new Fusible();
		this.fusible.setFusibleID(new FusibleID());				
		
		this.interrupcionesGemelas = new ArrayList();
		
		GregorianCalendar fecha = new GregorianCalendar();		
		this.fechaInicioInterrupcion = fecha.getTime();
		this.fechaFinalInterrupcion = fechaInicioInterrupcion;
		this.fechaRecepcionAviso = fechaInicioInterrupcion;
		
		
		this.postes = new ArrayList();
		this.equipos = new ArrayList();
		this.trafos = new ArrayList();
		this.fusibles = new ArrayList();
		this.listaFusibles = new ArrayList();
		this.empleadosCuadrilla = new ArrayList();
		
		this.numeroMovimientoFusible = new Integer(1);
		this.numeroMovimientoPoste = new Integer(1);
		this.numeroMovimientoTrafo = new Integer(1);
		this.numeroMovimientoGemela = new Integer(1);
		
		this.pueblo = new Pueblo();
		this.puebloEquipo = new Pueblo();
		this.pueblo.setNombre("");
		this.pueblo.setNumero(new Integer(0));
		this.puebloEquipo.setNombre("");
		this.puebloEquipo.setNumero(new Integer(0));
		this.acumuladorGemelas = 0;
		this.abonadosGemelas = new ArrayList();           
		this.seccionamientos = null;
		this.gemelas = Boolean.valueOf(false);
		this.clientesAfectados = new Long(0);
		indAgencia = false;
		accionProvolma = "";
		accionGuardar = "";
        this.estructuraSelected = "";
        
        this.subestacion = new Integer(0);
        this.circuito = new Integer(0);
        this.seccion = new Long(0);
        
        this.codigoProteccion = new Integer(0);
        this.codigoIntervenido = new Integer(0);
        this.codigoMaterial = new Integer(0);
        this.codigoCausa = new Integer(0);
        this.codigoCausa2 = new Integer(0);
        this.detalleFusibleTransformador = true;
        this.nuevoFoco="form1:voltaje"; 
        this.serviciosAcemas = new ServiciosAcemas();
        
        this.indTension = Boolean.FALSE;
		
	}
	/**
	 * @return Devuelve tipoVoltaje.
	 */
	public TipoVoltaje getTipoVoltaje() {
		if(this.codigoProteccion != null){
			if(this.codigoProteccion.equals(new Integer(103)) || this.codigoProteccion.equals(new Integer(104))){
				this.detalleFusibleTransformador = false;
			}
		}
	
		if(this.codigoMaterial != null ){
			if(this.codigoMaterial.equals(new Integer(232))){
				this.detalleFusibleTransformador = false;
			}else{
				if((this.codigoProteccion != null) && (this.codigoProteccion.equals(new Integer(103)) || this.codigoProteccion.equals(new Integer(104)))){
					this.detalleFusibleTransformador = false;
				}else{
					this.detalleFusibleTransformador = true;
				}
			}
		}
		
		return this.tipoVoltaje;
	}
	
	/**
	 * @param fechaFinalInterrupcion El fechaFinalInterrupcion a establecer.
	 */
	public void setFechaFinalInterrupcion(Date fechaFinalInterrupcion)
	{
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaFinalInterrupcion);
        if(calendar.get(Calendar.YEAR) != 1970)
        {
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
	 * @return Devuelve fechaInicioInterrupcion.
	 */
	public Date getFechaInicioInterrupcion() {
		return fechaInicioInterrupcion;
	}
	/**
	 * @param fechaInicioInterrupcion El fechaInicioInterrupcion a establecer.
	 */
	public void setFechaInicioInterrupcion(Date fechaInicioInterrup) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicioInterrup);
        if(calendar.get(Calendar.YEAR) != 1970){
        	
            this.fechaInicioInterrupcion = fechaInicioInterrup;
            
        }else{
        	
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(this.fechaInicioInterrupcion);
            calendar2.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
            calendar2.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
           this.fechaInicioInterrupcion = calendar2.getTime();
           
        }
		
		
	}
	/**
	 * @return Devuelve fechaRecepcionAviso.
	 */
	public Date getFechaRecepcionAviso() {
		return fechaRecepcionAviso;
	}
	/**
	 * @param fechaRecepcionAviso El fechaRecepcionAviso a establecer.
	 */
	public void setFechaRecepcionAviso(Date fechaRecepcionAviso) {
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaRecepcionAviso);
        if(calendar.get(Calendar.YEAR) != 1970){
            this.fechaRecepcionAviso = fechaRecepcionAviso;
           
        }else{
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(this.fechaRecepcionAviso);
            calendar2.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
            calendar2.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
            this.fechaRecepcionAviso = calendar2.getTime();
         
            
        }
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
		codigoCalleEquipo = codigoCalle;
	}
	/**
	 * Listener del campo poste de la localizacion de la falla.
	 */
	public void listenerPosteFalla() {
		posteEquipo = poste;
	}
	/**
	 * Listener del campo secuencia de la localizacion de la falla.
	 */
	public void listenerSecuenciaFalla() {
		secuenciaEquipo = secuencia;
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
	
	
	 public String listenerCambioOficina()
	    {
			
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
	  * Listener del campo Km inicial, es usado solo para que se setea el valor.
	  */
	public void listenerKmInicial() { }

  /*  public void ingresar(FacesContext context){
        Object ingresar = context.getExternalContext().getRequestParameterMap().get("ingresar");
        if(ingresar != null){
            this.interrupcion = null;
            this.titulo = "Ingreso de Interrupción mayor a 5 minutos ";
            
            UsuarioCia persona = Usuario.getUsuarioObj();
            this.cedula = new Long(persona.getCedula());
            

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
            
            //Comentado la restricción cuando se implementó el CIA
            //boolean userAdmin = Usuario.isUserAdmin();
            //this.comboOficina = (userAdmin) ? Boolean.valueOf(false) : Boolean.valueOf(true); 
            this.comboOficina = Boolean.valueOf(false); //Se cambia el catalogo de oficinas
          
            cuadrillaID = null;
            fusibleID = null;
            trafoID = null;
            posteInstaladoRetiradoID = null;
            equipoEspecialID = null;
            interrupcionGemelaID = null;                                    
            primeraVezConsecutivo = false;
            System.out.println("carga inicial::::::::::::::::::");    
            this.postes = new ArrayList();
            this.equipos = new ArrayList();
            this.empleadosCuadrilla = new ArrayList();
            this.trafos = new ArrayList();
            this.fusibles = new ArrayList();
            this.seccionamientos = new ArrayList();
            this.interrupcionesGemelas = new ArrayList();
            this.abonadosGemelas = new ArrayList();            
            this.acumuladorGemelas = 0;         
            
            this.numeroMovimientoFusible = new Integer(1);
            this.numeroMovimientoPoste = new Integer(1);
            this.numeroMovimientoTrafo = new Integer(1);
            this.numeroMovimientoGemela = new Integer(1);
            this.indTension = Boolean.FALSE;
            this.limpiarDatos();
            this.region = this.regionBO.buscarPorOficina(this.codigoOficina);
            if(region != null){
                this.nombreRegion = region.getNombreRegion();
                System.out.println("NOMBRE REGION: "+this.nombreRegion);
                this.subRegion = this.subRegionBO.buscarPorOficina(this.codigoOficina);
                System.out.println("SUBREGION: "+this.subRegion);
                if(subRegion != null)
                    this.nombreSubregion = this.subRegion.getNombreSubRegion();
                else
                    this.nombreSubregion = "";
            }
            else
                this.nombreRegion = "";         
            
        }
    }*/
    
    public void ingresarAdmin(FacesContext context){
        Object ingresar = context.getExternalContext().getRequestParameterMap().get("ingresar");
        if(ingresar != null){
            this.interrupcion = null;
            this.titulo = "Ingreso de Interrupción mayor a 5 minutos ";
            
          
            
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
            //Comentado la restricción cuando se implementó el CIA
            //boolean userAdmin = Usuario.isUserAdmin();
            //this.comboOficina = (userAdmin) ? Boolean.valueOf(false) : Boolean.valueOf(true); 
            
            this.comboOficina = Boolean.valueOf(false);
            
            cuadrillaID = null;
            fusibleID = null;
            trafoID = null;
            posteInstaladoRetiradoID = null;
            equipoEspecialID = null;
            interrupcionGemelaID = null;                                    
            primeraVezConsecutivo = false;
                
            this.postes = new ArrayList();
            this.equipos = new ArrayList();
            this.empleadosCuadrilla = new ArrayList();
            this.trafos = new ArrayList();
            this.fusibles = new ArrayList();
            this.seccionamientos = new ArrayList();
            this.interrupcionesGemelas = new ArrayList();
            this.abonadosGemelas = new ArrayList();            
            this.acumuladorGemelas = 0;         
            
            this.numeroMovimientoFusible = new Integer(1);
            this.numeroMovimientoPoste = new Integer(1);
            this.numeroMovimientoTrafo = new Integer(1);
            this.numeroMovimientoGemela = new Integer(1);
            this.codigoMaterial = null;
            this.indTension = Boolean.FALSE;
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
    
    public String getModificar() {
    	FacesContext context = FacesContext.getCurrentInstance();
        Object modificar = context.getExternalContext().getSessionMap().remove("modificar");
        if (modificar != null) {
            this.titulo = "Edición de Interrupción mayor a 5 minutos ";

            Integer ano = (Integer) context.getExternalContext().getSessionMap().remove("ano");
            Integer oficina = (Integer) context.getExternalContext().getSessionMap().remove("oficina");
            Long consecutivo = (Long) context.getExternalContext().getSessionMap().remove("consecutivo");
            primeraVezConsecutivo = false;
            this.consecutivoInterrupcion = ano + " - " + oficina + " - " + consecutivo;
            this.codigoOficina = oficina;
            this.limpiarDatos();
            this.cargarDatos();

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
	 * @return Devuelve consecutivoInterrupcion.
	 */
	public String getConsecutivoInterrupcion() {
		if(primeraVezConsecutivo){		
			this.consecutivoClor = this.consecutivoClorBO.getConsecutivoClor(this.codigoOficina);			
			this.consecutivoInterrupcion = this.consecutivoClor.getConsecutivoClorID().getAno() + " - " + this.codigoOficina + " - " + this.consecutivoClor.getConsecutivoInt();
			primeraVezConsecutivo = false;
		}
		return this.consecutivoInterrupcion;				
	}	
	/**
	 * @param operador El operador a establecer.
	 */
	public void setOperador(String operador) {
		this.operador = operador;
	}
	/**
	 * @return Devuelve telefonoCliente.
	 */
	public String getTelefonoCliente() {
		return telefonoCliente;
	}
	/**
	 * @param telefonoCliente El telefonoCliente a establecer.
	 */
	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}
	/**
	 * @return Devuelve operadoPor.
	 */
	public Boolean getScada() {
		return scada;
	}
	/**
	 * @param operadoPor El operadoPor a establecer.
	 */
	/**
	 * @return Devuelve subEstacion.
	 */
	public Integer getSubEstacion() {
		return subEstacion;
	}
	/**
	 * @param subEstacion El subEstacion a establecer.
	 */
	public void setSubEstacion(Integer subEstacion) {
		this.subEstacion = subEstacion;
	}

	/**
	 * @return Devuelve subEstacionBO.
	 */
	public SubEstacionBO getSubEstacionBO() {
		return subEstacionBO;
	}
	/**
	 * @param subEstacionBO El subEstacionBO a establecer.
	 */
	public void setSubEstacionBO(SubEstacionBO subEstacionBO) {
		this.subEstacionBO = subEstacionBO;
	}
	
    /**
     * @param scada El scada a establecer.
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
	 * @return Devuelve seccionBO.
	 */
	public SeccionBO getSeccionBO() {
		return seccionBO;
	}
	/**
	 * @param seccionBO El seccionBO a establecer.
	 */
	public void setSeccionBO(SeccionBO seccionBO) {
		this.seccionBO = seccionBO;
	}         	
	
	/**
	 * @return "success"
	 */
	public String regresar(){
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
		return "success";
	}
    
    /**
     * Método utilizado para que la segunda pagina realice la acción de regresar a la primera
     * @return <code>String</code> successEdicion o successIngreso 
     */
    public String regresarDePaginaDos(){
        this.accionProvolma = "";
        this.nuevoFoco = "form1:voltaje";
        this.regresaDePag = true;
        if(this.isModificar())
            return "successEdicion";
        else
            return "successIngreso";
    }    
    
	/**
	 * @return Devuelve manual.
	 */
	public Boolean getManual() {
		return manual;
	}
	/**
	 * @param manual El manual a establecer.
	 */
	public void setManual(Boolean manual) {
		this.manual = manual;
	}


	/**
	 * @return Devuelve interrupcion.
	 */
	public Interrupcion getInterrupcion() {
		return interrupcion;
	}
	/**
	 * @param interrupcion El interrupcion a establecer.
	 */
	public void setInterrupcion(Interrupcion interrupcion) {
		this.interrupcion = interrupcion;
	}
	/**
	 * @return Devuelve interrupcionBO.
	 */
	public InterrupcionBO getInterrupcionBO() {
		return interrupcionBO;
	}
	/**
	 * @param interrupcionBO El interrupcionBO a establecer.
	 */
	public void setInterrupcionBO(InterrupcionBO interrupcionBO) {
		this.interrupcionBO = interrupcionBO;
	}
	/**
	 * @return Devuelve trafoBO.
	 */
	public TrafoBO getTrafoBO() {
		return trafoBO;
	}
	/**
	 * @param trafoBO El trafoBO a establecer.
	 */
	public void setTrafoBO(TrafoBO trafoBO) {
		this.trafoBO = trafoBO;
	}
	/**
	 * @return Devuelve numeroTrafo.
	 */
	public Long getNumeroTrafo() {
		return numeroTrafo;
	}
	/**
	 * @param numeroTrafo El numeroTrafo a establecer.
	 */
	public void setNumeroTrafo(Long numeroTrafo) {
		this.numeroTrafo = numeroTrafo;
	}
	
	public List getListaFusibles() {
		return listaFusibles;
	}

	public void setListaFusibles(List listaFusibles) {
		this.listaFusibles = listaFusibles;
	}

	/**
	 * @return Devuelve serieTrafo.
	 */
	public String getSerieTrafo() {
		return serieTrafo;
	}
	/**
	 * @param serieTrafo El serieTrafo a establecer.
	 */
	public void setSerieTrafo(String serieTrafo) {
		this.serieTrafo = serieTrafo;
	}
	
	/**
	 * Comment for agregarTrafo
	 * @return "success" al agregar Trafo
	 */
	public String agregarTrafo(){
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
		
		if((this.codigoMaterial != null) && (this.codigoMaterial.intValue() != 232)){
			String mensaje = "Para incluir transformadores, el código de material debe ser 232";
	        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";		        
			return "error";
		}
		if(this.numeroTrafo == null || this.numeroTrafo.longValue() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de transformador es requerido."));
			return "error";
		}		
		if((this.trafoInstalado == null || this.trafoInstalado.booleanValue() == false) && (this.trafoRetirado == null || this.trafoRetirado.booleanValue() == false)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccione un tipo de movimiento."));
			return "error";
		}	
		if(trafoDuplicado() == true){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Movimiento de transformador duplicado."));
			return "error";
		}		
		
		Trafo miTrafo = new Trafo();
		TrafoID miTrafoID = new TrafoID();
		
		miTrafoID.setAa(null);
		miTrafoID.setCodigoOficina(null);
		miTrafoID.setNumeroInterrupcion(null);
		miTrafoID.setNumeroMovimiento(this.numeroMovimientoTrafo);			
		this.numeroMovimientoTrafo = new Integer(this.numeroMovimientoTrafo.intValue() + 1);
		miTrafo.setTrafoID(miTrafoID);
		miTrafo.setNumeroSerie(this.numeroTrafo);
		miTrafo.setSerie(this.serieTrafo);		
		
		if(this.trafoInstalado.booleanValue())
			miTrafo.setTipoMovimiento(new Integer(1));
		else{
			if(this.trafoRetirado.booleanValue())
				miTrafo.setTipoMovimiento(new Integer(0));
		}			
		this.trafos.add(miTrafo);
		this.numeroTrafo = null;
		this.serieTrafo = null;
		this.trafoInstalado = false;
		this.trafoRetirado = false;
		return "success";
	}
	
	private boolean trafoDuplicado(){
	    for(int i = 0; i < this.trafos.size(); i++){
	        Trafo trafo = (Trafo)this.trafos.get(i);
	        if(trafo.getNumeroSerie().longValue() == this.numeroTrafo.longValue() && 
	                trafo.getSerie().equals(this.serieTrafo) == true){
	            return true;
	        }
	    }
	    return false;
	}
	
	/**
	 * Comment for eliminarTrafo
	 * @return "succes" al eliminat Trafo
	 */
	public String eliminarTrafo(){
		
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
	    int index = this.dataTableTrafos.getRowIndex();
	    this.trafos.remove(index);
	    this.numeroMovimientoTrafo = new Integer(this.numeroMovimientoTrafo.intValue() - 1);
	    return "success";
	}	
		
	/**
	 * @return Devuelve trafo.
	 */
	public Trafo getTrafo() {
		return trafo;
	}
	/**
	 * @param trafo El trafo a establecer.
	 */
	public void setTrafo(Trafo trafo) {
		this.trafo = trafo;
	}
	/**
	 * @return Devuelve dataTableTrafos.
	 */
	public DataTable getDataTableTrafos() {
		return dataTableTrafos;
	}
	/**
	 * @param dataTableTrafos El dataTableTrafos a establecer.
	 */
	public void setDataTableTrafos(DataTable dataTableTrafos) {
		this.dataTableTrafos = dataTableTrafos;
	}
	/**
	 * @param consecutivoInterrupcion El consecutivoInterrupcion a establecer.
	 */
	public void setConsecutivoInterrupcion(String consecutivoInterrupcion) {
		this.consecutivoInterrupcion = consecutivoInterrupcion;
	}
	
	/**
	 * @return Devuelve consecutivoClorBO.
	 */
	public ConsecutivoClorBO getConsecutivoClorBO() {
		return consecutivoClorBO;
	}
	/**
	 * @param consecutivoClorBO El consecutivoClorBO a establecer.
	 */
	public void setConsecutivoClorBO(ConsecutivoClorBO consecutivoClorBO) {
		this.consecutivoClorBO = consecutivoClorBO;
	}
	/**
	 * @return Devuelve empleadoBO.
	 */
	public EmpleadoBO getEmpleadoBO() {
		return empleadoBO;
	}
	/**
	 * @param empleadoBO El empleadoBO a establecer.
	 */
	public void setEmpleadoBO(EmpleadoBO empleadoBO) {
		this.empleadoBO = empleadoBO;
	}
	
	/**
	 * Comment for getListaAgencias
	 * @return Lista Agentencias
	 */
	public List getListaAgencias(){
		List agencias = null;
		List agenciasSI = new Vector();

		agencias = this.agenciaBO.getAgencias();
		

		agenciasSI.add(new SelectItem(new Integer(0),"Seleccione una agencia"));

		if (agencias != null && agencias.size() > 0) {
			for (int i = 0; i < agencias.size(); i++) {
				Agencia agencia = (Agencia) agencias.get(i);
				agenciasSI.add(new SelectItem(agencia.getCodigoAgencia(), agencia.getCodigoAgencia() + " - " + 
						agencia.getNombreAgencia()));
			}
		}
		if((agencias == null || agencias.size() == 0) && indAgencia == true){
			indAgencia = false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Para la sección seleccionada no existe agencias."));
								
		}
		return agenciasSI;
	}
	/**
	 * @return Devuelve agenciaBO.
	 */
	public AgenciaBO getAgenciaBO() {
		return agenciaBO;
	}
	/**
	 * @param agenciaBO El agenciaBO a establecer.
	 */
	public void setAgenciaBO(AgenciaBO agenciaBO) {
		this.agenciaBO = agenciaBO;
	}
	/**
	 * @return Devuelve codigoAgencia.
	 */
	public Integer getCodigoAgencia() {
		return codigoAgencia;
	}
	/**
	 * @param codigoAgencia El codigoAgencia a establecer.
	 */
	public void setCodigoAgencia(Integer codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}
	/**
	 * @return Devuelve dataTableEmpleados.
	 */
	public DataTable getDataTableEmpleados() {
		return dataTableEmpleados;
	}
	/**
	 * @param dataTableEmpleados El dataTableEmpleados a establecer.
	 */
	public void setDataTableEmpleados(DataTable dataTableEmpleados) {
		this.dataTableEmpleados = dataTableEmpleados;
	}
	
	/**
	 * Comment for getListaEmpleados
	 * @return Lista de Empleados
	 */
	public List getListaEmpleados(){
		List empleados = new ArrayList();
		List resultado = null;
    	SelectItem item = new SelectItem();
    	Empleado emp = new Empleado();
    	emp.setCedula(new Long(0));
    	emp.setNombreEmpleado(" ");
        item.setValue(emp);
        item.setLabel("Seleccione un Empleado");
        empleados.add(item);
        if(this.codigoOficina != null)
        {
        	resultado = this.usuarioOficinaBO.buscarOficinaEmpleado(this.codigoOficina);
        }else{
    	    UsuarioCia persona = Usuario.getUsuarioObj();
            Long cedula = new Long(persona.getCedula());
            Empleado empleado = this.empleadoBO.buscar(cedula);  
            List<UsuarioOficina> listaPivote = this.usuarioOficinaBO.buscarCedula(cedula);
        	if(!listaPivote.isEmpty())
        	{
        		this.codigoOficina = listaPivote.get(0).getId().getOficina().getCodigoOficina();
        	}else
        	{
        		this.codigoOficina = new Integer(0);
        	}
            resultado = this.usuarioOficinaBO.buscarOficinaEmpleado(this.codigoOficina);
        }
        
        if(resultado != null){
	        for(int i = 0; i < resultado.size(); i++){
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
	 * @return Devuelve empleadoCuadrilla.
	 */
	public Empleado getEmpleadoCuadrilla() {
		return empleadoCuadrilla;
	}
	/**
	 * @param empleadoCuadrilla El empleadoCuadrilla a establecer.
	 */
	public void setEmpleadoCuadrilla(Empleado empleadoCuadrilla) {
		this.empleadoCuadrilla = empleadoCuadrilla;
	}
	
    /**
     * Elimina un empleado de la cuadrilla
     * @return success
     */
	public String eliminarEmpleadoCuadrilla(){
        this.accionProvolma = "";  
        this.nuevoFoco = "form1:voltaje";
	    int index = this.dataTableCuadrilla.getRowIndex();
	    this.empleadosCuadrilla.remove(index);
		return "success";
	}	
	
    /**
     * Agrega un empleado a la cuadrilla
     * @return error or success
     */
	public String agregarEmpleadoCuadrilla(){
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
	
		if(this.empleadoCuadrilla.getCedula().longValue() > 0){		
			if((this.empleadoResponsable == null || this.empleadoResponsable.booleanValue() == false) && (this.empleadoMiembro == null || this.empleadoMiembro.booleanValue() == false )){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Selecione un tipo de empleado."));
				return "error";
			}	
            if (empleadoResponsableExiste() == true  && this.empleadoResponsable.booleanValue() == true) {
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
			miCuadrillaID.setAa(null);
			miCuadrillaID.setCodigoOficina(null);
			miCuadrillaID.setNumeroInterrupcion(null);
			miCuadrillaID.setReporteInterrupcion(new Integer(1));
			miCuadrilla.setCuadrillaID(miCuadrillaID);			
			miCuadrilla.setNombreEmpleado(this.empleadoCuadrilla.getNombreCompleto());
			if(this.empleadoMiembro.booleanValue())				
				miCuadrilla.setIndicador("M");
			else{
				if(this.empleadoResponsable.booleanValue())					
					miCuadrilla.setIndicador("R");
			}			
			this.empleadosCuadrilla.add(miCuadrilla);				
		}
		else{
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
	
    private boolean empleadoResponsableExiste(){
        for(int i = 0; i < this.empleadosCuadrilla.size(); i++){
            Cuadrilla miCuadrilla = (Cuadrilla)this.empleadosCuadrilla.get(i);
            if(miCuadrilla.getIndicador().equals("R"))
                return true;
        }
        return false;
    }	
	
	/**
	 * @return Devuelve cuadrilla.
	 */
	public Cuadrilla getCuadrilla() {
		return cuadrilla;
	}
	/**
	 * @param cuadrilla El cuadrilla a establecer.
	 */
	public void setCuadrilla(Cuadrilla cuadrilla) {
		this.cuadrilla = cuadrilla;
	}
	/**
	 * @return Devuelve cuadrillaBO.
	 */
	public CuadrillaBO getCuadrillaBO() {
		return cuadrillaBO;
	}
	/**
	 * @param cuadrillaBO El cuadrillaBO a establecer.
	 */
	public void setCuadrillaBO(CuadrillaBO cuadrillaBO) {
		this.cuadrillaBO = cuadrillaBO;		
	}
	/**
	 * @return Devuelve empleadoMiembro.
	 */
	public Boolean getEmpleadoMiembro() {
		return empleadoMiembro;
	}
	/**
	 * @param empleadoMiembro El empleadoMiembro a establecer.
	 */
	public void setEmpleadoMiembro(Boolean empleadoMiembro) {
		this.empleadoMiembro = empleadoMiembro;
	}
	/**
	 * @return Devuelve empleadoResponsable.
	 */
	public Boolean getEmpleadoResponsable() {
		return empleadoResponsable;
	}
	/**
	 * @param empleadoResponsable El empleadoResponsable a establecer.
	 */
	public void setEmpleadoResponsable(Boolean empleadoResponsable) {
		this.empleadoResponsable = empleadoResponsable;
	}
	/**
	 * @return Devuelve dataTableCuadrilla.
	 */
	public DataTable getDataTableCuadrilla() {
		return dataTableCuadrilla;
	}
	/**
	 * @param dataTableCuadrilla El dataTableCuadrilla a establecer.
	 */
	public void setDataTableCuadrilla(DataTable dataTableCuadrilla) {
		this.dataTableCuadrilla = dataTableCuadrilla;
	}
	/**
	 * @return Devuelve vehiculo.
	 */
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	/**
	 * @param vehiculo El vehiculo a establecer.
	 */
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	
    /**
     * Lista de tipos de fusibles en formato de select item
     * @return tipos de fusibles
     */
	public List getListTiposFusibles(){
		List tipos = new ArrayList();
		tipos.add(new SelectItem(new Integer(0),"K"));
		tipos.add(new SelectItem(new Integer(1),"T"));
		tipos.add(new SelectItem(new Integer(2),"H"));
		tipos.add(new SelectItem(new Integer(3),"SF"));
		tipos.add(new SelectItem(new Integer(4),"LC"));
		return tipos;
	}
	/**
	 * @return Devuelve dataTableFusibles.
	 */
	public DataTable getDataTableFusibles() {
		return dataTableFusibles;
	}
	/**
	 * @param dataTableFusibles El dataTableFusibles a establecer.
	 */
	public void setDataTableFusibles(DataTable dataTableFusibles) {
		this.dataTableFusibles = dataTableFusibles;
	}
	
	/**
	 * Agrega un fusible a la lista
	 * @return error o success
	 */
	
	public String agregarFusible(){
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
		
		if((this.codigoProteccion != null) && (this.codigoProteccion.intValue() != 103 && this.codigoProteccion.intValue() != 104)){
	        String mensaje = "Para incluir fusibles, el código de protección debe ser 103 ó 104";
	        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";	        
			return "error";
		}
		if(this.fusible.getCapacidadRetirada() == null || this.fusible.getCapacidadRetirada().intValue() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La capacidad retirada es requerida."));
			return "error";
		}		
		if(this.fusible.getCapacidadInstalada() == null || this.fusible.getCapacidadInstalada().intValue() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La capacidad instalada es requerida."));
			return "error";
		}	
		
		if(this.fusible.getCapacidadInstalada().intValue() != this.fusible.getCapacidadRetirada().intValue()){
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!", "Capacidad del fusible es diferente a la del fusible retirado."));
	        
		}	
        
        if(this.fusible.getTipoInstalada().intValue() != this.fusible.getTipoRetirada().intValue()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!", "Tipo del fusible instalado es diferente al del fusible retirado."));
        	}        

		FusibleID mifusibleID = new FusibleID();
		Fusible mifusible = new Fusible();
		mifusible.setCapacidadInstalada(this.fusible.getCapacidadInstalada());
		mifusible.setTipoInstalada(this.fusible.getTipoInstalada());
		mifusible.setCapacidadRetirada(this.fusible.getCapacidadRetirada());
		mifusible.setTipoRetirada(this.fusible.getTipoRetirada());
		mifusibleID.setAa(null);
		mifusibleID.setCodigoOficina(null);
		mifusibleID.setNumeroInterrupcion(null);
		mifusibleID.setNumeroMovimiento(this.numeroMovimientoFusible);		
		this.numeroMovimientoFusible = new Integer(this.numeroMovimientoFusible.intValue() + 1);
		mifusible.setFusibleID(mifusibleID);	
		
		this.fusibles.add(mifusible);			
		return "success";
	}
	
    /**
     * Elimina un fusible deseado
     * @return success
     */
	public String eliminarFusible(){
						
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
	    int index = this.dataTableFusibles.getRowIndex();
	    listaFusibles.add(fusibles.get(index));
	 
	    
	    this.fusibles.remove(index);
	    this.numeroMovimientoFusible = new Integer(this.numeroMovimientoFusible.intValue() - 1);
		return "success";
	}
	/*
 * 	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
	    int index = this.dataTablePostes.getRowIndex();
	    this.postes.remove(index);
	    this.numeroMovimientoPoste = new Integer(this.numeroMovimientoPoste.intValue() - 1);
	    return "succes";
	 * */
	/**
	 * @return Devuelve fusible.
	 */
	public Fusible getFusible() {
		return fusible;
	}
	/**
	 * @param fusible El fusible a establecer.
	 */
	public void setFusible(Fusible fusible) {
		this.fusible = fusible;
	}
	
    /**
     * Realiza la accion de a la pagina de ingreso de seccionamientos.  Para ello pasa los parametros necesarios a través de la sesión
     * @return error o success
     */
	public String irSeccionamientos(){
	    String resultado = "success";
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
		FacesContext ctx = FacesContext.getCurrentInstance();
		
		if(this.saleTotal.booleanValue() == false && this.saleParcial.booleanValue() == false){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No puede agregar seccionamientos ya que no se indicó sale circuito."));
			return "error";
		}
		long secciones = this.seccionBO.getSeccionesEscalar(this.subestacion,this.circuito);
		if(secciones == 1){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No puede agregar seccionamientos ya que el circuito sólo posee una sección."));
			return "error";
		}
				
		if(this.operacion != null && this.operacion.intValue() == 1)
			ctx.getExternalContext().getSessionMap().put("opera",new Integer(1));
		else
			ctx.getExternalContext().getSessionMap().put("opera",new Integer(0));
		
	    ctx.getExternalContext().getSessionMap().put("consecutivoInterrupcion",this.getConsecutivoInterrupcion());
	    ctx.getExternalContext().getSessionMap().put("oficina",this.codigoOficina);
        
        SubEstacion sub = this.subEstacionBO.buscar(this.subestacion);        
	    ctx.getExternalContext().getSessionMap().put("subestacion",sub);
        
        Circuito cir = this.circuitoBO.buscar(this.subestacion, this.circuito);        
	    ctx.getExternalContext().getSessionMap().put("circuito",cir);
        
	    ctx.getExternalContext().getSessionMap().put("fechaInicioInterrupcion",this.fechaInicioInterrupcion);
	    ctx.getExternalContext().getSessionMap().put("fechaFinalInterrupcion",this.fechaFinalInterrupcion);
	    ctx.getExternalContext().getSessionMap().put("clientesAfectados",this.clientesAfectados);
	    ctx.getExternalContext().getSessionMap().put("lugarInterrupcion",this.lugarInterrupcion);
	    ctx.getExternalContext().getSessionMap().put("observaciones",this.observaciones);	    
	    if(this.saleTotal.booleanValue()){
	    	ctx.getExternalContext().getSessionMap().put("saleCircuito",new Integer(2));
	    }
	    else{
	    	if(this.saleParcial.booleanValue()){
	    		ctx.getExternalContext().getSessionMap().put("saleCircuito",new Integer(1));
	    	}
	    	else
	    		ctx.getExternalContext().getSessionMap().put("saleCircuito",new Integer(0));
	    }
        
        SeccionID secID = new SeccionID(this.subestacion,this.circuito,this.seccion); 
        Seccion sec = this.seccionBO.buscar(secID);
	    ctx.getExternalContext().getSessionMap().put("seccion",sec);
        
	    ctx.getExternalContext().getSessionMap().put("codigoVoltaje",this.tipoVoltaje.getTipoVoltajeID().getCodigoVoltaje());
	    ctx.getExternalContext().getSessionMap().put("codigoVoltaje",this.tipoVoltaje.getTipoVoltajeID().getCodigoVoltaje());
	    Map sessionMap = ctx.getExternalContext().getSessionMap();
	    
	    this.seccionamientos = new ArrayList();
	    this.seccionamientos = (ArrayList)sessionMap.get("seccionamientos");
	    ctx.getExternalContext().getSessionMap().put("seccionamientos",this.seccionamientos);
        
        Boolean isModificar = Boolean.valueOf(this.isModificar());
        ctx.getExternalContext().getSessionMap().put("isModificar",isModificar);
        
	    return resultado;
	}
	
	/**
	 * Determina si existen incongruencias entre la fechas de la interrupción madre y las fechas de las gemelas
	 * @return true o false
	 */
	public boolean esTiempoTotalGemela(){
		InterrupcionGemela gemela = null;	
		boolean tiempoFinal = false;
		boolean tiempoInicial = false;
		for(int i = 0; i < this.interrupcionesGemelas.size(); i++){
			gemela = (InterrupcionGemela)this.interrupcionesGemelas.get(i);
			if(gemela.getFechaFin().getTime() == this.fechaFinalInterrupcion.getTime())
			    tiempoFinal =  true;
			if(gemela.getFechaInicio().getTime() == this.fechaInicioInterrupcion.getTime())
			    tiempoInicial =  true;			
		}
		return (tiempoFinal && tiempoInicial);
	}
	
	/**
	 * Agrega una gemela
	 * @return error o success
	 */
	public String agregarGemela(){
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
		
		double horasInicio = 0;
		double horasFinal = 0;
		double horasInicioGemela = 0;
		double horasFinalGemela = 0;		
		double diferencia = 0;
		
		Date dummy = new Date();
		if(this.fechaInicioGemela == null || this.fechaInicioGemela.equals(dummy)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de inicio es requerida."));
			return "error";
		}		
		if(this.fechaFinalGemela == null || this.fechaFinalGemela.equals(dummy)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de fin es requerida."));
			return "error";
		}			
		
		horasInicio = Fechas.millisToMinutes(this.fechaInicioInterrupcion.getTime());
		horasFinal = Fechas.millisToMinutes(this.fechaFinalInterrupcion.getTime());
		horasInicioGemela = Fechas.millisToMinutes(this.fechaInicioGemela.getTime());
		horasFinalGemela = Fechas.millisToMinutes(this.fechaFinalGemela.getTime());		

		double b = horasFinalGemela - horasInicioGemela;
		
		if(b < 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de inicio debe ser menor a la fecha de fin."));
			return "error";
		}	
		if(!(horasInicioGemela >= horasInicio && horasInicioGemela <= horasFinal)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de inicio se encuentra fuera del rango de la interrupción madre."));
			return "error";
		}	
		if(!(horasFinalGemela >= horasInicio && horasFinalGemela <= horasFinal)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de fin se encuentra fuera del rango de la interrupción madre."));
			return "error";
		}			
		
		if(this.abonadosAfectadosGemela == null || this.abonadosAfectadosGemela.longValue() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La cantidad de abonados afectados es requerida."));
			return "error";
		}
		long total = this.abonadosAfectadosGemela.longValue() + this.acumuladorGemelas;
		if(total > this.clientesAfectados.longValue()){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La sumatoria total de abonados afectados de las gemelas no puede superar la cantidad de abonados afectados de la interrupción madre."));
			return "error";
		}
				
		
		this.acumuladorGemelas = this.acumuladorGemelas + this.abonadosAfectadosGemela.longValue();
		this.abonadosGemelas.add(this.abonadosAfectadosGemela);
		InterrupcionGemela gemela = new InterrupcionGemela();
		gemela.setAbonadoAfectado(this.abonadosAfectadosGemela);
		
		this.saldoGemelas = new Long(this.saldoGemelas.longValue() - this.abonadosAfectadosGemela.longValue());
		
		
		
		gemela.setFechaInicio(this.fechaInicioGemela);
		gemela.setFechaFin(this.fechaFinalGemela);		
				
		horasInicio = Fechas.millisToMinutes(this.fechaInicioGemela.getTime());
		horasFinal = Fechas.millisToMinutes(this.fechaFinalGemela.getTime());
		diferencia = (horasFinal - horasInicio) / 60.0;

        diferencia = UtilidadesI.roundNum(diferencia);
		gemela.setTiefue(new Double(diferencia));        
		double horasAbonado = diferencia * this.abonadosAfectadosGemela.longValue();
        
        horasAbonado = UtilidadesI.roundNum(horasAbonado);

		gemela.setHorasAbonado(new Double(horasAbonado));
		
		InterrupcionGemelaID gemelaID = new InterrupcionGemelaID();
		Interrupcion interrupcion = new Interrupcion();
		InterrupcionID interrupcionID = new InterrupcionID();		
		interrupcionID.setAa(null);
		interrupcionID.setCodigoOficina(null);
		interrupcionID.setNumeroInterrupcion(null);		
		interrupcion.setInterrupcionID(interrupcionID);
		gemelaID.setInterrupcion(interrupcion);		
		gemelaID.setNumeroMovimiento(this.numeroMovimientoGemela);
		this.numeroMovimientoGemela = new Integer(this.numeroMovimientoGemela.intValue() + 1);
		gemela.setInterrupcionGemelaID(gemelaID);
		
		SimpleDateFormat formater = new SimpleDateFormat("HH.mm");
		String hora = formater.format(this.fechaInicioGemela);
		double resultado = Double.parseDouble(hora.substring(0,2));
		double min = Double.parseDouble(hora.substring(3,5));
		min = min / 100.0;
		resultado = resultado + min;				
		gemela.setHoraInicio(new Double(resultado));
		
		formater = new SimpleDateFormat("HH.mm");
		hora = formater.format(this.fechaFinalGemela);
		resultado = Double.parseDouble(hora.substring(0,2));
		min = Double.parseDouble(hora.substring(3,5));
		min = min / 100.0;
		resultado = resultado + min;	
		gemela.setHoraFin(new Double(resultado));	
				
		this.interrupcionesGemelas.add(gemela);	
		this.fechaInicioGemela = this.fechaInicioInterrupcion;
		this.fechaFinalGemela = this.fechaFinalInterrupcion;
		this.abonadosAfectadosGemela = new Long(this.clientesAfectados.longValue() - this.acumuladorGemelas);
		if(total == this.clientesAfectados.longValue()){		    
			String mensaje = "Se completó la totalidad de los abonados afectados";
	        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";	        		        		
		}
		return "success";
	}
	
    /**
     * Elimina una gemela de la lista
     * @return success
     */
	public String eliminarGemela(){
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
		this.numeroMovimientoGemela = new Integer(this.numeroMovimientoGemela.intValue() - 1);
	    int index = this.dataTableGemelas.getRowIndex();
	    this.interrupcionesGemelas.remove(index);
		Long cantidad = (Long)this.abonadosGemelas.remove(index);
		this.acumuladorGemelas = this.acumuladorGemelas - cantidad.longValue();	        
        this.abonadosAfectadosGemela = new Long(this.clientesAfectados.longValue() - this.acumuladorGemelas);
		return "success";
	}
	
    /**
     * Retorna la lista de interrupciones gemelas ingresadas
     * @return interrupcionesGemelas
     */
	public List getListaInterrupcionesGemelas(){
		return this.interrupcionesGemelas;
	}
	
	/**
	 * Metodo accesor de abonadosAfectados.
	 * @return Retorna el abonadosAfectados.
	 */
	public Long getAbonadosAfectados() {
		return abonadosAfectados;
	}
	/**
	 * Metodo modificador de abonadosAfectados.
	 * @param abonadosAfectados El abonadosAfectados a modificar.
	 */
	public void setAbonadosAfectados(Long abonadosAfectados) {
		this.abonadosAfectados = abonadosAfectados;
	}
	/**
	 * Metodo accesor de fechaFinalGemela.
	 * @return Retorna el fechaFinalGemela.
	 */
	public Date getFechaFinalGemela() {
		return fechaFinalGemela;
	}
	/**
	 * Metodo modificador de fechaFinalGemela.
	 * @param fechaFinalGemela El fechaFinalGemela a modificar.
	 */
	public void setFechaFinalGemela(Date fechaFinalGemela) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaFinalGemela);
        if(calendar.get(Calendar.YEAR) != 1970){
            this.fechaFinalGemela = fechaFinalGemela;
        }else{
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(this.fechaFinalGemela);
            calendar2.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
            calendar2.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
            this.fechaFinalGemela = calendar2.getTime();
        }
	}
	/**
	 * Metodo accesor de fechaInicioGemela.
	 * @return Retorna el fechaInicioGemela.
	 */
	public Date getFechaInicioGemela() {
		return fechaInicioGemela;
	}
	/**
	 * Metodo modificador de fechaInicioGemela.
	 * @param fechaInicioGemela El fechaInicioGemela a modificar.
	 */
	public void setFechaInicioGemela(Date fechaInicioGemela) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicioGemela);
        if(calendar.get(Calendar.YEAR) != 1970){
            this.fechaInicioGemela = fechaInicioGemela;
        }else{
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(this.fechaInicioGemela);
            calendar2.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
            calendar2.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
            this.fechaInicioGemela = calendar2.getTime();
        }
	}
	
	

	
	private boolean validarDatosInterrupcion(){
		double minutosInicio = 0;
		double minutosFinal = 0;
		double diferencia = 0;
		boolean resultado = true;

		Date dummy = new Date();
		if(this.codigoOficina == null || this.codigoOficina.intValue() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La oficina  es requerida."));
			
			return false;
		}			
		if(this.fechaInicioInterrupcion == null || this.fechaInicioInterrupcion.equals(dummy)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de inicio de interrupción es requerida."));
			
	        return false;
		}		
		if(this.fechaFinalInterrupcion == null || this.fechaFinalInterrupcion.equals(dummy)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de fin de interrupción es requerida."));
			
	        return false;
		}	
		if(this.fechaRecepcionAviso == null || this.fechaRecepcionAviso.equals(dummy)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de aviso de interrupción es requerida."));
			System.out.println("------FECHA AVISO REQUERIDA");
	        return false;
		}
		
		GregorianCalendar fecha = new GregorianCalendar();
		minutosInicio = Fechas.millisToMinutes(fecha.getTime().getTime());
		minutosFinal = Fechas.millisToMinutes(this.fechaInicioInterrupcion.getTime());
		diferencia = minutosFinal - minutosInicio;	
		if(diferencia > 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de inicio de interrupcion debe ser menor o igual a la del sistema."));
			return false;
		}	
		
		
		minutosInicio = Fechas.millisToMinutes(this.fechaInicioInterrupcion.getTime());
		minutosFinal = Fechas.millisToMinutes(this.fechaFinalInterrupcion.getTime());
		diferencia = minutosFinal - minutosInicio;
		
		if(diferencia < 0){
		
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de fin de interrupción debe ser mayor o igual a la fecha de inicio de interrupción."));
			System.out.println("------FECHA FIN MAYOR/IGUAL A FECHA INICIO");
			return false;
		}		

		minutosInicio = Fechas.millisToMinutes(fecha.getTime().getTime());
		minutosFinal = Fechas.millisToMinutes(this.fechaFinalInterrupcion.getTime());
		diferencia = minutosFinal - minutosInicio;
		System.out.println("\n Minutos Incio: " + minutosInicio + "\n Minutos Final:" + minutosFinal + "\n Diferencia: " + diferencia);
		if(diferencia > 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de fin de interrupción debe ser menor a la del sistema."));
			System.out.println("------FECHA FIN MENOR AL SISTEMA");
			return false;
		}	
				
		minutosInicio = Fechas.millisToMinutes(this.fechaInicioInterrupcion.getTime());
		minutosFinal = Fechas.millisToMinutes(this.fechaRecepcionAviso.getTime());
		diferencia = minutosFinal - minutosInicio;	
		System.out.println("\n Minutos Incio: " + minutosInicio + "\n Minutos Final:" + minutosFinal + "\n Diferencia: " + diferencia);
		if(diferencia > 0){
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de aviso de interrupción debe ser menor o igual a la de inicio."));
			 System.out.println("------FECHA AVISO MENOR/IGUAL A FECHA INICIO");
			 return false;
		}
		
		minutosInicio = Fechas.millisToMinutes(this.fechaInicioInterrupcion.getTime());
		minutosFinal = Fechas.millisToMinutes(this.fechaFinalInterrupcion.getTime());
		double horasAviso = Fechas.millisToMinutes(this.fechaRecepcionAviso.getTime());	
        double minutosDia = 1440; //La fecha de aviso pueda ser menor en no más de 24 horas a la fecha de inicio de la interrupción.
		if(!((minutosInicio - minutosDia) <= horasAviso && horasAviso <= minutosFinal)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de aviso de interrupción se encuentra fuera de rango."));
			System.out.println("------FECHA AVISO FUERA DE RANGO");
			return false;
		}			
		
		minutosInicio = Fechas.millisToMinutes(this.fechaInicioInterrupcion.getTime());
		minutosFinal = Fechas.millisToMinutes(this.fechaFinalInterrupcion.getTime());
		diferencia = (minutosFinal - minutosInicio);
		if (diferencia <= 5) {
			String mensaje = "Información debe ser incluida como una interrupción menor a 5 minutos, la duración es: " + diferencia + " minutos";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", mensaje));
	        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";			    
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
        
		if(this.lugarInterrupcion == null || this.lugarInterrupcion.trim().length() == 0){
			System.out.println("---> El lugar de la interrupcion es requerida");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El lugar de interrupción es requerido."));
			return false;
		}	 	
		
		if(this.pueblo.getNumero() == null || this.pueblo.getNumero().intValue() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El pueblo es requerido."));
	        System.out.println("---> El pueblo es requerida");
	        return false;
		}	
        
         /*if (this.puebloBO.puebloExiste(this.pueblo) == false) {
            //msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            //msg.setDetail("El pueblo no existe");
 			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El pueblo no existe."));
            //FacesContext.getCurrentInstance().addMessage("form1:puebloFalla", msg);
            return false;
        }  se sustituye por el servicio */      
		
		if(!validarPueblo(this.pueblo)){
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Pueblo No existe."));
	        System.out.println("---> Pueblo no existe");
			return false;			
		}
		
		if(this.codigoCalle == null || this.codigoCalle.intValue() <= 0){
		
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La calle es requerida."));
		System.out.println("---> La calle es requerida");
			return false;
		}
		if(this.poste == null || this.poste.intValue() < 0){
			
	        System.out.println("---> El poste es requerida");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El poste es requerido."));
	        
			return false;
		}	
		if(this.secuencia == null || this.secuencia.intValue() < 0){
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La secuencia es requerida."));

			return false;
		}			
		
		if(this.puebloEquipo.getNumero() == null || this.puebloEquipo.getNumero().intValue() <= 0){
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El pueblo del equipo es requerido."));
	        System.out.println("---> El pueblo del equipo es requerida");
	      
			return false;
		}		
        
       if (!validarPueblo(this.puebloEquipo)) {
          
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El pueblo no existe."));
           System.out.println("---> El pueblo no existe");        
          
            return false;
        }          
        
		if(this.codigoCalleEquipo == null || this.codigoCalleEquipo.intValue() <= 0){
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La calle es requerida."));
	        System.out.println("---> La calle es requerida");
	        
			return false;
		}
		if(this.posteEquipo == null || this.posteEquipo.intValue() < 0){
		
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El poste es requrido."));
	        System.out.println("---> El poste es requerido");
	        
			return false;
		}	
		if(this.secuenciaEquipo == null || this.secuenciaEquipo.intValue() < 0){
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La secuencia es requerida."));
	        System.out.println("---> La secuencia es requerida");
	       
			return false;
		}	
		
		
		if(this.subestacion == null || this.subestacion.intValue() == 0){
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La subestación es requerida."));
	        System.out.println("---> La subestacion es requerida");
			return false;
		}else{        
            if(this.subEstacionBO.buscar(this.subestacion) == null){
                
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La subestación digitada no existe"));
                System.out.println("---> La subestacion digitada no existe");
              
                return false;
            }
        }
        
		if(this.circuito == null || this.circuito.intValue() == 0){
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El circuito es requerido."));
	        System.out.println("---> El circuito es requerido");
			return false;
		}else{
            if(this.circuitoBO.buscar(this.subestacion, this.circuito) == null){
                
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El circuito digitado no existe"));
                System.out.println("---> El circuito digitado no existe");
                return false;
            }
        }
		
		if(this.seccion == null || this.seccion.longValue() <= 0){
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La sección es requerida."));
	        System.out.println("---> La sección es requerida");
			return false;
		}else{
        
            SeccionID seccionID = new SeccionID(this.subestacion, this.circuito, this.seccion);
            Seccion seccion = this.seccionBO.buscar(seccionID);
            
            if(seccion == null){
               
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La sección digitada no existe."));
                System.out.println("---> La sección digitada no existe");
                return false;
            }
            
            if(seccion.getCodigoOficina().equals(this.codigoOficina) == false){
               
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La sección no pertenece a la oficina seleccionada."));
                System.out.println("---> Los sección no perternece a la oficina seleccionada");
                return false;
            }   
            
            if(seccion.getCongelado().equals(Seccion.ESTADO_CONGELADO)){
               
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La sección se encuentra congelada."));
                System.out.println("---> La sección se encuentra congelada");
                return false;
            }
    		
    		if(this.clientesAfectados == null || this.clientesAfectados.longValue() <= 0){
    			
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La cantidad de clientes afectados es requerida."));
    	        System.out.println("---> La cantidad de clientes afectados es requerida");
    			return false;
    		}				
            
            
    		if(seccion != null && this.clientesAfectados.longValue() > seccion.getAbonadoSeccion().longValue()){
    		
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Clientes mayor a los clientes de la sección: Debe ser menor o igual a " + seccion.getAbonadoSeccion().longValue()));
    	
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
	
	@SuppressWarnings("deprecation")
	private boolean validarDatosCuadrilla(){
	
        double horasAviso  = 0;
		double horasFinal  = 0;
		double horasInicioVehiculo = 0;
		double horasAvisoVehiculo = 0;
		double horasFinalVehiculo = 0;
		double horasLocalizacion = 0;
		double horaInicioInterrup =0;
		double horaFinInterrup =0;
        double horaFin = 0;  
		
		boolean resultado = true;
		if(this.vehiculo.getVehiculoID().getNumeroVehiculo() == null || this.vehiculo.getVehiculoID().getNumeroVehiculo().longValue() <= 0){
			return true;
		}
		else{	
			Date dummy = new Date();
			if(this.vehiculo.getTiempoAviso() == null || this.vehiculo.getTiempoAviso().equals(dummy)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de aviso es requerida."));
		        return false;
			}		
			if(this.vehiculo.getHoraInicio() == null || this.vehiculo.getHoraInicio().equals(dummy)){
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de salida es requerida."));
		      		        
				return false;
			}			
			if(this.vehiculo.getHoraLlegada() == null || this.vehiculo.getHoraLlegada().equals(dummy)){
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de llegada es requerida."));
					        
				return false;
			}	
            
            if(this.vehiculo.getHoraFin() == null || this.vehiculo.getHoraFin().equals(dummy)){
              
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha fin es requerida."));
                          
                return false;
            }
            if(this.vehiculo.getFechaLocalizacion() == null || this.vehiculo.getFechaLocalizacion().equals(dummy)){
              
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha y hora de la localizacion es requerida."));
            	            
                return false;
            }  
			
            horasAviso = Fechas.millisToMinutes(this.fechaRecepcionAviso.getTime());
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
                
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha y hora de la localización no puede ser mayor a la hora fin de la interrupción."));
                   
                return false;
            }
            if(horasLocalizacion < horaInicioInterrup)
            {
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha y hora de la localización no puede ser menor a la hora de la interrupción."));
            	       
                return false;
            }
            
            if(horasLocalizacion < horasAvisoVehiculo)
            {
            	
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha y hora de la localización no puede ser menor a la hora de aviso a la cuadrilla."));
            	   
                return false;
            }
            
            if(horasLocalizacion < horasFinalVehiculo)
            {
                
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha y hora de la localización tiene que ser mayor o igual a la hora de llegada del vehículo."));
            	
                return false;
            }
            
            if(!(horasAvisoVehiculo >= horasAviso)){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de aviso a la cuadrilla debe ser mayor o igual a la fecha de aviso del cliente."));
                return false;
            }
            
            if(!(horasInicioVehiculo >= horasAvisoVehiculo)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de salida del vehículo debe ser mayor o igual a la fecha de aviso a la cuadrilla."));
            	return false;
            }
            //if(horasFinalVehiculo > horasFinal || this.vehiculo.getHoraLlegada().getDay() > this.fechaFinalInterrupcion.getDay() || this.vehiculo.getHoraFin().getMonth() > this.fechaFinalInterrupcion.getMonth())
            if(horasFinalVehiculo > horasFinal)
            {
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha y hora de llegada del vehículo a la interrupción debe ser menor a la hora final de la interrupción."));
                return false;
            }
            
            if(!(horasFinalVehiculo >= horasInicioVehiculo)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de llegada del vehículo debe ser mayor o igual a la fecha de salida del vehículo."));
               return false;
            }  
            
            if(!(horasFinal <= horaFin)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha y hora de cierre de la cuadrilla debe ser mayor o igual a la fecha y hora de fin de la interrupción."));
            	 return false;
            }
            
            
            GregorianCalendar fechaActual = new GregorianCalendar();
            double horasActual = Fechas.millisToMinutes(fechaActual.getTime().getTime());
            if(!(horaFin <= horasActual)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha  y hora de cierre de la cuadrilla debe ser menor o igual a la fecha y hora actual."));
            	return false;
            }

			if(this.vehiculo.getKilometrosInicio() == null || this.vehiculo.getKilometrosInicio().doubleValue() <= 0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El kilometraje inicial es requerido."));
				return false;
			}
			if(this.vehiculo.getKilometrosLlegada() == null || this.vehiculo.getKilometrosLlegada().doubleValue() <= 0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El kilometraje de llegada es requerido."));
				return false;
			}
			if(this.vehiculo.getKilometrosFinal() == null || this.vehiculo.getKilometrosFinal().doubleValue() <= 0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El kilometraje final es requerido."));
				return false;
			}
			
			if(this.vehiculo.getKilometrosLocalizacion() == null || this.vehiculo.getKilometrosLocalizacion().doubleValue() <= 0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El kilometraje de la localización es requerido."));
				return false;
			}
			
			if(this.vehiculo.getKilometrosInicio().doubleValue() > this.vehiculo.getKilometrosLlegada().doubleValue()){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El kilometraje inicial debe ser menor o igual que el de llegada."));
				return false;
			}
			
			if(this.vehiculo.getKilometrosLlegada().doubleValue() > this.vehiculo.getKilometrosFinal().doubleValue()){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El kilometraje de llegada debe ser menor o igual que el final."));
				return false;
			}
			
			if(this.vehiculo.getKilometrosLocalizacion().doubleValue() > this.vehiculo.getKilometrosFinal().doubleValue()){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El kilometraje de la localización debe ser menor o igual que el final."));
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
     * Realiza la accion de ir a la pagina 2
     * @return error o success
     */
	public String irPagina2(){
	    List<Proteccion> listaProteccion = proteccionBO.getProtecciones();
	    int indice = 0;
	    int largo = listaProteccion.size();
	    
	    while(indice < largo)
	    {
	    	
	    	System.out.println("Codigo Proteccion: " + listaProteccion.get(indice).getCodigoProteccion());
	    	System.out.println("Nombre Proteccion: " + listaProteccion.get(indice).getNombreProteccion());
	    	indice++;
	    }
		
	    this.accionProvolma = "";
	    //this.indTension = false;
	    this.nuevoFoco = "form1:voltaje";
	    this.estructuraSelected = "";
	    this.nuevoFoco = "form1:voltaje";
		if(validarDatosInterrupcion()){
		    if(this.interrupcionesGemelas == null || this.interrupcionesGemelas.size() == 0){
				double horasInicio = Fechas.millisToMinutes(this.fechaInicioInterrupcion.getTime());
				double horasFinal = Fechas.millisToMinutes(this.fechaFinalInterrupcion.getTime());
				double tiefue = (horasFinal - horasInicio) / 60.0;
				System.out.println("HORAS INICIO --> "+ horasInicio +"\n HORAS FINAL---> "+ horasFinal + "\n TIFUE---> "+tiefue);
				
                tiefue = UtilidadesI.roundNum(tiefue);
				horasAbonado = new Double(tiefue * this.clientesAfectados.longValue());                
                horasAbonado = new Double(UtilidadesI.roundNum(horasAbonado.doubleValue()));
                
		    }
            else{
                horasAbonado = new Double(this.cacularHorasAbonado());
                horasAbonado = new Double(UtilidadesI.roundNum(horasAbonado.doubleValue()));
            }
		    if(this.operadorScada == null)
		        this.operadorScada = this.operador;
		    this.tituloFinal = this.titulo; //+ this.getConsecutivoInterrupcion();
			
		    return "success";
		}
		else{
			return "error";
		}			
	}
	
    /**
     * Metodo para validarDatosCombos
     * @return codigo a procesar
     */
	public String validarDatosCombos(){
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
	    if(this.codigoMaterial != null){
	    	if(this.codigoMaterial.intValue() == 201){			
				return "201";
			}      
			if(this.codigoMaterial.intValue() == 230 || this.codigoMaterial.intValue() == 234){
				return "230234";
			}
	    }
		return "";
	}
	
    /**
     * Determina y valida si se debe poder ingresar fusible y transformadores
     * @return success
     */
	public String irPagina3(){
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
	    if(this.codigoProteccion != null){
	    	if((this.codigoProteccion.intValue() == 103  || this.codigoProteccion.intValue() == 104)){
	    		this.botonFusible = false;
	    	}
	    	else{
	    		this.botonFusible = true;
	    	}
	    }	
	    else{
	    	this.botonFusible = true;
	    }
	    if(this.codigoMaterial != null){
	    	if(this.codigoMaterial.intValue() == 232){
	    		this.botonTransformador = false;
	    	}
	    	else{
	    		this.botonTransformador = true;
	    	}
	    }	
	    else{
	    	this.botonTransformador = true;
	    }
//	    if((this.codigoProteccion != null) && (this.codigoProteccion.intValue() != 103  && this.codigoProteccion.intValue() != 104))
//	        this.botonFusible = true;
//	    else
//	        this.botonFusible = false;
//	    if(this.codigoMaterial != null && this.codigoMaterial.intValue() != 232)
//	        this.botonTransformador = true;
//	    else{	    	
//	    	if(this.codigoProteccion != null && (this.codigoProteccion.intValue() != 103  && this.codigoProteccion.intValue() != 104))
//		        this.botonFusible = true;
//	    	else
//	    		this.botonTransformador = false;	
//	    }
		return "success";
	}	
	
	private String validarDatosPagina2(){
		
		accionProvolma = "";
        
        if(this.codigoProteccion == null || this.codigoProteccion.intValue() <= 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El codigo de protección es requerido."));
        	return "error";
        }else{
            if(this.proteccionBO.buscar(this.codigoProteccion) == null){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El codigo de protección no existe."));
            	return "error";
            }
        }
        
        if(this.codigoProteccion == 100 || this.codigoProteccion == 109)
        {
	        if(this.codigoIntervenido == null)
	        {
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El codigo de protección es requerido."));
	        	return "error";
	        }
        }
        if(this.codigoMaterial == null || this.codigoMaterial.intValue() <= 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El codigo de material es requerido."));
        	return "error";
        }else{
            if(this.materialBO.buscar(this.codigoMaterial) == null){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El codigo de material no existe."));
            	
                return "error";
            }
        }
        
        if(this.codigoCausa == null || this.codigoCausa.intValue() <= 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El codigo de causa es requerido."));
        	return "error";
        }else{
            if(this.causaBO.buscar(this.codigoCausa) == null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El codigo de causa no existe."));
            	return "error";
            }
        }    
        
        if(this.codigoCausa2 != null && this.codigoCausa2.intValue() <= 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El codigo de causa opcional no posee un valor válido."));
        	return "error";
        }else{
            if(this.codigoCausa2 != null && this.causaBO.buscar(this.codigoCausa2) == null){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El codigo de causa opcional no existe."));
                return "error";
            }
        }        
        
		Provolma provolma = new Provolma();
		ProvolmaID provolmaID = new ProvolmaID();
		provolmaID.setCodigoVoltaje(this.tipoVoltaje==null?Integer.valueOf(0):this.tipoVoltaje.getTipoVoltajeID().getCodigoVoltaje());
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
		if(provolmaBO.existe(provolma) == false){			   
	            this.accionProvolma = "<SCRIPT language='JavaScript1.2'>" +
	    		"window.alert('ERROR! Combinación de códigos no existe en PROVOLMA');" +
	    		"</SCRIPT>";	
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Combinación de códigos no existe en PROVOLMA."));
		        return "error";		    
		}	
		
	    
		if(this.tipoVoltaje == null || this.tipoVoltaje.getTipoVoltajeID().getCodigoVoltaje().intValue() == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El voltaje es requerido."));
			return "error";
		}
		
		String resultado = validarDatosCombos();		
		if(resultado == "201"){
			if(this.postes.size() == 0){
				String mensaje = "Seleccionó el código 201.  Ingrese el detalle";
		        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";		        
		        return "error";
			}	
		}
		if((this.codigoMaterial != null) && (this.codigoMaterial.intValue() == 223)){
			if((this.cantidadCable == null || this.cantidadCable.intValue() < 0) || (this.tipoCable == null || this.tipoCable.intValue() < 0)){
				String mensaje = "Seleccionó el código 223.  Ingrese el detalle";
		        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";		        
		        return "error";
			}	
		}
		if(resultado == "230234"){
			if(this.equipos.size() == 0){
				String mensaje = "Seleccionó el código 230 ó 234.  Ingrese el detalle";
		        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";		        
		        return "error";
			}	
		}	
//cambio validación voltaje Dif 10--------------------------		
		if(this.tipoVoltaje.getTipoVoltajeID().getCodigoVoltaje().intValue() != 10){
			if(this.codigoCausa.intValue() >= 421 && this.codigoCausa.intValue() <= 424){
				String mensaje = "Las causas de la 421 a la 424 sólo se permiten para el código de voltaje 10";
		        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";		        
		        return "error";
			}	

		}
		if(this.tipoVoltaje.getTipoVoltajeID().getCodigoVoltaje().intValue() != 10 && this.codigoCausa2 != null && this.codigoCausa2.intValue() > 0){
			if(this.codigoCausa2.intValue() >= 421 && this.codigoCausa2.intValue()  <= 424){
				String mensaje = "Las causas de la 421 a la 424 sólo se permiten para el código de voltaje 10";
		        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";		        
		        return "error";
			}
		}	
//-------------------------------------
		
		if(this.tipoVoltaje.getTipoVoltajeID().getCodigoVoltaje().intValue() == 10){
			if(this.codigoCausa.intValue() < 421 || this.codigoCausa.intValue() > 424){
				String mensaje = "Para el código de voltaje 10 sólo se permiten las causas de la 421 a la 424";
		        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";		        
		        return "error";
			}	

		}
		if(this.tipoVoltaje.getTipoVoltajeID().getCodigoVoltaje().intValue() == 10 && this.codigoCausa2 != null && this.codigoCausa2.intValue() > 0){
			if(this.codigoCausa2.intValue() < 421 || this.codigoCausa2.intValue() > 424){
				String mensaje = "Para el código de voltaje 10 sólo se permiten las causas de la 421 a la 424";
		        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";		        
		        return "error";
			}
		}		
		if(this.codigoCausa.intValue() == 402){
			if(this.codigoAnimal == null || this.codigoAnimal.intValue() <= 0){
				String mensaje = "Seleccionó el código 402.  Ingrese el detalle";
		        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";		        
		        return "error";
			}	
		}
		if(this.codigoCausa.intValue() == 411 || (this.codigoCausa2 != null && this.codigoCausa2.intValue() == 411)){			
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Causa no corresponde a una interripción."));    
			   return "error";			
		}		
		
		if(((this.codigoProteccion != null) && (this.codigoProteccion.intValue() == 103  || this.codigoProteccion.intValue() == 104)) && this.fusibles.size() == 0){       
			String mensaje = "Debe incluir fusibles, el código de protección es " + this.codigoProteccion.intValue();
	        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";		        
			return "error";
		}
		if(((this.codigoProteccion != null) && (this.codigoProteccion.intValue() != 103  && this.codigoProteccion.intValue() != 104) ) && this.fusibles.size() > 0){
	        String mensaje = "Debe eliminar el detalle de los fusibles ya que el código de protección no corresponde a 103 ó 104";
	        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";
			return "error";
		}	
		if((this.codigoMaterial != null) && this.codigoMaterial.intValue() == 232 && this.trafos.size() == 0){
	        String mensaje = "Debe agregar transformadores, el código de material es 232";
	        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";
			return "error";
		}
		if((this.codigoMaterial != null) && (this.codigoMaterial.intValue() != 232) && this.trafos.size() > 0){
		    String mensaje = "Debe eliminar el detalle de los transformadores ya que el código de material no es 232";
	        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";
			return "error";
		}
			
		if((this.irCuadrillaVehiculo.booleanValue()) && ((this.empleadosCuadrilla == null || this.empleadosCuadrilla.size() == 0) && (this.vehiculo.getVehiculoID().getNumeroVehiculo() == null || this.vehiculo.getVehiculoID().getNumeroVehiculo().longValue() <= 0))){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Se indicó que existe el detalle de cuadrilla y vehículo. Debe ingresar tal detalle."));
			return "error";
		}
		
	    return "success";
	}
	
	
    /**
     * Retorna el codigo de material para definir reglas de navegacion
     * @return String
     */
	public String detalleMaterial(){
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
		if((this.codigoMaterial != null) && (this.codigoMaterial.intValue() == 230 || this.codigoMaterial.intValue() == 234)){			
			return "230234";
		}
		return (this.codigoMaterial == null? "" : this.codigoMaterial.toString());
	}
	
	
    /**
     * Realiza la accion de ir a la pagina de ingreso de cuadrilla y vehiculo
     * @return error o success
     */
	public String irPagina4(){	    
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
		

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
	
	
	private boolean existenCambiosSeccionamientosEnCircuito(){
	    NoPropiaSeccionamiento seccionamiento = null;
	    if(this.seccionamientos != null && this.seccionamientos.size() > 0){
	        for(int i = 0; i < this.seccionamientos.size(); i++){
		        seccionamiento = (NoPropiaSeccionamiento)this.seccionamientos.get(i);
	            if(seccionamiento.getSubestacion().intValue() != this.subestacion.intValue() || 
	               seccionamiento.getCircuito().intValue() != this.circuito.intValue() ||
	               seccionamiento.getNoPropiaSeccionamientoID().getSeccion().longValue() == this.seccion.longValue())
	                return true;
	        }
	    }
	    return false;
	}	
	
	
	private boolean existenCambiosSeccionamientosEnFechas(){	    
	    NoPropiaSeccionamiento seccionamiento = null;	    
	    if(this.seccionamientos != null && this.seccionamientos.size() > 0){
	        for(int i = 0; i < this.seccionamientos.size(); i++){
	            seccionamiento = (NoPropiaSeccionamiento)this.seccionamientos.get(i);
				double horasInicio = Fechas.millisToMinutes(this.fechaFinalInterrupcion.getTime());
				double horasFinal = Fechas.millisToMinutes(seccionamiento.getFechaFin().getTime());
				double diferencia = horasFinal - horasInicio;					            
	            if(seccionamiento.getFechaInicio().equals(this.fechaInicioInterrupcion) != true || diferencia > 0){	    			
	    			return true;
	            }	            	           
	        }
	    }
	    return false;
	}		
	
	private boolean existenCambiosSaleCircuito(){
	    long cantidadSecciones = this.seccionBO.getSeccionesEscalar(this.subestacion,this.circuito);
	    cantidadSecciones = cantidadSecciones - 1; 
	    if(this.saleTotal.booleanValue() && cantidadSecciones > this.seccionamientos.size()){			
			return true;
	    }	    
	    return false;
	}
	
	/**
	 * Almacena la interrupcion
	 * @return error o success
	 */
	@SuppressWarnings("unchecked")
	public String guardarInterrupcion() {
		try {
		    String resultadoPag2 = this.validarDatosPagina2();
		    if (resultadoPag2.equals("error")) {
		        return "error";
		    }

			this.accionProvolma = "";
			this.nuevoFoco = "form1:voltaje";
			FacesContext context = FacesContext.getCurrentInstance();
			Map sessionMap = context.getExternalContext().getSessionMap();
			this.seccionamientos = (ArrayList) sessionMap.get("seccionamientos");
			if ((this.saleTotal.booleanValue() || this.saleParcial.booleanValue()) && (this.seccionamientos == null || this.seccionamientos.size() == 0)) {
				if (this.seccionamientos == null || this.seccionamientos.size() == 0) {
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Se indicó que sale circuito. Debe ingresar los respectivos seccionamientos."));
					return "error";
				}
			}

			if ((this.saleTotal.booleanValue() || this.saleParcial.booleanValue()) && (existenCambiosSeccionamientosEnCircuito() || existenCambiosSeccionamientosEnFechas() || existenCambiosSaleCircuito())) {
				String mensaje = "Realizó modificaciones en los parametros de los seccionamientos, debe actualizarlos";
		        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";
		        return "error";
			}

			if ((this.gemelas.booleanValue()) && (this.interrupcionesGemelas == null || this.interrupcionesGemelas.size() == 0)) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Se indicó que existen gemelas. Debe ingresar las respectivas gemelas."));
				return "error";
			}

			if (this.gemelas.booleanValue() == true && (existenCambiosGemelas() || existenCambiosGemelasEnFechas())) {
				String mensaje = "Realizó modificaciones en los parametros de las interrupciones gemelas, debe actualizarlas";
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia!", "Realizó modificaciones en los parametros de las interrupciones gemelas, debe actualizarlas."));
		        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";
		        return "error";
			}

			if (this.observaciones == null || this.observaciones.trim().length() == 0) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Las observaciones son requeridas."));
				return "error";
			}

			this.interrupcionID = null;
			this.interrupcionID = new InterrupcionID();
			double horasInicio = 0;
			double horasFinal = 0;
			double tiefue = 0;
			double horasAbonado = 0;
			/***********************************/

	        if (isModificar() == false) {
	            this.primeraVezConsecutivo = true;
	            this.getConsecutivoInterrupcion();
	        }
			String valoresLlave[] = this.getConsecutivoInterrupcion().split("-");
			this.interrupcionID.setAa(new Integer(valoresLlave[0].trim()));
			this.interrupcionID.setCodigoOficina(new Integer(valoresLlave[1].trim()));
			this.interrupcionID.setNumeroInterrupcion(new Long(valoresLlave[2].trim()));

	        if (this.interrupcion == null) {
	            this.interrupcion = new Interrupcion();
	            this.interrupcion.setInterrupcionID(this.interrupcionID);
	        } else {
	            this.interrupcion = this.interrupcionBO.getInterrupcion(this.interrupcionID);
	            if (this.interrupcion == null) {
	                this.interrupcion = new Interrupcion();
	                this.interrupcion.setInterrupcionID(this.interrupcionID);
	            }
	        }

	        if (this.codigoProteccion == 100 || this.codigoProteccion == 109) {
	    	    List<Proteccion> listaProteccion = proteccionBO.getProtecciones();
	    	    Boolean existe = false;
	    	    int indice = 0;
	        	int largo = listaProteccion.size();

	        	while(indice < largo) {
	        		if (this.codigoIntervenido == listaProteccion.get(indice).getCodigoProteccion()) {
	        			existe = true;
	            		interrupcion.setCodigoIntervenido(this.codigoIntervenido);
	        			break;
	        		} else {
	        			System.out.println("No existe");
	        		}
	        		indice++;
	        	}

	        	if (!existe) {
	    			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El codigo de protección no existe."));
	        		 return "error";
	        	}
	        } else {
	        	this.codigoIntervenido = null;
				this.interrupcion.setCodigoIntervenido(this.codigoIntervenido);
	        }

	        SeccionID seccionID = new SeccionID(this.subestacion, this.circuito, this.seccion);
	        Seccion seccion = this.seccionBO.buscar(seccionID);

			this.interrupcion.setAbonadoAfectado(this.clientesAfectados);

			if (this.bitacora.booleanValue()) {
				this.interrupcion.setBitacora(new Integer(1));
			} else {
				this.interrupcion.setBitacora(new Integer(0));
			}

			this.interrupcion.setCausa1(this.codigoCausa);
			if (this.codigoCausa2 != null && this.codigoCausa2.intValue() > 0) {
				this.interrupcion.setCausa2(this.codigoCausa2);
			} else {
				this.interrupcion.setCausa2(null);
			}
			this.interrupcion.setCircuito(this.circuito);
			if (this.codigoAgencia.intValue() > 0) {
			    this.interrupcion.setCodigoAgencia(this.codigoAgencia);
			} else {
			    this.interrupcion.setCodigoAgencia(null);
			}
			if (this.codigoCausa.intValue() == 402) {
				this.interrupcion.setCodigoAnimal(this.codigoAnimal);
			} else {
				this.interrupcion.setCodigoAnimal(null);
			}
			this.interrupcion.setCodigoCalleEquipo(this.codigoCalleEquipo);

			this.interrupcion.setCodigoDano(this.codigoDano);
			this.interrupcion.setCodigoCalle(this.codigoCalle);
			this.interrupcion.setCodigoMaterial(this.codigoMaterial);
			if ((this.codigoMaterial != null) && this.codigoMaterial.intValue() == 223) {
				this.interrupcion.setAcometida(this.cantidadCable);
				this.interrupcion.setTipoAcometida(this.tipoCable);
			}

			this.interrupcion.setCodigoProteccion(this.codigoProteccion);
			if (this.pueblo.getNumero() != null && this.pueblo.getNumero().intValue() > 0) {
				this.interrupcion.setCodigoPueblo(this.pueblo.getNumero());
			}

			this.interrupcion.setCodigoPuebloEquipo(this.puebloEquipo.getNumero());
			this.interrupcion.setCodigoVoltaje(this.tipoVoltaje.getTipoVoltajeID().getCodigoVoltaje());
			this.interrupcion.setComentario(this.observaciones.trim().toUpperCase());
			if (this.faseR != null && this.faseR.booleanValue()) {
				this.interrupcion.setFaseR("R");
			} else {
				this.interrupcion.setFaseR("");
			}
			if (this.faseS != null && this.faseS.booleanValue()) {
				this.interrupcion.setFaseS("S");
			} else {
				this.interrupcion.setFaseS("");
			}
			if (this.faseT != null && this.faseT.booleanValue()) {
				this.interrupcion.setFaseT("T");
			} else {
				this.interrupcion.setFaseT("");
			}
			if(this.indTension) {
				this.interrupcion.setIndTension(Integer.valueOf(2));
			} else {
				this.interrupcion.setIndTension(Integer.valueOf(1));
			}

			this.interrupcion.setFechaAviso(this.fechaRecepcionAviso);
			this.interrupcion.setFechaFin(this.fechaFinalInterrupcion);
			this.interrupcion.setFechaInicio(this.fechaInicioInterrupcion);	

			SimpleDateFormat formater = new SimpleDateFormat("HH.mm");
			String hora = formater.format(this.fechaRecepcionAviso);

			double resultado = Double.parseDouble(hora.substring(0,2));
			double min = Double.parseDouble(hora.substring(3,5));
			min = min / 100.0;
			resultado = resultado + min;				
			this.interrupcion.setHoraAviso(new Double(resultado));

			formater = new SimpleDateFormat("HH.mm");
			hora = formater.format(this.fechaInicioInterrupcion);
			resultado = Double.parseDouble(hora.substring(0,2));
			min = Double.parseDouble(hora.substring(3,5));
			min = min / 100.0;
			resultado = resultado + min;				
			this.interrupcion.setHoraInicio(new Double(resultado));

			formater = new SimpleDateFormat("HH.mm");
			hora = formater.format(this.fechaFinalInterrupcion);
			resultado = Double.parseDouble(hora.substring(0,2));
			min = Double.parseDouble(hora.substring(3,5));
			min = min / 100.0;
			resultado = resultado + min;	
			this.interrupcion.setHoraFin(new Double(resultado));						

			horasInicio = Fechas.millisToMinutes(this.fechaInicioInterrupcion.getTime());
			horasFinal = Fechas.millisToMinutes(this.fechaFinalInterrupcion.getTime());
			tiefue = (horasFinal - horasInicio) / 60.0;

	        tiefue = UtilidadesI.roundNum(tiefue);
			if (this.interrupcionesGemelas != null && this.interrupcionesGemelas.size() > 0 && this.gemelas.booleanValue()) {
			    horasAbonado = cacularHorasAbonado();
			} else {
			    horasAbonado = tiefue * this.clientesAfectados.longValue();
			}

	        horasAbonado = UtilidadesI.roundNum(horasAbonado);
			this.interrupcion.setHorasAbonado(new Double(horasAbonado));
			if (this.saleTotal.booleanValue()) {
				this.interrupcion.setIndCircuito(null);
			} else {
				if (this.saleParcial.booleanValue()) {
					this.interrupcion.setIndCircuito(new Integer(1));
				} else {
					this.interrupcion.setIndCircuito(new Integer(0));
				}
			}
			if (this.indicadorSubestacion.booleanValue()) {
				this.interrupcion.setIndSubEstacion(new Integer(1));
			} else {
				this.interrupcion.setIndSubEstacion(new Integer(0));
			}

			this.interrupcion.setLugar(this.lugarInterrupcion.trim().toUpperCase());
			this.interrupcion.setMedidor(this.medidor);
			this.interrupcion.setNombreCliente(this.cliente.trim().toUpperCase());
			if (this.scada.booleanValue()) {
				this.interrupcion.setOperadoPor(new Integer(0));
				this.interrupcion.setOperadorScada(this.operadorScada.trim().toUpperCase());
			} else {	
				this.interrupcion.setOperadoPor(new Integer(1));
				this.interrupcion.setOperadorScada(null);
			}

			this.interrupcion.setOperador(this.cedula);
			this.interrupcion.setPoste(this.poste);
			this.interrupcion.setPosteEquipo(this.posteEquipo);
			this.interrupcion.setRegion(seccion.getRegion());
			this.interrupcion.setSeccion(seccion.getSeccionID().getSeccion());
			this.interrupcion.setSecuencia(this.secuencia);
			this.interrupcion.setSecuenciaEquipo(this.secuenciaEquipo);
			this.interrupcion.setSubEstacion(this.subestacion);
			this.interrupcion.setSubRegion(seccion.getSubRegion());
			if (this.telefono != null) {
			    this.interrupcion.setTelefonoCliente(this.telefono.toString());
			}

	        tiefue = UtilidadesI.roundNum(tiefue);
			this.interrupcion.setTiefue(new Double(tiefue));

			int codigoVoltaje = this.tipoVoltaje.getTipoVoltajeID().getCodigoVoltaje().intValue();
			if (codigoVoltaje == 10) {
				this.interrupcion.setTipoVoltaje(new Integer(1));
			} else {
				if (codigoVoltaje == 20 || codigoVoltaje == 30 || codigoVoltaje == 40) {
					this.interrupcion.setTipoVoltaje(new Integer(2));
				} else {
					this.interrupcion.setTipoVoltaje(new Integer(3));
				}
			}

			if (this.trifasica.booleanValue()) {
				this.interrupcion.setTrifasica(new Integer(1));
			} else {
				this.interrupcion.setTrifasica(new Integer(0));
			}

	        this.interrupcion.setUsuario(Usuario.getUsuario());
			/******************************************************************************************/

	        asignarId(this.interrupcionID);

			if (this.vehiculo.getVehiculoID().getNumeroVehiculo() != null && this.vehiculo.getVehiculoID().getNumeroVehiculo().longValue() > 0) {
				this.vehiculo.getVehiculoID().setAa(this.interrupcion.getInterrupcionID().getAa());
				this.vehiculo.getVehiculoID().setCodigoOficina(this.interrupcion.getInterrupcionID().getCodigoOficina());
				this.vehiculo.getVehiculoID().setNumeroInterrupcion(this.interrupcion.getInterrupcionID().getNumeroInterrupcion());			
				this.vehiculo.getVehiculoID().setTipo(new Integer(0));
				double total = (this.vehiculo.getKilometrosLlegada().doubleValue() - this.vehiculo.getKilometrosInicio().doubleValue());
				total = total * 2;
				this.vehiculo.setKilometrosTotales(new Double(total));
			}

			String values[] = this.getConsecutivoInterrupcion().split("-");
			NoPropiaSeccionamientoID seccionamientoID = new NoPropiaSeccionamientoID();
			seccionamientoID.setAa(new Integer(values[0].trim()));
			seccionamientoID.setCodigoOficina(new Integer(values[1].trim()));
			seccionamientoID.setNumeroInterrupcion(new Long(values[2].trim()));

			if ((this.operacion == null || this.operacion.intValue() != 1) && this.interrupcionBO.interrupcionExiste(interrupcion.getInterrupcionID()) == false) {
				interrupcionBO.guardarInterrupcion(interrupcion, irCuadrillaVehiculo.booleanValue(), empleadosCuadrilla,
					codigoProteccion, fusibles, codigoMaterial, trafos, postes, equipos, gemelas.booleanValue(),
					interrupcionesGemelas, vehiculo, (saleTotal.booleanValue() || saleParcial.booleanValue()), seccionamientos);
			} else {
				interrupcionBO.modificarInterrupcion(interrupcion, codigoMaterial, irCuadrillaVehiculo,
					empleadosCuadrilla, cuadrillaID, fusibles, fusibleID, listaFusibles, trafos, trafoID,
					postes, posteInstaladoRetiradoID, equipos, equipoEspecialID, gemelas, interrupcionesGemelas,
					interrupcionGemelaID, vehiculo, vehiculoID, vehiculoIDViejo,
					(saleTotal.booleanValue() || saleParcial.booleanValue()), seccionamientos, seccionamientoID);
				context.getExternalContext().getSessionMap().put("operacion", new Integer(3));
			}

	        this.primeraVezConsecutivo = false;
	        this.regresaDePag = false;
	        String mensaje = "Interrupción mayor a 5 minutos almacenada en la base de datos con los siguientes datos: Año: " + interrupcionID.getAa() + " - " + "Oficina: " + interrupcionID.getCodigoOficina() + " - " + "Consecutivo: " + interrupcionID.getNumeroInterrupcion();
	        if (tiefue > 5) {
	            mensaje += ".  Atención, CANTIDAD DE HORAS DE LA INTERRUPCIÓN ES MAYOR A 5";
	        }
	        this.accionGuardar = "<SCRIPT language='JavaScript1.2'>" + "window.alert('" + mensaje + "');</SCRIPT>";
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Interrupción mayor a 5 minutos almacenada en la base de datos con los siguientes datos: Año: " + interrupcionID.getAa() + " - " + "Oficina: " + interrupcionID.getCodigoOficina() + " - " + "Consecutivo: " + interrupcionID.getNumeroInterrupcion()));
		    context.getExternalContext().getSessionMap().put("consecutivoInterrupcion",this.consecutivoInterrupcion);
			return "success";
		} catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

    private void asignarId(InterrupcionID interrupcionID){
        for(int i = 0; i < this.empleadosCuadrilla.size(); i++){
            ((Cuadrilla)this.empleadosCuadrilla.get(i)).getCuadrillaID().setAa(interrupcionID.getAa());
            ((Cuadrilla)this.empleadosCuadrilla.get(i)).getCuadrillaID().setCodigoOficina(interrupcionID.getCodigoOficina());
            ((Cuadrilla)this.empleadosCuadrilla.get(i)).getCuadrillaID().setNumeroInterrupcion(interrupcionID.getNumeroInterrupcion());
        }
        
        for(int i = 0; i < this.fusibles.size(); i++){
            ((Fusible)this.fusibles.get(i)).getFusibleID().setAa(interrupcionID.getAa());
            ((Fusible)this.fusibles.get(i)).getFusibleID().setCodigoOficina(interrupcionID.getCodigoOficina());
            ((Fusible)this.fusibles.get(i)).getFusibleID().setNumeroInterrupcion(interrupcionID.getNumeroInterrupcion());
        }    
        
        for(int i = 0; i < this.trafos.size(); i++){
            ((Trafo)this.trafos.get(i)).getTrafoID().setAa(interrupcionID.getAa());
            ((Trafo)this.trafos.get(i)).getTrafoID().setCodigoOficina(interrupcionID.getCodigoOficina());
            ((Trafo)this.trafos.get(i)).getTrafoID().setNumeroInterrupcion(interrupcionID.getNumeroInterrupcion());
        }   
        
        for(int i = 0; i < this.postes.size(); i++){
            ((PosteInstaladoRetirado)this.postes.get(i)).getPosteInstaladoRetiradoID().setAno(interrupcionID.getAa());
            ((PosteInstaladoRetirado)this.postes.get(i)).getPosteInstaladoRetiradoID().setCodigoOficina(interrupcionID.getCodigoOficina());
            ((PosteInstaladoRetirado)this.postes.get(i)).getPosteInstaladoRetiradoID().setNumeroInterrupcion(interrupcionID.getNumeroInterrupcion());
        }  
        
        for(int i = 0; i < this.equipos.size(); i++){
            ((EquipoEspecial)this.equipos.get(i)).getEquipoEspecialID().setAno(interrupcionID.getAa());
            ((EquipoEspecial)this.equipos.get(i)).getEquipoEspecialID().setCodigoOficina(interrupcionID.getCodigoOficina());
            ((EquipoEspecial)this.equipos.get(i)).getEquipoEspecialID().setNumeroInterrupcion(interrupcionID.getNumeroInterrupcion());
        }   
        
        for(int i = 0; i < this.interrupcionesGemelas.size(); i++){
            Interrupcion interup = new Interrupcion();
            interup.setInterrupcionID(interrupcionID);
            ((InterrupcionGemela)this.interrupcionesGemelas.get(i)).getInterrupcionGemelaID().setInterrupcion(interup);
        }   
        
        for(int i = 0; this.seccionamientos != null && i < this.seccionamientos.size(); i++){
            ((NoPropiaSeccionamiento)this.seccionamientos.get(i)).getNoPropiaSeccionamientoID().setAa(interrupcionID.getAa());
            ((NoPropiaSeccionamiento)this.seccionamientos.get(i)).getNoPropiaSeccionamientoID().setCodigoOficina(interrupcionID.getCodigoOficina());
            ((NoPropiaSeccionamiento)this.seccionamientos.get(i)).getNoPropiaSeccionamientoID().setNumeroInterrupcion(interrupcionID.getNumeroInterrupcion());
        }           
    }    
	
    private boolean isModificar(){
        this.interrupcionID = new InterrupcionID();
        if(this.consecutivoInterrupcion != null){
            String valoresLlave[] = this.getConsecutivoInterrupcion().split("-");
            this.interrupcionID.setAa(new Integer(valoresLlave[0].trim()));
            this.interrupcionID.setCodigoOficina(new Integer(valoresLlave[1].trim()));
            this.interrupcionID.setNumeroInterrupcion(new Long(valoresLlave[2].trim()));
            
            
            if(this.interrupcion == null){
                this.interrupcion = new Interrupcion();
                this.interrupcion.setInterrupcionID(this.interrupcionID);
            }
            else{
                this.interrupcion = this.interrupcionBO.getInterrupcion(this.interrupcionID);
            }       
            if((this.operacion == null || this.operacion.intValue() != 1) && (this.interrupcion == null || this.interrupcionBO.interrupcionExiste(this.interrupcion.getInterrupcionID()) == false))
                return false;
            else
                return true;
        }else
            return false;
    }
    
	private double cacularHorasAbonado(){
	    double horasAbonado = 0;
	    for(int i = 0; i < this.interrupcionesGemelas.size(); i++){
	        InterrupcionGemela ig = (InterrupcionGemela)this.interrupcionesGemelas.get(i);
	        horasAbonado = horasAbonado + ig.getHorasAbonado().doubleValue();
	    }
	    return horasAbonado;
	}
	/**
	 * Metodo accesor de saleCircuito.
	 * @return Retorna el saleCircuito.
	 */
	public Integer getSaleCircuito() {
		return saleCircuito;
	}
	/**
	 * Metodo modificador de saleCircuito.
	 * @param saleCircuito El saleCircuito a modificar.
	 */
	public void setSaleCircuito(Integer saleCircuito) {
		this.saleCircuito = saleCircuito;
	}

	/**
	 * Metodo accesor de faseR.
	 * @return Retorna el faseR.
	 */
	public Boolean getFaseR() {
		return faseR;
	}
	/**
	 * Metodo modificador de faseR.
	 * @param faseR El faseR a modificar.
	 */
	public void setFaseR(Boolean faseR) {
		this.faseR = faseR;
	}
	/**
	 * Metodo accesor de faseS.
	 * @return Retorna el faseS.
	 */
	public Boolean getFaseS() {
		return faseS;
	}
	/**
	 * Metodo modificador de faseS.
	 * @param faseS El faseS a modificar.
	 */
	public void setFaseS(Boolean faseS) {
		this.faseS = faseS;
	}
	/**
	 * Metodo accesor de faseT.
	 * @return Retorna el faseT.
	 */
	public Boolean getFaseT() {
		return faseT;
	}
	/**
	 * Metodo modificador de faseT.
	 * @param faseT El faseT a modificar.
	 */
	public void setFaseT(Boolean faseT) {
		this.faseT = faseT;
	}
	/**
	 * Metodo accesor de codigoCausa.
	 * @return Retorna el codigoCausa.
	 */
	public Integer getCodigoCausa() {
		return codigoCausa;
	}
	/**
	 * Metodo modificador de codigoCausa.
	 * @param codigoCausa El codigoCausa a modificar.
	 */
	public void setCodigoCausa(Integer codigoCausa) {
		this.codigoCausa = codigoCausa;
	}
	/**
	 * Metodo accesor de codigoDano.
	 * @return Retorna el codigoDano.
	 */
	public Integer getCodigoDano() {
		return codigoDano;
	}
	/**
	 * Metodo modificador de codigoDano.
	 * @param codigoDano El codigoDano a modificar.
	 */
	public void setCodigoDano(Integer codigoDano) {
		this.codigoDano = codigoDano;
	}
	/**
	 * Metodo accesor de codigoMaterial.
	 * @return Retorna el codigoMaterial.
	 */
	public Integer getCodigoMaterial() {
		System.out.println("Material "+ codigoMaterial);
		return codigoMaterial;
	}
	/**
	 * Metodo modificador de codigoMaterial.
	 * @param codigoMaterial El codigoMaterial a modificar.
	 */
	public void setCodigoMaterial(Integer codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}
	/**
	 * Metodo accesor de codigoProteccion.
	 * @return Retorna el codigoProteccion.
	 */
	public Integer getCodigoProteccion() {
		System.out.println("Protección "+ codigoProteccion);
		return codigoProteccion;
	}
	/**
	 * Metodo modificador de codigoProteccion.
	 * @param codigoProteccion El codigoProteccion a modificar.
	 */
	public void setCodigoProteccion(Integer codigoProteccion) {
		this.codigoProteccion = codigoProteccion;
		//this.nuevoFoco = "form1:cboMateriales";
	}
		
	public Integer getCodigoIntervenido() {
		return codigoIntervenido;
	}

	public void setCodigoIntervenido(Integer codigoIntervenido) {
		this.codigoIntervenido = codigoIntervenido;
	}

	/**
	 * Metodo accesor de dataTablePostes.
	 * @return Retorna el dataTablePostes.
	 */
	public DataTable getDataTablePostes() {
		return dataTablePostes;
	}
	/**
	 * Metodo modificador de dataTablePostes.
	 * @param dataTablePostes El dataTablePostes a modificar.
	 */
	public void setDataTablePostes(DataTable dataTablePostes) {
		this.dataTablePostes = dataTablePostes;
	}
	/**
	 * Metodo accesor de longitudPoste.
	 * @return Retorna el longitudPoste.
	 */
	public Integer getLongitudPoste() {
		return longitudPoste;
	}
	/**
	 * Metodo modificador de longitudPoste.
	 * @param longitudPoste El longitudPoste a modificar.
	 */
	public void setLongitudPoste(Integer longitudPoste) {
		this.longitudPoste = longitudPoste;
	}
	/**
	 * Metodo accesor de postes.
	 * @return Retorna el postes.
	 */
	@SuppressWarnings("rawtypes")
	public List getPostes() {
		return postes;
	}
	/**
	 * Metodo modificador de postes.
	 * @param postes El postes a modificar.
	 */
	@SuppressWarnings("rawtypes")
	public void setPostes(List postes) {
		this.postes = postes;
	}
	/**
	 * Metodo accesor de tipoPoste.
	 * @return Retorna el tipoPoste.
	 */
	public Integer getTipoPoste() {
		return tipoPoste;
	}
	/**
	 * Metodo modificador de tipoPoste.
	 * @param tipoPoste El tipoPoste a modificar.
	 */
	public void setTipoPoste(Integer tipoPoste) {
		this.tipoPoste = tipoPoste;
	}
	/**
	 * Metodo accesor de posteInstalado.
	 * @return Retorna el posteInstalado.
	 */
	public Boolean getPosteInstalado() {
		return posteInstalado;
	}
	/**
	 * Metodo modificador de posteInstalado.
	 * @param posteInstalado El posteInstalado a modificar.
	 */
	public void setPosteInstalado(Boolean posteInstalado) {
		this.posteInstalado = posteInstalado;
	}
	/**
	 * Metodo accesor de posteRetirado.
	 * @return Retorna el posteRetirado.
	 */
	public Boolean getPosteRetirado() {
		return posteRetirado;
	}
	/**
	 * Metodo modificador de posteRetirado.
	 * @param posteRetirado El posteRetirado a modificar.
	 */
	public void setPosteRetirado(Boolean posteRetirado) {
		this.posteRetirado = posteRetirado;
	}
	
    /**
     * Retorna una lista de tipos de postes
     * @return lista de postes
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getListaTiposPostes(){
		List tiposPostes = new ArrayList();
		tiposPostes.add(new SelectItem(new Integer(1),"CONCRETO"));
		tiposPostes.add(new SelectItem(new Integer(2),"MADERA"));
		tiposPostes.add(new SelectItem(new Integer(3),"RIEL"));
		tiposPostes.add(new SelectItem(new Integer(4),"AUTOSOPORTADO"));
		tiposPostes.add(new SelectItem(new Integer(5),"METAL"));
		return tiposPostes;
	}
	/**
	 * Comment for agregarPoste
	 * @return "success" al agregar Poste
	 */
	public String agregarPoste(){
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
		
		if(this.tipoPoste == null || this.tipoPoste.intValue() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El tipo de poste es requerido."));
			return "error";
		}
		if(this.longitudPoste == null || this.longitudPoste.intValue() <= 0){
			System.out.println("LONGTUD-----> "+ longitudPoste);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La longitud del poste es requerida."));
			return "error";
		}	
		if((this.posteInstalado == null || this.posteInstalado.booleanValue() == false) && (this.posteRetirado == null || this.posteRetirado.booleanValue() == false)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccione un tipo de movimiento."));
			return "error";
		}	
		
		PosteInstaladoRetirado poste = new PosteInstaladoRetirado();
		PosteInstaladoRetiradoID posteID = new PosteInstaladoRetiradoID();
		
		posteID.setAno(null);
		posteID.setCodigoOficina(null);
		posteID.setNumeroInterrupcion(null);
		posteID.setNumeroMovimiento(this.numeroMovimientoPoste);		
		this.numeroMovimientoPoste = new Integer(this.numeroMovimientoPoste.intValue() + 1);		
		poste.setPosteInstaladoRetiradoID(posteID);
		poste.setLonguitud(this.longitudPoste);
		poste.setTipoPoste(this.tipoPoste);
		
		if(this.posteInstalado.booleanValue())
			poste.setIndicador(new Integer(1));
		else{
			if(this.posteRetirado.booleanValue())
				poste.setIndicador(new Integer(0));
		}			
		this.postes.add(poste);		
		return "success";
	}
	
	/**
	 * Comment for eliminarPoste
	 * @return "succes" al eliminat Poste
	 */
	public String eliminarPoste(){
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
	    int index = this.dataTablePostes.getRowIndex();
	    this.postes.remove(index);
	    this.numeroMovimientoPoste = new Integer(this.numeroMovimientoPoste.intValue() - 1);
	    return "succes";
	}
	
    /**
     * Accion de agregar cable acometida
     * @return
     */
	public String agregarCableAcometida(){
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
		return "success";
	}
	
	/**
	 * Metodo accesor de cantidadCable.
	 * @return Retorna el cantidadCable.
	 */
	public Integer getCantidadCable() {
		return cantidadCable;
	}
	/**
	 * Metodo modificador de cantidadCable.
	 * @param cantidadCable El cantidadCable a modificar.
	 */
	public void setCantidadCable(Integer cantidadCable) {
		this.cantidadCable = cantidadCable;
	}
	/**
	 * Metodo accesor de tipoCable.
	 * @return Retorna el tipoCable.
	 */
	public Integer getTipoCable() {
		return tipoCable;
	}
	/**
	 * Metodo modificador de tipoCable.
	 * @param tipoCable El tipoCable a modificar.
	 */
	public void setTipoCable(Integer tipoCable) {
		this.tipoCable = tipoCable;
	}
	/**
	 * Metodo accesor de dataTableEquipos.
	 * @return Retorna el dataTableEquipos.
	 */
	public DataTable getDataTableEquipos() {
		return dataTableEquipos;
	}
	/**
	 * Metodo modificador de dataTableEquipos.
	 * @param dataTableEquipos El dataTableEquipos a modificar.
	 */
	public void setDataTableEquipos(DataTable dataTableEquipos) {
		this.dataTableEquipos = dataTableEquipos;
	}
	/**
	 * Metodo accesor de equipoInstalado.
	 * @return Retorna el equipoInstalado.
	 */
	public Boolean getEquipoInstalado() {
		return equipoInstalado;
	}
	/**
	 * Metodo modificador de equipoInstalado.
	 * @param equipoInstalado El equipoInstalado a modificar.
	 */
	public void setEquipoInstalado(Boolean equipoInstalado) {
		this.equipoInstalado = equipoInstalado;
	}
	/**
	 * Metodo accesor de equipoRetirado.
	 * @return Retorna el equipoRetirado.
	 */
	public Boolean getEquipoRetirado() {
		return equipoRetirado;
	}
	/**
	 * Metodo modificador de equipoRetirado.
	 * @param equipoRetirado El equipoRetirado a modificar.
	 */
	public void setEquipoRetirado(Boolean equipoRetirado) {
		this.equipoRetirado = equipoRetirado;
	}
	/**
	 * Metodo accesor de equipos.
	 * @return Retorna el equipos.
	 */
	@SuppressWarnings({ "rawtypes" })
	public List getEquipos() {
		return equipos;
	}
	/**
	 * Metodo modificador de equipos.
	 * @param equipos El equipos a modificar.
	 */
	@SuppressWarnings({ "rawtypes" })
	public void setEquipos(List equipos) {
		this.equipos = equipos;
	}
	/**
	 * Metodo accesor de numeroEquipo.
	 * @return Retorna el numeroEquipo.
	 */
	public Long getNumeroEquipo() {
		return numeroEquipo;
	}
	/**
	 * Metodo modificador de numeroEquipo.
	 * @param numeroEquipo El numeroEquipo a modificar.
	 */
	public void setNumeroEquipo(Long numeroEquipo) {
		this.numeroEquipo = numeroEquipo;
	}
	/**
	 * Metodo accesor de serieEquipo.
	 * @return Retorna el serieEquipo.
	 */
	public String getSerieEquipo() {
		return serieEquipo;
	}
	/**
	 * Metodo modificador de serieEquipo.
	 * @param serieEquipo El serieEquipo a modificar.
	 */
	public void setSerieEquipo(String serieEquipo) {
		this.serieEquipo = serieEquipo;
	}
	
	
	/**
	 * Comment for agregarEquipo
	 * @return "success" al agregar Equipo
	 */
	@SuppressWarnings({ "unchecked" })
	public String agregarEquipo(){
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
		
		if(this.numeroEquipo == null || this.numeroEquipo.longValue() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de equipo es requerido."));
			return "error";
		}	
        if(this.serieEquipo == null || this.serieEquipo.trim().length() == 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La serie del equipo es requerida."));
        	 return "error";
        }           
		if(existeEquipoEspecial()){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número del equipo ya fue introducido."));
			return "error";
		}
		EquipoEspecial equipo = new EquipoEspecial();
		EquipoEspecialID equipoID = new EquipoEspecialID();		
		
		equipoID.setAno(null);
		equipoID.setCodigoOficina(null);
		equipoID.setNumeroInterrupcion(null);
		equipoID.setNumeroICE(this.numeroEquipo);		
		equipo.setEquipoEspecialID(equipoID);
		equipo.setSerie(this.serieEquipo);				
		this.equipos.add(equipo);		
		return "success";
	}
	
	private boolean existeEquipoEspecial(){
		for(int i = 0; i < this.equipos.size(); i++){
			EquipoEspecial equipo = (EquipoEspecial)equipos.get(i);
			if(this.numeroEquipo.longValue() == equipo.getEquipoEspecialID().getNumeroICE().longValue())
				return true;
		}
		return false;
	}
	
	/**
	 * Comment for eliminarEquipo
	 * @return "succes" al eliminar Equipo
	 */
	public String eliminarEquipo(){
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
	    int index = this.dataTableEquipos.getRowIndex();
	    this.equipos.remove(index);
	    return "succes";
	}
	
    /**
     * Retorna una lista de animales
     * @return lista de animales
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getListaAnimales(){
		List animales = this.animalBO.getAnimalesOrdenNombre();
		List resultado = new ArrayList();
		if(animales != null){
			resultado.add(new SelectItem(new Integer(0), "Seleccione un animal"));
			for(int i = 0; i < animales.size(); i++){
				Animal animal = (Animal)animales.get(i);
				resultado.add(new SelectItem(animal.getCodigoAnimal(),animal.getNombreAnimal()));
			}
		}
	
		return resultado;
	}

	/**
	 * Metodo accesor de animalBO.
	 * @return Retorna el animalBO.
	 */
	public AnimalBO getAnimalBO() {
		return animalBO;
	}
	/**
	 * Metodo modificador de animalBO.
	 * @param animalBO El animalBO a modificar.
	 */
	public void setAnimalBO(AnimalBO animalBO) {
		this.animalBO = animalBO;
	}
	/**
	 * Metodo accesor de codigoAnimal.
	 * @return Retorna el codigoAnimal.
	 */
	public Integer getCodigoAnimal() {
		return codigoAnimal;
	}
	/**
	 * Metodo modificador de codigoAnimal.
	 * @param codigoAnimal El codigoAnimal a modificar.
	 */
	public void setCodigoAnimal(Integer codigoAnimal) {
		this.codigoAnimal = codigoAnimal;
	}	
	
	/**
	 * Metodo accesor de empleadosCuadrilla.
	 * @return Retorna el empleadosCuadrilla.
	 */
	@SuppressWarnings({ "rawtypes" })
	public List getEmpleadosCuadrilla() {
		return empleadosCuadrilla;
	}
	/**
	 * Metodo modificador de empleadosCuadrilla.
	 * @param empleadosCuadrilla El empleadosCuadrilla a modificar.
	 */
	@SuppressWarnings({ "rawtypes" })
	public void setEmpleadosCuadrilla(List empleadosCuadrilla) {
		this.empleadosCuadrilla = empleadosCuadrilla;
	}
	/**
	 * Metodo accesor de fusibles.
	 * @return Retorna el fusibles.
	 */
	@SuppressWarnings({ "rawtypes" })
	public List getFusibles() {
		return fusibles;
	}
	/**
	 * Metodo modificador de fusibles.
	 * @param fusibles El fusibles a modificar.
	 */
	@SuppressWarnings({ "rawtypes" })
	public void setFusibles(List fusibles) {
		this.fusibles = fusibles;
	}
	/**
	 * Metodo accesor de trafos.
	 * @return Retorna el trafos.
	 */
	@SuppressWarnings({ "rawtypes" })
	public List getTrafos() {
		return trafos;
	}
	/**
	 * Metodo modificador de trafos.
	 * @param trafos El trafos a modificar.
	 */
	@SuppressWarnings({ "rawtypes"})
	public void setTrafos(List trafos) {
		this.trafos = trafos;
	}
		
	/**
	 * Metodo accesor de codigoPueblo.
	 * @return Retorna el codigoPueblo.
	 */
	public Integer getCodigoPueblo() {
		return codigoPueblo;
	}
	/**
	 * Metodo modificador de codigoPueblo.
	 * @param codigoPueblo El codigoPueblo a modificar.
	 */
	public void setCodigoPueblo(Integer codigoPueblo) {
		this.codigoPueblo = codigoPueblo;
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
	 * Metodo accesor de indicadorSubestacion.
	 * @return Retorna el indicadorSubestacion.
	 */
	public Boolean getIndicadorSubestacion() {
		return indicadorSubestacion;
	}
	/**
	 * Metodo modificador de indicadorSubestacion.
	 * @param indicadorSubestacion El indicadorSubestacion a modificar.
	 */
	public void setIndicadorSubestacion(Boolean indicadorSubestacion) {
		this.indicadorSubestacion = indicadorSubestacion;
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
	 * Metodo accesor de fusibleBO.
	 * @return Retorna el fusibleBO.
	 */
	public FusibleBO getFusibleBO() {
		return fusibleBO;
	}
	/**
	 * Metodo modificador de fusibleBO.
	 * @param fusibleBO El fusibleBO a modificar.
	 */
	public void setFusibleBO(FusibleBO fusibleBO) {
		this.fusibleBO = fusibleBO;
	}
	/**
	 * Metodo accesor de interrupcionGemelaBO.
	 * @return Retorna el interrupcionGemelaBO.
	 */
	public InterrupcionGemelaBO getInterrupcionGemelaBO() {
		return interrupcionGemelaBO;
	}
	/**
	 * Metodo modificador de interrupcionGemelaBO.
	 * @param interrupcionGemelaBO El interrupcionGemelaBO a modificar.
	 */
	public void setInterrupcionGemelaBO(
			InterrupcionGemelaBO interrupcionGemelaBO) {
		this.interrupcionGemelaBO = interrupcionGemelaBO;
	}	
	
	/**
	 * Metodo accesor de puebloBO.
	 * @return Retorna el puebloBO.
	 */
	public PuebloBO getPuebloBO() {
		return puebloBO;
	}
	/**
	 * Metodo modificador de puebloBO.
	 * @param puebloBO El puebloBO a modificar.
	 */
	public void setPuebloBO(PuebloBO puebloBO) {
		this.puebloBO = puebloBO;
	}
	/**
	 * Metodo accesor de vehiculoBO.
	 * @return Retorna el vehiculoBO.
	 */
	public VehiculoBO getVehiculoBO() {
		return vehiculoBO;
	}
	/**
	 * Metodo modificador de vehiculoBO.
	 * @param vehiculoBO El vehiculoBO a modificar.
	 */
	public void setVehiculoBO(VehiculoBO vehiculoBO) {
		this.vehiculoBO = vehiculoBO;
	}
	/**
	 * Metodo accesor de equipoEspecialBO.
	 * @return Retorna el equipoEspecialBO.
	 */
	public EquipoEspecialBO getEquipoEspecialBO() {
		return equipoEspecialBO;
	}
	/**
	 * Metodo modificador de equipoEspecialBO.
	 * @param equipoEspecialBO El equipoEspecialBO a modificar.
	 */
	public void setEquipoEspecialBO(EquipoEspecialBO equipoEspecialBO) {
		this.equipoEspecialBO = equipoEspecialBO;
	}
	/**
	 * Metodo accesor de posteInstaladoRetiradoBO.
	 * @return Retorna el posteInstaladoRetiradoBO.
	 */
	public PosteInstaladoRetiradoBO getPosteInstaladoRetiradoBO() {
		return posteInstaladoRetiradoBO;
	}
	/**
	 * Metodo modificador de posteInstaladoRetiradoBO.
	 * @param posteInstaladoRetiradoBO El posteInstaladoRetiradoBO a modificar.
	 */
	public void setPosteInstaladoRetiradoBO(
			PosteInstaladoRetiradoBO posteInstaladoRetiradoBO) {
		this.posteInstaladoRetiradoBO = posteInstaladoRetiradoBO;
	}
	/**
	 * Metodo accesor de codigoCausa2.
	 * @return Retorna el codigoCausa2.
	 */
	public Integer getCodigoCausa2() {
		return codigoCausa2;
	}
	/**
	 * Metodo modificador de codigoCausa2.
	 * @param codigoCausa2 El codigoCausa2 a modificar.
	 */
	public void setCodigoCausa2(Integer codigoCausa2) {
		this.codigoCausa2 = codigoCausa2;
	}
	
    /**
     * Comment for getCausasSelectItems
     * @return Lista Causas Select Items
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public List getCausasSelectItems(){
    	List selectItems = new ArrayList();
    	List causas = this.causaBO.getCausas();
		selectItems.add(new SelectItem(new Integer(0), "Causa Opcional"));
    	if(causas != null){
	    	for(int i=0; i < causas.size();i++){
	    		Causa causa = (Causa) causas.get(i);
	    		selectItems.add(new SelectItem(causa.getCodigoCausa(), causa.getCodigoCausa() + " - " + causa.getNombreCausa()));
	    	}
    	}
    	return selectItems;	
    }	
	
	/**
	 * Metodo accesor de causaBO.
	 * @return Retorna el causaBO.
	 */
	public CausaBO getCausaBO() {
		return causaBO;
	}
	/**
	 * Metodo modificador de causaBO.
	 * @param causaBO El causaBO a modificar.
	 */
	public void setCausaBO(CausaBO causaBO) {
		this.causaBO = causaBO;
	}
	/**
	 * Metodo accesor de provolmaBO.
	 * @return Retorna el provolmaBO.
	 */
	public ProvolmaBO getProvolmaBO() {
		return provolmaBO;
	}
	/**
	 * Metodo modificador de provolmaBO.
	 * @param provolmaBO El provolmaBO a modificar.
	 */
	public void setProvolmaBO(ProvolmaBO provolmaBO) {
		this.provolmaBO = provolmaBO;
	}
	//AQUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUIIIIIIIIIIIIII
	/**
	 * Metodo que carga los datos de una interrupción deseada por el usuario.  Esto con el fin de modificarla.
	 */
	@SuppressWarnings("rawtypes")
	public void cargarDatos(){		
		this.estructuraSelected = "";
		InterrupcionID interrupcionID = new InterrupcionID();
		String valoresLlave[] = this.getConsecutivoInterrupcion().split("-");    	

		interrupcionID.setAa(new Integer(valoresLlave[0].trim()));
		interrupcionID.setCodigoOficina(new Integer(valoresLlave[1].trim()));
		interrupcionID.setNumeroInterrupcion(new Long(valoresLlave[2].trim()));		
		this.interrupcion = interrupcionBO.getInterrupcion(interrupcionID);
		
		this.clientesAfectados = interrupcion.getAbonadoAfectado();
		this.cantidadCable = interrupcion.getAcometida();
		if(interrupcion.getBitacora().intValue() == 1)
			this.bitacora = Boolean.valueOf(true);
		else
			this.bitacora = Boolean.valueOf(false);
		this.codigoCausa = interrupcion.getCausa1();
		this.codigoCausa2 = interrupcion.getCausa2();

		this.codigoAgencia = interrupcion.getCodigoAgencia();
		this.codigoAnimal = interrupcion.getCodigoAnimal();
		if(this.codigoAnimal != null){
			Animal animal = this.animalBO.buscar(this.codigoAnimal);
			this.nombreAnimal = animal.getNombreAnimal();
		}
		this.codigoCalle = interrupcion.getCodigoCalle();
		this.codigoCalleEquipo = interrupcion.getCodigoCalleEquipo();
		this.codigoDano = interrupcion.getCodigoDano();
		this.codigoMaterial = interrupcion.getCodigoMaterial();
		this.codigoProteccion = interrupcion.getCodigoProteccion();
		
		this.codigoIntervenido = interrupcion.getCodigoIntervenido();
		
		if(this.codigoIntervenido == null || this.codigoIntervenido == 0)
		{
			this.codigoIntervenido = null;
		}
		
		Pueblo p = new Pueblo();
        if(interrupcion.getCodigoPueblo() != null && interrupcion.getCodigoPueblo().intValue() > 0){
            p.setNumero(interrupcion.getCodigoPueblo());
            this.pueblo = this.obtenerPueblo(p);      
        }
        else{
            this.pueblo = new Pueblo();
            this.pueblo.setNumero(null);
        }

        if(interrupcion.getCodigoPuebloEquipo() != null && interrupcion.getCodigoPuebloEquipo().intValue() > 0){
            p.setNumero(interrupcion.getCodigoPuebloEquipo());
            this.puebloEquipo = this.obtenerPueblo(p);  
        }
        else{
            this.puebloEquipo = new Pueblo();
            this.puebloEquipo.setNumero(null);       
        }
	
		
		this.observaciones = interrupcion.getComentario();
		
		this.region = this.regionBO.buscar(interrupcion.getRegion());
	    SubRegionID subID = new SubRegionID();
	    Region region = new Region();
	    region.setRegion(interrupcion.getRegion());
	    subID.setRegion(region);
	    subID.setSubRegion(interrupcion.getSubRegion());		
		this.subRegion = this.subRegionBO.buscar(subID);
		this.nombreRegion = this.region.getNombreRegion();
		this.nombreSubregion = this.subRegion.getNombreSubRegion();   		
		
		if(interrupcion.getFaseR() == null || interrupcion.getFaseR().length() == 0)
			this.faseR = null;
		else
			this.faseR = Boolean.valueOf(true);
		if(interrupcion.getFaseS() == null || interrupcion.getFaseS().length() == 0)
			this.faseS = null;
		else
			this.faseS = Boolean.valueOf(true);			
		if(interrupcion.getFaseT() == null || interrupcion.getFaseT().length() == 0)
			this.faseT = null;
		else
			this.faseT = Boolean.valueOf(true);
		
		this.fechaFinalInterrupcion = interrupcion.getFechaFin();
		this.fechaInicioInterrupcion = interrupcion.getFechaInicio();
		this.fechaRecepcionAviso = interrupcion.getFechaAviso();
		this.horasAbonado = interrupcion.getHorasAbonado();
		this.saleCircuito = interrupcion.getIndCircuito();
		if(this.saleCircuito == null){
			this.saleTotal = Boolean.valueOf(true);
			this.saleParcial = Boolean.valueOf(false);
		}
		else{
			if(this.saleCircuito.intValue() == 1){
				this.saleParcial = Boolean.valueOf(true);
				this.saleTotal = Boolean.valueOf(false);
			}
			else{
				this.saleTotal = Boolean.valueOf(false);
				this.saleParcial = Boolean.valueOf(false);
			}
		}
		if(interrupcion.getIndSubEstacion().intValue() == 1)
			this.indicadorSubestacion = Boolean.valueOf(true);
		else
			this.indicadorSubestacion = Boolean.valueOf(false);
		this.lugarInterrupcion = interrupcion.getLugar();
		this.medidor = interrupcion.getMedidor();
		this.cliente = interrupcion.getNombreCliente();
		if(interrupcion.getOperadoPor() != null && interrupcion.getOperadoPor().intValue() == 0){
			this.scada = Boolean.valueOf(true);
			this.operadorScada = interrupcion.getOperadorScada();
			this.manual = Boolean.valueOf(false);
		}
		else{
			this.scada = Boolean.valueOf(false);
			this.operadorScada = interrupcion.getOperadorScada();
			this.manual = Boolean.valueOf(true);
		}
		this.cedula = interrupcion.getOperador();
		
        this.empleado = this.empleadoBO.buscar(this.cedula);
        this.operador = this.empleado.getNombreCompleto();	
        
        
        Oficina oficinaInterup = this.oficinaBO.buscar(this.codigoOficina);
        this.oficina = oficinaInterup.getNombreOficina();
		
		this.poste = interrupcion.getPoste();
		this.posteEquipo = interrupcion.getPosteEquipo();
		SeccionID seccionID = new SeccionID();
		seccionID.setCircuito(interrupcion.getCircuito());
		seccionID.setSeccion(interrupcion.getSeccion());
		seccionID.setSubEstacion(interrupcion.getSubEstacion());
        
        this.subestacion = interrupcion.getSubEstacion();
        this.circuito = interrupcion.getCircuito();
        this.seccion = interrupcion.getSeccion();
        
		this.secuencia = interrupcion.getSecuencia();
		this.secuenciaEquipo = interrupcion.getSecuenciaEquipo();
		if(interrupcion.getTelefonoCliente() != null)
		    this.telefono = new String(interrupcion.getTelefonoCliente());
		this.tipoCable = interrupcion.getTipoAcometida();
		if(interrupcion.getTrifasica().intValue() == 1)
			this.trifasica = Boolean.valueOf(true);
		else
			this.trifasica = Boolean.valueOf(false);
		
		this.tipoVoltaje = new TipoVoltaje();
		
		
		TipoVoltajeID tipoVoltajeID = new TipoVoltajeID();
		tipoVoltajeID.setCodigoVoltaje(interrupcion.getCodigoVoltaje());
		tipoVoltajeID.setTipoVoltaje(interrupcion.getTipoVoltaje());
		this.tipoVoltaje = new TipoVoltaje();
		this.tipoVoltaje.setTipoVoltajeID(tipoVoltajeID);
		
		
		this.cuadrillaID = new CuadrillaID();
		this.cuadrillaID.setAa(interrupcion.getInterrupcionID().getAa());
		this.cuadrillaID.setCodigoOficina(interrupcion.getInterrupcionID().getCodigoOficina());
		this.cuadrillaID.setNumeroInterrupcion(interrupcion.getInterrupcionID().getNumeroInterrupcion());
		this.cuadrillaID.setReporteInterrupcion(new Integer(1));		
		
		this.empleadosCuadrilla = this.cuadrillaBO.getCuadrillas(cuadrillaID);
		if(this.empleadosCuadrilla == null || this.empleadosCuadrilla.size() == 0){
			this.empleadosCuadrilla = new ArrayList();		
            this.irCuadrillaVehiculo = Boolean.valueOf(false);
		}
		else
		    this.irCuadrillaVehiculo = Boolean.valueOf(true);
		
		this.vehiculoID = null;
		VehiculoID vehiculoID = new VehiculoID();
		Vehiculo vehiculo = new Vehiculo();
		vehiculoID.setAa(interrupcion.getInterrupcionID().getAa());
		vehiculoID.setCodigoOficina(interrupcion.getInterrupcionID().getCodigoOficina());
		vehiculoID.setNumeroInterrupcion(interrupcion.getInterrupcionID().getNumeroInterrupcion());
		vehiculoID.setTipo(new Integer(0));
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
			this.vehiculoID.setTipo(new Integer(0));
			this.vehiculoID.setNumeroVehiculo(this.vehiculo.getVehiculoID().getNumeroVehiculo());
			this.vehiculoIDViejo = this.vehiculoID;
		}
		
		this.fusibleID = new FusibleID();
		this.fusibleID.setAa(interrupcion.getInterrupcionID().getAa());
		this.fusibleID.setCodigoOficina(interrupcion.getInterrupcionID().getCodigoOficina());
		this.fusibleID.setNumeroInterrupcion(interrupcion.getInterrupcionID().getNumeroInterrupcion());			
		this.fusibles = this.fusibleBO.getFusibles(this.fusibleID);
	//	this.listaFusibles = fusibleBO.getFusibles(this.fusibleID);
		if(this.fusibles == null){			
			this.fusibles = new ArrayList();
		}
		
		this.trafoID = new TrafoID();
		this.trafoID.setAa(interrupcion.getInterrupcionID().getAa());
		this.trafoID.setCodigoOficina(interrupcion.getInterrupcionID().getCodigoOficina());
		this.trafoID.setNumeroInterrupcion(interrupcion.getInterrupcionID().getNumeroInterrupcion());
		
		this.trafos = this.trafoBO.getTrafos(this.trafoID);
		if(this.trafos == null){			
			this.trafos = new ArrayList();
		}
		
		this.posteInstaladoRetiradoID = new PosteInstaladoRetiradoID();
		this.posteInstaladoRetiradoID.setAno(interrupcion.getInterrupcionID().getAa());
		this.posteInstaladoRetiradoID.setCodigoOficina(interrupcion.getInterrupcionID().getCodigoOficina());
		this.posteInstaladoRetiradoID.setNumeroInterrupcion(interrupcion.getInterrupcionID().getNumeroInterrupcion());
		
		this.postes = this.posteInstaladoRetiradoBO.getPosteInstaladoRetirado(this.posteInstaladoRetiradoID);
		if(this.postes == null){			
			this.postes = new ArrayList();
		}
		
		this.equipoEspecialID = new EquipoEspecialID();
		this.equipoEspecialID.setAno(interrupcion.getInterrupcionID().getAa());
		this.equipoEspecialID.setCodigoOficina(interrupcion.getInterrupcionID().getCodigoOficina());
		this.equipoEspecialID.setNumeroInterrupcion(interrupcion.getInterrupcionID().getNumeroInterrupcion());
		
		this.equipos = this.equipoEspecialBO.getEquiposEspeciales(this.equipoEspecialID);
		if(this.equipos == null){			
			this.equipos = new ArrayList();
		}
		
		this.interrupcionGemelaID = new InterrupcionGemelaID();
		Interrupcion interup = new Interrupcion(); 
		InterrupcionID interupID = new InterrupcionID();
		interupID.setAa(interrupcion.getInterrupcionID().getAa());
		interupID.setCodigoOficina(interrupcion.getInterrupcionID().getCodigoOficina());
		interupID.setNumeroInterrupcion(interrupcion.getInterrupcionID().getNumeroInterrupcion());
		interup.setInterrupcionID(interupID);
		this.interrupcionGemelaID.setInterrupcion(interup);		
		
		this.interrupcionesGemelas = this.interrupcionGemelaBO.getInterrupcionesGemelas(this.interrupcionGemelaID);
		this.abonadosGemelas = new ArrayList();
		this.acumuladorGemelas = 0;
		if(this.interrupcionesGemelas == null || this.interrupcionesGemelas.size() == 0){			
			this.interrupcionesGemelas = new ArrayList();
			this.gemelas = Boolean.valueOf(false);
		}
		else{		
			this.gemelas = Boolean.valueOf(true);
			for(int i = 0; i < interrupcionesGemelas.size(); i++){
				InterrupcionGemela ig = (InterrupcionGemela)interrupcionesGemelas.get(i);
				this.abonadosGemelas.add(ig.getAbonadoAfectado());
				this.acumuladorGemelas = this.acumuladorGemelas + ig.getAbonadoAfectado().longValue();
			}
			darFormatoFecha();
		}
		this.numeroMovimientoFusible = new Integer(this.fusibles.size() + 1);
		this.numeroMovimientoPoste = new Integer(this.postes.size() + 1);
		this.numeroMovimientoTrafo = new Integer(this.trafos.size() + 1);
		this.numeroMovimientoGemela = new Integer(this.interrupcionesGemelas.size() + 1);
		
		NoPropiaSeccionamientoID noPropiaSeccionamientoID = new NoPropiaSeccionamientoID();				
		noPropiaSeccionamientoID.setAa(interrupcion.getInterrupcionID().getAa());
		noPropiaSeccionamientoID.setCodigoOficina(interrupcion.getInterrupcionID().getCodigoOficina());
		noPropiaSeccionamientoID.setNumeroInterrupcion(interrupcion.getInterrupcionID().getNumeroInterrupcion());
		this.seccionamientos = this.noPropiaSeccionamientoBO.getNoPropiaSeccionamientos(noPropiaSeccionamientoID);		
		FacesContext ctx = FacesContext.getCurrentInstance();		
		ctx.getExternalContext().getSessionMap().put("seccionamientos",this.seccionamientos);
		reasignarNombreSeccionamientos();
		darFormatoFechaSeccionamientos();
		/*********************************************/
		
		double horasInicio = interrupcion.getHoraInicio().doubleValue();
		double horasFinal = interrupcion.getHoraFin().doubleValue();
		double horasAviso = interrupcion.getHoraAviso().doubleValue();
        
		Double hora = null;
		Double minutos = null;
		GregorianCalendar gc = null;
		
		gc = new GregorianCalendar();
		gc.setTime(interrupcion.getFechaInicio());
		hora = new Double(horasInicio);
		gc.set(GregorianCalendar.HOUR_OF_DAY,hora.intValue());
		if(horasInicio != 0){
			double min = horasInicio - hora.intValue();
			minutos = new Double(min * 100);			
		}
		else
			minutos = new Double(0);
		
		long minute = Math.round(minutos.doubleValue());
		minutos = new Double(minute);
		gc.set(GregorianCalendar.MINUTE,minutos.intValue());			
		this.fechaInicioInterrupcion = gc.getTime();
		
		gc = new GregorianCalendar();
		gc.setTime(interrupcion.getFechaFin());
		hora = new Double(horasFinal);
		gc.set(GregorianCalendar.HOUR_OF_DAY,hora.intValue());
		if(horasFinal != 0){
			double min = horasFinal - hora.intValue();
            minutos = new Double(min * 100);
		}
		else
			minutos = new Double(0);
		minute = Math.round(minutos.doubleValue());
		minutos = new Double(minute);
		gc.set(GregorianCalendar.MINUTE,minutos.intValue());			
		this.fechaFinalInterrupcion = gc.getTime();
		
		gc = new GregorianCalendar();
		gc.setTime(interrupcion.getFechaAviso());
		hora = new Double(horasAviso);
		gc.set(GregorianCalendar.HOUR_OF_DAY,hora.intValue());
		if(horasAviso != 0){
			double min = horasAviso - hora.intValue();
			minutos = new Double(min * 100);
		}
		else
			minutos = new Double(0);
		minute = Math.round(minutos.doubleValue());
		minutos = new Double(minute);
		gc.set(GregorianCalendar.MINUTE,minutos.intValue());			
		this.fechaRecepcionAviso = gc.getTime();		
		
		
	}
	
	
	private void darFormatoFechaSeccionamientos(){
	    
		double horas = 0;		
		Double minutos;
		Double hora;
		GregorianCalendar gc;	    
	    ArrayList resultado = new ArrayList();
		if(seccionamientos != null){
			for(int i = 0; i < seccionamientos.size(); i++){
						/********************************************************************************/
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void darFormatoFecha(){
		double horas = 0;		
		Double minutos;
		Double hora;
		GregorianCalendar gc;
		List resultado = new ArrayList();	
	    for(int i = 0; i < interrupcionesGemelas.size(); i++){
			InterrupcionGemela ig = (InterrupcionGemela)interrupcionesGemelas.get(i);
	        
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
	    this.interrupcionesGemelas = new ArrayList(resultado);
	}
	@SuppressWarnings({ "rawtypes" })
	private void limpiarDatos(){
		this.cliente = "";
		this.medidor = null;
		this.telefonoCliente = "";
		this.telefono = null;
		GregorianCalendar fecha = new GregorianCalendar();
		this.fechaInicioInterrupcion = fecha.getTime();
		this.fechaFinalInterrupcion = this.fechaInicioInterrupcion;
		this.fechaRecepcionAviso = this.fechaInicioInterrupcion;
		this.clientesAfectados = null;		

		this.operadorScada = null;
		scada = Boolean.valueOf(false);
		manual = Boolean.valueOf(true);
		
		trafoInstalado = Boolean.valueOf(false);
		trafoRetirado = Boolean.valueOf(false);
		this.numeroTrafo = new Long(0);
		this.serieTrafo = "";
		this.empleadoCuadrilla = new Empleado();
		this.empleadoCuadrilla.setCedula(new Long(0));
		this.empleadoCuadrilla.setNombreEmpleado(" ");
		this.cuadrilla = new Cuadrilla();
		this.vehiculo = new Vehiculo();
		this.vehiculo.setVehiculoID(new VehiculoID());
		this.fusible = new Fusible();
		this.fusible.setFusibleID(new FusibleID());		
				
		this.pueblo = new Pueblo();
		this.puebloEquipo = new Pueblo();
		this.pueblo.setNombre("");
		this.puebloEquipo.setNombre("");
		this.observaciones = "";
		this.codigoAnimal = null;		
		this.bitacora = new Boolean (false);
		this.trifasica = new Boolean (false);
		this.indicadorSubestacion = new Boolean (false);
		this.faseR = Boolean.valueOf(false);
		this.faseS = Boolean.valueOf(false);
		this.faseT = Boolean.valueOf(false);
		
		this.cantidadCable = new Integer(0);
		this.tipoCable = new Integer(0);
		this.numeroEquipo = null;
		this.serieEquipo = null;
		this.longitudPoste = null;		
		
		this.empleadoMiembro = Boolean.valueOf(false);
		this.empleadoResponsable = Boolean.valueOf(false);
		this.numeroTrafo = null;
		this.serieTrafo = null;
		
		this.poste = null;
		this.codigoCalle = null;
		this.secuencia = null;		
		
		this.codigoCalleEquipo = null;
		this.posteEquipo = null;
		this.secuenciaEquipo = null;
		
		this.saleTotal = null;
		this.saleParcial = null;
		this.gemelas = null;
		this.irCuadrillaVehiculo = null;
		
		
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
		this.codigoIntervenido = null;
		this.seccionamientos = null;
		FacesContext ctx = FacesContext.getCurrentInstance();		
		ctx.getExternalContext().getSessionMap().put("seccionamientos",null);
		
		this.subestacion = null;
		this.circuito = null;
		this.seccion = null;
		indAgencia = false;
		accionProvolma = "";
		accionGuardar = "";
		
		this.nombreAnimal = "";
		this.postes = new ArrayList();
		this.equipos = new ArrayList();
		//this.listaFusibles = new ArrayList<Fusible>();
		this.horasAbonado = null;
		
		this.indTension = Boolean.FALSE;
	
	}
	/**
	 * Metodo accesor de codigoCalle.
	 * @return Retorna el codigoCalle.
	 */
	public Integer getCodigoCalle() {
		return codigoCalle;
	}
	/**
	 * Metodo modificador de codigoCalle.
	 * @param codigoCalle El codigoCalle a modificar.
	 */
	public void setCodigoCalle(Integer codigoCalle) {
		this.codigoCalle = codigoCalle;
	}

	/**
	 * Metodo accesor de poste.
	 * @return Retorna el poste.
	 */
	public Integer getPoste() {
		return poste;
	}
	/**
	 * Metodo modificador de poste.
	 * @param poste El poste a modificar.
	 */
	public void setPoste(Integer poste) {
		this.poste = poste;
	}
	/**
	 * Metodo accesor de secuencia.
	 * @return Retorna el secuencia.
	 */
	public Integer getSecuencia() {
		return secuencia;
	}
	/**
	 * Metodo modificador de secuencia.
	 * @param secuencia El secuencia a modificar.
	 */
	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}		
	
	/**
	 * Metodo accesor de saleParcial.
	 * @return Retorna el saleParcial.
	 */
	public Boolean getSaleParcial() {
		return saleParcial;
	}
	/**
	 * Metodo modificador de saleParcial.
	 * @param saleParcial El saleParcial a modificar.
	 */
	public void setSaleParcial(Boolean saleParcial) {
		this.saleParcial = saleParcial;
	}
	/**
	 * Metodo accesor de saleTotal.
	 * @return Retorna el saleTotal.
	 */
	public Boolean getSaleTotal() {
		return saleTotal;
	}
	/**
	 * Metodo modificador de saleTotal.
	 * @param saleTotal El saleTotal a modificar.
	 */
	public void setSaleTotal(Boolean saleTotal) {
		this.saleTotal = saleTotal;
	}
	/**
	 * Metodo accesor de codigoCalleEquipo.
	 * @return Retorna el codigoCalleEquipo.
	 */
	public Integer getCodigoCalleEquipo() {
		return codigoCalleEquipo;
	}
	/**
	 * Metodo modificador de codigoCalleEquipo.
	 * @param codigoCalleEquipo El codigoCalleEquipo a modificar.
	 */
	public void setCodigoCalleEquipo(Integer codigoCalleEquipo) {
		this.codigoCalleEquipo = codigoCalleEquipo;
	}
	/**
	 * Metodo accesor de posteEquipo.
	 * @return Retorna el posteEquipo.
	 */
	public Integer getPosteEquipo() {
		return posteEquipo;
	}
	/**
	 * Metodo modificador de posteEquipo.
	 * @param posteEquipo El posteEquipo a modificar.
	 */
	public void setPosteEquipo(Integer posteEquipo) {
		this.posteEquipo = posteEquipo;
	}
	/**
	 * Metodo accesor de secuenciaEquipo.
	 * @return Retorna el secuenciaEquipo.
	 */
	public Integer getSecuenciaEquipo() {
		return secuenciaEquipo;
	}
	/**
	 * Metodo modificador de secuenciaEquipo.
	 * @param secuenciaEquipo El secuenciaEquipo a modificar.
	 */
	public void setSecuenciaEquipo(Integer secuenciaEquipo) {
		this.secuenciaEquipo = secuenciaEquipo;
	}
	/**
	 * Metodo accesor de pueblo.
	 * @return Retorna el pueblo.
	 */
	public Pueblo getPueblo() {		
		return pueblo;
	}
	/**
	 * Metodo modificador de pueblo.
	 * @param pueblo El pueblo a modificar.
	 */
	public void setPueblo(Pueblo pueblo) {
		this.pueblo = pueblo;		
	}
	/**
	 * Metodo accesor de puebloEquipo.
	 * @return Retorna el puebloEquipo.
	 */
	public Pueblo getPuebloEquipo() {
		return puebloEquipo;
	}
	/**
	 * Metodo modificador de puebloEquipo.
	 * @param puebloEquipo El puebloEquipo a modificar.
	 */
	public void setPuebloEquipo(Pueblo puebloEquipo) {
		this.puebloEquipo = puebloEquipo;
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
	 * Metodo accesor de gemelas.
	 * @return Retorna el gemelas.
	 */
	public Boolean getGemelas() {
		return gemelas;
	}
	/**
	 * Metodo modificador de gemelas.
	 * @param gemelas Las gemelas a modificar.
	 */
	public void setGemelas(Boolean gemelas) {
		this.gemelas = gemelas;
	}
	
    /**
     * Realiza la accion de ir al ingreso de gemelas
     * @return error o success
     */
	public String irGemelas(){
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
		
		if(this.gemelas.booleanValue() == false){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No puede agregar gemelas ya que no se indicó que existen."));
			return "error";
		}
        

        if(existenCambiosGemelasEnFechas()){
            String mensaje = "A las gemelas se les modificó sus rangos de fechas debido a que las fechas de la interrupción madre fueron modificadas";
            this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";                             
        }    
        
		this.fechaInicioGemela = this.fechaInicioInterrupcion;
		this.fechaFinalGemela = this.fechaFinalInterrupcion;
		this.abonadosAfectadosGemela = this.clientesAfectados;
		this.saldoGemelas = this.clientesAfectados;
		return "success";
	}
    
    /**
     * Determina si se produjeron cambios en las fechas de la interrupción madre
     * @return <code>true</code> o <code>false</code> si se produjeron cambios o no
     */ 
	@SuppressWarnings({ "rawtypes", "unchecked" })
    private boolean existenCambiosGemelasEnFechas(){        
        InterrupcionGemela gemela = null;
        List resultadoList = new ArrayList();
        boolean cambios = false;
        if(this.interrupcionesGemelas != null && this.interrupcionesGemelas.size() > 0){
            for(int i = 0; i < this.interrupcionesGemelas.size(); i++){
                gemela = (InterrupcionGemela)this.interrupcionesGemelas.get(i);
                double horasInicio = Fechas.millisToMinutes(this.fechaFinalInterrupcion.getTime());
                double horasFinal = Fechas.millisToMinutes(gemela.getFechaFin().getTime());
                double diferencia = horasFinal - horasInicio;                               
                if(gemela.getFechaInicio().equals(this.fechaInicioInterrupcion) != true || diferencia > 0){                 
                    gemela.setFechaInicio(this.fechaInicioInterrupcion);                    
                    cambios = true;
                }
                resultadoList.add(gemela);
               
            }
        }
        if(cambios){
            this.interrupcionesGemelas = new ArrayList();
            for(int i = 0; i < resultadoList.size(); i++){
                gemela = (InterrupcionGemela)resultadoList.get(i);
                
                SimpleDateFormat formater = new SimpleDateFormat("HH.mm");
                String hora = formater.format(gemela.getFechaInicio());
                double resultado = Double.parseDouble(hora.substring(0,2));
                double min = Double.parseDouble(hora.substring(3,5));
                min = min / 100.0;
                resultado = resultado + min;                            
                gemela.setHoraInicio(new Double(resultado));                                
                                
                double horasInicio = Fechas.millisToMinutes(gemela.getFechaInicio().getTime());
                double horasFinal = Fechas.millisToMinutes(gemela.getFechaFin().getTime());
                double diferencia = (horasFinal - horasInicio) / 60.0;

                diferencia = UtilidadesI.roundNum(diferencia);
                gemela.setTiefue(new Double(diferencia));
                double horasAbonado = diferencia * gemela.getAbonadoAfectado().longValue();

                horasAbonado = UtilidadesI.roundNum(horasAbonado);
                gemela.setHorasAbonado(new Double(horasAbonado));   
                
                
                
                this.interrupcionesGemelas.add(gemela);
            }  
        }else{
            this.interrupcionesGemelas = new ArrayList(resultadoList); 
        }

        return cambios;
    }    
	
	
	private boolean existenCambiosGemelas(){		    
		if(this.interrupcionesGemelas != null && this.interrupcionesGemelas.size() > 0 && this.acumuladorGemelas != this.clientesAfectados.longValue()){					        
			return true;
		}
		if(this.interrupcionesGemelas != null && this.interrupcionesGemelas.size() > 0 && this.esTiempoTotalGemela() == false){
		    return true;
		}			
	    return false;
	}
	
    /**
     * Actualiza los datos de la lista de gemelas
     * @return error o success
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String actualizarDatosTablaGemelas(){
	    
        this.accionProvolma = "";
        this.nuevoFoco = "form1:voltaje";
       
        
        int filas = this.dataTableGemelas.getRowCount();
        List lista = new ArrayList();
        long acumuladorAbonados = 0;
        
        for(int i = 0; i < filas; i++){
            int fila = i + 1;
            this.dataTableGemelas.setRowIndex(i);   
            InterrupcionGemela gemela = (InterrupcionGemela)this.dataTableGemelas.getRowData();
            if(gemela.getFechaFin() == null){
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error en la fila " + fila + ".  La fecha de fin es requerida"));
                return "error";
            }     
            if(gemela.getAbonadoAfectado() == null || gemela.getAbonadoAfectado().longValue() <= 0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error en la fila " + fila + ".  La cantidad de abonados afectados es requerida"));
                return "error";
            }   

            int horasInicio = Fechas.millisToMinutes(this.fechaInicioInterrupcion.getTime());
            int horasFinal = Fechas.millisToMinutes(this.fechaFinalInterrupcion.getTime());
            int horasInicioGemela = Fechas.millisToMinutes(gemela.getFechaInicio().getTime());
            int horasFinalGemela = Fechas.millisToMinutes(gemela.getFechaFin().getTime());     

            double b = horasFinalGemela - horasInicioGemela;
            
            if(b < 0){
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error en la fila " + fila + ".  La fecha de inicio debe ser menor a la fecha de fin"));

                return "error";
            }   
            if(!(horasInicioGemela >= horasInicio && horasInicioGemela <= horasFinal)){
               
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error en la fila " + fila + ".  La fecha de inicio se encuentra fuera del rango de la interrupción madre"));
                         
                return "error";
            }   
            if(!(horasFinalGemela >= horasInicio && horasFinalGemela <= horasFinal)){
                           
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error en la fila " + fila + ".  La fecha de fin se encuentra fuera del rango de la interrupción madre"));
                return "error";
            }           
            
            acumuladorAbonados = acumuladorAbonados + gemela.getAbonadoAfectado().longValue();
            
        }
        
        this.acumuladorGemelas = acumuladorAbonados;
        if( this.acumuladorGemelas > this.clientesAfectados.longValue()){
                          
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La sumatoria total de abonados afectados es de: " + this.acumuladorGemelas + ", y no puede superar la cantidad de abonados afectados de la interrupción madre de: " + this.clientesAfectados));
            return "error";
        }            
        
        this.abonadosGemelas = new ArrayList();
        for(int i = 0; i < filas; i++){
            this.dataTableGemelas.setRowIndex(i);
            InterrupcionGemela gemela = (InterrupcionGemela)this.dataTableGemelas.getRowData();
            
            
            SimpleDateFormat formater = new SimpleDateFormat("HH.mm");
            String hora = formater.format(gemela.getFechaFin());
            double resultado = Double.parseDouble(hora.substring(0,2));
            double min = Double.parseDouble(hora.substring(3,5));
            min = min / 100.0;
            resultado = resultado + min;                            
            gemela.setHoraFin(new Double(resultado));
                            
            double horasInicio = Fechas.millisToMinutes(gemela.getFechaInicio().getTime());
            double horasFinal = Fechas.millisToMinutes(gemela.getFechaFin().getTime());
            double diferencia = (horasFinal - horasInicio) / 60.0;

            diferencia = UtilidadesI.roundNum(diferencia);
            gemela.setTiefue(new Double(diferencia));
            double horasAbonado = diferencia * gemela.getAbonadoAfectado().longValue();

            horasAbonado = UtilidadesI.roundNum(horasAbonado);
            gemela.setHorasAbonado(new Double(horasAbonado));           
            
            lista.add(gemela);
            this.abonadosGemelas.add(gemela.getAbonadoAfectado());
        }     
        this.interrupcionesGemelas = null;
        this.interrupcionesGemelas = new ArrayList(lista);
        
         
        this.abonadosAfectadosGemela = new Long(this.clientesAfectados.longValue() - this.acumuladorGemelas);        
        return "success";
    }
	
    /**
     * Cancela el mantenimiento de interrupciones gemelas
     * @return success
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public String cancelarGemelas(){
        this.accionProvolma = "";
        this.nuevoFoco = "form1:voltaje";
        if(isModificar() == false){
            this.interrupcionesGemelas = new ArrayList();
            this.abonadosGemelas = new ArrayList();
            this.acumuladorGemelas = 0;
            this.gemelas = null;        
            this.numeroMovimientoGemela = new Integer(1);
            this.gemelas = Boolean.valueOf(false);
        }else{
            this.interrupcionGemelaID = new InterrupcionGemelaID();
            Interrupcion interup = new Interrupcion(); 
            InterrupcionID interupID = new InterrupcionID();
            interupID.setAa(interrupcion.getInterrupcionID().getAa());
            interupID.setCodigoOficina(interrupcion.getInterrupcionID().getCodigoOficina());
            interupID.setNumeroInterrupcion(interrupcion.getInterrupcionID().getNumeroInterrupcion());
            interup.setInterrupcionID(interupID);
            this.interrupcionGemelaID.setInterrupcion(interup);
            
            this.interrupcionesGemelas = this.interrupcionGemelaBO.getInterrupcionesGemelas(this.interrupcionGemelaID);
            this.abonadosGemelas = new ArrayList();
            this.acumuladorGemelas = 0;
            if(this.interrupcionesGemelas == null || this.interrupcionesGemelas.size() == 0){           
                this.interrupcionesGemelas = new ArrayList();
                this.gemelas = Boolean.valueOf(false);
            }
            else{       
                this.gemelas = Boolean.valueOf(true);
                for(int i = 0; i < interrupcionesGemelas.size(); i++){
                    InterrupcionGemela ig = (InterrupcionGemela)interrupcionesGemelas.get(i);
                    this.abonadosGemelas.add(ig.getAbonadoAfectado());
                    this.acumuladorGemelas = this.acumuladorGemelas + ig.getAbonadoAfectado().longValue();
                }
                darFormatoFecha();
            }

            this.numeroMovimientoGemela = new Integer(this.interrupcionesGemelas.size() + 1);
        }
        return "success";
    }
    
    /**
     * Cancela el mantenimiento de fusibles y trafos
     * @return success
     */
	@SuppressWarnings({ "rawtypes" })
    public String cancelarFusiblesTrafos(){
        this.accionProvolma = "";
        this.nuevoFoco = "form1:voltaje";
        if(isModificar() == false){
            this.fusible = new Fusible();
            this.fusible.setFusibleID(new FusibleID());
            this.fusibles = new ArrayList();
            this.trafos = new ArrayList();
            this.numeroMovimientoFusible = new Integer(this.fusibles.size() + 1);
            this.numeroMovimientoTrafo = new Integer(this.trafos.size() + 1);
            this.numeroTrafo = new Long(0);
            this.serieTrafo = "";
            this.trafoInstalado = Boolean.valueOf(false);
            this.trafoRetirado = Boolean.valueOf(false);
        }else{
            this.fusibleID = new FusibleID();
            this.fusibleID.setAa(interrupcion.getInterrupcionID().getAa());
            this.fusibleID.setCodigoOficina(interrupcion.getInterrupcionID().getCodigoOficina());
            this.fusibleID.setNumeroInterrupcion(interrupcion.getInterrupcionID().getNumeroInterrupcion());         
            this.fusibles = this.fusibleBO.getFusibles(this.fusibleID);
            if(this.fusibles == null){          
                this.fusibles = new ArrayList();
            }
            
            this.trafoID = new TrafoID();
            this.trafoID.setAa(interrupcion.getInterrupcionID().getAa());
            this.trafoID.setCodigoOficina(interrupcion.getInterrupcionID().getCodigoOficina());
            this.trafoID.setNumeroInterrupcion(interrupcion.getInterrupcionID().getNumeroInterrupcion());
            
            this.trafos = this.trafoBO.getTrafos(this.trafoID);
            if(this.trafos == null){            
                this.trafos = new ArrayList();
            }
            
            this.numeroMovimientoFusible = new Integer(this.fusibles.size() + 1);
            this.numeroMovimientoTrafo = new Integer(this.trafos.size() + 1);
        }
        return "success";
    }
    
    /**
     * Cancela el mantenimiento de cuadrilla y vehiculo
     * @return success
     */
	@SuppressWarnings({ "rawtypes" })
    public String cancelarCuadrillaVehiculo(){
        
        this.accionProvolma = "";
        this.nuevoFoco = "form1:voltaje";
        if(isModificar() == false){
            
            this.empleadoMiembro = Boolean.valueOf(false);
            this.empleadoResponsable = Boolean.valueOf(false);
            this.cuadrillaID = new CuadrillaID();
            this.empleadosCuadrilla = new ArrayList();   
            this.irCuadrillaVehiculo = Boolean.valueOf(false);
            this.vehiculo = new Vehiculo();
            this.vehiculo.setVehiculoID(new VehiculoID());
            this.vehiculoIDViejo = null;
            
        }else{
            this.cuadrillaID = new CuadrillaID();
            this.cuadrillaID.setAa(interrupcion.getInterrupcionID().getAa());
            this.cuadrillaID.setCodigoOficina(interrupcion.getInterrupcionID().getCodigoOficina());
            this.cuadrillaID.setNumeroInterrupcion(interrupcion.getInterrupcionID().getNumeroInterrupcion());
            this.cuadrillaID.setReporteInterrupcion(new Integer(1));        
            
            this.empleadosCuadrilla = this.cuadrillaBO.getCuadrillas(cuadrillaID);
            if(this.empleadosCuadrilla == null || this.empleadosCuadrilla.size() == 0){
                this.empleadosCuadrilla = new ArrayList();   
                this.irCuadrillaVehiculo = Boolean.valueOf(false);
            }
            else
                this.irCuadrillaVehiculo = Boolean.valueOf(true);
            
            this.vehiculoID = null;
            VehiculoID vehiculoID = new VehiculoID();
            Vehiculo vehiculo = new Vehiculo();
            vehiculoID.setAa(interrupcion.getInterrupcionID().getAa());
            vehiculoID.setCodigoOficina(interrupcion.getInterrupcionID().getCodigoOficina());
            vehiculoID.setNumeroInterrupcion(interrupcion.getInterrupcionID().getNumeroInterrupcion());
            vehiculoID.setTipo(new Integer(0));
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
                this.vehiculoID.setTipo(new Integer(0));
                this.vehiculoID.setNumeroVehiculo(this.vehiculo.getVehiculoID().getNumeroVehiculo());
                this.vehiculoIDViejo = this.vehiculoID;
            }
        }
        return "success";
    }
    
    /**
     * Cancela el mantenimiento de postes
     * @return success
     */
    
    public String cancelarPostes(){
        
        this.accionProvolma = "";
        this.nuevoFoco = "form1:voltaje";
        if(isModificar() == false){
            
        	this.postes = new ArrayList();
        	this.numeroMovimientoPoste = new Integer(this.postes.size() + 1);
        	this.longitudPoste = null;	
        	this.tipoPoste = null;	
        	this.posteInstalado = Boolean.valueOf(false);
        	this.posteRetirado = Boolean.valueOf(false);
            
        }else{
    		this.posteInstaladoRetiradoID = new PosteInstaladoRetiradoID();
    		this.posteInstaladoRetiradoID.setAno(interrupcion.getInterrupcionID().getAa());
    		this.posteInstaladoRetiradoID.setCodigoOficina(interrupcion.getInterrupcionID().getCodigoOficina());
    		this.posteInstaladoRetiradoID.setNumeroInterrupcion(interrupcion.getInterrupcionID().getNumeroInterrupcion());
    		
    		this.postes = this.posteInstaladoRetiradoBO.getPosteInstaladoRetirado(this.posteInstaladoRetiradoID);
    		if(this.postes == null){			
    			this.postes = new ArrayList();
    		}
    		
    		this.numeroMovimientoPoste = new Integer(this.postes.size() + 1);
    		this.longitudPoste = null;	
        	this.tipoPoste = null;	
        	this.posteInstalado = Boolean.valueOf(false);
        	this.posteRetirado = Boolean.valueOf(false);
    		
        }
        return "success";
    }
    
    
    /**
     * Cancela el mantenimiento de equipos
     * @return success
     */
    
    public String cancelarEquipos(){
        
        this.accionProvolma = "";
        this.nuevoFoco = "form1:voltaje";
        if(isModificar() == false){            
        	this.equipos = new ArrayList();
        	this.numeroEquipo = null;
        	this.serieEquipo = null;            
        }else{
    		this.equipoEspecialID = new EquipoEspecialID();
    		this.equipoEspecialID.setAno(interrupcion.getInterrupcionID().getAa());
    		this.equipoEspecialID.setCodigoOficina(interrupcion.getInterrupcionID().getCodigoOficina());
    		this.equipoEspecialID.setNumeroInterrupcion(interrupcion.getInterrupcionID().getNumeroInterrupcion());
    		
    		this.equipos = this.equipoEspecialBO.getEquiposEspeciales(this.equipoEspecialID);
    		if(this.equipos == null){			
    			this.equipos = new ArrayList();
    		}
        	this.numeroEquipo = null;
        	this.serieEquipo = null;   
        }
        return "success";
    }    
    
    
    /**
     * Regresa de la pagina de edicion de gemelas
     * @return error o success
     */
	public String regresarDeGemelas(){
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
		
        
        String resultado = this.actualizarDatosTablaGemelas();
        if(resultado.equals("error")){
            return resultado;
        }
        
		if(this.interrupcionesGemelas != null && this.interrupcionesGemelas.size() > 0 && this.acumuladorGemelas != this.clientesAfectados.longValue()){
            if(this.acumuladorGemelas < this.clientesAfectados.longValue()){
    			long diferencia = this.clientesAfectados.longValue() - this.acumuladorGemelas;
    			String mensaje = "La cantidad total de abonados afectados de las gemelas no coincide con la cantidad de la interrupción madre.  Falta por ingresar " + diferencia + " abonados afectados";
    	        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";		        
    			return "error";
            }
            else{
                long diferencia = this.acumuladorGemelas - this.clientesAfectados.longValue();                
                String mensaje = "La cantidad total de abonados afectados de las gemelas no coincide con la cantidad de la interrupción madre.  Se deben disminuir " + diferencia + " abonados afectados";
                this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";             
                return "error";                
            }
		}
		if(this.interrupcionesGemelas != null && this.interrupcionesGemelas.size() > 0 && this.esTiempoTotalGemela() == false){
	        String mensaje = "La suma total de tiempo fuera de las interrupciones gemelas no es igual al tiempo fuera de la interrupción madre";
	        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";
			return "error";
		}	
		
	    if(this.interrupcionesGemelas != null && this.interrupcionesGemelas.size() > 0){			
			horasAbonado = new Double(cacularHorasAbonado());
            horasAbonado = new Double(UtilidadesI.roundNum(horasAbonado.doubleValue()));
	    }
	    
		int gemelas = this.interrupcionesGemelas == null ? Integer.valueOf(0) : this.interrupcionesGemelas.size();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Se ingresaron "+gemelas+" gemelas.."));
		return "success";
	}
    
    /**
     * Determina si el boton del detalle de fusibles y transformadores aparecerá habilitado o no.
     * @return true o false
     */
    public boolean isDetalleFusibleTransformador(){        

        boolean deshabilitar = true;
        if(this.codigoProteccion != null && ((this.codigoProteccion.intValue() == 103 || this.codigoProteccion.intValue() == 104))){
            deshabilitar = false;     
        }
        
        if(this.codigoMaterial != null && this.codigoDano != null && (this.codigoMaterial.intValue() == 232)){
            deshabilitar = false;
        }else{
        	if(this.codigoProteccion != null && ((this.codigoProteccion.intValue() == 103 || this.codigoProteccion.intValue() == 104))){
                deshabilitar = false;     
            }
        }
        return deshabilitar;
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
	 * @param parametroBO El parametroBO a modificar.
	 */
	public void setParametroBO(ParametroBO parametroBO) {
		this.parametroBO = parametroBO;
	}

	/**
	 * Metodo accesor de codigoOficina.
	 * @return Retorna el codigoOficina.
	 */
	public Integer getCodigoOficina() {
		return codigoOficina;
	}
	/**
	 * Metodo modificador de codigoOficina.
	 * @param codigoOficina El codigoOficina a modificar.
	 */
	public void setCodigoOficina(Integer codigoOficina) {
		this.codigoOficina = codigoOficina;
	}
	
    /**
     * Cancela el proceso de interrupcion
     * @return success
     */
	public String cancelar(){
	    this.accionGuardar = "";
	    this.estructuraSelected = "";
        this.accionProvolma = "";	
        this.nuevoFoco = "form1:voltaje";
		return "success";
	}
	
    /**
     * Metodo para listenerOficina
     * @param v
     * @return
     */
	public String listenerOficina(){
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
		this.primeraVezConsecutivo = true;
		Integer oficina = Integer.valueOf(0);		
		this.region = this.regionBO.buscarPorOficina(oficina);
		if(region != null){
		    this.nombreRegion = region.getNombreRegion();
		    this.subRegion = this.subRegionBO.buscarPorOficina(oficina);
		    if(subRegion != null)
		        this.nombreSubregion = this.subRegion.getNombreSubRegion();
		    else
		        this.nombreSubregion = "";
		}		
		else
		    this.nombreRegion = "";
		return "listener";
	}
	
		
    /**
     * Regresa de la edicion de detalle de vehiculo y cuadgrilla
     * @return <String> error o success
     */
	public String regresarDePag4(){
	    this.accionProvolma = "";		    
	    this.nuevoFoco = "form1:voltaje";
        if((this.empleadosCuadrilla == null || this.empleadosCuadrilla.size() == 0)){
//            String mensaje = "Debe de completar el detalle de cuadrilla";
//            this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
        		"Debe de completar el detalle de cuadrilla."));
            return "error";
        }
	    if((this.vehiculo.getVehiculoID().getNumeroVehiculo() == null || this.vehiculo.getVehiculoID().getNumeroVehiculo().longValue() <= 0)){	        
//			String mensaje = "Debe de completar el detalle de vehículo utilizado";
//	        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";
	    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
	        	"Debe de completar el detalle de vehículo utilizado."));
	        return "error";
	    }
		if(this.validarDatosCuadrilla()){
		    if(horasAbonado == null){
				double horasInicio = Fechas.millisToMinutes(this.fechaInicioInterrupcion.getTime());
				double horasFinal = Fechas.millisToMinutes(this.fechaFinalInterrupcion.getTime());
				double tiefue = (horasFinal - horasInicio) / 60.0;				
                tiefue = UtilidadesI.roundNum(tiefue);
				horasAbonado = new Double(tiefue * this.clientesAfectados.longValue());
                horasAbonado = new Double(UtilidadesI.roundNum(horasAbonado.doubleValue()));
                horasAbonado = new Double(horasAbonado.doubleValue());       
		    }
		    return "success";
		}
		else{
			return "error";
		}	    
	    	    
	}
	
    /**
     * Regresa de edición de equipos especiales
     * @return String
     */
	public String regresarDeEquipoEspecial(){	
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
		int equipos = this.equipos.size();
		String mensaje = "Se ingresaron " + equipos + " equipos especiales";
        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>" +
		"window.alert('" + mensaje + "');</SCRIPT>";			
	    return "success";
	}
		
    /**
     * Regresa de edición de postes
     * @return String
     */
	public String regresarDePostes(){	
	    this.accionProvolma = "";
	    this.nuevoFoco = "form1:voltaje";
		int postes = this.postes.size();
		String mensaje = "Se ingresaron " + postes + " postes";
        this.accionProvolma = "<SCRIPT language='JavaScript1.2'>" +
		"window.alert('" + mensaje + "');</SCRIPT>";
	    return "success";
	}
	
    /**
     * Retorna una lista de postes ingresados por el usuario, en formato select item
     * @return List
     */
	public List getPostesSelectItems(){
		List postesSI = new ArrayList();
		if(this.postes.size() == 0){
		    postesSI.add(new SelectItem(new Integer(0),"Debe ingresar el detalle")); 
		}
		for(int i = 0; i < this.postes.size(); i++){
		    PosteInstaladoRetirado poste = (PosteInstaladoRetirado)this.postes.get(i);
		    postesSI.add(new SelectItem(new Integer(i),poste.getTipoPosteDescripcion() + ", " + poste.getMovimiento()     ));
		}		    		
		return postesSI;
	}
	
    /**
     * Retorna una lista de equipos especiales ingresados por el usuario, en formato select item
     * @return List
     */    
	public List getEquiposSelectItems(){
		List equipoSI = new ArrayList();
		if(this.equipos.size() == 0){
		    equipoSI.add(new SelectItem(new Integer(0),"Debe ingresar el detalle")); 
		}
		for(int i = 0; i < this.equipos.size(); i++){
            EquipoEspecial equipo = (EquipoEspecial)this.equipos.get(i);
            equipoSI.add(new SelectItem(new Integer(i),equipo.getEquipoEspecialID().getNumeroICE() + ", " + equipo.getSerie()));
		}		    		
		return equipoSI;
	}	
	
	public void cambioFocoProteccion(){
		this.nuevoFoco = "form1:cboMateriales";
	}
	
	public void cambioFocoMaterial(){
		this.nuevoFoco = "form1:menu3";
	}
	public String getAccionFoco() {
		return "<SCRIPT language='JavaScript1.2'>" +
		"document.getElementById('"+this.nuevoFoco+"').focus();</SCRIPT>";
	}

	public String getNuevoFoco() {
		return nuevoFoco;
	}

	public void setNuevoFoco(String nuevoFoco) {
		this.nuevoFoco = nuevoFoco;
	}

	public String getAccionProteccion() {
		if(this.codigoMaterial.equals(new Integer(103))||this.codigoMaterial.equals(new Integer(104))){
			return "<SCRIPT language='JavaScript1.2'>window.alert('Debe incluir fusibles');</SCRIPT>";
		}
		return "";
	}

	public Boolean getIndTension()
	{
		Boolean resultado = false;
		try
		{
			if(this.interrupcion!=null)
			{
				if(this.interrupcion.getIndTension() == null || this.interrupcion.getIndTension().equals(Integer.valueOf(1)))
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
				if(this.interrupcion!=null)
				{
					if(this.interrupcion.getIndTension() == null || this.interrupcion.getIndTension().equals(Integer.valueOf(1)))
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
	/**
	 * @param tipoVoltaje El tipoVoltaje a establecer.
	 */
	public void setTipoVoltaje(TipoVoltaje tipoVoltaje) {
		this.tipoVoltaje = tipoVoltaje;
	}

	/**
	 * @return Devuelve cliente.
	 */
	public String getCliente() {
		return cliente;
	}
	/**
	 * @param cliente El cliente a establecer.
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	/**
	 * @return Devuelve clientesAfectados.
	 */
	public Long getClientesAfectados() {
		return clientesAfectados;
	}
	/**
	 * @param clientesAfectados El clientesAfectados a establecer.
	 */
	public void setClientesAfectados(Long clientesAfectados) {		
			this.clientesAfectados = clientesAfectados;		
	}
	/**
	 * @return Devuelve duracionInterrupcion.
	 */
	public Double getDuracionInterrupcion() {
		return duracionInterrupcion;
	}
	/**
	 * @param duracionInterrupcion El duracionInterrupcion a establecer.
	 */
	public void setDuracionInterrupcion(Double duracionInterrupcion) {
		this.duracionInterrupcion = duracionInterrupcion;
	}
	/**
	 * @return Devuelve fechaFinalInterrupcion.
	 */
	public Date getFechaFinalInterrupcion() {
		return fechaFinalInterrupcion;
	}
		   
	/**
	 * @return Devuelve trafoInstalador.
	 */
	public Boolean getTrafoInstalado() {
		return trafoInstalado;
	}
	/**
	 * @param trafoInstalador El trafoInstalador a establecer.
	 */
	public void setTrafoInstalado(Boolean trafoInstalador) {
		this.trafoInstalado = trafoInstalador;
	}
	/**
	 * @return Devuelve trafoRetirado.
	 */
	public Boolean getTrafoRetirado() {
		return trafoRetirado;
	}
	/**
	 * @param trafoRetirado El trafoRetirado a establecer.
	 */
	public void setTrafoRetirado(Boolean trafoRetirado) {
		this.trafoRetirado = trafoRetirado;
	}	
		
	/**
	 * @return Devuelve circuitoBO.
	 */
	public CircuitoBO getCircuitoBO() {
		return circuitoBO;
	}
	/**
	 * @param circuitoBO El circuitoBO a establecer.
	 */
	public void setCircuitoBO(CircuitoBO circuitoBO) {
		this.circuitoBO = circuitoBO;
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
     * Metodo accesor de horasAbonado.
     * @return Retorna el horasAbonado.
     */    
    public Double getHorasAbonado() {
        return horasAbonado;
    }
    
    /**
     * Metodo modificador de horasAbonado.
     * @param horasAbonado El horasAbonado a modificar.
     */    
    public void setHorasAbonado(Double horasAbonado) {
        this.horasAbonado = horasAbonado;
    }
    
    /**
     * Metodo accesor de codigoEquipo.
     * @return Retorna el codigoEquipo.
     */    
    public Integer getCodigoEquipo() {
        return codigoEquipo;
    }
    
    /**
     * Metodo modificador de codigoEquipo.
     * @param codigoEquipo El codigoEquipo a modificar.
     */    
    public void setCodigoEquipo(Integer codigoEquipo) {
        this.codigoEquipo = codigoEquipo;
    }
    
    /**
     * Metodo accesor de codigoPoste.
     * @return Retorna el codigoPoste.
     */    
    public Integer getCodigoPoste() {
        return codigoPoste;
    }
    
    /**
     * Metodo modificador de codigoPoste.
     * @param codigoPoste El codigoPoste a modificar.
     */    
    public void setCodigoPoste(Integer codigoPoste) {
        this.codigoPoste = codigoPoste;
    }

    /**
     * Metodo accesor de botonFusible.
     * @return Retorna el botonFusible.
     */
    public boolean isBotonFusible() {
        return botonFusible;
    }
    
    /**
     * Metodo modificador de botonFusible.
     * @param botonFusible El botonFusible a modificar.
     */    
    public void setBotonFusible(boolean botonFusible) {
        this.botonFusible = botonFusible;
    }
    
    /**
     * Metodo accesor de botonTransformador.
     * @return Retorna el botonTransformador.
     */    
    public boolean isBotonTransformador() {
        return botonTransformador;
    }
    
    /**
     * Metodo modificador de botonTransformador.
     * @param botonTransformador El botonTransformador a modificar.
     */    
    public void setBotonTransformador(boolean botonTransformador) {
        this.botonTransformador = botonTransformador;
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
		return this.telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
     * Metodo modificador de oficinaBO.
     * @param oficinaBO El oficinaBO a modificar.
     */
    public void setOficinaBO(OficinaBO oficinaBO) {
        this.oficinaBO = oficinaBO;
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
     * Metodo modificador de proteccionBO.
     * @param proteccionBO El proteccionBO a modificar.
     */
    public void setProteccionBO(ProteccionBO proteccionBO) {
        this.proteccionBO = proteccionBO;
    }
    /**
     * Metodo modificador de materialBO.
     * @param materialBO El materialBO a modificar.
     */
    public void setMaterialBO(MaterialBO materialBO) {
        this.materialBO = materialBO;
    }
	public boolean getDetalleFusibleTransformador() {
		return this.detalleFusibleTransformador;
	}
	public void setDetalleFusibleTransformador(boolean detalleFusibleTransformador) {
		this.detalleFusibleTransformador = detalleFusibleTransformador;
	}
	/**
	 * Metodo accesor de numeroMovimientoGemela.
	 * @return Retorna el numeroMovimientoGemela.
	 */
	public Integer getNumeroMovimientoGemela() {
		return numeroMovimientoGemela;
	}
	/**
	 * Metodo modificador de numeroMovimientoGemela.
	 * @param numeroMovimientoGemela El numeroMovimientoGemela a modificar.
	 */
	public void setNumeroMovimientoGemela(Integer numeroMovimientoGemela) {
		this.numeroMovimientoGemela = numeroMovimientoGemela;
	}
	
	/**
	 * Metodo accesor de dataTableGemelas.
	 * @return Retorna el dataTableGemelas.
	 */
	public DataTable getDataTableGemelas() {
		return dataTableGemelas;
	}
	/**
	 * Metodo modificador de dataTableGemelas.
	 * @param dataTableGemelas El dataTableGemelas a modificar.
	 */
	public void setDataTableGemelas(DataTable dataTableGemelas) {
		this.dataTableGemelas = dataTableGemelas;
	}
	/**
	 * Metodo accesor de abonadosAfectadosGemela.
	 * @return Retorna el abonadosAfectadosGemela.
	 */
	public Long getAbonadosAfectadosGemela() {
		return abonadosAfectadosGemela;
	}
	/**
	 * Metodo modificador de abonadosAfectadosGemela.
	 * @param abonadosAfectadosGemela El abonadosAfectadosGemela a modificar.
	 */
	public void setAbonadosAfectadosGemela(Long abonadosAfectadosGemela) {
		this.abonadosAfectadosGemela = abonadosAfectadosGemela;
	}	
	/**
	 * @return Devuelve lugarInterrupcion.
	 */
	public String getLugarInterrupcion() {
		return lugarInterrupcion;
	}
	/**
	 * @param lugarInterrupcion El lugarInterrupcion a establecer.
	 */
	public void setLugarInterrupcion(String lugarInterrupcion) {
		this.lugarInterrupcion = lugarInterrupcion;
	}
	/**
	 * @return Devuelve medidor.
	 */
	public Long getMedidor() {
		return medidor;
	}
	/**
	 * @param medidor El medidor a establecer.
	 */
	public void setMedidor(Long medidor) {
		this.medidor = medidor;
	}
	/**
	 * @return Devuelve observaciones.
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * @param observaciones El observaciones a establecer.
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	/**
	 * @return Devuelve oficina.
	 */
	public String getOficina() {
		return oficina;
	}
	/**
	 * @param oficina El oficina a establecer.
	 */
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
}
