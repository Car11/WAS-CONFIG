package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.EmpleadoBO;
import cr.go.ice.interrupciones.BO.UsuarioOficinaBO;
import cr.go.ice.interrupciones.domain.Empleado;
import cr.go.ice.interrupciones.domain.UsuarioOficina;

/**
 * Clase: cr.go.ice.sige.sigecon.web.controller.TecnicoController
 * Creada el 24/01/2008 11:03:45 PM
 * Autor: Nano
 * Clase que controla los parametros ingresados por el usuario para realizar la consulta
 * de los técnicos (Empleados) almacenados en la base de datos
 */
public class ConsultaTecnicoController extends AbstractFacesController {
    /** atributo <code>empleadoBO</code>*/
    private EmpleadoBO empleadoBO;    
    
    private UsuarioOficinaBO usuarioOficinaBO; 
    /** atributo <code>buscar</code>*/
    private String criterioBusqueda;    
    /** atributo <code>empleados</code>*/
    private List empleados;
    
    private List empleadosOficina;
    
    /**
     * Constructor
     */
    public ConsultaTecnicoController(){
    	this.resetController();
    }   
    
    /**
     * Metodo para iniciar el módulo
     * @param context El contexto faces de la aplicación
     */
    public String getInit(){
    	FacesContext context = FacesContext.getCurrentInstance();
        Object init = context.getExternalContext().getRequestParameterMap().get("init");
        if(init != null){
            this.buscarTodos();
        }
        return "success";
    }
    
    /**
     * @return regresa el criterioBusqueda.
     */
    public String getCriterioBusqueda() {
        return criterioBusqueda;
    }
    /**
     * @param criterioBusqueda a colocar en el atributo correspondiente.
     */
    public void setCriterioBusqueda(String criterioBusqueda) {
        this.criterioBusqueda = criterioBusqueda;
    }
    /**
     * @param empleadoBO a colocar en el atributo correspondiente.
     */
    public void setEmpleadoBO(EmpleadoBO empleadoBO) {
        this.empleadoBO = empleadoBO;
    }
    /**
     * @return regresa el empleados.
     */
    public List getEmpleados() {
        if(this.empleados==null){
            this.empleados = this.empleadoBO.getEmpleados();
        }
        return empleados;
    }
    /**
     * @param empleados a colocar en el atributo correspondiente.
     */
    public void setEmpleados(List empleados) {
        this.empleados = empleados;
    }
    /**
     * Método buscarPorCedula
     * Método que busca el empleado que posee la cedula capturada
     * @return success si se encontró el empleado
     */
    public String buscarPorCedula(){
        this.empleadosOficina = new ArrayList();
        if((this.criterioBusqueda == null) || (this.criterioBusqueda.trim().equals(""))){
            this.buscarTodos();
            return "success";
        }
        if(this.esNumero(this.criterioBusqueda)){
            Long cedula = new Long(Long.parseLong(this.criterioBusqueda));
            List<UsuarioOficina> empleados = this.usuarioOficinaBO.buscarCedula(cedula);
            if(!empleados.isEmpty()){
                this.empleadosOficina = new ArrayList();
                empleadosOficina.addAll(empleados);
                return "success";
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se encontró un técnico con esa cédula."));
                return "error";
            }
        }else{
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La cédula no es numérica o excede la cantidad de dígitos."));
            return "error";
        }
    }
    
    /**
     * Método buscarTodos()
     * Realiza un listado de todas las agencias
     */
    public void buscarTodos()
    {
    	this.empleadosOficina = this.usuarioOficinaBO.buscarTodos();
        if(this.empleadosOficina.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No hay Técnicos registrados."));
        }
    }
    
    
    /**
     * Metodo buscarPorCedula
     * Metodo que busca el empleado que posee la cedula capturada
     * @return success si se encontró el empleado que posea la cedula digitada, error en caso 
     * de que no se digitará un número o bien, la cédula no existea
     */
    public String buscarPorCedulaOld(){
        if(this.validarCedulaEmpleado()){
            Long cedula = new Long(Long.parseLong(this.criterioBusqueda));
            Empleado empleado = this.empleadoBO.getEmpleadoActivo(cedula);
            if(empleado!=null){
                this.empleados = new ArrayList();
                empleados.add(empleado);
                return "success";
            }else{
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se encontró un técnico con esa cédula."));
                return "error";
            }
        }else{
            return "error";
        }
    }
    
    /**
     * 
     * Retorna si el parámetro string es un número
     * @param numero string a evaluar
     * @return true si es número, false si no es número 
     */
    private boolean esNumero(String numero){
        try{
            new Integer(Integer.parseInt(numero));
            return true;
        }
        catch(NumberFormatException nef){
            return false;
        }
    }
    
    /**
     * Metodo buscarPorNombre
     * Método que busca el empleado que posee el nombre, o bien, su nombre
     * comienze con el patrón capturado
     * @return success si se encontraron empleados
     */
    public String buscarPorNombre(){
        this.empleadosOficina = new ArrayList();
        if((this.criterioBusqueda == null) || (this.criterioBusqueda.trim().equals(""))){
            this.buscarTodos();
            return "success";
        }
        this.empleadosOficina = this.usuarioOficinaBO.buscarNombre(criterioBusqueda);
        if(!this.empleadosOficina.isEmpty()){
            return "success";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se encontraron técnicos con este nombre."));
            return "error";
        }
    }
    
    /**
     * Metodo buscarPorNombre
     * Método que busca el empleado que posee el nombre, o bien, su nombre
     * comienze con el patrón capturado
     * @return success si se encontraron empleados que cumplan con el patron, error en caso
     * de que no encontrara o no existiera algun nombre que cumpla con el patron
     */
    public String buscarPorNombreOld(){
        if(this.validarNombreEmpleado()){
            this.empleados = this.empleadoBO.getEmpleados("%"+this.criterioBusqueda+"%");
            if(!this.empleados.isEmpty()){
                return "success";
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se encontraron técnicos con este nombre."));
                return "error";
            }
        }else{
            return "error";
        }
    }
    
    /**
     * Metodo validarCedulaEmpleado
     * Metodo que valida que la cédula sea un dato válido. Se valida que la cédula
     * sea distinta de blanco y que sea solo números
     * @return verdadero si el patrón es un número valido, falso en caso contrario
     */
    private boolean validarCedulaEmpleado(){
        if((this.criterioBusqueda == null) || (this.criterioBusqueda.trim().equals(""))){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ".Debe digitar la cédula a buscar"));
            return false;
        }
        try{
            Long codigoParseado = new Long(Long.parseLong(this.criterioBusqueda));
            if(codigoParseado.intValue() <= 0){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El código no puede ser menor o igual a cero."));
                return false;
            }
            return true;
        }
        catch(NumberFormatException nef){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Búsqueda por cédula necesita un número."));
            return false;
        }
        
    }
    
    /**
     * Metodo validarNombreEmpleado
     * Metodo que valida que el patron del nombre sea distinto de blanco.
     * @return verdadero sino es blanco, falso en caso contrario
     */
    private boolean validarNombreEmpleado(){
        if((this.criterioBusqueda == null) || (this.criterioBusqueda.trim().equals(""))){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ".Debe digitar el nombre a buscar."));
            return false;
        }
        return true;
    }
    
    // -------------------- Métodos específicos del controller -------------------- //
    
    /**
     * Método addError
     * Agrega un mensaje de error a una determinada propiedad
     */
//    private void addError(String property, String message){
//        FacesContext context = FacesContext.getCurrentInstance();
//        FacesMessage msg = new FacesMessage();
//        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//        msg.setSummary(message);
//        msg.setDetail(message);
//        context.addMessage(property, msg);
//    }
//    
//    /**
//     * Método getPropertyFieldName
//     * Devuelve el nombre de la propiedad que se encuentra en el JSP
//     * @return String con el nombre en el controller
//     */
//    private String getPropertyFieldName(String property){
//        if(property.equals("consultaTecnico.buscar")) return "form1:txtBuscar";
//        return null;
//    }
    
   

	public UsuarioOficinaBO getUsuarioOficinaBO()
	{
		return usuarioOficinaBO;
	}

	public void setUsuarioOficinaBO(UsuarioOficinaBO usuarioOficinaBO) 
	{
		this.usuarioOficinaBO = usuarioOficinaBO;
	}

	public EmpleadoBO getEmpleadoBO() {
		return empleadoBO;
	}

	public List getEmpleadosOficina() {
		return empleadosOficina;
	}

	public void setEmpleadosOficina(List empleadosOficina) {
		this.empleadosOficina = empleadosOficina;
	}

	@Override
	protected String getPropertyFieldName(String property) {
		 if(property.equals("consultaTecnico.buscar")) return "form1:txtBuscar";
		 return null;
	}

	@Override
	protected void resetController() {
		this.criterioBusqueda = null;
        this.empleados = new ArrayList();
        this.empleadosOficina = new ArrayList();
		
	}
}
