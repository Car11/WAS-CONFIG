
package cr.go.ice.interrupciones.DAO.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import cr.go.ice.interrupciones.DAO.CuadrillaDAO;
import cr.go.ice.interrupciones.domain.Cuadrilla;
import cr.go.ice.interrupciones.domain.CuadrillaID;
import cr.go.ice.interrupciones.domain.Empleado;


/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.CuadrillaDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>CuadrillaDAOImpl.java</code>Define los metodos de los Datos para Cuadrilla.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class CuadrillaDAOImpl  extends HibernateDaoSupport implements CuadrillaDAO{
    


	/**
	 * @see cr.go.ice.interrupciones.DAO.CuadrillaDAO#agregar(cr.go.ice.interrupciones.domain.Cuadrilla)
	 */
	public void agregar(Cuadrilla cuadrilla) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.save(cuadrilla);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.CuadrillaDAO#modificar(cr.go.ice.interrupciones.domain.Cuadrilla)
	 */
	public void modificar(Cuadrilla cuadrilla) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.update(cuadrilla);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.CuadrillaDAO#eliminar(cr.go.ice.interrupciones.domain.Cuadrilla)
	 */
	public void eliminar(Cuadrilla cuadrilla) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.delete(cuadrilla);
		
	}

	/** 
	 * @see cr.go.ice.interrupciones.DAO.CuadrillaDAO#buscar(cr.go.ice.interrupciones.domain.Cuadrilla)
	 */
	public Cuadrilla buscar(Cuadrilla cuadrilla) {
        
        Cuadrilla cuadrillaResultado = null;
        CuadrillaID cuadrillaID = cuadrilla.getCuadrillaID();
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Cuadrilla cuadrilla WHERE cuadrilla.cuadrillaID.aa = ? AND " +
				"cuadrilla.cuadrillaID.cedula = ? AND " +
				"cuadrilla.cuadrillaID.codigoOficina = ? AND " +
				"cuadrilla.cuadrillaID.numeroInterrupcion = ? AND " +
				"cuadrilla.cuadrillaID.reporteInterrupcion = ?";		
		Object[] values = {cuadrillaID.getAa(), 
		        cuadrillaID.getCedula(),
		        cuadrillaID.getCodigoOficina(),
		        cuadrillaID.getNumeroInterrupcion(),
		        cuadrillaID.getReporteInterrupcion()};
		List cuadrillas = hibernate.find(hql, values);
		if(cuadrillas.size() > 0){
		    cuadrillaResultado = (Cuadrilla) cuadrillas.get(0);
		}
		return cuadrillaResultado;  	        
        
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.CuadrillaDAO#getCuadrillas()
	 */
	public List getCuadrillas() {
		
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Cuadrilla";
		List  cuadrillas = hibernate.find(hql);
		return cuadrillas;	   		
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.CuadrillaDAO#cuadrillaExiste(cr.go.ice.interrupciones.domain.Cuadrilla)
	 */
	public boolean cuadrillaExiste(Cuadrilla cuadrilla) {
		return (this.buscar(cuadrilla) != null);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.CuadrillaDAO#agregar(java.util.List)
	 */
	public void agregar(List cuadrilla) {		
	    HibernateTemplate hibernate = this.getHibernateTemplate();
	    if(cuadrilla != null){
		    for(int i = 0; i < cuadrilla.size(); i++){
		    	Cuadrilla cuadrillaItem = (Cuadrilla)cuadrilla.get(i);
		    	hibernate.save(cuadrillaItem);
		    }	    
	    }
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.CuadrillaDAO#getCuadrillas(cr.go.ice.interrupciones.domain.CuadrillaID)
	 */
	public List getCuadrillas(CuadrillaID cuadrillaID) {
		
		List resultado = null;		
		Integer aa = cuadrillaID.getAa();
		Integer codigoOficina = cuadrillaID.getCodigoOficina();
		Long numeroInterrupcion = cuadrillaID.getNumeroInterrupcion();
		Integer reporteInterrupcion = cuadrillaID.getReporteInterrupcion();
		HibernateTemplate hibernate = this.getHibernateTemplate();

		String hql = "SELECT empleado, cuadrilla from Empleado empleado, Cuadrilla cuadrilla where " +
				"cuadrilla.cuadrillaID.aa = ? AND " +
				"cuadrilla.cuadrillaID.codigoOficina = ? AND " +
				"cuadrilla.cuadrillaID.numeroInterrupcion = ? AND " +
				"cuadrilla.cuadrillaID.reporteInterrupcion = ? AND " +
				"cuadrilla.cuadrillaID.cedula = empleado.cedula";		
		Object[] values = {aa, 
		        codigoOficina,
		        numeroInterrupcion,
		        reporteInterrupcion};
		List cuadrillas = hibernate.find(hql, values);
		if(cuadrillas.size() > 0){
		    resultado = new ArrayList();
			for(int i = 0; i < cuadrillas.size(); i++){
				Object [] obj = (Object[])cuadrillas.get(i);
				Empleado empleado = (Empleado)obj[0];
				Cuadrilla cuadrilla = (Cuadrilla)obj[1];
				cuadrilla.setNombreEmpleado(empleado.getNombreEmpleado());
				resultado.add(cuadrilla);
			}
		}		
		    
		return resultado;  	    		
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.CuadrillaDAO#modificar(java.util.List, cr.go.ice.interrupciones.domain.CuadrillaID)
	 */
	public void modificar(List cuadrilla, CuadrillaID cuadrillaID) {
		Integer aa = cuadrillaID.getAa();
		Integer codigoOficina = cuadrillaID.getCodigoOficina();
		Long numeroInterrupcion = cuadrillaID.getNumeroInterrupcion();
		Integer reporteInterrupcion = cuadrillaID.getReporteInterrupcion();
		HibernateTemplate hibernate = this.getHibernateTemplate();

		String hql = "from Cuadrilla cuadrilla where cuadrilla.cuadrillaID.aa = ? AND " +		
		"cuadrilla.cuadrillaID.codigoOficina = ? AND " +
		"cuadrilla.cuadrillaID.numeroInterrupcion = ? AND " +
		"cuadrilla.cuadrillaID.reporteInterrupcion = ?";		
		Object[] values = {aa, 
		        codigoOficina,
		        numeroInterrupcion,
		        reporteInterrupcion};
		hibernate.deleteAll(hibernate.find(hql,values));
		//		 agregamos los nuevo detalles	
        for(int i = 0; i < cuadrilla.size(); i++){
        	Cuadrilla cuadrillaNueva = (Cuadrilla)cuadrilla.get(i);
        	hibernate.save(cuadrillaNueva);
        }			        

	}
}
