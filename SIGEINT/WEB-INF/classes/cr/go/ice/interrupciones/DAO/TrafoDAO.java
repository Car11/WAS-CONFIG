
package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.Trafo;
import cr.go.ice.interrupciones.domain.TrafoID;

/**
 * <p>Interface cr.go.ice.interrupciones.DAO.TrafoDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>TrafoDAO.java</code>Define los metodos de los Datos para Trafo.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public interface TrafoDAO {
	
	/**
	 * Agrega un trafo
	 * @param trafo a agregar
	 */
	public void agregar(Trafo trafo);
    
    /**
     * Modifica un trafo
     * @param trafo a modificar
     */
    public void modificar(Trafo trafo);
    
    /**
     * Elimina un trafo
     * @param trafo a eliminar
     */
    public void eliminar(Trafo trafo);
    
    /**
     * Retorna un trafo deseado
     * @param trafoID específica el trafo que se desea obtener
     * @return Trafo si se encontro, sino null
     */
    public Trafo getTrafo(TrafoID trafoID);    
    
    /**
     * Retorna la lista de trafos existentes
     * @return Lista de trafos
     */
    public List getTrafos();        
    
    /**
     * Retorna una sublista de trafos deseados
     * @param trafoID específica los parámetros de obtención de la sublista de trafos
     * @return Lista Trafos especcificados por trafoID
     */
    public List getTrafos(TrafoID trafoID);  
    
    /**
     * Determina la existencia de un trafo
     * @param trafoID específica el trafo a determinar su existencia
     * @return La existencia o no del trafo
     */
    public boolean trafoExiste(TrafoID trafoID);
    
    /**
     * Modifica una lista de trafos
     * @param trafo lista que especifica los nuevos trafos
     * @param trafoID específica los trafos a ser modificados
     */   
    public void modificar(List trafo,TrafoID trafoID);    
    
	/**
	 * Agrega una lista de trafos
	 * @param trafos lista de trafos a agregar
	 */
	public void agregar(List trafos);
    
}
