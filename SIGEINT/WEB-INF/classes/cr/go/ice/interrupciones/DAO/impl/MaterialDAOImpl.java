package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.MaterialDAO;
import cr.go.ice.interrupciones.domain.Material;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.MaterialDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>MaterialDAOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para Material.</p>
 * <p>Fecha creación: 22/01/2007</p>
 * <p>Ultima actualización: 22/01/2007</p>
 * @author Vista Verde Tecnologia (Administrador y Doc Cristian)
 * @version 1.1
 */
public class MaterialDAOImpl  extends HibernateDaoSupport  implements MaterialDAO{

    

    /**
     * @see cr.go.ice.interrupciones.DAO.MaterialDAO#agregar(cr.go.ice.interrupciones.domain.Material)
     */
    public void agregar(Material material) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.save(material);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.MaterialDAO#modificar(cr.go.ice.interrupciones.domain.Material)
     */
    public void modificar(Material material) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.update(material);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.MaterialDAO#eliminar(cr.go.ice.interrupciones.domain.Material)
     */
    public void eliminar(Material material) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.delete(material);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.MaterialDAO#buscar(java.lang.Integer)
     */
    public Material buscar(Integer codigo) {
        Material material = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Material WHERE codigoMaterial = ?";
		Object[] values = {codigo};
		List materiales = hibernate.find(hql, values);
		if(materiales.size()>0){
		    material = (Material) materiales.get(0);
		}
		return material;           
    }
    
    public Material buscar(Integer codigo, Integer ubicacionGeneral, Integer ubicacionEspecifica) {
        Material material = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Material WHERE (codigoMaterial = ? and danoGeneral = ? and danoEspecifico = ? ) ";
		Object[] values = {codigo, ubicacionGeneral, ubicacionEspecifica};
		List materiales = hibernate.find(hql, values);
		if(materiales.size()>0){
		    material = (Material) materiales.get(0);
		}
		return material;           
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.MaterialDAO#buscar(java.lang.String)
     */
    public Material buscar(String descripcion) {
        Material material = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Material WHERE nombreMaterial = ?";
		Object[] values = {descripcion};
		List materiales = hibernate.find(hql, values);
		if(materiales.size()>0){
		    material = (Material) materiales.get(0);
		}
		return material;            
        
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.MaterialDAO#getMateriales()
     */
    public List getMateriales() {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Material order by codigoMaterial";
		List materiales = hibernate.find(hql);
		return materiales;	        
        
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.MaterialDAO#materialExiste(cr.go.ice.interrupciones.domain.Material)
     */
    public boolean materialExiste(Material material) {
        return (this.buscar(material.getCodigoMaterial()) != null || this.buscar(material.getNombreMaterial()) != null);
    }
    
    public boolean existe(Material material)
    {
 		HibernateTemplate hibernate = this.getHibernateTemplate();
 		String hql = "from Material WHERE (codigoMaterial = ? and danoGeneral = ? and danoEspecifico = ? ) ";
 		Object[] values = {material.getCodigoMaterial(), material.getDanoGeneral(), material.getDanoEspecifico()};
 		List materiales = hibernate.find(hql, values);
 		if(materiales.size()>0){
 		    return true;
 		}else
 		{
 			return false;
 		}
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.MaterialDAO#registrosAsociados(java.lang.Integer)
     */
    public Long registrosAsociados(Integer codigoMaterial) {
        Long cantidad = new Long(0);
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "SELECT COUNT(*) from Interrupcion interup WHERE " +
                "interup.codigoMaterial = ? ";
        
        
        Object values [] = {codigoMaterial};
        List interrupciones = hibernate.find(hql, values);
        
        if(interrupciones.size() > 0){
            cantidad = (Long)interrupciones.get(0);             
        }
        
        hql = "SELECT COUNT(*) from Reporte rep WHERE " +
        "rep.codigoMaterial = ? ";
        
        Object values2 [] = {codigoMaterial};
        interrupciones = hibernate.find(hql, values2);
        
        if(interrupciones.size() > 0){
            Long cantidadRep = (Long)interrupciones.get(0);
            cantidad = new Long(cantidad.longValue() + cantidadRep.longValue());                
        }       
        
        return cantidad;   
    }
}