package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.ProteccionBO;
import cr.go.ice.interrupciones.domain.Proteccion;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.AnimalController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ProteccionController.java</code> Establese la relacion entre capa de presentacion y la logica del negocio.</p>
 * <p>Fecha creación: 25/01/2007</p>
 * <p>Ultima actualización: 28/01/2007</p>
 * @author Vista Verde Soft (Administrador y Doc Cristian)
 * @version 1.1
 */
public class ProteccionController extends AbstractFacesController{
    /**
     * <code>codigoProteccion</code> Codigo de proteccion
     */
    private Integer codigoProteccion;
    /**
     * <code>nombreProteccion</code> Nombre de la Proteccion
     */
    private String nombreProteccion;

    /**
     * <code>proteccion</code> Proteccion
     */
    private Proteccion proteccion;
    /**
     * <code>proteccionBO</code> Proteccion BO
     */
    private ProteccionBO proteccionBO;

    //private ResourceBundle rsc = ResourceBundle.getBundle("cr.go.ice.interupciones.web.resources");

    /**
     * Constructor
     */
    public ProteccionController() {
        this.codigoProteccion = new Integer(0);
        this.nombreProteccion = "";
        this.proteccion = null;
    }
    
    
   public String getInit(){
	   return "success";
   }

    /**
     * Metodo accesor de codigoProteccion.
     * @return Retorna el codigoProteccion.
     */
    public Integer getCodigoProteccion() {
        return this.codigoProteccion;
    }

    /**
     * Metodo modificador de codigoProteccion.
     * @param codigoProteccion El codigoProteccion a modificar.
     */
    public void setCodigoProteccion(Integer codigoProteccion) {
        this.codigoProteccion = codigoProteccion;
    }

    /**
     * Metodo accesor de nombreProteccion.
     * @return Retorna el nombreProteccion.
     */
    public String getNombreProteccion() {
        return this.nombreProteccion;
    }

    /**
     * Metodo modificador de nombreProteccion.
     * @param nombreProteccion El nombreProteccion a modificar.
     */
    public void setNombreProteccion(String nombreProteccion) {
        this.nombreProteccion = nombreProteccion.toUpperCase();
    }

    /**
     * Metodo accesor de proteccionBO.
     * @return Retorna el proteccionBO.
     */
    public ProteccionBO getProteccionBO() {
        return this.proteccionBO;
    }

    /**
     * Metodo modificador de proteccionBO.
     * @param proteccionBO El proteccionBO a modificar.
     */
    public void setProteccionBO(ProteccionBO proteccionBO) {
        this.proteccionBO = proteccionBO;
    }

    /**
     * Comment for agregar
     * @return "success" o "error" al agregar proteccion
     */
    public String agregar() {
        FacesMessage msg = new FacesMessage();
        
        if(this.codigoProteccion == null || this.codigoProteccion.intValue() <= 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requrido."));
            return "error";
        }
        
        if (this.nombreProteccion != null && this.nombreProteccion.length() > 0) {
            this.proteccion = new Proteccion();
            this.proteccion.setCodigoProteccion(this.codigoProteccion);
            this.proteccion.setNombreProteccion(this.nombreProteccion.toUpperCase());
            if (!this.proteccionBO.proteccionExiste(this.proteccion)) {
                this.proteccionBO.agregar(this.proteccion);
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Protección agregada con éxito."));
                return "success";
            } else {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La protección especificada ya existe."));
                return "error";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El valor es requerido."));
            return "error";
        }
    }

    /**
     * Comment for modificar
     * @return "success" o "error" al modificar proteccion
     */
    public String modificar() {
        FacesMessage msg = new FacesMessage();
        
        if(this.codigoProteccion == null || this.codigoProteccion.intValue() <= 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }
        
        this.proteccion = this.proteccionBO.buscar(this.codigoProteccion);
        if (this.proteccion == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La protección no existe."));
            return "error";
        }
        if (this.nombreProteccion.equals(this.proteccion.getNombreProteccion())) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre de la protección no ha sido cambiado."));
            return "error";
        }
        Proteccion prot2 = this.proteccionBO.buscar(this.nombreProteccion);
        if (prot2 == null || prot2.getCodigoProteccion().equals(this.proteccion.getCodigoProteccion())) {
            this.proteccion.setNombreProteccion(this.nombreProteccion);
            this.proteccionBO.modificar(this.proteccion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Protección modificada con éxito."));
            return "success";
        } else {
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre de la protección ya está registrado para otra protección."));
            return "error";
        }

    }

    /**
     * Comment for eliminar
     * @return "success" o "error" al eliminar proteccion
     */
    public String eliminar() {
        FacesMessage msg = new FacesMessage();
        
        if(this.codigoProteccion == null || this.codigoProteccion.intValue() <= 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }
        
        this.proteccion = this.proteccionBO.buscar(this.codigoProteccion);
        if (this.proteccion != null && this.proteccionBO.proteccionExiste(this.proteccion)) {
    		if(this.proteccionBO.registrosAsociados(this.codigoProteccion).longValue() > 0){
    		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede eliminar!", "Protección se encuentra asociada con diversa información."));
    			return "error";
    		}           
    		else{
	            this.proteccionBO.eliminar(this.proteccion);
	            this.proteccion = null;
	            this.codigoProteccion = new Integer(0);
	            this.nombreProteccion = "";
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Protección eliminada con éxito."));
	            return "success";
    		}
        } else {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La protección no existe."));
            return "error";
        }
    }

   
    /**
     * Comment for buscar
     * @return "success" o "error" al buscar proteccion
     */
    public String buscar() {
        FacesMessage msg = new FacesMessage();
        
        if(this.codigoProteccion == null || this.codigoProteccion.intValue() <= 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }
        
        this.proteccion = this.proteccionBO.buscar(this.codigoProteccion);
        if (proteccion != null) {
            this.codigoProteccion = this.proteccion.getCodigoProteccion();
            this.nombreProteccion = this.proteccion.getNombreProteccion();
            return "success";
        } else {
            this.nombreProteccion = "";            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La protección especificada no existe."));
            return "error";
        }
    }
    
    /**
     * Cancela
     * @return success
     */
    public String cancelar(){
        return "success";
    }
    
    /**
     * Comment for getProteccionesSelectItems
     * @return lista Items Proteccion
     */
    public List getProteccionesSelectItems(){
    	List selectItems = new ArrayList();
    	List protecciones = this.proteccionBO.getProtecciones();
    	for(int i=0; i<protecciones.size();i++){
    		Proteccion proteccion = (Proteccion) protecciones.get(i);
    		selectItems.add(new SelectItem(proteccion.getCodigoProteccion(), proteccion.getCodigoProteccion()+" - "+proteccion.getNombreProteccion()));
    	}
    	return selectItems;
    }
    
    /**
     * Comment for getListaProtecciones
     * @return lista de Protecciones
     */
    public List getListaProtecciones(){
    	List lista = new ArrayList();
    	lista = this.proteccionBO.getProtecciones();    	
    	return lista;
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