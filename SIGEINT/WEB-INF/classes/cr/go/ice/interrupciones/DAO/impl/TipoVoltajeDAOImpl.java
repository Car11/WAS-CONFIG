package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cr.go.ice.interrupciones.DAO.TipoVoltajeDAO;
import cr.go.ice.interrupciones.domain.TipoVoltaje;
import cr.go.ice.interrupciones.domain.TipoVoltajeID;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.TipoVoltajeDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>TipoVoltajeDAOImpl.java</code>Define los metodos de los Datos para TipoVoltaje.</p>
 * <p>Fecha creación: 02/04/2007</p>
 * <p>Ultima actualización: 02/04/2007</p>
 * @author Vista Verde Tecnologia (Nano Leon y Doc Cristian)
 * @version 1.1
 */
public class TipoVoltajeDAOImpl extends HibernateDaoSupport implements TipoVoltajeDAO {

    /**
     * Constructor  
     */
	public TipoVoltajeDAOImpl(){}

	/**
	 * @see cr.go.ice.interrupciones.DAO.TipoVoltajeDAO#agregar(cr.go.ice.interrupciones.domain.TipoVoltaje)
	 */
	public void agregar(TipoVoltaje tipoVoltaje) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.save(tipoVoltaje);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.TipoVoltajeDAO#modificar(cr.go.ice.interrupciones.domain.TipoVoltaje)
	 */
	public void modificar(TipoVoltaje tipoVoltaje) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.update(tipoVoltaje);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.TipoVoltajeDAO#eliminar(cr.go.ice.interrupciones.domain.TipoVoltaje)
	 */
	public void eliminar(TipoVoltaje tipoVoltaje) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.delete(tipoVoltaje);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.TipoVoltajeDAO#buscar(cr.go.ice.interrupciones.domain.TipoVoltajeID)
	 */
	public TipoVoltaje buscar(TipoVoltajeID tipoVoltajeID) {
		TipoVoltaje tipoVoltaje = null;
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from TipoVoltaje t " +
				"where t.tipoVoltajeID.codigoVoltaje = ? " +
				"AND t.tipoVoltajeID.tipoVoltaje = ? ";
		Object[] values = {tipoVoltajeID.getCodigoVoltaje(), tipoVoltajeID.getTipoVoltaje()};
		List tiposVoltaje = hibernate.find(hql, values);
		if(tiposVoltaje.size() > 0){
			tipoVoltaje = (TipoVoltaje) tiposVoltaje.get(0);
		}else{
			tipoVoltaje = null;
		}
		return tipoVoltaje;
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.TipoVoltajeDAO#getTiposVoltaje()
	 */
	public List getTiposVoltaje() {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from TipoVoltaje t ORDER BY t.tipoVoltajeID.codigoVoltaje, t.tipoVoltajeID.tipoVoltaje";
		return hibernate.find(hql);
	}
	
	/**
	 * @see cr.go.ice.interrupciones.DAO.TipoVoltajeDAO#existe(cr.go.ice.interrupciones.domain.TipoVoltaje)
	 */
	public boolean existe(TipoVoltaje tipoVoltaje) {
		return this.buscar(tipoVoltaje.getTipoVoltajeID())!=null?true:false;	
	}

    /**
     * @see cr.go.ice.interrupciones.DAO.TipoVoltajeDAO#registrosAsociados(cr.go.ice.interrupciones.domain.TipoVoltajeID)
     */
    public Long registrosAsociados(TipoVoltajeID tipoVoltajeID) {
        Long cantidad = new Long(0);
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "SELECT COUNT(*) from Interrupcion interup WHERE " +
        "interup.codigoVoltaje = ? " +
        "AND interup.tipoVoltaje = ?";
        
        
        Object values [] = {tipoVoltajeID.getCodigoVoltaje(), tipoVoltajeID.getTipoVoltaje()};
        List interrupciones = hibernate.find(hql, values);
        
        if(interrupciones.size() > 0){
            cantidad = (Long)interrupciones.get(0);             
        }
        
        hql = "SELECT COUNT(*) from Reporte rep WHERE " +
        "rep.codigoVoltaje = ? " +
        "AND rep.tipoVoltaje = ?";
        
        Object values2 [] = {tipoVoltajeID.getCodigoVoltaje(), tipoVoltajeID.getTipoVoltaje()};
        interrupciones = hibernate.find(hql, values2);
        
        if(interrupciones.size() > 0){
            Long cantidadRep = (Long)interrupciones.get(0);
            cantidad = new Long(cantidad.longValue() + cantidadRep.longValue());                
        }       
        
        return cantidad;   
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.TipoVoltajeDAO#getTiposVoltajeOrdenPorTipoVoltaje()
     */
    public List getTiposVoltajeOrdenPorTipoVoltaje() {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "from TipoVoltaje t ORDER BY t.tipoVoltajeID.tipoVoltaje";
        return hibernate.find(hql);
    }
	
}
