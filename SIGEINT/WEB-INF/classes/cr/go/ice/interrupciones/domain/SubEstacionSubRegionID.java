
package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * <p>Clase cr.go.ice.interrupciones.domain.SubEstacionSubRegionID.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SubEstacionSubRegionID.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 24/02/2007</p>
 * <p>Ultima actualización: 24/02/2007</p>
 * @author Vista Verde Soft (Nano)
 * @version 1.1
 */
public class SubEstacionSubRegionID implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 952753699895871657L;
    private Oficina oficina;
	private SubRegion subRegion;
	private Integer subEstacion;
	
	
	/**
	 * @return Returns the oficina.
	 */
	public Oficina getOficina() {
		return oficina;
	}
	/**
	 * @param oficina The oficina to set.
	 */
	public void setOficina(Oficina oficina) {
		this.oficina = oficina;
	}
	/**
	 * @return Returns the subEstacion.
	 */
	public Integer getSubEstacion() {
		return subEstacion;
	}
	/**
	 * @param subEstacion The subEstacion to set.
	 */
	public void setSubEstacion(Integer subEstacion) {
		this.subEstacion = subEstacion;
	}
	/**
	 * @return Returns the subRegion.
	 */
	public SubRegion getSubRegion() {
		return subRegion;
	}
	/**
	 * @param subRegion The subRegion to set.
	 */
	public void setSubRegion(SubRegion subRegion) {
		this.subRegion = subRegion;
	}
	
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		boolean resultado;
		if (!(obj instanceof SubEstacionSubRegionID)) {
			resultado = false;
		} else {
			SubEstacionSubRegionID subEstacionSubRegionID = (SubEstacionSubRegionID) obj;
			return new EqualsBuilder().append(getOficina(),subEstacionSubRegionID.getOficina()).append(getSubRegion(), 
					subEstacionSubRegionID.getSubRegion()).append(getSubEstacion(),subEstacionSubRegionID.getSubEstacion()).isEquals();
		}
		return resultado;
	}
	
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getOficina()).append(getSubRegion()).append(getSubEstacion()).toHashCode();
	}
}