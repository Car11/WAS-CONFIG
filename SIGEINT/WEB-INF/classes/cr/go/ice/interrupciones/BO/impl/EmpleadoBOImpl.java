
package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.EmpleadoBO;
import cr.go.ice.interrupciones.DAO.EmpleadoDAO;
import cr.go.ice.interrupciones.domain.Empleado;

/**
 * 
 * <p>Clase cr.go.ice.interrupciones.BO.impl.EmpleadoBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>EmpleadoBOImpl.java</code> Define los Metodos de Logica y Negocio de las Clases para Empleado.</p>
 * <p>Fecha creación: 17/02/2007</p>
 * <p>Ultima actualización: 17/02/2007</p>
 * @author Vista Verde Tecnologia (Nano y Doc Cristian)
 * @version 1.1
 */
public class EmpleadoBOImpl implements EmpleadoBO {
	/**
	 * <code>empleadoDAO</code> empleados Dao
	 */
	private EmpleadoDAO empleadoDAO;
    
    
    /**
     * Asigna EmpleadoDAO
     * @param empleadoDAO The empleadoDAO to set.
     */
    public void setEmpleadoDAO(EmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO;
    }
    
	/**
	 * @see cr.go.ice.interrupciones.BO.EmpleadoBO#agregar(cr.go.ice.interrupciones.domain.Empleado)
	 */
	public void agregar(Empleado empleado) {
		this.empleadoDAO.agregar(empleado);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.EmpleadoBO#modificar(cr.go.ice.interrupciones.domain.Empleado)
	 */
	public void modificar(Empleado empleado) {
		this.empleadoDAO.modificar(empleado);

	}

	/**
	 * @see cr.go.ice.interrupciones.BO.EmpleadoBO#eliminar(cr.go.ice.interrupciones.domain.Empleado)
	 */
	public void eliminar(Empleado empleado) {
		this.empleadoDAO.eliminar(empleado);

	}

	/**
	 * @see cr.go.ice.interrupciones.BO.EmpleadoBO#buscar(java.lang.Long)
	 */
	public Empleado buscar(Long codigoEmpleado) {
		return this.empleadoDAO.getEmpleado(codigoEmpleado);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.EmpleadoBO#getEmpleados()
	 */
	public List getEmpleados() {
		return this.empleadoDAO.getEmpleados();
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.EmpleadoBO#existe(cr.go.ice.interrupciones.domain.Empleado)
	 */
	public boolean existe(Empleado empleado) {
		return this.empleadoDAO.empleadoExiste(empleado);
	}



	/**
	 * @see cr.go.ice.interrupciones.BO.EmpleadoBO#getEmpleados(java.lang.String)
	 */
	public List getEmpleados(String nombreEmpleado) {
		return this.empleadoDAO.getEmpleados(nombreEmpleado);
	}
	
	public List getEmpleadosTodos(String nombreEmpleado) {
		return this.empleadoDAO.getEmpleadosTodos(nombreEmpleado);
	}

    /**
     * @see cr.go.ice.interrupciones.BO.EmpleadoBO#getEmpleadosPorOficina(java.lang.Integer)
     */
    public List getEmpleadosPorOficina(Integer codigoOficina) {
        return this.empleadoDAO.getEmpleadosPorOficina(codigoOficina);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.EmpleadoBO#getEmpleadoActivo(java.lang.Long)
     */   
    public Empleado getEmpleadoActivo(Long codigoEmpleado) {
        return this.empleadoDAO.getEmpleadoActivo(codigoEmpleado);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.EmpleadoBO#getRegistrosAsociados(java.lang.Long)
     */
    public Long getRegistrosAsociados(Long codigoEmpleado) {
        return this.empleadoDAO.getRegistrosAsociados(codigoEmpleado);
    }
    
   
}
