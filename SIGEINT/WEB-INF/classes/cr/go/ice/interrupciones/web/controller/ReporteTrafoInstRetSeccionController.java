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
import cr.go.ice.interrupciones.BO.*;
import cr.go.ice.interrupciones.domain.*;
import cr.go.ice.interrupciones.servlets.ServletReporte;
import cr.go.ice.interrupciones.utils.Usuario;
import cr.go.ice.interrupciones.utils.UtilidadesI;
import cr.go.ice.interrupciones.utils.UtilidadesFaces;
import cr.go.ice.interrupciones.web.Recurso;


/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ReporteTrafoInstRetSeccionController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ReporteTrafoInstRetSeccionController.java</code>.</p>
 * <p>Fecha creación: 15/07/2009</p>
 * <p>Ultima actualización: 15/07/2009</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ReporteTrafoInstRetSeccionController extends AbstractFacesController{

    private Date fechaInicio;
    private Date fechaFinal;    
    private Integer codigoOficina;
    private Integer formato;
    private boolean grupo;
    private Seccion seccion;

    private SeccionBO seccionBO;
    private OficinaBO oficinaBO;
    private EmpleadoBO empleadoBO;
    private UsuarioOficinaBO usuarioOficinaBO;
    
    private Seccion [] listaSeccionItems;
    private List items;    
    private  static final String JasperPath = "/jasperReports/";
private Long cedula;
    
    
    /**
     * Constructor por defecto
     */    
    public ReporteTrafoInstRetSeccionController(){
        reiniciarCampos();
    }
    
    /**
     * Metodo que reincia los atributos de la clase
     */
    private void reiniciarCampos(){         
        this.fechaInicio = new Date();
        this.fechaFinal = new Date();  
        this.formato = UtilidadesFaces.FORMATO_PDF;
        this.codigoOficina = new Integer(0);
        this.grupo = false;
        
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
        
        this.items = new ArrayList();
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
//          if(userClor){
              UsuarioCia persona = Usuario.getUsuarioObj();
              Long cedula = new Long(persona.getCedula());
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
       // }
                 
    }      
    
    
    public String getInit(FacesContext context){
        Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
        if(limpiar != null){
            reiniciarCampos();        
//          Comentado la restricción cuando se implementó el CIA
//          boolean userClor = Usuario.isUserClor();
//          if(userClor){
            String nombreUsuarioSession = (String) context.getApplication().createValueBinding("#{autorizacionController.nombreUsuario}").getValue(this.getFacesContext());
            String[] valores = nombreUsuarioSession.split("-");
            this.cedula = new Long(valores[0].trim());
                Empleado emp = this.empleadoBO.buscar(this.cedula);
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
     * Comment for listenerOficina
     * @param v
     * @return "listener" a oficina
     */
    public String listenerOficina(){                 
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
     * Lista de tipos de formatos para el reporte
     * @return lista de formatos
     */
    public List getListaFormatos(){
    	List formatos = new ArrayList();    	
        formatos.add(new SelectItem(UtilidadesFaces.FORMATO_PDF,"Pdf"));
        formatos.add(new SelectItem(UtilidadesFaces.FORMATO_XLS,"Excel"));
    	return formatos;
    }    
    
    
    private boolean validarParametros(){
        boolean correcto = true; 
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
        return correcto;
    }
    
    /**
     * Realiza las validaciones de los parámetros de entrada dados por el usuario y genera el reporte de trafos
     * instalados y retirados por sección
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
        String itemsSubestacionSTR = "0";
        String itemsCircuitoSTR = "0";   
        String itemsSTR = "0";   
        Integer cantidadItems = new Integer(0);  
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
            
            parametrosReporte.put("codigoOficina",this.codigoOficina);
            parametrosReporte.put("nombreOficina",this.oficinaBO.buscar(this.codigoOficina).getNombreOficina());
            SimpleDateFormat sf = new SimpleDateFormat();
            sf.applyPattern("dd/MM/yyyy");       
            
            parametrosReporte.put("fechaInicio",sf.format(this.fechaInicio));
            parametrosReporte.put("fechaFinal",sf.format(this.fechaFinal));
            
            Seccion sec = null;
            if(this.grupo){
                if(this.items.size() == 0){
                    itemsSubestacionSTR = this.seccion.getSeccionID().getSubEstacion().toString();
                    itemsCircuitoSTR = this.seccion.getSeccionID().getCircuito().toString();
                    itemsSTR = this.seccion.getSeccionID().getSeccion().toString();
                    cantidadItems = new Integer(1);
                    sec = this.seccion;
                }
                if(this.items.size() > 0){
                    itemsSubestacionSTR = UtilidadesI.getListaItemsSubestacionDeSeccion(this.items);
                    itemsCircuitoSTR = UtilidadesI.getListaItemsCircuitoDeSeccion(this.items);
                    itemsSTR = UtilidadesI.getListaItemsSeccion(this.items);
                    cantidadItems = new Integer(this.items.size());
                    sec = (Seccion)this.items.get(0);
                }
            }else{
                itemsSubestacionSTR = this.seccion.getSeccionID().getSubEstacion().toString();
                itemsCircuitoSTR = this.seccion.getSeccionID().getCircuito().toString();
                itemsSTR = this.seccion.getSeccionID().getSeccion().toString();
                cantidadItems = new Integer(1);
                sec = this.seccion;
            }
            
            parametrosReporte.put("itemsSubestacionSTR",itemsSubestacionSTR);
            parametrosReporte.put("itemsCircuitoSTR",itemsCircuitoSTR);
            parametrosReporte.put("itemsSTR",itemsSTR);
            parametrosReporte.put("cantidadItems",cantidadItems);
            String restriccionSQL = null;
            if(itemsSubestacionSTR.equals("0") && itemsCircuitoSTR.equals("0") && itemsSTR.equals("0"))
                restriccionSQL = "";
            else
                restriccionSQL = this.getSQLReporteTrafosInstRetirSeccion(itemsSubestacionSTR, itemsCircuitoSTR, itemsSTR);
            parametrosReporte.put("restriccionSQL",restriccionSQL);
            
            nombreArchivo = "SigeItrRepTrafosInstaladosRetiradosSeccion";                      
            
            nombre = "InterrupTransformadores";
            Date fechaActual = new Date();
            SimpleDateFormat spf = new SimpleDateFormat();
            spf.applyPattern("dd-MM-yyyy");                                
            String fechaActualSTR = spf.format(fechaActual);
             
            nombreArchivo += ".jasper";
            
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
    private String getSQLReporteTrafosInstRetirSeccion(String subestacionesSTR, String circuitosSTR, String seccionesSTR){
        StringBuffer resultado = new StringBuffer(" AND (");
        String codSubestaciones [] = subestacionesSTR.split(",");
        String codCircuitos [] = circuitosSTR.split(",");
        String codSecciones [] = seccionesSTR.split(",");
        for(int i = 0; i < codSubestaciones.length; i++){
            resultado.append(" (interup.sub = " + codSubestaciones[i].trim() +  " AND interup.cir = " + codCircuitos[i].trim() +  " AND interup.seccion = " + codSecciones[i].trim() + " ) OR");
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
        FacesContext ctx = FacesContext.getCurrentInstance();
        String itemsSubestacionSTR = "0";
        String itemsCircuitoSTR = "0";   
        String itemsSTR = "0";   
        Integer cantidadItems = new Integer(0);   
        
        ctx.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteTrafosInstRetSeccion));
        ctx.getExternalContext().getSessionMap().put("codigoOficina",this.codigoOficina);
        ctx.getExternalContext().getSessionMap().put("nombreOficina",this.oficinaBO.buscar(this.codigoOficina).getNombreOficina());
        SimpleDateFormat sf = new SimpleDateFormat();
        sf.applyPattern("dd/MM/yyyy");           
        ctx.getExternalContext().getSessionMap().put("fechaInicio",sf.format(this.fechaInicio));
        ctx.getExternalContext().getSessionMap().put("fechaFinal",sf.format(this.fechaFinal));
        ctx.getExternalContext().getSessionMap().put("formato",this.formato);      
        
        Seccion sec = null;
        if(this.grupo){
	        if(this.items.size() == 0){
                itemsSubestacionSTR = this.seccion.getSeccionID().getSubEstacion().toString();
                itemsCircuitoSTR = this.seccion.getSeccionID().getCircuito().toString();
	            itemsSTR = this.seccion.getSeccionID().getSeccion().toString();
	            cantidadItems = new Integer(1);
	            sec = this.seccion;
	        }
	        if(this.items.size() > 0){
                itemsSubestacionSTR = UtilidadesI.getListaItemsSubestacionDeSeccion(this.items);
                itemsCircuitoSTR = UtilidadesI.getListaItemsCircuitoDeSeccion(this.items);
	            itemsSTR = UtilidadesI.getListaItemsSeccion(this.items);
	            cantidadItems = new Integer(this.items.size());
	            sec = (Seccion)this.items.get(0);
	        }
        }else{
            itemsSubestacionSTR = this.seccion.getSeccionID().getSubEstacion().toString();
            itemsCircuitoSTR = this.seccion.getSeccionID().getCircuito().toString();
            itemsSTR = this.seccion.getSeccionID().getSeccion().toString();
            cantidadItems = new Integer(1);
            sec = this.seccion;
        }
        
        ctx.getExternalContext().getSessionMap().put("itemsSubestacionSTR",itemsSubestacionSTR);
        ctx.getExternalContext().getSessionMap().put("itemsCircuitoSTR",itemsCircuitoSTR);
        ctx.getExternalContext().getSessionMap().put("itemsSTR",itemsSTR);
        ctx.getExternalContext().getSessionMap().put("cantidadItems",cantidadItems);
        
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
            if(this.seccion.getSeccionID().getSeccion().longValue() > 0){
                if(this.items.contains(this.seccion) == false)
                    this.items.add(this.seccion);                   
            }
        }

        return "success";
    }    

    /**
     * Metodo modificador de seccionBO.
     * @param seccionBO El seccionBO a modificar.
     */
    public void setSeccionBO(SeccionBO seccionBO) {
        this.seccionBO = seccionBO;
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
