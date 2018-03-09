package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.InterrupcionGemelaID.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>InterrupcionGemelaID.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 26/03/2007</p>
 * <p>Ultima actualización: 26/03/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class InterrupcionGemelaID implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 9209268485730154004L;
    /**
	 * <code>interrupcion</code> Interrucion
	 */
	private Interrupcion interrupcion;
	/**
	 * <code>numeroMovimiento</code> Numero movimiento
	 */
	private Integer numeroMovimiento;
	
	
	
	/**
	 * Comment for getInterrupcion
	 * @return Interrupcion
	 */
	public Interrupcion getInterrupcion() {
		return interrupcion;
	}
	
	/**
	 * Comment for setInterrupcion
	 * @param interrupcion
	 */
	public void setInterrupcion(Interrupcion interrupcion) {
		this.interrupcion = interrupcion;
	}
	
	/**
	 * Comment for getNumeroMovimiento
	 * @return Numero Movimiento
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

		if (!(obj instanceof InterrupcionGemelaID)) {
			return false;
		} else {
			InterrupcionGemelaID interrupcionGemelaID = (InterrupcionGemelaID) obj;
			return new EqualsBuilder().append(getInterrupcion(), interrupcionGemelaID.getInterrupcion())
				.append(getNumeroMovimiento(),interrupcionGemelaID.getNumeroMovimiento()).isEquals();
		}
	}
	/**
     * 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getInterrupcion()).append(getNumeroMovimiento()).toHashCode();
	}
}
