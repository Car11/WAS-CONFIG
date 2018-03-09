package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.CorreoBO;
import cr.go.ice.interrupciones.BO.OficinaBO;
import cr.go.ice.interrupciones.domain.Correo;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.CorreoController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>CorreoController.java</code> Establese la relacion entre capa de presentacion y la logica del negocio.</p>
 * <p>Fecha creación: 20/02/2007</p>
 * <p>Ultima actualización: 20/02/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class CorreoController extends AbstractFacesController{
	/**
	 * <code>correo</code> Correo
	 */
	private String correo;
	/**
	 * <code>nombre</code> Nombre
	 */
	private String nombre;
	/**
	 * <code>telefono</code> Telefono
	 */
	private Long telefono;
	/**
	 * <code>oficina</code> Oficina
	 */
	private Integer oficina;
	
	/**
	 * <code>correoBO</code> Correo BO
	 */
	private CorreoBO correoBO;
	/**
	 * <code>oficinaBO</code> Oficina BO
	 */
	private OficinaBO oficinaBO;
	
	/**
	 * <code>tableData</code> Tabla de Datos
	 */
	private DataTable tableData;
	
	/**
	 * <code>correoDetalle</code> Detalle de Correo
	 */
	private Correo correoDetalle;
		
	/**
	 *  <code>listaCorreos</code> Lista de correos
	 */
	private List listaCorreos;
	/**
	 * <code>enviaCorreo</code> Indicador de envio de correo
	 */	
	private Boolean enviaCorreo;	
		
	
	/**
	 * Constructor
	 * Inicializa el valor de oficina en 0
	 */
	public CorreoController(){
		
		this.oficina = new Integer(0);
		this.listaCorreos = new ArrayList();
		
	}
	
	public String getInit(){
		return "success";
	}
	
	/**
	 * Metodo accesor de listaCorreos.
	 * @return Retorna la listaCorreos.
	 */
	public List getListaCorreos() {
		return listaCorreos;
	}
	
	
	/**
	 * Metodo modificador de listaCorreos.
	 * @param listaCorreos la listaCorreos a modificar.
	 */
	public void setListaCorreos(List listaCorreos) {
		this.listaCorreos = listaCorreos;
	}
	
	
	/**
	 * Comment for getCorreoDetalle
	 * @return correoDetalle
	 */
	public Correo getCorreoDetalle() {
		return correoDetalle;
	}
	
	/**
	 * Comment for setCorreoDetalle
	 * @param correoDetalle
	 */
	public void setCorreoDetalle(Correo correoDetalle) {
		this.correoDetalle = correoDetalle;
	}
	
	/**
	 * Comment for getTableData
	 * @return tableData
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
	 * Comment for getCorreo
	 * @return correo
	 */
	public String getCorreo() {
		return correo;
	}
	
	/**
	 * Comment for setCorreo
	 * @param correo
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	/**
	 * Comment for getCorreoBO
	 * @return correoBO
	 */
	public CorreoBO getCorreoBO() {
		return correoBO;
	}
	
	/**
	 * Comment for setCorreoBO
	 * @param correoBO
	 */
	public void setCorreoBO(CorreoBO correoBO) {
		this.correoBO = correoBO;
	}
	
	/**
	 * Comment for getNombre
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Comment for setNombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Comment for getOficina
	 * @return oficina
	 */
	public Integer getOficina() {
		return oficina;
	}
	
	/**
	 * Comment for setOficina
	 * @param oficina
	 */
	public void setOficina(Integer oficina) {
		this.oficina = oficina;
	}
	
	/**
	 * Comment for getTelefono
	 * @return telefono
	 */
	public Long getTelefono() {
		return telefono;
	}
	
	/**
	 * Comment for setTelefono
	 * @param telefono
	 */
	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}
	
	/**
	 * Comment for getOficinaBO
	 * @return oficina BO
	 */
	public OficinaBO getOficinaBO() {
		return oficinaBO;
	}
	
	/**
	 * Comment for setOficinaBO
	 * @param oficinaBO
	 */
	public void setOficinaBO(OficinaBO oficinaBO) {
		this.oficinaBO = oficinaBO;
	}	
	
	
	
	
	/**
	 * Comment for getCorreos
	 * @return Lista Correos
	 */
	public List getCorreos(){
		return this.listaCorreos;
	}
	
    /**
     * Metodo accesor de enviaCorreo.
     * @return Retorna el enviaCorreo.
     */
    public Boolean getEnviaCorreo() {
        return enviaCorreo;
    }
    /**
     * Metodo modificador de enviaCorreo.
     * @param enviaCorreo El enviaCorreo a modificar.
     */
    public void setEnviaCorreo(Boolean enviaCorreo) {
        this.enviaCorreo = enviaCorreo;
    }	
	
	
	/**
	 * Comment for listenerOficina
	 * @param v
	 * @return "listener" a Oficina
	 */
	public String listenerOficina(){
		
		this.listaCorreos = this.correoBO.getCorreos(this.oficina);
		return "listener";	
	}
	

	/**
	 * Comment for buscarNombre
	 * @return "success" o "error" al buscar un correo por el nombre de una persona
	 */
	public String buscarNombre() {
		
		boolean correcto = true;
		
		if((this.nombre==null) || (this.nombre.trim().length() <= 0)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar el nombre para realizar la búsqueda."));
            correcto = false;
		}
		
		if(correcto){
			
			this.listaCorreos = this.correoBO.getCorreosNombre(this.nombre);
			this.oficina = new Integer(0);
			this.correo = "";
			return "success";
		
		} else {
			
			return "error";
			
		}

	}
	
	/**
	 * Comment for buscarCorreo
	 * @return "success" o "error" al buscar un correo por el email de una persona
	 */
	public String buscarCorreo() {
		
		boolean correcto = true;
		
		if((this.correo==null) || (this.correo.trim().length() <= 0)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar el correo para realizar la búsqueda."));
            correcto = false;
		}
		
		if(correcto){
			
			this.listaCorreos = this.correoBO.getCorreosCorreo(this.correo);
			this.oficina = new Integer(0);
			this.nombre = "";
			return "success";
		
		} else {
			
			return "error";
			
		}
		
	}
	
	
	/**
	 * Comment for agregar
	 * @return "Success" o "Error" al agregar Correos
	 */
	public String agregar(){
		boolean correcto = true;
		
		if(this.nombre==null || this.nombre.trim().length() <= 0){
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre es inválido."));
            correcto=false;
		}
		
		if(this.oficina==null || this.oficina.intValue() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La oficina es inválida."));
            correcto=false;
		}
		
		if(this.correo==null || this.correo.trim().length() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El correo es inválido."));
            correcto=false;
		}		
		if(correcto){
			Correo correo = new Correo();
			correo.setCodigoOficina(this.oficina);
			correo.setNombre(this.nombre.toUpperCase());
			correo.setCorreo(this.correo.toLowerCase());
			correo.setTelefono(this.telefono);
			if(this.enviaCorreo.booleanValue())
			    correo.setIndicadorEnviado(new Integer(1));
			else
			    correo.setIndicadorEnviado(new Integer(0));
			
			if (this.correoBO.existe(correo)) {

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El correo ya existe."));
				return "error";

			} else {
			
				this.correoBO.agregar(correo);
			
				this.setOficina(null);
				this.setNombre(null);
				this.setCorreo(null);
				this.setTelefono(null);
				this.setEnviaCorreo(null);
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Inserción exitosa."));
			
				return "success";
			}
		}else{
			return "error";
		}		
	}
	
	/**
	 * Comment for modificarCorreoLista
	 * @return Modificar Lista de correos "Success" 
	 */
	public String modificarCorreoLista(){
		this.correoDetalle = (Correo) this.tableData.getRowData();
		return "success";
	}
	
	/**
	 * Comment for eliminarCorreoLista
	 * @return Eliminar "Success" 
	 */
	public String eliminarCorreoLista(){
		this.correoDetalle = (Correo) this.tableData.getRowData();
		this.correoBO.eliminar(this.correoDetalle);
		this.correoDetalle = null;
	    if(this.nombre == null){
	        this.nombre = "";
	    }
	    this.listaCorreos = this.correoBO.getCorreosNombre(this.nombre);	    
	    if(this.correo != null && this.correo.trim().length() > 0)
	        this.listaCorreos = this.correoBO.getCorreosCorreo(this.correo);	    	
		
		return "success";
	}
	
	/**
	 * Comment for modificar
	 * @return "Success" o "Error" al Modificar Correo
	 */
	public String modificar(){
		
		if(this.oficina==null || this.oficina.intValue() <= 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La oficina es inválida."));
            return "error";
		} else {
		
			if(this.correoDetalle.getNombre()!=null && this.correoDetalle.getNombre().trim().length() > 0){
				this.correoBO.modificar(this.correoDetalle);
				this.correoDetalle = null;
	            return "success";
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre es inválido."));
				return "error";
			}
		
		}
		
	}
    
    /**
     * Cancela
     * @return success
     */
    public String cancelar(){
        return "success";
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
