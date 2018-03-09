package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.EquipoEspecialDAO;
import cr.go.ice.interrupciones.domain.EquipoEspecial;
import cr.go.ice.interrupciones.domain.EquipoEspecialID;


/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.EquipoEspecialDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>EquipoEspecialDAOImpl.java</code>Define los metodos de los Datos para EquipoEspecial.</p>
 * <p>Fecha creación: 26/03/2007</p>
 * <p>Ultima actualización: 26/03/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class EquipoEspecialDAOImpl  extends HibernateDaoSupport implements EquipoEspecialDAO{

	/**
	 * @see cr.go.ice.interrupciones.DAO.EquipoEspecialDAO#agregar(cr.go.ice.interrupciones.domain.EquipoEspecial)
	 */
	public void agregar(EquipoEspecial equipoEspecial) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.save(equipoEspecial);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.EquipoEspecialDAO#modificar(cr.go.ice.interrupciones.domain.EquipoEspecial)
	 */
	public void modificar(EquipoEspecial equipoEspecial) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.update(equipoEspecial);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.EquipoEspecialDAO#eliminar(cr.go.ice.interrupciones.domain.EquipoEspecial)
	 */
	public void eliminar(EquipoEspecial equipoEspecial) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.delete(equipoEspecial);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.EquipoEspecialDAO#buscar(cr.go.ice.interrupciones.domain.EquipoEspecial)
	 */
	public EquipoEspecial buscar(EquipoEspecial equipoEspecial) {        
        EquipoEspecial equipoEspecialResultado = null;
        EquipoEspecialID equipoEspecialID = equipoEspecial.getEquipoEspecialID();
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from EquipoEspecial WHERE equipoEspecialID.ano = ? AND " +
				"equipoEspecialID.codigoOficina = ? AND " +
				"equipoEspecialID.numeroICE = ? AND " +
				"equipoEspecialID.numeroInterrupcion = ?";
		Object[] values = {equipoEspecialID.getAno(),
		        equipoEspecialID.getCodigoOficina(),
		        equipoEspecialID.getNumeroICE(),
		        equipoEspecialID.getNumeroInterrupcion()};
		List equipos = hibernate.find(hql, values);
		if(equipos.size() > 0){
		    equipoEspecialResultado = (EquipoEspecial) equipos.get(0);
		}
		return equipoEspecialResultado;           
        
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.EquipoEspecialDAO#getEquiposEspeciales()
	 */
	public List getEquiposEspeciales() {
		
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from EquiposEspecial";
		List equipos = hibernate.find(hql);
		return equipos;			
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.EquipoEspecialDAO#equipoEspecialExiste(cr.go.ice.interrupciones.domain.EquipoEspecial)
	 */
	public boolean equipoEspecialExiste(EquipoEspecial equipoEspecial) {
		return (this.buscar(equipoEspecial) != null);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.EquipoEspecialDAO#agregar(java.util.List)
	 */
	public void agregar(List equiposEspeciales) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
	    if(equiposEspeciales != null){
		    for(int i = 0; i < equiposEspeciales.size(); i++){
		    	EquipoEspecial equipoEspecial = (EquipoEspecial)equiposEspeciales.get(i);
		    	hibernate.save(equipoEspecial);
		    }
	    }
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.EquipoEspecialDAO#getEquiposEspeciales(cr.go.ice.interrupciones.domain.EquipoEspecialID)
	 */
	public List getEquiposEspeciales(EquipoEspecialID equipoEspecialID) {
		
		HibernateTemplate hibernate = this.getHibernateTemplate();
		Integer ano = equipoEspecialID.getAno();
		Integer codigoOficina = equipoEspecialID.getCodigoOficina();
		Long numeroInterrupcion = equipoEspecialID.getNumeroInterrupcion();			
		String hql = "from EquipoEspecial where equipoEspecialID.ano = ? AND equipoEspecialID.codigoOficina = ? AND " +
				"equipoEspecialID.numeroInterrupcion = ?";
		Object values[] = {ano, codigoOficina, numeroInterrupcion};
		List equipos = hibernate.find(hql, values);
		return equipos;				
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.EquipoEspecialDAO#modificar(java.util.List, cr.go.ice.interrupciones.domain.EquipoEspecialID)
	 */
	public void modificar(List equipoEspecial, EquipoEspecialID equipoEspecialID) {

        Integer ano = equipoEspecialID.getAno();
		Integer codigoOficina = equipoEspecialID.getCodigoOficina();
		Long numeroInterrupcion = equipoEspecialID.getNumeroInterrupcion();		
		HibernateTemplate hibernate = this.getHibernateTemplate();

		String hql = "from EquipoEspecial equipo where equipo.equipoEspecialID.ano = ? AND " +		
		"equipo.equipoEspecialID.codigoOficina = ? AND " +
		"equipo.equipoEspecialID.numeroInterrupcion = ?";		
		Object[] values = {ano, 
		        codigoOficina,
		        numeroInterrupcion};
		hibernate.deleteAll(hibernate.find(hql,values));
		//		 agregamos los nuevo detalles	
        for(int i = 0; i < equipoEspecial.size(); i++){
        	EquipoEspecial equipoEspecialNuevo = (EquipoEspecial)equipoEspecial.get(i);
        	hibernate.save(equipoEspecialNuevo);
        }	        
        
	}
    
}
