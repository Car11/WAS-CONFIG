
package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.TrafoBO;
import cr.go.ice.interrupciones.DAO.TrafoDAO;
import cr.go.ice.interrupciones.domain.Trafo;
import cr.go.ice.interrupciones.domain.TrafoID;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.TrafoBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>TrafoBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para Trafo.</p>
 * <p>Fecha creación: 20/02/2007</p>
 * <p>Ultima actualización: 20/02/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class TrafoBOImpl implements TrafoBO{

	/**
	 * <code>trafoDAO</code> Trafo Dao
	 */
	private TrafoDAO trafoDAO;
	
	/**
	 * Constructor
	 */
	public TrafoBOImpl(){
		
	}
	

	/**
	 * Asigna TrafoDAO
	 * @param trafoDAO El trafoDAO a establecer.
	 */
	public void setTrafoDAO(TrafoDAO trafoDAO) {
		this.trafoDAO = trafoDAO;
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.TrafoBO#agregar(cr.go.ice.interrupciones.domain.Trafo)
	 */
	public void agregar(Trafo trafo) {
		this.trafoDAO.agregar(trafo);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.TrafoBO#modificar(cr.go.ice.interrupciones.domain.Trafo)
	 */
	public void modificar(Trafo trafo) {
		this.trafoDAO.modificar(trafo);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.TrafoBO#eliminar(cr.go.ice.interrupciones.domain.Trafo)
	 */
	public void eliminar(Trafo trafo) {
		this.trafoDAO.eliminar(trafo);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.TrafoBO#getTrafo(cr.go.ice.interrupciones.domain.TrafoID)
	 */
	public Trafo getTrafo(TrafoID trafoID) {
		return this.trafoDAO.getTrafo(trafoID);		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.TrafoBO#getTrafos()
	 */
	public List getTrafos() {
		return this.trafoDAO.getTrafos();
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.TrafoBO#trafoExiste(cr.go.ice.interrupciones.domain.TrafoID)
	 */
	public boolean trafoExiste(TrafoID trafoID) {
		return this.trafoDAO.trafoExiste(trafoID);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.TrafoBO#agregar(java.util.List)
	 */
	public void agregar(List trafos) {
		this.trafoDAO.agregar(trafos);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.TrafoBO#getTrafos(cr.go.ice.interrupciones.domain.TrafoID)
	 */
	public List getTrafos(TrafoID trafoID) {
		return this.trafoDAO.getTrafos(trafoID);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.TrafoBO#modificar(java.util.List, cr.go.ice.interrupciones.domain.TrafoID)
	 */  
	public void modificar(List trafo, TrafoID trafoID) {
		this.trafoDAO.modificar(trafo,trafoID);
	}
}
