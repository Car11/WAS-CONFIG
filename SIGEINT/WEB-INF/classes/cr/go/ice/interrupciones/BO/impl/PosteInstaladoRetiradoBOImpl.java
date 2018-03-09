
package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.PosteInstaladoRetiradoBO;
import cr.go.ice.interrupciones.DAO.PosteInstaladoRetiradoDAO;
import cr.go.ice.interrupciones.domain.PosteInstaladoRetirado;
import cr.go.ice.interrupciones.domain.PosteInstaladoRetiradoID;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.PosteInstaladoRetiradoBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>PosteInstaladoRetiradoBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para PosteInstaladoRetirado.</p>
 * <p>Fecha creación: 20/02/2007</p>
 * <p>Ultima actualización: 20/02/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class PosteInstaladoRetiradoBOImpl implements PosteInstaladoRetiradoBO{

	/**
	 * <code>posteInstaladoRetiradoDAO</code> Poste Instalado Retirado DAO
	 */
	private PosteInstaladoRetiradoDAO posteInstaladoRetiradoDAO;
	
	/**
	 * Constructor
	 */
	public PosteInstaladoRetiradoBOImpl(){
		
	}
	
	/**
	 * Asigna PosteInstaladoRetiradoDAO
	 * @param posteInstaladoRetiradoDAO
	 */
	public void setPosteInstaladoRetiradoDAO(
			PosteInstaladoRetiradoDAO posteInstaladoRetiradoDAO) {
		this.posteInstaladoRetiradoDAO = posteInstaladoRetiradoDAO;
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.PosteInstaladoRetiradoBO#agregar(cr.go.ice.interrupciones.domain.PosteInstaladoRetirado)
	 */
	public void agregar(PosteInstaladoRetirado posteInstaladoRetirado) {
		this.posteInstaladoRetiradoDAO.agregar(posteInstaladoRetirado);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.PosteInstaladoRetiradoBO#modificar(cr.go.ice.interrupciones.domain.PosteInstaladoRetirado)
	 */
	public void modificar(PosteInstaladoRetirado posteInstaladoRetirado) {
		this.posteInstaladoRetiradoDAO.modificar(posteInstaladoRetirado);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.PosteInstaladoRetiradoBO#eliminar(cr.go.ice.interrupciones.domain.PosteInstaladoRetirado)
	 */
	public void eliminar(PosteInstaladoRetirado posteInstaladoRetirado) {
		this.posteInstaladoRetiradoDAO.eliminar(posteInstaladoRetirado);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.PosteInstaladoRetiradoBO#buscar(cr.go.ice.interrupciones.domain.PosteInstaladoRetirado)
	 */
	public PosteInstaladoRetirado buscar(PosteInstaladoRetirado posteInstaladoRetirado) {
		return this.posteInstaladoRetiradoDAO.buscar(posteInstaladoRetirado);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.PosteInstaladoRetiradoBO#getPosteInstaladoRetirado()
	 */
	public List getPosteInstaladoRetirado() {
		return this.posteInstaladoRetiradoDAO.getPosteInstaladoRetirado();
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.PosteInstaladoRetiradoBO#posteInstaladoRetiradoExiste(cr.go.ice.interrupciones.domain.PosteInstaladoRetirado)
	 */
	public boolean posteInstaladoRetiradoExiste(PosteInstaladoRetirado posteInstaladoRetirado) {
		return this.posteInstaladoRetiradoDAO.posteInstaladoRetiradoExiste(posteInstaladoRetirado);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.PosteInstaladoRetiradoBO#agregar(java.util.List)
	 */
	public void agregar(List postesInstaladoRetirado) {
		this.posteInstaladoRetiradoDAO.agregar(postesInstaladoRetirado);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.PosteInstaladoRetiradoBO#getPosteInstaladoRetirado(cr.go.ice.interrupciones.domain.PosteInstaladoRetiradoID)
	 */
	public List getPosteInstaladoRetirado(PosteInstaladoRetiradoID posteInstaladoRetiradoID) {
		return this.posteInstaladoRetiradoDAO.getPosteInstaladoRetirado(posteInstaladoRetiradoID);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.PosteInstaladoRetiradoBO#modificar(java.util.List, cr.go.ice.interrupciones.domain.PosteInstaladoRetiradoID)
	 */
	public void modificar(List posteInstaladoRetirado, PosteInstaladoRetiradoID posteInstaladoRetiradoID) {
		this.posteInstaladoRetiradoDAO.modificar(posteInstaladoRetirado, posteInstaladoRetiradoID);
	}
}
