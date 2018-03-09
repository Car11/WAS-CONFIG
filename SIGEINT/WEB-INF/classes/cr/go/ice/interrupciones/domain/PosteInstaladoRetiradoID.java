package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.PosteInstaladoRetiradoID.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>PosteInstaladoRetiradoID.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 26/03/2007</p>
 * <p>Ultima actualización: 26/03/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class PosteInstaladoRetiradoID implements Serializable {
	/**
     * serialVersionUID<code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3440355122269132356L;
    /**
	 * <code>ano</code> Ano
	 */
	private Integer ano;
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
	 * @return Returns the ano.
	 */
	public Integer getAno() {
		return ano;
	}
	/**
	 * @param ano The ano to set.
	 */
	public void setAno(Integer ano) {
		this.ano = ano;
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
     * 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof PosteInstaladoRetiradoID)) {
			return false;
		} else {
			PosteInstaladoRetiradoID posteInstaladoRetiradoID = (PosteInstaladoRetiradoID) obj;
			return new EqualsBuilder().append(getAno(), posteInstaladoRetiradoID.getAno())
				.append(getCodigoOficina(),posteInstaladoRetiradoID.getCodigoOficina())
				.append(getNumeroInterrupcion(), posteInstaladoRetiradoID.getNumeroInterrupcion())
				.append(getNumeroMovimiento(),posteInstaladoRetiradoID.getNumeroMovimiento()).isEquals();
		}
	}
	/**
     * 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getAno()).append(getCodigoOficina()).append(getNumeroInterrupcion()).append(getNumeroMovimiento()).toHashCode();
	}
}
