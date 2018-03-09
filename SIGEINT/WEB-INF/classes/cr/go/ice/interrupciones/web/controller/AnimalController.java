package cr.go.ice.interrupciones.web.controller;

import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.AnimalBO;
import cr.go.ice.interrupciones.domain.Animal;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.AnimalController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>AnimalController.java</code> Establese la relacion entre capa de presentacion y la logica del negocio.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (Cesar y Doc Cristian)
 * @version 1.1
 */
public class AnimalController extends AbstractFacesController {
	
	/**
	 * <code>codigoAnimal</code> Codigo del Animal
	 */
	private Integer codigoAnimal;
	/**
	 * <code>nombreAnimal</code> Nombre del Animal
	 */
	private String nombreAnimal;
	/**
	 * <code>tipoAnimal</code> Tipo de Animal
	 */
	private Integer tipoAnimal;
	
	 
	 /**
	 * <code>animalBO</code> Animal 1BO
	 */
	private AnimalBO animalBO;
	 
	 
	public String getInit(){
		return "success";
	}
	
	 
	/**
	 * Comment for getCodigoAnimal
	 * @return Codigo del Animal
	 */
	public Integer getCodigoAnimal() {
		return codigoAnimal;
	}
	
	/**
	 * Comment for setCodigoAnimal
	 * @param codigoAnimal
	 */
	public void setCodigoAnimal(Integer codigoAnimal) {
		this.codigoAnimal = codigoAnimal;
	}
	
	/**
	 * Comment for getNombreAnimal
	 * @return Nombre del Animal
	 */
	public String getNombreAnimal() {
		return nombreAnimal;
	}
	
	/**
	 * Comment for setNombreAnimal
	 * @param nombreAnimal
	 */
	public void setNombreAnimal(String nombreAnimal) {
		this.nombreAnimal = nombreAnimal;
	}
	
	/**
	 * Comment for getTipoAnimal
	 * @return Tipo de Animal
	 */
	public Integer getTipoAnimal() {
		return tipoAnimal;
	}
	
	/**
	 * Comment for setTipoAnimal
	 * @param tipoAnimal
	 */
	public void setTipoAnimal(Integer tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}
	
	/**
	 * Comment for agregar
	 * @return "Success" o "Error" al agregar Animal
	 */
	public String agregar(){
        FacesMessage msg = new FacesMessage();
        
        if(this.codigoAnimal == null || this.codigoAnimal.intValue() <= 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de animal es requerido."));
            return "error";
        }
        if(this.nombreAnimal == null || this.nombreAnimal.trim().length() == 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre del aniaml es requerido."));
            return "error";
        }       
        
        Animal animal = new Animal();
        animal.setCodigoAnimal(this.codigoAnimal);
        animal.setNombreAnimal(this.nombreAnimal.toUpperCase());
        animal.setTipoAnimal(this.tipoAnimal);
        
        if(!this.animalBO.animalExiste(animal)){
            this.animalBO.agregar(animal);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Animal agregado con éxito."));
            return "success";
        } else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El animal especificado ya existe."));
            return "error";
        }
        
    }
    
    /**
     * Comment for modificar
     * @return "Success" o "Error" al modificar Animal
     */
    public String modificar(){
        FacesMessage msg = new FacesMessage();
        
    	if(this.codigoAnimal == null || this.codigoAnimal.intValue() <= 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de anial es requerido."));
            return "error";
    	}
        
        Animal animal = this.animalBO.buscar(this.codigoAnimal);
        if(animal == null){
          FacesContext.getCurrentInstance().addMessage("form1:codigoAnimal", msg);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El animal no existe."));
            return "error";
        }
        if (this.nombreAnimal.equals(animal.getNombreAnimal())) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre del animal no ha sido cambiado."));
            return "error";
        }
        Animal animal2 = this.animalBO.buscar(this.codigoAnimal);
        if (animal2 != null && animal.getCodigoAnimal().equals(animal.getCodigoAnimal())) {
            animal2.setNombreAnimal(this.nombreAnimal);
            animal2.setTipoAnimal(this.tipoAnimal);
            this.animalBO.modificar(animal2);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Animal modificado con éxito."));
            return "success";
        } else {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El animal con el código "+this.codigoAnimal+" no existe."));
            return "error";
        }
        
    }
    
    /**
     * Comment for eliminar
     * @return "Success" o "Error" al eliminar Animal
     */
    public String eliminar() {
        FacesMessage msg = new FacesMessage();
        
    	if(this.codigoAnimal == null || this.codigoAnimal.intValue() <= 0){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código del animal es requerido."));
            return "error";
    	}
        
        Animal animal = this.animalBO.buscar(this.codigoAnimal);
        if (animal != null && this.animalBO.animalExiste(animal)) {            
    		if(this.animalBO.registrosAsociados(this.codigoAnimal).longValue() > 0){
    		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede eliminar!", "Animal se encuentra asociada con diversa información."));
                return "error";
    		}            
            else{
	            this.animalBO.eliminar(animal);
	            animal = null;
	            this.codigoAnimal = new Integer(0);
	            this.nombreAnimal = null;
	            this.tipoAnimal =null;
	           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Animal eliminado con éxito."));
	            return "success";
            }
        } else {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El animal no existe."));
            return "error";
        }
    }
    
    
    /**
     * Comment for buscar
     * @return "Success" o "Error" al buscar Animal
     */
    public String buscar(){
    	if(this.codigoAnimal == null || this.codigoAnimal.intValue() <= 0){
            FacesMessage msg = new FacesMessage();
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de animal es requerido."));
            return "error";
    	}
    	
        Animal animal = this.animalBO.buscar(this.codigoAnimal);
        if(animal != null){
            this.codigoAnimal = animal.getCodigoAnimal();
            this.nombreAnimal = animal.getNombreAnimal();
            this.tipoAnimal = animal.getTipoAnimal();
            return "success";
        } else{
            this.nombreAnimal = "";
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El animal especificado no existe."));
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
	 * Comment for getAnimalBO
	 * @return Anima lBO
	 */
	public AnimalBO getAnimalBO() {
		return animalBO;
	}
	
	/**
	 * Comment for setAnimalBO
	 * @param animalBO
	 */
	public void setAnimalBO(AnimalBO animalBO) {
		this.animalBO = animalBO;
	}
	
	/**
	 * Comment for getTiposAnimal
	 * @return Lista Tipo Vector para Tipos de Animal
	 */
	public List getTiposAnimal(){
		Vector vector = new Vector();
		vector.add(new SelectItem(new Integer(1),"Voladores"));
		vector.add(new SelectItem(new Integer(2),"Trepadores"));
		vector.add(new SelectItem(new Integer(3),"Mono"));
		
		return vector;
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
