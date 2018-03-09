package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.ReporteID.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ReporteID.java</code>Permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 20/04/2007</p>
 * <p>Ultima actualización: 20/04/2007</p>
 * @author Vista Verde Soft (David)
 * @version 1.1
 */
public class ReporteID implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6109389353611947656L;
    /**
	 * <code>aa</code> AA
	 */
	private Integer aa;
	/**
	 * <code>codigoOficina</code> Codigo de oficina
	 */
	private Integer codigoOficina;
	/**
	 * <code>numeroInterrupcion</code> Numero de Interrupcion
	 */
	private Long numeroReporte;
	
	
	
	/**
	 * Comment for getAa
	 * @return AA
	 */
	public Integer getAa() {
		return aa;
	}
	
	/**
	 * Comment for setAa
	 * @param aa
	 */
	public void setAa(Integer aa) {
		this.aa = aa;
	}
	
	/**
	 * Comment for getCodigoOficina
	 * @return Codigo Oficina
	 */
	public Integer getCodigoOficina() {
		return codigoOficina;
	}
	
	/**
	 * Comment for setCodigoOficina
	 * @param codigoOficina
	 */
	public void setCodigoOficina(Integer codigoOficina) {
		this.codigoOficina = codigoOficina;
	}
	
	/**
	 * Comment for getNumeroReporte
	 * @return Numero de Reporte
	 */
	public Long getNumeroReporte() {
		return numeroReporte;
	}
	
	/**
	 * Comment for setNumeroReporte
	 * @param numeroReporte
	 */
	public void setNumeroReporte(Long numeroReporte) {
		this.numeroReporte = numeroReporte;
	}
	
	
	/**
     * 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		boolean resultado;
		if (!(obj instanceof ReporteID)) {
			resultado = false;
		} else {
			ReporteID reporteID = (ReporteID) obj;
			return new EqualsBuilder().append(getAa(), reporteID.getAa()).append(getCodigoOficina(), 
					reporteID.getCodigoOficina()).append(getNumeroReporte(),reporteID.getNumeroReporte()).isEquals();
		}
		return resultado;
	}
	/**
     * 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getAa()).append(getCodigoOficina()).append(getNumeroReporte()).toHashCode();
	}
}
