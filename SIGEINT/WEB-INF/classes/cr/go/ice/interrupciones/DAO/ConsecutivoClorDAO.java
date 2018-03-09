
package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.ConsecutivoClor;

/**
 * <p>Interface cr.go.ice.interrupciones.DAO.ConsecutivoClorDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ConsecutivoClorDAO.java</code>Define los metodos de los Datos para ConsecutivoClor.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public interface ConsecutivoClorDAO {

    /**
     * Agrega un consecutivo ya sea para indicar una nueva interrupción - 5 min o una nueva interrupción + 5 min
     * @param consecutivoClor es el nuevo consecutivo
     */
    public void agregar(ConsecutivoClor consecutivoClor);
    
    /**
     * Modifica un consecutivo ya sea para indicar una interrupción - 5 min o una interrupción + 5 min
     * @param consecutivoClor es el consecutivo a modificar
     */
    public void modificar(ConsecutivoClor consecutivoClor);
    
    /**
     * Elimina un consecutivo ya sea para de una interrupción - 5 min o de una interrupción + 5 min
     * @param consecutivoClor es el consecutivo a eliminar
     */
    public void eliminar(ConsecutivoClor consecutivoClor);
    
    /**
     * Busca un consecutivo correspondiente a una interrupción - 5 min o una interrupción + 5 min
     * @param consecutivoClor a buscar
     * @return El consecutivo si lo encontro o null
     */
    public ConsecutivoClor buscar(ConsecutivoClor consecutivoClor);    
    
    /**
     * Retorna una lista de todos los consecutivos existentes en la base de datos
     * @return Lista de consecutivos
     */ 
    public List getConsecutivosClor();
    
    /**
     * Retorna un consecutivo de una interrupcion + 5 min de acuerdo a una oficina dada
     * @param codigoOficina código de la oficina para obtener el consecutivo correspondiente
     * @param ano año para obtener consecutivo correspondiente
     * @return el consecutivo correspondiente
     */
    public ConsecutivoClor getConsecutivoClor(Integer codigoOficina, Integer ano);     
    
    /**
     * Determina si un consecutivo de interrupción existe o no en la base de datos
     * @param el consecutivo mediante el cual se determinará su existencia o no en la base de datos
     * @return La existencia o no del consecutivo
     */
    public boolean consecutivoClorExiste(ConsecutivoClor consecutivoClor);
	
}
