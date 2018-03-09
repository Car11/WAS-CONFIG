
package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * <p>Clase cr.go.ice.interrupciones.domain.ConsecutivoClorID.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ConsecutivoClorID.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 26/03/2007</p>
 * <p>Ultima actualización: 26/03/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class ConsecutivoClorID implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4980451911179322052L;
    /**
	 * <code>ano</code> Ano
	 */
	private Integer ano;
	/**
	 * <code>codigoOficina</code> Codigo de Oficina
	 */
	private Integer codigoOficina;
	
	
	
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
	 * @return Codigo de oficina
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
     * 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof ConsecutivoClorID)) {
			return false;
		} else {
			ConsecutivoClorID consecutivoClorID = (ConsecutivoClorID) obj;
			return new EqualsBuilder().append(getAno(), consecutivoClorID.getAno()).isEquals();
		}
	}
	/**
     * 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getAno()).append(getCodigoOficina()).toHashCode();
	}
}
