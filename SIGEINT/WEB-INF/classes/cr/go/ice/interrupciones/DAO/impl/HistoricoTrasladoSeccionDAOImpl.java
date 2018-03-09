package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.HistoricoTrasladoSeccionDAO;
import cr.go.ice.interrupciones.domain.HistoricoTrasladoSeccion;

/**
 * <p>TODO <<Interface|Clase>> cr.go.ice.interrupciones.DAO.impl.HistoricoTrasladoSeccionDAOImpl.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>HistoricoTrasladoSeccionDAOImpl.java</code>.</p>
 * <p>Fecha creación: 02/03/2011</p>
 * <p>Última actualización: 02/03/2011</p>
 * @author Vista Verde Tecnología (root)
 * @version 1.0
 */
public class HistoricoTrasladoSeccionDAOImpl extends HibernateDaoSupport implements HistoricoTrasladoSeccionDAO{

    public void agregar(HistoricoTrasladoSeccion historicoTrasladoSeccion){
        HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.save(historicoTrasladoSeccion);
    }
    
    public void modificar(HistoricoTrasladoSeccion historicoTrasladoSeccion){
        HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.update(historicoTrasladoSeccion);
    }
    
    public void eliminar(HistoricoTrasladoSeccion historicoTrasladoSeccion){
        HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.delete(historicoTrasladoSeccion);
    }
    
    public HistoricoTrasladoSeccion buscar(HistoricoTrasladoSeccion historicoTrasladoSeccion){
        HistoricoTrasladoSeccion seccion = null;
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "FROM HistoricoTrasladoSeccion hts WHERE hts.id.fecha = ? ";
        Object[] values = {historicoTrasladoSeccion.getId().getFecha()};
        List lista = hibernate.find(hql, values);
        if(lista.size()>0){
            seccion = (HistoricoTrasladoSeccion) lista.get(0);
        }
        return seccion; 
    }
    
    public boolean existe(HistoricoTrasladoSeccion historicoTrasladoSeccion){
        
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "FROM HistoricoTrasladoSeccion hts WHERE hts.id.fecha = ? AND hts.id.region = ? AND hts.id.subRegion = ? AND " +
                "hts.id.subEstacion = ? AND hts.id.circuito = ? AND hts.id.seccion = ? AND hts.id.abonadoSeccion = ? AND hts.id.nuevaRegion = ? AND " +
                "hts.id.nuevaSubRegion = ? AND hts.id.nuevaSubEstacion = ? AND hts.id.nuevoCircuito = ? AND hts.id.nuevaSeccion = ? AND hts.id.usuario = ?";
        Object[] values = {historicoTrasladoSeccion.getId().getFecha(), historicoTrasladoSeccion.getId().getRegion(), historicoTrasladoSeccion.getId().getSubRegion(), historicoTrasladoSeccion.getId().getSubEstacion(), historicoTrasladoSeccion.getId().getCircuito(), historicoTrasladoSeccion.getId().getSeccion(), 
                            historicoTrasladoSeccion.getId().getAbonadoSeccion(), historicoTrasladoSeccion.getId().getNuevaRegion(), historicoTrasladoSeccion.getId().getNuevaSubRegion(), historicoTrasladoSeccion.getId().getNuevaSubEstacion(), historicoTrasladoSeccion.getId().getNuevoCircuito(), historicoTrasladoSeccion.getId().getNuevaSeccion(), historicoTrasladoSeccion.getId().getUsuario()};
        List lista = hibernate.find(hql, values);
        
        if(!lista.isEmpty()){
            return true;
        }else{
            return false;
        }
        
    }
    
    public List getHistoricoTrasladoSeccion(){
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "FROM HistoricoTrasladoSeccion hts ORDER BY hts.id.seccion";
        List lista = hibernate.find(hql);
        return lista;
    }
}
