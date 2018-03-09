package cr.go.ice.interrupciones.BO.impl;

import java.util.List;
import cr.go.ice.interrupciones.BO.PuebloBO;
import cr.go.ice.interrupciones.DAO.PuebloDAO;
import cr.go.ice.interrupciones.domain.Pueblo;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.PuebloBOImpl.java</p>
 * <p>Modulo (subsistema): InterrupcionesWeb</p>
 * <p>Descricion de <code>PuebloBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para Pueblo.</p>
 * <p>Fecha creación: 13/04/2007</p>
 * <p>Ultima actualización: 13/04/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class PuebloBOImpl implements PuebloBO{

	/**
	 * <code>puebloDAO</code> Pueblo Dao
	 */
	private PuebloDAO puebloDAO;
	
	/**
	 * Asigna puebloDAO.
	 * @param puebloDAO El puebloDAO a modificar.
	 */
	public void setPuebloDAO(PuebloDAO puebloDAO) {
		this.puebloDAO = puebloDAO;
	}
	
	/**
	 * @see cr.go.ice.interrupciones.BO.PuebloBO#agregar(cr.go.ice.interrupciones.domain.Pueblo)
	 */
	public void agregar(Pueblo pueblo) {
		this.puebloDAO.agregar(pueblo);
	}
	
	/**
	 * @see cr.go.ice.interrupciones.BO.PuebloBO#modificar(cr.go.ice.interrupciones.domain.Pueblo)
	 */
	public void modificar(Pueblo pueblo) {
		this.puebloDAO.modificar(pueblo);
	}
	
	/**
	 * @see cr.go.ice.interrupciones.BO.PuebloBO#eliminar(cr.go.ice.interrupciones.domain.Pueblo)
	 */
	public void eliminar(Pueblo pueblo) {
		this.puebloDAO.eliminar(pueblo);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.PuebloBO#buscar(cr.go.ice.interrupciones.domain.Pueblo)
	 */
	public Pueblo buscar(Pueblo pueblo) {
		return this.puebloDAO.buscar(pueblo);
	}	
	
    /**
     * @see cr.go.ice.interrupciones.BO.PuebloBO#buscar(java.lang.Integer)
     */
    public Pueblo buscar(Integer codigo) {
        return this.puebloDAO.buscar(codigo);
    }
	
	/**
	 * @see cr.go.ice.interrupciones.BO.PuebloBO#getPueblos()
	 */
	public List getPueblos() {
		return this.puebloDAO.getPueblos();
	}
	
	/**
	 * @see cr.go.ice.interrupciones.BO.PuebloBO#puebloExiste(cr.go.ice.interrupciones.domain.Pueblo)
	 */
	public boolean puebloExiste(Pueblo pueblo) {
		return this.puebloDAO.puebloExiste(pueblo);
	}
	
	

    /**
     * @see cr.go.ice.interrupciones.BO.PuebloBO#getPueblos(java.lang.String, java.lang.Integer)
     */
    public List getPueblos(String nombrePueblo, Integer orden) {
        return this.puebloDAO.getPueblos(nombrePueblo, orden);
    }
	/**
	 * @see cr.go.ice.interrupciones.BO.PuebloBO#getPueblos(java.lang.String)
	 */
	public List getPueblos(String nombrePueblo) {
		return this.puebloDAO.getPueblos(nombrePueblo);
	}
}
