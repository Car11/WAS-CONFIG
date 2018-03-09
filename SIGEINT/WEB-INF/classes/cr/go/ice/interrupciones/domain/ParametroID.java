package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.ParametroID.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ParametroID.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 13/12/2006</p>
 * <p>Ultima actualización: 13/12/2006</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class ParametroID implements Serializable {
	
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4249700621896454032L;
    /**
	 * <code>generacionIndices</code> Generacion de Indices
	 */
	private String generacionIndices;
	/**
	 * <code>congelarSeccion</code> Congelar Seccion
	 */
	private String congelarSeccion;
	
	
	
	/**
	 * Comment for getCongelarSeccion
	 * @return Congelar Seccion
	 */
	public String getCongelarSeccion() {
		return congelarSeccion;
	}


	/**
	 * Comment for setCongelarSeccion
	 * @param congelarSeccion
	 */
	public void setCongelarSeccion(String congelarSeccion) {
		this.congelarSeccion = congelarSeccion;
	}


	/**
	 * Comment for getGeneracionIndices
	 * @return Generacion de Indice
	 */
	public String getGeneracionIndices() {
		return generacionIndices;
	}


	/**
	 * Comment for setGeneracionIndices
	 * @param generacionIndices
	 */
	public void setGeneracionIndices(String generacionIndices) {
		this.generacionIndices = generacionIndices;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof ParametroID)) {
			return false;
		} else {
			ParametroID parametroID = (ParametroID) obj;
			return new EqualsBuilder().append(this.getCongelarSeccion(), parametroID.getCongelarSeccion())
			.append(this.getGeneracionIndices(), parametroID.getGeneracionIndices())
			.isEquals();
		}
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(this.getCongelarSeccion()).append(this.getGeneracionIndices()).toHashCode();
	}

	
}
