package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.Correo;

/**
 * <p>Interface cr.go.ice.interrupciones.DAO.CorreoDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>CorreoDAO.java</code> Define los metodos de los Datos para Correo</p>
 * <p>Fecha creación: 19/02/2007</p>
 * <p>Ultima actualización: 19/02/2007</p>
 * @author Vista Verde Tecnologia (Nano y Doc Cristian)
 * @version 1.1
 */
public interface CorreoDAO {
	
    /**
     * Agrega un Correo
     * @param correo a agregar
     */
	public void agregar(Correo correo);
	
    /**
     * Modifica un Correo
     * @param correo a modificar
     */
	public void modificar(Correo correo);
	
    /**
     * Elimina un Correo
     * @param correo a eliminar
     */
	public void eliminar(Correo correo);
	
    /**
     * Buscar un Correo
     * @param direccionCorreo Correo de filtro
     * @return Correo y si no esta NULL
     */
	public Correo buscar(String direccionCorreo);
	
	
    /**
     * Busca un Correo por nombre de usuario
     * @param nombre Nombre de usuario deseado
     * @return Correo y si no esta NULL
     */
	public Correo buscarNombre(String nombre);
	
	
    /**
     * Lista de correos
     * @return Lista de Correos
     */
	public List getCorreos();
	
    /**
     * Lista de Correos filtrados por oficina
     * @param oficina Código de oficina deseada como filtro
     * @return Lista de Correos
     */
	public List getCorreos(Integer oficina);
	
    /**
     * Lista de Correos filtrados por dirección de correo
     * @param direccionCorreo Dirección de correo deseada
     * @return Lista de Correos
     */
	public List getCorreosCorreo(String direccionCorreo);
	
    /**
     * Lista de Correos filtrados por nombre de usuario
     * @param nombre Nombre de usuario deseado
     * @return Lista de Correos
     */
	public List getCorreosNombre(String nombre);
	
	/**
	 * Comment for existe
	 * @param correo
	 * @return La existencia o no NUll
	 */
	public boolean existe(Correo correo);
    
    /**
     * Lista de correos por oficina que se encuentran activos para un envío
     * @param oficina Oficina deseada
     * @return Lista de correos por oficina que se encuentran activos para un envío
     */
    public List<Correo> getCorreosParaEnviar(Integer oficina);
}
