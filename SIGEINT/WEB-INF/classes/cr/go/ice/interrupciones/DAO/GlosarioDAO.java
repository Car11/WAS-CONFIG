package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.Glosario;

/**
 * <p>Interface cr.go.ice.interrupciones.DAO.GlosarioDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>GlosarioDAO.java</code> Define los metodos de los Datos para Glosario.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Tecnologia (Cesar y Doc Cristian)
 * @version 1.1
 */
public interface GlosarioDAO {
	
    /**
     * Agrega un Glosario
     * @param glosario a agregar
     */
	public void agregar(Glosario  glosario);
    
    /**
     * Modifica un Glosario
     * @param glosario a modificar
     */
    public void modificar(Glosario  glosario);
    
    /**
     * Elimina un Glosario
     * @param glosario a eliminar
     */
    public void eliminar(Glosario  glosario);
    
    /**
     * Busca un Glosario
     * @param termino Termino deseado como filtro
     * @return Glosario y si no esta NUll
     */
    public Glosario buscar(String termino);
    
    /**
     * Determina una lista de Glosarios
     * @return Lista de Glosarios
     */
    public List getGlosarios();

    /**   
     * Determina la existencia de un Glosario
     * @param termino Termino deseado como filtro
     * @return true o false
     */
	public boolean glosarioExiste(String termino);

    /**   
     * Determina la existencia de un Glosario
     * @param Glosario Termino deseado como filtro
     * @return true o false
     */
	public boolean existe(Glosario glosario);

}
