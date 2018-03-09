package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;


import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cr.go.ice.interrupciones.DAO.UsuarioOficinaDAO;
import cr.go.ice.interrupciones.domain.UsuarioOficina;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.UsuarioOficinaImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>UsuarioOficinaImpl.java</code>Define los metodos de los Datos para Usuario Oficina.</p>
 * <p>Fecha creación: 14/06/2017</p>
 * <p>Ultima actualización: 14/06/2017</p>
 * @author Rossmon (rhidalgo)
 * @version 1.1
 */
public class UsuarioOficinaDAOImpl extends HibernateDaoSupport implements UsuarioOficinaDAO {
	


	/**
	 * @see cr.go.ice.interrupciones.DAO.AnimalDAO#agregar(cr.go.ice.interrupciones.domain.Animal)
	 */
	public void agregar(UsuarioOficina usuarioOficina) {	    
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.clear();
		hibernate.save(usuarioOficina);
		hibernate.flush();
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.AnimalDAO#modificar(cr.go.ice.interrupciones.domain.Animal)
	 */
	public void modificar(UsuarioOficina usuarioOficina) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.update(usuarioOficina);	
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.AnimalDAO#eliminar(cr.go.ice.interrupciones.domain.Animal)
	 */
	public void eliminar(UsuarioOficina usuarioOficina) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.delete(usuarioOficina);
	}
	
	public void eliminarPorCedula(Integer cedula) {
		HibernateTemplate ht = this.getHibernateTemplate();
        String hql = "DELETE FROM UsuarioOficina WHERE id.empleado.cedula = ? ";
        Object[] values = { cedula.longValue() };
        ht.bulkUpdate(hql, values);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.AnimalDAO#buscar(java.lang.Integer)
	 */
	@SuppressWarnings("rawtypes")
	public UsuarioOficina buscar(Long cedula, Integer codigoOficina) {
		UsuarioOficina usuarioOficina = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "FROM UsuarioOficina WHERE id.empleado.cedula = ? and id.oficina.codigoOficina";
		Object[] values = {cedula, codigoOficina};
		List oficinasUsuario = hibernate.find(hql, values);
		if(oficinasUsuario.size()>0){
			usuarioOficina = (UsuarioOficina) oficinasUsuario.get(0);
		}
		return usuarioOficina;        
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<UsuarioOficina> buscarCedula(Long cedula) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "FROM UsuarioOficina WHERE id.empleado.cedula = ? order by id.oficina.codigoOficina asc";
		Object[] values = {cedula};
		List<UsuarioOficina> oficinasUsuario = hibernate.find(hql, values);
		return oficinasUsuario;        
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<UsuarioOficina> buscarOficina(Integer codigoOficina) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "FROM UsuarioOficina WHERE id.oficina.codigoOficina = ? order by id.oficina.codigoOficina asc";
		Object[] values = {codigoOficina};
		List<UsuarioOficina> oficinasUsuario = hibernate.find(hql, values);
		return oficinasUsuario;        
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<UsuarioOficina> buscarOficinaEmpleado(Integer codigoOficina) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "FROM UsuarioOficina WHERE id.oficina.codigoOficina = ? AND id.empleado.indicador = 0 AND id.empleado.congelado = 0 order by id.empleado.nombreEmpleado asc";
		Object[] values = {codigoOficina};
		List<UsuarioOficina> oficinasUsuario = hibernate.find(hql, values);
		return oficinasUsuario;        
	}
	
	/*
	 * String hql = "from Empleado WHERE oficina.codigoOficina = ? AND " +
				"indicador = 0 AND congelado = 0 ORDER BY nombreEmpleado";
	 */
	
	@SuppressWarnings({ "unchecked" })
	public List<UsuarioOficina> buscarNombre(String nombre) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "FROM UsuarioOficina WHERE UPPER(id.empleado.nombreEmpleado) LIKE ? order by id.oficina.codigoOficina asc";
		nombre = "%"+nombre.trim().toUpperCase() + "%";
		Object[] values = {nombre};
		List<UsuarioOficina> oficinasUsuario = hibernate.find(hql, values);
		return oficinasUsuario;              
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<UsuarioOficina> buscarCedulaNombreOficina(Long cedula, String nombre, Integer codigoOficina) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "FROM UsuarioOficina WHERE (id.empleado.cedula = ? OR  ? = 0L) and (UPPER(id.empleado.nombreEmpleado) LIKE ? OR ? ='') and (id.oficina.codigoOficina = ? OR ?=0) order by id.oficina.codigoOficina asc";
		nombre = "%" +nombre.trim().toUpperCase() + "%";
		Object[] values = {cedula, cedula, nombre, nombre, codigoOficina, codigoOficina};
		List<UsuarioOficina> oficinasUsuario = hibernate.find(hql, values);
		return oficinasUsuario;        
	}
	
	/**
	 * @see cr.go.ice.interrupciones.DAO.AnimalDAO#buscar(java.lang.Integer)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<UsuarioOficina> buscarTodos() {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "FROM UsuarioOficina order by id.empleado.nombreEmpleado, id.oficina.codigoOficina asc";
		List<UsuarioOficina> oficinasUsuario = hibernate.find(hql);
		return oficinasUsuario;        
	}
	
	@SuppressWarnings("rawtypes")
	public boolean existeCedula(Integer cedula)
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "FROM UsuarioOficina WHERE id.empleado.cedula = ?";
		Object[] values = {cedula.longValue()};
		List oficinasUsuario = hibernate.find(hql, values);
		if(oficinasUsuario.size()>0){
			return true;
		}else
		{
			return false;
		}        
    }
	
	
	 /**
	  * @see cr.go.ice.interrupciones.DAO.AnimalDAO#animalExiste(cr.go.ice.interrupciones.domain.Animal)
	  */
	public boolean existe(UsuarioOficina usuarioOficina){
		return (this.buscar(usuarioOficina.getId().getEmpleado().getCedula(), usuarioOficina.getId().getOficina().getCodigoOficina()) != null);		        
        
    }
}
