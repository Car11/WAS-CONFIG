package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.InterrupcionID.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>InterrupcionID.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 20/03/2007</p>
 * <p>Ultima actualización: 20/03/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class InterrupcionID implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5747312756577426129L;
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
	private Long numeroInterrupcion;
	
	
	
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
     * 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		boolean resultado;
		if (!(obj instanceof InterrupcionID)) {
			resultado = false;
		} else {
			InterrupcionID interrupcionID = (InterrupcionID) obj;
			return new EqualsBuilder().append(getAa(), interrupcionID.getAa()).append(getCodigoOficina(), 
					interrupcionID.getCodigoOficina()).append(getNumeroInterrupcion(),interrupcionID.getNumeroInterrupcion()).isEquals();
		}
		return resultado;
	}
	/**
     * 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getAa()).append(getCodigoOficina()).append(getNumeroInterrupcion()).toHashCode();
	}
}
