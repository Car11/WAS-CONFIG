package cr.go.ice.interrupciones.BO;

import java.util.List;

import cr.go.ice.interrupciones.domain.Causa;

/**
 * <p>Interface cr.go.ice.interrupciones.BO.CausaBO.java</p>
 * <p>Modulo (subsistema): Interrupciones</p>
 * <p>La interface <code>CausaBO</code> Establece los metodos de logica de negocio para Causa.</p>
 * <p>Fecha creación: 18/01/2007</p>
 * <p>Ultima actualización: 18/01/2007</p>
 * @author Mario Leon Castro
 * @version 1.1
 */
public interface CausaBO {
    
    /**
     * Metodo que permite agregar una causa
     * @param causa La causa a agregar
     */
    public void agregar(Causa causa);
    
    /**
     * Metodo que permite modificar una casua
     * @param causa La causa a modificar
     */
    public void modificar(Causa causa);
    
    /**
     * Metodo que permite eliminar una causa
     * @param causa La causa a eliminar
     */
    public void eliminar(Causa causa);
    
    /**
     * Metodo que permite la busqueda de una causa por su codigo
     * @param codigo El codigo de la causa a buscar
     * @return El objeto Causa correspondiente, o null si este no existe
     */
    public Causa buscar(Integer codigo);
    
    public Causa buscar(Integer codigo, Integer tipoCausa, Integer causaEspecifica, Integer detalleCausa);
    
    /**
     * Metodo que permite la busqueda de una causa medio del nombre
     * @param nombre El nombre de la causa a buscar
     * @return El objeto Causa correspondiete, o null si este no existe
     */
    public Causa buscar(String nombre);
    
    /**
     * Metodo que devuelve un listado de todas las causas.
     * @return List Una lista de todas las causas
     */
    public List getCausas();
    
    /**
     * Retorna las causas utilizadas para generar el reporte de indicadores por causa
     * @return Lista de causas
     */
    public List getCausasIndicadores();   
    
    /**
     * Determina la existencia de una Causa
     * @param causa Causa deseada
     * @return true o false
     */
    public boolean causaExiste(Causa causa);
    
    public boolean existe(Causa causa);
    
	/**
	 * Retorna la cantidad de registros asociados en las interrupciones mayores y menores a cinco minutos
	 * @param codigoCausa que indica el codigoCausa
	 * @return cantidad de registros asociados
	 */
	public Long registrosAsociados(Integer codigoCausa);    
     
}