package cr.go.ice.interrupciones.BO.impl;

import java.util.ArrayList;
import java.util.List;

import cr.go.ice.interrupciones.BO.CausaBO;
import cr.go.ice.interrupciones.DAO.CausaDAO;
import cr.go.ice.interrupciones.domain.Causa;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.CausaBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>CausaBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para Causa.</p>
 * <p>Fecha creación: 18/01/2007</p>
 * <p>Ultima actualización: 18/01/2007</p>
 * @author Vista Verde Soft (Mario Leon y Doc Cristian)
 * @version 1.1
 */
public class CausaBOImpl implements CausaBO {
    
    /**
     * <code>causaDAO</code> Causa Dao
     */
    private CausaDAO causaDAO;    
    
    /**
     * Constructor
     */
    public CausaBOImpl(){    	
    }
    
    /**
     * Asigna causaDAO.
     * @param causaDAO El causaDAO a modificar.
     */
    public void setCausaDAO(CausaDAO causaDAO) {
        this.causaDAO = causaDAO;
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.CausaBO#agregar(cr.go.ice.interrupciones.domain.Causa)
     */
    public void agregar(Causa causa){
        this.causaDAO.agregar(causa);
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.CausaBO#modificar(cr.go.ice.interrupciones.domain.Causa)
     */
    public void modificar(Causa causa){
        this.causaDAO.modificar(causa);
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.CausaBO#eliminar(cr.go.ice.interrupciones.domain.Causa)
     */
    public void eliminar(Causa causa){
        this.causaDAO.eliminar(causa);
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.CausaBO#buscar(java.lang.Integer)
     */
    public Causa buscar(Integer codigo){
        return this.causaDAO.buscar(codigo);
    }
    
    public Causa buscar(Integer codigo, Integer tipoCausa, Integer causaEspecifica, Integer detalleCausa){
        return this.causaDAO.buscar(codigo, tipoCausa, causaEspecifica, detalleCausa);
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.CausaBO#buscar(java.lang.String)
     */
    public Causa buscar(String nombre){
        return this.causaDAO.buscar(nombre);
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.CausaBO#getCausas()
     */
    public List getCausas(){
        return this.causaDAO.getCausas();
    }

    /**
     * @see cr.go.ice.interrupciones.BO.CausaBO#causaExiste(cr.go.ice.interrupciones.domain.Causa)
     */
    public boolean causaExiste(Causa causa) {
        return this.causaDAO.causaExiste(causa);
    }
    
    public boolean existe(Causa causa) {
        return this.causaDAO.existe(causa);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.CausaBO#registrosAsociados(java.lang.Integer)
     */
    public Long registrosAsociados(Integer codigoCausa) {
        return this.causaDAO.registrosAsociados(codigoCausa);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.CausaBO#getCausasIndicadores()
     */
    public List getCausasIndicadores() {
        List causas = new ArrayList();
        causas.add(new Integer(413));
        causas.add(new Integer(421));
        causas.add(new Integer(422));
        causas.add(new Integer(423));
        causas.add(new Integer(424));
        causas.add(new Integer(417));
        return this.causaDAO.getCausas(causas);
    }
}