package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.Parametro;

/**
 * <p>Interface cr.go.ice.interrupciones.DAO.ParametroDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ParametroDAO.java</code>Define los metodos de los Datos para Parametro.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Tecnologia (Juan Manuel y Doc Cristian)
 * @version 1.1
 */
public interface ParametroDAO {

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
