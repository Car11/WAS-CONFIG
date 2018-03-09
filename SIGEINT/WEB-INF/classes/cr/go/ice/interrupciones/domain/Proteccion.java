package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * <p>Clase cr.go.ice.interrupciones.domain.Proteccion.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Proteccion.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 13/12/2006</p>
 * <p>Ultima actualización: 13/12/2006</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class Proteccion implements Serializable{
	
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -6722574485730955886L;
    /**
	 * <code>codigoProteccion</code> Codigo de Proteccion
	 */
	private Integer codigoProteccion;
	/**
	 * <code>nombreProteccion</code> Nombre de Proteccion
	 */
	private String nombreProteccion;	
	/**
	 * <code>activo</code> Activo
	 */
	private Boolean activo;
	
	/**
	 * Constructor
	 */
	public Proteccion(){}
	
	
	
	/**
	 * Comment for getCodigoProteccion
	 * @return Codigo de Proteccion
	 */
	public Integer getCodigoProteccion() {
		return codigoProteccion;
	}

	
	/**
	 * Comment for getNombreProteccion
	 * @return Nombre de Proteccion
	 */
	public String getNombreProteccion() {
		return nombreProteccion;
	}

	
	/**
	 * Comment for setCodigoProteccion
	 * @param integer
	 */
	public void setCodigoProteccion(Integer integer) {
		codigoProteccion = integer;
	}

	
	/**
	 * Comment for setNombreProteccion
	 * @param string
	 */
	public void setNombreProteccion(String string) {
		nombreProteccion = string;
	}	
	
	/**
	 * Comment for getActivo
	 * @return Activo
	 */
	public Boolean getActivo() {
		return activo;
	}
	
	/**
	 * Comment for setActivo
	 * @param activo
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Proteccion)) {
			return false;
		} else {
			Proteccion proteccion = (Proteccion) obj;
			return new EqualsBuilder().append(this.getCodigoProteccion(), proteccion.getCodigoProteccion())			
			.isEquals();
		}
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(this.getCodigoProteccion()).toHashCode();
	}	
	
}
