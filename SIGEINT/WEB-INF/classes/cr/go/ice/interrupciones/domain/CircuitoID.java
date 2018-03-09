package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * <p>Clase cr.go.ice.interrupciones.domain.CircuitoID.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>CircuitoID.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 16/01/2007</p>
 * <p>Ultima actualización: 16/01/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class CircuitoID implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 2671084880368398363L;
    /**
	 * <code>subEstacion</code> Subestacion del Circuito Id
	 */
	private SubEstacion subEstacion;
	/**
	 * <code>circuito</code> Circuito objeto
	 */
	private Integer circuito;
	
	/**
     * Constructor
     */
    public CircuitoID() {
        this.subEstacion = new SubEstacion();
    }


    /**
	 * Comment for getCircuito
	 * @return Circuito objeto
	 */
	public Integer getCircuito() {
		return circuito;
	}
	
	
	/**
	 * Comment for getSubEstacion
	 * @return ubestacion del Circuito Id
	 */
	public SubEstacion getSubEstacion() {
		return subEstacion;
	}
	
	
	/**
	 * Comment for setCircuito
	 * @param integer
	 */
	public void setCircuito(Integer integer) {
		circuito = integer;
	}
	
	
	/**
	 * Comment for setSubEstacion
	 * @param estacion
	 */
	public void setSubEstacion(SubEstacion estacion) {
		subEstacion = estacion;
	}
	/**
     * 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object objeto) {
		boolean resultado;
		if (!(objeto instanceof CircuitoID)) {
			resultado = false;
		} else {
			CircuitoID circuitoID = (CircuitoID) objeto;
			return new EqualsBuilder().append(getSubEstacion(), circuitoID.getSubEstacion()).append(getCircuito(), circuitoID.getCircuito()).isEquals();
		}
		return resultado;
	}
	
	
	/**
     * 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getSubEstacion()).append(getCircuito()).toHashCode();
	}
}
