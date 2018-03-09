package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.FusibleID.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>FusibleID.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 26/03/2007</p>
 * <p>Ultima actualización: 26/03/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class FusibleID implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5527861407827007369L;
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
	 * @return Numero de Interrupcion
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
	 * Comment for getNumeroMovimiento
	 * @return Numero de Movimiento
	 */
	public Integer getNumeroMovimiento() {
		return numeroMovimiento;
	}
	
	/**
	 * Comment for setNumeroMovimiento
	 * @param numeroMovimiento
	 */
	public void setNumeroMovimiento(Integer numeroMovimiento) {
		this.numeroMovimiento = numeroMovimiento;
	}
	
	
	/**
     * 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof FusibleID)) {
			return false;
		} else {
			FusibleID fusibleID = (FusibleID) obj;
			return new EqualsBuilder().append(getAa(), fusibleID.getAa())
				.append(getCodigoOficina(), fusibleID.getCodigoOficina())
				.append(getNumeroInterrupcion(),fusibleID.getNumeroInterrupcion())
				.append(getNumeroMovimiento(),fusibleID.getNumeroMovimiento()).isEquals();
		}
	}
	/**
     * 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getAa()).append(getCodigoOficina())
		.append(getNumeroInterrupcion()).append(getNumeroMovimiento()).toHashCode();
	}
}
