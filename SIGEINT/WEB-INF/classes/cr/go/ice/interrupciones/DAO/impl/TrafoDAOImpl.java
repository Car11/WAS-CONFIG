package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cr.go.ice.interrupciones.DAO.TrafoDAO;
import cr.go.ice.interrupciones.domain.Trafo;
import cr.go.ice.interrupciones.domain.TrafoID;

/**
 * <p>Clase cr.go.ice.obras.DAO.impl.TrafoDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>TrafoDAOImpl.java</code>Define los metodos de los Datos para Trafo.</p>
 * <p>Fecha creación: 24/03/2007</p>
 * <p>Ultima actualización: 24/03/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class TrafoDAOImpl extends HibernateDaoSupport implements TrafoDAO{
	
	/**
	 * Constructor de la clase: TrafoDAOImpl
	 */
	public TrafoDAOImpl(){}

	/**
	 * @see cr.go.ice.interrupciones.DAO.TrafoDAO#agregar(cr.go.ice.interrupciones.domain.Trafo)
	 */
	public void agregar(Trafo trafo) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
	    hibernate.save(trafo);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.TrafoDAO#modificar(cr.go.ice.interrupciones.domain.Trafo)
	 */
	public void modificar(Trafo trafo) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.update(trafo);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.TrafoDAO#eliminar(cr.go.ice.interrupciones.domain.Trafo)
	 */
	public void eliminar(Trafo trafo) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
         hibernate.delete(trafo);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.TrafoDAO#getTrafo(cr.go.ice.interrupciones.domain.TrafoID)
	 */
	public Trafo getTrafo(TrafoID trafoID) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		Trafo trafo = null;

		String hql = "from Trafo t " +
				"WHERE t.trafoID.aa = ? " +
				"AND t.trafoID.codigoOficina = ? " +
				"AND t.trafoID.numeroInterrupcion = ? " +
				"AND t.trafoID.numeroMovimiento = ? ";	
		Object[] values = {trafoID.getAa(), trafoID.getCodigoOficina(), trafoID.getNumeroInterrupcion(), trafoID.getNumeroMovimiento()};
		
		List resultado = hibernate.find(hql, values);
		
		if(resultado.size()>0){
			trafo = (Trafo) resultado.get(0);
		}
		return trafo;
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.TrafoDAO#getTrafos()
	 */
	public List getTrafos() {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Trafo";
		return hibernate.find(hql);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.TrafoDAO#trafoExiste(cr.go.ice.interrupciones.domain.TrafoID)
	 */
	public boolean trafoExiste(TrafoID trafoID) {
		return (this.getTrafo(trafoID) != null)? true : false;  
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.TrafoDAO#agregar(java.util.List)
	 */
	public void agregar(List trafos) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
        if(trafos != null){
	        for(int i = 0; i < trafos.size(); i++){
	        	Trafo trafo = (Trafo)trafos.get(i);
	        	hibernate.save(trafo);
	        }
        }
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.TrafoDAO#getTrafos(cr.go.ice.interrupciones.domain.TrafoID)
	 */
	public List getTrafos(TrafoID trafoID) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		List trafos = null;

		String hql = "from Trafo t " +
				"where t.trafoID.aa = ? " +
				"AND t.trafoID.codigoOficina = ? " +
				"AND trafoID.numeroInterrupcion = ?";
	
		Object[] values = {trafoID.getAa(), trafoID.getCodigoOficina(), trafoID.getNumeroInterrupcion()};
		List resultado = hibernate.find(hql, values);
		if(resultado.size() > 0){
			trafos = resultado;			
		}
		return trafos;
	}
  
	/**
	 * @see cr.go.ice.interrupciones.DAO.TrafoDAO#modificar(java.util.List, cr.go.ice.interrupciones.domain.TrafoID)
	 */
	public void modificar(List trafo, TrafoID trafoID) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
			
        String hql = "from Trafo t " +
        		"where t.trafoID.aa = ? " +
        		"AND t.trafoID.codigoOficina = ? " +
        		"AND t.trafoID.numeroInterrupcion = ? ";		
		Object[] values = {trafoID.getAa(), trafoID.getCodigoOficina(), trafoID.getNumeroInterrupcion()};
		hibernate.deleteAll(hibernate.find(hql,values));

		// agregamos los nuevo detalles		
        for(int i = 0; i < trafo.size(); i++){
        	Trafo trafoNuevo = (Trafo)trafo.get(i);
        	hibernate.save(trafoNuevo);
        }		
	}	
}
