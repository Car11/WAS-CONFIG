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

import cr.go.ice.interrupciones.BO.AuditoriaBO;
import cr.go.ice.interrupciones.BO.EmpleadoBO;
import cr.go.ice.interrupciones.BO.OficinaBO;
import cr.go.ice.interrupciones.BO.SeccionBO;
import cr.go.ice.interrupciones.BO.CircuitoBO;
import cr.go.ice.interrupciones.BO.RegionBO;
import cr.go.ice.interrupciones.BO.SubEstacionBO;
import cr.go.ice.interrupciones.BO.SubRegionBO;
import cr.go.ice.interrupciones.BO.UsuarioOficinaBO;
import cr.go.ice.interrupciones.domain.Empleado;
import cr.go.ice.interrupciones.domain.Oficina;
import cr.go.ice.interrupciones.domain.Seccion;
import cr.go.ice.interrupciones.domain.Circuito;
import cr.go.ice.interrupciones.domain.Region;
import cr.go.ice.interrupciones.domain.SubEstacion;
import cr.go.ice.interrupciones.domain.SubRegion;
import cr.go.ice.interrupciones.domain.UsuarioOficina;
import cr.go.ice.interrupciones.servlets.ServletReporte;
import cr.go.ice.interrupciones.utils.UtilidadesFaces;
import cr.go.ice.interrupciones.web.Recurso;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ReporteInterrupcionPorAnimalController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ReporteAuditoriaController.java</code>.</p>
 * <p>Fecha creación: 25/09/2012</p>
 * <p>Ultima actualización: 25/09/2012</p>
 * @author Grettel
 * @version 1.1
 */
public class ReporteAuditoriaController extends AbstractFacesController {

   
    private Date fechaInicio;
    private Date fechaFinal;
    private Integer codigoOficina;
    
    private String nivelRed;
    
    private Integer region;
    private Integer subregion;
    private Integer subestacion;
    private Integer circuito;
    private Long seccion;
    private Long cedula;

    private Integer formato;

    private OficinaBO oficinaBO;
    private EmpleadoBO empleadoBO;
    
    private RegionBO regionBO;
    private SubRegionBO subRegionBO;
    private SubEstacionBO subEstacionBO;
    private CircuitoBO circuitoBO;
    private SeccionBO seccionBO;
    private UsuarioOficinaBO usuarioOficinaBO;
    
    private AuditoriaBO auditoriaBO;
       
    String error;
    String sqlFiltro = "";
    String tituloNivelRed="";
  
    private  static final String JasperPath = "/jasperReports/";  

	

	/**
     * Constructor por defecto
     */    
    public ReporteAuditoriaController(){
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

        this.formato = UtilidadesFaces.FORMATO_PDF;
        
        this.nivelRed = "";
    }
    
    
    public String getInit(){
    	FacesContext context = FacesContext.getCurrentInstance();
    	Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
        if(limpiar != null){
            reiniciarCampos();        
//          Comentado la restricción cuando se implementó el CIA
//          boolean userClor = Usuario.isUserClor();
            boolean userClor = true;
            if(userClor){
            	String nombreUsuarioSession = (String) context.getApplication().createValueBinding("#{autorizacionController.nombreUsuario}").getValue(this.getFacesContext());
	            String[] valores = nombreUsuarioSession.split("-");
	            cedula = new Long(valores[0].trim());
                Empleado emp = this.empleadoBO.buscar(cedula);
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
    	return "success";
    }
    
    /**
     * Precarga la oficina del usuario de la aplicación, si el mismo es un usuario CLOR
     * @param context
     */
       
    public void load(FacesContext context){
    	
        Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
        if(limpiar != null){
            reiniciarCampos();        
//          Comentado la restricción cuando se implementó el CIA
//          boolean userClor = Usuario.isUserClor();
            boolean userClor = true;
            if(userClor){
            	
           	 String nombreUsuarioSession = (String) context.getApplication().createValueBinding("#{autorizacionController.nombreUsuario}").getValue(this.getFacesContext());
                String[] valores = nombreUsuarioSession.split("-");
                this.cedula = new Long(valores[0].trim()); 
                Empleado emp = this.empleadoBO.buscar(cedula);
                List<UsuarioOficina> listaPivote = this.usuarioOficinaBO.buscarCedula(cedula);
            	if(!listaPivote.isEmpty())
            	{
            		this.codigoOficina = listaPivote.get(0).getId().getOficina().getCodigoOficina();
            	}else
            	{
            		this.codigoOficina = new Integer(0);
            	}
                
            } 
        }
                 
    }      
    
    
    public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List getListaOficinas(){ 
    	Vector voficinas = new Vector();
        List loficinas = oficinaBO.getOficinas();
        
        voficinas.add(new SelectItem(new Integer(0), "Seleccione una oficina"));
        
        for (int i = 0; i < loficinas.size(); i++) {
            Oficina xoficina = (Oficina) loficinas.get(i);
            voficinas.add(new SelectItem(xoficina.getCodigoOficina(), xoficina.getCodigoOficina() + " - " + xoficina.getNombreOficina()));
        }       
        
        return voficinas;
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
    	return UtilidadesFaces.getListaNivelesRed();
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
        items.add(new SelectItem(new Integer(0), "Seleccione un circuito"));
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
        items.add(new SelectItem(new Integer(0), "-- Seleccione una región --"));
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
        items.add(new SelectItem(new Long(0), "Seleccione una sección"));
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
        if(this.codigoOficina == null || this.codigoOficina.intValue() <= 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La oficina es requerida."));
            correcto=false;
        }   
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
        if(this.nivelRed.equals("subestacion")){
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
            if(this.circuito.intValue() <= 0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe selccionar el circuito."));
                correcto=false;
            }
        }  
        if(this.nivelRed.equals("seccion")){
            if(this.region.intValue() <= 0){
                FacesMessage msg = new FacesMessage();
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                msg.setDetail("Debe seleccionar la región");            
                context.addMessage("form1:cboRegion", msg);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar ."));
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
            if(this.seccion.intValue() <= 0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una sección."));
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
        ctx.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteAuditoria));
        ctx.getExternalContext().getSessionMap().put("codigoOficina",this.codigoOficina);
        ctx.getExternalContext().getSessionMap().put("nombreOficina",this.oficinaBO.buscar(this.codigoOficina).getNombreOficina());
        ctx.getExternalContext().getSessionMap().put("nivelRed",this.nivelRed);
        SimpleDateFormat sf = new SimpleDateFormat();
        sf.applyPattern("dd/MM/yyyy");           
        ctx.getExternalContext().getSessionMap().put("fechaInicio",sf.format(this.fechaInicio).toString());
        ctx.getExternalContext().getSessionMap().put("fechaFinal",sf.format(this.fechaFinal).toString());
        ctx.getExternalContext().getSessionMap().put("formato",this.formato); 
       
        ctx.getExternalContext().getSessionMap().put("region",this.region);
        ctx.getExternalContext().getSessionMap().put("subregion",this.subregion);
        ctx.getExternalContext().getSessionMap().put("subestacion",this.subestacion);
        ctx.getExternalContext().getSessionMap().put("circuito",this.circuito);
        ctx.getExternalContext().getSessionMap().put("seccion",this.seccion);
        ctx.getExternalContext().getSessionMap().put("sqlFiltro",this.sqlFiltro);
        ctx.getExternalContext().getSessionMap().put("tituloNivelRed",this.tituloNivelRed);
     
        
    }
     
    private void generarSQL(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");              
        sqlFiltro = "SELECT (inte.reg||'-'||inte.subregion||'-'||inte.sub||'-'||inte.cir||'-'||inte.seccion) AS tramoRed,"+
				"inte.tecres, "+
				"inte.causa1, "+
				"inte.tiefue, "+
				"inte.operador, "+
				"inte.cod_voltaje, "+
				"inte.abo_afecta, "+
				"to_char(inte.fecini, 'dd/mm/rrrr') fecini , " +
				"to_char(inte.fecfin, 'dd/mm/rrrr') fecfin, " +
				"inte.hrs_abo, " +
				"inte.usuario, " +
				"decode (inte.accion,'MODIFI_VI','Viejo','MODIFI_NU','Nuevo') accion, " +
				"to_char(inte.fecha, 'dd/mm/rrrr') fecha, " +
				"inte.reg, " +
				"(inte.aa||'-'||inte.cod_oficina||'-'||inte.numinterrup) AS numeroInterrupcion, " +
				"(inte.operador||'-'||cedulas.nombre_empleado) AS  nombre_empleado, ";
				
		        if(nivelRed.equals("region")){
		        	sqlFiltro += "(inte.reg||'-'||region.NOM_REGION) tituloEncabezado ";
		        }   
		        if(nivelRed.equals("subregion")){
		        	sqlFiltro += "(inte.reg||'-'||inte.subregion||'-'||subregion.nom_subregion) tituloEncabezado ";
		        }
		        if(nivelRed.equals("subestacion")){
		        	sqlFiltro += "(inte.reg||'-'||inte.subregion||'-'||inte.sub||'-'||subestacion.nom_subestacion) tituloEncabezado ";
		        }  
		        if(nivelRed.equals("circuito")){
		        	sqlFiltro += "(inte.reg||'-'||inte.subregion||'-'||inte.sub||'-'||inte.cir||'-'||circuito.nom_circuito) tituloEncabezado ";
		        } 
		        if(nivelRed.equals("seccion")){
		        	sqlFiltro += "(inte.reg||'-'||inte.subregion||'-'||inte.sub||'-'||inte.cir||'-'||inte.seccion||'-'||seccion.nom_seccion) tituloEncabezado ";
		        }  
				
				
				
		        sqlFiltro += "FROM eve19m inte " +
                "inner join eve05c region on (region.reg = inte.reg) " +
                "inner join eve04c subregion on (subregion.reg = inte.reg and subregion.subregion = inte.subregion) " +
                "inner join eve03c subestacion on (subestacion.sub = inte.sub) " +
                "inner join eve01c circuito on (circuito.sub = inte.sub and circuito.cir = inte.cir) " +
                "inner join eve06c seccion on (seccion.seccion = inte.seccion and seccion.sub = inte.sub and seccion.cir = inte.cir) " +
                "inner join eve07c cedulas on (cedulas.cedula=inte.operador) " +
                "WHERE " +
                "(inte.cod_oficina = "+this.codigoOficina+") and " +
                "(inte.fecini BETWEEN TO_DATE('"+sdf.format(this.fechaInicio)+"','DD/MM/YYYY') AND TO_DATE('"+sdf.format(this.fechaFinal)+"','DD/MM/YYYY')) and " + 
                "(inte.accion = 'MODIFI_VI' or inte.accion ='MODIFI_NU') and " ;
                
           	 
        
        if(nivelRed.equals("region")){
            sqlFiltro += "(inte.reg = "+ this.region+") ";
            tituloNivelRed="Región";
           
         }   
        if(nivelRed.equals("subregion")){
        	sqlFiltro += "(inte.reg = "+ this.region+" and inte.subregion = "+this.subregion+") "; 
        	 tituloNivelRed="Subregión";
        	
        	 
        }
        if(nivelRed.equals("subestacion")){
        	sqlFiltro += "(inte.reg = "+ this.region+" and inte.subregion = "+this.subregion+" and inte.sub = "+this.subestacion+") ";
        	 tituloNivelRed="Subestación";
        	
        }  
        if(nivelRed.equals("circuito")){
        	sqlFiltro += "(inte.reg = "+ this.region+" and inte.subregion = "+this.subregion+" and inte.sub = "+this.subestacion+" and inte.cir = "+this.circuito+") ";
        	tituloNivelRed="Circuito";
       		
        } 
        if(nivelRed.equals("seccion")){
        	sqlFiltro += "(inte.reg = "+ this.region+" and inte.subregion = "+this.subregion+" and inte.sub = "+this.subestacion+" and inte.cir = "+this.circuito+" and inte.seccion = "+this.seccion+") ";
        	tituloNivelRed="Sección";
       		
        }  
       
        
        sqlFiltro+="order by numeroInterrupcion";
        
             
	         System.out.println(sqlFiltro);
       
    }
    
    
    @SuppressWarnings("unchecked")
    public String generarReporte(){
    	
        FacesMessage msg = new FacesMessage();  
        String  nombreArchivoJasper = "";
        HashMap parametrosReporte = new HashMap();
        
		Long movimientos = null;
		FacesContext context = FacesContext.getCurrentInstance();
		
		movimientos = this.auditoriaBO.getAuditorias(this.codigoOficina, this.fechaInicio, this.fechaFinal, this.nivelRed, this.region, this.subregion, this.subestacion, this.circuito, this.seccion);
			
		if(movimientos.longValue() == 0){
			
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No hay información que cumpla con los parámetros definidos."));
          
			return "error";
		}
		else{
		    
		    try{	
		       // FacesContext ctx = FacesContext.getCurrentInstance();
		       // ctx.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteAuditoria));
		        SimpleDateFormat spf = new SimpleDateFormat();
		        spf.applyPattern("dd/MM/yyyy");  
                generarSQL();
  			    //pasarParametrosReporte(); 
				
			   // FacesContext context = FacesContext.getCurrentInstance();		    		    
//			    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//			    HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
//			    RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletReporte");
//                dispatcher.forward(request, response);
//                response.getOutputStream().flush();

//                if(!FacesContext.getCurrentInstance().getResponseComplete() ) {
//                    FacesContext.getCurrentInstance().responseComplete();
//                }  
                
                String nombreArchivo = "Auditoria";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR;

                parametrosReporte.put("codigoOficina",this.codigoOficina);
                parametrosReporte.put("nombreOficina",this.oficinaBO.buscar(this.codigoOficina).getNombreOficina());
                parametrosReporte.put("fechaInicio",spf.format(this.fechaInicio).toString());
                parametrosReporte.put("fechaFinal",sf.format(this.fechaFinal).toString());
                parametrosReporte.put("region",this.region);
                parametrosReporte.put("subregion",this.subregion);                                
                parametrosReporte.put("subestacion",this.subestacion);
                parametrosReporte.put("circuito",this.circuito);   
                parametrosReporte.put("seccion",this.seccion);
                parametrosReporte.put("formato",this.formato);
                parametrosReporte.put("nivelRed",this.nivelRed);
                parametrosReporte.put("sqlFiltro",this.sqlFiltro);
                parametrosReporte.put("tituloNivelRed",this.tituloNivelRed);
                
                nombreArchivoJasper += "SigeAuditoria.jasper";   
                if(this.formato.equals(Integer.valueOf(0))){
                    nombreArchivo += ".xls";
                }else{
                    nombreArchivo += ".pdf";
                }   
                sqlFiltro="";
                
                if (this.runReport(JasperPath + nombreArchivoJasper, nombreArchivo,parametrosReporte,this.getConnection(),this.formato,UtilidadesFaces.getCurrentUserId())){
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
 
    
    public AuditoriaBO getAuditoriaBO() {
		return auditoriaBO;
	}

	public void setAuditoriaBO(AuditoriaBO auditoriaBO) {
		this.auditoriaBO = auditoriaBO;
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
    public OficinaBO getOficinaBO() {
		return oficinaBO;
	}

	public void setOficinaBO(OficinaBO oficinaBO) {
		this.oficinaBO = oficinaBO;
	}

	public EmpleadoBO getEmpleadoBO() {
		return empleadoBO;
	}

	public void setEmpleadoBO(EmpleadoBO empleadoBO) {
		this.empleadoBO = empleadoBO;
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

	public String getTituloNivelRed() {
		return tituloNivelRed;
	}

	public void setTituloNivelRed(String tituloNivelRed) {
		this.tituloNivelRed = tituloNivelRed;
	}

	

	public String getSqlFiltro() {
		return sqlFiltro;
	}

	public void setSqlFiltro(String sqlFiltro) {
		this.sqlFiltro = sqlFiltro;
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

	public Long getCedula() {
		return cedula;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}
}
