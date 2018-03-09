
package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.InterrupcionGemela;
import cr.go.ice.interrupciones.domain.InterrupcionGemelaID;

/**
 * <p>Interface cr.go.ice.interrupciones.DAO.InterrupcionGemelaDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>InterrupcionGemelaDAO.java</code>Define los metodos de los Datos para InterrupcionGemela.</p>
 * <p>Fecha creaci�n: 26/01/2007</p>
 * <p>Ultima actualizaci�n: 26/01/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public interface InterrupcionGemelaDAO {
   
    /**
     * Agrega una interrupcion gemela
     * @param interrupcionGemela gemela a agregar
     */
	public void agregar(InterrupcionGemela interrupcionGemela);
    
	/**
	 * Agrega una lista de interrupciones gemelas a la base de datos
	 * @param interrupcionesGemelas lista de las interrupciones gemelas
	 */
	public void agregar(List interrupcionesGemelas);
	
    /**
     * Modifica una interrupcion gemela
     * @param interrupcionGemela gemela a modificar
     */
    public void modificar(InterrupcionGemela interrupcionGemela);
    
    /**
     * Elimina una interrupcion gemela
     * @param interrupcionGemela gemela a eliminar
     */
    public void eliminar(InterrupcionGemela interrupcionGemela);
    
    /**
     * Retorna una interrupci�n gemela espec�fica
     * @param interrupcionGemela indica cual es la interrupci�n deseada mediante interrupcionGemelaID
     * @return InterrupcionGemela que se desea o null
     */
    public InterrupcionGemela buscar(InterrupcionGemela interrupcionGemela);    
    
    /**
     * Retorna la lista de interrupciones gemelas almacenadas en la base de datos
     * @return Lista de Interrupciones Gemelas
     */
    public List getInterrupcionesGemelas();
    
    /**
     * 
     * Retorna una lista de interrupciones gemelas
     * @param interrupcionGemelaID indica las interrupciones gemelas deseadas
     * @return Lista de interrupciones gemelas deseadas
     */
    public List getInterrupcionesGemelas(InterrupcionGemelaID interrupcionGemelaID);   
    
    /**
     * Modifica una lista de interrupciones gemelas de la base de datos
     * @param interrupcionGemela lista de interrupciones gemelas a modificar
     * @param interrupcionGemelaID indica las interrupciones gemelas de la base de datos que ser�n modificadas
     */   
    public void modificar(List interrupcionGemela, InterrupcionGemelaID interrupcionGemelaID);    
    
    /**
     * Determina la existencia o no de una interrupci�n gemelas
     * @param interrupcionGemela indica mediante interrupcionGemelaID, la interrupci�n gemela por determinar su existencia
     * @return La existencia o no de la interrucpi�n gemela
     */
    public boolean interrupcionGemelaExiste(InterrupcionGemela interrupcionGemela);
    	   
    
}
