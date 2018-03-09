package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.Region.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Region.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 13/12/2006</p>
 * <p>Ultima actualización: 13/12/2006</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class Region implements Serializable {
    
    /**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -827773235299414406L;

    /** atributo <code>ORDEN_CODIGO</code>*/
    public static Integer ORDEN_CODIGO = new Integer(1);
    
    /** atributo <code>ORDEN_NOMBRE</code>*/
    public static Integer ORDEN_NOMBRE = new Integer(2);
	/**
	 * <code>region</code> Region
	 */
	private Integer region;
	/**
	 * <code>nombreRegion</code> Nombre de la Region
	 */
	private String nombreRegion;
	/**
	 * <code>kmsRegion</code> KMS REgion
	 */
	private Double kmsRegion;
	/**
	 * <code>abonadoRegion</code> Region Abonado 
	 */
	private Long abonadoRegion;
	/**
	 * <code>demanda</code> Demanda
	 */
	private Long demanda;
	/**
	 * <code>energia</code> Energia
	 */
	private Long energia;
	
	/**
	 * Constructor
	 */
	public Region(){}
	
	
	
	/**
	 * Comment for getAbonadoRegion
	 * @return Abonado Region
	 */
	public Long getAbonadoRegion() {
		return abonadoRegion;
	}

	
	/**
	 * Comment for getDemanda
	 * @return Demanda
	 */
	public Long getDemanda() {
		return demanda;
	}

	
	/**
	 * Comment for getEnergia
	 * @return Energia
	 */
	public Long getEnergia() {
		return energia;
	}

	
	/**
	 * Comment for getKmsRegion
	 * @return KMS Region
	 */
	public Double getKmsRegion() {
		return kmsRegion;
	}

	
	/**
	 * Comment for getNombreRegion
	 * @return Nombre de la REgion
	 */
	public String getNombreRegion() {
		return nombreRegion;
	}

	/**
	 * Comment for getRegion
	 * @return Region
	 */
	public Integer getRegion() {
		return region;
	}

	
	/**
	 * Comment for setAbonadoRegion
	 * @param long1
	 */
	public void setAbonadoRegion(Long long1) {
		abonadoRegion = long1;
	}

	
	/**
	 * Comment for setDemanda
	 * @param long1
	 */
	public void setDemanda(Long long1) {
		demanda = long1;
	}

	
	/**
	 * Comment for setEnergia
	 * @param long1
	 */
	public void setEnergia(Long long1) {
		energia = long1;
	}

	
	/**
	 * Comment for setKmsRegion
	 * @param double1
	 */
	public void setKmsRegion(Double double1) {
		kmsRegion = double1;
	}

	
	/**
	 * Comment for setNombreRegion
	 * @param string
	 */
	public void setNombreRegion(String string) {
		nombreRegion = string;
	}


	/**
	 * Comment for setRegion
	 * @param integer
	 */
	public void setRegion(Integer integer) {
		region = integer;
	}
	
	

	/**
     * 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
        if(obj instanceof Region){
        	Region region = (Region) obj;
            if(region.getRegion().intValue() == this.region.intValue()){
                return true;
            } else{
                return false;
            }
        } else{
            return false;
        }
	}
    
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {        
        return new HashCodeBuilder().append(getRegion()).toHashCode();
    }    
    
}
