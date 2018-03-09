package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.datatable.DataTable;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.CircuitoBO;
import cr.go.ice.interrupciones.BO.EmpleadoBO;
import cr.go.ice.interrupciones.BO.OficinaBO;
import cr.go.ice.interrupciones.BO.RegionBO;
import cr.go.ice.interrupciones.BO.SeccionBO;
import cr.go.ice.interrupciones.BO.SubEstacionBO;
import cr.go.ice.interrupciones.BO.SubRegionBO;
import cr.go.ice.interrupciones.BO.UsuarioOficinaBO;
import cr.go.ice.interrupciones.domain.Circuito;
import cr.go.ice.interrupciones.domain.Empleado;
import cr.go.ice.interrupciones.domain.Oficina;
import cr.go.ice.interrupciones.domain.Region;
import cr.go.ice.interrupciones.domain.Seccion;
import cr.go.ice.interrupciones.domain.SubEstacion;
import cr.go.ice.interrupciones.domain.SubRegion;
import cr.go.ice.interrupciones.domain.UsuarioOficina;
import cr.go.ice.interrupciones.utils.Usuario;

/**
 * <p>TODO <<Interface|Clase>> cr.go.ice.interrupciones.web.controller.CambioSeccionesController.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>CambioSeccionesController.java</code>.</p>
 * <p>Fecha creación: 26/09/2012</p>
 * <p>Última actualización: 26/09/2012</p>
 * @author Vista Verde Tecnología (eperaza)
 * @version 1.0
 */
public class CambioSeccionesController extends AbstractFacesController{

    private Integer tipo;
    private Seccion seccOrigen;
    private Seccion seccDestino;
    private DataTable dataTableOrigen;
    private DataTable dataTableDestino;
    private List<Seccion>    listaOrigen;
    private List<Seccion>    listaDestino;
    private Empleado empleado;
    
    private Integer codigoOficinaO;
    private Integer regionO;
    private Integer subregionO;
    private Integer subestacionO;
    private Integer circuitoO;
    private Long    seccionO;
    
    private Integer codigoOficinaD;
    private Integer regionD;
    private Integer subregionD;
    private Integer subestacionD;
    private Integer circuitoD;
    private Long    seccionD;
    private Long cedula;
    
    private OficinaBO       oficinaBO;
    private RegionBO        regionBO;
    private SubRegionBO     subRegionBO;
    private SubEstacionBO   subEstacionBO;
    private CircuitoBO      circuitoBO;
    private SeccionBO       seccionBO;
    private EmpleadoBO      empleadoBO;
    private UsuarioOficinaBO usuarioOficinaBO;
    
    
    public String getInit(){
    	boolean userClor = true;
    	FacesContext context = FacesContext.getCurrentInstance();
        Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
        
        if(userClor && limpiar != null){
            this.reiniciarCampos();
            
            String nombreUsuarioSession = (String) context.getApplication().createValueBinding("#{autorizacionController.nombreUsuario}").getValue(this.getFacesContext());
	            String[] valores = nombreUsuarioSession.split("-");
	            this.cedula = new Long(valores[0].trim());
	            
            this.empleado = this.empleadoBO.buscar(cedula);
            List<UsuarioOficina> listaPivote = this.usuarioOficinaBO.buscarCedula(cedula);
        	if(!listaPivote.isEmpty())
        	{
        		this.codigoOficinaO = listaPivote.get(0).getId().getOficina().getCodigoOficina();
        		this.codigoOficinaD = listaPivote.get(0).getId().getOficina().getCodigoOficina();
        	}else
        	{
        		this.codigoOficinaO = new Integer(0);
        		this.codigoOficinaD = new Integer(0);
        	}
        }   
    	return "success";
    }
    
    /**
     * 
     * Constructor
     */
    public CambioSeccionesController(){
        this.reiniciarCampos();
    }
    
    /**
     * Metodo que reincia los atributos de la clase
     */
    private void reiniciarCampos(){
        this.tipo =           Integer.valueOf(0);
        this.seccOrigen =     new Seccion();
        this.seccDestino =    new Seccion();
        this.dataTableDestino = new DataTable();
        this.dataTableOrigen  = new DataTable();
        this.listaDestino = new ArrayList<Seccion>();
        this.listaOrigen  = new ArrayList<Seccion>();
        this.empleado = new Empleado();
        
        this.codigoOficinaO = new Integer(0);
        this.regionO =        new Integer(0);
        this.subregionO =     new Integer(0);
        this.subestacionO =   new Integer(0);
        this.circuitoO =      new Integer(0);
        this.seccionO =       new Long(0);
        
        this.codigoOficinaD = new Integer(0);
        this.regionD =        new Integer(0);
        this.subregionD =     new Integer(0);
        this.subestacionD =   new Integer(0);
        this.circuitoD =      new Integer(0);
        this.seccionD =       new Long(0);
    }
    
    public String listenerCambioOficina()
    {
		
	 	this.regionO = new Integer(0);
	 	this.subestacionO= new Integer(0);
    	this.circuitoO= new Integer(0);
	 	this.seccionO= new Long(0);
	 	
	
	 	return "listener";
	   
    }
    
    
    public String listenerCambioRegionO()
    {
    	this.subregionO= new Integer(0);
    	this.subestacionO= new Integer(0);
	 	this.seccionO= new Long(0);
	 	this.circuitoO= new Integer(0);
	 	
	 	
	 return "listener";
       
    }
    
    public String listenerCambioSubRegionO()
    {
	
    	this.subestacionO=new Integer(0);
	 	this.seccionO= new Long(0);
	 	this.circuitoO= new Integer(0);
	 	
	 	
	 return "listener";
       
    }
    
    
    public String listenerCambioSubEstacionO()
    {
    	this.seccionO= new Long(0);
	 	this.circuitoO= new Integer(0);
	 	
	 	
	 return "listener";
       
    }
    
    
    public String listenerCambioCircuitoO()
    {
	
    	this.seccionO= new Long(0);
	 	
	 	
	 return "listener";
       
    }
    
    
    
    
    /**
     * Método que genera una lista de los tipos de cambios de secciones
     * @return lista tipos.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List getTipos() {
        List items = new ArrayList();
        items.add(new SelectItem(Integer.valueOf(0), "Sustituir"));
        items.add(new SelectItem(Integer.valueOf(1), "Unir"));
        items.add(new SelectItem(Integer.valueOf(2), "Dividir"));        
        return items;
    }
    
    /*********************************** SELECT ITEMS PARA ORIGEN  **************************************************/
    
    /**
     * Método que obtiene una lista de las oficinas de origen
     * @return Lista de oficinas
     */    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List getListaOficinasO(){    
        Vector vOficinas = new Vector();
        List lOficinas = oficinaBO.getOficinas();
        
        vOficinas.add(new SelectItem(new Integer(0), "Seleccione una oficina"));
        if(lOficinas != null && !lOficinas.isEmpty()){
            for (int i = 0; i < lOficinas.size(); i++) {
                Oficina oficina = (Oficina) lOficinas.get(i);
                vOficinas.add(new SelectItem(oficina.getCodigoOficina(), oficina.getCodigoOficina() + " - " + oficina.getNombreOficina()));
            } 
        }
        
        return vOficinas;
    }    
    
    /**
     * Método que obtiene una lista de las regiones de origen
     * @return lista regiones.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List getRegionesO() {
        List regiones = null;
        if(!this.codigoOficinaO.equals(Integer.valueOf(0))){
            regiones = this.regionBO.getRegionesActivasPorOficina(this.codigoOficinaO);
        }
        List items = new ArrayList();
        items.add(new SelectItem(Integer.valueOf(0), "Seleccione una región"));
        if(regiones != null && !regiones.isEmpty()){
            for(int i = 0; i < regiones.size(); i++){
                Region region = (Region) regiones.get(i);
                SelectItem item = new SelectItem();
                item.setValue(region.getRegion());
                item.setLabel(region.getRegion() + " - " + region.getNombreRegion());
                items.add(item);
            }
        }
        return items;
    }
    
    /**
     * Método que obtiene una lista de las subregiones de origen según la región que se seleccione
     * @return lista subRegiones.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List getSubRegionesO() { 
        List subRegiones = null;
        if(!this.regionO.equals(Integer.valueOf(0))){
            subRegiones = this.subRegionBO.getSubRegionesActivas(this.regionO);
        }
        List items = new ArrayList();
        items.add(new SelectItem(Integer.valueOf(0), "Seleccione una subregión"));
        if(subRegiones!= null && !subRegiones.isEmpty()){
            for(int i = 0; i < subRegiones.size(); i++){
                SubRegion subRegion = (SubRegion) subRegiones.get(i);
                SelectItem item = new SelectItem();
                item.setValue(subRegion.getSubRegionID().getSubRegion());
                item.setLabel(subRegion.getSubRegionID().getSubRegion() + " - " + subRegion.getNombreSubRegion());
                items.add(item);
            }
        }
        return items;
    }
    
    /**
     * Método que obtiene una lista de las subestaciones de origen según la subregión que se seleccione
     * @return Retorna el subEstaciones.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List getSubEstacionesO() {
        List subEstaciones = null;
        if((!this.regionO.equals(Integer.valueOf(0))) && (!this.subregionO.equals(Integer.valueOf(0)))){
            subEstaciones = this.subEstacionBO.getSubEstacionesActivas(this.regionO, this.subregionO);
        }
        List items = new ArrayList();
        items.add(new SelectItem(Integer.valueOf(0), "Seleccione una subestación"));
        if(subEstaciones != null && !subEstaciones.isEmpty()){
            for(int i = 0; i < subEstaciones.size(); i++){
                SubEstacion subEstacion = (SubEstacion) subEstaciones.get(i);
                SelectItem item = new SelectItem();
                item.setValue(subEstacion.getCodigoSubEstacion());
                item.setLabel(subEstacion.getCodigoSubEstacion() + " - " + subEstacion.getNombreSubEstacion());
                items.add(item);
            }
        }
        return items;
    }
    
    /**
     * Método que obtiene una lista de los circuitos de origen según la subestación que se seleccione
     * @return lista circuitos.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List getCircuitosO() {
        List circuitos = null;
        if(!this.subestacionO.equals(Integer.valueOf(0))){
            circuitos = this.circuitoBO.getCircuitosActivas(this.subestacionO);
        }
        List items = new ArrayList();
        items.add(new SelectItem(Integer.valueOf(0), "Seleccione un circuito"));
        if(circuitos != null && !circuitos.isEmpty()){
            for(int i = 0; i < circuitos.size(); i++){
                Circuito circuito = (Circuito) circuitos.get(i);
                SelectItem item = new SelectItem();
                item.setValue(circuito.getCircuitoID().getCircuito());
                item.setLabel(circuito.getCircuitoID().getCircuito() + " - " + circuito.getNombreCircuito());
                items.add(item);
            }
        }
        return items;
    }
    
    /**
     * Método que obtiene una lista de las secciones de origen según el circuito que se seleccione
     * @return Retorna el secciones.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List getSeccionesO() {   
        List secciones = null;
        if((!this.subestacionO.equals(Integer.valueOf(0))) && (!this.circuitoO.equals(Integer.valueOf(0)))){
          //secciones = this.seccionBO.getSecciones(this.subestacionO, this.circuitoO);
            secciones = this.seccionBO.getSeccionesOrigen(this.regionO, this.subregionO, this.subestacionO, this.circuitoO);
        }
        List items = new ArrayList();
        items.add(new SelectItem(Long.valueOf(0), "Seleccione una sección"));
        if(secciones != null && !secciones.isEmpty()){
            for(int i = 0; i < secciones.size(); i++){
                Seccion seccion = (Seccion) secciones.get(i);
                SelectItem item = new SelectItem();
                item.setValue(seccion.getSeccionID().getSeccion());
                item.setLabel(seccion.getSeccionID().getSeccion() + " - " + seccion.getNombreSeccion());
                items.add(item);
            }
        }
        return items;
    }
    
    public String listenerSeccionesO(){
        String resultado = "success";
       if((!this.seccionO.equals(Long.valueOf(0))) && (!this.circuitoO.equals(Integer.valueOf(0))) && (!this.subestacionO.equals(Integer.valueOf(0)))){
            this.seccOrigen.getSeccionID().setSubEstacion(this.subestacionO);
            this.seccOrigen.getSeccionID().setCircuito(this.circuitoO);
            this.seccOrigen.getSeccionID().setSeccion(this.seccionO);
            this.seccOrigen = this.seccionBO.buscar(this.seccOrigen.getSeccionID());
        }
        return resultado;
    }
    
/*********************************** SELECT ITEMS PARA DESTINO  **************************************************/
    
    
    public String listenerCambioOficinaD()
    {
		
	 	this.regionD =new Integer(0);
	 	this.subestacionD= new Integer(0);
    	this.circuitoD= new Integer(0);
	 	this.seccionD= new Long(0); 	
	
	 	return "listener";
	   
    }
    
    
    public String listenerCambioRegionD()
    {
    	this.subregionD= new Integer(0);
    	this.subestacionD= new Integer(0);
	 	this.seccionD=new Long(0);
	 	this.circuitoD=new Integer(0); 	
	 	
	 return "listener";
       
    }
    
    public String listenerCambioSubRegionD()
    {
	
    	this.subestacionD= new Integer(0);
	 	this.seccionD=new Long(0);
	 	this.circuitoD=new Integer(0); 		 	
	 	
	 return "listener";
       
    }
    
    
    public String listenerCambioSubEstacionD()
    {
    	this.seccionD=new Long(0);
	 	this.circuitoD= new Integer(0);	 	
	 	
	 return "listener";
       
    }
    
    
    public String listenerCambioCircuitoD()    {
	
    	 	
	 	
	 return "listener";
       
    }
    
    
    /**
     * Método que obtiene una lista de las oficinas de destino
     * @return Lista de oficinas
     */    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List getListaOficinasD(){    
        Vector vOficinas = new Vector();
        List lOficinas = oficinaBO.getOficinas();
        
        vOficinas.add(new SelectItem(Integer.valueOf(0), "Seleccione una oficina"));
        if(lOficinas != null && !lOficinas.isEmpty()){
            for (int i = 0; i < lOficinas.size(); i++) {
                Oficina oficina = (Oficina) lOficinas.get(i);
                vOficinas.add(new SelectItem(oficina.getCodigoOficina(), oficina.getCodigoOficina() + " - " + oficina.getNombreOficina()));
            }  
        }
        
        return vOficinas;
    }    
    
    /**
     * Método que obtiene una lista de las regiones de destino
     * @return lista regiones.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List getRegionesD() {
        List regiones = null;
        if(!this.codigoOficinaD.equals(Integer.valueOf(0))){
            regiones = this.regionBO.getRegionesPorOficina(this.codigoOficinaD);
        }
        List items = new ArrayList();
        items.add(new SelectItem(Integer.valueOf(0), "Seleccione una región"));
        if(regiones != null && !regiones.isEmpty()){
            for(int i = 0; i < regiones.size(); i++){
                Region region = (Region) regiones.get(i);
                SelectItem item = new SelectItem();
                item.setValue(region.getRegion());
                item.setLabel(region.getRegion() + " - " + region.getNombreRegion());
                items.add(item);
            }
        }
        return items;
    }
    
    /**
     * Método que obtiene una lista de las subregiones de destino según la región que se seleccione
     * @return lista subRegiones.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List getSubRegionesD() {     
        List subRegiones = null;
        if(!this.regionD.equals(Integer.valueOf(0))){
            subRegiones = this.subRegionBO.getSubRegiones(this.regionD);
        }
        List items = new ArrayList();
        items.add(new SelectItem(Integer.valueOf(0), "Seleccione una subregión"));
        if(subRegiones!= null && !subRegiones.isEmpty()){
            for(int i = 0; i < subRegiones.size(); i++){
                SubRegion subRegion = (SubRegion) subRegiones.get(i);
                SelectItem item = new SelectItem();
                item.setValue(subRegion.getSubRegionID().getSubRegion());
                item.setLabel(subRegion.getSubRegionID().getSubRegion() + " - " + subRegion.getNombreSubRegion());
                items.add(item);
            }
        }
        return items;
    }
    
    /**
     * Método que obtiene una lista de las subestaciones de destino según la subregión que se seleccione
     * @return lista subEstaciones.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List getSubEstacionesD() {
        List subEstaciones = null;
        if((!this.regionD.equals(Integer.valueOf(0))) && (!this.subregionD.equals(Integer.valueOf(0)))){
            subEstaciones = this.subEstacionBO.getSubEstaciones(this.regionD, this.subregionD);
        }
        List items = new ArrayList();
        items.add(new SelectItem(Integer.valueOf(0), "Seleccione una subestación"));
        if(subEstaciones != null && !subEstaciones.isEmpty()){
            for(int i = 0; i < subEstaciones.size(); i++){
                SubEstacion subEstacion = (SubEstacion) subEstaciones.get(i);
                SelectItem item = new SelectItem();
                item.setValue(subEstacion.getCodigoSubEstacion());
                item.setLabel(subEstacion.getCodigoSubEstacion() + " - " + subEstacion.getNombreSubEstacion());
                items.add(item);
            }
        }
        return items;
    }
    
    /**
     * Método que obtiene una lista de los circuitos de destino según la subestación que se seleccione
     * @return lista circuitos.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List getCircuitosD() {
    	List circuitos = null;
    	if(!this.subestacionD.equals(Integer.valueOf(0))){
    		circuitos = this.circuitoBO.getCircuitosActivas(this.subestacionD);
        }
    	List items = new ArrayList();
    	items.add(new SelectItem(Integer.valueOf(0), "Seleccione un circuito"));
        if(circuitos != null && !circuitos.isEmpty()){
            for(int i = 0; i < circuitos.size(); i++){
                Circuito circuito = (Circuito) circuitos.get(i);
                SelectItem item = new SelectItem();
                item.setValue(circuito.getCircuitoID().getCircuito());
                item.setLabel(circuito.getCircuitoID().getCircuito() + " - " + circuito.getNombreCircuito());
                items.add(item);
            }
        }
        return items;
    }

    
    /**
     * Método que obtiene una lista de las secciones de destino según el circuito que se seleccione
     * @return lista secciones.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List getSeccionesD() {
        List secciones = null;
        if((!this.subestacionD.equals(Integer.valueOf(0))) && (!this.circuitoD.equals(Integer.valueOf(0)))){
            //secciones = this.seccionBO.getSeccionesDestino(this.subestacionD, this.circuitoD);
        	System.out.println(this.regionD+"\n"+this.subregionD+"\n"+this.subestacionD+"\n"+this.circuitoD);
            secciones = this.seccionBO.getSeccionesDestino(this.regionD, this.subregionD, this.subestacionD, this.circuitoD);
        }
        List items = new ArrayList();
        items.add(new SelectItem(Long.valueOf(0), "Seleccione una sección"));
        if(secciones != null && !secciones.isEmpty()){
            for(int i = 0; i < secciones.size(); i++){
                Seccion seccion = (Seccion) secciones.get(i);
                SelectItem item = new SelectItem();
                item.setValue(seccion.getSeccionID().getSeccion());
                item.setLabel(seccion.getSeccionID().getSeccion() + " - " + seccion.getNombreSeccion());
                items.add(item);
            }
        }
        return items;
    }
    
    public String listenerSeccionesD(){
        String resultado = "success";
        if((!this.seccionD.equals(Long.valueOf(0))) && (!this.circuitoD.equals(Integer.valueOf(0))) && (!this.subestacionD.equals(Integer.valueOf(0)))){
            this.seccDestino.getSeccionID().setSubEstacion(this.subestacionD);
            this.seccDestino.getSeccionID().setCircuito(this.circuitoD);
            this.seccDestino.getSeccionID().setSeccion(this.seccionD);
            this.seccDestino = this.seccionBO.buscar(this.seccDestino.getSeccionID());
        }
        return resultado;
    }
    
    /********************************************* MÉTODOS ********************************************/
    
    /**
     * 
     * Método agregarSeccionO
     * Agrega un elemento a la lista de secciones de origen
     * @return string
     */
    public String agregarSeccionO(){
        String resultado = "error";
       if(this.validarAgregarO()){
            if(this.tipo.equals(Integer.valueOf(0))){
                if(this.listaOrigen.size() < Integer.valueOf(1)){
                    this.listaOrigen.add(this.seccOrigen);
                    this.seccOrigen = new Seccion();
                    resultado = "success";
                }else{
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Solo se permite seleccionar una sección tanto en origen como en destino"));
                }
            }
            if(this.tipo.equals(Integer.valueOf(1))){
                this.listaOrigen.add(this.seccOrigen);
                this.seccOrigen = new Seccion();
                resultado = "success";
            }
            if(this.tipo.equals(Integer.valueOf(2))){
                if(this.listaOrigen.size() < Integer.valueOf(1)){
                    this.listaOrigen.add(this.seccOrigen);
                    this.seccOrigen = new Seccion();
                    resultado = "success";
                }else{
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Solo se permite seleccionar una sección origen."));
                }
            }
        }
        return resultado;
    }
    
    public boolean validarAgregarO(){
       
        boolean correcto = true;
        if(this.codigoOficinaO.equals(Integer.valueOf(0))){
            correcto = false;
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una oficina."));
        }
        if(this.regionO.equals(Integer.valueOf(0))){
            correcto = false;
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una región."));
        }
        if(this.subregionO.equals(Integer.valueOf(0))){
            correcto = false;
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una subregión."));
        }
        if(this.subestacionO.equals(Integer.valueOf(0))){
            correcto = false;
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una subestación."));
        }
        if(this.circuitoO.equals(Integer.valueOf(0))){
            correcto = false;
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar un circuito."));
        }
        if(this.seccionO.equals(Long.valueOf(0))){
            correcto = false;
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una sección."));
        }
        return correcto;
    }
    
    /**
     * 
     * Método agregarSeccionD
     * Agrega un elemento a la lista de secciones de destino
     * @return string
     */
    public String agregarSeccionD(){
        String resultado = "error";
        if(this.validarAgregarD()){
            if(this.tipo.equals(Integer.valueOf(0))){
                if(this.listaDestino.size() < Integer.valueOf(1)){
                    this.listaDestino.add(this.seccDestino);
                    this.seccDestino = new Seccion();
                    resultado = "success";
                }else{
                    
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Solo se permite seleccioar una sección tanto en origen como en destino."));
                }
            }
            if(this.tipo.equals(Integer.valueOf(1))){
                if(this.listaDestino.size() < Integer.valueOf(1)){
                    this.listaDestino.add(this.seccDestino);
                    this.seccDestino = new Seccion();
                    resultado = "success";
                }else{
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Solo se permite seleccionar una sección destino."));
                }
            }
            if(this.tipo.equals(Integer.valueOf(2))){
                this.listaDestino.add(this.seccDestino);
                this.seccDestino = new Seccion();
                resultado = "success";
            }
        }
        return resultado;
    }
    
    public boolean validarAgregarD(){
        boolean correcto = true;
        if(this.codigoOficinaD.equals(Integer.valueOf(0))){
            correcto = false;
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una sección."));
        }
        if(this.regionD.equals(Integer.valueOf(0))){
            correcto = false;
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una región."));
        }
        if(this.subregionD.equals(Integer.valueOf(0))){
            correcto = false;
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una subregión."));
        }
        if(this.subestacionD.equals(Integer.valueOf(0))){
            correcto = false;
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una subestación."));
        }
        if(this.circuitoD.equals(Integer.valueOf(0))){
            correcto = false;
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar un circuito."));
        }
        if(this.seccionD.equals(Long.valueOf(0))){
            correcto = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una sección."));
        }
        return correcto;
    }
    
    /**
     * 
     * Método borrarOrigen
     * Elimina un objeto de la lista de secciones de origen
     * @return string
     */
    public String borrarOrigen(){
        String resultado = "error";
        if(!this.listaOrigen.isEmpty()){
            Seccion sec = (Seccion) this.dataTableOrigen.getRowData();
            this.listaOrigen.remove(sec);
        }
        return resultado;
    }
    
    /**
     * 
     * Método borrarDestino
     * Elimina un objeto de la lista de secciones de destino
     * @return string
     */
    public String borrarDestino(){
        String resultado = "error";
        if(!this.listaDestino.isEmpty()){
            Seccion sec = (Seccion) this.dataTableDestino.getRowData();
            this.listaDestino.remove(sec);
        }
        return resultado;
    }
    
    /**
     * 
     * Método grabar
     * Realiza el cambio de secciones según la opcione escogida 
     * @return string
     */
    public String grabar(){
        String resultado = "error";
        try{
            if(this.validarSecciones()){
            	 System.out.println("validaciones correctas");
            	 System.out.println("TIPO PROCESO 0-sustituir 1-Unir 2-Dividir:"+this.tipo);
                if(this.seccionBO.elimiarSecciones(this.empleado.getCedula().toString().trim())){
                    if(this.tipo.equals(Integer.valueOf(0))){ //sustituir
                    	System.out.println("entro sustituir");
                        if(this.seccionBO.agregarTodo(this.listaOrigen, this.empleado.getCedula().toString().trim())){
                        resultado = this.seccionBO.cambioSecciones(this.listaDestino.get(0), this.tipo, this.empleado.getCedula().toString().trim());
                        }
                    }
                    if(this.tipo.equals(Integer.valueOf(1))){ //unir
                    	System.out.println("entro a unir");
                        if(this.seccionBO.agregarTodo(this.listaOrigen, this.empleado.getCedula().toString().trim())){
                            resultado = this.seccionBO.cambioSecciones(this.listaDestino.get(0), this.tipo, this.empleado.getCedula().toString().trim());
                        }
                    }
                    if(this.tipo.equals(Integer.valueOf(2))){ //dividir
                    	System.out.println("entro a dividir");
                        if(this.seccionBO.agregarTodo(this.listaDestino, this.empleado.getCedula().toString().trim())){
                            resultado = this.seccionBO.cambioSecciones(this.listaOrigen.get(0), this.tipo, this.empleado.getCedula().toString().trim());
                        }
                    }
                }
                System.out.println("RESULTADO DE PROCESO:"+resultado);
                if(resultado.equals("success")){
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Se ha realizado el cambio satisfactoriamente."));
                }else if(resultado.equals("simap")){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Se ha presentado un error en el procedimiento almacenado de SIMAP."));
                }
                    
            }
        }catch (Exception e) {
            e.printStackTrace();
            resultado = "error";
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Se ha presentado un error en el procedimiento almacenado."));
        }
        return resultado;
    }
    
    /**
     * 
     * Método validarSecciones
     * Verifica que tanto las secciones de origen como las de destino cumplan con los requerimientos necesarios
     * @return true->correcto  false->error
     */
    public boolean validarSecciones(){
    	System.out.println("Entro a validarSecciones()");
        boolean correcto = true;
        if((this.listaOrigen == null || this.listaOrigen.isEmpty())){
        	correcto = false;
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Necesita agregar valores en la lista de origen para poder realizar el proceso."));
        }else{
        	if((this.listaDestino == null ||this.listaDestino.isEmpty())){
        		correcto = false;
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Necesita agregar valores en la lista de destino para poder realzar el proceso."));
        	}else{
        	    //Se deshabilita la validacion para la opcion de sustituir
//	        	if(this.tipo.equals(Integer.valueOf(0))){
//	                if(!this.validarCamposComunes(this.listaOrigen.get(0), this.listaDestino.get(0))){
//	                    correcto = false;
//	                }else{
//	                    if(!this.listaOrigen.get(0).getKmsSeccion().equals(this.listaDestino.get(0).getKmsSeccion())){
//	                        correcto = false;
//	                        FacesMessage msg = new FacesMessage();
//	                        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//	                        msg.setSummary("Los kilómetros de la(s) sección(es) destino  deben ser la misma cantidad que  la(s) sección(es) origen");            
//	                        context.addMessage(null, msg);
//	                    }
//	                    if(!this.listaOrigen.get(0).getAbonadoSeccion().equals(this.listaDestino.get(0).getAbonadoSeccion())){
//	                        correcto = false;
//	                        FacesMessage msg = new FacesMessage();
//	                        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//	                        msg.setSummary("Los clientes de la(s) sección(es) destino  deben ser la misma cantidad que  la(s) sección(es) origen");            
//	                        context.addMessage(null, msg);
//	                    }
//	                }
//	            }
	            if(this.tipo.equals(Integer.valueOf(1))){
	                if((this.listaOrigen.isEmpty()) || Integer.valueOf(1).equals(this.listaOrigen.size())){
	                    correcto = false;
	                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar dos o más secciones origen."));
	                    
	                }else{
	                    for(int i=0; i<listaOrigen.size(); i++){
	                        Seccion secc = listaOrigen.get(i);
	                        if(!this.validarCamposComunes(secc, this.listaDestino.get(0))){
	                            correcto = false;
	                        }
	                    }
	                    if(!this.validarListaSecciones(this.listaDestino.get(0), this.listaOrigen)){
	                        correcto = false;
	                    }
	                }
	            }
	            if(this.tipo.equals(Integer.valueOf(2))){
	                if((this.listaDestino.isEmpty()) || Integer.valueOf(1).equals(this.listaDestino.size())){
	                    correcto = false;
	                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ".Debe seleccionar dos o más secciones destino"));
	                }else{
	                    for(int i=0; i<listaDestino.size(); i++){
	                        Seccion secc = listaDestino.get(i);
	                        if(!this.validarCamposComunes(this.listaOrigen.get(0), secc)){
	                            correcto = false;
	                        }
	                    }
	                    if(!this.validarListaSecciones(this.listaOrigen.get(0), this.listaDestino)){
	                        correcto = false;
	                    }
	                }
	            }
	        }
        }
        return correcto;
    }
    
    public boolean validarListaSecciones(Seccion secc, List<Seccion> lista){
        boolean correcto=true;
        Long abon = Long.valueOf(0);
        Double kms = Double.valueOf(0);
        for(int i=0; i<lista.size(); i++){
            abon = abon + lista.get(i).getAbonadoSeccion();
            kms = (Math.round((kms + lista.get(i).getKmsSeccion())*100))/(double)100;
        }
        if(!secc.getKmsSeccion().equals(kms)){
            correcto = false;
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Los kilómetros de la(s) sección(es) destino deben ser la misma cantidad que la(s) sección(es) origen."));
        }
        if(!secc.getAbonadoSeccion().equals(abon)){
            correcto = false;
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Los clientes de la(s) sección(es) destino deben ser la misma cantidad que la(s) sección(es) origen."));
        }
        return correcto;
    }
    
    public boolean validarCamposComunes(Seccion secO, Seccion secD){
        boolean correcto=true;
        if(!secO.getRegion().equals(secD.getRegion())){
            correcto = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de región sea el mismo."));
        }
        if(!secO.getSubRegion().equals(secD.getSubRegion())){
            correcto = false;
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de subregión sea el mismo."));
        }
        if(!secO.getSeccionID().getSubEstacion().equals(secD.getSeccionID().getSubEstacion())){
            correcto = false;
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de subestación sea el mismo."));
        }
        if(secO.getSeccionID().getCircuito().equals(secD.getSeccionID().getCircuito())){
        	if(secO.getSeccionID().getSeccion().equals(secD.getSeccionID().getSeccion())){
                correcto = false;
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Los códigos de las secciones deben ser diferentes."));
            }
        }
        return correcto;
    }
    
    /**
     * @see com.vvs.jsf.AbstractFacesController#getPropertyFieldName(java.lang.String)
     */
    @Override
    protected String getPropertyFieldName(String arg0) {
        return null;
    }

    /**
     * Método accesor del atributo tipo.
     * @return Retorna el atributo tipo.
     */
    public Integer getTipo() {
        return this.tipo;
    }

    /**
     * Método modificador del atributo tipo.
     * @param tipo El dato para modificar el atributo tipo.
     */
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    /**
     * Método accesor del atributo dataTableOrigen.
     * @return Retorna el atributo dataTableOrigen.
     */
    public DataTable getDataTableOrigen() {
        return this.dataTableOrigen;
    }

    /**
     * Método modificador del atributo dataTableOrigen.
     * @param dataTableOrigen El dato para modificar el atributo dataTableOrigen.
     */
    public void setDataTableOrigen(DataTable dataTableOrigen) {
        this.dataTableOrigen = dataTableOrigen;
    }

    /**
     * Método accesor del atributo dataTableDestino.
     * @return Retorna el atributo dataTableDestino.
     */
    public DataTable getDataTableDestino() {
        return this.dataTableDestino;
    }

    /**
     * Método modificador del atributo dataTableDestino.
     * @param dataTableDestino El dato para modificar el atributo dataTableDestino.
     */
    public void setDataTableDestino(DataTable dataTableDestino) {
        this.dataTableDestino = dataTableDestino;
    }

    /**
     * Método accesor del atributo listaOrigen.
     * @return Retorna el atributo listaOrigen.
     */
    public List<Seccion> getListaOrigen() {
        return this.listaOrigen;
    }

    /**
     * Método modificador del atributo listaOrigen.
     * @param listaOrigen El dato para modificar el atributo listaOrigen.
     */
    public void setListaOrigen(List<Seccion> listaOrigen) {
        this.listaOrigen = listaOrigen;
    }

    /**
     * Método accesor del atributo listaDestino.
     * @return Retorna el atributo listaDestino.
     */
    public List<Seccion> getListaDestino() {
        return this.listaDestino;
    }

    /**
     * Método modificador del atributo listaDestino.
     * @param listaDestino El dato para modificar el atributo listaDestino.
     */
    public void setListaDestino(List<Seccion> listaDestino) {
        this.listaDestino = listaDestino;
    }

    /**
     * Método accesor del atributo codigoOficinaO.
     * @return Retorna el atributo codigoOficinaO.
     */
    public Integer getCodigoOficinaO() {
        return this.codigoOficinaO;
    }

    /**
     * Método modificador del atributo codigoOficinaO.
     * @param codigoOficinaO El dato para modificar el atributo codigoOficinaO.
     */
    public void setCodigoOficinaO(Integer codigoOficinaO) {
        this.codigoOficinaO = codigoOficinaO;
    }

    /**
     * Método accesor del atributo regionO.
     * @return Retorna el atributo regionO.
     */
    public Integer getRegionO() {
        return this.regionO;
    }

    /**
     * Método modificador del atributo regionO.
     * @param regionO El dato para modificar el atributo regionO.
     */
    public void setRegionO(Integer regionO) {
        this.regionO = regionO;
    }

    /**
     * Método accesor del atributo subregionO.
     * @return Retorna el atributo subregionO.
     */
    public Integer getSubregionO() {
        return this.subregionO;
    }

    /**
     * Método modificador del atributo subregionO.
     * @param subregionO El dato para modificar el atributo subregionO.
     */
    public void setSubregionO(Integer subregionO) {
        this.subregionO = subregionO;
    }

    /**
     * Método accesor del atributo subestacionO.
     * @return Retorna el atributo subestacionO.
     */
    public Integer getSubestacionO() {
        return this.subestacionO;
    }

    /**
     * Método modificador del atributo subestacionO.
     * @param subestacionO El dato para modificar el atributo subestacionO.
     */
    public void setSubestacionO(Integer subestacionO) {
        this.subestacionO = subestacionO;
    }

    /**
     * Método accesor del atributo circuitoO.
     * @return Retorna el atributo circuitoO.
     */
    public Integer getCircuitoO() {
        return this.circuitoO;
    }

    /**
     * Método modificador del atributo circuitoO.
     * @param circuitoO El dato para modificar el atributo circuitoO.
     */
    public void setCircuitoO(Integer circuitoO) {
        this.circuitoO = circuitoO;
    }

    /**
     * Método accesor del atributo seccionO.
     * @return Retorna el atributo seccionO.
     */
    public Long getSeccionO() {
        return this.seccionO;
    }

    /**
     * Método modificador del atributo seccionO.
     * @param seccionO El dato para modificar el atributo seccionO.
     */
    public void setSeccionO(Long seccionO) {
        this.seccionO = seccionO;
    }

    /**
     * Método accesor del atributo codigoOficinaD.
     * @return Retorna el atributo codigoOficinaD.
     */
    public Integer getCodigoOficinaD() {
        return this.codigoOficinaD;
    }

    /**
     * Método modificador del atributo codigoOficinaD.
     * @param codigoOficinaD El dato para modificar el atributo codigoOficinaD.
     */
    public void setCodigoOficinaD(Integer codigoOficinaD) {
        this.codigoOficinaD = codigoOficinaD;
    }

    /**
     * Método accesor del atributo regionD.
     * @return Retorna el atributo regionD.
     */
    public Integer getRegionD() {
        return this.regionD;
    }

    /**
     * Método modificador del atributo regionD.
     * @param regionD El dato para modificar el atributo regionD.
     */
    public void setRegionD(Integer regionD) {
        this.regionD = regionD;
    }

    /**
     * Método accesor del atributo subregionD.
     * @return Retorna el atributo subregionD.
     */
    public Integer getSubregionD() {
        return this.subregionD;
    }

    /**
     * Método modificador del atributo subregionD.
     * @param subregionD El dato para modificar el atributo subregionD.
     */
    public void setSubregionD(Integer subregionD) {
        this.subregionD = subregionD;
    }

    /**
     * Método accesor del atributo subestacionD.
     * @return Retorna el atributo subestacionD.
     */
    public Integer getSubestacionD() {
        return this.subestacionD;
    }

    /**
     * Método modificador del atributo subestacionD.
     * @param subestacionD El dato para modificar el atributo subestacionD.
     */
    public void setSubestacionD(Integer subestacionD) {
        this.subestacionD = subestacionD;
    }

    /**
     * Método accesor del atributo circuitoD.
     * @return Retorna el atributo circuitoD.
     */
    public Integer getCircuitoD() {
        return this.circuitoD;
    }

    /**
     * Método modificador del atributo circuitoD.
     * @param circuitoD El dato para modificar el atributo circuitoD.
     */
    public void setCircuitoD(Integer circuitoD) {
        this.circuitoD = circuitoD;
    }

    /**
     * Método accesor del atributo seccionD.
     * @return Retorna el atributo seccionD.
     */
    public Long getSeccionD() {
        return this.seccionD;
    }

    /**
     * Método modificador del atributo seccionD.
     * @param seccionD El dato para modificar el atributo seccionD.
     */
    public void setSeccionD(Long seccionD) {
        this.seccionD = seccionD;
    }

    /**
     * Método accesor del atributo oficinaBO.
     * @return Retorna el atributo oficinaBO.
     */
    public OficinaBO getOficinaBO() {
        return this.oficinaBO;
    }

    /**
     * Método modificador del atributo oficinaBO.
     * @param oficinaBO El dato para modificar el atributo oficinaBO.
     */
    public void setOficinaBO(OficinaBO oficinaBO) {
        this.oficinaBO = oficinaBO;
    }

    /**
     * Método accesor del atributo regionBO.
     * @return Retorna el atributo regionBO.
     */
    public RegionBO getRegionBO() {
        return this.regionBO;
    }

    /**
     * Método modificador del atributo regionBO.
     * @param regionBO El dato para modificar el atributo regionBO.
     */
    public void setRegionBO(RegionBO regionBO) {
        this.regionBO = regionBO;
    }

    /**
     * Método accesor del atributo subRegionBO.
     * @return Retorna el atributo subRegionBO.
     */
    public SubRegionBO getSubRegionBO() {
        return this.subRegionBO;
    }

    /**
     * Método modificador del atributo subRegionBO.
     * @param subRegionBO El dato para modificar el atributo subRegionBO.
     */
    public void setSubRegionBO(SubRegionBO subRegionBO) {
        this.subRegionBO = subRegionBO;
    }

    /**
     * Método accesor del atributo subEstacionBO.
     * @return Retorna el atributo subEstacionBO.
     */
    public SubEstacionBO getSubEstacionBO() {
        return this.subEstacionBO;
    }

    /**
     * Método modificador del atributo subEstacionBO.
     * @param subEstacionBO El dato para modificar el atributo subEstacionBO.
     */
    public void setSubEstacionBO(SubEstacionBO subEstacionBO) {
        this.subEstacionBO = subEstacionBO;
    }

    /**
     * Método accesor del atributo circuitoBO.
     * @return Retorna el atributo circuitoBO.
     */
    public CircuitoBO getCircuitoBO() {
        return this.circuitoBO;
    }

    /**
     * Método modificador del atributo circuitoBO.
     * @param circuitoBO El dato para modificar el atributo circuitoBO.
     */
    public void setCircuitoBO(CircuitoBO circuitoBO) {
        this.circuitoBO = circuitoBO;
    }

    /**
     * Método accesor del atributo seccionBO.
     * @return Retorna el atributo seccionBO.
     */
    public SeccionBO getSeccionBO() {
        return this.seccionBO;
    }

    /**
     * Método modificador del atributo seccionBO.
     * @param seccionBO El dato para modificar el atributo seccionBO.
     */
    public void setSeccionBO(SeccionBO seccionBO) {
        this.seccionBO = seccionBO;
    }

    /**
     * Método accesor del atributo empleadoBO.
     * @return Retorna el atributo empleadoBO.
     */
    public EmpleadoBO getEmpleadoBO() {
        return this.empleadoBO;
    }

    /**
     * Método modificador del atributo empleadoBO.
     * @param empleadoBO El dato para modificar el atributo empleadoBO.
     */
    public void setEmpleadoBO(EmpleadoBO empleadoBO) {
        this.empleadoBO = empleadoBO;
    }

    /**
     * Método accesor del atributo seccOrigen.
     * @return Retorna el atributo seccOrigen.
     */
    public Seccion getSeccOrigen() {
        return this.seccOrigen;
    }

    /**
     * Método modificador del atributo seccOrigen.
     * @param seccOrigen El dato para modificar el atributo seccOrigen.
     */
    public void setSeccOrigen(Seccion seccOrigen) {
        this.seccOrigen = seccOrigen;
    }

    /**
     * Método accesor del atributo seccDestino.
     * @return Retorna el atributo seccDestino.
     */
    public Seccion getSeccDestino() {
        return this.seccDestino;
    }

    /**
     * Método modificador del atributo seccDestino.
     * @param seccDestino El dato para modificar el atributo seccDestino.
     */
    public void setSeccDestino(Seccion seccDestino) {
        this.seccDestino = seccDestino;
    }

    /**
     * Método accesor del atributo empleado.
     * @return Retorna el atributo empleado.
     */
    public Empleado getEmpleado() {
        return this.empleado;
    }

    /**
     * Método modificador del atributo empleado.
     * @param empleado El dato para modificar el atributo empleado.
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

	@Override
	protected void resetController() {
		// TODO Apéndice de método generado automáticamente
		
	}

	public UsuarioOficinaBO getUsuarioOficinaBO() {
		return usuarioOficinaBO;
	}

	public void setUsuarioOficinaBO(UsuarioOficinaBO usuarioOficinaBO) {
		this.usuarioOficinaBO = usuarioOficinaBO;
	}

}
