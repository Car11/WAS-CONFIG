
package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.SubRegionBO;
import cr.go.ice.interrupciones.domain.SubRegion;
import cr.go.ice.interrupciones.domain.SubRegionID;
import cr.go.ice.interrupciones.DAO.SubRegionDAO;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.SubRegionBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SubRegionBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para SubRegion.</p>
 * <p>Fecha creación: 22/02/2007</p>
 * <p>Ultima actualización: 22/02/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class SubRegionBOImpl implements SubRegionBO{

	/**
	 * <code>subRegionDAO</code> Sub Region Dao
	 */
	private SubRegionDAO subRegionDAO;
	
	/**
     * Constructor.
     */
	public SubRegionBOImpl(){}

    /**
     * Asigna SubRegionDAO
     * @param subRegionDAO
     */
    public void setSubRegionDAO(SubRegionDAO subRegionDAO) {
        this.subRegionDAO = subRegionDAO;
    }
    /**
     * @see cr.go.ice.interrupciones.BO.SubRegionBO#agregar(cr.go.ice.interrupciones.domain.SubRegion)
     */
    public void agregar(SubRegion subRegion){
		this.subRegionDAO.agregar(subRegion);
	}    
    /**
     * @see cr.go.ice.interrupciones.BO.SubRegionBO#modificar(cr.go.ice.interrupciones.domain.SubRegion)
     */
    public void modificar(SubRegion subRegion){
    	this.subRegionDAO.modificar(subRegion);
    }    
    /**
     * @see cr.go.ice.interrupciones.BO.SubRegionBO#eliminar(cr.go.ice.interrupciones.domain.SubRegion)
     */
    public void eliminar(SubRegion subRegion){
    	this.subRegionDAO.eliminar(subRegion);
    }    
    /**
     * @see cr.go.ice.interrupciones.BO.SubRegionBO#buscar(cr.go.ice.interrupciones.domain.SubRegionID)
     */
    public SubRegion buscar(SubRegionID subRegionId){
    	return this.subRegionDAO.buscar(subRegionId);
    }   
    /**
     * @see cr.go.ice.interrupciones.BO.SubRegionBO#getSubRegiones()
     */
    public List getSubRegiones(){
    	return this.subRegionDAO.getSubRegiones();
    }
    /**
     * @see cr.go.ice.interrupciones.BO.SubRegionBO#getSubRegiones(java.lang.Integer)
     */
    public List getSubRegiones(Integer region){
    	return this.subRegionDAO.getSubRegiones(region);
    }
    
    public List getSubRegionesActivas(Integer region){
    	return this.subRegionDAO.getSubRegionesActivas(region);
    }
    /**
     * @see cr.go.ice.interrupciones.BO.SubRegionBO#getSubRegiones(java.lang.Integer, java.lang.Integer)
     */
    public List getSubRegiones(Integer region, Integer orden) {
        return this.subRegionDAO.getSubRegiones(region, orden);
    }
    /**
     * @see cr.go.ice.interrupciones.BO.SubRegionBO#subRegionExiste(cr.go.ice.interrupciones.domain.SubRegionID)
     */
    public boolean subRegionExiste(SubRegionID subRegionID){
    	return this.subRegionDAO.subRegionExiste(subRegionID);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.SubRegionBO#buscarPorOficina(java.lang.Integer)
     */
    public SubRegion buscarPorOficina(Integer codigoOficina) {
        return this.subRegionDAO.buscarPorOficina(codigoOficina);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.SubRegionBO#getSubRegionesPorOficina(java.lang.Integer, java.lang.Integer)
     */
    public List getSubRegionesPorOficina(Integer oficina, Integer region) {
        return this.subRegionDAO.getSubRegionesPorOficina(oficina,region);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.SubRegionBO#ejecutarIndicesGlobales(java.lang.Integer, java.lang.Integer)
     */
    public String ejecutarIndicesGlobales(Integer ano, Integer mes) {
        return this.subRegionDAO.ejecutarIndicesGlobales(ano,mes);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.SubRegionBO#getSubRegionesPorOficinaPorSubEstacionSubRegion(java.lang.Integer, java.lang.Integer)
     */
    public List getSubRegionesPorOficinaPorSubEstacionSubRegion(Integer oficina, Integer region) {
        return this.subRegionDAO.getSubRegionesPorOficinaPorSubEstacionSubRegion(oficina,region);
    }
}
