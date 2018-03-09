package cr.go.ice.interrupciones.web.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.sql.DataSource;

import com.vvs.jsf.AbstractFacesController;
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

public class ReporteInterrupcionMaterialPorPosteController extends AbstractFacesController{
	private Date fechaInicio;
    private Date fechaFinal;
    private String nivelRed;
    private Integer region;
    private Integer subregion;
    private Integer subestacion;
    private Integer circuito;
    private Long seccion;
    private Integer formato;
    private String movimiento;
    private String calidad;
    private Integer calidadR;
    private Integer movimientoR;
    
    private RegionBO regionBO;
    private SubRegionBO subRegionBO;
    private SubEstacionBO subEstacionBO;
    private CircuitoBO circuitoBO;
    private SeccionBO seccionBO;
    
    private List regionesList;
    private List subRegionesList;
    private List subEstacionesList;
    private List circuitoList;
    private List seccionList;
    private  static final String JasperPath = "/jasperReports/";
//    private Region [] listaRegionItemsx;
//    private SubRegion [] listaSubregionItemsx;
//    private SubEstacion [] listaSubestacionItemsx;
//    private Circuito [] listaCircuitoItemsx;
//    private Seccion [] listaSeccionItemsx;
//     
   // private String errorx;
    
    
	public ReporteInterrupcionMaterialPorPosteController(){
		this.reiniciarCampos();
	}
	
	private void reiniciarCampos(){
        this.fechaInicio = new Date();
        this.fechaFinal = new Date();  
        
        this.region = null;
        this.subregion = null;
        this.subestacion = null;
        this.circuito = null;
        this.seccion = null;
        this.movimiento = null;
        this.calidad = null;
        this.calidadR = null;
        this.movimientoR = null;
        
        this.formato = UtilidadesFaces.FORMATO_PDF;
        this.nivelRed = null; 
        
        this.regionesList=null;
        this.subRegionesList=null;
        this.subEstacionesList=null;
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
        	System.out.println("entre limpiar");
            reiniciarCampos();
        }
        
    }
    
    public String getInit() {
    	FacesContext context = FacesContext.getCurrentInstance();
        Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
        if (limpiar != null) {
        	System.out.println("entre limpiar");
            reiniciarCampos();
        }
        return "success";
    }
    
    /**
     * Lista de tipos de formatos para el reporte
     * @return lista de formatos
     */
    public List getListaFormatos(){
        List formatos = new ArrayList();
        formatos.add(new SelectItem(UtilidadesFaces.FORMATO_PDF,"Pdf"));
        formatos.add(new SelectItem(UtilidadesFaces.FORMATO_XLS,"Excel"));
        return formatos;
    }
    
    /**
     * Retorna una lista de select item de los diferentes niveles de red
     * @return Lista de niveles de red
     */    
    public List getListaTipoMovimiento(){
        List movimiento = new ArrayList();
        movimiento.add(new SelectItem("0","Todos"));
        movimiento.add(new SelectItem("1","Instalado"));
        movimiento.add(new SelectItem("2","Retirado"));
        return movimiento;
    }
    
    public void listenerMovimiento(){
        //this.movimiento = (String) v.getNewValue();
//        return "listener";  
    }
    
    /**
     * Retorna una lista de select item de los diferentes niveles de red
     * @return Lista de niveles de red
     */    
    public List getListaTipoCalidad(){
        List calidad = new ArrayList();      
        calidad.add(new SelectItem("0","Todos"));
        calidad.add(new SelectItem("1","Concreto"));
        calidad.add(new SelectItem("2","Madera"));
        calidad.add(new SelectItem("3","Riel"));
        calidad.add(new SelectItem("4","Autosoportado"));
        calidad.add(new SelectItem("5","Metal"));
        return calidad;
    }
    
    public void listenerCalidad(){
       // this.calidad = (String) v.getNewValue();
        //return "listener";  
    }
    
    /**
     * Retorna una lista de select item de los diferentes niveles de red.
     * @return Lista de niveles de red.
     */
    public List getListaNivelesRed() {
    	return UtilidadesFaces.getListaNivelesRed();
    }
    
    /**
     * Asigna el codigo de nivel de red de acuerdo a el cual se habilitan o deshabilitan los combos de red
     * @param v
     * @return success
     */ 
    public void listenerNivelRed() {
        this.region = new Integer(-1);
        this.subregion = new Integer(-1);
        this.subestacion = new Integer(-1);
        this.circuito = new Integer(-1);
        this.seccion = new Long(-1);
    }
    
    /**
     * Comment for listenerRegion
     * @param v
     * @return "listener" a Region
     */
    public void listenerRegion() {
    	initSubRegiones(region);
        initSubEstaciones(null, null);
        initCircuitos(null);
        initSecciones(null, null);
    }
    
    /**
     * Comment for listenerSubregion
     * @param v
     * @return "listener" a SubRegion
     */
    public void listenerSubregion() {
        initSubEstaciones(this.region, subregion);
        initCircuitos(null);
        initSecciones(null, null);
    }
    
    /**
     * Comment for listenerSubestacion
     * @param v
     * @return "listener" a SubEstacion
     */
    public void listenerSubestacion() {
        initCircuitos(subestacion);
        initSecciones(null, null);
    }
    
    /**
     * Comment for listenerCircuito
     * @param v
     * @return "listener" a Circuito
     */
    public void listenerCircuito() {
        initSecciones(circuito, this.subestacion);
    }
    
    
    /**
     * Metodo accesor de circuitos.
     * @return Retorna el circuitos.
     */
    public List getCircuitos() {
        initCircuitos(this.subestacion);
        return this.circuitoList;
    }
    private void initCircuitos(Integer subestacion) {
        this.circuitoList = new ArrayList();
        this.circuitoList.add(new SelectItem(new Integer(-1), "Todos"));
        if (subestacion != null) {
        	List circuitos = this.circuitoBO.getCircuitos(subestacion);
            if (circuitos != null && !circuitos.isEmpty()) {
                for (int i = 0; i < circuitos.size(); i++) {
                    Circuito circuito = (Circuito) circuitos.get(i);
                    SelectItem item = new SelectItem();
                    item.setValue(circuito.getCircuitoID().getCircuito());
                    item.setLabel(circuito.getCircuitoID().getCircuito() + " - " + circuito.getNombreCircuito());
                    this.circuitoList.add(item);
                }
            }
        }
    }
    /**
     * Metodo accesor de regiones.
     * @return Retorna el regiones.
     */
    public List getRegiones() {
    	if (this.regionesList ==null) {
    		initRegiones();
    	}
        return this.regionesList;
    }
    private void initRegiones() {
    	System.out.println("Region " + this.region);
        List regiones = null;
        regiones = this.regionBO.getRegiones();
        this.regionesList = new ArrayList();
        this.regionesList.add(new SelectItem(new Integer(-1), "Todas"));
        if (regiones != null && !regiones.isEmpty()) {
            for (int i = 0; i < regiones.size(); i++) {
                Region region = (Region) regiones.get(i);
                SelectItem item = new SelectItem();
                item.setValue(region.getRegion());
                item.setLabel(region.getRegion() + " - " + region.getNombreRegion());
                this.regionesList.add(item);
            }
        }
    }
    /**
     * Metodo accesor de secciones.
     * @return Retorna el secciones.
     */
    public List getSecciones() {
    	initSecciones(this.circuito, this.subestacion);
        return this.seccionList;
    }
    private void initSecciones(Integer circuito, Integer subestacion) {
    	System.out.println("seccion " + this.seccion);
    	this.seccionList = new ArrayList();
        this.seccionList.add(new SelectItem(new Long(-1), "Todas"));
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
    }
    /**
     * Metodo accesor de subEstaciones.
     * @return Retorna el subEstaciones.
     */
    public List getSubEstaciones() {
    	initSubEstaciones(this.region, this.subregion);
        return this.subEstacionesList;
    }
    private void initSubEstaciones(Integer region, Integer subregion) {
    	System.out.println("Sub " + this.subestacion);
        this.subEstacionesList = new ArrayList();
        this.subEstacionesList.add(new SelectItem(new Integer(-1), "Todas"));
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
    }
    /**
     * Metodo accesor de subRegiones.
     * @return Retorna el subRegiones.
     */
    public List getSubRegiones() {
    	initSubRegiones(this.region);
        return this.subRegionesList;
    }
    private void initSubRegiones(Integer region) {
        this.subRegionesList = new ArrayList();
        this.subRegionesList.add(new SelectItem(new Integer(-1), "Todas"));
        if (region !=null) {
        	List subRegiones = this.subRegionBO.getSubRegiones(region);
            if (subRegiones!= null && !subRegiones.isEmpty()) {
                for (int i = 0; i < subRegiones.size(); i++) {
                    SubRegion subRegion = (SubRegion) subRegiones.get(i);
                    SelectItem item = new SelectItem();
                    item.setValue(subRegion.getSubRegionID().getSubRegion());
                    item.setLabel(subRegion.getSubRegionID().getSubRegion() + " - " + subRegion.getNombreSubRegion());
                    this.subRegionesList.add(item);
                }
            }
        }
    }
    
    /**
     * 
     * @return String
     */
    public String aceptar(){
    	System.out.println("seccionUno " + this.seccion);
    	String correcto = "error";
    	if(validarParametros()){
    		if(validarOpcionesParametros()){
    			correcto = generarReporte();
//    			this.reiniciarCampos();
    		}
    	}
        System.out.println("Aceptar sale con: "+correcto);
//    	this.reiniciarCampos();
    	return correcto;
    }
    
    private boolean validarParametros(){
        boolean correcto = true;
       
        if(this.fechaInicio == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar una fecha de inicio."));
            correcto = false;
        }
        if(this.fechaFinal == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar una fecha de fin."));
            correcto=false;
        }        
        return correcto;
    }
    
    private boolean validarOpcionesParametros(){
        boolean correcto = true;
      
        if(this.fechaInicio.compareTo(new Date()) > 0){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de inicio no puede ser mayor a la fecha actual."));
            correcto=false;
        }  
        if(this.fechaFinal.compareTo(new Date()) > 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de fin no puede ser mayor a la fecha actual."));
            correcto=false;
        }           
        
        if(this.fechaFinal.compareTo(this.fechaInicio) < 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de fin debe ser mayor o igual a la fecha de inicio."));
            correcto=false;
        }
        if(this.nivelRed.equals(UtilidadesFaces.NIVEL_RED_SUB_REGION)){
        	if(this.region.intValue() <= 0){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una región."));
                correcto=false;
            }  
            
        }else
        if(this.nivelRed.equals(UtilidadesFaces.NIVEL_RED_SUB_ESTACION)){
        	if(this.region.intValue() <= 0){
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una región."));
                correcto=false;
            }
            if(this.subregion.intValue() <= 0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una subregión."));
                correcto=false;
            }    
            
        }else
        if(this.nivelRed.equals(UtilidadesFaces.NIVEL_RED_CIRCUITO)){
            if(this.region.intValue() <= 0){
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una región."));
                correcto=false;
            }
            if(this.subregion.intValue() <= 0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una subregión."));
                correcto=false;
            }              
            if(this.subestacion.intValue() <= 0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una subestación."));
                correcto=false;
            } 
             
        }else  
        if(this.nivelRed.equals(UtilidadesFaces.NIVEL_RED_SECCION)){
            if(this.region.intValue() <= 0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar la región."));
                correcto=false;
            }
            if(this.subregion.intValue() <= 0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar la subregión."));
                correcto=false;
            }              
            if(this.subestacion.intValue() <= 0){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar la subestación."));
                correcto=false;
            }            
            if(this.circuito.intValue() <= 0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar el circuito."));
                correcto=false;
            }
        }       
      
        return correcto;
    }
    
    @SuppressWarnings("unchecked")
    private String generarReporte(){  
       // pasarParametrosReporte();  
        String nombre = "";
        String nombreArchivo = "";
        HashMap parametrosReporte = new HashMap();
        try{                    
//            FacesContext context = FacesContext.getCurrentInstance();                       
//            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletReporte");
//            dispatcher.forward(request, response);                                  
//            response.getOutputStream().flush();
//
//            if(!FacesContext.getCurrentInstance().getResponseComplete() ) {
//                FacesContext.getCurrentInstance().responseComplete();
//            } 
            SimpleDateFormat sf = new SimpleDateFormat();
            sf.applyPattern("dd/MM/yyyy");    
            
            if(this.movimiento.equals("0")){
                this.movimientoR = null;
            }else
            if(this.movimiento.equals("1")){
                this.movimientoR = new Integer(1);
            }else
            if(this.movimiento.equals("2")){
                this.movimientoR = new Integer(0);
            }
            
            
            //parametrosReporte.put("tipoMovimiento",this.movimientoR);
            if((this.calidad.equals(""))||this.calidad.equals("0")){
                this.calidadR = null;
            }else{
                this.calidadR = new Integer(this.calidad);
            } 
            parametrosReporte.put("pFechaInicio",this.fechaInicio);
            parametrosReporte.put("pFechaFin",this.fechaFinal);
            
            
           
            if(region.equals(new Integer(-1))){
                parametrosReporte.put("pRegion",null);
            }else{
                parametrosReporte.put("pRegion",region);
            }
            parametrosReporte.put("pMovimiento",this.movimientoR);
            
            parametrosReporte.put("pCalidad",this.calidadR);
            
            String ServletJasperPath = "/jasperReports/";
            parametrosReporte.put("SUBREPORT_DIR",ServletJasperPath);
            
            if(this.nivelRed.equals(UtilidadesFaces.NIVEL_RED_REGION)){
                parametrosReporte.put("pSubregion",null);
                parametrosReporte.put("pSubestacion",null);
                parametrosReporte.put("pCircuito",null);
                parametrosReporte.put("pSeccion",null);
                parametrosReporte.put("pTipoNivelRed",new Integer(0));
                nombreArchivo = "SigeItrRepInterrupcionInformePostes";
            }
            if(nivelRed.equals(UtilidadesFaces.NIVEL_RED_SUB_REGION)){
                if(this.subregion.equals(new Integer(-1))){
                    parametrosReporte.put("pSubregion",null);
                }else{
                    parametrosReporte.put("pSubregion",this.subregion);
                }
                parametrosReporte.put("pSubestacion",null);
                parametrosReporte.put("pCircuito",null);
                parametrosReporte.put("pSeccion",null);
                parametrosReporte.put("pTipoNivelRed",new Integer(1));
                nombreArchivo = "SigeItrRepInterrupcionInformePostes";
            }else{
                if(nivelRed.equals(UtilidadesFaces.NIVEL_RED_SUB_ESTACION)){
                    parametrosReporte.put("pSubregion",this.subregion);
                    if(this.subestacion.equals(new Integer(-1))){
                        parametrosReporte.put("pSubestacion",null);
                    }else{
                        parametrosReporte.put("pSubestacion",this.subestacion);
                    }
                    parametrosReporte.put("pCircuito",null);
                    parametrosReporte.put("pSeccion",null);
                    parametrosReporte.put("pTipoNivelRed",new Integer(2));
                    nombreArchivo = "SigeItrRepInterrupcionInformePostes";
                }else{
                    if(nivelRed.equals(UtilidadesFaces.NIVEL_RED_CIRCUITO)){
                        parametrosReporte.put("pSubregion",this.subregion);
                        parametrosReporte.put("pSubestacion",this.subestacion);
                        if(circuito.equals(new Integer(-1))){
                            parametrosReporte.put("pCircuito",null);
                        }else{
                            parametrosReporte.put("pCircuito",circuito);
                        }
                        parametrosReporte.put("pSeccion",null);
                        parametrosReporte.put("pTipoNivelRed",new Integer(3));
                        nombreArchivo = "SigeItrRepInterrupcionInformePostes";
                    }else{
                        if(nivelRed.equals(UtilidadesFaces.NIVEL_RED_SECCION)){
                            parametrosReporte.put("pSubregion",this.subregion);
                            parametrosReporte.put("pSubestacion",this.subestacion);
                            parametrosReporte.put("pCircuito",circuito);
                            parametrosReporte.put("pTipoNivelRed",new Integer(4));
                            if(seccion.equals(new Long(-1))){
                                parametrosReporte.put("pSeccion",null);
                            }else{
                                parametrosReporte.put("pSeccion",new Integer(Integer.parseInt(seccion.toString())));
                            }
                            nombreArchivo = "SigeItrRepInterrupcionInformePostes"; 
                        }
                    }
                }
            }
            nombreArchivo = "SigeItrRepInterrupcionInformePostes"; 
            
            nombre = "InformePostes";
            
            Date fechaActual = new Date();
            SimpleDateFormat spf = new SimpleDateFormat();
            spf.applyPattern("dd-MM-yyyy");                                
            String fechaActualSTR = spf.format(fechaActual);
            
            nombreArchivo += ".jasper";
            
            if(this.formato == 1){
                nombre += fechaActualSTR + ".pdf";
            }
            else{
                nombre += fechaActualSTR + ".xls";
            }
            if (this.runReport(JasperPath + nombreArchivo, nombre,parametrosReporte,this.getConnection(),this.formato,UtilidadesFaces.getCurrentUserId())){
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
        ctx.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteMaterialPostes));// cambiar x nombre reporte
        ctx.getExternalContext().getSessionMap().put("nivelRedActual",this.nivelRed);
        if(this.movimiento.equals("0")){
        	this.movimientoR = null;
        }else
    	if(this.movimiento.equals("1")){
    		this.movimientoR = new Integer(1);
    	}else
		if(this.movimiento.equals("2")){
			this.movimientoR = new Integer(0);
		}
    	
        
        ctx.getExternalContext().getSessionMap().put("tipoMovimiento",this.movimientoR);
        if((this.calidad.equals(""))||this.calidad.equals("0")){
        	this.calidadR = null;
        }else{
    		this.calidadR = new Integer(this.calidad);
    	}
		
        ctx.getExternalContext().getSessionMap().put("tipoCalidad",this.calidadR);        
        ctx.getExternalContext().getSessionMap().put("fechaInicio",this.fechaInicio);
        ctx.getExternalContext().getSessionMap().put("fechaFinal",this.fechaFinal);
        ctx.getExternalContext().getSessionMap().put("region",this.region);
        ctx.getExternalContext().getSessionMap().put("subregion",this.subregion);
        ctx.getExternalContext().getSessionMap().put("subestacion",this.subestacion);
        ctx.getExternalContext().getSessionMap().put("circuito",this.circuito);
        ctx.getExternalContext().getSessionMap().put("seccion",this.seccion);
        ctx.getExternalContext().getSessionMap().put("formato",this.formato);    
                
    }
    
    public String cancelar(){
    	this.reiniciarCampos();
    	return "success";
    }
	
	/*******************************************************************************************************************************************************************************
     * ****************************************************************************************
     ******************************************************************************************************************************************************************************/

	public Integer getCircuito() {
		return circuito;
	}

	public void setCircuito(Integer circuito) {
		this.circuito = circuito;
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

	public String getNivelRed() {
		return nivelRed;
	}

	public void setNivelRed(String nivelRed) {
		this.nivelRed = nivelRed;
	}

	public Integer getRegion() {
		return this.region;
	}

	public void setRegion(Integer region) {
		System.out.println("se cambio region" + region.intValue());
		this.region = region;
	}

	public Long getSeccion() {
		return seccion;
	}

	public void setSeccion(Long seccion) {
		this.seccion = seccion;
	}

	public Integer getSubestacion() {
		return subestacion;
	}

	public void setSubestacion(Integer subestacion) {
		this.subestacion = subestacion;
	}

	public Integer getSubregion() {
		return subregion;
	}

	public void setSubregion(Integer subregion) {
		this.subregion = subregion;
	}

	

	public void setCircuitoBO(CircuitoBO circuitoBO) {
		this.circuitoBO = circuitoBO;
	}

	public void setRegionBO(RegionBO regionBO) {
		this.regionBO = regionBO;
	}

	

	public void setSeccionBO(SeccionBO seccionBO) {
		this.seccionBO = seccionBO;
	}

	

	public void setSubEstacionBO(SubEstacionBO subEstacionBO) {
		this.subEstacionBO = subEstacionBO;
	}

	

	public void setSubRegionBO(SubRegionBO subRegionBO) {
		this.subRegionBO = subRegionBO;
	}

	public String getCalidad() {
		return calidad;
	}

	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}

	public String getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}

//	public String getError() {
//		return error;
//	}
//
//	public void setError(String error) {
//		this.error = error;
//	}


//	public Circuito[] getListaCircuitoItems() {
//		return listaCircuitoItems;
//	}
//
//	public void setListaCircuitoItems(Circuito[] listaCircuitoItems) {
//		this.listaCircuitoItems = listaCircuitoItems;
//	}
//
//	public Region[] getListaRegionItems() {
//		return listaRegionItems;
//	}
//
//	public void setListaRegionItems(Region[] listaRegionItems) {
//		this.listaRegionItems = listaRegionItems;
//	}
//
//	public Seccion[] getListaSeccionItems() {
//		return listaSeccionItems;
//	}
//
//	public void setListaSeccionItems(Seccion[] listaSeccionItems) {
//		this.listaSeccionItems = listaSeccionItems;
//	}
//
//	public SubEstacion[] getListaSubestacionItems() {
//		return listaSubestacionItems;
//	}
//
//	public void setListaSubestacionItems(SubEstacion[] listaSubestacionItems) {
//		this.listaSubestacionItems = listaSubestacionItems;
//	}
//
//	public SubRegion[] getListaSubregionItems() {
//		return listaSubregionItems;
//	}
//
//	public void setListaSubregionItems(SubRegion[] listaSubregionItems) {
//		this.listaSubregionItems = listaSubregionItems;
//	}

	public Integer getCalidadR() {
		return calidadR;
	}

	public void setCalidadR(Integer calidadR) {
		this.calidadR = calidadR;
	}

	public Integer getMovimientoR() {
		return movimientoR;
	}

	public void setMovimientoR(Integer movimientoR) {
		this.movimientoR = movimientoR;
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
