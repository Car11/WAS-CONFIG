package cr.go.ice.sace.sacecon.dao;

import java.util.List;

import com.vvs.bussiness.BussinessError;

import cr.go.ice.sace.sacecon.domain.Pueblo;


/**
 * <p>Interface cr.go.ice.sace.sacecon.dao.PuebloDao.java</p>
 * <p>Modulo (subsistema):  TransformadoresWeb</p>
 * <p> Descricion de <code>PuebloDao.java</code>define
 * los métodos del acceso a datos para la manipulación de los
 * objetos <code>Pueblo</code>.</p>
 * <p>Fecha creación: 11/01/2011</p>
 * <p>Ultima actualización: 11/01/2011</p>
 * @author Vista Verde Tecnologia (eperaza)
 * @version 1.1
 */
public interface PuebloSDao {
    /**
     * Metodo agregar
     * Este Metodo agrega una nueva instancia de <code>Pueblo</code>.
     * @param pueblo <code>pueblo</code> instancia por agregar.
     */
    public void agregar(Pueblo pueblo);
    
    /**
     * Metodo modificar
      * Este Metodo modifica una instancia de <code>Pueblo</code>.
     * @param pueblo <code>pueblo</code> instancia por modificar.  
     */
    public void modificar(Pueblo pueblo);
    
    /**
     * Metodo eliminar
      * Este Metodo elimina una instancia de <code>Pueblo</code>.
     * @param pueblo <code>pueblo</code> instancia por ser eliminada.
     */
    public void eliminar(Pueblo pueblo);
    
    /**
     * Metodo buscar
     * Este Metodo busca una determinada instancia de <code>Pueblo</code> atravez de la pk <code>número</code>.
     * @param numero id unico de la instancia.
     * @return El objeto <code>Pueblo</code> correspondiente, o <code>null</code> si este no existe.
     */
    public Pueblo buscar(Integer numero) throws BussinessError;
    
    /**
     * Metodo existe
      * Este Metodo indica si existe o no una determinada instancia del objeto <code>Pueblo</code>.
     * @param pueblo <code>pueblo</code> la instancia de la cual se requiere comprobar la existencia.
     * @return boolean 
     */
    public boolean existe(Pueblo pueblo);
    
    /**
     * Metodo getPueblos
     * Metodo retorna la lista de que contiene todas las instancias del objeto <code>Pueblo</code> dentro de la base de datos.
     * @return List pueblos
     */
    public List getPueblos();
    
    /**
     * Metodo getPueblos
     * Metodo retorna la lista de que contiene todas las instancias del objeto <code>Pueblo</code> dentro de la base de datos filtradas por un nombre.
     * @param nombre
     * @return List pueblos
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
