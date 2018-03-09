package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.EquipoEspecial.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>EquipoEspecial.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 26/03/2007</p>
 * <p>Ultima actualización: 26/03/2007/p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class EquipoEspecial implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 7145366540317577903L;
    /**
	 * <code>equipoEspecialID</code> Equipo Especial Identificacion
	 */
	private EquipoEspecialID equipoEspecialID;
	/**
	 * <code>serie</code> Serie Equipo Especial
	 */
	private String serie;
	/**
	 * <code>movimiento</code> movimiento
	 */
	private String movimiento;	
	
	private Long numeroICE;
	 
	/**
	 * Comment for getEquipoEspecialID
	 * @return Equipo Especial Identificacion
	 */
	public EquipoEspecialID getEquipoEspecialID() {
		return equipoEspecialID;
	}
	
	/**
	 * Comment for setEquipoEspecialID
	 * @param equipoEspecialID
	 */
	public void setEquipoEspecialID(EquipoEspecialID equipoEspecialID) {
		this.equipoEspecialID = equipoEspecialID;
	}
	
	/**
	 * Comment for getSerie
	 * @return Serie Equipo Especial
	 */
	public String getSerie() {
		return serie;
	}
	
	/**
	 * Comment for setSerie
	 * @param serie
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}
	
	
	/**
     * 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof EquipoEspecial)) {
			return false;
		} else {
			EquipoEspecial equipoEspecial = (EquipoEspecial) obj;
			return new EqualsBuilder().append(getEquipoEspecialID(), equipoEspecial.getEquipoEspecialID()).isEquals();
		}
	}
	/**
     * 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getEquipoEspecialID()).toHashCode();
	}
	/**
	 * Metodo accesor de movimiento.
	 * @return Retorna el movimiento.
	 */
	public String getMovimiento() {
		return movimiento;
	}
	/**
	 * Metodo modificador de movimiento.
	 * @param movimiento El movimiento a modificar.
	 */
	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}
    /**
     * Metodo accesor de numeroICE.
     * @return Retorna el numeroICE.
     */
    public Long getNumeroICE() {
        return numeroICE;
    }
    /**
     * Metodo modificador de numeroICE.
     * @param numeroICE El numeroICE a modificar.
     */
    public void setNumeroICE(Long numeroICE) {
        this.numeroICE = numeroICE;
    }
}
