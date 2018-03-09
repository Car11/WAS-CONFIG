package cr.go.ice.sace.sacecon.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.vvs.bussiness.BussinessError;

import cr.go.ice.sace.sacecon.dao.PuebloSDao;
import cr.go.ice.sace.sacecon.domain.Pueblo;

/**
 * <p> Clase cr.go.ice.sace.sacecon.dao.impl.PuebloSDaoHB.java</p>
 * <p> Módulo (subsistema): TransformadoresWeb</p>
 * <p> Descricion de <code>PuebloDaoHB.java</code>
 * es la que implementa los metodos de la interface <code>PuebloDao</code>.</p>
 * <p>Fecha creación: 11/01/2011</p>
 * <p>Ultima actualización: 11/01/2011</p>
 * @author Vista Verde Tecnología (eperaza)
 * @version 1.1
 */
public class PuebloSDaoHB extends HibernateDaoSupport implements PuebloSDao{

    /**
     * @see cr.go.ice.sace.sacecon.dao.PuebloSDao#agregar(cr.go.ice.sace.sacecon.domain.Pueblo)
     */
    public void agregar(Pueblo pueblo) {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.save(pueblo);
    }

    /**
     * @see cr.go.ice.sace.sacecon.dao.PuebloSDao#modificar(cr.go.ice.sace.sacecon.domain.Pueblo)
     */
    public void modificar(Pueblo pueblo) {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.update(pueblo);
    }

    /**
     * @see cr.go.ice.sace.sacecon.dao.PuebloSDao#eliminar(cr.go.ice.sace.sacecon.domain.Pueblo)
     */
    public void eliminar(Pueblo pueblo) {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.delete(pueblo);
    }

    /**
     * @see cr.go.ice.sace.sacecon.dao.PuebloSDao#buscar(java.lang.Integer)
     */
    public Pueblo buscar(Integer numero) throws BussinessError{
        Pueblo pueblo = null;
        if(numero!= null){
            HibernateTemplate hibernate = this.getHibernateTemplate();
            String hql = "FROM Pueblo WHERE numero = ?";
            Object[] values = {numero};
            List agen = hibernate.find(hql, values);
            if(agen.size()>0){
                pueblo = (Pueblo) agen.get(0);
            }
        }
        return pueblo;     
    }

    /**
     * @see cr.go.ice.sace.sacecon.dao.PuebloSDao#existe(cr.go.ice.sace.sacecon.domain.Pueblo)
     */
    public boolean existe(Pueblo pueblo) {
        HibernateTemplate ht = this.getHibernateTemplate();
        String hql = "SELECT pueblo.numero FROM Pueblo pueblo WHERE (pueblo.numero = ?)";
        Object[] values = {pueblo.getNumero()};
        List list =  ht.find(hql, values);
        return !list.isEmpty(); 
    }

    /**
     * @see cr.go.ice.sace.sacecon.dao.PuebloSDao#getPueblos()
     */
    public List getPueblos() {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "FROM Pueblo pueblo ORDER BY pueblo.nombre";
        List pueblos = hibernate.find(hql);
        return pueblos; 
    }
    
    

    /**
     * vea @see cr.go.ice.sace.sacecon.dao.PuebloDao#getPueblos(java.lang.String)
     * @param nombre 
     * @return List
     */
    public List getPueblos(String nombre) throws BussinessError{
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "FROM Pueblo " +
        		"WHERE UPPER(nombre) like ? " +
        		"ORDER BY nombre";
        Object[] values = {nombre.trim().toUpperCase()+"%"};
        return hibernate.find(hql, values);
    }
    
    
    /**
     * @see cr.go.ice.sace.sacecon.dao.PuebloSDao#getPueblosAgencia(java.lang.Integer)
     */
    public List getPueblosAgencia(Integer agencia) throws BussinessError{
        HibernateTemplate hibernate = this.getHibernateTemplate();
        
        String hql = "FROM Pueblo pueblo " +
                     "WHERE pueblo.noAgencia = ? " +
                     " ORDER BY pueblo.nombre ";
        Object[] values = {agencia};
        return hibernate.find(hql,values);
        
    }
}
