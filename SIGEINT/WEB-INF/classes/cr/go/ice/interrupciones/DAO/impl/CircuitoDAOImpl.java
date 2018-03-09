package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import cr.go.ice.interrupciones.DAO.CircuitoDAO;
import cr.go.ice.interrupciones.domain.Circuito;
import cr.go.ice.interrupciones.domain.procedure.Pevemq_CauCirGlobal;
import cr.go.ice.interrupciones.domain.procedure.Pevemq_CirGlobal;
import cr.go.ice.interrupciones.domain.procedure.Pevemq_TriCirGlobal;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.CircuitoDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>CircuitoDAOImpl.java</code>Define los metodos de los Datos para Circuito.</p>
 * <p>Fecha creación: 08/01/2007</p>
 * <p>Ultima actualización: 08/01/2007</p>
 * @author Vista Verde Tecnologia (Mario Leon y Doc Cristian)
 * @version 1.1
 */
public class CircuitoDAOImpl  extends HibernateDaoSupport implements CircuitoDAO {


    /** atributo <code>ds</code>*/
    private DataSource ds;    	

    /**
     * Metodo modificador de ds.
     * @param ds El ds a modificar.
     */
    public void setDs(DataSource ds) {
        this.ds = ds;
    }
    
    /**
     * @see cr.go.ice.interrupciones.DAO.CircuitoDAO#agregar(cr.go.ice.interrupciones.domain.Circuito)
     */
    public void agregar(Circuito circuito) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.save(circuito);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.CircuitoDAO#modificar(cr.go.ice.interrupciones.domain.Circuito)
     */
    public void modificar(Circuito circuito) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.update(circuito);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.CircuitoDAO#eliminar(cr.go.ice.interrupciones.domain.Circuito)
     */
    public void eliminar(Circuito circuito) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.delete(circuito);
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.CircuitoDAO#buscar(java.lang.Integer, java.lang.Integer)
     */
    public Circuito buscar(Integer subEstacion, Integer circuito) {
        
        Circuito cir = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Circuito cir WHERE cir.circuitoID.circuito = ? " +
        		"AND cir.circuitoID.subEstacion.codigoSubEstacion = ?";
		Object[] values = {circuito, subEstacion};
		List circuitos = hibernate.find(hql, values);
		if(circuitos.size() > 0){
		    cir = (Circuito) circuitos.get(0);
		}
		return cir;               
        
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.CircuitoDAO#buscar(java.lang.Integer)
     */
    public List buscar(Integer subEstacion) {
        
        List circuitos = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Circuito cir WHERE cir.circuitoID.subEstacion.codigoSubEstacion = ? ORDER BY cir.nombreCircuito";
		Object[] values = {subEstacion};
		circuitos = hibernate.find(hql, values);
		return circuitos;           
        
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.CircuitoDAO#getCircuitos()
     */
    public List getCircuitos() {
        
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Circuito ORDER BY nombreCircuito";
		List circuitos = hibernate.find(hql);
		return circuitos;	            
        
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.CircuitoDAO#circuitoExiste(java.lang.Integer, java.lang.Integer)
     */
    public boolean circuitoExiste(Integer subEstacion, Integer circuito) {
        return (this.buscar(subEstacion,circuito) != null);
    }

	/**
	 * @see cr.go.ice.interrupciones.DAO.CircuitoDAO#getCircuitos(java.lang.Integer)
	 */
	public List getCircuitos(Integer subEstacion) {
		
        List circuitos = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Circuito cir where cir.circuitoID.subEstacion.codigoSubEstacion = ? ORDER BY cir.circuitoID.circuito";
		Object[] values = {subEstacion};
		circuitos = hibernate.find(hql, values);
		return circuitos;   		
		
	}
	
	/**
	 * @see cr.go.ice.interrupciones.DAO.CircuitoDAO#getCircuitos(java.lang.Integer)
	 */
	public List getCircuitosActivas(Integer subEstacion) {
		
        List circuitos = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Circuito cir where cir.circuitoID.subEstacion.codigoSubEstacion = ? AND(cir.kmsCircuito!=0 AND cir.abonadoCircuito!=0) ORDER BY cir.circuitoID.circuito";
		Object[] values = {subEstacion};
		circuitos = hibernate.find(hql, values);
		return circuitos;   		
		
	}

    /**
     * @see cr.go.ice.interrupciones.DAO.CircuitoDAO#ejecutarIndicesGlobales(java.lang.Integer, java.lang.Integer)
     */
    public String ejecutarIndicesGlobales(Integer ano, Integer mes) {
        try{              
            Pevemq_CirGlobal proc = new Pevemq_CirGlobal(this.ds);            
            String resultado = proc.execute(ano, mes);
	        return resultado;                    	        
        }        
        catch(Exception e){
            return "N";
        }
    }
  
    /**
     * @see cr.go.ice.interrupciones.DAO.CircuitoDAO#ejecutarIndicesCausa(java.lang.Integer, java.lang.Integer)
     */
    public String ejecutarIndicesCausa(Integer ano, Integer mes) {
        try{                     
            Pevemq_CauCirGlobal proc = new Pevemq_CauCirGlobal(this.ds);            
            String resultado = proc.execute(ano, mes);
	        return resultado;                
        }        
        catch(Exception e){
            return "N";
        }
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.CircuitoDAO#ejecutarIndicesTrifasicos(java.lang.Integer, java.lang.Integer)
     */
    public String ejecutarIndicesTrifasicos(Integer ano, Integer mes) {
        try{   
            Pevemq_TriCirGlobal proc = new Pevemq_TriCirGlobal(this.ds);            
            String resultado = proc.execute(ano, mes);
	        return resultado;              
        }        
        catch(Exception e){
            return "N";
        }
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.CircuitoDAO#getCircuitosPorOficina(java.lang.Integer)
     */
    public List getCircuitosPorOficina(Integer codigoOficina) {        
        List circuitos = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "SELECT DISTINCT cir FROM Seccion sec, Circuito cir where sec.codigoOficina = ? " +
        		"AND sec.seccionID.subEstacion = cir.circuitoID.subEstacion.codigoSubEstacion AND " +
        		"sec.seccionID.circuito = cir.circuitoID.circuito " +
                "ORDER BY cir.circuitoID.subEstacion.codigoSubEstacion, cir.circuitoID.circuito";
		Object[] values = {codigoOficina};
		circuitos = hibernate.find(hql, values);
		return circuitos;           
        
    }
    
    

    /**
     * @see cr.go.ice.interrupciones.DAO.CircuitoDAO#getNombreTipo(java.lang.Integer)
     */
    public String getNombreTipo(Integer tipo) {
        String respuesta = "N/A";
        if(tipo!=null){
            switch(tipo.intValue()){
        		case 0:{
        		    respuesta="Oro";
        		    break;
        		}
        		case 1:{
        		    respuesta="Plata";
        		    break;
        		}
        		
        		case 2:{
        		    respuesta="Bronce";
        		    break;
        		}
        		default:{
        		    respuesta="N/A";
        		    break;
        		}
        	}
    	}
        return respuesta;
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.CircuitoDAO#getCircuitosOrdenPorCodigo()
     */
    public List getCircuitosOrdenPorCodigo() {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "from Circuito ORDER BY circuitoID.subEstacion.codigoSubEstacion, circuitoID.circuito";
        List circuitos = hibernate.find(hql);
        return circuitos;       
    }
}
