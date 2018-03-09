package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cr.go.ice.interrupciones.DAO.ProvolmaDAO;
import cr.go.ice.interrupciones.domain.Provolma;
import cr.go.ice.interrupciones.domain.ProvolmaID;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.ProvolmaDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ProvolmaDAOImpl.java</code>Define los metodos de los Datos para Provolma.</p>
 * <p>Fecha creación: 23/02/2007</p>
 * <p>Ultima actualización: 23/02/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class ProvolmaDAOImpl extends HibernateDaoSupport implements ProvolmaDAO {

	/**
	 * Constructor de la clase: ProvolmaDAOImpl
	 * 
	 */
	public ProvolmaDAOImpl(){}
	
	/**
	 * @see cr.go.ice.interrupciones.DAO.ProvolmaDAO#agregar(cr.go.ice.interrupciones.domain.Provolma)
	 */
	public void agregar(Provolma provolma) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.save(provolma);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.ProvolmaDAO#modificar(cr.go.ice.interrupciones.domain.Provolma)
	 */
	public void modificar(Provolma provolma) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.update(provolma);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.ProvolmaDAO#eliminar(cr.go.ice.interrupciones.domain.Provolma)
	 */
	public void eliminar(Provolma provolma) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.delete(provolma);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.ProvolmaDAO#getProvolmas()
	 */
	public List getProvolmas() {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Provolma p " +
				"ORDER BY p.provolmaID.codigoVoltaje, " +
				"p.provolmaID.dano, " +
				"p.provolmaID.material, " +
				"p.provolmaID.proteccion";
		return hibernate.find(hql);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.ProvolmaDAO#existe(cr.go.ice.interrupciones.domain.Provolma)
	 */
	public boolean existe(Provolma provolma) {
	    return this.buscar(provolma.getProvolmaID())!=null?true:false;
	}
	
    /**
     * @see cr.go.ice.interrupciones.DAO.ProvolmaDAO#buscar(cr.go.ice.interrupciones.domain.ProvolmaID)
     */
    public Provolma buscar(ProvolmaID id) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
	    Provolma provolma = null;
	    
		String hql = "from Provolma provolma " +
				"WHERE provolma.provolmaID.codigoVoltaje = ? " +
				"AND provolma.provolmaID.dano.codigoDano = ? " +
				"AND provolma.provolmaID.material.codigoMaterial = ? " +
				"AND provolma.provolmaID.proteccion.codigoProteccion = ?";
		Object[] values = {id.getCodigoVoltaje(), id.getDano().getCodigoDano(), 
		        id.getMaterial().getCodigoMaterial(), id.getProteccion().getCodigoProteccion()};
		
		List provolmas = hibernate.find(hql, values);
		if(provolmas.size() > 0){
		    provolma = (Provolma) provolmas.get(0);
		}
		return provolma;
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.ProvolmaDAO#registrosAsociados(cr.go.ice.interrupciones.domain.ProvolmaID)
     */
    public Long registrosAsociados(ProvolmaID provolmaID) {
        Long cantidad = new Long(0);
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "SELECT COUNT(*) from Interrupcion interup WHERE interup.codigoVoltaje = ? " +
        "AND interup.codigoDano = ? " +
        "AND interup.codigoMaterial = ? " +
        "AND interup.codigoProteccion = ?";
        
        
        Object values [] = {provolmaID.getCodigoVoltaje(), provolmaID.getDano().getCodigoDano(), provolmaID.getMaterial().getCodigoMaterial(), provolmaID.getProteccion().getCodigoProteccion()};
        List interrupciones = hibernate.find(hql, values);
        
        if(interrupciones.size() > 0){
            cantidad = (Long)interrupciones.get(0);             
        }
        
        hql = "SELECT COUNT(*) from Reporte rep WHERE rep.codigoVoltaje = ? " +
        "AND rep.codigoDano = ? " +
        "AND rep.codigoMaterial = ? " +
        "AND rep.codigoProteccion = ?";
        
        Object values2 [] = {provolmaID.getCodigoVoltaje(), provolmaID.getDano().getCodigoDano(), provolmaID.getMaterial().getCodigoMaterial(), provolmaID.getProteccion().getCodigoProteccion()};
        interrupciones = hibernate.find(hql, values2);
        
        if(interrupciones.size() > 0){
            Long cantidadRep = (Long)interrupciones.get(0);
            cantidad = new Long(cantidad.longValue() + cantidadRep.longValue());                
        }       
        
        return cantidad;   
    }
}
