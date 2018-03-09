package cr.go.ice.interrupciones.web.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.sql.DataSource;

import com.vvs.jsf.AbstractFacesController;
import com.vvs.utilidades.Fechas;
import com.vvs.utilidades.Utilidades;

import cr.go.ice.interrupciones.BO.CircuitoBO;
import cr.go.ice.interrupciones.BO.RegionBO;
import cr.go.ice.interrupciones.BO.SeccionBO;
import cr.go.ice.interrupciones.BO.SubEstacionBO;
import cr.go.ice.interrupciones.BO.SubRegionBO;
import cr.go.ice.interrupciones.domain.Circuito;
import cr.go.ice.interrupciones.domain.Region;
import cr.go.ice.interrupciones.domain.Seccion;
import cr.go.ice.interrupciones.domain.SubEstacion;
import cr.go.ice.interrupciones.domain.SubRegion;
import cr.go.ice.interrupciones.servlets.ServletReporte;
import cr.go.ice.interrupciones.utils.UtilidadesFaces;
import cr.go.ice.interrupciones.web.Recurso;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ReporteInterrupcionClientesAfectadosController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ReporteInterrupcionClientesAfectadosController.java</code>.</p>
 * <p>Fecha creación: 16/04/2010</p>
 * <p>Ultima actualización: 16/04/2010</p>
 * @author Vista Verde Tecnologia (Evelyn)
 * @version 1.5
 */
public class ReporteInterrupcionClientesAfectadosController extends AbstractFacesController {
    
    
    private static final Integer CERO = new Integer(0);
	
	private Integer medidor;
    private Integer pueblo;
    private Integer calle;
    private Integer poste;
    private Integer secue;
    private Date fechaInicio;
    private Date fechaFinal;
    private String nivelRed;
    private Integer region;
    private Integer subregion;
    private Integer subestacion;
    private Integer circuito;
    private Long seccion;
    private Integer formato;
    
    private RegionBO regionBO;
    private SubRegionBO subRegionBO;
    private SubEstacionBO subEstacionBO;
    private CircuitoBO circuitoBO;
    private SeccionBO seccionBO;
    
    private List nivelList;
    private List regionesList;
    private List subRegionesList;
    private List subEstacionesList;
    private List circuitoList;
    private List seccionList;
    
    String sqlFiltro = "";
    private  static final String jasperPath = "/jasperReports/";  
    public static String servletJasperPath = "/jasperReports/";; 
    /**
     * Constructor
     *
     */
    public ReporteInterrupcionClientesAfectadosController(){
        this.reiniciarCampos();
    }
    
    /**
     * Reinicia los valores de la página
     *
     */
    private void reiniciarCampos(){
        this.fechaInicio = new Date();
        this.fechaFinal = new Date();  
        
        /*this.region = null;
        this.subregion = null;
        this.subestacion = null;
        this.circuito = null;
        this.seccion = null;*/
        
        this.region = new Integer(0);
        this.subregion = new Integer(0);
        this.subestacion = new Integer(0);
        this.circuito = new Integer(0);
        this.seccion = new Long(0);
        
        
        
        this.medidor = null;
        this.pueblo = null;
        this.calle = null;
        this.poste = null;
        this.secue = null;

        this.formato = UtilidadesFaces.FORMATO_PDF;
        this.nivelRed = null;
        
        this.nivelList=new ArrayList();
        this.regionesList=null;
        this.subRegionesList=null;
        this.subEstacionesList= null;
        this.circuitoList=null;
        this.seccionList=null;
        
    }
    
     
    /**
     * Precarga la oficina del usuario de la aplicación, si el mismo es un usuario CLOR
     * @param context
     */
    public void load(FacesContext context){
    	
        Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
        
        if(limpiar != null){
            reiniciarCampos();
        }
        
    }
    
    
    
    
    
    /**
     * Asigna el codigo de nivel de red de acuerdo a el cual se habilitan o deshabilitan los combos de red
     * @param v
     * @return success
     */ 
    public void listenerNivelRed(){
    	this.circuito = new Integer(0);
    	this.seccion = new Long(0);
        
    }        
    
    /**
     * Retorna una lista de select item de los diferentes niveles de red
     * @return Lista de niveles de red
     */    
    public List getListaNivelesRed(){
    	return UtilidadesFaces.getListaNivelesRedCircuito();
    }  
     
    /**
     * Comment for listenerRegion
     * @param v
     * @return "listener" a Region
     */
    public void listenerRegion(){
    	this.subregion = new Integer(0);
    	this.subestacion = new Integer(0);
    	this.circuito = new Integer(0);
    	this.seccion = new Long(0);
    }
    /**
     * TODO (Descripción) 
     * @param i
     */
    private void initCircuitos(Integer newSubEstacion) {
        this.circuitoList = new ArrayList();
        if(newSubEstacion!=null && !newSubEstacion.equals(CERO)){
         //   System.out.println("subestaciones = "+this.subestacion);
            List circuitos = this.circuitoBO.getCircuitos(newSubEstacion);
           
            this.circuitoList.add(new SelectItem(CERO, "Seleccione un Circuito"));
     
            if(circuitos != null){
                for(int i = 0; i < circuitos.size(); i++){
                    Circuito circuito = (Circuito) circuitos.get(i);
                    this.circuitoList.add(new SelectItem(circuito.getCircuitoID().getCircuito(),circuito.getCircuitoID().getCircuito() + " - " + circuito.getNombreCircuito()));
                }
                
            }
           
        }
        else{
            this.circuitoList.add(new SelectItem(CERO, "Seleccione un Circuito"));
        }
    }
    
    private void initSecciones(Integer circuito, Integer subestacion) {
    	System.out.println("seccion " + this.seccion);
    	this.seccionList = new ArrayList();
        this.seccionList.add(new SelectItem(new Long(0), "Seleccione una Sección"));
        if (circuito != null && subestacion != null) {
        	List secciones = this.seccionBO.getSeccionesFiltro(this.region, this.subregion, subestacion, circuito);
            if (secciones != null && !secciones.isEmpty()) {
                for (int i = 0; i < secciones.size(); i++) {
                    Seccion seccion = (Seccion) secciones.get(i);
                    SelectItem item = new SelectItem();
                    item.setValue(seccion.getSeccionID().getSeccion());
                    item.setLabel(seccion.getSeccionID().getSeccion() + " - " + seccion.getNombreSeccion());
                    this.seccionList.add(item);
                }
            }
        }
        else{
            
            this.seccionList.add(new SelectItem(new Long(0), "Seleccione una Sección"));
            
        }
    }

    /**
     * TODO (Descripción) 
     * @param i
     */
    private void initSubEstaciones(Integer region, Integer subregion) {
    	System.out.println("Sub " + this.subestacion);
        this.subEstacionesList = new ArrayList();
        this.subEstacionesList.add(new SelectItem(new Integer(CERO), "Seleccione una Subestación"));
        if (subregion !=null && region !=null ) {
        	List subEstaciones = this.subEstacionBO.getSubEstaciones(region, subregion);
            if (subEstaciones != null && !subEstaciones.isEmpty()) {
                for (int i = 0; i < subEstaciones.size(); i++) {
                    SubEstacion subEstacion = (SubEstacion) subEstaciones.get(i);
                    SelectItem item = new SelectItem();
                    item.setValue(subEstacion.getCodigoSubEstacion());
                    item.setLabel(subEstacion.getCodigoSubEstacion() + " - " + subEstacion.getNombreSubEstacion());
                    this.subEstacionesList.add(item);
                }
            }
        } 
        else{
           
            this.subEstacionesList.add(new SelectItem(CERO, "Seleccione una Subestación"));
            
        }
    }
    
    private void initRegiones(){
        this.regionesList = new ArrayList(); 
//        System.out.println("Entro a getRegiones");
        List regiones = this.regionBO.getRegiones();
        this.regionesList.add(new SelectItem(CERO, "Seleccione una Región"));
        if(regiones != null ){
            for(int i = 0; i < regiones.size(); i++){
                Region region = (Region) regiones.get(i);
                
                this.regionesList.add( new SelectItem(region.getRegion(),region.getRegion() + " - " + region.getNombreRegion()));
            }
            
        }        
        
        
    }

    /**
     * TODO (Descripción) 
     * @param region2
     */
    private void initSubRegiones(Integer newRegion) {
        this.subRegionesList = new ArrayList();
 //       System.out.println("Entro a getSubRegiones");
        if(newRegion!=null && !newRegion.equals(CERO)){
            List subRegiones = this.subRegionBO.getSubRegiones(newRegion);
             
            this.subRegionesList.add(new SelectItem(CERO, "Seleccione una Subregión"));
             
            if(subRegiones!= null && !subRegiones.isEmpty()){
                for(int i = 0; i < subRegiones.size(); i++){
                    SubRegion subRegion = (SubRegion) subRegiones.get(i);
                    this.subRegionesList.add( new SelectItem(subRegion.getSubRegionID().getSubRegion(),subRegion.getSubRegionID().getSubRegion() + " - " + subRegion.getNombreSubRegion()));
                }
                
            }        
            
        }
        else{
           
            this.subRegionesList.add(new SelectItem(CERO, "Seleccione una Subregión"));
            
        }
    }

    /**
     * Metodo accesor de regiones.
     * @return Retorna el regiones.
     */
    public List getRegiones() {      
       
        if(this.regionesList==null){
            initRegiones();
        }
        return this.regionesList;
    }
    
    /**
     * Comment for listenerSubregion
     * @param v
     * @return "listener" a SubRegion
     */
    public void listenerSubregion(){
    	this.subestacion = new Integer(0);
    	this.circuito = new Integer(0);
    	this.seccion = new Long(0);
    }    
    
    /**
     * Metodo accesor de subRegiones.
     * @return Retorna el subRegiones.
     */
    public List getSubRegiones() {
    	initSubRegiones(this.region);
        return this.subRegionesList;
    } 
    
    /**
     * Comment for listenerSubestacion
     * @param v
     * @return "listener" a SubEstacion
     */
    public void listenerSubestacion(){
    	this.circuito = new Integer(0);
    	this.seccion = new Long(0);
    }   
    
    /**
     * Metodo accesor de subEstaciones.
     * @return Retorna el subEstaciones.
     */
    public List getSubEstaciones() {
    	initSubEstaciones(this.region, this.subregion);
        return this.subEstacionesList;
    }
   
    
    

    public List getSubEstacionesList() {
		return subEstacionesList;
	}

	public void setSubEstacionesList(List subEstacionesList) {
		this.subEstacionesList = subEstacionesList;
	}

	/**
     * Comment for listenerCircuito
     * @param v
     * @return "listener" a Circuito
     */
    public void listenerCircuito(){
    	this.seccion = new Long(0);
    }   
    
    
    /**
     * Metodo accesor de circuitos.
     * @return Retorna el circuitos.
     */
    public List getCircuitos() {  
    	initCircuitos(this.subestacion);
    	return this.circuitoList;
    }
   
    /**
     * Metodo accesor de secciones.
     * @return Retorna el secciones.
     */
    public List getSecciones() {  
    	initSecciones(this.circuito, this.subestacion);
        return this.seccionList;
        
    }
    

    /**
     * Lista de tipos de formatos para el reporte
     * @return lista de formatos
     */
    public List getListaFormatos(){
        
        List formatos = new ArrayList();       
        
        formatos.add(0,new SelectItem(UtilidadesFaces.FORMATO_PDF,"Pdf"));
        formatos.add(new SelectItem(UtilidadesFaces.FORMATO_XLS,"Excel"));
        return formatos;
    }
    
   
    
    /**
     * Realiza las validaciones de los parámetros de entrada dados por el usuario y genera el reporte
     * @return error o success de acuerdo a la correctitud de los parametros de entrada o no, de haberse encontrado
     * datos en la base de datos y de la correcta generación del reporte
     */ 
    public String aceptar(){
        if(validarParametros()){
            if(validarOpcionesParametros()){                
                return generarReporte();
            }else{
                return "error";
            }
        }else{
            return "error";
        }
    } 
    
    private boolean validarParametros(){
    	boolean correcto = true;
        FacesContext context = FacesContext.getCurrentInstance();
        if (this.medidor == null && !digitoLocalizacion()) {
        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Por favor indicar el medidor o la localización de la falla."));
        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Por favor indicar la localización de la falla o el medidor."));
            return false;
        }

        if (this.medidor != null && this.medidor.compareTo(CERO) <= 0) {
        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de medidor debe ser mayor a cero."));
            correcto = false;
        }

        //Si digito por lo menos un campo de la lacalizacion, procedemos a validarla
        if (digitoLocalizacion()) {

            // Validando pueblo
            if (this.pueblo == null) {
            	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar el pueblo."));
                correcto = false;
            } else if (this.pueblo.intValue() <= 0) {
            	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de pueblo debe ser mayor a cero."));
                correcto = false;
            }

            // Validando calle
            if (this.calle == null) {
            	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar un número de calle."));
            	correcto = false;
            } else if (this.calle.intValue() < 0) {
            	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de calle debe ser mayor o igual a cero."));
                correcto = false;
            }

            // Validando poste
            if (this.poste == null) {
            	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar un número de poste."));
            	correcto = false;
            } else if (this.poste.intValue() < 0) {
            	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de poste debe ser mayor o igual a cero."));
                correcto = false;
            }

            // Validando secuencia
            if (this.secue == null) {
            	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar un número de secuencia."));
            	correcto = false;
            } else if (this.secue.intValue() < 0) {
            	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de secuencia debe ser mayor o igual a cero."));
            	correcto = false;
            }

        }

        if (this.fechaInicio == null) {
        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar una fecha de inicio."));
        	correcto = false;
        }
        if (this.fechaFinal == null) {
        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar una fecha de fin."));
        	correcto = false;
        }
        return correcto;
         
    }
    
    private boolean digitoLocalizacion() {
    	if (this.pueblo==null)
    		return false;
    	else{
    		if(this.pueblo != 0)
    			return true;
    	}
    	if (this.calle==null)
    		return false;
    	else{
    		if(this.calle != 0)
    			return true;
    	}
    	if (this.poste==null)
    		return false;
    	else{
    		if(this.poste != 0)
    			return true;
    	}
    	
    	return true;
    	/*return this.pueblo!=null || this.calle!=null || this.poste!=null || this.secue!=null;*/
    }
    
    private boolean validarOpcionesParametros(){
        boolean correcto = true;
        FacesContext context = FacesContext.getCurrentInstance();
        if(this.fechaInicio.compareTo(new Date()) > 0){
        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha inicial es mayor a la fecha actual, por favor, corregirla."));
            correcto=false;
        }  
        if(this.fechaFinal.compareTo(new Date()) > 0){
        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha final es mayor a la fecha actual, por favor, corregirla."));
            correcto=false;
        }          
        long horasInicio = Fechas.millisToMinutes(this.fechaInicio.getTime());
        long horasFinal = Fechas.millisToMinutes(this.fechaFinal.getTime());
        long diferencia = (horasFinal - horasInicio);
        if(diferencia < 0){
        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha final debe ser mayor o igual que la fecha inicial."));
            correcto=false;
        }
         
        if(this.region.intValue() <= 0){
        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar la región."));
        	correcto=false;
        }         
        if(this.subregion.intValue() <= 0){
        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar la subregión."));
            correcto=false;
        }    
        if(this.subestacion.intValue() <= 0){
        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar la subestación."));
            correcto=false;
        }
        if(this.nivelRed.equals(UtilidadesFaces.NIVEL_RED_CIRCUITO)){
        	if(this.circuito.intValue() < new Integer(0).intValue()){
        		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar el circuito."));
                correcto=false;
            }      
        }else{
        	if(this.circuito.intValue() < 0){
        		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar el circuito"));
        		correcto=false;
            }      
        }
                 
        if(this.nivelRed.equals(UtilidadesFaces.NIVEL_RED_SECCION)){
        	if(this.circuito.equals(new Integer(0))){
        		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar el circuito"));
        		correcto=false;
            }
            if(this.seccion.intValue() < 0){
            	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una sección"));
            	correcto=false;
            }
        }       
        
        return correcto;
    }
    
    @SuppressWarnings("unchecked")
    private String generarReporte(){  
    	generarSQL();
      //  pasarParametrosReporte();    
        String nombreArchivo = "";
        String nombre = "";
        
        HashMap parametrosReporte = new HashMap();
        try{                    
            FacesContext ctx = FacesContext.getCurrentInstance();                       
//            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletReporte");
//            dispatcher.forward(request, response);                                  
//            response.getOutputStream().flush();
//            sqlFiltro="";
//
//            if(!FacesContext.getCurrentInstance().getResponseComplete() ) {
//                FacesContext.getCurrentInstance().responseComplete();
//            } 
            Map sessionMap = ctx.getExternalContext().getSessionMap();
 
           
            
            
            SimpleDateFormat sf = new SimpleDateFormat();
            sf.applyPattern("dd-MM-yyyy");
            Integer circuitoP = null;
//            if(sessionMap.get("circuito")!=null){
//             circuitoP = (Integer) sessionMap.remove("circuito"); 
//            }
            
            if(this.circuito!=null){
              circuitoP = this.circuito; 
             }
            Long seccionP = null;
//            if(sessionMap.get("seccion")!=null){
//                seccionP =(Long) sessionMap.remove("seccion");
//                }
            
            if(this.seccion!=null){
              seccionP =this.seccion;
              }
            
            parametrosReporte.put("psqlFiltro",this.sqlFiltro);
            parametrosReporte.put("pMedidor",this.medidor);
            parametrosReporte.put("pLocaFallaPueblo",this.pueblo);
            parametrosReporte.put("pLocaFallaCalle",this.calle);
            parametrosReporte.put("pLocaFallaPoste",this.poste);
            parametrosReporte.put("pLocaFallaSecuencia",this.secue);
            parametrosReporte.put("SUBREPORT_DIR",servletJasperPath);
            
            parametrosReporte.put("fechaInicio",sf.format(this.fechaInicio));
            parametrosReporte.put("fechaFinal",sf.format(this.fechaFinal));
            
            parametrosReporte.put("pRegion",this.region);
            parametrosReporte.put("pSubRegion",this.subregion);
            parametrosReporte.put("pSubEstacion",this.subestacion);
            parametrosReporte.put("pCircuito", circuitoP);
            if (nivelRed.equals(UtilidadesFaces.NIVEL_RED_CIRCUITO)) {
            	parametrosReporte.put("pTipoNivelRed", 0);
            } else if (nivelRed.equals(UtilidadesFaces.NIVEL_RED_SECCION)) {
            	parametrosReporte.put("pTipoNivelRed", 1);
            }
            

            
            nombreArchivo = "SigeItrRepInterrupcionClientesAfectadosEventos";
            
            if(nivelRed.equals(UtilidadesFaces.NIVEL_RED_SECCION)){
               parametrosReporte.put("pSeccion",seccionP);
            }else
                if(nivelRed.equals(UtilidadesFaces.NIVEL_RED_CIRCUITO)){
                    parametrosReporte.put("pSeccion",null); 
            }
            
            
            nombre = "ClientesAfectadosEventos";
            Date fechaActual = new Date();                              
            String fechaActualSTR = sf.format(fechaActual);
            
            nombreArchivo += ".jasper";
            
            
            if(this.formato == 1){
                nombre += fechaActualSTR + ".pdf";
            }
            else{
                nombre += fechaActualSTR + ".xls";
            }
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
    private void pasarParametrosReporte(){        
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteClientesAfectadosPorEvento));
        ctx.getExternalContext().getSessionMap().put("medidor",this.medidor);
        ctx.getExternalContext().getSessionMap().put("pueblo",this.pueblo);
        ctx.getExternalContext().getSessionMap().put("calle",this.calle);
        ctx.getExternalContext().getSessionMap().put("poste",this.poste);
        ctx.getExternalContext().getSessionMap().put("secue",this.secue);
        ctx.getExternalContext().getSessionMap().put("nivelRedActual",this.nivelRed); 
        SimpleDateFormat sf = new SimpleDateFormat();
        sf.applyPattern("dd/MM/yyyy");           
        ctx.getExternalContext().getSessionMap().put("fechaInicio",sf.format(this.fechaInicio).toString());
        ctx.getExternalContext().getSessionMap().put("fechaFinal",sf.format(this.fechaFinal).toString());
        ctx.getExternalContext().getSessionMap().put("region",this.region);
        ctx.getExternalContext().getSessionMap().put("subregion",this.subregion);
        ctx.getExternalContext().getSessionMap().put("subestacion",this.subestacion);
        System.out.println(sqlFiltro);
        ctx.getExternalContext().getSessionMap().put("sqlFiltro",this.sqlFiltro);
       
        
        if(this.circuito.equals(new Integer(0))){
        	ctx.getExternalContext().getSessionMap().put("circuito",null);
        }else{
        	ctx.getExternalContext().getSessionMap().put("circuito",this.circuito);
        }
        if(this.seccion.equals(new Long(0))){
        	ctx.getExternalContext().getSessionMap().put("seccion",null);
        }else{
        	ctx.getExternalContext().getSessionMap().put("seccion",this.seccion);
        }   
        ctx.getExternalContext().getSessionMap().put("formato",this.formato);               
    }
    
    private void generarSQL(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
        
        this.sqlFiltro =   "SELECT eve01m.aa as anno, eve01m.cod_oficina as oficina, eve01m.numinterrup as numInterrupcion, " +
        		" eve01m.fecini as fechaInicio,eve01m.medidor as Medidor," +
        		" eve01m.codpueblo as locaFallaPueblo, eve01m.codcalle as locaFallaCalle, eve01m.poste as locaFallaPoste, eve01m.secuencia as locaFallaSecuencia," +
        		" eve01m.codpueblo_equipo as equipoPueblo, eve01m.codcalle_equipo as equipoCalle, eve01m.poste_equipo as equipoPoste," +
        		" eve01m.secuencia_equipo as equipoSecuencia,eve01m.comentario,"+
        		//"---Numero Vehiculo" +
        		" (SELECT eve05m.num_vehiculo FROM eve05m  WHERE eve05m.aa = eve01m.aa " +
        		" AND eve05m.cod_oficina = eve01m.cod_oficina AND eve05m.numinterrup = eve01m.numinterrup AND rownum = 1)as NUM_VEHICULO," +
        		//"---Numero circuito" +
        		" (select (to_char(eve01m.reg)||'-'||to_char(eve01m.subregion)||'-'||to_char(eve01c.sub)||'-'||to_char(eve01m.cir))" +
        		" from eve01c" +
        		" where eve01c.cir=eve01m.cir and eve01c.sub=eve01m.sub and eve01m.reg=eve01m.reg " +
        		" and subregion=eve01m.subregion and eve01m.cir=eve01c.cir AND rownum = 1) numCircuito," +
        		//"---DESCCIRCUITO" +
        		" (select eve01c.nom_circuito from eve01c" +
        		" where eve01c.cir=eve01m.cir and eve01c.sub=eve01m.sub and subregion=eve01m.subregion and eve01m.cir=eve01c.cir  AND rownum = 1) descCircuito," +
        		//"---Numero seccion" +
        		" (select ((to_char(eve06c.reg)||'-'||to_char(eve06c.subregion)||'-'||to_char(eve06c.sub)||'-'||to_char(eve06c.cir))||'-'||to_char(eve06c.seccion))" +
        		" from eve06c" +
        		" where eve06c.cir=eve01m.cir and eve06c.sub=eve01m.sub and eve06c.reg=eve01m.reg" +
        		" and eve06c.subregion=eve01m.subregion and eve06c.seccion=eve01m.seccion" +
        		" AND rownum = 1) numSeccion," +
        		//"---Nombreseccion" +
        		" (SELECT eve06c.nom_seccion" +
        		" from eve06c" +
        		" where eve06c.cir=eve01m.cir and eve06c.sub=eve01m.sub and eve01m.reg=eve01m.reg and eve06c.subregion=eve01m.subregion and" +
        		" eve06c.seccion=eve01m.seccion" +
        		" AND rownum = 1)  descSeccion," +
        		" NVL((SELECT eve10c.nom_material   FROM eve10c WHERE eve10c.cod_material  = eve01m.cod_material   AND rownum = 1), null) descMaterial," +
        		" NVL((SELECT eve11c.nom_dano       FROM eve11c WHERE eve11c.cod_dano      = eve01m.cod_dano       AND rownum = 1), null) descDano," +
        		" NVL((SELECT eve12c.nom_proteccion FROM eve12c WHERE eve12c.cod_proteccion= eve01m.cod_proteccion AND rownum = 1), null) descProteccion," +
        		" NVL((SELECT eve09c.nom_causa      FROM eve09c WHERE eve09c.cod_causa     = eve01m.causa1         AND rownum = 1), null) descCausa" +
        		" FROM eve01m" +
        		" WHERE "+
        		" TO_DATE('"+sdf.format(this.fechaInicio)+"','DD/MM/YYYY') <=  trunc(eve01m.FECINI)  AND trunc(eve01m.FECFIN) <= TO_DATE('"+sdf.format(this.fechaFinal)+"','DD/MM/YYYY')  " + 
        		" AND eve01m.REG = NVL("+this.region+",eve01m.REG) AND eve01m.SUBREGION = NVL("+this.subregion+",eve01m.SUBREGION)" +
        		" AND eve01m.SUB = NVL("+this.subestacion+",eve01m.SUB)AND eve01m.CIR = NVL("+this.circuito+",eve01m.CIR) "; 
		
                System.out.println("SQL ----<---<" + sqlFiltro);
           	 
        if(this.nivelRed.equals(UtilidadesFaces.NIVEL_RED_SECCION)){
        	System.out.println("BUSQUEDA POR SECCION-------------------------------------");
        	sqlFiltro += " AND eve01m.SECCION = NVL("+this.seccion+",eve01m.SECCION) ";       
        }
        if(medidor!=null){
        	sqlFiltro += " AND eve01m.MEDIDOR = NVL("+this.medidor+",eve01m.MEDIDOR) ";           
        }        
        if(pueblo!=null){
        	sqlFiltro += " AND eve01m.CODPUEBLO = NVL("+this.pueblo+",eve01m.CODPUEBLO)  "; 
        	
        	if(calle!=null){
            	sqlFiltro += " AND eve01m.CODCALLE = NVL("+this.calle+",eve01m.CODCALLE)  ";            
            } 
            if(poste!=null){
            	sqlFiltro += " AND eve01m.POSTE = NVL("+this.poste+",eve01m.POSTE)  ";            
            } 
            if(secue!=null){
            	sqlFiltro += "  AND eve01m.SECUENCIA = NVL("+this.secue+",eve01m.SECUENCIA)  ";            
            } 
        }        
             
	         System.out.println(":::::::FILTRO:::::::"+sqlFiltro);
    }

 /**
     * Cancela el proceso de la generación de reportes
     * @return success
     */    
    public String cancelar(){
        this.reiniciarCampos();
        return "success";
    } 
    /*****************************************************************************************************
     ***************************************************************************************************** 
     *****************************************************************************************************/
    
    
	public Integer getCalle() {
		return calle;
	}

	public void setCalle(Integer calle) {
		this.calle = calle;
	}

	public Integer getCircuito() {
    //    System.out.println("Entro a getCircuito "+this.circuito);
		return circuito;
	}

	public void setCircuito(Integer circuito) {
   //     System.out.println("entro a setCircuito "+circuito);
		this.circuito = circuito;
	}

	public void setCircuitoBO(CircuitoBO circuitoBO) {
		this.circuitoBO = circuitoBO;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Integer getFormato() {
		return formato;
	}

	public void setFormato(Integer formato) {
		this.formato = formato;
	}

	public Integer getMedidor() {
		return medidor;
	}

	public void setMedidor(Integer medidor) {
		this.medidor = medidor;
	}

	public String getNivelRed() {
		return nivelRed;
	}

	public void setNivelRed(String nivelRed) {
  //      System.out.println("entro a setNivelRed");
		this.nivelRed = nivelRed;
	}

	public Integer getPoste() {
		return poste;
	}

	public void setPoste(Integer poste) {
		this.poste = poste;
	}

	public Integer getPueblo() {
		return pueblo;
	}

	public void setPueblo(Integer pueblo) {
		this.pueblo = pueblo;
	}

	public Integer getRegion() {
 //       System.out.println("Entro a getRegion "+this.region);
		return region;
	}

	public void setRegion(Integer region) {
//        System.out.println("entro a setRegion "+region);
		this.region = region;
	}

	public void setRegionBO(RegionBO regionBO) {
		this.regionBO = regionBO;
	}

	public Long getSeccion() {
//        System.out.println("Entro a getSeccion "+this.seccion);
		return seccion;
	}

	public void setSeccion(Long seccion) {
//        System.out.println("entro a setSeccion "+seccion);
		this.seccion = seccion;
	}

	public void setSeccionBO(SeccionBO seccionBO) {
		this.seccionBO = seccionBO;
	}

	public Integer getSecue() {
		return secue;
	}

	public void setSecue(Integer secue) {
		this.secue = secue;
	}

	public Integer getSubestacion() {
//        System.out.println("Entro a getSubestacion "+this.subestacion);
        return subestacion;
	}

	public void setSubestacion(Integer subestacion) {
 //       System.out.println("entro a setSubestacion "+subestacion);
		this.subestacion = subestacion;
	}

	public void setSubEstacionBO(SubEstacionBO subEstacionBO) {
		this.subEstacionBO = subEstacionBO;
	}

	public Integer getSubregion() {
 //       System.out.println("Entro a getSubregion "+this.subregion);
		return subregion;
	}

	public void setSubregion(Integer subregion) {
       // System.out.println("entro a setSubregion "+subregion);
		this.subregion = subregion;
	}


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

}
