package cr.go.ice.interrupciones.web.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.EmpleadoBO;
import cr.go.ice.interrupciones.BO.OficinaBO;
import cr.go.ice.interrupciones.domain.Empleado;
import java.util.List;
import java.util.ArrayList;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.AnimalController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>EmpleadoController.java</code> Establese la relacion entre capa de presentacion y la logica del negocio.</p>
 * <p>Fecha creación: 17/02/2007</p>
 * <p>Ultima actualización: 17/02/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class EmpleadoController extends AbstractFacesController{
	/**
	 * <code>numeroCedula</code> Numero de Cedula
	 */
	private Long numeroCedula;
	/**
	 * <code>numeroCedulaOriginal</code> Numero de Cedula Original
	 */
	private Long numeroCedulaOriginal;
	/**
	 * <code>nombre</code> Nombre
	 */
	private String nombre;
    private String apellido1;
    private String apellido2;
	/**
	 * <code>rol</code> Rol
	 */
	private Integer rol;
	/**
	 * <code>perfil</code> Perfil
	 */
	private Integer perfil;
	
	public Integer getPerfil() {
		return perfil;
	}


	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}

	/**
	 * <code>estado</code> Estado
	 */
	private Integer estado;
	/**
	 * <code>oficina</code> Oficina
	 */
	private Integer oficina;
	
	/**
	 * <code>rolTecnico</code> Rol Tecnico
	 */
	private final Integer rolTecnico = new Integer(0);
	/**
	 * <code>rolOperador</code> Rol Operador
	 */
	private final Integer rolOperador = new Integer(1);
	
	/**
	 * <code>estadoActivo</code> Estado Activo
	 */
	private final Integer estadoActivo = new Integer(0);
	/** 
	 * <code>estadoInactivo</code> Estado Inactivo
	 */
	private final Integer estadoInactivo = new Integer(1);
	
	/**
	 * <code>empleadoBO</code> Empleado BO
	 */
	private EmpleadoBO empleadoBO;
	/**
	 * <code>oficinaBO</code> Oficina BO
	 */
	private OficinaBO oficinaBO;
	
	/**
	 * Constructor
	 */
	public EmpleadoController(){
		this.rol = new Integer(1);
		this.estado = new Integer(0);
	}
	
	public String getInit(){
		return "success";
	}
	/**
	 * Comment for getNumeroCedulaOriginal
	 * @return Returns the numeroCedulaOriginal.
	 */
	public Long getNumeroCedulaOriginal() {
		return numeroCedulaOriginal;
	}
	/**
	 * Comment for setNumeroCedulaOriginal
	 * @param numeroCedulaOriginal The numeroCedulaOriginal to set.
	 */
	public void setNumeroCedulaOriginal(Long numeroCedulaOriginal) {
		this.numeroCedulaOriginal = numeroCedulaOriginal;
	}
	/**
	 * Comment for getOficinaBO
	 * @return Returns the oficinaBO.
	 */
	public OficinaBO getOficinaBO() {
		return oficinaBO;
	}
	/**
	 * Comment for setOficinaBO
	 * @param oficinaBO The oficinaBO to set.
	 */
	public void setOficinaBO(OficinaBO oficinaBO) {
		this.oficinaBO = oficinaBO;
	}
	/**
	 * Comment for getEmpleadoBO
	 * @return Returns the empleadoBO.
	 */
	public EmpleadoBO getEmpleadoBO() {
		return empleadoBO;
	}
	/**
	 * Comment for setEmpleadoBO
	 * @param empleadoBO The empleadoBO to set.
	 */
	public void setEmpleadoBO(EmpleadoBO empleadoBO) {
		this.empleadoBO = empleadoBO;
	}
	/**
	 * Comment for getRolOperador
	 * @return Returns the rolOperador.
	 */
	
	public Integer getRolOperador() {
		return rolOperador;
	}
	/**
	 * Comment for getRolTecnico
	 * @return Returns the rolTecnico.
	 */
	
	public Integer getRolTecnico() {
		return rolTecnico;
	}	
	
	/**
	 * Comment for getEstadoActivo
	 * @return Returns the estadoActivo.
	 */
	public Integer getEstadoActivo() {
		return estadoActivo;
	}
	/**
	 * Comment for getEstadoInactivo
	 * @return Returns the estadoInactivo.
	 */
	public Integer getEstadoInactivo() {
		return estadoInactivo;
	}
	/**
	 * Comment for getEstado
	 * @return Returns the estado.
	 */
	public Integer getEstado() {
		return estado;
	}
	/**
	 * Comment for setEstado
	 * @param estado The estado to set.
	 */
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	/**
	 * Comment for getNombre
	 * @return Returns the nombre.
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Comment for setNombre
	 * @param nombre The nombre to set.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Comment for getNumeroCedula
	 * @return Returns the numeroCedula.
	 */
	public Long getNumeroCedula() {
		return numeroCedula;
	}
	/**
	 * Comment for setNumeroCedula
	 * @param numeroCedula The numeroCedula to set.
	 */
	public void setNumeroCedula(Long numeroCedula) {
		this.numeroCedula = numeroCedula;
	}
	/**
	 * Comment for getOficina
	 * @return Returns the oficina.
	 */
	public Integer getOficina() {
		return oficina;
	}
	/**
	 * Comment for setOficina
	 * @param oficina The oficina to set.
	 */
	public void setOficina(Integer oficina) {
		this.oficina = oficina;
	}
	/**
	 * Comment for getRol
	 * @return Returns the rol.
	 */
	public Integer getRol() {
		return rol;
	}
	/**
	 * Comment for setRol
	 * @param rol The rol to set.
	 */
	public void setRol(Integer rol) {
		this.rol = rol;
	}

	/**
     * Método accesor del atributo apellido1.
     * @return Retorna el atributo apellido1.
     */
    public String getApellido1() {
        return this.apellido1;
    }


    /**
     * Método modificador del atributo apellido1.
     * @param apellido1 El dato para modificar el atributo apellido1.
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }


    /**
     * Método accesor del atributo apellido2.
     * @return Retorna el atributo apellido2.
     */
    public String getApellido2() {
        return this.apellido2;
    }


    /**
     * Método modificador del atributo apellido2.
     * @param apellido2 El dato para modificar el atributo apellido2.
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }
    
    /**
     * Método guardarNombre
     * TODO (Descripción) 
     * @return String
     */
    private String guardarNombre(){
        return this.nombre + "&" + this.apellido1 + "&" + this.apellido2;
    }
    
    private void obtenerNombre(String nombre){
    	
        if(!nombre.contains("&")){
            String values [] = nombre.split(" ");   
             if(values.length==1){
             	this.nombre = values[0];
            	this.apellido1 = "";
            	this.apellido2 = "";
            }else{
            if(values.length==2){ 
            	this.nombre = values[0];
            	this.apellido1 = values[1];
            	this.apellido2 = "";
            }else{
            	if(values.length==3){  
            		this.nombre = values[0];
            		this.apellido1 = values[1];
            		this.apellido2 = values[2]; 
            	}
            	else{
            		if(values.length==4){   
            			this.nombre = values[0];                    
            			this.apellido1 = values[2];                    
            			this.apellido2 = values[3]; 
            		}//if length==4
            	}//else fin length==3
            }//else fin length==2
            }//else fin length==1
        }
        else{
            String values2 [] = nombre.split("&");
            if(values2.length==3){     
                this.nombre = values2[0];
                this.apellido1 = values2[1];
                this.apellido2 = values2[2]; 
            }
            else{
                if(values2.length==4){    
                    this.nombre = values2[0];                    
                    this.apellido1 = values2[2];                    
                    this.apellido2 = values2[3]; 
                }
            }
        }
        
    }


    /**
	 * Comment for buscar
	 * @return "success" al buscar Empleado
	 */
	public String buscar(){
		if(this.numeroCedula!=null){
			try{
				Empleado empleado = this.empleadoBO.buscar(this.numeroCedula);
				this.numeroCedula = empleado.getCedula();
				this.numeroCedulaOriginal = empleado.getCedula();
				this.obtenerNombre(empleado.getNombreEmpleado());
				System.out.println("EMPLEADO: "+empleado.getNombreEmpleado());
				this.estado = empleado.getCongelado();
				this.rol = empleado.getIndicador();
			}catch(Exception e){
                e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de cédula "+ this.numeroCedula+" no existe"));
				this.numeroCedula = null;
				this.numeroCedulaOriginal = null;
				this.nombre = null;
				this.estado = null;
				this.rol = null;
				this.oficina = null;
                this.apellido1=null;
                this.apellido2=null;
			}
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar un número de cédula par realizar la búsqueda."));
		}
		return "success";
	}
	
	/**
	 * Comment for agregar
	 * @return "success" al agregar Empleado
	 */
	public String agregar(){
		
		if(this.numeroCedula!=null){
			if(this.nombre!=null && this.nombre.trim().length() > 0){
                if(this.apellido1!=null && this.apellido1.trim().length() > 0){
                    if(this.apellido2!=null && this.apellido2.trim().length() > 0)
                    {
                    	Empleado empleado = new Empleado();
                    	empleado.setCedula(this.numeroCedula);
                    	empleado.setNombreEmpleado(this.guardarNombre().trim().toUpperCase());
                    	empleado.setCongelado(this.estado);
                    	empleado.setIndicador(this.rol);  
                    	this.numeroCedulaOriginal = this.numeroCedula;
                    	if(!this.empleadoBO.existe(empleado))
                    	{
                    		this.empleadoBO.agregar(empleado);
                    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Empleado incluido exitosamente."));
                    	}else
                    	{
                    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de cédula "+this.numeroCedula+" para agregar ya existe"));
                    	}
                    }
                    else{
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El apellido 2 es requerido."));
                    }   
                }
                else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El apellido 1 es requerido."));
                }  
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar un nombre."));
			}
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar un número de cédula para agregar."));
		}
		return "success";
	}
	
	/**
	 * Comment for modificar
	 * @return "success" al modificar Empleado
	 */
	public String modificar(){
		FacesMessage msg = new FacesMessage();
		if(this.numeroCedulaOriginal!=null)
		{
		    if(this.numeroCedula!=null)
		    {
				if(this.nombre!=null && this.nombre.trim().length() > 0)
				{
                    if(this.apellido1!=null && this.apellido1.trim().length() > 0)
                    {
                        if(this.apellido2!=null && this.apellido2.trim().length() > 0)
                        {
                        	if(this.numeroCedula.compareTo(this.numeroCedulaOriginal)==0)
                        	{
                        		Empleado empleado = new Empleado();
                        		empleado.setCedula(this.numeroCedula);
                        		this.numeroCedula = this.numeroCedulaOriginal;
                        		if(this.empleadoBO.existe(empleado))
                        		{
                        			empleado = this.empleadoBO.buscar(this.numeroCedula);
                        			empleado.setNombreEmpleado(this.guardarNombre().trim().toUpperCase());
                        			empleado.setCongelado(this.estado);
                        			empleado.setIndicador(this.rol);
                        			this.empleadoBO.modificar(empleado);
                        		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Empleado modificado exitosamente."));
                        		}else
                        		{
                        			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de cédula " + this.numeroCedula + " para modificar no existe"));
                        		}
                        	}else
                        	{
                        		this.numeroCedula = this.numeroCedulaOriginal;
                        		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe buscar un empleado para modificarlo.  Recuerde: la cédula no es modificable"));
                        	}
                        }
                        else
                        {
                        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El apellido 2 es requerido."));
                        }   
                    }else
                    {
                    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El apellido 1 es requerido."));
                    }   
				}else
				{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar un nombre."));
				}
		    }else
		    {
		    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe buscar un empleado para modificarlo."));
		    }
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe buscar un empleado para modificarlo."));
		}
		return "success";
	}
	
	/**
	 * Comment for eliminar
	 * @return "success" al eliminar Empleado
	 */
	public String eliminar(){
		FacesMessage msg = new FacesMessage();
		if(this.numeroCedulaOriginal!=null && this.numeroCedula != null){
		    if(this.numeroCedulaOriginal.longValue() == this.numeroCedula.longValue()){
				Empleado empleado = new Empleado();
				empleado.setCedula(this.numeroCedulaOriginal);
				if(this.empleadoBO.existe(empleado)){
                    Long cantidad = this.empleadoBO.getRegistrosAsociados(this.numeroCedulaOriginal);
                    if(cantidad.longValue() == 0){
    				    empleado = this.empleadoBO.buscar(this.numeroCedulaOriginal);
    					this.empleadoBO.eliminar(empleado);
    					this.numeroCedula=null;
    					this.numeroCedulaOriginal=null;
    					this.nombre=null;
                        this.apellido1=null;
                        this.apellido2=null;
    					this.estado=this.estadoActivo;
    					this.rol=this.rolOperador;
    					this.oficina=null;
    					 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Empleado eliminado exitosamente."));
                    }else{
                       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede eliminar!", "La cédula tiene información asociada."));
                    }
				}else{
					 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El número de cédula "+this.numeroCedula+" para eliminar no existe."));
				}
		    }
		    else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe buscar in empleado. Seguidamente prodrá eliminarlo"));
			}
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe buscar un empleado para eliminarlo."));
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
