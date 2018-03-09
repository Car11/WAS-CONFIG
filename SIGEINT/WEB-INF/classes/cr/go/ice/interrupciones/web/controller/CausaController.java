package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.CausaBO;
import cr.go.ice.interrupciones.BO.DetalleCausaBO;
import cr.go.ice.interrupciones.BO.TiposCausaBO;
import cr.go.ice.interrupciones.BO.TiposCausaEspecificaBO;
import cr.go.ice.interrupciones.domain.Causa;
import cr.go.ice.interrupciones.domain.DetalleCausa;
import cr.go.ice.interrupciones.domain.TiposCausa;
import cr.go.ice.interrupciones.domain.TiposCausaEspecifica;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.AnimalController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>CausaController.java</code> Establese la relacion entre capa de presentacion y la logica del negocio.</p>
 * <p>Fecha creación: 25/01/2007</p>
 * <p>Ultima actualización: 25/01/2007</p>
 * @author Vista Verde Soft (Administrador y Doc Cristian)
 * @version 1.1
 */
public class CausaController extends AbstractFacesController {
    /**
     * <code>codigoCausa</code> Codigo de Causa
     */
    private Integer codigoCausa;
    /**
     * <code>nombreCausa</code> Nombre de la Causa
     */
    private String nombreCausa;
    
    private Integer codigoDetalleCausa;
	
	private Integer codigoTipoCausa;
	
	private Integer codigoTipoCausaEspecifica;
	
	private Integer estado;
	
	private Integer estadoActivo = new Integer(0);
	
	private Integer estadoInactivo = new Integer(1);

    /**
     * <code>causa</code> Causa
     */
    private Causa causa;
    /**
     * <code>causaBO</code> Causa BO
     */
    private CausaBO causaBO;
    
    private TiposCausaBO tiposCausaBO;
	
	private TiposCausaEspecificaBO tiposCausaEspecificaBO;
	
	private DetalleCausaBO detalleCausaBO;

    //private ResourceBundle rsc = ResourceBundle.getBundle("cr.go.ice.interupciones.web.resources");

    /**
     * Constructor
     */
    public CausaController() {
    	this.iniciarValores();
    }
    
    public void iniciarValores(){
    	this.codigoCausa = new Integer(0);
        this.nombreCausa = "";
        this.causa = new Causa();
        this.codigoTipoCausa = new Integer(0);
        this.codigoTipoCausaEspecifica = new Integer(0);
        this.codigoDetalleCausa  = new Integer(0);
        this.estado = new Integer(0);
    }
    
    
    public String getInit(){
    	 FacesContext context = FacesContext.getCurrentInstance();
    	boolean userClor = true;
        Object limpiar = context.getExternalContext().getRequestParameterMap().get("init");
        
        if(userClor && limpiar != null){
            this.iniciarValores();
        }  
        return "success";
    }
    /**
     * @param context
     */
    public void load(FacesContext context){
      boolean userClor = true;
        Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
        
        if(userClor && limpiar != null){
            this.iniciarValores();
        }   
    }

    
    /**
     * Comment for getCodigoCausa
     * @return Codigo de Causa
     */
    public Integer getCodigoCausa() {
        return this.codigoCausa;
    }

   
    /**
     * Comment for setCodigoCausa
     * @param codigoCausa
     */
    public void setCodigoCausa(Integer codigoCausa) {
        this.codigoCausa = codigoCausa;
    }

    
    /**
     * Comment for getNombreCausa
     * @return Nombre de la Causa
     */
    public String getNombreCausa() {
        return this.nombreCausa;
    }

    
    /**
     * Comment for setNombreCausa
     * @param nombreCausa
     */
    public void setNombreCausa(String nombreCausa) {
        this.nombreCausa = nombreCausa.toUpperCase();
    }

    
    /**
     * Comment for getCausaBO
     * @return Causa BO
     */
    public CausaBO getCausaBO() {
        return this.causaBO;
    }

    
    /**
     * Comment for setCausaBO
     * @param causaBO
     */
    public void setCausaBO(CausaBO causaBO) {
        this.causaBO = causaBO;
    }

    /**
     * Comment for agregar
     * @return "Success" o "Error" al agregar Causas
     */
    public String agregar() {
       
        
        if(this.codigoCausa == null || this.codigoCausa.intValue() <= 0){
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }
        if(this.codigoTipoCausa != null && this.codigoTipoCausa.intValue() > 0)
        {
            if(this.codigoTipoCausaEspecifica == null || this.codigoTipoCausaEspecifica.intValue() <= 0){
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La causa específica es requerida"));
                return "error";
            }
            if(this.codigoDetalleCausa == null || this.codigoDetalleCausa.intValue() <= 0){
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El detalle de causa es requerido."));
                return "error";
            }
        }
        if (this.nombreCausa != null && this.nombreCausa.length() > 0) {
            this.causa = new Causa();
            this.causa.setCodigoCausa(this.codigoCausa);
            this.causa.setNombreCausa(this.nombreCausa.toUpperCase());
            this.causa.setTiposCausa(this.codigoTipoCausa);
            this.causa.setCausaEspecifica(this.codigoTipoCausaEspecifica);
            this.causa.setDetalleCausa(this.codigoDetalleCausa);
            this.causa.setEstado(this.estado);
            if (!this.causaBO.causaExiste(this.causa)) {
                this.causaBO.agregar(this.causa);
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Causa agrega con éxito."));
                return "success";
            } else {
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La causa especificada ya existe."));
                return "error";
            }
        } else {
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El valor es requerido."));
            return "error";
        }
    }

    /**
     * Comment for modificar
     * @return "Success" o "Error" al modificar Causas
     */
    public String modificar() {
     
        if(this.codigoCausa == null || this.codigoCausa.intValue() <= 0){
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }
        if(this.codigoTipoCausa != null && this.codigoTipoCausa.intValue() > 0)
        {
            if(this.codigoTipoCausaEspecifica == null || this.codigoTipoCausaEspecifica.intValue() <= 0){
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La causa específica es requerida."));
                return "error";
            }
            if(this.codigoDetalleCausa == null || this.codigoDetalleCausa.intValue() <= 0){
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El detalle de causa es requerido."));
                return "error";
            }
        }
        this.causa = this.causaBO.buscar(this.codigoCausa);
        if (this.causa == null) {
        
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La causa no existe."));
            return "error";
        }
        /*if (this.nombreCausa.equals(this.causa.getNombreCausa())) {
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            msg.setSummary("El nombre de la causa no ha sido cambiado.");
            msg.setDetail("El nombre de la causa no ha sido cambiado.");
            FacesContext.getCurrentInstance().addMessage("form1:nombreCausa", msg);
            return "error";
        }*/
        Causa cau2 = this.causaBO.buscar(this.nombreCausa);
        if (cau2 == null || cau2.getCodigoCausa().equals(this.causa.getCodigoCausa())) {
            this.causa.setNombreCausa(this.nombreCausa);
            this.causaBO.modificar(this.causa);
            this.causa.setTiposCausa(this.codigoTipoCausa);
            this.causa.setCausaEspecifica(this.codigoTipoCausaEspecifica);
            this.causa.setDetalleCausa(this.codigoDetalleCausa);
            this.causa.setEstado(this.estado);
          
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Causa modificada con éxito."));
            return "success";
        } else {
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre de la causa ya está registrado para otra causa."));
            return "error";
        }

    }

    /**
     * Comment for eliminar
     * @return "Success" o "Error" al eliminar Causas
     */
    public String eliminar() {
      
        
        if(this.codigoCausa == null || this.codigoCausa.intValue() <= 0){
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }
        this.causa = this.causaBO.buscar(this.codigoCausa);
        if (this.causa != null && this.causaBO.causaExiste(this.causa)) {
    		if(this.causaBO.registrosAsociados(this.codigoCausa).longValue() > 0){
    		    
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede eliminar!", "Causa se encuentra asociada con diversa información."));
    			return "error";
    		}         
    		else{
	            this.causaBO.eliminar(this.causa);
	            this.causa = null;
	            this.codigoCausa = new Integer(0);
	            this.nombreCausa = "";
	            this.codigoTipoCausa = new Integer(0);
	            this.codigoTipoCausaEspecifica= new Integer(0);
	            this.codigoDetalleCausa= new Integer(0);
	            this.estado =new Integer(0);
	            
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Causa eliminada con éxito."));
	            return "success";
    		}
        } else {
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La causa no existe."));
            return "error";
        }
    }

   
    /**
     * Comment for buscar
     * @return "Success" o "Error" al buscar Causas
     */
    public String buscar() {
             
        if(this.codigoCausa == null || this.codigoCausa.intValue() <= 0){
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código es requerido."));
            return "error";
        }
        this.causa = this.causaBO.buscar(this.codigoCausa);
        if (causa != null) {
            this.codigoCausa = this.causa.getCodigoCausa();
            this.nombreCausa = this.causa.getNombreCausa();
            this.codigoTipoCausa = this.causa.getTiposCausa();
            this.codigoTipoCausaEspecifica = this.causa.getCausaEspecifica();
            this.codigoDetalleCausa = this.causa.getDetalleCausa();
            this.estado = this.causa.getEstado();
            return "success";
        } else {
            this.nombreCausa = "";            
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La causa especificada no existe."));
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
     * Comment for getCausasSelectItems
     * @return Lista Causas Select Items
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List getCausasSelectItems(){
    	List selectItems = new ArrayList();
    	List causas = this.causaBO.getCausas();    	
    	if(causas != null){
	    	for(int i=0; i < causas.size();i++){
	    		Causa causa = (Causa) causas.get(i);
	    		selectItems.add(new SelectItem(causa.getCodigoCausa(), causa.getCodigoCausa() + " - " + causa.getNombreCausa()));
	    	}
    	}
    	return selectItems;	
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List getTiposCausasSelectItems()
	{	
    	Vector vcausas = new Vector();
    	vcausas.add(new SelectItem(0, "Seleccione una opcion"));
		List lcausas = this.tiposCausaBO.buscarTodos();
		for (int i = 0; i < lcausas.size(); i++) {
			TiposCausa xcausa = (TiposCausa) lcausas.get(i);
			vcausas.add(new SelectItem(xcausa.getCodigo(), xcausa.getCodigo()+" - "+xcausa.getDescripcion()));
		}
		return vcausas;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List getTiposCausasEspecificaSelectItems()
	{
    	Vector vEspecifica = new Vector();
    	vEspecifica.add(new SelectItem(0, "Seleccione una opcion"));
    	if(this.codigoTipoCausa!=null && this.codigoTipoCausa!=0)
    	{
			List lEspecifica = this.tiposCausaEspecificaBO.buscarTodosTipoCausa(this.codigoTipoCausa);
			for (int i = 0; i < lEspecifica.size(); i++) {
				TiposCausaEspecifica causa = (TiposCausaEspecifica) lEspecifica.get(i);
				vEspecifica.add(new SelectItem(causa.getTiposCausaEspecificaID().getCausaEspecifica(),causa.getTiposCausaEspecificaID().getCausaEspecifica()+" - "+causa.getDescripcion()));
			}
    	}
		return vEspecifica;
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getDetalleCausaSelectItems()
	{
		System.out.println("Valores: " + this.codigoTipoCausa + " - " + this.codigoTipoCausaEspecifica);
    	List selectItems = new ArrayList();
    	selectItems.add(new SelectItem(0, "Seleccione una opcion"));
    	if( (this.codigoTipoCausa!=null && this.codigoTipoCausa!=0) && (this.codigoTipoCausaEspecifica!=null && this.codigoTipoCausaEspecifica!=0) )
    	{
	    	List<DetalleCausa> causas = this.detalleCausaBO.buscarTodosEspecifica(this.codigoTipoCausa,  this.codigoTipoCausaEspecifica);
	    	for(int i=0; i<causas.size();i++)
	        {
	    		DetalleCausa causa = causas.get(i);
	        	selectItems.add(new SelectItem(causa.getDetalleCausaID().getDetalleCausa(), causa.getDetalleCausaID().getDetalleCausa() +" - "+ causa.getDescripcion()));
	    	}
    	}
    	return selectItems;
    }
	
	public String listenerCausa()
	{
		System.out.println("Entra al lisener");
		if (this.causa.getCodigoCausa() != null && this.causa.getCodigoCausa() != 0) {
			System.out.println("seea");
			this.codigoCausa = this.causa.getCodigoCausa();
		}
		return "success";
	}
	
	public String listenerEspecifica(){
		this.codigoDetalleCausa = Integer.valueOf(0);
		return "listener";
	}
	
	public String listenerDetalle(){
		return "listener";			
	}

	public Integer getCodigoDetalleCausa() {
		return codigoDetalleCausa;
	}
	public void setCodigoDetalleCausa(Integer codigoDetalleCausa) {
		this.codigoDetalleCausa = codigoDetalleCausa;
	}
	

	public Causa getCausa() {
		return causa;
	}

	public void setCausa(Causa causa) {
		this.causa = causa;
	}

	
	public Integer getCodigoTipoCausa() {
		return codigoTipoCausa;
	}


	public void setCodigoTipoCausa(Integer codigoTipoCausa) {
		this.codigoTipoCausa = codigoTipoCausa;
	}


	public Integer getCodigoTipoCausaEspecifica() {
		return codigoTipoCausaEspecifica;
	}


	public void setCodigoTipoCausaEspecifica(Integer codigoTipoCausaEspecifica) {
		this.codigoTipoCausaEspecifica = codigoTipoCausaEspecifica;
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


	public TiposCausaBO getTiposCausaBO() {
		return tiposCausaBO;
	}


	public void setTiposCausaBO(TiposCausaBO tiposCausaBO) {
		this.tiposCausaBO = tiposCausaBO;
	}


	public TiposCausaEspecificaBO getTiposCausaEspecificaBO() {
		return tiposCausaEspecificaBO;
	}


	public void setTiposCausaEspecificaBO(TiposCausaEspecificaBO tiposCausaEspecificaBO) {
		this.tiposCausaEspecificaBO = tiposCausaEspecificaBO;
	}


	public DetalleCausaBO getDetalleCausaBO() {
		return detalleCausaBO;
	}


	public void setDetalleCausaBO(DetalleCausaBO detalleCausaBO) {
		this.detalleCausaBO = detalleCausaBO;
	}
	public Integer getCero(){
		return new Integer(0);
	}
	public Integer getCero2(){
		return new Integer(0);
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