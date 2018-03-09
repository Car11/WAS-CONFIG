
package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;


import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import cr.go.ice.interrupciones.DAO.ConsecutivoClorDAO;
import cr.go.ice.interrupciones.domain.ConsecutivoClor;
import cr.go.ice.interrupciones.domain.ConsecutivoClorID;


/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.ConsecutivoClorDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ConsecutivoClorDAOImpl.java</code>Define los metodos de los Datos para ConsecutivoClor.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class ConsecutivoClorDAOImpl  extends HibernateDaoSupport  implements ConsecutivoClorDAO{



	/**
	 * @see cr.go.ice.interrupciones.DAO.ConsecutivoClorDAO#agregar(cr.go.ice.interrupciones.domain.ConsecutivoClor)
	 */
	public void agregar(ConsecutivoClor consecutivoClor) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.save(consecutivoClor);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.ConsecutivoClorDAO#modificar(cr.go.ice.interrupciones.domain.ConsecutivoClor)
	 */
	public void modificar(ConsecutivoClor consecutivoClor) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.update(consecutivoClor);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.ConsecutivoClorDAO#eliminar(cr.go.ice.interrupciones.domain.ConsecutivoClor)
	 */
	public void eliminar(ConsecutivoClor consecutivoClor) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.delete(consecutivoClor);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.ConsecutivoClorDAO#buscar(cr.go.ice.interrupciones.domain.ConsecutivoClor)
	 */
	public ConsecutivoClor buscar(ConsecutivoClor consecutivoClor) {        
        ConsecutivoClor consecutivoClorResultado = null;
        ConsecutivoClorID consecutivoClorID = consecutivoClor.getConsecutivoClorID();
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from ConsecutivoClor WHERE consecutivoClorID.ano = ? AND consecutivoClorID.codigoOficina = ?";
		Object[] values = {consecutivoClorID.getAno(), consecutivoClorID.getCodigoOficina()};
		List consecutivos = hibernate.find(hql, values);
		if(consecutivos.size() > 0){
		    consecutivoClorResultado = (ConsecutivoClor) consecutivos.get(0);
		}
		return consecutivoClorResultado;            
        
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.ConsecutivoClorDAO#getConsecutivosClor()
	 */
	public  List getConsecutivosClor() {		
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "FROM ConsecutivoClor";
		List consecutivos = hibernate.find(hql);
		return consecutivos;	   		
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.ConsecutivoClorDAO#consecutivoClorExiste(cr.go.ice.interrupciones.domain.ConsecutivoClor)
	 */
	public boolean consecutivoClorExiste(ConsecutivoClor consecutivoClor) {
		return (this.buscar(consecutivoClor) != null); 
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.ConsecutivoClorDAO#getConsecutivoClor()
	 */
	public ConsecutivoClor getConsecutivoClor(Integer codigoOficina, Integer ano) {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        ConsecutivoClor consecutivoClor = null;
        String hql = "FROM ConsecutivoClor consecutivo where consecutivo.consecutivoClorID.ano = ? AND " +
                "consecutivo.consecutivoClorID.codigoOficina = ?";
        Object[] values = {ano, codigoOficina};
        List consecutivos = hibernate.find(hql, values);
        if(consecutivos.size() > 0){
            consecutivoClor = (ConsecutivoClor)consecutivos.get(0);
        }
        return consecutivoClor;   			
		
	}
    
    
	
}
