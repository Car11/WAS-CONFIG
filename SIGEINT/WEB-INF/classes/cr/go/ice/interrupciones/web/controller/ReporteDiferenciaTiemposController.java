package cr.go.ice.interrupciones.web.controller;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vvs.jasperreports.JasperReportVVS;
import com.vvs.jsf.AbstractFacesController;
import com.vvs.utilidades.Fechas;
import com.vvs.utilidades.Utilidades;

import cr.go.ice.interrupciones.web.Recurso;
import net.sf.jasperreports.engine.JasperRunManager;
import cr.go.ice.interrupciones.BO.SeccionBO;
import cr.go.ice.interrupciones.BO.CircuitoBO;
import cr.go.ice.interrupciones.BO.RegionBO;
import cr.go.ice.interrupciones.BO.SubEstacionBO;
import cr.go.ice.interrupciones.BO.SubRegionBO;
import cr.go.ice.interrupciones.BO.UsuarioOficinaBO;
import cr.go.ice.interrupciones.domain.Seccion;
import cr.go.ice.interrupciones.domain.Circuito;
import cr.go.ice.interrupciones.domain.Region;
import cr.go.ice.interrupciones.domain.SeccionID;
import cr.go.ice.interrupciones.domain.SubEstacion;
import cr.go.ice.interrupciones.domain.SubRegion;
import cr.go.ice.interrupciones.domain.UsuarioOficina;
import cr.go.ice.interrupciones.servlets.ServletReporte;
import cr.go.ice.interrupciones.BO.EmpleadoBO;
import cr.go.ice.interrupciones.BO.OficinaBO;
import cr.go.ice.interrupciones.domain.Empleado;
import cr.go.ice.interrupciones.domain.Oficina;
import cr.go.ice.interrupciones.utils.Usuario;
import cr.go.ice.interrupciones.utils.UtilidadesFaces;
import cr.go.ice.cia.dominio.UsuarioCia;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ReporteDiferenciaTiemposController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ReporteDiferenciaTiemposController.java</code>.</p>
 * <p>Fecha creación: 27/08/2008</p>
 * <p>Ultima actualización: 27/08/2008</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ReporteDiferenciaTiemposController extends AbstractFacesController {

    private Integer codigoOficina;
    private Date fechaInicio;
    private Date fechaFinal;
    
    private Long cedula;
    private String nivelRed;
    
    private Integer region;
    private Integer subregion;
    private Integer subestacion;
    private Integer circuito;
    private Long seccion;
    
    private Integer actor;
    private Integer tiempo;
    private Integer formato;
    
   
    
    private OficinaBO oficinaBO;
    private EmpleadoBO empleadoBO;
    
    private RegionBO regionBO;
    private SubRegionBO subRegionBO;
    private SubEstacionBO subEstacionBO;
    private CircuitoBO circuitoBO;
    private SeccionBO seccionBO;
    private UsuarioOficinaBO usuarioOficinaBO;    
       
    private  static final String JasperPath = "/jasperReports/";
    
    /**
     * Constructor por defecto
     */    
    public ReporteDiferenciaTiemposController(){
        reiniciarCampos();        
    }
    
    /**
     * Metodo que reincia los atributos de la clase
     */
    private void reiniciarCampos(){
        this.fechaInicio = new Date();
        this.fechaFinal = new Date();        
        this.codigoOficina = new Integer(0);
        
        this.region = new Integer(0);
        this.subregion = new Integer(0);
        this.subestacion = new Integer(0);
        this.circuito = new Integer(0);
        this.seccion = new Long(0);
        
        this.actor = new Integer(0);
        this.tiempo = new Integer(0);
        this.formato = UtilidadesFaces.FORMATO_PDF;
        
        
        this.nivelRed = "";
    }
    
    /**
     * Precarga la oficina del usuario de la aplicación, si el mismo es un usuario CLOR
     * @param context
     */
    public void load(FacesContext context){
//      Comentado la restricción cuando se implementó el CIA
//      boolean userClor = Usuario.isUserClor();
      boolean userClor = true;
      
        Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
        
        System.out.println("seccion: "+seccion);
        if(limpiar != null)
            reiniciarCampos();
        
        if(userClor && limpiar != null){            
        	
            Empleado emp = this.empleadoBO.buscar(this.cedula);
            List<UsuarioOficina> listaPivote = this.usuarioOficinaBO.buscarCedula(this.cedula);
        	if(!listaPivote.isEmpty())
        	{
        		this.codigoOficina = listaPivote.get(0).getId().getOficina().getCodigoOficina();
        	}else
        	{
        		this.codigoOficina = new Integer(0);
        	}           
        }   
        
    }   
    
    
    public String getInit(){
//      Comentado la restricción cuando se implementó el CIA
//      boolean userClor = Usuario.isUserClor();
      boolean userClor = true;
      FacesContext context= FacesContext.getCurrentInstance();
        Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
        
        System.out.println("seccion: "+seccion);
        if(limpiar != null)
            reiniciarCampos();
        
        if(userClor && limpiar != null){            
        	
       	 String nombreUsuarioSession = (String) context.getApplication().createValueBinding("#{autorizacionController.nombreUsuario}").getValue(this.getFacesContext());
            String[] valores = nombreUsuarioSession.split("-");
            this.cedula = new Long(valores[0].trim());
            Empleado emp = this.empleadoBO.buscar(this.cedula);
            List<UsuarioOficina> listaPivote = this.usuarioOficinaBO.buscarCedula(this.cedula);
        	if(!listaPivote.isEmpty())
        	{
        		this.codigoOficina = listaPivote.get(0).getId().getOficina().getCodigoOficina();
        	}else
        	{
        		this.codigoOficina = new Integer(0);
        	}           
        }   
        
    	return "success";
    	
    }
    
    
    public String listenerCambioOficina(){
    	
    	this.region=Integer.valueOf(0);
    	this.subregion=Integer.valueOf(0);
    	this.subestacion=Integer.valueOf(0);
    	this.circuito=Integer.valueOf(0);
    	this.seccion=Long.valueOf(0);
    	
    	return "listener";
    	
    }
    
    public String listenerRegion(){
    	   	   	
    	this.subregion=Integer.valueOf(0);
    	this.subestacion=Integer.valueOf(0);
    	this.circuito=Integer.valueOf(0);
    	this.seccion=Long.valueOf(0);
    	
    	return "listener";
    }
    
    
    public String listenerSubregion(){
    	
    	this.subestacion=Integer.valueOf(0);
    	this.circuito=Integer.valueOf(0);
    	this.seccion=Long.valueOf(0);
    	
    	return "listener";
    }
    
  public String listenerSubestacion(){
    	
    	this.circuito=Integer.valueOf(0);
    	this.seccion=Long.valueOf(0);
    	
    	return "listener";
    }
  
  
  public String listenerCircuito(){
	  this.seccion=Long.valueOf(0);
	  return "listener";
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
    	return UtilidadesFaces.getListaNivelesRedCircuito();
    }  
    
    /**
     * Lista de tipos de actores por los cuales se puede presentar el informe
     * @return lista de actores
     */
    public List getListaActores(){
    	List actores = new ArrayList();    	
    	actores.add(new SelectItem(new Integer(0),"Cliente"));
    	actores.add(new SelectItem(new Integer(1),"Operador"));
    	actores.add(new SelectItem(new Integer(2),"Cuadrilla"));
    	actores.add(new SelectItem(new Integer(3),"Desplazamiento"));
    	actores.add(new SelectItem(new Integer(4),"Tiempo de reparación"));
    	actores.add(new SelectItem(new Integer(5),"Tiempo de retorno"));    	
    	return actores;
    }    
    
    /**
     * Retorna una lista de select item de las oficinas existentes
     * @return Lista de oficinas
     */    
    public List getListaOficinas(){    
        Vector voficinas = new Vector();
        List loficinas = oficinaBO.getOficinas();
        
        voficinas.add(new SelectItem(new Integer(0), "Todas"));
        
        for (int i = 0; i < loficinas.size(); i++) {
            Oficina xoficina = (Oficina) loficinas.get(i);
            voficinas.add(new SelectItem(xoficina.getCodigoOficina(), xoficina.getCodigoOficina() + " - " + xoficina.getNombreOficina()));
        }       
        
        return voficinas;
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
        regiones = this.regionBO.getRegionesPorOficina(this.codigoOficina);
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
        FacesContext context = FacesContext.getCurrentInstance();
        if(this.codigoOficina ==  null || this.codigoOficina.intValue() == 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una oficina."));
            correcto=false;
        }
        if(this.fechaInicio == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar una fecha de inicio."));
            correcto = false;
        }
        if(this.fechaFinal == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar una fecha de fin ."));
            correcto=false;
        }        
        return correcto;
    }
    
    private boolean validarOpcionesParametros(){
        boolean correcto = true;
        FacesContext context = FacesContext.getCurrentInstance();

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
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar la subestación."));
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
        
        if(this.tiempo == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El tiempo es requerido."));
            correcto=false;
        }else if(this.tiempo.intValue() < 0){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El tiempo no pueder ser menor a cero."));
            correcto=false;
        }
        
        return correcto;
    }
    
    /**
     * Realiza las validaciones de los parámetros de entrada dados por el usuario y genera el reporte de diferencia
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
        ctx.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteDiferenciaTiempos));
        ctx.getExternalContext().getSessionMap().put("nivelRedActual",this.nivelRed);
        ctx.getExternalContext().getSessionMap().put("codigoOficina",this.codigoOficina);        
        ctx.getExternalContext().getSessionMap().put("nombreOficina",this.oficinaBO.buscar(this.codigoOficina).getNombreOficina());
        SimpleDateFormat sf = new SimpleDateFormat();
        sf.applyPattern("dd/MM/yyyy");           
        ctx.getExternalContext().getSessionMap().put("fechaInicio",sf.format(this.fechaInicio));
        ctx.getExternalContext().getSessionMap().put("fechaFinal",sf.format(this.fechaFinal));
        ctx.getExternalContext().getSessionMap().put("region",this.region);
        ctx.getExternalContext().getSessionMap().put("subregion",this.subregion);
        ctx.getExternalContext().getSessionMap().put("subestacion",this.subestacion);
        ctx.getExternalContext().getSessionMap().put("circuito",this.circuito);
        ctx.getExternalContext().getSessionMap().put("seccion",this.seccion);
        if(this.nivelRed.equals("circuito")){
        	if(this.circuito.intValue() > 0)
        		ctx.getExternalContext().getSessionMap().put("nombreCircuito",this.circuitoBO.buscar(this.subestacion, this.circuito).getNombreCircuito());
        	else
        		ctx.getExternalContext().getSessionMap().put("nombreCircuito",null);
        }else{
        	SeccionID secID = new SeccionID();
        	secID.setSubEstacion(this.subestacion);
        	secID.setCircuito(this.circuito);
        	secID.setSeccion(this.seccion);
        	if(this.seccion.longValue() > 0)
        		ctx.getExternalContext().getSessionMap().put("nombreSeccion",this.seccionBO.buscar(secID).getNombreSeccion());
        	else
        		ctx.getExternalContext().getSessionMap().put("nombreSeccion",null);
        }
        ctx.getExternalContext().getSessionMap().put("actor",this.actor);
        ctx.getExternalContext().getSessionMap().put("tiempo",this.tiempo);
        ctx.getExternalContext().getSessionMap().put("formato",this.formato);        
                
    }
     
    /**
     * Metodo que obtiene los beans y invoca la generacion del reporte
     * @return success o error
     */
    @SuppressWarnings({ "unchecked"})
    private String generarReporte(){  
        //pasarParametrosReporte(); 
        int verificar = 0;
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
            
            
            //ServletContext contextServlet = (ServletContext)this.getFacesContext().getExternalContext().getContext();
           //String ServletImagepath = contextServlet.getRealPath("images" + File.separatorChar);
            //parametrosReporte.put("DIR_IMAGENES",ServletImagepath);
            SimpleDateFormat sf = new SimpleDateFormat();
            sf.applyPattern("dd/MM/yyyy");       
            parametrosReporte.put("codigoOficina",this.codigoOficina);
            parametrosReporte.put("nombreOficina",this.oficinaBO.buscar(this.codigoOficina).getNombreOficina());
            parametrosReporte.put("fechaInicio",sf.format(this.fechaInicio));
            parametrosReporte.put("fechaFinal",sf.format(this.fechaFinal));
            parametrosReporte.put("region",this.region);
            parametrosReporte.put("subregion",this.subregion);                                
            parametrosReporte.put("subestacion",this.subestacion);
            parametrosReporte.put("circuito",this.circuito);   
            parametrosReporte.put("seccion",this.seccion);
            
            if(this.nivelRed.equals("circuito")){
                if(this.circuito.intValue() > 0)
                    parametrosReporte.put("nombreCircuito",this.circuitoBO.buscar(this.subestacion, this.circuito).getNombreCircuito());
                else
                    parametrosReporte.put("nombreCircuito",null);
                verificar = 1;
            }else{
                SeccionID secID = new SeccionID();
                secID.setSubEstacion(this.subestacion);
                secID.setCircuito(this.circuito);
                secID.setSeccion(this.seccion);
                if(this.seccion.longValue() > 0)
                    parametrosReporte.put("nombreSeccion",this.seccionBO.buscar(secID).getNombreSeccion());
                else
                    parametrosReporte.put("nombreSeccion",null);
            }
            parametrosReporte.put("actor",actor);
            parametrosReporte.put("tiempo",tiempo);
            parametrosReporte.put("formato",this.formato);
            
            
            Date fechaActual = new Date();
            SimpleDateFormat spf = new SimpleDateFormat();
            spf.applyPattern("dd-MM-yyyy");                                
           String fechaActualSTR = spf.format(fechaActual);
            
            if(this.formato.equals(Integer.valueOf(1))){
                nombre = "Diferencia_de_Tiempo"+ fechaActualSTR +".pdf";
            }else{
                nombre = "Diferencia_de_Tiempo"+ fechaActualSTR +".xls";
            }   
            
            if(verificar == 0){
                nombreArchivo = "SigeItrRepDiferenciaTiemposSeccion";
            }
            else{
                nombreArchivo = "SigeItrRepDiferenciaTiemposCircuito";
            }
            
           
            if (this.runReport(JasperPath + nombreArchivo, nombre,parametrosReporte,this.getConnection(),this.formato,UtilidadesFaces.getCurrentUserId())){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Reporte Ejecutado."));
                return  "success";      
                
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error Reporte"));
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
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
     * Metodo modificador de empleadoBO.
     * @param empleadoBO El empleadoBO a modificar.
     */
    public void setEmpleadoBO(EmpleadoBO empleadoBO) {
        this.empleadoBO = empleadoBO;
    }
    /**
     * Metodo modificador de oficinaBO.
     * @param oficinaBO El oficinaBO a modificar.
     */
    public void setOficinaBO(OficinaBO oficinaBO) {
        this.oficinaBO = oficinaBO;
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
     * Metodo accesor de codigoOficina.
     * @return Retorna el codigoOficina.
     */
    public Integer getCodigoOficina() {
        return this.codigoOficina;
    }
    /**
     * Metodo modificador de codigoOficina.
     * @param codigoOficina El codigoOficina a modificar.
     */
    public void setCodigoOficina(Integer codigoOficina) {
        this.codigoOficina = codigoOficina;
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
	 * @return the actor
	 */
	public Integer getActor() {
		return actor;
	}

	/**
	 * @param actor the actor to set
	 */
	public void setActor(Integer actor) {
		this.actor = actor;
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
	 * @return the tiempo
	 */
	public Integer getTiempo() {
		return tiempo;
	}

	/**
	 * @param tiempo the tiempo to set
	 */
	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
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

	public UsuarioOficinaBO getUsuarioOficinaBO() {
		return usuarioOficinaBO;
	}

	public void setUsuarioOficinaBO(UsuarioOficinaBO usuarioOficinaBO) {
		this.usuarioOficinaBO = usuarioOficinaBO;
	}



}
