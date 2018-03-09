package cr.go.ice.interrupciones.DAO.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.NoPropiaSeccionamientoDAO;
import cr.go.ice.interrupciones.domain.NoPropiaSeccionamiento;
import cr.go.ice.interrupciones.domain.NoPropiaSeccionamientoID;
import cr.go.ice.interrupciones.domain.Seccion;
import cr.go.ice.interrupciones.domain.procedure.Pevemq_SecNoPropia;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.NoPropiaSeccionamientoDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>NoPropiaSeccionamientoDAOImpl.java</code>Define los metodos de los Datos para NoPropiaSeccionamiento.</p>
 * <p>Fecha creación: 11/04/2007</p>
 * <p>Ultima actualización: 11/04/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class  NoPropiaSeccionamientoDAOImpl  extends HibernateDaoSupport implements NoPropiaSeccionamientoDAO {


    private DataSource ds;    	

    /**
     * Metodo modificador de ds.
     * @param ds El ds a modificar.
     */
    public void setDs(DataSource ds) {
        this.ds = ds;
    }
    
	/**
	 * @see cr.go.ice.interrupciones.DAO.NoPropiaSeccionamientoDAO#agregar(cr.go.ice.interrupciones.domain.NoPropiaSeccionamiento)
	 */
	public void agregar(NoPropiaSeccionamiento noPropiaSeccionamiento) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.save(noPropiaSeccionamiento);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.NoPropiaSeccionamientoDAO#agregar(java.util.List)
	 */
	public void agregar(List noPropiaSeccionamientos) {		
	    HibernateTemplate hibernate = this.getHibernateTemplate();
		if(noPropiaSeccionamientos != null){
			for(int i = 0; i < noPropiaSeccionamientos.size(); i++){
				NoPropiaSeccionamiento noPropiaSeccionamiento = (NoPropiaSeccionamiento)noPropiaSeccionamientos.get(i);
				if(noPropiaSeccionamiento != null)
				    hibernate.save(noPropiaSeccionamiento);
			}
		}
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.NoPropiaSeccionamientoDAO#modificar(cr.go.ice.interrupciones.domain.NoPropiaSeccionamiento)
	 */
	public void modificar(NoPropiaSeccionamiento noPropiaSeccionamiento) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.update(noPropiaSeccionamiento);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.NoPropiaSeccionamientoDAO#eliminar(cr.go.ice.interrupciones.domain.NoPropiaSeccionamiento)
	 */
	public void eliminar(NoPropiaSeccionamiento noPropiaSeccionamiento) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.delete(noPropiaSeccionamiento);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.NoPropiaSeccionamientoDAO#buscar(cr.go.ice.interrupciones.domain.NoPropiaSeccionamiento)
	 */
	public NoPropiaSeccionamiento buscar(NoPropiaSeccionamiento noPropiaSeccionamiento) {        
        NoPropiaSeccionamiento noPropiaSeccionamientoResultado = null;
        NoPropiaSeccionamientoID noPropiaSeccionamientoID = noPropiaSeccionamiento.getNoPropiaSeccionamientoID();
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from NoPropiaSeccionamiento WHERE noPropiaSeccionamientoID.aa = ? AND " +
				"noPropiaSeccionamientoID.codigoOficina = ? AND " +
				"noPropiaSeccionamientoID.numeroInterrupcion = ? AND " +
				"noPropiaSeccionamientoID.seccion = ?";
		Object[] values = {noPropiaSeccionamientoID.getAa(),
		        noPropiaSeccionamientoID.getCodigoOficina(),
		        noPropiaSeccionamientoID.getNumeroInterrupcion(),
		        noPropiaSeccionamientoID.getSeccion()};
		List seccionamientos = hibernate.find(hql, values);
		if(seccionamientos.size() > 0){
		    noPropiaSeccionamientoResultado = (NoPropiaSeccionamiento) seccionamientos.get(0);
		}
		return noPropiaSeccionamientoResultado;          
        
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.NoPropiaSeccionamientoDAO#getNoPropiaSeccionamientos()
	 */
	public List getNoPropiaSeccionamientos() {		
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from NoPropiaSeccionamiento";
		List noPropiaSeccionamientos = hibernate.find(hql);
		return noPropiaSeccionamientos;			
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.NoPropiaSeccionamientoDAO#noPropiaSeccionamientoExiste(cr.go.ice.interrupciones.domain.NoPropiaSeccionamiento)
	 */
	public boolean noPropiaSeccionamientoExiste(NoPropiaSeccionamiento noPropiaSeccionamiento) {
		return (this.buscar(noPropiaSeccionamiento) != null);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.NoPropiaSeccionamientoDAO#modificar(java.util.List, cr.go.ice.interrupciones.domain.NoPropiaSeccionamientoID)
	 */
	public void modificar(List noPropiaSeccionamiento, NoPropiaSeccionamientoID noPropiaSeccionamientoID) {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        Integer aa = noPropiaSeccionamientoID.getAa();
		Integer codigoOficina = noPropiaSeccionamientoID.getCodigoOficina();
		Long numeroInterrupcion = noPropiaSeccionamientoID.getNumeroInterrupcion();						
			String hql = "from NoPropiaSeccionamiento seccionamiento where seccionamiento.noPropiaSeccionamientoID.aa = ? AND " +		
		"seccionamiento.noPropiaSeccionamientoID.codigoOficina = ? AND " +
		"seccionamiento.noPropiaSeccionamientoID.numeroInterrupcion = ?";		
		Object[] values = {aa, 
		        codigoOficina,
		        numeroInterrupcion};
		hibernate.deleteAll(hibernate.find(hql,values));

		//		 agregamos los nuevo detalles	
        for(int i = 0; i < noPropiaSeccionamiento.size(); i++){
        	NoPropiaSeccionamiento noPropiaSeccionamientoNuevo = (NoPropiaSeccionamiento)noPropiaSeccionamiento.get(i);
        	hibernate.save(noPropiaSeccionamientoNuevo);
        }	            
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.NoPropiaSeccionamientoDAO#getNoPropiaSeccionamientos(cr.go.ice.interrupciones.domain.NoPropiaSeccionamientoID)
	 */
	public List getNoPropiaSeccionamientos(NoPropiaSeccionamientoID noPropiaSeccionamientoID) {
		List resultado = new ArrayList();		
		Integer aa = noPropiaSeccionamientoID.getAa();
		Integer codigoOficina = noPropiaSeccionamientoID.getCodigoOficina();
		Long numeroInterrupcion = noPropiaSeccionamientoID.getNumeroInterrupcion();	
		HibernateTemplate hibernate = this.getHibernateTemplate();

		String hql = "SELECT seccion, seccionamiento from Seccion seccion, NoPropiaSeccionamiento seccionamiento " +
				"where seccionamiento.noPropiaSeccionamientoID.aa = ? AND seccionamiento.noPropiaSeccionamientoID.codigoOficina = ? " +
				"AND seccionamiento.noPropiaSeccionamientoID.numeroInterrupcion = ? AND " +
				"seccion.seccionID.seccion = seccionamiento.noPropiaSeccionamientoID.seccion AND " +
				"seccion.seccionID.subEstacion = seccionamiento.subestacion AND " +
				"seccion.seccionID.circuito = seccionamiento.circuito";		
		Object[] values = {aa, 
		        codigoOficina,
		        numeroInterrupcion};
		List seccionamientos = hibernate.find(hql, values);
		if(seccionamientos.size() > 0){
		    resultado = new ArrayList();
			for(int i = 0; i < seccionamientos.size(); i++){
				Object [] obj = (Object[])seccionamientos.get(i);
				Seccion seccion = (Seccion)obj[0];
				NoPropiaSeccionamiento seccionamiento = (NoPropiaSeccionamiento)obj[1];
				seccionamiento.setNombreSeccion(seccion.getNombreSeccion());
				resultado.add(seccionamiento);
			}
		}		
		    
		return resultado;  			
		
	}

    /**
     * @see cr.go.ice.interrupciones.DAO.NoPropiaSeccionamientoDAO#ejecutarIndicesNoPropios(java.lang.Integer, java.lang.Integer)
     */
    public String ejecutarIndicesNoPropios(Integer ano, Integer mes) {
        try{  
            Pevemq_SecNoPropia proc = new Pevemq_SecNoPropia(this.ds);            
            String resultado = proc.execute(ano, mes);
	        return resultado;
        }        
        catch(Exception e){
            return "N";
        }
    }
	
}
