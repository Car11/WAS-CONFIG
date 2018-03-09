package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.datatable.DataTable;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.RegionBO;
import cr.go.ice.interrupciones.BO.SubRegionBO;
import cr.go.ice.interrupciones.domain.Region;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.AnimalController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>RegionesController.java</code> Establese la relacion entre capa de presentacion y la logica del negocio.</p>
 * <p>Fecha creación: 25/01/2007</p>
 * <p>Ultima actualización: 25/01/2007</p>
 * @author Vista Verde Soft (Juan MAnuel y Doc Cristian)
 * @version 1.1
 */
public class RegionesController extends AbstractFacesController{
	
	/**
	 * <code>numeroRegion</code> Numero de Region
	 */
	private Integer numeroRegion;
	/**
	 * <code>nombreRegion</code> Nombre de Region
	 */
	private String nombreRegion;
	/**
	 * <code>kmsRegion</code> Kms de Region
	 */
	private Double kmsRegion;
	/**
	 * <code>abonadoRegion</code> Abonado de Region
	 */
	private Long abonadoRegion;
	/**
	 * <code>demanda</code> Demanda
	 */
	private Long demanda;
	/**
	 * <code>energia</code> Energia
	 */
	private Long energia;

	/**
	 * <code>regionBO</code> Region BO
	 */
	private RegionBO regionBO;
	/**
	 * <code>regionDetalle</code> Region de Detalle
	 */
	private Region regionDetalle;
	/**
	 * <code>tableData</code> Tabla de Datos
	 */
	private DataTable tableData;
	/**
	 * <code>subRegionBO</code> subRegion BO
	 */	
	private SubRegionBO subRegionBO;
	 
    /**
     * Constructor  
     */
	public RegionesController(){
	    this.abonadoRegion = new Long(0);
	    this.kmsRegion = new Double(0);
	}
	
	public String getInit(){
		return "success";
	}
	/**
	 * Comment for getDemanda
	 * @return demanda
	 */
	public Long getDemanda() {
		return demanda;
	}

	/**
	 * Comment for setDemanda
	 * @param demanda
	 */
	public void setDemanda(Long demanda) {
		this.demanda = demanda;
	}

	/**
	 * Comment for getEnergia
	 * @return energia
	 */
	public Long getEnergia() {
		return energia;
	}

	/**
	 * Comment for setEnergia
	 * @param energia
	 */
	public void setEnergia(Long energia) {
		this.energia = energia;
	}

	/**
	 * Comment for getNombreRegion
	 * @return nombre Region
	 */
	public String getNombreRegion() {
		return nombreRegion;
	}

	/**
	 * Comment for getKmsRegion
	 * @return kms Region
	 */
	public Double getKmsRegion() {
		return kmsRegion;
	}

	/**
	 * Comment for setKmsRegion
	 * @param kmsRegion
	 */
	public void setKmsRegion(Double kmsRegion) {
		this.kmsRegion = kmsRegion;
	}
	
	/**
	 * Comment for getAbonadoRegion
	 * @return abonado Region
	 */
	public Long getAbonadoRegion() {
		return abonadoRegion;
	}

	/**
	 * Comment for setAbonadoRegion
	 * @param abonadoRegion
	 */
	public void setAbonadoRegion(Long abonadoRegion) {
		this.abonadoRegion = abonadoRegion;
	}
		
	/**
	 * Comment for setNombreRegion
	 * @param nombreRegion
	 */
	public void setNombreRegion(String nombreRegion) {
		this.nombreRegion = nombreRegion;
	}

	/**
	 * Comment for getNumeroRegion
	 * @return numero Region
	 */
	public Integer getNumeroRegion() {
		return numeroRegion;
	}

	/**
	 * Comment for setNumeroRegion
	 * @param numeroRegion
	 */
	public void setNumeroRegion(Integer numeroRegion) {
		this.numeroRegion = numeroRegion;
	}
	
	/**
	 * Comment for getRegionBO
	 * @return region BO
	 */
	public RegionBO getRegionBO() {
		return regionBO;
	}

	/**
	 * Comment for setRegionBO
	 * @param regionBO
	 */
	public void setRegionBO(RegionBO regionBO) {
		this.regionBO = regionBO;
	}

	/**
	 * Comment for getRegionDetalle
	 * @return region Detalle
	 */
	public Region getRegionDetalle() {
		return regionDetalle;
	}

	/**
	 * Comment for setRegionDetalle
	 * @param regionDetalle
	 */
	public void setRegionDetalle(Region regionDetalle) {
		this.regionDetalle = regionDetalle;
	}

	/**
	 * Comment for getTableData
	 * @return table Data
	 */
	public DataTable getTableData() {
		return tableData;
	}

	/**
	 * Comment for setTableData
	 * @param tableData
	 */
	public void setTableData(DataTable tableData) {
		this.tableData = tableData;
	}
	
	
	/**
	 * Comment for agregarRegion
	 * @return "success" o "failure" al agregar Region
	 */
	public String agregarRegion() {

		boolean correcto = true;	
		
		
		if(this.numeroRegion == null || this.numeroRegion.intValue() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de región es requerido."));
			correcto=false;
		}else{
		    if(this.numeroRegion > Integer.valueOf(9)){
		        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de la región debe ser menor a 10."));
	            correcto=false;
		    }
		}
		
		if(this.nombreRegion == null || this.nombreRegion.trim().length() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre de la región es inválido."));
			correcto=false;
		}
		
		if(this.kmsRegion == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de kilometros es requerido."));
			correcto=false;
		}
		
		if(this.abonadoRegion == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de abonado es requerido."));
			correcto=false;
		}
		
		if(this.demanda == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de demanda es requerido."));
			correcto=false;
		}
		
		if(this.energia == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de energía es requerido."));
			correcto=false;
		}
			
		
		if(correcto){
			Region region = new Region();
			
			region.setRegion(this.numeroRegion);
			region.setNombreRegion(this.nombreRegion);
			region.setKmsRegion(this.kmsRegion);
			region.setAbonadoRegion(this.abonadoRegion);
			region.setDemanda(this.demanda);
			region.setEnergia(this.energia);
		
			if(this.regionBO.existe(region)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de la región ya existe."));
	            return "failure";
			} else {
				
				
				this.regionBO.agregar(region);
		
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Inserción exitosa."));
				
				return "success";
			}
		}else{
			return "failure";
		}
		
	}
	
	/**
	 * Comment for modificarRegion
	 * @return "success" o "failure" al modificar Region
	 */
	public String modificarRegion() {
		
		boolean correcto = true;
		
		if(this.numeroRegion == null || this.numeroRegion.intValue() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de región es requerido."));
			correcto=false;
		}
		
		if(this.nombreRegion == null || this.nombreRegion.trim().length() <= 0){
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre de región es inválido."));
			correcto=false;
		}
		
		if(this.kmsRegion == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de kilomentros es requerido."));
			correcto=false;
		}
		
		if(this.abonadoRegion == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de abonado es requerido."));
			correcto=false;
		}
		
		if(this.demanda == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de demanda es requerido."));
			correcto=false;
		}
		
		if(this.energia == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de energía es requerido."));
			correcto=false;
		}
		
		
		if(correcto) {
			Region region = new Region();
			
			region.setRegion(this.numeroRegion);
			region.setNombreRegion(this.nombreRegion);
			region.setKmsRegion(this.kmsRegion);
			region.setAbonadoRegion(this.abonadoRegion);
			region.setDemanda(this.demanda);
			region.setEnergia(this.energia);
			
			this.regionBO.modificar(region);
			
		
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Actualización exitosa."));
			
			return "success";
		}else{
			return "failure";
		}
		
	}
	
	/**
	 * Comment for eliminarRegion
	 * @return "success" o "failure" al eliminar Region
	 */
	public String eliminarRegion() {
		Region region = new Region();
		
		region.setRegion(this.numeroRegion);
		region.setNombreRegion(this.nombreRegion);
		region.setKmsRegion(this.kmsRegion);
		region.setAbonadoRegion(this.abonadoRegion);
		region.setDemanda(this.demanda);
		region.setEnergia(this.energia);
		if(this.numeroRegion != null && this.numeroRegion.intValue() > 0){
			List subregiones = this.subRegionBO.getSubRegiones(this.numeroRegion);
			if(subregiones == null || subregiones.size() == 0){
				this.regionBO.eliminar(region);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Eliminación exitosa."));
				
				this.numeroRegion = null;
				this.nombreRegion = null;
				this.kmsRegion = new Double(0);
				this.abonadoRegion = new Long(0);
				this.demanda = null;
				this.energia = null;
			}
			else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede eliminar!", "La región tiene subregiones asociadas."));
			}
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de región es requerido."));
		}
		
		return "success";
		
	}
	
	/**
	 * Comment for buscarRegion
	 * @return "success" o "failure" al buscar Region
	 */
	public String buscarRegion() {	
		
        if(this.numeroRegion != null && this.numeroRegion.intValue() > 0){
            this.regionDetalle = this.regionBO.buscar(this.numeroRegion);
            if(this.regionDetalle != null){
            	this.numeroRegion = this.regionDetalle.getRegion();
            	this.nombreRegion = this.regionDetalle.getNombreRegion();
            	this.kmsRegion = this.regionDetalle.getKmsRegion();
            	this.abonadoRegion = this.regionDetalle.getAbonadoRegion();
            	this.demanda = this.regionDetalle.getDemanda();
            	this.energia = this.regionDetalle.getEnergia();
            } else{
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Región no existe."));
            }
        } else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código de región es requerido."));
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
	 * Comment for getRegiones
	 * @return Lista de Regiones
	 */
	public List getRegiones(){
		List regiones = this.regionBO.getRegiones();
		List reg = new ArrayList();
		Region region;
		for(int i=0;i<regiones.size();i++){
			region = (Region) regiones.get(i);
			SelectItem item = new SelectItem();
			item.setValue(region);
			item.setLabel(region.getRegion().toString()+" - "+region.getNombreRegion());
			reg.add(item);
		}
		return reg;
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
