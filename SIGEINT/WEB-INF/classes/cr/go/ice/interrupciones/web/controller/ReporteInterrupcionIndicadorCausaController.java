package cr.go.ice.interrupciones.web.controller;

import java.io.IOException;
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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.vvs.jsf.AbstractFacesController;
import com.vvs.utilidades.Utilidades;

import cr.go.ice.interrupciones.BO.*;
import cr.go.ice.interrupciones.domain.*;
import cr.go.ice.interrupciones.servlets.ServletReporte;
import cr.go.ice.interrupciones.utils.UtilidadesI;
import cr.go.ice.interrupciones.utils.UtilidadesFaces;
import cr.go.ice.interrupciones.web.Recurso;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ReporteInterrupcionIndicadorCausaController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ReporteInterrupcionIndicadorCausaController.java</code>.</p>
 * <p>Fecha creación: 11/06/2008</p>
 * <p>Ultima actualización: 11/06/2008</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ReporteInterrupcionIndicadorCausaController extends AbstractFacesController{

    private Integer ano;
    private Integer mes;    
    private Integer tipoInterrupcion;
    private Integer codigoCausa;
    
    private String nivelRed;
    private boolean grupo;
        
    private Circuito circuitoObj;        
    private Long seccion;
    
    private CircuitoBO circuitoBO;
    private SeccionBO seccionBO;
    private CausaBO causaBO;  
    private InterrupcionBO interrupcionBO;
    
    private Circuito [] listaCircuitoItems;
    private Seccion [] listaSeccionItems;
    
    
    private List items;
    private  static final String JasperPath = "/jasperReports/";
    private Integer formato;
    
    /**
     * Constructor por defecto
     */    
    public ReporteInterrupcionIndicadorCausaController(){
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
        
        this.seccion = new Long(0);
        
        this.circuitoObj = new Circuito();
        this.circuitoObj.setCircuitoID(new CircuitoID());
        this.circuitoObj.setNombreCircuito("a");
        this.circuitoObj.getCircuitoID().setCircuito(new Integer(0));
        this.circuitoObj.getCircuitoID().setSubEstacion(new SubEstacion());
        this.circuitoObj.getCircuitoID().getSubEstacion().setCodigoSubEstacion(new Integer(0));
        
        this.codigoCausa = new Integer(413);
        
        this.items = new ArrayList();
        
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
           
    /**
     * Retorna una lista de select item de los diferentes niveles de red
     * @return Lista de niveles de red
     */    
    public List getListaNivelesRed(){
        return UtilidadesFaces.getListaNivelesRedCircuito();
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
    	this.circuitoObj = new Circuito();
    	circuitoObj.setCircuitoID(new CircuitoID());
    	circuitoObj.setNombreCircuito("a");
    	circuitoObj.getCircuitoID().setCircuito(new Integer(0));
    	circuitoObj.getCircuitoID().setSubEstacion(new SubEstacion());
    	circuitoObj.getCircuitoID().getSubEstacion().setCodigoSubEstacion(new Integer(0));
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
     * Retorna una lista de select item de los tipos de interrupción
     * @return Lista de tipos de interrupción
     */    
    public List getListaTipoInterrupcion(){
        List tramo = new ArrayList();         
        if(this.codigoCausa.intValue() == 413){
            tramo.add(new SelectItem(new Integer(6),"6 - Primario"));
            tramo.add(new SelectItem(new Integer(7),"7 - Secundario"));
            tramo.add(new SelectItem(new Integer(8),"8 - Distribución"));
        }else if(this.codigoCausa.intValue() == 421){
            tramo.add(new SelectItem(new Integer(9),"9 - Avería en sistema de transporte"));
        }else if(this.codigoCausa.intValue() == 422){
            tramo.add(new SelectItem(new Integer(10),"10 - Baja frecuencia"));
        }else if(this.codigoCausa.intValue() == 423){
            tramo.add(new SelectItem(new Integer(11),"11 - Suspensión programada en transporte"));
        }else if(this.codigoCausa.intValue() == 424){
            tramo.add(new SelectItem(new Integer(12),"12 - Error humano en trasporte"));
        }else{
            tramo.add(new SelectItem(new Integer(4),"4 - Catástrofe"));
        }
        return tramo;       
    }
    
    /**
     * Metodo accesor de causas.
     * @return Retorna el causas.
     */
    public List getCausas(){
        List causas = this.causaBO.getCausasIndicadores();
        List items = new ArrayList();
        if(causas != null && !causas.isEmpty()){
            for(int i = 0; i < causas.size(); i++){
                Causa causa = (Causa) causas.get(i);
                SelectItem item = new SelectItem();
                item.setValue(causa.getCodigoCausa());
                item.setLabel(causa.getCodigoCausa() + " - " + causa.getNombreCausa());
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
        
        List circuitos = this.circuitoBO.getCircuitosOrdenPorCodigo();
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
                item.setLabel(circuito.getCircuitoID().getSubEstacion().getCodigoSubEstacion() + "-" + circuito.getCircuitoID().getCircuito() + " - " + circuito.getNombreCircuito());
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
        List secciones = this.seccionBO.getTodasSecciones(this.circuitoObj.getCircuitoID().getSubEstacion().getCodigoSubEstacion(), this.circuitoObj.getCircuitoID().getCircuito());
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
    
    
    private boolean validarParametros(){
        boolean correcto = true;
        FacesContext context = FacesContext.getCurrentInstance();
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
        FacesContext context = FacesContext.getCurrentInstance();
        Calendar calendar = Calendar.getInstance();
        
        int anoActual = calendar.get(Calendar.YEAR);       
        if(this.ano.intValue() > anoActual){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El año no puede ser mayor al actual."));
            correcto=false;
        }
        
        int mesActual = calendar.get(Calendar.MONTH) + 1;
        if(this.ano.intValue() == anoActual && this.mes.intValue() > mesActual){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El mes no puede ser mayor al actual."));
            correcto=false;
        }
 
        if(this.nivelRed.equals("seccion")){       
            if(this.getCircuitoObj().getCircuitoID().getCircuito().intValue() <= 0){
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
        //pasarParametrosReporte();  
        String nombre = "";
        String nombreArchivo = "";
        HashMap parametrosReporte = new HashMap();
        try{    
            String  circuitosSTR = "0";
            Integer cantidadCircuitos = new Integer(0);
            String  seccionesSTR = "0";
            Integer cantidadSecciones = new Integer(0);
            String  subestacionesSTR = "0";
            Integer cantidadSubestaciones = new Integer(0);        
            Integer codigoVoltaje = null;        
            Integer tipoVoltaje = null;
               
            boolean utilizarDatosHistoricos = false;
            
            
           
            
            if(this.nivelRed.equals("circuito")){
                if(this.items.size() == 0 && this.getCircuitoObj().getCircuitoID().getCircuito().intValue() > 0){
                    subestacionesSTR = this.getCircuitoObj().getCircuitoID().getSubEstacion().getCodigoSubEstacion().toString();
                    cantidadSubestaciones = new Integer(1);                
                    circuitosSTR = this.getCircuitoObj().getCircuitoID().getCircuito().toString();
                    cantidadCircuitos = new Integer(1);                
                }
                if(this.items.size() > 0){
                    subestacionesSTR = UtilidadesI.getListaItemsSubestacionFromListaCircuitos(this.items);
                    cantidadSubestaciones = new Integer(this.items.size());                
                    circuitosSTR = UtilidadesI.getListaItemsCircuito(this.items);
                    cantidadCircuitos = new Integer(this.items.size());                                
                }
            }  
            
            if(this.nivelRed.equals("seccion")){
                if(this.items.size() == 0 && this.seccion.longValue() > 0){
                    subestacionesSTR = this.getCircuitoObj().getCircuitoID().getSubEstacion().getCodigoSubEstacion().toString();
                    cantidadSubestaciones = new Integer(1);                
                    circuitosSTR = this.getCircuitoObj().getCircuitoID().getCircuito().toString();
                    cantidadCircuitos = new Integer(1);   
                    seccionesSTR = this.seccion.toString();
                    cantidadSecciones = new Integer(1);                   
                }
                
                if(this.items.size() == 0 && this.seccion.longValue() == 0){
                    subestacionesSTR = this.getCircuitoObj().getCircuitoID().getSubEstacion().getCodigoSubEstacion().toString();
                    cantidadSubestaciones = new Integer(1);                
                    circuitosSTR = this.getCircuitoObj().getCircuitoID().getCircuito().toString();
                    cantidadCircuitos = new Integer(1);   
                    seccionesSTR = this.seccion.toString();
                    cantidadSecciones = new Integer(0);   
                }            
                
                if(this.items.size() > 0){
                    subestacionesSTR = UtilidadesI.getListaItemsSubestacionFromListaSecciones(this.items);
                    cantidadSubestaciones = new Integer(this.items.size());                
                    circuitosSTR = UtilidadesI.getListaItemsCircuitoFromListaSecciones(this.items);
                    cantidadCircuitos = new Integer(this.items.size());  
                    seccionesSTR = UtilidadesI.getListaItemsSeccion(this.items);
                    cantidadSecciones = new Integer(this.items.size());
                }
            } 
            
//            parametrosReporte.put("subestacionesSTR",subestacionesSTR);
//            parametrosReporte.put("cantidadSubestaciones",cantidadSubestaciones);
//            parametrosReporte.put("circuitosSTR",circuitosSTR);
//            parametrosReporte.put("cantidadCircuitos",cantidadCircuitos);
//            parametrosReporte.put("seccionesSTR",seccionesSTR);
//            parametrosReporte.put("cantidadSecciones",cantidadSecciones);
            
            tipoVoltaje = this.tipoInterrupcion;                                
            if(this.codigoCausa.intValue() == 417){
                codigoVoltaje = new Integer(66);
            }else{            
                codigoVoltaje = new Integer(0);
            }
            
                                                     
            GregorianCalendar fechaParametro = new GregorianCalendar();
            fechaParametro.set(GregorianCalendar.YEAR, this.ano.intValue());
            fechaParametro.set(GregorianCalendar.MONTH, this.mes.intValue() - 1);
            fechaParametro.set(GregorianCalendar.DAY_OF_MONTH, 1);
            
            utilizarDatosHistoricos = this.interrupcionBO.utilizarDatosHistoricos(fechaParametro.getTime());
            
            Integer mesHistorico = null;
            Integer anoHistorico = null;
            if(utilizarDatosHistoricos){
                
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
            
            String nombreCausa = this.causaBO.buscar(this.codigoCausa).getNombreCausa();
            
            parametrosReporte.put("nombreCausa",nombreCausa);
            
            String tipoInterrupcion = getTipoInterrupcion(this.tipoInterrupcion);
            tipoInterrupcion = (tipoInterrupcion == null) ? "Todos los tipos de interrupción" : tipoInterrupcion;
            
            SimpleDateFormat sf = new SimpleDateFormat();
            sf.applyPattern("dd/MM/yyyy");      
            
            String mesSTR = UtilidadesFaces.getDescripcionMes(this.mes).toLowerCase();
            
            parametrosReporte.put("tipoInterrupcion",tipoInterrupcion);
            parametrosReporte.put("ano",this.ano);
            parametrosReporte.put("mes",this.mes);
            parametrosReporte.put("causa",this.codigoCausa);
            
            parametrosReporte.put("anoHistorico",anoHistorico);
            parametrosReporte.put("mesHistorico",mesHistorico);
            
            parametrosReporte.put("mesSTR",mesSTR);
            parametrosReporte.put("tipoVoltaje",tipoVoltaje);
            parametrosReporte.put("codigoVoltaje",codigoVoltaje);
            
            String restriccionSQL = "";
            
            
            if(nivelRed.equals("circuito")){
                if(cantidadCircuitos.intValue() > 0)
                    restriccionSQL = this.getSQLReporteIndicadorCircuito(subestacionesSTR, circuitosSTR);
                parametrosReporte.put("restriccionSQL",restriccionSQL);
                if(utilizarDatosHistoricos)
                    nombreArchivo = "SigeItrRepInterrupcionIndicadorCausaCircuitoHistorico";
                else                          
                    nombreArchivo = "SigeItrRepInterrupcionIndicadorCausaCircuito";
                                    
            }   
            if(nivelRed.equals("seccion")){
                if(cantidadSecciones.intValue() > 0)
                    restriccionSQL = this.getSQLReporteIndicadorSeccion(subestacionesSTR, circuitosSTR, seccionesSTR);
                else
                    restriccionSQL = this.getSQLReporteIndicadorCircuito(subestacionesSTR, circuitosSTR);
                parametrosReporte.put("restriccionSQL",restriccionSQL);
                if(utilizarDatosHistoricos)
                    nombreArchivo = "SigeItrRepInterrupcionIndicadorCausaSeccionHistorico";
                else                         
                    nombreArchivo = "SigeItrRepInterrupcionIndicadorCausaSeccion";
                
            } 
            
            nombre = "IndCausaMT";
            Date fechaActual = new Date();
            SimpleDateFormat spf = new SimpleDateFormat();
            spf.applyPattern("dd-MM-yyyy");                                
            String fechaActualSTR = spf.format(fechaActual);
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
    private String getSQLReporteIndicadorCircuito(String subestacionesSTR, String circuitosSTR){
        StringBuffer resultado = new StringBuffer( " AND (");
        String codSubestaciones [] = subestacionesSTR.split(",");
        String codCircuitos [] = circuitosSTR.split(",");
        for(int i = 0; i < codSubestaciones.length; i++){
            resultado.append(" (indicador.sub = " + codSubestaciones[i].trim() +  " AND indicador.cir = " + codCircuitos[i].trim() +  " ) OR");
        }
        resultado = new StringBuffer(resultado.substring(0,resultado.length() - 2)).append(") ");
        return resultado.toString();
    }
    private String getSQLReporteIndicadorSeccion(String subestacionesSTR, String circuitosSTR, String seccionesSTR){
        StringBuffer resultado = new StringBuffer( " AND (");
        String codSubestaciones [] = subestacionesSTR.split(",");
        String codCircuitos [] = circuitosSTR.split(",");
        String codSecciones [] = seccionesSTR.split(",");
        for(int i = 0; i < codSubestaciones.length; i++){
            resultado.append(" (indicador.sub = " + codSubestaciones[i].trim() +  " AND indicador.cir = " + codCircuitos[i].trim() + " AND indicador.seccion = " + codSecciones[i].trim() + " ) OR");
        }
        resultado = new StringBuffer(resultado.substring(0,resultado.length() - 2)).append(") ");        
        return resultado.toString();
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
        String  seccionesSTR = "0";
        Integer cantidadSecciones = new Integer(0);
        String  subestacionesSTR = "0";
        Integer cantidadSubestaciones = new Integer(0);        
        Integer codigoVoltaje = null;        
        Integer tipoVoltaje = null;
           
        boolean utilizarDatosHistoricos = false;
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteIndicadorCausa));
        ctx.getExternalContext().getSessionMap().put("nivelRedActual",this.nivelRed);
        ctx.getExternalContext().getSessionMap().put("codigoCausa",this.codigoCausa);
                                
        if(this.nivelRed.equals("circuito")){
            if(this.items.size() == 0 && this.getCircuitoObj().getCircuitoID().getCircuito().intValue() > 0){
                subestacionesSTR = this.getCircuitoObj().getCircuitoID().getSubEstacion().getCodigoSubEstacion().toString();
                cantidadSubestaciones = new Integer(1);                
                circuitosSTR = this.getCircuitoObj().getCircuitoID().getCircuito().toString();
                cantidadCircuitos = new Integer(1);                
            }
            if(this.items.size() > 0){
                subestacionesSTR = UtilidadesI.getListaItemsSubestacionFromListaCircuitos(this.items);
                cantidadSubestaciones = new Integer(this.items.size());                
                circuitosSTR = UtilidadesI.getListaItemsCircuito(this.items);
                cantidadCircuitos = new Integer(this.items.size());                                
            }
        }  
        
        if(this.nivelRed.equals("seccion")){
            if(this.items.size() == 0 && this.seccion.longValue() > 0){
                subestacionesSTR = this.getCircuitoObj().getCircuitoID().getSubEstacion().getCodigoSubEstacion().toString();
                cantidadSubestaciones = new Integer(1);                
                circuitosSTR = this.getCircuitoObj().getCircuitoID().getCircuito().toString();
                cantidadCircuitos = new Integer(1);   
                seccionesSTR = this.seccion.toString();
                cantidadSecciones = new Integer(1);                   
            }
            
            if(this.items.size() == 0 && this.seccion.longValue() == 0){
                subestacionesSTR = this.getCircuitoObj().getCircuitoID().getSubEstacion().getCodigoSubEstacion().toString();
                cantidadSubestaciones = new Integer(1);                
                circuitosSTR = this.getCircuitoObj().getCircuitoID().getCircuito().toString();
                cantidadCircuitos = new Integer(1);   
                seccionesSTR = this.seccion.toString();
                cantidadSecciones = new Integer(0);   
            }            
            
            if(this.items.size() > 0){
                subestacionesSTR = UtilidadesI.getListaItemsSubestacionFromListaSecciones(this.items);
                cantidadSubestaciones = new Integer(this.items.size());                
                circuitosSTR = UtilidadesI.getListaItemsCircuitoFromListaSecciones(this.items);
                cantidadCircuitos = new Integer(this.items.size());  
                seccionesSTR = UtilidadesI.getListaItemsSeccion(this.items);
                cantidadSecciones = new Integer(this.items.size());
            }
        } 
        
        ctx.getExternalContext().getSessionMap().put("subestacionesSTR",subestacionesSTR);
        ctx.getExternalContext().getSessionMap().put("cantidadSubestaciones",cantidadSubestaciones);
        ctx.getExternalContext().getSessionMap().put("circuitosSTR",circuitosSTR);
        ctx.getExternalContext().getSessionMap().put("cantidadCircuitos",cantidadCircuitos);
        ctx.getExternalContext().getSessionMap().put("seccionesSTR",seccionesSTR);
        ctx.getExternalContext().getSessionMap().put("cantidadSecciones",cantidadSecciones);
        
        tipoVoltaje = this.tipoInterrupcion;                                
        if(this.codigoCausa.intValue() == 417){
            codigoVoltaje = new Integer(66);
        }else{            
            codigoVoltaje = new Integer(0);
        }
        
                                                 
        GregorianCalendar fechaParametro = new GregorianCalendar();
        fechaParametro.set(GregorianCalendar.YEAR, this.ano.intValue());
        fechaParametro.set(GregorianCalendar.MONTH, this.mes.intValue() - 1);
        fechaParametro.set(GregorianCalendar.DAY_OF_MONTH, 1);
        
        utilizarDatosHistoricos = this.interrupcionBO.utilizarDatosHistoricos(fechaParametro.getTime());
        
        Integer mesHistorico = null;
        Integer anoHistorico = null;
        if(utilizarDatosHistoricos){
            
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
        
        String nombreCausa = this.causaBO.buscar(this.codigoCausa).getNombreCausa();
        ctx.getExternalContext().getSessionMap().put("nombreCausa",nombreCausa);
        
        String tipoInterrupcion = getTipoInterrupcion(this.tipoInterrupcion);
        tipoInterrupcion = (tipoInterrupcion == null) ? "Todos los tipos de interrupción" : tipoInterrupcion;
        
        SimpleDateFormat sf = new SimpleDateFormat();
        sf.applyPattern("dd/MM/yyyy");

        ctx.getExternalContext().getSessionMap().put("tipoInterrupcion",tipoInterrupcion);
        ctx.getExternalContext().getSessionMap().put("ano",this.ano);
        ctx.getExternalContext().getSessionMap().put("mes",this.mes);
        
        ctx.getExternalContext().getSessionMap().put("utilizarDatosHistoricos",Boolean.valueOf(utilizarDatosHistoricos));
                
        ctx.getExternalContext().getSessionMap().put("anoHistorico",anoHistorico);
        ctx.getExternalContext().getSessionMap().put("mesHistorico",mesHistorico);        
        
        String mesSTR = UtilidadesFaces.getDescripcionMes(this.mes).toLowerCase();
        ctx.getExternalContext().getSessionMap().put("mesSTR",mesSTR);
        
        ctx.getExternalContext().getSessionMap().put("tipoVoltaje",tipoVoltaje);
        ctx.getExternalContext().getSessionMap().put("codigoVoltaje",codigoVoltaje);
        

    }    
    
    private String getTipoInterrupcion(Integer tipoInterrupcion){
        switch(tipoInterrupcion.intValue()){
            case 6:return "PRIMARIO";
            case 7:return "SECUNDARIO";
            case 8:return "DISTRIBUCION";
            case 9:return "TRANSMISION";
            case 10:return "TRANSMISION";
            case 11:return "TRANSMISION";
            case 12:return "TRANSMISION";
            case 4:return "CATASTROFE";
            default:return "";
        }
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
                item.setLabel(circuito.getCircuitoID().getSubEstacion().getCodigoSubEstacion() + "-" +circuito.getCircuitoID().getCircuito() + " - " + circuito.getNombreCircuito());           
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
                item.setLabel(seccion.getSeccionID().getSubEstacion() + "-" + seccion.getSeccionID().getCircuito() + "-" + seccion.getSeccionID().getSeccion() + " - " + seccion.getNombreSeccion());           
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
            if(this.nivelRed.equals("circuito") && this.getCircuitoObj().getCircuitoID().getCircuito().intValue() > 0){
                Circuito circuito = this.circuitoBO.buscar(this.getCircuitoObj().getCircuitoID().getSubEstacion().getCodigoSubEstacion(), this.getCircuitoObj().getCircuitoID().getCircuito());
                if(this.items.contains(circuito) == false)
                    this.items.add(circuito); 
            }    
            if(this.nivelRed.equals("seccion") && this.seccion.longValue() > 0){
                SeccionID id = new SeccionID();
                id.setSubEstacion(this.getCircuitoObj().getCircuitoID().getSubEstacion().getCodigoSubEstacion());
                id.setCircuito(this.getCircuitoObj().getCircuitoID().getCircuito());
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
            if(this.nivelRed.equals("circuito")){
                eliminarItemCircuito();
            }    
            if(this.nivelRed.equals("seccion") ){
                eliminarItemSeccion();
            }    
        }
        return "success";
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
     * Metodo modificador de seccionBO.
     * @param seccionBO El seccionBO a modificar.
     */
    public void setSeccionBO(SeccionBO seccionBO) {
        this.seccionBO = seccionBO;
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
     * Metodo modificador de interrupcionBO.
     * @param interrupcionBO El interrupcionBO a modificar.
     */
    public void setInterrupcionBO(InterrupcionBO interrupcionBO) {
        this.interrupcionBO = interrupcionBO;
    }

    /**
     * Metodo modificador de causaBO.
     * @param causaBO El causaBO a modificar.
     */
    public void setCausaBO(CausaBO causaBO) {
        this.causaBO = causaBO;
    }

    /**
     * Comentario para getCodigoCausa
     * @return
     */
    public Integer getCodigoCausa() {
        return this.codigoCausa;
    }

    /**
     * Comentario para setCodigoCausa
     * @param codigoCausa
     */
    public void setCodigoCausa(Integer codigoCausa) {
        this.codigoCausa = codigoCausa;
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
