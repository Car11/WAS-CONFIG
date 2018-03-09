
package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.Fusible;
import cr.go.ice.interrupciones.domain.FusibleID;

/**
 * <p>Interface cr.go.ice.interrupciones.DAO.FusibleDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>FusibleDAO.java</code>Define los metodos de los Datos para Fusible.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public interface FusibleDAO {

    /**
     * Agrega un fusible
     * @param fusible a agregar
     */
    public void agregar(Fusible fusible);
    
    /**
     * Agrega una lista de fusibles
     * @param fusibles lista de fusibles a agregar
     */
    public void agregar(List fusibles);
    
    /**
     * Modifica un fusible
     * @param fusible a modificar
     */
    public void modificar(Fusible fusible);
    
    /**
     * Elimina un fusible
     * @param fusible a eliminar
     */
    public void eliminar(Fusible fusible);
    
    /**
     * Busca un fusible indicado por su fusibleID
     * @param fusible a buscar
     * @return Fusible si se encuentra o null sino se encuentra
     */
    public Fusible buscar(Fusible fusible);    
    
    /**
     * Retorna una lista de todos los fusibles
     * @return Lista de Fusibles
     */
    public List getFusibles();
    
    /**
     * Retorna una lista de fusibles correspondientes a un fusibleID
     * @param fusibleID indica cuales son los fusibles a obtener
     * @return Lista Fusibles obtenidos
     */
    public List getFusibles(FusibleID fusibleID);
    
    /**
     * Modifica una lista de fusibles
     * @param fusible es la lista de los nuevos fusibles
     * @param fusibleID determina la lista de fusibles a ser modificados
     */
    public void modificar(List fusible, FusibleID fusibleID);    
    
    /**
     * Determina la existencia o no de un fusible
     * @param fusible que indica el fusibleID a determinar su existencia
     * @return  La existencia o no del fusible
     */
    public boolean fusibleExiste(Fusible fusible);
	
}
