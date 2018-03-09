package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;


import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.vvs.bussiness.BussinessError;
import com.vvs.generic.dao.impl.GenericUtilityDaoImpl;
import com.vvs.generic.domain.Parametro;
import com.vvs.reporte.ReportesDinamicos;

import cr.go.ice.interrupciones.DAO.GenericoDao;




	
    public class GenericoDaoHB extends GenericUtilityDaoImpl implements GenericoDao{
        
        
        public void eliminarAsociacion (ReportesDinamicos reportesDinamicos){
            HibernateTemplate ht = this.getHibernateTemplate();
            String hql="DELETE FROM ReportesDinamicosSub subReporte WHERE subReporte.id.reportesDinamicos.codigoReporte = ?";
            Object[] values = {reportesDinamicos.getCodigoReporte()};
            ht.bulkUpdate(hql, values); 

        }
}
