package cr.go.ice.interrupciones.DAO.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

//import com.ibm.ISecurityL13SupportImpl.sec;

import cr.go.ice.interrupciones.DAO.SeccionDAO;
import cr.go.ice.interrupciones.domain.CambioSeccion;
import cr.go.ice.interrupciones.domain.Seccion;
import cr.go.ice.interrupciones.domain.SeccionID;
import cr.go.ice.interrupciones.domain.procedure.PevemqCausecGlobal;
import cr.go.ice.interrupciones.domain.procedure.PevemqSecGlobal;
import cr.go.ice.interrupciones.domain.procedure.PevemqTrisecPropio;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.SeccionDAOImpl.java</p>
 * <p>Modulo (subsistema): InterrupcionesWeb</p>
 * <p>Descricion de <code>SeccionDAOImpl.java</code>Define los metodos de los Datos para Seccion.</p>
 * <p>Fecha creación: 24/02/2007</p>
 * <p>Ultima actualización: 24/02/2007</p>
 * @author Vista Verde Tecnologia (Cesar y Doc Cristian)
 * @version 1.1
 */
public class SeccionDAOImpl extends HibernateDaoSupport implements SeccionDAO {

    private DataSource ds;
    
    private final static String PEVEMQ_CAMBIOSECCIONGLOBAL = "{ call PEVEMQ_CAMBIOSECCIONGLOBAL(?,?,?,?,?,?,?,?,?,?)}";
    
    private final static String PEVEMQ_ACTUALIZARTRAMOS = "{ call PEVEMQ_ACTUALIZARTRAMOS()}";
    
	/**
	 * Constructor de la clase: SeccionDAOImpl
	 */
	public SeccionDAOImpl() {}
    
    
    /**
     * Metodo modificador de ds.
     * @param ds El ds a modificar.
     */
    public void setDs(DataSource ds) {
        this.ds = ds;
    }
    
    /**
     * Método accesor del atributo ds.
     * @return Retorna el atributo ds.
     */
    public DataSource getDs() {
        return this.ds;
    }

	/**
	 * @see cr.go.ice.interrupciones.DAO.SeccionDAO#agregar(cr.go.ice.interrupciones.domain.Seccion)
	 */
	public void agregar(Seccion seccion) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.save(seccion);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.SeccionDAO#modificar(cr.go.ice.interrupciones.domain.Seccion)
	 */
	public void modificar(Seccion seccion) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.update(seccion);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.SeccionDAO#eliminar(cr.go.ice.interrupciones.domain.Seccion)
	 */
	public void eliminar(Seccion seccion) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.delete(seccion);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.SeccionDAO#buscar(cr.go.ice.interrupciones.domain.SeccionID)
	 */
	public Seccion buscar(SeccionID seccionID) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
	    Seccion seccion = null;
	    
	    String hql = "from Seccion s where s.seccionID.circuito = ? " +
	    		"AND s.seccionID.seccion = ? " +
	    		"AND s.seccionID.subEstacion = ?";
	    Object[] values = {seccionID.getCircuito(), seccionID.getSeccion(), seccionID.getSubEstacion()};
	    List resultado = hibernate.find(hql, values);

	    if(resultado.size()>0){
	        seccion = (Seccion) resultado.get(0);
		}
	    return seccion;
	}
	
	/**
	 * @see cr.go.ice.interrupciones.DAO.SeccionDAO#getSecciones()
	 */
	public List getSecciones() {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
	    String hql = "from Seccion";
		return hibernate.find(hql);

	}

    /**
     * @see cr.go.ice.interrupciones.DAO.SeccionDAO#existe(cr.go.ice.interrupciones.domain.Seccion)
     */
	public boolean existe(Seccion seccion) {
	    return this.buscar(seccion.getSeccionID())!=null?true:false;
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.SeccionDAO#getSecciones(java.lang.Integer, java.lang.Integer)
	 */
	public List getSecciones(Integer subEstacion, Integer circuito) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();		

	    String hql = " FROM Seccion s " +
					 " WHERE s.seccionID.subEstacion = ? " +
					 "AND s.seccionID.circuito = ? " +
					 "AND congelado = 0 " +
					 "ORDER BY s.seccionID.seccion";
		Object[] values = {subEstacion, circuito};
		return hibernate.find(hql, values);
	}
	
	/**
	 * 
	 * @see cr.go.ice.interrupciones.DAO.SeccionDAO#getBorrarSecciones(java.lang.Integer, java.lang.Integer)
	 */
	public long getBorrarSecciones(Integer subEstacion, Integer circuito) {
        HibernateTemplate hibernate = this.getHibernateTemplate();      

        String hql = " FROM Seccion s " +
                     " WHERE s.seccionID.subEstacion = ? " +
                     "AND s.seccionID.circuito = ? " +
                     //"AND congelado = 0 " +
                     "ORDER BY s.seccionID.seccion";
        Object[] values = {subEstacion, circuito};
        List lista = hibernate.find(hql, values);
        if(lista.isEmpty()){
            return 0;
        }else{
            return lista.size();
        }
    }
	
    /**
     * @see cr.go.ice.interrupciones.DAO.SeccionDAO#getSecciones(java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    public List getSecciones(Integer subEstacion, Integer circuito,
            Integer orden) {
        List secciones = null;
        if(orden!=null && orden.equals(Seccion.ORDEN_NOMBRE)){
    	    HibernateTemplate hibernate = this.getHibernateTemplate();		

    	    String hql = " FROM Seccion s " +
    					 " WHERE s.seccionID.subEstacion = ? " +
    					 "AND s.seccionID.circuito = ? " +
    					 "AND congelado = 0 " +
    					 "ORDER BY s.nombreSeccion";
    		Object[] values = {subEstacion, circuito};
    		secciones = hibernate.find(hql, values);
        }else{
            secciones = this.getSecciones(subEstacion, circuito);
        }
        return secciones;
    }
    
    public List getSecciones(Integer subEstacion, Integer circuito, Integer subregion, Integer region, Integer orden) {
        List secciones = null;
        if(orden!=null && orden.equals(Seccion.ORDEN_NOMBRE)){
            HibernateTemplate hibernate = this.getHibernateTemplate();      

            String hql = " FROM Seccion s " +
                         " WHERE s.region = ? " +
                         "AND s.subRegion = ? " +
                         "AND s.seccionID.subEstacion = ? " +
                         "AND s.seccionID.circuito = ? " +
                         "AND congelado = 0 " +
                         "ORDER BY s.nombreSeccion";
            Object[] values = {region, subregion, subEstacion, circuito};
            secciones = hibernate.find(hql, values);
        }else{
            HibernateTemplate hibernate = this.getHibernateTemplate();      

            String hql = " FROM Seccion s " +
                         " WHERE s.region = ? " +
                         "AND s.subRegion = ? " +
                         "AND s.seccionID.subEstacion = ? " +
                         "AND s.seccionID.circuito = ? " +
                         "ORDER BY s.seccionID.seccion";
            Object[] values = {region, subregion, subEstacion, circuito};
            secciones = hibernate.find(hql, values);
        }
        return secciones;
    }
    
    /**
     * 
     * @see cr.go.ice.interrupciones.DAO.SeccionDAO#getSeccionesFiltro(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @SuppressWarnings("rawtypes")
    public List getSeccionesFiltro(Integer region, Integer subregion, Integer subEstacion, Integer circuito) {
        List secciones = null;
        HibernateTemplate hibernate = this.getHibernateTemplate();      

        String hql = " FROM Seccion s " +
                     " WHERE s.region = ?" +
                     "AND s.subRegion = ? " +
                     "AND s.seccionID.subEstacion = ? " +
                     "AND s.seccionID.circuito = ? " +
                     "ORDER BY s.seccionID.seccion";
        Object[] values = {region, subregion, subEstacion, circuito};
        secciones = hibernate.find(hql, values);
        return secciones;
    }
    
    public List getSeccionesOrigen(Integer region, Integer subregion, Integer subEstacion, Integer circuito) {
        HibernateTemplate hibernate = this.getHibernateTemplate();      

        String hql = " FROM Seccion s " +
                     " WHERE s.region = ?" +
                     "AND s.subRegion = ? " +
                     "AND s.seccionID.subEstacion = ? " +
                     "AND s.seccionID.circuito = ? " +
                     "AND congelado = 0 " +
                     "ORDER BY s.seccionID.seccion";
        Object[] values = {region, subregion, subEstacion, circuito};
        return hibernate.find(hql, values);
    }
    
    
    /**
     * @see cr.go.ice.interrupciones.DAO.SeccionDAO#getTodasSecciones(java.lang.Integer, java.lang.Integer)
     */
    public List getTodasSecciones(Integer subEstacion, Integer circuito) {
        HibernateTemplate hibernate = this.getHibernateTemplate();      

        String hql = " FROM Seccion s " +
                     " WHERE s.seccionID.subEstacion = ? " +
                     "AND s.seccionID.circuito = ? " +
                     "ORDER BY s.seccionID.seccion";
        Object[] values = {subEstacion, circuito};
        return hibernate.find(hql, values);
    }    
    
    /**
     * @see cr.go.ice.interrupciones.DAO.SeccionDAO#getSeccionesEscalar(java.lang.Integer, java.lang.Integer)
     */
    public long getSeccionesEscalar(Integer subEstacion, Integer circuito) {	
		return this.getSecciones(subEstacion, circuito).size();		
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.SeccionDAO#ejecutarIndicesGlobales(java.lang.Integer, java.lang.Integer)
     */
    public String ejecutarIndicesGlobales(Integer ano, Integer mes) {
        try{
	        PevemqSecGlobal procedimiento = new PevemqSecGlobal(this.ds);
	        String resultado = procedimiento.execute(ano, mes);
	        return resultado;
        }        
        catch(Exception e){
            return "N";
        }
    }
    
    /**
     * @see cr.go.ice.interrupciones.DAO.SeccionDAO#ejecutarIndicesCausa(java.lang.Integer, java.lang.Integer)
     */
    public String ejecutarIndicesCausa(Integer ano, Integer mes) {
        try{
	        PevemqCausecGlobal procedimiento = new PevemqCausecGlobal(this.ds);
	        return procedimiento.execute(ano,mes);
        }
        catch(Exception e){
            return "N";
        }
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.SeccionDAO#ejecutarIndicesTrifasicos(java.lang.Integer, java.lang.Integer)
     */
    public String ejecutarIndicesTrifasicos(Integer ano, Integer mes) {
        try{
	        PevemqTrisecPropio procedimiento = new PevemqTrisecPropio(this.ds);
	        return procedimiento.execute(ano, mes);
	    }        
	    catch(Exception e){
	        return "N";
	    }        
    }    

    /**
     * @see cr.go.ice.interrupciones.DAO.SeccionDAO#registrosAsociados(cr.go.ice.interrupciones.domain.SeccionID)
     */
    public Long registrosAsociados(SeccionID seccionID) {
        Long cantidad = new Long(0);
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "SELECT COUNT(*) from Interrupcion interup WHERE interup.subEstacion = ? " +
        "AND interup.circuito = ? " +
        "AND interup.seccion = ? ";
        
        
        Object values [] = {seccionID.getSubEstacion(), seccionID.getCircuito(), seccionID.getSeccion()};
        List interrupciones = hibernate.find(hql, values);
        
        if(interrupciones.size() > 0){
            cantidad = (Long)interrupciones.get(0);             
        }
        
        hql = "SELECT COUNT(*) from Reporte rep WHERE rep.subEstacion = ? " +
        "AND rep.circuito = ? " +
        "AND rep.seccion = ? ";
        
        Object values2 [] = {seccionID.getSubEstacion(), seccionID.getCircuito(), seccionID.getSeccion()};
        interrupciones = hibernate.find(hql, values2);
        
        if(interrupciones.size() > 0){
            Long cantidadRep = (Long)interrupciones.get(0);
            cantidad = new Long(cantidad.longValue() + cantidadRep.longValue());                
        }       
        
        return cantidad;   
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.SeccionDAO#getSeccionesPorOficina(java.lang.Integer)
     */
    public List getSeccionesPorOficina(Integer codigoOficina) {
        HibernateTemplate hibernate = this.getHibernateTemplate();      
        String hql = "FROM Seccion s " +
                     "WHERE s.codigoOficina = ? " +
                     "ORDER BY s.seccionID.subEstacion, s.seccionID.circuito, s.seccionID.seccion";
        Object[] values = {codigoOficina};
        return hibernate.find(hql, values);
    }
    
    public List getSeccionesDestino(Integer subEstacion, Integer circuito) {
        HibernateTemplate hibernate = this.getHibernateTemplate();      

        String hql = " FROM Seccion s " +
                     " WHERE s.seccionID.subEstacion = ? " +
                     "AND s.seccionID.circuito = ? " +
                     "AND congelado = 2 " +
                     "ORDER BY s.seccionID.seccion";
        Object[] values = {subEstacion, circuito};
        return hibernate.find(hql, values);
    }
    
    public List getSeccionesDestino(Integer region, Integer subregion, Integer subEstacion, Integer circuito) {
        HibernateTemplate hibernate = this.getHibernateTemplate();      

        String hql = " FROM Seccion s " +
                     " WHERE s.region = ?" +
                     "AND s.subRegion = ? " +
                     "AND s.seccionID.subEstacion = ? " +
                     "AND s.seccionID.circuito = ? " +
                     "AND congelado = 2 " +
                     "ORDER BY s.seccionID.seccion";
        Object[] values = {region, subregion, subEstacion, circuito};
        return hibernate.find(hql, values);
    }
    
    public String actualizarTramo(){
        Connection conn = null;
        CallableStatement declaracionCall = null;
        Integer error = Integer.valueOf(0);// cero error
        try {
            conn = this.getDs().getConnection();
            declaracionCall = conn.prepareCall(PEVEMQ_ACTUALIZARTRAMOS); //llamado al procedimiento
            declaracionCall.execute();    
            conn.commit();
            error = declaracionCall.getInt(10);
            if(error.equals(Integer.valueOf(1))){
                return "simap";
            }
            if(error.equals(Integer.valueOf(0))){
                return "error";
            }
            if(error.equals(Integer.valueOf(3))){
                return "success";
            }
        }catch (Exception e) {
            e.printStackTrace();
            try {if(conn            != null){conn.rollback();}} catch (SQLException e1) {e1.printStackTrace(); }
        }finally{
            try {
                if(declaracionCall != null){
                    declaracionCall.close();
                }
            }catch (SQLException e){
                e.printStackTrace(); 
                }
            try 
                {
                    if(conn != null){
                        conn.close();           
                    }
                }catch (SQLException e){
                    e.printStackTrace(); 
                    }
        }
        return "error";
    }

    public String cambioSecciones(Seccion seccion, Integer tipo, String cedula){
        Connection conn = null;
        CallableStatement declaracionCall = null;
        Integer error = Integer.valueOf(0);// cero error
        try {
            conn = this.getDs().getConnection();
            declaracionCall = conn.prepareCall(PEVEMQ_CAMBIOSECCIONGLOBAL); //llamado al procedimiento
            declaracionCall.setInt(1, tipo);
            declaracionCall.setInt(2, seccion.getRegion());
            declaracionCall.setInt(3, seccion.getSubRegion());                        
            declaracionCall.setInt(4, seccion.getSeccionID().getSubEstacion());   
            declaracionCall.setInt(5, seccion.getSeccionID().getCircuito());
            declaracionCall.setLong(6, seccion.getSeccionID().getSeccion());                        
            declaracionCall.setDouble(7, seccion.getKmsSeccion()); 
            declaracionCall.setLong(8, seccion.getAbonadoSeccion());
            declaracionCall.setString(9, cedula);
            declaracionCall.registerOutParameter(10, Types.INTEGER);
            declaracionCall.execute();    
            conn.commit();
            error = declaracionCall.getInt(10);
            if(error.equals(Integer.valueOf(1))){
                return "simap";
            }
            if(error.equals(Integer.valueOf(0))){
                return "error";
            }
            if(error.equals(Integer.valueOf(3))){
                return "success";
            }
        }catch (Exception e) {
            e.printStackTrace();
            try {if(conn            != null){conn.rollback();}} catch (SQLException e1) {e1.printStackTrace(); }
        }finally{
            try {
                if(declaracionCall != null){
                    declaracionCall.close();
                }
            }catch (SQLException e){
                e.printStackTrace(); 
                }
            try 
                {
                    if(conn != null){
                        conn.close();           
                    }
                }catch (SQLException e){
                    e.printStackTrace(); 
                    }
        }
        return "error";
    }
    
    public Boolean agregarTodo(List<CambioSeccion> cambioSeccion){
        try{
            HibernateTemplate hibernate = this.getHibernateTemplate();
            hibernate.flush();
            hibernate.clear();
            hibernate.saveOrUpdateAll(cambioSeccion);
            return Boolean.TRUE;
        }catch (Exception e) {
           e.printStackTrace();
           return Boolean.FALSE;
        }
    }
    
    public Boolean elimiarSecciones(String cedula) {
        try{
            HibernateTemplate hibernate = this.getHibernateTemplate();      
            String hql = "DELETE FROM CambioSeccion cambioSeccion WHERE cambioSeccion.id.cedula = ? ";
            Object[] values = {cedula};
            hibernate.bulkUpdate(hql, values);
            return Boolean.TRUE;
        }catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
         }
    }
    
    public Boolean buscarCedula(String cedula) {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        Boolean seccion = Boolean.FALSE;
        
        String hql = "from CambioSeccion cs where cs.id.cedula = ? ";
        Object[] values = {cedula};
        List resultado = hibernate.find(hql, values);

        if(!resultado.isEmpty()){
            seccion = Boolean.TRUE;
        }
        return seccion;
    }
    
    public void agregar(CambioSeccion cambio) {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        hibernate.flush();
        hibernate.clear();
        hibernate.save(cambio);
    }
    
}
