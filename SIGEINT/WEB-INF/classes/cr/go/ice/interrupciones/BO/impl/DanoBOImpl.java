package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.DanoBO;
import cr.go.ice.interrupciones.DAO.DanoDAO;
import cr.go.ice.interrupciones.domain.Dano;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.DanoBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>DanoBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para Dano.</p>
 * <p>Fecha creación: 22/01/2007</p>
 * <p>Ultima actualización: 22/01/2007</p>
 * @author Vista Verde Soft (Administrador y Doc Cristian)
 * @version 1.1
 */
public class DanoBOImpl implements DanoBO {
    
    /**
     * <code>danoDAO</code> Dano Dao
     */
    private DanoDAO danoDAO;
    
    /**
     * Constructor
     */
    public DanoBOImpl(){
    }

    /**
     * Asigna danoDAO.
     * @param danoDAO El danoDAO a modificar.
     */
    public void setDanoDAO(DanoDAO danoDAO) {
        this.danoDAO = danoDAO;
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.DanoBO#agregar(cr.go.ice.interrupciones.domain.Dano)
     */
    public void agregar(Dano dano) {
        this.danoDAO.agregar(dano);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.DanoBO#modificar(cr.go.ice.interrupciones.domain.Dano)
     */
    public void modificar(Dano dano) {
        this.danoDAO.modificar(dano);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.DanoBO#eliminar(cr.go.ice.interrupciones.domain.Dano)
     */
    public void eliminar(Dano dano) {
        this.danoDAO.eliminar(dano);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.DanoBO#buscar(java.lang.Integer)
     */
    public Dano buscar(Integer codigo) {
        return this.danoDAO.buscar(codigo);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.DanoBO#buscar(java.lang.String)
     */
    public Dano buscar(String descripcion) {
        return this.danoDAO.buscar(descripcion);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.DanoBO#getDanos()
     */
    public List getDanos() {
        return this.danoDAO.getDanos();
    }

    /**
     * @see cr.go.ice.interrupciones.BO.DanoBO#danoExiste(cr.go.ice.interrupciones.domain.Dano)
     */
    public boolean danoExiste(Dano dano) {
        return this.danoDAO.danoExiste(dano);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.DanoBO#registrosAsociados(java.lang.Integer)
     */
    public Long registrosAsociados(Integer codigoDano) {
        return this.danoDAO.registrosAsociados(codigoDano);
    }
}