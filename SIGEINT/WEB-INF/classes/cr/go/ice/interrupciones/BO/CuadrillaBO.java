
package cr.go.ice.interrupciones.BO;

import java.util.List;

import cr.go.ice.interrupciones.domain.Cuadrilla;
import cr.go.ice.interrupciones.domain.CuadrillaID;

/**
 * <p>Interface cr.go.ice.interrupciones.BO.CuadrillaBO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>CuadrillaBO.java</code>Define los Metodos de Logica y Negocio de las Clases para Cuadrilla.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public interface CuadrillaBO{

    /**
     * Agrega una cuadrilla
     * @param la cuadrilla a agregar
     */
    public void agregar(Cuadrilla cuadrilla);
    
    /**
     * Modifica una cuadrilla
     * @param la cuadrilla a modificar
     */
    public void modificar(Cuadrilla cuadrilla);
    
    /**
     * Elimina una cuadrilla
     * @param la cuadrilla a eliminar
     */
    public void eliminar(Cuadrilla cuadrilla);
    
    /**
     * Retorna la cuadrilla perteneciente a la cuadrillaID
     * @param la cuadrilla que indica la cuadrillaID
     * @return la cuadrilla si se encuentra en la base de datos o null
     */
    public Cuadrilla buscar(Cuadrilla cuadrilla);    
    
    /**
     * Retorna la lista de todas las cuadrillas de la base de datos
     * @return Lista de Cuadrillas
     */
    public List getCuadrillas();
    
    /**
     * Determina la existencia o no de una cuadrilla
     * @param cuadrilla que indica la cuadrillaID
     * @return La existencia o no
     */
    public boolean cuadrillaExiste(Cuadrilla cuadrilla);
    
    /**
     * Agrega una lista de cuadrillas
     * @param cuadrilla es la lista de cuadrillas
     */
    public void agregar(List cuadrilla);    
    
    /**    
     * Modifica las cuadrillas que se encuentran en la base de datos
     * @param cuadrilla es la lista de las nuevas cuadrillas
     * @param cuadrillaID indica las cuadrillas a modificar
     */
    public void modificar(List cuadrilla, CuadrillaID cuadrillaID);        
    
    /** 
     * Retorna una cuadrilla de empleados
     * @param cuadrillaID indica cual cuadrilla es la indicada
     * @return la lista completa de la cuadrilla
     */          
    public List getCuadrillas(CuadrillaID cuadrillaID);
	
}
