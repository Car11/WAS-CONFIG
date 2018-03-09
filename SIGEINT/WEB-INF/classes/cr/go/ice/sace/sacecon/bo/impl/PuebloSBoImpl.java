package cr.go.ice.sace.sacecon.bo.impl;

import java.util.List;

import com.vvs.bussiness.BussinessError;
import com.vvs.bussiness.BussinessObject;

import cr.go.ice.sace.sacecon.bo.PuebloSBo;
import cr.go.ice.sace.sacecon.dao.PuebloSDao;
import cr.go.ice.sace.sacecon.domain.Pueblo;

/**
 * <p>Clase cr.go.ice.sace.sacecon.bo.impl.PuebloBoImpl.java</p>
 * <p>Modulo (subsistema): TransformadoresWeb</p>
 * <p>La clase <code>PuebloBoImpl.java</code> provee las implementaciones de lógica de negocio definidas por la interface <code>PuebloBo</code>.</p>
 * <p>Fecha creación: 11/01/2011</p>
 * <p>Ultima actualización: 11/01/2011</p>
 * @author Vista Verde Tecnologia (TransformadoresWeb)
 * @version 1.1
 */
public class PuebloSBoImpl extends BussinessObject implements PuebloSBo {
    /** atributo <code>puebloDao</code>*/
    private PuebloSDao puebloSDao;
    
    /**
     * Constructor
     * 
     */
    public PuebloSBoImpl() {
        super();
    }

    /**
     * Método accesor del atributo puebloSDao.
     * @return Retorna el atributo puebloSDao.
     */
    public PuebloSDao getPuebloSDao() {
        return this.puebloSDao;
    }

    /**
     * Método modificador del atributo puebloSDao.
     * @param puebloSDao El dato para modificar el atributo puebloSDao.
     */
    public void setPuebloSDao(PuebloSDao puebloSDao) {
        this.puebloSDao = puebloSDao;
    }

    /**
     * @see cr.go.ice.sace.sacecon.bo.PuebloSBo#buscar(cr.go.ice.sace.sacecon.domain.Pueblo)
     */
    public Pueblo buscar(Integer numero) throws BussinessError{
        return this.puebloSDao.buscar(numero);
    }

    /**
     * @see cr.go.ice.sace.sacecon.bo.PuebloSBo#agregar(cr.go.ice.sace.sacecon.domain.Pueblo)
     */
    public void agregar(Pueblo pueblo) throws BussinessError {
        if(!this.puebloSDao.existe(pueblo)){
            this.puebloSDao.agregar(pueblo);
        }else{
            this.throwBussinessError();
        }
    }

    /**
     * @see cr.go.ice.sace.sacecon.bo.PuebloSBo#modificar(cr.go.ice.sace.sacecon.domain.Pueblo)
     */
    public void modificar(Pueblo pueblo) throws BussinessError {
        if(this.puebloSDao.existe(pueblo)){
            this.puebloSDao.modificar(pueblo);
        }else{
            this.throwBussinessError();
        }
    }

    /**
     * @see cr.go.ice.sace.sacecon.bo.PuebloSBo#eliminar(cr.go.ice.sace.sacecon.domain.Pueblo)
     */
    public void eliminar(Pueblo pueblo) throws BussinessError {
        if(this.puebloSDao.existe(pueblo)){
            this.puebloSDao.eliminar(pueblo);
        }else{
            this.throwBussinessError();
        }
    }

    /**
     * @return List
     * @see cr.go.ice.sace.sacecon.bo.PuebloSBo#getListaPueblos()
     */
    public List getPueblos() {
        return this.puebloSDao.getPueblos();
    }

    /**
     * @see cr.go.ice.sace.sacecon.bo.PuebloSBo#existe(cr.go.ice.sace.sacecon.domain.Pueblo)
     */
    public boolean existe(Pueblo pueblo) {
        return this.puebloSDao.existe(pueblo);
    }

    
    /**
     * vea @see cr.go.ice.sace.sacecon.bo.PuebloBo#getPueblos(java.lang.String)
     * @param nombre 
     * @return list
     */
    public List getPueblos(String nombre) throws BussinessError{
        return this.puebloSDao.getPueblos(nombre);
    }
    /**
     * @see cr.go.ice.sace.sacecon.bo.PuebloSBo#getPueblosAgencia(java.lang.Integer)
     */
    public List getPueblosAgencia(Integer agencia)throws BussinessError{
        return this.puebloSDao.getPueblosAgencia(agencia);
    }
    
  
}
