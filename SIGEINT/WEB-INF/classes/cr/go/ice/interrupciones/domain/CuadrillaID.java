package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * <p>Clase cr.go.ice.interrupciones.domain.CuadrillaID.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>CuadrillaID.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 26/03/2007</p>
 * <p>Ultima actualización: 26/03/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class CuadrillaID implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4686790464438966404L;
    /**
	 * <code>aa</code> AA
	 */
	private Integer aa;
	/**
	 * <code>codigoOficina</code> Codigo de Oficina
	 */
	private Integer codigoOficina;
	/**
	 * <code>numeroInterrupcion</code> Numero Interrupcion
	 */
	private Long numeroInterrupcion;
	/**
	 * <code>cedula</code> Numero de Cedula
	 */
	private Long cedula;
	/**
	 * <code>reporteInterrupcion</code> Reporte de Interrupcion
	 */
	private Integer reporteInterrupcion;
	
	
	
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
	 * Comment for getCedula
	 * @return Numero de Cedula
	 */
	public Long getCedula() {
		return cedula;
	}
	
	/**
	 * Comment for setCedula
	 * @param cedula
	 */
	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}
	
	/**
	 * Comment for getCodigoOficina
	 * @return Codigo de Oficina
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
	 * Comment for getNumeroInterrupcion
	 * @return Numero Interrupcion
	 */
	public Long getNumeroInterrupcion() {
		return numeroInterrupcion;
	}
	
	/**
	 * Comment for setNumeroInterrupcion
	 * @param numeroInterrupcion
	 */
	public void setNumeroInterrupcion(Long numeroInterrupcion) {
		this.numeroInterrupcion = numeroInterrupcion;
	}
	
	/**
	 * Comment for getReporteInterrupcion
	 * @return Reporte de Interrupcion
	 */
	public Integer getReporteInterrupcion() {
		return reporteInterrupcion;
	}
	
	/**
	 * Comment for setReporteInterrupcion
	 * @param reporteInterrupcion
	 */
	public void setReporteInterrupcion(Integer reporteInterrupcion) {
		this.reporteInterrupcion = reporteInterrupcion;
	}
	
	
	/**
     * 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		boolean resultado;
		if (!(obj instanceof CuadrillaID)) {
			resultado = false;
		} else {
			CuadrillaID cuadrillaID = (CuadrillaID) obj;
			return new EqualsBuilder().append(getAa(), cuadrillaID.getAa())
				.append(getCodigoOficina(), cuadrillaID.getCodigoOficina())
				.append(getNumeroInterrupcion(),cuadrillaID.getNumeroInterrupcion())
				.append(getCedula(), cuadrillaID.getCedula())
				.append(getReporteInterrupcion(), cuadrillaID.getReporteInterrupcion()).isEquals();
		}
		return resultado;
	}
	/**
     * 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getAa()).append(getCodigoOficina()).append(getNumeroInterrupcion())
		.append(getCedula()).append(getReporteInterrupcion()).toHashCode();
	}
}
