package cr.go.ice.interrupciones.DAO.impl;

import java.util.Date;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.vvs.utilidades.Fechas;

import cr.go.ice.interrupciones.DAO.ReporteDAO;
import cr.go.ice.interrupciones.domain.Reporte;
import cr.go.ice.interrupciones.domain.ReporteID;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.ReporteDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ReporteDAOImpl.java</code>Define los metodos de los Datos para Reporte.</p>
 * <p>Fecha creación: 20/04/2007</p>
 * <p>Ultima actualización: 20/04/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ReporteDAOImpl extends HibernateDaoSupport implements ReporteDAO{
	    
    /**
     * Constructor de la clase: ReporteDAOImpl
     * 
     */
    public ReporteDAOImpl(){}

	/**
	 * @see cr.go.ice.interrupciones.DAO.ReporteDAO#agregar(cr.go.ice.interrupciones.domain.Reporte)
	 */
	public void agregar(Reporte reporte) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();	       
        hibernate.save(reporte);
	}
	
	/**
	 * @see cr.go.ice.interrupciones.DAO.ReporteDAO#modificar(cr.go.ice.interrupciones.domain.Reporte)
	 */
	public void modificar(Reporte reporte) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();	 	    
        hibernate.update(reporte);        
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.ReporteDAO#eliminar(cr.go.ice.interrupciones.domain.Reporte)
	 */
	public void eliminar(Reporte reporte) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.delete(reporte);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.ReporteDAO#getReporte(cr.go.ice.interrupciones.domain.ReporteID)
	 */
	public Reporte getReporte(ReporteID reporteID) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		Reporte reporte = null;
		
		String hql = "from Reporte r " +
				"WHERE r.reporteID.aa = ? " +
				"AND r.reporteID.codigoOficina = ? " +
				"AND r.reporteID.numeroReporte = ?";			
		Object[] values = {reporteID.getAa(), reporteID.getCodigoOficina(), reporteID.getNumeroReporte()};
		
		List resultado = hibernate.find(hql, values);
		if(resultado.size() > 0){
			reporte = (Reporte) resultado.get(0);
		}
		return reporte;
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.ReporteDAO#getReportes()
	 */
	public List getReportes() {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Reporte";
		return hibernate.find(hql);
	}
	
	/**
	 * @see cr.go.ice.interrupciones.DAO.ReporteDAO#reporteExiste(cr.go.ice.interrupciones.domain.ReporteID)
	 */
	public boolean reporteExiste(ReporteID reporteID) {
	    return this.getReporte(reporteID) != null ? true : false;
	}

    /**
     * @see cr.go.ice.interrupciones.DAO.ReporteDAO#getAnosDeReportes()
     */
    public List getAnosDeReportes() {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "SELECT DISTINCT rep.reporteID.aa from Reporte rep ORDER BY rep.reporteID.aa DESC";
        List anos = hibernate.find(hql);
        return anos;    
    }		
    
    public Long getInterrupcionesPorCircuitoMenor5Min(Integer codigoOficina, Date fechaInicio, Date fechaFinal, String voltajes, Boolean causa1, Integer bitacora, Integer codigoCircuito, Integer subEstacion, Boolean causa411) {
        List interrupciones = null;         
        HibernateTemplate hibernate = this.getHibernateTemplate();
        Integer diaInicio = new Integer(Integer.parseInt(Fechas.dateToString(fechaInicio, "yyyyMMdd")));
        Integer diaFinal = new Integer(Integer.parseInt(Fechas.dateToString(fechaFinal, "yyyyMMdd")));
        String hql = "SELECT COUNT(*) FROM Reporte AS interup " +
        "WHERE interup.reporteID.codigoOficina = ? " +
        "AND (to_number(to_char(interup.fechaInicio, 'rrrrmmdd')) >= ? ) " +
        "AND (to_number(to_char(interup.fechaFin, 'rrrrmmdd')) <= ? ) ";
        
        if(codigoCircuito.intValue() != 0)
            hql += "AND interup.circuito = ? AND interup.subEstacion = ? ";
        
        if(causa1.booleanValue())
            hql += " AND interup.causa1 = 417 ";    
        else
            hql += "AND interup.codigoVoltaje IN (" + voltajes + ") ";        

        
        if(bitacora.intValue() == 1)
            hql += "AND interup.bitacora = 1 ";
        else{
            if(bitacora.intValue() == 0)
                hql += "AND interup.bitacora = 0 ";             
        }               
        if(causa411.booleanValue())
            hql += " AND interup.causa1 <> 411 ";    
                                     
        if(codigoCircuito.intValue() != 0){
            Object values [] = {codigoOficina,diaInicio,diaFinal,codigoCircuito,subEstacion};
            interrupciones = hibernate.find(hql,values);
        }
        else{
            Object values [] = {codigoOficina,diaInicio,diaFinal}; 
            interrupciones = hibernate.find(hql,values);
        }
        Long cantidad = new Long(0);
        if(interrupciones.size() > 0)
            cantidad = (Long)interrupciones.get(0);
        return cantidad;
    }
    
    public boolean existeReportes(Integer reg, Integer subR, Integer sub, Integer cir, Long sec) {
        HibernateTemplate hibernate = this.getHibernateTemplate();      

        String hql = "FROM Reporte AS reporte " +
                     "WHERE reporte.region = ? " +
                     "AND reporte.subRegion = ? " +
                     "AND reporte.subEstacion = ? AND reporte.circuito = ? AND reporte.seccion = ? ";
        Object[] values = {reg, subR, sub, cir, sec};
        List lista = hibernate.find(hql, values);
        if(lista.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
	
}
