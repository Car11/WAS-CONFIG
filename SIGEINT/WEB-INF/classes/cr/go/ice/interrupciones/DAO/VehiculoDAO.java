
package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.Vehiculo;

/**
 * <p>Interface cr.go.ice.obras.DAO.VehiculoDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>VehiculoDAO.java</code>Define los metodos de los Datos para Vehiculo.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public interface VehiculoDAO {

    /**
     * Agrega un vehiculo
     * @param vehiculo a agregar
     */
    public void agregar(Vehiculo vehiculo);
    
    /**
     * Modifica un vehiculo
     * @param vehiculo a modificar
     */
    public void modificar(Vehiculo vehiculo);
    
    /**
     * Elimina un vehiculo
     * @param vehiculo a eliminar
     */
    public void eliminar(Vehiculo vehiculo);
    
    /**
     * Busca un vehiculo determinado en la base de datos
     * @param vehiculo especifica el vehiculoID a buscar
     * @return Vehiculo si se encontrara, sino null
     */
    public Vehiculo buscar(Vehiculo vehiculo);    
    
    /**
     * Retorna una lista de vehiculos
     * @return Lista de vehiculos
     */
    public List getVehiculos();         
    
    /**
     * Determina la existencia de un vehiculo
     * @param vehiculo que especifica el vehiculoID a buscar
     * @return La existencia o no del vehiculo
     */
    public boolean vehiculoExiste(Vehiculo vehiculo);
	
}
