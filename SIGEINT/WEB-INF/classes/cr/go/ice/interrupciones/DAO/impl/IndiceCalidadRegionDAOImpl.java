package cr.go.ice.interrupciones.DAO.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.IndiceCalidadRegionDAO;
import cr.go.ice.interrupciones.domain.IndiceCalidadRegion;

public class IndiceCalidadRegionDAOImpl extends HibernateDaoSupport implements IndiceCalidadRegionDAO {
	
	public List<IndiceCalidadRegion> buscar(Integer anno, Integer mes) {
        HibernateTemplate ht = this.getHibernateTemplate();
        String hql = "FROM IndiceCalidadRegion indice " +
                "WHERE indice.id.ano = ? " +
                "AND indice.id.mes = ? ";
        Object [] values={anno,mes};
        List<IndiceCalidadRegion> indices = ht.find(hql, values);
        if(!indices.isEmpty()){
            return indices;
        }else
        {
        	return new ArrayList<IndiceCalidadRegion>();
        }
    }
}
