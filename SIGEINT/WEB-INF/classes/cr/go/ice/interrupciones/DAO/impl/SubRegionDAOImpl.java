package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cr.go.ice.interrupciones.DAO.SubRegionDAO;
import cr.go.ice.interrupciones.domain.Seccion;
import cr.go.ice.interrupciones.domain.SubRegion;
import cr.go.ice.interrupciones.domain.SubRegionID;
import cr.go.ice.interrupciones.domain.procedure.PevemqSubRegGlobal;
/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.SubRegionDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SubRegionDAOImpl.java</code>Define los metodos de los Datos para SubRegion.</p>
 * <p>Fecha creación: 22/02/2007</p>
 * <p>Ultima actualización: 22/02/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class SubRegionDAOImpl extends HibernateDaoSupport implements SubRegionDAO{
    
    private DataSource ds;
    
    /**
     * Constructor de la clase: SubRegionDAOImpl
     */
    public SubRegionDAOImpl(){}
    

    /**
     * Metodo modificador de ds.
     * @param ds El ds a modificar.
     */
    public void setDs(DataSource ds) {
        this.ds = ds;
    }    

	/**
	 * @see cr.go.ice.interrupciones.DAO.SubRegionDAO#agregar(cr.go.ice.interrupciones.domain.SubRegion)
	 */
	public void agregar(SubRegion subRegion){
	    HibernateTemplate hibernate = this.getHibernateTemplate();
	    hibernate.save(subRegion);
	}

    /**
     * @see cr.go.ice.interrupciones.DAO.SubRegionDAO#modificar(cr.go.ice.interrupciones.domain.SubRegion)
     */
    public void modificar(SubRegion subRegion){
	    HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.update(subRegion);
    }
    
    /**
     * @see cr.go.ice.interrupciones.DAO.SubRegionDAO#eliminar(cr.go.ice.interrupciones.domain.SubRegion)
     */
    public void eliminar(SubRegion subRegion){
	    HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.delete(subRegion);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.SubRegionDAO#buscar(cr.go.ice.interrupciones.domain.SubRegionID)
     */
    public SubRegion buscar(SubRegionID subRegionId){
	    HibernateTemplate hibernate = this.getHibernateTemplate();
	    SubRegion subRegion = null;
	    
        String hql = "from SubRegion s " +
        		"WHERE s.subRegionID.region.region = ? " +
        		"AND s.subRegionID.subRegion = ?";
        Object[] values = {subRegionId.getRegion().getRegion(), subRegionId.getSubRegion()};
        
        List resultado = hibernate.find(hql, values);
        if(resultado.size()>0){
            subRegion = (SubRegion) resultado.get(0);
        }
        return subRegion;
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.SubRegionDAO#getSubRegiones()
     */
    public List getSubRegiones(){
	    HibernateTemplate hibernate = this.getHibernateTemplate();
	    
        String hql = "FROM SubRegion";
        return hibernate.find(hql);
    }
    
    /**
     * @see cr.go.ice.interrupciones.DAO.SubRegionDAO#getSubRegiones(java.lang.Integer)
     */
    public List getSubRegiones(Integer region){
	    HibernateTemplate hibernate = this.getHibernateTemplate();

	    String hql = "from SubRegion sr where sr.subRegionID.region.region = ? ORDER BY sr.subRegionID.subRegion";
        Object[] values = {region};
        
        return hibernate.find(hql, values);
    }
    
    /**
     * @see cr.go.ice.interrupciones.DAO.SubRegionDAO#getSubRegiones(java.lang.Integer)
     */
    public List getSubRegionesActivas(Integer region){
	    HibernateTemplate hibernate = this.getHibernateTemplate();

	    String hql = "from SubRegion sr where sr.subRegionID.region.region = ?  AND (sr.kmsSubRegion!=0 AND sr.abonadoSubregion!=0) ORDER BY sr.subRegionID.subRegion";
        Object[] values = {region};
        
        return hibernate.find(hql, values);
    }

    
    
    /**
     * @see cr.go.ice.interrupciones.DAO.SubRegionDAO#getSubRegiones(java.lang.Integer, java.lang.Integer)
     */
    public List getSubRegiones(Integer region, Integer orden) {
        List subregiones = null;
        if(orden!=null && orden.equals(SubRegion.ORDEN_NOMBRE)){
    	    HibernateTemplate hibernate = this.getHibernateTemplate();
    	    String hql = "from SubRegion sr where sr.subRegionID.region.region = ? ORDER BY sr.nombreSubRegion";
            Object[] values = {region};            
            subregiones = hibernate.find(hql, values);    
        }else{
            subregiones = this.getSubRegiones(region);
        }

        return subregiones;
    }
    /**
     * @see cr.go.ice.interrupciones.DAO.SubRegionDAO#subRegionExiste(cr.go.ice.interrupciones.domain.SubRegionID)
     */
    public boolean subRegionExiste(SubRegionID subRegionID){
        return this.buscar(subRegionID)!=null?true:false;
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.SubRegionDAO#buscarPorOficina(java.lang.Integer)
     */
    public SubRegion buscarPorOficina(Integer codigoOficina) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
	    SubRegion subRegionRes = null;
	   
		String hql = "from Seccion s where s.codigoOficina = ?";
		Object[] values = {codigoOficina};
		List secciones = hibernate.find(hql, values);
		if(secciones.size() > 0){
		    Seccion miSeccion = (Seccion) secciones.get(0);
			hql = "from SubRegion s " +
					"where s.subRegionID.region.region = ? " +
					"AND s.subRegionID.subRegion = ?";
            Object[] valuesSubRegion = {miSeccion.getRegion(),miSeccion.getSubRegion()};
			List subregiones = hibernate.find(hql, valuesSubRegion);	
			if(subregiones.size()>0){
			    subRegionRes = (SubRegion) subregiones.get(0);
			}
		}
		return subRegionRes;
    }

   
    /**
     * @see cr.go.ice.interrupciones.DAO.SubRegionDAO#getSubRegionesPorOficina(java.lang.Integer, java.lang.Integer)
     */
    public List getSubRegionesPorOficina(Integer oficina, Integer region) {
        HibernateTemplate hibernate = this.getHibernateTemplate();    
        String hql = "Select DISTINCT sr " +
        "from SubRegion sr, Seccion sec " +
        "where sec.codigoOficina = ? " +
        "AND sec.region = sr.subRegionID.region.region " +
        "AND sr.subRegionID.region.region = ? " +
        "ORDER BY sr.subRegionID.subRegion";
        Object[] values = {oficina, region};
        return hibernate.find(hql, values);
    }
     
    /**
     * @see cr.go.ice.interrupciones.DAO.SubRegionDAO#ejecutarIndicesGlobales(java.lang.Integer, java.lang.Integer)
     */
    public String ejecutarIndicesGlobales(Integer ano, Integer mes) {
        try{
	        PevemqSubRegGlobal procedimiento = new PevemqSubRegGlobal(ds);
	        return procedimiento.execute(ano, mes);
        }
        catch(Exception e){
            return "N";
        }
    }


    /**
     * @see cr.go.ice.interrupciones.DAO.SubRegionDAO#getSubRegionesPorOficinaPorSubEstacionSubRegion(java.lang.Integer, java.lang.Integer)
     */
    public List getSubRegionesPorOficinaPorSubEstacionSubRegion(Integer oficina, Integer region) {
        HibernateTemplate hibernate = this.getHibernateTemplate();    
        
        String hql = "Select DISTINCT sr " +
        "from SubRegion sr, SubEstacionSubRegion sub " +
        "where (sub.subEstacionSubRegionID.oficina.codigoOficina = ?) " +
        "AND (sub.subEstacionSubRegionID.subRegion.subRegionID.region.region = ?) " +
        "AND (sub.subEstacionSubRegionID.subRegion.subRegionID.region.region = sr.subRegionID.region.region) " +
        "AND (sub.subEstacionSubRegionID.subRegion.subRegionID.subRegion = sr.subRegionID.subRegion) " +
        "ORDER BY sr.subRegionID.subRegion";
        
        Object[] values = {oficina, region};
        return hibernate.find(hql, values);
    }
}
