package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.ParametroBO;
import cr.go.ice.interrupciones.DAO.ParametroDAO;
import cr.go.ice.interrupciones.domain.Parametro;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.ParametroBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ParametroBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para Parametro.</p>
 * <p>Fecha creación: 20/02/2007</p>
 * <p>Ultima actualización: 20/02/2007</p>
 * @author Vista Verde Soft (Juan Manuel y Doc Cristian)
 * @version 1.1
 */

public class ParametroBOImpl implements ParametroBO{

	/**
	 * <code>parametroDAO</code> Parametro Dao
	 */
	private ParametroDAO parametroDAO;


	/**
	 * Asigna ParametroDAO
	 * @param parametroDAO
	 */
	public void setParametroDAO(ParametroDAO parametroDAO) {
		this.parametroDAO = parametroDAO;
	}
	
	
	/**
	 * @see cr.go.ice.interrupciones.BO.ParametroBO#modificar(cr.go.ice.interrupciones.domain.Parametro, cr.go.ice.interrupciones.domain.Parametro)
	 */
	public void modificar(Parametro parametroNuevo, Parametro parametroViejo) {
		this.parametroDAO.modificar(parametroNuevo,parametroViejo);
	}


	/**
	 * @see cr.go.ice.interrupciones.BO.ParametroBO#getParametros()
	 */
	public List getParametros() {
		return this.parametroDAO.getParametros();
	}
	
}
