package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.component.datatable.DataTable;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.sige.carga.bo.AplicacionBo;
import cr.go.ice.sige.carga.bo.ArchivoBo;
import cr.go.ice.sige.carga.bo.RolUsuarioBo;
import cr.go.ice.sige.carga.bo.TipoArchivoBo;
import cr.go.ice.sige.carga.bo.UsuarioBo;
import cr.go.ice.sige.carga.decorator.ElementoHistorial;
import cr.go.ice.sige.carga.domain.Aplicacion;
import cr.go.ice.sige.carga.domain.Archivo;
import cr.go.ice.sige.carga.domain.ArchivoPuro;
import cr.go.ice.sige.carga.domain.RolArchivo;
import cr.go.ice.sige.carga.domain.RolUsuario;
import cr.go.ice.sige.carga.domain.TipoArchivo;
import cr.go.ice.sige.carga.domain.UsuarioCarga;
import cr.go.ice.sige.carga.utilidades.ArchivoVisualizado;



/**
 * <p>TODO <<Interface|Clase>> cr.go.ice.energia.cence.carga.web.controller.ListaInformacionGeneralController.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>ArchivosController.java</code>.</p>
 * <p>Fecha creación: 17/10/2011</p>
 * <p>Última actualización: 17/10/2011</p>
 * @author Vista Verde Tecnología (lsanchez)
 * @version 1.0
 */
public class ModuloDescargaController extends AbstractFacesController{

    private List<Aplicacion> listaAplicaciones;
    private List<ArchivoVisualizado> listaArchivosVisualizados;
//    private List<TipoArchivo> listaTipoArchivos;

    private DataTable tablaArchivosVisualizados; 
    private DataTable tablaAplicaciones; 
//    private HtmlDataTable tablaTipoArchivos;

    private Aplicacion aplicacion;
    private TipoArchivo tipoArchivo;
    private TipoArchivo tipoArchivoPadre;
    private Archivo archivo;
    private ArchivoVisualizado archivoVisualizado;
//    private UsuarioCarga usuario;

    private ArchivoBo archivoBo;
    private AplicacionBo aplicacionBo;
    private TipoArchivoBo tipoArchivoBo;
    private RolUsuarioBo rolUsuarioBo;
    
    private List<ElementoHistorial> elementosHistorial;
    
    private static final String PAGINA = "Body_SigeDescargaArchivos.jsf";//Poner nombre de la página creada
    
    private static Integer CODIGO_APLICACION = 2;//TODO Poner codigo aplicacion deseada
    

    /**
     * 
     * Constructor
     */
    public ModuloDescargaController(){
        this.resetController();
    }

    /**
     * Metodo para iniciar el módulo
     * @param context El contexto faces de la aplicación
     */
    public void init(FacesContext context){
    	
        Object init = context.getExternalContext().getRequestParameterMap().get("init");
      
//        Map sessionMap = context.getExternalContext().getSessionMap();//SESSION MAP
        if(init != null){
            this.resetController();
//            this.usuario = ((UsuarioCarga)sessionMap.get("usuario"));
            this.aplicacion.setCodigoAplicacion(CODIGO_APLICACION);
            this.buscarAplicacion();
//            this.llenarLista();
        }        
    }
    
    public Boolean buscarAplicacion(){
      this.aplicacion = this.aplicacionBo.buscar(this.aplicacion);       
      if(this.aplicacion.equals(new Aplicacion())){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La aplicación no está en la lista de aplicaciones del Sistema de Carga de Archivos."));
          return Boolean.FALSE;
      }
      else{
    	  System.out.println("Else buscarAplicacion()::::"+aplicacion);
          this.llenarListaTipoArchivo();
          return Boolean.TRUE;
      }
  }
    
    private Boolean buscarAplicacionTipo(){
        FacesContext context = FacesContext.getCurrentInstance();
        Object init2 = context.getExternalContext().getRequestParameterMap().get("init2");
        Map sessionMap = context.getExternalContext().getSessionMap();//SESSION MAP
        if(init2 != null){
            this.resetController();
//            this.usuario = ((UsuarioCarga)sessionMap.get("usuario"));
            this.aplicacion.setCodigoAplicacion(CODIGO_APLICACION);
            this.buscarAplicacion();
        }
        try{
            
            String codigoTipoArchivoString = (String)context.getExternalContext().getRequestParameterMap().get("categoria");  
            
            if(codigoTipoArchivoString!=null){
                this.aplicacion.setCodigoAplicacion(CODIGO_APLICACION);            
                if(this.buscarAplicacion()){
                    Long codigoTipoArchivo = Long.parseLong(codigoTipoArchivoString);
                    this.buscarTipoArchivo(codigoTipoArchivo);                        
                }                
                else{
                    return Boolean.FALSE;
                }                 
                return Boolean.TRUE;
            }
            return Boolean.TRUE;
        }
        catch(Exception exception){
            exception.printStackTrace();
            return Boolean.FALSE;
        }
    }
    
    private Boolean buscarTipoArchivo(Long codigoTipoArchivo){
        this.tipoArchivoPadre = this.tipoArchivoBo.getTipoArchivo(codigoTipoArchivo);
        if(this.tipoArchivoPadre.equals(new TipoArchivo())){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La caterogría no existe."));
            this.llenarListaTipoArchivoPadre();
            return Boolean.FALSE;
        }
        this.llenarListaTipoArchivoHijo();
        return Boolean.TRUE;
    }

    /**
     * 
     * Método reiniciarController
     * Reinicia todas las variables del controlador
     */
    public void resetController(){
        this.listaArchivosVisualizados = new ArrayList<ArchivoVisualizado>();
        this.listaAplicaciones = new ArrayList<Aplicacion>();
        this.elementosHistorial = new ArrayList<ElementoHistorial>();
        this.tablaArchivosVisualizados = new DataTable();
        this.tablaAplicaciones = new DataTable();
        this.aplicacion = new Aplicacion();
        this.archivo = new Archivo();
        this.tipoArchivoPadre=new TipoArchivo();
        this.archivoVisualizado=null;
    }

    
    public String seleccionarAplicacion(){
        String resultado = "success";
        this.aplicacion = (Aplicacion)this.tablaAplicaciones.getRowData();
        this.tipoArchivoPadre=new TipoArchivo();
        this.llenarListaTipoArchivoPadre();        
        this.calculaElementoHistorial();
        return resultado;
    }
    
    public void llenarListaTipoArchivo(){
        if(this.tipoArchivoPadre.getCodigoTipoArchivo()==null){
            this.llenarListaTipoArchivoPadre();
        }
        else{
            this.llenarListaTipoArchivoHijo();
        }
    }
    
       
    public void llenarListaTipoArchivoPadre(){
    	System.out.println("Else llenarListaTipoArchivoPadre()::::"+aplicacion);
        this.tipoArchivoPadre= new TipoArchivo();
        this.tipoArchivoPadre.setTitulo("Raíz");
        this.listaArchivosVisualizados = this.archivoBo.getListaPorTipoAplicacionActivoPadre(this.aplicacion); //TODO Mostrar Tipo Archivos, Aplicacion
        List<TipoArchivo> listaTipoArchivos = new ArrayList<TipoArchivo>();
        Iterator<ArchivoVisualizado> iterator = this.listaArchivosVisualizados.iterator();
        while(iterator.hasNext()){
            ArchivoVisualizado archivoVisualizado = iterator.next();
            if(!archivoVisualizado.getEsArchivo()){
                listaTipoArchivos.add(archivoVisualizado.getTipoArchivoObjeto());
            }
        }        
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().getSessionMap().put("nombreLista","listaTipoArchivos-Archivos");
        ctx.getExternalContext().getSessionMap().put("listaTipoArchivos-Archivos",listaTipoArchivos);
    }
    
    public void llenarListaTipoArchivoHijo(){
    	System.out.println("Else llenarListaTipoArchivoHijo()::::"+aplicacion);
        this.listaArchivosVisualizados = this.archivoBo.getListaPorTipoAplicacionActivo(this.tipoArchivoPadre,this.aplicacion); //TODO Mostrar Tipo Archivos, Aplicacion
        List<TipoArchivo> listaTipoArchivos = new ArrayList<TipoArchivo>();
        Iterator<ArchivoVisualizado> iterator = this.listaArchivosVisualizados.iterator();
        while(iterator.hasNext()){
            ArchivoVisualizado archivoVisualizado = iterator.next();
            if(!archivoVisualizado.getEsArchivo()){
                listaTipoArchivos.add(archivoVisualizado.getTipoArchivoObjeto());
            }
        }        
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().getSessionMap().put("nombreLista","listaTipoArchivos-Archivos");
        ctx.getExternalContext().getSessionMap().put("listaTipoArchivos-Archivos",listaTipoArchivos);
    }
    
    public String seleccionarArchivoVisualizado(){
        this.archivoVisualizado = (ArchivoVisualizado) this.tablaArchivosVisualizados.getRowData();        
        if(this.archivoVisualizado.getEsArchivo()){
            try{
                Archivo archivo = archivoVisualizado.getArchivoObjeto();
                FacesContext ctx = FacesContext.getCurrentInstance();
                ctx.getExternalContext().getSessionMap().put("archivo",this.archivoBo.buscar(archivo));
                this.archivoBo.fueDescargado(archivo);
                HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
                HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletDocumentos");
                dispatcher.forward(request, response);    
                if(!FacesContext.getCurrentInstance().getResponseComplete() ) {
                    FacesContext.getCurrentInstance().responseComplete();
                } 
                return "successArchivo";
            }catch(Exception e)
            {
                e.printStackTrace();
                return "failure";
            }
        }
        else{
            this.tipoArchivoPadre = this.archivoVisualizado.getTipoArchivoObjeto();
            this.llenarListaTipoArchivoHijo();
            this.calculaElementoHistorial();
            return "successTipo";
        }
    }

    

    public String regresar(){
        return "success";
    }
    
    public String regresarArchivoVisualizado(){
        if(this.tipoArchivoPadre.getCodigoTipoArchivo()==null){            
            this.listaArchivosVisualizados.clear();
            this.elementosHistorial.clear();
            return "successAplicacion";
        }
        else{
            if(this.tipoArchivoPadre.getCodigoTipoArchivoPadre()==null){
                this.llenarListaTipoArchivoPadre();
                this.calculaElementoHistorial();
                return "successArchivoVisualizado";
            }
            else{    
                this.tipoArchivoPadre = this.tipoArchivoBo.getTipoArchivo(this.tipoArchivoPadre.getCodigoTipoArchivoPadre());
                this.llenarListaTipoArchivoHijo();
                this.calculaElementoHistorial();
                return "successArchivoVisualizado";
            }    
            
        }
        
    }
    
    public Boolean getEsRaiz(){
        return this.tipoArchivoPadre.getCodigoTipoArchivo()==null;
    }

    public String cancelar(){        
        return "success";
    }
    
    private void calculaElementoHistorial(){
        this.elementosHistorial.clear();
        this.elementosHistorial.add(new ElementoHistorial(this.aplicacion.getNombre(),this.aplicacion.getCodigoAplicacion(),null));
        TipoArchivo tipoArchivoHistorico = this.tipoArchivoPadre; 
        while(tipoArchivoHistorico.getCodigoTipoArchivo()!=null){
            this.elementosHistorial.add(1,new ElementoHistorial(tipoArchivoHistorico.getNombre(),this.aplicacion.getCodigoAplicacion(),tipoArchivoHistorico.getCodigoTipoArchivo()));
            tipoArchivoHistorico = tipoArchivoHistorico.getCodigoTipoArchivoPadre()==null?new TipoArchivo():this.tipoArchivoBo.getTipoArchivo(tipoArchivoHistorico.getCodigoTipoArchivoPadre());
        }
        tipoArchivoHistorico=null;
    }
    
    public String getHistorial(){ 
        if(this.buscarAplicacionTipo()){
            this.calculaElementoHistorial();
        }
        StringBuilder historial = new StringBuilder();
        Iterator<ElementoHistorial> iterator = this.elementosHistorial.iterator();
        while(iterator.hasNext()){            
            ElementoHistorial elementoHistorial = iterator.next();
            if(iterator.hasNext()){
                historial.append(elementoHistorial.getDireccion(PAGINA));
                historial.append("->");
            }
            else{
                historial.append(elementoHistorial.getDireccion(PAGINA));
            }
        }
        return historial.toString();
    }
    
    public Boolean getEsArchivoAgregable(){
        return this.tipoArchivoPadre.getCodigoTipoArchivo()!=null;
    }


    /**
     * @see com.vvs.jsf.AbstractFacesController#getPropertyFieldName(java.lang.String)
     */
    @Override
    protected String getPropertyFieldName(String property) {
        return null;
    }

    /**
     * Método accesor del atributo listaArchivosVisualizados.
     * @return Retorna el atributo listaArchivosVisualizados.
     */
    public List<ArchivoVisualizado> getListaArchivosVisualizados() {
        return this.listaArchivosVisualizados;
    }

    /**
     * Método modificador del atributo listaArchivosVisualizados.
     * @param listaArchivosVisualizados El dato para modificar el atributo listaArchivosVisualizados.
     */
    public void setListaArchivosVisualizados(List<ArchivoVisualizado> listaArchivosVisualizados) {
        this.listaArchivosVisualizados = listaArchivosVisualizados;
    }

    /**
     * Método accesor del atributo archivoBo.
     * @return Retorna el atributo archivoBo.
     */
    public ArchivoBo getArchivoBo() {
        return this.archivoBo;
    }

    /**
     * Método modificador del atributo archivoBo.
     * @param archivoBo El dato para modificar el atributo archivoBo.
     */
    public void setArchivoBo(ArchivoBo archivoBo) {
        this.archivoBo = archivoBo;
    }

    /**
     * Método accesor del atributo tipoArchivoBo.
     * @return Retorna el atributo tipoArchivoBo.
     */
    public TipoArchivoBo getTipoArchivoBo() {
        return this.tipoArchivoBo;
    }

    /**
     * Método modificador del atributo tipoArchivoBo.
     * @param tipoArchivoBo El dato para modificar el atributo tipoArchivoBo.
     */
    public void setTipoArchivoBo(TipoArchivoBo tipoArchivoBo) {
        this.tipoArchivoBo = tipoArchivoBo;
    }

    /**
     * Método accesor del atributo archivo.
     * @return Retorna el atributo archivo.
     */
    public Archivo getArchivo() {
        return this.archivo;
    }

    /**
     * Método modificador del atributo archivo.
     * @param archivo El dato para modificar el atributo archivo.
     */
    public void setArchivo(Archivo archivo) {
        this.archivo = archivo;
    }


    /**
     * Método accesor del atributo tablaArchivosVisualizados.
     * @return Retorna el atributo tablaArchivosVisualizados.
     */
    public DataTable getTablaArchivosVisualizados() {
        return this.tablaArchivosVisualizados;
    }

    /**
     * Método modificador del atributo tablaArchivosVisualizados.
     * @param tablaArchivosVisualizados El dato para modificar el atributo tablaArchivosVisualizados.
     */
    public void setTablaArchivosVisualizados(DataTable tablaArchivosVisualizados) {
        this.tablaArchivosVisualizados = tablaArchivosVisualizados;
    }

    /**
     * Método accesor del atributo tipoArchivo.
     * @return Retorna el atributo tipoArchivo.
     */
    public TipoArchivo getTipoArchivo() {
        return this.tipoArchivo;
    }

    /**
     * Método modificador del atributo tipoArchivo.
     * @param tipoArchivo El dato para modificar el atributo tipoArchivo.
     */
    public void setTipoArchivo(TipoArchivo tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    /**
     * Método accesor del atributo rolUsuarioBo.
     * @return Retorna el atributo rolUsuarioBo.
     */
    public RolUsuarioBo getRolUsuarioBo() {
        return this.rolUsuarioBo;
    }

    /**
     * Método modificador del atributo rolUsuarioBo.
     * @param rolUsuarioBo El dato para modificar el atributo rolUsuarioBo.
     */
    public void setRolUsuarioBo(RolUsuarioBo rolUsuarioBo) {
        this.rolUsuarioBo = rolUsuarioBo;
    }

    /**
     * Método accesor del atributo aplicacion.
     * @return Retorna el atributo aplicacion.
     */
    public Aplicacion getAplicacion() {
        return this.aplicacion;
    }

    /**
     * Método modificador del atributo aplicacion.
     * @param aplicacion El dato para modificar el atributo aplicacion.
     */
    public void setAplicacion(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
    }

    /**
     * Método accesor del atributo aplicacionBo.
     * @return Retorna el atributo aplicacionBo.
     */
    public AplicacionBo getAplicacionBo() {
        return this.aplicacionBo;
    }

    /**
     * Método modificador del atributo aplicacionBo.
     * @param aplicacionBo El dato para modificar el atributo aplicacionBo.
     */
    public void setAplicacionBo(AplicacionBo aplicacionBo) {
        this.aplicacionBo = aplicacionBo;
    }

    /**
     * Método accesor del atributo listaAplicaciones.
     * @return Retorna el atributo listaAplicaciones.
     */
    public List<Aplicacion> getListaAplicaciones() {
        return this.listaAplicaciones;
    }

    /**
     * Método modificador del atributo listaAplicaciones.
     * @param listaAplicaciones El dato para modificar el atributo listaAplicaciones.
     */
    public void setListaAplicaciones(List<Aplicacion> listaAplicaciones) {
        this.listaAplicaciones = listaAplicaciones;
    }

    /**
     * Método accesor del atributo tablaAplicaciones.
     * @return Retorna el atributo tablaAplicaciones.
     */
    public DataTable getTablaAplicaciones() {
        return this.tablaAplicaciones;
    }

    /**
     * Método modificador del atributo tablaAplicaciones.
     * @param tablaAplicaciones El dato para modificar el atributo tablaAplicaciones.
     */
    public void setTablaAplicaciones(DataTable tablaAplicaciones) {
        this.tablaAplicaciones = tablaAplicaciones;
    }

    /**
     * Método accesor del atributo archivoVisualizado.
     * @return Retorna el atributo archivoVisualizado.
     */
    public ArchivoVisualizado getArchivoVisualizado() {
        return this.archivoVisualizado;
    }

    /**
     * Método modificador del atributo archivoVisualizado.
     * @param archivoVisualizado El dato para modificar el atributo archivoVisualizado.
     */
    public void setArchivoVisualizado(ArchivoVisualizado archivoVisualizado) {
        this.archivoVisualizado = archivoVisualizado;
    }

    /**
     * Método accesor del atributo tipoArchivoPadre.
     * @return Retorna el atributo tipoArchivoPadre.
     */
    public TipoArchivo getTipoArchivoPadre() {
        return this.tipoArchivoPadre;
    }

    /**
     * Método modificador del atributo tipoArchivoPadre.
     * @param tipoArchivoPadre El dato para modificar el atributo tipoArchivoPadre.
     */
    public void setTipoArchivoPadre(TipoArchivo tipoArchivoPadre) {
        this.tipoArchivoPadre = tipoArchivoPadre;
    }



}
