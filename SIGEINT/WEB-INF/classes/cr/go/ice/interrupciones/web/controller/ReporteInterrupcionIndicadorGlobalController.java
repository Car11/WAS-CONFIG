package cr.go.ice.interrupciones.web.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.GregorianCalendar;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.sql.DataSource;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.InterrupcionBO;
import cr.go.ice.interrupciones.BO.SeccionBO;
import cr.go.ice.interrupciones.BO.CircuitoBO;
import cr.go.ice.interrupciones.BO.RegionBO;
import cr.go.ice.interrupciones.BO.SubEstacionBO;
import cr.go.ice.interrupciones.BO.SubRegionBO;
import cr.go.ice.interrupciones.domain.Interrupcion;
import cr.go.ice.interrupciones.domain.SubRegionID;
import cr.go.ice.interrupciones.domain.SeccionID;
import cr.go.ice.interrupciones.domain.Seccion;
import cr.go.ice.interrupciones.domain.Circuito;
import cr.go.ice.interrupciones.domain.Region;
import cr.go.ice.interrupciones.domain.SubEstacion;
import cr.go.ice.interrupciones.domain.SubRegion;
import cr.go.ice.interrupciones.servlets.ServletReporte;
import cr.go.ice.interrupciones.utils.UtilidadesI;
import cr.go.ice.interrupciones.utils.UtilidadesFaces;
import cr.go.ice.interrupciones.web.Recurso;

import com.vvs.utilidades.Utilidades;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ReporteInterrupcionIndicadorGlobalController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ReporteInterrupcionIndicadorGlobalController.java</code>.</p>
 * <p>Fecha creación: 19/11/2007</p>
 * <p>Ultima actualización: 19/11/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ReporteInterrupcionIndicadorGlobalController extends AbstractFacesController{

    private Integer ano;
    private Integer mes;    
    private Integer tipoInterrupcion;
    
    private String nivelRed;
    private boolean grupo;
    
    private Integer region;
    private Integer subregion;
    private Integer subestacion;
    private Integer circuito;
    private Long seccion;
    
    private RegionBO regionBO;
    private SubRegionBO subRegionBO;
    private SubEstacionBO subEstacionBO;
    private CircuitoBO circuitoBO;
    private SeccionBO seccionBO;
    
    private InterrupcionBO interrupcionBO;
    
    private Region [] listaRegionItems;
    private SubRegion [] listaSubregionItems;
    private SubEstacion [] listaSubestacionItems;
    private Circuito [] listaCircuitoItems;
    private Seccion [] listaSeccionItems;
    
    
    private List items;
    
    private String error;
    
    private  static final String JasperPath = "/jasperReports/";
    private String itemsSTR;
    private Integer cantidadItems;
    private boolean utilizarDatosHistoricos;
    /**
     * Constructor por defecto
     */    
    public ReporteInterrupcionIndicadorGlobalController(){
        reiniciarCampos();
        
    }
    
    /**
     * Metodo que reincia los atributos de la clase
     */
    private void reiniciarCampos(){        
        Calendar calendar = Calendar.getInstance();
        this.ano = new Integer(calendar.get(Calendar.YEAR));
        this.mes = new Integer(calendar.get(Calendar.MONTH) + 1);
        this.tipoInterrupcion = new Integer(0);
        grupo = false;
        
        this.region = new Integer(0);
        this.subregion = new Integer(0);
        this.subestacion = new Integer(0);
        this.circuito = new Integer(0);
        this.seccion = new Long(0);
        
        this.items = new ArrayList();
        
        this.nivelRed = "";
        this.error = "";
        this.itemsSTR = "0";
        this.cantidadItems = Integer.valueOf(0);
        this.utilizarDatosHistoricos = Boolean.FALSE;
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
     * Retorna una lista de select item de los diferentes niveles de red
     * @return Lista de niveles de red
     */    
    public List getListaNivelesRed(){
        return UtilidadesFaces.getListaNivelesRed();
    }  
    
    /**
     * Lista de meses en formato select item
     * @return lista de meses
     */    
    public List getListaMesSI(){
        return UtilidadesFaces.getListaMes();
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
        List regiones = this.regionBO.getRegiones();
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
    
    
    private boolean validarParametros(){
        boolean correcto = true;
       
        if(this.ano == null || this.ano.intValue() <= 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El año es requerido."));
            correcto=false;
        }   
        if(this.mes == null || this.mes.intValue() <= 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El mes es requerido."));
            correcto=false;
        }          
        return correcto;
    }
    
    private boolean validarOpcionesParametros(){
        boolean correcto = true;
       
        Calendar calendar = Calendar.getInstance();
        
        int anoActual = calendar.get(Calendar.YEAR);       
        if(this.ano.intValue() > anoActual){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El anño no puede ser mayor al actual."));
            correcto=false;
        }
        
        int mesActual = calendar.get(Calendar.MONTH) + 1;
        if(this.ano.intValue() == anoActual && this.mes.intValue() > mesActual){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El mes no puede ser mayot al actual."));
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
    
    /**
     * Metodo que obtiene los beans y invoca la generacion del reporte
     * @return success o error
     */
    @SuppressWarnings("unchecked")
    private String generarReporte(){  
        this.error = "";
        HashMap parametrosReporte = new HashMap();
       // ();    
        String nombre = "";
        String nombreArchivo = "";
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
            this.itemsSTR = "0";
            this.cantidadItems = new Integer(0);
            Integer codigoVoltaje = null;        
            Integer tipoVoltaje = null;
               
            this.utilizarDatosHistoricos = false;
            parametrosReporte.put("tipoReporte",new Integer(ServletReporte.reporteIndicadorGlobal));
            
            
            if(this.nivelRed.equals("region")){
                if(this.items.size() == 0 && this.region.intValue() > 0){
                    this.itemsSTR = this.region.toString();
                    cantidadItems = new Integer(1);
                }
                if(this.items.size() > 0){
                    this.itemsSTR = UtilidadesI.getListaItemsRegion(this.items);
                    cantidadItems = new Integer(this.items.size());
                }
            }
            
            if(this.nivelRed.equals("subregion")){
                if(this.items.size() == 0 && this.subregion.intValue() > 0){
                    this.itemsSTR = this.subregion.toString();
                    cantidadItems = new Integer(1);
                }            
                if(this.items.size() > 0){
                    this.itemsSTR = UtilidadesI.getListaItemsSubregion(this.items);
                    cantidadItems = new Integer(this.items.size());
                }
               // ctx.getExternalContext().getSessionMap().put("region",this.region); 
                parametrosReporte.put("region",this.region);
            }  
            
            if(this.nivelRed.equals("subestacion")){
                if(this.items.size() == 0 && this.subestacion.intValue() > 0){
                    this.itemsSTR = this.subestacion.toString();
                    cantidadItems = new Integer(1);
                }
                if(this.items.size() > 0){
                    this.itemsSTR = UtilidadesI.getListaItemsSubestacion(this.items);
                    cantidadItems = new Integer(this.items.size());
                }
                //ctx.getExternalContext().getSessionMap().put("region",this.region);
               // ctx.getExternalContext().getSessionMap().put("subregion",this.subregion);
                parametrosReporte.put("region",this.region);
                parametrosReporte.put("subregion",this.subregion);
            } 
            
            if(this.nivelRed.equals("circuito")){
                if(this.items.size() == 0 && this.circuito.intValue() > 0){
                    this.itemsSTR = this.circuito.toString();
                    cantidadItems = new Integer(1);
                }
                if(this.items.size() > 0){
                    this.itemsSTR = UtilidadesI.getListaItemsCircuito(this.items);
                    cantidadItems = new Integer(this.items.size());
                }
                //ctx.getExternalContext().getSessionMap().put("region",this.region);
               // ctx.getExternalContext().getSessionMap().put("subregion",this.subregion);
               // ctx.getExternalContext().getSessionMap().put("subestacion",this.subestacion);
                
                parametrosReporte.put("region",this.region);
                parametrosReporte.put("subregion",this.subregion);
                parametrosReporte.put("subestacion",this.subestacion);
            }  
            
            if(this.nivelRed.equals("seccion")){
                if(this.items.size() == 0 && this.seccion.longValue() > 0){
                    this.itemsSTR = this.seccion.toString();
                    cantidadItems = new Integer(1);
                }
                if(this.items.size() > 0){
                    this.itemsSTR = UtilidadesI.getListaItemsSeccion(this.items);
                    cantidadItems = new Integer(this.items.size());
                }
                //ctx.getExternalContext().getSessionMap().put("region",this.region);
                //ctx.getExternalContext().getSessionMap().put("subregion",this.subregion);
                //ctx.getExternalContext().getSessionMap().put("subestacion",this.subestacion);
               // ctx.getExternalContext().getSessionMap().put("circuito",this.circuito);
                
                parametrosReporte.put("region",this.region);
                parametrosReporte.put("subregion",this.subregion);
                parametrosReporte.put("subestacion",this.subestacion);
                parametrosReporte.put("circuito",this.circuito);
            } 
                    
                    
            
            if(tipoInterrupcion.intValue() == Interrupcion.INTERRUPCION_PRIMARIA.intValue()){
                codigoVoltaje = new Integer(88);
                tipoVoltaje = new Integer(2);
            }
            else if(tipoInterrupcion.intValue() == Interrupcion.INTERRUPCION_SECUNDARIA.intValue()){
                    codigoVoltaje = new Integer(77);
                    tipoVoltaje = new Integer(3);
                 }
                 else if(tipoInterrupcion.intValue() == Interrupcion.INTERRUPCION_SUMINISTRO.intValue()){
                         codigoVoltaje = new Integer(10);
                         tipoVoltaje = new Integer(1);
                     }
                     else if(tipoInterrupcion.intValue() == Interrupcion.INTERRUPCION_CATASTROFE.intValue()){
                             codigoVoltaje = new Integer(66);
                             tipoVoltaje = new Integer(4);
                          }  
                          else if(tipoInterrupcion.intValue() == Interrupcion.INTERRUPCION_DISTRIBUCION.intValue()){
                                  codigoVoltaje = new Integer(55);
                                  tipoVoltaje = new Integer(5);                          
                              }else{
                                  codigoVoltaje = new Integer(66);
                                  tipoVoltaje = new Integer(0);
                               }
                                                     
            GregorianCalendar fechaParametro = new GregorianCalendar();
            fechaParametro.set(GregorianCalendar.YEAR, this.ano.intValue());
            fechaParametro.set(GregorianCalendar.MONTH, this.mes.intValue() - 1);
            fechaParametro.set(GregorianCalendar.DAY_OF_MONTH, 1);
            
            this.utilizarDatosHistoricos = this.interrupcionBO.utilizarDatosHistoricos(fechaParametro.getTime());
            
            Integer mesHistorico = null;
            Integer anoHistorico = null;
            if(this.utilizarDatosHistoricos){
                
                if(this.ano.intValue() < 2007 || (this.ano.intValue() == 2007 && this.mes.intValue() < 9)){
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
                    anoHistorico = this.ano;
                    mesHistorico = this.mes; 
                }
            }
            
            String tipoInterrupcion = UtilidadesFaces.getTipoInterrupcion(this.tipoInterrupcion);
            tipoInterrupcion = (tipoInterrupcion == null) ? "Todos los tipos de interrupción" : tipoInterrupcion;
            
            SimpleDateFormat sf = new SimpleDateFormat();
            sf.applyPattern("dd/MM/yyyy");

           // ctx.getExternalContext().getSessionMap().put("tipoInterrupcion",tipoInterrupcion);
           // ctx.getExternalContext().getSessionMap().put("ano",this.ano);
           // ctx.getExternalContext().getSessionMap().put("mes",this.mes);
            
           // ctx.getExternalContext().getSessionMap().put("utilizarDatosHistoricos",Boolean.valueOf(utilizarDatosHistoricos));
                    
          //  ctx.getExternalContext().getSessionMap().put("anoHistorico",anoHistorico);
          //  ctx.getExternalContext().getSessionMap().put("mesHistorico",mesHistorico);        
            
            String mesSTR = UtilidadesFaces.getDescripcionMes(this.mes).toLowerCase();
            //ctx.getExternalContext().getSessionMap().put("mesSTR",mesSTR);
            
           // ctx.getExternalContext().getSessionMap().put("tipoVoltaje",tipoVoltaje);
           // ctx.getExternalContext().getSessionMap().put("codigoVoltaje",codigoVoltaje);
            
            //ctx.getExternalContext().getSessionMap().put("itemsSTR",itemsSTR);
           // ctx.getExternalContext().getSessionMap().put("cantidadItems",cantidadItems);
            
            parametrosReporte.put("tipoInterrupcion",tipoInterrupcion);
            parametrosReporte.put("ano",this.ano);
            parametrosReporte.put("mes",this.mes);
            
            parametrosReporte.put("utilizarDatosHistoricos",Boolean.valueOf(utilizarDatosHistoricos));
            
            parametrosReporte.put("anoHistorico",anoHistorico);
            parametrosReporte.put("mesHistorico",mesHistorico);
            
            parametrosReporte.put("mesHistorico",mesHistorico);
            
            parametrosReporte.put("mesSTR",mesSTR);
            
            parametrosReporte.put("tipoVoltaje",tipoVoltaje);
            parametrosReporte.put("codigoVoltaje",codigoVoltaje);
            
            //parametrosReporte.put("itemsSTR",itemsSTR);
            parametrosReporte.put("cantidadItems",cantidadItems);
            if(this.nivelRed.equals("region")){
                parametrosReporte.put("regionesSTR",this.itemsSTR);
                parametrosReporte.put("cantidadRegiones",this.cantidadItems);
                if(this.utilizarDatosHistoricos)
                    nombreArchivo = "SigeItrRepInterrupcionIndicadorGlobalRegionHistorico";
                else
                    nombreArchivo = "SigeItrRepInterrupcionIndicadorGlobalRegion";
                
                nombre = "IndGlobalesRegion";
            }
            if(this.nivelRed.equals("subregion")){
                parametrosReporte.put("subregionesSTR",this.itemsSTR);
                parametrosReporte.put("cantidadSubregiones",this.cantidadItems);
                if(this.utilizarDatosHistoricos)
                    nombreArchivo = "SigeItrRepInterrupcionIndicadorGlobalSubregionHistorico";
                else
                    nombreArchivo = "SigeItrRepInterrupcionIndicadorGlobalSubregion";
                
                nombre = "IndGlobalesSubregion";
            }   
            if(this.nivelRed.equals("subestacion")){
                parametrosReporte.put("subestacionesSTR",this.itemsSTR);
                parametrosReporte.put("cantidadSubestaciones",this.cantidadItems);
                if(this.utilizarDatosHistoricos)
                    nombreArchivo = "SigeItrRepInterrupcionIndicadorGlobalSubestacionHistorico";
                else                    
                    nombreArchivo = "SigeItrRepInterrupcionIndicadorGlobalSubestacion";
                
                nombre = "IndGlobalesSubestacion";
            }   
            if(this.nivelRed.equals("circuito")){
                parametrosReporte.put("circuitosSTR",this.itemsSTR);
                parametrosReporte.put("cantidadCircuitos",this.cantidadItems);
                if(this.utilizarDatosHistoricos)
                    nombreArchivo = "SigeItrRepInterrupcionIndicadorGlobalCircuitoHistorico";
                else                          
                    nombreArchivo = "SigeItrRepInterrupcionIndicadorGlobalCircuito";
                
                nombre = "IndGlobalesCircuito";
            }   
            if(this.nivelRed.equals("seccion")){
                parametrosReporte.put("seccionesSTR",this.itemsSTR);
                parametrosReporte.put("cantidadSecciones",this.cantidadItems);
                if(this.utilizarDatosHistoricos)
                    nombreArchivo = "SigeItrRepInterrupcionIndicadorGlobalSeccionHistorico";
                else                         
                    nombreArchivo = "SigeItrRepInterrupcionIndicadorGlobalSeccion";
                
                nombre = "IndGlobalesSeccion";
            }                 
                           
            Date fechaActual = new Date();
            SimpleDateFormat spf = new SimpleDateFormat();
            spf.applyPattern("dd-MM-yyyy");                
            String fechaActualSTR = spf.format(fechaActual);
            nombre += fechaActualSTR + ".pdf";
            
            nombreArchivo += ".jasper";
           Integer formato = UtilidadesFaces.FORMATO_PDF;      
            
           
            if (this.runReport(JasperPath + nombreArchivo, nombre,parametrosReporte,this.getConnection(),formato,UtilidadesFaces.getCurrentUserId())){
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
    
    @SuppressWarnings("unchecked")
    private void pasarParametrosReporte(){
        
        HashMap parametrosReporte = new HashMap();
        this.itemsSTR = "0";
        this.cantidadItems = new Integer(0);
        Integer codigoVoltaje = null;        
        Integer tipoVoltaje = null;
           
        this.utilizarDatosHistoricos = false;
        
      //  FacesContext ctx = FacesContext.getCurrentInstance();
       // ctx.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteIndicadorGlobal));
       // ctx.getExternalContext().getSessionMap().put("nivelRedActual",this.nivelRed);
       
        parametrosReporte.put("tipoReporte",new Integer(ServletReporte.reporteIndicadorGlobal));
        
        
        if(this.nivelRed.equals("region")){
            if(this.items.size() == 0 && this.region.intValue() > 0){
                this.itemsSTR = this.region.toString();
                cantidadItems = new Integer(1);
            }
            if(this.items.size() > 0){
                this.itemsSTR = UtilidadesI.getListaItemsRegion(this.items);
                cantidadItems = new Integer(this.items.size());
            }
        }
        
        if(this.nivelRed.equals("subregion")){
            if(this.items.size() == 0 && this.subregion.intValue() > 0){
                this.itemsSTR = this.subregion.toString();
                cantidadItems = new Integer(1);
            }            
            if(this.items.size() > 0){
                this.itemsSTR = UtilidadesI.getListaItemsSubregion(this.items);
                cantidadItems = new Integer(this.items.size());
            }
           // ctx.getExternalContext().getSessionMap().put("region",this.region); 
            parametrosReporte.put("region",this.region);
        }  
        
        if(this.nivelRed.equals("subestacion")){
            if(this.items.size() == 0 && this.subestacion.intValue() > 0){
                this.itemsSTR = this.subestacion.toString();
                cantidadItems = new Integer(1);
            }
            if(this.items.size() > 0){
                this.itemsSTR = UtilidadesI.getListaItemsSubestacion(this.items);
                cantidadItems = new Integer(this.items.size());
            }
            //ctx.getExternalContext().getSessionMap().put("region",this.region);
           // ctx.getExternalContext().getSessionMap().put("subregion",this.subregion);
            parametrosReporte.put("region",this.region);
            parametrosReporte.put("subregion",this.subregion);
        } 
        
        if(this.nivelRed.equals("circuito")){
            if(this.items.size() == 0 && this.circuito.intValue() > 0){
                this.itemsSTR = this.circuito.toString();
                cantidadItems = new Integer(1);
            }
            if(this.items.size() > 0){
                this.itemsSTR = UtilidadesI.getListaItemsCircuito(this.items);
                cantidadItems = new Integer(this.items.size());
            }
            //ctx.getExternalContext().getSessionMap().put("region",this.region);
           // ctx.getExternalContext().getSessionMap().put("subregion",this.subregion);
           // ctx.getExternalContext().getSessionMap().put("subestacion",this.subestacion);
            
            parametrosReporte.put("region",this.region);
            parametrosReporte.put("subregion",this.subregion);
            parametrosReporte.put("subestacion",this.subestacion);
        }  
        
        if(this.nivelRed.equals("seccion")){
            if(this.items.size() == 0 && this.seccion.longValue() > 0){
                this.itemsSTR = this.seccion.toString();
                cantidadItems = new Integer(1);
            }
            if(this.items.size() > 0){
                this.itemsSTR = UtilidadesI.getListaItemsSeccion(this.items);
                cantidadItems = new Integer(this.items.size());
            }
            //ctx.getExternalContext().getSessionMap().put("region",this.region);
            //ctx.getExternalContext().getSessionMap().put("subregion",this.subregion);
            //ctx.getExternalContext().getSessionMap().put("subestacion",this.subestacion);
           // ctx.getExternalContext().getSessionMap().put("circuito",this.circuito);
            
            parametrosReporte.put("region",this.region);
            parametrosReporte.put("subregion",this.subregion);
            parametrosReporte.put("subestacion",this.subestacion);
            parametrosReporte.put("circuito",this.circuito);
        } 
                
                
        
        if(tipoInterrupcion.intValue() == Interrupcion.INTERRUPCION_PRIMARIA.intValue()){
            codigoVoltaje = new Integer(88);
            tipoVoltaje = new Integer(2);
        }
        else if(tipoInterrupcion.intValue() == Interrupcion.INTERRUPCION_SECUNDARIA.intValue()){
                codigoVoltaje = new Integer(77);
                tipoVoltaje = new Integer(3);
             }
             else if(tipoInterrupcion.intValue() == Interrupcion.INTERRUPCION_SUMINISTRO.intValue()){
                     codigoVoltaje = new Integer(10);
                     tipoVoltaje = new Integer(1);
                 }
                 else if(tipoInterrupcion.intValue() == Interrupcion.INTERRUPCION_CATASTROFE.intValue()){
                         codigoVoltaje = new Integer(66);
                         tipoVoltaje = new Integer(4);
                      }  
                      else if(tipoInterrupcion.intValue() == Interrupcion.INTERRUPCION_DISTRIBUCION.intValue()){
                              codigoVoltaje = new Integer(55);
                              tipoVoltaje = new Integer(5);                          
                          }else{
                              codigoVoltaje = new Integer(66);
                              tipoVoltaje = new Integer(0);
                           }
                                                 
        GregorianCalendar fechaParametro = new GregorianCalendar();
        fechaParametro.set(GregorianCalendar.YEAR, this.ano.intValue());
        fechaParametro.set(GregorianCalendar.MONTH, this.mes.intValue() - 1);
        fechaParametro.set(GregorianCalendar.DAY_OF_MONTH, 1);
        
        this.utilizarDatosHistoricos = this.interrupcionBO.utilizarDatosHistoricos(fechaParametro.getTime());
        
        Integer mesHistorico = null;
        Integer anoHistorico = null;
        if(this.utilizarDatosHistoricos){
            
            if(this.ano.intValue() < 2007 || (this.ano.intValue() == 2007 && this.mes.intValue() < 9)){
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
                anoHistorico = this.ano;
                mesHistorico = this.mes; 
            }
        }
        
        String tipoInterrupcion = UtilidadesFaces.getTipoInterrupcion(this.tipoInterrupcion);
        tipoInterrupcion = (tipoInterrupcion == null) ? "Todos los tipos de interrupción" : tipoInterrupcion;
        
        SimpleDateFormat sf = new SimpleDateFormat();
        sf.applyPattern("dd/MM/yyyy");

       // ctx.getExternalContext().getSessionMap().put("tipoInterrupcion",tipoInterrupcion);
       // ctx.getExternalContext().getSessionMap().put("ano",this.ano);
       // ctx.getExternalContext().getSessionMap().put("mes",this.mes);
        
       // ctx.getExternalContext().getSessionMap().put("utilizarDatosHistoricos",Boolean.valueOf(utilizarDatosHistoricos));
                
      //  ctx.getExternalContext().getSessionMap().put("anoHistorico",anoHistorico);
      //  ctx.getExternalContext().getSessionMap().put("mesHistorico",mesHistorico);        
        
        String mesSTR = UtilidadesFaces.getDescripcionMes(this.mes).toLowerCase();
        //ctx.getExternalContext().getSessionMap().put("mesSTR",mesSTR);
        
       // ctx.getExternalContext().getSessionMap().put("tipoVoltaje",tipoVoltaje);
       // ctx.getExternalContext().getSessionMap().put("codigoVoltaje",codigoVoltaje);
        
        //ctx.getExternalContext().getSessionMap().put("itemsSTR",itemsSTR);
       // ctx.getExternalContext().getSessionMap().put("cantidadItems",cantidadItems);
        
        parametrosReporte.put("tipoInterrupcion",tipoInterrupcion);
        parametrosReporte.put("ano",this.ano);
        parametrosReporte.put("mes",this.mes);
        
        parametrosReporte.put("utilizarDatosHistoricos",Boolean.valueOf(utilizarDatosHistoricos));
        
        parametrosReporte.put("anoHistorico",anoHistorico);
        parametrosReporte.put("mesHistorico",mesHistorico);
        
        parametrosReporte.put("mesHistorico",mesHistorico);
        
        parametrosReporte.put("mesSTR",mesSTR);
        
        parametrosReporte.put("tipoVoltaje",tipoVoltaje);
        parametrosReporte.put("codigoVoltaje",codigoVoltaje);
        
        //parametrosReporte.put("itemsSTR",itemsSTR);
        parametrosReporte.put("cantidadItems",cantidadItems);
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
                Region region = this.regionBO.buscar(this.region);
                if(this.items.contains(region) == false)
                    this.items.add(region);
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
            Region seleccionado = (Region)this.listaRegionItems[i];
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
            SubRegion seleccionado = (SubRegion)this.listaSubregionItems[i];
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
            SubEstacion seleccionado = (SubEstacion)this.listaSubestacionItems[i];
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
            Circuito seleccionado = (Circuito)this.listaCircuitoItems[i];
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
     * Metodo accesor de ano.
     * @return Retorna el ano.
     */
    public Integer getAno() {
        return this.ano;
    }
    /**
     * Metodo modificador de ano.
     * @param ano El ano a modificar.
     */
    public void setAno(Integer ano) {
        this.ano = ano;
    }
    /**
     * Metodo accesor de mes.
     * @return Retorna el mes.
     */
    public Integer getMes() {
        return this.mes;
    }
    /**
     * Metodo modificador de mes.
     * @param mes El mes a modificar.
     */
    public void setMes(Integer mes) {
        this.mes = mes;
    }
    /**
     * Metodo accesor de error.
     * @return Retorna el error.
     */
    public String getError() {
        return this.error;
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

    /**
     * Método accesor del atributo itemsSTR.
     * @return Retorna el atributo itemsSTR.
     */
    public String getItemsSTR() {
        return this.itemsSTR;
    }

    /**
     * Método modificador del atributo itemsSTR.
     * @param itemsSTR El dato para modificar el atributo itemsSTR.
     */
    public void setItemsSTR(String itemsSTR) {
        this.itemsSTR = itemsSTR;
    }

    /**
     * Método accesor del atributo cantidadItems.
     * @return Retorna el atributo cantidadItems.
     */
    public Integer getCantidadItems() {
        return this.cantidadItems;
    }

    /**
     * Método modificador del atributo cantidadItems.
     * @param cantidadItems El dato para modificar el atributo cantidadItems.
     */
    public void setCantidadItems(Integer cantidadItems) {
        this.cantidadItems = cantidadItems;
    }

    /**
     * Método accesor del atributo utilizarDatosHistoricos.
     * @return Retorna el atributo utilizarDatosHistoricos.
     */
    public boolean isUtilizarDatosHistoricos() {
        return this.utilizarDatosHistoricos;
    }

    /**
     * Método modificador del atributo utilizarDatosHistoricos.
     * @param utilizarDatosHistoricos El dato para modificar el atributo utilizarDatosHistoricos.
     */
    public void setUtilizarDatosHistoricos(boolean utilizarDatosHistoricos) {
        this.utilizarDatosHistoricos = utilizarDatosHistoricos;
    }
}
