package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.EmpleadoDAO;
import cr.go.ice.interrupciones.domain.Empleado;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.EmpleadoDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>EmpleadoDAOImpl.java</code> Define los metodos de los Datos para Empleado.</p>
 * <p>Fecha creación: 17/01/2007</p>
 * <p>Ultima actualización: 17/01/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class EmpleadoDAOImpl  extends HibernateDaoSupport implements EmpleadoDAO {

	/**
	 * @see cr.go.ice.interrupciones.DAO.EmpleadoDAO#agregar(cr.go.ice.interrupciones.domain.Empleado)
	 */
	public void agregar(Empleado empleado) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.save(empleado);
	}


	/**
	 * @see cr.go.ice.interrupciones.DAO.EmpleadoDAO#modificar(cr.go.ice.interrupciones.domain.Empleado)
	 */
	public void modificar(Empleado empleado) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.update(empleado);
	}


	/**
	 * @see cr.go.ice.interrupciones.DAO.EmpleadoDAO#eliminar(cr.go.ice.interrupciones.domain.Empleado)
	 */
	public void eliminar(Empleado empleado) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.delete(empleado);
	}
	
	/**
	 * @see cr.go.ice.interrupciones.DAO.EmpleadoDAO#getEmpleado(java.lang.Long)
	 */
	public Empleado getEmpleado(Long codigoEmpleado) {        
        Empleado empleado = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Empleado WHERE cedula = ?";
		Object[] values = {codigoEmpleado};
		List empleados = hibernate.find(hql, values);
		if(empleados.size() > 0){
		    empleado = (Empleado) empleados.get(0);
		}
		return empleado;              
        
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.EmpleadoDAO#getEmpleados()
	 */
	public List getEmpleados() {
		
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Empleado WHERE indicador = 0 AND congelado = 0 ORDER BY nombreEmpleado";
		List empleados = hibernate.find(hql);
		return empleados;	    		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.EmpleadoDAO#empleadoExiste(cr.go.ice.interrupciones.domain.Empleado)
	 */
	public boolean empleadoExiste(Empleado empleado) {
		return (this.getEmpleado(empleado.getCedula()) != null);	
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.EmpleadoDAO#getEmpleados(java.lang.String)
	 */
	public List getEmpleados(String nombreEmpleado) {		
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "FROM Empleado " +
				"WHERE UPPER(nombreEmpleado) LIKE ? " +
				"AND indicador = 0 AND congelado = 0 ORDER BY nombreEmpleado";
		nombreEmpleado = nombreEmpleado.trim().toUpperCase() + "%";
		Object values[] = {nombreEmpleado};
		List empleados = hibernate.find(hql, values);
		return empleados;			
		
	}
	
	public List getEmpleadosTodos(String nombreEmpleado) {		
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "FROM Empleado " +
				"WHERE UPPER(nombreEmpleado) LIKE ? " +
				"ORDER BY nombreEmpleado";
		nombreEmpleado = "%" + nombreEmpleado.trim().toUpperCase() + "%";
		Object values[] = {nombreEmpleado};
		List empleados = hibernate.find(hql, values);
		return empleados;			
		
	}

    /**
     * @see cr.go.ice.interrupciones.DAO.EmpleadoDAO#getEmpleadosPorOficina(java.lang.Integer)
     */
    public List getEmpleadosPorOficina(Integer codigoOficina) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Empleado WHERE oficina.codigoOficina = ? AND " +
				"indicador = 0 AND congelado = 0 ORDER BY nombreEmpleado";
		Object values[] = {codigoOficina};
		List empleados = hibernate.find(hql, values);
		return empleados;			
		
    }
  
    /**
     * @see cr.go.ice.interrupciones.DAO.EmpleadoDAO#getEmpleadoActivo(java.lang.Long)
     */
    public Empleado getEmpleadoActivo(Long codigoEmpleado) {        
        Empleado empleado = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Empleado WHERE cedula = ? AND indicador = 0 AND congelado = 0";
		Object values[] = {codigoEmpleado};
		List empleados = hibernate.find(hql, values);
        if(empleados.size()> 0){
            empleado = (Empleado) empleados.get(0);
        } 		
		return empleado;	        
        
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.EmpleadoDAO#getRegistrosAsociados(java.lang.Long)
     */
    public Long getRegistrosAsociados(Long codigoEmpleado) {
        Long cantidad = new Long(0);
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "SELECT COUNT(*) from Interrupcion interup WHERE " +
                "interup.operador = ? ";
        
        
        Object values [] = {codigoEmpleado};
        List interrupciones = hibernate.find(hql, values);
        
        if(interrupciones.size() > 0){
            cantidad = (Long)interrupciones.get(0);             
        }
        
        hql = "SELECT COUNT(*) from Reporte rep WHERE " +
        "rep.operador = ? ";
        
        Object values2 [] = {codigoEmpleado};
        interrupciones = hibernate.find(hql, values2);
        
        if(interrupciones.size() > 0){
            Long cantidadRep = (Long)interrupciones.get(0);
            cantidad = new Long(cantidad.longValue() + cantidadRep.longValue());                
        }       
        
        return cantidad;       
    }
    
   


}
