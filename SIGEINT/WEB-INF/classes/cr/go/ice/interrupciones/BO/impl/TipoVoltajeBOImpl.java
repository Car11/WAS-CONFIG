package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.TipoVoltajeBO;
import cr.go.ice.interrupciones.DAO.TipoVoltajeDAO;
import cr.go.ice.interrupciones.domain.TipoVoltaje;
import cr.go.ice.interrupciones.domain.TipoVoltajeID;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.TipoVoltajeBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>TipoVoltajeBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para TipoVoltaje.</p>
 * <p>Fecha creación: 20/02/2007</p>
 * <p>Ultima actualización: 20/02/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */

public class TipoVoltajeBOImpl implements TipoVoltajeBO {
	
	/**
	 * <code>tipoVoltajeDAO</code> Tipo de Voltaje Dao
	 */
	private TipoVoltajeDAO tipoVoltajeDAO;


	/**
	 * Constructor
	 */
	public TipoVoltajeBOImpl(){
		
	}


	/**
	 * Asigna TipoVoltajeDAO
	 * @param tipoVoltajeDAO
	 */
	public void setTipoVoltajeDAO(TipoVoltajeDAO tipoVoltajeDAO) {
		this.tipoVoltajeDAO = tipoVoltajeDAO;
	}

	

	/**
	 * @see cr.go.ice.interrupciones.BO.TipoVoltajeBO#getTiposVoltaje()
	 */
	public List getTiposVoltaje() {
		return this.tipoVoltajeDAO.getTiposVoltaje();
	}


	/**
	 * @see cr.go.ice.interrupciones.BO.TipoVoltajeBO#agregar(cr.go.ice.interrupciones.domain.TipoVoltaje)
	 */
	public void agregar(TipoVoltaje tipoVoltaje) {
		this.tipoVoltajeDAO.agregar(tipoVoltaje);
	}


	/**
	 * @see cr.go.ice.interrupciones.BO.TipoVoltajeBO#modificar(cr.go.ice.interrupciones.domain.TipoVoltaje)
	 */
	public void modificar(TipoVoltaje tipoVoltaje) {
		this.tipoVoltajeDAO.modificar(tipoVoltaje);
	}


	/**
	 * @see cr.go.ice.interrupciones.BO.TipoVoltajeBO#eliminar(cr.go.ice.interrupciones.domain.TipoVoltaje)
	 */
	public void eliminar(TipoVoltaje tipoVoltaje) {
		this.tipoVoltajeDAO.eliminar(tipoVoltaje);
	}


	/**
	 * @see cr.go.ice.interrupciones.BO.TipoVoltajeBO#buscar(cr.go.ice.interrupciones.domain.TipoVoltajeID)
	 */
	public TipoVoltaje buscar(TipoVoltajeID tipoVoltajeID) {
		TipoVoltaje tipoVoltaje = this.tipoVoltajeDAO.buscar(tipoVoltajeID);
		return tipoVoltaje;
	}


	/**
	 * @see cr.go.ice.interrupciones.BO.TipoVoltajeBO#existe(cr.go.ice.interrupciones.domain.TipoVoltaje)
	 */
	public boolean existe(TipoVoltaje tipoVoltaje) {
		boolean existe = this.tipoVoltajeDAO.existe(tipoVoltaje);
		return existe;
	}


    /**
     * @see cr.go.ice.interrupciones.BO.TipoVoltajeBO#registrosAsociados(cr.go.ice.interrupciones.domain.TipoVoltajeID)
     */
    public Long registrosAsociados(TipoVoltajeID tipoVoltajeID) {
        return this.tipoVoltajeDAO.registrosAsociados(tipoVoltajeID);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.TipoVoltajeBO#getTiposVoltajeOrdenPorTipoVoltaje()
     */
    public List getTiposVoltajeOrdenPorTipoVoltaje() {
        return this.tipoVoltajeDAO.getTiposVoltajeOrdenPorTipoVoltaje();
    }
	
}
