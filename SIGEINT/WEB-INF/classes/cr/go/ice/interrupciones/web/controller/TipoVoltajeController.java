package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.TipoVoltajeBO;
import cr.go.ice.interrupciones.domain.TipoVoltaje;
import cr.go.ice.interrupciones.domain.TipoVoltajeID;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.AnimalController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>TipoVoltajeController.java</code> Establese la relacion entre capa de presentacion y la logica del negocio.</p>
 * <p>Fecha creación: 25/01/2007</p>
 * <p>Ultima actualización: 25/01/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class TipoVoltajeController extends AbstractFacesController {
	
	/**
	 * <code>tipoVoltajeBO</code> Tipo de Voltaje
	 */
	private TipoVoltajeBO tipoVoltajeBO;
	
	/**
	 * <code>codigoDescripcion</code> Codigo de Descripcion
	 */
	private String codigoDescripcion;
	/**
	 * <code>nombreVoltaje</code> Nombre del Voltaje
	 */
	private String nombreVoltaje;
	/**
	 * <code>tipoVoltajeID</code> Tipo de Voltaje Identificacion
	 */
	private TipoVoltajeID tipoVoltajeID;
	
	/**
	 * <code>codigoVoltaje</code> Codigo de Voltaje
	 */
	private Integer codigoVoltaje;
	/**
	 * <code>tipoVoltaje</code> Tipo de Voltaje
	 */
	private Integer tipoVoltaje;

	
	/**
	 * Comment for getTipoVoltajeBO
	 * @return tipo Voltaje BO
	 */
	public TipoVoltajeBO getTipoVoltajeBO() {
		return tipoVoltajeBO;
	}

	
	/**
	 * Comment for setTipoVoltajeBO
	 * @param tipoVoltajeBO
	 */
	public void setTipoVoltajeBO(TipoVoltajeBO tipoVoltajeBO) {
		this.tipoVoltajeBO = tipoVoltajeBO;
	}


	/**
	 * Comment for getCodigoDescripcion
	 * @return codigo Descripcion
	 */
	public String getCodigoDescripcion() {
		return codigoDescripcion;
	}


	/**
	 * Comment for setCodigoDescripcion
	 * @param codigoDescripcion
	 */
	public void setCodigoDescripcion(String codigoDescripcion) {
		this.codigoDescripcion = codigoDescripcion;
	}


	/**
	 * Comment for getCodigoVoltaje
	 * @return codigo Voltaje
	 */
	public Integer getCodigoVoltaje() {
		return codigoVoltaje;
	}


	/**
	 * Comment for setCodigoVoltaje
	 * @param codigoVoltaje
	 */
	public void setCodigoVoltaje(Integer codigoVoltaje) {
		this.codigoVoltaje = codigoVoltaje;
	}


	/**
	 * Comment for getNombreVoltaje
	 * @return nombre Voltaje
	 */
	public String getNombreVoltaje() {
		return nombreVoltaje;
	}


	/**
	 * Comment for setNombreVoltaje
	 * @param nombreVoltaje
	 */
	public void setNombreVoltaje(String nombreVoltaje) {
		this.nombreVoltaje = nombreVoltaje;
	}


	/**
	 * Comment for getTipoVoltaje
	 * @return tipo Voltaje
	 */
	public Integer getTipoVoltaje() {
		return tipoVoltaje;
	}


	/**
	 * Comment for setTipoVoltaje
	 * @param tipoVoltaje
	 */
	public void setTipoVoltaje(Integer tipoVoltaje) {
		this.tipoVoltaje = tipoVoltaje;
	}


	/**
	 * Comment for getTipoVoltajeID
	 * @return tipo Voltaje ID
	 */
	public TipoVoltajeID getTipoVoltajeID() {
		return tipoVoltajeID;
	}


	/**
	 * Comment for setTipoVoltajeID
	 * @param tipoVoltajeID
	 */
	public void setTipoVoltajeID(TipoVoltajeID tipoVoltajeID) {
		this.tipoVoltajeID = tipoVoltajeID;
	}

	
	/**
	 * Comment for getTipoVoltajes
	 * @return Lista de Tipo de Voltajes
	 */
	public List getTipoVoltajes(){
		return this.tipoVoltajeBO.getTiposVoltaje();
	}
	
	
	public String getInit(){
		return "success";
	}
	
	
	/**
	 * Comment for getTipoVoltajesSelectItems
	 * @return Lista para seleccionar el Tipo de Voltajes
	 */
	public List getTipoVoltajesSelectItems(){
    	List selectItems = new ArrayList();
    	List tipoVoltajes = this.tipoVoltajeBO.getTiposVoltaje();
    	for(int i=0; i<tipoVoltajes.size();i++){
    		TipoVoltaje tipoVoltaje = (TipoVoltaje) tipoVoltajes.get(i);    		
    		selectItems.add(new SelectItem(tipoVoltaje.getTipoVoltajeID().getCodigoVoltaje(), tipoVoltaje.getTipoVoltajeID().getCodigoVoltaje()+" - "+tipoVoltaje.getNombreVoltaje()));
    	}
    	return selectItems;	
	}
	
	/**
	 * Comment for getTipoVoltajesObj
	 * @return Lista de tipo de Voltaje
	 */
	public List getTipoVoltajesObj(){
    	List selectItems = new ArrayList();
    	List tipoVoltajes = this.tipoVoltajeBO.getTiposVoltaje();
    	if(tipoVoltajes != null){
	    	for(int i = 0; i < tipoVoltajes.size();i++){
	    		TipoVoltaje tipoVoltaje = (TipoVoltaje) tipoVoltajes.get(i);
	    		SelectItem itm = new SelectItem();
	    		itm.setValue(tipoVoltaje);
	    		itm.setLabel(tipoVoltaje.getTipoVoltajeID().getCodigoVoltaje() + " - " + tipoVoltaje.getNombreVoltaje() + " - " + tipoVoltaje.getCodigoDescripcion());
	    		selectItems.add(itm);
	    	}
    	}
    	return selectItems;	
	}
	
	
	/**
	 * Comment for agregarTipoVoltaje
	 * @return "success" o "error" al agregar el Tipo de Voltaje
	 */
	public String agregarTipoVoltaje() {
		
		boolean correcto = true;
		
		if(this.codigoVoltaje == null || this.codigoVoltaje.intValue() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de voltaje es requerido."));
            correcto=false;
		}
		
		if(this.tipoVoltaje == null || this.tipoVoltaje.intValue() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El tipo de voltaje es requerido."));
            correcto=false;
		}
		
		if(this.codigoDescripcion == null || this.codigoDescripcion.trim().length() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La descripción del código de voltaje es requerida."));
           correcto=false;
		}
		
		if(this.nombreVoltaje == null || this.nombreVoltaje.trim().length() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre de región es inválido."));
            correcto=false;
		}
		
		
		if(correcto) {
			
			TipoVoltajeID tipoVoltajeID = new TipoVoltajeID();
			
			tipoVoltajeID.setCodigoVoltaje(this.codigoVoltaje);
			tipoVoltajeID.setTipoVoltaje(this.tipoVoltaje);
			
			TipoVoltaje tipoVoltaje = new TipoVoltaje();
			
			tipoVoltaje.setTipoVoltajeID(tipoVoltajeID);
			tipoVoltaje.setCodigoDescripcion(this.codigoDescripcion);
			tipoVoltaje.setNombreVoltaje(this.nombreVoltaje);
			
			
			if (this.tipoVoltajeBO.existe(tipoVoltaje)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código ya existe."));
				
				return "error";

			} else {
				
				this.tipoVoltajeBO.agregar(tipoVoltaje);
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Inserción exitosa."));
			    
				
				return "success";
				
			}
			
		} else {
			
			return "error";
			
		}		
		
	}
	
	
	/**
	 * Comment for modificarTipoVoltaje
	 * @return "success" o "error" al modificar el Tipo de Voltaje
	 */
	public String modificarTipoVoltaje() {
		
		boolean correcto = true;
		
		if(this.codigoVoltaje == null || this.codigoVoltaje.intValue() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de voltaje es requerido."));
           correcto=false;
		}
		
		if(this.tipoVoltaje == null || this.tipoVoltaje.intValue() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El tipo de voltaje es requerido."));
            correcto=false;
		}
		
		if(this.codigoDescripcion == null || this.codigoDescripcion.trim().length() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La descripción del código de voltaje es requerida."));
            correcto=false;
		}
		
		if(this.nombreVoltaje == null || this.nombreVoltaje.trim().length() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre es inválido."));
            correcto=false;
		}
		
		
		if(correcto) {
			
			TipoVoltajeID tipoVoltajeID = new TipoVoltajeID();
			
			tipoVoltajeID.setCodigoVoltaje(this.codigoVoltaje);
			tipoVoltajeID.setTipoVoltaje(this.tipoVoltaje);
			
			TipoVoltaje tipoVoltaje = new TipoVoltaje();
			
			tipoVoltaje.setTipoVoltajeID(tipoVoltajeID);
			tipoVoltaje.setCodigoDescripcion(this.codigoDescripcion);
			tipoVoltaje.setNombreVoltaje(this.nombreVoltaje);			

			this.tipoVoltajeBO.modificar(tipoVoltaje);
				
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Modificación exitosa."));
			
				
			return "success";
			
		} else {
			
			return "error";
			
		}	
		
	}
	
	 
	/**
	 * Comment for eliminarTipoVoltaje
	 * @return "success" o "error" al eliminar el Tipo de Voltaje
	 */
	public String eliminarTipoVoltaje() {
		
		if(this.codigoVoltaje == null || this.codigoVoltaje.intValue() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de voltaje es requerido."));
            return "error";
		}
		
		if(this.tipoVoltaje == null || this.tipoVoltaje.intValue() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El tipo de voltaje es requerido."));
            return "error";
		}
		
		TipoVoltajeID tipoVoltajeID = new TipoVoltajeID();
		
		tipoVoltajeID.setCodigoVoltaje(this.codigoVoltaje);
		tipoVoltajeID.setTipoVoltaje(this.tipoVoltaje);
		
		TipoVoltaje tipoVoltaje = new TipoVoltaje();
		
		tipoVoltaje.setTipoVoltajeID(tipoVoltajeID);
		
		if(this.tipoVoltajeBO.registrosAsociados(tipoVoltajeID).longValue() > 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puede eliminar.  Tipo de Voltaje se encuentra asociada con diversa información."));
            
			return "error";
		}
		else{
			this.tipoVoltajeBO.eliminar(tipoVoltaje);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Eliminación exitosa."));
	        
	        
	        this.codigoDescripcion=null;
			this.codigoVoltaje=null;
			this.nombreVoltaje=null;
			this.tipoVoltaje=null;
			
			return "success";
		}
		
	}
	
	/**
	 * Metodo para buscar TipoVoltaje
	 * @return error o success
	 */
	public String buscarTipoVoltaje() {
	
		boolean correcto = true;
		
		if(this.codigoVoltaje == null || this.codigoVoltaje.intValue() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de voltaje es requerido."));
            correcto=false;
		}
		
		if(this.tipoVoltaje == null || this.tipoVoltaje.intValue() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El tipo de voltaje es requerido."));
            
			correcto=false;
		}				
		if(correcto) {
		
			TipoVoltajeID tipoVoltajeID = new TipoVoltajeID();
			
			tipoVoltajeID.setCodigoVoltaje(this.codigoVoltaje);
			tipoVoltajeID.setTipoVoltaje(this.tipoVoltaje);
			
			TipoVoltaje tipoVoltaje = this.tipoVoltajeBO.buscar(tipoVoltajeID);
			
			if(tipoVoltaje == null) {
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Tipo de voltaje no existe.."));
	            
			
			} else {
				
				this.setCodigoVoltaje(tipoVoltajeID.getCodigoVoltaje());
				this.setTipoVoltaje(tipoVoltajeID.getTipoVoltaje());
				this.setCodigoDescripcion(tipoVoltaje.getCodigoDescripcion());
				this.setNombreVoltaje(tipoVoltaje.getNombreVoltaje());
			
			}
			
			return "success";
		
		} else {
		
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
	 * Retorna una lista de tipos de voltaje donde el value es el codigo del voltaje y no el objeto TipoVoltaje
	 * @return Lista de tipo de Voltaje
	 */
	public List getListaTipoVoltajes(){
    	List selectItems = new ArrayList();
    	List tipoVoltajes = this.tipoVoltajeBO.getTiposVoltaje();
    	if(tipoVoltajes != null){
	    	for(int i = 0; i < tipoVoltajes.size();i++){
	    		TipoVoltaje tipoVoltaje = (TipoVoltaje) tipoVoltajes.get(i);
	    		SelectItem itm = new SelectItem(tipoVoltaje.getTipoVoltajeID().getCodigoVoltaje(),tipoVoltaje.getTipoVoltajeID().getCodigoVoltaje() + "-" + tipoVoltaje.getCodigoDescripcion() + "-" + tipoVoltaje.getNombreVoltaje());	    		
	    		selectItems.add(itm);
	    	}
    	}
    	return selectItems;	
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
