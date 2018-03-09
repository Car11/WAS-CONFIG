package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 
 * <p>TODO <<Interface|Clase>> cr.go.ice.interrupciones.domain.CambioSeccion.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>CambioSeccion.java</code>.</p>
 * <p>Fecha creación: 01/10/2012</p>
 * <p>Última actualización: 01/10/2012</p>
 * @author Vista Verde Tecnología (eperaza)
 * @version 1.0
 */
public class CambioSeccion implements Serializable {
    
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
	private CambioSeccionID id;
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
	
	
	public CambioSeccion(){
	    this.id = new CambioSeccionID();
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
		if (!(obj instanceof CambioSeccion)) {
			resultado = false;
		} else {
		    CambioSeccion seccion = (CambioSeccion) obj;
			resultado = new EqualsBuilder().append(getId(), seccion.getId()).isEquals();
		}
		return resultado;
	}
	/**
     * 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

    /**
     * Método accesor del atributo id.
     * @return Retorna el atributo id.
     */
    public CambioSeccionID getId() {
        return this.id;
    }

    /**
     * Método modificador del atributo id.
     * @param id El dato para modificar el atributo id.
     */
    public void setId(CambioSeccionID id) {
        this.id = id;
    }

}
