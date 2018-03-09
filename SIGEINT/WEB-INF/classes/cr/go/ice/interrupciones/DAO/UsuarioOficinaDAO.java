package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.UsuarioOficina;

/**

 * <p>Interface cr.go.ice.interrupciones.DAO.UsuarioOficinaDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>UsuarioOficinaDAO.java</code> Define los metodos de los Datos para Animal</p>
 * <p>Fecha creaci�n: 14/06/2017</p>
 * <p>Ultima actualizaci�n: 14/06/2017</p>
 * @author Rossmon (rhidalgo)
 * @version 1.1
 */
public interface UsuarioOficinaDAO {
	
    /**
     * Agrega un UsuarioOficina
     * @param UsuarioOficina a agregar
     */
	public void agregar(UsuarioOficina usuarioOficina);
	    
    /**
     * Modifica un UsuarioOficina
     * @param UsuarioOficina a modificar
     */
	public void modificar(UsuarioOficina usuarioOficina);
	    
    /**
     * Elimina un UsuarioOficina
     * @param UsuarioOficina a eliminar
     */
	public void eliminar(UsuarioOficina usuarioOficina);
	
	public void eliminarPorCedula(Integer cedula);
	    
    /**
     * Busca un UsuarioOficina
     * @return UsuarioOficina y null si no esta
     */
	public UsuarioOficina buscar(Long cedula, Integer codigoOficina);
	
	public List<UsuarioOficina> buscarCedula(Long cedula);
	
	public List<UsuarioOficina> buscarOficina(Integer codigoOficina);
	
	public List<UsuarioOficina> buscarOficinaEmpleado(Integer codigoOficina);
	
	public List<UsuarioOficina> buscarNombre(String nombre);
	
	public List<UsuarioOficina> buscarCedulaNombreOficina(Long cedula, String nombre, Integer codigoOficina);
	
	public List<UsuarioOficina> buscarTodos();
	   
    /**
     * Determina la existencia de un UsuarioOficina
     * @return true o false
     */
	public boolean existe(UsuarioOficina usuarioOficina);
	
	public boolean existeCedula(Integer cedula);	
}
