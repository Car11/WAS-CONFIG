package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.SubEstacionBO;
import cr.go.ice.interrupciones.domain.Region;
import cr.go.ice.interrupciones.domain.SubEstacion;
import cr.go.ice.interrupciones.domain.SubRegion;

/**
 * <p>Clase cr.go.ice.sige.sigecon.web.controller.SubEstacionController.java</p>
 * <p>Modulo (subsistema): Sigecon</p>
 * <p>La clase <code>SubEstacionController</code> se utiliza para gestionar las acciones de la capa de presentacion 
 * del jsp SigeSigeconConsultaSubEstacion.jsp.</p>
 * <p>Fecha creación: 25/01/2008</p>
 * <p>Ultima actualización: 25/01/2008</p>
 * @author Vista Verde Tecnologia (Diego Hernández Pérez)
 * @version 1.0
 */
public class ConsultaSubEstacionController extends AbstractFacesController{

    // -------------------- Atributos -------------------- //
    
    //BO's
    /** atributo <code>subEstacionBO</code>*/
    private SubEstacionBO subEstacionBO;

    
    //atributos
    /** atributo <code>subRegion</code>*/
    private SubRegion subRegion;
    /** atributo <code>subRegion</code>*/
    private SubEstacion subEstacion;
    /** atributo <code>subRegiones</code>*/
    private List subEstaciones;
    /** atributo <code>dataTableSubRegiones</code>*/
    private DataTable dataTableSubEstaciones;
    
    /** atributo <code>inicioBusqueda</code>*/
    private Boolean inicioBusqueda;
    
    private Integer buscar;
    
    
    /**
     * Constructor
     * 
     */
    public ConsultaSubEstacionController(){
        this.subEstacionBO = null;
        this.subRegion = new SubRegion();
        this.subEstacion = new SubEstacion();
        this.subEstaciones = new ArrayList();
        this.dataTableSubEstaciones = new DataTable();
        this.inicioBusqueda = Boolean.valueOf(false);
        this.buscar = null;
    }
    
     
    // -------------------- Setters and Getters -------------------- //
    
    /**
     * Metodo accesor de dataTableSubEstaciones.
     * @return Retorna el dataTableSubEstaciones.
     */
    public DataTable getDataTableSubEstaciones() {
        return this.dataTableSubEstaciones;
    }
    /**
     * Metodo modificador de dataTableSubEstaciones.
     * @param dataTableSubEstaciones El dataTableSubEstaciones a modificar.
     */
    public void setDataTableSubEstaciones(DataTable dataTableSubEstaciones) {
        this.dataTableSubEstaciones = dataTableSubEstaciones;
    }
    /**
     * Metodo accesor de subEstacion.
     * @return Retorna el subEstacion.
     */
    public SubEstacion getSubEstacion() {
        return this.subEstacion;
    }
    /**
     * Metodo modificador de subEstacion.
     * @param subEstacion El subEstacion a modificar.
     */
    public void setSubEstacion(SubEstacion subEstacion) {
        this.subEstacion = subEstacion;
    }
    /**
     * Metodo accesor de subEstacionBO.
     * @return Retorna el subEstacionBO.
     */
    public SubEstacionBO getSubEstacionBO() {
        return this.subEstacionBO;
    }
    /**
     * Metodo modificador de subEstacionBO.
     * @param subEstacionBO El subEstacionBO a modificar.
     */
    public void setSubEstacionBO(SubEstacionBO subEstacionBO) {
        this.subEstacionBO = subEstacionBO;
    }
    /**
     * Metodo accesor de subEstaciones.
     * @return Retorna el subEstaciones.
     */
    public List getSubEstaciones() {
        return this.subEstaciones;
    }
    /**
     * Metodo modificador de subEstaciones.
     * @param subEstaciones El subEstaciones a modificar.
     */
    public void setSubEstaciones(List subEstaciones) {
        this.subEstaciones = subEstaciones;
    }
    /**
     * Metodo accesor de subRegion.
     * @return Retorna el subRegion.
     */
    public SubRegion getSubRegion() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        this.subRegion = (SubRegion) ctx.getApplication().createValueBinding("#{consultaSubRegionController.subRegion}").getValue(ctx);
        return this.subRegion;
    }
    /**
     * Metodo modificador de subRegion.
     * @param subRegion El subRegion a modificar.
     */
    public void setSubRegion(SubRegion subRegion) {
        this.subRegion = subRegion;
    }
    /**
     * Metodo accesor de inicioBusqueda.
     * @return Retorna el inicioBusqueda.
     */
    public Boolean getInicioBusqueda() {
        return this.inicioBusqueda;
    }
    /**
     * Metodo modificador de inicioBusqueda.
     * @param inicioBusqueda El inicioBusqueda a modificar.
     */
    public void setInicioBusqueda(Boolean inicioBusqueda) {
        this.inicioBusqueda = inicioBusqueda;
    }
    
    
    
    // -------------------- Métodos de la clase -------------------- //
    
    /**
     * Metodo para iniciar el módulo
     * @param context El contexto faces de la aplicación
     */
    public String getInit(){
    	FacesContext context = FacesContext.getCurrentInstance();
        Object init = context.getExternalContext().getRequestParameterMap().get("init");
        if(init != null)
        {
        	this.buscarTodos();
            this.inicioBusqueda = Boolean.valueOf(true);
            context.getApplication().createValueBinding("#{consultaSubRegionController.subRegion}").setValue(context,new SubRegion());
        }
        return "success";
    }
    
    /**
     * Método buscarTodos
     * Realiza un listado de todas los pueblos
     */
    private void buscarTodos(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        if(!this.inicioBusqueda.booleanValue()){
        	Region region = (Region)ctx.getApplication().createValueBinding("#{consultaRegionController.region}").getValue(ctx);
            this.subRegion = (SubRegion) ctx.getApplication().createValueBinding("#{consultaSubRegionController.subRegion}").getValue(ctx);
            if(region == null){
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No hay región registrada."));
            }else if(this.subRegion == null){
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No hay subregión registrada."));
            }else{
                this.subRegion = (SubRegion) ctx.getApplication().createValueBinding("#{consultaSubRegionController.subRegion}").getValue(ctx);
                this.subEstaciones = this.subEstacionBO.getSubEstaciones(region.getRegion(),this.subRegion.getSubRegionID().getSubRegion());
                if(this.subEstaciones.isEmpty()){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No hay subestaciones registradas para la subregión seleccionada."));
                }
            }
        }
        else{
            //this.inicioBusqueda = Boolean.valueOf(true);
            this.subRegion = new SubRegion();
            this.subEstacion = new SubEstacion();
            this.subEstaciones = this.subEstacionBO.getSubEstaciones();
            if(this.subEstaciones.isEmpty()){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No hay subestaciones registradas."));
            }
        }
    }
    
    /**
     * Muestra un nuevo JSP con los Circuitos correspondientes
     * a la sub estación seleccionada
     * @return String "success"
     */
    public String verCircuitos(){
        this.subEstacion = (SubEstacion) this.dataTableSubEstaciones.getRowData();
        this.buscar = Integer.valueOf(0);
        return "success";
    }
    
    /**
     * Muestra un  JSP con los Circuitos correspondientes 
     * a la sub estación seleccionada
     * @return String "success"
     */
    public String abrirCircuitos(){
        this.subEstacion = (SubEstacion) this.dataTableSubEstaciones.getRowData();
        this.buscar = Integer.valueOf(1);
        return "success";
    }
    
    /**
     * Envía a la página anterior
     * @return String "success"
     */
    public String regresar(){
        return "success";
    }
    
    
    
    //  -------------------- Métodos específicos del controller -------------------- //
    
    /**
     * Método addError
     * Agrega un mensaje de error a una determinada propiedad
     */
   


    /**
     * Método accesor del atributo buscar.
     * @return Retorna el atributo buscar.
     */
    public Integer getBuscar() {
        return this.buscar;
    }


    /**
     * Método modificador del atributo buscar.
     * @param buscar El dato para modificar el atributo buscar.
     */
    public void setBuscar(Integer buscar) {
        this.buscar = buscar;
    }


	@Override
	protected String getPropertyFieldName(String property) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected void resetController() {
		// TODO Auto-generated method stub
		
	}
}
