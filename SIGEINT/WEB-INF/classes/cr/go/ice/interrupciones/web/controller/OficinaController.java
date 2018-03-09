package cr.go.ice.interrupciones.web.controller;


import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.OficinaBO;
import cr.go.ice.interrupciones.domain.Oficina;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.AnimalController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>OficinaController.java</code> Establese la relacion entre capa de presentacion y la logica del negocio.</p>
 * <p>Fecha creación: 25/01/2007</p>
 * <p>Ultima actualización: 25/01/2007</p>
 * @author Vista Verde Soft (Administrador y Doc Cristian)
 * @version 1.1
 */
public class OficinaController extends AbstractFacesController{
    /**
     * <code>codigoOficina</code> Codigo Oficina
     */
    private Integer codigoOficina;
    /**
     * <code>nombreOficina</code> Nombre Oficina
     */
    private String nombreOficina;
    
    /**
     * <code>oficina</code> Oficina
     */
    private Oficina oficina;
    /**
     * <code>oficinaBO</code> Oficina BO
     */
    private OficinaBO oficinaBO;
    
    //private ResourceBundle rsc = ResourceBundle.getBundle("cr.go.ice.interupciones.web.resources");
    
    /**
     * Constructor
     */
    public OficinaController(){
        this.codigoOficina = new Integer(0);
        this.nombreOficina = "";
        this.oficina = null;
    }
    
    public String getInit(){
    	return "success";
    }
    
    /**
     * Metodo accesor de codigoOficina.
     * @return Retorna el codigoOficina.
     */
    public Integer getCodigoOficina() {
        return this.codigoOficina;
    }
    /**
     * Metodo modificador de codigoOficina.
     * @param codigoOficina El codigoOficina a modificar.
     */
    public void setCodigoOficina(Integer codigoOficina) {
        this.codigoOficina = codigoOficina;
    }
    /**
     * Metodo accesor de nombreOficina.
     * @return Retorna el nombreOficina.
     */
    public String getNombreOficina() {
        return this.nombreOficina;
    }
    /**
     * Metodo modificador de nombreOficina.
     * @param nombreOficina El nombreOficina a modificar.
     */
    public void setNombreOficina(String nombreOficina) {
        this.nombreOficina = nombreOficina.toUpperCase();
    }
    /**
     * Metodo accesor de oficinaBO.
     * @return Retorna el oficinaBO.
     */
    public OficinaBO getOficinaBO() {
        return this.oficinaBO;
    }
    /**
     * Metodo modificador de oficinaBO.
     * @param oficinaBO El oficinaBO a modificar.
     */
    public void setOficinaBO(OficinaBO oficinaBO) {
        this.oficinaBO = oficinaBO;
    }
    

    /**
     * Comment for agregar
     * @return "success" o "error" al agregar Oficina
     */
    public String agregar(){
        if(this.codigoOficina == null || this.codigoOficina.intValue() <= 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }
        
        if(this.nombreOficina != null && this.nombreOficina.length() > 0){
            this.oficina = new Oficina();
            this.oficina.setCodigoOficina(this.codigoOficina);
            this.oficina.setNombreOficina(this.nombreOficina.toUpperCase());
	        if(!this.oficinaBO.oficinaExiste(this.oficina)){
	            this.oficinaBO.agregar(this.oficina);
	           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Oficina agregada con éxito."));
	            return "success";
	        } else{
	          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La oficina ya existe."));
	            return "error";
	        }
        } else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El valor es requerido."));
            return "error";
        }
    }
    
    /**
     * Comment for modificar
     * @return "success" o "error" al modificar Oficina
     */
    public String modificar(){
        FacesMessage msg = new FacesMessage();
        
        if(this.codigoOficina == null || this.codigoOficina.intValue() <= 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }
        
        this.oficina = this.oficinaBO.buscar(this.codigoOficina);
        if(this.oficina == null){
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La oficina no existe."));
            return "error";
        }
        if (this.nombreOficina.equals(this.oficina.getNombreOficina())) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre de la oficina no ha sido cambiado."));
            return "error";
        }
        Oficina ofic2 = this.oficinaBO.buscar(this.nombreOficina);
        if (ofic2 == null || ofic2.getCodigoOficina().equals(this.oficina.getCodigoOficina())) {
            this.oficina.setNombreOficina(this.nombreOficina);
            this.oficinaBO.modificar(this.oficina);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Oficina modificada con éxito."));
            return "success";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre de la oficina ya está registrado para la oficina."));
            return "error";
        }
        
    }
    
    /**
     * Comment for eliminar
     * @return "success" o "error" al eliminar Oficina
     */
    public String eliminar() {
        if(this.codigoOficina == null || this.codigoOficina.intValue() <= 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }
        
        this.oficina = this.oficinaBO.buscar(this.codigoOficina);
        if (this.oficina != null && this.oficinaBO.oficinaExiste(this.oficina)) {
			if(this.oficinaBO.registrosAsociados(this.oficina.getCodigoOficina()).longValue() > 0){
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede eliminar!", "Oficina se encuentra asociada con diversa información."));
				return "error";
			}
			else{	
			    if(this.oficinaBO.tieneOficinasAsociadasACorreos(this.oficina.getCodigoOficina())){
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede eliminar!", ".Oficina se encuentra asociada con la información de correos."));
                    return "error";
                }else{
                    try{
                        this.oficinaBO.eliminar(this.oficina);
                    }
                    catch(Exception e){
                       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede eliminar!", "Oficina se encuentra asociada con diversa información."));
                        return "error"; 
                    }
    	            this.oficina = null;
    	            this.codigoOficina = new Integer(0);
    	            this.nombreOficina = "";
    	           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Oficina eliminada con éxito."));
    	            return "success";
                }
			}
        } else {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La oficina no existe."));
            return "error";
        }
    }
    
    
    /**
     * Comment for buscar
     * @return "success" o "error" al buscar Oficina
     */
    public String buscar(){
        if(this.codigoOficina == null || this.codigoOficina.intValue() <= 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }
        
        this.oficina = this.oficinaBO.buscar(this.codigoOficina);
        if(oficina != null){
            this.codigoOficina = this.oficina.getCodigoOficina();
            this.nombreOficina = this.oficina.getNombreOficina();
            return "success";
        } else{
            this.nombreOficina = "";            
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La oficina no existe."));
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
     * Comment for getOficinasSelectItems
     * @return Lista Oficinas
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List getOficinasSelectItems(){
    	List selectItems = new ArrayList();
    	List oficinas = this.oficinaBO.getOficinas();
    	
    	selectItems.add(new SelectItem(new Integer(0), "Seleccione una oficina"));
    	
    	for(int i=0; i<oficinas.size();i++){
    		Oficina oficina = (Oficina) oficinas.get(i);
    		selectItems.add(new SelectItem(oficina.getCodigoOficina(), oficina.getCodigoOficina() +" - "+ oficina.getNombreOficina()));
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