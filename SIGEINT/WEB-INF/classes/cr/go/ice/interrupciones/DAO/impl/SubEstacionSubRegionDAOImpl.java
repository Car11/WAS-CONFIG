package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.SubEstacionSubRegionDAO;
import cr.go.ice.interrupciones.domain.SubEstacionSubRegion;


/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.SubEstacionSubRegionDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SubEstacionSubRegionDAOImpl.java</code> Define los metodos de los Datos para SubEstacionSubRegion.</p>
 * <p>Fecha creación: 08/04/2008</p>
 * <p>Ultima actualización: 08/04/2008</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class SubEstacionSubRegionDAOImpl  extends HibernateDaoSupport implements SubEstacionSubRegionDAO{

    /**
     * @see cr.go.ice.interrupciones.DAO.SubEstacionSubRegionDAO#agregar(cr.go.ice.interrupciones.domain.SubEstacionSubRegion)
     */
    public void agregar(SubEstacionSubRegion sub) {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.save(sub);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.SubEstacionSubRegionDAO#eliminar(cr.go.ice.interrupciones.domain.SubEstacionSubRegion)
     */
    public void eliminar(SubEstacionSubRegion sub) {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.delete(sub);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.SubEstacionSubRegionDAO#existe(cr.go.ice.interrupciones.domain.SubEstacionSubRegion)
     */
    public boolean existe(SubEstacionSubRegion sub) {
        HibernateTemplate ht = this.getHibernateTemplate();
        String hql = "SELECT sub.subEstacionSubRegionID.subEstacion FROM SubEstacionSubRegion sub WHERE (sub.subEstacionSubRegionID.oficina.codigoOficina = ?) " +
                "AND (sub.subEstacionSubRegionID.subRegion.subRegionID.region.region = ?) " +
                "AND (sub.subEstacionSubRegionID.subRegion.subRegionID.subRegion = ?) " +
                "AND (sub.subEstacionSubRegionID.subEstacion = ?)";
        Object[] values = {sub.getSubEstacionSubRegionID().getOficina().getCodigoOficina(), 
                sub.getSubEstacionSubRegionID().getSubRegion().getSubRegionID().getRegion().getRegion(),
                sub.getSubEstacionSubRegionID().getSubRegion().getSubRegionID().getSubRegion(),
                sub.getSubEstacionSubRegionID().getSubEstacion()};
        List list =  ht.find(hql, values);
        return !list.isEmpty();
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.SubEstacionSubRegionDAO#buscarOficina(java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    public Integer buscarOficina(Integer region, Integer subregion, Integer subestacion) {
        HibernateTemplate ht = this.getHibernateTemplate();
        String hql =  " SELECT s.subEstacionSubRegionID.oficina.codigoOficina " +
                " FROM SubEstacionSubRegion s  " +
                " WHERE s.subEstacionSubRegionID.subRegion.subRegionID.region.region = ? AND " +
                " s.subEstacionSubRegionID.subRegion.subRegionID.subRegion = ? AND " +
                " s.subEstacionSubRegionID.subEstacion = ? ";
        Object[] values ={region,subregion,subestacion};
        List resultado = ht.find(hql,values);
        if(!resultado.isEmpty()){
            return (Integer)resultado.get(0);
        }
        else{
            return new Integer(0);
        }
    }



}
