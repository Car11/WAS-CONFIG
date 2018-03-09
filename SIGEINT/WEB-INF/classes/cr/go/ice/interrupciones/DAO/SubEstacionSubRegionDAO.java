package cr.go.ice.interrupciones.DAO;

import cr.go.ice.interrupciones.domain.SubEstacionSubRegion;

/**
 * <p>Interface cr.go.ice.interrupciones.DAO.SubEstacionSubRegionDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SubEstacionSubRegionDAO.java</code>Define los metodos de los Datos para SubEstacionSubRegion</p>
 * <p>Fecha creación: 08/04/2008</p>
 * <p>Ultima actualización: 08/04/2008</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public interface SubEstacionSubRegionDAO {

    /**
     * Agrega un SubEstacionSubRegion
     * @param sub SubEstacionSubRegion a agregar
     */
    public void agregar(SubEstacionSubRegion sub);
    
    
    /**
     * Elimina un SubEstacionSubRegion
     * @param sub SubEstacionSubRegion a eliminar
     */
    public void eliminar(SubEstacionSubRegion sub);
    
    /**
     * Determina la existencia de un SubEstacionSubRegion
     * @param sub SubEstacionSubRegion a determinar su existencia
     * @return true o false
     */
    public boolean existe(SubEstacionSubRegion sub);
    
    /**
     * Método buscarOficina
     * Obtiene la oficina asociada a una region, subregion, subestacion
     * @param region
     * @param subregion
     * @param subestacion
     * @return oficina
     */
    public Integer buscarOficina(Integer region, Integer subregion, Integer subestacion);
}
