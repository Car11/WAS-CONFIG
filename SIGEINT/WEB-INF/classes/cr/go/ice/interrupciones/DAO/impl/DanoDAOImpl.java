package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.DanoDAO;
import cr.go.ice.interrupciones.domain.Dano;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.DanoDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>DanoDAOImpl.java</code>Define los metodos de los Datos para Dano.</p>
 * <p>Fecha creación: 22/01/2007</p>
 * <p>Ultima actualización: 22/01/2007</p>
 * @author Vista Verde Tecnologia (Administrador y Doc Cristian)
 * @version 1.1
 */
public class DanoDAOImpl  extends HibernateDaoSupport implements DanoDAO {


    /**
     * @see cr.go.ice.interrupciones.DAO.DanoDAO#agregar(cr.go.ice.interrupciones.domain.Dano)
     */
    public void agregar(Dano dano) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.save(dano);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.DanoDAO#modificar(cr.go.ice.interrupciones.domain.Dano)
     */
    public void modificar(Dano dano) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.update(dano);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.DanoDAO#eliminar(cr.go.ice.interrupciones.domain.Dano)
     */
    public void eliminar(Dano dano) {
        HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.delete(dano);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.DanoDAO#buscar(java.lang.Integer)
     */
    public Dano buscar(Integer codigo) {
        
        Dano dano = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "FROM Dano WHERE codigoDano = ? ";
		Object[] values = {codigo};
		List danos = hibernate.find(hql, values);
		if(danos.size() > 0){
		    dano = (Dano) danos.get(0);
		}
		return dano;             
        
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.DanoDAO#buscar(java.lang.String)
     */
    public Dano buscar(String descripcion) {
        Dano dano = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "FROM Dano WHERE nombreDano = ? ";
		Object[] values = {descripcion};
		List danos = hibernate.find(hql, values);
		if(danos.size() > 0){
		    dano = (Dano) danos.get(0);
		}
		return dano;           
        
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.DanoDAO#getDanos()
     */
    public List getDanos() {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Dano order by codigoDano";
		List danos = hibernate.find(hql);
		return danos;	         
        
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.DanoDAO#danoExiste(cr.go.ice.interrupciones.domain.Dano)
     */
    public boolean danoExiste(Dano dano) {       
        return (this.buscar(dano.getCodigoDano()) != null || this.buscar(dano.getNombreDano()) != null);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.DanoDAO#registrosAsociados(java.lang.Integer)
     */
    public Long registrosAsociados(Integer codigoDano) {
        Long cantidad = new Long(0);
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "SELECT COUNT(*) from Interrupcion interup WHERE " +
                "interup.codigoDano = ? ";
        
        
        Object values [] = {codigoDano};
        List interrupciones = hibernate.find(hql, values);
        
        if(interrupciones.size() > 0){
            cantidad = (Long)interrupciones.get(0);             
        }
        
        hql = "SELECT COUNT(*) from Reporte rep WHERE " +
        "rep.codigoDano = ? ";
        
        Object values2 [] = {codigoDano};
        interrupciones = hibernate.find(hql, values2);
        
        if(interrupciones.size() > 0){
            Long cantidadRep = (Long)interrupciones.get(0);
            cantidad = new Long(cantidad.longValue() + cantidadRep.longValue());                
        }       
        
        return cantidad;   
    }
}