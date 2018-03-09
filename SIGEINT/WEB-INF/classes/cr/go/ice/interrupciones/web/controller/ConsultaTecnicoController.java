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
 * de los t�cnicos (Empleados) almacenados en la base de datos
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
     * Metodo para iniciar el m�dulo
     * @param context El contexto faces de la aplicaci�n
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
     * M�todo buscarPorCedula
     * M�todo que busca el empleado que posee la cedula capturada
     * @return success si se encontr� el empleado
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
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se encontr� un t�cnico con esa c�dula."));
                return "error";
            }
        }else{
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La c�dula no es num�rica o excede la cantidad de d�gitos."));
            return "error";
        }
    }
    
    /**
     * M�todo buscarTodos()
     * Realiza un listado de todas las agencias
     */
    public void buscarTodos()
    {
    	this.empleadosOficina = this.usuarioOficinaBO.buscarTodos();
        if(this.empleadosOficina.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No hay T�cnicos registrados."));
        }
    }
    
    
    /**
     * Metodo buscarPorCedula
     * Metodo que busca el empleado que posee la cedula capturada
     * @return success si se encontr� el empleado que posea la cedula digitada, error en caso 
     * de que no se digitar� un n�mero o bien, la c�dula no existea
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
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se encontr� un t�cnico con esa c�dula."));
                return "error";
            }
        }else{
            return "error";
        }
    }
    
    /**
     * 
     * Retorna si el par�metro string es un n�mero
     * @param numero string a evaluar
     * @return true si es n�mero, false si no es n�mero 
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
     * M�todo que busca el empleado que posee el nombre, o bien, su nombre
     * comienze con el patr�n capturado
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se encontraron t�cnicos con este nombre."));
            return "error";
        }
    }
    
    /**
     * Metodo buscarPorNombre
     * M�todo que busca el empleado que posee el nombre, o bien, su nombre
     * comienze con el patr�n capturado
     * @return success si se encontraron empleados que cumplan con el patron, error en caso
     * de que no encontrara o no existiera algun nombre que cumpla con el patron
     */
    public String buscarPorNombreOld(){
        if(this.validarNombreEmpleado()){
            this.empleados = this.empleadoBO.getEmpleados("%"+this.criterioBusqueda+"%");
            if(!this.empleados.isEmpty()){
                return "success";
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se encontraron t�cnicos con este nombre."));
                return "error";
            }
        }else{
            return "error";
        }
    }
    
    /**
     * Metodo validarCedulaEmpleado
     * Metodo que valida que la c�dula sea un dato v�lido. Se valida que la c�dula
     * sea distinta de blanco y que sea solo n�meros
     * @return verdadero si el patr�n es un n�mero valido, falso en caso contrario
     */
    private boolean validarCedulaEmpleado(){
        if((this.criterioBusqueda == null) || (this.criterioBusqueda.trim().equals(""))){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ".Debe digitar la c�dula a buscar"));
            return false;
        }
        try{
            Long codigoParseado = new Long(Long.parseLong(this.criterioBusqueda));
            if(codigoParseado.intValue() <= 0){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El c�digo no puede ser menor o igual a cero."));
                return false;
            }
            return true;
        }
        catch(NumberFormatException nef){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "B�squeda por c�dula necesita un n�mero."));
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
    
    // -------------------- M�todos espec�ficos del controller -------------------- //
    
    /**
     * M�todo addError
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
//     * M�todo getPropertyFieldName
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
