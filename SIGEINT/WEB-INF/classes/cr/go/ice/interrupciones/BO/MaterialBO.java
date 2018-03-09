package cr.go.ice.interrupciones.BO;

import java.util.List;

import cr.go.ice.interrupciones.domain.Material;

/**
 * <p>Interface cr.go.ice.interrupciones.BO.MaterialBO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>MaterialBO.java</code> Define los Metodos de Logica y Negocio de las Clases para Material para Material.</p>
 * <p>Fecha creación: 22/01/2007</p>
 * <p>Ultima actualización: 22/01/2007</p>
 * @author Administrador
 * @version 1.1
 */
public interface MaterialBO {
    
    /**
     * Agrega un Material
     * @param material a agregar
     */
    public void agregar(Material material);
    
    /**
     * Modifica un Material
     * @param material a modificar
     */
    public void modificar(Material material);
    
    /**
     * Elimina un Material
     * @param material a eliminar
     */
    public void eliminar(Material material);
    
    /**
     * Buscar un Material
     * @param codigo Código de material deseado como filtro
     * @return BMaterial y si no encuentra NULL
     */
    public Material buscar(Integer codigo);
    
    public Material buscar(Integer codigo, Integer ubicacionGeneral, Integer ubicacionEspecifica);
    
    /**
     * Buscar un Material por nombre
     * @param descripcion Nombre de material deseado como filtro
     * @return Material y si no esta NULL
     */
    public Material buscar(String descripcion);
    
    /**
     * Determina una lista de materiales
     * @return Lista de Materiales
     */
    public List getMateriales();
    
    /**
     * Determina la existencia de un material
     * @param material Material deseado como filtro
     * @return true o false
     */
    public boolean materialExiste(Material material);
    
    public boolean existe(Material material);
    
	/**
	 * Retorna la cantidad de registros asociados en las interrupciones mayores y menores a cinco minutos
	 * @param codigoMaterial que indica el codigoMaterial
	 * @return cantidad de registros asociados
	 */
	public Long registrosAsociados(Integer codigoMaterial);        
}