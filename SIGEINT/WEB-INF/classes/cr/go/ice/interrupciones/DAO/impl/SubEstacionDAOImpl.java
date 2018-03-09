package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cr.go.ice.interrupciones.DAO.SubEstacionDAO;
import cr.go.ice.interrupciones.domain.SubEstacion;
import cr.go.ice.interrupciones.domain.procedure.PevemqSubGlobal;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.SubEstacionDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SubEstacionDAOImpl.java</code>Define los metodos de los Datos para SubEstacion.</p>
 * <p>Fecha creación: 01/02/2007</p>
 * <p>Ultima actualización: 01/02/2007</p>
 * @author Vista Verde Tecnologia (Mario Leon y Doc Cristian)
 * @version 1.1
 */
public class SubEstacionDAOImpl extends HibernateDaoSupport implements SubEstacionDAO {
    
    private DataSource ds;
    /**
     * Constructor de la clase: SubEstacionDAOImpl
     * 
     */
    public SubEstacionDAOImpl(){}
    
    /**
     * Metodo modificador de ds.
     * @param ds El ds a modificar.
     */
    public void setDs(DataSource ds) {
        this.ds = ds;
    }    

    /**
     * @see cr.go.ice.interrupciones.DAO.SubEstacionDAO#agregar(cr.go.ice.interrupciones.domain.SubEstacion)
     */
    public void agregar(SubEstacion subEstacion) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.save(subEstacion);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.SubEstacionDAO#modificar(cr.go.ice.interrupciones.domain.SubEstacion)
     */
    public void modificar(SubEstacion subEstacion) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.update(subEstacion);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.SubEstacionDAO#eliminar(cr.go.ice.interrupciones.domain.SubEstacion)
     */
    public void eliminar(SubEstacion subEstacion) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.delete(subEstacion);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.SubEstacionDAO#buscar(java.lang.Integer)
     */
    public SubEstacion buscar(Integer codigo) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
        SubEstacion subEstacion = null;
        
        String hql = "from SubEstacion se " +
        		"WHERE se.codigoSubEstacion = ?";
        Object[] values = {codigo};
        
        List resultado = hibernate.find(hql, values);
        if(resultado.size() > 0){
        	subEstacion = (SubEstacion) resultado.get(0);
        }
        return subEstacion;
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.SubEstacionDAO#buscar(java.lang.String)
     */
    public SubEstacion buscar(String nombre) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
        SubEstacion subEstacion = null;
        
        String hql = "from SubEstacion se " +
        		"WHERE se.nombreSubEstacion = ?";
        Object[] values = {nombre.toLowerCase()};
      
        List resultado = hibernate.find(hql, values);
        
        if(resultado.size() > 0){
        	subEstacion = (SubEstacion) resultado.get(0);
        }
        return subEstacion;
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.SubEstacionDAO#getSubEstaciones()
     */
    public List getSubEstaciones() {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "from SubEstacion order by codigoSubEstacion";
        return hibernate.find(hql);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.SubEstacionDAO#subEstacionExiste(cr.go.ice.interrupciones.domain.SubEstacion)
     */
    public boolean subEstacionExiste(SubEstacion subEstacion) {
        return this.buscar(subEstacion.getCodigoSubEstacion())!=null?true:false;
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.SubEstacionDAO#getSubEstacionesSubRegionOficina(java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    public List getSubEstacionesSubRegionOficina(Integer codigoRegion, Integer codigoSubRegion, Integer codigoOficina){
	    HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "Select subEst from SubEstacion subEst, SubEstacionSubRegion subEstSubReg " +
        		"WHERE subEstSubReg.subEstacionSubRegionID.subEstacion = subEst.codigoSubEstacion " +
        		"AND subEstSubReg.subEstacionSubRegionID.subRegion.subRegionID.region.region = ? " +
        		"AND subEstSubReg.subEstacionSubRegionID.subRegion.subRegionID.subRegion = ? " +
				"AND subEstSubReg.subEstacionSubRegionID.oficina.codigoOficina = ?" +
        		"order by subEst.nombreSubEstacion";
        Object[] values = {codigoRegion, codigoSubRegion, codigoOficina};
 
        return hibernate.find(hql, values);
    }

	/**
	 * @see cr.go.ice.interrupciones.DAO.SubEstacionDAO#getSubEstaciones(java.lang.Integer)
	 */
	public List getSubEstaciones(Integer codigoOficina) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "SELECT DISTINCT subestacion FROM SubEstacion subestacion, Seccion seccion " +
        		"WHERE seccion.codigoOficina = ? " +
        		"AND seccion.seccionID.subEstacion = subestacion.codigoSubEstacion " +
        		"order by subestacion.codigoSubEstacion";
        Object[] values = {codigoOficina};
        return hibernate.find(hql, values);
	}


    /**
     * @see cr.go.ice.interrupciones.DAO.SubEstacionDAO#ejecutarIndicesGlobales(java.lang.Integer, java.lang.Integer)
     */
    public String ejecutarIndicesGlobales(Integer ano, Integer mes) {
        try{
	        PevemqSubGlobal procedimiento = new PevemqSubGlobal(ds);
	        return procedimiento.execute(ano, mes);
        }
        catch(Exception e){
            return "N";
        }
    }
    
    /**
     * @see cr.go.ice.interrupciones.DAO.SubEstacionDAO#getSubEstaciones(java.lang.Integer, java.lang.Integer)
     */
    public List getSubEstaciones(Integer region, Integer subregion) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "select subEst from SubEstacion subEst, SubEstacionSubRegion subEstSubReg " +
        		"WHERE (subEstSubReg.subEstacionSubRegionID.subEstacion = subEst.codigoSubEstacion) " +
        		"AND (subEstSubReg.subEstacionSubRegionID.subRegion.subRegionID.region.region = ?) " +
        		"AND (subEstSubReg.subEstacionSubRegionID.subRegion.subRegionID.subRegion = ?) " +
        		"order by subEst.nombreSubEstacion"; 
        Object[] values = {region, subregion};
        return hibernate.find(hql, values);
    }
    
    /**
     * @see cr.go.ice.interrupciones.DAO.SubEstacionDAO#getSubEstaciones(java.lang.Integer, java.lang.Integer)
     */
    public List getSubEstacionesActivas(Integer region, Integer subregion) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "select subEst from SubEstacion subEst, SubEstacionSubRegion subEstSubReg " +
        		"WHERE (subEstSubReg.subEstacionSubRegionID.subEstacion = subEst.codigoSubEstacion) " +
        		"AND (subEstSubReg.subEstacionSubRegionID.subRegion.subRegionID.region.region = ?) " +
        		"AND (subEstSubReg.subEstacionSubRegionID.subRegion.subRegionID.subRegion = ?) " +
        		"AND (subEst.kmsSubEstacion !=0 AND subEst.abonadoSubEstacion !=0) " +
        		"order by subEst.nombreSubEstacion"; 
        Object[] values = {region, subregion};
        return hibernate.find(hql, values);
    }

  
    /**
     * @see cr.go.ice.interrupciones.DAO.SubEstacionDAO#getSubEstacionesRegion(java.lang.Integer)
     */
    public List getSubEstacionesRegion(Integer codigoRegion) {
    	HibernateTemplate hibernate = this.getHibernateTemplate();        
        String hql = "Select distinct(subEst) from SubEstacion subEst, SubEstacionSubRegion subEstSubReg " +
        		"WHERE subEst.codigoSubEstacion = subEstSubReg.subEstacionSubRegionID.subEstacion " +
        		"AND subEstSubReg.subEstacionSubRegionID.subRegion.subRegionID.region.region = ? " +
        		"ORDER BY subEst.codigoSubEstacion";
        Object[] values = {codigoRegion};
        return hibernate.find(hql, values);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.SubEstacionDAO#getCodigoRegionSubEstacion(java.lang.Integer)
     */
    public Integer getCodigoRegionSubEstacion(Integer codigoSubEstacion) {
    	HibernateTemplate hibernate = this.getHibernateTemplate(); 
        Integer region = null;
        String hql = "select sesr.subEstacionSubRegionID.subRegion.subRegionID.region.region " +
        		"from SubEstacionSubRegion sesr " +
        		"WHERE sesr.subEstacionSubRegionID.subEstacion = ?";
        Object[] values = {codigoSubEstacion};
        List lista = hibernate.find(hql, values);
        if(lista.size() > 0){
        	region = (Integer) lista.get(0);
        }
        return region;
    }	
    
    
}