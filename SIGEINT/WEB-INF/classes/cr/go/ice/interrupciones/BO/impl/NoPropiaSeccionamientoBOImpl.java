package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.NoPropiaSeccionamientoBO;
import cr.go.ice.interrupciones.DAO.NoPropiaSeccionamientoDAO;
import cr.go.ice.interrupciones.domain.NoPropiaSeccionamiento;
import cr.go.ice.interrupciones.domain.NoPropiaSeccionamientoID;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.NoPropiaSeccionamientoBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>NoPropiaSeccionamientoBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para NoPropiaSeccionamiento.</p>
 * <p>Fecha creación: 11/04/2007</p>
 * <p>Ultima actualización: 11/04/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class NoPropiaSeccionamientoBOImpl implements NoPropiaSeccionamientoBO{

    /**
     * <code>noPropiaSeccionamientoDAO</code> noPropiaSeccionamiento Dao
     */
    private NoPropiaSeccionamientoDAO noPropiaSeccionamientoDAO;
    
    /**
     * Constructor.
     */
    public NoPropiaSeccionamientoBOImpl(){
        
    }

	/**
	 * Asigna noPropiaSeccionamientoDAO.
	 * @param noPropiaSeccionamientoDAO El noPropiaSeccionamientoDAO a modificar.
	 */
	public void setNoPropiaSeccionamientoDAO(
			NoPropiaSeccionamientoDAO noPropiaSeccionamientoDAO) {
		this.noPropiaSeccionamientoDAO = noPropiaSeccionamientoDAO;
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.NoPropiaSeccionamientoBO#agregar(cr.go.ice.interrupciones.domain.NoPropiaSeccionamiento)
	 */
	public void agregar(NoPropiaSeccionamiento noPropiaSeccionamiento) {
		this.noPropiaSeccionamientoDAO.agregar(noPropiaSeccionamiento);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.NoPropiaSeccionamientoBO#agregar(java.util.List)
	 */
	public void agregar(List noPropiaSeccionamientos) {
		this.noPropiaSeccionamientoDAO.agregar(noPropiaSeccionamientos);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.NoPropiaSeccionamientoBO#modificar(cr.go.ice.interrupciones.domain.NoPropiaSeccionamiento)
	 */
	public void modificar(NoPropiaSeccionamiento noPropiaSeccionamiento) {
		this.noPropiaSeccionamientoDAO.modificar(noPropiaSeccionamiento);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.NoPropiaSeccionamientoBO#eliminar(cr.go.ice.interrupciones.domain.NoPropiaSeccionamiento)
	 */
	public void eliminar(NoPropiaSeccionamiento noPropiaSeccionamiento) {
		this.noPropiaSeccionamientoDAO.eliminar(noPropiaSeccionamiento);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.NoPropiaSeccionamientoBO#buscar(cr.go.ice.interrupciones.domain.NoPropiaSeccionamiento)
	 */
	public NoPropiaSeccionamiento buscar(NoPropiaSeccionamiento noPropiaSeccionamiento) {
		return this.noPropiaSeccionamientoDAO.buscar(noPropiaSeccionamiento);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.NoPropiaSeccionamientoBO#getNoPropiaSeccionamientos()
	 */
	public List getNoPropiaSeccionamientos() {
		return this.noPropiaSeccionamientoDAO.getNoPropiaSeccionamientos();
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.NoPropiaSeccionamientoBO#noPropiaSeccionamientoExiste(cr.go.ice.interrupciones.domain.NoPropiaSeccionamiento)
	 */
	public boolean noPropiaSeccionamientoExiste(NoPropiaSeccionamiento noPropiaSeccionamiento) {
		return this.noPropiaSeccionamientoDAO.noPropiaSeccionamientoExiste(noPropiaSeccionamiento);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.NoPropiaSeccionamientoBO#modificar(java.util.List, cr.go.ice.interrupciones.domain.NoPropiaSeccionamientoID)
	 */
	public void modificar(List noPropiaSeccionamiento, NoPropiaSeccionamientoID noPropiaSeccionamientoID) {
		this.noPropiaSeccionamientoDAO.modificar(noPropiaSeccionamiento, noPropiaSeccionamientoID);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.NoPropiaSeccionamientoBO#getNoPropiaSeccionamientos(cr.go.ice.interrupciones.domain.NoPropiaSeccionamientoID)
	 */
	public List getNoPropiaSeccionamientos(NoPropiaSeccionamientoID noPropiaSeccionamientoID) {
		return this.noPropiaSeccionamientoDAO.getNoPropiaSeccionamientos(noPropiaSeccionamientoID);
	}

    /**
     * @see cr.go.ice.interrupciones.BO.NoPropiaSeccionamientoBO#ejecutarIndicesNoPropios(java.lang.Integer, java.lang.Integer)
     */
    public String ejecutarIndicesNoPropios(Integer ano, Integer mes) {
        return this.noPropiaSeccionamientoDAO.ejecutarIndicesNoPropios(ano,mes);
    }
}
