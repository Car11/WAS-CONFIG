package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.SubRegionID.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SubRegionID.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 16/01/2007</p>
 * <p>Ultima actualización: 16/01/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class SubRegionID implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -1969938365360623769L;
    /**
	 * <code>subRegion</code> Sub Region
	 */
	private Integer subRegion;
	/**
	 * <code>region</code> Region
	 */
	private Region region;

	
	/**
	 * Comment for getRegion
	 * @return Region
	 */
	public Region getRegion() {
		return region;
	}

	
	/**
	 * Comment for getSubRegion
	 * @return Sub Region
	 */
	public Integer getSubRegion() {
		return subRegion;
	}

	
	/**
	 * Comment for setRegion
	 * @param region
	 */
	public void setRegion(Region region) {
		this.region = region;
	}

	
	/**
	 * Comment for setSubRegion
	 * @param integer
	 */
	public void setSubRegion(Integer integer) {
		subRegion = integer;
	}
	
	/**
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object objeto) {
		boolean resultado;
		if (!(objeto instanceof SubRegionID)) {
			resultado = false;
		} else {
			SubRegionID subRegionID = (SubRegionID) objeto;
			return new EqualsBuilder().append(getSubRegion(), subRegionID.getSubRegion()).append(getRegion(),subRegionID.getRegion()).isEquals();
		}
		return resultado;
	}
	
	
	/** 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getSubRegion()).append(getRegion()).toHashCode();
	}
}
