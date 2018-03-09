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

import cr.go.ice.cia.dominio.UsuarioCia;
import cr.go.ice.interrupciones.BO.*;
import cr.go.ice.interrupciones.domain.*;
import cr.go.ice.interrupciones.servlets.ServletReporte;
import cr.go.ice.interrupciones.utils.Usuario;
import cr.go.ice.interrupciones.utils.UtilidadesFaces;
import cr.go.ice.interrupciones.web.Recurso;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ReporteInterrupcionIndicadorPropioNoPropioController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ReporteInterrupcionIndicadorPropioNoPropioController.java</code>.</p>
 * <p>Fecha creación: 11/06/2008</p>
 * <p>Ultima actualización: 11/06/2008</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ReporteInterrupcionIndicadorPropioNoPropioController extends AbstractFacesController{

    private Integer ano;
    private Integer mes;    
    private Integer tipoInterrupcion;
    private Integer codigoOficina;
    private Integer tipoIndicador;

    private SeccionBO seccionBO;
    private OficinaBO oficinaBO;
    private InterrupcionBO interrupcionBO;
    private UsuarioOficinaBO usuarioOficinaBO;
    private EmpleadoBO empleadoBO;
    private Seccion seccion;
    private  static final String JasperPath = "/jasperReports/";
    private Integer formato;
    private Long cedula;
    
    /**
     * Constructor por defecto
     */    
    public ReporteInterrupcionIndicadorPropioNoPropioController(){
        reiniciarCampos();
        
    }
    
    public String getInit(){
    	 FacesContext context = FacesContext.getCurrentInstance();
    	 Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
         if(limpiar != null){
             reiniciarCampos();        
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
         return "success";
    }
    
    /**
     * Metodo que reincia los atributos de la clase
     */
    private void reiniciarCampos(){        
        Calendar calendar = Calendar.getInstance();
        this.ano = new Integer(calendar.get(Calendar.YEAR));
        this.mes = new Integer(calendar.get(Calendar.MONTH) + 1);
        this.tipoInterrupcion = new Integer(0);
        this.codigoOficina = new Integer(0);
        this.tipoIndicador = new Integer(1);
        
        this.seccion = new Seccion();
        this.seccion.setSeccionID(new SeccionID());
        this.seccion.getSeccionID().setSubEstacion(new Integer(0));
        this.seccion.getSeccionID().setCircuito(new Integer(0));
        this.seccion.getSeccionID().setSeccion(new Long(0));
        this.seccion.setRegion(new Integer(0));
        this.seccion.setSubRegion(new Integer(0));
        this.seccion.setNombreSeccion("a");
        this.seccion.setKmsSeccion(new Double(0));
        this.seccion.setAbonadoSeccion(new Long(0));
        this.formato = UtilidadesFaces.FORMATO_PDF;
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
    
    /**
     * Lista de meses en formato select item
     * @return lista de meses
     */    
    public List getListaMesSI(){
        return UtilidadesFaces.getListaMes();
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
     * Metodo accesor de secciones.
     * @return Retorna el secciones.
     */
    public List getSecciones() {
        List secciones = this.seccionBO.getSeccionesPorOficina(this.codigoOficina);
        List items = new ArrayList();
        
        Seccion seccionDummy = new Seccion();
        seccionDummy.setSeccionID(new SeccionID());
        seccionDummy.getSeccionID().setSubEstacion(new Integer(0));
        seccionDummy.getSeccionID().setCircuito(new Integer(0));
        seccionDummy.getSeccionID().setSeccion(new Long(0));
        seccionDummy.setRegion(new Integer(0));
        seccionDummy.setSubRegion(new Integer(0));
        seccionDummy.setNombreSeccion("a");
        seccionDummy.setKmsSeccion(new Double(0));
        seccionDummy.setAbonadoSeccion(new Long(0));
        
        items.add(new SelectItem(seccionDummy, "Todas"));
        if(secciones != null && !secciones.isEmpty()){
            for(int i = 0; i < secciones.size(); i++){
                Seccion seccion = (Seccion) secciones.get(i);
                SelectItem item = new SelectItem();
                item.setValue(seccion);
                item.setLabel(seccion.getSeccionID().getSubEstacion() + "-" + seccion.getSeccionID().getCircuito() + "-" + seccion.getSeccionID().getSeccion() + " - " + seccion.getNombreSeccion());
                items.add(item);
            }
        }
        return items;
    }   
    
    /**
     * Retorna una lista de select item de las oficinas existentes
     * @return Lista de oficinas
     */    
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
     * Retorna una lista de select item de los tipos de indicador existentes
     * @return Lista de tipos de indicador
     */   
    public List getListaTipoIndicador(){
        List indicadores = new ArrayList();        
        indicadores.add(new SelectItem(new Integer(1),"Propio"));
        indicadores.add(new SelectItem(new Integer(2),"No propio"));      
        return indicadores;      
    }
    
    
    private boolean validarParametros(){
        boolean correcto = true;
    
        if(this.codigoOficina == null || this.codigoOficina.intValue() <= 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La oficina es requerida."));
            correcto=false;
        }   
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
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El año no puede ser mayor al actual."));
            correcto=false;
        }
        
        int mesActual = calendar.get(Calendar.MONTH) + 1;
        if(this.ano.intValue() == anoActual && this.mes.intValue() > mesActual){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El mes no puede ser mayor al actual."));
            correcto=false;
        }
       
        return correcto;
    }
    
    /**
     * Realiza las validaciones de los parámetros de entrada dados por el usuario y genera el reporte de indicadores
     * propios y no propios
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
        HashMap parametrosReporte = new HashMap();
        Integer codigoVoltaje = null;        
        Integer tipoVoltaje = null;
        String nombre = "";
        String nombreArchivo = "";
        boolean utilizarDatosHistoricos = false;
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
           
            
           
            parametrosReporte.put("codigoOficina",this.codigoOficina);
            
            parametrosReporte.put("subestacion",this.seccion.getSeccionID().getSubEstacion());
            parametrosReporte.put("circuito",this.seccion.getSeccionID().getCircuito());
            parametrosReporte.put("seccion",this.seccion.getSeccionID().getSeccion());

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
            
            String tipoInterrupcion = getTipoInterrupcion(this.tipoInterrupcion.intValue());
            
            SimpleDateFormat sf = new SimpleDateFormat();
            sf.applyPattern("dd/MM/yyyy");
            
            parametrosReporte.put("tipoInterrupcion",tipoInterrupcion);
            parametrosReporte.put("ano",this.ano);
            parametrosReporte.put("mes",this.mes);
            parametrosReporte.put("anoHistorico",anoHistorico);
            parametrosReporte.put("mesHistorico",mesHistorico);
            
            String mesSTR = UtilidadesFaces.getDescripcionMes(this.mes).toLowerCase();
            parametrosReporte.put("mesSTR",mesSTR);
            parametrosReporte.put("tipoVoltaje",tipoVoltaje);
            parametrosReporte.put("codigoVoltaje",codigoVoltaje);
            
            
            if(tipoIndicador.intValue() == 1){
                if(utilizarDatosHistoricos)
                    nombreArchivo = "SigeItrRepInterrupcionIndicadorPropioSeccionHistorico";
                else                          
                    nombreArchivo = "SigeItrRepInterrupcionIndicadorPropioSeccion";
                nombre = "IndPropio";                   
            }   
            if(tipoIndicador.intValue() == 2){
                if(utilizarDatosHistoricos)
                    nombreArchivo = "SigeItrRepInterrupcionIndicadorNoPropioSeccionHistorico";
                else                         
                    nombreArchivo = "SigeItrRepInterrupcionIndicadorNoPropioSeccion";
                nombre = "IndNoPropio";
            }                      
            
             
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
    
    /**
     * Cancela el proceso de la generación de reportes
     * @return success
     */    
    public String cancelar(){
        return "success";
    }    
    
    private void pasarParametrosReporte(){
        
        
        Integer codigoVoltaje = null;        
        Integer tipoVoltaje = null;
           
        boolean utilizarDatosHistoricos = false;
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteIndicadorPropioNoPropio));
        ctx.getExternalContext().getSessionMap().put("tipoIndicador",this.tipoIndicador);
        ctx.getExternalContext().getSessionMap().put("codigoOficina",this.codigoOficina);
        
        ctx.getExternalContext().getSessionMap().put("subestacion",this.seccion.getSeccionID().getSubEstacion());
        ctx.getExternalContext().getSessionMap().put("circuito",this.seccion.getSeccionID().getCircuito());
        ctx.getExternalContext().getSessionMap().put("seccion",this.seccion.getSeccionID().getSeccion());

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
        
        String tipoInterrupcion = getTipoInterrupcion(this.tipoInterrupcion.intValue());
        
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
            
    private String getTipoInterrupcion(int tipoInterrupcion){
        if(tipoInterrupcion == 0)
            return "TOTAL";
        else if (tipoInterrupcion == Interrupcion.INTERRUPCION_SUMINISTRO.intValue())
            return "TRANSPORTE";
        else if (tipoInterrupcion == Interrupcion.INTERRUPCION_PRIMARIA.intValue())
            return "PRIMARIA";
        else if (tipoInterrupcion == Interrupcion.INTERRUPCION_SECUNDARIA.intValue())
            return "SECUNDARIA";
        else if (tipoInterrupcion == Interrupcion.INTERRUPCION_CATASTROFE.intValue())
            return "CATASTROFE";
        else if (tipoInterrupcion == Interrupcion.INTERRUPCION_DISTRIBUCION.intValue())  
            return "DISTRIBUCION";
        else
            return "";            
    }
    

    /**
     * Metodo modificador de seccionBO.
     * @param seccionBO El seccionBO a modificar.
     */
    public void setSeccionBO(SeccionBO seccionBO) {
        this.seccionBO = seccionBO;
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
     * Metodo modificador de oficinaBO.
     * @param oficinaBO El oficinaBO a modificar.
     */
    public void setOficinaBO(OficinaBO oficinaBO) {
        this.oficinaBO = oficinaBO;
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
     * Metodo accesor de tipoIndicador.
     * @return Retorna el tipoIndicador.
     */
    public Integer getTipoIndicador() {
        return this.tipoIndicador;
    }
    /**
     * Metodo modificador de tipoIndicador.
     * @param tipoIndicador El tipoIndicador a modificar.
     */
    public void setTipoIndicador(Integer tipoIndicador) {
        this.tipoIndicador = tipoIndicador;
    }
    /**
     * Metodo accesor de seccion.
     * @return Retorna el seccion.
     */
    public Seccion getSeccion() {
        return this.seccion;
    }
    /**
     * Metodo modificador de seccion.
     * @param seccion El seccion a modificar.
     */
    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }
    /**
     * Metodo modificador de empleadoBO.
     * @param empleadoBO El empleadoBO a modificar.
     */
    public void setEmpleadoBO(EmpleadoBO empleadoBO) {
        this.empleadoBO = empleadoBO;
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
