
package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.PosteInstaladoRetirado;
import cr.go.ice.interrupciones.domain.PosteInstaladoRetiradoID;

/**
 * <p>Interface cr.go.ice.interrupciones.DAO.PosteInstaladoRetiradoDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>PosteInstaladoRetiradoDAO.java</code>Define los Metodos de Logica y Negocio de las Clases para PosteInstaladoRetirado.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public interface PosteInstaladoRetiradoDAO {

    /**
     * Agrega un posteInstaladoRetirado
     * @param posteInstaladoRetirado a agregar
     */
    public void agregar(PosteInstaladoRetirado posteInstaladoRetirado);
    
    /**
     * Agrega una lista de posteInstaladoRetirados
     * @param postesInstaladoRetirado lista de posteInstaladoRetirado a agregar
     */
    public void agregar(List postesInstaladoRetirado);
    
    /**
     * Modifica un posteInstaladoRetirado
     * @param posteInstaladoRetirado a modificar
     */
    public void modificar(PosteInstaladoRetirado posteInstaladoRetirado);
    
    /**
     * Elimina un posteInstaladoRetirado
     * @param posteInstaladoRetirado a eliminar
     */
    public void eliminar(PosteInstaladoRetirado posteInstaladoRetirado);
    
    /**
     * Busca un posteInstaladoRetirado deseada
     * @param posteInstaladoRetirado que indica PosteInstaladoRetiradoID a buscar
     * @return PosteInstaladoRetirado si se encuentra sino null
     */
    public PosteInstaladoRetirado buscar(PosteInstaladoRetirado posteInstaladoRetirado);    
    
    /**
     * Retorna una lista de posteInstaladoRetirados almacenados en la base de datos
     * @return Lista de Postes Instalados Retirados
     */
    public List getPosteInstaladoRetirado();
    
    /**
     * Retorna lista de PosteInstaladoRetirados
     * @param posteInstaladoRetiradoID indica los postes deseados
     * @return Lista de posteInstaladoRetirados deseados
     */
    public List getPosteInstaladoRetirado(PosteInstaladoRetiradoID posteInstaladoRetiradoID);    
    
    /**
     * Modifica una lista de posteInstaladoRetirados de la base de datos
     * @param posteInstaladoRetirado lista de los nuevos posteInstaladoRetirados
     * @param posteInstaladoRetiradoID indica los posteInstaladoRetirados de la base de datos que serán modificadas
     */
    public void modificar(List posteInstaladoRetirado, PosteInstaladoRetiradoID posteInstaladoRetiradoID);  
    
    /**
     * Determina la existencia o no de un posteInstaladoRetirado
     * @param posteInstaladoRetirado indica mediante posteInstaladoRetiradoID, el posteInstaladoRetirado por determinar su existencia
     * @returnLa existencia o no del posteInstaladoRetirado
     */
    public boolean posteInstaladoRetiradoExiste(PosteInstaladoRetirado posteInstaladoRetirado);
	
}
