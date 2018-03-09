package cr.go.ice.interrupciones.BO.impl;

import java.util.Date;
import java.util.List;

import cr.go.ice.interrupciones.BO.ReporteBO;
import cr.go.ice.interrupciones.DAO.ReporteDAO;
import cr.go.ice.interrupciones.domain.Reporte;
import cr.go.ice.interrupciones.domain.ReporteID;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.ReporteBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ReporteBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para Reporte.</p>
 * <p>Fecha creación: 20/04/2007</p>
 * <p>Ultima actualización: 20/04/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ReporteBOImpl implements ReporteBO{
	
    /**
     * <code>reporteDAO</code> Reporte Dao
     */
    private ReporteDAO reporteDAO;
    
    /**
     * Constructor
     */
    public ReporteBOImpl(){
    }

	/**
	 * Asigna reporteDAO.
	 * @param reporteDAO El reporteDAO a modificar.
	 */
	public void setReporteDAO(ReporteDAO reporteDAO) {
		this.reporteDAO = reporteDAO;
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.ReporteBO#agregar(cr.go.ice.interrupciones.domain.Reporte)
	 */
	public void agregar(Reporte reporte) {
		this.reporteDAO.agregar(reporte);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.ReporteBO#modificar(cr.go.ice.interrupciones.domain.Reporte)
	 */
	public void modificar(Reporte reporte) {
		this.reporteDAO.modificar(reporte);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.ReporteBO#eliminar(cr.go.ice.interrupciones.domain.Reporte)
	 */
	public void eliminar(Reporte reporte) {
		this.reporteDAO.eliminar(reporte);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.ReporteBO#getReporte(cr.go.ice.interrupciones.domain.ReporteID)
	 */
	public Reporte getReporte(ReporteID reporteID) {
		return this.reporteDAO.getReporte(reporteID);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.ReporteBO#getReportes()
	 */
	public List getReportes() {
		return this.reporteDAO.getReportes();
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.ReporteBO#reporteExiste(cr.go.ice.interrupciones.domain.ReporteID)
	 */
	public boolean reporteExiste(ReporteID reporteID) {
		return this.reporteDAO.reporteExiste(reporteID);
	}

    /**
     * @see cr.go.ice.interrupciones.BO.ReporteBO#getAnosDeReportes()
     */
    public List getAnosDeReportes() {
        return this.reporteDAO.getAnosDeReportes();
    }

    /**
     * @see cr.go.ice.interrupciones.BO.ReporteBO#getInterrupcionesPorCircuitoMenor5Min(java.lang.Integer, java.util.Date, java.util.Date, java.lang.String, java.lang.Boolean, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    public Long getInterrupcionesPorCircuitoMenor5Min(Integer codigoOficina, Date fechaInicio, Date fechaFinal, String voltajes, Boolean causa1, Integer bitacora, Integer codigoCircuito, Integer subEstacion, Boolean causa411) {
        return this.reporteDAO.getInterrupcionesPorCircuitoMenor5Min(codigoOficina, fechaInicio, fechaFinal, voltajes, causa1, bitacora, codigoCircuito, subEstacion, causa411);
    }
    
    public boolean existeReportes(Integer reg, Integer subR, Integer sub, Integer cir, Long sec){
        return this.reporteDAO.existeReportes(reg, subR, sub, cir, sec);
    }

}
