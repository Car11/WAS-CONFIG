package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.Oficina;

/**
 * <p>Interface cr.go.ice.interrupciones.DAO.OficinaDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>OficinaDAO.java</code> Define los metodos de los Datos para Oficina</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Tecnologia (Cesar y Doc Cristian)
 * @version 1.1
 */
public interface OficinaDAO {
    
    /**
     * Agrega una Oficina
     * @param oficina a agregar
     */
    public void agregar(Oficina oficina);
    
    /**
     * Modifica una Oficina
     * @param oficina a modificar
     */
    public void modificar(Oficina oficina);
    
    /**
     * Elimina una Oficina
     * @param oficina a eliminar
     */
    public void eliminar(Oficina oficina);
    
    /**
     * Busca una Oficina
     * @param codigo Código de oficina deseado como filtro
     * @return Oficina y si no esta Null
     */
    public Oficina buscar(Integer codigo);
    
    /**
     * Buscar una Oficina por nombre
     * @param descripcion Nombre deseado de la oficina
     * @return Oficina y si no esta NUll
     */
    public Oficina buscar(String descripcion);
    
    /**
     * Determina una lista de oficinas
     * @return Lista de Oficinas
     */
    public List getOficinas();
    
    /**
     * Determina la existencia de una Oficina
     * @param oficina Código de oficina deseado como filtro
     * @return true o false
     */
    public boolean oficinaExiste(Oficina oficina);
    
	/**
	 * Retorna la cantidad de registros asociados en las interrupciones mayores y menores a cinco minutos
	 * @param codigoOficina que indica el codigoOficina
	 * @return cantidad de registros asociados
	 */
	public Long registrosAsociados(Integer codigoOficina);   
    
    /**
     * Determina si una oficina determinada se encuentra asociada a algún correo
     * @param codigoOficina Código de oficina deseado
     * @return true o false
     */
    public boolean tieneOficinasAsociadasACorreos(Integer codigoOficina);    
    
}