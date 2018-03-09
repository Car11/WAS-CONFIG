package cr.go.ice.interrupciones.web.controller;

import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.CircuitoBO;
import cr.go.ice.interrupciones.BO.InterrupcionBO;
import cr.go.ice.interrupciones.BO.OficinaBO;
import cr.go.ice.interrupciones.BO.RegionBO;
import cr.go.ice.interrupciones.BO.ReporteBO;
import cr.go.ice.interrupciones.BO.SeccionBO;
import cr.go.ice.interrupciones.BO.SubEstacionBO;
import cr.go.ice.interrupciones.BO.SubRegionBO;
import cr.go.ice.interrupciones.domain.Circuito;
import cr.go.ice.interrupciones.domain.Oficina;
import cr.go.ice.interrupciones.domain.Region;
import cr.go.ice.interrupciones.domain.Seccion;
import cr.go.ice.interrupciones.domain.SeccionID;
import cr.go.ice.interrupciones.domain.SubEstacion;
import cr.go.ice.interrupciones.domain.SubRegion;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.SeccionController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SeccionController.java</code> Establese la relacion entre capa de presentacion y la logica del negocio.</p>
 * <p>Fecha creación: 21/02/2007</p>
 * <p>Ultima actualización: 21/02/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class SeccionController extends AbstractFacesController {


	/**
	 * <code>seccionBO</code> Seccion BO 
	 */
	private SeccionBO seccionBO;

	/**
	 * <code>regionBO</code> Regio BO
	 */
	private RegionBO regionBO;
	/**
	 * <code>subRegionBO</code> Ub Region BO
	 */
	private SubRegionBO subRegionBO;
	/**
	 * <code>subEstacionBO</code> Sub Estacion BO
	 */
	private SubEstacionBO subEstacionBO;
	/**
	 * <code>circuitoBO</code> Circuito
	 */
	private CircuitoBO circuitoBO;
	/**
	 * <code>oficinaBO</code> Oficina BO
	 */
	private OficinaBO oficinaBO;
	
	private InterrupcionBO interrupcionBO;
	private ReporteBO reporteBO;
		
	/**
	 * <code>region</code> Region
	 */
	private Integer region;
	/**
	 * <code>subRegion</code> Sub Region
	 */
	private Integer subRegion;
	/**
	 * <code>subEstacion</code> Sub Estacion
	 */
	private Integer subEstacion;
	/**
	 * <code>circuito</code> Circuitos
	 */
	private Integer circuito;
	/**
	 * <code>oficina</code> Oficina
	 */
	private Integer oficina;
	/**
	 * <code>numeroSeccion</code> Numero Seccion
	 */
	private Long numeroSeccion;
	/**
	 * <code>nombreSeccion</code> Nombre Seccion
	 */
	private String nombreSeccion;
	/**
	 * <code>numeroClientes</code> Numero Cliente
	 */
	private Long numeroClientes;
	/**
	 * <code>numeroKilometros</code> Numero Kilometros
	 */
	private Double numeroKilometros;
	/**
	 * <code>estado</code> Estado
	 */
	private Integer estado;

	/**
	 * <code>subEstacionOriginal</code> Sub Estacion Original
	 */
	private Integer subEstacionOriginal;
	/**
	 * <code>circuitoOriginal</code> Circuito Original
	 */
	private Integer circuitoOriginal;
	/**
	 * <code>numeroSeccionOriginal</code> Numero Seccion Original
	 */
	private Long numeroSeccionOriginal;
	
	
	
	/**
     * Método accesor del atributo interrupcionBO.
     * @return Retorna el atributo interrupcionBO.
     */
    public InterrupcionBO getInterrupcionBO() {
        return this.interrupcionBO;
    }
    /**
     * Método modificador del atributo interrupcionBO.
     * @param interrupcionBO El dato para modificar el atributo interrupcionBO.
     */
    public void setInterrupcionBO(InterrupcionBO interrupcionBO) {
        this.interrupcionBO = interrupcionBO;
    }
    /**
     * Método accesor del atributo reporteBO.
     * @return Retorna el atributo reporteBO.
     */
    public ReporteBO getReporteBO() {
        return this.reporteBO;
    }
    /**
     * Método modificador del atributo reporteBO.
     * @param reporteBO El dato para modificar el atributo reporteBO.
     */
    public void setReporteBO(ReporteBO reporteBO) {
        this.reporteBO = reporteBO;
    }
    /**
	 * @return Returns the circuito.
	 */
	public Integer getCircuito() {
		return circuito;
	}
	/**
	 * @param circuito The circuito to set.
	 */
	public void setCircuito(Integer circuito) {
		this.circuito = circuito;
	}
	/**
	 * @return Returns the circuitoBO.
	 */
	public CircuitoBO getCircuitoBO() {
		return circuitoBO;
	}
	/**
	 * @param circuitoBO The circuitoBO to set.
	 */
	public void setCircuitoBO(CircuitoBO circuitoBO) {
		this.circuitoBO = circuitoBO;
	}
	/**
	 * @return Returns the estado.
	 */
	public Integer getEstado() {
		return estado;
	}
	/**
	 * @param estado The estado to set.
	 */
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	/**
	 * @return Returns the nombreSeccion.
	 */
	public String getNombreSeccion() {
		return nombreSeccion;
	}
	/**
	 * @param nombreSeccion The nombreSeccion to set.
	 */
	public void setNombreSeccion(String nombreSeccion) {
		this.nombreSeccion = nombreSeccion;
	}
	/**
	 * @return Returns the numeroClientes.
	 */
	public Long getNumeroClientes() {
		return numeroClientes;
	}
	/**
	 * @param numeroClientes The numeroClientes to set.
	 */
	public void setNumeroClientes(Long numeroClientes) {
		this.numeroClientes = numeroClientes;
	}
	/**
	 * @return Returns the numeroKilometros.
	 */
	public Double getNumeroKilometros() {
		return numeroKilometros;
	}
	/**
	 * @param numeroKilometros The numeroKilometros to set.
	 */
	public void setNumeroKilometros(Double numeroKilometros) {
		this.numeroKilometros = numeroKilometros;
	}
	/**
	 * @return Returns the numeroSeccion.
	 */
	public Long getNumeroSeccion() {
		return numeroSeccion;
	}
	/**
	 * @param numeroSeccion The numeroSeccion to set.
	 */
	public void setNumeroSeccion(Long numeroSeccion) {
		this.numeroSeccion = numeroSeccion;
	}
	/**
	 * @return Returns the oficina.
	 */
	public Integer getOficina() {
		return oficina;
	}
	/**
	 * @param oficina The oficina to set.
	 */
	public void setOficina(Integer oficina) {
		this.oficina = oficina;
	}
	/**
	 * @return Returns the oficinaBO.
	 */
	public OficinaBO getOficinaBO() {
		return oficinaBO;
	}
	/**
	 * @param oficinaBO The oficinaBO to set.
	 */
	public void setOficinaBO(OficinaBO oficinaBO) {
		this.oficinaBO = oficinaBO;
	}
	/**
	 * @return Returns the region.
	 */
	public Integer getRegion() {
		return region;
	}
	/**
	 * @param region The region to set.
	 */
	public void setRegion(Integer region) {
		this.region = region;
	}
	/**
	 * @return Returns the regionBO.
	 */
	public RegionBO getRegionBO() {
		return regionBO;
	}
	/**
	 * @param regionBO The regionBO to set.
	 */
	public void setRegionBO(RegionBO regionBO) {
		this.regionBO = regionBO;
	}
	/**
	 * @return Returns the seccionBO.
	 */
	public SeccionBO getSeccionBO() {
		return seccionBO;
	}
	/**
	 * @param seccionBO The seccionBO to set.
	 */
	public void setSeccionBO(SeccionBO seccionBO) {
		this.seccionBO = seccionBO;
	}
	/**
	 * @return Returns the subEstacion.
	 */
	public Integer getSubEstacion() {
		return subEstacion;
	}
	/**
	 * @param subEstacion The subEstacion to set.
	 */
	public void setSubEstacion(Integer subEstacion) {
		this.subEstacion = subEstacion;
	}
	/**
	 * @return Returns the subEstacionBO.
	 */
	public SubEstacionBO getSubEstacionBO() {
		return subEstacionBO;
	}
	/**
	 * @param subEstacionBO The subEstacionBO to set.
	 */
	public void setSubEstacionBO(SubEstacionBO subEstacionBO) {
		this.subEstacionBO = subEstacionBO;
	}
	/**
	 * @return Returns the subRegion.
	 */
	public Integer getSubRegion() {
		return subRegion;
	}
	/**
	 * @param subRegion The subRegion to set.
	 */
	public void setSubRegion(Integer subRegion) {
		this.subRegion = subRegion;
	}
	/**
	 * @return Returns the subRegionBO.
	 */
	public SubRegionBO getSubRegionBO() {
		return subRegionBO;
	}
	/**
	 * @param subRegionBO The subRegionBO to set.
	 */
	public void setSubRegionBO(SubRegionBO subRegionBO) {
		this.subRegionBO = subRegionBO;
	}	
		
	/**
	 * @return Returns the circuitoOriginal.
	 */
	public Integer getCircuitoOriginal() {
		return circuitoOriginal;
	}
	/**
	 * @param circuitoOriginal The circuitoOriginal to set.
	 */
	public void setCircuitoOriginal(Integer circuitoOriginal) {
		this.circuitoOriginal = circuitoOriginal;
	}
	/**
	 * @return Returns the numeroSeccionOriginal.
	 */
	public Long getNumeroSeccionOriginal() {
		return numeroSeccionOriginal;
	}
	/**
	 * @param numeroSeccionOriginal The numeroSeccionOriginal to set.
	 */
	public void setNumeroSeccionOriginal(Long numeroSeccionOriginal) {
		this.numeroSeccionOriginal = numeroSeccionOriginal;
	}
	/**
	 * @return Returns the subEstacionOriginal.
	 */
	public Integer getSubEstacionOriginal() {
		return subEstacionOriginal;
	}
	/**
	 * @param subEstacionOriginal The subEstacionOriginal to set.
	 */
	public void setSubEstacionOriginal(Integer subEstacionOriginal) {
		this.subEstacionOriginal = subEstacionOriginal;
	}
	
	/**
	 * Constructor
	 */
	public SeccionController() {
		iniciarValores();
	}

	
	/**
	 * Comment for iniciarValores
	 * 
	 */
	public void iniciarValores()
	{
		region = new Integer(0);
		subRegion = new Integer(0);
		subEstacion = new Integer(0);
		circuito = new Integer(0);
		oficina = new Integer(0);
		numeroSeccion = null;
		nombreSeccion = null;
		numeroClientes = null;
		numeroKilometros = null;
		estado = null;    
	}
	
	/**
     * @param context
     */
    public void load(FacesContext context){
      boolean userClor = true;
        Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
        
        if(userClor && limpiar != null){
            this.iniciarValores();
        }   
    }
	

	/**
	 * Comment for getRegiones
	 * @return Lista Regiones
	 */
	public List getRegiones() {
		Vector vRegiones = new Vector();

		List lRegiones = this.regionBO.getRegionesPorOficinaPorSubEstacionSubRegion(this.oficina);
		
		vRegiones.add(new SelectItem(new Integer(0), "Seleccione una región"));
		
		for (int i = 0; i < lRegiones.size(); i++) {
			Region region = (Region) lRegiones.get(i);
			vRegiones.add(new SelectItem(region.getRegion(), region.getRegion()+" - "+region.getNombreRegion()));
		}
		
		if(this.region==null && lRegiones.size() > 0){
			Region region = (Region) lRegiones.get(0);
			this.region = region.getRegion();
		}
		return vRegiones;
	}

	/**
	 * Comment for getSubRegiones
	 * @return Lista Sub Regiones
	 */
	public List getSubRegiones() {
		Vector vsubRegiones = new Vector();

		List lSubRegiones = this.subRegionBO.getSubRegionesPorOficinaPorSubEstacionSubRegion(this.oficina, this.region);		
		
		for (int i=0; i < lSubRegiones.size(); i++) {
			SubRegion subregion = (SubRegion) lSubRegiones.get(i);
			vsubRegiones.add(new SelectItem(subregion.getSubRegionID().getSubRegion(), subregion.getSubRegionID().getSubRegion()+" - "+subregion.getNombreSubRegion()));
		}
		return vsubRegiones;		
	}

	/**
	 * Comment for getSubEstaciones
	 * @return Lista Sub Estaciones
	 */
	public List getSubEstaciones() {
		Vector vsubEstaciones = new Vector();

		List lSubEstaciones = this.subEstacionBO.getSubEstacionesSubRegionOficina(this.region, this.subRegion, this.oficina);

		for (int i=0; i < lSubEstaciones.size(); i++) {
			SubEstacion xsubestacion = (SubEstacion) lSubEstaciones.get(i);
			vsubEstaciones.add(new SelectItem(xsubestacion.getCodigoSubEstacion(), xsubestacion.getCodigoSubEstacion()+" - "+xsubestacion.getNombreSubEstacion()));
		}
		return vsubEstaciones;
	}

	/**
	 * Comment for getCircuitos
	 * @return Lista de Circuitos
	 */
	public List getCircuitos() {
		Vector vcircuitos = new Vector();

		List lCircuitos = circuitoBO.getCircuitos(this.subEstacion);
		for (int i = 0; i < lCircuitos.size(); i++) {
			Circuito xcircuito = (Circuito) lCircuitos.get(i);
			vcircuitos.add(new SelectItem(xcircuito.getCircuitoID().getCircuito(), xcircuito.getCircuitoID().getCircuito()+" - "+xcircuito.getNombreCircuito()));
		}
		return vcircuitos;
	}

	/**
	 * Comment for getOficinas
	 * @return Lista Oficinas 
	 */
	public List getOficinas() {
		Vector voficinas = new Vector();
		List loficinas = oficinaBO.getOficinas();
		for (int i = 0; i < loficinas.size(); i++) {
			Oficina xoficina = (Oficina) loficinas.get(i);
			voficinas.add(new SelectItem(xoficina.getCodigoOficina(), xoficina.getCodigoOficina()+" - "+xoficina.getNombreOficina()));
		}
		return voficinas;
	}

	/**
	 * Comment for getEstados
	 * @return Lista Estado
	 */
	public List getEstados() {
		List estados = new Vector();
		estados.add(new SelectItem(new Integer(0), "Activo"));
		estados.add(new SelectItem(new Integer(1), "Inactivo"));
		estados.add(new SelectItem(new Integer(2), "Cambio Sección"));
		return estados;
	}

    /**
     * Metodo para getCero
     * @return
     */
	public Integer getCero(){
		return new Integer(0);
	}
	
	/**
	 * Comment for buscar
	 * @return "success" o "error" al buscar Seccion
	 */
	public String buscar() {
		boolean correcto = true;
		if (this.numeroSeccion == null || this.numeroSeccion.intValue() == 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
				"Debe digitar un número de sección para realizar la búsqueda."));
            correcto = false;
		}
		if (this.subEstacion == null || this.subEstacion.intValue() == 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
				"Debe seleccionar una Subestación para realizar la búsqueda."));
            correcto = false;
		}
		if (this.circuito == null || this.circuito.intValue() == 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
				"Debe seleccionar un circuito para realizar la búsqueda."));
            correcto = false;
		}
		if (correcto) {
			SeccionID seccionID = new SeccionID();
			seccionID.setSeccion(this.numeroSeccion);
			seccionID.setSubEstacion(this.subEstacion);
			seccionID.setCircuito(this.circuito);

			Seccion seccion = this.seccionBO.buscar(seccionID);
			if (seccion != null) {
				this.region = seccion.getRegion();
				this.subRegion = seccion.getSubRegion();
				this.oficina = seccion.getCodigoOficina();
				this.subEstacion = seccion.getSeccionID().getSubEstacion();
				this.subEstacionOriginal = seccion.getSeccionID().getSubEstacion();
				this.circuito = seccion.getSeccionID().getCircuito();
				this.circuitoOriginal = seccion.getSeccionID().getCircuito();
				this.numeroSeccion = seccion.getSeccionID().getSeccion();
				this.numeroSeccionOriginal = seccion.getSeccionID().getSeccion();
				this.nombreSeccion = seccion.getNombreSeccion();
				this.numeroClientes = seccion.getAbonadoSeccion();
				this.numeroKilometros = seccion.getKmsSeccion();
				this.estado = seccion.getCongelado();
				return "success";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"La sección no existe."));
				return "error";
			}
		} else {
			return "error";
		}
	}
	/**
	 * Comment for agregar
	 * @return "success" o "error" al agregar Seccion
	 */
	public String agregar() {
	    try{
    		if(this.validarDatos()){
    			Seccion seccion = new Seccion();
    			SeccionID seccionID = new SeccionID();
    			seccionID.setCircuito(this.circuito);
    			seccionID.setSeccion(this.numeroSeccion);
    			seccionID.setSubEstacion(this.subEstacion);
    			if(this.seccionBO.buscar(seccionID)==null){
    				seccion.setSeccionID(seccionID);
    				
    				seccion.setRegion(this.region);
    				seccion.setSubRegion(this.subRegion);
    				seccion.setCodigoOficina(this.oficina);
    				seccion.setNombreSeccion(this.nombreSeccion.trim().toUpperCase());
    				seccion.setAbonadoSeccion(this.numeroClientes);
    				seccion.setKmsSeccion(this.numeroKilometros);
    				seccion.setCongelado(this.estado);
    				if(seccion.getCongelado().equals(Integer.valueOf(1))){
    					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puede agregar una sección en estado Inactivo."));
    		           return "error";
    				}else{
    				    seccionBO.agregar(seccion); 
    				    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Sección agregada exitosamente."));
                        this.iniciarValores();
                        return "success";
    				}
    			}else{
    				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La sección ya existe."));
    	            
    				return "error";
    			}
    		}else{
    			return "error";
    		}
	    }catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puede agregar una sección en estado Inactivo."));
            
            return "error";
        }
	}

	/**
	 * Comment for modificar
	 * @return "success" o "error" al modificar Seccion
	 */
	public String modificar() {
		try {
    		if (this.subEstacionOriginal != null && this.circuitoOriginal != null && this.numeroSeccionOriginal != null) {
    			if (this.validarDatos()) {
    			    if (this.validarEstado()) {
        				Seccion seccion = new Seccion();
        				SeccionID seccionID = new SeccionID();

        				seccionID.setCircuito(this.circuitoOriginal);
        				seccionID.setSeccion(this.numeroSeccionOriginal);
        				seccionID.setSubEstacion(this.subEstacionOriginal);
        				seccion.setSeccionID(seccionID);

        				seccion.setRegion(this.region);
        				seccion.setSubRegion(this.subRegion);
        				seccion.setCodigoOficina(this.oficina);
        				seccion.setNombreSeccion(this.nombreSeccion.trim().toUpperCase());
        				seccion.setAbonadoSeccion(this.numeroClientes);
        				seccion.setKmsSeccion(this.numeroKilometros);
        				seccion.setCongelado(this.estado);

        				seccionBO.modificar(seccion);

        				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Sección modificada exitosamente."));
        	            this.iniciarValores();
        				return "success";
    			    } else {
    			    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puede realizar el cambio de sección, tiene registros asociados."));
    		           return "error"; 
    			    }
    			} else {
    				return "error";
    			}
    		} else {
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe realizar una busqueda de sección para poder modificar."));
    			return "error";	
    		}
	    } catch (Exception e) {
	        e.printStackTrace();
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puede modificar la sección."));
            return "error";
        }
	}

	/**
	 * Comment for eliminar
	 * @return "success" o "error" al eliminar Seccion
	 */
	public String eliminar() {
	    try{
    		if(this.subEstacionOriginal != null && this.circuitoOriginal != null && this.numeroSeccionOriginal != null){
    		    if(this.validarEstado()){
        			Seccion seccion = new Seccion();
        			SeccionID seccionID = new SeccionID();
        	
        			seccionID.setCircuito(this.circuitoOriginal);
        			seccionID.setSeccion(this.numeroSeccionOriginal);
        			seccionID.setSubEstacion(this.subEstacionOriginal);
        			
        			if(this.seccionBO.registrosAsociados(seccionID).longValue() > 0){
        				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puede eliminar.  Sección se encuentra asociada con diversa información."));
        	           return "error";
        			}
        			else{			
        				seccion.setSeccionID(seccionID);
        				seccionBO.eliminar(seccion);
        				iniciarValores();
        				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Sección eliminada exitosamente."));
        	            return "success";
        			}
    		    }else{
    		    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puede realizar la eliminación, tiene registros asociados."));
    	            return "error";
    		    }
    		}else{
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe realizar una busqueda de sección para poder eliminarla."));
                return "error";	
    		}
	    }catch (Exception e) {
	        e.printStackTrace();
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo eliminar la sección."));
            return "error";
        }
	}
	
	// listeners
	
	/**
	 * Comment for listenerRegion
	 * @param v
	 * @return "listener" a regiones
	 */
	public String listenerRegion() {
		this.subRegion = Integer.valueOf(0);
		this.subEstacion = Integer.valueOf(0);
		this.subEstacionOriginal = Integer.valueOf(0);
		this.circuito = Integer.valueOf(0);
		this.circuitoOriginal = Integer.valueOf(0);
		this.numeroSeccion = Long.valueOf(0);
		this.numeroSeccionOriginal = Long.valueOf(0);
		this.nombreSeccion = "";
		this.numeroClientes = Long.valueOf(0);
		this.numeroKilometros = Double.valueOf(0);
		this.estado = null;
		return "listener";
	}
	
	/**
	 * Comment for listenerSubRegion
	 * @param v
	 * @return "listener" a Sub regiones
	 */
	public String listenerSubRegion(){
		this.subEstacion = Integer.valueOf(0);
        this.subEstacionOriginal = Integer.valueOf(0);
        this.circuito = Integer.valueOf(0);
        this.circuitoOriginal = Integer.valueOf(0);
        this.numeroSeccion = Long.valueOf(0);
        this.numeroSeccionOriginal = Long.valueOf(0);
        this.nombreSeccion = "";
        this.numeroClientes = Long.valueOf(0);
        this.numeroKilometros = Double.valueOf(0);
        this.estado = null;
		return "listener";
	}
	
	/**
	 * Comment for listenerOficina
	 * @param v
	 * @return "listener" a Oficina
	 */
	public String listenerOficina()
	{
		
		this.region = Integer.valueOf(0);
		this.subRegion = Integer.valueOf(0);
        this.subEstacion = Integer.valueOf(0);
        this.subEstacionOriginal = Integer.valueOf(0);
        this.circuito = Integer.valueOf(0);
        this.circuitoOriginal = Integer.valueOf(0);
        this.numeroSeccion = Long.valueOf(0);
        this.numeroSeccionOriginal = Long.valueOf(0);
        this.nombreSeccion = "";
        this.numeroClientes = Long.valueOf(0);
        this.numeroKilometros = Double.valueOf(0);
        this.estado = null;
		return "listener";	
	}	
	
	/**
	 * Comment for listenerSubEstacion
	 * @param v
	 * @return "listener" Sub estacion
	 */
	public String listenerSubEstacion(){
		this.circuito = Integer.valueOf(0);
        this.circuitoOriginal = Integer.valueOf(0);
        this.numeroSeccion = Long.valueOf(0);
        this.numeroSeccionOriginal = Long.valueOf(0);
        this.nombreSeccion = "";
        this.numeroClientes = Long.valueOf(0);
        this.numeroKilometros = Double.valueOf(0);
        this.estado = null;
		return "listener";			
	}
	
	
	public String listenerCircuitos(){
		 this.numeroSeccion = Long.valueOf(0);
        this.numeroSeccionOriginal = Long.valueOf(0);
        this.nombreSeccion = "";
        this.numeroClientes = Long.valueOf(0);
        this.numeroKilometros = Double.valueOf(0);
        this.estado = null;
		return "listener";			
	}
	
	
	/**
	 * Comment for validarDatos
	 * @return correcto al validar los datos
	 */
	public boolean validarDatos(){
		boolean correcto=true;
		if(this.numeroSeccion==null || this.numeroSeccion.compareTo(new Long(0)) == 0 ){
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setDetail("Debe digitar un número de sección");
            FacesContext.getCurrentInstance().addMessage("form1:txtNumeroSeccion",msg);
			correcto = false;	
		}
		if(this.subEstacion==null || this.subEstacion.compareTo(new Integer(0)) == 0 ){
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setDetail("Debe seleccionar una Subestación");
            FacesContext.getCurrentInstance().addMessage("form1:cmdSubEstacion",msg);
			correcto = false;	
		}
		if(this.circuito==null || this.circuito.compareTo(new Integer(0)) == 0 ){
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setDetail("Debe seleccionar un Circuito");
            FacesContext.getCurrentInstance().addMessage("form1:cmdCirciuto",msg);
			correcto = false;	
		}
		if(this.circuito==null || this.circuito.compareTo(new Integer(0)) == 0 ){
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setDetail("Debe seleccionar un Circuito");
            FacesContext.getCurrentInstance().addMessage("form1:cmdCirciuto",msg);
			correcto = false;	
		}
		if(this.region==null || this.region.compareTo(new Integer(0)) == 0 ){
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setDetail("Debe seleccionar una Región");
            FacesContext.getCurrentInstance().addMessage("form1:cmbRegion",msg);
			correcto = false;	
		}
		if(this.subRegion==null || this.subRegion.compareTo(new Integer(0)) == 0 ){
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setDetail("Debe seleccionar una Subregión");
            FacesContext.getCurrentInstance().addMessage("form1:cmdSubRegion",msg);
			correcto = false;	
		}
		if(this.oficina==null || this.oficina.compareTo(new Integer(0)) == 0 ){
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setDetail("Debe seleccionar una Oficina");
            FacesContext.getCurrentInstance().addMessage("form1:cmbOficina",msg);
			correcto = false;	
		}
		if(this.nombreSeccion==null || this.nombreSeccion.trim().length() <= 0){
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setDetail("Debe digitar un Nombre de Sección");
            FacesContext.getCurrentInstance().addMessage("form1:txtNombreSeccion",msg);
			correcto = false;	
		}
		if(this.numeroKilometros==null || this.numeroKilometros.compareTo(new Double(0)) == 0){
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setDetail("Debe digitar el Número de Kilómetros");
            FacesContext.getCurrentInstance().addMessage("form1:txtNumeroKilometros",msg);
			correcto = false;	
		}
		if(this.numeroClientes==null || this.numeroClientes.compareTo(new Long(0))== 0){
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setDetail("Debe digitar el Número de Clientes");
            FacesContext.getCurrentInstance().addMessage("form1:txtNumeroCliente",msg);
			correcto = false;	
		}
		if(this.estado==null){
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setDetail("Debe seleccionar un estado");
            FacesContext.getCurrentInstance().addMessage("form1:radEstado",msg);
			correcto = false;	
		}
		return correcto;
	}
	
	public boolean validarEstado(){
	    boolean correcto = true;
	    if(this.estado.equals(Integer.valueOf(2))){//TODO Usar valores static final
	        if(this.interrupcionBO.existeInterrupciones(this.region, this.subRegion, this.subEstacion, this.circuito, this.numeroSeccion)){
	            correcto = false;
	        }else if(this.reporteBO.existeReportes(this.region, this.subRegion, this.subEstacion, this.circuito, this.numeroSeccion)){
                correcto = false;
            }
	    }
	    return correcto;
	}
    
    /**
     * Cancela
     * @return success
     */
    public String cancelar(){
        return "success";
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
