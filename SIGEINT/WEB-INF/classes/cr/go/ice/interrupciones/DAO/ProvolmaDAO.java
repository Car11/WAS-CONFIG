
package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.Provolma;
import cr.go.ice.interrupciones.domain.ProvolmaID;


/**
 * <p>Interface cr.go.ice.obras.DAO.ProvolmaDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ProvolmaDAO.java</code>Define los metodos de los Datos para Provolma.</p>
 * <p>Fecha creación: 23/02/2007</p>
 * <p>Ultima actualización: 23/02/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public interface ProvolmaDAO {
	
	/**
	 * Agrega un provolma
	 * @param provolma a agregar
	 */
	public void agregar(Provolma provolma);
	
	/**
	 * Modifica un provolma
	 * @param provolma a modificar
	 */
	public void modificar(Provolma provolma);
	
	/**
	 * Elimina un provolma
	 * @param provolma a eliminar
	 */
	public void eliminar(Provolma provolma);
	
	/**
	 * Retorna la lista de provolmas almacenados en la base de datos
	 * @return Lista de Provolmas
	 */
	public List getProvolmas();
	
	/**
	 * Determina la existencia de un provolma de acuerdo a su provolmaID
	 * @param provolma que indica el provolmaID
	 * @return La existencia o no de un provolma
	 */
	public boolean existe(Provolma provolma);

	/**
	 * Busca la existencia de un provolma de acuerdo a su provolmaID
	 * @param provolmaID que indica el provolmaID
	 * @return Un provolma Objecto o null
	 */
	public Provolma buscar(ProvolmaID provolmaID);
	
	/**
	 * Retorna la cantidad de registros asociados en las interrupciones mayores y menores a cinco minutos
	 * @param provolmaID que indica el provolmaID
	 * @return cantidad de registros asociados
	 */
	public Long registrosAsociados(ProvolmaID provolmaID);
}
