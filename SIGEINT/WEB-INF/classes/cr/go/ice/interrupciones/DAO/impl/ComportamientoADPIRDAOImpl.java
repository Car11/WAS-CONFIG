package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.ComportamientoADPIRDAO;

/**
 * <p>TODO <<Interface|Clase>> cr.go.ice.interrupciones.DAO.impl.ComportamientoADPIRDAOImpl.java</p>
 * <p>M�dulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripci�n) La <<Interface|Clase>> <code>ComportamientoADPIRDAOImpl.java</code>.</p>
 * <p>Fecha creaci�n: 26/10/2010</p>
 * <p>�ltima actualizaci�n: 26/10/2010</p>
 * @author Vista Verde Tecnolog�a (root)
 * @version 1.0
 */
public class ComportamientoADPIRDAOImpl extends HibernateDaoSupport implements ComportamientoADPIRDAO{

    /**
     * @see cr.go.ice.interrupciones.DAO.ComportamientoADPIRDAO#getComportamientoADPIR(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    public List getComportamientoADPIR(Integer anoInicio, Integer anoFinal, Integer region, Integer subregion, Integer subestacion, Integer circuito) {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        
        String hql = "from ComportamientoADPIR WHERE " +
                        "id.region = ? AND " +
                        "id.subregion = ? AND " +
                        "id.subestacion = ? AND " +
                        "id.circuito = ? AND " +
                        "id.tipoVoltaje = 5 AND " +
                        "id.codigoVoltaje = 55 AND " +
                        "id.anno BETWEEN ? AND ? order by id.anno, id.mes";
        
        Object[] values = {region,subregion,subestacion,circuito,anoInicio,anoFinal};
        
        List comportamiento = hibernate.find(hql, values);
        
        return comportamiento;
    }    
}
