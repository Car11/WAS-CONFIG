package cr.go.ice.interrupciones.DAO.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.IndiceCalidadNacionalDAO;
import cr.go.ice.interrupciones.domain.IndiceCalidadNacional;

public class IndiceCalidadNacionalDAOImpl extends HibernateDaoSupport implements IndiceCalidadNacionalDAO{
	
	public List<IndiceCalidadNacional> buscar(Integer anno, Integer mes) {
        HibernateTemplate ht = this.getHibernateTemplate();
        String hql = "FROM IndiceCalidadNacional indice " +
                "WHERE indice.id.ano = ? " +
                "AND indice.id.mes = ? ";
        Object [] values={anno,mes};
        List<IndiceCalidadNacional> indices = ht.find(hql, values);
        if(!indices.isEmpty()){
            return indices;
        }else
        {
        	return new ArrayList<IndiceCalidadNacional>();
        }
    }
}
