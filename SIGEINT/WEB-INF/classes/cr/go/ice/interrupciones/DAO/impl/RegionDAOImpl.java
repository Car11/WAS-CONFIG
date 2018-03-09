package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import cr.go.ice.interrupciones.DAO.RegionDAO;
import cr.go.ice.interrupciones.domain.Region;
import cr.go.ice.interrupciones.domain.Seccion;
import cr.go.ice.interrupciones.domain.procedure.Pevemq_RegGlobal;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.RegionDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>RegionDAOImpl.java</code>Define los metodos de los Datos para Region</p>
 * <p>Fecha creación: 26/08/2007</p>
 * <p>Ultima actualización: 26/08/2007</p>
 * @author Vista Verde Tecnologia (Nano y Doc Cristian)
 * @version 1.1
 */
public class RegionDAOImpl extends HibernateDaoSupport implements RegionDAO {

        
	/**
	 * Constructor de la clase: RegionDAOImpl
	 * 
	 */
	public RegionDAOImpl(){}
	
    private DataSource ds;    	

    /**
     * Metodo modificador de ds.
     * @param ds El ds a modificar.
     */
    public void setDs(DataSource ds) {
        this.ds = ds;
    }	
	
	/**
	 * @see cr.go.ice.interrupciones.DAO.RegionDAO#agregar(cr.go.ice.interrupciones.domain.Region)
	 */
	public void agregar(Region region) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.save(region);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.RegionDAO#modificar(cr.go.ice.interrupciones.domain.Region)
	 */
	public void modificar(Region region) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.update(region);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.RegionDAO#eliminar(cr.go.ice.interrupciones.domain.Region)
	 */
	public void eliminar(Region region) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.delete(region);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.RegionDAO#buscar(java.lang.Integer)
	 */
	public Region buscar(Integer codigoRegion) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		Region region = null;
		
		String hql = "from Region r where r.region = ?";
		Object[] values = {codigoRegion};
		List resultado = hibernate.find(hql, values);
		if(resultado.size() > 0){
			region = (Region) resultado.get(0);
		}
		return region;
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.RegionDAO#getRegiones()
	 */
	public List getRegiones() {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Region region ORDER BY region.region";
		return hibernate.find(hql);
	}
	
    /**
     * @see cr.go.ice.interrupciones.DAO.RegionDAO#getRegiones(java.lang.Integer)
     */
    public List getRegiones(Integer orden) {
        List regiones = null;
        
        if(orden!=null && orden.equals(Region.ORDEN_NOMBRE)){
    	    HibernateTemplate hibernate = this.getHibernateTemplate();
    		String hql = "from Region region ORDER BY region.nombreRegion";
    		regiones = hibernate.find(hql);    
        }else{
            regiones = this.getRegiones();
        }
        return regiones;
    }
	/**
	 * @see cr.go.ice.interrupciones.DAO.RegionDAO#existe(cr.go.ice.interrupciones.domain.Region)
	 */
	public boolean existe(Region region) {
	    return this.buscar(region.getRegion())!=null?true:false;
	}

    /**
     * @see cr.go.ice.interrupciones.DAO.RegionDAO#buscarPorOficina(java.lang.Integer)
     */
    public Region buscarPorOficina(Integer codigoOficina) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		Region regionRes = null;
		
		String hql = "from Seccion seccion where seccion.codigoOficina = ?";
		Object[] values = {codigoOficina};

		List secciones = hibernate.find(hql, values);
		if(secciones.size() > 0){
		    Seccion miSeccion = (Seccion)secciones.get(0);
		    Integer region = miSeccion.getRegion();
			hql = "from Region region where region.region = ?";
			values = null;
			Object[] values2 = {region};
			
			List regiones = hibernate.find(hql, values2);
			if(regiones.size()>0){
			    regionRes = (Region) regiones.get(0);
			}
		}
		return regionRes;
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.RegionDAO#getRegionesPorOficina(java.lang.Integer)
     */
    public List getRegionesPorOficina(Integer oficina) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "Select DISTINCT r from Region r, Seccion s " +
				"where s.codigoOficina = ? " +
				" AND s.region = r.region " +
				"ORDER BY r.region";
		Object[] values = {oficina};
		return hibernate.find(hql, values);
    }
    /**
     * @see cr.go.ice.interrupciones.DAO.RegionDAO#getRegionesPorOficina(java.lang.Integer)
     */
    public List getRegionesActivasPorOficina(Integer oficina) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "Select DISTINCT r from Region r, Seccion s " +
				"where s.codigoOficina = ? " +
				" AND (s.region = r.region) " +
				" AND (r.kmsRegion!=0 AND r.abonadoRegion!=0) " +
				"ORDER BY r.region";
		Object[] values = {oficina};
		return hibernate.find(hql, values);
    }
       
    /**
     * @see cr.go.ice.interrupciones.DAO.RegionDAO#ejecutarIndicesGlobales(java.lang.Integer, java.lang.Integer)
     */
    public String ejecutarIndicesGlobales(Integer ano, Integer mes) {
        try{         
            Pevemq_RegGlobal proc = new Pevemq_RegGlobal(this.ds);            
            String resultado = proc.execute(ano, mes);
	        return resultado;  
        }        
        catch(Exception e){
            return "N";
        }
    }
    
    /**
     * @see cr.go.ice.interrupciones.DAO.RegionDAO#getRegionesPorOficinaPorSubEstacionSubRegion(java.lang.Integer)
     */
    public List getRegionesPorOficinaPorSubEstacionSubRegion(Integer oficina) {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "Select DISTINCT reg from Region reg, SubEstacionSubRegion sub " +
                "where (sub.subEstacionSubRegionID.oficina.codigoOficina = ?) " +
                " AND (sub.subEstacionSubRegionID.subRegion.subRegionID.region.region = reg.region) " +
                "ORDER BY reg.region";
        Object[] values = {oficina};
        return hibernate.find(hql, values);
    }

}
