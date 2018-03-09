package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.CorreoDAO;
import cr.go.ice.interrupciones.domain.Correo;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.CorreoDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>CorreoDAOImpl.java</code>Define los metodos de los Datos para Correo</p>
 * <p>Fecha creación: 19/02/2007</p>
 * <p>Ultima actualización: 19/02/2007</p>
 * @author Vista Verde Tecnologia (Nano y Doc Cristian)
 * @version 1.1
 */
public class CorreoDAOImpl extends HibernateDaoSupport  implements CorreoDAO {

	
	/**
	 * @see cr.go.ice.interrupciones.DAO.CorreoDAO#agregar(cr.go.ice.interrupciones.domain.Correo)
	 */
	public void agregar(Correo correo) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.save(correo);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.CorreoDAO#modificar(cr.go.ice.interrupciones.domain.Correo)
	 */
	public void modificar(Correo correo) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.update(correo);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.CorreoDAO#eliminar(cr.go.ice.interrupciones.domain.Correo)
	 */
	public void eliminar(Correo correo) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.delete(correo);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.CorreoDAO#buscar(java.lang.String)
	 */
	public Correo buscar(String direccionCorreo) {		
		Correo correo = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Correo correo where correo.correo = ?";
		Object[] values = {direccionCorreo.trim()};
		List correos = hibernate.find(hql, values);
		if(correos.size() > 0){
		    correo = (Correo) correos.get(0);
		}
		return correo;  		
		
	}
	
	
	/**
	 * @see cr.go.ice.interrupciones.DAO.CorreoDAO#buscarNombre(java.lang.String)
	 */
	public Correo buscarNombre(String nombre) {
		
		Correo correo = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Correo correo where correo.nombre = ?";
		Object[] values = {nombre.trim()};
		List correos = hibernate.find(hql, values);
		if(correos.size() > 0){
		    correo = (Correo) correos.get(0);
		}
		return correo;  			
		
	}
	
	

	/**
	 * @see cr.go.ice.interrupciones.DAO.CorreoDAO#getCorreos()
	 */
	public List getCorreos() {        
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Correo correo ORDER BY correo.correo";
		List correos = hibernate.find(hql);
		return correos;	    		
	}
	
	
	/**
	 * @see cr.go.ice.interrupciones.DAO.CorreoDAO#getCorreos(java.lang.Integer)
	 */
	public List getCorreos(Integer oficina){
		
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "FROM Correo correo WHERE correo.codigoOficina = ? ORDER BY correo.nombre";
		Object[] values = {oficina};
		List correos = hibernate.find(hql, values);
		return correos;  		
		
	}
	
	/**
	 * @see cr.go.ice.interrupciones.DAO.CorreoDAO#getCorreosCorreo(java.lang.String)
	 */
	public List getCorreosCorreo(String direccionCorreo){
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "FROM Correo correo WHERE correo.correo LIKE ? ORDER BY correo.nombre";
		direccionCorreo = direccionCorreo.trim() + "%";
		Object[] values = {direccionCorreo};
		List correos = hibernate.find(hql, values);
		return correos;  				
		
	}
	
	
	/**
	 * @see cr.go.ice.interrupciones.DAO.CorreoDAO#getCorreosNombre(java.lang.String)
	 */
	public List getCorreosNombre(String nombre){
		
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "FROM Correo correo WHERE correo.nombre LIKE ? ORDER BY correo.nombre";
		nombre = nombre.trim() + "%";
		Object[] values = {nombre};
		List correos = hibernate.find(hql, values);
		return correos;  		
		
	}	

	/**
	 * @see cr.go.ice.interrupciones.DAO.CorreoDAO#existe(cr.go.ice.interrupciones.domain.Correo)
	 */
	public boolean existe(Correo correo) {
		boolean existe;
		Correo mail = this.buscar(correo.getCorreo());
		try{
			if(mail != null) {
				existe = (mail.getCorreo()!=null)? true : false;
			} else {
				existe = false;
			}
		}catch(ObjectNotFoundException e){
			existe = false;
		}
		return existe;	
		
	}

    /**
     * @see cr.go.ice.interrupciones.DAO.CorreoDAO#getCorreosParaEnviar(java.lang.Integer)
     */
    public List<Correo> getCorreosParaEnviar(Integer oficina) {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "FROM Correo correo WHERE correo.codigoOficina = ? and correo.indicadorEnviado = 1 ORDER BY correo.correo";
        Object[] values = {oficina};
        List<Correo> correos = hibernate.find(hql, values);
        return correos;     
    }

}
