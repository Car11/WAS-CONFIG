package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.Glosario.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Glosario.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 26/03/2007</p>
 * <p>Ultima actualización: 26/03/2007/p>
 * @author Vista Verde Soft (Cesar y Doc Cristian)
 * @version 1.1
 */
public class Glosario implements Serializable{
	
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4535394490756487319L;

    /**
	 * <code>termino</code> Termino Glosario
	 */
	private String termino;
	
	/**
	 * <code>descripcion</code> Descripcion Glosario
	 */
	private String descripcion;
	
	

	
	/**
	 * Comment for getDescripcion
	 * @return Descripcion Glosario
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Comment for setDescripcion
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Comment for getTermino
	 * @return Termino Glosario
	 */
	public String getTermino() {
		return termino;
	}
	
	/**
	 * Comment for setTermino
	 * @param termino
	 */
	public void setTermino(String termino) {
		this.termino = termino;
	}
    
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */ 
    public boolean equals(Object objeto) {
        boolean resultado;
        if (!(objeto instanceof Glosario)) {
            resultado = false;
        } else {
            Glosario glosario = (Glosario) objeto;
            return new EqualsBuilder().append(getTermino(), glosario.getTermino()).isEquals();
        }
        return resultado;
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder().append(getTermino()).toHashCode();
    }        
    
}
