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

import cr.go.ice.interrupciones.BO.*;
import cr.go.ice.interrupciones.domain.Causa;
import cr.go.ice.interrupciones.domain.Interrupcion;
import cr.go.ice.interrupciones.domain.Seccion;
import cr.go.ice.interrupciones.domain.Circuito;
import cr.go.ice.interrupciones.domain.SeccionID;
import cr.go.ice.interrupciones.domain.SubEstacion;
import cr.go.ice.interrupciones.domain.UsuarioOficina;
import cr.go.ice.interrupciones.servlets.ServletReporte;
import cr.go.ice.interrupciones.domain.Empleado;
import cr.go.ice.interrupciones.domain.Oficina;
import cr.go.ice.interrupciones.utils.UtilidadesI;
import cr.go.ice.interrupciones.utils.UtilidadesFaces;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ReporteIndiceGlobalSeccionController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ReporteIndiceGlobalSeccionController.java</code>.</p>
 * <p>Fecha creación: 05/09/2008</p>
 * <p>Ultima actualización: 05/09/2008</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ReporteIndiceGlobalSeccionController extends AbstractFacesController{

    private Integer codigoOficina;
    private Date fechaInicio;
    private Date fechaFinal;
    
    
    private Integer subestacion;
    private Integer circuito;
    private Long seccion;
    private Long seccionFinal;
    private Long cedula;
    private Integer formato;
    private Integer tipoInterrupcion;
           
    private OficinaBO oficinaBO;
    private EmpleadoBO empleadoBO;
    
    private SubEstacionBO subEstacionBO;
    private CircuitoBO circuitoBO;
    private SeccionBO seccionBO;
    private InterrupcionBO interrupcionBO;
    private UsuarioOficinaBO usuarioOficinaBO; 
    
    private Seccion [] listaSeccionItems;
    private List items;      
    
    private boolean grupo;
    private boolean rango;
    private  static final String JasperPath = "/jasperReports/";   
    
    /**
     * Constructor por defecto
     */    
    public ReporteIndiceGlobalSeccionController(){
        reiniciarCampos();        
    }
    
    /**
     * Metodo que reincia los atributos de la clase
     */
    private void reiniciarCampos(){
        this.fechaInicio = new Date();
        this.fechaFinal = new Date();        
        this.codigoOficina = new Integer(0);
        
        this.subestacion = new Integer(0);
        this.circuito = new Integer(0);
        this.seccion = new Long(0);
        this.seccionFinal = new Long(0);
        this.tipoInterrupcion = new Integer(0);
        this.formato = UtilidadesFaces.FORMATO_PDF;
        
        this.grupo = false;
        this.rango = false;
        this.items = new ArrayList();

    }
    
    public String getInit(){
    	 FacesContext context = FacesContext.getCurrentInstance();

         String nombreUsuarioSession = (String) context.getApplication().createValueBinding("#{autorizacionController.nombreUsuario}").getValue(this.getFacesContext());
         String[] valores = nombreUsuarioSession.split("-");
         this.cedula = new Long(valores[0].trim());
         
    	 boolean userClor = true;

         Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
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
         return "success";
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
    
    /**
     * Comment for listenerOficina
     * @param v
     * @return "listener" a oficina
     */
    public String listenerOficina(){                 
        this.items = new ArrayList();
        return "listener";  
    }   
    
    /**
     * Comment for listenerSubestacion
     * @param v
     * @return "listener" a subestacion
     */
    public String listenerSubestacion(){                 
        this.items = new ArrayList();
        return "listener";  
    }    
    
    /**
     * Comment for listenerCircuito
     * @param v
     * @return "listener" a circuito
     */
    public String listenerCircuito(){                 
        this.items = new ArrayList();
        return "listener";  
    }      
    
    /**
     * Comment for listenerCheck
     * @param v
     * @return "listener" a grupo
     */
    public String listenerCheck(){                 
        this.items = new ArrayList();
        return "listener";  
    }   
    
    /**
     * Comment for listenerRango
     * @param v
     * @return "listener" a rango
     */
    public String listenerRango(){                 
        this.items = new ArrayList();
        return "listener";  
    }      
    
    /**
     * Retorna una lista de select item de los tipos de interrupción
     * @return Lista de tipos de interrupción
     */    
    public List getListaTipoInterrupcion(){
        List tramo = new ArrayList();        
        tramo.add(new SelectItem(new Integer(0),"0 - Todos"));
        tramo.add(new SelectItem(Interrupcion.INTERRUPCION_SUMINISTRO,"1 - Interrupción de transporte"));
        tramo.add(new SelectItem(Interrupcion.INTERRUPCION_PRIMARIA,"2 - Interrupción primaria"));
        tramo.add(new SelectItem(Interrupcion.INTERRUPCION_SECUNDARIA,"3 - Interrupción secundaria"));
        tramo.add(new SelectItem(Interrupcion.INTERRUPCION_CATASTROFE,"4 - Interrupción por catástrofe"));
        tramo.add(new SelectItem(Interrupcion.INTERRUPCION_DISTRIBUCION,"5 - Interrupción por distribución"));        
        return tramo;       
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
    	niveles.add(new SelectItem("circuito","Circuito"));
    	niveles.add(new SelectItem("seccion","Sección"));    	
    	return niveles;
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
     * Metodo accesor de secciones.
     * @return Retorna el secciones.
     */
    public List getSecciones() {  
        List secciones = this.seccionBO.getTodasSecciones(this.subestacion, this.circuito);
        List items = new ArrayList();
        items.add(new SelectItem(new Long(0),"Seleccione una sección"));
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
        List subEstaciones = this.subEstacionBO.getSubEstaciones(this.codigoOficina);
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
     * Metodo que retorna la lista de items seleccionados para el nivel de red seccion
     * @return lista de items de acuerdo al nivel de red deseado
     */     
    public List getItemsSeccionesSI(){
        List itemsSI = new ArrayList();
        if(this.grupo){
	        for(int i = 0; i < this.items.size(); i++){
	            SelectItem item = new SelectItem();
	            Seccion seccion = (Seccion)this.items.get(i);
	            item.setValue(seccion);
	            item.setLabel(seccion.getSeccionID().getSubEstacion() + "-" + seccion.getSeccionID().getCircuito() + "-" + seccion.getSeccionID().getSeccion() + " - " + seccion.getNombreSeccion());           
	            itemsSI.add(item);
	        }
        }
        return itemsSI;
    }    
    

    /**
     * Metodo que elimina un item a la lista de items seleccionados para generar el reporte, 
     * de acuerdo a la opción de grupo
     * @return success
     */  
    public String eliminarItem(){
    	if(this.grupo){
	        for(int i = 0; i < this.listaSeccionItems.length; i++){
	            Seccion seleccionado = (Seccion)this.listaSeccionItems[i];
	            for(int j = 0; j < this.items.size(); j++){
	                Seccion seccion = (Seccion)this.items.get(j);
	                if(seccion.equals(seleccionado)){
	                    this.items.remove(j);
	                    j = this.items.size() + 1;
	                }
	            }
	        }    
    	}
    	return "success";
    }   
    
    /**
     * Metodo que agrega un item a la lista de items seleccionados para generar el reporte, 
     * de acuerdo a la opción de grupo
     * @return success
     */
    public String agregarItem(){
        if(this.grupo){    
        	SeccionID id = new SeccionID();
            id.setSubEstacion(this.subestacion);
            id.setCircuito(this.circuito);
            id.setSeccion(this.seccion);
            Seccion seccion = this.seccionBO.buscar(id);
            if(seccion != null && this.items.contains(seccion) == false)
                this.items.add(seccion);                   
        }

        return "success";
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar una fecha de fin ."));
            correcto=false;
        }   
                   
        
        return correcto;
    }
    
    private boolean validarOpcionesParametros(){
        boolean correcto = true;
        FacesContext context = FacesContext.getCurrentInstance();
        if(this.codigoOficina.intValue() == 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una oficina ."));
            correcto=false;
        }
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
            FacesMessage msg = new FacesMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de fin debe ser mayor o igual a la fecha de inicio."));
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
        
        if(this.grupo == true){
            if(this.seccion == null || this.seccion.longValue() == 0){
                FacesMessage msg = new FacesMessage();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe selccionar secciones."));
                correcto=false;
            }
        }           
        
        if(this.rango == true){
        	if(this.seccion.longValue() >= this.seccionFinal.longValue()){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La sección final debe ser mayor a la inicial."));
                correcto=false;
        	}
        }     
        
        return correcto;
    }
    
    /**
     * Realiza las validaciones de los parámetros de entrada dados por el usuario y genera el reporte de indices
     * globales por sección
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
        String itemsSTR = "0";       
        Integer cantidadItems = new Integer(0);  
        boolean utilizarDatosHistoricos = false;
        
        ctx.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteIndiceGlobalSeccion));
        ctx.getExternalContext().getSessionMap().put("codigoOficina",this.codigoOficina);        
        ctx.getExternalContext().getSessionMap().put("nombreOficina",this.oficinaBO.buscar(this.codigoOficina).getNombreOficina());
        SimpleDateFormat sf = new SimpleDateFormat();
        sf.applyPattern("dd/MM/yyyy");           
        ctx.getExternalContext().getSessionMap().put("fechaInicio",sf.format(this.fechaInicio));
        ctx.getExternalContext().getSessionMap().put("fechaFinal",sf.format(this.fechaFinal));
        ctx.getExternalContext().getSessionMap().put("formato",this.formato);   
        
        String tipoInterrupcionSTR = this.getTipoInterrupcion(this.tipoInterrupcion);
        System.out.println("tipoInterrupcion: " + this.tipoInterrupcion);
        System.out.println("tipoInterrupcionSTR: " + tipoInterrupcionSTR);
        ctx.getExternalContext().getSessionMap().put("tipoInterrupcionSTR",tipoInterrupcionSTR);
        
        if(this.tipoInterrupcion.equals(new Integer(0))){
        	ctx.getExternalContext().getSessionMap().put("tipoVoltaje","1,2,3,4");
        	ctx.getExternalContext().getSessionMap().put("causaCatastrofe1",new Integer(0));
            ctx.getExternalContext().getSessionMap().put("causaCatastrofe2",new Integer(0));
        }else if(this.tipoInterrupcion.equals(Interrupcion.INTERRUPCION_SUMINISTRO)){
        	ctx.getExternalContext().getSessionMap().put("tipoVoltaje","1");
        	ctx.getExternalContext().getSessionMap().put("causaCatastrofe1",Causa.CODIGO_CATASTROFE);
            ctx.getExternalContext().getSessionMap().put("causaCatastrofe2",new Integer(0));
        }else if(this.tipoInterrupcion.equals(Interrupcion.INTERRUPCION_PRIMARIA)){
        	ctx.getExternalContext().getSessionMap().put("tipoVoltaje","2");
        	ctx.getExternalContext().getSessionMap().put("causaCatastrofe1",Causa.CODIGO_CATASTROFE);
            ctx.getExternalContext().getSessionMap().put("causaCatastrofe2",new Integer(0));
        }else if(this.tipoInterrupcion.equals(Interrupcion.INTERRUPCION_SECUNDARIA)){
        	ctx.getExternalContext().getSessionMap().put("tipoVoltaje","3");
        	ctx.getExternalContext().getSessionMap().put("causaCatastrofe1",Causa.CODIGO_CATASTROFE);
            ctx.getExternalContext().getSessionMap().put("causaCatastrofe2",new Integer(0));
        }else if(this.tipoInterrupcion.equals(Interrupcion.INTERRUPCION_CATASTROFE)){
        	ctx.getExternalContext().getSessionMap().put("tipoVoltaje","0");
        	ctx.getExternalContext().getSessionMap().put("causaCatastrofe1",new Integer(0));
            ctx.getExternalContext().getSessionMap().put("causaCatastrofe2",Causa.CODIGO_CATASTROFE);
        }else if(this.tipoInterrupcion.equals(Interrupcion.INTERRUPCION_DISTRIBUCION)){
        	ctx.getExternalContext().getSessionMap().put("tipoVoltaje","2,3");
        	ctx.getExternalContext().getSessionMap().put("causaCatastrofe1",Causa.CODIGO_CATASTROFE);
            ctx.getExternalContext().getSessionMap().put("causaCatastrofe2",new Integer(0));
        }
        
        
        utilizarDatosHistoricos = this.interrupcionBO.utilizarDatosHistoricos(this.fechaFinal);
        GregorianCalendar fecha = new GregorianCalendar();
        fecha.setTime(this.fechaFinal);
        
        int anoFechaFinal = fecha.get(GregorianCalendar.YEAR);
        int mesFechaFinal = fecha.get(GregorianCalendar.MONTH);
        
        Integer mesHistorico = null;
        Integer anoHistorico = null;
        if(utilizarDatosHistoricos){
            
            if(anoFechaFinal < 2007 || (anoFechaFinal == 2007 && mesFechaFinal < 9)){
                GregorianCalendar gc = new GregorianCalendar();
                anoHistorico = new Integer(gc.get(GregorianCalendar.YEAR));
                mesHistorico = new Integer(gc.get(GregorianCalendar.MONTH) + 1);
                if(mesHistorico.intValue() == 1){
                    mesHistorico = new Integer(12);
                    anoHistorico = new Integer(anoHistorico.intValue() - 1);
                }else{
                    mesHistorico = new Integer(mesHistorico.intValue() - 1);                    
                }
            }
            else{                
                anoHistorico = new Integer(anoFechaFinal);
                mesHistorico = new Integer(mesFechaFinal); 
            }
        }
        
        ctx.getExternalContext().getSessionMap().put("utilizarDatosHistoricos",Boolean.valueOf(utilizarDatosHistoricos));        
        ctx.getExternalContext().getSessionMap().put("anoHistorico",anoHistorico);
        ctx.getExternalContext().getSessionMap().put("mesHistorico",mesHistorico);  
        
        if(this.grupo){
	        if(this.items.size() == 0){
	            itemsSTR = this.seccion.toString();
	            cantidadItems = new Integer(1);
	        }
	        if(this.items.size() > 0){
	            itemsSTR = UtilidadesI.getListaItemsSeccion(this.items);
	            cantidadItems = new Integer(this.items.size());
	        }
            ctx.getExternalContext().getSessionMap().put("seccionFinal",new Long(0));  
        }else{
            itemsSTR = this.seccion.toString();
            cantidadItems = new Integer(1);
            if(this.rango){                
                ctx.getExternalContext().getSessionMap().put("seccionFinal",this.seccionFinal);  
                SeccionID seccionID = new SeccionID(this.subestacion, this.circuito, this.seccion);
                ctx.getExternalContext().getSessionMap().put("nombreSeccionInicial",this.seccionBO.buscar(seccionID).getNombreSeccion());
                seccionID = new SeccionID(this.subestacion, this.circuito, this.seccionFinal);
                ctx.getExternalContext().getSessionMap().put("nombreSeccionFinal",this.seccionBO.buscar(seccionID).getNombreSeccion());
            }
        }
        
        ctx.getExternalContext().getSessionMap().put("subestacion",this.subestacion);
        ctx.getExternalContext().getSessionMap().put("circuito",this.circuito);
        ctx.getExternalContext().getSessionMap().put("seccion",this.seccion);                
        ctx.getExternalContext().getSessionMap().put("itemsSTR",itemsSTR);
        ctx.getExternalContext().getSessionMap().put("cantidadItems",cantidadItems);        
                
    }
    
    /**
     * Retorna la descripción de un tipo de interrupción
     */
    private String getTipoInterrupcion(Integer tipoInterrupcion){
        
        switch(tipoInterrupcion.intValue()){
            case 0:return "TODOS";
            case 1:return "INTERRUPCIÓN DE TRANSPORTE";
            case 2:return "INTERRUPCIÓN PRIMARIA";
            case 3:return "INTERRUPCIÓN SECUNDARIA";
            case 4:return "INTERRUPCIÓN POR CATÁSTROFE";
            case 5:return "INTERRUPCIÓN POR DISTRIBUCIÓN";
            default:return "";
        }
    }
     
    /**
     * Metodo que obtiene los beans y invoca la generacion del reporte
     * @return success o error
     */
    @SuppressWarnings("unchecked")
    private String generarReporte(){  
        //pasarParametrosReporte();  
        String itemsSTR = "0";       
        Integer cantidadItems = new Integer(0);  
        boolean utilizarDatosHistoricos = false;
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
            
            parametrosReporte.put("codigoOficina",this.codigoOficina);
            parametrosReporte.put("nombreOficina",this.oficinaBO.buscar(this.codigoOficina).getNombreOficina());
            parametrosReporte.put("fechaInicio",sf.format(this.fechaInicio));
            parametrosReporte.put("fechaFinal",sf.format(this.fechaFinal));
          
            
            String tipoInterrupcionSTR = this.getTipoInterrupcion(this.tipoInterrupcion);
            System.out.println("tipoInterrupcion: " + this.tipoInterrupcion);
            System.out.println("tipoInterrupcionSTR: " + tipoInterrupcionSTR);
            parametrosReporte.put("tipoInterrupcionSTR",tipoInterrupcionSTR);
            
            if(this.tipoInterrupcion.equals(new Integer(0))){
                parametrosReporte.put("tipoVoltaje","1,2,3,4");
                parametrosReporte.put("causaCatastrofe1",new Integer(0));
                parametrosReporte.put("causaCatastrofe2",new Integer(0));
            }else if(this.tipoInterrupcion.equals(Interrupcion.INTERRUPCION_SUMINISTRO)){
                parametrosReporte.put("tipoVoltaje","1");
                parametrosReporte.put("causaCatastrofe1",Causa.CODIGO_CATASTROFE);
                parametrosReporte.put("causaCatastrofe2",new Integer(0));
            }else if(this.tipoInterrupcion.equals(Interrupcion.INTERRUPCION_PRIMARIA)){
                parametrosReporte.put("tipoVoltaje","2");
                parametrosReporte.put("causaCatastrofe1",Causa.CODIGO_CATASTROFE);
                parametrosReporte.put("causaCatastrofe2",new Integer(0));
            }else if(this.tipoInterrupcion.equals(Interrupcion.INTERRUPCION_SECUNDARIA)){
                parametrosReporte.put("tipoVoltaje","3");
                parametrosReporte.put("causaCatastrofe1",Causa.CODIGO_CATASTROFE);
                parametrosReporte.put("causaCatastrofe2",new Integer(0));
            }else if(this.tipoInterrupcion.equals(Interrupcion.INTERRUPCION_CATASTROFE)){
                parametrosReporte.put("tipoVoltaje","0");
                parametrosReporte.put("causaCatastrofe1",new Integer(0));
                parametrosReporte.put("causaCatastrofe2",Causa.CODIGO_CATASTROFE);
            }else if(this.tipoInterrupcion.equals(Interrupcion.INTERRUPCION_DISTRIBUCION)){
                parametrosReporte.put("tipoVoltaje","2,3");
                parametrosReporte.put("causaCatastrofe1",Causa.CODIGO_CATASTROFE);
                parametrosReporte.put("causaCatastrofe2",new Integer(0));
            }
            
            
            utilizarDatosHistoricos = this.interrupcionBO.utilizarDatosHistoricos(this.fechaFinal);
            GregorianCalendar fecha = new GregorianCalendar();
            fecha.setTime(this.fechaFinal);
            
            int anoFechaFinal = fecha.get(GregorianCalendar.YEAR);
            int mesFechaFinal = fecha.get(GregorianCalendar.MONTH);
            
            Integer mesHistorico = null;
            Integer anoHistorico = null;
            if(utilizarDatosHistoricos){
                
                if(anoFechaFinal < 2007 || (anoFechaFinal == 2007 && mesFechaFinal < 9)){
                    GregorianCalendar gc = new GregorianCalendar();
                    anoHistorico = new Integer(gc.get(GregorianCalendar.YEAR));
                    mesHistorico = new Integer(gc.get(GregorianCalendar.MONTH) + 1);
                    if(mesHistorico.intValue() == 1){
                        mesHistorico = new Integer(12);
                        anoHistorico = new Integer(anoHistorico.intValue() - 1);
                    }else{
                        mesHistorico = new Integer(mesHistorico.intValue() - 1);                    
                    }
                }
                else{                
                    anoHistorico = new Integer(anoFechaFinal);
                    mesHistorico = new Integer(mesFechaFinal); 
                }
            }
             
          
            if(this.grupo){
                if(this.items.size() == 0){
                    itemsSTR = this.seccion.toString();
                    cantidadItems = new Integer(1);
                }
                if(this.items.size() > 0){
                    itemsSTR = UtilidadesI.getListaItemsSeccion(this.items);
                    cantidadItems = new Integer(this.items.size());
                } 
                parametrosReporte.put("seccionFinal",new Long(0));
            }else{
                itemsSTR = this.seccion.toString();
                cantidadItems = new Integer(1);
                if(this.rango){   
                    parametrosReporte.put("seccionFinal",this.seccionFinal);
                    SeccionID seccionID = new SeccionID(this.subestacion, this.circuito, this.seccion);
                    parametrosReporte.put("nombreSeccionInicial",this.seccionBO.buscar(seccionID).getNombreSeccion());
                    seccionID = new SeccionID(this.subestacion, this.circuito, this.seccionFinal);
                    parametrosReporte.put("nombreSeccionFinal",this.seccionBO.buscar(seccionID).getNombreSeccion());
                }
            }
              
            parametrosReporte.put("subestacion",this.subestacion);
            parametrosReporte.put("circuito",this.circuito);
            parametrosReporte.put("seccionInicial",this.seccion);
            parametrosReporte.put("itemsSTR",itemsSTR);
            parametrosReporte.put("cantidadItems",cantidadItems);
            
            if(utilizarDatosHistoricos)
                nombreArchivo = "SigeItrRepRangoGrupoSeccionH";
            else
                nombreArchivo = "SigeItrRepRangoGrupoSeccionC";
            
            nombre = "RanGrupSecciones";
            Date fechaActual = new Date();
            SimpleDateFormat spf = new SimpleDateFormat();
            spf.applyPattern("dd-MM-yyyy");                                
            String fechaActualSTR = spf.format(fechaActual);
            nombre += fechaActualSTR;

            
            nombreArchivo += ".jasper";
            
            if(this.formato.equals(Integer.valueOf(1))){
                nombre +=".pdf";
            }else{
                nombre +=".xls";
            }   
            if (this.runReport(JasperPath + nombreArchivo, nombre,parametrosReporte,this.getConnection(),this.formato,UtilidadesFaces.getCurrentUserId())){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Reporte Ejecutado."));
                return  "success";      
                
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error Reporte."));
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
	 * @return the listaSeccionItems
	 */
	public Seccion[] getListaSeccionItems() {
		return listaSeccionItems;
	}

	/**
	 * @param listaSeccionItems the listaSeccionItems to set
	 */
	public void setListaSeccionItems(Seccion[] listaSeccionItems) {
		this.listaSeccionItems = listaSeccionItems;
	}

	/**
	 * @return the grupo
	 */
	public boolean isGrupo() {
		return grupo;
	}

	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(boolean grupo) {
		this.grupo = grupo;
	}

	/**
	 * @return the rango
	 */
	public boolean isRango() {
		return rango;
	}

	/**
	 * @param rango the rango to set
	 */
	public void setRango(boolean rango) {
		this.rango = rango;
	}

	/**
	 * @return the tipoInterrupcion
	 */
	public Integer getTipoInterrupcion() {
		return tipoInterrupcion;
	}

	/**
	 * @param tipoInterrupcion the tipoInterrupcion to set
	 */
	public void setTipoInterrupcion(Integer tipoInterrupcion) {
		this.tipoInterrupcion = tipoInterrupcion;
	}

	/**
	 * @return the seccionFinal
	 */
	public Long getSeccionFinal() {
		return seccionFinal;
	}

	/**
	 * @param seccionFinal the seccionFinal to set
	 */
	public void setSeccionFinal(Long seccionFinal) {
		this.seccionFinal = seccionFinal;
	}

    /**
     * Metodo modificador de interrupcionBO.
     * @param interrupcionBO El interrupcionBO a modificar.
     */
    public void setInterrupcionBO(InterrupcionBO interrupcionBO) {
        this.interrupcionBO = interrupcionBO;
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
