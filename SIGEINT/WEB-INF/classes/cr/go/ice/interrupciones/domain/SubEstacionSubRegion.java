package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.SubEstacionSubRegion.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SubEstacionSubRegion.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 24/02/2007</p>
 * <p>Ultima actualización: 24/02/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class SubEstacionSubRegion implements Serializable{
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -1544437429225883645L;
    private SubEstacionSubRegionID subEstacionSubRegionID;
	
    /**
     * Constructor por defecto
     */
    public SubEstacionSubRegion(){
        
    }
    
    /**
     * Constructor sobrecargado
     * @param oficina Oficina deseada
     * @param region Region deseada
     * @param subregion Subregion deseada
     * @param subestacion Subestacion deseada
     */
    public SubEstacionSubRegion(Integer oficina, Integer region, Integer subregion, Integer subestacion){
        this.subEstacionSubRegionID = new SubEstacionSubRegionID();
        Oficina oficinaObj = new Oficina();
        oficinaObj.setCodigoOficina(oficina);
        this.subEstacionSubRegionID.setOficina(oficinaObj);
        SubRegion s = new SubRegion();
        SubRegionID sId = new SubRegionID();
        Region reg = new Region();
        reg.setRegion(region);
        sId.setRegion(reg);
        sId.setSubRegion(subregion);
        s.setSubRegionID(sId);
        this.subEstacionSubRegionID.setSubRegion(s);
        this.subEstacionSubRegionID.setSubEstacion(subestacion);
        
    }
	/**
	 * @return Returns the subEstacionSubRegionID.
	 */
	public SubEstacionSubRegionID getSubEstacionSubRegionID() {
		return subEstacionSubRegionID;
	}
	/**
	 * @param subEstacionSubRegionID The subEstacionSubRegionID to set.
	 */
	public void setSubEstacionSubRegionID(
			SubEstacionSubRegionID subEstacionSubRegionID) {
		this.subEstacionSubRegionID = subEstacionSubRegionID;
	}
	
	
	/** 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		boolean resultado;
		if (!(obj instanceof SubEstacionSubRegion)) {
			resultado = false;
		} else {
			SubEstacionSubRegion subEstacionSubRegion = (SubEstacionSubRegion) obj;
			resultado = new EqualsBuilder().append(getSubEstacionSubRegionID(), subEstacionSubRegion.getSubEstacionSubRegionID()).isEquals();
		}
		return resultado;
	}
	/** 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getSubEstacionSubRegionID()).toHashCode();
	}
}
