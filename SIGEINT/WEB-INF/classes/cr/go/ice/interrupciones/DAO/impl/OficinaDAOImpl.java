package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.OficinaDAO;
import cr.go.ice.interrupciones.domain.Oficina;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.OficinaDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>OficinaDAOImpl.java</code>Define los metodos de los Datos para Oficina.</p>
 * <p>Fecha creación: 22/01/2007</p>
 * <p>Ultima actualización: 22/01/2007</p>
 * @author Vista Verde Tecnologia (Administrador y Doc Cristian)
 * @version 1.1
 */

public class OficinaDAOImpl  extends HibernateDaoSupport implements OficinaDAO {
    

    /**
     * @see cr.go.ice.interrupciones.DAO.OficinaDAO#agregar(cr.go.ice.interrupciones.domain.Oficina)
     */
    public void agregar(Oficina oficina) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.save(oficina);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.OficinaDAO#modificar(cr.go.ice.interrupciones.domain.Oficina)
     */
    public void modificar(Oficina oficina) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.update(oficina);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.OficinaDAO#eliminar(cr.go.ice.interrupciones.domain.Oficina)
     */
    public void eliminar(Oficina oficina) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.delete(oficina);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.OficinaDAO#buscar(java.lang.Integer)
     */
    public Oficina buscar(Integer codigo) {        
        Oficina oficina = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Oficina WHERE codigoOficina = ?";
		Object[] values = {codigo};
		List oficinas = hibernate.find(hql, values);
		if(oficinas.size()>0){
		    oficina = (Oficina) oficinas.get(0);
		}
		return oficina;           
        
    }
    
   
    /**
     * @see cr.go.ice.interrupciones.DAO.OficinaDAO#buscar(java.lang.String)
     */
    public Oficina buscar(String descripcion) {
        Oficina oficina = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Oficina WHERE nombreOficina = ?";
		Object[] values = {descripcion};
		List oficinas = hibernate.find(hql, values);
		if(oficinas.size()>0){
		    oficina = (Oficina) oficinas.get(0);
		}
		return oficina;             
        
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.OficinaDAO#getOficinas()
     */
    public List getOficinas() {        
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Oficina order by codigoOficina";
		List oficinas = hibernate.find(hql);
		return oficinas;	        
    }
  
    
    /**
     * @see cr.go.ice.interrupciones.DAO.OficinaDAO#oficinaExiste(cr.go.ice.interrupciones.domain.Oficina)
     */
    public boolean oficinaExiste(Oficina oficina){
        return (this.buscar(oficina.getCodigoOficina()) != null || this.buscar(oficina.getNombreOficina()) != null);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.OficinaDAO#registrosAsociados(java.lang.Integer)
     */
    public Long registrosAsociados(Integer codigoOficina) {
        Long cantidad = new Long(0);
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "SELECT COUNT(*) from Interrupcion interup WHERE " +
                "interup.interrupcionID.codigoOficina = ? ";
        
        
        Object values [] = {codigoOficina};
        List interrupciones = hibernate.find(hql, values);
        
        if(interrupciones.size() > 0){
            cantidad = (Long)interrupciones.get(0);             
        }
        
        hql = "SELECT COUNT(*) from Reporte rep WHERE " +
        "rep.reporteID.codigoOficina= ? ";
        
        Object values2 [] = {codigoOficina};
        interrupciones = hibernate.find(hql, values2);
        
        if(interrupciones.size() > 0){
            Long cantidadRep = (Long)interrupciones.get(0);
            cantidad = new Long(cantidad.longValue() + cantidadRep.longValue());                
        }       
        
        return cantidad;   
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.OficinaDAO#tieneOficinasAsociadasACorreos(java.lang.Integer)
     */
    public boolean tieneOficinasAsociadasACorreos(Integer codigoOficina) {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "FROM Correo correo WHERE correo.codigoOficina = ? ";               
        Object values [] = {codigoOficina};
        return ((hibernate.find(hql, values)).size() > 0);
    }
}