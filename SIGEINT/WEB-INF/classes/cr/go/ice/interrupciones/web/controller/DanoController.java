package cr.go.ice.interrupciones.web.controller;


import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.DanoBO;
import cr.go.ice.interrupciones.domain.Dano;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.AnimalController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>DanoController.java</code> Establese la relacion entre capa de presentacion y la logica del negocio.</p>
 * <p>Fecha creación: 25/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (Administrador y Doc Cristian)
 * @version 1.1
 */
public class DanoController  extends AbstractFacesController{
    /**
     * <code>codigoDano</code> Codigo de Dano
     */
    private Integer codigoDano;
    /**
     * <code>nombreDano</code> Nombre de vDano
     */
    private String nombreDano;

    /**
     * <code>dano</code> Dano
     */
    private Dano dano;
    /**
     * <code>danoBO</code> Dano BO
     */
    private DanoBO danoBO;

    //private ResourceBundle rsc = ResourceBundle.getBundle("cr.go.ice.interupciones.web.resources");

    /**
     * Constructor
     */
    public DanoController() {
        this.codigoDano = new Integer(0);
        this.nombreDano = "";
        this.dano = null;
    }
    
    public String getInit(){
    	return "success";
    }

    /**
     * Metodo accesor de codigoDano.
     * @return Retorna el codigoDano.
     */
    public Integer getCodigoDano() {
        return this.codigoDano;
    }

    /**
     * Metodo modificador de codigoDano.
     * @param codigoDano El codigoDano a modificar.
     */
    public void setCodigoDano(Integer codigoDano) {
        this.codigoDano = codigoDano;
    }

    /**
     * Metodo accesor de nombreDano.
     * @return Retorna el nombreDano.
     */
    public String getNombreDano() {
        return this.nombreDano;
    }

    /**
     * Metodo modificador de nombreDano.
     * @param nombreDano El nombreDano a modificar.
     */
    public void setNombreDano(String nombreDano) {
        this.nombreDano = nombreDano.toUpperCase();
    }

    /**
     * Metodo accesor de danoBO.
     * @return Retorna el danoBO.
     */
    public DanoBO getDanoBO() {
        return this.danoBO;
    }

    /**
     * Metodo modificador de danoBO.
     * @param danoBO El danoBO a modificar.
     */
    public void setDanoBO(DanoBO danoBO) {
        this.danoBO = danoBO;
    }

    /**
     * Comment for agregar
     * @return "Success" o "Error" al agregar Dano
     */
    public String agregar() {
        FacesMessage msg = new FacesMessage();
        
        if(this.codigoDano == null || this.codigoDano.intValue() <= 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }
        
        if (this.nombreDano != null && this.nombreDano.length() > 0) {
            this.dano = new Dano();
            this.dano.setCodigoDano(this.codigoDano);
            this.dano.setNombreDano(this.nombreDano.toUpperCase());
            if (!this.danoBO.danoExiste(this.dano)) {
                this.danoBO.agregar(this.dano);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Daño agregado con éxito."));
                return "success";
            } else {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El daño especificado ya existe."));
                return "error";
            }
        } else {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El valor es requerido."));
            return "error";
        }
    }

    /**
     * Comment for modificar
     * @return "Success" o "Error" al modificar Dano
     */
    public String modificar() {
        FacesMessage msg = new FacesMessage();
        
        if(this.codigoDano == null || this.codigoDano.intValue() <= 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }
        
        this.dano = this.danoBO.buscar(this.codigoDano);
        if (this.dano == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El daño no existe."));
            return "error";
        }
        if (this.nombreDano.equals(this.dano.getNombreDano())) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre del daño no ha sido cambiado."));
            return "error";
        }
        Dano dan2 = this.danoBO.buscar(this.nombreDano);
        if (dan2 == null || dan2.getCodigoDano().equals(this.dano.getCodigoDano())) {
            this.dano.setNombreDano(this.nombreDano);
            this.danoBO.modificar(this.dano);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Daño modificado con éxito."));
            return "success";
        } else {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre del daño ya esta registrado para otro daño."));
            return "error";
        }

    }

    /**
     * Comment for eliminar
     * @return "Success" o "Error" al eliminar Dano
     */
    public String eliminar() {
        FacesMessage msg = new FacesMessage();
        
        if(this.codigoDano == null || this.codigoDano.intValue() <= 0){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }
        
        this.dano = this.danoBO.buscar(this.codigoDano);
        if (this.dano != null && this.danoBO.danoExiste(this.dano)) {
    		if(this.danoBO.registrosAsociados(this.codigoDano).longValue() > 0){
    		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede eliminar!", "Daño se encuentra asociada con diversa información."));
    			return "error";
    		}   
    		else{
	            this.danoBO.eliminar(this.dano);
	            this.dano = null;
	            this.codigoDano = new Integer(0);
	            this.nombreDano = "";
	           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Daño eliminado con éxito."));
	            return "success";
    		}
        } else {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El daño no existe."));
            return "error";
        }
    }

    /**
     * Metodo para buscar danos
     * @return "Success" o "Error" al buscar Dano
     */
    public String buscar() {
        FacesMessage msg = new FacesMessage();
        if(this.codigoDano == null || this.codigoDano.intValue() <= 0){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }
        
        this.dano = this.danoBO.buscar(this.codigoDano);
        if (dano != null) {
            this.codigoDano = this.dano.getCodigoDano();
            this.nombreDano = this.dano.getNombreDano();
            return "success";
        } else {
            this.nombreDano = "";
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El daño especificado no existe."));
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
     * Metodo getDanosSelectItems
     * @return lista de objestos Dano en formato select item
     */
    public List getDanosSelectItems(){
    	List selectItems = new ArrayList();
    	List danos = this.danoBO.getDanos();
    	for(int i=0; i<danos.size();i++){
    		Dano dano = (Dano) danos.get(i);
    		selectItems.add(new SelectItem(dano.getCodigoDano(), dano.getCodigoDano()+" - "+dano.getNombreDano()));
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