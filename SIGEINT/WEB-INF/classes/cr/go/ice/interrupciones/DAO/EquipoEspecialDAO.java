
package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.EquipoEspecial;
import cr.go.ice.interrupciones.domain.EquipoEspecialID;


/**
 * <p>Interface cr.go.ice.interrupciones.DAO.EquipoEspecialDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>EquipoEspecialDAO.java</code>Define los metodos de los Datos para EquipoEspecial.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public interface EquipoEspecialDAO {

    /**
     * Agrega un  equipo especial
     * @param equipoEspecial a agregar
     */
    public void agregar(EquipoEspecial equipoEspecial);
    
    /**
     * Agrega una lista de equipos especiales
     * @param equiposEspeciales lista de equipos especiales a agregar
     */
    public void agregar(List equiposEspeciales);
    
    /**
     * Modifica un equipo especial
     * @param equipoEspecial a modificar
     */
    public void modificar(EquipoEspecial equipoEspecial);
    
    /**
     * Elimina un equipo especial
     * @param equipoEspecial a eliminar
     */
    public void eliminar(EquipoEspecial equipoEspecial);
    
    /**
     * Busca un equipo especial determinado por su equipoEspecialID
     * @param equipoEspecial que indica el equipoEspecialID
     * @return EquipoEspecial si existe, sino null
     */
    public EquipoEspecial buscar(EquipoEspecial equipoEspecial);    
    
    /**
     * Retorna la lista de todos los equipos especiales existentes en la base de datos
     * @return Lista de equipos Especiales
     */
    public List getEquiposEspeciales();
    
    /**
     * Retorna una lista de todos los equipos especiales identificados por un equipoEspecialID
     * @param equipoEspecialID que indica cuales son los equipos especiales a obtener
     * @return Lista de equipos Especiales
     */
    public List getEquiposEspeciales(EquipoEspecialID equipoEspecialID);
    
    /**
     * Modifica una lista de equipos especiales
     * @param equipoEspecial es la lista de los nuevos equipos especiales
     * @param equipoEspecialID determina la lista de equipos especiales a ser modificados
     */
    public void modificar(List equipoEspecial, EquipoEspecialID equipoEspecialID);  
    
    /**
     * Determina la existencia o no de un equipo especial
     * @param equipoEspecial que indica el equipoEspecialID a determinar su existencia
     * @return La existencia o no del equipo especial
     */
    public boolean equipoEspecialExiste(EquipoEspecial equipoEspecial);
	
}
