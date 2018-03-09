package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.ProvolmaID.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ProvolmaID.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 16/01/2007</p>
 * <p>Ultima actualización: 16/01/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class ProvolmaID implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6627904519305105780L;
    /**
	 * <code>codigoVoltaje</code> Codigo de Voltaje
	 */
	private Integer codigoVoltaje;
	/**
	 * <code>material</code> Codigo de Material
	 */
	private Material material; 
	/**
	 * <code>dano</code> Dano
	 */
	private Dano dano;
	/**
	 * <code>proteccion</code> Proteccion
	 */
	private Proteccion proteccion;
	
	/**
	 * Constructor
	 */
	public ProvolmaID(){}
	
	
	/**
	 * Comment for getCodigoVoltaje
	 * @return Codigo de Voltaje
	 */
	public Integer getCodigoVoltaje() {
		return codigoVoltaje;
	}

	
	/**
	 * Comment for getDano
	 * @return Dano
	 */
	public Dano getDano() {
		return dano;
	}

	
	/**
	 * Comment for getMaterial
	 * @return Material
	 */
	public Material getMaterial() {
		return material;
	}

	
	/**
	 * Comment for getProteccion
	 * @return Proteccion
	 */
	public Proteccion getProteccion() {
		return proteccion;
	}

	
	/**
	 * Comment for setCodigoVoltaje
	 * @param integer
	 */
	public void setCodigoVoltaje(Integer integer) {
		codigoVoltaje = integer;
	}

	
	/**
	 * Comment for setDano
	 * @param dano
	 */
	public void setDano(Dano dano) {
		this.dano = dano;
	}

	
	/**
	 * Comment for setMaterial
	 * @param material
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}

	
	/**
	 * Comment for setProteccion
	 * @param proteccion
	 */
	public void setProteccion(Proteccion proteccion) {
		this.proteccion = proteccion;
	}
	/**
     * 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object objeto) {
		boolean resultado;
		if (!(objeto instanceof ProvolmaID)) {
			resultado = false;
		} else {
			ProvolmaID provolmaID = (ProvolmaID) objeto;
			return new EqualsBuilder().append(getCodigoVoltaje(), provolmaID.getCodigoVoltaje()).append(getMaterial(), 
					provolmaID.getMaterial()).append(getDano(),provolmaID.getDano()).append(getProteccion(), provolmaID.getProteccion()).isEquals();
		}
		return resultado;
	}
	
	
	/**
     * 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getCodigoVoltaje()).append(getMaterial()).append(getDano()).append(getProteccion()).toHashCode();
	}
}
