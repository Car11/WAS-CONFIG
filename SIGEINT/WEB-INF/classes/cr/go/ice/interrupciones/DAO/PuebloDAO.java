package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.Pueblo;

/**
 * <p>Interface cr.go.ice.interrupciones.DAO.PuebloDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>PuebloDAO.java</code>Define los metodos de los Datos para Pueblo.</p>
 * <p>Fecha creación: 13/04/2007</p>
 * <p>Ultima actualización: 13/04/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public interface PuebloDAO {

    /**
     * Agrega un pueblo
     * @param pueblo a agregar
     */
    public void agregar(Pueblo pueblo);
    
    /**
     * Modifica un pueblo
     * @param pueblo a modificar
     */
    public void modificar(Pueblo pueblo);
    
    /**
     * Elimina un pueblo
     * @param pueblo a eliminar
     */
    public void eliminar(Pueblo pueblo);
    
    /**
     * Busca un pueblo especificado por su puebloID
     * @param pueblo que especifica su correspondiente puebloID
     * @return Busca Pueblo y si no esta Null
     */
    public Pueblo buscar(Pueblo pueblo);
    
    /**
     * Metodo buscar de un pueblo
     * @param codigo Codigo deseado como filtro para el pueblo
     * @return  Pueblo o null
     */
    public Pueblo buscar(Integer codigo);
        
    /**
     * Retorna la lista de pueblos existentes
     * @return Lista de pueblos
     */
    public List getPueblos();
    
    /**
     * Retorna una lista de pueblos
     * @param nombrePueblo que especifica la semejanza al nombre del pueblo que debe tener la lista resultado
     * @return Lista de pueblos
     */
    public List getPueblos(String nombrePueblo);
    
    /**
     * Metodo que retorna una lista de pueblos
     * @param nombrePueblo que especifica la semejanza al nombre del pueblo que debe tener la lista resultado
     * @param orden Especifica si se requiere un ordenamiento por nombre del pueblo o no
     * @return Lista de pueblos deseados
     */
    public List getPueblos(String nombrePueblo, Integer orden);
    
    /**
     * Determina la existencia de un pueblo
     * @param pueblo que especifica su correspondiente puebloID
     * @return La existencia o no de un pueblo
     */
    public boolean puebloExiste(Pueblo pueblo);	
	
}
