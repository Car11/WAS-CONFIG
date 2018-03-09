package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.EquipoEspecialBO;
import cr.go.ice.interrupciones.DAO.EquipoEspecialDAO;
import cr.go.ice.interrupciones.domain.EquipoEspecial;
import cr.go.ice.interrupciones.domain.EquipoEspecialID;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.EquipoEspecialBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>EquipoEspecialBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para EquipoEspecial.</p>
 * <p>Fecha creación: 26/03/2007</p>
 * <p>Ultima actualización: 26/03/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class EquipoEspecialBOImpl implements EquipoEspecialBO{

	/**
	 * <code>equipoEspecialDAO</code> Equipo Especial DAO
	 */
	private EquipoEspecialDAO equipoEspecialDAO;
	
	/**
	 * Constructor
	 */
	public EquipoEspecialBOImpl(){
		
	}
		
	/**
	 * Asigna EquipoEspecialDAO
	 * @param equipoEspecialDAO El equipoEspecialDAO a establecer.
	 */
	public void setEquipoEspecialDAO(EquipoEspecialDAO equipoEspecialDAO) {
		this.equipoEspecialDAO = equipoEspecialDAO;
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.EquipoEspecialBO#agregar(cr.go.ice.interrupciones.domain.EquipoEspecial)
	 */
	public void agregar(EquipoEspecial equipoEspecial) {
		this.equipoEspecialDAO.agregar(equipoEspecial);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.EquipoEspecialBO#modificar(cr.go.ice.interrupciones.domain.EquipoEspecial)
	 */
	public void modificar(EquipoEspecial equipoEspecial) {
		this.equipoEspecialDAO.modificar(equipoEspecial);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.EquipoEspecialBO#eliminar(cr.go.ice.interrupciones.domain.EquipoEspecial)
	 */
	public void eliminar(EquipoEspecial equipoEspecial) {
		this.equipoEspecialDAO.eliminar(equipoEspecial);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.EquipoEspecialBO#buscar(cr.go.ice.interrupciones.domain.EquipoEspecial)
	 */
	public EquipoEspecial buscar(EquipoEspecial equipoEspecial) {
		return this.equipoEspecialDAO.buscar(equipoEspecial);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.EquipoEspecialBO#getEquiposEspeciales()
	 */
	public List getEquiposEspeciales() {
		return this.equipoEspecialDAO.getEquiposEspeciales();
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.EquipoEspecialBO#equipoEspecialExiste(cr.go.ice.interrupciones.domain.EquipoEspecial)
	 */
	public boolean equipoEspecialExiste(EquipoEspecial equipoEspecial) {
		return this.equipoEspecialDAO.equipoEspecialExiste(equipoEspecial);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.EquipoEspecialBO#agregar(java.util.List)
	 */
	public void agregar(List equiposEspeciales) {
		this.equipoEspecialDAO.agregar(equiposEspeciales);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.EquipoEspecialBO#getEquiposEspeciales(cr.go.ice.interrupciones.domain.EquipoEspecialID)
	 */
	public List getEquiposEspeciales(EquipoEspecialID equipoEspecialID) {
		return this.equipoEspecialDAO.getEquiposEspeciales(equipoEspecialID);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.EquipoEspecialBO#modificar(java.util.List, cr.go.ice.interrupciones.domain.EquipoEspecialID)
	 */
	public void modificar(List equipoEspecial, EquipoEspecialID equipoEspecialID) {
		this.equipoEspecialDAO.modificar(equipoEspecial, equipoEspecialID);
		
	}
}
