package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.TipoVoltajeID.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>TipoVoltajeID.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 16/01/2007</p>
 * <p>Ultima actualización: 16/01/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class TipoVoltajeID implements Serializable {
	
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7105846304030917211L;
    /**
	 * <code>codigoVoltaje</code> Codigo de Voltaje
	 */
	private Integer codigoVoltaje;
	/**
	 * <code>tipoVoltaje</code> Tipo de Voltaje
	 */
	private Integer tipoVoltaje;
	
	
	/**
	 * Comment for getCodigoVoltaje
	 * @return Codigo de Voltaje
	 */
	public Integer getCodigoVoltaje() {
		return codigoVoltaje;
	}


	/**
	 * Comment for getTipoVoltaje
	 * @return Tipo de Voltaje
	 */
	public Integer getTipoVoltaje() {
		return tipoVoltaje;
	}


	/**
	 * Comment for setCodigoVoltaje
	 * @param integer
	 */
	public void setCodigoVoltaje(Integer integer) {
		codigoVoltaje = integer;
	}


	/**
	 * Comment for setTipoVoltaje
	 * @param integer
	 */
	public void setTipoVoltaje(Integer integer) {
		tipoVoltaje = integer;
	}
	
	
	/** 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object objeto) {
		boolean resultado;
		if (!(objeto instanceof TipoVoltajeID)) {
			resultado = false;
		} else {
			TipoVoltajeID tipoVoltajeID = (TipoVoltajeID) objeto;
			return new EqualsBuilder().append(getCodigoVoltaje(), tipoVoltajeID.getCodigoVoltaje()).append(getTipoVoltaje(), tipoVoltajeID.getTipoVoltaje()).isEquals();
		}
		return resultado;
	}


	/** 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getCodigoVoltaje()).append(getTipoVoltaje()).toHashCode();
	}
	
}
