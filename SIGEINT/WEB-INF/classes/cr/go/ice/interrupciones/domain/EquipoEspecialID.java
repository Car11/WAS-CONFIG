package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.EquipoEspecialID.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>EquipoEspecialID.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 26/03/2007</p>
 * <p>Ultima actualización: 26/03/2007/p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class EquipoEspecialID implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 100753810084485847L;
    /**
	 * <code>ano</code> Ano
	 */
	private Integer ano;
	/**
	 * <code>codigoOficina</code> Codigo de la Oficina
	 */
	private Integer codigoOficina;
	/**
	 * <code>numeroInterrupcion</code> Numero de la Interrupcion
	 */
	private Long numeroInterrupcion;
	/**
	 * <code>numeroICE</code> Numero del ICE
	 */
	private Long numeroICE;
	
	
	
	/**
	 * Comment for getAno
	 * @return Ano
	 */
	public Integer getAno() {
		return ano;
	}
	
	/**
	 * Comment for setAno
	 * @param ano
	 */
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	
	/**
	 * Comment for getCodigoOficina
	 * @return Codigo de la Oficina
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
	 * Comment for getNumeroICE
	 * @return Numero del ICE
	 */
	public Long getNumeroICE() {
		return numeroICE;
	}
	
	/**
	 * Comment for setNumeroICE
	 * @param numeroICE
	 */
	public void setNumeroICE(Long numeroICE) {
		this.numeroICE = numeroICE;
	}
	
	/**
	 * Comment for getNumeroInterrupcion
	 * @return Numero de la Interrupcion
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
     * 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof EquipoEspecialID)) {
			return false;
		} else {
			EquipoEspecialID equipoEspecialID = (EquipoEspecialID) obj;
			return new EqualsBuilder().append(getAno(), equipoEspecialID.getAno())
				.append(getCodigoOficina(), equipoEspecialID.getCodigoOficina())
				.append(getNumeroInterrupcion(),equipoEspecialID.getNumeroInterrupcion())
				.append(getNumeroICE(),equipoEspecialID.getNumeroICE()).isEquals();
		}
	}
	/**
     * 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getAno()).append(getCodigoOficina())
		.append(getNumeroInterrupcion()).append(getNumeroICE()).toHashCode();
	}
}
