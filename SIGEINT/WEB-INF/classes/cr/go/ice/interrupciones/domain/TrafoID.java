package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.TrafoID.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>TrafoID.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 23/03/2007</p>
 * <p>Ultima actualización: 23/03/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class TrafoID implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8573519419099872871L;
    /**
	 * <code>aa</code> AA
	 */
	private Integer aa;
	/**
	 * <code>codigoOficina</code> Codigo de Oficina
	 */
	private Integer codigoOficina;
	/**
	 * <code>numeroInterrupcion</code> Numero de Interrupcion
	 */
	private Long numeroInterrupcion;
	/**
	 * <code>numeroMovimiento</code> Numero de Movimiento
	 */
	private Integer numeroMovimiento;
	
	/**
	 * @return Returns the aa.
	 */
	public Integer getAa() {
		return aa;
	}
	/**
	 * @param aa The aa to set.
	 */
	public void setAa(Integer aa) {
		this.aa = aa;
	}
	/**
	 * @return Returns the codigoOficina.
	 */
	public Integer getCodigoOficina() {
		return codigoOficina;
	}
	/**
	 * @param codigoOficina The codigoOficina to set.
	 */
	public void setCodigoOficina(Integer codigoOficina) {
		this.codigoOficina = codigoOficina;
	}
	/**
	 * @return Returns the numeroInterrupcion.
	 */
	public Long getNumeroInterrupcion() {
		return numeroInterrupcion;
	}
	/**
	 * @param numeroInterrupcion The numeroInterrupcion to set.
	 */
	public void setNumeroInterrupcion(Long numeroInterrupcion) {
		this.numeroInterrupcion = numeroInterrupcion;
	}
	/**
	 * @return Returns the numeroMovimiento.
	 */
	public Integer getNumeroMovimiento() {
		return numeroMovimiento;
	}
	/**
	 * @param numeroMovimiento The numeroMovimiento to set.
	 */
	public void setNumeroMovimiento(Integer numeroMovimiento) {
		this.numeroMovimiento = numeroMovimiento;
	}
	
	
	/** 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		boolean resultado;
		if (!(obj instanceof TrafoID)) {
			resultado = false;
		} else {
			TrafoID trafoID = (TrafoID) obj;
			return new EqualsBuilder().append(getAa(), trafoID.getAa())
			.append(getCodigoOficina(), trafoID.getCodigoOficina())
			.append(getNumeroMovimiento(), trafoID.getNumeroMovimiento()).
			append(getNumeroInterrupcion(), trafoID.getNumeroInterrupcion()).isEquals();
		}
		return resultado;
	}
    
	/** 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getAa()).append(getCodigoOficina()).append(getNumeroMovimiento()).append(getNumeroInterrupcion()).toHashCode();
	}
}
