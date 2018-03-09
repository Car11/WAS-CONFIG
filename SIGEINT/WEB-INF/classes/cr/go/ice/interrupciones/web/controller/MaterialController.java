package cr.go.ice.interrupciones.web.controller;


import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.MaterialBO;
import cr.go.ice.interrupciones.BO.UbicacionEspecificaDanoBO;
import cr.go.ice.interrupciones.BO.UbicacionGeneralDanoBO;
import cr.go.ice.interrupciones.domain.Material;
import cr.go.ice.interrupciones.domain.UbicacionEspecificaDano;
import cr.go.ice.interrupciones.domain.UbicacionGeneralDano;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.AnimalController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>MaterialController.java</code> Establese la relacion entre capa de presentacion y la logica del negocio.</p>
 * <p>Fecha creación: 25/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (Administrador y Doc Cristian)
 * @version 1.1
 */
public class MaterialController extends AbstractFacesController{
    /**
     * <code>codigoMaterial</code> Codigo Material
     */
    private Integer codigoMaterial;
    /**
     * <code>nombreMaterial</code> Nombre de Material
     */
    private String nombreMaterial;

    /**
     * <code>material</code> Material
     */
    private Material material;
    /**
     * <code>materialBO</code> Material BO 
     */
    private MaterialBO materialBO;
    
    private UbicacionGeneralDanoBO ubicacionGeneralDanoBO;
	
	private UbicacionEspecificaDanoBO ubicacionEspecificaDanoBO;
	
	private Integer codigo;
	
	private Integer codigoUbicacionGeneral;
	
	private Integer estado;
	
	private Integer estadoActivo = new Integer(0);
	
	private Integer estadoInactivo = new Integer(1);

    /**
     * Constructor
     */
    public MaterialController() {
        this.codigoMaterial = new Integer(0);
        this.nombreMaterial = "";
        this.material = null;
        this.codigo = new Integer(0);
        this.estado = new Integer(0);
        this.codigoUbicacionGeneral = new Integer(0);
    }
    
    
    public String getInit(){
    	return "success";
    }

    /**
     * Comment for getCodigoMaterial
     * @return Retorna el codigoMaterial.
     */
    public Integer getCodigoMaterial() {
        return this.codigoMaterial;
    }

    /**
     * Comment for setCodigoMaterial
     * @param codigoMaterial El codigoMaterial a modificar.
     */
    public void setCodigoMaterial(Integer codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
    }

    /**
     * Comment for getNombreMaterial
     * @return Retorna el nombreMaterial.
     */
    public String getNombreMaterial() {
        return this.nombreMaterial;
    }

    /**
     * Comment for setNombreMaterial
     * @param nombreMaterial El nombreMaterial a modificar.
     */
    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial.toUpperCase();
    }

    /**
     * Comment for getMaterialBO
     * @return Retorna el materialBO.
     */
    public MaterialBO getMaterialBO() {
        return this.materialBO;
    }

    /**
     * Comment for setMaterialBO
     * @param materialBO El materialBO a modificar.
     */
    public void setMaterialBO(MaterialBO materialBO) {
        this.materialBO = materialBO;
    }
    
    public String listenerUbicacionGeneral(){
		
		this.codigo = new Integer(0);
		return "listener";	
	}

    /**
     * Comment for agregar
     * @return "success" o "error" al agregar Material
     */
    public String agregar() {
        
        if(this.codigoMaterial == null || this.codigoMaterial.intValue() <= 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "success";
        }
        if(this.codigoUbicacionGeneral != null && this.codigoUbicacionGeneral.intValue() > 0)
        {
        	if(this.codigo == null || this.codigo.intValue() <= 0){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La Ubicación Específica del Daño es requerida."));
                return "success";
            }
        }
        
        
        if (this.nombreMaterial != null && this.nombreMaterial.length() > 0) {
            this.material = new Material();
            this.material.setCodigoMaterial(this.codigoMaterial);
            this.material.setNombreMaterial(this.nombreMaterial.toUpperCase());
            this.material.setDanoGeneral(this.codigoUbicacionGeneral);
            this.material.setDanoEspecifico(this.codigo);
            this.material.setEstado(this.estado);
            if (!this.materialBO.materialExiste(this.material)) {
                this.materialBO.agregar(this.material);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Material agregado con éxito."));
                return "success";
            } else {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El material especificado ya existe."));
                return "success";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El valor es requerido."));
            return "success";
        }
    }

    /**
     * Comment for modificar
     * @return "success" o "error" al modificar Material
     */
    public String modificar() {
    	System.out.println("Valor: " + this.codigo);
        
        if(this.codigoMaterial == null || this.codigoMaterial.intValue() <= 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ".El código es requerido"));
            return "success";
        }
        if(this.codigoUbicacionGeneral != null && this.codigoUbicacionGeneral.intValue() > 0)
        {
        	if(this.codigo == null || this.codigo.intValue() <= 0){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La Ubicación Específica del Daño es requerida."));
                return "success";
            }
        }
        
        this.material = this.materialBO.buscar(this.codigoMaterial);
        if (this.material == null) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El material no existe."));
            return "success";
        }
        Material mat2 = this.materialBO.buscar(this.nombreMaterial);
        if (mat2 == null || mat2.getCodigoMaterial().equals(this.material.getCodigoMaterial())) {
            this.material.setNombreMaterial(this.nombreMaterial);
            this.material.setDanoGeneral(this.getCodigoUbicacionGeneral());
            this.material.setDanoEspecifico(this.codigo);
            this.material.setEstado(this.estado);
            this.materialBO.modificar(this.material);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Material modificado con éxito."));
            return "success";
        } else {
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre del material ya esta registrado para otro material."));
            
            return "success";
        }

    }

    /**
     * Comment for eliminar
     * @return "success" o "error" al eliminar Material
     */
    public String eliminar() {
       
        
        if(this.codigoMaterial == null || this.codigoMaterial.intValue() <= 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            
            return "success";
        }
        this.material = this.materialBO.buscar(this.codigoMaterial);
        if (this.material != null && this.materialBO.materialExiste(this.material)) {
    		if(this.materialBO.registrosAsociados(this.codigoMaterial).longValue() > 0){
    		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede eliminar!", "Material se encuentra asociada con divesa información."));
    			return "success";
    		}   
    		else{
	            this.materialBO.eliminar(this.material);
	            this.material = null;
	            this.codigoMaterial = new Integer(0);
	            this.nombreMaterial = "";
	           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Material eliminado con éxito."));
	            return "success";
    		}
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El material no existe."));
            return "success";
        }
    }

    
    /**
     * Comment for buscar
     * @return "success" o "error" al buscar Material
     */
    public String buscar() {
        System.out.println("Entra a buscsar");
        if(this.codigoMaterial == null || this.codigoMaterial.intValue() <= 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "success";
        }
        this.material = this.materialBO.buscar(this.codigoMaterial);
        if (material != null) {
            this.codigoMaterial = this.material.getCodigoMaterial();
            this.nombreMaterial = this.material.getNombreMaterial();
            this.codigoUbicacionGeneral = this.material.getDanoGeneral();
            this.codigo = this.material.getDanoEspecifico();
            this.estado = this.material.getEstado();
            return "success";
        } else
        {
            this.nombreMaterial = "";            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El material especificado no existe."));
            return "success";
        }
    }
    
    /**
     * Cancela
     * @return success
     */
    public String cancelar(){
    	this.resetController();
        return "success";
    }
    
    /**
     * Comment for getMaterialesSelectItems
     * @return Lista de materiales
     */
    public List getMaterialesSelectItems(){
    	List selectItems = new ArrayList();
    	List materiales = this.materialBO.getMateriales();
    	for(int i=0; i<materiales.size();i++){
    		Material material = (Material) materiales.get(i);
    		selectItems.add(new SelectItem(material.getCodigoMaterial(), material.getCodigoMaterial()+" - "+material.getNombreMaterial()));
    	}
    	return selectItems;	
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List getUbicacionGeneralSelectItems()
	{
    	List selectItems = new ArrayList();
    	List<UbicacionGeneralDano> ubicaciones = this.ubicacionGeneralDanoBO.buscarTodos();
    	
    	selectItems.add(new SelectItem(new Integer(0), "Seleccione una Ubicación General del Daño"));
    	
    	for(int i=0; i<ubicaciones.size();i++)
    	{
    		UbicacionGeneralDano ubicacion = ubicaciones.get(i);
    		selectItems.add(new SelectItem(ubicacion.getCodigo(), ubicacion.getCodigo() +" - "+ ubicacion.getDescripcion()));
    	}
    	return selectItems;
    }
    public List getUbicacionEspecificaSelectItems()
	{
    	List selectItems = new ArrayList();
    	List<UbicacionEspecificaDano> ubicaciones = this.ubicacionEspecificaDanoBO.buscarTodosGeneral(this.codigoUbicacionGeneral);
    	
    	selectItems.add(new SelectItem(new Integer(0), "Seleccione una Ubicación Específica del Daño"));
    	
    	for(int i=0; i<ubicaciones.size();i++)
    	{
    		UbicacionEspecificaDano ubicacion = ubicaciones.get(i);
    		selectItems.add(new SelectItem(ubicacion.getUbicacionEspecificaDanoID().getDanoEspecifico(), ubicacion.getUbicacionEspecificaDanoID().getDanoEspecifico() +" - "+ ubicacion.getDescripcion()));
    	}
    	return selectItems;
    }

	public UbicacionGeneralDanoBO getUbicacionGeneralDanoBO() {
		return ubicacionGeneralDanoBO;
	}

	public void setUbicacionGeneralDanoBO(UbicacionGeneralDanoBO ubicacionGeneralDanoBO) {
		this.ubicacionGeneralDanoBO = ubicacionGeneralDanoBO;
	}

	public UbicacionEspecificaDanoBO getUbicacionEspecificaDanoBO() {
		return ubicacionEspecificaDanoBO;
	}

	public void setUbicacionEspecificaDanoBO(UbicacionEspecificaDanoBO ubicacionEspecificaDanoBO) {
		this.ubicacionEspecificaDanoBO = ubicacionEspecificaDanoBO;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigoUbicacionGeneral() {
		return codigoUbicacionGeneral;
	}

	public void setCodigoUbicacionGeneral(Integer codigoUbicacionGeneral) {
		this.codigoUbicacionGeneral = codigoUbicacionGeneral;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getEstadoActivo() {
		return estadoActivo;
	}

	public void setEstadoActivo(Integer estadoActivo) {
		this.estadoActivo = estadoActivo;
	}

	public Integer getEstadoInactivo() {
		return estadoInactivo;
	}

	public void setEstadoInactivo(Integer estadoInactivo) {
		this.estadoInactivo = estadoInactivo;
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