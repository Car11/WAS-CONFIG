package cr.go.ice.interrupciones.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.CircuitoBO;
import cr.go.ice.interrupciones.BO.HistoricoTrasladoSeccionBO;
import cr.go.ice.interrupciones.BO.OficinaBO;
import cr.go.ice.interrupciones.BO.RegionBO;
import cr.go.ice.interrupciones.BO.SeccionBO;
import cr.go.ice.interrupciones.BO.SubEstacionBO;
import cr.go.ice.interrupciones.BO.SubRegionBO;
import cr.go.ice.interrupciones.domain.Circuito;
import cr.go.ice.interrupciones.domain.HistoricoTrasladoSeccion;
import cr.go.ice.interrupciones.domain.Oficina;
import cr.go.ice.interrupciones.domain.Region;
import cr.go.ice.interrupciones.domain.Seccion;
import cr.go.ice.interrupciones.domain.SeccionID;
import cr.go.ice.interrupciones.domain.SubEstacion;
import cr.go.ice.interrupciones.domain.SubRegion;
import cr.go.ice.interrupciones.domain.SubRegionID;
import cr.go.ice.cia.dominio.UsuarioCia;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.TrasSeccionController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>TrasSeccionController.java</code>.</p>
 * <p>Fecha creación: 04/07/2007</p>
 * <p>Ultima actualización: 04/07/2007</p>
 * @author Vista Verde Tecnologia (Juan Manuel)
 * @version 1.1
 */
public class TrasSeccionController extends AbstractFacesController {
	
	//SeccionID
	private Long seccion;
	private Integer subEstacion;
	private Integer circuito;
	//Seccion
	private Integer region;
	private Integer subRegion;
	private Integer codigoOficina;
	private String nombreSeccion;
	private Double kmsSeccion;
	private Long abonadoSeccion;
	
	//SeccionID
	private Long seccion2;
	private Integer subEstacion2;
	private Integer circuito2;
	//Seccion
	private Integer region2;
	private Integer subRegion2;
	private Integer codigoOficina2;
	private String nombreSeccion2;
	private Double kmsSeccion2;
	private Long abonadoSeccion2;
	
	//BO
	private RegionBO regionBO;
	private SubRegionBO subRegionBO;
	private OficinaBO oficinaBO;
	private SubEstacionBO subEstacionBO;
	private CircuitoBO circuitoBO;
	private SeccionBO seccionBO;
    private HistoricoTrasladoSeccionBO historicoTrasladoSeccionBO;
	
		
	/*************************************************************************/
	
	/**
	 * Constructor
	 */
	public TrasSeccionController() {
		
		this.region = new Integer(0);
		this.subRegion = new Integer(0);
		this.codigoOficina = new Integer(0);
		this.subEstacion = new Integer(0);
		this.circuito = new Integer(0);
		this.seccion = new Long(0);
		
		this.region2 = new Integer(0);
		this.subRegion2 = new Integer(0);
		this.codigoOficina2 = new Integer(0);
		this.subEstacion2 = new Integer(0);
		this.circuito2 = new Integer(0);
		this.seccion2 = new Long(0);
		
	}
	
	public String getInit(){
		return "success";
	}
	
	
	/*************************************************************************/
	
	/**
	 * Metodo accesor de abonadoSeccion.
	 * @return Retorna el abonadoSeccion.
	 */
	public Long getAbonadoSeccion() {
		return abonadoSeccion;
	}
	/**
	 * Metodo modificador de abonadoSeccion.
	 * @param abonadoSeccion El abonadoSeccion a modificar.
	 */
	public void setAbonadoSeccion(Long abonadoSeccion) {
		this.abonadoSeccion = abonadoSeccion;
	}
	/**
	 * Metodo accesor de abonadoSeccion2.
	 * @return Retorna el abonadoSeccion2.
	 */
	public Long getAbonadoSeccion2() {
		return abonadoSeccion2;
	}
	/**
	 * Metodo modificador de abonadoSeccion2.
	 * @param abonadoSeccion2 El abonadoSeccion2 a modificar.
	 */
	public void setAbonadoSeccion2(Long abonadoSeccion2) {
		this.abonadoSeccion2 = abonadoSeccion2;
	}
	/**
	 * Metodo accesor de circuito.
	 * @return Retorna el circuito.
	 */
	public Integer getCircuito() {
		return circuito;
	}
	/**
	 * Metodo modificador de circuito.
	 * @param circuito El circuito a modificar.
	 */
	public void setCircuito(Integer circuito) {
		this.circuito = circuito;
	}
	/**
	 * Metodo accesor de circuito2.
	 * @return Retorna el circuito2.
	 */
	public Integer getCircuito2() {
		return circuito2;
	}
	/**
	 * Metodo modificador de circuito2.
	 * @param circuito2 El circuito2 a modificar.
	 */
	public void setCircuito2(Integer circuito2) {
		this.circuito2 = circuito2;
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
	 * Metodo accesor de codigoOficina2.
	 * @return Retorna el codigoOficina2.
	 */
	public Integer getCodigoOficina2() {
		return codigoOficina2;
	}
	/**
	 * Metodo modificador de codigoOficina2.
	 * @param codigoOficina2 El codigoOficina2 a modificar.
	 */
	public void setCodigoOficina2(Integer codigoOficina2) {
		this.codigoOficina2 = codigoOficina2;
	}
	/**
	 * Metodo accesor de kmsSeccion.
	 * @return Retorna el kmsSeccion.
	 */
	public Double getKmsSeccion() {
		return kmsSeccion;
	}
	/**
	 * Metodo modificador de kmsSeccion.
	 * @param kmsSeccion El kmsSeccion a modificar.
	 */
	public void setKmsSeccion(Double kmsSeccion) {
		this.kmsSeccion = kmsSeccion;
	}
	/**
	 * Metodo accesor de kmsSeccion2.
	 * @return Retorna el kmsSeccion2.
	 */
	public Double getKmsSeccion2() {
		return kmsSeccion2;
	}
	/**
	 * Metodo modificador de kmsSeccion2.
	 * @param kmsSeccion2 El kmsSeccion2 a modificar.
	 */
	public void setKmsSeccion2(Double kmsSeccion2) {
		this.kmsSeccion2 = kmsSeccion2;
	}
	/**
	 * Metodo accesor de nombreSeccion.
	 * @return Retorna el nombreSeccion.
	 */
	public String getNombreSeccion() {
		return nombreSeccion;
	}
	/**
	 * Metodo modificador de nombreSeccion.
	 * @param nombreSeccion El nombreSeccion a modificar.
	 */
	public void setNombreSeccion(String nombreSeccion) {
		this.nombreSeccion = nombreSeccion;
	}
	/**
	 * Metodo accesor de nombreSeccion2.
	 * @return Retorna el nombreSeccion2.
	 */
	public String getNombreSeccion2() {
		return nombreSeccion2;
	}
	/**
	 * Metodo modificador de nombreSeccion2.
	 * @param nombreSeccion2 El nombreSeccion2 a modificar.
	 */
	public void setNombreSeccion2(String nombreSeccion2) {
		this.nombreSeccion2 = nombreSeccion2;
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
	 * Metodo accesor de region.
	 * @return Retorna el region.
	 */
	public Integer getRegion() {
		return region;
	}
	/**
	 * Metodo modificador de region.
	 * @param region El region a modificar.
	 */
	public void setRegion(Integer region) {
		this.region = region;
	}
	/**
	 * Metodo accesor de region2.
	 * @return Retorna el region2.
	 */
	public Integer getRegion2() {
		return region2;
	}
	/**
	 * Metodo modificador de region2.
	 * @param region2 El region2 a modificar.
	 */
	public void setRegion2(Integer region2) {
		this.region2 = region2;
	}
	/**
	 * Metodo accesor de regionBO.
	 * @return Retorna el regionBO.
	 */
	public RegionBO getRegionBO() {
		return regionBO;
	}
	/**
	 * Metodo modificador de regionBO.
	 * @param regionBO El regionBO a modificar.
	 */
	public void setRegionBO(RegionBO regionBO) {
		this.regionBO = regionBO;
	}
	/**
	 * Metodo accesor de seccion.
	 * @return Retorna el seccion.
	 */
	public Long getSeccion() {
		return seccion;
	}
	/**
	 * Metodo modificador de seccion.
	 * @param seccion El seccion a modificar.
	 */
	public void setSeccion(Long seccion) {
		this.seccion = seccion;
	}
	/**
	 * Metodo accesor de seccion2.
	 * @return Retorna el seccion2.
	 */
	public Long getSeccion2() {
		return seccion2;
	}
	/**
	 * Metodo modificador de seccion2.
	 * @param seccion2 El seccion2 a modificar.
	 */
	public void setSeccion2(Long seccion2) {
		this.seccion2 = seccion2;
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
	 * Metodo accesor de subEstacion.
	 * @return Retorna el subEstacion.
	 */
	public Integer getSubEstacion() {
		return subEstacion;
	}
	/**
	 * Metodo modificador de subEstacion.
	 * @param subEstacion El subEstacion a modificar.
	 */
	public void setSubEstacion(Integer subEstacion) {
		this.subEstacion = subEstacion;
	}
	/**
	 * Metodo accesor de subEstacion2.
	 * @return Retorna el subEstacion2.
	 */
	public Integer getSubEstacion2() {
		return subEstacion2;
	}
	/**
	 * Metodo modificador de subEstacion2.
	 * @param subEstacion2 El subEstacion2 a modificar.
	 */
	public void setSubEstacion2(Integer subEstacion2) {
		this.subEstacion2 = subEstacion2;
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
	 * Metodo accesor de subRegion.
	 * @return Retorna el subRegion.
	 */
	public Integer getSubRegion() {
		return subRegion;
	}
	/**
	 * Metodo modificador de subRegion.
	 * @param subRegion El subRegion a modificar.
	 */
	public void setSubRegion(Integer subRegion) {
		this.subRegion = subRegion;
	}
	/**
	 * Metodo accesor de subRegion2.
	 * @return Retorna el subRegion2.
	 */
	public Integer getSubRegion2() {
		return subRegion2;
	}
	/**
	 * Metodo modificador de subRegion2.
	 * @param subRegion2 El subRegion2 a modificar.
	 */
	public void setSubRegion2(Integer subRegion2) {
		this.subRegion2 = subRegion2;
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
	
	
	/*************************************************************************/
	
	/**
	 * Comment for getRegiones
	 * @return lista de regiones
	 */
	public List getRegiones(){
		
		Vector vRegiones = new Vector();

		List lRegiones = this.regionBO.getRegionesPorOficina(this.codigoOficina);

		vRegiones.add(new SelectItem(new Integer(0), "Seleccione una region"));
		
		for (int i = 0; i < lRegiones.size(); i++) {
			Region region = (Region) lRegiones.get(i);
			vRegiones.add(new SelectItem(region.getRegion(), region.getRegion() + " - " + region.getNombreRegion()));
		}

		return vRegiones;
		
	}
	
	/**
	 * Comment for getSubRegiones
	 * @return Lista Sub Regiones
	 */
	public List getSubRegiones(){
		
		Vector vsubRegiones = new Vector();

		List lSubRegiones = this.subRegionBO.getSubRegionesPorOficina(this.codigoOficina, this.region);

		vsubRegiones.add(new SelectItem(new Integer(0), "Seleccione una subregion"));
		
		for (int i=0; i < lSubRegiones.size(); i++) {
			SubRegion subregion = (SubRegion) lSubRegiones.get(i);
			vsubRegiones.add(new SelectItem(subregion.getSubRegionID().getSubRegion(), subregion.getSubRegionID().getSubRegion()+" - "+subregion.getNombreSubRegion()));
		}
		return vsubRegiones;
		
	}
	
	/**
	 * Comment for getOficinas
	 * @return Lista de Oficinas
	 */
	public List getOficinas(){
		
		Vector voficinas = new Vector();
		List loficinas = oficinaBO.getOficinas();
		
		voficinas.add(new SelectItem(new Integer(0), "Seleccione una oficina"));
		
		for (int i = 0; i < loficinas.size(); i++) {
			Oficina xoficina = (Oficina) loficinas.get(i);
			voficinas.add(new SelectItem(xoficina.getCodigoOficina(), xoficina.getCodigoOficina()+" - "+xoficina.getNombreOficina()));
		}
		return voficinas;
		
	}
	
	/**
	 * Comment for getSubEstaciones
	 * @return Lista de Sub Estaciones
	 */
	public List getSubEstaciones(){
		
		Vector vsubEstaciones = new Vector();

		List lSubEstaciones = this.subEstacionBO.getSubEstacionesSubRegionOficina(this.region, this.subRegion, this.codigoOficina);

		vsubEstaciones.add(new SelectItem(new Integer(0), "Seleccione una SubEstacion"));
		
		for (int i=0; i < lSubEstaciones.size(); i++) {
			SubEstacion xsubestacion = (SubEstacion) lSubEstaciones.get(i);
			vsubEstaciones.add(new SelectItem(xsubestacion.getCodigoSubEstacion(), xsubestacion.getCodigoSubEstacion()+" - "+xsubestacion.getNombreSubEstacion()));
		}
		return vsubEstaciones;
		
	}
	
	/**
	 * Comment for getCircuitos
	 * @return Lista Circuitos
	 */
	public List getCircuitos(){
		
		Vector vcircuitos = new Vector();

		List lCircuitos = circuitoBO.getCircuitos(this.subEstacion);
		
		vcircuitos.add(new SelectItem(new Integer(0), "Seleccione un circuito"));
		
		for (int i = 0; i < lCircuitos.size(); i++) {
			Circuito xcircuito = (Circuito) lCircuitos.get(i);
			vcircuitos.add(new SelectItem(xcircuito.getCircuitoID().getCircuito(), xcircuito.getCircuitoID().getCircuito()+" - "+xcircuito.getNombreCircuito()));
		}
		return vcircuitos;
		
	}
	
	
	/**
	 * 
	 * Comment for getRegiones2
	 * @return Lista de regiones2
	 */
	public List getRegiones2(){
		
		Vector vRegiones = new Vector();

		List lRegiones = this.regionBO.getRegionesPorOficina(this.codigoOficina2);

		vRegiones.add(new SelectItem(new Integer(0), "Seleccione una region"));
		
		for (int i = 0; i < lRegiones.size(); i++) {
			Region region = (Region) lRegiones.get(i);
			vRegiones.add(new SelectItem(region.getRegion(), region.getRegion() + " - " + region.getNombreRegion()));
		}

		return vRegiones;
		
	}	
	
	
	/**
	 * Comment for getSubRegiones2
	 * @return Lista Sub Regiones
	 */
	public List getSubRegiones2(){
		
		Vector vsubRegiones = new Vector();

		List lSubRegiones = this.subRegionBO.getSubRegionesPorOficina(this.codigoOficina2,this.region2);

		vsubRegiones.add(new SelectItem(new Integer(0), "Seleccione una subregion"));
		
		for (int i=0; i < lSubRegiones.size(); i++) {
			SubRegion subregion = (SubRegion) lSubRegiones.get(i);
			vsubRegiones.add(new SelectItem(subregion.getSubRegionID().getSubRegion(), subregion.getSubRegionID().getSubRegion()+" - "+subregion.getNombreSubRegion()));
		}
		return vsubRegiones;
		
	}
	
	
	/**
	 * Comment for getSubEstaciones2
	 * @return Lista de Sub Estaciones
	 */
	public List getSubEstaciones2(){
		
		Vector vsubEstaciones = new Vector();

		List lSubEstaciones = this.subEstacionBO.getSubEstacionesSubRegionOficina(this.region2, this.subRegion2, this.codigoOficina2);

		vsubEstaciones.add(new SelectItem(new Integer(0), "Seleccione una SubEstacion"));
		
		for (int i=0; i < lSubEstaciones.size(); i++) {
			SubEstacion xsubestacion = (SubEstacion) lSubEstaciones.get(i);
			vsubEstaciones.add(new SelectItem(xsubestacion.getCodigoSubEstacion(), xsubestacion.getCodigoSubEstacion()+" - "+xsubestacion.getNombreSubEstacion()));
		}
		return vsubEstaciones;
		
	}
	
	/**
	 * Comment for getCircuitos2
	 * @return Lista Circuitos
	 */
	public List getCircuitos2(){
		
		Vector vcircuitos = new Vector();

		List lCircuitos = circuitoBO.getCircuitos(this.subEstacion2);
		
		vcircuitos.add(new SelectItem(new Integer(0), "Seleccione un circuito"));
		
		for (int i = 0; i < lCircuitos.size(); i++) {
			Circuito xcircuito = (Circuito) lCircuitos.get(i);
			vcircuitos.add(new SelectItem(xcircuito.getCircuitoID().getCircuito(), xcircuito.getCircuitoID().getCircuito()+" - "+xcircuito.getNombreCircuito()));
		}
		return vcircuitos;
		
	}
	
	/**
	 * Comment for getSecciones
	 * @return Lista Secciones
	 */
	public List getSecciones(){
		
		Vector vsecciones = new Vector();

		List listaSecciones = seccionBO.getSecciones(this.subEstacion, this.circuito);
		
		vsecciones.add(new SelectItem(new Long(0), "Seleccione una seccion"));
		
		if(listaSecciones != null){
			for (int i = 0; i < listaSecciones.size(); i++) {
				Seccion xseccion = (Seccion) listaSecciones.get(i);
				vsecciones.add(new SelectItem(xseccion.getSeccionID().getSeccion(), xseccion.getSeccionID().getSeccion()+" - "+xseccion.getNombreSeccion()));
			}
		}
		return vsecciones;
		
	}
	
	
	/*************************************************************************/
	
	/**
	 * Comment for listenerRegion
	 * @param v
	 * @return "listener" de Region
	 */
	public String listenerRegion(javax.faces.event.ValueChangeEvent v) {
		
		PhaseId id = v.getPhaseId();
		
		if(id == PhaseId.ANY_PHASE){
			
			v.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			v.queue();
			
		} else if(id == PhaseId.UPDATE_MODEL_VALUES) {
			
			this.region = (Integer) v.getNewValue();
			
			this.region2 = (Integer) v.getNewValue();
			
			this.subRegion = new Integer(0);
			//this.codigoOficina = new Integer(0);
			this.subEstacion = new Integer(0);
			this.circuito = new Integer(0);
			this.seccion = new Long(0);
			
			this.subRegion2 = new Integer(0);
			//this.codigoOficina2 = new Integer(0);
			this.subEstacion2 = new Integer(0);
			this.circuito2 = new Integer(0);
			this.seccion2 = new Long(0);
			
			this.nombreSeccion = "";
			this.abonadoSeccion = new Long(0);
			this.kmsSeccion = new Double(0);
			
			this.nombreSeccion2 = "";
			this.abonadoSeccion2 = new Long(0);
			this.kmsSeccion2 = new Double(0);
			
		}
		
		return "listener";
	}
	
	/**
	 * Comment for listenerSubRegion
	 * @param v
	 * @return "listener" de Region
	 */
	public String listenerSubRegion(javax.faces.event.ValueChangeEvent v) {
		
		PhaseId id = v.getPhaseId();
		
		if(id == PhaseId.ANY_PHASE){
			
			v.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			v.queue();
			
		} else if(id == PhaseId.UPDATE_MODEL_VALUES) {
			
			this.subRegion = (Integer) v.getNewValue();
			
			this.subRegion2 = (Integer) v.getNewValue();
			
			//this.codigoOficina = new Integer(0);
			this.subEstacion = new Integer(0);
			this.circuito = new Integer(0);
			this.seccion = new Long(0);
			
			//this.codigoOficina2 = new Integer(0);
			this.subEstacion2 = new Integer(0);
			this.circuito2 = new Integer(0);
			this.seccion2 = new Long(0);
			
			this.nombreSeccion = "";
			this.abonadoSeccion = new Long(0);
			this.kmsSeccion = new Double(0);
			
			this.nombreSeccion2 = "";
			this.abonadoSeccion2 = new Long(0);
			this.kmsSeccion2 = new Double(0);
			
		}
		
		return "listener";
	}
	
	/**
	 * Comment for listenerOficinas
	 * @param v
	 * @return "listener" de Oficinas
	 */
	public String listenerOficinas(javax.faces.event.ValueChangeEvent v) {
		
		PhaseId id = v.getPhaseId();
		
		if(id == PhaseId.ANY_PHASE){
			
			v.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			v.queue();
			
		} else if(id == PhaseId.UPDATE_MODEL_VALUES) {
			
			this.codigoOficina = (Integer) v.getNewValue();
			
			this.codigoOficina2 = (Integer) v.getNewValue();
			this.region = new Integer(0);
			this.subRegion = new Integer(0);
			this.subEstacion = new Integer(0);			
			this.circuito = new Integer(0);
			this.seccion = new Long(0);
			
			this.region2 = new Integer(0);
			this.subRegion2 = new Integer(0);
			this.subEstacion2 = new Integer(0);
			this.circuito2 = new Integer(0);
			this.seccion2 = new Long(0);
			
			this.nombreSeccion = "";
			this.abonadoSeccion = new Long(0);
			this.kmsSeccion = new Double(0);
			
			this.nombreSeccion2 = "";
			this.abonadoSeccion2 = new Long(0);
			this.kmsSeccion2 = new Double(0);
			
		}
		
		return "listener";
	}
	
	/**
	 * Comment for listenerSubEstaciones
	 * @param v
	 * @return "listener" de subEstacion
	 */
	public String listenerSubEstaciones(javax.faces.event.ValueChangeEvent v) {
		
		PhaseId id = v.getPhaseId();
		
		if(id == PhaseId.ANY_PHASE){
			
			v.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			v.queue();
			
		} else if(id == PhaseId.UPDATE_MODEL_VALUES) {
			
			this.subEstacion = (Integer) v.getNewValue();
			
			this.subEstacion2 = (Integer) v.getNewValue();
			
			this.circuito = new Integer(0);
			this.seccion = new Long(0);
			
			this.circuito2 = new Integer(0);
			this.seccion2 = new Long(0);
			
			this.nombreSeccion = "";
			this.abonadoSeccion = new Long(0);
			this.kmsSeccion = new Double(0);
			
			this.nombreSeccion2 = "";
			this.abonadoSeccion2 = new Long(0);
			this.kmsSeccion2 = new Double(0);
			
		}
		
		return "listener";
	}
	
	/**
	 * Comment for listenerCircuitos
	 * @param v
	 * @return "listener" de Circuitos
	 */
	public String listenerCircuitos(javax.faces.event.ValueChangeEvent v) {
		
		PhaseId id = v.getPhaseId();
		
		if(id == PhaseId.ANY_PHASE){
			
			v.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			v.queue();
			
		} else if(id == PhaseId.UPDATE_MODEL_VALUES) {
			
			this.circuito = (Integer) v.getNewValue();
			
			this.circuito2 = (Integer) v.getNewValue();
			
			this.seccion = new Long(0);
			
			this.seccion2 = new Long(0);
			
			this.nombreSeccion = "";
			this.abonadoSeccion = new Long(0);
			this.kmsSeccion = new Double(0);
			
			this.nombreSeccion2 = "";
			this.abonadoSeccion2 = new Long(0);
			this.kmsSeccion2 = new Double(0);
			
		}
		
		return "listener";
	}
	
	/**
	 * Comment for listenerSecciones
	 * @param v
	 * @return "listener" de Secciones
	 */
	public String listenerSecciones(javax.faces.event.ValueChangeEvent v) {
		
		PhaseId id = v.getPhaseId();
		
		if(id == PhaseId.ANY_PHASE){
			
			v.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			v.queue();
			
		} else if(id == PhaseId.UPDATE_MODEL_VALUES) {
			
			this.seccion = (Long) v.getNewValue();
			
			SeccionID seccionID = new SeccionID();
			seccionID.setCircuito(this.circuito);
			seccionID.setSubEstacion(this.subEstacion);
			seccionID.setSeccion(this.seccion);
			
			Seccion seccion = this.seccionBO.buscar(seccionID);
			if(seccion != null){
				this.nombreSeccion = seccion.getNombreSeccion();
				this.abonadoSeccion = seccion.getAbonadoSeccion();
				this.kmsSeccion = seccion.getKmsSeccion();
				
				this.seccion2 = (Long) v.getNewValue();
				this.nombreSeccion2 = seccion.getNombreSeccion();
				this.abonadoSeccion2 = seccion.getAbonadoSeccion();
				this.kmsSeccion2 = seccion.getKmsSeccion();
			}
			else{
				this.nombreSeccion = "";
				this.abonadoSeccion = new Long(0);
				this.kmsSeccion = new Double(0);
				
				this.seccion2 = new Long(0);
				this.nombreSeccion2 = "";
				this.abonadoSeccion2 = new Long(0);
				this.kmsSeccion2 = new Double(0);
			}
			
		}
		
		return "listener";
	}
	
	
	
	/**
	 * Comment for listenerRegion2
	 * @param v
	 * @return "listener" de subRegion
	 */
	public String listenerRegion2() {
		
		
		this.subRegion2 = new Integer(0);
		//this.codigoOficina2 = new Integer(0);
		this.subEstacion2 = new Integer(0);
		this.circuito2 = new Integer(0);
		//this.seccion2 = new Long(0);
		
		//this.nombreSeccion2 = "";
		//this.abonadoSeccion2 = new Long(0);
		//this.kmsSeccion2 = new Double(0);
		
		return "listener";
		
	}
	
	/**
	 * Comment for listenerSubRegion2
	 * @param v
	 * @return "listener" de subRegion2
	 */
	public String listenerSubRegion2() {
		
		
		//this.codigoOficina2 = new Integer(0);
		this.subEstacion2 = new Integer(0);
		this.circuito2 = new Integer(0);
		//this.seccion2 = new Long(0);
		
		//this.nombreSeccion2 = "";
		//this.abonadoSeccion2 = new Long(0);
		//this.kmsSeccion2 = new Double(0);
		
		return "listener";
		
	}
	
	/**
	 * Comment for listenerOficinas2
	 * @param v
	 * @return "listener" de Oficinas
	 */
	public String listenerOficinas2() {
		
		this.region2 = new Integer(0);
		this.subRegion2 = new Integer(0);
		this.subEstacion2 = new Integer(0);
		this.setSubEstacion2(new Integer(0));		
		this.circuito2 = new Integer(0);
		this.setCircuito2(new Integer(0));
		//this.seccion2 = new Long(0);
		
		//this.nombreSeccion2 = "";
		//this.abonadoSeccion2 = new Long(0);
		//this.kmsSeccion2 = new Double(0);
			
		return "listener";
		
	}
	
	/**
	 * Comment for listenerSubEstaciones2
	 * @param v
	 * @return "listener" de subEstacion
	 */
	public String listenerSubEstaciones2() {
		
		
		this.circuito2 = new Integer(0);
		/*this.seccion2 = new Long(0);
		
		this.nombreSeccion2 = "";
		this.abonadoSeccion2 = new Long(0);
		this.kmsSeccion2 = new Double(0);*/
		
		return "listener";
		
	}
	
	/**
	 * Comment for listenerCircuitos2
	 * @param v
	 * @return "listener" de circuito
	 */
	public String listenerCircuitos2() {
		
		
		
		//this.seccion2 = new Long(0);
		
		//this.nombreSeccion2 = "";
		//this.abonadoSeccion2 = new Long(0);
		//this.kmsSeccion2 = new Double(0);
		
		return "listener";
		
	}
	
	
	/*************************************************************************/
	
	/**
	 * Comment for cancelar
	 * reinicia todos los valores de la clase
	 */
	public void reiniciarCampos() {
		
		this.seccion = new Long(0);
		this.subEstacion = new Integer(0);
		this.circuito = new Integer(0);

		this.region = new Integer(0);
		this.subRegion = new Integer(0);
		this.codigoOficina = new Integer(0);
		this.nombreSeccion = "";
		this.kmsSeccion = new Double(0);
		this.abonadoSeccion = new Long(0);
		
		this.seccion2 = new Long(0);
		this.subEstacion2 = new Integer(0);
		this.circuito2 = new Integer(0);

		this.region2 = new Integer(0);
		this.subRegion2 = new Integer(0);
		this.codigoOficina2 = new Integer(0);
		this.nombreSeccion2 = "";
		this.kmsSeccion2 = new Double(0);
		this.abonadoSeccion2 = new Long(0);
		
	}
	
	/**
	 * Comment for cancelar
	 * @return "success" o "error" al Cancelar
	 */
	public String cancelar() {
		
		this.reiniciarCampos();
		return "success";
		
	}
	
	/**
	 * Comment for aceptar
	 * @return "success" o "error" al Aceptar
	 */
	
	public String aceptar() {
		
		boolean correcto = true;
		
		if(this.seccion == null || this.seccion.intValue() <= 0) {
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar la sección de origen."));
            
			correcto = false;
			
		}
		
		if(this.circuito == null || this.circuito.intValue() <= 0) {
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar el circuito origen."));
            
			correcto = false;
			
		}
		
		if(this.circuito2 == null || this.circuito2.intValue() <= 0) {
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar el circuito destino."));
            
			correcto = false;
			
		}
		
		if(this.seccion2 == null || this.seccion2.intValue() <= 0) {
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de sección es requerido."));
			correcto = false;
			
		}
		
		if(this.nombreSeccion2 == null || this.nombreSeccion2.trim().length() <= 0) {
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre de la sección es inválido."));
			correcto = false;
			
		}
		
		
		
		if(correcto) {
		
			SeccionID seccionID = new SeccionID();
			seccionID.setCircuito(this.circuito);
			seccionID.setSeccion(this.seccion);
			seccionID.setSubEstacion(this.subEstacion);
			
			Seccion seccion =  this.seccionBO.buscar(seccionID);
			
			
			SeccionID seccionID2 = new SeccionID();
			seccionID2.setCircuito(this.circuito2);
			seccionID2.setSeccion(this.seccion2);
			seccionID2.setSubEstacion(this.subEstacion2);
			
			Seccion sec = this.seccionBO.buscar(seccionID2);
			if(sec == null) {
				
				Double varKilometros;
				Long varClientes;
				
				
				
				Region region = this.regionBO.buscar(this.region);	
				
				varKilometros = new Double(region.getKmsRegion().doubleValue() - seccion.getKmsSeccion().doubleValue());			
				region.setKmsRegion(varKilometros);			
				
				varClientes = new Long(region.getAbonadoRegion().longValue() - seccion.getAbonadoSeccion().longValue());
				region.setAbonadoRegion(varClientes);
				
				this.regionBO.modificar(region);
				
				
				
				SubRegionID subRegionID = new SubRegionID();
				subRegionID.setRegion(region);
	        	subRegionID.setSubRegion(this.subRegion); 
				
				SubRegion subRegion = this.subRegionBO.buscar(subRegionID);		
				
				varKilometros = new Double(subRegion.getKmsSubRegion().doubleValue() - seccion.getKmsSeccion().doubleValue());			
				subRegion.setKmsSubRegion(varKilometros);			
				
				varClientes = new Long(region.getAbonadoRegion().longValue() - seccion.getAbonadoSeccion().longValue());
				subRegion.setAbonadoSubregion(varClientes);
				
				this.subRegionBO.modificar(subRegion);
				
				
				
				SubEstacion subEstacion = this.subEstacionBO.buscar(this.subEstacion);
				
				varKilometros = new Double(subEstacion.getKmsSubEstacion().doubleValue() - seccion.getKmsSeccion().doubleValue());			
				subEstacion.setKmsSubEstacion(varKilometros);			
				
				varClientes = new Long(subEstacion.getAbonadoSubEstacion().longValue() - seccion.getAbonadoSeccion().longValue());
				subEstacion.setAbonadoSubEstacion(varClientes);
				
				this.subEstacionBO.modificar(subEstacion);
				
				
				
				Circuito circuito  = this.circuitoBO.buscar(this.subEstacion, this.circuito);
				
				varKilometros = new Double(circuito.getKmsCircuito().doubleValue() - seccion.getKmsSeccion().doubleValue());			
				circuito.setKmsCircuito(varKilometros);			
				
				varClientes = new Long(circuito.getAbonadoCircuito().longValue() - seccion.getAbonadoSeccion().longValue());
				circuito.setAbonadoCircuito(varClientes);
				
				this.circuitoBO.modificar(circuito);
				
				
				
				Region region2 = this.regionBO.buscar(this.region2);	
				
				varKilometros = new Double(region2.getKmsRegion().doubleValue() + seccion.getKmsSeccion().doubleValue());			
				region2.setKmsRegion(varKilometros);			
				
				varClientes = new Long(region2.getAbonadoRegion().longValue() + seccion.getAbonadoSeccion().longValue());
				region2.setAbonadoRegion(varClientes);
				
				this.regionBO.modificar(region2);/**/
				
				
				
				SubRegionID subRegionID2 = new SubRegionID();
				subRegionID2.setRegion(region2);
	        	subRegionID2.setSubRegion(this.subRegion2); 
				
				SubRegion subRegion2 = this.subRegionBO.buscar(subRegionID2);		
				
				varKilometros = new Double(subRegion2.getKmsSubRegion().doubleValue() + seccion.getKmsSeccion().doubleValue());			
				subRegion2.setKmsSubRegion(varKilometros);			
				
				varClientes = new Long(region2.getAbonadoRegion().longValue() + seccion.getAbonadoSeccion().longValue());
				subRegion2.setAbonadoSubregion(varClientes);
				
				this.subRegionBO.modificar(subRegion2);
				
				
				
				SubEstacion subEstacion2 = this.subEstacionBO.buscar(this.subEstacion2);
				
				varKilometros = new Double(subEstacion2.getKmsSubEstacion().doubleValue() + seccion.getKmsSeccion().doubleValue());			
				subEstacion2.setKmsSubEstacion(varKilometros);			
				
				varClientes = new Long(subEstacion2.getAbonadoSubEstacion().longValue() + seccion.getAbonadoSeccion().longValue());
				subEstacion2.setAbonadoSubEstacion(varClientes);
				
				this.subEstacionBO.modificar(subEstacion2);
				
				
				
				Circuito circuito2  = this.circuitoBO.buscar(this.subEstacion2, this.circuito2);
				
				varKilometros = new Double(circuito2.getKmsCircuito().doubleValue() + seccion.getKmsSeccion().doubleValue());			
				circuito2.setKmsCircuito(varKilometros);			
				
				varClientes = new Long(circuito2.getAbonadoCircuito().longValue() + seccion.getAbonadoSeccion().longValue());
				circuito2.setAbonadoCircuito(varClientes);
				
				this.circuitoBO.modificar(circuito2);
				
				
				
				seccion.setRegion(this.region);
				seccion.setSubRegion(this.subRegion);
				seccion.setCodigoOficina(this.codigoOficina);
				seccion.setNombreSeccion(this.nombreSeccion.trim().toUpperCase());
				seccion.setAbonadoSeccion(this.abonadoSeccion);
				seccion.setCongelado(new Integer(1));
				
				this.seccionBO.modificar(seccion);
				
				
				
				Seccion seccion2 = new Seccion();
				
				seccion2.setSeccionID(seccionID2);
				
				seccion2.setRegion(this.region2);
				seccion2.setSubRegion(this.subRegion2);
				seccion2.setCodigoOficina(this.codigoOficina2);
				seccion2.setNombreSeccion(this.nombreSeccion2.trim().toUpperCase());
				seccion2.setAbonadoSeccion(this.abonadoSeccion2);
				seccion2.setKmsSeccion(seccion.getKmsSeccion());
				seccion2.setCongelado(new Integer(0));
				
				this.seccionBO.agregar(seccion2);		
				
                HistoricoTrasladoSeccion temp = new HistoricoTrasladoSeccion();
                temp.getId().setFecha(new Date());
                temp.getId().setRegion(this.region);
                temp.getId().setNuevaRegion(this.region2);
                temp.getId().setSubRegion(this.subRegion);
                temp.getId().setNuevaSubRegion(this.subRegion2);
                temp.getId().setSubEstacion(this.subEstacion);
                temp.getId().setNuevaSubEstacion(this.subEstacion2);
                temp.getId().setCircuito(this.circuito);
                temp.getId().setNuevoCircuito(this.circuito2);
                temp.getId().setSeccion(this.seccion);
                temp.getId().setNuevaSeccion(this.seccion2);
                UsuarioCia usr = this.obtenerUsuario();
                temp.getId().setUsuario(usr.getUsuario());
                temp.getId().setAbonadoSeccion(this.abonadoSeccion);
                this.historicoTrasladoSeccionBO.agregar(temp);
				this.reiniciarCampos();
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Traslado realizado con éxito."));
	            
	            
	            return "success";
				
			} else {//el registro sec existe
                if(sec.getCongelado().equals(Seccion.ESTADO_CONGELADO)){
    			    if(this.seccionBO.registrosAsociados(seccion.getSeccionID()).longValue() > 0){
    			        seccion.setCongelado(Seccion.ESTADO_CONGELADO);
    			        this.seccionBO.modificar(seccion);
    			    }
    			    else
    			        this.seccionBO.eliminar(seccion);
    			    sec.setCongelado(Seccion.ESTADO_DESCONGELADO);
    			    this.seccionBO.modificar(sec);
    			    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Traslado realizado con éxito."));
    	            
    				return "success";
                }else{
                	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Sección ya existente."));
                    
                    return "error";
                }
				
			}
		
		} else {
			
			return "error";
		
		}
		
	}

	   public UsuarioCia obtenerUsuario(){
	        FacesContext ctx = FacesContext.getCurrentInstance();
	        HttpServletRequest rq = (HttpServletRequest) ctx.getExternalContext().getRequest();  
	        HttpSession session = rq.getSession(true);
	        return ((UsuarioCia)session.getAttribute("usuario"));
	    }


    public HistoricoTrasladoSeccionBO getHistoricoTrasladoSeccionBO() {
        return this.historicoTrasladoSeccionBO;
    }


    public void setHistoricoTrasladoSeccionBO(HistoricoTrasladoSeccionBO historicoTrasladoSeccionBO) {
        this.historicoTrasladoSeccionBO = historicoTrasladoSeccionBO;
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
