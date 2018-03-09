package cr.go.ice.interrupciones.web.controller;

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

import cr.go.ice.interrupciones.BO.AnimalBO;
import cr.go.ice.interrupciones.BO.SeccionBO;
import cr.go.ice.interrupciones.BO.CircuitoBO;
import cr.go.ice.interrupciones.BO.RegionBO;
import cr.go.ice.interrupciones.BO.SubEstacionBO;
import cr.go.ice.interrupciones.BO.SubRegionBO;
import cr.go.ice.interrupciones.domain.Animal;
import cr.go.ice.interrupciones.domain.Seccion;
import cr.go.ice.interrupciones.domain.Circuito;
import cr.go.ice.interrupciones.domain.Region;
import cr.go.ice.interrupciones.domain.SubEstacion;
import cr.go.ice.interrupciones.domain.SubRegion;
import cr.go.ice.interrupciones.servlets.ServletReporte;
import cr.go.ice.interrupciones.utils.UtilidadesFaces;
import cr.go.ice.interrupciones.web.Recurso;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ReporteInterrupcionPorAnimalController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ReporteInterrupcionPorAnimalController.java</code>.</p>
 * <p>Fecha creación: 26/06/2009</p>
 * <p>Ultima actualización: 26/06/2009</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ReporteInterrupcionPorAnimalController extends AbstractFacesController{

   
    private Date fechaInicio;
    private Date fechaFinal;
    private Integer animal;
    
    private String nivelRed;
    
    private Integer region;
    private Integer subregion;
    private Integer subestacion;
    private Integer circuito;
    private Long seccion;

    private Integer formato;

    private AnimalBO animalBO;
    
    private RegionBO regionBO;
    private SubRegionBO subRegionBO;
    private SubEstacionBO subEstacionBO;
    private CircuitoBO circuitoBO;
    private SeccionBO seccionBO;   
    private  static final String JasperPath = "/jasperReports/";  
    
    /**
     * Constructor por defecto
     */    
    public ReporteInterrupcionPorAnimalController(){
        reiniciarCampos();        
    }
    
    /**
     * Metodo que reincia los atributos de la clase
     */
    private void reiniciarCampos(){
        this.fechaInicio = new Date();
        this.fechaFinal = new Date();        
        this.animal = new Integer(0);
        
        this.region = new Integer(0);
        this.subregion = new Integer(0);
        this.subestacion = new Integer(0);
        this.circuito = new Integer(0);
        this.seccion = new Long(0);

        this.formato = UtilidadesFaces.FORMATO_PDF;
        
        this.nivelRed = "";
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
    public List getListaNivelesRed(){
    	List niveles = new ArrayList();    	
        niveles.add(new SelectItem("region","Región"));  
        niveles.add(new SelectItem("subregion","Subregión"));  
        niveles.add(new SelectItem("subestacion","Subestación"));  
    	niveles.add(new SelectItem("circuito","Circuito"));
    	niveles.add(new SelectItem("seccion","Sección"));
    	return niveles;
    }  
    
    
    /**
     * Retorna una lista de select item de los animales existentes
     * @return Lista de animales
     */    
    public List getListaAnimales(){    
        Vector voficinas = new Vector();
        List lanimales = animalBO.getAnimales();
        
        voficinas.add(new SelectItem(new Integer(0), "Todos"));
        
        for (int i = 0; i < lanimales.size(); i++) {
            Animal animal = (Animal) lanimales.get(i);
            voficinas.add(new SelectItem(animal.getCodigoAnimal(), animal.getCodigoAnimal() + " - " + animal.getNombreAnimal()));
        }       
        
        return voficinas;
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
    	this.seccion = new Long(0);
        return "success";
    }
    public void listenerRegion() {
    	this.subregion = new Integer(0);
    	this.subestacion = new Integer(0);
    	this.circuito = new Integer(0);
    	this.seccion = new Long(0);
    }
    public void listenerSubregion() {
    	this.subestacion = new Integer(0);
    	this.circuito = new Integer(0);
    	this.seccion = new Long(0);
    }
    public void listenerSubestacion() {
    	this.circuito = new Integer(0);
    	this.seccion = new Long(0);
    }
    public void listenerCircuito() {
    	this.seccion = new Long(0);
    }
    
       /**
     * Metodo accesor de circuitos.
     * @return Retorna el circuitos.
     */
    public List getCircuitos() {  
        List circuitos = this.circuitoBO.getCircuitos(this.subestacion);
        List items = new ArrayList();
        items.add(new SelectItem(new Integer(0), "Todos"));
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
        items.add(new SelectItem(new Integer(0), "Todas"));
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
     * Metodo accesor de secciones.
     * @return Retorna el secciones.
     */
    public List getSecciones() {  
        //List secciones = this.seccionBO.getTodasSecciones(this.subestacion, this.circuito);
        List secciones = this.seccionBO.getSeccionesFiltro(this.region, this.subregion, this.subestacion, this.circuito);
        List items = new ArrayList();
        items.add(new SelectItem(new Long(0), "Todas"));
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
    /**
     * Metodo accesor de subEstaciones.
     * @return Retorna el subEstaciones.
     */
    public List getSubEstaciones() {
        List subEstaciones = this.subEstacionBO.getSubEstaciones(this.region, this.subregion);
        List items = new ArrayList();
        items.add(new SelectItem(new Integer(0), "Todas"));
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
        items.add(new SelectItem(new Integer(0), "Todas"));
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
       
        if(this.fechaInicio == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar un fecha de inicio."));
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
        long horasInicio = Fechas.millisToMinutes(this.fechaInicio.getTime());
        long horasFinal = Fechas.millisToMinutes(this.fechaFinal.getTime());
        long diferencia = horasFinal - horasInicio;
        if(diferencia < 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de fin debe ser mayor o igual a la fecha de inicio."));
            correcto=false;
        }
         
        if(this.nivelRed.equals("subregion")){
            if(this.region.intValue() <= 0){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar la región."));
                correcto=false;
            }         
        }          
        if(this.nivelRed.equals("subestacion")){
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
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccioar la subregión."));
                correcto=false;
            }              
            if(this.subestacion.intValue() <= 0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccinoar la subestación."));
                correcto=false;
            }
        }  
        if(this.nivelRed.equals("seccion")){
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
    
    /**
     * Realiza las validaciones de los parámetros de entrada dados por el usuario y genera el reporte
     * de tiempos
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
        ctx.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteInterrupcionesPorAnimales));
        ctx.getExternalContext().getSessionMap().put("animal",this.animal);
        ctx.getExternalContext().getSessionMap().put("nivelRedActual",this.nivelRed);
        SimpleDateFormat sf = new SimpleDateFormat();
        sf.applyPattern("dd/MM/yyyy");           
        ctx.getExternalContext().getSessionMap().put("fechaInicio",sf.format(this.fechaInicio).toString());
        ctx.getExternalContext().getSessionMap().put("fechaFinal",sf.format(this.fechaFinal).toString());
        ctx.getExternalContext().getSessionMap().put("region",this.region);
        ctx.getExternalContext().getSessionMap().put("subregion",this.subregion);
        ctx.getExternalContext().getSessionMap().put("subestacion",this.subestacion);
        ctx.getExternalContext().getSessionMap().put("circuito",this.circuito);
        ctx.getExternalContext().getSessionMap().put("seccion",this.seccion);
        ctx.getExternalContext().getSessionMap().put("formato",this.formato);        
                
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
            parametrosReporte.put("animal",this.animal);
            SimpleDateFormat sf = new SimpleDateFormat();
            sf.applyPattern("dd/MM/yyyy");      
            
            parametrosReporte.put("fechaInicio",sf.format(this.fechaInicio).toString());
            parametrosReporte.put("fechaFin",sf.format(this.fechaFinal).toString());
            parametrosReporte.put("region",this.region);
            
            if(nivelRed.equals("subregion")){
                parametrosReporte.put("subregion",this.subregion);
                parametrosReporte.put("nivelRed","Subregión");
                nombreArchivo = "SigeItrRepCausaAnimales-SubRegion";
            }else{
                if(nivelRed.equals("subestacion")){
                    parametrosReporte.put("subregion",this.subregion);
                    parametrosReporte.put("subestacion",this.subestacion);
                    parametrosReporte.put("nivelRed","Subestación");
                    nombreArchivo = "SigeItrRepCausaAnimales-SubEstacion";
                }else{
                    if(nivelRed.equals("circuito")){
                        parametrosReporte.put("subregion",this.subregion);
                        parametrosReporte.put("subestacion",this.subestacion);
                        parametrosReporte.put("circuito",circuito);
                        parametrosReporte.put("nivelRed","Circuito");
                        nombreArchivo = "SigeItrRepCausaAnimales-Circuito";
                    }else{
                        if(nivelRed.equals("seccion")){
                            parametrosReporte.put("subregion",this.subregion);
                            parametrosReporte.put("subestacion",this.subestacion);
                            parametrosReporte.put("circuito",circuito);
                            parametrosReporte.put("nivelRed","Sección");
                            parametrosReporte.put("seccion",new Integer(Integer.parseInt(seccion.toString())));
                            nombreArchivo = "SigeItrRepCausaAnimales-Seccion";
                        }else{
                            parametrosReporte.put("nivelRed","Región");
                            nombreArchivo = "SigeItrRepCausaAnimales-Region";
                        }
                    }
                }
            }      
            
            nombreArchivo += ".jasper";
            
            nombre = "InterrupCausaAnimales";
            Date fechaActual = new Date();
            SimpleDateFormat spf = new SimpleDateFormat();
            spf.applyPattern("dd-MM-yyyy");                                
            String fechaActualSTR = spf.format(fechaActual);
            
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
    /**
     * Cancela el proceso de la generación de reportes
     * @return success
     */    
    public String cancelar(){
        this.reiniciarCampos();
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
     * Metodo modificador de seccionBO.
     * @param seccionBO El seccionBO a modificar.
     */
    public void setSeccionBO(SeccionBO seccionBO) {
        this.seccionBO = seccionBO;
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
     * Metodo accesor de animal.
     * @return Retorna el animal.
     */
    public Integer getAnimal() {
        return this.animal;
    }

    /**
     * Metodo modificador de animal.
     * @param animal El animal a modificar.
     */
    public void setAnimal(Integer animal) {
        this.animal = animal;
    }

    /**
     * Metodo modificador de animalBO.
     * @param animalBO El animalBO a modificar.
     */
    public void setAnimalBO(AnimalBO animalBO) {
        this.animalBO = animalBO;
    }

    /**
	 * @return the formato
	 */
	public Integer getFormato() {
		return formato;
	}

	/**
	 * @param formato the formato to set
	 */
	public void setFormato(Integer formato) {
		this.formato = formato;
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
