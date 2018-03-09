package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.TipoVoltaje;
import cr.go.ice.interrupciones.domain.TipoVoltajeID;

/**
 * <p>Interface cr.go.ice.interrupciones.DAO.TipoVoltajeDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>TipoVoltajeDAO.java</code>Define los metodos de los Datos para TipoVoltaje</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Tecnologia (Nano y Doc Cristian)
 * @version 1.1
 */

public interface TipoVoltajeDAO {
	
    /**
     * Agrega un TipoVoltaje
     * @param tipoVoltaje a agregar
     */
	public void agregar(TipoVoltaje tipoVoltaje);
	
    /**
     * Modifica un TipoVoltaje
     * @param tipoVoltaje a modificar
     */
	public void modificar(TipoVoltaje tipoVoltaje);
	
    /**
     * Elimina un TipoVoltaje
     * @param tipoVoltaje a eliminar
     */
	public void eliminar(TipoVoltaje tipoVoltaje);
	
    /**
     * Busca un determinado Agrega un TipoVoltaje
     * @param tipoVoltajeID Indica el ID del TipoVoltaje deseado
     * @return TipoVoltaje y si no Null
     */
	public  TipoVoltaje buscar(TipoVoltajeID tipoVoltajeID);
	
    /**
     * Determina la lista de tipos de voltaje
     * @return Lista de Tipos de Voltajes existentes
     */
	public List getTiposVoltaje();
    
    /**
     * Determina la lista de <code>TipoVoltaje</code> ordenados por su tipo de voltajes
     * @return Lista de Tipos de Voltajes existentes
     */
    public List getTiposVoltajeOrdenPorTipoVoltaje();    
	
    /**
     * Determina la existencia de un TipoVoltaje
     * @param tipoVoltaje Indica el TipoVoltaje para el cual se determinara su existencia
     * @return true o false
     */
	public boolean existe(TipoVoltaje tipoVoltaje);
	
	/**
	 * Retorna la cantidad de registros asociados en las interrupciones mayores y menores a cinco minutos
	 * @param tipoVoltajeID que indica el tipoVoltajeID
	 * @return cantidad de registros asociados
	 */
	public Long registrosAsociados(TipoVoltajeID tipoVoltajeID);   	
	
}
