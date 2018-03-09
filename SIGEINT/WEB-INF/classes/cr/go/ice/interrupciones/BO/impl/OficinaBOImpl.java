package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.OficinaBO;
import cr.go.ice.interrupciones.DAO.OficinaDAO;
import cr.go.ice.interrupciones.domain.Oficina;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.OficinaBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>OficinaBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para Oficina.</p>
 * <p>Fecha creación: 22/01/2007</p>
 * <p>Ultima actualización: 22/01/2007</p>
 * @author Vista Verde Soft (Administrador y Doc Cristian)
 * @version 1.1
 */
public class OficinaBOImpl implements OficinaBO {
    /**
     * <code>oficinaDAO</code> Oficina Dao
     */
    private OficinaDAO oficinaDAO;
    
    /**
     * Constructor
     */
    public OficinaBOImpl(){
    }

    
    /**
     * Asigna OficinaDAO
     * @param oficinaDAO
     */
    public void setOficinaDAO(OficinaDAO oficinaDAO) {
        this.oficinaDAO = oficinaDAO;
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.OficinaBO#agregar(cr.go.ice.interrupciones.domain.Oficina)
     */
    public void agregar(Oficina oficina) {
        this.oficinaDAO.agregar(oficina);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.OficinaBO#modificar(cr.go.ice.interrupciones.domain.Oficina)
     */
    public void modificar(Oficina oficina) {
        this.oficinaDAO.modificar(oficina);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.OficinaBO#eliminar(cr.go.ice.interrupciones.domain.Oficina)
     */
    public void eliminar(Oficina oficina) {
        this.oficinaDAO.eliminar(oficina);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.OficinaBO#buscar(java.lang.Integer)
     */
    public Oficina buscar(Integer codigo) {
        return this.oficinaDAO.buscar(codigo);
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.OficinaBO#buscar(java.lang.String)
     */
    public Oficina buscar(String descripcion){
        return this.oficinaDAO.buscar(descripcion);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.OficinaBO#getOficinas()
     */
    public List getOficinas() {
        return this.oficinaDAO.getOficinas();
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.OficinaBO#oficinaExiste(cr.go.ice.interrupciones.domain.Oficina)
     */
    public boolean oficinaExiste(Oficina oficina){
        return this.oficinaDAO.oficinaExiste(oficina);
    }


    /**
     * @see cr.go.ice.interrupciones.BO.OficinaBO#registrosAsociados(java.lang.Integer)
     */
    public Long registrosAsociados(Integer codigoOficina) {
        return this.oficinaDAO.registrosAsociados(codigoOficina);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.OficinaBO#tieneOficinasAsociadasACorreos(java.lang.Integer)
     */
    public boolean tieneOficinasAsociadasACorreos(Integer codigoOficina) {
        return this.oficinaDAO.tieneOficinasAsociadasACorreos(codigoOficina);
    }
}