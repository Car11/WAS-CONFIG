package cr.go.ice.interrupciones.BO;

import java.util.List;

import cr.go.ice.interrupciones.domain.Proteccion;

/**
 * <p>Interface cr.go.ice.interrupciones.BO.ProteccionBO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ProteccionBO.java</code>Define los Metodos de Logica y Negocio de las Clases para Proteccion.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (Administrador y Doc Cristian)
 * @version 1.1
 */
public interface ProteccionBO {
    
    /**
     * Agrega una Proteccion
     * @param proteccion a agregar
     */
    public void agregar(Proteccion proteccion);
    
    /**
     * Modifica una Proteccion
     * @param proteccion a modificar
     */
    public void modificar(Proteccion proteccion);
    
    /**
     * Elimina una Proteccion
     * @param proteccion a elimina
     */
    public void eliminar(Proteccion proteccion);
    
    /**
     * Busca una Proteccion
     * @param codigo Código deseado como filtro
     * @return Proteccion y si no esta Null
     */
    public Proteccion buscar(Integer codigo);
    
    /**
     * Busca una protección por nombre
     * @param descripcion Nombre deseado como filtro
     * @return Proteccion y si no esta Null
     */
    public Proteccion buscar(String descripcion);
    
    /**
     * Determina una lista de protecciones existentes
     * @return Lista de Protecciones
     */
    public List getProtecciones();
    
    /**
     * Determina una lista de protecciones filtrada por un tipo de voltaje
     * @param tipoVoltaje Tipo de voltaje deseado como filtro
     * @return Lista de protecciones
     */
    public List getProtecciones(Integer tipoVoltaje);    
    
    /**
     * Determina la existencia de una Proteccion
     * @param proteccion Proteccion deseada como filtro
     * @return true o false
     */
    public boolean proteccionExiste(Proteccion proteccion);
    
	/**
	 * Retorna la cantidad de registros asociados en las interrupciones mayores y menores a cinco minutos
	 * @param codigoProteccion que indica el codigoProteccion
	 * @return cantidad de registros asociados
	 */
	public Long registrosAsociados(Integer codigoProteccion);     
}