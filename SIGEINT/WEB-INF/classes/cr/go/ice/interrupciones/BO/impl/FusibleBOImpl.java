
package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.FusibleBO;
import cr.go.ice.interrupciones.DAO.FusibleDAO;
import cr.go.ice.interrupciones.domain.Fusible;
import cr.go.ice.interrupciones.domain.FusibleID;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.FusibleBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>FusibleBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para Fusible.</p>
 * <p>Fecha creación: 20/02/2007</p>
 * <p>Ultima actualización: 20/02/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class FusibleBOImpl implements FusibleBO{

	/**
	 * <code>fusibleDAO</code> Fusible DAO
	 */
	private  FusibleDAO fusibleDAO;
	
	/**
	 * Constructor
	 */
	public FusibleBOImpl(){
		
	}
    
	/**
	 * Asigna FusibleDAO
	 * @param fusibleDAO El fusibleDAO a establecer.
	 */
	public void setFusibleDAO(FusibleDAO fusibleDAO) {
		this.fusibleDAO = fusibleDAO;
	}
	/**
	 * @see cr.go.ice.interrupciones.BO.FusibleBO#agregar(cr.go.ice.interrupciones.domain.Fusible)
	 */
	public void agregar(Fusible fusible) {
		this.fusibleDAO.agregar(fusible);
		
	}
	/**
	 * @see cr.go.ice.interrupciones.BO.FusibleBO#modificar(cr.go.ice.interrupciones.domain.Fusible)
	 */
	public void modificar(Fusible fusible) {
		this.fusibleDAO.modificar(fusible);
		
	}
	/**
	 * @see cr.go.ice.interrupciones.BO.FusibleBO#eliminar(cr.go.ice.interrupciones.domain.Fusible)
	 */
	public void eliminar(Fusible fusible) {
		this.fusibleDAO.eliminar(fusible);
		
	}
	/**
	 * @see cr.go.ice.interrupciones.BO.FusibleBO#buscar(cr.go.ice.interrupciones.domain.Fusible)
	 */
	public Fusible buscar(Fusible fusible) {
		return this.fusibleDAO.buscar(fusible);
	}
    
	/**
	 * @see cr.go.ice.interrupciones.BO.FusibleBO#getFusibles()
	 */
	public List getFusibles() {
		return this.fusibleDAO.getFusibles();
	}
    
	/**
	 * @see cr.go.ice.interrupciones.BO.FusibleBO#fusibleExiste(cr.go.ice.interrupciones.domain.Fusible)
	 */
	public boolean fusibleExiste(Fusible fusible) {
		return this.fusibleDAO.fusibleExiste(fusible);
	}
    
	/**
	 * @see cr.go.ice.interrupciones.BO.FusibleBO#agregar(java.util.List)
	 */
	public void agregar(List fusibles) {
		this.fusibleDAO.agregar(fusibles);
	}
    
	/**
	 * @see cr.go.ice.interrupciones.BO.FusibleBO#getFusibles(cr.go.ice.interrupciones.domain.FusibleID)
	 */
	public List getFusibles(FusibleID fusibleID) {
		return this.fusibleDAO.getFusibles(fusibleID);
	}
    
	/**
	 * @see cr.go.ice.interrupciones.BO.FusibleBO#modificar(java.util.List, cr.go.ice.interrupciones.domain.FusibleID)
	 */
	public void modificar(List fusible, FusibleID fusibleID) {
		this.fusibleDAO.modificar(fusible, fusibleID);
	}
}
