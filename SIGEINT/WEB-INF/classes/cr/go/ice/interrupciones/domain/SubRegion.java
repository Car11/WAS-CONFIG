package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.SubRegion.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SubRegion.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 13/12/2006</p>
 * <p>Ultima actualización: 13/12/2006</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class SubRegion implements Serializable {
    
    /**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 424687952770492196L;

    /** atributo <code>ORDEN_CODIGO</code>*/
    public static Integer ORDEN_CODIGO = new Integer(1);
    
    /** atributo <code>ORDEN_NOMBRE</code>*/
    public static Integer ORDEN_NOMBRE = new Integer(2);
    
	/**
	 * <code>subRegionID</code> Identificacion de sub region
	 */
	private SubRegionID subRegionID;
	/**
	 * <code>nombreSubRegion</code> Nombre de sub region
	 */
	private String nombreSubRegion;
	/**
	 * <code>kmsSubRegion</code> KMS de sub region
	 */
	private Double kmsSubRegion;
	/**
	 * <code>abonadoSubregion</code> Sub region del Abonado
	 */
	private Long abonadoSubregion;
	
	/**
	 * Constructor
	 */
	public SubRegion(){}
	
	
	/**
	 * Comment for getAbonadoSubregion
	 * @return Sub region del Abonado
	 */
	public Long getAbonadoSubregion() {
		return abonadoSubregion;
	}

	
	/**
	 * Comment for getKmsSubRegion
	 * @return KMS de Sub Region
	 */
	public Double getKmsSubRegion() {
		return kmsSubRegion;
	}

	
	/**
	 * Comment for getNombreSubRegion
	 * @return NOmbre de sub Region
	 */
	public String getNombreSubRegion() {
		return nombreSubRegion;
	}

	
	/**
	 * Comment for setAbonadoSubregion
	 * @param long1
	 */
	public void setAbonadoSubregion(Long long1) {
		abonadoSubregion = long1;
	}

	
	/**
	 * Comment for setKmsSubRegion
	 * @param double1
	 */
	public void setKmsSubRegion(Double double1) {
		kmsSubRegion = double1;
	}

	
	/**
	 * Comment for setNombreSubRegion
	 * @param string
	 */
	public void setNombreSubRegion(String string) {
		nombreSubRegion = string;
	}
	
	
	/**
	 * Comment for getSubRegionID
	 * @return Identificacion Sub Region
	 */
	public SubRegionID getSubRegionID() {
		return subRegionID;
	}
	
	/**
	 * Comment for setSubRegionID
	 * @param subRegionID
	 */
	public void setSubRegionID(SubRegionID subRegionID) {
		this.subRegionID = subRegionID;
	}
	/** 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object objeto) {
		boolean resultado;
		if (!(objeto instanceof SubRegion)) {
			resultado = false;
		} else {
			SubRegion subRegion = (SubRegion) objeto;
			resultado = new EqualsBuilder().append(getSubRegionID(), subRegion.getSubRegionID()).isEquals();
		}
		return resultado;
	}
	
	
	/** 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {        
		return new HashCodeBuilder().append(getSubRegionID()).toHashCode();
	}
}
