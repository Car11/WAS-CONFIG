package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.Region;

/**
 * <p>Interface cr.go.ice.interrupciones.DAO.RegionDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>RegionDAO.java</code>Define los metodos de los Datos para Region</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Tecnologia (Nano y Doc Cristian)
 * @version 1.1
 */

public interface RegionDAO {
	
    /**
     * Agrega una region
     * @param region Region a agregar
     */
	public void agregar(Region region);
	
    /**
     * Modifica una region
     * @param region Region a modificar
     */
	public void modificar(Region region);
	
    /**
     * Elimina una region
     * @param region Region a eliminar
     */
	public void eliminar(Region Region);
	
    /**
     * Busca una determinada region
     * @param codigoRegion Código de la región deseada
     * @return La <code>Region</code> y si no Null
     */
	public  Region buscar(Integer codigoRegion);
	
    /**
     * Retorna una lista de las regiones
     * @return Lista de Regiones
     */
	public List getRegiones();
	
    /**
     * Retorna una lista de regiones ordenadas o no por nombre de la region
     * @param orden Indica si se requiere el orden de la lista por el nombre de la region
     * @return Lista de regiones deseadas
     */
	public List getRegiones(Integer orden);
	
    /**
     * Determina la existencia de una región
     * @param region Region deseada
     * @return true o false
     */
	public boolean existe(Region Region);
	
    /**
     * Obtiene una Region de acuerdo a la oficina que determina las secciones
     * @param codigoOficina Oficina deseada como filtro
     * @return Una region obtenida por oficina
     */ 
	public Region buscarPorOficina(Integer codigoOficina);
	
    /**
     * Obtiene una lista de regiones asociadas a una oficina
     * @param oficina Oficina deseada como filtro
     * @return Lista de regiones
     */
	public List getRegionesPorOficina(Integer oficina);
	
	public List getRegionesActivasPorOficina(Integer oficina);
	
	/**
	 * Método que ejecuta el procedimiento almacenado de cálculo de índices globales por región
	 * @param ano Año a partir del cual se desea realizar el cálculo de índices
	 * @param mes Mes a partir del cual se desea realizar el cálculo de índices
	 * @return S o N de acuerdo a la correcta ejecución o no del procedimiento almacenado
	 */
    public String ejecutarIndicesGlobales(Integer ano, Integer mes); 
    
    /**
     * Obtiene las regiones de acuerdo una oficina dada, de acuerdo a la tabla correspondiente a SubEstacionSubRegion 
     * @param oficina Oficina deseada
     * @return  Lista de regiones deseadas
     */
    public List getRegionesPorOficinaPorSubEstacionSubRegion(Integer oficina);
    
	
}
