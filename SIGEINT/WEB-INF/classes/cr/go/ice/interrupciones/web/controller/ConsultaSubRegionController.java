package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.domain.Region;
import cr.go.ice.interrupciones.BO.SubRegionBO;
import cr.go.ice.interrupciones.domain.SubRegion;

/**
 * <p>Clase cr.go.ice.sige.sigecon.web.controller.SubregionController.java</p>
 * <p>Modulo (subsistema): Sigecon</p>
 * <p>La clase <code>SubregionController</code> se utiliza para gestionar las acciones de la capa de presentacion 
 * del jsp SigeSigeconConsultaSubRegion.jsp.</p>
 * <p>Fecha creación: 25/01/2008</p>
 * <p>Ultima actualización: 25/01/2008</p>
 * @author Vista Verde Tecnologia (Diego Hernández Pérez)
 * @version 1.0
 */
public class ConsultaSubRegionController extends AbstractFacesController {

    
    // -------------------- Atributos -------------------- //
    
    //BO's
    /** atributo <code>suRegionBO</code>*/
    private SubRegionBO subRegionBO;
    
    
    //atributos
    /** atributo <code>region</code>*/
    private Region region;
    /** atributo <code>subRegion</code>*/
    private SubRegion subRegion;
    /** atributo <code>subRegiones</code>*/
    private List subRegiones;
    /** atributo <code>dataTableSubRegiones</code>*/
    private DataTable dataTableSubRegiones;
    
    
    /**
     * Constructor
     * 
     */
    public ConsultaSubRegionController(){
        this.subRegionBO = null;
        this.region = new Region();
        this.subRegion = new SubRegion();
        this.subRegiones = new ArrayList();
        this.dataTableSubRegiones = new DataTable();
    }
    
    public String getInit(){
    	FacesContext context = FacesContext.getCurrentInstance();
        Object init = context.getExternalContext().getRequestParameterMap().get("init");
        if(init != null)
        {
        	this.buscarTodos();
        }
        return "success";
    }
    
    
    // -------------------- Setters and Getters -------------------- //
    
    /**
     * Metodo accesor de dataTableSubRegiones.
     * @return Retorna el dataTableSubRegiones.
     */
    public DataTable getDataTableSubRegiones() {
        return this.dataTableSubRegiones;
    }
    /**
     * Metodo modificador de dataTableSubRegiones.
     * @param dataTableSubRegiones El dataTableSubRegiones a modificar.
     */
    public void setDataTableSubRegiones(DataTable dataTableSubRegiones) {
        this.dataTableSubRegiones = dataTableSubRegiones;
    }
    /**
     * Metodo accesor de region.
     * @return Retorna el region.
     */
    public Region getRegion() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        this.region = (Region) ctx.getApplication().createValueBinding("#{consultaRegionController.region}").getValue(ctx);        
        return this.region;
    }
    /**
     * Metodo modificador de region.
     * @param region El region a modificar.
     */
    public void setRegion(Region region) {
        this.region = region;
    }
    /**
     * Metodo accesor de subRegionBO.
     * @return Retorna el subRegionBO.
     */
    public SubRegionBO getSubRegionBO() {
        return this.subRegionBO;
    }
    /**
     * Metodo modificador de subRegionBO.
     * @param subRegionBO El subRegionBO a modificar.
     */
    public void setSubRegionBO(SubRegionBO subRegionBO) {
        this.subRegionBO = subRegionBO;
    }
    /**
     * Metodo accesor de subRegiones.
     * @return Retorna el subRegiones.
     */
    public List getSubRegiones() {
        return this.subRegiones;
    }
    /**
     * Metodo modificador de subRegiones.
     * @param subRegiones El subRegiones a modificar.
     */
    public void setSubRegiones(List subRegiones) {
        this.subRegiones = subRegiones;
    }
    /**
     * Metodo accesor de subRegion.
     * @return Retorna el subRegion.
     */
    public SubRegion getSubRegion() {
        return this.subRegion;
    }
    /**
     * Metodo modificador de subRegion.
     * @param subRegion El subRegion a modificar.
     */
    public void setSubRegion(SubRegion subRegion) {
        this.subRegion = subRegion;
    }
    // -------------------- Métodos de la clase -------------------- //
    
    /**
     * Metodo para iniciar el módulo
     * @param context El contexto faces de la aplicación
     */
    public void init(FacesContext context){
        this.buscarTodos();
    }
    
    /**
     * Método buscarTodos
     * Realiza un listado de todas los pueblos
     */
    private void buscarTodos(){
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        this.region = (Region) ctx.getApplication().createValueBinding("#{consultaRegionController.region}").getValue(ctx);
        ctx.getApplication().createValueBinding("#{consultaSubEstacionController.inicioBusqueda}").setValue(ctx,Boolean.valueOf(false));
        
        if((this.region == null) || (this.region.getRegion() == null)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No hay región registrada."));
        }
        else{
            this.subRegiones = this.subRegionBO.getSubRegiones(this.region.getRegion(),SubRegion.ORDEN_NOMBRE);
            if(this.subRegiones.isEmpty()){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No hay subregiones registradas para la región seleccionada."));
            }
        }
        
    }
    
    /**
     * Muestra un nuevo JSP con las Sub Estaciones correspondientes
     * a la sub región seleccionada
     * @return String "success"
     */
    public String mostrarSubEstaciones(){
        this.subRegion = (SubRegion) this.dataTableSubRegiones.getRowData();
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
