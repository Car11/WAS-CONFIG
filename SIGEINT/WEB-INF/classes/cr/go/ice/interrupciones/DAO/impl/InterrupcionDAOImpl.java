
package cr.go.ice.interrupciones.DAO.impl;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.vvs.utilidades.Fechas;

import cr.go.ice.interrupciones.DAO.InterrupcionDAO;
import cr.go.ice.interrupciones.domain.Interrupcion;
import cr.go.ice.interrupciones.domain.InterrupcionID;
import cr.go.ice.interrupciones.domain.procedure.Pevemq_SecPropia;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.InterrupcionDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>InterrupcionDAOImpl.java</code>Define los metodos de los Datos para Interrupcion.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class InterrupcionDAOImpl  extends HibernateDaoSupport implements InterrupcionDAO{

    private DataSource ds;    	

    /**
     * Metodo modificador de ds.
     * @param ds El ds a modificar.
     */
    public void setDs(DataSource ds) {
        this.ds = ds;
    }    

	/**
	 * @see cr.go.ice.interrupciones.DAO.InterrupcionDAO#agregar(cr.go.ice.interrupciones.domain.Interrupcion)
	 */
	public void agregar(Interrupcion interrupcion) {	    
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.save(interrupcion);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.InterrupcionDAO#modificar(cr.go.ice.interrupciones.domain.Interrupcion)
	 */
	public void modificar(Interrupcion interrupcion) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.flush();
		hibernate.clear();
		hibernate.update(interrupcion);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.InterrupcionDAO#eliminar(cr.go.ice.interrupciones.domain.Interrupcion)
	 */
	public void eliminar(Interrupcion interrupcion) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.delete(interrupcion);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.InterrupcionDAO#getInterrupcion(cr.go.ice.interrupciones.domain.InterrupcionID)
	 */
	public Interrupcion getInterrupcion(InterrupcionID interrupcionID) {
		Interrupcion interrupcion = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Interrupcion WHERE interrupcionID.aa = ? AND " +
				"interrupcionID.codigoOficina = ? AND " +
				"interrupcionID.numeroInterrupcion = ?";
		Object[] values = {interrupcionID.getAa(),
		        interrupcionID.getCodigoOficina(),
		        interrupcionID.getNumeroInterrupcion()};
		List interrupciones = hibernate.find(hql, values);
		if(interrupciones.size() > 0){
		    interrupcion = (Interrupcion) interrupciones.get(0);
		}
		return interrupcion;  		
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.InterrupcionDAO#getInterrupciones()
	 */
	public List getInterrupciones() {		
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Interrupcion";
		List interrupciones = hibernate.find(hql);
		return interrupciones;			
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.InterrupcionDAO#interrupcionExiste(cr.go.ice.interrupciones.domain.InterrupcionID)
	 */
	public boolean interrupcionExiste(InterrupcionID interrupcionID) {
		return (this.getInterrupcion(interrupcionID) != null);
	}

    /**
     * @see cr.go.ice.interrupciones.DAO.InterrupcionDAO#ejecutarIndicesPropios(java.lang.Integer, java.lang.Integer)
     */
    public String ejecutarIndicesPropios(Integer ano, Integer mes) {
        try{                                   
            Pevemq_SecPropia proc = new Pevemq_SecPropia(this.ds);            
            String resultado = proc.execute(ano, mes);
	        return resultado;
        }        
        catch(Exception e){
            return "N";
        }
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.InterrupcionDAO#getInterrupcionesPorCircuito(java.lang.Integer, java.util.Date, java.util.Date, java.lang.String, java.lang.Boolean, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    public Long getInterrupcionesPorCircuito(Integer codigoOficina, Date fechaInicio, Date fechaFinal, String voltajes, Boolean causa1, Integer bitacora, Integer codigoCircuito, Integer subEstacion) {
		List interrupciones = null;			
		HibernateTemplate hibernate = this.getHibernateTemplate();
        Integer diaInicio = new Integer(Integer.parseInt(Fechas.dateToString(fechaInicio, "yyyyMMdd")));
        Integer diaFinal = new Integer(Integer.parseInt(Fechas.dateToString(fechaFinal, "yyyyMMdd")));
		String hql = "SELECT COUNT(*) FROM Interrupcion AS interup " +
		"WHERE interup.interrupcionID.codigoOficina = ? AND " +
        "(to_number(to_char(interup.fechaInicio, 'rrrrmmdd')) >= ? ) " +
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

    /**
     * @see cr.go.ice.interrupciones.DAO.InterrupcionDAO#getInterrupcionesPorPeriodo(java.lang.Integer, java.lang.Integer, java.util.Date, java.util.Date)
     */
    public Long getInterrupcionesPorPeriodo(Integer codigoOficina, Integer codigoAgencia, Date fechaInicio, Date fechaFinal) {
		List interrupciones = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();   
        Integer diaInicio = new Integer(Integer.parseInt(Fechas.dateToString(fechaInicio, "yyyyMMdd")));
        Integer diaFinal = new Integer(Integer.parseInt(Fechas.dateToString(fechaFinal, "yyyyMMdd")));
		String hql = "SELECT COUNT(*) FROM Interrupcion AS interup " +
			"WHERE (to_number(to_char(interup.fechaInicio, 'rrrrmmdd')) >= ? ) " +
			"AND (to_number(to_char(interup.fechaFin, 'rrrrmmdd')) <= ? ) AND ";
		if(codigoOficina != null)
		    hql += "interup.interrupcionID.codigoOficina = ? ";
		else
		    hql += "interup.codigoAgencia = ? ";		
		
		if(codigoOficina != null){
		    Object values [] = {diaInicio,diaFinal,codigoOficina};
		    interrupciones = hibernate.find(hql,values);
		}
		else{
		    Object values [] = {diaInicio,diaFinal,codigoAgencia}; 
		    interrupciones = hibernate.find(hql,values);
		}	
        Long cantidad = new Long(0);
        if(interrupciones.size() > 0)
            cantidad = (Long)interrupciones.get(0);
        return cantidad;
    }
    
    /**
     * @see cr.go.ice.interrupciones.DAO.InterrupcionDAO#getAnosDeInterrupciones()
     */
    public List getAnosDeInterrupciones() {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "SELECT DISTINCT interup.interrupcionID.aa from Interrupcion interup ORDER BY interup.interrupcionID.aa DESC";
		List anos = hibernate.find(hql);
		return anos;			
		
    } 	
    
    public boolean existeInterrupciones(Integer reg, Integer subR, Integer sub, Integer cir, Long sec) {
        HibernateTemplate hibernate = this.getHibernateTemplate();      

        String hql = "FROM Interrupcion AS interr " +
                     "WHERE interr.region = ? " +
                     "AND interr.subRegion = ? " +
                     "AND interr.subEstacion = ? AND interr.circuito = ? AND interr.seccion = ? ";
        Object[] values = {reg, subR, sub, cir, sec};
        List lista = hibernate.find(hql, values);
        if(lista.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
	    
}


