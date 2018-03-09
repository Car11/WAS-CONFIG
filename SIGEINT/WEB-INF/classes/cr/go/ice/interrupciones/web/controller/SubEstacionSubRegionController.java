package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.*;
import cr.go.ice.interrupciones.domain.*;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.SubEstacionSubRegionController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SubEstacionSubRegionController.java</code> Establece la relación entre capa de presentación y la lógica del negocio.</p>
 * <p>Fecha creación: 17/07/2009</p>
 * <p>Ultima actualización: 17/07/2009</p>
 * @author Vista Verde Soft (David)
 * @version 1.1
 */
public class SubEstacionSubRegionController extends AbstractFacesController {    
      
    private Integer oficina;
    private Integer region;
    private Integer subregion;
    private Integer subestacion;
    
    private OficinaBO oficinaBO;
    private RegionBO regionBO;
    private SubRegionBO subRegionBO;
    private SubEstacionBO subEstacionBO;  
    private SubEstacionSubRegionBO subEstacionSubRegionBO;
    
    /**
     * Constructor
     */
    public SubEstacionSubRegionController(){

        this.oficina = new Integer(0);
        this.region = new Integer(0);
        this.subregion = new Integer(0);
        this.subestacion = new Integer(0);
    }

    
    public String getInit(){
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

        items.add(new SelectItem(new Integer(0), "Seleccione una Subestación"));
        for(int i = 0; i < subEstaciones.size(); i++){
            SubEstacion se = (SubEstacion) subEstaciones.get(i);
            SelectItem item = new SelectItem();
            item.setValue(se.getCodigoSubEstacion());
            item.setLabel(se.getCodigoSubEstacion()+"-"+se.getNombreSubEstacion());
            items.add(item);
        }
        return items;
    }
    
    private boolean validarParametros(){
        boolean correcto = true;
       
        if(this.oficina == null || this.oficina.intValue() <= 0){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La oficina es requerida."));            
            correcto=false;
        } 
        if(this.region == null || this.region.intValue() <= 0){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La región es requerida."));            
            correcto=false;
        } 
        if(this.subregion == null || this.subregion.intValue() <= 0){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La subregión es requerida."));           
            correcto=false;
        } 
        if(this.subestacion == null || this.subestacion.intValue() <= 0){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La subestación es requerida."));           
            correcto=false;
        }         
        return correcto;
    }
    
    /**
     * Comment for agregar
     * @return "error" o "success" al agregar SubEstacionSubRegion
     */
    public String agregar() {
        if(validarParametros()){
            if(this.subEstacionSubRegionBO.existe(this.oficina,this.region,this.subregion,this.subestacion)){
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La subestación de la subregión ya existe."));        
                return "error";
            }else{
                this.subEstacionSubRegionBO.agregar(this.oficina,this.region,this.subregion,this.subestacion);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La subestación de la subregión fue agregada éxitosamente."));            
                return "success";
            }
        }else{
            return "error";
        }        
    }

    /**
     * Comment for modificar
     * @return "error" o "success" al modificar SubEstacionSubRegion
     */
    public String modificar() {   
        if(validarParametros()){
            return "success";
        }else{
            return "error";
        }  
    }

    /**
     * Comment for eliminar
     * @return "error" o "success" al eliminar SubEstacionSubRegion
     */
    public String eliminar() {
        if(validarParametros()){
            if(this.subEstacionSubRegionBO.existe(this.oficina,this.region,this.subregion,this.subestacion) == false){
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La subestación de la subregión no existe."));            
                return "error";
            }else{
                this.subEstacionSubRegionBO.eliminar(this.oficina,this.region,this.subregion,this.subestacion);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La subestación de la subregión fue eliminada exitosamente."));            
                return "success";
            }
        }else{
            return "error";
        }      
    }

    
    /**
     * Comment for buscar
     * @return "error" o "success" al buscar SubEstacionSubRegion
     */
    public String buscar() {
        if(validarParametros()){
            if(this.subEstacionSubRegionBO.existe(this.oficina,this.region,this.subregion,this.subestacion) == false){
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La subestación de la subregión no existe."));            
                return "error";
            }else{
                return "success";
            }
        }else{
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

    /**
     * Metodo accesor de subestacion.
     * @return Retorna el subestacion.
     */
    public Integer getSubestacion() {
        return this.subestacion;
    }

    /**
     * Metodo modificador de subestacion.
     * @param subestacion El subestacion a modificar.
     */
    public void setSubestacion(Integer subestacion) {
        this.subestacion = subestacion;
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