package cr.go.ice.interrupciones.DAO.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.perturbacionesDAO;
import cr.go.ice.interrupciones.domain.Perturbaciones;

public class perturbacionesDAOImpl {

	public List<Perturbaciones> buscar(Integer anno, Integer mes) {
        HibernateTemplate ht = this.getHibernateTemplate();
        String hql = "FROM IndiceCalidadNacional indice " +
                "WHERE indice.id.ano = ? " +
                "AND indice.id.mes = ? ";
        Object [] values={anno,mes};
        List<Perturbaciones> indices = ht.find(hql, values);
        if(!indices.isEmpty()){
            return indices;
        }else
        {
        	return new ArrayList<Perturbaciones>();
        }
    }

	private HibernateTemplate getHibernateTemplate() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
