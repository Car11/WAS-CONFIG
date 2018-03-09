package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.InterrupcionGemelaBO;
import cr.go.ice.interrupciones.DAO.InterrupcionGemelaDAO;
import cr.go.ice.interrupciones.domain.InterrupcionGemela;
import cr.go.ice.interrupciones.domain.InterrupcionGemelaID;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.InterrupcionGemelaBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>InterrupcionGemelaBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para InterrupcionGemela.</p>
 * <p>Fecha creación: 26/03/2007</p>
 * <p>Ultima actualización: 26/03/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class InterrupcionGemelaBOImpl implements InterrupcionGemelaBO{

	/**
	 * <code>interrupcionGemelaDAO</code> Interrupciones Gemela DAo
	 */
	private InterrupcionGemelaDAO interrupcionGemelaDAO;
	
	/**
	 * Constructor
	 */
	public InterrupcionGemelaBOImpl(){
		
	}	
	
	/**
	 * Asigna InterrupcionGemelaDAO
	 * @param interrupcionGemelaDAO El interrupcionGemelaDAO a establecer.
	 */
	public void setInterrupcionGemelaDAO(
			InterrupcionGemelaDAO interrupcionGemelaDAO) {
		this.interrupcionGemelaDAO = interrupcionGemelaDAO;
	}

	
	/**
	 * @see cr.go.ice.interrupciones.BO.InterrupcionGemelaBO#agregar(cr.go.ice.interrupciones.domain.InterrupcionGemela)
	 */
	public void agregar(InterrupcionGemela interrupcionGemela) {
		this.interrupcionGemelaDAO.agregar(interrupcionGemela);
		
	}


	/**
	 * @see cr.go.ice.interrupciones.BO.InterrupcionGemelaBO#modificar(cr.go.ice.interrupciones.domain.InterrupcionGemela)
	 */
	public void modificar(InterrupcionGemela interrupcionGemela) {
		this.interrupcionGemelaDAO.modificar(interrupcionGemela);
		
	}


	/**
	 * @see cr.go.ice.interrupciones.BO.InterrupcionGemelaBO#eliminar(cr.go.ice.interrupciones.domain.InterrupcionGemela)
	 */
	public void eliminar(InterrupcionGemela interrupcionGemela) {
		this.interrupcionGemelaDAO.eliminar(interrupcionGemela);
		
	}


	/**
	 * @see cr.go.ice.interrupciones.BO.InterrupcionGemelaBO#buscar(cr.go.ice.interrupciones.domain.InterrupcionGemela)
	 */
	public InterrupcionGemela buscar(InterrupcionGemela interrupcionGemela) {
		return this.interrupcionGemelaDAO.buscar(interrupcionGemela);
	}


	/**
	 * @see cr.go.ice.interrupciones.BO.InterrupcionGemelaBO#getInterrupcionesGemelas()
	 */
	public List getInterrupcionesGemelas() {
		return this.interrupcionGemelaDAO.getInterrupcionesGemelas();
	}


	/**
	 * @see cr.go.ice.interrupciones.BO.InterrupcionGemelaBO#interrupcionGemelaExiste(cr.go.ice.interrupciones.domain.InterrupcionGemela)
	 */
	public boolean interrupcionGemelaExiste(InterrupcionGemela interrupcionGemela) {
		return this.interrupcionGemelaDAO.interrupcionGemelaExiste(interrupcionGemela);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.InterrupcionGemelaBO#agregar(java.util.List)
	 */
	public void agregar(List interrupcionesGemelas) {
		this.interrupcionGemelaDAO.agregar(interrupcionesGemelas);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.InterrupcionGemelaBO#getInterrupcionesGemelas(cr.go.ice.interrupciones.domain.InterrupcionGemelaID)
	 */
	public List getInterrupcionesGemelas(InterrupcionGemelaID interrupcionGemelaID) {
		return this.interrupcionGemelaDAO.getInterrupcionesGemelas(interrupcionGemelaID);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.InterrupcionGemelaBO#modificar(java.util.List, cr.go.ice.interrupciones.domain.InterrupcionGemelaID)
	 */
	public void modificar(List interrupcionGemela, InterrupcionGemelaID interrupcionGemelaID) {
		
		this.interrupcionGemelaDAO.modificar(interrupcionGemela, interrupcionGemelaID);
	}







}
