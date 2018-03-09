package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.GlosarioDAO;
import cr.go.ice.interrupciones.domain.Glosario;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.GlosarioDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>GlosarioDAOImpl.java</code>Define los metodos de los Datos para Glosario</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Tecnologia (Cesar y Doc Cristian)
 * @version 1.1
 */
public class GlosarioDAOImpl  extends HibernateDaoSupport implements GlosarioDAO {


	/**
	 * @see cr.go.ice.interrupciones.DAO.GlosarioDAO#agregar(cr.go.ice.interrupciones.domain.Glosario)
	 */
	public void agregar(Glosario glosario) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.save(glosario);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.GlosarioDAO#modificar(cr.go.ice.interrupciones.domain.Glosario)
	 */
	public void modificar(Glosario glosario) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.update(glosario);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.GlosarioDAO#eliminar(cr.go.ice.interrupciones.domain.Glosario)
	 */
	public void eliminar(Glosario glosario) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.delete(glosario);

	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.GlosarioDAO#buscar(java.lang.String)
	 */
	public Glosario buscar(String termino) {        
        Glosario glosario = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Glosario WHERE termino = ?";
		Object[] values = {termino};
		List glosarios = hibernate.find(hql, values);
		if(glosarios.size()>0){
		    glosario = (Glosario) glosarios.get(0);
		}
		return glosario;            
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.GlosarioDAO#getGlosarios()
	 */
	public List getGlosarios() {					
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Glosario order by termino";
		List terminos = hibernate.find(hql);
		return terminos;		
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.GlosarioDAO#glosarioExiste(java.lang.String)
	 */
	public boolean glosarioExiste(String termino) {
	    return (this.buscar(termino) != null);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.GlosarioDAO#existe(cr.go.ice.interrupciones.domain.Glosario)
	 */
	public boolean existe(Glosario glosario) {
	    return (this.buscar(glosario.getTermino()) != null);
	}

}
