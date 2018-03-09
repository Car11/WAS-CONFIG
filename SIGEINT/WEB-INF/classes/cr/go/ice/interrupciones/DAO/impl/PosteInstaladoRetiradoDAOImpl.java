
package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cr.go.ice.interrupciones.DAO.PosteInstaladoRetiradoDAO;
import cr.go.ice.interrupciones.domain.PosteInstaladoRetirado;
import cr.go.ice.interrupciones.domain.PosteInstaladoRetiradoID;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.PosteInstaladoRetiradoDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>PosteInstaladoRetiradoDAOImpl.java</code>Define los metodos de los Datos para PosteInstaladoRetirado.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class PosteInstaladoRetiradoDAOImpl extends HibernateDaoSupport implements PosteInstaladoRetiradoDAO{
    
    /**
     * Constructor de la clase: PosteInstaladoRetiradoDAOImpl
     * 
     */
    public PosteInstaladoRetiradoDAOImpl(){}

	/**
	 * @see cr.go.ice.interrupciones.DAO.PosteInstaladoRetiradoDAO#agregar(cr.go.ice.interrupciones.domain.PosteInstaladoRetirado)
	 */
	public void agregar(PosteInstaladoRetirado posteInstaladoRetirado) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
	    hibernate.save(posteInstaladoRetirado);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.PosteInstaladoRetiradoDAO#modificar(cr.go.ice.interrupciones.domain.PosteInstaladoRetirado)
	 */
	public void modificar(PosteInstaladoRetirado posteInstaladoRetirado) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.update(posteInstaladoRetirado);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.PosteInstaladoRetiradoDAO#eliminar(cr.go.ice.interrupciones.domain.PosteInstaladoRetirado)
	 */
	public void eliminar(PosteInstaladoRetirado posteInstaladoRetirado) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.delete(posteInstaladoRetirado);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.PosteInstaladoRetiradoDAO#buscar(cr.go.ice.interrupciones.domain.PosteInstaladoRetirado)
	 */
	public PosteInstaladoRetirado buscar(PosteInstaladoRetirado posteInstaladoRetirado) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		PosteInstaladoRetirado posteInstaladoRetiradoResultado = null;
		
		PosteInstaladoRetiradoID id = posteInstaladoRetirado.getPosteInstaladoRetiradoID();		

		String hql = "from PosteInstaladoRetirado p " +
				"WHERE p.posteInstaladoRetiradoID.ano = ? " +
				"AND p.posteInstaladoRetiradoID.codigoOficina = ? " +
				"AND p.posteInstaladoRetiradoID.numeroInterrupcion = ? " +
				"AND p.posteInstaladoRetiradoID.numeroMovimiento = ?";
        Object[] values = {id.getAno(), id.getCodigoOficina(), id.getNumeroInterrupcion(), id.getNumeroMovimiento()};
        List resultado = hibernate.find(hql, values);
        if(resultado.size()> 0){
        	posteInstaladoRetiradoResultado = (PosteInstaladoRetirado) resultado.get(0);
        } 
        return posteInstaladoRetiradoResultado;
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.PosteInstaladoRetiradoDAO#getPosteInstaladoRetirado()
	 */
	public List getPosteInstaladoRetirado() {
	    HibernateTemplate hibernate = this.getHibernateTemplate();

	    String hql = "from PosteInstaladoRetirado";
	    return hibernate.find(hql);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.PosteInstaladoRetiradoDAO#posteInstaladoRetiradoExiste(cr.go.ice.interrupciones.domain.PosteInstaladoRetirado)
	 */
	public boolean posteInstaladoRetiradoExiste(PosteInstaladoRetirado posteInstaladoRetirado) {
	    return this.buscar(posteInstaladoRetirado)!=null?true:false;
	}
 
	/**
	 * @see cr.go.ice.interrupciones.DAO.PosteInstaladoRetiradoDAO#agregar(java.util.List)
	 */
	public void agregar(List postesInstaladoRetirado) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();

	    if(postesInstaladoRetirado != null){
	    	for(int i = 0; i < postesInstaladoRetirado.size(); i++){
	    		PosteInstaladoRetirado posteInstaladoRetirado = (PosteInstaladoRetirado)postesInstaladoRetirado.get(i);
	    		hibernate.save(posteInstaladoRetirado);
	    	}
	    }
	}
	
	/**
	 * @see cr.go.ice.interrupciones.DAO.PosteInstaladoRetiradoDAO#getPosteInstaladoRetirado(cr.go.ice.interrupciones.domain.PosteInstaladoRetiradoID)
	 */
	public List getPosteInstaladoRetirado(PosteInstaladoRetiradoID posteInstaladoRetiradoID) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from PosteInstaladoRetirado " +
				"where posteInstaladoRetiradoID.ano = ? " +
				"AND posteInstaladoRetiradoID.codigoOficina = ? " +
				"AND posteInstaladoRetiradoID.numeroInterrupcion = ?";
		Object[] values = {posteInstaladoRetiradoID.getAno(), posteInstaladoRetiradoID.getCodigoOficina(), 
		        posteInstaladoRetiradoID.getNumeroInterrupcion()};
		return hibernate.find(hql, values);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.PosteInstaladoRetiradoDAO#modificar(java.util.List, cr.go.ice.interrupciones.domain.PosteInstaladoRetiradoID)
	 */
	public void modificar(List posteInstaladoRetirado, PosteInstaladoRetiradoID id) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "from PosteInstaladoRetirado poste " +
        		"where poste.posteInstaladoRetiradoID.ano = ? " +
        		"AND poste.posteInstaladoRetiradoID.codigoOficina = ? " +
        		"AND poste.posteInstaladoRetiradoID.numeroInterrupcion = ?";		
		Object[] values = {id.getAno(), id.getCodigoOficina(), id.getNumeroInterrupcion()};
		hibernate.deleteAll(hibernate.find(hql,values));
		for(int i = 0; i < posteInstaladoRetirado.size(); i++){
		    PosteInstaladoRetirado posteInstaladoRetiradoNuevo = (PosteInstaladoRetirado)posteInstaladoRetirado.get(i);
		    hibernate.save(posteInstaladoRetiradoNuevo);
        }
	}    
}
