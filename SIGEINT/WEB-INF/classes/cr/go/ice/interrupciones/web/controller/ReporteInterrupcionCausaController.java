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
import com.vvs.utilidades.Fechas;
import com.vvs.utilidades.Utilidades;

import cr.go.ice.cia.dominio.UsuarioCia;
import cr.go.ice.interrupciones.BO.InterrupcionBO;
import cr.go.ice.interrupciones.BO.SeccionBO;
import cr.go.ice.interrupciones.BO.CircuitoBO;
import cr.go.ice.interrupciones.BO.RegionBO;
import cr.go.ice.interrupciones.BO.SubEstacionBO;
import cr.go.ice.interrupciones.BO.SubRegionBO;
import cr.go.ice.interrupciones.BO.UsuarioOficinaBO;
import cr.go.ice.interrupciones.domain.Interrupcion;
import cr.go.ice.interrupciones.domain.SubRegionID;
import cr.go.ice.interrupciones.domain.UsuarioOficina;
import cr.go.ice.interrupciones.domain.SeccionID;
import cr.go.ice.interrupciones.domain.Seccion;
import cr.go.ice.interrupciones.domain.Circuito;
import cr.go.ice.interrupciones.domain.Region;
import cr.go.ice.interrupciones.domain.SubEstacion;
import cr.go.ice.interrupciones.domain.SubRegion;
import cr.go.ice.interrupciones.servlets.ServletReporte;
import cr.go.ice.interrupciones.utils.UtilidadesI;
import cr.go.ice.interrupciones.utils.UtilidadesFaces;

import cr.go.ice.interrupciones.BO.CausaBO;
import cr.go.ice.interrupciones.BO.EmpleadoBO;
import cr.go.ice.interrupciones.BO.OficinaBO;
import cr.go.ice.interrupciones.domain.Causa;
import cr.go.ice.interrupciones.domain.Empleado;
import cr.go.ice.interrupciones.domain.Oficina;
import cr.go.ice.interrupciones.utils.Usuario;
import cr.go.ice.interrupciones.web.Recurso;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ReporteInterrupcionCausaController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ReporteInterrupcionCausaController.java</code>.</p>
 * <p>Fecha creación: 19/11/2007</p>
 * <p>Ultima actualización: 19/11/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ReporteInterrupcionCausaController extends AbstractFacesController{

    private Integer codigoOficina;
    private Date fechaInicio;
    private Date fechaFinal;
    private Integer tipoInterrupcion;
    private Integer codigoCausa;
    
    private String nivelRed;
    private boolean grupo;
    
    private Integer region;
    private Integer subregion;
    private Integer subestacion;
    private Integer circuito;

    private Long seccion;
   
    
    private EmpleadoBO empleadoBO;
    private OficinaBO oficinaBO;
    private CausaBO causaBO;
    
    private RegionBO regionBO;
    private SubRegionBO subRegionBO;
    private SubEstacionBO subEstacionBO;
    private CircuitoBO circuitoBO;
    private SeccionBO seccionBO;
    private InterrupcionBO interrupcionBO;
    private UsuarioOficinaBO usuarioOficinaBO;
    
    private Region [] listaRegionItems;
    private SubRegion [] listaSubregionItems;
    private SubEstacion [] listaSubestacionItems;
    private Circuito [] listaCircuitoItems;
    private Seccion [] listaSeccionItems;
    
    private String[] grafico;
    private List items;
    
    private Integer formato;
    
    private String error;
    private  static final String JasperPath = "/jasperReports/";
    private Long cedula;
    
    /**
     * Constructor por defecto
     */    
    public ReporteInterrupcionCausaController(){
        reiniciarCampos();
    }
    
    public String getInit(){
    	FacesContext context = FacesContext.getCurrentInstance();
     	boolean userClor = true;
     	Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
         if(limpiar != null)
             reiniciarCampos();
         
         if(userClor && limpiar != null){            

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
         return "success";
    }
    
    /**
     * Metodo que reincia los atributos de la clase
     */
    private void reiniciarCampos(){
        this.fechaInicio = new Date();
        this.fechaFinal = new Date();        
        this.codigoOficina = new Integer(0);
        this.codigoCausa = new Integer(0);
        this.tipoInterrupcion = new Integer(0);
        grupo = false;
        
        this.region = new Integer(0);
        this.subregion = new Integer(0);
        this.subestacion = new Integer(0);
        this.circuito = new Integer(0);
        this.seccion = new Long(0);
        this.grafico= null;
        
        this.items = new ArrayList();
        
        this.nivelRed = "";
        this.error = "";
        
        this.formato = UtilidadesFaces.FORMATO_PDF;
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
     * Retorna una lista de select item de las causas existentes
     * @return Lista de causas
     */    
    public List getListaCausas(){ 
        Vector vcausas = new Vector();
        List lcausas = causaBO.getCausas();
        
        vcausas.add(new SelectItem(new Integer(0), "Todas"));
        
        for (int i = 0; i < lcausas.size(); i++) {
            Causa xcausa = (Causa) lcausas.get(i);
            vcausas.add(new SelectItem(xcausa.getCodigoCausa(), xcausa.getCodigoCausa() + " - " + xcausa.getNombreCausa()));
        }       
        
        return vcausas;       
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
        this.error = "";

        this.region = new Integer(0);
    	this.subregion = new Integer(0);
    	this.subestacion = new Integer(0);
    	this.circuito = new Integer(0);
    	this.seccion = new Long(0);

        this.items = new ArrayList();
 
        return "success";
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
     * Comment for listenerRegion
     * @param v
     * @return "listener" a Region
     */
    public String listenerRegion(){
        if(nivelRed.equals("subregion") || nivelRed.equals("subestacion") || nivelRed.equals("circuito") || nivelRed.equals("seccion"))
            this.items = new ArrayList();
        return "listener";  
    }
    
    /**
     * Comment for listenerSubregion
     * @param v
     * @return "listener" a SubRegion
     */
    public String listenerSubregion(){
        if(nivelRed.equals("subestacion") || nivelRed.equals("circuito") || nivelRed.equals("seccion"))
            this.items = new ArrayList();
        return "listener";  
    }    
    
    /**
     * Comment for listenerSubestacion
     * @param v
     * @return "listener" a SubEstacion
     */
    public String listenerSubestacion(){
        if(nivelRed.equals("circuito") || nivelRed.equals("seccion"))
            this.items = new ArrayList();
        return "listener";  
    }   
    
    /**
     * Comment for listenerCircuito
     * @param v
     * @return "listener" a Circuito
     */
    public String listenerCircuito(){
        if(nivelRed.equals("seccion"))
            this.items = new ArrayList();
        return "listener";  
    }     
    
    /**
     * Comment for listenerOficina
     * @param v
     * @return "listener" a Oficina
     */
    public String listenerOficina(){
        return "listener";  
    }       
    
       /**
     * Metodo accesor de circuitos.
     * @return Retorna el circuitos.
     */
    public List getCircuitos() {
        this.error = "";      
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
        this.error = "";        
        List regiones = null;
        if(this.codigoOficina.intValue() == 0)
        	regiones = this.regionBO.getRegiones();
        else
        	regiones = this.regionBO.getRegionesPorOficina(this.codigoOficina);
        List items = new ArrayList();
       // items = new ArrayList();
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
        this.error = "";    
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
        this.error = "";   
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
        this.error = "";             
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
    

    /**
     * Método getGraficosSI
     * @return retorna los tipos de graficos
     */
    public List getGraficosSI() {     
        List items = new ArrayList();
        items.add(new SelectItem("DPIR", "DPIR"));
        items.add(new SelectItem("Cantidad", "Cantidad"));
        items.add(new SelectItem("Comparativo", "Comparativo"));
        return items;
    }

    /**
     * Método getFormatosSI
     * @return retorna los tipos de formatos
     */
    public List getFormatosSI() {     
        List items = new ArrayList();
        items.add(new SelectItem(new Integer(1), "PDF"));
        items.add(new SelectItem(new Integer(0), "Excel"));
        return items;
    }
    
    private boolean validarParametros(){
        boolean correcto = true;
        FacesContext context = FacesContext.getCurrentInstance();
        if(this.fechaInicio == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe sigitar una fecha de inicio"));
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
        if(this.codigoOficina.intValue() == 0 && this.nivelRed.equals("region") == false && this.nivelRed.equals("subregion") == false){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La opción de todas para la oficina sólo es permitida cuando el nivel de red es región o subregión."));
            
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
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de fin debe ser mayor o igual a la fecha de inicio."));
            correcto=false;
        }
        
        if(this.tipoInterrupcion.equals(Interrupcion.INTERRUPCION_CATASTROFE) == true && this.codigoCausa.equals(Causa.CODIGO_CATASTROFE) == false){
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El tipo de interrupción con voltaje 4 solo puede seleccionar la causa 417."));
            
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
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar la subregión."));
                correcto=false;
            }              
            if(this.subestacion.intValue() <= 0){
                FacesMessage msg = new FacesMessage();
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                msg.setDetail("Debe seleccionar la subestación");            
                context.addMessage("form1:cboSubestacion", msg);
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
        
        return correcto;
    }
    
    /**
     * Realiza las validaciones de los parámetros de entrada dados por el usuario y genera el reporte de interrupciones
     * por causa
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
        
        String itemsSTR = "0";
        String voltajesSTR = "0";
        Integer cantidadItems = new Integer(0);
        Integer cantidadVoltajes = new Integer(0);
        //quitar, prueba
       // cantidadItems = 2;
        //itemsSTR = "1";
        //this.region = 1;
        boolean utilizarDatosHistoricos = false;

        
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reportePorCausa));
        ctx.getExternalContext().getSessionMap().put("nivelRedActual",this.nivelRed);
        
        if(this.nivelRed.equals("region")){
            //this.items = getRegiones();
            if(this.items.size() == 0 && this.region.intValue() > 0){
                itemsSTR = this.region.toString();
                cantidadItems = new Integer(1);
            }
            if(this.items.size() > 0){
                itemsSTR = UtilidadesI.getListaItemsRegion(this.items);
                cantidadItems = new Integer(this.items.size());
            }
        }
        
        if(this.nivelRed.equals("subregion")){
            if(this.items.size() == 0 && this.subregion.intValue() > 0){
                itemsSTR = this.subregion.toString();
                cantidadItems = new Integer(1);
            }
            if(this.items.size() > 0){
                itemsSTR = UtilidadesI.getListaItemsSubregion(this.items);
                cantidadItems = new Integer(this.items.size());
            }
            ctx.getExternalContext().getSessionMap().put("region",this.region);            
        }  
        
        if(this.nivelRed.equals("subestacion")){
            if(this.items.size() == 0 && this.subestacion.intValue() > 0){
                itemsSTR = this.subestacion.toString();
                cantidadItems = new Integer(1);
            }
            if(this.items.size() > 0){
                itemsSTR = UtilidadesI.getListaItemsSubestacion(this.items);
                cantidadItems = new Integer(this.items.size());
            }
            ctx.getExternalContext().getSessionMap().put("region",this.region);
            ctx.getExternalContext().getSessionMap().put("subregion",this.subregion);
        } 
        
        if(this.nivelRed.equals("circuito")){
            if(this.items.size() == 0 && this.circuito.intValue() > 0){
                itemsSTR = this.circuito.toString();
                cantidadItems = new Integer(1);
            }
            if(this.items.size() > 0){
                itemsSTR = UtilidadesI.getListaItemsCircuito(this.items);
                cantidadItems = new Integer(this.items.size());
            }
            ctx.getExternalContext().getSessionMap().put("region",this.region);
            ctx.getExternalContext().getSessionMap().put("subregion",this.subregion);
            ctx.getExternalContext().getSessionMap().put("subestacion",this.subestacion);
        }  
        
        if(this.nivelRed.equals("seccion")){
            if(this.items.size() == 0 && this.seccion.longValue() > 0){
                itemsSTR = this.seccion.toString();
                cantidadItems = new Integer(1);
            }
            if(this.items.size() > 0){
                itemsSTR = UtilidadesI.getListaItemsSeccion(this.items);
                cantidadItems = new Integer(this.items.size());
            }
            ctx.getExternalContext().getSessionMap().put("region",this.region);
            ctx.getExternalContext().getSessionMap().put("subregion",this.subregion);
            ctx.getExternalContext().getSessionMap().put("subestacion",this.subestacion);
            ctx.getExternalContext().getSessionMap().put("circuito",this.circuito);
        } 
        
        List tiposVoltaje =  this.interrupcionBO.getTiposVoltajeInterrupcionCausa(this.tipoInterrupcion);        
        
        cantidadVoltajes = new Integer(tiposVoltaje.size()); 
        if(tiposVoltaje.size() > 0)
            voltajesSTR = UtilidadesI.getListaStr(tiposVoltaje);
        
        utilizarDatosHistoricos = this.interrupcionBO.utilizarDatosHistoricos(this.fechaFinal);
        ctx.getExternalContext().getSessionMap().put("utilizarDatosHistoricos",Boolean.valueOf(utilizarDatosHistoricos));
        
        String tipoInterrupcion = UtilidadesFaces.getTipoInterrupcion(this.tipoInterrupcion);
        tipoInterrupcion = (tipoInterrupcion == null) ? "Todos los tipos de interrupción" : tipoInterrupcion;
        String nombreOficina = (this.codigoOficina.intValue() == 0) ? "Todas las oficinas" : this.oficinaBO.buscar(this.codigoOficina).getNombreOficina();
        
        SimpleDateFormat sf = new SimpleDateFormat();
        sf.applyPattern("dd/MM/yyyy");

        ctx.getExternalContext().getSessionMap().put("tipoInterrupcion",tipoInterrupcion);
        ctx.getExternalContext().getSessionMap().put("fechaInicio",sf.format(this.fechaInicio));
        ctx.getExternalContext().getSessionMap().put("fechaFinal",sf.format(this.fechaFinal));
        ctx.getExternalContext().getSessionMap().put("codigoOficina",this.codigoOficina);
        ctx.getExternalContext().getSessionMap().put("nombreOficina",nombreOficina);
        
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(this.fechaFinal);        
        Integer anoFechaFinal = new Integer (gc.get(GregorianCalendar.YEAR));
        Integer mesFechaFinal = new Integer (gc.get(GregorianCalendar.MONTH) + 1);
        
        
        ctx.getExternalContext().getSessionMap().put("anoFechaFinal",anoFechaFinal);
        ctx.getExternalContext().getSessionMap().put("mesFechaFinal",mesFechaFinal);
        
        ctx.getExternalContext().getSessionMap().put("itemsSTR",itemsSTR);
        ctx.getExternalContext().getSessionMap().put("voltajesSTR",voltajesSTR);
        ctx.getExternalContext().getSessionMap().put("codigoCausa",this.codigoCausa);
        ctx.getExternalContext().getSessionMap().put("cantidadVoltajes",cantidadVoltajes);
        if(cantidadVoltajes.intValue() > 0)
            ctx.getExternalContext().getSessionMap().put("codigoCausaCatastrofe",Causa.CODIGO_CATASTROFE);
        else
            ctx.getExternalContext().getSessionMap().put("codigoCausaCatastrofe",new Integer(0));
        ctx.getExternalContext().getSessionMap().put("cantidadItems",cantidadItems);
        
        ctx.getExternalContext().getSessionMap().put("DPIR",Boolean.valueOf(false));
        ctx.getExternalContext().getSessionMap().put("Cantidad",Boolean.valueOf(false));
        ctx.getExternalContext().getSessionMap().put("Comparativo",Boolean.valueOf(false));
        for(int j=0; j < this.grafico.length; j++){
                ctx.getExternalContext().getSessionMap().put(this.grafico[j],Boolean.valueOf(true));
        }
    }
     
    /**
     * Método accesor del atributo items.
     * @return Retorna el atributo items.
     */
    public List getItems() {
        return this.items;
    }

    /**
     * Método modificador del atributo items.
     * @param items El dato para modificar el atributo items.
     */
    public void setItems(List items) {
        this.items = items;
    }

    /**
     * Metodo que obtiene los beans y invoca la generacion del reporte
     * @return success o error
     */
    @SuppressWarnings("unchecked")
    private String generarReporte(){  
        this.error = "";
        //pasarParametrosReporte(); 
        String itemsSTR = "0";
        String voltajesSTR = "0";
        Integer cantidadItems = new Integer(0);
        Integer cantidadVoltajes = new Integer(0);
        boolean utilizarDatosHistoricos = false;
       String ServletJasperPath = "/jasperReports/";
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
   

            parametrosReporte.put("SUBREPORT_DIR",ServletJasperPath);
            if(this.nivelRed.equals("region")){
                if(this.items.size() == 0 && this.region.intValue() > 0){
                    itemsSTR = this.region.toString();
                    cantidadItems = new Integer(1);
                }
                if(this.items.size() > 0){
                    itemsSTR = UtilidadesI.getListaItemsRegion(this.items);
                    cantidadItems = new Integer(this.items.size());
                }
            }
            
            if(this.nivelRed.equals("subregion")){
                if(this.items.size() == 0 && this.subregion.intValue() > 0){
                    itemsSTR = this.subregion.toString();
                    cantidadItems = new Integer(1);
                }
                if(this.items.size() > 0){
                    itemsSTR = UtilidadesI.getListaItemsSubregion(this.items);
                    cantidadItems = new Integer(this.items.size());
                }
                parametrosReporte.put("region",this.region);
            }  
            
            if(this.nivelRed.equals("subestacion")){
                if(this.items.size() == 0 && this.subestacion.intValue() > 0){
                    itemsSTR = this.subestacion.toString();
                    cantidadItems = new Integer(1);
                }
                if(this.items.size() > 0){
                    itemsSTR = UtilidadesI.getListaItemsSubestacion(this.items);
                    cantidadItems = new Integer(this.items.size());
                }
                parametrosReporte.put("region",this.region);
                parametrosReporte.put("subregion",this.subregion);
            } 
            
            if(this.nivelRed.equals("circuito")){
                if(this.items.size() == 0 && this.circuito.intValue() > 0){
                    itemsSTR = this.circuito.toString();
                    cantidadItems = new Integer(1);
                }
                if(this.items.size() > 0){
                    itemsSTR = UtilidadesI.getListaItemsCircuito(this.items);
                    cantidadItems = new Integer(this.items.size());
                }
                parametrosReporte.put("region",this.region);
                parametrosReporte.put("subregion",this.subregion);
                parametrosReporte.put("subestacion",this.subestacion);
            }  
            
            if(this.nivelRed.equals("seccion")){
                if(this.items.size() == 0 && this.seccion.longValue() > 0){
                    itemsSTR = this.seccion.toString();
                    cantidadItems = new Integer(1);
                }
                if(this.items.size() > 0){
                    itemsSTR = UtilidadesI.getListaItemsSeccion(this.items);
                    cantidadItems = new Integer(this.items.size());
                }
                parametrosReporte.put("region",this.region);
                parametrosReporte.put("subregion",this.subregion);
                parametrosReporte.put("subestacion",this.subestacion);
                parametrosReporte.put("circuito",this.circuito);
            } 
            
            List tiposVoltaje =  this.interrupcionBO.getTiposVoltajeInterrupcionCausa(this.tipoInterrupcion);        
            
            cantidadVoltajes = new Integer(tiposVoltaje.size()); 
            if(tiposVoltaje.size() > 0)
                voltajesSTR = UtilidadesI.getListaStr(tiposVoltaje);
            
            utilizarDatosHistoricos = this.interrupcionBO.utilizarDatosHistoricos(this.fechaFinal);
            
            String tipoInterrupcion = UtilidadesFaces.getTipoInterrupcion(this.tipoInterrupcion);
            tipoInterrupcion = (tipoInterrupcion == null) ? "Todos los tipos de interrupción" : tipoInterrupcion;
            String nombreOficina = (this.codigoOficina.intValue() == 0) ? "Todas las oficinas" : this.oficinaBO.buscar(this.codigoOficina).getNombreOficina();
            
            SimpleDateFormat sf = new SimpleDateFormat();
            sf.applyPattern("dd/MM/yyyy");

            parametrosReporte.put("tipoInterrupcion",tipoInterrupcion);
            parametrosReporte.put("fechaInicio",sf.format(this.fechaInicio));
            parametrosReporte.put("fechaFinal",sf.format(this.fechaFinal));
            parametrosReporte.put("codigoOficina",this.codigoOficina);
            parametrosReporte.put("nombreOficina",nombreOficina);
            
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(this.fechaFinal);        
            Integer anoFechaFinal = new Integer (gc.get(GregorianCalendar.YEAR));
            Integer mesFechaFinal = new Integer (gc.get(GregorianCalendar.MONTH) + 1);
            
            
            parametrosReporte.put("anoFechaFinal",anoFechaFinal);
            parametrosReporte.put("mesFechaFinal",mesFechaFinal);
            
            parametrosReporte.put("itemsSTR",itemsSTR);
            parametrosReporte.put("voltajesSTR",voltajesSTR);
            parametrosReporte.put("codigoCausa",this.codigoCausa);
            parametrosReporte.put("cantidadVoltajes",cantidadVoltajes);
            if(cantidadVoltajes.intValue() > 0)
                parametrosReporte.put("codigoCausaCatastrofe",Causa.CODIGO_CATASTROFE);
            else
            
            parametrosReporte.put("codigoCausaCatastrofe",new Integer(0));
            parametrosReporte.put("cantidadItems",cantidadItems);
            
            parametrosReporte.put("DPIR",Boolean.valueOf(false));
            parametrosReporte.put("Cantidad",Boolean.valueOf(false));
            parametrosReporte.put("Comparativo",Boolean.valueOf(false));
            for(int j=0; j < this.grafico.length; j++){
                    parametrosReporte.put(this.grafico[j],Boolean.valueOf(true));
            } 
            
            if(nivelRed.equals("region")){
                parametrosReporte.put("regionesSTR",itemsSTR);
                parametrosReporte.put("cantidadRegiones",cantidadItems);
                if(utilizarDatosHistoricos)
                    nombreArchivo = "SigeItrRepInterrupcionPorCausaRegionHistorico";
                else
                    nombreArchivo = "SigeItrRepInterrupcionPorCausaRegion";
                
                nombre = "InterrupcionPorCausaRegion";
            }
            if(nivelRed.equals("subregion")){
                parametrosReporte.put("subregionesSTR",itemsSTR);
                parametrosReporte.put("cantidadSubregiones",cantidadItems);
                if(utilizarDatosHistoricos)
                    nombreArchivo = "SigeItrRepInterrupcionPorCausaSubregionHistorico";
                else
                    nombreArchivo = "SigeItrRepInterrupcionPorCausaSubregion";
                
                nombre = "InterrupcionPorCausaSubregion";
            }   
            if(nivelRed.equals("subestacion")){
                parametrosReporte.put("subestacionesSTR",itemsSTR);
                parametrosReporte.put("cantidadSubestaciones",cantidadItems);
                if(utilizarDatosHistoricos)
                    nombreArchivo = "SigeItrRepInterrupcionPorCausaSubestacionHistorico";
                else
                    nombreArchivo = "SigeItrRepInterrupcionPorCausaSubestacion";
                
                nombre = "InterrupcionPorCausaSubestacion";
            }   
            if(nivelRed.equals("circuito")){
                parametrosReporte.put("circuitosSTR",itemsSTR);
                parametrosReporte.put("cantidadCircuitos",cantidadItems);
                if(utilizarDatosHistoricos)
                    nombreArchivo = "SigeItrRepInterrupcionPorCausaCircuitoHistorico";
                else
                    nombreArchivo = "SigeItrRepInterrupcionPorCausaCircuito";
                
                nombre = "InterrupcionPorCausaCircuito";
            }   
            if(nivelRed.equals("seccion")){
                parametrosReporte.put("seccionesSTR",itemsSTR);
                parametrosReporte.put("cantidadSecciones",cantidadItems);
                if(utilizarDatosHistoricos)
                    nombreArchivo = "SigeItrRepInterrupcionPorCausaSeccionHistorico";
                else
                    nombreArchivo = "SigeItrRepInterrupcionPorCausaSeccion";
                
                nombre = "InterrupcionPorCausaSeccion";
            }  
            
            Date fechaActual = new Date();
            SimpleDateFormat spf = new SimpleDateFormat();
            spf.applyPattern("dd-MM-yyyy");                
            String fechaActualSTR = spf.format(fechaActual);
            nombre += fechaActualSTR;
            
            nombreArchivo += ".jasper";
            
            if(this.formato == 1){ 
                nombre += ".pdf";                          
            }
            else{     
                nombre += ".xls";
            }
            
            if (this.runReport(JasperPath + nombreArchivo, nombre,parametrosReporte,this.getConnection(),this.formato,UtilidadesFaces.getCurrentUserId())){
                this.addInfo(null, "reporteEjecutado");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Reporte Ejecutado."));
                return  "success";      
                
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Reporte Error."));
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
     * Metodo que retorna la lista de items seleccionados para el nivel de red region
     * @return lista de items de acuerdo al nivel de red deseado
     */
    public List getItemsRegionesSI(){
        List itemsSI = new ArrayList();
        if(this.nivelRed.equals("region")){
            for(int i = 0; i < this.items.size(); i++){ 
                SelectItem item = new SelectItem();
                Region regionObj = (Region)this.items.get(i);
                item.setValue(regionObj);            
                item.setLabel(regionObj.getRegion() + " - " + regionObj.getNombreRegion());           
                itemsSI.add(item);
            }
        }
        return itemsSI;
    }
    
    /**
     * Metodo que retorna la lista de items seleccionados para el nivel de red subregion
     * @return lista de items de acuerdo al nivel de red deseado
     */    
    public List getItemsSubregionesSI(){
        List itemsSI = new ArrayList();
        if(this.nivelRed.equals("subregion")){
            for(int i = 0; i < this.items.size(); i++){ 
                SelectItem item = new SelectItem();
                SubRegion subregionObj = (SubRegion)this.items.get(i);
                item.setValue(subregionObj);            
                item.setLabel(subregionObj.getSubRegionID().getSubRegion() + " - " + subregionObj.getNombreSubRegion());           
                itemsSI.add(item);
            }
        }
        return itemsSI;
    }
    
    /**
     * Metodo que retorna la lista de items seleccionados para el nivel de red subestacion
     * @return lista de items de acuerdo al nivel de red deseado
     */      
    public List getItemsSubestacionesSI(){
        List itemsSI = new ArrayList();
        if(this.nivelRed.equals("subestacion")){
            for(int i = 0; i < this.items.size(); i++){ 
                SelectItem item = new SelectItem();
                SubEstacion subestacionObj = (SubEstacion)this.items.get(i);
                item.setValue(subestacionObj);            
                item.setLabel(subestacionObj.getCodigoSubEstacion() + " - " + subestacionObj.getNombreSubEstacion());           
                itemsSI.add(item);
            }
        }
        return itemsSI;    
    }
    
    /**
     * Metodo que retorna la lista de items seleccionados para el nivel de red circuito
     * @return lista de items de acuerdo al nivel de red deseado
     */      
    public List getItemsCircuitosSI(){
        List itemsSI = new ArrayList();
        if(this.nivelRed.equals("circuito")){
            for(int i = 0; i < this.items.size(); i++){ 
                SelectItem item = new SelectItem();
                Circuito circuito = (Circuito)this.items.get(i);
                item.setValue(circuito);            
                item.setLabel(circuito.getCircuitoID().getCircuito() + " - " + circuito.getNombreCircuito());           
                itemsSI.add(item);
            }
        }
        return itemsSI;    
    }
    
    /**
     * Metodo que retorna la lista de items seleccionados para el nivel de red seccion
     * @return lista de items de acuerdo al nivel de red deseado
     */      
    public List getItemsSeccionesSI(){
        List itemsSI = new ArrayList();
        if(this.nivelRed.equals("seccion")){
            for(int i = 0; i < this.items.size(); i++){ 
                SelectItem item = new SelectItem();
                Seccion seccion = (Seccion)this.items.get(i);
                item.setValue(seccion);            
                item.setLabel(seccion.getSeccionID().getSeccion() + " - " + seccion.getNombreSeccion());           
                itemsSI.add(item);
            }
        }
        return itemsSI;      
    }    
    

    
    
    /**
     * Metodo que agrega un item a la lista de items seleccionados para generar el reporte, 
     * de acuerdo a la opción de grupo
     * @return success
     */
    public String agregarItem(){
        if(this.grupo){
            if(this.nivelRed.equals("region") && this.region.intValue() > 0){
                Region regionObj = this.regionBO.buscar(this.region);
                if(this.items.contains(regionObj) == false)
                    this.items.add(regionObj);
            }
            if(this.nivelRed.equals("subregion") && this.subregion.intValue() > 0){
                SubRegionID id = new SubRegionID();
                Region region = new Region();
                region.setRegion(this.region);
                id.setRegion(region);
                id.setSubRegion(this.subregion);
                SubRegion subRegion = this.subRegionBO.buscar(id);
                if(this.items.contains(subRegion) == false)
                    this.items.add(subRegion);        
            }        
            if(this.nivelRed.equals("subestacion") && this.subestacion.intValue() > 0){
                SubEstacion subEstacion = this.subEstacionBO.buscar(this.subestacion);
                if(this.items.contains(subEstacion) == false)
                    this.items.add(subEstacion); 
            }
            if(this.nivelRed.equals("circuito") && this.circuito.intValue() > 0){
                Circuito circuito = this.circuitoBO.buscar(this.subestacion, this.circuito);
                if(this.items.contains(circuito) == false)
                    this.items.add(circuito); 
            }    
            if(this.nivelRed.equals("seccion") && this.seccion.longValue() > 0){
                SeccionID id = new SeccionID();
                id.setSubEstacion(this.subestacion);
                id.setCircuito(this.circuito);
                id.setSeccion(this.seccion);
                Seccion seccion = this.seccionBO.buscar(id);
                if(this.items.contains(seccion) == false)
                    this.items.add(seccion);                   
            }
        }

        return "success";
    }
    
    
    /**
     * Metodo que elimina un item a la lista de items seleccionados para generar el reporte, 
     * de acuerdo a la opción de grupo
     * @return success
     */  
    public String eliminarItem(){
        if(this.grupo){
            if(this.nivelRed.equals("region")){
                eliminarItemRegion();
            }
            if(this.nivelRed.equals("subregion")){
                eliminarItemSubregion();
            }        
            if(this.nivelRed.equals("subestacion")){
                eliminarItemSubestacion();
            }
            if(this.nivelRed.equals("circuito")){
                eliminarItemCircuito();
            }    
            if(this.nivelRed.equals("seccion") ){
                eliminarItemSeccion();
            }    
        }
        return "success";
    }  
    
    private void eliminarItemRegion(){
        for(int i = 0; i < this.listaRegionItems.length; i++){
            Region seleccionado = this.listaRegionItems[i];
            for(int j = 0; j < this.items.size(); j++){
                Region region = (Region)this.items.get(j);
                if(region.equals(seleccionado)){
                    this.items.remove(j);
                    j = this.items.size() + 1;
                }
            }
        }    
    }
    
    private void eliminarItemSubregion(){
        for(int i = 0; i < this.listaSubregionItems.length; i++){
            SubRegion seleccionado = this.listaSubregionItems[i];
            for(int j = 0; j < this.items.size(); j++){
                SubRegion subregion = (SubRegion)this.items.get(j);
                if(subregion.equals(seleccionado)){
                    this.items.remove(j);
                    j = this.items.size() + 1;
                }
            }
        } 
    } 
    
    private void eliminarItemSubestacion(){
        for(int i = 0; i < this.listaSubestacionItems.length; i++){
            SubEstacion seleccionado = this.listaSubestacionItems[i];
            for(int j = 0; j < this.items.size(); j++){
                SubEstacion subestacion = (SubEstacion)this.items.get(j);
                if(subestacion.equals(seleccionado)){
                    this.items.remove(j);
                    j = this.items.size() + 1;
                }
            }
        }    
    } 
    
    private void eliminarItemCircuito(){
        for(int i = 0; i < this.listaCircuitoItems.length; i++){
            Circuito seleccionado = this.listaCircuitoItems[i];
            for(int j = 0; j < this.items.size(); j++){
                Circuito circuito = (Circuito)this.items.get(j);
                if(circuito.equals(seleccionado)){
                    this.items.remove(j);
                    j = this.items.size() + 1;
                }
            }
        }    
    }    
    
    
    private void eliminarItemSeccion(){
        for(int i = 0; i < this.listaSeccionItems.length; i++){
            Seccion seleccionado = this.listaSeccionItems[i];
            for(int j = 0; j < this.items.size(); j++){
                Seccion seccion = (Seccion)this.items.get(j);
                if(seccion.equals(seleccionado)){
                    this.items.remove(j);
                    j = this.items.size() + 1;
                }
            }
        }    
    }              
    
    /**
     * Metodo modificador de causaBO.
     * @param causaBO El causaBO a modificar.
     */
    public void setCausaBO(CausaBO causaBO) {
        this.causaBO = causaBO;
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
     * Metodo accesor de codigoCausa.
     * @return Retorna el codigoCausa.
     */
    public Integer getCodigoCausa() {
        return this.codigoCausa;
    }
    /**
     * Metodo modificador de codigoCausa.
     * @param codigoCausa El codigoCausa a modificar.
     */
    public void setCodigoCausa(Integer codigoCausa) {
        this.codigoCausa = codigoCausa;
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
     * Metodo accesor de grupo.
     * @return Retorna el grupo.
     */
    public boolean isGrupo() {
        return this.grupo;
    }
    /**
     * Metodo modificador de grupo.
     * @param grupo El grupo a modificar.
     */
    public void setGrupo(boolean grupo) {
        this.grupo = grupo;
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
     * Metodo accesor de listaRegionItems.
     * @return Retorna el listaRegionItems.
     */
    public Region[] getListaRegionItems() {
        return this.listaRegionItems;
    }
    /**
     * Metodo modificador de listaRegionItems.
     * @param listaRegionItems El listaRegionItems a modificar.
     */
    public void setListaRegionItems(Region[] listaRegionItems) {
        this.listaRegionItems = listaRegionItems;
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
     * Metodo accesor de listaSeccionItems.
     * @return Retorna el listaSeccionItems.
     */
    public Seccion[] getListaSeccionItems() {
        return this.listaSeccionItems;
    }
    /**
     * Metodo modificador de listaSeccionItems.
     * @param listaSeccionItems El listaSeccionItems a modificar.
     */
    public void setListaSeccionItems(Seccion[] listaSeccionItems) {
        this.listaSeccionItems = listaSeccionItems;
    }
    /**
     * Metodo accesor de listaSubestacionItems.
     * @return Retorna el listaSubestacionItems.
     */
    public SubEstacion[] getListaSubestacionItems() {
        return this.listaSubestacionItems;
    }
    /**
     * Metodo modificador de listaSubestacionItems.
     * @param listaSubestacionItems El listaSubestacionItems a modificar.
     */
    public void setListaSubestacionItems(SubEstacion[] listaSubestacionItems) {
        this.listaSubestacionItems = listaSubestacionItems;
    }

    /**
     * Metodo accesor de listaSubregionItems.
     * @return Retorna el listaSubregionItems.
     */
    public SubRegion[] getListaSubregionItems() {
        return this.listaSubregionItems;
    }
    /**
     * Metodo modificador de listaSubregionItems.
     * @param listaSubregionItems El listaSubregionItems a modificar.
     */
    public void setListaSubregionItems(SubRegion[] listaSubregionItems) {
        this.listaSubregionItems = listaSubregionItems;
    }
    /**
     * Metodo modificador de interrupcionBO.
     * @param interrupcionBO El interrupcionBO a modificar.
     */
    public void setInterrupcionBO(InterrupcionBO interrupcionBO) {
        this.interrupcionBO = interrupcionBO;
    }
    /**
     * Metodo accesor de error.
     * @return Retorna el error.
     */
    public String getError() {
        return this.error;
    }

    /**
     * Método accesor del atributo grafico.
     * @return Retorna el atributo grafico.
     */
    public String[] getGrafico() {
        return this.grafico;
    }

    /**
     * Método modificador del atributo grafico.
     * @param grafico El dato para modificar el atributo grafico.
     */
    public void setGrafico(String[] grafico) {
        this.grafico = grafico;
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
