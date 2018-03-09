
package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.FusibleDAO;
import cr.go.ice.interrupciones.domain.Fusible;
import cr.go.ice.interrupciones.domain.FusibleID;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.FusibleDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>FusibleDAOImpl.java</code>Define los metodos de los Datos para Fusible.</p>
 * <p>Fecha creación: 26/02/2007</p>
 * <p>Ultima actualización: 26/02/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class FusibleDAOImpl  extends HibernateDaoSupport implements FusibleDAO{



	/**
	 * @see cr.go.ice.interrupciones.DAO.FusibleDAO#agregar(cr.go.ice.interrupciones.domain.Fusible)
	 */
	public void agregar(Fusible fusible) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.save(fusible);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.FusibleDAO#modificar(cr.go.ice.interrupciones.domain.Fusible)
	 */
	public void modificar(Fusible fusible) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.update(fusible);		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.FusibleDAO#eliminar(cr.go.ice.interrupciones.domain.Fusible)
	 */
	public void eliminar(Fusible fusible) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.delete(fusible);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.FusibleDAO#buscar(cr.go.ice.interrupciones.domain.Fusible)
	 */
	public Fusible buscar(Fusible fusible) {		

        Fusible fusibleRes = null;
        FusibleID fusibleID = fusible.getFusibleID();
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Fusible fusible WHERE fusible.fusibleID.aa = ? AND " +
				"fusible.fusibleID.codigoOficina = ? AND " +
				"fusible.fusibleID.numeroInterrupcion = ? AND " +
				"fusible.fusibleID.numeroMovimiento = ?";		
		Object[] values = {fusibleID.getAa(),
		        fusibleID.getCodigoOficina(),
		        fusibleID.getNumeroInterrupcion(),
		        fusibleID.getNumeroMovimiento()};
		List fusibles = hibernate.find(hql, values);
		if(fusibles.size() > 0){
		    fusibleRes = (Fusible) fusibles.get(0);
		}
		return fusibleRes;          
        
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.FusibleDAO#getFusibles()
	 */
	public List getFusibles() {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Fusible";
		List  fusibles = hibernate.find(hql);
		return fusibles;	   		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.FusibleDAO#fusibleExiste(cr.go.ice.interrupciones.domain.Fusible)
	 */
	public boolean fusibleExiste(Fusible fusible) {
		return (this.buscar(fusible) != null);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.FusibleDAO#agregar(java.util.List)
	 */
	public void agregar(List fusibles) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
	    if(fusibles != null){
		    for(int i = 0;  i < fusibles.size(); i++){
		    	Fusible fusible = (Fusible)fusibles.get(i);
		    	hibernate.save(fusible);
		    }
	    }
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.FusibleDAO#getFusibles(cr.go.ice.interrupciones.domain.FusibleID)
	 */
	public List getFusibles(FusibleID fusibleID) {
		Integer aa = fusibleID.getAa();
		Integer codigoOficina = fusibleID.getCodigoOficina();
		Long numeroInterrupcion = fusibleID.getNumeroInterrupcion();
		HibernateTemplate hibernate = this.getHibernateTemplate();

		String hql = "from Fusible where fusibleID.aa = ? AND fusibleID.codigoOficina = ? AND " +
				"fusibleID.numeroInterrupcion = ?";		
		Object[] values = {aa, 
		        codigoOficina,
		        numeroInterrupcion};
		List fusibles = hibernate.find(hql, values);		    
		return fusibles;  	  		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.FusibleDAO#modificar(java.util.List, cr.go.ice.interrupciones.domain.FusibleID)
	 */
	public void modificar(List fusible, FusibleID fusibleID) {
        
        Integer aa = fusibleID.getAa();
		Integer codigoOficina = fusibleID.getCodigoOficina();
		Long numeroInterrupcion = fusibleID.getNumeroInterrupcion();		
		HibernateTemplate hibernate = this.getHibernateTemplate();

		String hql = "from Fusible fusible where fusible.fusibleID.aa = ? AND " +		
		"fusible.fusibleID.codigoOficina = ? AND " +
		"fusible.fusibleID.numeroInterrupcion = ?";		
		Object[] values = {aa, 
		        codigoOficina,
		        numeroInterrupcion};
		hibernate.deleteAll(hibernate.find(hql,values));
		//		 agregamos los nuevo detalles	
        for(int i = 0; i < fusible.size(); i++){
        	Fusible fusibleNuevo = (Fusible)fusible.get(i);
        	hibernate.save(fusibleNuevo);
        }		       
	}
	
}
