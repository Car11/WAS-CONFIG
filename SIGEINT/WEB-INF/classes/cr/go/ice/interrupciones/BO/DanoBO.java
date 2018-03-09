package cr.go.ice.interrupciones.BO;

import java.util.List;

import cr.go.ice.interrupciones.domain.Dano;

/**
 * <p>Interface cr.go.ice.interrupciones.BO.DanoBO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>DanoBO.java</code>Define los Metodos de Logica y Negocio de las Clases para Dano.</p>
 * <p>Fecha creación: 17/02/2007</p>
 * <p>Ultima actualización: 17/02/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public interface DanoBO {
    
    /**
     * Agrega un Dano
     * @param dano a agregar
     */
    public void agregar(Dano dano);
    
    /**
     * Modifica un Dano
     * @param dano a agregar
     */
    public void modificar(Dano dano);
    
    /**
     * Elimina un Dano
     * @param dano a eliminar
     */
    public void eliminar(Dano dano);
    
    /**
     * Busca un Dano
     * @param codigo Código de Dano a buscar
     * @return Dano y si no esta NUll
     */
    public Dano buscar(Integer codigo);
    
    /**
     * Busca un Dano por descripcion
     * @param descripcion Nombre del Dano deseado como filtro
     * @return Dano y si no esta NUll
     */
    public Dano buscar(String descripcion);
    
    /**
     * Lista de danos
     * @return Lista de Danos
     */
    public List getDanos();
    
    /**
     * Determina la existencia de un Dano
     * @param dano Dano deseado como filtro
     * @return true o false
     */
    public boolean danoExiste(Dano dano);
    
	/**
	 * Retorna la cantidad de registros asociados en las interrupciones mayores y menores a cinco minutos
	 * @param codigoDano que indica el codigoDano
	 * @return cantidad de registros asociados
	 */
	public Long registrosAsociados(Integer codigoDano);    
}