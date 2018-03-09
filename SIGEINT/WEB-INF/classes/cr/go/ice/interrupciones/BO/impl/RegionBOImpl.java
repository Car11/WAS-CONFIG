package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.RegionBO;
import cr.go.ice.interrupciones.DAO.RegionDAO;
import cr.go.ice.interrupciones.domain.Region;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.RegionBOImpl.java</p>
 * <p>Modulo (subsistema): InterrupcionesWeb</p>
 * <p>Descricion de <code>RegionBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para Region.</p>
 * <p>Fecha creación: 03/04/2007</p>
 * <p>Ultima actualización: 03/04/2007</p>
 * @author Vista Verde Soft (Cristian)
 * @version 1.1
 */
public class RegionBOImpl implements RegionBO {
	
	/**
	 * <code>regionDAO</code> Region DAO
	 */
	private RegionDAO regionDAO;

	
	/**
	 * Asigna setRegionDAO
	 * @param regionDAO
	 */
	public void setRegionDAO(RegionDAO regionDAO) {
		this.regionDAO = regionDAO;
	}
	

	/**
	 * @see cr.go.ice.interrupciones.BO.RegionBO#agregar(cr.go.ice.interrupciones.domain.Region)
	 */
	public void agregar(Region region) {
		this.regionDAO.agregar(region);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.RegionBO#modificar(cr.go.ice.interrupciones.domain.Region)
	 */
	public void modificar(Region region) {
		this.regionDAO.modificar(region);

	}

	/**
	 * @see cr.go.ice.interrupciones.BO.RegionBO#eliminar(cr.go.ice.interrupciones.domain.Region)
	 */
	public void eliminar(Region region) {
		this.regionDAO.eliminar(region);

	}

	/**
	 * @see cr.go.ice.interrupciones.BO.RegionBO#buscar(java.lang.Integer)
	 */
	public Region buscar(Integer codigoRegion) {
		Region reg = this.regionDAO.buscar(codigoRegion);
		return reg;
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.RegionBO#existe(cr.go.ice.interrupciones.domain.Region)
	 */
	public boolean existe(Region region) {
		boolean existe = this.regionDAO.existe(region);
		return existe;
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.RegionBO#getRegiones()
	 */
	public List getRegiones() {
		List regiones = this.regionDAO.getRegiones();
		return regiones;
	}
	
    /**
     * @see cr.go.ice.interrupciones.BO.RegionBO#getRegiones(java.lang.Integer)
     */
    public List getRegiones(Integer orden) {
        return this.regionDAO.getRegiones(orden);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.RegionBO#buscarPorOficina(java.lang.Integer)
     */
    public Region buscarPorOficina(Integer codigoOficina){
        return this.regionDAO.buscarPorOficina(codigoOficina);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.RegionBO#getRegionesPorOficina(java.lang.Integer)
     */
    public List getRegionesPorOficina(Integer oficina) {
        return this.regionDAO.getRegionesPorOficina(oficina);
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.RegionBO#getRegionesPorOficina(java.lang.Integer)
     */
    public List getRegionesActivasPorOficina(Integer oficina) {
        return this.regionDAO.getRegionesActivasPorOficina(oficina);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.RegionBO#ejecutarIndicesGlobales(java.lang.Integer, java.lang.Integer)
     */
    public String ejecutarIndicesGlobales(Integer ano, Integer mes) {
        return this.regionDAO.ejecutarIndicesGlobales(ano,mes);
    }


    /**
     * @see cr.go.ice.interrupciones.BO.RegionBO#getRegionesPorOficinaPorSubEstacionSubRegion(java.lang.Integer)
     */
    public List getRegionesPorOficinaPorSubEstacionSubRegion(Integer oficina) {
        return this.regionDAO.getRegionesPorOficinaPorSubEstacionSubRegion(oficina);
    }	
}
