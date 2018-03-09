package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.Parametro.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Parametro.java</code> permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 23/05/2007</p>
 * <p>Ultima actualización: 23/05/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class Parametro implements Serializable{

	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3283097615189219901L;
    /**
	 * <code>parametroID</code> parametro ID
	 */
	private ParametroID parametroID;
	
	
	/**
	 * Metodo accesor de parametroID.
	 * @return Retorna el parametroID.
	 */
	public ParametroID getParametroID() {
		return parametroID;
	}
	/**
	 * Metodo modificador de parametroID.
	 * @param parametroID El parametroID a modificar.
	 */
	public void setParametroID(ParametroID parametroID) {
		this.parametroID = parametroID;
	}
	
	
	
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Parametro)) {
			return false;
		} else {
			Parametro parametro = (Parametro) obj;
			return new EqualsBuilder().append(this.getParametroID(), parametro.getParametroID())			
			.isEquals();
		}
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(this.getParametroID()).toHashCode();
	}
}
