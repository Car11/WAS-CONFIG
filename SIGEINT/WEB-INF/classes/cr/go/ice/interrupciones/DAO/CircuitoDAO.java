package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.Circuito;

/**
 * <p>Interface cr.go.ice.interrupciones.DAO.CircuitoDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>CircuitoDAO.java</code>Define los metodos de los Datos para Circuito</p>
 * <p>Fecha creaci�n: 07/01/2007</p>
 * <p>Ultima actualizaci�n: 07/01/2007</p>
 * @author Vista Verde Tecnologia (Mario Leon y Doc Cristian)
 * @version 1.1
 */
public interface CircuitoDAO {
    
    /**
     * Agrega un Circuito
     * @param circuito a agregar
     */
    public void agregar(Circuito circuito);
    
    /**
     * Modifica un Circuito
     * @param circuito a modificar
     */
    public void modificar(Circuito circuito);
    
    /**
     * Elimina un Circuito
     * @param circuito a eliminar
     */
    public void eliminar(Circuito circuito);
    
    /**
     * Busca un Circuito
     * @param subEstacion Subestacion de filtro
     * @param circuito Circuito de filtro
     * @return Circuito o null
     */
    public Circuito buscar(Integer subEstacion, Integer circuito);
    
    /**
     * Lista de circuitos filtrados por subEstacion
     * @param subEstacion SubEstacion deseada como filtro
     * @return Lista de circuitos filtrados por subEstacion
     */
    public List buscar(Integer subEstacion);
    
    /**
     * Lista de circuitos
     * @return Lista de Circuitos 
     */
    public List getCircuitos();
        
    /**
     * Lista de circuitos ordenados por codigo
     * @return Lista de circuitos ordenados por codigo
     */
    public List getCircuitosOrdenPorCodigo();    
    
    /**
     * Lista de circuitos filtrados por subestacion
     * @param subEstacion SubEstacion deseada como filtro
     * @return Lista de Circuitos
     */
    public List getCircuitos(Integer subEstacion);
    
    public List getCircuitosActivas(Integer subEstacion);
    
    
    /**
     * Determina la existencia de un Circuito
     * @param subEstacion Subestacion deseada como filtro
     * @param circuito Circuito deseada como filtro
     * @return true o false
     */
    public boolean circuitoExiste(Integer subEstacion, Integer circuito);

	/**
	 * M�todo que ejecuta el procedimiento almacenado de c�lculo de �ndices globales por circuito
	 * @param ano A�o a partir del cual se desea realizar el c�lculo de �ndices
	 * @param mes Mes a partir del cual se desea realizar el c�lculo de �ndices
	 * @return S o N de acuerdo a la correcta ejecuci�n o no del procedimiento almacenado
	 */
	public String ejecutarIndicesGlobales(Integer ano, Integer mes);
	
	/**
	 * M�todo que ejecuta el procedimiento almacenado de c�lculo de �ndices de causa por circuito
	 * @param ano A�o a partir del cual se desea realizar el c�lculo de �ndices
	 * @param mes Mes a partir del cual se desea realizar el c�lculo de �ndices
	 * @return S o N de acuerdo a la correcta ejecuci�n o no del procedimiento almacenado
	 */
    public String ejecutarIndicesCausa(Integer ano, Integer mes);
    
	/**
	 * M�todo que ejecuta el procedimiento almacenado de c�lculo de �ndices trif�sicos por circuito
	 * @param ano A�o a partir del cual se desea realizar el c�lculo de �ndices
	 * @param mes Mes a partir del cual se desea realizar el c�lculo de �ndices
	 * @return S o N de acuerdo a la correcta ejecuci�n o no del procedimiento almacenado
	 */
    public String ejecutarIndicesTrifasicos(Integer ano, Integer mes);   
	
    /**
     * Obtiene una lista de circuitos pertenecientes a una oficina
     * @param codigoOficina Oficina deseada
     * @return Lista de circuitos deseados
     */
    public List getCircuitosPorOficina(Integer codigoOficina);
    
    /**
     * Determina el nombre del tipo del circuito
     * @param tipo Tipo deseado como filtro
     * @return Nombre del tipo del circuito
     */
    public String getNombreTipo(Integer tipo);
}