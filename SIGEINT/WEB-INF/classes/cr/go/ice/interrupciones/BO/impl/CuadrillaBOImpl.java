package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.CuadrillaBO;
import cr.go.ice.interrupciones.DAO.CuadrillaDAO;
import cr.go.ice.interrupciones.domain.Cuadrilla;
import cr.go.ice.interrupciones.domain.CuadrillaID;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.CuadrillaBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>CuadrillaBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para Cuadrilla.</p>
 * <p>Fecha creación: 20/02/2007</p>
 * <p>Ultima actualización: 20/02/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class CuadrillaBOImpl implements CuadrillaBO{

    /**
     * <code>cuadrillaDAO</code> cuadrilla Dao
     */
    private CuadrillaDAO cuadrillaDAO;
    
    /**
     * Constructor.
     */
    public CuadrillaBOImpl(){
        
    }
	
	/**
	 * Asigna CuadrillaDAO
	 * @param cuadrillaDAO El cuadrillaDAO a establecer.
	 */
	public void setCuadrillaDAO(CuadrillaDAO cuadrillaDAO) {
		this.cuadrillaDAO = cuadrillaDAO;
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.CuadrillaBO#agregar(cr.go.ice.interrupciones.domain.Cuadrilla)
	 */
	public void agregar(Cuadrilla cuadrilla) {
		this.cuadrillaDAO.agregar(cuadrilla);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.CuadrillaBO#modificar(cr.go.ice.interrupciones.domain.Cuadrilla)
	 */
	public void modificar(Cuadrilla cuadrilla) {
		this.cuadrillaDAO.modificar(cuadrilla);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.CuadrillaBO#eliminar(cr.go.ice.interrupciones.domain.Cuadrilla)
	 */
	public void eliminar(Cuadrilla cuadrilla) {
		this.cuadrillaDAO.eliminar(cuadrilla);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.CuadrillaBO#buscar(cr.go.ice.interrupciones.domain.Cuadrilla)
	 */
	public Cuadrilla buscar(Cuadrilla cuadrilla) {
		return this.cuadrillaDAO.buscar(cuadrilla);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.CuadrillaBO#getCuadrillas()
	 */
	public List getCuadrillas() {
		return this.cuadrillaDAO.getCuadrillas();
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.CuadrillaBO#cuadrillaExiste(cr.go.ice.interrupciones.domain.Cuadrilla)
	 */
	public boolean cuadrillaExiste(Cuadrilla cuadrilla) {
		return this.cuadrillaDAO.cuadrillaExiste(cuadrilla);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.CuadrillaBO#agregar(java.util.List)
	 */
	public void agregar(List cuadrilla) {
		this.cuadrillaDAO.agregar(cuadrilla);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.CuadrillaBO#getCuadrillas(cr.go.ice.interrupciones.domain.CuadrillaID)
	 */   
	public List getCuadrillas(CuadrillaID cuadrillaID) {
		return this.cuadrillaDAO.getCuadrillas(cuadrillaID);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.CuadrillaBO#modificar(java.util.List, cr.go.ice.interrupciones.domain.CuadrillaID)
	 */
	public void modificar(List cuadrilla, CuadrillaID cuadrillaID) {
		this.cuadrillaDAO.modificar(cuadrilla, cuadrillaID);
	}
}
