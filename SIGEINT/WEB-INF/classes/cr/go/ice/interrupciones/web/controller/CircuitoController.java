
package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.CircuitoBO;
import cr.go.ice.interrupciones.BO.SeccionBO;
import cr.go.ice.interrupciones.domain.Circuito;
import cr.go.ice.interrupciones.domain.CircuitoID;
import cr.go.ice.interrupciones.domain.SubEstacion;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.AnimalController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>CircuitoController.java</code> Establese la relacion entre capa de presentacion y la logica del negocio.</p>
 * <p>Fecha creación: 08/02/2007</p>
 * <p>Ultima actualización: 08/02/2007</p>
 * @author Vista Verde Soft (Mario Leon y Doc Cristian)
 * @version 1.1
 */
public class CircuitoController extends AbstractFacesController {
    /**
     * <code>circuitoBO</code> Circuito Bo
     */
    private CircuitoBO circuitoBO;
    /**
     * <code>circuito</code> Circuito
     */
    private Circuito circuito;
    /**
     * <code>codigoCircuito</code> Codigo de Circuito
     */
    private Integer codigoCircuito;
    
    private Integer codigoCircuitoAresep;
    /**
     * <code>subEstacion</code> Sub Estado
     */
    private SubEstacion subEstacion;
    /**
     * <code>nombreCircuito</code> Nombre de Circuito
     */
    private String nombreCircuito;
    /**
     * <code>tipoCircuito</code> Tipo de Circuito
     */
    private Integer tipoCircuito;
    /**
     * <code>seccionBO</code> seccion BO
     */    
    private SeccionBO seccionBO;
    
	private Double kmsCircuito;
	private Long abonadoCircuito;
    /**
     * Constructor
     */
    public CircuitoController(){
    	this.tipoCircuito = new Integer(1);
	    this.kmsCircuito = new Double(0);
	    this.abonadoCircuito = new Long(0);    
    }
    
    public String getInit(){
    	return "success";
    }
    /**
     * Comment for getCodigoCircuito
     * @return Codigo de Circuito
     */
    public Integer getCodigoCircuito() {
        return this.codigoCircuito;
    }
    
    /**
     * Comment for setCodigoCircuito
     * @param codigoCircuito
     */
    public void setCodigoCircuito(Integer codigoCircuito) {
        this.codigoCircuito = codigoCircuito;
    }
   
    /**
     * Comment for getNombreCircuito
     * @return Nombre del Circuito
     */
    public String getNombreCircuito() {
        return this.nombreCircuito;
    }
    
    /**
     * Comment for setNombreCircuito
     * @param nombreCircuito
     */
    public void setNombreCircuito(String nombreCircuito) {
        this.nombreCircuito = nombreCircuito;
    }
    
    /**
     * Comment for getSubEstacion
     * @return Sub Estacion
     */
    public SubEstacion getSubEstacion() {
        return this.subEstacion;
    }
    
    /**
     * Comment for setSubEstacion
     * @param subEstacion
     */
    public void setSubEstacion(SubEstacion subEstacion) {
        this.subEstacion = subEstacion;
    }
    
    /**
     * Comment for getTipoCircuito
     * @return Tipo de Circuito
     */
    public Integer getTipoCircuito() {
        return this.tipoCircuito;
    }
    
    /**
     * Comment for setTipoCircuito
     * @param tipoCircuito
     */
    public void setTipoCircuito(Integer tipoCircuito) {
        this.tipoCircuito = tipoCircuito;
    }
   
    /**
     * Comment for setCircuitoBO
     * @param circuitoBO
     */
    public void setCircuitoBO(CircuitoBO circuitoBO) {
        this.circuitoBO = circuitoBO;
    }
    
    
    /**
     * Comment for getTiposCircuito
     * @return Lista Tipos Circuito
     */
    public List getTiposCircuito(){
        List items = new ArrayList();
        String[] tipos = {"ORO","PLATA","BRONCE"};
        for(int i = 0; i < tipos.length; i++){
            SelectItem item = new SelectItem();
            item.setValue(new Integer(i));
            item.setLabel(tipos[i]);
            items.add(item);
        }
        return items;
    }
    
    /**
     * Comment for agregar
     * @return Agregar "success"
     */
    public String agregar(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        boolean errors = false;
        
        if(this.subEstacion == null || this.subEstacion.getCodigoSubEstacion().intValue() <= 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de subestación es requerido."));
            errors = true;
        }        
        if(this.codigoCircuito == null || this.codigoCircuito.intValue() <= 0){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de circuito es requerido."));
            errors = true;
        }
        if(this.nombreCircuito == null || this.nombreCircuito.equals("")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre del circuito es requerido."));
            errors = true;
        }
        if(this.tipoCircuito == null){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El tipo de circuito es requerido."));
            errors = true;
        }
        if(!errors && this.circuitoBO.circuitoExiste(this.subEstacion.getCodigoSubEstacion(), this.codigoCircuito)){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El circuito ya existe."));
            errors = true;
        }
        if(!errors){
            this.circuito = new Circuito();
            CircuitoID id = new CircuitoID();
            id.setSubEstacion(this.subEstacion);
            id.setCircuito(this.codigoCircuito);
            this.circuito.setCircuitoID(id);
            this.circuito.setNombreCircuito(this.nombreCircuito);
            this.circuito.setTipo(this.tipoCircuito);
            this.circuito.setAbonadoCircuito(new Long(0));
            this.circuito.setKmsCircuito(new Double(0));
            this.circuito.setCircuitoAresep(this.codigoCircuitoAresep);
            this.circuitoBO.agregar(this.circuito);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "El circuito fue agregado con éxito."));
        }
        return "success";
    }
    
    /**
     * Comment for modificar
     * @return Modificar "success"
     */
    public String modificar(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        if(this.codigoCircuito == null || this.codigoCircuito.intValue() <= 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de circuito es requerido."));
            return "error";
        }
        if(this.nombreCircuito == null || this.nombreCircuito.equals("")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre del circuito es requerdio."));
            return "error";
        }
        
        this.circuito = this.circuitoBO.buscar(this.subEstacion.getCodigoSubEstacion(), this.codigoCircuito);
        if(this.circuito == null){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El circuito no existe."));
            return "error";
        }      
        else{
            this.circuito.setNombreCircuito(this.nombreCircuito);
            this.circuito.setTipo(this.tipoCircuito);
            this.circuito.setCircuitoAresep(this.codigoCircuitoAresep);
            this.circuitoBO.modificar(this.circuito);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Circuito modificado con éxito."));
        }
        return "success";
    }
    
    /**
     * Comment for eliminar
     * @return Eliminar "success"
     */
    public String eliminar(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        this.buscar();
        if(this.circuito != null){
            long cantidad = this.seccionBO.getBorrarSecciones(this.circuito.getCircuitoID().getSubEstacion().getCodigoSubEstacion(),this.circuito.getCircuitoID().getCircuito());
            if(cantidad == 0){
	            this.circuitoBO.eliminar(this.circuito);
	           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Circuito eliminado con éxito."));
	            this.codigoCircuito = null;
	            this.nombreCircuito = null;
	            this.tipoCircuito = null;
            }
            else{
	          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El circuito no puede ser eliminado porque tiene secciones asociadas."));
            }
        }
        return "success";
    }
    
    /**
     * Comment for buscar
     * @return Buscar "success"
     */
    public String buscar(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        if(this.codigoCircuito != null && this.codigoCircuito.intValue() > 0){
            this.circuito = this.circuitoBO.buscar(this.subEstacion.getCodigoSubEstacion(), this.codigoCircuito);
            if(this.circuito != null){
            	this.subEstacion = this.circuito.getCircuitoID().getSubEstacion();
            	this.codigoCircuito = this.circuito.getCircuitoID().getCircuito();
                this.nombreCircuito = this.circuito.getNombreCircuito();                
                this.tipoCircuito = this.circuito.getTipo();
                this.kmsCircuito = this.circuito.getKmsCircuito();
                this.abonadoCircuito = this.circuito.getAbonadoCircuito();
                this.codigoCircuitoAresep = this.circuito.getCircuitoAresep();
            } else{
            	this.subEstacion = null;
            	this.codigoCircuito = null;
                this.nombreCircuito = null;                
                this.tipoCircuito = null;
                this.codigoCircuitoAresep = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Circuito no existe."));
            }
        } else{
        	this.subEstacion = null;
        	this.codigoCircuito = null;
            this.nombreCircuito = null;                
            this.tipoCircuito = null;
            this.codigoCircuitoAresep = null;
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de circuito es requerido."));
        }
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
     * Metodo accesor de seccionBO.
     * @return Retorna el seccionBO.
     */
    public SeccionBO getSeccionBO() {
        return this.seccionBO;
    }
    /**
     * Metodo modificador de seccionBO.
     * @param seccionBO El seccionBO a modificar.
     */
    public void setSeccionBO(SeccionBO seccionBO) {
        this.seccionBO = seccionBO;
    }
    /**
     * Metodo accesor de abonadoCircuito.
     * @return Retorna el abonadoCircuito.
     */
    public Long getAbonadoCircuito() {
        return this.abonadoCircuito;
    }
    /**
     * Metodo accesor de kmsCircuito.
     * @return Retorna el kmsCircuito.
     */
    public Double getKmsCircuito() {
        return this.kmsCircuito;
    }

	public Integer getCodigoCircuitoAresep() {
		return codigoCircuitoAresep;
	}

	public void setCodigoCircuitoAresep(Integer codigoCircuitoAresep) {
		this.codigoCircuitoAresep = codigoCircuitoAresep;
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