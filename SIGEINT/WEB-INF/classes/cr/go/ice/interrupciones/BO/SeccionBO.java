
package cr.go.ice.interrupciones.BO;

import java.util.List;

import cr.go.ice.interrupciones.domain.CambioSeccion;
import cr.go.ice.interrupciones.domain.Seccion;
import cr.go.ice.interrupciones.domain.SeccionID;

/**
 * <p>Interface cr.go.ice.interrupciones.BO.SeccionBO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SeccionBO.java</code>Define los Metodos de Logica y Negocio de las Clases para Seccion.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (Cesar y Doc Cristian)
 * @version 1.1
 */
public interface SeccionBO {


	/**
	 * Agrega una seccion
	 * @param seccion a agregar
	 */
	public void agregar(Seccion seccion);

	/**
	 * Modifica una seccion
	 * @param seccion a modificar
	 */
	public void modificar(Seccion seccion);

	/**
	 * Elimina una seccion
	 * @param seccion a eliminar
	 */
	public void eliminar(Seccion seccion);

	/**
	 * Busca una seccion
	 * @param seccionID ID deseado para la búsqueda
	 * @return Seccion y si no esta Null
	 */
	public Seccion buscar(SeccionID seccionID);

	/**
	 * Determina una lista de secciones
	 * @return lista de secciones
	 */
	public List getSecciones();
	
	/**
	 * Determina una lista de secciones filtrada por subEstacion y circuito
	 * @param subEstacion SubEstacion deseada como filtro
	 * @param circuito Circuito deseado como filtro
	 * @return Lista de secciones con sub estaciones y circuitos
	 */
	public List getSecciones(Integer subEstacion, Integer circuito);
	
	/**
     * Determina la cantidad de secciones existentes pertenecientes a la subEstacion y circuito dados
     * @param subEstacion SubEstacion dada como filtro
     * @param circuito Circuito dado como filtro
     * @return Retorna el contador de secciones pertenecientes a la subEstacion y circuito dados
     */
	public long getBorrarSecciones(Integer subEstacion, Integer circuito);
    
    /**
     * Determina una lista de secciones filtrada por subEstacion y circuito, incluyendo las secciones congeladas
     * @param subEstacion SubEstacion deseada como filtro
     * @param circuito Circuito deseado como filtro
     * @return Lista Secciones filtradas por con Subestacion y circuito, sin tomar en cuenta si estan congeladas o no
     */
    public List getTodasSecciones(Integer subEstacion, Integer circuito);        

	/**
	 * Determina una lista de secciones filtrada por subEstacion y circuito
	 * @param subEstacion SubEstacion deseada como filtro
	 * @param circuito Circuito deseado como filtro
	 * @param orden Orden, ya sea por nombre de la sección o sin el
	 * @return Lista Secciones filtradas por con Subestacion y circuito
	 */
	public List getSecciones(Integer subEstacion, Integer circuito, Integer orden);
	
	/**
	 * 
	 * Determina una lista de secciones filtrada por subRegion, subEstacion y circuito 
	 * @param subEstacion
	 * @param circuito
	 * @param subregion
	 * @param region
	 * @param orden
	 * @return lista
	 */
	public List getSecciones(Integer subEstacion, Integer circuito, Integer subregion, Integer region, Integer orden);
	
	/**
     * 
     * Método getSeccionesFiltro
     * Obtiene la lista de secciones filtrada por todo el tramo de red
     * @param region
     * @param subregion
     * @param subEstacion
     * @param circuito
     * @return lista
     */
	@SuppressWarnings("rawtypes")
    public List getSeccionesFiltro(Integer region, Integer subregion, Integer subEstacion, Integer circuito);
	
	/**
	 * Determina la existencia de una Seccion
	 * @param seccion Seccion a determina su existencia
	 * @return true o false
	 */
	public boolean existe(Seccion seccion);
	
	/**
	 * Determina la cantidad de secciones existentes pertenecientes a la subEstacion y circuito dados
	 * @param subEstacion SubEstacion dada como filtro
	 * @param circuito Circuito dado como filtro
	 * @return Retorna el contador de secciones pertenecientes a la subEstacion y circuito dados
	 */
	public long getSeccionesEscalar(Integer subEstacion, Integer circuito);
	
	/**
	 * Método que ejecuta el procedimiento almacenado de cálculo de índices globales por sección
	 * @param ano Año a partir del cual se desea realizar el cálculo de índices
	 * @param mes Mes a partir del cual se desea realizar el cálculo de índices
	 * @return S o N de acuerdo a la correcta ejecución o no del procedimiento almacenado
	 */
	public String ejecutarIndicesGlobales(Integer ano, Integer mes);	
	
	/**
	 * Método que ejecuta el procedimiento almacenado de cálculo de índices de causa por sección
	 * @param ano Año a partir del cual se desea realizar el cálculo de índices
	 * @param mes Mes a partir del cual se desea realizar el cálculo de índices
	 * @return S o N de acuerdo a la correcta ejecución o no del procedimiento almacenado
	 */
    public String ejecutarIndicesCausa(Integer ano, Integer mes);	
    
	/**
	 * Método que ejecuta el procedimiento almacenado de cálculo de índices trifásicos por sección
	 * @param ano Año a partir del cual se desea realizar el cálculo de índices
	 * @param mes Mes a partir del cual se desea realizar el cálculo de índices
	 * @return S o N de acuerdo a la correcta ejecución o no del procedimiento almacenado
	 */
    public String ejecutarIndicesTrifasicos(Integer ano, Integer mes);   
    
	/**
	 * Retorna la cantidad de registros asociados en las interrupciones mayores y menores a cinco minutos
	 * @param seccionID que indica el seccionID
	 * @return cantidad de registros asociados
	 */
	public Long registrosAsociados(SeccionID seccionID);    
    
    /**
     * Obtiene las secciones de acuerdo a una oficina
     * @param codigoOficina Oficina deseada
     * @return Lista de secciones de acuerdo a una oficina
     */
    public List getSeccionesPorOficina(Integer codigoOficina);
    
    public List getSeccionesDestino(Integer subEstacion, Integer circuito);
    
    public List getSeccionesDestino(Integer region, Integer subregion, Integer subEstacion, Integer circuito);
    
    public List getSeccionesOrigen(Integer region, Integer subregion, Integer subEstacion, Integer circuito);
    
    public String cambioSecciones(Seccion seccion, Integer tipo, String cedula);
    
    public Boolean agregarTodo(List<Seccion> cambioSeccion, String cedula);

    public Boolean elimiarSecciones(String cedula);
    
}
