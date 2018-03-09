package cr.go.ice.interrupciones.BO;

import java.util.List;

import cr.go.ice.interrupciones.domain.UsuarioOficina;

/**
 * <p>Interface cr.go.ice.interrupciones.BO.UsuarioOficinaBO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>UsuarioOficinaBO.java</code>Define los Metodos de Logica y Negocio de las Clases para UsuarioOficinaBO.</p>
 * <p>Fecha creación: 14/06/2017</p>
 * <p>Ultima actualización: 14/06/2017</p>
 * @author Rossmon (rhidalgo)
 * @version 1.1
 */
public interface UsuarioOficinaBO
{
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
	
	
	/**
     * Busca toos los UsuarioOficina
     * @return List<UsuarioOficina> y null si no esta
     */
	public List<UsuarioOficina> buscarTodos();
	   
    /**
     * Determina la existencia de un UsuarioOficina
     * @return true o false
     */
	public boolean existe(UsuarioOficina usuarioOficina);
	
	public boolean existeCedula(Integer cedula);
}
