
package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.VehiculoBO;
import cr.go.ice.interrupciones.DAO.VehiculoDAO;
import cr.go.ice.interrupciones.domain.Vehiculo;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.VehiculoBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>VehiculoBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para Vehiculo.</p>
 * <p>Fecha creación: 26/03/2007</p>
 * <p>Ultima actualización: 26/03/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class VehiculoBOImpl implements VehiculoBO{

	/**
	 * <code>vehiculoDAO</code> Vehiculo DAO
	 */
	private VehiculoDAO vehiculoDAO;
	
	/**
	 * Constructor
	 */
	public VehiculoBOImpl(){
		
	}
	
	/**
	 * Asigna VehiculoDAO
	 * @param vehiculoDAO El vehiculoDAO a establecer.
	 */
	public void setVehiculoDAO(VehiculoDAO vehiculoDAO) {
		this.vehiculoDAO = vehiculoDAO;
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.VehiculoBO#agregar(cr.go.ice.interrupciones.domain.Vehiculo)
	 */
	public void agregar(Vehiculo vehiculo) {
		this.vehiculoDAO.agregar(vehiculo);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.VehiculoBO#modificar(cr.go.ice.interrupciones.domain.Vehiculo)
	 */
	public void modificar(Vehiculo vehiculo) {
		this.vehiculoDAO.modificar(vehiculo);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.VehiculoBO#eliminar(cr.go.ice.interrupciones.domain.Vehiculo)
	 */
	public void eliminar(Vehiculo vehiculo) {
		this.vehiculoDAO.eliminar(vehiculo);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.VehiculoBO#buscar(cr.go.ice.interrupciones.domain.Vehiculo)
	 */
	public Vehiculo buscar(Vehiculo vehiculo) {
		return this.vehiculoDAO.buscar(vehiculo);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.VehiculoBO#getVehiculos()
	 */
	public List getVehiculos() {
		return this.vehiculoDAO.getVehiculos();
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.VehiculoBO#vehiculoExiste(cr.go.ice.interrupciones.domain.Vehiculo)
	 */
	public boolean vehiculoExiste(Vehiculo vehiculo) {
		return this.vehiculoDAO.vehiculoExiste(vehiculo);
	}
}
