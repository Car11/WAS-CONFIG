package cr.go.ice.interrupciones.BO;

import java.util.List;

import cr.go.ice.interrupciones.domain.Parametro;


/**
 * <p>Interface cr.go.ice.interrupciones.BO.ParametroBO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ParametroBO.java</code>Define los Metodos de Logica y Negocio de las Clases para Parametro.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (Juan Manuel y Doc Cristian)
 * @version 1.1
 */

public interface ParametroBO {
	
	/**
	 * Modifica un Parametro
	 * @param parametro a modificar
	 */
	public void modificar(Parametro parametroNuevo, Parametro parametroViejo);
	
    /**
     * Determina una lista de Parametro
     * @return Lista de Parametros
     */
    public List getParametros();

}
