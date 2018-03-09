package cr.go.ice.interrupciones.BO;

import java.util.List;

import cr.go.ice.interrupciones.domain.SubEstacion;

/**
* <p>Interface cr.go.ice.interrupciones.BO.SubEstacionBO.java</p>
* <p>Modulo (subsistema): Interrupciones Web</p>
* <p>Descricion de <code>SubEstacionBO.java</code>Define los Metodos de Logica y Negocio de las Clases.</p>
* <p>Fecha creaci�n: 22/02/2007</p>
* <p>Ultima actualizaci�n: 22/02/2007</p>
* @author Vista Verde Soft (Administrador y Doc Cristian)
* @version 1.1
*/
public interface SubEstacionBO {
    
    /**
     * Agrega una SubEstacion
     * @param subEstacion a agregar
     */
    public void agregar(SubEstacion subEstacion);
    
    /**
     * Modifica una SubEstacion
     * @param subEstacion a modificar
     */
    public void modificar(SubEstacion subEstacion);
    
    /**
     * Elimina una SubEstacion
     * @param subEstacion a eliminar
     */
    public void eliminar(SubEstacion subEstacion);
    
    /**
     * Busca una SubEstacion
     * @param codigo Codigo deseado como filtro
     * @return SubEstacion o null
     */
    public SubEstacion buscar(Integer codigo);
    
    /**
     * Busca una SubEstacion por nombre
     * @param nombre Nombre deseado como filtro
     * @return SubEstacion, sino null
     */
    public SubEstacion buscar(String nombre);
    
    /**
     * Determina una lista de subestaciones
     * @return Lista de sub Estaciones
     */
    public List getSubEstaciones();
    
    /**
     * Determina una lista de subestaciones por oficina
     * @param codigoOficina Oficina deseada como filtro
     * @return Lista Sub estaciones deseadas
     */
    public List getSubEstaciones(Integer codigoOficina);  
        
    /**
     * Determina la existencia de una SubEstacion
     * @param subEstacion SubEstacion deseada par determinar su existencia.
     * @return true o false
     */
    public boolean subEstacionExiste(SubEstacion subEstacion);
    
    /**
     * Determina un alista de SubEstaciones por oficina, region y subregion
     * @param codigoRegion C�digo de regi�n deseado como filtro
     * @param codigoSubRegion C�digo de subregi�n deseado como filtro
     * @param codigoOficina C�digo de oficina deseado como filtro
     * @return Lista sub estaciones, subregion y sub codigo de Oficina
     */
    public List getSubEstacionesSubRegionOficina(Integer codigoRegion, Integer codigoSubRegion, Integer codigoOficina);
    
	/**
	 * M�todo que ejecuta el procedimiento almacenado de c�lculo de �ndices globales por subestaci�n
	 * @param ano A�o a partir del cual se desea realizar el c�lculo de �ndices
	 * @param mes Mes a partir del cual se desea realizar el c�lculo de �ndices
	 * @return S o N de acuerdo a la correcta ejecuci�n o no del procedimiento almacenado
	 */
	public String ejecutarIndicesGlobales(Integer ano, Integer mes);
	
    /**
     * Retorna un listado de las subestaciones pertenecientes a la region y subregion indicadas
     * @param region La region de las subestaciones
     * @param subregion La subregion de las subestaciones
     * @return Un listado de objetos <code>SubEstacion</code>.
     */
    public List getSubEstaciones(Integer region, Integer subregion);
    
    public List getSubEstacionesActivas(Integer region, Integer subregion);
    
    /**
     * M�todo getSubEstacionesRegion
     * TODO (Descripci�n) 
     * @param codigoRegion
     * @return
     */
    public List getSubEstacionesRegion(Integer codigoRegion);
    
    /**
     * M�todo getCodigoRegionSubEstacion
     * TODO (Descripci�n) 
     * @param codigoSubEstacion
     * @return
     */
    public Integer getCodigoRegionSubEstacion(Integer codigoSubEstacion);
	
}