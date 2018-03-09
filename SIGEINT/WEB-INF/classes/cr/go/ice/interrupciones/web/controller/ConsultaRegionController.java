package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.RegionBO;
import cr.go.ice.interrupciones.domain.Region;

/**
 * <p>Clase cr.go.ice.sige.sigecon.web.controller.RegionController.java</p>
 * <p>Modulo (subsistema): Sigecon</p>
 * <p>La clase <code>RegionController</code> se utiliza para gestionar las acciones de la capa de presentacion 
 * del jsp SigeSigeconConsultaRegion.jsp.</p>
 * <p>Fecha creación: 25/01/2008</p>
 * <p>Ultima actualización: 25/01/2008</p>
 * @author Vista Verde Tecnologia (Diego Hernández Pérez)
 * @version 1.0*/
 
public class ConsultaRegionController extends AbstractFacesController {

     //-------------------- Atributos -------------------- 
    
   // BO's
  private RegionBO regionBO;
    
    //atributos
    private Region region;
    private List regiones;
    private DataTable dataTableRegiones;
   
    /**
     * Constructor
     */
	
     
    public ConsultaRegionController(){
        this.regionBO = null;
        this.region = new Region();
        this.regiones = new ArrayList();
    }
    
    
    
    // -------------------- Setters and Getters -------------------- 
    
    
    
    /**
     * Metodo accesor de region.
     * @return Retorna el region.
     */
    public Region getRegion() {
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
     * Metodo accesor de regionBO.
     * @return Retorna el regionBO.
     */
    public RegionBO getRegionBO() {
        return this.regionBO;
    }
    /**
     * Metodo modificador de regionBO.
     * @param regionBO El regionBO a modificar.
     */
    public void setRegionBO(RegionBO regionBO) {
        this.regionBO = regionBO;
    }
    
    /**
     * Metodo accesor de regiones.
     * @return Retorna el regiones.
     */
    public List getRegiones() {
        return this.regiones;
    }
    /**
     * Metodo modificador de regiones.
     * @param regiones El regiones a modificar.
     */
    public void setRegiones(List regiones) {
        this.regiones = regiones;
    }
    /**
     * Metodo accesor de dataTableRegiones.
     * @return Retorna el dataTableRegiones.
     */
    public DataTable getDataTableRegiones() {
        return this.dataTableRegiones;
    }
    /**
     * Metodo modificador de dataTableRegiones.
     * @param dataTableRegiones El dataTableRegiones a modificar.
     */
    public void setDataTableRegiones(DataTable dataTableRegiones) {
        this.dataTableRegiones = dataTableRegiones;
    }
    
    
    // -------------------- Métodos de la clase -------------------- 
    
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
        }
        return "success";
    }
    
    /**
     * Método buscarTodos
     * Realiza un listado de todas las regiones
     */
    private void buscarTodos(){
        List listRegiones = this.regionBO.getRegiones(Region.ORDEN_NOMBRE);
        if(!listRegiones.isEmpty()){
        	this.regiones = listRegiones;
        }
        else{
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No hay Regiones registradas"));
        	
        }
    }
    
    /**
     * Muestra un nuevo JSP con las sub regiones correspondientes
     * a la región seleccionada
     * @return String "success"
     */
    public String mostrarSubRegiones(){
        this.region = (Region) this.dataTableRegiones.getRowData();
        return "success";
    }
    
      //-------------------- Métodos específicos del controller -------------------- 
    
    /**
     * Método addError
     * Agrega un mensaje de error a una determinada propiedad
     */
    /*private void addError(String property, String message){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        msg.setSummary(message);
        msg.setDetail(message);
        context.addMessage(property, msg);
    }*/

	@Override
	protected String getPropertyFieldName(String property) {
		 return null;
	}

	@Override
	protected void resetController() {
		
		
	}
	
	
	

}







