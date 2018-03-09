
package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.Trafo.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Trafo.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 16/01/2007</p>
 * <p>Ultima actualización: 16/01/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class Trafo implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 2453448126692510162L;
    /**
	 * <code>trafoID</code> Identificacion del Trato
	 */
	private TrafoID trafoID;
	/**
	 * <code>tipoMovimiento</code> Tipo de Movimiento
	 */
	private Integer tipoMovimiento;
	/**
	 * <code>numeroSerie</code> Numero de serie
	 */
	private Long numeroSerie;
	/**
	 * <code>serie</code> Serie
	 */
	private String serie;	
	/**
	 * <code>movimiento</code> Movimiento
	 */
	private String movimiento;
	
	
	/**
	 * Constructor
	 */
	public Trafo(){		
	}
	/**
	 * @return Returns the numeroSerie.
	 */
	public Long getNumeroSerie() {
		return numeroSerie;
	}
	/**
	 * @param numeroSerie The numeroSerie to set.
	 */
	public void setNumeroSerie(Long numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	/**
	 * @return Returns the serie.
	 */
	public String getSerie() {
		return serie;
	}
	/**
	 * @param serie The serie to set.
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}
	/**
	 * @return Returns the tipoMovimiento.
	 */
	public Integer getTipoMovimiento() {
		return tipoMovimiento;
	}
	/**
	 * @param tipoMovimiento The tipoMovimiento to set.
	 */
	public void setTipoMovimiento(Integer tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
		if(tipoMovimiento.intValue() == 0)
			this.movimiento = "RETIRADO";
		else
			this.movimiento = "INSTALADO";
	}
	/**
	 * @return Returns the trafoID.
	 */
	public TrafoID getTrafoID() {
		return trafoID;
	}
	/**
	 * @param trafoID The trafoID to set.
	 */
	public void setTrafoID(TrafoID trafoID) {
		this.trafoID = trafoID;
	}
	
	
	/** 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		boolean resultado;
		if (!(obj instanceof Trafo)) {
			resultado = false;
		} else {
			Trafo trafo = (Trafo) obj;
			resultado = new EqualsBuilder().append(getTrafoID(), trafo.getTrafoID()).isEquals();
		}
		return resultado;
	}
	/** 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getTrafoID()).toHashCode();
	}
	/**
	 * @return Devuelve movimiento.
	 */
	public String getMovimiento() {
		return movimiento;
	}
	/**
	 * @param movimiento El movimiento a establecer.
	 */
	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}
}
