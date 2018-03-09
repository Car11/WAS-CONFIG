
package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.SubRegion;
import cr.go.ice.interrupciones.domain.SubRegionID;
;

/**
 * <p>Interface cr.go.ice.interrupciones.DAO.SubRegionDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SubRegionDAO.java</code>Define los metodos de los Datos para SubRegion.</p>
 * <p>Fecha creación: 22/01/2007</p>
 * <p>Ultima actualización: 22/01/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public interface SubRegionDAO {
	
	/**
	 * Agrega una subregion
	 * @param subRegion a agregar
	 */
	public void agregar(SubRegion subRegion);
    
    /**
     * Modifica una subregion
     * @param subRegion a modificar
     */
    public void modificar(SubRegion subRegion);
    
    /**
     * Elimina una subregion
     * @param subRegion a eliminar
     */
    public void eliminar(SubRegion subRegion);
    
    /**
     * Busca una subregion deseada
     * @param subRegionId especifica cual es la subregion deseada
     * @return SubRegion si se encuentra, sino null
     */
    public SubRegion buscar(SubRegionID subRegionId);        
    
    /**
     * Retorna una lista de subregiones
     * @return Lista de sub regiones
     */
    public List getSubRegiones();
    
    /**
     * Retorna una lista de subregiones asociadas a una determinada region
     * @param region específica la region a la que pertenecen las subregiones deseadas
     * @return Lista Sub Regiones pertenecientes a una determinada region
     */
    public List getSubRegiones(Integer region);
    
    public List getSubRegionesActivas(Integer region);
    
    /**
     * Metodo getSubRegiones
     * Retorna una lista de subregiones asociadas a una determinada region
     * @param region específica la region a la que pertenecen las subregiones deseadas
     * @param orden. Si se desea que se la lista este ordenada por codigo (1) o nombre (2). Sino se especifica,
     * se regresará la lista ordenada por codigo
     * @return Lista Sub Regiones pertenecientes a una determinada region 
     */
    public List getSubRegiones(Integer region, Integer orden);
    
    /**
     * Determina la existencia de una subregion
     * @param subRegionID especifica cual es la subregion deseada
     * @return La existencia o no de la subregion
     */
    public boolean subRegionExiste(SubRegionID subRegionID);
    
	/**
	 * Obtiene una SubRegion de acuerdo a la oficina que determina las secciones
     * @param codigoOficina Codigo oficina deseado como filtro
     * @return SubRegion o null
	 */
    public SubRegion buscarPorOficina(Integer codigoOficina);
    
    /**
     * Obtiene una lista de subregiones filtrada de acuerdo a una oficina y una región
     * @param oficina Oficina deseada como filtro
     * @param region Region deseada como filtro
     * @return Lista de subregiones deseadas
     */     
    public List getSubRegionesPorOficina(Integer oficina, Integer region);
    
	/**
	 * Método que ejecuta el procedimiento almacenado de cálculo de índices globales por subregión
	 * @param ano Año a partir del cual se desea realizar el cálculo de índices
	 * @param mes Mes a partir del cual se desea realizar el cálculo de índices
	 * @return S o N de acuerdo a la correcta ejecución o no del procedimiento almacenado
	 */	
    public String ejecutarIndicesGlobales(Integer ano, Integer mes);
    
    /**
     * Obtiene las sub regiones de acuerdo una oficina y region dada, de acuerdo a la tabla correspondiente a SubEstacionSubRegion 
     * @param oficina Oficina deseada
     * @param region Region deseada
     * @return  Lista de sub regiones deseadas
     */
    public List getSubRegionesPorOficinaPorSubEstacionSubRegion(Integer oficina, Integer region);
    
}
