package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cr.go.ice.interrupciones.DAO.ProteccionDAO;
import cr.go.ice.interrupciones.domain.Proteccion;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.ProteccionDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ProteccionDAOImpl.java</code>Define los metodos de los Datos para Proteccion</p>
 * <p>Fecha creación: 22/01/2007</p>
 * <p>Ultima actualización: 22/01/2007</p>
 * @author Vista Verde Tecnologia (Administrador y Doc Cristian)
 * @version 1.1
 */
public class ProteccionDAOImpl extends HibernateDaoSupport implements ProteccionDAO {

    /**
     * Constructor de la clase: ProteccionDAOImpl
     * 
     */
    public ProteccionDAOImpl(){}

    /**
     * @see cr.go.ice.interrupciones.DAO.ProteccionDAO#agregar(cr.go.ice.interrupciones.domain.Proteccion)
     */
    public void agregar(Proteccion proteccion) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
	    hibernate.save(proteccion);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.ProteccionDAO#modificar(cr.go.ice.interrupciones.domain.Proteccion)
     */
    public void modificar(Proteccion proteccion) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.update(proteccion);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.ProteccionDAO#eliminar(cr.go.ice.interrupciones.domain.Proteccion)
     */
    public void eliminar(Proteccion proteccion) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.delete(proteccion);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.ProteccionDAO#buscar(java.lang.Integer)
     */
    public Proteccion buscar(Integer codigo) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
	    Proteccion proteccion = null;
	    String hql = "FROM Proteccion p WHERE p.codigoProteccion = ?";
	    Object[] values = {codigo};
	    List resultado = hibernate.find(hql, values);
		if(resultado.size() > 0){
		    proteccion = (Proteccion) resultado.get(0);
		}
		return proteccion;
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.ProteccionDAO#buscar(java.lang.String)
     */
    public Proteccion buscar(String descripcion) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
	    Proteccion proteccion = null;
	    String hql = "FROM Proteccion p WHERE p.nombreProteccion = ?";
	    Object[] values = {descripcion};
	    List resultado = hibernate.find(hql, values);
		if(resultado.size() > 0){
		    proteccion = (Proteccion) resultado.get(0);
		}
		return proteccion;
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.ProteccionDAO#getProtecciones()
     */
    public List getProtecciones() {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "from Proteccion p order by p.codigoProteccion";
        return hibernate.find(hql);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.ProteccionDAO#proteccionExiste(cr.go.ice.interrupciones.domain.Proteccion)
     */
    public boolean proteccionExiste(Proteccion proteccion) {
        return this.buscar(proteccion.getCodigoProteccion())!=null?true:false;
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.ProteccionDAO#registrosAsociados(java.lang.Integer)
     */
    public Long registrosAsociados(Integer codigoProteccion) {
        Long cantidad = new Long(0);
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "SELECT COUNT(*) from Interrupcion interup WHERE " +
                "interup.codigoProteccion = ? ";
        
        
        Object values [] = {codigoProteccion};
        List interrupciones = hibernate.find(hql, values);
        
        if(interrupciones.size() > 0){
            cantidad = (Long)interrupciones.get(0);             
        }
        
        hql = "SELECT COUNT(*) from Reporte rep WHERE " +
        "rep.codigoProteccion = ? ";
        
        Object values2 [] = {codigoProteccion};
        interrupciones = hibernate.find(hql, values2);
        
        if(interrupciones.size() > 0){
            Long cantidadRep = (Long)interrupciones.get(0);
            cantidad = new Long(cantidad.longValue() + cantidadRep.longValue());                
        }       
        
        return cantidad;   
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.ProteccionDAO#getProtecciones(java.lang.String)
     */
	public List getProtecciones(String protecciones) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
	    String hql = "FROM Proteccion p WHERE p.codigoProteccion IN (" + protecciones + ")";
	    List resultado = hibernate.find(hql);
		return resultado;
	}
}