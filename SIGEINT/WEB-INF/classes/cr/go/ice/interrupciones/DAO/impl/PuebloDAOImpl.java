package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cr.go.ice.interrupciones.DAO.PuebloDAO;
import cr.go.ice.interrupciones.domain.Pueblo;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.PuebloDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>PuebloDAOImpl.java</code>Define los metodos de los Datos para Pueblo</p>
 * <p>Fecha creación: 13/04/2007</p>
 * <p>Ultima actualización: 13/04/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class PuebloDAOImpl extends HibernateDaoSupport implements PuebloDAO {

    /**
     * Constructor de la clase: PuebloDAOImpl
     */
    public PuebloDAOImpl() {
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.PuebloDAO#agregar(cr.go.ice.interrupciones.domain.Pueblo)
     */
    public void agregar(Pueblo pueblo) {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.save(pueblo);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.PuebloDAO#modificar(cr.go.ice.interrupciones.domain.Pueblo)
     */
    public void modificar(Pueblo pueblo) {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.update(pueblo);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.PuebloDAO#eliminar(cr.go.ice.interrupciones.domain.Pueblo)
     */
    public void eliminar(Pueblo pueblo) {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.delete(pueblo);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.PuebloDAO#buscar(cr.go.ice.interrupciones.domain.Pueblo)
     */
    public Pueblo buscar(Pueblo pueblo) {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        Pueblo puebloResultado = null;

        String hql = "from Pueblo WHERE codigoPueblo = ?";
        Object[] values = { pueblo.getCodigoPueblo() };

        List resultado = hibernate.find(hql, values);
        if (resultado.size() > 0) {
            puebloResultado = (Pueblo) resultado.get(0);
        }
        return puebloResultado;
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.PuebloDAO#buscar(java.lang.Integer)
     */
    public Pueblo buscar(Integer codigo) {
        Pueblo pueblo = new Pueblo();
        pueblo.setCodigoPueblo(codigo);
        return this.buscar(pueblo);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.PuebloDAO#getPueblos()
     */
    public List getPueblos() {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "from Pueblo ORDER BY nombrePueblo";
        return hibernate.find(hql);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.PuebloDAO#puebloExiste(cr.go.ice.interrupciones.domain.Pueblo)
     */
    public boolean puebloExiste(Pueblo pueblo) {
        return (this.buscar(pueblo) != null) ? true : false;
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.PuebloDAO#getPueblos(java.lang.String)
     */
    public List getPueblos(String nombrePueblo) {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "FROM Pueblo pueblo where UPPER(pueblo.nombrePueblo) LIKE ? ORDER BY pueblo.codigoPueblo";
        Object[] values = { nombrePueblo.trim().toUpperCase() + "%" };
        return hibernate.find(hql, values);
    }
    
    
    /**
     * @see cr.go.ice.interrupciones.DAO.PuebloDAO#getPueblos(java.lang.String, java.lang.Integer)
     */
    public List getPueblos(String nombrePueblo, Integer orden) {
        if(orden!=null && orden.equals(Pueblo.ORDEN_NOMBRE)){
	        HibernateTemplate hibernate = this.getHibernateTemplate();
	        String hql = "FROM Pueblo pueblo where UPPER(pueblo.nombrePueblo) LIKE ? ORDER BY pueblo.nombrePueblo";
	        Object[] values = { nombrePueblo.trim().toUpperCase() + "%" };
	        return hibernate.find(hql, values);
        }else{
            return this.getPueblos(nombrePueblo);
        }
    }
}
