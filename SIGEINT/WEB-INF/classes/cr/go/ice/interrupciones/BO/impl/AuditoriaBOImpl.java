package cr.go.ice.interrupciones.BO.impl;

import java.util.Date;


import cr.go.ice.interrupciones.BO.AuditoriaBO;
import cr.go.ice.interrupciones.DAO.AuditoriaDAO;


/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.AuditoriaBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>AuditoriaBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para Auditoria.</p>
 * <p>Fecha creación: 25/09/2012</p>
 * <p>Ultima actualización: 25/09/2012</p>
 * @author Grettel
 * @version 1.1
 */
public class AuditoriaBOImpl implements AuditoriaBO{

    /**
     * <code>noPropiaSeccionamientoDAO</code> noPropiaSeccionamiento Dao
     */
    private AuditoriaDAO auditoriaDAO;
    
    /**
     * Constructor.
     */
    public AuditoriaBOImpl(){
        
    }

	/**
	 * Asigna noPropiaSeccionamientoDAO.
	 * @param noPropiaSeccionamientoDAO El noPropiaSeccionamientoDAO a modificar.
	 */
	public void setAuditoriaDAO(
			AuditoriaDAO auditoriaDAO) {
		this.auditoriaDAO = auditoriaDAO;
	}


	
	public Long getAuditorias(Integer codigoOficina, Date fechaInicio, Date fechaFinal,String nivelRed, Integer region, Integer subRegion, Integer subEstacion, Integer circuito, Long seccion) {
	        Long auditoias = this.auditoriaDAO.getAuditorias(codigoOficina, fechaInicio, fechaFinal,  nivelRed, region,  subRegion,  subEstacion,  circuito,  seccion);
			return auditoias;
	}
	  
	
}
