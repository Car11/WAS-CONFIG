package cr.go.ice.interrupciones.web.controller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.jibx.runtime.JiBXException;

import com.vvs.jsf.AbstractFacesController;
import com.vvs.utilidades.Utilidades;

import cr.go.ice.cia.dominio.UsuarioCia;
import cr.go.ice.interrupciones.BO.AnimalBO;
import cr.go.ice.interrupciones.BO.CausaBO;
import cr.go.ice.interrupciones.BO.CircuitoBO;
import cr.go.ice.interrupciones.BO.CuadrillaBO;
import cr.go.ice.interrupciones.BO.DanoBO;
import cr.go.ice.interrupciones.BO.EmpleadoBO;
import cr.go.ice.interrupciones.BO.EquipoEspecialBO;
import cr.go.ice.interrupciones.BO.FusibleBO;
import cr.go.ice.interrupciones.BO.InterrupcionBO;
import cr.go.ice.interrupciones.BO.InterrupcionGemelaBO;
import cr.go.ice.interrupciones.BO.MaterialBO;
import cr.go.ice.interrupciones.BO.NoPropiaSeccionamientoBO;
import cr.go.ice.interrupciones.BO.OficinaBO;
import cr.go.ice.interrupciones.BO.PosteInstaladoRetiradoBO;
import cr.go.ice.interrupciones.BO.ProteccionBO;
import cr.go.ice.interrupciones.BO.PuebloBO;
import cr.go.ice.interrupciones.BO.RegionBO;
import cr.go.ice.interrupciones.BO.ReporteBO;
import cr.go.ice.interrupciones.BO.SeccionBO;
import cr.go.ice.interrupciones.BO.SubEstacionBO;
import cr.go.ice.interrupciones.BO.SubRegionBO;
import cr.go.ice.interrupciones.BO.TipoVoltajeBO;
import cr.go.ice.interrupciones.BO.TrafoBO;
import cr.go.ice.interrupciones.BO.UsuarioOficinaBO;
import cr.go.ice.interrupciones.BO.VehiculoBO;
import cr.go.ice.interrupciones.domain.Animal;
import cr.go.ice.interrupciones.domain.Causa;
import cr.go.ice.interrupciones.domain.Circuito;
import cr.go.ice.interrupciones.domain.Cuadrilla;
import cr.go.ice.interrupciones.domain.CuadrillaID;
import cr.go.ice.interrupciones.domain.Dano;
import cr.go.ice.interrupciones.domain.Empleado;
import cr.go.ice.interrupciones.domain.EquipoEspecial;
import cr.go.ice.interrupciones.domain.EquipoEspecialID;
import cr.go.ice.interrupciones.domain.FusibleID;
import cr.go.ice.interrupciones.domain.Interrupcion;
import cr.go.ice.interrupciones.domain.InterrupcionGemela;
import cr.go.ice.interrupciones.domain.InterrupcionGemelaID;
import cr.go.ice.interrupciones.domain.InterrupcionID;
import cr.go.ice.interrupciones.domain.Material;
import cr.go.ice.interrupciones.domain.NoPropiaSeccionamiento;
import cr.go.ice.interrupciones.domain.NoPropiaSeccionamientoID;
import cr.go.ice.interrupciones.domain.Oficina;
import cr.go.ice.interrupciones.domain.PosteInstaladoRetiradoID;
import cr.go.ice.interrupciones.domain.Proteccion;
import cr.go.ice.interrupciones.domain.Region;
import cr.go.ice.interrupciones.domain.Reporte;
import cr.go.ice.interrupciones.domain.ReporteID;
import cr.go.ice.interrupciones.domain.Seccion;
import cr.go.ice.interrupciones.domain.SeccionID;
import cr.go.ice.interrupciones.domain.SubEstacion;
import cr.go.ice.interrupciones.domain.SubRegion;
import cr.go.ice.interrupciones.domain.SubRegionID;
import cr.go.ice.interrupciones.domain.TipoVoltaje;
import cr.go.ice.interrupciones.domain.TipoVoltajeID;
import cr.go.ice.interrupciones.domain.TrafoID;
import cr.go.ice.interrupciones.domain.UsuarioOficina;
import cr.go.ice.interrupciones.domain.Vehiculo;
import cr.go.ice.interrupciones.domain.VehiculoID;
import cr.go.ice.interrupciones.servicios.ConsultaPueblos;
import cr.go.ice.interrupciones.servicios.RespuestaConsultaPueblos;
import cr.go.ice.interrupciones.servicios.ServiciosAcemas;
import cr.go.ice.interrupciones.servlets.ServletReporte;
import cr.go.ice.interrupciones.utils.Usuario;
import cr.go.ice.interrupciones.utils.UtilidadesFaces;
import cr.go.ice.interrupciones.utils.UtilidadesI;
import cr.go.ice.interrupciones.web.Recurso;
import cr.go.ice.obras.BO.AgenciaBO;
import cr.go.ice.obras.domain.Agencia;
import cr.go.ice.sace.sacecon.domain.Pueblo;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ReporteInterrupcionController.java</p>
 * <p>Modulo (subsistema): InterrupcionesWeb</p>
 * <p>Descricion de <code>ReporteInterrupcionController.java</code>.</p>
 * <p>Fecha creación: 03/07/2007</p>
 * <p>Ultima actualización: 03/07/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ReporteInterrupcionController extends AbstractFacesController {
	
	private Integer ano;
	private Integer anoInicial;
	private Integer mesInicial;
	private Integer oficina;
	private Long consecutivo;
	private Boolean mas5;
	private Boolean menos5;
	private Long cedula;
	
	private InterrupcionBO interrupcionBO;
	private ReporteBO reporteBO;
	private CuadrillaBO cuadrillaBO;
	private VehiculoBO vehiculoBO;
	
	private RegionBO regionBO;
	private SubRegionBO subRegionBO;
	private SubEstacionBO subEstacionBO;
	private CircuitoBO circuitoBO;
	private SeccionBO seccionBO;
	private EmpleadoBO empleadoBO;
	private OficinaBO oficinaBO;	
	private PuebloBO puebloBO;
	private AgenciaBO agenciaBO;	
	private TipoVoltajeBO tipoVoltajeBO;	
	private ProteccionBO proteccionBO;
	private MaterialBO materialBO;
	private DanoBO danoBO;
	private CausaBO causaBO;
	private AnimalBO animalBO;
	
	private PosteInstaladoRetiradoBO posteInstaladoRetiradoBO;
	private EquipoEspecialBO equipoEspecialBO;
	private FusibleBO fusibleBO;
	private TrafoBO trafoBO;
	private InterrupcionGemelaBO interrupcionGemelaBO;
	private NoPropiaSeccionamientoBO noPropiaSeccionamientoBO;
	private UsuarioOficinaBO usuarioOficinaBO;
	
	private Boolean comboOficina;
	private ServiciosAcemas serviciosAcemas;
	
	 private  static final String JasperPath = "/jasperReports/";   
	 private Integer formato;
	/**
	 * 
	 * Constructor por defecto
	 */
	public ReporteInterrupcionController(){
        inicializarAtributos();
	}
    
	public String getInit(){
		 FacesContext context = FacesContext.getCurrentInstance();
		Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
       String nombreUsuarioSession = (String) context.getApplication().createValueBinding("#{autorizacionController.nombreUsuario}").getValue(this.getFacesContext());
        String[] valores = nombreUsuarioSession.split("-");
        this.cedula = new Long(valores[0].trim());
        if(limpiar != null){
        	inicializarAtributos();
           
    }
		return "success";
	}
	 public void load(FacesContext context)
	    {
	        Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
	        FacesContext ctx = FacesContext.getCurrentInstance();
	       
	        String nombreUsuarioSession = (String) context.getApplication().createValueBinding("#{autorizacionController.nombreUsuario}").getValue(this.getFacesContext());
	        String[] valores = nombreUsuarioSession.split("-");
	        this.cedula = new Long(valores[0].trim());
	        if(limpiar != null)
	        	inicializarAtributos();
	        
	    
	    }
	    
	
	
    private void inicializarAtributos(){
    	Calendar calendar = Calendar.getInstance();
    	this.anoInicial = new Integer(calendar.get(Calendar.YEAR));
    	this.mesInicial = new Integer(calendar.get(Calendar.MONTH) + 1);
        this.mas5 = Boolean.valueOf(true);
        this.menos5 = Boolean.valueOf(false);
        this.serviciosAcemas = new ServiciosAcemas();
        this.formato = UtilidadesFaces.FORMATO_PDF; 
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
			// buscar por código de  pueblo
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
	 * Retorna una lista de los años en que han ocurrido interrupciones
	 * @return Lista de años
	 */	
	public List getListaAnos(){
		
        List anos = new ArrayList();
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
//      this.comboOficina = (userAdmin) ? Boolean.valueOf(false) : Boolean.valueOf(true);
      this.comboOficina = Boolean.valueOf(false);
      
        Empleado emp = this.empleadoBO.buscar(cedula);
        
        List<UsuarioOficina> listaPivote = this.usuarioOficinaBO.buscarCedula(cedula);
    	if(!listaPivote.isEmpty())
    	{
    		this.oficina = listaPivote.get(0).getId().getOficina().getCodigoOficina();
    	}else
    	{
    		this.oficina = new Integer(0);
    	}

		
		return anosSI;	
	}	
    
	public List getListaMesSI()
    {
        return UtilidadesFaces.getListaMes();
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
	 * Realiza las validaciones de acuerdo a los datos introducidos por el usuario y determina si la 
	 * interrupción (mayor o menor a cinco minutos) existe en la base de datos
	 * @return error or success de acuerdo a la existencia o no de la interrupción y de la correcta generación
	 * del reporte
	 */
    public String imprimir() {
        
        if (this.oficina == null || this.oficina.intValue() <= 0) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La oficina es requerida."));
            return "error";
        }
        if ((this.mas5 == null || this.mas5.booleanValue() == false) && (this.menos5 == null || this.menos5.booleanValue() == false)) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar el tio de interrupción."));

            return "error";
        }
        if (this.consecutivo == null || this.consecutivo.longValue() < 0) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El consecutivo es requerido."));

            return "error";
        }

        if (this.mas5.booleanValue()) {
            InterrupcionID interrupcionID = new InterrupcionID();
            interrupcionID.setAa(this.ano);
            interrupcionID.setCodigoOficina(this.oficina);
            interrupcionID.setNumeroInterrupcion(this.consecutivo);
            if (this.interrupcionBO.interrupcionExiste(interrupcionID) == false) {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La interrupción no existe en la base de datos."));

                return "error";
            }
        }
        if (this.menos5.booleanValue()) {
            ReporteID reporteID = new ReporteID();
            reporteID.setAa(this.ano);
            reporteID.setCodigoOficina(this.oficina);
            reporteID.setNumeroReporte(this.consecutivo);
            if (this.reporteBO.reporteExiste(reporteID) == false) {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La interrupción no existe en la base de datos"));

                return "error";
            }
        }

        if (this.menos5.booleanValue()) {
            // ctx.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteMenorCincoMin));
            this.cargarDatosMenorCincoMin();
        } else {
            // ctx.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteMayorCincoMin));
            if (this.cargarDatosMayorCincoMin().equals("success")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Ejecutando reporte."));
                return "success";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ocurrió un problema."));
                return "error";
            }
        }
        return "success";

    }

	/**
	 * Obtiene la interrupción deseada de la base de datos y los parametros relacionados que se enviarán y mostrarán
	 * en el reporte para la interrupción mayor a cinco minutos.  En este caso se colocan en la sesion para que luego
	 * el servlet que genera el reporte se encargue de enviarle los parámetros correspondientes.	 
	 */
	private String cargarDatosMayorCincoMin(){
	    FacesContext ctx = FacesContext.getCurrentInstance();
	    HashMap parametrosReporte = new HashMap();
	    JRDataSource jrDataSource = null;
	    
	    InterrupcionID interrupcionID = new InterrupcionID();
	    interrupcionID.setNumeroInterrupcion(this.consecutivo);
	    interrupcionID.setCodigoOficina(this.oficina);
	    interrupcionID.setAa(this.ano);
		Interrupcion interrupcion = this.interrupcionBO.getInterrupcion(interrupcionID);
		
		interrupcion.setFechaInicio(UtilidadesI.getFechaCompleta(interrupcion.getFechaInicio(), interrupcion.getHoraInicio().doubleValue()));		
		interrupcion.setFechaFin(UtilidadesI.getFechaCompleta(interrupcion.getFechaFin(), interrupcion.getHoraFin().doubleValue()));
		interrupcion.setFechaAviso(UtilidadesI.getFechaCompleta(interrupcion.getFechaAviso(), interrupcion.getHoraAviso().doubleValue()));
		
		CuadrillaID cuadrillaID = new CuadrillaID();
		cuadrillaID.setAa(this.ano);
		cuadrillaID.setCodigoOficina(this.oficina);
		cuadrillaID.setNumeroInterrupcion(this.consecutivo);
		cuadrillaID.setReporteInterrupcion(new Integer(1));
		List cuadrillas = this.cuadrillaBO.getCuadrillas(cuadrillaID);
		List cuadrillasRes = new ArrayList();
		if(cuadrillas != null){
		    for(int i = 0; i < cuadrillas.size(); i++){
		        Cuadrilla cuadrilla = (Cuadrilla)cuadrillas.get(i);
		        cuadrilla.setCedula(cuadrilla.getCuadrillaID().getCedula());
		        cuadrillasRes.add(cuadrilla);
		    }
		}
		cuadrillas = new ArrayList(cuadrillasRes);
		
		Vehiculo vehiculo = new Vehiculo();
		VehiculoID vehiculoID = new VehiculoID();
		vehiculoID.setAa(this.ano);
		vehiculoID.setCodigoOficina(this.oficina);
		vehiculoID.setNumeroInterrupcion(this.consecutivo);
		vehiculoID.setTipo(new Integer(0));
		vehiculo.setVehiculoID(vehiculoID);
		vehiculo = this.vehiculoBO.buscar(vehiculo);
		if(vehiculo != null)
		    vehiculo.setNumeroVehiculo(vehiculo.getVehiculoID().getNumeroVehiculo());
		
		PosteInstaladoRetiradoID posteID = new PosteInstaladoRetiradoID();
		posteID.setAno(this.ano);
		posteID.setNumeroInterrupcion(this.consecutivo);
		posteID.setCodigoOficina(this.oficina);		
		List postes = this.posteInstaladoRetiradoBO.getPosteInstaladoRetirado(posteID);
		
		EquipoEspecialID equipoID = new EquipoEspecialID();
		equipoID.setAno(this.ano);
		equipoID.setCodigoOficina(this.oficina);
		equipoID.setNumeroInterrupcion(this.consecutivo);		
		List equipos = this.equipoEspecialBO.getEquiposEspeciales(equipoID);
		List equiposRes = new ArrayList();
		if(equipos != null){
		    for(int i = 0; i < equipos.size(); i++){
		        EquipoEspecial equipo = (EquipoEspecial)equipos.get(i);
		        equipo.setNumeroICE(equipo.getEquipoEspecialID().getNumeroICE());
		        equiposRes.add(equipo);
		    }
		}
		equipos = new ArrayList(equiposRes);		
		
		
		FusibleID fusibleID = new FusibleID();
		fusibleID.setAa(this.ano);
		fusibleID.setCodigoOficina(this.oficina);
		fusibleID.setNumeroInterrupcion(this.consecutivo);
		List fusibles = this.fusibleBO.getFusibles(fusibleID);
		
		TrafoID trafoID = new TrafoID();
		trafoID.setAa(this.ano);
		trafoID.setCodigoOficina(this.oficina);
		trafoID.setNumeroInterrupcion(this.consecutivo);
		List trafos = this.trafoBO.getTrafos(trafoID);
		
		InterrupcionGemelaID interrupcionGemelaID = new InterrupcionGemelaID();		
		Interrupcion interup = new Interrupcion(); 
		InterrupcionID interupID = new InterrupcionID();
		interupID.setAa(interrupcion.getInterrupcionID().getAa());
		interupID.setCodigoOficina(interrupcion.getInterrupcionID().getCodigoOficina());
		interupID.setNumeroInterrupcion(interrupcion.getInterrupcionID().getNumeroInterrupcion());
		interup.setInterrupcionID(interupID);
		interrupcionGemelaID.setInterrupcion(interup);						
		List gemelas = this.interrupcionGemelaBO.getInterrupcionesGemelas(interrupcionGemelaID);
        if(gemelas != null){
            for(int i = 0; i < gemelas.size(); i++){                
                ((InterrupcionGemela)gemelas.get(i)).setFechaInicio(UtilidadesI.getFechaCompleta(((InterrupcionGemela)gemelas.get(i)).getFechaInicio(), ((InterrupcionGemela)gemelas.get(i)).getHoraInicio().doubleValue()));
                ((InterrupcionGemela)gemelas.get(i)).setFechaFin(UtilidadesI.getFechaCompleta(((InterrupcionGemela)gemelas.get(i)).getFechaFin(), ((InterrupcionGemela)gemelas.get(i)).getHoraFin().doubleValue()));
            }
        }
		
		NoPropiaSeccionamientoID seccionamientoID = new NoPropiaSeccionamientoID();
		seccionamientoID.setAa(this.ano);
		seccionamientoID.setCodigoOficina(this.oficina);
		seccionamientoID.setNumeroInterrupcion(this.consecutivo);
		List seccionamientos = this.noPropiaSeccionamientoBO.getNoPropiaSeccionamientos(seccionamientoID);
        if(seccionamientos != null){
            for(int i = 0; i < seccionamientos.size(); i++){                
                ((NoPropiaSeccionamiento)seccionamientos.get(i)).setFechaInicio(UtilidadesI.getFechaCompleta(((NoPropiaSeccionamiento)seccionamientos.get(i)).getFechaInicio(), ((NoPropiaSeccionamiento)seccionamientos.get(i)).getHoraInicio().doubleValue()));
                ((NoPropiaSeccionamiento)seccionamientos.get(i)).setFechaFin(UtilidadesI.getFechaCompleta(((NoPropiaSeccionamiento)seccionamientos.get(i)).getFechaFin(), ((NoPropiaSeccionamiento)seccionamientos.get(i)).getHoraFin().doubleValue()));
                ((NoPropiaSeccionamiento)seccionamientos.get(i)).setSeccion(((NoPropiaSeccionamiento)seccionamientos.get(i)).getNoPropiaSeccionamientoID().getSeccion());
            }
        }        
		
	    String consecutivoInterrupcion = this.ano + " - " + this.oficina + " - " + this.consecutivo;	    
//	    ctx.getExternalContext().getSessionMap().put("consecutivoInterrupcion",consecutivoInterrupcion);
//	    ctx.getExternalContext().getSessionMap().put("interrupcion",interrupcion);
//	    ctx.getExternalContext().getSessionMap().put("cuadrillas",cuadrillas);
//	    ctx.getExternalContext().getSessionMap().put("vehiculo",vehiculo);
//	    
//	    ctx.getExternalContext().getSessionMap().put("postes",postes);
//	    ctx.getExternalContext().getSessionMap().put("equipos",equipos);
//	    ctx.getExternalContext().getSessionMap().put("fusibles",fusibles);
//	    ctx.getExternalContext().getSessionMap().put("trafos",trafos);
//	    ctx.getExternalContext().getSessionMap().put("gemelas",gemelas);
//	    ctx.getExternalContext().getSessionMap().put("seccionamientos",seccionamientos);
//	    
//	    ctx.getExternalContext().getSessionMap().put("region",regionBO.buscar(interrupcion.getRegion()));
	    SubRegionID subID = new SubRegionID();
	    Region reg = new Region();
	    reg.setRegion(interrupcion.getRegion());
	    reg = regionBO.buscar(interrupcion.getRegion());
	    subID.setSubRegion(interrupcion.getSubRegion());
	    subID.setRegion(reg);
	    
	    SubRegion subReg = new SubRegion();
	    subReg = subRegionBO.buscar(subID);
//	    ctx.getExternalContext().getSessionMap().put("subregion",subRegionBO.buscar(subID));	    
	    
	    
	    SubEstacion sub = new SubEstacion();
	    sub = subEstacionBO.buscar(interrupcion.getSubEstacion());
	    //ctx.getExternalContext().getSessionMap().put("subEstacion",subEstacionBO.buscar(interrupcion.getSubEstacion()));
	    
	    Circuito cir = new Circuito();
	    cir  = circuitoBO.buscar(interrupcion.getSubEstacion(),interrupcion.getCircuito());
	   // ctx.getExternalContext().getSessionMap().put("circuito",circuitoBO.buscar(interrupcion.getSubEstacion(),interrupcion.getCircuito()));
	    
		SeccionID seccionID = new SeccionID();
		seccionID.setSubEstacion(interrupcion.getSubEstacion());
		seccionID.setCircuito(interrupcion.getCircuito());
		seccionID.setSeccion(interrupcion.getSeccion());
		
		Seccion sec = new Seccion();
		sec = seccionBO.buscar(seccionID);
//	    ctx.getExternalContext().getSessionMap().put("seccion",seccionBO.buscar(seccionID));
//	    ctx.getExternalContext().getSessionMap().put("empleado",empleadoBO.buscar(interrupcion.getOperador()));
	    
	    Oficina ofic = new Oficina();
	    ofic = oficinaBO.buscar(this.oficina);
	//    ctx.getExternalContext().getSessionMap().put("oficina",oficinaBO.buscar(this.oficina));
	    
		Pueblo puebloFalla = new Pueblo();		
		puebloFalla.setNumero(interrupcion.getCodigoPueblo());		
	 //   ctx.getExternalContext().getSessionMap().put("puebloFalla",obtenerPueblo(puebloFalla));
	    
		Pueblo puebloEquipo = new Pueblo();
		puebloEquipo.setNumero(interrupcion.getCodigoPuebloEquipo());			   
	   // ctx.getExternalContext().getSessionMap().put("puebloEquipo",obtenerPueblo(puebloEquipo));
//	    if(interrupcion.getCodigoAgencia() != null)
//	        ctx.getExternalContext().getSessionMap().put("agencia",agenciaBO.buscar(interrupcion.getCodigoAgencia()));
//	    else
//	        ctx.getExternalContext().getSessionMap().put("agencia",null);
	    
		TipoVoltajeID tipoVoltajeID = new TipoVoltajeID();
		tipoVoltajeID.setCodigoVoltaje(interrupcion.getCodigoVoltaje());
		tipoVoltajeID.setTipoVoltaje(interrupcion.getTipoVoltaje());	
		
		TipoVoltaje tipoVoltaje= new TipoVoltaje();
		if(tipoVoltajeID != null)
		tipoVoltaje = tipoVoltajeBO.buscar(tipoVoltajeID);
		
		Proteccion proteccion = new Proteccion();
		if(interrupcion.getCodigoProteccion() != null)
		proteccion = proteccionBO.buscar(interrupcion.getCodigoProteccion());
		
		Material material = new Material();
		if(interrupcion.getCodigoMaterial() != null)
		material = materialBO.buscar(interrupcion.getCodigoMaterial());
		
		Dano dano = new Dano();
		if(interrupcion.getCodigoDano() != null)
		dano = danoBO.buscar(interrupcion.getCodigoDano());
		
//		ctx.getExternalContext().getSessionMap().put("tipoVoltaje",tipoVoltajeBO.buscar(tipoVoltajeID));
//		ctx.getExternalContext().getSessionMap().put("proteccion",proteccionBO.buscar(interrupcion.getCodigoProteccion()));
//		ctx.getExternalContext().getSessionMap().put("material",materialBO.buscar(interrupcion.getCodigoMaterial()));
//		ctx.getExternalContext().getSessionMap().put("dano",danoBO.buscar(interrupcion.getCodigoDano()));
		
		Causa causa1 = new Causa();
		if(interrupcion.getCausa1() != null)
		causa1 = causaBO.buscar(interrupcion.getCausa1());
		
		//ctx.getExternalContext().getSessionMap().put("causa1",causaBO.buscar(interrupcion.getCausa1()));
//		if(interrupcion.getCausa2() != null)
//		    ctx.getExternalContext().getSessionMap().put("causa2",causaBO.buscar(interrupcion.getCausa2()));
//		else
//		    ctx.getExternalContext().getSessionMap().put("causa2",null);
//		if(interrupcion.getCodigoAnimal() != null)
//		    ctx.getExternalContext().getSessionMap().put("animal",animalBO.buscar(interrupcion.getCodigoAnimal()));
//		else
//		    ctx.getExternalContext().getSessionMap().put("animal",null);	
		

        String nombreArchivo = "reporteMayorCincoMin";
        Date fechaActual = new Date();
        SimpleDateFormat sf = new SimpleDateFormat();
        sf.applyPattern("dd-MM-yyyy");                                
        String fechaActualSTR = sf.format(fechaActual);
        nombreArchivo += fechaActualSTR;
        
        String nombreArchivoJasper = "SigeItrRepMayorCincoMin.jasper";
        
        
        //parametros del reporte
        String ServletJasperPath = "/jasperReports/";
        parametrosReporte.put("SUBREPORT_DIR",ServletJasperPath);
        
        List<Serializable> vehiculos = new ArrayList<Serializable>();
        vehiculos.add(vehiculo);
        List<Serializable> interrupciones = new ArrayList<Serializable>();
        interrupciones.add(interrupcion);
        
        parametrosReporte.put("CONSECUTIVO",this.ano + "-" +this.oficina + "-" + this.consecutivo);
        parametrosReporte.put("CUADRILLA",new JRBeanCollectionDataSource(cuadrillas));
        
        if(vehiculo != null)
            parametrosReporte.put("VEHICULO",new JRBeanCollectionDataSource(vehiculos));
        else
            parametrosReporte.put("VEHICULO",new JRBeanCollectionDataSource(new ArrayList()));
        
        parametrosReporte.put("POSTES",new JRBeanCollectionDataSource(postes));
        parametrosReporte.put("EQUIPOS",new JRBeanCollectionDataSource(equipos));
        parametrosReporte.put("FUSIBLES",new JRBeanCollectionDataSource(fusibles));
        parametrosReporte.put("TRAFOS",new JRBeanCollectionDataSource(trafos));
        parametrosReporte.put("GEMELAS",new JRBeanCollectionDataSource(gemelas));
        parametrosReporte.put("SECCIONAMIENTOS",new JRBeanCollectionDataSource(seccionamientos));
        
        parametrosReporte.put("ANO",this.ano);
        parametrosReporte.put("OFICINA",this.oficina);
        parametrosReporte.put("NUMERO_REPORTE",this.consecutivo);
        
        parametrosReporte.put("NOMBRE_REGION",reg.getRegion() + " - " + reg.getNombreRegion());
        parametrosReporte.put("NOMBRE_SUBREGION",subReg.getSubRegionID().getSubRegion() + " - " + subReg.getNombreSubRegion());
        parametrosReporte.put("NOMBRE_SUBESTACION",sub.getCodigoSubEstacion() + " - " + sub.getNombreSubEstacion());  
        parametrosReporte.put("NOMBRE_CIRCUITO",cir.getCircuitoID().getCircuito() + " - " + cir.getNombreCircuito());
        parametrosReporte.put("NOMBRE_SECCION",sec.getSeccionID().getSeccion() + " - " + sec.getNombreSeccion());
        parametrosReporte.put("TENSION",interrupcion.getIndTension());
        
        if (interrupcion.getOperador() != null) {
            Empleado emp = empleadoBO.buscar(interrupcion.getOperador());
            parametrosReporte.put("NOMBRE_EMPLEADO", emp.getNombreCompleto());
        } else
            parametrosReporte.put("NOMBRE_EMPLEADO", "INHABILITADO");
        
        parametrosReporte.put("NOMBRE_OFICINA",ofic.getNombreOficina());
        
        if (interrupcion.getCodigoPueblo() != null) {
            puebloFalla = obtenerPueblo(puebloFalla);
            parametrosReporte.put("NOMBRE_PUEBLOFALLA", puebloFalla.getNombre());
        } else
            parametrosReporte.put("NOMBRE_PUEBLOFALLA", "DESCONOCIDO");
        
        if (interrupcion.getCodigoPuebloEquipo() != null) {
            puebloEquipo = obtenerPueblo(puebloEquipo);
            parametrosReporte.put("NOMBRE_PUEBLOEQUIPO", puebloEquipo.getNombre());
        } else
            parametrosReporte.put("NOMBRE_PUEBLOEQUIPO", "DESCONOCIDO");
        
        if (interrupcion.getCodigoAgencia() != null) {
            Agencia agencia = agenciaBO.buscar(interrupcion.getCodigoAgencia());
            parametrosReporte.put("NOMBRE_AGENCIA", agencia.getCodigoAgencia() + " - " + agencia.getNombreAgencia());
        } else
            parametrosReporte.put("NOMBRE_AGENCIA", "NINGUNA");
        
        parametrosReporte.put("NOMBRE_TIPOVOLTAJE",tipoVoltaje.getCodigoDescripcion());
        parametrosReporte.put("NOMBRE_PROTECCION",proteccion.getCodigoProteccion() + " - " + proteccion.getNombreProteccion());
        parametrosReporte.put("NOMBRE_MATERIAL",material.getCodigoMaterial() + " - " + material.getNombreMaterial());
        parametrosReporte.put("NOMBRE_DANO",dano.getCodigoDano() + " - " + dano.getNombreDano());
        parametrosReporte.put("NOMBRE_CAUSA1",causa1.getCodigoCausa() + " - " + causa1.getNombreCausa());
        
        
        if (interrupcion.getCausa2() != null) {
            Causa causa2 = new Causa();
            causa2 = causaBO.buscar(interrupcion.getCausa2());
            parametrosReporte.put("NOMBRE_CAUSA2", causa2.getCodigoCausa() + " - " + causa2.getNombreCausa());
        } else
            parametrosReporte.put("NOMBRE_CAUSA2", "NINGUNA");
        
        if (interrupcion.getCodigoAnimal() != null) {
            Animal animal = new Animal();
            animal = animalBO.buscar(interrupcion.getCodigoAnimal());
            parametrosReporte.put("NOMBRE_ANIMAL", animal.getNombreAnimal());
        } else
            parametrosReporte.put("NOMBRE_ANIMAL", "NINGUNO");
        
        jrDataSource = new JRBeanCollectionDataSource(interrupciones);
        
        if (this.runReport(JasperPath + nombreArchivoJasper, nombreArchivo, parametrosReporte,interrupciones , UtilidadesFaces.getCurrentUserId())) {
            this.addInfo(null, "reporteEjecutado");
            return "success";
        } else {
            this.addError(null, "reporteError");
        }
        return "success";

	}	
	
	 private Connection getConnection(){
	        DataSource data = (DataSource)Utilidades.getContextVariable("jdbc/interrupciones2DS");
	        Connection conn = null;
	        try {
	            conn = data.getConnection();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
	        }
	        return conn;
	    }

	/**
	 * Obtiene la interrupción deseada de la base de datos y los parametros relacionados que se enviarán y mostrarán
	 * en el reporte para la interrupción menor a cinco minutos.  En este caso se colocan en la sesion para que luego
	 * el servlet que genera el reporte se encargue de enviarle los parámetros correspondientes.	 
	 */	
	private String cargarDatosMenorCincoMin(){
	    FacesContext ctx = FacesContext.getCurrentInstance();
	    HashMap parametrosReporte = new HashMap();
	    
		ReporteID reporteID = new ReporteID();
		reporteID.setNumeroReporte(this.consecutivo);
		reporteID.setCodigoOficina(this.oficina);
		reporteID.setAa(this.ano);
		Reporte reporte = this.reporteBO.getReporte(reporteID);
		
		
		reporte.setFechaInicio(UtilidadesI.getFechaCompleta(reporte.getFechaInicio(), reporte.getHoraInicio().doubleValue()));		
		reporte.setFechaFin(UtilidadesI.getFechaCompleta(reporte.getFechaFin(), reporte.getHoraFin().doubleValue()));
		
		
		CuadrillaID cuadrillaID = new CuadrillaID();
		cuadrillaID.setAa(this.ano);
		cuadrillaID.setCodigoOficina(this.oficina);
		cuadrillaID.setNumeroInterrupcion(this.consecutivo);
		cuadrillaID.setReporteInterrupcion(new Integer(0));		
		List cuadrillas = this.cuadrillaBO.getCuadrillas(cuadrillaID);
		List cuadrillasRes = new ArrayList();
		if(cuadrillas != null){
		    for(int i = 0; i < cuadrillas.size(); i++){
		        Cuadrilla cuadrilla = (Cuadrilla)cuadrillas.get(i);
		        cuadrilla.setCedula(cuadrilla.getCuadrillaID().getCedula());
		        cuadrillasRes.add(cuadrilla);
		    }
		}
		cuadrillas = new ArrayList(cuadrillasRes);		
		
		Vehiculo vehiculo = new Vehiculo();
		VehiculoID vehiculoID = new VehiculoID();
		vehiculoID.setAa(this.ano);
		vehiculoID.setCodigoOficina(this.oficina);
		vehiculoID.setNumeroInterrupcion(this.consecutivo);
		vehiculoID.setTipo(new Integer(1));
		vehiculo.setVehiculoID(vehiculoID);
		vehiculo = this.vehiculoBO.buscar(vehiculo);
		if(vehiculo != null)
            vehiculo.setNumeroVehiculo(vehiculo.getVehiculoID().getNumeroVehiculo());
		
		
	    String consecutivoInterrupcion = this.ano + " - " + this.oficina + " - " + this.consecutivo;	    
//	    ctx.getExternalContext().getSessionMap().put("consecutivoInterrupcion",consecutivoInterrupcion);
//	    ctx.getExternalContext().getSessionMap().put("reporte",reporte);
//	    ctx.getExternalContext().getSessionMap().put("cuadrillas",cuadrillas);
//	    ctx.getExternalContext().getSessionMap().put("vehiculo",vehiculo);
	    
	    //ctx.getExternalContext().getSessionMap().put("region",regionBO.buscar(reporte.getRegion()));
	    SubRegionID subID = new SubRegionID();
	    Region reg = new Region();
	    reg.setRegion(reporte.getRegion());
	    reg = regionBO.buscar(reporte.getRegion());
	    subID.setSubRegion(reporte.getSubRegion());
	    subID.setRegion(reg);
	    SubRegion subReg = new SubRegion();
        subReg = subRegionBO.buscar(subID);
	    
	    //ctx.getExternalContext().getSessionMap().put("subregion",subRegionBO.buscar(subID));		    
	    
	    SubEstacion sub = new SubEstacion();
        sub = subEstacionBO.buscar(reporte.getSubEstacion());
	   // ctx.getExternalContext().getSessionMap().put("subEstacion",subEstacionBO.buscar(reporte.getSubEstacion()));
	    
	    Circuito cir = new Circuito();
        cir  = circuitoBO.buscar(reporte.getSubEstacion(),reporte.getCircuito());
	   // ctx.getExternalContext().getSessionMap().put("circuito",circuitoBO.buscar(reporte.getSubEstacion(),reporte.getCircuito()));
	    
		SeccionID seccionID = new SeccionID();
		seccionID.setSubEstacion(reporte.getSubEstacion());
		seccionID.setCircuito(reporte.getCircuito());
		seccionID.setSeccion(reporte.getSeccion());		
		
		Seccion sec = new Seccion();
        sec = seccionBO.buscar(seccionID);
        
//	    ctx.getExternalContext().getSessionMap().put("seccion",seccionBO.buscar(seccionID));
//	    ctx.getExternalContext().getSessionMap().put("empleado",empleadoBO.buscar(reporte.getOperador()));
	    
	    Oficina ofic = new Oficina();
        ofic = oficinaBO.buscar(this.oficina);
	    //ctx.getExternalContext().getSessionMap().put("oficina",oficinaBO.buscar(this.oficina));
	    
		Pueblo puebloFalla = new Pueblo();
		puebloFalla.setNumero(reporte.getCodigoPueblo());		
	  //  ctx.getExternalContext().getSessionMap().put("puebloFalla",obtenerPueblo(puebloFalla));
	    
		Pueblo puebloEquipo = new Pueblo();
		puebloEquipo.setNumero(reporte.getCodigoPuebloEquipo());			   
//	    ctx.getExternalContext().getSessionMap().put("puebloEquipo",obtenerPueblo(puebloEquipo));
//	    if(reporte.getCodigoAgencia() != null)
//	        ctx.getExternalContext().getSessionMap().put("agencia",agenciaBO.buscar(reporte.getCodigoAgencia()));
//	    else
//	        ctx.getExternalContext().getSessionMap().put("agencia",null);
	    
		TipoVoltajeID tipoVoltajeID = new TipoVoltajeID();
		tipoVoltajeID.setCodigoVoltaje(reporte.getCodigoVoltaje());
		tipoVoltajeID.setTipoVoltaje(reporte.getTipoVoltaje());	
		
		TipoVoltaje tipoVoltaje= new TipoVoltaje();
        if(tipoVoltajeID != null)
        tipoVoltaje = tipoVoltajeBO.buscar(tipoVoltajeID);
        
        Proteccion proteccion = new Proteccion();
        if(reporte.getCodigoProteccion() != null)
        proteccion = proteccionBO.buscar(reporte.getCodigoProteccion());
        
        Material material = new Material();
        if(reporte.getCodigoMaterial() != null)
        material = materialBO.buscar(reporte.getCodigoMaterial());
        
        Dano dano = new Dano();
        if(reporte.getCodigoDano() != null)
        dano = danoBO.buscar(reporte.getCodigoDano());
        
//		ctx.getExternalContext().getSessionMap().put("tipoVoltaje",tipoVoltajeBO.buscar(tipoVoltajeID));
//		ctx.getExternalContext().getSessionMap().put("proteccion",proteccionBO.buscar(reporte.getCodigoProteccion()));
//		ctx.getExternalContext().getSessionMap().put("material",materialBO.buscar(reporte.getCodigoMaterial()));
//		ctx.getExternalContext().getSessionMap().put("dano",danoBO.buscar(reporte.getCodigoDano()));
		
		Causa causa1 = new Causa();
        if(reporte.getCausa1() != null)
        causa1 = causaBO.buscar(reporte.getCausa1());
        
//		ctx.getExternalContext().getSessionMap().put("causa1",causaBO.buscar(reporte.getCausa1()));
//		if(reporte.getCausa2() != null)
//		    ctx.getExternalContext().getSessionMap().put("causa2",causaBO.buscar(reporte.getCausa2()));
//		else
//		    ctx.getExternalContext().getSessionMap().put("causa2",null);
//		if(reporte.getCodigoAnimal() != null)
//		    ctx.getExternalContext().getSessionMap().put("animal",animalBO.buscar(reporte.getCodigoAnimal()));
//		else
//		    ctx.getExternalContext().getSessionMap().put("animal",null);	
		
		 String nombreArchivo = "reporteMenorCincoMin";
	        Date fechaActual = new Date();
	        SimpleDateFormat sf = new SimpleDateFormat();
	        sf.applyPattern("dd-MM-yyyy");                                
	        String fechaActualSTR = sf.format(fechaActual);
	        nombreArchivo += fechaActualSTR;
	        
	        String nombreArchivoJasper = "SigeItrRepMenorCincoMin.jasper";
	        
	        
	        //parametros del reporte
	        String ServletJasperPath = "/jasperReports/";
	        parametrosReporte.put("SUBREPORT_DIR",ServletJasperPath);
        
	        List<Serializable> vehiculos = new ArrayList<Serializable>();
            vehiculos.add(vehiculo);
            List<Serializable> reportes = new ArrayList<Serializable>();
            reportes.add(reporte);
            
            parametrosReporte.put("CONSECUTIVO",this.ano + "-" + this.oficina + "-" + this.consecutivo);
            parametrosReporte.put("CUADRILLA", new JRBeanCollectionDataSource(cuadrillas));
      
            if(vehiculo != null)
                parametrosReporte.put("VEHICULO",new JRBeanCollectionDataSource(vehiculos));
            else
                parametrosReporte.put("VEHICULO",new JRBeanCollectionDataSource(new ArrayList()));
            
            parametrosReporte.put("ANO",this.ano);
            parametrosReporte.put("OFICINA",this.oficina);
            parametrosReporte.put("NUMERO_REPORTE",this.consecutivo);
            
            parametrosReporte.put("NOMBRE_REGION",reg.getRegion() + " - " + reg.getNombreRegion());
            parametrosReporte.put("NOMBRE_SUBREGION",subReg.getSubRegionID().getSubRegion() + " - " + subReg.getNombreSubRegion());
            parametrosReporte.put("NOMBRE_SUBESTACION",sub.getCodigoSubEstacion() + " - " + sub.getNombreSubEstacion());  
            parametrosReporte.put("NOMBRE_CIRCUITO",cir.getCircuitoID().getCircuito() + " - " + cir.getNombreCircuito());
            parametrosReporte.put("NOMBRE_SECCION",sec.getSeccionID().getSeccion() + " - " + sec.getNombreSeccion());
            
            if (reporte.getOperador() != null) {
                Empleado emp = empleadoBO.buscar(reporte.getOperador());
                parametrosReporte.put("NOMBRE_EMPLEADO", emp.getNombreCompleto());
            } else
                parametrosReporte.put("NOMBRE_EMPLEADO", "INHABILITADO");
            
            parametrosReporte.put("NOMBRE_OFICINA",ofic.getNombreOficina());
            
            if (reporte.getCodigoPueblo() != null) {
                puebloFalla = obtenerPueblo(puebloFalla);
                parametrosReporte.put("NOMBRE_PUEBLOFALLA", puebloFalla.getNombre());
            } else
                parametrosReporte.put("NOMBRE_PUEBLOFALLA", "DESCONOCIDO");
            
            if (reporte.getCodigoPuebloEquipo() != null) {
                puebloEquipo = obtenerPueblo(puebloEquipo);
                parametrosReporte.put("NOMBRE_PUEBLOEQUIPO", puebloEquipo.getNombre());
            } else
                parametrosReporte.put("NOMBRE_PUEBLOEQUIPO", "DESCONOCIDO");
            
            if (reporte.getCodigoAgencia() != null) {
                Agencia agencia = agenciaBO.buscar(reporte.getCodigoAgencia());
                parametrosReporte.put("NOMBRE_AGENCIA", agencia.getCodigoAgencia() + " - " + agencia.getNombreAgencia());
            } else
                parametrosReporte.put("NOMBRE_AGENCIA", "NINGUNA");
            
            parametrosReporte.put("NOMBRE_TIPOVOLTAJE",tipoVoltaje.getCodigoDescripcion());
            parametrosReporte.put("NOMBRE_PROTECCION",proteccion.getCodigoProteccion() + " - " + proteccion.getNombreProteccion());
            parametrosReporte.put("NOMBRE_MATERIAL",material.getCodigoMaterial() + " - " + material.getNombreMaterial());
            parametrosReporte.put("NOMBRE_DANO",dano.getCodigoDano() + " - " + dano.getNombreDano());
            parametrosReporte.put("NOMBRE_CAUSA1",causa1.getCodigoCausa() + " - " + causa1.getNombreCausa());
            
            parametrosReporte.put("TENSION",reporte.getIndTension());
            
            if (reporte.getCausa2() != null) {
                Causa causa2 = new Causa();
                causa2 = causaBO.buscar(reporte.getCausa2());
                parametrosReporte.put("NOMBRE_CAUSA2", causa2.getCodigoCausa() + " - " + causa2.getNombreCausa());
            } else
                parametrosReporte.put("NOMBRE_CAUSA2", "NINGUNA");
            
            if (reporte.getCodigoAnimal() != null) {
                Animal animal = new Animal();
                animal = animalBO.buscar(reporte.getCodigoAnimal());
                parametrosReporte.put("NOMBRE_ANIMAL", animal.getNombreAnimal());
            } else
                parametrosReporte.put("NOMBRE_ANIMAL", "NINGUNO");
            
            if (this.runReport(JasperPath + nombreArchivoJasper, nombreArchivo, parametrosReporte, reportes, UtilidadesFaces.getCurrentUserId())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Reporte Ejecutado."));
                return "success";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Reporte Error."));
            }
            return "success";
	    	    
	}
	
	/**
	 * Cancela el proceso de impresión
	 * @return success para cancelar el proceso de impresión
	 */
	public String cancelar(){
		return "success";
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
     * Metodo accesor de cuadrillaBO.
     * @return Retorna el cuadrillaBO.
     */
    public CuadrillaBO getCuadrillaBO() {
        return cuadrillaBO;
    }
    /**
     * Metodo modificador de cuadrillaBO.
     * @param cuadrillaBO El cuadrillaBO a modificar.
     */
    public void setCuadrillaBO(CuadrillaBO cuadrillaBO) {
        this.cuadrillaBO = cuadrillaBO;
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
     * Metodo accesor de agenciaBO.
     * @return Retorna el agenciaBO.
     */
    public AgenciaBO getAgenciaBO() {
        return agenciaBO;
    }
    /**
     * Metodo modificador de agenciaBO.
     * @param agenciaBO El agenciaBO a modificar.
     */
    public void setAgenciaBO(AgenciaBO agenciaBO) {
        this.agenciaBO = agenciaBO;
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
     * Metodo accesor de circuitoBO.
     * @return Retorna el circuitoBO.
     */
    public CircuitoBO getCircuitoBO() {
        return circuitoBO;
    }
    /**
     * Metodo modificador de circuitoBO.
     * @param circuitoBO El circuitoBO a modificar.
     */
    public void setCircuitoBO(CircuitoBO circuitoBO) {
        this.circuitoBO = circuitoBO;
    }
    /**
     * Metodo accesor de danoBO.
     * @return Retorna el danoBO.
     */
    public DanoBO getDanoBO() {
        return danoBO;
    }
    /**
     * Metodo modificador de danoBO.
     * @param danoBO El danoBO a modificar.
     */
    public void setDanoBO(DanoBO danoBO) {
        this.danoBO = danoBO;
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
     * Metodo accesor de materialBO.
     * @return Retorna el materialBO.
     */
    public MaterialBO getMaterialBO() {
        return materialBO;
    }
    /**
     * Metodo modificador de materialBO.
     * @param materialBO El materialBO a modificar.
     */
    public void setMaterialBO(MaterialBO materialBO) {
        this.materialBO = materialBO;
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
     * Metodo accesor de proteccionBO.
     * @return Retorna el proteccionBO.
     */
    public ProteccionBO getProteccionBO() {
        return proteccionBO;
    }
    /**
     * Metodo modificador de proteccionBO.
     * @param proteccionBO El proteccionBO a modificar.
     */
    public void setProteccionBO(ProteccionBO proteccionBO) {
        this.proteccionBO = proteccionBO;
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
     * Metodo accesor de subEstacionBO.
     * @return Retorna el subEstacionBO.
     */
    public SubEstacionBO getSubEstacionBO() {
        return subEstacionBO;
    }
    /**
     * Metodo modificador de subEstacionBO.
     * @param subEstacionBO El subEstacionBO a modificar.
     */
    public void setSubEstacionBO(SubEstacionBO subEstacionBO) {
        this.subEstacionBO = subEstacionBO;
    }
    /**
     * Metodo accesor de tipoVoltajeBO.
     * @return Retorna el tipoVoltajeBO.
     */
    public TipoVoltajeBO getTipoVoltajeBO() {
        return tipoVoltajeBO;
    }
    /**
     * Metodo modificador de tipoVoltajeBO.
     * @param tipoVoltajeBO El tipoVoltajeBO a modificar.
     */
    public void setTipoVoltajeBO(TipoVoltajeBO tipoVoltajeBO) {
        this.tipoVoltajeBO = tipoVoltajeBO;
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
    public void setInterrupcionGemelaBO(InterrupcionGemelaBO interrupcionGemelaBO) {
        this.interrupcionGemelaBO = interrupcionGemelaBO;
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
    public void setNoPropiaSeccionamientoBO(NoPropiaSeccionamientoBO noPropiaSeccionamientoBO) {
        this.noPropiaSeccionamientoBO = noPropiaSeccionamientoBO;
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
    public void setPosteInstaladoRetiradoBO(PosteInstaladoRetiradoBO posteInstaladoRetiradoBO) {
        this.posteInstaladoRetiradoBO = posteInstaladoRetiradoBO;
    }
    /**
     * Metodo accesor de trafoBO.
     * @return Retorna el trafoBO.
     */
    public TrafoBO getTrafoBO() {
        return trafoBO;
    }
    /**
     * Metodo modificador de trafoBO.
     * @param trafoBO El trafoBO a modificar.
     */
    public void setTrafoBO(TrafoBO trafoBO) {
        this.trafoBO = trafoBO;
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
     * Metodo accesor de subRegionBO.
     * @return Retorna el subRegionBO.
     */
    public SubRegionBO getSubRegionBO() {
        return this.subRegionBO;
    }
    /**
     * Metodo modificador de subRegionBO.
     * @param subRegionBO El subRegionBO a modificar.
     */
    public void setSubRegionBO(SubRegionBO subRegionBO) {
        this.subRegionBO = subRegionBO;
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

    /**
     * Método accesor del atributo formato.
     * @return Retorna el atributo formato.
     */
    public Integer getFormato() {
        return this.formato;
    }

    /**
     * Método modificador del atributo formato.
     * @param formato El dato para modificar el atributo formato.
     */
    public void setFormato(Integer formato) {
        this.formato = formato;
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

	public UsuarioOficinaBO getUsuarioOficinaBO() {
		return usuarioOficinaBO;
	}

	public void setUsuarioOficinaBO(UsuarioOficinaBO usuarioOficinaBO) {
		this.usuarioOficinaBO = usuarioOficinaBO;
	}
    
}
