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

import cr.go.ice.interrupciones.BO.CircuitoBO;
import cr.go.ice.interrupciones.BO.RegionBO;
import cr.go.ice.interrupciones.BO.SubEstacionBO;
import cr.go.ice.interrupciones.BO.SubRegionBO;
import cr.go.ice.interrupciones.domain.SubRegionID;
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
 * <p>Clase cr.go.ice.interrupciones.web.controller.ReporteFusiblesInstRetController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ReporteFusiblesInstRetController.java</code>.</p>
 * <p>Fecha creación: 29/08/2008</p>
 * <p>Ultima actualización: 29/08/2008</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ReporteFusibleInstRetController extends AbstractFacesController {

    private Date fechaInicio;
    private Date fechaFinal;
    private Integer tipoFusible;
    private Integer formato;
    
    private String nivelRed;
    private boolean grupo;
    
    private Integer region;
    private Integer subregion;
    private Integer subestacion;
    private Integer circuito;   
    
    private RegionBO regionBO;
    private SubRegionBO subRegionBO;
    private SubEstacionBO subEstacionBO;
    private CircuitoBO circuitoBO;    
    
    private Region [] listaRegionItems;
    private SubRegion [] listaSubregionItems;
    private Circuito [] listaCircuitoItems;
    
    
    private List items;
    /**
     * <code>FUSIBLE_INSTALADO</code> Fusible instalado
     */    
    public static final Integer FUSIBLE_INSTALADO = new Integer(0);
    /**
     * <code>FUSIBLE_RETIRADO</code> Fusible retirado
     */    
    public static final Integer FUSIBLE_RETIRADO = new Integer(1);
    private  static final String jasperPath = "/jasperReports/";  
    public static String servletJasperPath;    
    /**
     * Constructor por defecto
     */    
    public ReporteFusibleInstRetController(){
        reiniciarCampos();        
    }
    
    /**
     * Metodo que reincia los atributos de la clase
     */
    private void reiniciarCampos(){
        this.fechaInicio = new Date();
        this.fechaFinal = new Date();     
        this.tipoFusible = FUSIBLE_INSTALADO;
        this.formato = UtilidadesFaces.FORMATO_PDF;
        grupo = false;
        
        this.region = new Integer(0);
        this.subregion = new Integer(0);
        this.subestacion = new Integer(0);
        this.circuito = new Integer(0);        
        
        this.items = new ArrayList();
        
        this.nivelRed = "";
    }
    
    public String getInit(){
    	FacesContext context= FacesContext.getCurrentInstance();
    	 Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
         if(limpiar != null)
             reiniciarCampos();
         return "success";
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
     * Retorna una lista de select item de los tipos de fusibles
     * @return Lista de tipos de fusibles
     */    
    public List getListaTiposFusible(){ 
        List tipos = new ArrayList();    
        tipos.add(new SelectItem(FUSIBLE_INSTALADO, "Instalado"));
        tipos.add(new SelectItem(FUSIBLE_RETIRADO, "Retirado"));          
        return tipos;       
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
        if(nivelRed.equals("subregion") || nivelRed.equals("subestacion") || nivelRed.equals("circuito") )
            this.items = new ArrayList();
        return "listener";  
    }
    
    /**
     * Comment for listenerSubregion
     * @param v
     * @return "listener" a SubRegion
     */
    public String listenerSubregion(){
        if(nivelRed.equals("subestacion") || nivelRed.equals("circuito"))
            this.items = new ArrayList();
        return "listener";  
    }    
    
    /**
     * Comment for listenerSubestacion
     * @param v
     * @return "listener" a SubEstacion
     */
    public String listenerSubestacion(){
        if(nivelRed.equals("circuito"))
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
        FacesContext context = FacesContext.getCurrentInstance();
        if(this.fechaInicio == null){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar un fecha de inicio."));
            correcto = false;
        }
        if(this.fechaFinal == null){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar un fecha de fin."));
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de fin debe ser mayor o gial a la fecha de inicio."));
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
                FacesMessage msg = new FacesMessage();
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                msg.setDetail("Debe seleccionar la subregión");            
                context.addMessage("form1:cboSubregion", msg);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar la subregión."));
                correcto=false;
            }              
            if(this.subestacion.intValue() <= 0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar la subestación."));
                correcto=false;
            }
        }      
        
        return correcto;
    }
    
    /**
     * Realiza las validaciones de los parámetros de entrada dados por el usuario y genera el reporte de fusibles instalados
     * y retirados
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
        
    @SuppressWarnings("unchecked")
    private void pasarParametrosReporte(){
        
        String itemsSTR = "0";       
        Integer cantidadItems = new Integer(0);    

        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ctx.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteFusiblesInstRet));
//        ctx.getExternalContext().getSessionMap().put("nivelRedActual",this.nivelRed);
//        SimpleDateFormat sf = new SimpleDateFormat();
//        sf.applyPattern("dd/MM/yyyy");           
//        ctx.getExternalContext().getSessionMap().put("fechaInicio",sf.format(this.fechaInicio));
//        ctx.getExternalContext().getSessionMap().put("fechaFinal",sf.format(this.fechaFinal));
//        ctx.getExternalContext().getSessionMap().put("tipoFusible",this.tipoFusible);
//        ctx.getExternalContext().getSessionMap().put("formato",this.formato);
        HashMap parametrosReporte = new HashMap();
        servletJasperPath = "/jasperReports/";
        
        
        SimpleDateFormat sf = new SimpleDateFormat();
       sf.applyPattern("dd/MM/yyyy");        
        
        //parametrosReporte.put("nivelRedActual",this.nivelRed);  
        parametrosReporte.put("SUBREPORT_DIR",servletJasperPath);   
        parametrosReporte.put("fechaInicio",sf.format(this.fechaInicio));
        parametrosReporte.put("fechaFinal",sf.format(this.fechaFinal));
        parametrosReporte.put("tipoFusible",this.tipoFusible);
        
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
//            ctx.getExternalContext().getSessionMap().put("region",this.region);  
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
//            ctx.getExternalContext().getSessionMap().put("region",this.region);
//            ctx.getExternalContext().getSessionMap().put("subregion",this.subregion);
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
//            ctx.getExternalContext().getSessionMap().put("region",this.region);
//            ctx.getExternalContext().getSessionMap().put("subregion",this.subregion);
//            ctx.getExternalContext().getSessionMap().put("subestacion",this.subestacion);
            parametrosReporte.put("region",this.region);
            parametrosReporte.put("subregion",this.subregion);
            parametrosReporte.put("subestacion",this.subestacion);
        }  
                
//        ctx.getExternalContext().getSessionMap().put("itemsSTR",itemsSTR);
//        ctx.getExternalContext().getSessionMap().put("cantidadItems",cantidadItems);
        parametrosReporte.put("itemsSTR",itemsSTR);
        parametrosReporte.put("cantidadItems",cantidadItems);
        
    }
     
    /**
     * Metodo que obtiene los beans y invoca la generacion del reporte
     * @return success o error
     */
    @SuppressWarnings("unchecked")
    private String generarReporte(){  
      //  pasarParametrosReporte(); 
        String nombre = "";
        String nombreArchivo = "";
        HashMap parametrosReporte = new HashMap();
        try{                    
//            FacesContext context = FacesContext.getCurrentInstance();                       
//            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletReporte");
//            
//            dispatcher.forward(request, response);
//            response.getOutputStream().flush();
//
//            if(!FacesContext.getCurrentInstance().getResponseComplete() ) {
//                FacesContext.getCurrentInstance().responseComplete();
//            } 
             
           // parametrosReporte.put("itemsSTR",itemsSTR);
          // parametrosReporte.put("cantidadItems",cantidadItems);
            String itemsSTR = "0";       
            Integer cantidadItems = new Integer(0); 
            servletJasperPath = "/jasperReports/";
            
            
            SimpleDateFormat sf = new SimpleDateFormat();
           sf.applyPattern("dd/MM/yyyy");        
            
            //parametrosReporte.put("nivelRedActual",this.nivelRed);  
            parametrosReporte.put("SUBREPORT_DIR",servletJasperPath);   
            parametrosReporte.put("fechaInicio",sf.format(this.fechaInicio));
            parametrosReporte.put("fechaFinal",sf.format(this.fechaFinal));
            parametrosReporte.put("tipoFusible",this.tipoFusible);
            
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
//                ctx.getExternalContext().getSessionMap().put("region",this.region);  
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
//                ctx.getExternalContext().getSessionMap().put("region",this.region);
//                ctx.getExternalContext().getSessionMap().put("subregion",this.subregion);
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
//                ctx.getExternalContext().getSessionMap().put("region",this.region);
//                ctx.getExternalContext().getSessionMap().put("subregion",this.subregion);
//                ctx.getExternalContext().getSessionMap().put("subestacion",this.subestacion);
                parametrosReporte.put("region",this.region);
                parametrosReporte.put("subregion",this.subregion);
                parametrosReporte.put("subestacion",this.subestacion);
            }  
                    
//            ctx.getExternalContext().getSessionMap().put("itemsSTR",itemsSTR);
//            ctx.getExternalContext().getSessionMap().put("cantidadItems",cantidadItems);
            parametrosReporte.put("itemsSTR",itemsSTR);
            parametrosReporte.put("cantidadItems",cantidadItems);
            
            Date fechaActual = new Date();
            SimpleDateFormat spf = new SimpleDateFormat();
            spf.applyPattern("dd-MM-yyyy");                                
            String fechaActualSTR = spf.format(fechaActual);
            
            if(this.formato.equals(Integer.valueOf(1))){
                nombre = "FusiblesInstRet"+ fechaActualSTR +".pdf";
            }else{
                nombre = "FusiblesInstRet"+ fechaActualSTR +".xls";
            }   
            
            if(this.nivelRed.equals("region")){
                if(tipoFusible.equals(ReporteFusibleInstRetController.FUSIBLE_INSTALADO))
                    nombreArchivo = "SigeItrRepFusiblesInstaladosRegion";
                else
                    nombreArchivo = "SigeItrRepFusiblesRetiradosRegion";
            }
            if(this.nivelRed.equals("subregion")){
                if(tipoFusible.equals(ReporteFusibleInstRetController.FUSIBLE_INSTALADO))
                    nombreArchivo = "SigeItrRepFusiblesInstaladosSubregion";
                else
                    nombreArchivo = "SigeItrRepFusiblesRetiradosSubregion";
            }   
            if(this.nivelRed.equals("circuito")){
                if(tipoFusible.equals(ReporteFusibleInstRetController.FUSIBLE_INSTALADO))
                    nombreArchivo = "SigeItrRepFusiblesInstaladosCircuito";
                else
                    nombreArchivo = "SigeItrRepFusiblesRetiradosCircuito";
            }
            nombreArchivo += ".jasper";
            
           
            if (this.runReport(jasperPath + nombreArchivo, nombre,parametrosReporte,this.getConnection(),this.formato,UtilidadesFaces.getCurrentUserId())){
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Reporte Ejecutado ."));
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
            if(this.nivelRed.equals("circuito")){
                eliminarItemCircuito();
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
	 * @return the tipoFusible
	 */
	public Integer getTipoFusible() {
		return tipoFusible;
	}

	/**
	 * @param tipoFusible the tipoFusible to set
	 */
	public void setTipoFusible(Integer tipoFusible) {
		this.tipoFusible = tipoFusible;
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
