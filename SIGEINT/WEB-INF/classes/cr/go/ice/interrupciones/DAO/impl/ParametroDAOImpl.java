package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.ParametroDAO;
import cr.go.ice.interrupciones.domain.Parametro;
/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.ParametroDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ParametroDAOImpl.java</code>Define los metodos de los Datos para Parametro.</p>
 * <p>Fecha creación: 22/01/2007</p>
 * <p>Ultima actualización: 22/01/2007</p>
 * @author Vista Verde Tecnologia (Administrador y Doc Cristian)
 * @version 1.1
 */
public class ParametroDAOImpl  extends HibernateDaoSupport implements ParametroDAO{

	
	
	/**
	 * @see cr.go.ice.interrupciones.DAO.ParametroDAO#modificar(cr.go.ice.interrupciones.domain.Parametro, cr.go.ice.interrupciones.domain.Parametro)
	 */
	public void modificar(Parametro parametroNuevo, Parametro parametroViejo) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.delete(parametroViejo);
		hibernate.save(parametroNuevo);
		
	}


	/**
	 * @see cr.go.ice.interrupciones.DAO.ParametroDAO#getParametros()
	 */
	public List getParametros() {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Parametro";
		List parametros = hibernate.find(hql);
		return parametros;	        
        
	}

	
}
