package cr.go.ice.interrupciones.web.controller;


import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.SubEstacionBO;
import cr.go.ice.interrupciones.BO.SubRegionBO;
import cr.go.ice.interrupciones.domain.Region;
import cr.go.ice.interrupciones.domain.SubRegion;
import cr.go.ice.interrupciones.domain.SubRegionID;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.AnimalController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SubRegionController.java</code> Establese la relacion entre capa de presentacion y la logica del negocio.</p>
 * <p>Fecha creación: 22/02/2007</p>
 * <p>Ultima actualización: 22/02/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class SubRegionController extends AbstractFacesController {

	/**
	 * <code>region</code> Region
	 */
	private Region region;	
	/**
	 * <code>codigoSubregion</code> Codigo SubRegion
	 */
	private Integer codigoSubregion;
	/**
	 * <code>nombreSubregion</code> Nombre del SubREgion
	 */
	private String nombreSubregion;
	/**
	 * <code>subRegion</code>SubRegion
	 */
	private SubRegion subRegion;
	/**
	 * <code>subRegionBO</code>SubRegionBO
	 */
	private SubRegionBO subRegionBO;
	/**
	 * <code>subEstacionBO</code>subEstacionBO
	 */	
	private SubEstacionBO subEstacionBO;
	
	private Double kmsSubRegion;
	private Long abonadoSubRegion;
	
	/**
	 * Constructor
	 */
	public SubRegionController(){
	    this.kmsSubRegion = new Double(0);
	    this.abonadoSubRegion = new Long(0);
	}
	
	public String getInit(){
		return "success";
	}
	
	/**
	 * Comment for getItemsRegiones
	 * @return Vector Lista de Regiones
	 */
	public List getItemsRegiones(){		
		List regiones = null;
		regiones = new Vector();
		regiones.add(new SelectItem(new Integer(1),"01 Region1"));
		regiones.add(new SelectItem(new Integer(2),"02 Region2"));
		regiones.add(new SelectItem(new Integer(3),"03 Region3"));
		return regiones;						
	}
	
	/**
	 * @return Devuelve region.
	 */
	public Region getRegion() {
		return region;
	}
	/**
	 * @param region El region a establecer.
	 */
	public void setRegion(Region region) {
		this.region = region;
	}
	
	/**
	 * @return Devuelve codigoSubregion.
	 */
	public Integer getCodigoSubregion() {
		return codigoSubregion;
	}
	/**
	 * @param codigoSubregion El codigoSubregion a establecer.
	 */
	public void setCodigoSubregion(Integer codigoSubregion) {
		this.codigoSubregion = codigoSubregion;
	}
	
	/**
	 * @return Devuelve nombreSubregion.
	 */
	public String getNombreSubregion() {
		return nombreSubregion;
	}
	/**
	 * @param nombreSubregion El nombreSubregion a establecer.
	 */
	public void setNombreSubregion(String nombreSubregion) {
		this.nombreSubregion = nombreSubregion;
	}
	
	/**
	 * @return Devuelve subRegionBO.
	 */
	public SubRegionBO getSubRegionBO() {
		return subRegionBO;
	}
	/**
	 * @param subRegionBO El subRegionBO a establecer.
	 */
	public void setSubRegionBO(SubRegionBO subRegionBO) {
		this.subRegionBO = subRegionBO;
	}
	
	/**
	 * @return Devuelve subRegion.
	 */
	public SubRegion getSubRegion() {
		return subRegion;
	}
	/**
	 * @param subRegion El subRegion a establecer.
	 */
	public void setSubRegion(SubRegion subRegion) {
		this.subRegion = subRegion;
	}
	
    /**
     * Metodo para action
     * @return
     */
	public String action() {
		return "";
	 }
	
	/**
	 * Comment for buscar
	 * @return "success" al buscar SubEstacion
	 */
	public String buscar(){		
	
        if(this.codigoSubregion != null && this.codigoSubregion.intValue() > 0){
        	SubRegionID subRegionID = new SubRegionID();    
        	subRegionID.setRegion(this.region);
        	subRegionID.setSubRegion(this.codigoSubregion);        	
            this.subRegion = subRegionBO.buscar(subRegionID);
            if(this.subRegion != null){
                this.nombreSubregion = this.subRegion.getNombreSubRegion();    
                this.kmsSubRegion = this.subRegion.getKmsSubRegion();
                this.abonadoSubRegion = this.subRegion.getAbonadoSubregion();
            }else{                
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "SubRegion no existe."));
                
            }
        } else{
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de subregion es requerido."));
            
        }
		return "success";		
	}
	
	/**
	 * Comment for agregar
	 * @return "success" al agregar SubEstacion
	 */
	public String agregar(){
      
        boolean errors = false;
        if(this.region == null){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de región es requerido."));
             errors = true;
        }
        if(this.codigoSubregion == null || this.codigoSubregion.intValue() <= 0){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de subregión es requerido."));
            errors = true;
        }
        if(this.nombreSubregion == null || this.nombreSubregion.equals("")){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre de la subregión es requerida."));
            errors = true;
        }        
        SubRegionID subRegionID = new SubRegionID();
        subRegionID.setSubRegion(this.codigoSubregion);                
        subRegionID.setRegion(this.region);
        if(!errors && this.subRegionBO.subRegionExiste(subRegionID)){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La subregión ya existe."));
            errors = true;
        }
        if(!errors){
            this.subRegion = new SubRegion();  
            subRegionID.setRegion(region);
            subRegion.setNombreSubRegion(this.nombreSubregion);
            subRegion.setSubRegionID(subRegionID);
            subRegion.setAbonadoSubregion(new Long(0));
            subRegion.setKmsSubRegion(new Double(0));
            this.subRegionBO.agregar(subRegion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La subregión fue agregada con éxito."));
            
                     
        }
		return "success";
	}
	
	/**
	 * Comment for modificar
	 * @return "success" al modificar SubEstacion
	 */
	public String modificar(){
       
        if(this.codigoSubregion == null || this.codigoSubregion.intValue() <= 0){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de subregión es requerido."));
            return "error";
        }        
        if(this.nombreSubregion == null || this.nombreSubregion.length() == 0){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre de la subregión es requerida."));
            return "error";
        }
    	SubRegionID subRegID = new SubRegionID();    
    	subRegID.setRegion(this.region);
    	subRegID.setSubRegion(this.codigoSubregion);        	
        this.subRegion = subRegionBO.buscar(subRegID);
        if(this.subRegion != null){
            subRegion.setNombreSubRegion(this.nombreSubregion);
            subRegion.setAbonadoSubregion(new Long(0));            
            subRegion.setKmsSubRegion(new Double(0));            
            this.subRegionBO.modificar(subRegion);           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Éxito!", "Subregión modificada con éxito."));
            
        }       
        else{
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Subregión no existe."));
              
        }
		return "success";
	}
	
	/**
	 * Comment for eliminar
	 * @return "success" al eliminar SubEstacion
	 */
	public String eliminar(){
      
        if(this.codigoSubregion == null || this.codigoSubregion.intValue() <= 0){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de subregión es requerido."));
            return "error";
        }           
    	SubRegionID subRegID = new SubRegionID();    
    	subRegID.setRegion(this.region);
    	subRegID.setSubRegion(this.codigoSubregion);        	
        this.subRegion = subRegionBO.buscar(subRegID);
        if(this.subRegion != null){            
			List subEstaciones = this.subEstacionBO.getSubEstaciones(this.subRegion.getSubRegionID().getRegion().getRegion(), this.subRegion.getSubRegionID().getSubRegion());            
			if(subEstaciones == null || subEstaciones.size() == 0){            	           
	           	this.subRegionBO.eliminar(this.subRegion);
	           	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Subregión eliminada con éxito."));
	            
	            this.region = null;
	            this.codigoSubregion = null;
	            this.nombreSubregion = null;
			}
			else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puede eliminar.  La subregión tiene subestaciones asociadas."));
	            
			}			
        }
        else{
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Subregión no existe."));
              
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
     * Metodo accesor de abonadoSubRegion.
     * @return Retorna el abonadoSubRegion.
     */
    public Long getAbonadoSubRegion() {
        return this.abonadoSubRegion;
    }
    /**
     * Metodo accesor de kmsSubRegion.
     * @return Retorna el kmsSubRegion.
     */
    public Double getKmsSubRegion() {
        return this.kmsSubRegion;
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
