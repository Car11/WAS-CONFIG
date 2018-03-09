package cr.go.ice.interrupciones.web.controller;
import java.io.IOException;
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

import com.vvs.jsf.AbstractFacesController;
import com.vvs.utilidades.Utilidades;

import cr.go.ice.interrupciones.BO.CircuitoBO;
import cr.go.ice.interrupciones.BO.RegionBO;
import cr.go.ice.interrupciones.BO.SubEstacionBO;
import cr.go.ice.interrupciones.BO.SubRegionBO;
import cr.go.ice.interrupciones.domain.*;
import cr.go.ice.interrupciones.servlets.ServletReporte;
import cr.go.ice.interrupciones.utils.UtilidadesFaces;
import cr.go.ice.interrupciones.web.Recurso;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ReporteInterrupcionIndiceMAController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ReporteInterrupcionIndiceMAController.java</code>.</p>
 * <p>Fecha creación: 09/09/2008</p>
 * <p>Ultima actualización: 09/09/2008</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ReporteInterrupcionIndiceMAController extends AbstractFacesController{

    private Integer ano;
    private Integer tipoInterrupcion;    
    private String nivelRed;
    
    private Integer region;
    private Integer subregion;
    private Integer subestacion;
    private Integer circuito;
    private Long seccion;      
    
    private RegionBO regionBO;
    private SubRegionBO subRegionBO;
    private SubEstacionBO subEstacionBO;
    private CircuitoBO circuitoBO;
    private Integer formato;
    private  static final String JasperPath = "/jasperReports/";
    
    /**
     * Constructor por defecto
     */    
    public ReporteInterrupcionIndiceMAController(){
        reiniciarCampos();        
    }
    
    /**
     * Metodo que reincia los atributos de la clase
     */
    private void reiniciarCampos(){
        Calendar calendar = Calendar.getInstance();
        this.ano = new Integer(calendar.get(Calendar.YEAR));
        this.tipoInterrupcion = Interrupcion.INTERRUPCION_DISTRIBUCION;
        
        this.region = new Integer(0);
        this.subregion = new Integer(0);
        this.subestacion = new Integer(0);
        this.circuito = new Integer(0);
        this.seccion = new Long(0);
        
        this.nivelRed = "";
        this.formato = UtilidadesFaces.FORMATO_PDF;
    }
    
    /**
     * Precarga la oficina del usuario de la aplicación, si el mismo es un usuario CLOR
     * @param context
     */
    public void load(FacesContext context){
        Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
        if(limpiar != null)
            reiniciarCampos();
        
    }    
    
    public String getInit(FacesContext context){
    	 Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
         if(limpiar != null)
             reiniciarCampos();
    	return "success";
    }
           
    
    /**
     * Retorna una lista de select item de los diferentes niveles de red
     * @return Lista de niveles de red
     */    
    public List getListaNivelesRed(){
        List niveles = new ArrayList();        
        niveles.add(new SelectItem("region","Región"));
        niveles.add(new SelectItem("subregion","Subregión"));       
        niveles.add(new SelectItem("circuito","Circuito"));      
        return niveles;   
    }      
    
    /**
     * Asigna el codigo de nivel de red de acuerdo a el cual se habilitan o deshabilitan los combos de red
     * @param v
     * @return success
     */ 
    public String listenerNivelRed(){
    	this.region = new Integer(0);
        this.subregion = new Integer(0);
        this.subestacion = new Integer(0);
        this.circuito = new Integer(0);
        return "success";
    }            
    
    /**
     * Retorna una lista de select item de los tipos de interrupción
     * @return Lista de tipos de interrupción
     */    
    public List getListaTipoInterrupcion(){
        List tramo = new ArrayList();                
        tramo.add(new SelectItem(Interrupcion.INTERRUPCION_SUMINISTRO,"1 - Interrupción de transporte"));
        tramo.add(new SelectItem(Interrupcion.INTERRUPCION_CATASTROFE,"4 - Interrupción por catástrofe"));
        tramo.add(new SelectItem(Interrupcion.INTERRUPCION_DISTRIBUCION,"5 - Interrupción por distribución"));        
        return tramo;       
    }     
    
     /**
     * Metodo accesor de circuitos.
     * @return Retorna el circuitos.
     */
    public List getCircuitos() {    
        List circuitos = this.circuitoBO.getCircuitos(this.subestacion);
        List items = new ArrayList();
        items.add(new SelectItem(new Integer(0), "Seleccione una circuito"));
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
     * Metodo accesor de regiones.
     * @return Retorna el regiones.
     */
    public List getRegiones() {   
        List regiones = null;
        regiones = this.regionBO.getRegiones();
        List items = new ArrayList();
        items.add(new SelectItem(new Integer(0), "Seleccione una región"));
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
     * Metodo accesor de subEstaciones.
     * @return Retorna el subEstaciones.
     */
    public List getSubEstaciones() {
        List subEstaciones = this.subEstacionBO.getSubEstaciones(this.region, this.subregion);
        List items = new ArrayList();
        items.add(new SelectItem(new Integer(0), "Seleccione una subestación"));
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
     * Metodo accesor de subRegiones.
     * @return Retorna el subRegiones.
     */
    public List getSubRegiones() {              
        List subRegiones = this.subRegionBO.getSubRegiones(this.region);
        List items = new ArrayList();
        items.add(new SelectItem(new Integer(0), "Seleccione una subregión"));
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
    
    
    private boolean validarParametros(){
        boolean correcto = true;
        
        if(this.ano == null || this.ano.intValue() <= 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar una fecha de inicio."));
            correcto = false;
        }  
        return correcto;
    }
    
    private boolean validarOpcionesParametros(){
        boolean correcto = true;
        Calendar calendar = Calendar.getInstance();
        
        int anoActual = calendar.get(Calendar.YEAR);       
        if(this.ano.intValue() > anoActual){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El año no puede ser mayor al actual."));
            correcto=false;
        }
        if(this.nivelRed.equals("region")){
            if(this.region.intValue() <= 0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar la región."));
                correcto=false;
            } 
        }
        if(this.nivelRed.equals("subregion")){
            if(this.region.intValue() <= 0){
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar la región."));
                correcto=false;
            }            
            if(this.subregion.intValue() <= 0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar la subregión."));
                correcto=false;
            }
        }
        if(this.nivelRed.equals("circuito")){
            if(this.region.intValue() <= 0){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar la región."));
                correcto=false;
            }
            if(this.subregion.intValue() <= 0){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar la subregión."));
                correcto=false;
            }              
            if(this.subestacion.intValue() <= 0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccinoar la subestación."));
                correcto=false;
            }
            if(this.circuito.intValue() <= 0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar el circuito."));
                correcto=false;
            }            
        }          
        
        return correcto;
    }
    
    /**
     * Realiza las validaciones de los parámetros de entrada dados por el usuario y genera el reporte de indices
     * mensuales y acumulados
     * @return error o success de acuerdo a la correctitud de los parametros de entrada o no, de haberse encontrado
     * datos en la base de datos y de la correcta generación del reporte
     */ 
    public String aceptar(){
        if(validarParametros())
            if(validarOpcionesParametros())                
                return generarReporte();
            else
                return "error";
        else
            return "error";
    }     
        
    private void pasarParametrosReporte(){     
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteIndiceMesAcum));
        ctx.getExternalContext().getSessionMap().put("ano",this.ano);
        
        
        if(this.tipoInterrupcion.equals(Interrupcion.INTERRUPCION_SUMINISTRO)){
        	ctx.getExternalContext().getSessionMap().put("codigoVoltaje",new Integer(10));        	
        }else if(this.tipoInterrupcion.equals(Interrupcion.INTERRUPCION_CATASTROFE)){
        	ctx.getExternalContext().getSessionMap().put("codigoVoltaje",new Integer(66));
        }else if(this.tipoInterrupcion.equals(Interrupcion.INTERRUPCION_DISTRIBUCION)){
        	ctx.getExternalContext().getSessionMap().put("codigoVoltaje",new Integer(55));
        }
        
        if(nivelRed.equals("region")){
            ctx.getExternalContext().getSessionMap().put("nombreNivelRed",this.regionBO.buscar(this.region).getNombreRegion());
        }
        if(nivelRed.equals("subregion")){
            SubRegionID subID = new SubRegionID();
            Region reg = new Region();
            reg.setRegion(this.region);
            subID.setRegion(reg);
            subID.setSubRegion(this.subregion);            
            ctx.getExternalContext().getSessionMap().put("nombreNivelRed",this.subRegionBO.buscar(subID).getNombreSubRegion());
        }   
        if(nivelRed.equals("circuito")){            
            ctx.getExternalContext().getSessionMap().put("nombreNivelRed",this.circuitoBO.buscar(this.subestacion, this.circuito).getNombreCircuito());
        } 
        
        ctx.getExternalContext().getSessionMap().put("tipoInterrupcion",this.tipoInterrupcion);
        ctx.getExternalContext().getSessionMap().put("nivelRedActual",this.nivelRed);
        ctx.getExternalContext().getSessionMap().put("region",this.region);
        ctx.getExternalContext().getSessionMap().put("subregion",this.subregion);
        ctx.getExternalContext().getSessionMap().put("subestacion",this.subestacion);
        ctx.getExternalContext().getSessionMap().put("circuito",this.circuito);    
        String tipoInterrupcionSTR = getTipoInterrupcion(this.tipoInterrupcion);
        ctx.getExternalContext().getSessionMap().put("tipoInterrupcionSTR",tipoInterrupcionSTR);                            
        
    }
    
    private String getTipoInterrupcion(Integer tipoInterrupcion){
        if(Interrupcion.INTERRUPCION_SUMINISTRO.equals(tipoInterrupcion))
            return "TRANSPORTE";
        else if(Interrupcion.INTERRUPCION_CATASTROFE.equals(tipoInterrupcion))
            return "CATASTROFE";
        else if(Interrupcion.INTERRUPCION_DISTRIBUCION.equals(tipoInterrupcion))
            return "DISTRIBUCION";
        else
            return "";   
    }
     
    /**
     * Metodo que obtiene los beans y invoca la generacion del reporte
     * @return success o error
     */
    @SuppressWarnings("unchecked")
    private String generarReporte(){  
        //pasarParametrosReporte();   
        String nombreArchivo = "";
        String nombre = "";
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
            parametrosReporte.put("ano",this.ano);
            
            
            if(this.tipoInterrupcion.equals(Interrupcion.INTERRUPCION_SUMINISTRO)){
                parametrosReporte.put("codigoVoltaje",new Integer(10));
            }else if(this.tipoInterrupcion.equals(Interrupcion.INTERRUPCION_CATASTROFE)){
                parametrosReporte.put("codigoVoltaje",new Integer(66));
            }else if(this.tipoInterrupcion.equals(Interrupcion.INTERRUPCION_DISTRIBUCION)){
                parametrosReporte.put("codigoVoltaje",new Integer(55));
            }
            
            if(nivelRed.equals("region")){
                parametrosReporte.put("nombreNivelRed",this.regionBO.buscar(this.region).getNombreRegion());
                nombreArchivo = "SigeItrRepResumenIndiceMensualAcumuladoRegion";
                parametrosReporte.put("nombreNivelRed","region");
            }
            if(nivelRed.equals("subregion")){
                SubRegionID subID = new SubRegionID();
                Region reg = new Region();
                reg.setRegion(this.region);
                subID.setRegion(reg);
                subID.setSubRegion(this.subregion);
                parametrosReporte.put("nombreNivelRed",this.subRegionBO.buscar(subID).getNombreSubRegion());
                nombreArchivo = "SigeItrRepResumenIndiceMensualAcumuladoSubregion";
                parametrosReporte.put("nombreNivelRed","subregion");
            }   
            if(nivelRed.equals("circuito")){            
                parametrosReporte.put("nombreNivelRed",this.circuitoBO.buscar(this.subestacion, this.circuito).getNombreCircuito());
                nombreArchivo = "SigeItrRepResumenIndiceMensualAcumuladoCircuito";
                parametrosReporte.put("nombreNivelRed","circuito");
            }    
            String tipoInterrupcionSTR = getTipoInterrupcion(this.tipoInterrupcion);   
            parametrosReporte.put("subregion",this.subregion);
            parametrosReporte.put("region",this.region);
            parametrosReporte.put("subestacion",this.subestacion);
            parametrosReporte.put("circuito",this.circuito);
            parametrosReporte.put("tipoInterrupcionSTR",tipoInterrupcionSTR);
            parametrosReporte.put("tipoVoltaje",this.tipoInterrupcion);
            
        
        nombre = "IndResumenMA";
        Date fechaActual = new Date();
        SimpleDateFormat sf = new SimpleDateFormat();
        sf.applyPattern("dd-MM-yyyy");                                
        String fechaActualSTR = sf.format(fechaActual);
        nombre += fechaActualSTR + ".pdf";

        
        nombreArchivo += ".jasper";
             
        
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
    /**
     * Cancela el proceso de la generación de reportes
     * @return success
     */    
    public String cancelar(){
        return "success";
    }               
           
    /**
     * Metodo modificador de circuitoBO.
     * @param circuitoBO El circuitoBO a modificar.
     */
    public void setCircuitoBO(CircuitoBO circuitoBO) {
        this.circuitoBO = circuitoBO;
    }
    /**
     * Metodo modificador de regionBO.
     * @param regionBO El regionBO a modificar.
     */
    public void setRegionBO(RegionBO regionBO) {
        this.regionBO = regionBO;
    }
    /**
     * Metodo modificador de subEstacionBO.
     * @param subEstacionBO El subEstacionBO a modificar.
     */
    public void setSubEstacionBO(SubEstacionBO subEstacionBO) {
        this.subEstacionBO = subEstacionBO;
    }
    /**
     * Metodo modificador de subRegionBO.
     * @param subRegionBO El subRegionBO a modificar.
     */
    public void setSubRegionBO(SubRegionBO subRegionBO) {
        this.subRegionBO = subRegionBO;
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
     * Metodo accesor de region.
     * @return Retorna el region.
     */
    public Integer getRegion() {
        return this.region;
    }
    /**
     * Metodo modificador de region.
     * @param region El region a modificar.
     */
    public void setRegion(Integer region) {
        this.region = region;
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
     * Metodo accesor de subregion.
     * @return Retorna el subregion.
     */
    public Integer getSubregion() {
        return this.subregion;
    }
    /**
     * Metodo modificador de subregion.
     * @param subregion El subregion a modificar.
     */
    public void setSubregion(Integer subregion) {
        this.subregion = subregion;
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
     * Metodo accesor de nivelRed.
     * @return Retorna el nivelRed.
     */
    public String getNivelRed() {
        return this.nivelRed;
    }
    /**
     * Metodo modificador de nivelRed.
     * @param nivelRed El nivelRed a modificar.
     */
    public void setNivelRed(String nivelRed) {
        this.nivelRed = nivelRed;
    }

	/**
	 * @return the ano
	 */
	public Integer getAno() {
		return ano;
	}

	/**
	 * @param ano the ano to set
	 */
	public void setAno(Integer ano) {
		this.ano = ano;
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
}
