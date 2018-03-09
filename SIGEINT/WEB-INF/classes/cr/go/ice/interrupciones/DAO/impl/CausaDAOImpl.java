package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import cr.go.ice.interrupciones.DAO.CausaDAO;
import cr.go.ice.interrupciones.domain.Causa;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.CausaDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>CausaDAOImpl.java</code>Define los metodos de los Datos para Causa</p>
 * <p>Fecha creación: 22/08/2007</p>
 * <p>Ultima actualización: 22/08/2007</p>
 * @author Vista Verde Tecnologia (Mario Leon y Doc Cristian)
 * @version 1.1
 */
public class CausaDAOImpl extends HibernateDaoSupport  implements CausaDAO {


    /**
     * @see cr.go.ice.interrupciones.DAO.CausaDAO#agregar(cr.go.ice.interrupciones.domain.Causa)
     */
    public void agregar(Causa causa){
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.save(causa);
    }
    
    /**
     * @see cr.go.ice.interrupciones.DAO.CausaDAO#modificar(cr.go.ice.interrupciones.domain.Causa)
     */
    public void modificar(Causa causa){
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.update(causa);
    }
    
    /**
     * @see cr.go.ice.interrupciones.DAO.CausaDAO#eliminar(cr.go.ice.interrupciones.domain.Causa)
     */
    public void eliminar(Causa causa){
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.delete(causa);
    }
    
    /**
     * @see cr.go.ice.interrupciones.DAO.CausaDAO#buscar(java.lang.Integer)
     */
    public Causa buscar(Integer codigo){        
        Causa causa = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "FROM Causa WHERE codigoCausa = ? ";
		Object[] values = {codigo};
		List causas = hibernate.find(hql, values);
		if(causas.size() > 0){
		    causa = (Causa) causas.get(0);
		}
		return causa;                
    }
    
    public Causa buscar(Integer codigo, Integer tipoCausa, Integer causaEspecifica, Integer detalleCausa){        
        Causa causa = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "FROM Causa WHERE codigoCausa = ? AND tiposCausa = ? AND causaEspecifica = ? AND detalleCausa = ? ";
		Object[] values = {codigo,  tipoCausa, causaEspecifica, detalleCausa};
		List causas = hibernate.find(hql, values);
		if(causas.size() > 0){
		    causa = (Causa) causas.get(0);
		}
		return causa;                
    }
    /**
     * @see cr.go.ice.interrupciones.DAO.CausaDAO#buscar(java.lang.String)
     */
    public Causa buscar(String nombre){
        Causa causa = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "FROM Causa WHERE nombreCausa = ? ";
		Object[] values = {nombre};
		List causas = hibernate.find(hql, values);
		if(causas.size() > 0){
		    causa = (Causa) causas.get(0);
		}
		return causa;            
        
    }
    
    /**
     * @see cr.go.ice.interrupciones.DAO.CausaDAO#getCausas()
     */
    public List getCausas(){        
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Causa ORDER BY codigoCausa";
		List causas = hibernate.find(hql);
		return causas;	        
        
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.CausaDAO#causaExiste(cr.go.ice.interrupciones.domain.Causa)
     */
    public boolean causaExiste(Causa causa) {
        return (this.buscar(causa.getCodigoCausa()) != null);
    }
    
    public boolean existe(Causa causa) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "FROM Causa WHERE codigoCausa = ? AND tiposCausa = ? AND causaEspecifica = ? AND detalleCausa = ? ";
		Object[] values = {causa.getCodigoCausa(), causa.getTiposCausa(), causa.getCausaEspecifica(), causa.getDetalleCausa()};
		List causas = hibernate.find(hql, values);
		if(causas.size() > 0){
		    return true;
		}else
		{
			return false;
		}
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.CausaDAO#registrosAsociados(java.lang.Integer)
     */
    public Long registrosAsociados(Integer codigoCausa) {
        Long cantidad = new Long(0);
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "SELECT COUNT(*) from Interrupcion interup WHERE " +
                "interup.causa1 = ? ";
        
        
        Object values [] = {codigoCausa};
        List interrupciones = hibernate.find(hql, values);
        
        if(interrupciones.size() > 0){
            cantidad = (Long)interrupciones.get(0);             
        }
        
        hql = "SELECT COUNT(*) from Reporte rep WHERE " +
        "rep.causa1 = ? ";
        
        Object values2 [] = {codigoCausa};
        interrupciones = hibernate.find(hql, values2);
        
        if(interrupciones.size() > 0){
            Long cantidadRep = (Long)interrupciones.get(0);
            cantidad = new Long(cantidad.longValue() + cantidadRep.longValue());                
        }       
        
        return cantidad;   
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.CausaDAO#getCausas(java.util.List)
     */
    public List getCausas(List causas) {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "from Causa where codigoCausa IN (413,421,422,423,424,417)";       
        List causasResultado = hibernate.find(hql);
        return causasResultado;      
    }
}