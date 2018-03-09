package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.GlosarioBO;
import cr.go.ice.interrupciones.domain.Glosario;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.AnimalController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>GlosarioController.java</code> Establese la relacion entre capa de presentacion y la logica del negocio.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (Cesar y Doc Cristian)
 * @version 1.1
 */
public class GlosarioController extends AbstractFacesController {

	/**
	 * <code>termino</code> Termino
	 */
	private String termino;

	/**
	 * <code>descripcion</code> Descripcion
	 */
	private String descripcion;

	/**
	 * <code>glosarioBO</code> Glosario BO
	 */
	private GlosarioBO glosarioBO = null;
	
	
	
	
	/**
	 * Comment for setGlosarioBO
	 * @param glosarioBO
	 */
	public void setGlosarioBO(GlosarioBO glosarioBO) {
		this.glosarioBO = glosarioBO;
	}

	/**
	 * Constructor
	 */
	public GlosarioController() {
		this.termino = "";
		this.descripcion = "";
	}

	public String getInit(){
		return "succes";
	}
	
	/**
	 * Comment for agregar
	 * @return "success" o "error" al agregar Glosario
	 */
	public String agregar() {
	
		
        if(this.termino == null || this.termino.trim().length() == 0){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }
        
		//Validaciones
		if (this.descripcion.trim().length()==0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo es requerido", "No se actualizó el registro por errores de validación."));
			return "error";
		}

		Glosario glosario = new Glosario();
		glosario.setTermino(this.termino);
		glosario.setDescripcion(this.descripcion);
		
		try {
			this.glosarioBO.agregar(glosario);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Glosario agregado con éxito."));
			return "success";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
			return "error";
		}

	
	}

	/**
	 * Comment for modificar
	 * @return "success" o "error" al modificar Glosario
	 */
	public String modificar() {
		
        
        if(this.termino == null || this.termino.trim().length() == 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }
        
        if (this.descripcion.trim().length()==0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo es requerido", "No se actualizó el registro por errores de validación."));
            return "error";
        }
        
		Glosario glosario = this.glosarioBO.buscar(termino);
		if (glosario == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El glosario no existe."));
			return "error";
		}
		glosario.setDescripcion(this.descripcion);
		this.glosarioBO.modificar(glosario);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Glosario modificado con éxito."));
		return "success";

	}

	/**
	 * Comment for eliminar
	 * @return "success" o "error" al eliminar Glosario
	 */
	public String eliminar() {
		
        
        if(this.termino == null || this.termino.trim().length() == 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }
        
		Glosario glosario = this.glosarioBO.buscar(termino);
		if (glosario != null) {
			this.glosarioBO.eliminar(glosario);
			this.termino = "";
			this.descripcion = "";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Glosario eliminado con éxito."));
			return "success";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El glosario no existe."));
			return "error";
		}
	}

	
	/**
	 * Comment for buscar
	 * @return "success" o "error" al buscar Glosario
	 */
	public String buscar() {
      
        
        if(this.termino == null || this.termino.trim().length() == 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }
        
		Glosario glosario = this.glosarioBO.buscar(this.termino);
		if (glosario != null) {
			this.termino = glosario.getTermino();
			this.descripcion = glosario.getDescripcion();
			return "success";
		} else {
			this.descripcion = "";			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El glosario especificado no existe."));
			return "error";
		}
	}

	/**
	 * Comment for getDescripcion
	 * @return Returns the descripcion.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Comment for setDescripcion
	 * @param descripcion The descripcion to set.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Comment for getTermino
	 * @return Returns the termino.
	 */
	public String getTermino() {
		return termino;
	}

	/**
	 * Comment for setTermino
	 * @param termino The termino to set.
	 */
	public void setTermino(String termino) {
		this.termino = termino;
	}
	
	/**
	 * Retorna success para implementar una regla de navegacion
	 * @return
	 */
	public String irListaTerminos(){
	    return "success";
	}
	
    /**
     * Cancela
     * @return success
     */
    public String cancelar(){
        return "success";
    }
    
	/**
	 * Retorna una lista de select item de los términos del glosario
	 * @return Lista de términos del glosario
	 */
	public List getListaTerminos(){
	    List terminosSI = new ArrayList();
	    terminosSI.add(new SelectItem("","Seleccione un término"));
	    List terminos = this.glosarioBO.getGlosarios();
	    if(terminos != null){
	        for(int i = 0; i < terminos.size(); i++){
	            Glosario glosario = (Glosario)terminos.get(i);
	            terminosSI.add(new SelectItem(glosario.getTermino(),glosario.getTermino()));
	        }
	    }
	    return terminosSI;	    
	}
	
	/**
	 * Muestra el contenido del item (término) seleccionado por el usuario
	 * @param v
	 * @return listener
	 */
	public String listenerTermino(){	    
		String termino = new String();	
		Glosario glosario = this.glosarioBO.buscar(termino);
		if(glosario != null)
		    this.setDescripcion(glosario.getDescripcion());
		else
		    this.setDescripcion("");
		return "listener";
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
