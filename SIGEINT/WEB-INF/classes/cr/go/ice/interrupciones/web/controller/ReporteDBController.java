package cr.go.ice.interrupciones.web.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.component.datatable.DataTable;
import org.springframework.dao.DataIntegrityViolationException;

import com.vvs.jsf.AbstractFacesController;
import com.vvs.reporte.ReportesDinamicos;
import com.vvs.reporte.ReportesDinamicosMod;
import com.vvs.reporte.ReportesDinamicosSub;


import cr.go.ice.interrupciones.BO.GenericoBo;
//import cr.go.ice.interrupciones.utils.Utilidades;
import cr.go.ice.interrupciones.web.Recurso;
import com.vvs.utilidades.Utilidades;

/**
 * <p>TODO <<Interface|Clase>> cr.go.ice.interrupciones.web.controller.ReporteDBController.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>ReporteDBController.java</code>.</p>
 * <p>Fecha creación: 29/04/2015</p>
 * <p>Última actualización: 29/04/2015</p>
 * @author Vista Verde Tecnología (root)
 * @version 1.0
 */
/**
 * <p>TODO <<Interface|Clase>> cr.go.ice.interrupciones.web.controller.ReporteDBController.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>ReporteDBController.java</code>.</p>
 * <p>Fecha creación: 29/04/2015</p>
 * <p>Última actualización: 29/04/2015</p>
 * @author Vista Verde Tecnología (root)
 * @version 1.0
 */
/**
 * <p>TODO <<Interface|Clase>> cr.go.ice.interrupciones.web.controller.ReporteDBController.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>ReporteDBController.java</code>.</p>
 * <p>Fecha creación: 29/04/2015</p>
 * <p>Última actualización: 29/04/2015</p>
 * @author Vista Verde Tecnología (root)
 * @version 1.0
 */
/**
 * <p>TODO <<Interface|Clase>> cr.go.ice.interrupciones.web.controller.ReporteDBController.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>ReporteDBController.java</code>.</p>
 * <p>Fecha creación: 29/04/2015</p>
 * <p>Última actualización: 29/04/2015</p>
 * @author Vista Verde Tecnología (root)
 * @version 1.0
 */
public class ReporteDBController extends AbstractFacesController {
	
    private DataTable reporteDataTable;
    private DataTable subreporteDataTable;
    private List<ReportesDinamicos> listReporte;
    private List<ReportesDinamicosSub> listSubReporte;
    
    private ReportesDinamicos reportesDinamicos;
    private ReportesDinamicos reporteAsociado;
    
    private String nombreDigitador;
    private String nombreSolicita;
    
    private Boolean actualizacion;
    private Boolean mostrarTodos;
    private Boolean iReport;
    
    private byte[] archJrxml;
    private byte[] archJasper;    
   
    //BO's
    private GenericoBo genericoBo;
    
    /**
     * Constructor
     */
    public ReporteDBController() {
        super();
        this.resetController();
    }
    
   
    public void init(FacesContext context){
        Object init = context.getExternalContext().getRequestParameterMap().get("init");
        if(init != null){
            this.resetController();
        }
    }

    @Override
    protected void resetController() {
        
        this.reporteDataTable = new DataTable();
        this.subreporteDataTable = new DataTable();
        this.listReporte = new  ArrayList<ReportesDinamicos>();
        
        this.reportesDinamicos = new ReportesDinamicos();
        this.archJasper = null;
        this.archJrxml = null;
        
        this.actualizacion = Boolean.FALSE;
        this.mostrarTodos = Boolean.FALSE;
        this.iReport = Boolean.TRUE;
        
        this.reporteAsociado = new ReportesDinamicos();
        this.listSubReporte = new ArrayList<ReportesDinamicosSub>();
        
        this.nombreDigitador = "N/A";
        this.nombreSolicita = "N/A";
    }
    
    /**
     * 
     * Método listenerAdjuntarJasper
     * Metodo listener captura un evento de pantalla se modifico pues no estaba funcionando correctamente ahora solo se usa para cargar el valor en el objeto reportesDinamicos
     * 
     */
    public void listenerAdjuntarJasper() {
        try{
            if(this.archJasper != null){
                this.reportesDinamicos.setJasper(this.archJasper);
            }
        }catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falla Archivo."));
            System.out.println("falla archivo");
            e.printStackTrace();
        }
    }
    
   
    public void listenerAdjuntarJrxml() {
        try{
            if(this.archJrxml != null){
                this.reportesDinamicos.setJrxml(this.archJrxml);
            }
        }catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falla Archivo."));
            e.printStackTrace();
        }
    }
    
  
    public String descargarJasper (){
        try{
            
            ReportesDinamicos objAux= (ReportesDinamicos)reporteDataTable.getRowData();
            HttpServletResponse response = (HttpServletResponse) this.getFacesContext().getExternalContext().getResponse();
            String path= objAux.getIdentificador()+".jasper";
            response.setContentType("application/*");
            response.setHeader("Content-disposition","attachment;filename=" +path);
            InputStream in =new ByteArrayInputStream(objAux.getJasper()); 
            OutputStream outs = response.getOutputStream();
            int b;
               while ((b = in.read()) != -1) {
                   outs.write(b);
                }
            in.close();
            outs.flush();
            outs.close();
            this.getFacesContext().responseComplete();
            
            return "success";
        } catch(Exception e){
            e.printStackTrace();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
            return "error";
        }
    }
    
    
    /**
     * Método descargarJrxml
     * TODO (Descripción) 
     * @return ""
     */
    public String descargarJrxml (){
        try{
            ReportesDinamicos objAux= (ReportesDinamicos)reporteDataTable.getRowData();
       
            HttpServletResponse response = (HttpServletResponse) this.getFacesContext().getExternalContext().getResponse();
           // HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            //response.reset();
          //  response.setHeader("pragma", "no-cache");
          //  response.setHeader("Cache-control","no-cache, no-store, must-revalidate") ;
            response.setContentType("application/*");
            String path= objAux.getIdentificador()+".jrxml";
            response.setHeader("Content-Disposition", "attachment;filename=\""+path+"\"");
            InputStream in =new ByteArrayInputStream(objAux.getJrxml()); 
            OutputStream outs = response.getOutputStream();
            int b;
               while ((b = in.read()) != -1) {
                   outs.write(b);
                }
            
            in.close();
            outs.flush();
            outs.close();
            responseComplete();
            
            return "success";
        } catch(Exception e){
            e.printStackTrace();
            this.addError(null, e.getMessage());
            return "error";
        }
    }
    
    public String descargarVerDetalleJasper (){
        try{
            
            ReportesDinamicos objAux= this.reportesDinamicos;
            HttpServletResponse response = (HttpServletResponse) this.getFacesContext().getExternalContext().getResponse();
            String path= objAux.getIdentificador()+".jasper";
            response.setContentType("application/*");
            response.setHeader("Content-disposition","attachment;filename=" +path);
            InputStream in =new ByteArrayInputStream(objAux.getJasper()); 
            OutputStream outs = response.getOutputStream();
            int b;
               while ((b = in.read()) != -1) {
                   outs.write(b);
                }
            in.close();
            outs.flush();
            outs.close();
            this.getFacesContext().responseComplete();
            
            return "success";
        } catch(Exception e){
            e.printStackTrace();
            this.addError(null, e.getMessage());
            return "error";
        }
    }
    public String descargarVerDetalleJrxml (){
        try{
            ReportesDinamicos objAux= this.reportesDinamicos;
       
            HttpServletResponse response = (HttpServletResponse) this.getFacesContext().getExternalContext().getResponse();
           // HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            //response.reset();
          //  response.setHeader("pragma", "no-cache");
          //  response.setHeader("Cache-control","no-cache, no-store, must-revalidate") ;
            response.setContentType("application/*");
            String path= objAux.getIdentificador()+".jrxml";
            response.setHeader("Content-Disposition", "attachment;filename=\""+path+"\"");
            InputStream in =new ByteArrayInputStream(objAux.getJrxml()); 
            OutputStream outs = response.getOutputStream();
            int b;
               while ((b = in.read()) != -1) {
                   outs.write(b);
                }
            
            in.close();
            outs.flush();
            outs.close();
            responseComplete();
            
            return "success";
        } catch(Exception e){
            e.printStackTrace();
            this.addError(null, e.getMessage());
            return "error";
        }
    }
    
  
    public String getIp() {
        return Utilidades.getHostIp();
    }


    
 
    /**
     * Método agregarReporte
     * TODO (Descripción) 
     * @return ""
     */
    public String agregarReporte(){
        try{
            if(this.validarDatosInsertar()) {
                this.reportesDinamicos.setCedulaDigitador((Long) this.getValueBinding("#{seguridadController.usuarioConectado.cedula}",Long.class));
                this.reportesDinamicos.setIp(this.getIp());
                    if(!this.listSubReporte.isEmpty()){
                        this.reportesDinamicos.setSubreporte(ReportesDinamicos.SUBREPORTE_EXISTE);
                    }else{
                        this.reportesDinamicos.setSubreporte(ReportesDinamicos.SUBREPORTE_NO_EXISTE);
                    }
                    this.genericoBo.agregar(this.reportesDinamicos, this.listSubReporte);
                    this.addInfo(null,Recurso.getEtiqueta("datosAgregados"));
                    this.actualizacion = Boolean.TRUE;
                    this.resetController();
                    return "success";
            }
            else{
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", Recurso.getEtiqueta("datosNoAlmacenados") ));
                return "error";           
            }
        }
        catch(Exception e){
            e.printStackTrace();
            this.addError(null,e.getMessage());
            return "error";   
        }
    }    

    
    /**
     * Método modificarReporte
     * TODO (Descripción) 
     * @return "success"
     */
    public String modificarReporte(){
        try{
            if(this.validarDatosInsertar()){
                if(!this.listSubReporte.isEmpty()){
                    this.reportesDinamicos.setSubreporte(ReportesDinamicos.SUBREPORTE_EXISTE);
                }else{
                    this.reportesDinamicos.setSubreporte(ReportesDinamicos.SUBREPORTE_NO_EXISTE);
                }
               // this.reportesDinamicos.setFechaModifica(new Date());
                this.genericoBo.modificar(this.reportesDinamicos, this.listSubReporte);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", Recurso.getEtiqueta("reporteMant.modificar") ));
                this.resetController();
                    return "success";
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", Recurso.getEtiqueta("datosNoAlmacenados") ));
                return "error";           
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() ));
            return "error";
        }
    }    
    
    
    /**
     * Método eliminarReporte
     * TODO (Descripción) 
     * @return true or false
     */
    public String eliminarReporte() {
        try{
            this.reportesDinamicos = (ReportesDinamicos) this.reporteDataTable.getRowData();
            this.genericoBo.eliminar(this.reportesDinamicos);
            this.resetController();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", Recurso.getEtiqueta("reporteMan.eliminar") ));
            return "success";
        }
        catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            //this.addError (null, Recurso.getEtiqueta("eliminar.error.existeHijos"));
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", Recurso.getEtiqueta("eliminar.error.existeHijos") ));
            return "error";
        }
        catch(Exception e)
        {
            e.printStackTrace();
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() ));
            return "error";         
        }
    }    

    
    /**
     * Método validarDatosInsertar
     * TODO (Descripción) 
     * @return true or false
     */
    private boolean validarDatosInsertar() {
        boolean correcto = Boolean.TRUE;
        this.listenerAdjuntarJasper();
        this.listenerAdjuntarJrxml();
        if((this.reportesDinamicos.getDescripcion().trim() == null) || (this.reportesDinamicos.getDescripcion().trim().equals(""))){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La Descripción es requerido" ));
            correcto = Boolean.FALSE;
        }
        if((this.reportesDinamicos.getIdentificador().trim() == null) || (this.reportesDinamicos.getIdentificador().trim().equals(""))){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El identificador es requerido" ));
                
                correcto = Boolean.FALSE;
        }else{
            ReportesDinamicos r = new ReportesDinamicos();
            r.setIdentificador(this.reportesDinamicos.getIdentificador());
            if(this.genericoBo.existe(r)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El identificador ya existe."));
                correcto = Boolean.FALSE;
            }
        }
        if((this.reportesDinamicos.getNombre().trim() == null) || (this.reportesDinamicos.getNombre().trim().equals(""))){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre es requerido."));
            correcto = Boolean.FALSE;
        }
        if(this.reportesDinamicos.getSalidaReporte().equals(ReportesDinamicos.SALIDA_IREPORT)) {//Solo para reportes tipo Ireport
            if(this.reportesDinamicos.getJasper() == null){
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Campo Requerido."));
//                this.addError(this.getPropertyFieldName("jasper"),Recurso.getEtiqueta("campoRequerido"));
                correcto = Boolean.FALSE;
            }
            
            if(this.reportesDinamicos.getJrxml() == null){
//                this.addError(this.getPropertyFieldName("jrxml"),Recurso.getEtiqueta("campoRequerido"));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Campo Requerido."));
                correcto = Boolean.FALSE;
            }
        }
        if(this.reportesDinamicos.getSalidaReporte().equals(ReportesDinamicos.SALIDA_CSV)){//Solo para reportes tipo CSV
            if(this.reportesDinamicos.getSentenciaSQL() == null || this.reportesDinamicos.getSentenciaSQL().trim().equals("")){
//                this.addError(this.getPropertyFieldName("sql"),Recurso.getEtiqueta("campoRequerido"));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Campo Requerido."));
                correcto = Boolean.FALSE;
            }
        }        
//        if(this.reportesDinamicos.getCedulaSolicita().equals(Long.valueOf(0))) {
//            this.addError(this.getPropertyFieldName("usuarioSolicita"),Recurso.getEtiqueta("campoRequerido"));
//            correcto = Boolean.FALSE;
//        }
        if(this.reportesDinamicos.getModulo() == null || this.reportesDinamicos.getModulo().trim().equals("")){
//            this.addError(this.getPropertyFieldName("modulo"),Recurso.getEtiqueta("campoRequerido"));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El modulo es requerido."));
            correcto = Boolean.FALSE;
        }
        return correcto;
    }
    
   
    /**
     * Método agregarSubreporte
     * TODO (Descripción) 
     * @return "success"
     */
    public String agregarSubreporte(){
        if (this.reporteAsociado!=null){
            ReportesDinamicosSub objAux = new ReportesDinamicosSub();
            objAux.getId().setReportesDinamicos(this.reportesDinamicos);
            objAux.getId().setReportesDinamicosSub(this.reporteAsociado);
            objAux.setEstado(ReportesDinamicos.ESTADO_ACTIVO);
            if(!this.listSubReporte.contains(objAux)){
                this.listSubReporte.add(objAux);
            }
            this.reporteAsociado = new ReportesDinamicos();
            return "success";
        }else{
            this.addError(this.getPropertyFieldName("cmbReporte"),Recurso.getEtiqueta("reporteMant.reporteAsociado.error"));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar un reporte para asociarlo."));
            return "error";
        }
    }
    
    
    
    /**
     * Método eliminarSubreporte
     * TODO (Descripción) 
     * @return "success"
     */
    public String eliminarSubreporte(){
        ReportesDinamicosSub objAux = (ReportesDinamicosSub) this.subreporteDataTable.getRowData();
        this.listSubReporte.remove(objAux);
        return "success";
    }
    
    /******** SelectItems ***********/
    /*************************************************************************************************************************************/
    
    
    /**
     * Método getSinoItems
     * TODO (Descripción) 
     * @return items
     */
    public List<SelectItem> getSinoItems(){     
        List<SelectItem> list = new ArrayList<SelectItem>();        
        list.add(new SelectItem(ReportesDinamicos.ES_SUBREPORTE, Recurso.getEtiqueta("si")));
        list.add(new SelectItem(ReportesDinamicos.NO_ES_SUBREPORTE, Recurso.getEtiqueta("no")));
        return list;
    }    
    
    
    /**
     * Método getEstadoReporteItems
     * TODO (Descripción) 
     * @return items
     */
    public List<SelectItem> getEstadoReporteItems(){     
        List<SelectItem> list = new ArrayList<SelectItem>();        
        list.add(new SelectItem(ReportesDinamicos.ESTADO_ACTIVO, Recurso.getEtiqueta("reporteMant.estadoActivo")));
        list.add(new SelectItem(ReportesDinamicos.ESTADO_INACTIVO, Recurso.getEtiqueta("reporteMant.estadoInactivo")));
        return list;
    }
    
    
    /**
     * Método getExtensionReporteItems
     * TODO (Descripción) 
     * @return items
     */
    public List<SelectItem> getExtensionReporteItems(){     
        List<SelectItem> list = new ArrayList<SelectItem>();      
        list.add(new SelectItem(ReportesDinamicos.EXTENSION_PDF, Recurso.getEtiqueta("reporteMant.pdf")));
        list.add(new SelectItem(ReportesDinamicos.EXTENSION_EXCEL, Recurso.getEtiqueta("reporteMant.excel")));        
        return list;
    }
    
   
    /**
     * Método getSalidaReporteItems
     * TODO (Descripción) 
     * @return items
     */
    public List<SelectItem> getSalidaReporteItems(){     
        List<SelectItem> list = new ArrayList<SelectItem>(); 
        list.add(new SelectItem(ReportesDinamicos.SALIDA_IREPORT, Recurso.getEtiqueta("reporteMant.ireport")));
        list.add(new SelectItem(ReportesDinamicos.SALIDA_CSV, Recurso.getEtiqueta("reporteMant.csv")));
        list.add(new SelectItem(ReportesDinamicos.SALIDA_PROCEDIMIENTO, Recurso.getEtiqueta("reporteMant.procedAlmacenado")));
        return list;
    }
   
    /**
     * Método getTipoReporteItems
     * TODO (Descripción) 
     * @return items
     */
    public List<SelectItem> getTipoReporteItems(){     
        List<SelectItem> list = new ArrayList<SelectItem>();  
        list.add(new SelectItem(ReportesDinamicos.TIPO_EN_LINEA, Recurso.getEtiqueta("reporteMant.enLinea")));
        list.add(new SelectItem(ReportesDinamicos.TIPO_BATCH, Recurso.getEtiqueta("reporteMant.batch")));
        return list;
    }
    
   
    /**
     * Método getModuloReporteItems
     * TODO (Descripción) 
     * @return items
     */
    public List<SelectItem> getModuloReporteItems(){     
        List<SelectItem> items = new ArrayList<SelectItem>();   
        List<ReportesDinamicosMod> listAux = this.genericoBo.buscar(ReportesDinamicosMod.class, "estado", ReportesDinamicos.ESTADO_ACTIVO);
        items.add(new SelectItem("", "Seleccione un Módulo"));
        if(listAux != null){
        for(ReportesDinamicosMod objAux : listAux){
            items.add(new SelectItem(objAux.getId(), objAux.getId()));
        }
        }
        return items;
    }

    /**
     * Método getReporteItems
     * TODO (Descripción) 
     * @return items
     */
    public List<SelectItem> getReporteItems(){   
        List<SelectItem> items = new ArrayList<SelectItem>();   
        List<ReportesDinamicos> listAux = this.genericoBo.listar(ReportesDinamicos.class, "codigoReporte", Boolean.TRUE);
        items.add(new SelectItem(Integer.valueOf(0), "Seleccione un Reporte"));
        if(listAux != null){
        for(ReportesDinamicos objAux : listAux){
            if(this.mostrarTodos) {
                items.add(new SelectItem(objAux.getCodigoReporte(), objAux.getNombre() + " - " + objAux.getModulo()));
            }
            else {
                if(this.reportesDinamicos.getModulo() != null){
                    if((objAux.getEsSubreporte() != null && objAux.getModulo() != null) &&(objAux.getEsSubreporte().equals(ReportesDinamicos.ES_SUBREPORTE)) && (objAux.getModulo().equals(this.reportesDinamicos.getModulo()))) {
                        items.add(new SelectItem(objAux.getCodigoReporte(), objAux.getNombre()));
                    }
                }
            }
        }
        }
        return items;
    }
    
   
    /**
     * Método listenerReporte
     * TODO (Descripción) 
     * @param v
     */
    public void listenerReporte() {
    	 Integer i = Integer.valueOf(0);
        if(!i.equals(Integer.valueOf(0))){
            this.reporteAsociado = new ReportesDinamicos();
            this.reporteAsociado.setCodigoReporte(i); 
            this.reporteAsociado = this.genericoBo.buscar(this.reporteAsociado);
        }
    }
    
    
    /**
     * Método listenerSalida
     * TODO (Descripción) 
     * @param v
     */
    public void listenerSalida() {
        if(this.reportesDinamicos.getSalidaReporte().equals(ReportesDinamicos.SALIDA_IREPORT)){
            this.iReport = Boolean.TRUE;
        }
        else {
            this.iReport = Boolean.FALSE;
        }
    }
    
   
    /**
     * Método verDetalle
     * TODO (Descripción) 
     * @return "success"
     */
    public String verDetalle() {
        
        this.reportesDinamicos = (ReportesDinamicos) this.reporteDataTable.getRowData();
        if(this.reportesDinamicos.getSalidaReporte() != null) {
            if(this.reportesDinamicos.getSalidaReporte().equals(ReportesDinamicos.SALIDA_IREPORT)){
                this.iReport = Boolean.TRUE;
            }
            else {
                this.iReport = Boolean.FALSE;
            } 
        }
       //this.nombreDigitador = this.usuarioNuevoBo.nombreUsuario(this.reportesDinamicos.getCedulaDigitador());
       // this.nombreSolicita = this.usuarioNuevoBo.nombreUsuario(this.reportesDinamicos.getCedulaSolicita());

        this.listSubReporte = this.genericoBo.buscar(ReportesDinamicosSub.class, "id.reportesDinamicos.codigoReporte", this.reportesDinamicos.getCodigoReporte());

        return "success";
    }
    
   
    /**
     * Método regresar
     * TODO (Descripción) 
     * @return "success"
     */
    public String regresar() {
        this.actualizacion = Boolean.FALSE;
        this.resetController();
        return "success";
    }   

   
    /**
     * Método agregar
     * TODO (Descripción) 
     * @return "success"
     */
    public String agregar(){
        this.reportesDinamicos = new ReportesDinamicos();
        this.actualizacion = Boolean.FALSE;
        return "success";
    }    
    
    
    /**
     * Método modificar
     * TODO (Descripción) 
     * @return "success"
     */
    public String modificar(){
        this.reportesDinamicos = (ReportesDinamicos) this.reporteDataTable.getRowData();

        this.listSubReporte = this.genericoBo.buscar(ReportesDinamicosSub.class, "id.reportesDinamicos.codigoReporte", this.reportesDinamicos.getCodigoReporte());
        this.actualizacion = Boolean.TRUE;
        return "success";
    }
    
   
    /**
     * @see com.vvs.jsf.AbstractFacesController#getPropertyFieldName(java.lang.String)
     */
    @Override
    protected String getPropertyFieldName(String property) {
        if(property.equals("descripcion")) return "form1:txtDescripcion";
        if(property.equals("identificador")) return "form1:txtIdentificador";
        if(property.equals("nombre")) return "form1:txtNombre";
        if(property.equals("extension")) return "form1:txtExtension";
        if(property.equals("jasper")) return "form1:archivoJasper";
        if(property.equals("jrxml")) return "form1:archivoJrxml";
        if(property.equals("cmbReporte")) return "form1:cmbReporte";
        if(property.equals("usuarioSolicita")) return "form1:cmbSolicitado";
        if(property.equals("tipo")) return "form1:cmbTipo";
        if(property.equals("salida")) return "form1:cmbSalida";
        if(property.equals("sql")) return "form1:txtSQL";
        if(property.equals("modulo")) return "form1:cmbModulo";
        
        
        return null;
    }

    
    /***************** GETS - SETS ******************/
    /**************************************************************************************************************************************/

    
    /**
     * Método getReporteDataTable
     * TODO (Descripción) 
     * @return HtmlDataTable
     */
    public DataTable getReporteDataTable() {
        return this.reporteDataTable;
    }

   
    /**
     * Método setReporteDataTable
     * TODO (Descripción) 
     * @param reporteDataTable
     */
    public void setReporteDataTable(DataTable reporteDataTable) {
        this.reporteDataTable = reporteDataTable;
    }
    
  
    /**
     * Método getListReporte
     * TODO (Descripción) 
     * @return list
     */
    public List<ReportesDinamicos> getListReporte() {
        this.listReporte = this.genericoBo.listar(ReportesDinamicos.class, "nombre", Boolean.TRUE);
        return listReporte;
    }

   
    /**
     * Método setListReporte
     * TODO (Descripción) 
     * @param listReporte
     */
    public void setListReporte(List<ReportesDinamicos> listReporte) {
        this.listReporte = listReporte;
    }

   
    
    /**
     * Método getSubreporteDataTable
     * TODO (Descripción) 
     * @return HtmlDataTable
     */
    public DataTable getSubreporteDataTable() {
        return this.subreporteDataTable;
    }

   
    /**
     * Método setSubreporteDataTable
     * TODO (Descripción) 
     * @param subreporteDataTable
     */
    public void setSubreporteDataTable(DataTable subreporteDataTable) {
        this.subreporteDataTable = subreporteDataTable;
    }

   
    /**
     * Método getListSubReporte
     * TODO (Descripción) 
     * @return list
     */
    public List<ReportesDinamicosSub> getListSubReporte() {
        return this.listSubReporte;
    }

    
    /**
     * Método setListSubReporte
     * TODO (Descripción) 
     * @param listSubReporte
     */
    public void setListSubReporte(List<ReportesDinamicosSub> listSubReporte) {
        this.listSubReporte = listSubReporte;
    }

    
    /**
     * Método getReportesDinamicos
     * TODO (Descripción) 
     * @return reportesDinamicos
     */
    public ReportesDinamicos getReportesDinamicos() {
        return this.reportesDinamicos;
    }

  
    /**
     * Método setReportesDinamicos
     * TODO (Descripción) 
     * @param reportesDinamicos
     */
    public void setReportesDinamicos(ReportesDinamicos reportesDinamicos) {
        this.reportesDinamicos = reportesDinamicos;
    }

   
    /**
     * Método getActualizacion
     * TODO (Descripción) 
     * @return actualización
     */
    public Boolean getActualizacion() {
        return this.actualizacion;
    }

   
    /**
     * Método setActualizacion
     * TODO (Descripción) 
     * @param actualizacion
     */
    public void setActualizacion(Boolean actualizacion) {
        this.actualizacion = actualizacion;
    }

    
    /**
     * Método setMostrarTodos
     * TODO (Descripción) 
     * @param mostrarTodos
     */
    public void setMostrarTodos(Boolean mostrarTodos) {
        this.mostrarTodos = mostrarTodos;
    }

  
    /**
     * Método getMostrarTodos
     * TODO (Descripción) 
     * @return mostrarTodos
     */
    public Boolean getMostrarTodos() {
        return mostrarTodos;
    }

   
    /**
     * Método setiReport
     * TODO (Descripción) 
     * @param iReport
     */
    public void setiReport(Boolean iReport) {
        this.iReport = iReport;
    }

   
    /**
     * Método getiReport
     * TODO (Descripción) 
     * @return iReporte
     */
    public Boolean getiReport() {
        return iReport;
    }

   
    /**
     * Método getArchJrxml
     * TODO (Descripción) 
     * @return archJrxml
     */
//    public byte[] getArchJrxml() {
//        return this.archJrxml;
//    }
//
//    
//    /**
//     * Método setArchJrxml
//     * TODO (Descripción) 
//     * @param archJrxml
//     */
//    public void setArchJrxml(byte[] archJrxml) {
//        this.archJrxml = archJrxml;
//    }
//
//    
//    /**
//     * Método getArchJasper
//     * TODO (Descripción) 
//     * @return archJasper
//     */
//    public byte[] getArchJasper() {
//        return this.archJasper;
//    }
//
//   
//    /**
//     * Método setArchJasper
//     * TODO (Descripción) 
//     * @param archJasper
//     */
//    public void setArchJasper(byte[] archJasper) {
//        this.archJasper = archJasper;
//    }

    
    /**
     * Método getReporteAsociado
     * TODO (Descripción) 
     * @return reportesDinamicos
     */
    public ReportesDinamicos getReporteAsociado() {
        return this.reporteAsociado;
    }

   
    /**
     * Método setReporteAsociado
     * TODO (Descripción) 
     * @param reporteAsociado
     */
    public void setReporteAsociado(ReportesDinamicos reporteAsociado) {
        this.reporteAsociado = reporteAsociado;
    }

    
    /**
     * Método setNombreDigitador
     * TODO (Descripción) 
     * @param nombreDigitador
     */
    public void setNombreDigitador(String nombreDigitador) {
        this.nombreDigitador = nombreDigitador;
    }
    

    
    /**
     * Método getNombreDigitador
     * TODO (Descripción) 
     * @return ""
     */
    public String getNombreDigitador() {
        return nombreDigitador;
    }
    

   
    /**
     * Método setNombreSolicita
     * TODO (Descripción) 
     * @param nombreSolicita
     */
    public void setNombreSolicita(String nombreSolicita) {
        this.nombreSolicita = nombreSolicita;
    }
    

    
    /**
     * Método getNombreSolicita
     * TODO (Descripción) 
     * @return ""
     */
    public String getNombreSolicita() {
        return nombreSolicita;
    }
    
    
    /**
     * Método getGenericoBo
     * TODO (Descripción) 
     * @return genericoBo
     */
    public GenericoBo getGenericoBo() {
        return this.genericoBo;
    }


    /**
     * Método setGenericoBo
     * TODO (Descripción) 
     * @param genericoBo
     */
    public void setGenericoBo(GenericoBo genericoBo) {
        this.genericoBo = genericoBo;
    }


    /**
     * Método accesor del atributo archJrxml.
     * @return Retorna el atributo archJrxml.
     */
    public byte[] getArchJrxml() {
        return this.archJrxml;
    }


    /**
     * Método modificador del atributo archJrxml.
     * @param archJrxml El dato para modificar el atributo archJrxml.
     */
    public void setArchJrxml(byte[] archJrxml) {
        this.archJrxml = archJrxml;
    }


    /**
     * Método accesor del atributo archJasper.
     * @return Retorna el atributo archJasper.
     */
    public byte[] getArchJasper() {
        return this.archJasper;
    }


    /**
     * Método modificador del atributo archJasper.
     * @param archJasper El dato para modificar el atributo archJasper.
     */
    public void setArchJasper(byte[] archJasper) {
        this.archJasper = archJasper;
    }
    
}
