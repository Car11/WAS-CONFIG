package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.Provolma.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Provolma.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 13/12/2006</p>
 * <p>Ultima actualización: 13/12/2006</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class Provolma implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -1348330572825668687L;
    /**
	 * <code>provolmaID</code> Provolma Identificacion
	 */
	private ProvolmaID provolmaID;
		
	
	/**
	 * Comment for getProvolmaID
	 * @return provolma Identificacion
	 */
	public ProvolmaID getProvolmaID() {
		return provolmaID;
	}
	
	
	/**
	 * Comment for setProvolmaID
	 * @param provolmaID
	 */
	public void setProvolmaID(ProvolmaID provolmaID) {
		this.provolmaID = provolmaID;
	}
	/**
     * 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object objeto) {
		boolean resultado;
		if (!(objeto instanceof Provolma)) {
			resultado = false;
		} else {
			Provolma provolma = (Provolma) objeto;
			resultado = new EqualsBuilder().append(getProvolmaID(), provolma.getProvolmaID()).isEquals();
		}
		return resultado;
	}
	
	
	/**
     * 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getProvolmaID()).toHashCode();
	}
}
