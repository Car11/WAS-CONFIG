package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.*;
import cr.go.ice.interrupciones.BO.OficinaBO;
import cr.go.ice.interrupciones.BO.RegionBO;
import cr.go.ice.interrupciones.BO.SubEstacionBO;
import cr.go.ice.interrupciones.domain.*;
import cr.go.ice.interrupciones.domain.Oficina;
import cr.go.ice.interrupciones.domain.SubEstacion;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.AnimalController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SubEstacionController.java</code> Establese la relacion entre capa de presentacion y la logica del negocio.</p>
 * <p>Fecha creación: 06/012/2007</p>
 * <p>Ultima actualización: 06/02/2007</p>
 * @author Vista Verde Soft (Administrador y Doc Cristian)
 * @version 1.1
 */
public class SubEstacionController extends AbstractFacesController {
    /**
     * <code>subEstacionBO</code> SubEstado BO
     */
    private SubEstacionBO subEstacionBO;
    /**
     * <code>subEstacion</code> Sub Estacion
     */
    private SubEstacion subEstacion;
    /**
     * <code>codigo</code> Codigo de la sub estacion
     */
    private Integer codigo;
    
    private Integer codigoAresep;
    /**
     * <code>nombre</code> Nombre de sub estacion
     */
    private String nombre;
    
	private Double kmsSubEstacion;
	private Long abonadoSubEstacion; 
    
    private Integer oficina;
    private Integer region;
    private Integer subregion;
    
    private OficinaBO oficinaBO;
    private RegionBO regionBO;
    private SubRegionBO subRegionBO;
    
    private SubEstacionSubRegionBO subEstacionSubRegionBO;
    
    /**
     * Constructor
     */
    public SubEstacionController(){

	    this.kmsSubEstacion = new Double(0);
	    this.abonadoSubEstacion = new Long(0);   
        
        this.oficina = new Integer(0);
        this.region = new Integer(0);
        this.subregion = new Integer(0);
    }
    
    
    public String getInit(){
    	return "success";
    }
    
    /**
     * Metodo accesor de codigo.
     * @return Retorna el codigo.
     */
    public Integer getCodigo() {
        return this.codigo;
    }
    /**
     * Metodo modificador de codigo.
     * @param codigo El codigo a modificar.
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    /**
     * Metodo accesor de nombre.
     * @return Retorna el nombre.
     */
    public String getNombre() {
        return this.nombre;
    }
    /**
     * Metodo modificador de nombre.
     * @param nombre El nombre a modificar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * Comment for getOficinas
     * @return Lista Oficinas 
     */
    public List getOficinas() {
        Vector items = new Vector();
        List loficinas = oficinaBO.getOficinas();
        items.add(new SelectItem(new Integer(0), "Seleccione una oficina"));
        for (int i = 0; i < loficinas.size(); i++) {
            Oficina xoficina = (Oficina) loficinas.get(i);
            items.add(new SelectItem(xoficina.getCodigoOficina(), xoficina.getCodigoOficina()+" - "+xoficina.getNombreOficina()));
        }
        return items;
    }
    
    /**
     * Metodo accesor de regiones.
     * @return Retorna el regiones.
     */
    public List getRegiones() {       
        List regiones = this.regionBO.getRegiones();
        List items = new ArrayList();
        items.add(new SelectItem(new Integer(0), "Seleccione una región"));
        if(regiones != null && !regiones.isEmpty()){
            for(int i = 0; i < regiones.size(); i++){
                Region region = (Region) regiones.get(i);
                SelectItem item = new SelectItem();
                item.setValue(region.getRegion());
                item.setLabel(region.getRegion() + " - " + region.getNombreRegion());
                items.add(item);
            }
            
        }
        return items;
    }

    /**
     * Metodo accesor de subRegiones.
     * @return Retorna el subRegiones.
     */
    public List getSubregiones() {               
        List subRegiones = this.subRegionBO.getSubRegiones(this.region);
        List items = new ArrayList();
        items.add(new SelectItem(new Integer(0), "Seleccione una subregión"));
        if(subRegiones!= null && !subRegiones.isEmpty()){
            for(int i = 0; i < subRegiones.size(); i++){
                SubRegion subRegion = (SubRegion) subRegiones.get(i);
                SelectItem item = new SelectItem();
                item.setValue(subRegion.getSubRegionID().getSubRegion());
                item.setLabel(subRegion.getSubRegionID().getSubRegion() + " - " + subRegion.getNombreSubRegion());
                items.add(item);
            }
            
        }
        return items;
    }     
    
   
    /**
     * Comment for getListaSubEstaciones
     * @return lista de Sub Estacion
     */
    public List getListaSubEstaciones(){
        List subEstaciones = this.subEstacionBO.getSubEstaciones();
        List items = new ArrayList();
        SubEstacion se = new SubEstacion();
        se.setCodigoSubEstacion(new Integer(0));
        se.setNombreSubEstacion("Seleccione una Subestación");
        items.add(new SelectItem(se, se.getNombreSubEstacion()));
        for(int i = 0; i < subEstaciones.size(); i++){
            se = (SubEstacion) subEstaciones.get(i);
            SelectItem item = new SelectItem();
            item.setValue(se);
            item.setLabel(se.getCodigoSubEstacion()+"-"+se.getNombreSubEstacion());
            items.add(item);
        }
        return items;
    }
    
    
    /**
     * Comment for getSubestacionesSI
     * @return lista de subEstaciones
     */
    public List getSubestacionesSI(){
        List subEstaciones = this.subEstacionBO.getSubEstaciones();
        List items = new ArrayList();
        SubEstacion se = new SubEstacion();
        se.setCodigoSubEstacion(new Integer(0));
        se.setNombreSubEstacion("Seleccione una Subestación");
        items.add(new SelectItem(se, se.getNombreSubEstacion()));
        for(int i = 0; i < subEstaciones.size(); i++){
            se = (SubEstacion) subEstaciones.get(i);
            SelectItem item = new SelectItem();
            item.setValue(se.getCodigoSubEstacion());
            item.setLabel(se.getCodigoSubEstacion()+"-"+se.getNombreSubEstacion());
            items.add(item);
        }
        return items;
    }
    
    /**
     * Comment for agregar
     * @return "error" o "success" al agregar Sub Estacion
     */
    public String agregar() {
              
        if(this.oficina == null || this.oficina.intValue() <= 0){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La oficina es requerida."));
            return "error";
        }
        if(this.region == null || this.region.intValue() <= 0){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La region es requerida."));
            
            return "error";
        } 
        if(this.subregion == null || this.subregion.intValue() <= 0){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La subregion es requerida."));
           return "error";
        }           
        
        if (this.codigo == null || this.codigo.intValue() <= 0) {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }          
        if (this.nombre != null && this.nombre.length() > 0) {
            this.subEstacion = new SubEstacion();
            this.subEstacion.setCodigoSubEstacion(this.codigo);
            this.subEstacion.setNombreSubEstacion(this.nombre.trim().toUpperCase());
            
            if(this.subEstacionSubRegionBO.existe(this.oficina,this.region,this.subregion,this.codigo) == true){
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La subestación específicada ya existe."));
                
                return "error";
            }
            
            if (!this.subEstacionBO.subEstacionExiste(this.subEstacion)) {
                this.subEstacion.setKmsSubEstacion(new Double(0));
                this.subEstacion.setAbonadoSubEstacion(new Long(0));
                this.subEstacion.setSubEstacionAresep(this.codigoAresep);
                this.subEstacionBO.agregar(this.subEstacion);                                 
                this.subEstacionSubRegionBO.agregar(this.oficina,this.region,this.subregion,this.codigo);                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Subestación agregada con éxito."));
                
                return "success";
            } else {
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La Subestación especificada ya existe."));
                return "error";
            }
        } else {
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El valor es requerido."));
            return "error";
        }
    }

    /**
     * Comment for modificar
     * @return "error" o "success" al modificar Sub Estacion
     */
    public String modificar() {
       
        
        if(this.oficina == null || this.oficina.intValue() <= 0){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La oficina es requerida."));
            return "error";
        }
        if(this.region == null || this.region.intValue() <= 0){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La region es requerida."));
            
            return "error";
        } 
        if(this.subregion == null || this.subregion.intValue() <= 0){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La subregion es requerida."));
           
            return "error";
        }        
        
        if (this.codigo == null || this.codigo.intValue() <= 0) {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }        
        this.subEstacion = this.subEstacionBO.buscar(this.codigo);
        if (this.subEstacion == null) {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La SubEstación no existe."));
            return "error";
        }
        /*if (this.nombre.equals(this.subEstacion.getNombreSubEstacion())) {
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            //msg.setSummary("El nombre de la subEstacion no ha sido cambiado.");
            msg.setDetail("El nombre de la Subestación no ha sido cambiado");
            FacesContext.getCurrentInstance().addMessage("form1:nombreSubEstacion", msg);
            return "error";
        }*/
        
        if(this.subEstacionSubRegionBO.existe(this.oficina,this.region,this.subregion,this.codigo) == false){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La subestación específicada no existe."));
            
            return "error";
        }
            
        
        SubEstacion sub2 = this.subEstacionBO.buscar(this.nombre);
        if (sub2 == null || sub2.getCodigoSubEstacion().equals(this.subEstacion.getCodigoSubEstacion())) {
            this.subEstacion.setNombreSubEstacion(this.nombre);
            this.subEstacion.setSubEstacionAresep(this.codigoAresep);
            this.subEstacionBO.modificar(this.subEstacion);            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Subestación modificada con éxito."));
            
            return "success";
        } else {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre de la Subestación ya está registrado para otra Subestación."));
           return "error";
        }

    }

    /**
     * Comment for eliminar
     * @return "error" o "success" al eliminar Sub Estacion
     */
    public String eliminar() {
      
        
        if(this.oficina == null || this.oficina.intValue() <= 0){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La oficina es requerida."));
            return "error";
        }
        if(this.region == null || this.region.intValue() <= 0){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La region es requerida."));
            return "error";
        } 
        if(this.subregion == null || this.subregion.intValue() <= 0){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La subregion es requerida."));
            return "error";
        }        
        
        if (this.codigo == null || this.codigo.intValue() <= 0) {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }       
        
        if(this.subEstacionSubRegionBO.existe(this.oficina,this.region,this.subregion,this.codigo) == false){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La subestación específicada no existe."));
            
            return "error";
        }        
        
        this.subEstacion = this.subEstacionBO.buscar(this.codigo);
        if (this.subEstacion != null && this.subEstacionBO.subEstacionExiste(this.subEstacion)) {
        	try{          
        		this.subEstacionBO.eliminar(this.subEstacion);
                this.subEstacionSubRegionBO.eliminar(this.oficina,this.region,this.subregion,this.codigo);
        	}catch(Exception e){
        		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puede eliminar.  La subestación tiene circuitos asociados."));
                
                return "error";
        	}
            this.subEstacion = null;
            this.codigo = new Integer(0);
            this.nombre = "";
            this.codigoAresep = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Subestación eliminada con exito."));
            return "success";
        } else {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La Subestación no existe."));
            return "error";
        }
    }

    
    /**
     * Comment for buscar
     * @return "error" o "success" al buscar Sub Estacion
     */
    public String buscar() { 
    	if (this.oficina == null || this.oficina.intValue() <= 0) {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La oficina es requerida."));
            return "error";
        }
        if (this.region == null || this.region.intValue() <= 0) {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La region es requerida."));
            return "error";
        }
        if (this.subregion == null || this.subregion.intValue() <= 0) {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La subregion es requerida."));
            return "error";
        }
        if (this.codigo == null || this.codigo.intValue() <= 0) {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }
        if (this.subEstacionSubRegionBO.existe(this.oficina,this.region,this.subregion,this.codigo) == false) {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La subestación específicada no existe."));
            return "error";
        }
        this.subEstacion = this.subEstacionBO.buscar(this.codigo);
        if (subEstacion != null) {
            this.codigo = this.subEstacion.getCodigoSubEstacion();
            this.nombre = this.subEstacion.getNombreSubEstacion();
            this.kmsSubEstacion =  this.subEstacion.getKmsSubEstacion();
            this.abonadoSubEstacion = this.subEstacion.getAbonadoSubEstacion();
            this.codigoAresep = this.subEstacion.getSubEstacionAresep();
            return "success";
        } else {
            this.nombre = "";
            this.codigoAresep = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La Subestación especificada no existe."));
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
     * Metodo accesor de abonadoSubEstacion.
     * @return Retorna el abonadoSubEstacion.
     */
    public Long getAbonadoSubEstacion() {
        return this.abonadoSubEstacion;
    }
    /**
     * Metodo accesor de kmsSubEstacion.
     * @return Retorna el kmsSubEstacion.
     */
    public Double getKmsSubEstacion() {
        return this.kmsSubEstacion;
    }
    /**
     * Metodo accesor de oficina.
     * @return Retorna el oficina.
     */
    public Integer getOficina() {
        return this.oficina;
    }
    /**
     * Metodo modificador de oficina.
     * @param oficina El oficina a modificar.
     */
    public void setOficina(Integer oficina) {
        this.oficina = oficina;
    }
    /**
     * Metodo accesor de region.
     * @return Retorna el region.
     */
    public Integer getRegion() {
        return this.region;
    }
    /**
     * Metodo modificador de region.
     * @param region El region a modificar.
     */
    public void setRegion(Integer region) {
        this.region = region;
    }
    /**
     * Metodo accesor de subregion.
     * @return Retorna el subregion.
     */
    public Integer getSubregion() {
        return this.subregion;
    }
    /**
     * Metodo modificador de subregion.
     * @param subregion El subregion a modificar.
     */
    public void setSubregion(Integer subregion) {
        this.subregion = subregion;
    }
    /**
     * Metodo modificador de oficinaBO.
     * @param oficinaBO El oficinaBO a modificar.
     */
    public void setOficinaBO(OficinaBO oficinaBO) {
        this.oficinaBO = oficinaBO;
    }
    /**
     * Metodo modificador de regionBO.
     * @param regionBO El regionBO a modificar.
     */
    public void setRegionBO(RegionBO regionBO) {
        this.regionBO = regionBO;
    }
    /**
     * Metodo modificador de subRegionBO.
     * @param subRegionBO El subRegionBO a modificar.
     */
    public void setSubRegionBO(SubRegionBO subRegionBO) {
        this.subRegionBO = subRegionBO;
    }
    /**
     * Metodo modificador de subEstacionSubRegionBO.
     * @param subEstacionSubRegionBO El subEstacionSubRegionBO a modificar.
     */
    public void setSubEstacionSubRegionBO(SubEstacionSubRegionBO subEstacionSubRegionBO) {
        this.subEstacionSubRegionBO = subEstacionSubRegionBO;
    }
	public Integer getCodigoAresep() {
		return codigoAresep;
	}
	public void setCodigoAresep(Integer codigoAresep) {
		this.codigoAresep = codigoAresep;
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