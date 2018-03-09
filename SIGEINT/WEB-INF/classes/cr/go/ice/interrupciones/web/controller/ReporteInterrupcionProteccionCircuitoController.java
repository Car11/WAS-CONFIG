package cr.go.ice.interrupciones.web.controller;

import java.io.IOException;
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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.vvs.jsf.AbstractFacesController;
import com.vvs.utilidades.Fechas;
import com.vvs.utilidades.Utilidades;

import cr.go.ice.interrupciones.BO.*;
import cr.go.ice.interrupciones.domain.*;
import cr.go.ice.interrupciones.servlets.ServletReporte;
import cr.go.ice.interrupciones.utils.UtilidadesI;
import cr.go.ice.interrupciones.utils.UtilidadesFaces;
import cr.go.ice.interrupciones.web.Recurso;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ReporteInterrupcionProteccionCircuitoController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ReporteInterrupcionProteccionCircuitoController.java</code>.</p>
 * <p>Fecha creación: 27/08/2008</p>
 * <p>Ultima actualización: 27/08/2008</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ReporteInterrupcionProteccionCircuitoController extends AbstractFacesController{

    private Date fechaInicio;
    private Date fechaFinal;
    
    private boolean grupoCircuitos;
    private boolean grupoProtecciones;
        
    private Integer subestacion;
    private Circuito circuitoObj;        
    private Long seccion;
    private Proteccion proteccion;
    private Integer voltaje;
    
    private Integer formato;
    
    private SubEstacionBO subEstacionBO;
    private CircuitoBO circuitoBO;
    private ProteccionBO proteccionBO;
          
    private Circuito [] listaCircuitoItems;
    private Proteccion [] listaProteccionItems;    
    
    private List itemsCircuitos;
    private List itemsProtecciones;
    private  static final String JasperPath = "/jasperReports/";
    
    
    /**
     * Constructor por defecto
     */    
    public ReporteInterrupcionProteccionCircuitoController(){
        reiniciarCampos();
        
    }
    
    /**
     * Metodo que reincia los atributos de la clase
     */
    private void reiniciarCampos(){    
        this.fechaInicio = new Date();
        this.fechaFinal = new Date();     
        grupoCircuitos = false;
        grupoProtecciones = false;
        
        this.seccion = new Long(0);
        this.proteccion = new Proteccion();
        this.proteccion.setCodigoProteccion(new Integer(0));
        this.proteccion.setNombreProteccion("a");        
        this.formato = UtilidadesFaces.FORMATO_PDF;
        
        this.circuitoObj = new Circuito();
        this.circuitoObj.setCircuitoID(new CircuitoID());
        this.circuitoObj.setNombreCircuito("a");
        this.circuitoObj.getCircuitoID().setCircuito(new Integer(0));
        this.circuitoObj.getCircuitoID().setSubEstacion(new SubEstacion());
        this.circuitoObj.getCircuitoID().getSubEstacion().setCodigoSubEstacion(new Integer(0));
        
        this.subestacion = new Integer(0);
        this.voltaje = Interrupcion.INTERRUPCION_PRIMARIA;
        this.itemsCircuitos = new ArrayList();
        this.itemsProtecciones = new ArrayList();
        

        
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
     * Comment for listenerCheck
     * @param v
     * @return "listener" a grupo
     */
    public String listenerCheckCircuitos(){                 
        this.itemsCircuitos = new ArrayList();
        return "listener";  
    }   
    
    /**
     * Comment for listenerSubestacion
     * @param v
     * @return "listener" a subestacion
     */
    public String listenerSubestacion(){                 
        this.itemsCircuitos = new ArrayList();
        return "listener";  
    }       
    
    /**
     * Comment for listenerVoltaje
     * @param v
     * @return "listener" a voltaje
     */
    public String listenerVoltaje(){                 
        this.itemsProtecciones = new ArrayList();
        return "listener";  
    }       
    
    /**
     * Comment for listenerCheck
     * @param v
     * @return "listener" a grupo
     */
    public String listenerCheckProtecciones(){                 
        this.itemsProtecciones = new ArrayList();
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
     * Metodo accesor de subEstaciones.
     * @return Retorna el subEstaciones.
     */
    public List getSubEstaciones() {
        List subEstaciones = this.subEstacionBO.getSubEstaciones();
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
     * Metodo accesor de voltajes.
     * @return Retorna los voltajes.
     */
    public List getVoltajes(){
        List voltajes = new ArrayList();        
        voltajes.add(new SelectItem(Interrupcion.INTERRUPCION_PRIMARIA, "Primario"));
        voltajes.add(new SelectItem(Interrupcion.INTERRUPCION_SECUNDARIA, "Secundario"));
        return voltajes;
    }
    
    /**
     * Metodo accesor de protecciones.
     * @return Retorna las protecciones.
     */
    public List getProtecciones(){
        List protecciones = this.proteccionBO.getProtecciones(this.voltaje);
        List items = new ArrayList();
        if(protecciones != null && !protecciones.isEmpty()){
            for(int i = 0; i < protecciones.size(); i++){
            	Proteccion proteccion = (Proteccion) protecciones.get(i);
                SelectItem item = new SelectItem();
                item.setValue(proteccion);
                item.setLabel(proteccion.getCodigoProteccion() + " - " + proteccion.getNombreProteccion());
                items.add(item);
            }

        }
        return items;
    }    
    
     /**
     * Metodo accesor de circuitos.
     * @return Retorna el circuitos.
     */
    public List getCircuitos() {
        List circuitos = this.circuitoBO.getCircuitos(this.subestacion);
        List items = new ArrayList();
        Circuito cir = new Circuito();
        cir.setCircuitoID(new CircuitoID());
        cir.setNombreCircuito("a");
        cir.getCircuitoID().setCircuito(new Integer(0));
        cir.getCircuitoID().setSubEstacion(new SubEstacion());
        cir.getCircuitoID().getSubEstacion().setCodigoSubEstacion(new Integer(0));
        
        items.add(new SelectItem(cir, "Todos"));
        if(circuitos != null && !circuitos.isEmpty()){
            for(int i = 0; i < circuitos.size(); i++){
                Circuito circuito = (Circuito) circuitos.get(i);
                SelectItem item = new SelectItem();
                item.setValue(circuito);
                item.setLabel(circuito.getCircuitoID().getCircuito() + " - " + circuito.getNombreCircuito());
                items.add(item);
            }
        }
        return items;
    }
    
    private boolean validarParametros(){
        boolean correcto = true;
       if(this.fechaInicio == null){
    	   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar una fecha de inicio."));
            correcto = false;
        }
        if(this.fechaFinal == null){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar una fecha fin."));
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
        if(this.subestacion == null || this.subestacion.intValue() <= 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La subestación es requerida."));
            correcto=false;
        }          
        return correcto;
    }
    
    
    /**
     * Realiza las validaciones de los parámetros de entrada dados por el usuario y genera el reporte de protecciones
     * por circuito a nivel de interrupciones
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
    
    /**
     * Metodo que obtiene los beans y invoca la generacion del reporte
     * @return success o error
     */
    @SuppressWarnings("unchecked")
    private String generarReporte(){  
       // pasarParametrosReporte();    
        String  circuitosSTR = "0";
        Integer cantidadCircuitos = new Integer(0);
        String  proteccionesSTR = "0";
        Integer cantidadProtecciones = new Integer(0); 
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
        
            parametrosReporte.put("fechaInicio",sf.format(this.fechaInicio));
            parametrosReporte.put("fechaFinal",sf.format(this.fechaFinal));
            parametrosReporte.put("formato",this.formato);
            
            if(this.grupoCircuitos){
                if(this.itemsCircuitos.size() == 0 && this.getCircuitoObj().getCircuitoID().getCircuito().intValue() > 0){              
                    circuitosSTR = this.getCircuitoObj().getCircuitoID().getCircuito().toString();
                    cantidadCircuitos = new Integer(1);    
                }
                if(this.itemsCircuitos.size() > 0){        
                    circuitosSTR = UtilidadesI.getListaItemsCircuito(this.itemsCircuitos);
                    cantidadCircuitos = new Integer(this.itemsCircuitos.size());      
                }
            }else{             
                circuitosSTR = this.getCircuitoObj().getCircuitoID().getCircuito().toString();
                cantidadCircuitos = new Integer(1);    
            }
            
            if(this.grupoProtecciones){
                if(this.itemsProtecciones.size() == 0){
                    proteccionesSTR = this.proteccion.getCodigoProteccion().toString();
                    cantidadProtecciones = new Integer(1);                 
                }
                if(this.itemsProtecciones.size() > 0){
                    proteccionesSTR = UtilidadesI.getListaItemsProteccion(this.itemsProtecciones);
                    cantidadProtecciones = new Integer(this.itemsProtecciones.size());     
                }
            }else{
                proteccionesSTR = this.proteccion.getCodigoProteccion().toString();
                cantidadProtecciones = new Integer(1);     
            }
            
            parametrosReporte.put("subestacion",this.subestacion);
            parametrosReporte.put("circuitosSTR",circuitosSTR);
            parametrosReporte.put("cantidadCircuitos",cantidadCircuitos);
            parametrosReporte.put("proteccionesSTR",proteccionesSTR);
            parametrosReporte.put("cantidadProtecciones",cantidadProtecciones);
            parametrosReporte.put("tipoVoltaje",this.voltaje);
            if(this.voltaje.equals(Interrupcion.INTERRUPCION_PRIMARIA)){
                parametrosReporte.put("codigoVoltaje","20, 30, 40");
            }else if(this.voltaje.equals(Interrupcion.INTERRUPCION_SECUNDARIA)){
                parametrosReporte.put("codigoVoltaje","50, 60");
            }  
            nombreArchivo = "SigeItrRepProtecciones";              
            
            nombre = "Protecciones";
            Date fechaActual = new Date();
            SimpleDateFormat spf = new SimpleDateFormat();
            spf.applyPattern("dd-MM-yyyy");                                
            String fechaActualSTR = spf.format(fechaActual);
            nombre += fechaActualSTR;

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
    
    private void pasarParametrosReporte(){
                
        String  circuitosSTR = "0";
        Integer cantidadCircuitos = new Integer(0);
        String  proteccionesSTR = "0";
        Integer cantidadProtecciones = new Integer(0);            
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteProteccionesCircuito));
        SimpleDateFormat sf = new SimpleDateFormat();
        sf.applyPattern("dd/MM/yyyy");   
        
        ctx.getExternalContext().getSessionMap().put("fechaInicio",sf.format(this.fechaInicio));
        ctx.getExternalContext().getSessionMap().put("fechaFinal",sf.format(this.fechaFinal));
        ctx.getExternalContext().getSessionMap().put("formato",this.formato);
        
        if(this.grupoCircuitos){
            if(this.itemsCircuitos.size() == 0 && this.getCircuitoObj().getCircuitoID().getCircuito().intValue() > 0){              
                circuitosSTR = this.getCircuitoObj().getCircuitoID().getCircuito().toString();
                cantidadCircuitos = new Integer(1);    
            }
            if(this.itemsCircuitos.size() > 0){        
                circuitosSTR = UtilidadesI.getListaItemsCircuito(this.itemsCircuitos);
                cantidadCircuitos = new Integer(this.itemsCircuitos.size());      
            }
        }else{             
            circuitosSTR = this.getCircuitoObj().getCircuitoID().getCircuito().toString();
            cantidadCircuitos = new Integer(1);    
        }
        
        if(this.grupoProtecciones){
            if(this.itemsProtecciones.size() == 0){
            	proteccionesSTR = this.proteccion.getCodigoProteccion().toString();
            	cantidadProtecciones = new Integer(1);                 
            }
            if(this.itemsProtecciones.size() > 0){
            	proteccionesSTR = UtilidadesI.getListaItemsProteccion(this.itemsProtecciones);
            	cantidadProtecciones = new Integer(this.itemsProtecciones.size());     
            }
        }else{
        	proteccionesSTR = this.proteccion.getCodigoProteccion().toString();
        	cantidadProtecciones = new Integer(1);     
        }
                
        ctx.getExternalContext().getSessionMap().put("subestacion",this.subestacion);
        ctx.getExternalContext().getSessionMap().put("circuitosSTR",circuitosSTR);
        ctx.getExternalContext().getSessionMap().put("cantidadCircuitos",cantidadCircuitos);
        ctx.getExternalContext().getSessionMap().put("proteccionesSTR",proteccionesSTR);
        ctx.getExternalContext().getSessionMap().put("cantidadProtecciones",cantidadProtecciones);
        
        ctx.getExternalContext().getSessionMap().put("tipoVoltaje",this.voltaje);
        if(this.voltaje.equals(Interrupcion.INTERRUPCION_PRIMARIA)){
        	ctx.getExternalContext().getSessionMap().put("codigoVoltaje","20, 30, 40");
        }else if(this.voltaje.equals(Interrupcion.INTERRUPCION_SECUNDARIA)){
        	ctx.getExternalContext().getSessionMap().put("codigoVoltaje","50, 60");
        }                                                                    

    }        

    
    /**
     * Metodo que retorna la lista de items seleccionados
     * @return lista de items
     */     
    public List getItemsCircuitosSI(){
        List itemsSI = new ArrayList();
        if(this.grupoCircuitos){
            for(int i = 0; i < this.itemsCircuitos.size(); i++){ 
                SelectItem item = new SelectItem();
                Circuito circuito = (Circuito)this.itemsCircuitos.get(i);
                item.setValue(circuito);            
                item.setLabel(circuito.getCircuitoID().getSubEstacion().getCodigoSubEstacion() + "-" +circuito.getCircuitoID().getCircuito() + " - " + circuito.getNombreCircuito());           
                itemsSI.add(item);
            }
        }
        return itemsSI;    
    }
    
    /**
     * Metodo que retorna la lista de items seleccionados
     * @return lista de items
     */     
    public List getItemsProteccionesSI(){
        List itemsSI = new ArrayList();
        if(this.grupoProtecciones){
            for(int i = 0; i < this.itemsProtecciones.size(); i++){
                SelectItem item = new SelectItem();
                Proteccion proteccion = (Proteccion)this.itemsProtecciones.get(i);
                item.setValue(proteccion);
                item.setLabel(proteccion.getCodigoProteccion() + "-" + proteccion.getNombreProteccion());           
                itemsSI.add(item);
            }
        }
        return itemsSI;
    }

    
    /**
     * Agrega un item a la lista de protecciones
     * @return success
     */
    public String agregarItemProteccion(){
        if(this.grupoProtecciones){      
            if(this.proteccion.getCodigoProteccion().intValue() > 0){
                //Proteccion proteccion = this.proteccionBO.buscar(this.proteccion);
                if(this.itemsProtecciones.contains(proteccion) == false)
                    this.itemsProtecciones.add(proteccion); 
            }    
        }        

        return "success";
    }
    
    /**
     * Agrega un item a la lista de circuitos
     * @return success
     */
    public String agregarItemCircuito(){
        if(this.grupoCircuitos){      
            if(this.getCircuitoObj().getCircuitoID().getCircuito().intValue() > 0){
                Circuito circuito = this.circuitoBO.buscar(this.getCircuitoObj().getCircuitoID().getSubEstacion().getCodigoSubEstacion(), this.getCircuitoObj().getCircuitoID().getCircuito());
                if(this.itemsCircuitos.contains(circuito) == false)
                    this.itemsCircuitos.add(circuito); 
            }    
        }
        return "success";
    }             
    
    /**
     * Elimina un item de la lista de circuitos
     * @return success
     */
    public String eliminarItemCircuito(){
        for(int i = 0; i < this.listaCircuitoItems.length; i++){
            Circuito seleccionado = (Circuito)this.listaCircuitoItems[i];
            for(int j = 0; j < this.itemsCircuitos.size(); j++){
                Circuito circuito = (Circuito)this.itemsCircuitos.get(j);
                if(circuito.equals(seleccionado)){
                    this.itemsCircuitos.remove(j);
                    j = this.itemsCircuitos.size() + 1;
                }
            }
        }  
        return "success";
    }    
    
    /**
     * Elimina un item de la lista de protecciones
     * @return success
     */
    public String eliminarItemProteccion(){
        for(int i = 0; i < this.listaProteccionItems.length; i++){
            Proteccion seleccionado = (Proteccion)this.listaProteccionItems[i];
            for(int j = 0; j < this.itemsProtecciones.size(); j++){
            	Proteccion proteccion = (Proteccion)this.itemsProtecciones.get(j);
                if(proteccion.equals(seleccionado)){
                    this.itemsProtecciones.remove(j);
                    j = this.itemsProtecciones.size() + 1;
                }
            }
        } 
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
     * Metodo accesor de listaCircuitoItems.
     * @return Retorna el listaCircuitoItems.
     */
    public Circuito[] getListaCircuitoItems() {
        return this.listaCircuitoItems;
    }
    /**
     * Metodo modificador de listaCircuitoItems.
     * @param listaCircuitoItems El listaCircuitoItems a modificar.
     */
    public void setListaCircuitoItems(Circuito[] listaCircuitoItems) {
        this.listaCircuitoItems = listaCircuitoItems;
    } 

    /**
     * Comentario para getCircuitoObj
     * @return
     */
    public Circuito getCircuitoObj() {
        return this.circuitoObj;
    }

    /**
     * Comentario para setCircuitoObj
     * @param circuitoObj
     */
    public void setCircuitoObj(Circuito circuitoObj) {
        this.circuitoObj = circuitoObj;
    }

	/**
	 * @return the fechaFinal
	 */
	public Date getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the grupoCircuitos
	 */
	public boolean isGrupoCircuitos() {
		return grupoCircuitos;
	}

	/**
	 * @param grupoCircuitos the grupoCircuitos to set
	 */
	public void setGrupoCircuitos(boolean grupoCircuitos) {
		this.grupoCircuitos = grupoCircuitos;
	}

	/**
	 * @return the grupoProtecciones
	 */
	public boolean isGrupoProtecciones() {
		return grupoProtecciones;
	}

	/**
	 * @param grupoProtecciones the grupoProtecciones to set
	 */
	public void setGrupoProtecciones(boolean grupoProtecciones) {
		this.grupoProtecciones = grupoProtecciones;
	}

	/**
	 * @return the subestacion
	 */
	public Integer getSubestacion() {
		return subestacion;
	}

	/**
	 * @param subestacion the subestacion to set
	 */
	public void setSubestacion(Integer subestacion) {
		this.subestacion = subestacion;
	}


	/**
	 * @return the voltaje
	 */
	public Integer getVoltaje() {
		return voltaje;
	}

	/**
	 * @param voltaje the voltaje to set
	 */
	public void setVoltaje(Integer voltaje) {
		this.voltaje = voltaje;
	}

	/**
	 * @return the proteccion
	 */
	public Proteccion getProteccion() {
		return proteccion;
	}

	/**
	 * @param proteccion the proteccion to set
	 */
	public void setProteccion(Proteccion proteccion) {
		this.proteccion = proteccion;
	}

	/**
	 * @param proteccionBO the proteccionBO to set
	 */
	public void setProteccionBO(ProteccionBO proteccionBO) {
		this.proteccionBO = proteccionBO;
	}

	/**
	 * @param subEstacionBO the subEstacionBO to set
	 */
	public void setSubEstacionBO(SubEstacionBO subEstacionBO) {
		this.subEstacionBO = subEstacionBO;
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
	 * @return the ListaProteccionItems
	 */
	public Proteccion[] getListaProteccionItems() {
		return listaProteccionItems;
	}

	/**
	 * @param listaProteccionItems the listaProteccionItems to set
	 */	
	public void setListaProteccionItems(Proteccion[] listaProteccionItems) {
		this.listaProteccionItems = listaProteccionItems;
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
