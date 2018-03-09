
package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.InterrupcionGemelaDAO;
import cr.go.ice.interrupciones.domain.InterrupcionGemela;
import cr.go.ice.interrupciones.domain.InterrupcionGemelaID;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.InterrupcionGemelaDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>InterrupcionGemelaDAOImpl.java</code>Define los metodos de los Datos para InterrupcionGemela.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class InterrupcionGemelaDAOImpl  extends HibernateDaoSupport implements InterrupcionGemelaDAO{


	/**
	 * @see cr.go.ice.interrupciones.DAO.InterrupcionGemelaDAO#agregar(cr.go.ice.interrupciones.domain.InterrupcionGemela)
	 */
	public void agregar(InterrupcionGemela interrupcionGemela) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.save(interrupcionGemela);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.InterrupcionGemelaDAO#modificar(cr.go.ice.interrupciones.domain.InterrupcionGemela)
	 */
	public void modificar(InterrupcionGemela interrupcionGemela) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.update(interrupcionGemela);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.InterrupcionGemelaDAO#eliminar(cr.go.ice.interrupciones.domain.InterrupcionGemela)
	 */
	public void eliminar(InterrupcionGemela interrupcionGemela) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.delete(interrupcionGemela);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.InterrupcionGemelaDAO#buscar(cr.go.ice.interrupciones.domain.InterrupcionGemela)
	 */
	public InterrupcionGemela buscar(InterrupcionGemela interrupcionGemela) {
        
        InterrupcionGemela interrupcionGemelaResultado = null;
        InterrupcionGemelaID interrupcionGemelaID = interrupcionGemela.getInterrupcionGemelaID();
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from InterrupcionGemela WHERE interrupcionGemelaID.interrupcion.interrupcionID.aa = ? AND " +
				"interrupcionGemelaID.interrupcion.interrupcionID.codigoOficina = ? AND " +
				"interrupcionGemelaID.interrupcion.interrupcionID.numeroInterrupcion = ? AND " +
				"interrupcionGemelaID.numeroMovimiento = ?";
		Object[] values = {interrupcionGemelaID.getInterrupcion().getInterrupcionID().getAa(),
		        interrupcionGemelaID.getInterrupcion().getInterrupcionID().getCodigoOficina(),
		        interrupcionGemelaID.getInterrupcion().getInterrupcionID().getNumeroInterrupcion(),
		        interrupcionGemelaID.getNumeroMovimiento()};
		List interrupciones = hibernate.find(hql, values);
		if(interrupciones.size() > 0){
		    interrupcionGemelaResultado = (InterrupcionGemela) interrupciones.get(0);
		}
		return interrupcionGemelaResultado;  	        
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.InterrupcionGemelaDAO#getInterrupcionesGemelas()
	 */
	public List getInterrupcionesGemelas() {		
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from InterrupcionGemela";
		List interrupcionesGemelas = hibernate.find(hql);
		return interrupcionesGemelas;						
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.InterrupcionGemelaDAO#interrupcionGemelaExiste(cr.go.ice.interrupciones.domain.InterrupcionGemela)
	 */
	public boolean interrupcionGemelaExiste(InterrupcionGemela interrupcionGemela) {
		return (this.buscar(interrupcionGemela) != null);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.InterrupcionGemelaDAO#agregar(java.util.List)
	 */
	public void agregar(List interrupcionesGemelas){		
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		if(interrupcionesGemelas != null){
			for(int i = 0; i < interrupcionesGemelas.size(); i++){
				InterrupcionGemela interrupcionGemela = (InterrupcionGemela)interrupcionesGemelas.get(i);
				hibernate.save(interrupcionGemela);
			}
		}
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.InterrupcionGemelaDAO#getInterrupcionesGemelas(cr.go.ice.interrupciones.domain.InterrupcionGemelaID)
	 */
	public List getInterrupcionesGemelas(InterrupcionGemelaID interrupcionGemelaID) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		Integer aa = interrupcionGemelaID.getInterrupcion().getInterrupcionID().getAa();
		Integer codigoOficina = interrupcionGemelaID.getInterrupcion().getInterrupcionID().getCodigoOficina();		
		Long numeroInterrupcion = interrupcionGemelaID.getInterrupcion().getInterrupcionID().getNumeroInterrupcion();			
		String hql = "from InterrupcionGemela where interrupcionGemelaID.interrupcion.interrupcionID.aa = ? AND " +
				"interrupcionGemelaID.interrupcion.interrupcionID.codigoOficina = ? AND " +
				"interrupcionGemelaID.interrupcion.interrupcionID.numeroInterrupcion = ?";
		Object values[] = {aa, codigoOficina, numeroInterrupcion};
		List interrupcionesGemelas = hibernate.find(hql, values);
		return interrupcionesGemelas;			
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.InterrupcionGemelaDAO#modificar(java.util.List, cr.go.ice.interrupciones.domain.InterrupcionGemelaID)
	 */
	public void modificar(List interrupcionGemela, InterrupcionGemelaID interrupcionGemelaID) {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        if(interrupcionGemelaID != null){
	        Integer aa = interrupcionGemelaID.getInterrupcion().getInterrupcionID().getAa();
			Integer codigoOficina = interrupcionGemelaID.getInterrupcion().getInterrupcionID().getCodigoOficina();
			Long numeroInterrupcion = interrupcionGemelaID.getInterrupcion().getInterrupcionID().getNumeroInterrupcion();						
			String hql = "from InterrupcionGemela interup where interup.interrupcionGemelaID.interrupcion.interrupcionID.aa = ? AND " +		
				"interup.interrupcionGemelaID.interrupcion.interrupcionID.codigoOficina = ? AND " +
				"interup.interrupcionGemelaID.interrupcion.interrupcionID.numeroInterrupcion = ?";		
			Object[] values = {aa, 
			        codigoOficina,
			        numeroInterrupcion};
			hibernate.deleteAll(hibernate.find(hql,values));
        }
		//		 agregamos los nuevo detalles	
        for(int i = 0; i < interrupcionGemela.size(); i++){
        	InterrupcionGemela interrupcionGemelaNuevo = (InterrupcionGemela)interrupcionGemela.get(i);
        	hibernate.save(interrupcionGemelaNuevo);
        }	        
        
	}


    
}
