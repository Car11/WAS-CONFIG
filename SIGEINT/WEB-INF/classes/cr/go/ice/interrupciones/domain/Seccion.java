package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.Seccion.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Seccion.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 23/02/2007</p>
 * <p>Ultima actualización: 23/02/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class Seccion implements Serializable {
    
    /**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4889880937787427470L;

    /** atributo <code>ORDEN_CODIGO</code>*/
    public static final Integer ORDEN_CODIGO = new Integer(1);
    
    /** atributo <code>ORDEN_NOMBRE</code>*/
    public static final Integer ORDEN_NOMBRE = new Integer(2);
    /**
     * <code>ESTADO_CONGELADO</code> Congelado
     */
    public static final Integer ESTADO_CONGELADO = new Integer(1);
    /**
     * <code>ESTADO_DESCONGELADO</code> Descongelado
     */
    public static final Integer ESTADO_DESCONGELADO = new Integer(0);
    
	/**
	 * <code>seccionID</code> Seccion identificacion
	 */
	private SeccionID seccionID;
	/**
	 * <code>region</code> Region
	 */
	private Integer region;
	/**
	 * <code>subRegion</code> SubRegion
	 */
	private Integer subRegion;
	/**
	 * <code>codigoOficina</code> Codigo de Oficina
	 */
	private Integer codigoOficina;
	/**
	 * <code>nombreSeccion</code> Nombre de la Seccion
	 */
	private String nombreSeccion;
	/**
	 * <code>kmsSeccion</code> KMS Seccion
	 */
	private Double kmsSeccion;
	/**
	 * <code>abonadoSeccion</code> Abonado Seccion
	 */
	private Long abonadoSeccion;
	/**
	 * <code>congelado</code> Congelado
	 */
	private Integer congelado;
	
	public Seccion(){
	    this.seccionID = new SeccionID();
	}

	/**
	 * @return Returns the abonadoSeccion.
	 */
	public Long getAbonadoSeccion() {
		return abonadoSeccion;
	}
	/**
	 * @param abonadoSeccion The abonadoSeccion to set.
	 */
	public void setAbonadoSeccion(Long abonadoSeccion) {
		this.abonadoSeccion = abonadoSeccion;
	}
	/**
	 * @return Returns the codigoOficina.
	 */
	public Integer getCodigoOficina() {
		return codigoOficina;
	}
	/**
	 * @param codigoOficina The codigoOficina to set.
	 */
	public void setCodigoOficina(Integer codigoOficina) {
		this.codigoOficina = codigoOficina;
	}
	/**
	 * @return Returns the congelado.
	 */
	public Integer getCongelado() {
		return congelado;
	}
	/**
	 * @param congelado The congelado to set.
	 */
	public void setCongelado(Integer congelado) {
		this.congelado = congelado;
	}
	/**
	 * @return Returns the kmsSeccion.
	 */
	public Double getKmsSeccion() {
		return kmsSeccion;
	}
	/**
	 * @param kmsSeccion The kmsSeccion to set.
	 */
	public void setKmsSeccion(Double kmsSeccion) {
		this.kmsSeccion = kmsSeccion;
	}
	/**
	 * @return Returns the nombreSeccion.
	 */
	public String getNombreSeccion() {
		return nombreSeccion;
	}
	/**
	 * @param nombreSeccion The nombreSeccion to set.
	 */
	public void setNombreSeccion(String nombreSeccion) {
		this.nombreSeccion = nombreSeccion;
	}
	/**
	 * @return Returns the region.
	 */
	public Integer getRegion() {
		return region;
	}
	/**
	 * @param region The region to set.
	 */
	public void setRegion(Integer region) {
		this.region = region;
	}
	/**
	 * @return Returns the seccionID.
	 */
	public SeccionID getSeccionID() {
		return seccionID;
	}
	/**
	 * @param seccionID The seccionID to set.
	 */
	public void setSeccionID(SeccionID seccionID) {
		this.seccionID = seccionID;
	}
	/**
	 * @return Returns the subRegion.
	 */
	public Integer getSubRegion() {
		return subRegion;
	}
	/**
	 * @param subRegion The subRegion to set.
	 */
	public void setSubRegion(Integer subRegion) {
		this.subRegion = subRegion;
	}
	
	
	/**
     * 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		boolean resultado;
		if (!(obj instanceof Seccion)) {
			resultado = false;
		} else {
			Seccion seccion = (Seccion) obj;
			resultado = new EqualsBuilder().append(getSeccionID(), seccion.getSeccionID()).isEquals();
		}
		return resultado;
	}
	/**
     * 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getSeccionID()).toHashCode();
	}
}
