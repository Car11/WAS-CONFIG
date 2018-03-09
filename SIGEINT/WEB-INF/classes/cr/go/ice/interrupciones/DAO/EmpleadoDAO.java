package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.Empleado;

/**
 * <p>Interface cr.go.ice.interrupciones.DAO.EmpleadoDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>EmpleadoDAO.java</code> Define los metodos de los Datos para Empleado</p>
 * <p>Fecha creación: 17/02/2007</p>
 * <p>Ultima actualización: 17/02/2007</p>
 * @author Vista Verde Tecnologia (Nano y Doc Cristian)
 * @version 1.1
 */
public interface EmpleadoDAO {
    
    /**
     * Agrega un Empleado
     * @param empleado a agregar
     */
	public void agregar(Empleado empleado);
	
    /**
     * Modifica un Empleado
     * @param empleado a modificar
     */
	public void modificar(Empleado empleado);
	
    /**
     * Elimina un Empleado
     * @param empleado a eliminar
     */
	public void eliminar(Empleado empleado);
	
    /**
     * Busca un empleado
     * @param codigoEmpleado Códigoo o número de empleado deseado como filtro
     * @return Empleado y si no esta NULL
     */
	public Empleado getEmpleado(Long codigoEmpleado);
	
    /**
     * Determina una lista de Empleados
     * @return Lista de empleados
     */
	public List getEmpleados();
	
    /**
     * Lista de empleados filtradas por nombre
     * @param nombreEmpleado Nombre deseado como filtro
     * @return Lista de Empleados
     */
	public List getEmpleados(String nombreEmpleado);
	
	public List getEmpleadosTodos(String nombreEmpleado);
	
    /**
     * Determina la existencia de un Empleado
     * @param empleado Empleado deseado como filtro
     * @return true o false
     */
	public boolean empleadoExiste(Empleado empleado);
	
	/**
	 * Obtiene una lista de empleados pertenecientes a una oficina
	 * @param codigoOficina Oficina mediante la cual se filtraran los empleados
	 * @return Lista de empleados de una oficina
	 */
	public List getEmpleadosPorOficina(Integer codigoOficina);
	
	/**
	 * Obtiene un empleado activo
	 * @param codigoEmpleado Es la cedula del empleado
	 * @return El empleado o null sino existe
	 */
	public Empleado getEmpleadoActivo(Long codigoEmpleado);
    
    /**
     * Determina si un empleado tiene registros asociados en otras tablas
     * @param codigoEmpleado Empleado deseado
     * @return Cantidad de registros asociados
     */
    public Long getRegistrosAsociados(Long codigoEmpleado);
    
   
    
}
