package cr.go.ice.interrupciones.BO;



/**
 * <p>Interface cr.go.ice.interrupciones.BO.SubEstacionSubRegionBO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SubEstacionSubRegionBO.java</code>Define los Metodos de Logica y Negocio de las Clases para SubEstacionSubRegion.</p>
 * <p>Fecha creación: 08/04/2008</p>
 * <p>Ultima actualización: 08/04/2008</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public interface SubEstacionSubRegionBO {

    
    /**
     * Agrega una SubEstacionSubRegion
     * @param oficina Oficina deseada para la SubEstacionSubRegion
     * @param region Region deseada para la SubEstacionSubRegion
     * @param subregion SubRegion deseada para la SubEstacionSubRegion
     * @param subestacion SubEstacion deseada para la SubEstacionSubRegion
     */
    public void agregar(Integer oficina, Integer region, Integer subregion, Integer subestacion);
    
    
    /**
     * Elimina una SubEstacionSubRegion
     * @param oficina Oficina deseada para la SubEstacionSubRegion
     * @param region Region deseada para la SubEstacionSubRegion
     * @param subregion SubRegion deseada para la SubEstacionSubRegion
     * @param subestacion SubEstacion deseada para la SubEstacionSubRegion
     */
    public void eliminar(Integer oficina, Integer region, Integer subregion, Integer subestacion);
    
    /**

     * Determina la existencia de una SubEstacionSubRegion
     * @param oficina Oficina deseada para la SubEstacionSubRegion
     * @param region Region deseada para la SubEstacionSubRegion
     * @param subregion SubRegion deseada para la SubEstacionSubRegion
     * @param subestacion SubEstacion deseada para la SubEstacionSubRegion
     * @return true o false
     */
    public boolean existe(Integer oficina, Integer region, Integer subregion, Integer subestacion);
    
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
