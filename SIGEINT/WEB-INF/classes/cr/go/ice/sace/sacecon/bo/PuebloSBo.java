package cr.go.ice.sace.sacecon.bo;

import java.util.List;
import com.vvs.bussiness.BussinessError;
import cr.go.ice.sace.sacecon.domain.Pueblo;

/**
 * <p>Interface cr.go.ice.sace.sacecon.bo.PuebloBo.java</p>
 * <p>Modulo (subsistema): TransformadoresWeb</p>
 * <p>La interface <code>PuebloBo.java</code> define los metodos de logica de negocios relacionados a un PuebloBo.</p>
 * <p>Fecha creación: 11/01/2011</p>
 * <p>Ultima actualización: 11/01/2011</p>
 * @author Vista Verde Tecnologia (eperaza)
 * @version 1.1
 */
public interface PuebloSBo {
    /**
     * Devuelve la <code>pueblo</code> relacionada al id indicado, si no hay ninguna relacionada, se devuelve <code>null</code>.
     * @param numero El Id de <code>pueblo</code>.
     * @return El objeto <code>pueblo</code> correspondiente, o <code>null</code> si este no existe.
     */
    public Pueblo buscar(Integer numero) throws BussinessError;
    
    /**
     * Permite agregar una nueva <code>pueblo</code>
     * @param pueblo La <code>pueblo</code> a agregar
     * @throws BussinessError En caso que suceda algun error de logica de negocio
     */
    public void agregar(Pueblo pueblo) throws BussinessError;
    
    /**
     * Permite modificar una <code>pueblo</code> existente
     * @param pueblo La <code>pueblo</code> a modificar con el ID establecido
     * @throws BussinessError En caso que suceda algun error de logica de negocio
     */
    public void modificar(Pueblo pueblo) throws BussinessError;
    
    /**
     * Permite eliminar una <code>pueblo</code>
     * @param pueblo La <code>pueblo</code> a eliminar con el ID establecido
     * @throws BussinessError En caso que suceda algun error de logica de negocio
     */
    public void eliminar(Pueblo pueblo) throws BussinessError;
    /**
     * Obtiene la lista con todas los Pueblos de la base de datos
     * @return La lista de los Pueblos ordenados acendentemente por codigo, si no hay datos retorna una lista vacia
     */
    public List getPueblos();
    /**
     * Permite verificar la existencia de un <code>Pueblo</code>
     * @param pueblo El Pueblo de que se quiere confirmar exitencia
     * @return true en el caso de que exista o false en el caso contrario.
     */
    public boolean existe(Pueblo pueblo);
    
    /**
     * Obtiene la lista con todos los Pueblos de la base de datos filtrados por un nombre
     * @param nombre El nombre con que se va a filtrar la lista
     * @return La lista de los pueblos asocicados a un nombre
     */
    public List getPueblos(String nombre) throws BussinessError;
    

    
    /**
     * Método getPueblosAgencia
     * TODO (Descripción) 
     * @param agencia
     * @return List
     */
    public List getPueblosAgencia(Integer agencia) throws BussinessError;
}
