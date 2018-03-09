package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.GlosarioBO;
import cr.go.ice.interrupciones.DAO.GlosarioDAO;
import cr.go.ice.interrupciones.domain.Glosario;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.GlosarioBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>GlosarioBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para Glosario.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (Cesar y Doc Cristian)
 * @version 1.1
 */
public class GlosarioBOImpl implements GlosarioBO {
	
	/**
	 * <code>glosarioDAO</code> Glosario Dao
	 */
	private GlosarioDAO glosarioDAO;
	
	

	/**
	 * Asigna GlosarioDAO
	 * @param glosarioDAO The glosarioDAO to set.
	 */
	public void setGlosarioDAO(GlosarioDAO glosarioDAO) {
		this.glosarioDAO = glosarioDAO;
	}
    
	/**
	 * @see cr.go.ice.interrupciones.BO.GlosarioBO#agregar(cr.go.ice.interrupciones.domain.Glosario)
	 */
	public void agregar(Glosario glosario) {		
		 this.glosarioDAO.agregar(glosario);		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.GlosarioBO#modificar(cr.go.ice.interrupciones.domain.Glosario)
	 */
	public void modificar(Glosario glosario) {
		this.glosarioDAO.modificar(glosario);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.GlosarioBO#eliminar(cr.go.ice.interrupciones.domain.Glosario)
	 */
	public void eliminar(Glosario glosario) {
		this.glosarioDAO.eliminar(glosario);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.GlosarioBO#buscar(java.lang.String)
	 */
	public Glosario buscar(String termino) {
		
		return this.glosarioDAO.buscar(termino);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.GlosarioBO#getGlosarios()
	 */
	public List getGlosarios() {
		 
		return this.glosarioDAO.getGlosarios();
	}
	
	/**
	 * @see cr.go.ice.interrupciones.BO.GlosarioBO#glosarioExiste(java.lang.String)
	 */
	public boolean glosarioExiste(String termino){
        return this.glosarioDAO.glosarioExiste(termino);
    }
	

}
