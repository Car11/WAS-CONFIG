package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.NoPropiaSeccionamiento;
import cr.go.ice.interrupciones.domain.NoPropiaSeccionamientoID;

/**
 * <p>Interface cr.go.ice.interrupciones.DAO.NoPropiaSeccionamientoDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>NoPropiaSeccionamientoDAO.java</code>Define los metodos de los Datos para NoPropiaSeccionamiento.</p>
 * <p>Fecha creaci�n: 11/04/2007</p>
 * <p>Ultima actualizaci�n: 11/04/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public interface NoPropiaSeccionamientoDAO {

	/**
	 * Agrega un noPropiaSeccionamiento
	 * @param noPropiaSeccionamiento a agregar
	 */
	public void agregar(NoPropiaSeccionamiento noPropiaSeccionamiento);
    
	/**
	 * Agrega una lista de noPropiaSeccionamiento
	 * @param noPropiaSeccionamientos lista a agregar
	 */
	public void agregar(List noPropiaSeccionamientos);
	
	/**
	 * Modificar un noPropiaSeccionamiento
	 * @param noPropiaSeccionamiento a modificar
	 */
    public void modificar(NoPropiaSeccionamiento noPropiaSeccionamiento);
    
	/**
	 * Eliminar un noPropiaSeccionamiento
	 * @param noPropiaSeccionamiento a eliminar
	 */
    public void eliminar(NoPropiaSeccionamiento noPropiaSeccionamiento);
    
    /**
     * Retorna un noPropiaSeccionamiento espec�fico
     * @param noPropiaSeccionamiento que espec�fica el noPropiaSeccionamientoID
     * @return NoPropiaSeccionamiento si existe o sino null
     */
    public NoPropiaSeccionamiento buscar(NoPropiaSeccionamiento noPropiaSeccionamiento);    
    
    /**
     * Retorna una lista de todos los noPropiaSeccionamientos
     * @return Lista NoPropiaSeccionamientos de la base de datos
     */
    public List getNoPropiaSeccionamientos();
    
    /**
     * Determina si un noPropiaSeccionamiento existe en la base de datos.
     * @param noPropiaSeccionamiento que espec�fica el noPropiaSeccionamientoID
     * @return La existencia o no de noPropiaSeccionamiento
     */
    public boolean noPropiaSeccionamientoExiste(NoPropiaSeccionamiento noPropiaSeccionamiento);
    
    
    /**
     * Modifica una lista de noPropiaSeccionamientos de la base de datos
     * @param noPropiaSeccionamiento lista de los noPropiaSeccionamientos modificados
     * @param noPropiaSeccionamientoID indica la lista de noPropiaSeccionamientos a ser modificados
     */
    public void modificar(List noPropiaSeccionamiento, NoPropiaSeccionamientoID noPropiaSeccionamientoID);        
    
    /**
     * Retorna una lista de noPropiaSeccionamientos
     * @param noPropiaSeccionamientoID indica los noPropiaSeccionamientos deseados
     * @return la lista de noPropiaSeccionamientos deseados
     */
    public List getNoPropiaSeccionamientos(NoPropiaSeccionamientoID noPropiaSeccionamientoID);

	/**
	 * M�todo que ejecuta el procedimiento almacenado de c�lculo de �ndices no propios
	 * @param ano A�o a partir del cual se desea realizar el c�lculo de �ndices
	 * @param mes Mes a partir del cual se desea realizar el c�lculo de �ndices
	 * @return S o N de acuerdo a la correcta ejecuci�n o no del procedimiento almacenado
	 */
    public String ejecutarIndicesNoPropios(Integer ano, Integer mes);  
}
