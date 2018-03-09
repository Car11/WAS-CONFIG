package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.SubEstacionBO;
import cr.go.ice.interrupciones.DAO.SubEstacionDAO;
import cr.go.ice.interrupciones.domain.SubEstacion;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.SubEstacionBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SubEstacionBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para SubEstacion.</p>
 * <p>Fecha creación: 02/02/2007</p>
 * <p>Ultima actualización: 02/02/2007</p>
 * @author Vista Verde Soft (Administrador y Doc Cristian)
 * @version 1.1
 */
public class SubEstacionBOImpl implements SubEstacionBO {
    
    /**
     * <code>subEstacionDAO</code> Sub Estacion Dao
     */
    private SubEstacionDAO subEstacionDAO;
    
    /**
     * Constructor
     */
    public SubEstacionBOImpl(){        
    }

    /**
     * Asigna SubEstacionDAO
     * @param subEstacionDAO
     */
    public void setSubEstacionDAO(SubEstacionDAO subEstacionDAO) {
        this.subEstacionDAO = subEstacionDAO;
    }
    /**
     * @see cr.go.ice.interrupciones.BO.SubEstacionBO#agregar(cr.go.ice.interrupciones.domain.SubEstacion)
     */
    public void agregar(SubEstacion subEstacion) {
        this.subEstacionDAO.agregar(subEstacion);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.SubEstacionBO#modificar(cr.go.ice.interrupciones.domain.SubEstacion)
     */
    public void modificar(SubEstacion subEstacion) {
        this.subEstacionDAO.modificar(subEstacion);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.SubEstacionBO#eliminar(cr.go.ice.interrupciones.domain.SubEstacion)
     */
    public void eliminar(SubEstacion subEstacion) {
        this.subEstacionDAO.eliminar(subEstacion);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.SubEstacionBO#buscar(java.lang.Integer)
     */
    public SubEstacion buscar(Integer codigo) {
        return this.subEstacionDAO.buscar(codigo);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.SubEstacionBO#buscar(java.lang.String)
     */
    public SubEstacion buscar(String nombre) {
        return this.subEstacionDAO.buscar(nombre);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.SubEstacionBO#getSubEstaciones()
     */
    public List getSubEstaciones() {
        return this.subEstacionDAO.getSubEstaciones();
    }

    /**
     * @see cr.go.ice.interrupciones.BO.SubEstacionBO#subEstacionExiste(cr.go.ice.interrupciones.domain.SubEstacion)
     */
    public boolean subEstacionExiste(SubEstacion subEstacion) {
        return this.subEstacionDAO.subEstacionExiste(subEstacion);
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.SubEstacionBO#getSubEstacionesSubRegionOficina(java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    public List getSubEstacionesSubRegionOficina(Integer codigoRegion, Integer codigoSubRegion, Integer codigoOficina){
    	return this.subEstacionDAO.getSubEstacionesSubRegionOficina(codigoRegion, codigoSubRegion, codigoOficina);
    }

	/**
	 * @see cr.go.ice.interrupciones.BO.SubEstacionBO#getSubEstaciones(java.lang.Integer)
	 */
	public List getSubEstaciones(Integer codigoOficina) {
		return this.subEstacionDAO.getSubEstaciones(codigoOficina);
	}

    /**
     * @see cr.go.ice.interrupciones.BO.SubEstacionBO#ejecutarIndicesGlobales(java.lang.Integer, java.lang.Integer)
     */
    public String ejecutarIndicesGlobales(Integer ano, Integer mes) {
        return this.subEstacionDAO.ejecutarIndicesGlobales(ano,mes);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.SubEstacionBO#getSubEstaciones(java.lang.Integer, java.lang.Integer)
     */   
    public List getSubEstaciones(Integer region, Integer subregion) {
        return this.subEstacionDAO.getSubEstaciones(region, subregion);
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.SubEstacionBO#getSubEstaciones(java.lang.Integer, java.lang.Integer)
     */   
    public List getSubEstacionesActivas(Integer region, Integer subregion) {
        return this.subEstacionDAO.getSubEstacionesActivas(region, subregion);
    }

    
    /**
     * @see cr.go.ice.interrupciones.BO.SubEstacionBO#getSubEstacionesRegion(java.lang.Integer)
     */
    public List getSubEstacionesRegion(Integer codigoRegion) {
        return this.subEstacionDAO.getSubEstacionesRegion(codigoRegion);
    }
    
    public Integer getCodigoRegionSubEstacion(Integer codigoSubEstacion){
        return this.subEstacionDAO.getCodigoRegionSubEstacion(codigoSubEstacion);
    }
}