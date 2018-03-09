package cr.go.ice.interrupciones.DAO.impl;

import cr.go.ice.interrupciones.domain.IndiceGlobalRegion;
import cr.go.ice.interrupciones.DAO.IndiceGlobalRegionDAO;
import java.util.List; 
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * <p>Clase cr.go.ice.interrupciones.dao.impl.IndiceGlobalRegionDaoImpl.java</p>
 * <p>Modulo (subsistema): Proyecto</p>
 * <p>La clase <code>IndiceGlobalRegionDaoImpl.java</code> implementa los metodos de acceso a datos</p>
 * <p>Fecha creacion: 10/04/2010</p>
 * <p>Ultima actualizacion: 10/04/2010</p>
 * @author VVS Code Generator
 * @version 1.1
 */
public class IndiceGlobalRegionDAOImpl extends HibernateDaoSupport implements IndiceGlobalRegionDAO{


    public IndiceGlobalRegion buscar(Integer region, Integer anno, Integer mes, Integer voltaje) {
        HibernateTemplate ht = this.getHibernateTemplate();
        String hql = "FROM IndiceGlobalRegion indice " +
                "WHERE indice.id.region = ? " +
                "AND indice.id.anno = ? " +
                "AND indice.id.mes = ? " +
                "AND indice.id.codigoVoltaje = ? ";
        Object [] values={region,anno,mes,voltaje};
        IndiceGlobalRegion indiceGlobalRegion= new IndiceGlobalRegion();
        List indices = ht.find(hql, values);
        if(!indices.isEmpty()){
            indiceGlobalRegion = (IndiceGlobalRegion)indices.get(0);
        }
        return indiceGlobalRegion;
    }

}
